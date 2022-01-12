/*****************************************************************************
 * 프로그램명  : BasicMultiController.java
 * 설     명  : 기본데이터 관리  처리 컨트롤러
 * 참고  사항  : DB처리나 특수한 처리가  있는 경우만 해당 Controller에 정의, 화면이동은 web에 정의
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 ******************************************************************************/
package com.eaction.framework.business.logic.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.assembly.constant.AssemblyConstKey;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.basic.constant.BasicConstKey;
import com.eaction.framework.business.logic.basic.model.BasicInfo;
import com.eaction.framework.business.logic.basic.model.BasicSearchInfo;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




/**
 * 기본데이터 처리컨트롤러
 * @author  eaction
 * @version 1.0
 */
@Controller
public class BasicMultiController {
	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( BasicMultiController.class );
	
	@Autowired
	private BasicService basicService;	

//	@Autowired
//	private MessageSourceAccessor messageSourceAccessor;

	/*
	 * 로그 저장
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ECAT_FR_LOG_URL)
	@ResponseBody
    public void selectAssemblyList(HttpServletRequest request, HttpServletResponse response) throws UserSysException{
		String eventCode = (String)request.getParameter("event");
		String item = (String)request.getParameter("item");
		String content = (String)request.getParameter("content");

		try{
			basicService.doLog(request, eventCode, item, content); 
			
		}catch(Exception e){
			logger.debug("ERROR LOG :: "+e.getMessage());
		}
	}
}
