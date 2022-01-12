/*****************************************************************************
 * 프로그램명  : MainController.java
 * 설      명  : 메인 대쉬보드 처리를 위한 컨트롤러  
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.11.02   정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.main.web;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.eaction.framework.business.common.code.service.CodeService;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.jqgrid.DatatablesUtil;
import com.eaction.framework.common.jqgrid.JqGridUtil;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.PagingUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.login.constant.LoginConstKey;
import com.eaction.framework.business.logic.main.constant.MainConstKey;
import com.eaction.framework.business.logic.main.constant.MainConstUrl;
import com.eaction.framework.business.logic.main.model.MainInfo;
import com.eaction.framework.business.logic.main.model.MainSearchInfo;
import com.eaction.framework.business.logic.main.service.MainService;
import org.apache.commons.lang3.StringEscapeUtils;
/**
 * 메인 화면 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class MainController {
	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( MainController.class );
	
	@Autowired
	private MainService mainService;
	@Resource
	private BasicService basicService;
	@Resource
	private CodeService codeService;
	
//	@Autowired
//	private MessageSourceAccessor messageSourceAccessor;
	
    /**
     * 메인 대쉬보드 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MAIN_LANG_CHANGE_URL)
	@ResponseBody
	public JSONObject doLanguageChange(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		HttpSession session = request.getSession(true);
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonLang = new JSONObject();
		String param_lang = (String) request.getParameter(ConstKey.SESSION_LANG);
		String ar_lang_cd = (String) request.getParameter("ar_lang_cd");
		
		List<CodeInfo> nation_combo = codeService.getNationCodeInfo();
		List<CodeInfo> lang_combo = LangMng.getLangCode();
		JSONObject json_nation = new JSONObject();
		JSONObject json_lang = new JSONObject();
		if("KOR".equals(param_lang)) {
			nation_combo.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_k)).forEach(
					s -> json_nation.put(s.getCode(), s.getCodenm_k())
			);
		}else {
			nation_combo.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_e)).forEach(
					s -> json_nation.put(s.getCode(), s.getCodenm_e())
			);
		}
		// 정렬
		if("KOR".equals(param_lang)) {
			lang_combo.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_k)).forEach(
					s -> json_lang.put(s.getCode(), s.getCodenm_k())
			);
		}else {
			lang_combo.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_e)).forEach(
					s -> json_lang.put(s.getCode(), s.getCodenm_e())
			);
		}
		
		// 개인정보동의서 내용조회
		CodeInfo privacy_info = codeService.getPrivacyInfo(param_lang);
		jsonObject.put("privacy_info", StringEscapeUtils.unescapeHtml3(privacy_info.getName()));
		jsonObject.put("json_nation", json_nation);
		jsonObject.put("json_lang", json_lang);
		
		jsonObject.put("resCode", "0");
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if(!"".equals(StringUtil.nvl(param_lang))) {
			session.setAttribute(ConstKey.SESSION_LANG, param_lang);
			jsonObject.put("lang_list", LangMng.LANG_DISPLAY(param_lang));
		}else {
			String session_lang = ConfigMng.getLang(request);
			jsonObject.put("lang_list", LangMng.LANG_DISPLAY(session_lang));
		}
		
   		return jsonObject;
	}
	
    /**
     * 메인 대쉬보드 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MAIN_SIZE_UNIT_CHANGE_URL)
	@ResponseBody
	public JSONObject doSizeUnitChange(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		HttpSession session = request.getSession(true);
		JSONObject jsonObject = new JSONObject();
		String session_size_unit = (String) request.getParameter(ConstKey.SESSION_SIZE_UNIT);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if(!"".equals(session_size_unit)) {
			session.setAttribute(ConstKey.SESSION_SIZE_UNIT, session_size_unit);
			jsonObject.put("resCode", "0");
		}else {
			jsonObject.put("resCode", "1");
		}

   		return jsonObject;
	}

    /**
     * 메인 대쉬보드 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MAIN_CURRENCY_CHANGE_URL)
	@ResponseBody
	public JSONObject doCurrencyChange(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		HttpSession session = request.getSession(true);
		JSONObject jsonObject = new JSONObject();
		String session_currency = (String) request.getParameter(ConstKey.SESSION_CURRENCY);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if(!"".equals(session_currency)) {
			session.setAttribute(ConstKey.SESSION_CURRENCY, session_currency);
			jsonObject.put("resCode", "0");
		}else {
			jsonObject.put("resCode", "1");
		}

   		return jsonObject;
	}
	
    /**
     * 메인 대쉬보드 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MAIN_NATION_CHANGE_URL)
	@ResponseBody
	public JSONObject doNationChange(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		HttpSession session = request.getSession(true);
		JSONObject jsonObject = new JSONObject();
		String session_nation = (String) request.getParameter(ConstKey.SESSION_NATION);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if(!"".equals(session_nation)) {
			session.setAttribute(ConstKey.SESSION_NATION, session_nation);
			jsonObject.put("resCode", "0");
		}else {
			jsonObject.put("resCode", "1");
		}

   		return jsonObject;
	}
	
    /**
     * 메인 대쉬보드 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.MAIN_GET_LANG_URL)
	@ResponseBody
	public JSONObject doGetLanguage(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		String session_lang = ConfigMng.getLang(request);
   		return LangMng.LANG_DISPLAY(session_lang);
	}
	
}
