/*****************************************************************************
 * 프로그램명  : BasketMultiController.java
 * 설     명  : 장바구니 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.09  SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basket.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.basket.constant.BasketConstKey;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.ExcelUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.FileUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 장바구니 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class BasketMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(BasketMultiController.class);    

	@Resource
	private BasketService basketService;
	@Resource
	private AppService appService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/*
	 * 장바구니 목록 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_LIST)
	@ResponseBody
    public Map<String, Object> selectBasketList(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
    	Map<String, Object> result = new HashMap<>();
    	int nTotal = 0;
    	List<BasketInfo> list = new ArrayList<>();
		
    	String exceptionMsg ="";
    	String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부	
		try{
			result = basketService.selectBasketListInfo(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN BASKET LIST RESULT :: "+exceptionMsg);
		}
		result.put("priceYN", priceYN);
		result.put("stockYN", stockYN);	
		return result;
	}	
	
	/*
	 * 장바구니 수량 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_LIST_CNT)
	@ResponseBody
	public int selectBasketListCnt(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		int nTotal = 0;
		String exceptionMsg ="";
		
		try{
			nTotal = basketService.selectBasketListCnt(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN BASKET LIST RESULT :: "+exceptionMsg);
		}
		
		return nTotal;
	}	
	
	/*
	 * 장바구니 그룹 등록
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_ADD_OK)
	@ResponseBody
    public String insertBasketInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		
    	String exceptionMsg ="";
    	JSONObject jsonObject = new JSONObject();
    	boolean bResult = false;
    	// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		
		try{
			bResult = basketService.insertBasket(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN INSERT BASKET :: "+exceptionMsg);
		}
		
		int cartCnt = basketService.selectBasketListCnt(info);
		
		if(bResult){
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("cartCnt", cartCnt );
		}else{
			jsonObject.put("resCode", "1");
									//Fail	
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
		}
		
		return jsonObject.toString();
	}	
	/*
	 * 장바구니 그룹 수정
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_EDIT_OK)
	@ResponseBody
	public String updateBasketInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		
		try{
			bResult = basketService.updateBasket(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN UPDATE BASKET :: "+exceptionMsg);
		}
		
		if(bResult){
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
		}else{
			jsonObject.put("resCode", "1");
									//Fail	
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
		}
		return jsonObject.toString();
	}	
	/*
	 * 장바구니 그룹 삭제
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_DEL_OK)
	@ResponseBody
	public String deleteBasketInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		// 패스 및 파일명 설정
		HttpSession session = request.getSession(true);
		//사용자 언어 취득
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		try{
			bResult = basketService.deleteBasket(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN DELETE BASKET :: "+exceptionMsg);
		}
		int cartCnt = basketService.selectBasketListCnt(info);
		if(bResult){
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("cartCnt", cartCnt );
		}else{
			jsonObject.put("resCode", "1");
									//Fail
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
		}

		return jsonObject.toString();
	}	
	
	/*
	 * 장바구니 목록의 GTC 파일 다운로드
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_GET_GTC_ZIP_FILE_PATH)
	@ResponseBody
	public String doGtcZipdownload(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		JSONObject jsonObject = new JSONObject();
		String errorfile = "";
		// 패스 및 파일명 설정
		HttpSession session = request.getSession();
		//사용자 언어 취득
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		try {
			//압축파일 업로드할 경로 set
 			String path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
			//압축파일의 실제 업로드 경로 앞에 부분만
			String destDir = session.getServletContext().getRealPath(path);


			String uploadPath = destDir + System.getProperty("file.separator") + "cart_gtc" + System.getProperty("file.separator") ;
			String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
			//GTC+ cart_no + time -> zip으로
			String filename = uploadPath + "GTC" + ConstKey.SEP_UNDER + info.getCart_no()+ ConstKey.SEP_UNDER +thisTime;
			String file = filename + ".zip";
			File fileDestDir = new File(uploadPath);
			if(!fileDestDir.exists()) {
				fileDestDir.mkdirs();
			}
			//압축할 파일리스트
			List<String> fileNames = new ArrayList<String>();
			//실제 파일 위치 앞에 경로
			String filePath = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
			
			//fileNames에 압축할 파일 추가 
			List<BasketInfo> tmpFileList = new ArrayList<>();
//			String zipFilePath ="";
			try{
				//fileNames에 압축할 파일 추가 
				tmpFileList = basketService.selectBasketItemFileList(info);
				
				if(tmpFileList.size() !=0){
					for(BasketInfo files : tmpFileList){
						if(files !=null){
	//				String fileName = path+files.getFile_dwl_path() + "/"+ files.getFile_thn_nm();
							String fileName = files.getFile_phy_path();
							logger.debug("fileName : : "+ fileName);
							fileNames.add(fileName);
						}
					}
				}
			}catch(Exception e){
				logger.debug("ERROR IN GTC_ZIP_DOWNLOAD ::" +e.getMessage());
			}
			//파일 압축
			FileUtil.makeZipFileAndReturnPath(file, fileNames);
			
			// 파일의 전체경로 설정
			StringBuffer sbFileName = new StringBuffer("");
			sbFileName.append(file);
			
			errorfile = sbFileName.toString();
			
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("resData", sbFileName.toString());
		} catch(Exception e) {
	    	jsonObject.put("resCode", "1");
	    							//Fail	
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
			jsonObject.put("resData", errorfile);
			logger.debug(e.getMessage());
		}
		  return jsonObject.toString();
	}	
	
	/*
	 * 장바구니 목록의 엑셀 파일 다운로드
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_EXCEL)
	@ResponseBody
	public String doExceldownload(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		JSONObject jsonObject = new JSONObject();
		// 패스 및 파일명 설정
		HttpSession session = request.getSession();
		//사용자 언어 취득
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		//사용자 통화 취득
		String cur_cd = (String) session.getAttribute(ConstKey.SESSION_CURRENCY);
		info.setCur(cur_cd);
		info.setComm_cur(cur_cd);
		if("".equals(info.getCur())){
			info.setCur("CC0077");
		}
		if("".equals(info.getComm_cur())){
			info.setComm_cur("CC0077");
		}
		
		String currency = basketService.selectCurrencyName(info);
//		if("CC0076".equals(cur_cd)){
//			currency ="KRW";
//		}else if("CC0077".equals(cur_cd)){
//			currency ="USD";
//		}else if("CC0078".equals(cur_cd)){
//			currency ="EUR";
//		}else{
//			currency ="USD";
//		}
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부	
		try {
			
			String path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
			String destDir = session.getServletContext().getRealPath(path);
			
			// 
			String uploadPath = destDir + System.getProperty("file.separator") + "excel" + System.getProperty("file.separator") ;
			
			String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
			String filename = "Cart_Items_List" + ConstKey.SEP_UNDER + thisTime;
			String file = filename + ".xls";
			
			List<HashMap> excelList = new ArrayList<HashMap>();
			int nPage = 1;
	
			List<BasketInfo> list = basketService.selectBasketItemListInfo(info);
			List<List<Object>> dataList = new ArrayList<List<Object>>();
	
				for(int index=0 ; index<list.size(); index++) {
					BasketInfo result = (BasketInfo)list.get(index);
			    	List data = new ArrayList();
			    	
			    	data.add(result.getMatnr());
			    	data.add(result.getDesignation());
			    	data.add(result.getGrade());
			    	if("Y".equals(priceYN)){
			    		data.add(currency);
			    		String price = StringUtil.toCommaIncZero(result.getPrice());
			    		data.add(price);
			    	}
			    	data.add(result.getQty());
			    	
			    	if("Y".equals(stockYN)){
			    		if("Y".equals(result.getStock_yn())){
							//In stock
							data.add(LangMng.LANG_D("FR000227", session_lang));
						}else{
										    		//Out of stock
							data.add(LangMng.LANG_D("FR000226", session_lang));
						}
			    	}
			      	if("Y".equals(priceYN)){
			      		//가격정보와 수량정보가 있을 경우 
			      		if(!"".equals(result.getPrice()) && !"".equals(result.getQty())){
			      			String totalPrice = StringUtil.getMultipleQtyAndPrice(result.getQty(), result.getPrice(), result.getCur());
			      			totalPrice = StringUtil.toCommaIncZero(totalPrice);
			      			data.add(totalPrice);
			      		}else{
			      			data.add(0);
			      		}
			      	}
			    	
			    	dataList.add(data);
				}
	
			// 타이틀
			String title = "";
			// 컬럼명
		   	List<List<String>> colList = new ArrayList<List<String>>();
		   	List<String> col = new ArrayList<String>();
		   	//Order No
		   	col.add(LangMng.LANG_D("FR000038", session_lang));
		   	//Designation
		   	col.add(LangMng.LANG_D("FR000039", session_lang));
		   	//Grade
		   	col.add(LangMng.LANG_D("FR000041", session_lang));
		   	//Currency
		   	//Price
		   	if("Y".equals(priceYN)){
		   		col.add(LangMng.LANG_D("FR000045", session_lang));
		   		col.add(LangMng.LANG_D("FR000046", session_lang));
		   	}
		   	//Quantity
		   	col.add(LangMng.LANG_D("FR000059", session_lang));
		   	//Stock
		   	if("Y".equals(stockYN)){
			   	col.add(LangMng.LANG_D("FR000047", session_lang));
		   	}
		   	//Total
			if("Y".equals(priceYN)){
			   	col.add(LangMng.LANG_D("FR000173", session_lang));
			}
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
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("resData", sbFileName.toString());
		} catch(Exception e) {
	    	jsonObject.put("resCode", "1");
    								//Fail	
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
			
		}
        return jsonObject.toString();
	}
	
////////////////////////아이템	
//	/*
//	 * 장바구니 목록 아이템 리스트 중  하나의 아이템에 대한 GTC 파일 다운로드
//	 * @param request Request객체
//	 * @param response Response객체
//	 * @exception UserSysException 시스템예외
//	 */
//	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_GET_GTC_FILE)
//	@ResponseBody
//	public void doGtcdownload(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
//		// 패스 및 파일명 설정
//		String path = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
//		
//		BasketInfo downFileInfo = basketService.selectBasektItemFile(info);
//		String realFilePath ="";
//		
//		try{
//			realFilePath = path + downFileInfo.getFile_dwl_path() +"/"+downFileInfo.getFile_org_nm();
//			logger.debug("realFilePath : : "+realFilePath);
//		}catch(Exception e){
//			realFilePath =path+"\\data\\gtc\\korloy.gtc-package.1-02-055739.zip";
//			logger.debug(e.getMessage());
//		}
//		
//		String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
//		String filename = "GTC" + ConstKey.SEP_UNDER + ConstKey.SEP_UNDER +thisTime;
//		
//		// 파일의 전체경로 설정
//		StringBuffer sbFileName = new StringBuffer("");
//		sbFileName.append(realFilePath);
//		
//		// 파일 다운로드 실행
//		FileDownload fileDownload = new FileDownload(response);
//		fileDownload.download(sbFileName.toString(), realFilePath, request);
//		
//		//다운로드후에 임시생성한 다운로드 파일 삭제
//        FileUtil.deleteFile(sbFileName.toString());
//		
//	}
	
	/*
	 * 장바구니 아이템 목록의 수량 수정
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_ITEM_EDIT_OK)
	@ResponseBody
	public String updateBasketItemInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		// 패스 및 파일명 설정
		HttpSession session = request.getSession(true);
		//사용자 언어 취득
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
        
		try{
			bResult = basketService.updateBasketItemInfo(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN UPDATE BASKET :: "+exceptionMsg);
		}
		
		if(bResult){
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("total_price", info.getTotal_price());
			jsonObject.put("qty", info.getQty());
			jsonObject.put("cur", info.getCur());
			jsonObject.put("item_total", info.getPrice());
			
		}else{
			jsonObject.put("resCode", "1");
									//Fail
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
		}
		return jsonObject.toString();
	}	
	
	/*
	 * 장바구니 아이템 목록의 아이템 단일 삭제
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_BASKET_ITEM_DEL_OK)
	@ResponseBody
	public String deleteBasketItemInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException{
		
		String exceptionMsg ="";
		JSONObject jsonObject = new JSONObject();
		boolean bResult = false;
		
		// 패스 및 파일명 설정
		HttpSession session = request.getSession(true);
		//사용자 언어 취득
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부
		
		try{
			bResult = basketService.deleteBasketItem(info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN UPDATE BASKET :: "+exceptionMsg);
		}
		//삭제 성공 후 다시 아이템 리스트를 그리기 위해
		List<BasketInfo> itemList = basketService.selectBasketItemListInfo(info);
		BigDecimal cartTotal = BigDecimal.ZERO;
		for(BasketInfo item : itemList){
			String itemTotal = StringUtil.getMultipleQtyAndPrice(item.getQty(), item.getPrice(), info.getCur());
			BigDecimal ttl = BigDecimal.valueOf(Double.parseDouble(itemTotal));
			item.setTotal_price(itemTotal);
			cartTotal = cartTotal.add(ttl);
		}
		String finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", cartTotal.toString(), info.getCur());
		info.setTotal_price(finalTtlPrice);
		
		if(bResult){
			jsonObject.put("resCode", "0");
									//success
			jsonObject.put("resMsg", LangMng.LANG_D("FR000075", session_lang));
			jsonObject.put("basket_detail_list", itemList);
			jsonObject.put("total_price", info.getTotal_price());
		}else{
			jsonObject.put("resCode", "1");
									//Fail	
			jsonObject.put("resMsg", LangMng.LANG_D("FR000174", session_lang));
		}
		jsonObject.put("priceYN", priceYN);
		jsonObject.put("stockYN", stockYN);	
		return jsonObject.toString();
	}	
	
	/*
	 * 장바구니 아이템 목록의 아이템 레이어 팝업
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ITEM_DETAIL_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_SHOW_ITEM_DETAIL)
	@ResponseBody
	public Map<String, Object> selectBasketItemPopInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info, AppSearchInfo searchInfo) throws UserSysException{
		
		Map<String, Object> result = new HashMap<>();
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))){
			session_lang = ConfigMng.getLang(request);
			//언어 설정이 되어있지 않은 경우 설정
			searchInfo.setUser_lang(session_lang);
			info.setUser_lang(session_lang);
		}
		
		//선택한 아이템 목록
		Map<String, Object> itemInfo = appService.selectItemInfo(searchInfo);
		//선택한 아이템의 서브 어플리케이션 리스트 
		List<AppInfo> subAppImgList =  appService.selectSubImageList(searchInfo);
		
		//선택한 아이템의 대표 아이템 그룹 정보 
		String ig_cd = basketService.selectIgCd(searchInfo);
		searchInfo.setSearch_ig_cd(ig_cd);
		//선택한 아이템의 심볼 리스트
		List<AppInfo> itemPropertyList = appService.selectItemPropSymbolList(searchInfo);
//		List<AppInfo> itemPropertyList = basketService.selectItemPropSymbolList(searchInfo);
		AppInfo igInfo=  appService.selectItemGroupInfo(searchInfo);
		//선택한 아이템의 대표 아이템 이미지 정보 
		AppInfo igImgInfo=  appService.selectIgImageInfo(searchInfo); 
		
		// 아이템 그룹 설명
		String ig_desc = StringUtil.nvl(LangMng.LANG_D(igInfo.getIg_cd(), session_lang), igInfo.getIg_dscr());
		igInfo.setIg_dscr(ig_desc);
		// TM
		String tm_nm = StringUtil.nvl(LangMng.LANG_D(igInfo.getTm_cd(), session_lang), igInfo.getTm_nm());
		igInfo.setTm_nm(tm_nm);
		//Related Inserts 목록
		List<Map<String, Object>> insertsList = appService.selectRelatedInsertsList(searchInfo);
		// 프로퍼티 목록조회
		List<String> arr_ig_cd = new ArrayList<String>();
		for(Map map : insertsList) {
			if(!arr_ig_cd.contains(map.get("ITEM_GRP"))) {
				arr_ig_cd.add((String)map.get("ITEM_GRP"));
			}
		}
		AppSearchInfo search_prop = new AppSearchInfo();
		search_prop.setIg_cd_arr(arr_ig_cd.toArray(new String[arr_ig_cd.size()]));
		List<AppInfo> prop_list = null;
		if(arr_ig_cd.size() > 0) {
			prop_list = appService.selectItemPropSymbolList(search_prop);
		}
		JSONArray ins_json_prop = AjaxJsonUtil.getJsonData(prop_list);
		//Related Holders 목록
		List<Map<String, Object>> holdersList = appService.selectRelatedHoldersList(searchInfo);
		arr_ig_cd.clear();
		for(Map map : holdersList) {
			if(!arr_ig_cd.contains(map.get("ITEM_GRP"))) {
				arr_ig_cd.add((String)map.get("ITEM_GRP"));
			}
		}
		search_prop.setIg_cd_arr(arr_ig_cd.toArray(new String[arr_ig_cd.size()]));
		if(arr_ig_cd.size() > 0) {
			prop_list = appService.selectItemPropSymbolList(search_prop);
		}
		JSONArray hold_json_prop = AjaxJsonUtil.getJsonData(prop_list);
		
		//Recommended Cutting Speeds 목록 취득 
		List<AppInfo> cuttingSpeedList = appService.selectCuttingSpeedList(searchInfo);
		//Spare Parts 목록 취득 
		List<AppInfo> sparePartsList = appService.selectSparePartsList(searchInfo);
		// ISO metric/inch 계산식 단위 및 값 취득
		List<AppInfo> isoCalList = appService.selectIsoCalList(searchInfo);
		
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부		
		String inchYN = CodeTableMng.getCodeName("CM0019", "CC0204"); // 인치 사용여부		
		
		result.put("itemInfo", itemInfo);
		result.put("igImgInfo", igImgInfo);
		result.put("igInfo", igInfo);
		result.put("subAppImgList", subAppImgList);
		result.put("itemPropertyList", itemPropertyList);
		result.put("insertsList", insertsList);
		result.put("holdersList", holdersList);
		result.put("cuttingSpeedList", cuttingSpeedList);
		result.put("sparePartsList", sparePartsList);
		result.put("ins_prop", ins_json_prop);
		result.put("hold_prop", hold_json_prop);
		result.put("isoCalList", isoCalList);
		result.put("priceYN", priceYN);
		result.put("stockYN", stockYN);		
		result.put("inchYN", inchYN);		
		
		return result;
	}	
	

	/**
	 * 파일다운로드
	 * @param request
	 * @param response
	 * @param info
	 * @throws UserSysException
	 */
	@RequestMapping(value = ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_FILE)
	@ResponseBody
	public void doOrderCartExcelFileDown(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException {

		String atch = info.getExcel_file_path();
//		String[] fileName_arr = atch.split("\\");
//		String fileName = fileName_arr[fileName_arr.length - 1]; 
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(atch, info.getAtch_file_name(), request);
		
		//다운로드후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(atch);
	}
	/**
	 * 파일다운로드 - gtcZip
	 * @param request
	 * @param response
	 * @param info
	 * @throws UserSysException
	 */
	@RequestMapping(value = ConstUrl.BASKET_URL, params = ConstKey.ACTION_ID + "=" + BasketConstKey.ACTION_GET_GTC_ZIP_FILE)
	@ResponseBody
	public void doGtcZipFileDown(HttpServletRequest request, HttpServletResponse response, BasketInfo info) throws UserSysException {
		
		String atch = info.getGtc_file_path();
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(atch, info.getGtc_file_name(), request);
		
		//다운로드후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(atch);
	}
}
