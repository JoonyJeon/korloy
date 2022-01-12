/*****************************************************************************
 * 프로그램명  : UserServiceImpl.java
 * 설     명  : 회원정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.21  YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.user.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.user.dao.UserDao;
import com.eaction.framework.business.logic.user.model.UserInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.AES256Util;
import com.eaction.framework.common.util.SendEmail;

/**
 * 회원정보관리 서비스
 * @author  eaction
 * @version 1.0
 */
/**
 * @author esthe
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( UserServiceImpl.class );

	@Resource
	private UserDao userDao;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	

  	/**
  	 * 회원정보 조회 
  	 * @param userInfo
  	 * @return
  	 * @throws UserSysException
  	 */
	@Override
	public UserInfo selectUserInfo(UserInfo userInfo) throws UserSysException {
		UserInfo resultInfo = userDao.selectUserInfo(userInfo); 
		return resultInfo;
	}


	/**
	 * 회원정보 등록 
	 * @param userInfo
	 * @return 처리결과 값
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertUser(HttpServletRequest request, UserInfo userInfo) throws UserSysException, UnsupportedEncodingException {
		logger.debug("insertUser--{}", ConstKey.START_LOG);
		//사용자 언어 취득
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		boolean result = true;
		
		// 이메일 confirm을 위해 cert_key 생성
		String cert_key = AES256Util.randomUuid();
		userInfo.setEmail_cert_key(cert_key);
		userInfo.setUser_grp(ConfigMng.getValue(IPropertyKey.UG0004));
		
		int iResult = userDao.insertUser(userInfo);
		
		if (iResult < 1) {
			result = false;
//			return result;
		} else {
			// 메일발송
			String href = userInfo.getConfirm_url() + userInfo.getEmail_cert_key() + "&user_id=" + userInfo.getUser_id();
			String setfrom =  ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
			String mail_from_name = "KORLOY";
			String tomail = userInfo.getUser_id();

			// Welcome! KORLOY Digital Catalog
			String title = LangMng.LANG_D("FR000300", session_lang);
			Map<String, String> convertMap = new HashMap<String, String>();
			// Thank you for signing up!
			convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000301", session_lang));
			// We just need you to verify your email address to complete setting up your account.
			convertMap.put("#MAIL_TXT_2#", LangMng.LANG_D("FR000302", session_lang));
			// Email address
			convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000009", session_lang));
			convertMap.put("#MAIL_TXT_4#", userInfo.getUser_id());
			convertMap.put("#MAIL_TXT_5#", href);
			// This is an automatically generated email, please do not reply to this message.
			convertMap.put("#MAIL_TXT_6#", LangMng.LANG_D("FR000304", session_lang));
			// Terms of use and Legal disclaimer, Privacy Statement
			convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000007", session_lang));
			// Copyright ⓒ KORLOY
			convertMap.put("#MAIL_TXT_8#", LangMng.LANG_D("FR000008", session_lang));
			// Verify my Email
			convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000303", session_lang));
			String content = "";
			try {
				// readTmplConvertMail(메일템플릿파일경로, convertMap)
//				content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
				String destDir = session.getServletContext().getRealPath("/mail");
				content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_certify.html", convertMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
//-----------------------------------------------------
			List<String> uploadPath = new ArrayList();
//			uploadPath.add("C:\\ryan.PNG");
//			uploadPath.add("C:\\qjffp.PNG");
//-----------------------------------------------------
			result = userJoinSendMail(setfrom, mail_from_name, tomail, title, content, uploadPath, null);
		}
		
		logger.debug("insertUser--{}", ConstKey.END_LOG);
		return result;
	}


	/**
	 * 회원가입 시 이메일 중복 체크 
	 * @param userInfo
	 * @return 조회 된 row 갯수
	 * @throws UserSysException
	 */
	@Override
	public String selectUserEmailDuplChk(UserInfo userInfo) throws UserSysException {
		logger.debug("selectUserEmailDuplChk--{}", ConstKey.START_LOG);
//		String result = "";
		
		String sResult = userDao.selectUserEmailDuplChk(userInfo);
		
//		if((sResult).equals("NEW")) {	// DB에 없는 신규
//			
//		} else if((sResult).equals("PRE")) {	// DB에는 있으나 이메일 인증 완료하지 않아 회원이 아님
//			
//		} else {	// 'DUPL': 활성화 되어있는 회원이 있음, 중복
//			
//		}
		logger.debug("selectUserEmailDuplChk--{}", ConstKey.END_LOG);
		return sResult;
	}
	
	/**
	 * "PRE" 회원 email_cert_key 조회 (메일발송용)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public boolean selectUserEmailCertInfo(HttpServletRequest request, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("selectUserEmailCertInfo--{}", ConstKey.START_LOG);
		//사용자 언어 취득
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		boolean result = false;		// 메일발송 처리결과
		boolean uResult = false;	// (Optional) info 업데이트 처리 결과
		UserInfo userInfo = userDao.selectUserEmailCertInfo(paramUserInfo);
		userInfo.setConfirm_url(paramUserInfo.getConfirm_url());
		// 기존에 입력했던 name과 pw를 다른 값이 넘어왔을경우 대비하여 update
		uResult = updateUserJoinInfo(paramUserInfo);
		
		if (!uResult) {
			result = false;
		} else {
			String href = "";
			String setfrom = "";
			String mail_from_name = "";
			String tomail = "";
			String title = "";
			String content = "";
			// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
//-----------------------------------------------------
			List<String> uploadPath = new ArrayList();
//-----------------------------------------------------
			// USE_YN ==> 'N':회원가입 이메일재발송, 'Y':비밀번호변경 이메일재발송
			if (!(paramUserInfo.getUse_yn()).equals("Y")) {
				href = paramUserInfo.getConfirm_url() + userInfo.getEmail_cert_key() + "&user_id=" + userInfo.getUser_id();
				setfrom =  ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
				mail_from_name = "KORLOY";
				tomail = paramUserInfo.getUser_id();
				// Welcome! KORLOY Digital Catalog	
				title = LangMng.LANG_D("FR000300", session_lang);
				// 메일본문 content 구성
				Map<String, String> convertMap = new HashMap<String, String>();
				// Thank you for signing up!
				convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000301", session_lang));
				// We just need you to verify your email address to complete setting up your account.
				convertMap.put("#MAIL_TXT_2#", LangMng.LANG_D("FR000302", session_lang));
				// Email address
				convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000009", session_lang));
				convertMap.put("#MAIL_TXT_4#", userInfo.getUser_id());
				convertMap.put("#MAIL_TXT_5#", href);
				// This is an automatically generated email, please do not reply to this message.
				convertMap.put("#MAIL_TXT_6#", LangMng.LANG_D("FR000304", session_lang));
				// Terms of use and Legal disclaimer, Privacy Statement
				convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000007", session_lang));
				// Copyright ⓒ KORLOY
				convertMap.put("#MAIL_TXT_8#", LangMng.LANG_D("FR000008", session_lang));
				// Verify my Email
				convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000303", session_lang));
				
				content = "";
				try {
					// readTmplConvertMail(메일템플릿파일경로, convertMap)
//					content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
					String destDir = session.getServletContext().getRealPath("/mail");
					content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_certify.html", convertMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				// 파일 첨부시
//				uploadPath.add("C:\\ryan.PNG");
//				uploadPath.add("C:\\qjffp.PNG");
			} else {
				// 메일발송
				href = userInfo.getConfirm_url() + userInfo.getEmail_cert_key() + "&user_id=" + userInfo.getUser_id();
				setfrom =  ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
				mail_from_name = "KORLOY";
				tomail = userInfo.getUser_id();
				// [KORLOY Digital Catalog] Verify your e-mail account to change password
				title = LangMng.LANG_D("FR000328", session_lang);
				// 메일본문 content 구성
				Map<String, String> convertMap = new HashMap<String, String>();
				// Please click below URL to verify your email account.
				convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000329", session_lang));
				// After verifying your email account, please reset new password on KORLOY Digital Catalog.
				convertMap.put("#MAIL_TXT_2#", LangMng.LANG_D("FR000330", session_lang));
				// Email address
				convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000009", session_lang));
				convertMap.put("#MAIL_TXT_4#", userInfo.getUser_id());
				convertMap.put("#MAIL_TXT_5#", href);
				// This is an automatically generated email, please do not reply to this message.
				convertMap.put("#MAIL_TXT_6#", LangMng.LANG_D("FR000304", session_lang));
				// Terms of use and Legal disclaimer, Privacy Statement
				convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000007", session_lang));
				// Copyright ⓒ KORLOY
				convertMap.put("#MAIL_TXT_8#", LangMng.LANG_D("FR000008", session_lang));
				// Verify my Email
				convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000303", session_lang));

				content = "";
				try {
					// readTmplConvertMail(메일템플릿파일경로, convertMap)
//					content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
					String destDir = session.getServletContext().getRealPath("/mail");
					content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_certify.html", convertMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				// 파일 첨부시
//			uploadPath.add("C:\\ryan.PNG");
//			uploadPath.add("C:\\qjffp.PNG");
			}
			result = userJoinSendMail(setfrom, mail_from_name, tomail, title, content, uploadPath, null);
		}
		logger.debug("selectUserEmailCertInfo--{}", ConstKey.END_LOG);
		return result;
	}
	
	/**
	 * 로그인 회원 비밀번호 변경 입력비밀번호 일치 확인
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public boolean selectChkUserPwd(UserInfo userInfo) throws UserSysException {
		logger.debug("selectChkUserPwd--{}", ConstKey.START_LOG);
		boolean result = false;
		
		int resultCnt = userDao.selectChkUserPwd(userInfo);
		
		if(resultCnt > 0) {
			result = true;
		}
		
		logger.debug("selectChkUserPwd--{}", ConstKey.END_LOG);
		return result;
	}


	/**
	 * 회원 이메일 인증 여부 값
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	public boolean selectUserAuthYN(UserInfo userInfo) throws UserSysException {
		logger.debug("selectUserAuthYN--{}", ConstKey.START_LOG);
		boolean result = false;
		
		UserInfo resultInfo = userDao.selectUserEmailCertInfo(userInfo);
		if((resultInfo.getEmail_cert_yn()).equals("Y")) {
			result = true;
		}
		
		logger.debug("selectUserAuthYN--{}", ConstKey.END_LOG);
		return result;
	};
	
	/**
	 * 'PRE' 회원 이메일 인증 전에 가입 재시도 시 name, pw 수정
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateUserJoinInfo(UserInfo userInfo) throws UserSysException {
		logger.debug("updateUserJoinInfo--{}", ConstKey.START_LOG);
		boolean result = true;
		
		int iResult = userDao.updateUserJoinInfo(userInfo);
		
		// 처리 된 결과가 없으면 false 반환
		if (iResult < 1) {
			result = false;
			return result;
		}
		logger.debug("updateUserJoinInfo--{}", ConstKey.END_LOG);
		return result;
	}

	/**
	 * 이메일 인증 처리(일치하는 user_id와 email_cert_key쌍 존재 여부를 체크함)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateUserEmailAuthCd(UserInfo userInfo) throws UserSysException {
		logger.debug("updateUserEmailAuthCd--{}", ConstKey.START_LOG);
		boolean result = true;
		

		int iResult = userDao.updateUserEmailAuthCd(userInfo);
		
		// 조회 된 결과가 없으면 인증실패
		if (iResult < 1) {
			result = false;
			return result;
		}
		logger.debug("updateUserEmailAuthCd--{}", ConstKey.END_LOG);
		return result;
	}
	
	/**
	 * 회원가입 인증 완료 (성공시 USE_YN = 'Y'_)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateUserJoinConf(UserInfo userInfo) throws UserSysException {
		logger.debug("updateUserJoinConf--{}", ConstKey.START_LOG);
		boolean result = true;
		
		int iResult = userDao.updateUserJoinConf(userInfo);
		
		// 조회 된 결과가 없으면 인증실패
		if (iResult < 1) {
			result = false;
			return result;
		}
		logger.debug("updateUserJoinConf--{}", ConstKey.END_LOG);
		return result;
	}

	/**
	 * email_cert_key update (reset password)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
    @Override
    @Transactional
	public boolean updateUserEmailCertKey(HttpServletRequest request, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("updateUserEmailCertKey--{}", ConstKey.START_LOG);
		//사용자 언어 취득
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		UserInfo userInfo = userDao.selectUserEmailCertInfo(paramUserInfo);
		boolean result = false;	// 메일발송 결과
		boolean uResult = false;	// email_cert_key 재발급 처리 결과
		
		// 이메일 confirm을 위해 cert_key 생성
		String cert_key = AES256Util.randomUuid();
		paramUserInfo.setEmail_cert_key(cert_key);
		
		uResult = updateUserJoinInfo(paramUserInfo);
		
		if (!uResult) {
			result = false;
		} else {
			// 메일발송
			String href = paramUserInfo.getConfirm_url() + cert_key + "&user_id=" + userInfo.getUser_id();
			String setfrom =  ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
			String mail_from_name = "KORLOY";
			String tomail = userInfo.getUser_id();
			// [KORLOY Digital Catalog] Verify your e-mail account to change password
			String title = LangMng.LANG_D("FR000328", session_lang);
			// 메일본문 content 구성
			Map<String, String> convertMap = new HashMap<String, String>();
			// Please click below URL to verify your email account.
			convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000329", session_lang));
			// After verifying your email account, please reset new password on KORLOY Digital Catalog.
			convertMap.put("#MAIL_TXT_2#", LangMng.LANG_D("FR000330", session_lang));
			// Email address
			convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000009", session_lang));
			convertMap.put("#MAIL_TXT_4#", userInfo.getUser_id());
			convertMap.put("#MAIL_TXT_5#", href);
			// This is an automatically generated email, please do not reply to this message.
			convertMap.put("#MAIL_TXT_6#", LangMng.LANG_D("FR000304", session_lang));
			// Terms of use and Legal disclaimer, Privacy Statement
			convertMap.put("#MAIL_TXT_7#", LangMng.LANG_D("FR000007", session_lang));
			// Copyright ⓒ KORLOY
			convertMap.put("#MAIL_TXT_8#", LangMng.LANG_D("FR000008", session_lang));
			// Verify my Email
			convertMap.put("#MAIL_TXT_9#", LangMng.LANG_D("FR000303", session_lang));

			String content = "";
			try {
				// readTmplConvertMail(메일템플릿파일경로, convertMap)
//				content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
				String destDir = session.getServletContext().getRealPath("/mail");
				content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_certify.html", convertMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
//-----------------------------------------------------
			List<String> uploadPath = new ArrayList();
//			uploadPath.add("C:\\ryan.PNG");
//			uploadPath.add("C:\\qjffp.PNG");
//-----------------------------------------------------
			result = userJoinSendMail(setfrom, mail_from_name, tomail, title, content, uploadPath, null);
		}
		logger.debug("updateUserEmailCertKey--{}", ConstKey.END_LOG);
		return result;
	}


	/**
     * 마이페이지 정보 수정
     * @param mypageInfo
     * @return
     * @throws UserSysException
     */
	@Override
	@Transactional
	public boolean updateUserInfo(UserInfo paramUserInfo) throws UserSysException {
		logger.debug("updateUserInfo--{}", ConstKey.START_LOG);
		boolean bResult = true;
		
		int iResult = userDao.updateUserInfo(paramUserInfo);

    	if(iResult < 1) {
    		bResult = false;
    	}

		logger.debug("updateUserInfo--{}", ConstKey.END_LOG);
    	return bResult;
	}
	/**
	 * 비밀번호 재설정 및 비밀번호 변경시 사용
	 * 비밀번호 변경
	 * @param mypageInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateUserPassword(UserInfo paramUserInfo) throws UserSysException {
		logger.debug("updateUserPassword--{}", ConstKey.START_LOG);
		boolean bResult = true;
		
		int iResult = userDao.updateUserPassword(paramUserInfo);
		
		if(iResult < 1) {
			bResult = false;
		}
		
		logger.debug("updateUserPassword--{}", ConstKey.END_LOG);
		return bResult;
	}
    /**
     * 회원탈퇴 처리 STEP1 ~ STEP5
     * @param paramUserInfo
     * @return
     * @throws UserSysException
     */
	@Override
	@Transactional
	public boolean deleteUserInfo(UserInfo paramUserInfo) throws UserSysException {
		logger.debug("deleteUserInfo--{}", ConstKey.START_LOG);
		boolean bResult = true;
		
		// 회원탈퇴처리 STEP1 : 어셈블리 상세 fk.assem_no 데이타 삭제
		int iResult = userDao.deleteUserInfoStepDeleteAssem_d(paramUserInfo);
		// 회원탈퇴처리 STEP2 : 어셈블리 마스터 fk.user_id 데이타 삭제
		iResult = userDao.deleteUserInfoStepDeleteAssem_m(paramUserInfo);
		// 회원탈퇴처리 STEP3 : 장바구니 견적서 fk.user_no 데이타 삭제
		iResult = userDao.deleteUserInfoStepDeleteBasket_o(paramUserInfo);
		// 회원탈퇴처리 STEP4 : 장바구니 상세 fk.cart_no 데이타 삭제
		iResult = userDao.deleteUserInfoStepDeleteBasket_d(paramUserInfo);
		// 회원탈퇴처리 STEP5 : 장바구니 마스터 fk.user_id 데이타 삭제
		iResult = userDao.deleteUserInfoStepDeleteBasket_m(paramUserInfo);
		// 회원탈퇴처리 STEP6 : user테이블 데이타 삭제
		iResult = userDao.deleteUserInfo(paramUserInfo);

    	if(iResult < 1) {
    		bResult = false;
    	}

		logger.debug("deleteUserInfo--{}", ConstKey.END_LOG);
    	return bResult;
	}

    /**
     * VIP 승급요청
     * @param paramUserInfo
     * @return
     * @throws UserSysException
     */
	@Override
	@Transactional
	public boolean reqUpgradeUserGrp(HttpServletRequest request, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("reqUpgradeUserGrp--{}", ConstKey.START_LOG);
		//사용자 언어 취득
		HttpSession session = request.getSession();
		String session_lang = ConfigMng.getLang(request);
		boolean result = true;
		User user = (User)session.getAttribute(ConstKey.USER_INFO);
//		String ug_emp_nation = user.getUser_nation();
		paramUserInfo.setUser_grp(IPropertyKey.UG0007);
//		int iResult = userDao.updateUserGrp(paramUserInfo);
		int iResult = userDao.updateUserInfo(paramUserInfo);

		if (iResult < 1) {
			result = false;
			return result;
		}
//		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		UserInfo userInfo = userDao.selectUserInfo(paramUserInfo);
		// 메일발송
		List<String> mail_cc = new ArrayList(); /* 메일 참조자 */
		UserInfo mailReceiver = userDao.selectUserMailReceiver(paramUserInfo.getUser_nation());	// 수신자
		if (!"".equals(mailReceiver.getEmail_cc1())) mail_cc.add(mailReceiver.getEmail_cc1());	// 참조자1
		if (!"".equals(mailReceiver.getEmail_cc2())) mail_cc.add(mailReceiver.getEmail_cc2());	// 참조자2

		String setfrom =  ConfigMng.getValue(IPropertyKey.SMTP_HOST_NAME);
		String mail_from_name = "KORLOY";
		String tomail = mailReceiver.getEmail_to(); 	// 국가별 담당자
//		String tomail = "esther.yoo329@gmail.com"; 	// 테스트
//		String tomail = "jiyoo@e-act.co.kr"; 	// 테스트
//		String tomail = "lemon.yu@hanmail.net"; 	// 테스트

		// [Digital Catalog] Request to upgrade Pro membership
		String title = LangMng.LANG_D("FR000305", session_lang);
		// 메일본문 content 구성
		Map<String, String> convertMap = new HashMap<String, String>();
		// Received request from
		convertMap.put("#MAIL_TXT_1#", LangMng.LANG_D("FR000306", session_lang));
		convertMap.put("#MAIL_TXT_2#", paramUserInfo.getUser_id());
		// for upgrading Pro membership
		convertMap.put("#MAIL_TXT_3#", LangMng.LANG_D("FR000307", session_lang));
		// Email address
		convertMap.put("#MAIL_TXT_4#", LangMng.LANG_D("FR000009", session_lang));
		convertMap.put("#MAIL_TXT_5#", paramUserInfo.getUser_id());
		// User Name
		convertMap.put("#MAIL_TXT_6#", LangMng.LANG_D("FR000017", session_lang));
		convertMap.put("#MAIL_TXT_7#", userInfo.getUser_name());
		// Company Name
		convertMap.put("#MAIL_TXT_8#", LangMng.LANG_D("FR000099", session_lang));
		convertMap.put("#MAIL_TXT_9#", paramUserInfo.getUser_company());
		// Company Email Address
		convertMap.put("#MAIL_TXT_10#", LangMng.LANG_D("FR000100", session_lang));
		convertMap.put("#MAIL_TXT_11#", paramUserInfo.getUser_com_mail());
		
		// (추가됨 21.11.15)
		// Company Phone
		convertMap.put("#MAIL_TXT_16#", LangMng.LANG_D("FR000241", session_lang));
		convertMap.put("#MAIL_TXT_17#", paramUserInfo.getUser_com_tel());
		// Company Address
		convertMap.put("#MAIL_TXT_18#", LangMng.LANG_D("FR000242", session_lang));
		convertMap.put("#MAIL_TXT_19#", paramUserInfo.getUser_com_addr());
		//------------------------------
		
		// Phone
		convertMap.put("#MAIL_TXT_12#", LangMng.LANG_D("FR000194", session_lang));
		convertMap.put("#MAIL_TXT_13#", userInfo.getUser_mobile());
		
		// (추가됨 21.11.15)
		// Location(Country)
		convertMap.put("#MAIL_TXT_20#", LangMng.LANG_D("FR000019", session_lang));
		convertMap.put("#MAIL_TXT_21#", paramUserInfo.getNation_txt());
		// Language
		convertMap.put("#MAIL_TXT_22#", LangMng.LANG_D("FR000195", session_lang));
		convertMap.put("#MAIL_TXT_23#", paramUserInfo.getLang_txt());
		// Unit
		convertMap.put("#MAIL_TXT_24#", LangMng.LANG_D("FR000058", session_lang));
		convertMap.put("#MAIL_TXT_25#", paramUserInfo.getUnit_txt());
		// Currency
		convertMap.put("#MAIL_TXT_26#", LangMng.LANG_D("FR000045", session_lang));
		convertMap.put("#MAIL_TXT_27#", paramUserInfo.getCurr_txt());
		//------------------------------
		
		// This is an automatically generated email, please do not reply to this message.
		convertMap.put("#MAIL_TXT_14#", LangMng.LANG_D("FR000304", session_lang));
		// Copyright ⓒ KORLOY
		convertMap.put("#MAIL_TXT_15#", LangMng.LANG_D("FR000008", session_lang));
//		String destDir2 = session.getServletContext().getRealPath("/img");
		Map<String, String> imgMap = new HashMap<String, String>();
//		imgMap.put("image1", destDir2 + System.getProperty("file.separator") + "btn_verify.png");
		
		String content = "";
		try {
			// readTmplConvertMail(메일템플릿파일경로, convertMap)
//			content = SendEmail.readTmplConvertMail("mail/mail_member_certify.html", convertMap);
			String destDir = session.getServletContext().getRealPath("/mail");
			content = SendEmail.readTmplConvertMail(destDir + System.getProperty("file.separator") + "mail_member_vip.html", convertMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// request 객체에서 첨부파일 정보 사용하여 파일경로들의 ArrayList 
//-----------------------------------------------------
		List<String> uploadPath = new ArrayList();
//		uploadPath.add("C:\\ryan.PNG");
//		uploadPath.add("C:\\qjffp.PNG");
//-----------------------------------------------------
		result = SendOrderRequestMail2(setfrom, mail_from_name, tomail, title, content, uploadPath, mail_cc);
		
		logger.debug("reqUpgradeUserGrp--{}", ConstKey.END_LOG);
		return result;
	}


	/**
	 * 회원가입 이메일 인증 메일 발송
	 * @param paramInfo
	 * @param uploadFilePath
	 * @return
	 * @throws UserSysException
	 */
	public boolean userJoinSendMail(String setfrom, String mail_from_name, String tomail, String title, String content, List<String> uploadPath, Map<String, String>imgMap) throws UserSysException {
		boolean prcsResult = false; 
		String result = "";
		try {
			result = SendEmail.sendEmail(setfrom, mail_from_name, tomail, title, content, uploadPath, null, imgMap);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		if ("SUC".equals(result)) {
			prcsResult = true;
		}
		return prcsResult;
	}

	/**
	 * 주문서 메일 발송
	 * @param paramInfo
	 * @param uploadFilePath
	 * @return
	 * @throws UserSysException
	 */
	public boolean SendOrderRequestMail2(String setfrom, String mail_from_name, String tomail, String title, String content, List<String> uploadPath, List<String> mail_cc) throws UserSysException {
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

	/**
	 * 국가 정보 코드인포 리스트
	 * @return
	 */
	@Override
	public List<CodeInfo> selectUserNationCodeList() throws UserSysException {
		return userDao.selectUserNationCodeList();
	}

	/**
	 * 단위 정보 코드인포 리스트
	 * @return
	 */
	@Override
	public List<CodeInfo> selectUserUnitCodeList() throws UserSysException {
		return userDao.selectUserUnitCodeList();
	}

	/**
	 * 통화 정보 코드인포 리스트
	 * @return
	 */
	@Override
	public List<CodeInfo> selectUserCurrencyCodeList() throws UserSysException {
		return userDao.selectUserCurrencyCodeList();
	}

	/**
	 * 언어 정보 코드인포 리스트
	 */
	@Override
	public List<CodeInfo> selectUserLangCodeList() throws UserSysException {
		return userDao.selectUserLangCodeList();
	}

	/**
	 * 국가코드와 담당자이메일
	 * @return
	 */
	@Override
	public List<CodeInfo> selectEmpbyNationCdList() throws UserSysException {
		return userDao.selectEmpbyNationCdList();
	}
	
}
