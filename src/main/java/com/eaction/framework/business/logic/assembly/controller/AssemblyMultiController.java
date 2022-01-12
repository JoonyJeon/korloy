/*****************************************************************************
 * 프로그램명  : Assembly MultiController.java
 * 설     명  : Assembly 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01  왕윤아    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.assembly.constant.AssemblyConstKey;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.ExcelUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.FileUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;
import net.sf.json.spring.web.servlet.view.JsonView;

/**
 * Assembly 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class AssemblyMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(AssemblyMultiController.class);    

	@Resource
	private AssemblyService assemblyService;
	@Resource
	private BasicService basicService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	
	
	/*
	 * Assembly 목록 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_LIST)
	@ResponseBody
    public Map<String, Object> selectAssemblyList(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
    	Map<String, Object> result = new HashMap<>();
    	int nTotal = 0;
    	List<AssemblyInfo> list = new ArrayList<>();
		
    	String exceptionMsg ="";
    	
		try{
			result = assemblyService.selectAssemblyListInfo(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ASSEMBLY LIST RESULT :: "+exceptionMsg);
		}
		
		return result;
	}	
	
	/*
	 * Assembly 수량 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_LIST_CNT)
	@ResponseBody
	public int selectAssemblyListCnt(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		int nTotal = 0;
		String exceptionMsg ="";
		
		try{
			nTotal = assemblyService.selectAssemblyListCnt(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ASSEMBLY LIST RESULT :: "+exceptionMsg);
		}
		
		return nTotal;
	}
	
	/*
	 * Assembly 개별정보 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_DETAIL)
	@ResponseBody
    public Map<String, Object> selectAssemblyDetail(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
    	Map<String, Object> result = new HashMap<>();
		
    	String exceptionMsg ="";
    	
		try{
			Map<String, List<AssemblyInfo>> item = assemblyService.selectAssemblyItemListInfo(info);
			result.put("tot_list", item.get("tot_list"));
			result.put("tl_adp_list", item.get("tl_adp_list"));
			result.put("ins_list", item.get("ins_list"));
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ASSEMBLY DETAIL RESULT :: "+exceptionMsg);
		}
		
		return result;
	}	
	
	/*
	 * Assembly 그룹 삭제
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_DEL_OK)
	@ResponseBody
	public String deleteAssemblyInfo(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		try{
			bResult = assemblyService.deleteAssembly(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN DELETE ASSEMBLY :: "+exceptionMsg);
		}
		
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		}else{
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
		}
		return jsonObject.toString();
	}	
	
	/*
	 * Assembly 그룹의 아이템 삭제
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_ITEM_DEL_OK)
	@ResponseBody
	public String deleteAssemblyItemInfo(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws Exception{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		try{
			bResult = assemblyService.deleteAssemblyItem(info);

			//Assembly location 갯수
			int locationCnt = assemblyService.selectAssemblyLocationCnt(info);
			//location 갯수가 0이상이면 location 삭제
			if(locationCnt > 0){
				bResult = assemblyService.deleteAssemblyItemLoc(info);	
			}
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ERROR ASSEMBLY ITEM :: "+exceptionMsg);
		}
		//삭제 성공 후 다시 아이템 리스트를 그리기 위해
		Map<String, List<AssemblyInfo>> item = assemblyService.selectAssemblyItemListInfo(info);
		
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
			jsonObject.put("tot_list", item.get("tot_list"));
			jsonObject.put("tl_adp_list", item.get("tl_adp_list"));
			jsonObject.put("ins_list", item.get("ins_list"));
		}else{
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
		}
		return jsonObject.toString();
	}	
	
	/*
	 * Assembly 그룹 등록
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_ADD_OK)
	@ResponseBody
    public String insertAssemblyInfo(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		
    	String exceptionMsg ="";
    	JSONObject jsonObject = new JSONObject();
    	boolean bResult = false;
    	
		try{
			bResult = assemblyService.insertAssembly(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN INSERT ASSEMBLY :: "+exceptionMsg);
		}
		
		
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		}else{
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
		}
		
		return jsonObject.toString();
	}	
	/*
	 * Assembly 그룹 수정
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_EDIT_OK)
	@ResponseBody
	public String updateAssemblyInfo(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		try{
			bResult = assemblyService.updateAssembly(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN UPDATE ASSEMBLY :: "+exceptionMsg);
		}
		
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		}else{
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
		}
		return jsonObject.toString();
	}	
	
	/**
	 * Cart 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_CART_LIST)
	@ResponseBody	
	private ModelAndView getAssemCartList(HttpServletRequest request, AssemblyInfo info) throws UserSysException {
		
		HttpSession session = request.getSession();
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);       
		
		List<AssemblyInfo> cartList = assemblyService.selectAssemCartList(info);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("cartList", cartList);		
		
		return modelAndView;
		
	}
	
	/*
	 * 카트등록 및 카트에 아이템 등록
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_CART_ADD_OK)
	@ResponseBody
    public String doInsertCartItem(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();    	

    	boolean bResult = true;
    	
		try{
			bResult = assemblyService.insertCartItem(info);
			AppSearchInfo searchinfo = new AppSearchInfo();
			searchinfo.setLogin_user_id(info.getLogin_user_id());
			List<AssemblyInfo> cartList = assemblyService.selectAssemCartList(info);
			if (bResult) {
				jsonObject.put("resCode", "0");
				jsonObject.put("resCartCnt", cartList.size());
				jsonObject.put("resCartNo", info.getCart_no());
				jsonObject.put("resMsg", "SUCCESS");
			} else {
				jsonObject.put("resCode", "1");
				jsonObject.put("resMsg",  "FAIL");
			}
			
		}catch(Exception e){
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  "MSG.FAIL");
		}
		
		
		return jsonObject.toString();
	}	
	
	
	/*
	 * Assembly 목록의 엑셀 파일 다운로드
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_EXCEL)
	@ResponseBody
	public String doExceldownload(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		JSONObject jsonObject = new JSONObject();
		
		try {
			// 패스 및 파일명 설정
			HttpSession session = request.getSession();
			
			String path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
			String destDir = session.getServletContext().getRealPath(path);
	//		String destDir = "C:\\";
			
			String uploadPath = destDir + System.getProperty("file.separator") + "excel" + System.getProperty("file.separator") ;
			
			String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
			String filename = "Assembly_Items_List" + ConstKey.SEP_UNDER + thisTime;
			String file = filename + ".xls";
			
			List<HashMap> excelList = new ArrayList<HashMap>();
			
			Map<String, List<AssemblyInfo>> item = assemblyService.selectAssemblyItemListInfo(info);
			List<AssemblyInfo> list = item.get("tot_list"); 
			
			List<List<Object>> dataList = new ArrayList<List<Object>>();
	
				for(int index=0 ; index<list.size(); index++) {
					AssemblyInfo result = (AssemblyInfo)list.get(index);
			    	List data = new ArrayList();
			    	
			    	data.add(result.getMatnr());
			    	data.add(result.getDesignation());
			    	data.add(result.getGrade());
			    	dataList.add(data);
				}
	
			// 타이틀
			String title = "";
			// 컬럼명
		   	List<List<String>> colList = new ArrayList<List<String>>();
		   	List<String> col = new ArrayList<String>();
		   	col.add("Order Code");
		   	col.add("Item Designation");
		   	col.add("Grade");
		   	
		   	colList.add(col);
		
		   	List<List<String>> footerList = null;
		
		   	HashMap map = new HashMap();
		   	map.put("title", title);
		   	map.put("colList", colList);
		   	map.put("dataList", dataList);
		   	map.put("footerList", footerList);
		
		   	excelList.add(map);
		   	
		   	// Excel생성
		   	ExcelUtil excelUtil = new ExcelUtil();
		   	excelUtil.download(uploadPath, file, file, excelList);
		
		   	// 파일의 전체경로 설정
			StringBuffer sbFileName = new StringBuffer("");
			sbFileName.append(uploadPath);
			sbFileName.append(file);
			
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
			jsonObject.put("resData", sbFileName.toString());
		} catch(Exception e) {
	    	jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
			
		}
	    return jsonObject.toString();
		
	}
	/**
	 * 파일다운로드
	 * @param request
	 * @param response
	 * @param info
	 * @throws UserSysException
	 */
	@RequestMapping(value = ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_FILE)
	@ResponseBody
	public void doOrderAssemExcelFileDown(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException {

		String atch = info.getExcel_file_path();
//		String[] fileName_arr = atch.split("\\");
//		String fileName = fileName_arr[fileName_arr.length - 1]; 
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(atch, info.getAtch_file_name(), request);
		
		//다운로드후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(atch);
	}
	/*
	 * Assembly 목록의 GTC 파일 다운로드
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_GET_GTC_ZIP_FILE)
	@ResponseBody
	public void doGtcZipdownload(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		Map result = new HashMap<>();
		
		// 패스 및 파일명 설정
		HttpSession session = request.getSession();
		
		String path = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
		String sep = System.getProperty("file.separator");
		path= path.replaceAll(sep+sep, "/");
		
		String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
		//GTC+ cart_no + time -> zip으로
		String filename = "GTC" + ConstKey.SEP_UNDER + info.getCart_no()+ ConstKey.SEP_UNDER +thisTime;
		String file = filename + ".zip";
		
		//압축할 파일리스트
		List<String> fileNames = new ArrayList<String>();
		//fileNames에 압축할 파일 추가 
		List<AssemblyInfo> tmpFileList = new ArrayList<>();
		try{
			//fileNames에 압축할 파일 추가 
			tmpFileList = assemblyService.selectAssemblyItemFileList(info);
			
			if(tmpFileList.size() !=0){
				for(AssemblyInfo files : tmpFileList){
					if(files !=null){
//				String fileName = path+files.getFile_dwl_path() + "/"+ files.getFile_thn_nm();
						String fileName = path+files.getFile_dwl_path() + "/"+ files.getFile_org_nm();
						logger.debug("fileName : : "+ fileName);
					}
				}
			}
		}catch(Exception e){
			logger.debug("ERROR IN GTC_ZIP_DOWNLOAD ::" +e.getMessage());
		}
		
		//파일 압축
		FileUtil.makeZipFiles(file, fileNames);
		
		// 파일의 전체경로 설정
		StringBuffer sbFileName = new StringBuffer("");
		sbFileName.append(file);
		
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(sbFileName.toString(), file, request);
		
		//다운로드후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(sbFileName.toString());
		
	}	
	
	/***
	 * 어셈블리 테스트
	 * 
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_TEST)
//	@ResponseBody
//	public String transferAssembly(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws Exception {
//		
//		String link = "";
//				
//		/**
//		 * Tool 	: 1-06-055453
//		 * Holder	: 1-06-008725
//		 */
//		AssemblyInfo vo = assemblyService.getAssembly("1-06-037597"); 
//		
//		logger.debug("Conclusion MATNR >>>>>>>>>>>>>>>> " + vo.getMatnr());	
//		logger.debug("Conclusion Assy Type >>>>>>>>>>>>>>>> " + vo.getAssy_type());		
//		logger.debug("Conclusion Item Type >>>>>>>>>>>>>>>> " + vo.getItem_type());		
//		logger.debug("Conclusion Insert >>>>>>>>>>>>>>>> " + vo.getItemINS());
//		logger.debug("Conclusion Adaptive >>>>>>>>>>>>>>>> " + vo.getItemADP());
//		logger.debug("Conclusion Tool >>>>>>>>>>>>>>>> " + vo.getItemTL());
//		
//		// Tool
//		if("TL".contentEquals(vo.getAssy_type())) {
//			if(vo.getItemADP() != null) {
//				AssemblyInfo tvo1 = assemblyService.getAssembly((vo.getItemADP().get(0)).getMatnr());					
//				link += (tvo1.getItemADP().get(0)).getAssy_type() + ":" + (tvo1.getItemADP().get(0)).getMatnr() + " > ";
//			}
//			
//			link += vo.getAssy_type() + ":" + vo.getMatnr() + " > ";
//			
//			if(vo.getItemINS() != null) {
//				AssemblyInfo tvo2 = assemblyService.getAssembly((vo.getItemINS().get(0)).getMatnr());
//				
//				if(tvo2.getItemINS() == null) {
//					link += (vo.getItemINS().get(0)).getAssy_type() + ":" + (vo.getItemINS().get(0)).getMatnr() + " > ";
//				} else {
//					link += (tvo2.getItemINS().get(0)).getAssy_type() + ":" + (tvo2.getItemINS().get(0)).getMatnr() + " > ";
//				}
//			}			
//		}
//		
//		// Adaptive
//		if("ADP".contentEquals(vo.getAssy_type())) {
//			if(vo.getItemADP() != null) {
//				AssemblyInfo tvo1 = assemblyService.getAssembly((vo.getItemADP().get(0)).getMatnr());
//				
//				if(tvo1.getItemADP() != null) {
//					link += (tvo1.getItemADP().get(0)).getAssy_type() + ":" + (tvo1.getItemADP().get(0)).getMatnr() + " > ";
//				}
//			}
//						
//			link += vo.getAssy_type() + ":" + vo.getMatnr() + " > ";
//						
//			if(vo.getItemTL() != null) {
//				AssemblyInfo tvo1 = assemblyService.getAssembly((vo.getItemTL().get(0)).getMatnr());
//				
//				if(tvo1.getItemTL() != null) {
//					link += (tvo1.getItemTL().get(0)).getAssy_type() + ":" + (tvo1.getItemTL().get(0)).getMatnr() + " > ";
//				} else {
//					link += (vo.getItemTL().get(0)).getAssy_type() + ":" + (vo.getItemTL().get(0)).getMatnr() + " > ";
//				}
//							
//				if(tvo1.getItemINS() != null) {
//					AssemblyInfo tvo2 = assemblyService.getAssembly((tvo1.getItemINS().get(0)).getMatnr());
//					
//					if(tvo2.getItemINS() != null) {					
//						link += (tvo2.getItemINS().get(0)).getAssy_type() + ":" + (tvo2.getItemINS().get(0)).getMatnr() + " > ";
//					} else {
//						link += (tvo1.getItemINS().get(0)).getAssy_type() + ":" + (tvo1.getItemINS().get(0)).getMatnr() + " > ";
//					}
//				}
//			}
//		}
//		
//		// Insert
//		if("INS".contentEquals(vo.getAssy_type())) {
//			if(vo.getItemTL() != null) {
//				AssemblyInfo tvo1 = assemblyService.getAssembly((vo.getItemTL().get(0)).getMatnr());				
//				link += "," + (vo.getItemTL().get(0)).getMatnr();
//							
//				if(tvo1.getItemADP() != null) {
//					AssemblyInfo tvo2 = assemblyService.getAssembly((tvo1.getItemADP().get(0)).getMatnr());					
//					link += "," + (tvo2.getItemADP().get(0)).getMatnr();
//				}
//			}			
//		}		
//			
//		/*
//		if(vo.getItemTL() != null) {
//			AssemblyVO tvo1 = assemblyService.getAssembly((vo.getItemTL().get(0)).getMatnr());
//			
//			link += "," + (vo.getItemTL().get(0)).getMatnr();
//			
//			logger.debug("Conclusion Assy Type >>>>>>>>>>>>>>>> " + tvo1.getAssy_type());		
//			logger.debug("Conclusion Item Type >>>>>>>>>>>>>>>> " + tvo1.getItem_type());		
//			logger.debug("Conclusion Insert >>>>>>>>>>>>>>>> " + tvo1.getItemINS());
//			logger.debug("Conclusion Adaptive >>>>>>>>>>>>>>>> " + tvo1.getItemADP());
//			logger.debug("Conclusion Tool >>>>>>>>>>>>>>>> " + tvo1.getItemTL());
//			
//			
//			if(tvo1.getItemINS() != null) {
//				AssemblyVO tvo2 = assemblyService.getAssembly((tvo1.getItemINS().get(0)).getMatnr());
//				
//				link += "," + (tvo1.getItemINS().get(0)).getMatnr();
//				
//				logger.debug("Conclusion Assy Type >>>>>>>>>>>>>>>> " + tvo2.getAssy_type());		
//				logger.debug("Conclusion Item Type >>>>>>>>>>>>>>>> " + tvo2.getItem_type());		
//				logger.debug("Conclusion Insert >>>>>>>>>>>>>>>> " + tvo2.getItemINS());
//				logger.debug("Conclusion Adaptive >>>>>>>>>>>>>>>> " + tvo2.getItemADP());
//				logger.debug("Conclusion Tool >>>>>>>>>>>>>>>> " + tvo2.getItemTL());				
//			}
//		}
//		*/		
//		
//		logger.debug("Assembly ================ " + link);
//	
//		//return "admin/contents/escalate/escalateList";
//		
//		return link;
//	}	
	
	
	
	/*
	 * Assembly 생성
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_BUILD_OK)
	@ResponseBody
	public String executeAssemblyCreate(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		String exceptionMsg = "Fail";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		String resCode = "0";
		
		try{
			List<String> lstLogMatnr = new ArrayList<String>();
			//생성중인 갯수
			int ingCnt = assemblyService.selectAssemblyCreateIngCnt(info);
			if(ingCnt == 0) {
				JSONParser parser = new JSONParser();
				List<AssemblyItemInfo> itemADPList = new ArrayList<AssemblyItemInfo>();
				List<AssemblyItemInfo> itemTLList = new ArrayList<AssemblyItemInfo>();
				List<AssemblyItemInfo> itemINSList = new ArrayList<AssemblyItemInfo>();
				
				Object itemADP = parser.parse( info.getItemADP_str() );
				JSONArray jsonItemADP = (JSONArray) itemADP;
				for(int i=0; i<jsonItemADP.size(); i++) {
					org.json.simple.JSONObject ADP = (org.json.simple.JSONObject) jsonItemADP.get(i);
					AssemblyItemInfo assemblyItemInfo = new AssemblyItemInfo();
					assemblyItemInfo.setMatnr((String) ADP.get("matnr"));
					lstLogMatnr.add((String) ADP.get("matnr"));
					assemblyItemInfo.setAssy_type((String) ADP.get("assy_type"));
					//assemblyItemInfo.setLocation((String) ADP.get("location"));
					itemADPList.add(assemblyItemInfo);
				}
				info.setItemADP(itemADPList);
				
				Object itemTL = parser.parse( info.getItemTL_str() );
				JSONArray jsonItemTL = (JSONArray) itemTL;
				for(int i=0; i<jsonItemTL.size(); i++) {
					org.json.simple.JSONObject TL = (org.json.simple.JSONObject) jsonItemTL.get(i);
					AssemblyItemInfo assemblyItemInfo = new AssemblyItemInfo();
					assemblyItemInfo.setMatnr((String) TL.get("matnr"));
					lstLogMatnr.add((String) TL.get("matnr"));
					assemblyItemInfo.setAssy_type((String) TL.get("assy_type"));
					assemblyItemInfo.setOverhang(StringUtil.nvl((String) TL.get("overhang")));
					itemTLList.add(assemblyItemInfo);
				}
				info.setItemTL(itemTLList);
				
				Object itemINS = parser.parse( info.getItemINS_str() );
				JSONArray jsonItemINS = (JSONArray) itemINS;
				for(int i=0; i<jsonItemINS.size(); i++) {
					org.json.simple.JSONObject INS = (org.json.simple.JSONObject) jsonItemINS.get(i);
					AssemblyItemInfo assemblyItemInfo = new AssemblyItemInfo();
					assemblyItemInfo.setMatnr((String) INS.get("matnr"));
					lstLogMatnr.add((String) INS.get("matnr"));
					assemblyItemInfo.setAssy_type((String) INS.get("assy_type"));
					assemblyItemInfo.setLocation(StringUtil.nvl((String) INS.get("location")));
					itemINSList.add(assemblyItemInfo);
				}
				info.setItemINS(itemINSList);
				
				// 로그저장을 위해 Assembly정보 조회
				AssemblyInfo assem_mst_info = assemblyService.selectAssemMstInfo(info);
				basicService.doLog(request, "EC0011", assem_mst_info.getAssem_nm(), StringUtils.join(lstLogMatnr,","));
				// 생성중 상태 변경
				info.setBuld_sta("ING");
				assemblyService.updateAssemblyState(info); 
				
				// 생성	
				assemblyService.createAssembly(request, info);
				
			} else {
				resCode = "2";
			}
			
		}catch(Exception e){
			resCode = "1";
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN CREATE ASSEMBLY :: "+exceptionMsg);
		}
				
		if(bResult){
			jsonObject.put("resCode", resCode);
			jsonObject.put("resMsg", "Success");
		}else{
			jsonObject.put("resCode", resCode);
			
			if("1".equals(resCode)) {
				exceptionMsg = exceptionMsg.replace("com.eaction.framework.common.exception.UserSysException: java.lang.Exception:", "");
				jsonObject.put("resMsg", exceptionMsg);
			}
		}
		return jsonObject.toString();
	}
	
	/*
	 * Assembly 정보 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_INFO)
	@ResponseBody
    public AssemblyInfo selectAssemblyInfo(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
    	
    	AssemblyInfo result = new AssemblyInfo();
		
    	String exceptionMsg ="";
    	
		try{
			result = assemblyService.selectAssemMstInfo(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ASSEMBLY INFO RESULT :: "+exceptionMsg);
		}
		
		return result;
	}	
	
	/*
	 * Assembly 생성 취소
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_CREATE_CANCEL)
	@ResponseBody
	public String updateAssemblyCreateCancel(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		try{
			bResult = assemblyService.updateAssemblyState(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN UPDATE ASSEMBLY CREATE CANCEL :: "+exceptionMsg);
		}
		
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		}else{
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
		}
		return jsonObject.toString();
	}	
	
	/*
	 * Build 생성완료 Assembly Location 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL, params = ConstKey.ACTION_ID + "=" + AssemblyConstKey.ACTION_ASSEMBLY_BUILD_COMP_LOCATION)
	@ResponseBody
    public List<AssemblyItemInfo> selectAssemblyLocation(HttpServletRequest request, HttpServletResponse response, AssemblyInfo info) throws UserSysException{
    	
    	List<AssemblyItemInfo> result = null;
    	
    	String exceptionMsg ="";
    	
		try {
			result = assemblyService.selectAssemblyLocation(info);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN ASSEMBLY COMP LOCATION :: "+exceptionMsg);
		}
		
		return result;
	}	
}
