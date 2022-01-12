/*****************************************************************************
 * 프로그램명  : SearchUrlController.java
 * 설     명  : 검색 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.02  정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.search.web;

import java.util.List;
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
import com.eaction.framework.business.logic.search.model.SearchInfo;
import com.eaction.framework.business.logic.search.constant.SearchConstUrl;
import com.eaction.framework.business.logic.search.service.SearchService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * SearchUrlController controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class SearchUrlController {
	@Resource
	private SearchService searchService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	
	/**
	 * 검색 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL)
	@ResponseBody
	public ModelAndView goSearchList(HttpServletRequest request, HttpServletResponse response, Device device, SearchInfo searchInfo) throws UserSysException {
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		Map gtc_list = searchService.selectGTCHierList(searchInfo);
		ModelAndView modelAndView = new ModelAndView(SearchConstUrl.JSP_SEARCH_URL, ConstKey.SEARCH_DATA, searchInfo);
		List<SearchInfo> cond_main_app_list = searchService.selectSearchCondMainApp();
		modelAndView.addObject("cond_main_app_list", cond_main_app_list);
		modelAndView.addObject("gtc_list", gtc_list.get("list"));
		modelAndView.addObject("gtc_item_cnt", gtc_list.get("cnt"));
		String pc_yn = "";
		if(device.isMobile()) {
			pc_yn = "N";
		}else {
			pc_yn = "Y";
		}
		modelAndView.addObject("pc_yn", pc_yn);
		return modelAndView;
		
	}
}
