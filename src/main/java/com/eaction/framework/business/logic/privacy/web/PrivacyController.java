/*****************************************************************************
 * 프로그램명  : PrivacyController.java
 * 설      명  : 개인정보동의 처리를 위한 컨트롤러  
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.11.02   정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.privacy.web;
import java.io.IOException;
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
import org.apache.commons.lang3.StringEscapeUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.privacy.constant.PrivacyConstUrl;
/**
 * 개인정보동의 화면 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class PrivacyController {
	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( PrivacyController.class );
	@Autowired
	private CodeService codeService;
	@Autowired
	private BasicService basicService;	
    /**
     * 개인정보동의 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.PRIVACY_URL)
	@ResponseBody
	public ModelAndView goPrivacy(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		basicService.doLog(request, "EC0038", "", "");
   		return new ModelAndView(PrivacyConstUrl.JSP_PRIVACY_URL);
	}
	
    /**
     * 개인정보동의 화면 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */ 
	@RequestMapping(value = PrivacyConstUrl.PRIVACY_CHANGE_URL)
	@ResponseBody
	public JSONObject getPrivacy(HttpServletRequest request, HttpServletResponse response) throws UserSysException {
		JSONObject jsonObject = new JSONObject();
		String changeLang = (String)request.getParameter("changeLang");
		CodeInfo privacy_info = codeService.getPrivacyInfo(changeLang);
		jsonObject.put("result", StringEscapeUtils.unescapeHtml3(privacy_info.getName()));
		return jsonObject;
	}
}
