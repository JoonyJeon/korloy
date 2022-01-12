/*****************************************************************************
 * 프로그램명  : OrderMultiController.java
 * 설     명  : 주문 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.25  SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
import com.eaction.framework.business.logic.order.constant.OrderConstKey;
import com.eaction.framework.business.logic.order.model.OrderAssemblyInfo;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.business.logic.order.service.OrderService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.ExcelUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.FileUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 장바구니 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class OrderMultiController {
	/** 로그처리 객체 */
    private static final Logger logger = LoggerFactory.getLogger( OrderMultiController.class );

	@Resource
	private BasketService basketService;
	@Resource
	private OrderService orderService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	

	/**
	 * 주문서 페이지 Create Excel
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.ORDER_URL, params = ConstKey.ACTION_ID + "=" + OrderConstKey.ORDER_CART_LIST_EXCEL_OK)
	@ResponseBody
	public JSONObject doOrderBasketListExcel(HttpServletRequest request, HttpServletResponse response, OrderInfo info) throws UserSysException {
    	JSONObject jsonObject = new JSONObject();
    	// 패스 및 파일명 설정
    	HttpSession session = request.getSession();
    	//사용자 언어 취득
    	String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
    	String orderType = "CART";	// Cart > 주문서
		if(!"".equals(info.getAssem_no())){	// Assembly > 주문서	
			orderType = "ASSEM";
		}
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부	
		try {
			String path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
			String destDir = session.getServletContext().getRealPath(path);
			
			// 
			String uploadPath = destDir + System.getProperty("file.separator") + "excel" + System.getProperty("file.separator") ;
			
			String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
			String filename = "cart_list(" + thisTime + ")";
			String file = filename + ".xls";
			
			
			//cart_no가 여러 개일 수 있음
//			String[] tmpArr = info.getCart_no().split(",");
			String[] tmpArr = new String[0];
			info.setComm_cur(ConfigMng.getCurrency(request));
			
			// 엑셀
			List<HashMap> excelList = new ArrayList<HashMap>();
			int nPage = 1;
			
			List<List<Object>> dataList = new ArrayList<List<Object>>();
			// ----------
			
			if ("CART".equals(orderType)) {
				tmpArr = info.getCart_no().split(",");
				info.setCart_no_arr(tmpArr);
				List<BasketInfo> list = orderService.selectOrderBasketItemList(info);
				
				for(int index=0 ; index<list.size(); index++) {
					BasketInfo result = (BasketInfo)list.get(index);
					List data = new ArrayList();
					
					data.add(result.getCart_nm());
					data.add(result.getMatnr());
			    	data.add(result.getDesignation());
			    	data.add(result.getGrade());
			    	if ("Y".equals(priceYN)) {
			    		data.add(result.getCur());
			    		String price = StringUtil.toCommaIncZero(result.getPrice());
			    		data.add(price);
			    	}
			    	data.add(result.getQty());

		    		if("Y".equals(stockYN)) {
				    	if("Y".equals(result.getStock_yn())){
			    									//In stock
			    			data.add(LangMng.LANG_D("FR000227", session_lang));
			    		}else{
			    									//Out of stock
			    			data.add(LangMng.LANG_D("FR000226", session_lang));
			    		}
			    	}
			    	if ("Y".equals(priceYN)) {
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
			} else {
				tmpArr = info.getAssem_no().split(",");
				info.setAssem_no_arr(tmpArr);
				List<OrderAssemblyInfo> list = orderService.selectOrderAssemItemList(info);
				
				for(int index=0 ; index<list.size(); index++) {
					OrderAssemblyInfo result = (OrderAssemblyInfo)list.get(index);
					List data = new ArrayList();
					
					data.add(result.getAssem_nm());
					data.add(result.getMatnr());
			    	data.add(result.getDesignation());
			    	data.add(result.getGrade());
			    	if ("Y".equals(priceYN)) {
				    	data.add(result.getCur());
				    	String price = StringUtil.toCommaIncZero(result.getPrice());
				    	data.add(price);
			    	}
			    	data.add(result.getQty());
			    	
			    	if ("Y".equals(stockYN)) {
			    		if("Y".equals(result.getStock_yn())){
			    			//In stock
			    			data.add(LangMng.LANG_D("FR000227", session_lang));
			    		}else{
			    			//Out of stock
			    			data.add(LangMng.LANG_D("FR000226", session_lang));
			    		}
			    	}
			    	if ("Y".equals(priceYN)) {
			    		
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
			}

			
			
			
			// 타이틀
			String title = "";
			// 컬럼명
			List<List<String>> colList = new ArrayList<List<String>>();
			List<String> col = new ArrayList<String>();
			if ("CART".equals(orderType)) {
				//Cart Name
				col.add(LangMng.LANG_D("FR000070", session_lang));
			} else {
				//Assembly 그룹명
				col.add(LangMng.LANG_D("FR000132", session_lang));
			}
		   	//Order No
		   	col.add(LangMng.LANG_D("FR000038", session_lang));
		   	//Designation
		   	col.add(LangMng.LANG_D("FR000039", session_lang));
		   	//Grade
		   	col.add(LangMng.LANG_D("FR000041", session_lang));
		   	if ("Y".equals(priceYN)) {
			   	//Currency
			   	col.add(LangMng.LANG_D("FR000045", session_lang));
			   	//Price
			   	col.add(LangMng.LANG_D("FR000046", session_lang));
		   	}
			   	//Quantity
			   	col.add(LangMng.LANG_D("FR000059", session_lang));
		   	if ("Y".equals(stockYN)) {
			   	//Stock
			   	col.add(LangMng.LANG_D("FR000047", session_lang));
		   	}
		   	if ("Y".equals(priceYN)) {
		   		//Total
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
			StringBuffer sbFileName = new StringBuffer();
			sbFileName.append(uploadPath);
			sbFileName.append(file);
			
			// 파일 다운로드 실행
//			FileDownload fileDownload = new FileDownload(response);
//			fileDownload.download(sbFileName.toString(), file, request);
			
			// 엑셀 재다운일 경우 기존 파일 삭제처리
//			if("N".equals(info.getFirst_excel())) {
//				FileUtil.deleteFile(info.getExcel_file_path());
//			}

			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
			jsonObject.put("resData", sbFileName.toString());
		} catch(Exception e) {
	    	jsonObject.put("resCode", "1");
			jsonObject.put("resMsg", "Fail");
			
			logger.debug("ERROR WHILE ORDER LIST EXCEL :: " + e.getMessage());
		}
		
		return jsonObject;
	}
	
	/**
	 * 화면에서 통화 값 수정 시 금액 새로 조회하여 표시
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.ORDER_URL, params = ConstKey.ACTION_ID + "=" + OrderConstKey.ORDER_CART_LIST_OK)
	@ResponseBody
	public String doSelectOrderCartList(HttpServletRequest request, HttpServletResponse response, OrderInfo info ) throws UserSysException {
		logger.debug("doSelectOrderCartList--{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();

		Map<String, Object> map= new HashMap<>();	// 선택한 장바구니 정보 리스트 map
		
		//cart_no가 여러 개일 수 이거나 없을 수있음
		if(!"".equals(info.getCart_no())){
			String[] tmpArr = info.getCart_no().split(",");
			info.setCart_no_arr(tmpArr);
		}
		try {
			map = orderService.selectOrderBasketList(info);
		} catch (Exception e) {
			jsonObject.put("resCode", "1");
			// 주문요청 이메일 발송 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
			return jsonObject.toString();
		}

		// 성공:0
		jsonObject.put("resCode", "0");
		// 주문요청서를 전송하였습니다.
		jsonObject.put("resMsg", "Success");
		jsonObject.put("resData", map);
		
		logger.debug("doSelectOrderCartList--{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}
	
	/**
	 * 주문서 등록
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.ORDER_URL, params = ConstKey.ACTION_ID + "=" + OrderConstKey.ORDER_INSERT_OK)
	@ResponseBody
	public String doInsertOrder(HttpServletRequest request, HttpServletResponse response, OrderInfo info) throws UserSysException {
		logger.debug("doInsertOrder--{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = false;
		String orderType = "CART";	// Cart > 주문서
		if(!"".equals(info.getAssem_no())){	// Assembly > 주문서	
			orderType = "ASSEM";
		}
		
		// 생성된 주문서가 없으면
		JSONObject excel_result = doOrderBasketListExcel(request, response, info);
		logger.debug("excel_result ==>" + excel_result.get("resCode"));
		logger.debug("resData ==>" + excel_result.get("resData"));
		
		info.setExcel_file_path(excel_result.get("resData").toString());
		if ("1".equals(excel_result.get("resCode"))) {
			jsonObject.put("resCode", "2");
			jsonObject.put("resMsg", "Fail");
			return jsonObject.toString();
		}
		
		
		try{
			if ("CART".equals(orderType)) {
				result = orderService.insertOrder(request, info);
			} else {
				result = orderService.sendAssemblyOrderMail(request, info);
			}
		} catch(Exception e) {
			jsonObject.put("resCode", "2");
			// 주문요청 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
			return jsonObject.toString();
		} 

		// 성공:0
		if(result){
			// 성공했으면 생성했던 주문서 엑셀파일 삭제
			FileUtil.deleteFile(info.getExcel_file_path());
			
			jsonObject.put("resCode", "0");
			// 주문요청서를 전송하였습니다.
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 주문요청 이메일 발송 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("doInsertOrder--{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}

	/**
	 * 파일다운로드
	 * @param request
	 * @param response
	 * @param info
	 * @throws UserSysException
	 */
	@RequestMapping(value = ConstUrl.ORDER_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_FILE)
	@ResponseBody
	public void doOrderCartExcelFileDown(HttpServletRequest request, HttpServletResponse response, OrderInfo info) throws UserSysException {

		String atch = info.getExcel_file_path();
//		String[] fileName_arr = atch.split("\\");
//		String fileName = fileName_arr[fileName_arr.length - 1]; 
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(atch, info.getAtch_file_name(), request);
		
		//다운로드후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(atch);
	}
}
