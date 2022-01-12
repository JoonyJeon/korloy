/*****************************************************************************
 * 프로그램명  : LoginMultiController.java
 * 설     명  : 로그인 처리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 ******************************************************************************/
package com.eaction.framework.business.logic.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
import com.eaction.framework.business.logic.login.constant.LoginConstKey;
import com.eaction.framework.business.logic.login.service.LoginService;
import com.eaction.framework.business.logic.user.model.UserInfo;
import com.eaction.framework.business.logic.user.service.UserService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.AuthInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.CookieMng;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 로그인 처리 컨트롤러
 * @author  eaction
 * @version 1.0
 */
@Controller
public class LoginMultiController {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( LoginMultiController.class );

	@Resource
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private BasicService basicService;
	@Resource
	private BasketService basketService;
	@Resource
	private AssemblyService assemblyService;
//	@Autowired
	private MessageSourceAccessor messageSourceAccessor;
			
	@RequestMapping(value = ConstUrl.LOGIN_URL, params = ConstKey.ACTION_ID + "=" + LoginConstKey.LOGIN_OK)
	@ResponseBody
	public JSONObject doLogin(HttpServletRequest request, HttpServletResponse response, User paramUserInfo ) throws UserSysException {
		logger.debug("doLogin--{}", ConstKey.START_LOG);

		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession(true);
			
		//유저정보빈을 취득
		User user = null;
		// ID 존재 확인
		UserInfo userInfo = new UserInfo(); 
		userInfo.setUser_id(paramUserInfo.getUser_id());
		userInfo = userService.selectUserInfo(userInfo);
		
		// 입력 email로 조회 된 회원 없음
		if (userInfo == null) {
			jsonObject.put("resCode", "2");
			// 존재하지 않는 ID입니다.
			jsonObject.put("resMsg", "Success");
		} else {
			// userInfo null 아닐 시  회원 확인
			if ((userInfo.getUse_yn()).equals("N")) {
				jsonObject.put("resCode", "3");
				// 이메일 인증을 진행하여 주세요.
				jsonObject.put("resMsg", "Success");
			} else {
				try {
					user = loginService.selectUserInfo(paramUserInfo);
				} catch (Exception e) {
					request.setAttribute("ERROR", "error : " + e);
					logger.error(e.getMessage());		
				} 
				//로그인에 성공한 경우
				if ((user != null) && !"".equals(user.getUser_id())) {
					//쿠키설정
					setLoginCookie(request, response, user);
					//유저정보 세션에 설정
					session.setAttribute(ConstKey.USER_INFO, user);
					session.setAttribute(ConstKey.SESSION_NATION, user.getUser_nation());
					session.setAttribute(ConstKey.SESSION_LANG, user.getUser_lang());
					session.setAttribute(ConstKey.SESSION_SIZE_UNIT, user.getUnit_cd());
					session.setAttribute(ConstKey.SESSION_CURRENCY, user.getCurr_cd());
					jsonObject = AjaxJsonUtil.getReturnJson("0", "Success", user);
			 		AuthInfo authInfo = basicService.selectUserAuthInfo(userInfo.getUser_grp());
			 		JSONObject json_auth = JSONObject.fromObject(authInfo);
			 		jsonObject.put("json_auth", json_auth);
			 		jsonObject.put(ConstKey.SESSION_NATION, user.getUser_nation());
					jsonObject.put(ConstKey.SESSION_NATION, user.getUser_nation());
					jsonObject.put(ConstKey.SESSION_LANG, user.getUser_lang());
					jsonObject.put(ConstKey.SESSION_SIZE_UNIT, user.getUnit_cd());
					jsonObject.put(ConstKey.SESSION_CURRENCY, user.getCurr_cd());
		 			BasketInfo basketInfo = new BasketInfo();
		 			basketInfo.setUser_id(userInfo.getUser_id());
		 			int cartCnt = basketService.selectBasketListCnt(basketInfo);
		 			AssemblyInfo assemInfo = new AssemblyInfo();
		 			assemInfo.setLogin_user_id(userInfo.getUser_id());
		 			int assemCnt = assemblyService.selectAssemblyListCnt(assemInfo);
		 			
		 			jsonObject.put("assemCnt", assemCnt);
		 			jsonObject.put("cartCnt", cartCnt);
		 			basicService.doLog(request, "EC0001", "", "");
				//로그인 이 안된경우
				} else {
					jsonObject.put("resCode", "1");
					// 비밀번호를 확인하여 주세요.
					jsonObject.put("resMsg", "Success");
				}
			}
		}

		logger.debug("doLogin--{}", ConstKey.END_LOG);
		return jsonObject;
	}	
	
	
    /**
     * 로그아웃후화면 이동을 위한 쿠키설정 및 화면이동 URL취득처리
     * @param request 요청
     * @param response 응답
     * @param paramUserInfo 사용자정보
     */
    private void setLoginCookie(HttpServletRequest request, HttpServletResponse response, User paramUserInfo){  
    	String cookieSave = "";
    	String cookieId = "";
		String cookiePasswd = "";
       	//쿠키설정
		String save = StringUtil.nvl(request.getParameter("save"));
		CookieMng cookieMng = new CookieMng();
		if (ConstKey.KEY_YES.equals(save)) {
			int time = ConfigMng.getValueInt(IPropertyKey.COOKIE_MAX_TIME);
			cookieMng.addCookie(ConstKey.COOKIE_SAVE, ConstKey.KEY_CHECKED, time);
			cookieMng.addCookie(ConstKey.COOKIE_ID, paramUserInfo.getUser_id(), time);
			cookieMng.addCookie(ConstKey.COOKIE_PASSWD, paramUserInfo.getPassword(), time);
			cookieMng.setCookieList(response);
			
			cookieSave = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_SAVE));
			cookieId = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_ID));
			cookiePasswd = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_PASSWD));
		} else {
			cookieMng.addCookie(ConstKey.COOKIE_SAVE, "", 0);
			cookieMng.addCookie(ConstKey.COOKIE_ID, "", 0);
			cookieMng.addCookie(ConstKey.COOKIE_PASSWD, "", 0);
			cookieMng.setCookieList(response);
		}

		request.setAttribute(ConstKey.COOKIE_SAVE, cookieSave);
		request.setAttribute(ConstKey.COOKIE_ID, cookieId);
		request.setAttribute(ConstKey.COOKIE_PASSWD, cookiePasswd);

    }       
    
    /**
	 * 로그인실패 카운트 수정 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.LOGIN_URL, params = ConstKey.ACTION_ID + "=" + LoginConstKey.LOGIN_FAIL_COUNT)
	@ResponseBody
    public boolean doUpdateLoginFailCnt(@ModelAttribute("user") User paramUserInfo) throws UserSysException{
		
		//JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
    	String exceptionMsg = "";
		
		try{
			bResult = loginService.updateLoginFailCnt(paramUserInfo);
				
	   	}catch(Exception e){
	   		bResult = false;
	   		exceptionMsg = e.getMessage();
	   	}
		
		//insert 성공:0,  실패 1
//		if(bResult){
//			jsonObject.put("resCode", "0");
//			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.UPDATE.SUCCESS"));
//	   	} else {
//	   		jsonObject.put("resCode", "1");
//			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL")+"\n\r"+exceptionMsg);
//	   	}
//		

   		return bResult;
	}
	
	
	/**
	 * 로그인성공 카운트 수정 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.LOGIN_URL, params = ConstKey.ACTION_ID + "=" + LoginConstKey.LOGIN_SUCCESS_COUNT)
	@ResponseBody
    public boolean doUpdateLoginSuccessCnt(@ModelAttribute("user") User paramUserInfo) throws UserSysException{
		
		//JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
    	String exceptionMsg = "";
		
		try{
			bResult = loginService.updateLoginSuccessCnt(paramUserInfo);
				
	   	}catch(Exception e){
	   		bResult = false;
	   		exceptionMsg = e.getMessage();
	   	}
		
		//insert 성공:0,  실패 1
//		if(bResult){
//			jsonObject.put("resCode", "0");
//			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.UPDATE.SUCCESS"));
//	   	} else {
//	   		jsonObject.put("resCode", "1");
//			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL")+"\n\r"+exceptionMsg);
//	   	}

   		return bResult;
	}
	
	
}
