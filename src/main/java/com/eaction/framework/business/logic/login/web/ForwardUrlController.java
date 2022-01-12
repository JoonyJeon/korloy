/*****************************************************************************
 * 프로그램명  : ForwardMultiController.java
 * 설     명  : 로그인화면으로 포워드 처리 컨트롤러
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 ******************************************************************************/
package com.eaction.framework.business.logic.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.login.constant.LoginConstUrl;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 포워드 처리 컨트롤러
 * @author  eaction 
 * @version 1.0
 */
@Controller
public class ForwardUrlController {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger(ForwardUrlController.class);
	
	/** 에러발생시 이동할 페이지 URL */
	protected String errCallBackPage = ConstUrl.JSP_ERROR_URL;

	@RequestMapping(value = ConstUrl.FORWARD_URL)
 	public ModelAndView doProcess( HttpServletRequest   request 
 								 , HttpServletResponse  response ) throws Exception {
		
		logger.debug("doProcess--{}", ConstKey.START_LOG);
		
 		ModelAndView modelAndView = null;
		
		try {
			modelAndView = new ModelAndView(LoginConstUrl.JSP_FORWARD_URL);
            
		
		} catch (Exception ex) {
			logger.error(ex.getMessage() + " : " + ex);
			
			// 시스템에러 발생상태
			request.setAttribute(ConstKey.RESULT_KEY, ConstKey.RESULT_FAIL);
			// 시스템에러
			request.setAttribute(ConstKey.EX_ERR_OBJ, new UserSysException (this.getClass().getName(), "doProcess",  ex));
			
			return new ModelAndView(errCallBackPage);			
			
		}
		
		logger.debug("doProcess--{}", ConstKey.END_LOG);
        
 		return modelAndView;
 	}
 	
}
