/*****************************************************************************
 * 프로그램명  : OrderServiceImpl.java
 * 설     명  : 견적 요청 관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.01   YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.order.dao.OrderDao;
import com.eaction.framework.business.logic.order.model.OrderAssemblyInfo;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.UploadFile;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.FileUploadUtil;
import com.eaction.framework.common.util.SendEmail;
import com.eaction.framework.common.util.StringUtil;

/**
 * 견적 요청 관리 서비스
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( OrderServiceImpl.class );
	
	/** 로그인 처리 DAO */
	@Resource
	private OrderDao orderDao;


	/**
	 * 주문요청서 작성 페이지 주문자 초기 표시값
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public OrderInfo selectOrderUserInfo(OrderInfo paramInfo) throws UserSysException {
		logger.debug("selectOrderUserInfo--{}", ConstKey.START_LOG);
		logger.debug("selectOrderUserInfo--{}", ConstKey.END_LOG);
		return orderDao.selectOrderUserInfo(paramInfo);
	}

	@Override
	public Map<String, Object> selectOrderBasketList(OrderInfo paramInfo) throws UserSysException {
		logger.debug("selectOrderBasketList--{}", ConstKey.START_LOG);
		Map<String, Object> map= new HashMap<>();
		
		int nTotal = orderDao.selectOrderBasketListCnt(paramInfo);
		List<BasketInfo> result = orderDao.selectOrderBasketList(paramInfo);
		logger.debug("result size ==> " + result.size());
		
		//카트의 토탈
//		BigDecimal cartTotal = BigDecimal.ZERO;
		BigDecimal k_cartTotal = BigDecimal.ZERO;	// 한화
		BigDecimal u_cartTotal = BigDecimal.ZERO;	// 달러화
		BigDecimal e_cartTotal = BigDecimal.ZERO;	// 유로화
		if (result.size() != 0) {
			for(BasketInfo basket : result) {
				String k_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getK_kbetr(), "CC0076");
				String u_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getU_kbetr(), "CC0077");
				String e_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getE_kbetr(), "CC0078");
				BigDecimal k_ttl = BigDecimal.valueOf(Double.parseDouble(k_basketTotal));
				BigDecimal u_ttl = BigDecimal.valueOf(Double.parseDouble(u_basketTotal));
				BigDecimal e_ttl = BigDecimal.valueOf(Double.parseDouble(e_basketTotal));
				k_cartTotal = k_cartTotal.add(k_ttl);
				u_cartTotal = u_cartTotal.add(u_ttl);
				e_cartTotal = e_cartTotal.add(e_ttl);
				
				logger.debug("basketTotalPrice ==> " + basket.getTotal_price());
				
			}
			String K_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", k_cartTotal.toString(), paramInfo.getComm_cur());
			String U_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", u_cartTotal.toString(), paramInfo.getComm_cur());
			String E_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", e_cartTotal.toString(), paramInfo.getComm_cur());
			map.put("k_basketTotalPrice", K_finalTtlPrice);
			map.put("u_basketTotalPrice", U_finalTtlPrice);
			map.put("e_basketTotalPrice", E_finalTtlPrice);
		}
		map.put("nTotal", nTotal);
		map.put("list", result);
		map.put("comm_curr", paramInfo.getComm_cur());
		logger.debug("selectOrderBasketList--{}", ConstKey.END_LOG);
		return map;
	}

	
	/**
	 * 선택 장바구니 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public List<BasketInfo> selectOrderBasketItemList(OrderInfo paramInfo) throws UserSysException {
		logger.debug("selectOrderBasketItemList--{}", ConstKey.START_LOG);
		List<BasketInfo> result = orderDao.selectOrderBasketItemList(paramInfo);
		logger.debug("selectOrderBasketItemList--{}", ConstKey.END_LOG);
		return result;
	}
	
	/**
	 * 선택 어셈블리 그룹 리스트
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public Map<String, Object> selectOrderAssemList(OrderInfo paramInfo) throws UserSysException {
		logger.debug("selectOrderAssemList--{}", ConstKey.START_LOG);
		Map<String, Object> map= new HashMap<>();
		
		int nTotal = orderDao.selectOrderAssemListCnt(paramInfo);
		List<BasketInfo> result = orderDao.selectOrderAssemList(paramInfo);
 		logger.debug("result size ==> " + result.size());
		
		//카트의 토탈
//		BigDecimal cartTotal = BigDecimal.ZERO;
		BigDecimal k_cartTotal = BigDecimal.ZERO;	// 한화
		BigDecimal u_cartTotal = BigDecimal.ZERO;	// 달러화
		BigDecimal e_cartTotal = BigDecimal.ZERO;	// 유로화
		String matnr = "";
		String assem_nm = "";
		if (result.size() != 0) {
			for(BasketInfo basket : result) {
				String k_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getK_kbetr(), "CC0076");
				String u_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getU_kbetr(), "CC0077");
				String e_basketTotal = StringUtil.getMultipleQtyAndPrice("1", basket.getE_kbetr(), "CC0078");
				BigDecimal k_ttl = BigDecimal.valueOf(Double.parseDouble(k_basketTotal));
				BigDecimal u_ttl = BigDecimal.valueOf(Double.parseDouble(u_basketTotal));
				BigDecimal e_ttl = BigDecimal.valueOf(Double.parseDouble(e_basketTotal));
				k_cartTotal = k_cartTotal.add(k_ttl);
				u_cartTotal = u_cartTotal.add(u_ttl);
				e_cartTotal = e_cartTotal.add(e_ttl);
				matnr = String.format("%s%s,", matnr, basket.getMatnr());
				assem_nm = String.format("%s%s,", assem_nm, basket.getCart_nm());
			}
			String K_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", k_cartTotal.toString(), paramInfo.getComm_cur());
			String U_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", u_cartTotal.toString(), paramInfo.getComm_cur());
			String E_finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", e_cartTotal.toString(), paramInfo.getComm_cur());
			map.put("k_basketTotalPrice", K_finalTtlPrice);
			map.put("u_basketTotalPrice", U_finalTtlPrice);
			map.put("e_basketTotalPrice", E_finalTtlPrice);
			matnr = matnr.substring(0, matnr.length()-1);
			map.put("matnr", matnr);
			assem_nm = assem_nm.substring(0, assem_nm.length()-1);
			map.put("assem_nm", assem_nm);
		}
		map.put("nTotal", nTotal);
		map.put("list", result);
		map.put("comm_curr", paramInfo.getComm_cur());
		logger.debug("selectOrderAssemList--{}", ConstKey.END_LOG);
		return map;
	}
	
	/**
	 * 선택 어셈블리 그룹 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public List<OrderAssemblyInfo> selectOrderAssemItemList(OrderInfo paramInfo) throws UserSysException {
		logger.debug("selectOrderAssemItemList--{}", ConstKey.START_LOG);
		List<OrderAssemblyInfo> result = orderDao.selectOrderAssemItemList(paramInfo);
		logger.debug("selectOrderAssemItemList--{}", ConstKey.END_LOG);
		return result;
	}

	/**
	 * 견적요청서 등록
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertOrder(HttpServletRequest request, OrderInfo paramOrderInfo) throws UserSysException {
		logger.debug("insertOrder--{}", ConstKey.START_LOG);
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		boolean bResult = true;
		int iResult = 0;
		String nthCart = "";	// 2개이상 카트 선택시 외 N건 표시
		
		String org_cart_no = paramOrderInfo.getCart_no();
		//cart_no가 여러 개일 수 있음
		String[] tmpArr = paramOrderInfo.getCart_no().split(",");
		paramOrderInfo.setCart_no_arr(tmpArr);
		nthCart = Integer.toString(tmpArr.length - 1); 
		for (String cart_no : tmpArr) {
			paramOrderInfo.setCart_no(cart_no);
			iResult = orderDao.insertOrder(paramOrderInfo);
		}

    	if(iResult < 1) {
    		bResult = false;
    	} else {
    		// 파일 업로드
			String outpath = "emailAtch";
    		List<UploadFile> uploadFileList = FileUploadUtil.uploadFormFile(request, outpath);
//-----------------------------------------------------
    		// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
			List<String> uploadPath = new ArrayList();
			uploadPath.add(paramOrderInfo.getExcel_file_path());
			for(UploadFile uploadFile : uploadFileList) {
				uploadPath.add(uploadFile.getFull_path() + uploadFile.getFileName());
			}
//-----------------------------------------------------
    		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
    		// 메일발송
    		List<String> mail_cc = new ArrayList(); /* 메일 참조자 */
    		OrderInfo mailReceiver = orderDao.selectOrderMailReceiver(paramOrderInfo);	// 수신자
    		if (!"".equals(mailReceiver.getEmail_cc1())) mail_cc.add(mailReceiver.getEmail_cc1());	// 참조자1
    		if (!"".equals(mailReceiver.getEmail_cc2())) mail_cc.add(mailReceiver.getEmail_cc2());	// 참조자2
//// =============================================    		
//    		mail_cc = new ArrayList();
//    		mail_cc.add("lemon.yu@hanmail.net");
//    		mail_cc.add("jiyoo@e-act.co.kr");
// =============================================
    		paramOrderInfo.setCart_no(tmpArr[0]);
    		String cart_nm = orderDao.selectOrderInsertBasketInfo(paramOrderInfo).getCart_nm();
    		String setfrom =  paramOrderInfo.getLogin_user_id();	// login 
    		String mail_from_name = "KORLOY";
    		String tomail = mailReceiver.getEmail_to(); 	// 국가별 담당자
// =============================================    		
//    		tomail = "esther.yoo329@gmail.com";
// =============================================
    		// [Digital Catalog] Request
    		String title = LangMng.LANG_D("FR000308", session_lang) + cart_nm + ", ("  + nthCart + ") ("+ userInfo.getUser_id() + ")";
    		// 메일본문 content 구성
    		Map<String, String> convertMap = new HashMap<String, String>();
    		// Received request from
    		convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000306", session_lang));
    		convertMap.put("#MAIL_TXT_2#", userInfo.getUser_id());
    		// Cart Name
    		convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000070", session_lang));
    		convertMap.put("#MAIL_TXT_4#", cart_nm + ", ("  + nthCart + ")");
			// Email address
			convertMap.put("#MAIL_TXT_5#", LangMng.LANG_D("FR000009", session_lang));
			convertMap.put("#MAIL_TXT_6#", userInfo.getUser_id());
			// User Name
			convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000017", session_lang));
			convertMap.put("#MAIL_TXT_8#", userInfo.getUser_name());
			// Company Name
			convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000099", session_lang));
			convertMap.put("#MAIL_TXT_10#", paramOrderInfo.getCompany());
			// Company Email Address
			convertMap.put("#MAIL_TXT_11#", LangMng.LANG_D("FR000100", session_lang));
			convertMap.put("#MAIL_TXT_12#", paramOrderInfo.getCemail());
			// Company Phone
			convertMap.put("#MAIL_TXT_19#", LangMng.LANG_D("FR000241", session_lang));
			convertMap.put("#MAIL_TXT_20#", userInfo.getUser_com_tel());
			// Phone
			// (추가됨 21.11.15)
			// Request for Quotation
			convertMap.put("#MAIL_TXT_17#", LangMng.LANG_D("FR000217", session_lang));
			convertMap.put("#MAIL_TXT_18#", paramOrderInfo.getBigo().replace("\n", "<br/>"));
			//------------------------------
			convertMap.put("#MAIL_TXT_13#", LangMng.LANG_D("FR000194", session_lang));
			convertMap.put("#MAIL_TXT_14#", userInfo.getUser_mobile());
			// This is an automatically generated email, please do not reply to this message.
			convertMap.put("#MAIL_TXT_15#", LangMng.LANG_D("FR000304", session_lang));
			// Copyright ⓒ KORLOY
			convertMap.put("#MAIL_TXT_16#", LangMng.LANG_D("FR000008", session_lang));
			
			String destDir2 = session.getServletContext().getRealPath("/img");
			Map<String, String> imgMap = new HashMap<String, String>();
//			imgMap.put("image1", destDir2 + System.getProperty("file.separator") + "btn_verify.png");
			
			String content = "";
			try {
				// readTmplConvertMail(메일템플릿파일경로, convertMap)
//				content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
				String destDir = session.getServletContext().getRealPath("/mail");
				content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_request.html", convertMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			bResult = SendOrderRequestMail(setfrom, mail_from_name, tomail, title, content, uploadPath, mail_cc);
		}
    	
    	logger.debug("insertOrder--{}", ConstKey.END_LOG);

    	return bResult;
	}
	
	/**
	 * 어셈블리 견적요청서 메일발송(메일발송만)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public boolean sendAssemblyOrderMail(HttpServletRequest request, OrderInfo paramInfo) throws UserSysException {
		logger.debug("sendAssemblyOrderMail--{}", ConstKey.START_LOG);
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		boolean bResult = true;
		int iResult = 0;
		String nthAssem = "";	// 2개이상 어셈블리 선택시 외 N건 표시
		
		String org_assem_no = paramInfo.getAssem_no();
		//cart_no가 여러 개일 수 있음
		String[] tmpArr = paramInfo.getAssem_no().split(",");
		paramInfo.setAssem_no_arr(tmpArr);
		nthAssem = Integer.toString(tmpArr.length - 1); 
		
		// 파일 업로드
		String outpath = "emailAtch";
		List<UploadFile> uploadFileList = FileUploadUtil.uploadFormFile(request, outpath);
		//-----------------------------------------------------
		// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
		List<String> uploadPath = new ArrayList();
		uploadPath.add(paramInfo.getExcel_file_path());
		for(UploadFile uploadFile : uploadFileList) {
			uploadPath.add(uploadFile.getFull_path() + uploadFile.getFileName());
		}
		//-----------------------------------------------------
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		// 메일발송
		List<String> mail_cc = new ArrayList(); /* 메일 참조자 */
		OrderInfo mailReceiver = orderDao.selectOrderMailReceiver(paramInfo);	// 수신자
		if (!"".equals(mailReceiver.getEmail_cc1())) mail_cc.add(mailReceiver.getEmail_cc1());	// 참조자1
		if (!"".equals(mailReceiver.getEmail_cc2())) mail_cc.add(mailReceiver.getEmail_cc2());	// 참조자2
//
//// =============================================    		
//		mail_cc = new ArrayList();
//		mail_cc.add("lemon.yu@hanmail.net");
//		mail_cc.add("jiyoo@e-act.co.kr");
// =============================================   
		String setfrom =  paramInfo.getLogin_user_id();	// login 
		String mail_from_name = "KORLOY";
		String tomail = mailReceiver.getEmail_to(); 	// 국가별 담당자
// =============================================    		
//		tomail = "syjeong@e-act.co.kr";
//=============================================    	
		paramInfo.setAssem_no(tmpArr[0]);
		String assem_nm = orderDao.selectOrderInsertAssemblyInfo(paramInfo).getAssem_nm();
		// [Digital Catalog] Request
		String title = LangMng.LANG_D("FR000308", session_lang) + assem_nm + ", ("  + nthAssem + ") ("+ userInfo.getUser_id() + ")";
		// 메일본문 content 구성
		Map<String, String> convertMap = new HashMap<String, String>();
		// Received request from
		convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000306", session_lang));
		convertMap.put("#MAIL_TXT_2#", userInfo.getUser_id());
		// Assembly Name
		convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000132", session_lang));
		convertMap.put("#MAIL_TXT_4#", assem_nm + ", ("  + nthAssem + ")");
		// Email address
		convertMap.put("#MAIL_TXT_5#", LangMng.LANG_D("FR000009", session_lang));
		convertMap.put("#MAIL_TXT_6#", userInfo.getUser_id());
		// User Name
		convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000017", session_lang));
		convertMap.put("#MAIL_TXT_8#", userInfo.getUser_name());
		// Company Name
		convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000099", session_lang));
		convertMap.put("#MAIL_TXT_10#", paramInfo.getCompany());
		// Company Email Address
		convertMap.put("#MAIL_TXT_11#", LangMng.LANG_D("FR000100", session_lang));
		convertMap.put("#MAIL_TXT_12#", paramInfo.getCemail());
		// Company Phone
		convertMap.put("#MAIL_TXT_19#", LangMng.LANG_D("FR000241", session_lang));
		convertMap.put("#MAIL_TXT_20#", userInfo.getUser_com_tel());
		// Phone
		convertMap.put("#MAIL_TXT_13#", LangMng.LANG_D("FR000194", session_lang));
		convertMap.put("#MAIL_TXT_14#", userInfo.getUser_mobile());
		// (추가됨 21.11.15)
		// Request for Quotation
		convertMap.put("#MAIL_TXT_17#", LangMng.LANG_D("FR000217", session_lang));
		convertMap.put("#MAIL_TXT_18#", paramInfo.getBigo().replace("\n", "<br/>"));
		//------------------------------
		// This is an automatically generated email, please do not reply to this message.
		convertMap.put("#MAIL_TXT_15#", LangMng.LANG_D("FR000304", session_lang));
		// Copyright ⓒ KORLOY
		convertMap.put("#MAIL_TXT_16#", LangMng.LANG_D("FR000008", session_lang));
		
		String destDir2 = session.getServletContext().getRealPath("/img");
		Map<String, String> imgMap = new HashMap<String, String>();
//		imgMap.put("image1", destDir2 + System.getProperty("file.separator") + "btn_verify.png");
		
		String content = "";
		try {
			// readTmplConvertMail(메일템플릿파일경로, convertMap)
//			content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
			String destDir = session.getServletContext().getRealPath("/mail");
			content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_request.html", convertMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		bResult = SendOrderRequestMail(setfrom, mail_from_name, tomail, title, content, uploadPath, mail_cc);
	
    	logger.debug("sendAssemblyOrderMail--{}", ConstKey.END_LOG);

    	return bResult;
	}

	/**
	 * 주문서 메일 발송
	 * @param paramInfo
	 * @param uploadFilePath
	 * @return
	 * @throws UserSysException
	 */
	public boolean SendOrderRequestMail(String setfrom, String mail_from_name, String tomail, String title, String content, List<String> uploadPath, List<String> mail_cc) throws UserSysException {
		boolean prcsResult = false; 
		String result = "";
		try {
			result = SendEmail.sendEmail(setfrom, mail_from_name, tomail, title, content, uploadPath, mail_cc);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		if ("SUC".equals(result)) {
			prcsResult = true;
		}
		return prcsResult;
	}

}
