/*****************************************************************************
 * 프로그램명  : AssemblyServiceImpl.java
 * 설     명  : Assembly 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01   왕윤아    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.assembly.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.assembly.constant.AssemblyConstKey;
import com.eaction.framework.business.logic.assembly.dao.AssemblyDao;
import com.eaction.framework.business.logic.assembly.model.AssemblyFileInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.business.logic.assembly.model.GtcXmlVO;
import com.eaction.framework.business.logic.escalate.service.MutiLanguageString;
import com.eaction.framework.business.logic.escalate.service.MutiLanguageString.StringWithLanguage;
import com.eaction.framework.business.logic.escalate.service.ObjectAssortmentFactory;
import com.eaction.framework.business.logic.escalate.service.ObjectMetaDataFactory;
import com.eaction.framework.business.logic.escalate.service.ObjectSubSetFactory;
import com.eaction.framework.business.logic.escalate.service.PackageAssortment;
import com.eaction.framework.business.logic.escalate.service.PackageAssortment.Item;
import com.eaction.framework.business.logic.escalate.service.PackageMetaData;
import com.eaction.framework.business.logic.escalate.service.PackageMetaData.Language;
import com.eaction.framework.business.logic.escalate.service.PackageSubsetAssortment;
import com.eaction.framework.business.logic.escalate.service.PackageSubsetAssortment.Subset;
import com.eaction.framework.business.logic.escalate.service.PackageSubsetAssortment.Subset.Items;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Assembly 관리 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@EnableAsync
public class AssemblyServiceImpl implements AssemblyService {	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( AssemblyServiceImpl.class );
	
	/** 클래스 명칭 */
	private final String CLASS_NAME = getClass().getName();

	/** Assembly 관리 DAO */
	@Resource
	private AssemblyDao assemblyDao;
		
	
	
	/**
	 * Assembly 리스트+개수
	 * @param basketInfo
	 * @return Map
	 * @throws UserSysException
	 */
	@Override
	public Map<String, Object> selectAssemblyListInfo(AssemblyInfo info) throws UserSysException {
		logger.debug("selectAssemblyListInfo--{}", ConstKey.START_LOG);
		Map<String, Object> result = new HashMap<>();
		
		int nTotal=0;
		List<AssemblyInfo> list = new ArrayList<>();
		
		//더보기 버튼 관련 공통 처리
		info.setIsPageYn(ConstKey.KEY_Y);
		info.setPageCount(Integer.parseInt(ConfigMng.getValue(IPropertyKey.NORMAL_PAGE_ROWNUM)));
		
		try{
			nTotal = assemblyDao.selectAssemblyListCnt(info);
			list = assemblyDao.selectAssemblyList(info);
						
			//Assembly별 아이템리스트 set
//			for(AssemblyInfo itemList : list){
//				List<AssemblyInfo> tmpList = assemblyDao.selectAssemblyItemList(itemList);
//				int adp_cnt = 0;
//				for(int i=0; i<tmpList.size(); i++){
//					//아이템의 Assembly Type 조회(ADP, TL, INS)
//					//String assem_type = getAssemType(tmpList.get(i).getMatnr());
//					//tmpList.get(i).setAssy_type(assem_type);
//					
//		
//					AssemblyInfo assemDetail = getAssembly(tmpList.get(i).getMatnr());
//					tmpList.get(i).setAssy_type(assemDetail.getAssy_type());
//					tmpList.get(i).setItemADP(assemDetail.getItemADP());
//					tmpList.get(i).setItGroupADP(assemDetail.getItGroupADP());
//					
//					tmpList.get(i).setItemINS(assemDetail.getItemINS());
//					tmpList.get(i).setItGroupINS(assemDetail.getItGroupINS());
//										
//					tmpList.get(i).setItemTL(assemDetail.getItemTL());
//					tmpList.get(i).setItGroupTL(assemDetail.getItGroupTL());
//					
//					tmpList.get(i).setCtype(assemDetail.getCtype());
//					itemList.get(i).setArr_csw(assemDetail.getArr_csw());
//					
//					if("ADP".equals(assemDetail.getAssy_type())) {
//						adp_cnt++;
//					}
//					
//				}
//				
//				//아답터 총갯수
//				
//				for(int i=0; i<tmpList.size(); i++){
//					if("ADP".equals(tmpList.get(i).getAssy_type())) {
//						tmpList.get(i).setAdp_cnt(adp_cnt);
//					}
//				}
//				
//				
//				itemList.setAssembly_detail_list(tmpList);
//			}

			
		}catch (Exception e){
			logger.debug("ERROR IN SELECT ASSEMBLY LIST INFO SERVICE :: "+ e.getMessage());
		}
		
		result.put("nTotal", nTotal);
		result.put("list", list);
		
		logger.debug("selectAssemblyListInfo--{}", ConstKey.END_LOG);

    	return result;
	}
	
	/**
	 * Assembly 개수
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public int selectAssemblyListCnt(AssemblyInfo info) throws UserSysException {
		return assemblyDao.selectAssemblyListCnt(info);
	}
	
	/**
	 * Assembly 리스트
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public List<AssemblyInfo> selectAssemblyInfoList(AssemblyInfo info) throws UserSysException {
		return null;
	}
	
	/**
	 * Assembly 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean deleteAssembly(AssemblyInfo info) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		if(info.getDeleteParam() !=null){
			for(String no : info.getDeleteParam()){
				info.setAssem_no(no);
				//Assembly 아이템이 있는 경우 아이템 먼저 삭제 후 Assembly 그룹 삭제해야
				int itemCnt = assemblyDao.selectAssemblyItemListCnt(info);
				
				if(itemCnt !=0){
					for(int i =0; i<itemCnt; i++){
						deleteAssemblyItem(info);
						deleteAssemblyItemLoc(info);
					}
				}
				iResult += assemblyDao.deleteAssembly(info);
			}
		}

    	if(iResult != info.getDeleteParam().length) {
    		bResult = false;
    	}
    	
    	//아이템삭제 후, Assembly FIle 초기화
    	if(bResult){
    		assemblyDao.updateAssemblyFileInit(info);
    	}
    	
    	return bResult;
	}
	
	/**
	 * Assembly 아이템 삭제 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean deleteAssemblyItem(AssemblyInfo info) throws UserSysException {
		boolean bResult = true;
    	int iResult = assemblyDao.deleteAssemblyItem(info);
    	
    	if(iResult == 1){
			bResult = true;
    	}else{
			bResult = false;
    	}
		
    	return bResult;
	}
	
	/**
	 * Assembly 아이템 Location 삭제 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean deleteAssemblyItemLoc(AssemblyInfo info) throws UserSysException {

		boolean bResult = true;
    	int iResult = assemblyDao.deleteAssemblyLocation(info);
    	
    	if(iResult >= 1){
			bResult = true;
    	}else{
			bResult = false;
    	}
		
    	return bResult;
	}	
	
	/**
	 * Assembly 등록
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertAssembly(AssemblyInfo info) throws UserSysException {
		boolean bResult = true;
		String result = "";
		int arrLength = 0;
		int iResult = 0;
		if(!"".equals(info.getAddParam())){
			JSONArray jsonFilter = JSONArray.fromObject(info.getAddParam());
			arrLength = jsonFilter.size();
			for(int n=0; n<jsonFilter.size(); n++) {
				JSONObject jo = (JSONObject)jsonFilter.get(n);
				String assem_nm = jo.getString("assem_nm");
				String assem_desc = jo.getString("assem_desc");
				AssemblyInfo insertNewAssem = new AssemblyInfo();
				insertNewAssem.setAssem_nm(assem_nm);
				insertNewAssem.setAssem_desc(assem_desc);
				insertNewAssem.setLogin_user_id(info.getLogin_user_id());
				
				/*try{
					assemblyDao.insertAssembly(insertNewAssem);
				}catch(Exception e){
					bResult=false;
					logger.debug(e.getMessage());
				}*/
				iResult += assemblyDao.insertAssembly(insertNewAssem);
		    	logger.debug("Assembly Insert JSONArray : :" + assem_nm +" : "+ assem_desc);
			}
		}
    	
    	return bResult;
	}

	/**
	 * Assembly 수정
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateAssembly(AssemblyInfo info) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		int arrLength = 0;
		if(!"".equals(info.getEditParam())){
			JSONArray jsonFilter = JSONArray.fromObject(info.getEditParam());
			for(int n=0; n<jsonFilter.size(); n++) {
				JSONObject jo = (JSONObject)jsonFilter.get(n);
				String assem_no = jo.getString("assem_no");
				String assem_nm = jo.getString("assem_nm");
				String assem_desc = jo.getString("assem_desc");
				AssemblyInfo insertNewAssem = new AssemblyInfo();
				insertNewAssem.setAssem_no(assem_no);
				insertNewAssem.setAssem_nm(assem_nm);
				insertNewAssem.setAssem_desc(assem_desc);
				iResult += assemblyDao.updateAssembly(insertNewAssem);
				logger.debug("Assembly Update JSONArray : :" + assem_no +" : "+ assem_nm+" : "+assem_desc);
			}
		}
		

    	if(iResult != arrLength) {
    		bResult = false;
    	}
    	
    	return bResult;
		
	}
	
	/**
	 * 장바구니 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AssemblyInfo> Cart List
	 */
	@Override
    public List<AssemblyInfo> selectAssemCartList(AssemblyInfo info) throws UserSysException {
		return assemblyDao.selectAssemCartList(info);
    }
	
	/**
	 * 장바구니 및 장바구니에 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertCartItem(AssemblyInfo info) throws UserSysException {
		boolean bResult = true;		
		// Cart No가 없으면 장바구니 생성 후, 신규 장바구니에 Item Insert
		String[] tmpArr = info.getMatnr().split(",");
		if("".equals(info.getCart_no())) {
			int cnt = assemblyDao.insertCart(info);
			if(cnt == 1) {
				for(int y = 0; y < tmpArr.length; y++){
					info.setMatnr(tmpArr[y]);
					int itemCnt = assemblyDao.selectDuplicatedItemCnt(info);
					//동일한  아이템이 있으면 수량 +1
					if(itemCnt == 1){
						assemblyDao.updateCartItemQty(info);
					}else{
						//동일한 아이템이 없으면 insert
						assemblyDao.insertCartItem(info);
					}
				}
				
			}else {
	    		bResult = false;
			}
		// 기존 장바구니에 Item Insert	
		}else {
			for(int x = 0; x < tmpArr.length; x++){
				info.setMatnr(tmpArr[x]);
				int itemCnt = assemblyDao.selectDuplicatedItemCnt(info);
				//동일한  아이템이 있으면 수량 +1
				if(itemCnt == 1){
					assemblyDao.updateCartItemQty(info);
				}else{
					//동일한 아이템이 없으면 insert
					assemblyDao.insertCartItem(info);
				}
			}
		}
    	
    	return bResult;
	}
	
	/**
	 * Assembly 아이템 리스트 조회
	 * @param paramBasketInfo
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Map<String, List<AssemblyInfo>> selectAssemblyItemListInfo(AssemblyInfo info) throws Exception {
					
		List<AssemblyInfo> itemList = assemblyDao.selectAssemblyItemList(info);
		int adp_cnt = 0;
		for(int i=0; i<itemList.size(); i++){			

			AssemblyInfo assemDetail = null;
			try {
				assemDetail = getAssembly(itemList.get(i).getMatnr());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.debug(e.getMessage());
			}
			itemList.get(i).setAssy_type(assemDetail.getAssy_type());
//			itemList.get(i).setItemADP(assemDetail.getItemADP());
//			itemList.get(i).setItGroupADP(assemDetail.getItGroupADP());
			
//			itemList.get(i).setItemINS(assemDetail.getItemINS());
//			itemList.get(i).setItGroupINS(assemDetail.getItGroupINS());
//								
//			itemList.get(i).setItemTL(assemDetail.getItemTL());
//			itemList.get(i).setItGroupTL(assemDetail.getItGroupTL());
			
			itemList.get(i).setItemMS(assemDetail.getItemMS());
			itemList.get(i).setItemWS(assemDetail.getItemWS());
			
			itemList.get(i).setCtype(assemDetail.getCtype());
			itemList.get(i).setArr_csw(assemDetail.getArr_csw());
			
			if("ADP".equals(assemDetail.getAssy_type())) {
				adp_cnt++;
			}
			
		}
		
		//아답터 총갯수
		for(int i=0; i<itemList.size(); i++){
			if("ADP".equals(itemList.get(i).getAssy_type())) {
				itemList.get(i).setAdp_cnt(adp_cnt);
			}
		}
		
		List<AssemblyInfo> ins_list = new ArrayList<AssemblyInfo>();
		List<AssemblyInfo> tl_adp_list = new ArrayList<AssemblyInfo>();
		
		// ADP 리스트 작성
		for(int i=0; i<itemList.size(); i++){	
			if("ADP".equals(itemList.get(i).getAssy_type())) {
				boolean exist = false;
				for(int k=0; k<tl_adp_list.size(); k++){
					if(itemList.get(i).getMatnr().equals(tl_adp_list.get(k).getMatnr())) {
						exist = true;
					}
				}
				// 리턴 리스트에 존재하지 않는 경우 추가
				if(exist == false) {
					tl_adp_list.add(itemList.get(i));
					List<AssemblyItemInfo> itemMS = itemList.get(i).getItemMS();
					List<AssemblyItemInfo> itemWS = itemList.get(i).getItemWS();
					if(itemMS != null) {
						for(AssemblyItemInfo item :itemMS) {
							for(int j=0; j<itemList.size(); j++){
								if("ADP".equals(itemList.get(j).getAssy_type())) {
									if(itemList.get(j).getMatnr().equals(item.getMatnr())) {
										tl_adp_list.add(tl_adp_list.size()-1, itemList.get(j));
									}
								}
								
							}
						}
					}
					if(itemWS != null) {
						for(AssemblyItemInfo item :itemWS) {
							for(int j=0; j<itemList.size(); j++){
								if("ADP".equals(itemList.get(j).getAssy_type())) {
									if(itemList.get(j).getMatnr().equals(item.getMatnr())) {
										tl_adp_list.add(itemList.get(j));
									}
								}
							}
						}
					}
				}
				
			}
		}
		
		// TOOL 리스트 추가
		for(int i=0; i<itemList.size(); i++){
			if("TL".equals(itemList.get(i).getAssy_type())) {
				boolean exist = false;
				for(int k=0; k<tl_adp_list.size(); k++){
					if(itemList.get(i).getMatnr().equals(tl_adp_list.get(k).getMatnr())) {
						exist = true;
					}
				}
				if(exist == false) {
					tl_adp_list.add(itemList.get(i));
					List<AssemblyItemInfo> itemMS = itemList.get(i).getItemMS();
					List<AssemblyItemInfo> itemWS = itemList.get(i).getItemWS();
					if(itemMS != null) {
						for(AssemblyItemInfo item :itemMS) {
							for(int j=0; j<itemList.size(); j++){
								if("TL".equals(itemList.get(j).getAssy_type())) {
									if(itemList.get(j).getMatnr().equals(item.getMatnr())) {
										tl_adp_list.add(tl_adp_list.size()-1, itemList.get(j));
									}
								}
							}
						}
					}
					if(itemWS != null) {
						for(AssemblyItemInfo item :itemWS) {
							for(int j=0; j<itemList.size(); j++){
								if("TL".equals(itemList.get(j).getAssy_type())) {
									if(itemList.get(j).getMatnr().equals(item.getMatnr())) {
										tl_adp_list.add(itemList.get(j));
									}
								}
								
							}
						}
					}
				}
			}
		}
		
		// INS 리스트 작성
		for(int i=0; i<itemList.size(); i++){
			if("INS".equals(itemList.get(i).getAssy_type())) {
				ins_list.add(itemList.get(i));
			}
		}
		
		// 화면에 표시할 종합 리스트 작성
		List<AssemblyInfo> tot_list = new ArrayList<AssemblyInfo>();
		tot_list.addAll(tl_adp_list);
		tot_list.addAll(ins_list);
		
		if(tl_adp_list.size() > 0) {
			
			// ADP/TOOL 리스트 처음에 ADD버튼 추가(MS가 있는 경우)
			AssemblyInfo firstTlAdp = tl_adp_list.get(0);
			List<AssemblyItemInfo> itemMS = firstTlAdp.getItemMS();
			if(itemMS != null && itemMS.size() > 0) {
				AssemblyInfo addBtnInfo = new AssemblyInfo();
				addBtnInfo.setAdd_btn(true);
				addBtnInfo.setItemMS(itemMS);
				
				List<AssemblyItemInfo> itGroup = getItGroup(itemMS);
				if(itGroup != null && itGroup.size() > 0) {
					addBtnInfo.setItGroupMS(itGroup);
					addBtnInfo.setSaCdList(getSaCd(itGroup));
				}
				
				tl_adp_list.add(0, addBtnInfo);

			}
		
			// ADP/TOOL 리스트 마지막에 ADD버튼 추가(WS가 있는 경우)
			AssemblyInfo lastTlAdp = tl_adp_list.get(tl_adp_list.size()-1);
			List<AssemblyItemInfo> itemWS = lastTlAdp.getItemWS();
			if(itemWS != null && itemWS.size() > 0) {
				AssemblyInfo addBtnInfo = new AssemblyInfo();
				addBtnInfo.setAdd_btn(true);
				
				//체결할수 있는 아이템그룹 코드 리스트
				List<AssemblyItemInfo> itGroup = getItGroup(itemWS);
				
				if("TL".equals(lastTlAdp.getAssy_type())) {
					// 마지막이 TOOL인 경우(INS에 Add 버튼을 추가)
					addBtnInfo.setItemMS(itemWS);
					if(itGroup != null && itGroup.size() > 0) {
						addBtnInfo.setItGroupMS(itGroup);
						addBtnInfo.setSaCdList(getSaCd(itGroup));
					}
					ins_list.add(0, addBtnInfo);
				} else {
					// 마지막 ADP인 경우(ADP/TOOL에 Add 버튼을 추가)
					addBtnInfo.setItemWS(itemWS);
					if(itGroup != null && itGroup.size() > 0) {
						addBtnInfo.setItGroupWS(itGroup);
						addBtnInfo.setSaCdList(getSaCd(itGroup));
					}
					tl_adp_list.add(addBtnInfo);
				}

			}
		}
		
		// ADP/TOOL 리스트에  ADD버튼 추가(최초에 INS를 추가한 경우)
		if(ins_list.size() > 0) {
			AssemblyInfo lastIns = ins_list.get(ins_list.size()-1);
			List<AssemblyItemInfo> insMS = lastIns.getItemMS();
			if(insMS != null && insMS.size() > 0 && tl_adp_list.size() == 0) {
				AssemblyInfo addBtnInfo = new AssemblyInfo();
				addBtnInfo.setAdd_btn(true);
				addBtnInfo.setItemMS(insMS);
				List<AssemblyItemInfo> itGroup = getItGroup(insMS);
				if(itGroup != null && itGroup.size() > 0) {
					addBtnInfo.setItGroupMS(itGroup);
					addBtnInfo.setSaCdList(getSaCd(itGroup));
				}
				
				tl_adp_list.add(addBtnInfo);
			}
			
		}
				
		Map<String, List<AssemblyInfo>> item_list = new HashMap<String, List<AssemblyInfo>>();
		item_list.put("tot_list", tot_list);
		item_list.put("tl_adp_list", tl_adp_list);
		item_list.put("ins_list", ins_list);
		
		return item_list;
	}
	
	public List<AssemblyItemInfo> getItGroup(List<AssemblyItemInfo> itMWS) throws Exception {
		//체결할수 있는 아이템그룹 코드 리스트
		String[] matnr_arr = new String[itMWS.size()];
		for(int j=0; j<itMWS.size(); j++) {
			matnr_arr[j] = itMWS.get(j).getMatnr();
		}
		AssemblyItemInfo itemInfo = new AssemblyItemInfo();
		itemInfo.setMatnr_arr(matnr_arr);
		List<AssemblyItemInfo> itGroup = assemblyDao.getAssyIgGroup(itemInfo);
		
		return itGroup;
	}
	
	public List<AssemblyItemInfo> getSaCd(List<AssemblyItemInfo> igList) throws Exception {
		//서브어플리케이션 코드 리스트
		String[] ig_cd_arr = new String[igList.size()];
		for(int j=0; j<igList.size(); j++) {
			ig_cd_arr[j] = igList.get(j).getIg_cd();
		}
		AssemblyItemInfo itemInfo = new AssemblyItemInfo();
		itemInfo.setIg_cd_arr(ig_cd_arr);
		List<AssemblyItemInfo> saCdList = assemblyDao.selectSaCd(itemInfo);
		
		return saCdList;
	}
		
	/**
	 * 장바구니 아이템 목록 전체 GTC 파일리스트 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public List<AssemblyInfo> selectAssemblyItemFileList(AssemblyInfo info) throws UserSysException {
		List<AssemblyInfo> itemList = assemblyDao.selectAssemblyItemList(info);
		List<AssemblyInfo> gtcFileList = new ArrayList<>();
		if(itemList.size() !=0){
			for(AssemblyInfo item : itemList){
				AssemblyInfo file = assemblyDao.selectAssemblyItemFile(item);
					gtcFileList.add(file);
			}
		}
		return gtcFileList;
	}
	
	
	
	
	/* ##################################################################### */
	
	/**
	 * 아이템의 Assembly Type 조회 (ADP, TL, INS 중 확인)
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getAssemType(String matnr) throws Exception{
		List<AssemblyInfo> gtcList = null;	
		AssemblyInfo assyVO = null;
				
		gtcList = assemblyDao.getGtcHierarchyList(matnr);
		if(gtcList == null || gtcList.size() == 0) {
			gtcList = assemblyDao.getPlmHierarchyList(matnr);			
			if(gtcList != null && gtcList.size() > 0) {
				assyVO = (AssemblyInfo) gtcList.get(0);
			}

		} else {
			if(gtcList.size() > 0) {
				assyVO = (AssemblyInfo) gtcList.get(0);
			}
		}
		
		String assemType = "";
		
		if(assyVO != null) {
			assyVO.setMatnr(matnr);
			
			// GTC 계층구조 Depth로 결정한다.
			if(assyVO.getGtc_depth() != null && !"".equals(assyVO.getGtc_depth())) {
				String depth[] = assyVO.getGtc_depth().split("\\>");
				
				if(depth.length > 3) {
					// Assembly Type (ADP, TL, INS)
					//assyVO.setAssy_type(depth[1].trim());
					assemType = depth[1].trim();
					
					logger.debug("Assembly Type ====================================================>> " + assyVO.getAssy_type());
					
				}
			}
		}
		return assemType;
				
	}
		
	
	/**
	 * Assembly 정보를 조회한다.
	 * 
	 * @param matnr - 자재번호
	 * @return AssemblyVO
	 * @throws Exception
	 */
	public AssemblyInfo getAssembly(String matnr) throws Exception {
				
		List<AssemblyInfo> gtcList = null;	
		AssemblyInfo assyVO = null;
				
		gtcList = assemblyDao.getGtcHierarchyList(matnr);
		if(gtcList == null || gtcList.size() == 0) {
			gtcList = assemblyDao.getPlmHierarchyList(matnr);			
			if(gtcList != null && gtcList.size() > 0) {
				assyVO = (AssemblyInfo) gtcList.get(0);
			}

		} else {
			if(gtcList.size() > 0) {
				assyVO = (AssemblyInfo) gtcList.get(0);
			}
		}
		
		if(assyVO != null) {
			assyVO.setMatnr(matnr);
			
			List<AssemblyItemInfo> itemWS = new ArrayList<AssemblyItemInfo>();
			List<AssemblyItemInfo> itemMS = new ArrayList<AssemblyItemInfo>();			
			
			/*****************************************************
			 * GTC Hierarchy 구조
			 * : Assembly 순서 : 1 + 2 + 3
			 *****************************************************
			 * 1. Adaptive 	: CTL > ADP 
			 *****************************************************
			 * 2. Tool     	: CTL > TL
			 *****************************************************
			 *    Turning  	: CTL > TL > TRN 인경우
			 *    Solid    	: GTC_VD_ROOT값의 마지막 문자가 "S" (O) 경우 
			 *    Indexable	: GTC_VD_ROOT값의 마지막 문자가 "S" (X) 경우
			 *****************************************************
			 * 3. Cutting   : CTL > INS 
			 *****************************************************/
			
			// GTC 계층구조 Depth로 결정한다.
			if(assyVO.getGtc_depth() != null && !"".equals(assyVO.getGtc_depth())) {
				String depth[] = assyVO.getGtc_depth().split("\\>");
				
				if(depth.length > 3) {
					// Assembly Type (ADP, TL, INS)
					assyVO.setAssy_type(depth[1].trim());
					logger.debug("Assembly Type ====================================================>> " + assyVO.getAssy_type());
										
					// 선택된 아이템이 Tool인경우에는 어셈블리하기 위해서 Turning, Solid, Indexable인지를 먼저 판단해야 함.
					if("TL".equals(depth[1].toString())) {
						if("TRN".contentEquals(depth[2].trim())) {
							assyVO.setItem_type("TURNING");
						} else {
							if(assyVO.getGtc_vd_rt() != null && !"".contentEquals(assyVO.getGtc_vd_rt())) {
								String temp = assyVO.getGtc_vd_rt();
								temp = temp.substring(temp.length()-1);
								if("S".equals(temp)) {
									assyVO.setItem_type("SOLID");
								} else {
									assyVO.setItem_type("INDEXABLE");
								}
							}
						}
					}
					
					logger.debug("Item Type ====================================================>> " + assyVO.getItem_type());					
					
					// 선택된 아이템(자재번호)에 대한 필수정보 축출 (CCMS, CCWS, DCON-MS, DCONMS, DCON-WS, DCONWS, IIC)
					List<AssemblyInfo> propList = assemblyDao.getAssemblyInfo(matnr);
					Map<String, String> assyMap = new HashMap<String, String>();
					
					if(propList != null && propList.size() > 0) {
						for(int i=0; i<propList.size(); i++) {
							AssemblyInfo propVO = (AssemblyInfo) propList.get(i);
							
							if("CCMS".equals(propVO.getSymbol())) {
								assyMap.put("CCMS", propVO.getVal());
							}

							if("CCWS".equals(propVO.getSymbol())) {
								assyMap.put("CCWS", propVO.getVal());
							}

							if("DCON-MS".equals(propVO.getSymbol()) || "DCONMS".equals(propVO.getSymbol())) {
								assyMap.put("DCON-MS", propVO.getVal());
							}
							
							if("DCON-WS".equals(propVO.getSymbol()) || "DCONWS".equals(propVO.getSymbol())) {
								assyMap.put("DCON-WS", propVO.getVal());
							}
														
							if("IIC".equals(propVO.getSymbol())) {
								assyMap.put("IIC", propVO.getVal());
							}
						}
					}
										
					/************************************************************************************
					 * 0. 체결할 수 있는 아이템은 Adaptive, Tool, Insert만 체결 가능하다.
					 ************************************************************************************/						
					if("ADP".equals(depth[1].toString()) || "TL".equals(depth[1].toString()) || "INS".equals(depth[1].toString())) {
						/************************************************************************************
						 * 1. Insert
						 ************************************************************************************
						 * 체결규칙
						 * - 선택된 아이템이 Insert인 경우 MS쪽은 무조건 Tool이 체결되고 WS쪽으로는 더이상 체결될 아이템이 존재하지 않음.
						 * - IIC값에서 ISO$, KOR$ 제외하고 LEFT 6자리까지만 허용한다.
						 ************************************************************************************/						
						if("INS".equals(depth[1].toString())) {
							// ISO$, KOR$ 제외하고 LEFT 6자리까지 허용
							if(assyMap.size() > 0 && !"".equals(assyMap.get("IIC"))) {
								String temp = assyMap.get("IIC").replaceAll("\\{", "").replaceAll("\\}", "\\,");
								String arry[] = temp.split("\\,");										

								if(arry.length > 0) {
									for(int j=0; j<arry.length; j++) {
										String iic = arry[j].trim().replace("ISO$", "").replace("KOR$", "").substring(0, 6);												
										Map<String, String> map = new HashMap<String, String>();
										map.put("key1", "IIC");
										map.put("key2", "CCMS");
										map.put("val", iic);

										List<AssemblyItemInfo> item = assemblyDao.getAssyTool(map);

										// STP 파일정보 가져온다.
										if(item != null && item.size() > 0) {
											itemMS.addAll(item);
										}
									}
								}
							}
							
						} else {
							/************************************************************************************
							 * 2. Machine Side (MS)
							 ************************************************************************************
							 * 체결규칙
							 * - 선택된 아이템에 Machine Side로 체결할 수 있는 모든 아이템 (Adaptive, Tool)
							 * - CCMS의 값에서 LEFT 10 자리까지만 허용한다.
							 * - DCON-MS(구경)의 값이 체결할 아이템의 DCON-WS(구경)값과 일치하여야 한다.
							 ************************************************************************************/
							if(assyMap.size() > 0 && assyMap.get("CCMS") != null && assyMap.get("DCON-MS") != null) {
								assyVO.setCtype("MS");
								logger.debug("Machine Side >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
								logger.debug("CCMS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + assyMap.get("CCMS") + "<<<");
								logger.debug("DCON-MS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + assyMap.get("DCON-MS") + "<<<");
															
								if(assyMap.size() > 0 && !"".equals(assyMap.get("CCMS").trim()) && !"".equals(assyMap.get("DCON-MS").trim())) {
									String temp = assyMap.get("CCMS").replaceAll("\\{", "").replaceAll("\\}", "\\,");
									String arry[] = temp.split("\\,");
									
									if(arry.length > 0) {
										for(int i=0; i<arry.length; i++) {
											String ccms = arry[i].trim().substring(0, 10);

											// 1-1. MS쪽으로 더 체결할 수 있는 아이템이 있는 경우 (LEFT 10 자리까지 허용) = 체결할 아이템 WS쪽의 아이템을 찾는다.
											Map<String, String> map = new HashMap<String, String>();
											map.put("CTYP","MS");
											map.put("CCMS", ccms);
											map.put("DCON-MS", assyMap.get("DCON-MS"));
											
											// 자재번호 및 STP 파일정보 가져온다.
											List<AssemblyItemInfo> item = assemblyDao.getAssyAdaptive(map);											
											if(item != null && item.size() > 0) {
												itemMS.addAll(item);
											}
										}
									}
								}
							}
						
							/************************************************************************************
							 * 3. Workpiece Side (WS)
							 ************************************************************************************
							 * 규칙
							 * - 선택된 아이템에 Workpiece Side로 체결할 수 있는 모든 아이템 (Adaptive, Tool, Insert)
							 * - CCWS의 값에서 LEFT 10 자리까지만 허용한다.
							 * - DCON-WS(구경)의 값이 체결할 아이템의 DCON-MS(구경)값과 일치하여야 한다.
							 ************************************************************************************/
							if(assyMap.size() > 0 && ((assyMap.get("CCWS") != null && assyMap.get("DCON-WS") != null) || assyMap.get("IIC") != null)) {
								assyVO.setCtype("WS");
								logger.debug("Workpiece Side >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + assyMap.get("CCWS"));
								logger.debug("Workpiece Side >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + assyMap.get("DCON-WS"));
								logger.debug("Workpiece Side >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + assyMap.get("IIC"));
								

								// 3-1. 선택된 아이템이 Tool인 경우에 체결할 수 있는 Insert (ISO$, KOR$ 제외하고 LEFT 6자리까지 허용)								
								if("TL".contentEquals(assyVO.getAssy_type())) {								
									if(!"".equals(assyMap.get("IIC").trim()) && assyMap.get("CCWS") == null) {
										String temp = assyMap.get("IIC").replaceAll("\\{", "").replaceAll("\\}", "\\,");
										String arry[] = temp.split("\\,");

										if(arry.length > 0) {
											for(int j=0; j<arry.length; j++) {
												String iic = arry[j].trim().replace("ISO$", "").replace("KOR$", "").substring(0, 6);
												List<AssemblyItemInfo> item = assemblyDao.getAssyInsert(iic);
												
												// STP 파일정보 가져온다.
												if(item != null && item.size() > 0) {
													itemWS.addAll(item);
												}
											}
										}
									}
									
								// 3-2. 선택된 아이템이 Adaptive인 경우에는 체결할 수 있는 아이템이 Tool, Adaptive (LEFT 10 자리까지 허용)									
								} else {
									// 3-3. 체결할 수 있는 Adaptive, Tool (LEFT 10 자리까지 허용)
									if(!"".equals(assyMap.get("CCWS").trim()) && !"".equals(assyMap.get("DCON-WS").trim()) && assyMap.get("IIC") == null) {
										String temp = assyMap.get("CCWS").replaceAll("\\{", "").replaceAll("\\}", "\\,");
										String arry[] = temp.split("\\,");
										
										if(arry.length > 0) {
											//List<AssyItemVO> item = new ArrayList<AssyItemVO>();
											for(int i=0; i<arry.length; i++) {
												String ccws = arry[i].trim().substring(0, 10);

												// 3-4. MS쪽으로 더 체결할 수 있는 아이템이 있는 경우 (LEFT 10 자리까지 허용) = 체결할 아이템 WS쪽의 아이템을 찾는다.
												Map<String, String> map = new HashMap<String, String>();
												map.put("CTYP","WS");										
												map.put("CCWS", ccws);
												map.put("DCON-WS", assyMap.get("DCON-WS"));
	
												// 자재번호 및 STP 파일정보 가져온다.												
												List<AssemblyItemInfo> item = assemblyDao.getAssyAdaptive(map);
												if(item != null && item.size() > 0) {
													itemWS.addAll(item);
												}										
											}
										}
									}
								}
							}
							
							if("TL".equals(depth[1].toString())) {
								// CSW 목록
								List<String> arr_csw = getCSW(matnr);
								if(arr_csw.size() > 0) {
									assyVO.setArr_csw(arr_csw);
								}
							}
						}					
					}
				}
				
				// Machine Side (MS)
				if(itemMS != null && itemMS.size() > 0) assyVO.setItemMS(itemMS);
				
				// Workpiece Side (WS)
				if(itemWS != null && itemWS.size() > 0) assyVO.setItemWS(itemWS);
			}
						
		}		
		
		return assyVO;
	}


	
	/**
	 * Assembly 생성
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Async("threadPoolTaskExecutor")
	public void createAssembly(HttpServletRequest request, AssemblyInfo assemblyInfo) throws UserSysException {
		boolean bResult = true;
		int ret = 0;
		String msg = "";
						
		try {
			String destDir = ConfigMng.getValue("KORLOY_UPLOAD_PATH");
	
			//xml 생성.
			AssemblyInfo vo = makeAssemblyXml(request, assemblyInfo, destDir);
			
			//생성 성공시.
			if(vo != null) {
				logger.debug("executeAssemblyCreate = 1");
				//프로그램 실행.
				if(makeAssembly(vo, destDir) == 0) {
					//프로그램 실행 후 데이터가 정상적으로 만들어졌을 경우.
					make3D(vo, destDir);
					logger.debug("executeAssemblyCreate = 2");
					List<String> arr_checkP21 = checkP21Data(vo);
					logger.debug("executeAssemblyCreate = 3");
					int p21_ret = 0; 
					//p21 파일이 없는 자재번호가 존재할 경우 GTC 패키지 때문에 생성. (어셈블리 파일 제외, 자재번호만)
					if(arr_checkP21 != null && arr_checkP21.size() > 0) {
						logger.debug("executeAssemblyCreate = 4");
						GtcXmlVO tempGtcVO = new GtcXmlVO();
						tempGtcVO.setArr_matnr(arr_checkP21);
						
						GtcXmlVO p21VO = insertP21Data(tempGtcVO);
						logger.debug("executeAssemblyCreate = 5");
						if(p21VO != null && p21VO.getArr_matnr() != null && p21VO.getArr_matnr().size() > 0) {
							logger.debug("executeAssemblyCreate = 6");
							//프로그램 실행.
							if(makeP21() == 0) {
								logger.debug("executeAssemblyCreate = 7");
								p21_ret = insertFile(p21VO.getArr_matnr(), "CC0028", destDir);
								logger.debug("executeAssemblyCreate = 8 " + p21_ret + ", " + p21VO.getArr_matnr().size());
								//p21이 준비 안되면 작업 중단.
								if(p21_ret != p21VO.getArr_matnr().size()) {
									p21_ret = -1;
								}
							} 
						}
					}
					
					//GTC package 생성
					if(p21_ret >= 0) {
						ret = makeAssyGTCPackage(vo, destDir, assemblyInfo);
						if(ret <= 0) {
							msg = "GTC package 생성 실패";
							bResult = false;
							logger.debug(msg);
							//throw new UserSysException (CLASS_NAME, "makeAssembly", new Exception(msg));
						}
					}
					
					// 어셈블리 마스터 테이블 업데이트
					if(ret > 0) {
						ret = assemblyDao.updateAssembly(assemblyInfo);
						if(ret <= 0) {
							msg = "어셈블리 마스터 테이블 업데이트 실패";
							bResult = false;
							logger.debug(msg);
							//throw new UserSysException (CLASS_NAME, "makeAssembly", new Exception(msg));
						}
					}
					
				} else {
					msg = "Assembly 파일 생성 실패";
					bResult = false;
					logger.debug(msg);
				}
			} else {
				msg = "xml 생성 실패";
				bResult = false;
				logger.debug(msg);
			}
						
		} catch (Exception e) {
			msg = e.getMessage();
			if(msg.getBytes().length > 499 ) {
				msg = new String(msg.getBytes(), 0, 499);
			}
			bResult = false;
			//throw new UserSysException (CLASS_NAME, "makeAssembly", e);
		}
		
//		if(ret <= 0) {
//			bResult = false;
//		}
		
		// 생성 상태 변경
		if(bResult == false) {
			assemblyInfo.setBuld_msg(msg);
			assemblyInfo.setBuld_sta("FAIL");
		} else {
			try {
				assemblyInfo.setBuld_sta("OK");
				// 생성 성공하면 Location정보 삭제 후, 등록
				assemblyInfo.setMatnr("");
				assemblyDao.deleteAssemblyLocation(assemblyInfo);
				if(assemblyInfo.getItemINS() != null) {
					for(int i=0; i<assemblyInfo.getItemINS().size(); i++){
						assemblyInfo.getItemINS().get(i).setAssem_no(assemblyInfo.getAssem_no());
						assemblyInfo.getItemINS().get(i).setLogin_user_id(assemblyInfo.getLogin_user_id());
						assemblyDao.insertAssemblyLocation(assemblyInfo.getItemINS().get(i));
					}
				}
			} catch (Exception e) {
				msg = e.getMessage();
				if(msg.getBytes().length > 499 ) {
					msg = new String(msg.getBytes(), 0, 499);
				}
				bResult = false;
				assemblyInfo.setBuld_msg(msg);
				assemblyInfo.setBuld_sta("FAIL");
			}
		}
		assemblyDao.updateAssemblyState(assemblyInfo);
    	
	}
	
	/**
	 * Assembly xml 파일 생성.
	 * 
	 * @throws Exception
	 */
	public AssemblyInfo makeAssemblyXml(HttpServletRequest request, AssemblyInfo assemblyInfo, String destDir) throws Exception {
		// TODO Auto-generated method stub
		if(assemblyInfo == null) {
			return null;
		}
		
		boolean isFailGen = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		//public static final String UPLOAD_ECAT_ASSEMBLY_FILE           = "/data/assembly"; 			// 대표이미지, ISO 치수도면 이미지, Non-ISO 치수도면 이미지
		String assyFolderPath = String.format("%s%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE });
		
		//일자별로 폴더 생성 후 넣기로 함.
		//public static final String UPLOAD_ECAT_ASSEMBLY_XML             		= "/ecat/assembly";		//GTC Package
		String xmlFolderPath = String.format("%s%s/%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_XML,  sdf.format(new Date()) });
				
		//전달 받은 파일명 앞에 시간넣어서 중복 방지 하기로 함.
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileNameNoExt = sdfTime.format(new Date()) + "_" + assemblyInfo.getFileName();
		
		logger.debug("fileNamePrefix = " + fileNameNoExt);
		
		if(!Files.exists(Paths.get(xmlFolderPath))) {
			Files.createDirectories(Paths.get(xmlFolderPath));
		}
		
		//basic, detail stp 파일 임시 폴더에 복사 해야 함. 
		//동시 실행시 캐디언스에서 만든 exe 파일이 실행 중 멈춤. 
		//해당 폴더에 prt 파일을 생성하다 보니. 실행 시 xml상 중복 접근해야 하는 파일이 있을 경우 prt 파일 생성 실패.
		//캐디언스는 자기 영역 아니라고 함. 그래서 임시폴더에 복사 후 만듬.... 만들고 나선 또 지워야지....
		//public static final String UPLOAD_ECAT_ASSEMBLY_TEMP             		= "/ecat/assembly_temp";
		String tempFolderPath = String.format("%s%s/%s", 
												new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_TEMP,  fileNameNoExt });
		if(!Files.exists(Paths.get(tempFolderPath))) {
			Files.createDirectories(Paths.get(tempFolderPath));
		}
	
		//CUTTING에 들어갈 location 추출. TOOL에 있는 detail stp 파일에서 추출.
		//List<String> arr_csw = getCSW(AssemblyInfo.getMatnr());
		//AssemblyInfo.setArr_csw("1-06-055453_detail.stp");//"1-06-055453_detail.stp"
		
		//XML 생성.
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        //루트 엘리먼트
    	Document doc = docBuilder.newDocument();
    	Element rootElement = doc.createElement("Request");
    	doc.appendChild(rootElement);
    	
    	//루트 속성값.
    	rootElement.setAttribute("service", "AssemblyStp");
    	
    	//타입 
    	Element productElement = doc.createElement("Product");
    	rootElement.appendChild(productElement);
    	
    	Element productItemElement = doc.createElement("Item");
    	
    	if(assemblyInfo.getItemTL() == null || assemblyInfo.getItemTL().size() <= 0) {
    		throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception("TOOL은 무조건 1개 이상 있어야 있어야 합니다."));
    	}
    	
    	String assyType = getAssyType(assemblyInfo.getItemTL().get(0).getMatnr());
    	
    	if(StringUtils.isBlank(assyType)) {
    		throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception("assy Type이 정확하지 않습니다. : " + assyType));
    	}
    	
    	productItemElement.setAttribute("type", assyType);		
    	productElement.appendChild(productItemElement);
    	
    	//실제 파일들.
    	Element itemsElement = doc.createElement("Items"); 
    	rootElement.appendChild(itemsElement);
    	
    	String matnr = "";
    	
    	//ADAPTIVE
    	if(assemblyInfo.getItemADP() != null && assemblyInfo.getItemADP().size() > 0) {
    		for(AssemblyItemInfo adp : assemblyInfo.getItemADP()) {
    			//자재번호만 받을 수도 있어서... 정보 다 넘어오거든 adp에서 받도록 수정.
    			AssemblyItemInfo vo = assemblyDao.getSTPFiles(adp.getMatnr());
    			if(vo == null) {
    				continue;
    			}
    			
    			adp.setStp_basic((StringUtils.isBlank(vo.getStp_basic())?"" : vo.getStp_basic()));
				adp.setStp_detail((StringUtils.isBlank(vo.getStp_detail())?"" : vo.getStp_detail()));
    			
    			//temp로 파일 복제.
    			vo = copyAssyItems(tempFolderPath, vo);
				if(vo == null) {
					throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception(adp.getStp_basic() + " 또는 " + adp.getStp_detail() + " 파일을 복사하는데 실패하였습니다."));
				} 
				
				logger.debug("assy cop = 1 ab " + adp.getStp_basic());
				logger.debug("assy cop = 1 ad " + adp.getStp_detail());
				
    			Element itemElement = doc.createElement("Item");
    	    	itemElement.setAttribute("designation", ("ADP".equalsIgnoreCase(adp.getAssy_type())? "ADAPTIVE" : adp.getAssy_type()));
    	    	itemElement.setAttribute("detail-file", StringUtils.isBlank(vo.getStp_detail())? vo.getStp_basic() : vo.getStp_detail());
    	    	itemElement.setAttribute("basic-file", StringUtils.isBlank(vo.getStp_basic())? vo.getStp_detail() : vo.getStp_basic());
    	    	itemsElement.appendChild(itemElement);
    		}
    	}
    	
    	//TOOL
    	if(assemblyInfo.getItemTL() != null && assemblyInfo.getItemTL().size() > 0) {
    		for(AssemblyItemInfo tool : assemblyInfo.getItemTL()) {
    			//자재번호만 받을 수도 있어서... 정보 다 넘어오거든 tool에서 받도록 수정.
    	    	AssemblyItemInfo vo = assemblyDao.getSTPFiles(tool.getMatnr());
    	    	if(vo == null || (StringUtils.isBlank(vo.getStp_detail()) && StringUtils.isBlank(vo.getStp_basic()))) {
    	    		isFailGen = true;
    	    		matnr = matnr + "," + tool.getMatnr();
    				continue;
    			}
    	    	
    	    	tool.setStp_basic((StringUtils.isBlank(vo.getStp_basic())?"" : vo.getStp_basic()));
				tool.setStp_detail((StringUtils.isBlank(vo.getStp_detail())?"" : vo.getStp_detail()));
    	    	
    	    	//temp로 파일 복제.
    	    	vo = copyAssyItems(tempFolderPath, vo);
				if(vo == null) {
					throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception(tool.getStp_basic() + " 또는 " + tool.getStp_detail() + " 파일을 복사하는데 실패하였습니다."));
				} 
				
				logger.debug("assy cop = 1 tb " + tool.getStp_basic());
				logger.debug("assy cop = 1 td " + tool.getStp_detail());
    	    	
    			Element itemElement = doc.createElement("Item");
    	    	itemElement.setAttribute("designation",  ("TL".equalsIgnoreCase(tool.getAssy_type())? "TOOL" : tool.getAssy_type()));
    	    	itemElement.setAttribute("detail-file", StringUtils.isBlank(vo.getStp_detail())? vo.getStp_basic() : vo.getStp_detail());
    	    	itemElement.setAttribute("basic-file", StringUtils.isBlank(vo.getStp_basic())? vo.getStp_detail() : vo.getStp_basic());
    	    	itemElement.setAttribute("overhang", StringUtils.isBlank(tool.getOverhang())? "0" : tool.getOverhang());
    	    	itemsElement.appendChild(itemElement);
    		}
    	}
    	
    	//CUTTING
    	if(assemblyInfo.getItemINS() != null && assemblyInfo.getItemINS().size() > 0) {
    		for(AssemblyItemInfo cutting : assemblyInfo.getItemINS()) {
    			//자재번호만 받을 수도 있어서... 정보 다 넘어오거든 cutting에서 받도록 수정.
    	    	AssemblyItemInfo vo = assemblyDao.getSTPFiles(cutting.getMatnr());
    	    	if(vo == null) {
    				continue;
    			}
    	    	
    	    	cutting.setStp_basic((StringUtils.isBlank(vo.getStp_basic())?"" : vo.getStp_basic()));
				cutting.setStp_detail((StringUtils.isBlank(vo.getStp_detail())?"" : vo.getStp_detail()));
    	    	
    	    	//temp로 파일 복제.
    	    	vo = copyAssyItems(tempFolderPath, vo);
				if(vo == null) {
					throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception(cutting.getStp_basic() + " 또는 " + cutting.getStp_detail() + " 파일을 복사하는데 실패하였습니다."));
				} 
				
				logger.debug("assy cop = 1 ib " + cutting.getStp_basic());
				logger.debug("assy cop = 1 id " + cutting.getStp_detail());
    	    	
    			Element itemElement = doc.createElement("Item");
    	    	itemElement.setAttribute("designation", ("INS".equalsIgnoreCase(cutting.getAssy_type())? "CUTTING" : cutting.getAssy_type()));
    	    	itemElement.setAttribute("detail-file", StringUtils.isBlank(vo.getStp_detail())? "" : vo.getStp_detail());
    	    	itemElement.setAttribute("basic-file", StringUtils.isBlank(vo.getStp_basic())? "" : vo.getStp_basic());
    	    	itemElement.setAttribute("location", cutting.getLocation());
    	    	itemsElement.appendChild(itemElement);
    		}
    	}
    	
    	//TOOL은 무조건 1개가 있어야 한다. 아이템이 1개면 단품이라 그냥 패스...
    	if(isFailGen || itemsElement.getChildNodes().getLength() < 2) {
    		if(!"".equals(matnr)) {
    			matnr = matnr.substring(1);
    		}
    		throw new UserSysException (CLASS_NAME, "makeAssemblyXml", new Exception(matnr + " : 연결된 STP 파일 없음"));
    	}
    	
    	//로그및 결과 파일 생성 경로 등.
    	Element outputsElement = doc.createElement("Outputs"); 
    	rootElement.appendChild(outputsElement);
    	
    	//Assembly 결과 파일 네이밍 룰. Constants에 정의함.
    	//public static final String[] ARR_ASSEMBLY_SUFFIX = {"_detail.stp", "_basic.stp", ".dxf", "_list.txt", "_log.log", ".xml" };
    	
    	//detail 결과 파일.
    	Element outDetailElement = doc.createElement("Output");
    	outDetailElement.setAttribute("type", "Detail-STP");
    	String detailFileName = fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[0];
    	outDetailElement.setAttribute("file-path", getOutputFilePath(assyFolderPath, detailFileName )); 
    	outputsElement.appendChild(outDetailElement);
    	
    	//basic 결과 파일.
    	Element outBasicElement = doc.createElement("Output");
    	outBasicElement.setAttribute("type", "Basic-STP");
    	String basicFileName = fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[1];
    	outBasicElement.setAttribute("file-path", getOutputFilePath(assyFolderPath, basicFileName )); 
    	outputsElement.appendChild(outBasicElement);
    	
    	//dxf 결과 파일.
    	Element dxfElement = doc.createElement("Output");
    	dxfElement.setAttribute("type", "DXF");
    	String dxfFileName = fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[2];
    	dxfElement.setAttribute("file-path", getOutputFilePath(assyFolderPath,  dxfFileName));
    	outputsElement.appendChild(dxfElement);
    	
    	//part-list 결과 파일.
    	Element partListElement = doc.createElement("Output");
    	partListElement.setAttribute("type", "Part-List");
    	partListElement.setAttribute("file-path", getOutputFilePath(xmlFolderPath,  fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[3]));
    	outputsElement.appendChild(partListElement);
    	
    	//Log 파일.
    	Element logElement = doc.createElement("Output");
    	logElement.setAttribute("type", "Log");
    	logElement.setAttribute("file-path",getOutputFilePath(xmlFolderPath, fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[4]));
    	outputsElement.appendChild(logElement);
    	
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	 
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        
        String xmlFilePath = getOutputFilePath(xmlFolderPath, fileNameNoExt + AssemblyConstKey.ARR_ASSEMBLY_SUFFIX[5]);
        
        FileOutputStream fos = new FileOutputStream(new File(xmlFilePath));

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fos);

        transformer.transform(source, result);
        
        if(fos != null)
        	fos.close();
        
        if(!Files.exists(Paths.get(xmlFilePath)) && Files.size(Paths.get(xmlFilePath)) <= 0) {
        	
        	return null;
        }
        
        assemblyInfo.setMatnr(fileNameNoExt);
        assemblyInfo.setXmlFileFullPath(xmlFilePath);
        assemblyInfo.setAssyDetailFileName(detailFileName);
        assemblyInfo.setAssyBasicFileName(basicFileName);
        assemblyInfo.setAssyDxfFileName(dxfFileName);
        assemblyInfo.setTempFolderPath(tempFolderPath);
    	
		return assemblyInfo;
	}
	
	/**
	 * Assyembly에서 타입을 가져온다.
	 * @param matnr 자재번호.
	 * @return
	 */
	@Override
	public String getAssyType(String matnr) {
		if(StringUtils.isBlank(matnr)) {
			return "";
		}
		
		String assy_type = "";
		
		List<AssemblyInfo> gtcList = null;	
		AssemblyInfo assyVO = null;
		
		try {
			
			gtcList = assemblyDao.getGtcHierarchyList(matnr);
			if(gtcList == null || gtcList.size() == 0) {
				gtcList = assemblyDao.getPlmHierarchyList(matnr);
				if(gtcList != null && gtcList.size() > 0) {			
					assyVO = (AssemblyInfo) gtcList.get(0);
				}
							
			} else {
				if(gtcList.size() > 0) {
					assyVO = (AssemblyInfo) gtcList.get(0);
				}
			}
			
			if(assyVO != null) {	
				// GTC 계층구조 Depth로 결정한다.
				if(assyVO.getGtc_depth() != null && !"".equals(assyVO.getGtc_depth())) {
					String depth[] = assyVO.getGtc_depth().split("\\>");
					
					if(depth.length > 3) {
						if("TL".equals(depth[1].toString())) {
							if("TRN".contentEquals(depth[2].trim())) {
								assy_type = "TURNING";
							} else {
								if(assyVO.getGtc_vd_rt() != null && !"".contentEquals(assyVO.getGtc_vd_rt())) {
									String temp = assyVO.getGtc_vd_rt();
									temp = temp.substring(temp.length()-1);
									if("S".equals(temp)) {
										assy_type = "SOLID";
									} else {
										assy_type = "INDEXABLE";
									}
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return assy_type;
	}
	
	/**
	 * basic, detail 파일 복사.
	 * @param tempFolderPath
	 * @param item
	 * @return
	 */
	public AssemblyItemInfo copyAssyItems(String tempFolderPath, AssemblyItemInfo item) {
		try {
			
			if(!StringUtils.isBlank(item.getStp_basic())) {
				String basic = copyAssyItem(tempFolderPath, item.getStp_basic());
				if(!StringUtils.isBlank(basic)) {
					item.setStp_basic(basic);
					logger.debug("assy cop = 2 " + basic);
				} else {
					return null;
				}
			}
			
			if(!StringUtils.isBlank(item.getStp_detail())) {
				String detail = copyAssyItem(tempFolderPath, item.getStp_detail());
				if(!StringUtils.isBlank(detail)) {
					item.setStp_detail(detail);
					logger.debug("assy cop = 3 " + detail);
				} else {
					return null;
				}
			}
			
			return item;
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 캐디언스에서 만든 exe가 동일한 파일을 사용할 경우 prt create시 오류로 문제발생.
	 * 관련하여 미리 다른 폴더에 복사해서 처리해야 함.
	 * @param tempFolderPath	복사할 디렉토리 경로.
	 * @param item 복사할 파일 정보.
	 * @return
	 */
	public String copyAssyItem(String tempFolderPath, String item) {
		
		try {
			//파일 복사.
			if(!StringUtils.isBlank(item)) {
				Path path = Paths.get(item);
				Path cpPath = Paths.get(tempFolderPath + "/" + path.getFileName().toString());	
				
				logger.debug("assy cop = 4 " + path.toAbsolutePath().toString());
				logger.debug("assy cop = 4 " + cpPath.toAbsolutePath().toString());
				
				if(Files.exists(path)) {
					Files.copy(path, cpPath, StandardCopyOption.REPLACE_EXISTING);
				}
				
				if(Files.exists(cpPath)) {
					return cpPath.toAbsolutePath().toString();
				}
			}
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Assembly 결과 파일 경로 생성.
	 * @param path
	 * @param name
	 * @param idx
	 * @return
	 */
	public String getOutputFilePath(String path, String name) {
		if(StringUtils.isBlank(name) || StringUtils.isBlank(path))
			return "";
		
		return Paths.get(String.format("%s/%s", new Object[] { path, name })).toAbsolutePath().toString();
	}
	
	/**
	 * Assembly 파일 생성.
	 * @throws UserSysException 
	 * 
	 * @throws Exception
	 */
	public int makeAssembly(AssemblyInfo AssemblyInfo, String destDir) throws UserSysException {
		// TODO Auto-generated method stub
		if(true) {
			//execute(AssemblyInfo);
			//return -1;
			return sendAssy(AssemblyInfo);
		}
		
		int exitCode = -1;
		
		//프로퍼티에 넣을려고 했으나 전달해야 하는 소스 일 수 있어서 하드코딩.
		String execFilePath = "C:/Moldream/Assy/KORLOYStpAssy.exe";
		Path execPath = Paths.get(execFilePath);
		
		Path xmlPath = Paths.get(AssemblyInfo.getXmlFileFullPath());
		
		logger.debug("Assy = 1 " + xmlPath.toAbsolutePath().toString());
		
		if(!Files.exists(xmlPath)) {
			logger.debug("Assy = 1_1 " + xmlPath.toAbsolutePath().toString());
			return -1;
		}
		
		DefaultExecutor executor = new DefaultExecutor();

		ExecuteWatchdog watchDog = new ExecuteWatchdog(1000 * 60 * 30);

		executor.setWatchdog(watchDog);

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ExecuteStreamHandler stream = new PumpStreamHandler(bos, bos, null);

		executor.setStreamHandler(stream);
		
		CommandLine cmdLine = new CommandLine("cmd.exe");
		
		cmdLine.addArgument("/c");
		cmdLine.addArgument("start");
		//cmdLine.addArgument("/wait");
		
		cmdLine.addArgument(execPath.toAbsolutePath().toString(), true);
		cmdLine.addArgument(xmlPath.toAbsolutePath().toString(), true);
		
//		Path a = Paths.get("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//		cmdLine.addArgument(a.toAbsolutePath().toString(), true);
//		cmdLine.addArgument("https://www.google.com");
//		cmdLine.addArgument("-incognito");
		
		logger.debug("Assy = 2 " + cmdLine.toString());

		try {
			executor.execute(cmdLine, resultHandler);
			logger.debug("Assy = 2_1 " + cmdLine.toString());
			
//			String msg = "";
//			while ((msg = stream.setProcessErrorStream(is);) != null) {
//				logger.debug("Assy = ZZ  " + cmdLine.toString());
//            }
			//resultHandler.waitFor(1000 * 20);
			resultHandler.waitFor();
			logger.debug("Assy = 3 " + cmdLine.toString());
			
			exitCode = resultHandler.getExitValue();
			logger.debug("Assy = 4 " + cmdLine.toString() + ", " + exitCode);
			String assyFolderPath = String.format("%s%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE });
			if(!Files.exists(Paths.get(assyFolderPath + AssemblyInfo.getAssyDetailFileName())) 
					&& !Files.exists(Paths.get(assyFolderPath + AssemblyInfo.getAssyBasicFileName()))
					&& !Files.exists(Paths.get(assyFolderPath + AssemblyInfo.getAssyDxfFileName()))
					) {
				logger.debug("Assy = 5 " + cmdLine.toString());
				return -1;
			}
			
		} catch(Exception e) {
			logger.debug("Assy = ex " + cmdLine.toString());

		} finally {
			if(bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage());
				}
		}
		logger.debug("Assy = 6 " + cmdLine.toString());
		return exitCode;
	}
	
	public int sendAssy(AssemblyInfo AssemblyInfo) throws UserSysException {
		int port = 5000;
		String startFlag = "ACK ";
		String successFlag = "EXP SUC";
		String failFlag = "EXP END";
		int ret = -1;
		
		try (Socket client = new Socket()) {
			// 로컬:9999 포트에 서버에 접속한다.
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", port);
			client.setReuseAddress(true);
			client.connect(ipep);
            //client.setSoLinger(true, 1000);

			// OutputStream과 InputStream를 받는다.
			try (OutputStream send = client.getOutputStream(); 
					InputStream is = client.getInputStream();
					BufferedReader rd = new BufferedReader(new InputStreamReader(is));
					) {
				
				logger.debug("sendAssy =" + client.getRemoteSocketAddress().toString());
				
				String sendMsg = startFlag + AssemblyInfo.getXmlFileFullPath();
				
				ByteBuffer sendByteBuffer = null;
				 
	            sendByteBuffer = ByteBuffer.allocate(sendMsg.getBytes().length + 1);
	            sendByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	 
	            sendByteBuffer.put(sendMsg.getBytes());
	            sendByteBuffer.put(new byte[1]);
	 
	            send.write(sendByteBuffer.array());
	            send.flush();
	            
	            try {
	            	
					int read = 0;
	            	logger.debug("sendAssy = 1");
					while (true) {
						logger.debug("sendAssy = 2");
						if(client == null) {
							logger.debug("sendAssy = 3");
							break;
						}
						logger.debug("sendAssy = 4");
						
						String flag = rd.readLine();
						logger.debug("sendAssy = 6");
						logger.debug("sendAssy = " + ((flag == null)? "" : flag));
						
						if(flag != null && flag.startsWith("EXP")) {
							if(flag.equals(successFlag)) {
								logger.debug("sendAssy = END SUCCESS");		
								ret = 0;
							} else {
								logger.debug("sendAssy = END FAIL");
							}
							
							break;
						}
						logger.debug("sendAssy = 7");
					}
					logger.debug("sendAssy = 8");
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
	            logger.debug("sendAssy = 9");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new UserSysException (CLASS_NAME, "sendAssy", e);
		} finally {
			//성공이든 실패든 완료시 temp 파일 및 폴더 제거.
			deleteDirNFiles(AssemblyInfo.getTempFolderPath());
		}
		
		return ret;
	}
	
	/**
	 * 디렉토리 삭제. (하위 포함.)
	 * @param name 경로.
	 */
	public void deleteDirNFiles(String name) {
		try {
			if(Files.isDirectory(Paths.get(name))) {
				File rootDir = new File(name);
			    Files.walk(rootDir.toPath())
			        .sorted(Comparator.reverseOrder())
			        .map(Path::toFile)
			        .forEach(File::delete);
			} else {
				Files.deleteIfExists(Paths.get(name));
			}
		} catch(Exception e) {
			//실패해도 일단 무시.
		}
	}
	
	/**
	 * 3D view 파일 생성.
	 * @param fileName
	 * @param mode
	 * @return
	 * @throws UserSysException 
	 * @throws Exception
	 */
	public AssemblyInfo make3D(AssemblyInfo assemblyInfo, String destDir) throws UserSysException {
		// TODO Auto-generated method stub
		if(assemblyInfo == null) {
			logger.debug("make3D AssemblyInfo== " + "null");
			return null;
		}
		
		String assyFolderPath = String.format("%s%s/", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE });
		logger.debug("make3D assyFolderPath== " + assyFolderPath);
				
		int make3DCode1 = -1;
		int make3DCode2 = -1;
		int make3DCode3 = -1;
		
		logger.debug("make3D AssyBasicFileName== " + assemblyInfo.getAssyBasicFileName());
		//어셈블리 파일은 따로 관리하기에 일단 파일 비교만.. basic
		if(!StringUtils.isBlank(assemblyInfo.getAssyBasicFileName()) && Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyBasicFileName()))) {
			make3DCode1 = make3DFile(assemblyInfo.getAssyBasicFileName(), false, destDir);
		}
		
		//어셈블리 파일은 따로 관리하기에 일단 파일 비교만.. detail
		logger.debug("make3D AssyDetailFileName== " + assemblyInfo.getAssyDetailFileName());
		if(!StringUtils.isBlank(assemblyInfo.getAssyDetailFileName()) && Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyDetailFileName()))) {
			make3DCode2 = make3DFile(assemblyInfo.getAssyDetailFileName(), false, destDir);
		}
		
		//어셈블리 파일은 따로 관리하기에 일단 파일 비교만.. dxf
		logger.debug("make3D AssyDxfFileName== " + assemblyInfo.getAssyDxfFileName());
		if(!StringUtils.isBlank(assemblyInfo.getAssyDxfFileName()) && Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyDxfFileName()))) {
			make3DCode3 = make3DFile(assemblyInfo.getAssyDxfFileName(), true, destDir);
		}
		
		logger.debug("make3DCode1== " + make3DCode1);
		logger.debug("make3DCode2== " + make3DCode2);
		logger.debug("make3DCode3== " + make3DCode3);
		
		if(!Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyBasicFileName()))) {
			logger.debug("make3D Not exist basic : " + Paths.get(assyFolderPath + assemblyInfo.getAssyBasicFileName()));
		}
		if(!Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyDetailFileName()))) {
			logger.debug("make3D Not exist detail : " + Paths.get(assyFolderPath + assemblyInfo.getAssyDetailFileName()));
		}
		if(!Files.exists(Paths.get(assyFolderPath + assemblyInfo.getAssyDxfFileName()))) {
			logger.debug("make3D Not exist dfx : " + Paths.get(assyFolderPath + assemblyInfo.getAssyDxfFileName()));
		}
		
		if(make3DCode1 == -1 && make3DCode2 == -1 && make3DCode3 == -1) {
			throw new UserSysException (CLASS_NAME, "make3D", new Exception("2D/3D 파일이 생성되지 않았습니다."));
		}
		
		if(make3DCode1 != -1) {
			String stp_b_file = AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyInfo.getMatnr() + "_basic.stp";
			logger.debug("stp_b_file== " + stp_b_file);
			assemblyInfo.setStp_b_file(stp_b_file);
		}
		if(make3DCode2 != -1) {
			String stp_d_file = AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyInfo.getMatnr() + "_detail.stp";
			logger.debug("stp_d_file== " + stp_d_file);
			assemblyInfo.setStp_d_file(stp_d_file);
		}
		if(make3DCode3 != -1) {
			String dxf_file = AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyInfo.getMatnr() + ".dxf";
			logger.debug("dxf_file== " + dxf_file);
			assemblyInfo.setDxf_file(dxf_file);
		}
				
		return assemblyInfo;
	}
	
	/**
	 * 3D view 파일 생성. (dxf 파일도 생성.)
	 * @param fileName 각 파일 명
	 * @param isDxf dxf의 경우 실행 파일이 달라 구분하기 위함. 
	 * @return
	 * @throws UserSysException 
	 */
	public int make3DFile(String fileName, boolean isDxf, String destDir) throws UserSysException {
		if(StringUtils.isBlank(fileName)) {
			logger.debug("make3DFile fileName=" + fileName);
			return -1;
		}
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int exitCode = -1;
		
		try {
		
			String assyOriFilePath = String.format("%s%s/%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE, fileName });
			logger.debug("make3DFile assyOriFilePath=" + Paths.get(assyOriFilePath));
			
			if(!Files.exists(Paths.get(assyOriFilePath))) {
				return -1;
			}
			
			//public static final String UPLOAD_3DVIEWER_FILE_EXT      						= ".vizw";					//3d viewer file ext
			String outName =  FilenameUtils.getBaseName(fileName) + AssemblyConstKey.UPLOAD_3DVIEWER_FILE_EXT;
			
			//dxf to jpg 의 경우 jpg로 생성.
			logger.debug("make3DFile isDxf=" + isDxf);
			if(isDxf) {
				outName =  FilenameUtils.getBaseName(fileName) + ".jpg";
			}
			
			DefaultExecutor executor = new DefaultExecutor();
	
			ExecuteWatchdog watchDog = new ExecuteWatchdog(1000 * 60 * 30);
	
			executor.setWatchdog(watchDog);
	
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			resultHandler.onProcessComplete(0);
			
			ExecuteStreamHandler stream = new PumpStreamHandler(bos, bos, null);
	
			executor.setStreamHandler(stream);
			
			String outPath = String.format("%s%s/%s", 
										new Object[] { destDir, (isDxf?AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE : AssemblyConstKey.UPLOAD_3DVIEWER_FILE ), outName});
			logger.debug("make3DFile outPath= " + outPath);
			
			CommandLine cmdLine = new CommandLine("cmd.exe");
			
			cmdLine.addArgument("/c");
			cmdLine.addArgument("start");
			
			if(isDxf) {
				cmdLine.addArgument( Paths.get(ConfigMng.getValue("DXFTOJPG_EXE_PATH")).toAbsolutePath().toString());
				cmdLine.addArgument("-i");
				cmdLine.addArgument(Paths.get(assyOriFilePath).toAbsolutePath().toString(), true);
				cmdLine.addArgument("-o");
				cmdLine.addArgument(Paths.get(outPath).toAbsolutePath().toString(), true);
			} else {
				cmdLine.addArgument( Paths.get(ConfigMng.getValue("3DVIEW_EXE_PATH")).toAbsolutePath().toString());
				cmdLine.addArgument("-i");
				cmdLine.addArgument(Paths.get(assyOriFilePath).toAbsolutePath().toString(), true);
				cmdLine.addArgument("-ovizw");
				cmdLine.addArgument(Paths.get(outPath).toAbsolutePath().toString(), true);
				cmdLine.addArgument("-mode");
				cmdLine.addArgument("web");
				//cmdLine.addArgument("-log");
				//cmdLine.addArgument("2");
				cmdLine.addArgument("-cref");
				cmdLine.addArgument("t");
				cmdLine.addArgument("-freec");
				cmdLine.addArgument("t");
				cmdLine.addArgument("-freep");
				cmdLine.addArgument("t");
			}
			
			logger.debug("make3DFiles Esc= " + cmdLine.toString());
		
			executor.setExitValue(0);
			executor.execute(cmdLine, resultHandler);
			resultHandler.waitFor();
			
			exitCode = resultHandler.getExitValue();
			
//			if(!Files.exists(Paths.get(outPath))) {
//				logger.debug("make3DFile outPath : " + Paths.get(outPath) + " 파일이 존재하지 않습니다.");
//				return -1;
//			}
			
			logger.debug("make3DFile exitCode : " + exitCode);
			return exitCode;
			
		} catch(Exception e) {
			logger.debug(e.getMessage());
			return -1;
			
		} finally {
			if(bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage());
				}
		}
			
	}
	
	/**
	 * 어셈블리에 사용한 자재번호 중 p21 파일이 없는 파일 찾기. (생성하기 위함.)
	 * @param AssemblyInfo
	 * @return
	 */
	public List<String> checkP21Data(AssemblyInfo AssemblyInfo) {
		
		if(AssemblyInfo == null)
			return null;
		
		//어셈블리 세부 아이템 
		List<AssemblyItemInfo> items = new ArrayList<AssemblyItemInfo>();
		//확인할 자재번호들.
		List<String> arr_matnr = new ArrayList<String>();
		
		try {
			if(AssemblyInfo.getItemADP() != null && AssemblyInfo.getItemADP().size() > 0) {
				items.addAll(AssemblyInfo.getItemADP());
			}
			
			if(AssemblyInfo.getItemTL() != null && AssemblyInfo.getItemTL().size() > 0) {
				items.addAll(AssemblyInfo.getItemTL());
			}
			
			if(AssemblyInfo.getItemINS() != null && AssemblyInfo.getItemINS().size() > 0) {
				items.addAll(AssemblyInfo.getItemINS());
			}
			
			for(AssemblyItemInfo vo : items) {
				if(!StringUtils.isBlank(vo.getMatnr())) {
					AssemblyFileInfo fileVO = new AssemblyFileInfo();
					fileVO.setFile_cd(vo.getMatnr());
					fileVO.setFile_typ("CC0028"); //p21
					//파일 존재여부 DB 조회
					String path = assemblyDao.getAssyFilePath(fileVO);
					if(StringUtils.isBlank(path)) {
						if(!arr_matnr.contains(vo.getMatnr())) {
							arr_matnr.add(vo.getMatnr());
						}
					} else {
						//실제 물리경로에 파일 있는지 확인.
						if(!Files.exists(Paths.get(path))) {
							if(!arr_matnr.contains(vo.getMatnr())) {
								arr_matnr.add(vo.getMatnr());
							}
						}
					}
				}
			}
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return arr_matnr;
	}
	
	/**
	 * P21 파일 데이터 insert.
	 * 
	 * @throws Exception
	 */
	public GtcXmlVO insertP21Data(GtcXmlVO gtcXmlVO) throws Exception {
		if(gtcXmlVO == null || gtcXmlVO.getArr_matnr() == null || gtcXmlVO.getArr_matnr().size() <= 0) {
			return null;
		}
		
		//기존 데이터 삭제.
		assemblyDao.deleteP21Data(gtcXmlVO);
		
		logger.debug("executeAssemblyCreate = 767676");
		
		//새로 선택한 데이터 저장.
		for(String matnr : gtcXmlVO.getArr_matnr()) {
			logger.debug("executeAssemblyCreate = 7575");
			assemblyDao.insertP21Data(matnr);
		}
		
		GtcXmlVO retVO = new GtcXmlVO();
		retVO.setArr_matnr(assemblyDao.selectP21Data());
		logger.debug("executeAssemblyCreate = 747474 " + retVO.getArr_matnr().size());
		
		return retVO;
	}
	
	/**
	 * P21 파일 생성.
	 * 
	 * @throws Exception
	 */
	public int makeP21() throws Exception {
		// TODO Auto-generated method stub
		
		int exitCode = -1;
		
		DefaultExecutor executor = new DefaultExecutor();

		ExecuteWatchdog watchDog = new ExecuteWatchdog(1000 * 60 * 60);

		executor.setWatchdog(watchDog);

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		resultHandler.onProcessComplete(0);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ExecuteStreamHandler stream = new PumpStreamHandler(bos, bos, null);

		executor.setStreamHandler(stream);
		
		CommandLine cmdLine = new CommandLine("cmd.exe");
		
		cmdLine.addArgument("/c");
		
		cmdLine.addArgument( Paths.get(ConfigMng.getValue("P21_BAT_PATH")).toAbsolutePath().toString());

		try {
			executor.setExitValue(0);
			executor.execute(cmdLine, resultHandler);
			resultHandler.waitFor();
			
			exitCode = resultHandler.getExitValue();
			logger.debug("executeAssemblyCreate = 777 " + exitCode);
			
		} catch(Exception e) {
			logger.debug(e.getMessage());
		} finally {
			if(bos != null)
				bos.close();
		}
		
		return exitCode;
	}
	
	/**
	 * P21 파일 확인 및 파일 테이블에 insert 단품만.(assembly는 따로 관리)
	 * @param GtcXmlVO
	 * @return
	 * @throws Exception
	 */
	public int insertFile(List<String> arrMatnr, String code, String destDir) throws Exception {
		// TODO Auto-generated method stub
		if(arrMatnr == null || arrMatnr.size() <= 0) {
			return 0;
		}
		
		int ret = 0;

		
		String suffix = "";
		String dwl_path = "";
		
		//여기서는 p21만 사용.
		if("CC0028".equalsIgnoreCase(code)) {
			//p21
			suffix = ".p21";
			dwl_path = AssemblyConstKey.UPLOAD_GTC_DATA_FILE;
		} else if("CC0104".equalsIgnoreCase(code)) {
			//3d basic
			suffix = AssemblyConstKey.UPLOAD_3DVIEWER_FILE_BASIC_SUFFIC;
			dwl_path = AssemblyConstKey.UPLOAD_3DVIEWER_FILE;
		} else if("CC0105".equalsIgnoreCase(code)) {
			//3d detail
			suffix = AssemblyConstKey.UPLOAD_3DVIEWER_FILE_DETAIL_SUFFIC;
			dwl_path = AssemblyConstKey.UPLOAD_3DVIEWER_FILE;
		}
		
		for(String matnr : arrMatnr) {
			String fileName = matnr + suffix;
			String fullPath = String.format("%s%s/%s", new Object[] { destDir, dwl_path, fileName });
			logger.debug("executeAssemblyCreate = 999 " + fullPath);
			if(!Files.exists(Paths.get(fullPath))) {
				logger.debug("executeAssemblyCreate = 989898 ");
				continue;
			}
			
			//나중에 update 됨. 사용 불가.
//			if("CC0028".equalsIgnoreCase(code)) {
//				logger.debug("executeAssemblyCreate = 9797 " + matnr);
//				int a = assemblyDao.selectP21Cnt(matnr);
//				if(a <= 0) {
//					logger.debug("executeAssemblyCreate = 9696 " + a);
//					continue;
//				}
//			}
			
			AssemblyFileInfo fileVO = new AssemblyFileInfo();
			fileVO.setWerks("1000");
			fileVO.setFile_cd(matnr);			//자재번호
			fileVO.setFile_org_nm(fileName);
			fileVO.setFile_phy_path(fullPath);
			fileVO.setFile_thn_nm(fileName);
			fileVO.setFile_typ(code);
			fileVO.setFile_dwl_path(dwl_path);
			fileVO.setUse_yn("Y");
			
			if(assemblyDao.getAssyFileCnt(fileVO) > 0) {
				logger.debug("executeAssemblyCreate = 9595 ");
				assemblyDao.deleteAssyFile(fileVO);
			}
			
			ret += assemblyDao.insertAssyFile(fileVO);
			logger.debug("executeAssemblyCreate = 9494 " + ret);
			
		}
		
		return ret;
	}
	
	public int makeAssyGTCPackage(AssemblyInfo assemblyVO, String destDir, AssemblyInfo assemblyInfo) throws Exception {
		
		if(assemblyVO == null || StringUtils.isBlank(assemblyVO.getMatnr())) {
			logger.debug("assemblyVO == null || StringUtils.isBlank(assemblyVO.getMatnr())");
			return 0;
		}
		
		if(assemblyVO.getItemTL() == null || assemblyVO.getItemTL().size() <= 0) {
			logger.debug("assemblyVO.getItemTL() == null || assemblyVO.getItemTL().size() <= 0");
			return 0;
		}
			
		
		List<GtcXmlVO> arrSelectGtcXmlVO = new ArrayList<GtcXmlVO>();
		
		
		//어셈블리 세부 아이템 
		List<AssemblyItemInfo> items = new ArrayList<AssemblyItemInfo>();
		//중복 안되는 자재번호들.
		List<String> arr_matnr = new ArrayList<String>();
		
		if(assemblyVO.getItemADP() != null && assemblyVO.getItemADP().size() > 0) {
			items.addAll(assemblyVO.getItemADP());
		}
		
		if(assemblyVO.getItemTL() != null && assemblyVO.getItemTL().size() > 0) {
			items.addAll(assemblyVO.getItemTL());
		}
		
		if(assemblyVO.getItemINS() != null && assemblyVO.getItemINS().size() > 0) {
			items.addAll(assemblyVO.getItemINS());
		}
		
		for(AssemblyItemInfo vo : items) {
			if(!StringUtils.isBlank(vo.getMatnr())) {
				if(!arr_matnr.contains(vo.getMatnr())) {
					arr_matnr.add(vo.getMatnr());
				}
			}
		}
		
		//META xml용 VO
		GtcXmlVO metaDataVO  = new GtcXmlVO();
		
		//각 자재번호로 GTC 패키지 정보 가져옴. package_assortment용도.
		for(String matnr : arr_matnr) {
			GtcXmlVO vo = new GtcXmlVO();
			vo.setMatnr(matnr);
			vo = assemblyDao.selectGtcXmlData(vo);
			if(vo != null) {
				//gtc_hier테이블에 vendor아이디가 없고 plm에 있을 경우 버전을 가져올 수 없어 임시로 제너릭 아이디로 버전 추출.
				if(StringUtils.isBlank(vo.getGtc_ver()) && !StringUtils.isBlank(vo.getGtc_gen_id())) {
					vo.setGtc_ver(assemblyDao.selectGtcVer(vo.getGtc_gen_id()));
				}
				arrSelectGtcXmlVO.add(vo);
				if(assemblyVO.getItemTL().get(0).getMatnr().equals(vo.getMatnr())) {
					metaDataVO = vo;
				}
				logger.debug("makeGTCPackageAssortment = 0 " + vo.getMatnr() + ", " + matnr);
				
			}
		}
		
		//subset xml용 VO.
		GtcXmlVO subSetVO  = new GtcXmlVO();
		subSetVO.setMatnr(assemblyVO.getMatnr());
		subSetVO.setArr_assy(arr_matnr);
		
		//meta data용 VO. tool 기준으로 처리하기로 함. (8.19)
		//metaDataVO.setMatnr(assemblyVO.getItemTL().get(0).getMatnr());
		//metaDataVO = assemblyDao.selectGtcXmlData(metaDataVO);
		
		//tool이 없으면 만들어지지 않음.
		if(StringUtils.isBlank(metaDataVO.getMatnr())) {
			logger.debug("tool이 없으면 만들어지지 않음.");
			return 0;
		}
			
		
		//tool + insert 정도는 있어야 어셈블리..
		if(arrSelectGtcXmlVO.size() < 2) {
			logger.debug("tool + insert 정도는 있어야 어셈블리..");
			return  0;
		}
		
		//package_assortment의 첫번째 자료는 어셈블리 p21 파일인데 해당 프로젝트에서는 어셈블리용 p21은 생성하지 않기로 함. (8.19)
		GtcXmlVO assyVO = new GtcXmlVO();
		assyVO.setMatnr(assemblyVO.getMatnr());
		assyVO.setGtc_gen_id(metaDataVO.getGtc_gen_id());
		assyVO.setVender_id(metaDataVO.getVender_id());
		assyVO.setGtc_ver(metaDataVO.getGtc_ver());
		assyVO.setUnit(metaDataVO.getUnit());
		assyVO.setP21_image_nm("");
		assyVO.setP21StructureChangeTimestamp("");
		assyVO.setP21ValueChangeTimestamp("");
		//public static final String UPLOAD_ECAT_ASSEMBLY_FILE           = "/data/assembly"; 			// 대표이미지, ISO 치수도면 이미지, Non-ISO 치수도면 이미지
		assyVO.setDxf_file_image_nm(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/"+ assemblyVO.getAssyDxfFileName());
		assyVO.setStp_file_image_nm(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/"+ assemblyVO.getAssyBasicFileName());
		assyVO.setStp_file_detail_image_nm(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/"+ assemblyVO.getAssyDetailFileName());
		arrSelectGtcXmlVO.add(0, assyVO);
		
		for(GtcXmlVO vo : arrSelectGtcXmlVO) {
			logger.debug("makeGTCPackageAssortment = 1 " + vo.getMatnr());
		}

		// gtc package 만들어질 경로
		String targetPath = assemblyVO.getMatnr();

		// 대상 경로를 확인하고 없으면 생성한다.
		//public static final String UPLOAD_ECAT_ASSEMBLY_FILE           = "/data/assembly"; 			// 대표이미지, ISO 치수도면 이미지, Non-ISO 치수도면 이미지
		String zipPath = String.format("%s%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE });
		String gtcPath = zipPath + "/" + targetPath;
		File gtcFolder = new File(gtcPath);
		gtcFolder.mkdirs();
		
		List<HashMap<String, String>> selectMap = assemblyDao.selectGtcPkgInfo("CM0012");//단품, 어셈블리용.
		HashMap<String, String> hMap = new HashMap<String, String>();
		for(HashMap<String, String> element: selectMap) {
			hMap.put(element.get("CODE_KEY"), element.get("CODE_VAL"));
	    }
		
		//파일 복사.
		if(makeGTCDirectoryAndCopyFiles(gtcPath, arrSelectGtcXmlVO, hMap, destDir) <= 0) {
			logger.debug("makeGTCDirectoryAndCopyFiles(gtcPath, arrSelectGtcXmlVO, hMap, destDir) <= 0");
			return 0;
		}
		
		if(makeGTCPackageAssortment(gtcPath, arrSelectGtcXmlVO, hMap, destDir) <= 0) {
			logger.debug("makeGTCPackageAssortment(gtcPath, arrSelectGtcXmlVO, hMap, destDir) <= 0");
			return 0;
		}
		
		makeGTCPackageMetaData(gtcPath, metaDataVO, hMap, assemblyVO.getMatnr());
		makeGTCPackageSubset(gtcPath, subSetVO, hMap);
		
		//korloy.gtc-package.1-02-056846.zip
		String zipfile = AssemblyConstKey.GTC_PACKAGE_ZIP_PREFIX + assemblyVO.getMatnr() + ".zip";
		compressZipGtcPackage(gtcPath, zipPath, zipfile);
		
//		assemblyInfo.setStp_b_file(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyVO.getMatnr() + "_basic.stp");
//		assemblyInfo.setStp_d_file(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyVO.getMatnr() + "_detail.stp");
//		assemblyInfo.setDxf_file(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + assemblyVO.getMatnr() + ".dxf");
		assemblyInfo.setGtc_file(AssemblyConstKey.UPLOAD_ECAT_ASSEMBLY_FILE + "/" + zipfile);
		
		return 1;
	}
	
	/**
	 * GTC Package중 파일 처리.
	 * @param gtcPath
	 * @param isFullPkg
	 * @return
	 * @throws Exception
	 */
	public int makeGTCDirectoryAndCopyFiles(String gtcPath, List<GtcXmlVO> arr_gtcXmlVO, HashMap<String, String> hMap, String destDir) throws Exception {
		logger.debug("method : makeGTCDirectoryAndCopyFiles");
		if(arr_gtcXmlVO == null || arr_gtcXmlVO.size() <= 0) {
			logger.debug("arr_gtcXmlVO == null || arr_gtcXmlVO.size() <= 0");
			return 0;
		}
	
		List<String> arrFolderKeys = Arrays.asList(AssemblyConstKey.ARR_GTC_FOLDER_KEY);
		
		//일단 기존 파일 삭제하고 새로 만든다.
		String zipFileName = gtcPath.substring(0, gtcPath.lastIndexOf("/") + 1) + AssemblyConstKey.GTC_PACKAGE_ZIP_PREFIX + arr_gtcXmlVO.get(0).getMatnr() + ".zip";	
		deleteDirNFiles(zipFileName);
		deleteDirNFiles(gtcPath);
		
		for(String key:hMap.keySet()) {
			//디렉토리 생성이라 파일은 제외.
			if(arrFolderKeys.contains(key)) {
				continue;
			}
			//폴더 생성.
			File file = new File(gtcPath(gtcPath, "/" + hMap.getOrDefault(key , ""), ""));
			file.mkdirs();
		}
		
		//String disclaimer = "/disclaimer/en-us/disclaimer_en.txt";
		//String product_brand_logos = "/product_brand_logos/korloy.png";
		String disclaimer = "/" + hMap.getOrDefault("1", "disclaimer");
		String packageLogo = hMap.getOrDefault("10", "package_logo.png");
		//이건 파일 하나라 하드코딩 하기로 함.
		String readmeTxt = "/readme.txt";	
		
		//String packageLogo = "/item_group/header/AlphaMill.jpg";
		//String readmeTxt = "/gtc_readme/readme.txt";
		
		//이건 언어별 여러개가 될 수 있어 복사를 디렉토리 복사로 해야 할듯... 단 여러개가 될 경우 xml에도 추가가 되야 해서 수정은 필요 함.
		String disclaimerFullPath = String.format("%s%s%s", new Object[] { destDir, AssemblyConstKey.GTC_PACKAGE_COPY_FOLDER, disclaimer });
		//String productBrandLogosFullPath = String.format("%s/%s%s", new Object[] { destDir, "data", product_brand_logos });
		String packageLogosFullPath = String.format("%s%s/%s", new Object[] { destDir, AssemblyConstKey.GTC_PACKAGE_COPY_FOLDER, packageLogo });
		String readmeTxtFullPath = String.format("%s%s%s", new Object[] { destDir, AssemblyConstKey.GTC_PACKAGE_COPY_FOLDER, readmeTxt });
	
		//디렉토리 복사.
		copyDirectory(disclaimerFullPath, gtcPath + disclaimer);
		
		//copyGtcFile(disclaimerFullPath, gtcPath, "", disclaimer);
		//단건 복제.
		//패키지가 코오로이고 브랜드가 알파밀 형태여야 한다고 전달 받음.
		//copyGtcFile(productBrandLogosFullPath, gtcPath, "", packageLogo);
		
		if(copyGtcFile(packageLogosFullPath, gtcPath, "", packageLogo) <= 0) {
			logger.debug("copyGtcFile(packageLogosFullPath, gtcPath, '', packageLogo) <= 0");
			return 0;
		}
		
		if(copyGtcFile(readmeTxtFullPath, gtcPath, "",  readmeTxt) <= 0) {
			logger.debug("copyGtcFile(readmeTxtFullPath, gtcPath, '',  readmeTxt) <= 0");
			return 0;
		}
		
		//product_2d_drawings, DXF 파일
		if(hMap.getOrDefault("2", "").length() > 0) {
			for(GtcXmlVO gtcXmlVO : arr_gtcXmlVO) {
				//DB에 저장된 파일이 있는 확인. (파일이 만들어져서 관리되고 있는지 여부 확인)
				if(!StringUtils.isBlank(gtcXmlVO.getDxf_file_image_nm())) {
					//파일이 있는데 복사가 실패하면 정상적이지 않은 GTC 패키지가 되니 중단 시킴.
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getDxf_file_image_nm()), gtcPath, 
							hMap.getOrDefault("2", ""), getFileName(gtcXmlVO.getDxf_file_image_nm())) <= 0) {
						logger.debug("product_2d_drawings, DXF 파일이 있는데 복사가 실패하면 정상적이지 않은 GTC 패키지가 되니 중단 시킴.");
						return 0;
					}
				}
			}
			
		}
		
		//product_3d_models_basic, Basic STP 파일 디렉토리
		if(hMap.getOrDefault("3", "").length() > 0) {
			for(int i = 0; i < arr_gtcXmlVO.size(); i++) {
				GtcXmlVO gtcXmlVO = arr_gtcXmlVO.get(i);
				if(!StringUtils.isBlank(gtcXmlVO.getStp_file_image_nm())) {
					//어셈블리된 vo는 0번째에 넣어둠. 추후 어셈블리용 베이직파일이 정상적으로 만들어질 경우 상수 true로 변경.
					if(i == 0 && !AssemblyConstKey.COPY_GTC_PACKAGE_ASSEMBLY_BASIC_FILE) {
						continue;
					} 
					
					//파일 복사.
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getStp_file_image_nm()), gtcPath, 
							hMap.getOrDefault("3", ""), getFileName(gtcXmlVO.getStp_file_image_nm())) <= 0) {
						logger.debug("product_3d_models_basic, Basic STP 파일 디렉토리 복사 실패");
						return 0;
					
					}
				}
			}
		}
		
		//product_3d_models_detailed, Detail STP 파일 디렉토리
		if(hMap.getOrDefault("4", "").length() > 0) {
			for(GtcXmlVO gtcXmlVO : arr_gtcXmlVO) {
				if(!StringUtils.isBlank(gtcXmlVO.getStp_file_detail_image_nm())) {
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getStp_file_detail_image_nm()), gtcPath,
							hMap.getOrDefault("4", ""), getFileName(gtcXmlVO.getStp_file_detail_image_nm())) <= 0) {
						logger.debug("product_3d_models_detailed, Detail STP 파일 디렉토리 복사 실패");
						return 0;
					}
				}
			}
		}
		
		//product_data_files, P21 파일 디렉토리
		if(hMap.getOrDefault("6", "").length() > 0) {
			for(GtcXmlVO gtcXmlVO : arr_gtcXmlVO) {
				if(!StringUtils.isBlank(gtcXmlVO.getP21_image_nm())) {
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getP21_image_nm()), gtcPath, 
							hMap.getOrDefault("6", ""), getFileName(gtcXmlVO.getP21_image_nm())) <= 0) {
						logger.debug("product_data_files, P21 파일 디렉토리 복사 실패");
						return 0;
					}
				}
			}
		}
		
		//product_family_drawings, 치수도면 이미지 디렉토리
		if(hMap.getOrDefault("7", "").length() > 0) {
			for(GtcXmlVO gtcXmlVO : arr_gtcXmlVO) {
				if(!StringUtils.isBlank(gtcXmlVO.getIso_dd_image_nm())) {
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getIso_dd_image_nm()), gtcPath, 
							hMap.getOrDefault("7", ""), getFileName(gtcXmlVO.getIso_dd_image_nm())) <= 0) {
						logger.debug("product_family_drawings, 치수도면 이미지 디렉토리 복사 실패");
						return 0;
					}
				}
			}
		}
		
		//product_pictures
		if(hMap.getOrDefault("8", "").length() > 0) {
			for(GtcXmlVO gtcXmlVO : arr_gtcXmlVO) {
				if(!StringUtils.isBlank(gtcXmlVO.getItem_image_nm())) {
					if(copyGtcFile(gtcPath(destDir, "", gtcXmlVO.getItem_image_nm()), gtcPath, 
							hMap.getOrDefault("8", ""), getFileName(gtcXmlVO.getItem_image_nm())) <= 0 ) {
						logger.debug("product_pictures 복사 실패");
						return 0;
					}
				}
			}
		}
		
		return 1;
	}
	
	/**
	 * GTC Package 경로 설정.
	 * @param gtcPath
	 * @param path
	 * @return
	 */
	public String gtcPath(String gtcPath, String path, String fileName) {
		logger.debug("gtcPath :" + gtcPath+path+fileName);
		return String.format("%s%s%s", new Object[] { gtcPath, path, fileName });
	}
	
	/**
	 * 폴더 복사.
	 * @param sourceDirectoryLocation
	 * @param destinationDirectoryLocation
	 * @throws Exception
	 */
	public void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws Exception {
	    Files.walk(Paths.get(sourceDirectoryLocation))
	      .forEach(source -> {
	          Path destination = Paths.get(destinationDirectoryLocation, source.toString()
	            .substring(sourceDirectoryLocation.length()));
	          try {
	        	  Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	          } catch (IOException e) {
	              logger.debug(e.getMessage());
	          }
	      });
	}
	
	/**
	 * GTC Package로 카피.
	 * @param ori
	 * @param gtcPath
	 * @param dest
	 * @return
	 */
	public int copyGtcFile(String ori, String gtcPath, String dest, String fileName) {
		try {
			if(StringUtils.isBlank(ori)) {
				return 0;
			}
			
			if(!dest.startsWith("/")) {
				dest = "/" + dest;
			}
			
			File fOri = new File(ori);
			
			if(Files.exists(fOri.toPath())) {
				Files.copy(fOri.toPath(), new File(gtcPath(gtcPath, dest, fileName)).toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			return 1;
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return 0;
	}
	
	public String getFileName(String name) {
		String ret = "";
		
		if(StringUtils.isBlank(name)) {
			return ret;
		}
		
		int idx = name.lastIndexOf("/");
		
		if(idx > 0) {
			ret = name.substring(idx, name.length());
		} else {
			ret = name;
		}
		
		return ret;
	}
	
	/**
	 * GTC Package 중 Assortment xml을 만들기 위함.
	 * @param gtcPath
	 * @return
	 * @throws Exception
	 */
	public int makeGTCPackageAssortment(String gtcPath, List<GtcXmlVO> arr_gtcXmlVO, HashMap<String, String> hMap, String destDir) throws Exception {
		logger.debug("method : makeGTCPackageAssortment");
		if(arr_gtcXmlVO == null || arr_gtcXmlVO.size() <= 0)
			return -1;
		
		//P21 파일 관련 정보 추가.
		for(GtcXmlVO vo : arr_gtcXmlVO) {
			vo = getP21Timestamp(vo, destDir);
			logger.debug("makeGTCPackageAssortment = 2 " + vo.getMatnr());
		}
		
		// 최종 xml 파일
		String xmlName = hMap.getOrDefault("9", "package_assortment.xml");
		String xmlPath = String.format("%s/%s", new Object[] { gtcPath, xmlName });

		JAXBContext context = JAXBContext
				.newInstance(com.eaction.framework.business.logic.escalate.service.ObjectAssortmentFactory.class);
		Marshaller jaxbMarshaller = context.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?>");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, 
				"http://dds-s3.azurewebsites.net/api/v1.0/schemas/package_assortment.xsd");
		//jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		OutputStream os = new FileOutputStream(xmlPath);

		// xjc로 jaxb를 사용하기 위해 변환한 클래스.
		// 해당 규격으로 GTC Package 중 PackageAssortment 관련 xml 생성.
		// 각 이름은 package_assortment.xsd 참조.
		ObjectAssortmentFactory objFactory = new ObjectAssortmentFactory();

		PackageAssortment pa = objFactory.createPackageAssortment();
		GregorianCalendar c = new GregorianCalendar();
		
		//현재는 단품이라... 추후 어셈블리 처리시 리스트 사이즈만큼 구해야져야 함.
		for (GtcXmlVO vo : arr_gtcXmlVO) {
			Item item = objFactory.createPackageAssortmentItem();
			
			logger.debug("makeGTCPackageAssortment = 3 " + vo.getMatnr());

			item.setProductId(vo.getMatnr());
			item.setGtcGenericClassId(vo.getGtc_gen_id());
			item.setGtcVendorClassId(vo.getVender_id());
			item.setP21ValueChangeTimestamp(convertXMLGregorianCalendar(vo.getP21ValueChangeTimestamp()));
			item.setP21StructureChangeTimestamp(convertXMLGregorianCalendar(vo.getP21StructureChangeTimestamp()));
			
			String p21 = vo.getP21_image_nm();
			if(!StringUtils.isBlank(p21)) {
				int idx = p21.lastIndexOf("/") + 1;
				String fName = p21.substring(idx, p21.length());
				
				item.setP21FileName(fName);
				item.setP21FileUrl("/" + hMap.getOrDefault("6", "product_data_files") + "/" + fName);
			} else {
				item.setP21FileName(null);
				item.setP21FileUrl(null);
			}
			
			item.setGtcGenericVersion(vo.getGtc_ver());
			item.setUnitSystem(vo.getUnit());
			
			pa.getItem().add(item);

		}

		jaxbMarshaller.marshal(pa, os);

		return 1;
	}
	
	/**
	 * p21 파일에서 p21파일 value change date 및 structure change date를 구함.
	 * GTC 패키지 만들때 필요. 
	 * @param gtcXmlVO
	 * @return
	 */
	public GtcXmlVO getP21Timestamp(GtcXmlVO gtcXmlVO, String destDir) {
		if(gtcXmlVO == null || StringUtils.isBlank(gtcXmlVO.getP21_image_nm())) {
			return gtcXmlVO;
		}
		
		HashMap<String,String> hMap = new HashMap<String,String>();
		
		boolean bFlag = false;
		int iSTX = 0;
		int iETX = 0;
		
		BufferedReader br = null;
		
		try {
			String fullPath = String.format("%s%s/%s", new Object[] { destDir, AssemblyConstKey.UPLOAD_GTC_DATA_FILE, getFileName(gtcXmlVO.getP21_image_nm()) });
			Path path = Paths.get(fullPath);
			if (Files.exists(path) && !Files.isDirectory(path)) {
				logger.debug("getP21Timestamp = s 1");
				String line = "";
				int iSEQ = 0;
				br = Files.newBufferedReader(path, StandardCharsets.UTF_8);

				while ((line = br.readLine()) != null) {
					iSEQ++;
			    	if(line.indexOf("DATA;") != -1) {
			    		bFlag = true;
			    		iSTX = iSEQ;
			    		continue;
			    	}
			    	
			    	if(line.indexOf("ENDSEC;") != -1) {
			    		bFlag = false;
			    		iETX = iSEQ;
			    	}
			    	
			    	if(bFlag && !line.equals("")) {
			    		String temp[] = line.split("\\=");
			    		hMap.put(temp[0].trim(), temp[1]);
			    	}
				}
			}
			
			logger.debug("getP21Timestamp = s 22222 " + hMap.getOrDefault("#19", "1"));
			
			
			if(hMap.size() > 0) {
				HashMap<String,String> plib = new HashMap<String,String>();
			   // writer = new BufferedWriter(new FileWriter(expt));
				logger.debug("getP21Timestamp = s 2");
				Set set = hMap.entrySet();
				Iterator iterator = set.iterator();
				
				while(iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					if(((String)entry.getValue()).indexOf("EFFECTIVITY_ASSIGNMENT") != -1) {
						logger.debug("getP21Timestamp = s 3");
						if(((String)entry.getValue()).indexOf("modified value") != -1) {
							//p21 value change
							gtcXmlVO.setP21ValueChangeTimestamp(getP21DateValue(hMap, entry));
							logger.debug("getP21Timestamp = s 4");
						} else if(((String)entry.getValue()).indexOf("modified structure") != -1) {
							//p21 structure change
							gtcXmlVO.setP21StructureChangeTimestamp(getP21DateValue(hMap, entry));
							logger.debug("getP21Timestamp = s 5");
						}
						
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.debug(e.getMessage());
				}
			}
		}
		
		logger.debug("getP21Timestamp = s 6 " + gtcXmlVO.getP21ValueChangeTimestamp());
		logger.debug("getP21Timestamp = s 7 " + gtcXmlVO.getP21StructureChangeTimestamp());
		
		return gtcXmlVO;
	}
	
	public String getP21DateValue(HashMap<String,String> hMap, Map.Entry entry) {
		
		
		try {
			String[] tmp = getSplitData("(", ");", ",", entry.getValue().toString().replace("EFFECTIVITY_ASSIGNMENT", "").trim());
			String val = "";
			logger.debug("getP21Timestamp = s 6");
			if(tmp != null && tmp.length > 0) {
				val = hMap.getOrDefault(tmp[0].trim(), "");
				logger.debug("getP21Timestamp = s 7" + val + ", " + tmp[0]);
			}
			
			if(val.length() > 0) {
				logger.debug("getP21Timestamp = s 8");
				String[] tmp1 = getSplitData("(", ");", ",", val);
				logger.debug("getP21Timestamp = s 8_1 " + tmp1[6]);
				if(tmp1 != null && tmp1.length > 6) {
					logger.debug("getP21Timestamp = s 9 " + hMap.getOrDefault(tmp1[6].trim(), "1") );
					String[] tmp2 = getSplitData("(", ");", ",", hMap.getOrDefault(tmp1[6].trim(), "").replace("DATE_TIME", "").trim());
					
					if(tmp2 != null && tmp2.length == 2) {
						logger.debug("getP21Timestamp = s 10 " + tmp2[0].replace("'", "") + " " + tmp2[1].replace("'", ""));
						return tmp2[0].replace("'", "").trim() + " " + tmp2[1].replace("'", "").trim();
					}
				}
			}
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		
		return "";
	}
	
	/**
	 * P21 항목들에서 값 축출하기.
	 * 
	 * @param stx
	 * @param etx
	 * @param div
	 * @param val
	 * @return
	 */
	public String[] getSplitData(String stx, String etx, String div, String val) {
		
		/*
		logger.debug("stx ========== " + stx);
		logger.debug("etx ========== " + etx);
		logger.debug("div ========== " + div);
		logger.debug("val ========== " + val);
		*/
		
		String ret[] = null;
		if(!stx.equals("")) val = val.replaceAll("\\" + stx, "");
		if(!etx.equals("")) val = val.replaceAll("\\" +etx, "");
		if(!div.equals("")) ret = val.split("\\" + div);
		
		return ret;
	}
	
	/**
	 * Date를 XMLGregorianCalendar로 변환. GTC Package 만들때 xml에 사용하기 위함.
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public XMLGregorianCalendar convertXMLGregorianCalendar(String d) throws Exception {
		
		if(StringUtils.isBlank(d)) return null;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = format.parse(d);

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		xmlGregCal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		
		return xmlGregCal;
	}
	
	/**
	 * GTC Package중 Meta xml을 만들기 위함.
	 * @param gtcPath
	 * @return
	 * @throws Exception
	 */
	public int makeGTCPackageMetaData(String gtcPath, GtcXmlVO gtcXmlVO, HashMap<String, String> hMap, String pkgName) throws Exception {
		// 최종 xml 파일
		String xmlName = hMap.getOrDefault("11", "package_meta_data.xml");
		String xmlPath = String.format("%s/%s", new Object[] { gtcPath, xmlName });

		JAXBContext context = JAXBContext
				.newInstance(com.eaction.framework.business.logic.escalate.service.ObjectMetaDataFactory.class);
		Marshaller jaxbMarshaller = context.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?>");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"http://dds-s3.azurewebsites.net/api/v1.0/schemas/package_meta_data.xsd");
		//jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		OutputStream os = new FileOutputStream(xmlPath);

		// xjc로 jaxb를 사용하기 위해 변환한 클래스.
		// 해당 규격으로 GTC Package 중 PackageSubsetAssortment 관련 xml 생성.
		// 각 이름은 package_subset_assortment.xsd 참조.
		ObjectMetaDataFactory objFactory = new ObjectMetaDataFactory();

		PackageMetaData pmd = objFactory.createPackageMetaData();

		pmd.setSupportedGtcGenericVersions(gtcXmlVO.getGtc_ver());
		//8.19 자체 번호로 0.1 붙이기로 함.
		pmd.setVendorHierarchyVersion((StringUtils.isBlank(gtcXmlVO.getGtc_ver())? "" : (gtcXmlVO.getGtc_ver() + ".0.1")));
		//8.19 하드코딩 하기로 결정됨.
		pmd.setVendorName("KORLOY Inc.");
		//8.19 하드코딩 하기로 결정됨.
		pmd.setVendorAcronym("KOR");
		pmd.setGtcPackageCreationDate(
				convertXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		pmd.setLogoUrl(hMap.getOrDefault("10", "package_logo.png"));
		pmd.setGtcPackageId(UUID.randomUUID().toString());	
		//아래는 하드코딩 하기로 함.
		pmd.setDownloadSecurity("no");
		pmd.setVendorSystemVersion("0.0.1");
		
		ArrayList<Path> fileList = new ArrayList<Path>();
		
		String disclaimer = hMap.getOrDefault("1", "disclaimer");
		
		//disclaimer 파일들(원래는 언어셋별로 있음) 추가.
		Files.walk(Paths.get(gtcPath + "/" + disclaimer))
	      .forEach(source -> {
	    	  try {
	    		  if(!Files.isDirectory(source)) {
	    			  fileList.add(source);
	    		  } 
	    	  } catch(Exception e) {
	    		  logger.debug(e.getMessage());
	    	  }
	          
	      });

		for (int i = 0; i < fileList.size(); i++) {
			String fName = fileList.get(i).toAbsolutePath().toString();
			String lang_code = assemblyDao.selectLangCode(fileList.get(i).getName(fileList.get(i).getNameCount() - 2).toString());
			
			if( fName.indexOf(disclaimer) < 0 || StringUtils.isBlank(lang_code)) {
				continue;
			}
			
			Language lang = objFactory.createPackageMetaDataLanguage();
			
			lang.setLanguageCode(lang_code.toLowerCase());
			//8.19 하드코딩 하기로 결정됨.
			//lang.setShortDescription("KORLOY Inc. GTC V2.0 package");
			lang.setShortDescription("Assembly package " + pkgName);
			String fileName = fName.substring(fName.indexOf(disclaimer) - 1, fName.length());
			lang.setDisclaimerUrl(fileName.replace("\\", "/"));
			//lang.setDisclaimerUrl("/disclaimer/en-us/disclaimer_en.txt");
			pmd.getLanguage().add(lang);
		}

		jaxbMarshaller.marshal(pmd, os);

		return 1;
	}
	
	/**
	 * GTC Package중 Subset xml을 만듬.
	 * @param gtcPath
	 * @return
	 * @throws Exception
	 */
	public int makeGTCPackageSubset(String gtcPath, GtcXmlVO gtcXmlVO, HashMap<String, String> hMap) throws Exception {
		if(gtcXmlVO == null || gtcXmlVO.getArr_assy() == null || gtcXmlVO.getArr_assy().size() <= 0)
			return -1;
		
		// 최종 xml 파일
		String xmlName = hMap.getOrDefault("12", "package_subset_assortment.xml");
		String xmlPath = String.format("%s/%s", new Object[] { gtcPath, xmlName });

		JAXBContext context = JAXBContext
				.newInstance(com.eaction.framework.business.logic.escalate.service.ObjectSubSetFactory.class);
		Marshaller jaxbMarshaller = context.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?>");
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"http://dds-s3.azurewebsites.net/api/v1.0/schemas/package_subset_assortment.xsd");
		//jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
		OutputStream os = new FileOutputStream(xmlPath);

		// xjc로 jaxb를 사용하기 위해 변환한 클래스.
		// 해당 규격으로 GTC Package 중 PackageMetaData 관련 xml 생성.
		// 각 이름은 package_meta_data.xsd 참조.
		ObjectSubSetFactory objFactory = new ObjectSubSetFactory();

		PackageSubsetAssortment psa = objFactory.createPackageSubsetAssortment();

		//현재는 단품이라.. 추후 어셈블리용으로 만들때는 리스트 형태여야 함.
		for (int i = 0; i < 2; i++) {
			MutiLanguageString mls = objFactory.createMutiLanguageString();

			StringWithLanguage swl = objFactory.createMutiLanguageStringStringWithLanguage();

			Subset subset = objFactory.createPackageSubsetAssortmentSubset();

			Items items = objFactory.createPackageSubsetAssortmentSubsetItems();

			//첫번째는 어셈블리 아이템 두번째에 자재번호 묶음.
			if (i == 0) {
				//8.19 하드코딩 결정 됨.
				subset.setSubsetId("AssemblyAssortment");
				
				swl.setLanguage("eng");
				swl.setStringValue("AssemblyAssortment");

				mls.getStringWithLanguage().add(swl);

				subset.setSubsetName(mls);

				items.getProductId().add(gtcXmlVO.getMatnr());

				subset.setItems(items);

				psa.getSubset().add(subset);
				
			} else {
				//8.19 하드코딩 결정 됨.
				subset.setSubsetId("ItemAssortment");

				swl.setLanguage("eng");
				swl.setStringValue("ItemAssortment");

				mls.getStringWithLanguage().add(swl);

				subset.setSubsetName(mls);
				
				items.getProductId().addAll(gtcXmlVO.getArr_assy());

				//items.getProductId().add(gtcXmlVO.getMatnr());

				subset.setItems(items);

				psa.getSubset().add(subset);
			}

		}

		jaxbMarshaller.marshal(psa, os);

		return 1;
	}
	
	/**
	 * GTC 패키지 생성시 마지막 폴더 구조 유지하면서 압축.
	 * @param path	원본
	 * @param toPath	zip경로
	 * @param fileName	zip 이름
	 * @return
	 */
	public int compressZipGtcPackage(String path, String toPath, String fileName) {
		
		File dir = new File(path);
        String[] list = dir.list();
        String _path;
        
        if(!dir.isDirectory()) {
        	return 0;
        }
 
        if (!dir.canRead() || !dir.canWrite())
            return 0;
 
        int len = list.length;
 
        if (path.charAt(path.length() - 1) != '/')
            _path = path + "/";
        else
            _path = path;
 
        try(ZipOutputStream zip_out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(toPath+ File.separator +fileName), 2048));) {
            
            for (int i = 0; i < len; i++) {
            	findZipFolder("",new File(_path + list[i]), zip_out, path);
            }
 
           // zip_out.close();
 
        } catch (FileNotFoundException e) {
        	logger.debug(e.getMessage());	
        } catch (IOException e) {
        	logger.debug(e.getMessage());
        }
        
        logger.debug("zip file : " + toPath + "/" + fileName);

		return 1;
	}
	
	/**
	 * 파일 순환 zip압축에서 사용.
	 * @param parent
	 * @param file
	 * @param zout
	 * @param toPath
	 * @throws IOException
	 */
	public  void findZipFolder(String parent, File file, ZipOutputStream zout, String toPath) throws IOException {
        byte[] data = new byte[2048];
        int read;
 
        if (file.isFile()) {
            ZipEntry entry = new ZipEntry(parent + file.getName());
            zout.putNextEntry(entry);
            BufferedInputStream instream = new BufferedInputStream(new FileInputStream(file));
 
            while ((read = instream.read(data, 0, 2048)) != -1)
                zout.write(data, 0, read);
 
            zout.flush();
            zout.closeEntry();
            instream.close();
 
        } else if (file.isDirectory()) {
        	String replaceString = Paths.get(toPath).toString();
        	//윈도우 기본 압축프로그램에서 상대경로 문제로 인하여.
        	if(!replaceString.endsWith("/") && !replaceString.endsWith(File.separator) ) {
        		replaceString += File.separator;
        	}
        	String parentString = file.getPath().replace(replaceString, "");
            parentString = parentString.substring(0,parentString.length() - file.getName().length());
            ZipEntry entry = new ZipEntry(parentString+file.getName()+ File.separator);
            zout.putNextEntry(entry);
 
            String[] list = file.list();
            if (list != null) {
                int len = list.length;
                for (int i = 0; i < len; i++) {
                	findZipFolder(entry.getName(),new File(file.getPath() + File.separator + list[i]), zout, toPath);
                }
            }
        }
    }
	
	/**
	 * TOOL로 지정된 자재번호기준 detail stp 파일을 읽어 CSW값 추출.
	 * @param fileName detail stp 파일.
	 * @return
	 * @throws Exception
	 */
	public List<String> getCSW(String matnr) {
		List<String> arrCSW = new ArrayList<String>();
		
		if(StringUtils.isBlank(matnr)) {
			return arrCSW;
		}
		
		BufferedReader br = null;

		try {
			Path path = Paths.get(assemblyDao.getDetailStpPath(matnr));
			if (Files.exists(path) && !Files.isDirectory(path)) {

				String line = "";
				br = Files.newBufferedReader(path, StandardCharsets.UTF_8);

				while ((line = br.readLine()) != null) {
					if (line.contains("'CSW")) {
						String tmp = line.substring(line.indexOf("'CSW") + 1, line.length());
						arrCSW.add(tmp.substring(0, tmp.indexOf("'")));
					}
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.debug(e.getMessage());
				}
			}
		}
		
		Collections.sort(arrCSW);

		return arrCSW;
	}
	
	/**
	 * 어셈블리 마스터 취득
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	@Override
	public AssemblyInfo selectAssemMstInfo(AssemblyInfo info) throws Exception {
		return assemblyDao.selectAssemMstInfo(info);
	}
	
	/**
	 * Assembly 생성 상태 변경
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public boolean updateAssemblyState(AssemblyInfo info) {
		boolean bResult = true;
    	int iResult = assemblyDao.updateAssemblyState(info);
    	
    	if(iResult == 1){
			bResult = true;
    	}else{
			bResult = false;
    	}
		
    	return bResult;
	}
	
	/**
	 * Assembly 생성중 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public int selectAssemblyCreateIngCnt(AssemblyInfo info) {
		return assemblyDao.selectAssemblyCreateIngCnt(info);
	}
	
	/**
	 * Assembly location 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyLocationCnt(AssemblyInfo info) {
		return assemblyDao.selectAssemblyLocationCnt(info);
	}
	
	/**
	 *  Assembly Build Comp Location 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<AssemblyItemInfo> selectAssemblyLocation(AssemblyInfo info) throws Exception {
		return assemblyDao.selectAssemblyLocation(info);
	}
	
}
