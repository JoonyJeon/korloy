/*****************************************************************************
 * 프로그램명  : LoginMultiController.java
 * 설     명  : 로그인 처리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 ******************************************************************************/
package com.eaction.framework.business.logic.login.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.login.constant.LoginConstKey;
import com.eaction.framework.business.logic.login.constant.LoginConstUrl;
import com.eaction.framework.business.logic.login.service.LoginService;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.CookieMng;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 로그인 처리 컨트롤러
 * @author  eaction
 * @version 1.0
 */
@Controller
public class LoginUrlController {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger(LoginUrlController.class);
	
	@Autowired
	private LoginService loginService;	

//	@Autowired
//	private MessageSourceAccessor messageSourceAccessor;
		
	/**
	 * 로그인 화면 이동 처리
	 * @param request 요청객체
	 * @param response 응답객체
	 * @param paramUserInfo 사용자정보
	 * @return ModelAndView 화면이동객체
	 */
	@RequestMapping(value = ConstUrl.LOGIN_URL)
	public ModelAndView goLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User paramUserInfo ) {
		
		logger.debug("goLogin--{}", ConstKey.START_LOG);
		
		ModelAndView modelAndView = new ModelAndView();
		
        String goController 	= "";		
 		 		 		 		        
 		String result_login = StringUtil.nvl(request.getParameter("LOGIN_RESULT"));
 		 		 		
        //로그인화면 표시  		
		goController = goLogin(request);
		if(!"".equals(result_login) && result_login.equals(LoginConstKey.LOGIN_NEED)){
			return new ModelAndView(goController,LoginConstKey.LOGIN_RESULT , LoginConstKey.LOGIN_NEED);
		}  			
		List<String> ipList = loginService.selectUserIp();
		modelAndView.addObject("mgmt_ip", ipList);
        modelAndView.addObject("user", paramUserInfo);
        modelAndView.setViewName(goController);
        
        logger.debug("goLogin--{}", ConstKey.END_LOG);
        
		return modelAndView;
	}
	
	@RequestMapping(value = ConstUrl.LOGIN_URL, params = ConstKey.ACTION_ID + "=" + LoginConstKey.LOGOUT_OK)
	@ResponseBody
	public String goLogout(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User paramUserInfo ) {
		
		logger.debug("goLogout--{}", ConstKey.START_LOG);
		JSONObject jsonObject = new JSONObject();

		try{
//		ModelAndView modelAndView = new ModelAndView();
//
//		StringBuffer sbUrlBuffer = new StringBuffer("");		
//        String goController 	= "";
		HttpSession session = request.getSession(true);
		     
		//로그아웃시 로그등록
    	User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		
//    	if("YES".equals(ConfigMng.getValue(IPropertyKey.SET_CONNLOG))){
//			try {
//				loginService.setConnLog(userInfo,"LOGOUT");
//			} catch (UserSysException e) {
//				// TODO Auto-generated catch block
//				logger.error("Connect error log error!!");
//			} catch (BizException e) {
//				request.setAttribute("ERROR", "error : " + e);
//
//  			}
//		}
    			
		//유저정보 삭제
		session.removeAttribute(ConstKey.USER_INFO);
		//세션무효화
		session.invalidate();

		//로그인 화면으로 이동
//		sbUrlBuffer.append("redirect:");
//		sbUrlBuffer.append(ConstUrl.LOGIN_URL);
//		goController = sbUrlBuffer.toString();
		//goController = goLogin(request);

//  		
//        modelAndView.addObject("user", paramUserInfo);
//        modelAndView.setViewName(goController);
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "Success");
		
		} catch(Exception e) {
			jsonObject.put("resCode", "1");
			// error occured while LOGOUT. please try again.
			jsonObject.put("resMsg", "Fail");
		} 
        logger.debug("goLogout--{}", ConstKey.END_LOG);
        
		return jsonObject.toString();
	}
	
	@RequestMapping(value = ConstUrl.LOGIN_URL, params = ConstKey.ACTION_ID + "=" + LoginConstKey.TIMEOUT_LOGOUT_OK)
	public ModelAndView goTimeoutLogout(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User paramUserInfo ) {
		
		logger.debug("goTimeoutLogout--{}", ConstKey.START_LOG);
		
		ModelAndView modelAndView = new ModelAndView();

		String goController 	= "";
        HttpSession session = request.getSession(true);
        
		String user_id = StringUtil.nvl(request.getParameter("user_id"));
		User user = new User();
		user.setUser_id(user_id);
		
		//세션무효화
		session.invalidate();
		//로그인 화면으로 이동
		goController = goLogin(request);
  	
        modelAndView.addObject("user", paramUserInfo);
        modelAndView.setViewName(goController);
        
        logger.debug("goTimeoutLogout--{}", ConstKey.END_LOG);
        
		return modelAndView;
	}
	
    /**
     * 로그인화면 이동을 위한 쿠키설정 및 화면이동 URL취득처리
     * @param request 요청
     * @return String 이동URL
     */
    private String goLogin(HttpServletRequest request){
    	CookieMng cookieMng = new CookieMng(request);
		String cookieSave = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_SAVE));
		String cookieId = "";
		String cookiePasswd = "";
		if (ConstKey.KEY_CHECKED.equals(cookieSave)) {
			cookieId = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_ID));
			cookiePasswd = StringUtil.nvl(cookieMng.getValue(ConstKey.COOKIE_PASSWD));
		}

		request.setAttribute(ConstKey.COOKIE_SAVE, cookieSave);
		request.setAttribute(ConstKey.COOKIE_ID, cookieId);
		request.setAttribute(ConstKey.COOKIE_PASSWD, cookiePasswd);

		//로그인 화면으로 이동
		return LoginConstUrl.JSP_LOGIN_URL;
    }    
}
