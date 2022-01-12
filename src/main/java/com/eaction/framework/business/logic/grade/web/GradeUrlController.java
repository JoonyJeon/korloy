/*****************************************************************************
 * 프로그램명  : GradeUrlController.java
 * 설     명  : GradesGuide 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.02  신주연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.grade.web;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.grade.constant.GradeConstUrl;
import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.business.logic.grade.service.GradeService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 * GradesGuide controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class GradeUrlController {
	@Resource
	private GradeService gradeService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	
	/**
	 * 재종가이드 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.GRADES_GUIDE_URL)
	@ResponseBody
	public ModelAndView goGradesGuide(HttpServletRequest request, HttpServletResponse response, Device device, GradeInfo info) throws UserSysException {
		
		//다국어 처리에서 사용
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		
		ModelAndView modelAndView = new ModelAndView(GradeConstUrl.JSP_GRADE_GUIDE_URL);
		//검색조건리스트들
		Map<String, Object> condition_lists = gradeService.selectSearchConditionList(request);

		String fromItmeYn = "";
		if("".equals(info.getGrade())){
			fromItmeYn = "N";
			modelAndView.addObject("grade", "");
		}else{
			fromItmeYn = "Y";
			modelAndView.addObject("grade", info.getGrade());
		}
		modelAndView.addObject("condition_lists", condition_lists);
		modelAndView.addObject("fromItmeYn", fromItmeYn);
		
		return modelAndView;
		
	}
}
