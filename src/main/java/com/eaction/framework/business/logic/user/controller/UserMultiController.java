/*****************************************************************************
 * 프로그램명  : UserMultiController.java
 * 설     명  : 회원 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.06  YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.user.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.login.service.LoginService;
import com.eaction.framework.business.logic.user.constant.UserConstKey;
import com.eaction.framework.business.logic.user.constant.UserConstUrl;
import com.eaction.framework.business.logic.user.model.UserInfo;
import com.eaction.framework.business.logic.user.service.UserService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 회원 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class UserMultiController {

	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( UserMultiController.class );

	@Resource
	private UserService userService;	
	@Autowired
	private LoginService loginService;	
	@Autowired
	private BasicService basicService;	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	

	/**
	 * 회원정보 등록 처리
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_USER_JOIN_OK)
	@ResponseBody
	public String insertUser(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("insertUser --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = true;
//		String eResult = "";	// 메일 발송 성공 값
		basicService.doLog(request, "EC0006", "", "", paramUserInfo.getUser_id());
		// boolean [true:ID(이메일)사용가능, false:ID(이메일)중복]
		String checkId = userService.selectUserEmailDuplChk(paramUserInfo);
		
		if((checkId).equals("DUPL")) { // 활성화 되어있는 회원이 있음, 중복
			jsonObject.put("resCode", "2");
			// 이미 존재하는 이메일입니다.
			jsonObject.put("resMsg", "Fail");
			return jsonObject.toString();
		} else {
			if((checkId).equals("PRE")) {	// DB에는 있으나 이메일 인증 완료하지 않아 회원이 아님
				result = userService.selectUserEmailCertInfo(request, paramUserInfo);
			} else {	// 'NEW' : DB에 없는 신규
				try{
					result = userService.insertUser(request, paramUserInfo);
				} catch(Exception e) {
					result = false;
					jsonObject.put("resCode", "1");
					// 이메일 발송 중 오류가 발생했습니다.
					jsonObject.put("resMsg", "Fail");
				} 
			}
			
		}
		// 성공:0
		if(result){
			jsonObject.put("resCode", "0");
//			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.INSERT.SUCCESS"));
			// 이메일 인증 메일을 발송하였습니다.
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 이메일 발송 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("insertUser --{}", ConstKey.END_LOG);
		
		return jsonObject.toString();
	}

	/**
	 * 회원가입 이메일 인증 결과 화면
	 * @param request
	 * @param response
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_EMAIL_AUTH)
	@ResponseBody
	public ModelAndView userEmailAuth(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) throws UserSysException {
		logger.debug("userEmailAuth --{}", ConstKey.START_LOG);
		ModelAndView modelAndView = new ModelAndView(UserConstUrl.JSP_USER_JOIN_AUTH);

		boolean result = userService.updateUserEmailAuthCd(userInfo);
		
		if(result) {
			result = userService.selectUserAuthYN(userInfo);
		}

		modelAndView.addObject("result", result);
		modelAndView.addObject("user", userInfo);
		logger.debug("userEmailAuth --{}", ConstKey.END_LOG);
		return modelAndView;
	}
	
	/**
	 * 회원가입 이메일 인증 완료 (사용자 메일로 cert_key, id를 통해 부여받은 url 접속시)
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_EMAIL_AUTH, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_USER_MAIL_AUTH_OK)
	@ResponseBody
	public String userEmailAuthCmpl(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("userEmailAuthCmpl --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = true;
		
		try{
			result = userService.updateUserJoinConf(paramUserInfo);
		} catch(Exception e) {
			result = false;
			jsonObject.put("resCode", "1");
			// 이메일 인증 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		} 
		
		// 성공:0
		if(result){
			boolean authResult = userService.selectUserAuthYN(paramUserInfo);
			if(authResult) {
				jsonObject.put("resCode", "0");
				// 인증 완료되었습니다.
				jsonObject.put("resMsg", "Success");
			} else {
				jsonObject.put("resCode", "2");
				// 이메일 인증을 완료하여 주세요.
				jsonObject.put("resMsg", "Fail");
			}
		} else {
			jsonObject.put("resCode", "1");
			// 이메일 인증 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("userEmailAuthCmpl --{}", ConstKey.END_LOG);
		
		return jsonObject.toString();
	}
	
	/**
	 * 회원가입 이메일 인증 메일 재발송
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_EMAIL_AUTH, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_USER_RESEND_AUTH_MAIL_OK)
	@ResponseBody
	public String userResendAuthMail(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("userEmailAuthCmpl --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = true;
		
		result = userService.selectUserEmailCertInfo(request, paramUserInfo);
		
		// 성공:0
		if(result){
			jsonObject.put("resCode", "0");
			// 이메일 인증 메일을 발송하였습니다.
			jsonObject.put("resMsg", "Fail");
		} else {
			jsonObject.put("resCode", "1");
			// 이메일 발송 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("userEmailAuthCmpl --{}", ConstKey.END_LOG);
		
		return jsonObject.toString();
	}
	

	/**
	 * Mypage 정보 수정
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_EDIT_USER_INFO_OK)
	@ResponseBody
	public String doUpdateUserInfo(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("doUpdateUserInfo --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = true;
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		
		result = userService.updateUserInfo(paramUserInfo);
		
		// 성공:0
		if(result){
			User user = new User();
			if ((paramUserInfo.getUse_yn()).equals("Y")) {
				user.setUser_id(paramUserInfo.getUser_id());
				user = loginService.selectUserInfo(user);
				//유저정보 세션에 설정
				session.setAttribute(ConstKey.USER_INFO, user);
				session.setAttribute(ConstKey.SESSION_NATION, user.getUser_nation());
				session.setAttribute(ConstKey.SESSION_LANG, user.getUser_lang());
				session.setAttribute(ConstKey.SESSION_SIZE_UNIT, user.getUnit_cd());
				session.setAttribute(ConstKey.SESSION_CURRENCY, user.getCurr_cd());
				
			}
			jsonObject.put("resCode", "0");
			if((paramUserInfo.getUser_pwd()).equals("")) {

				jsonObject.put(ConstKey.SESSION_NATION, user.getUser_nation());
				jsonObject.put(ConstKey.SESSION_LANG, user.getUser_lang());
				jsonObject.put(ConstKey.SESSION_SIZE_UNIT, user.getUnit_cd());
				jsonObject.put(ConstKey.SESSION_CURRENCY, user.getCurr_cd());
				
				// 회원 정보를 수정했습니다. 114
				jsonObject.put("resMsg", LangMng.LANG_D("FR000114", session_lang));
				jsonObject.put("data", user);
				jsonObject.put("resData", user);
			} else {
				// 비밀번호를 변경하였습니다. 193
				jsonObject.put("resMsg", LangMng.LANG_D("FR000193", session_lang));
			}
		} else {
			jsonObject.put("resCode", "1");
			// 요청하신 처리가 실패했습니다
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("doUpdateUserInfo --{}", ConstKey.END_LOG);
		
		return jsonObject.toString();
	}
	
	/**
	 * 회원 탈퇴 처리
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_DELETE_USER_INFO_OK)
	@ResponseBody
	public String doDeleteUserInfo(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("doDeleteUserInfo --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession(true);
		boolean result = true;
		
		result = userService.deleteUserInfo(paramUserInfo);
		
		// 성공:0
		if(result){
			//유저정보 삭제
			session.removeAttribute(ConstKey.USER_INFO);
			//세션무효화
			session.invalidate();
			jsonObject.put("resCode", "0");
			// 탈퇴 완료했습니다. 116
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 요청하신 처리가 실패했습니다
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("doDeleteUserInfo --{}", ConstKey.END_LOG);
		
		return jsonObject.toString();
	}
	
	/**
	 * 비밀번호 변경 이메일 인증 메일 발송
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_USER_RESET_PWD_MAIL_SEND_OK)
	@ResponseBody
	public String deResetUserPassword(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("deResetUserPassword --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = true;
		
		result = userService.updateUserEmailCertKey(request, paramUserInfo);
		
		// 성공:0
		if(result){
			jsonObject.put("resCode", "0");
			// 이메일 인증 메일을 발송하였습니다.
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 이메일 발송 중 오류가 발생했습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("deResetUserPassword --{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}

	/**
	 * 비밀번호 찾기 (회원 등록 여부 확인)
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_SELECT_USER_BY_EMAIL)
	@ResponseBody
	public String sendEmailAuthUpdPassword(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("sendEmailAuthUpdPassword --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = false;
		
		UserInfo resultInfo = userService.selectUserInfo(paramUserInfo);
		if(resultInfo == null ||
				(resultInfo.getUse_yn()).equals("N")) {
			jsonObject.put("resCode", "2");
			// 존재하지 않는 이메일 입니다.
			jsonObject.put("resMsg", "Fail");
		} else {
			result = userService.updateUserEmailCertKey(request, paramUserInfo);
			
			if (result) {
				jsonObject.put("resCode", "0");
				// 이메일 인증 메일을 발송하였습니다.
				jsonObject.put("resMsg", "Success");
			} else {
				jsonObject.put("resCode", "1");
				// 이메일 발송 중 오류가 발생했습니다.
				jsonObject.put("resMsg", "Fail");
			}
		}
		
		logger.debug("sendEmailAuthUpdPassword --{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}

	/**
	 * VIP 승급요청
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_USER_REQ_UPGRADE)
	@ResponseBody
	public String userReqUpgrade(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("userReqUpgrade --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = false;
		HttpSession session = request.getSession(true);
		basicService.doLog(request, "EC0005", "", "");
		result = userService.reqUpgradeUserGrp(request, paramUserInfo);
		
		
		// 회사명,회사이메일 기존값과 비교하여 다른값일 경우 세션 새로등록
		User user = (User)session.getAttribute(ConstKey.USER_INFO);

		// 성공:0
		if (result) {
			user = new User();
			user.setUser_id(paramUserInfo.getUser_id());
			user = loginService.selectUserInfo(user);
			session.setAttribute(ConstKey.USER_INFO, user);

			session.setAttribute(ConstKey.SESSION_NATION, user.getUser_nation());
			session.setAttribute(ConstKey.SESSION_LANG, user.getUser_lang());
			session.setAttribute(ConstKey.SESSION_SIZE_UNIT, user.getUnit_cd());
			session.setAttribute(ConstKey.SESSION_CURRENCY, user.getCurr_cd());
			
			jsonObject.put("resCode", "0");
			// 담당자에게 VIP 승급요청 메일을 발송했습니다.
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 요청하신 처리가 실패했습니다
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("userReqUpgrade --{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}
	
	/**
	 * 로그인 회원 비밀번호 변경 
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_EDIT_USER_PASSWORD_OK)
	@ResponseBody
	public String doUpdateLoginUserPassword(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("doUpdateLoginUserPassword --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = false;
		HttpSession session = request.getSession(true);
		// old password 검증
		result = userService.selectChkUserPwd(paramUserInfo);
		
		// 성공:0
		if (result) {
			// 비밀번호 검증 통과시 비밀번호 update
			result = userService.updateUserPassword(paramUserInfo);
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "2");
			// 비밀번호가 일치하지 않습니다.
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("doUpdateLoginUserPassword --{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}
	
	
	/**
	 * 비밀번호 재설정 - 로그인 전 forgot Password
	 * @param request
	 * @param response
	 * @param paramUserInfo
	 * @return
	 * @throws UserSysException
	 */
	@RequestMapping(value=UserConstUrl.USER_URL, params=ConstKey.ACTION_ID + "=" + UserConstKey.ACTION_RESET_PASSWORD_OK)
	@ResponseBody
	public String doResetPasswordBeforeLogin(HttpServletRequest request, HttpServletResponse response, UserInfo paramUserInfo) throws UserSysException {
		logger.debug("doResetPasswordBeforeLogin --{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();
		boolean result = false;
		HttpSession session = request.getSession(true);
		
		result = userService.updateUserPassword(paramUserInfo);
		if (result) {
			jsonObject.put("resCode", "0");
			// 비밀번호를 변경하였습니다
			jsonObject.put("resMsg", "Success");
		} else {
			jsonObject.put("resCode", "1");
			// 요청하신 처리가 실패했습니다
			jsonObject.put("resMsg", "Fail");
		}
		
		logger.debug("doResetPasswordBeforeLogin --{}", ConstKey.END_LOG);
		return jsonObject.toString();
	}
	
	
}
