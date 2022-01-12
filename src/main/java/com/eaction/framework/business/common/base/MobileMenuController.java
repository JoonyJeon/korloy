/*****************************************************************************
 * 프로그램명  : TopMenuController.java
 * 설     명  : Top 메뉴 제어 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
//import com.eaction.framework.business.logic.main.model.MainInfo;
//import com.eaction.framework.business.logic.main.model.MainSearchInfo;
//import com.eaction.framework.business.logic.main.service.MainServiceImpl;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.util.StringUtil;

/**
 * Top 메뉴 제어 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class MobileMenuController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(MobileMenuController.class);    
//	@Autowired
//	MainServiceImpl mainService;
	
	/**
	 * doProcess 메소드
	 *
	 * @param   HttpServletRequest 요청	
	 * @param   HttpServletResponse	 응답
	 * @return  ModelAndView 뷰표시처리객체
	 */
	@RequestMapping(value = ConstUrl.MOBILE_TOP_URL)
 	public ModelAndView getMobileTopPage( HttpServletRequest   request, HttpServletResponse  response ) throws UserSysException {        		
 		ModelAndView modelAndView = new ModelAndView();
// 		MainSearchInfo searchInfo = new MainSearchInfo();
 		
 		
// 		MainInfo info = mainService.selectAlertCnt(searchInfo);
//		modelAndView.addObject(ConstKey.RESULT_DATA, info);
 		
		modelAndView.setViewName(ConstUrl.MOBILE_TOP_JSP_URL);
            
 		return modelAndView;
 	}
	
	@RequestMapping(value = ConstUrl.MOBILE_TOP_URL, params=ConstKey.ACTION_ID + "=" + ConstKey.ACTION_RESET_SESSION)
	@ResponseBody
 	public JSONObject setSession( HttpServletRequest   request, HttpServletResponse  response ) {     
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(12000);
		logger.info("Session 유효시간 재할당시간 : " + new Date(session.getLastAccessedTime()).toString());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("retCode", "00");
		jsonObject.put("retMsg", session.getMaxInactiveInterval());
		
		return jsonObject;
 	}
	
	/**
	 * doProcess 메소드
	 *
	 * @param   HttpServletRequest 요청	
	 * @param   HttpServletResponse	 응답
	 * @return  ModelAndView 뷰표시처리객체
	 * @throws UserSysException 
	 */
	@RequestMapping(value = ConstUrl.MOBILE_LEFT_URL)
 	public ModelAndView getMobileLeftPage( HttpServletRequest   request, HttpServletResponse  response ) throws UserSysException {
 		ModelAndView modelAndView = new ModelAndView();
 		
 		String root = request.getParameter("root");
 		request.setAttribute("ROOT_MENU", root);
 		//CDR 조회 통합은 뒤에 파라미터가 붙어서 오므로 같이 확인해야 해서 단순히 request.getServletPath() 하면 안된다. 다시한번 확인 
 		String menu_url = (String)request.getAttribute("MENU_URL");
 		
 		String menuUrl = StringUtil.nvl(request.getParameter("menuUrl"));
 		//새로고침시 menuUrl 잃어 버림.
 		if("".equals(menuUrl) && "".equals(menu_url)){
			menuUrl = request.getServletPath();
 		}else if("".equals(menuUrl) && !"".equals(menu_url)){
 			menuUrl = menu_url;
		}
 		request.setAttribute("MENU_URL", menuUrl);
 		
// 		MainSearchInfo searchInfo = new MainSearchInfo();
// 		MainInfo info = mainService.selectAlertCnt(searchInfo);
//		modelAndView.addObject(ConstKey.RESULT_DATA, info);
 		
		modelAndView.setViewName(ConstUrl.MOBILE_LEFT_JSP_URL);
 		return modelAndView;
 	}
	
	/**
     * 풋터 표시처리 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MOBILE_FOOTER_URL)
	public ModelAndView getMobileFootPage(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject(ConstKey.RESULT_DATA);
        modelAndView.setViewName(ConstUrl.MOBILE_FOOTER_JSP_URL);
		
		return modelAndView;
	}
}
