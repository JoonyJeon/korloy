/*****************************************************************************
 * 프로그램명  : AppServiceImpl.java
 * 설     명  : Main/Sub/Item 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.22   WYA    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.logic.app.dao.AppDao;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.assembly.dao.AssemblyDao;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.util.StringUtil;


/**
 * Main/Sub/Item 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
public class AppServiceImpl implements AppService {	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( AppServiceImpl.class );
		
	/** Main/Sub/Item 정보취득 처리 DAO */
	@Resource
	private AppDao appDao;
	
	/** Assembly 관리 DAO */
	@Resource
	private AssemblyDao assemblyDao;
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	/** 메소드명칭 */
	private String METHOD_NAME = "";
	

	@Resource
	private AssemblyService assemblyService;

    /**
	 * Main Application 목록 조회
	 * @return List<AppInfo> Main Application 목록
	 */
	@Override
    public List<AppInfo> selectMainApplicationList(AppInfo info) throws UserSysException {
		return appDao.selectMainApplicationList(info);
	}
    
    /**
	 * Sub Application 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Main Application 목록
	 */
	@Override
    public List<AppInfo> selectSubApplicationList(AppInfo info) throws UserSysException {
		return appDao.selectSubApplicationList(info);
    }
	
    /**
	 * Sub Application 목록 개수 조회
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
	@Override
    public int selectSubApplicationListCnt(AppInfo info) throws UserSysException {
		return appDao.selectSubApplicationListCnt(info);
    }
	
	/**
	 * Sub Application 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Main Application 목록
	 */
	@Override
	public List<AppInfo> selectItemGroupList(AppInfo info) throws UserSysException {
		return appDao.selectItemGroupList(info);
	}
	
	/**
	 * Sub Application 목록 개수 조회
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
	@Override
	public int selectItemGroupListCnt(AppInfo info) throws UserSysException {
		return appDao.selectItemGroupListCnt(info);
	}
	
	/**
	 * ItemGroup 상세 정보
	 * @param AppSearchInfo 검색조건
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
	@Override
	public AppInfo selectItemGroupInfo(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectItemGroupInfo(searchInfo);
	}
	
	/**
	 * More Info 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
	@Override
    public AppInfo selectMoreInfo(AppSearchInfo searchInfo) throws UserSysException {
		AppInfo moreInfo = appDao.selectMoreInfo(searchInfo);
		String str = moreInfo.getMore_info();
		str = StringEscapeUtils.unescapeHtml3(str);
		moreInfo.setMore_info(str);

		return moreInfo;
    }
	
	 /**
	 * Item Group 대표 이미지, ISO도면, Non-ISO도면 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
	@Override
    public AppInfo selectIgImageInfo(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectIgImageInfo(searchInfo);		
	}
	
	 /**
	 * Item Group이 속한 Sub App 이미지 취득
     * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Sub App 이미지 목록
	 * @throws UserSysException 
	 */
	@Override
    public List<AppInfo> selectSubImageList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectSubImageList(searchInfo);		
	}
	
	/**
	 * Item 목록 조회
     * @param AppSearchInfo 데이타
	 * @return List<Map<String, Object>> Item 목록
	 * @throws UserSysException 
	 */
	@Override
    public List<Map<String, Object>> selectItemList(AppSearchInfo searchInfo) throws UserSysException {
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		// 아이템 그룹의 프로퍼티 그룹 코드
		String ig_prop_cd =  appDao.selectIGPropGrpCd(searchInfo.getSearch_ig_cd());
//		if("".equals(StringUtil.nvl(ig_prop_cd))) {
//			ig_prop_cd = "NULL";
//		}
		searchInfo.setSearch_prop_grp_cd(ig_prop_cd);
		List<Map<String, Object>> list = appDao.selectItemList(searchInfo); 
		
		// 어셈블리에서 검색 시 
		if(!"".equals(searchInfo.getMatnr_list())) {
			String[] matnrArr = searchInfo.getMatnr_list().split(",");
			searchInfo.setMatnr_arr(matnrArr);
			
			for(Map<String, Object> map : list) {
				String matnr1 = (String) map.get("MATNR");
				for(String matnr2 : matnrArr) {
					if(matnr1.equals(matnr2)) {
						rtnList.add(map);
					}
				}
			}
			
			return rtnList;
			
		} else {
			return list;
		}
		 		
	}
	
    /**
	 * Insert Item 갯수 조회
     * @param AppSearchInfo 데이타
	 * @return String 아이템 갯수
	 * @throws UserSysException 
	 */
	@Override
    public String selectItemInsertsCnt(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectItemInsertsCnt(searchInfo);
	}
	
	/**
	 * Item 상세 정보
	 * @param AppSearchInfo 검색조건
	 * @return Map<String, Object> 상세정보
	 * @throws UserSysException 
	 */
	@Override
	public Map<String, Object> selectItemInfo(AppSearchInfo searchInfo) throws UserSysException {
		// SQL 조회
		Map<String, Object> item_info = appDao.selectItemInfo(searchInfo);
		
		// 아이템 그룹의 프로퍼티 그룹 코드
		String ig_prop_cd =  appDao.selectIGPropGrpCd((String)item_info.get("IG_CD"));
//		if("".equals(StringUtil.nvl(ig_prop_cd))) {
//			ig_prop_cd = "NULL";
//		}
		searchInfo.setSearch_prop_grp_cd(ig_prop_cd);
		// 프로시저로 조회
		Map<String, Object> sp_item_info = appDao.selectSPItemInfo(searchInfo);
		item_info.putAll(sp_item_info);
		
		return item_info;
	}
	
	/**
	 * Item이 담긴 마지막 카트번호 조회
     * @param AppSearchInfo 데이타
	 * @return String Cart 번호
	 * @throws UserSysException 
	 */
	@Override
    public String selectItemLastCartNo(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectItemLastCartNo(searchInfo);
    }
	
	/**
   	 * 선택한 Item Property Symbol 목록 조회
   	 * @param AppSearchInfo 검색조건
   	 * @return List<AppInfo> Property Symbol 목록
	 * @throws UserSysException 
   	 */
	@Override
       public List<AppInfo> selectItemPropSymbolList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectItemPropSymbolList(searchInfo);
	}
	
	/**
	 * Related Inserts 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<Map<String, Object>> Related Inserts 목록
	 */
	@Override
    public List<Map<String, Object>> selectRelatedInsertsList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectRelatedInsertsList(searchInfo);
    }
	
	/**
	 * Related Holders 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<Map<String, Object>> Related Holders 목록
	 */
	@Override
    public List<Map<String, Object>> selectRelatedHoldersList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectRelatedHoldersList(searchInfo);
    }
	
    /**
	 * Recommended Cutting Speeds 목록 갯수 조회
	 * @param AppSearchInfo 검색조건
	 * @return int Cutting Speeds 목록 갯수
	 */
    public int selectCuttingSpeedListCnt(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectCuttingSpeedListCnt(searchInfo);
    }
    
	/**
	 * Recommended Cutting Speeds  목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Cutting Speeds 목록
	 */
	@Override
    public List<AppInfo> selectCuttingSpeedList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectCuttingSpeedList(searchInfo);
    }
	
    /**
	 * Spare PartsList 목록 갯수 조회
	 * @param AppSearchInfo 검색조건
	 * @return int Spare Parts 목록 갯수
	 */
    public int selectSparePartsListCnt(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectSparePartsListCnt(searchInfo);
    }
	
    /**
	 * Spare PartsList 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Spare Parts 목록
	 */
	@Override
    public List<AppInfo> selectSparePartsList(AppSearchInfo searchInfo) throws UserSysException {
		return appDao.selectSparePartsList(searchInfo);
	}
	
    /**
	 * Item 전체개수 조회
	 * @return Item 전체개수
	 */
	@Override
    public int selectItemTotalCnt(AppInfo info) throws UserSysException {
		return appDao.selectItemTotalCnt(info);
    }
	

    /**
	 * Sub Application의 전체 아이템 개수
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
	@Override
    public int selectSubApplicationOfItemCnt(AppInfo info) throws UserSysException {
    	return appDao.selectSubApplicationOfItemCnt(info);
    }
    /**
	 * 필터에 Combo Code/Value 조회
	 * @return List<CodeInfo> Combo Code/Value
	 */
	@Override
    public List<CodeInfo> selectItemPropComboList(AppInfo info) throws UserSysException {
    	return appDao.selectItemPropComboList(info);
    }
    
    /**
   	 * 장바구니 목록 조회
   	 * @param AppSearchInfo 데이타
   	 * @return List<AppInfo> Cart List
   	 */
	@Override
   public List<AppInfo> selectCartList(AppSearchInfo searchInfo) throws UserSysException {
	   return appDao.selectCartList(searchInfo);
   }
	
   /**
	 * 장바구니 및 장바구니에 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertCartItem(AppInfo info) throws UserSysException {
		boolean bResult = true;		
		// Cart No가 없으면 장바구니 생성 후, 신규 장바구니에 Item Insert
		if("".equals(info.getCart_no())) {
			int cnt = appDao.insertCart(info);
			if(cnt == 1) {
				int itemCnt = appDao.selectDuplicatedItemCnt(info);
				//동일한  아이템이 있으면 수량 +1
				if(itemCnt == 1){
					appDao.updateCartItemQty(info);
				}else{
					//동일한 아이템이 없으면 insert
					appDao.insertCartItem(info);
				}
			
			}else {
	    		bResult = false;
			}
		// 기존 장바구니에 Item Insert	
		}else {
			int itemCnt = appDao.selectDuplicatedItemCnt(info);
			//동일한  아이템이 있으면 수량 +1
			if(itemCnt == 1){
				appDao.updateCartItemQty(info);
			}else{
				//동일한 아이템이 없으면 insert
				appDao.insertCartItem(info);
			}
		}
		
		BasketInfo bInfo = new BasketInfo();
		bInfo.setCart_no(info.getCart_no());
		bInfo.setLogin_user_id(info.getLogin_user_id());
		appDao.updateCart(bInfo);
    	
    	return bResult;
	}
	
	/**
	 * Assembly 그룹 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Assembly List
	 */
	@Override
    public List<AppInfo> selectAssemblyList(AppSearchInfo searchInfo) throws UserSysException {
		   return appDao.selectAssemblyList(searchInfo);
    }
	
	/**
	 * Assembly 그룹 아이템 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Assembly List
	 */
/*	@Override
    public List<AppInfo> selectAssemblyItemList(AppSearchInfo searchInfo) throws UserSysException {
		   return appDao.selectAssemblyItemList(searchInfo);
    }*/
	
    /**
	 * Assembly 아이템 추가
	 * @param AppInfo
	 * @return
     * @throws Exception 
	 */
	@Override
	@Transactional
	public int insertAssemblyItem(AppInfo info) throws Exception {
		int iResult = 0;
		
		// Assembly No가 없으면 장바구니 생성 후, 신규 Assembly 그룹에 Item Insert
		if("".equals(info.getAssem_no())) {
			iResult = appDao.insertAssembly(info);
			if(iResult == 1) {
				boolean availabilityYN = true;
				availabilityYN = assemAvailabilityCheck(info.getMatnr(), info.getAssem_no());
				
				iResult = 0;
				if(availabilityYN){
					iResult = appDao.insertAssemblyItem(info);	
				}else{
					iResult = 3;
				}				
			}
		// 기존 Assembly 그룹에 Item Insert	
		}else {
			boolean availabilityYN = true;
			// Assembly 화면에서 이동한 경우는 아이템 체크하지 않는다.
			//if("".equals(info.getSearch_assem_no())) {
				availabilityYN = assemAvailabilityCheck(info.getMatnr(), info.getAssem_no());
			//}
			
			if(availabilityYN){
				iResult = appDao.insertAssemblyItem(info);	
			}else{
				iResult = 3;
			}			
		}
		
		// Item 등록하면 Assembly 그룹의 파일들 삭제
		if(iResult == 1){
			iResult = 0;
			iResult = appDao.updateAssemblyFile(info);
		}
    	
    	return iResult;
		
	}
	
	
	/**
	 * 어셈블리 가능한 아이템인지 체크
	 * @return
	 * @throws Exception 
	 */
	private boolean assemAvailabilityCheck(String itMatnr, String assemNo) throws Exception{
		
		boolean bResult = false;		
		
		//선택한 아이템의 Assembly정보를 가지고온다. (ASSEM_TYPE 및 itemMS(left), itemWS(right) 리스트 취득)
		AssemblyInfo pickItemAssemInfo = assemblyService.getAssembly(itMatnr);
		
		//선택한 아이템의 계층구조 및 타입을 알 수 없으면 어셈블리 그룹안에 아이템을 넣을 수 없다.
		if(pickItemAssemInfo == null || pickItemAssemInfo.getAssy_type() == null){
			return false;
		}
					
		//선택한 Asssembly 그룹내의 아이템정보를 가지고온다.
		AppInfo info = new AppInfo();
		info.setAssem_no(assemNo);
		List<AppInfo> assemGroupItemList = appDao.selectAssemblyItemList(info);
		//Assembly 그룹내 각 아이템의 itemMs, itemWs, assem_type을 가져와 담는다.
		for(int i=0; i<assemGroupItemList.size(); i++){
			AssemblyInfo assemItem =  assemblyService.getAssembly(assemGroupItemList.get(i).getMatnr());
			assemGroupItemList.get(i).setItemMS(assemItem.getItemMS());
			assemGroupItemList.get(i).setItemWS(assemItem.getItemWS());
			assemGroupItemList.get(i).setAssy_type(assemItem.getAssy_type());
		}
		
		
		//어셈블리 그룹내에 아이템이 하나도 없으면 선택 아이템을 담을 수 있다. 
		if(assemGroupItemList.size() < 1){
			return true;
		}else{
			//Assembly 그룹내에 ADP, TL, INS 여부 확인
			boolean TL_YN = false;
			boolean INS_YN = false;
			boolean ADP_YN = false;
			
			for(int i=0; i<assemGroupItemList.size(); i++){
				if("TL".equals(assemGroupItemList.get(i).getAssy_type())){
					TL_YN = true;
				}else if("INS".equals(assemGroupItemList.get(i).getAssy_type())){
					INS_YN = true;
				}else if("ADP".equals(assemGroupItemList.get(i).getAssy_type())){
					ADP_YN = true;
				}
			}
			
			// 1. 넣을려고하는 아이템이 TL이면
			if("TL".equals(pickItemAssemInfo.getAssy_type())){
				// Assembly 그룹내에 TL이 있으면 담을 수 없다.
				if(TL_YN){
					logger.debug("assemAvailabilityCheck ==> Select Item : " + pickItemAssemInfo.getMatnr() + "(" +pickItemAssemInfo.getAssy_type() + ")  /  Fail :: Exist TL");
					return false;
				}
				// Assembly 그룹내에 INS가 있으면 INS의 itemMS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
				if(INS_YN){
					for(int k=0; k<assemGroupItemList.size(); k++){
						if("INS".equals(assemGroupItemList.get(k).getAssy_type())){
							if(assemGroupItemList.get(k).getItemMS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemMS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemMS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}
							}
						}
					}
				}
				// Assembly 그룹내에 ADP가 있으면  ADP의 itemWS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
				if(ADP_YN){
					for(int k=0; k<assemGroupItemList.size(); k++){
						if("ADP".equals(assemGroupItemList.get(k).getAssy_type())){
							if(assemGroupItemList.get(k).getItemWS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemWS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemWS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}	
							}
						}
					}					 					 
				}		
				
			// 2. 넣을려고하는 아이템이 INS이면
			}else if("INS".equals(pickItemAssemInfo.getAssy_type())){
				// Assembly 그룹내 TL이 없으면 넣을 수 없다.(체결가능한 아이템인지 확인할 수 없기 때문에)
				if(!TL_YN){
					logger.debug("assemAvailabilityCheck ==> Select Item : " + pickItemAssemInfo.getMatnr() + "(" +pickItemAssemInfo.getAssy_type() + ")  /  Fail :: Not Exist TL");
					return false;
				}else{					
					for(int k=0; k<assemGroupItemList.size(); k++){
						// Assembly 그룹내에 동일한 matnr이 있으면 중복되어 넣을 수 없다(동일 INS 중복안됨)
						if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getMatnr())){
							logger.debug("assemAvailabilityCheck ==> Select Item : " + pickItemAssemInfo.getMatnr() + "(" +pickItemAssemInfo.getAssy_type() + ")  /  Fail :: Overlap matnr");
							return false;
						}else{
							// Assembly 그룹내 아이템 중 TL의 itemWS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
							if("TL".equals(assemGroupItemList.get(k).getAssy_type())){
								if(assemGroupItemList.get(k).getItemWS() != null){
									for(int j=0; j < assemGroupItemList.get(k).getItemWS().size(); j++){
										if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemWS().get(j).getMatnr())){
											bResult = true;
											break;
										}
									}
								}
							}
						}
					}
				}
			// 3. 넣을려고하는 아이템이 ADP이면	
			}else if("ADP".equals(pickItemAssemInfo.getAssy_type())){
				// Assembly 그룹내에 TL있고, ADP 없는경우
				if(TL_YN && !ADP_YN){
					// Assembly 그룹내 아이템 중 TL의 itemMS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
					for(int k=0; k<assemGroupItemList.size(); k++){
						if("TL".equals(assemGroupItemList.get(k).getAssy_type())){
							if(assemGroupItemList.get(k).getItemMS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemMS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemMS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}	
							}
						}
					}
				// Assembly 그룹내에 ADP있고, TL 없는경우 	
				}else if(!TL_YN && ADP_YN){
					// Assembly 그룹내 아이템 중 ADP의 itemMS, itemWS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
					// ADP가 왼쪽, 오른쪽 어느쪽에도 체결할 수 있기 때문에 MS, WS 모두 체크해야한다. 
					for(int k=0; k<assemGroupItemList.size(); k++){
						if("ADP".equals(assemGroupItemList.get(k).getAssy_type())){
							// itemMS 체크
							if(assemGroupItemList.get(k).getItemMS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemMS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemMS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}
							}
							
							// itemWS 체크
							if(assemGroupItemList.get(k).getItemWS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemWS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemWS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}
							}
						}
					}
				// Assembly 그룹내에 TL, ADP 모두 있는경우
				}else if(TL_YN && ADP_YN){
					// Assembly 그룹내 아이템 중 ADP의 itemMS 리스트 중 넣을려고 하는 아이템의 matnr값이 있는지 확인
					// TL과ADP 둘다있는 경우 TL과 ADP 중간에 들어갈수는 없기때문에 ADP의 itemMS만 체크하면 된다
					for(int k=0; k<assemGroupItemList.size(); k++){
						if("ADP".equals(assemGroupItemList.get(k).getAssy_type())){
							if(assemGroupItemList.get(k).getItemMS() != null){
								for(int j=0; j < assemGroupItemList.get(k).getItemMS().size(); j++){
									if(pickItemAssemInfo.getMatnr().equals(assemGroupItemList.get(k).getItemMS().get(j).getMatnr())){
										bResult = true;
										break;
									}
								}	
							}
						}
					}	
				}
			}
		}
		
		if(!bResult){
			logger.debug("assemAvailabilityCheck ==> Select Item : " + pickItemAssemInfo.getMatnr() + "(" +pickItemAssemInfo.getAssy_type() + ")  /  Fail :: Not in the itrmMS, itemWS list");	
		}
		//bResult = true;
		return bResult;
		
	}
	
	/**
	 * Item Group Code로 Sub Applicatioin Code 조회
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public String selectIgCodeToSaCode(AppInfo info) throws UserSysException {
		return appDao.selectIgCodeToSaCode(info);
	}
	
	/**
	 * Sub Applicatioin Code로 Main Applicatioin Code 조회
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public String selectSaCodeToMaCode(AppInfo info) throws UserSysException {
		return appDao.selectSaCodeToMaCode(info);
	}	

	/**
	 * 아이템 그룹 필터목록
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectItemGroupFilterList(AppSearchInfo info) throws UserSysException {
		return appDao.selectItemGroupFilterList(info);
	}
	
	/**
	 * ISO metric/inch 계산식 단위 및 값 목록
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectIsoCalList(AppSearchInfo info) throws UserSysException {
		return appDao.selectIsoCalList(info);
	}
	
	/**
	 * TOOL로 지정된 자재번호기준 detail stp 파일을 읽어 CSW값 추출.
	 * @param fileName detail stp 파일.
	 * @return
	 * @throws Exception
	 */
	private List<String> getCSW(String matnr) {
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

		return arrCSW;
	}
	
	 /**
	 * Main App 테일러드 정보 조회
	 * @param AppInfo 데이타
	 * @return List<AppInfo> Main App 테일러드 정보 목록
	 */
	@Override
	public List<AppInfo> selectTailInfo(AppInfo info) throws UserSysException{
		return appDao.selectTailInfo(info);
	}
}
