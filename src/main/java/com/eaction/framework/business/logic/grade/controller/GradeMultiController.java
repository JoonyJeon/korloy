/*****************************************************************************
 * 프로그램명  : GradeMultiController.java
 * 설     명  : 재종가이드 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02  SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.grade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.grade.constant.GradeConstKey;
import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.business.logic.grade.service.GradeService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 * GradesGuide 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class GradeMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(GradeMultiController.class);    

	@Resource
	private GradeService gradeService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/*
	 * 재종가이드 겸색결과 목록 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.GRADES_GUIDE_URL, params = ConstKey.ACTION_ID + "=" + GradeConstKey.ACTION_GRADE_GUIDE_SEARCH_RESULT_LIST)
	@ResponseBody
    public Map<String, Object> selectGradeGuideSearchList(HttpServletRequest request, HttpServletResponse response, GradeInfo info) throws UserSysException{
    	Map<String, Object> result = new HashMap<>();
		
    	String exceptionMsg ="";
    	
		try{
			result = gradeService.selectGradeList(request, info);
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN GRADES GUIDE SEARCH RESULT :: "+exceptionMsg);
		}
		
		return result;
	}	
	/*
	 * 재종가이드 팝업 겸색결과 목록 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.GRADES_GUIDE_URL, params = ConstKey.ACTION_ID + "=" + GradeConstKey.ACTION_GRADE_DETAIL)
	@ResponseBody
	public Map<String, Object> selectGradeGuideDetail(HttpServletRequest request, HttpServletResponse response, GradeInfo info) throws UserSysException{
		Map<String, Object> result = new HashMap<>();
		GradeInfo gradeInfo = new GradeInfo();
		String exceptionMsg ="";
		//사용자 언어 취득 - 다국어처리를 위해
		HttpSession session = request.getSession(true);
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);

        if("".equals(StringUtil.nvl(session_lang))) {
            session_lang = ConfigMng.getLang(request);
        }
		
		try{
			gradeInfo = gradeService.selectGradeDetail(request, info);
			gradeInfo.setGrade_desc(LangMng.LANG_D(gradeInfo.getGrade(), session_lang));
			
		}catch(Exception e){
			exceptionMsg = e.getMessage();
			logger.debug("ERROR IN GRADES GUIDE DETAIL :: "+exceptionMsg);
		}
		result.put("gradeInfo", gradeInfo);
		return result;
	}	

}
