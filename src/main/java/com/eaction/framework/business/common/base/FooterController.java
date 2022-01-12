/*****************************************************************************
 * 프로그램명  : FooterController.java
 * 설     명  : 풋터 제어 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 풋터 제어 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class FooterController {
    /**
     * 풋터 표시처리 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.FOOTER_URL)
	public ModelAndView goController(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject(ConstKey.RESULT_DATA);
        modelAndView.setViewName(ConstUrl.FOOTER_JSP_URL);
		
		return modelAndView;
	}
}
