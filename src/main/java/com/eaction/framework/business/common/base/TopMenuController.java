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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.service.CodeService;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
//import com.eaction.framework.business.logic.main.model.MainInfo;
//import com.eaction.framework.business.logic.main.model.MainSearchInfo;
//import com.eaction.framework.business.logic.main.service.MainServiceImpl;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * Top 메뉴 제어 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class TopMenuController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(TopMenuController.class);
	@Resource
	private AppService appService;
	@Resource
	private CodeService codeService;
	@Resource
	private BasketService basketService;	
	@Resource
	private AssemblyService assemblyService;	
//	@Autowired
//	MainServiceImpl mainService;
	
	/**
	 * doProcess 메소드
	 *
	 * @param   HttpServletRequest 요청	
	 * @param   HttpServletResponse	 응답
	 * @return  ModelAndView 뷰표시처리객체
	 */
	@RequestMapping(value = ConstUrl.TOP_URL)
 	public ModelAndView getPage( HttpServletRequest   request, HttpServletResponse  response, Device device) throws UserSysException {        		
 		ModelAndView modelAndView = new ModelAndView();
 		AppInfo searchInfo = new AppInfo();
 		List<AppInfo> main_app_list = appService.selectMainApplicationList(searchInfo);
		AppInfo searchinfo = new AppInfo();
		searchinfo.setOrderSort("SA_HIER.SEQ");
		searchinfo.setIsPageYn(ConstKey.KEY_N);
		List<AppInfo> sub_app_list = appService.selectSubApplicationList(searchinfo);
		List<CodeInfo> nation_list = codeService.getNationCodeInfo();
		
		//장바구니 개수 취득을 위한 userInfo 취득
		HttpSession session = request.getSession(true);
 		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
 		String comm_nation = (String)session.getAttribute(ConstKey.SESSION_NATION);
 		String comm_lang = ConfigMng.getLang(request);
 		String comm_size_unit = (String)session.getAttribute(ConstKey.SESSION_SIZE_UNIT);
 		String comm_curr = (String)session.getAttribute(ConstKey.SESSION_CURRENCY);
 		
 		int cartCnt = 0;
 		int assemCnt = 0;
 		if(userInfo !=null){
 			BasketInfo basketInfo = new BasketInfo();
 			basketInfo.setUser_id(userInfo.getUser_id());
 			cartCnt = basketService.selectBasketListCnt(basketInfo);
 			AssemblyInfo assemInfo = new AssemblyInfo();
 			assemInfo.setLogin_user_id(userInfo.getUser_id());
 			assemCnt = assemblyService.selectAssemblyListCnt(assemInfo);
 			
 			// 세션에 설정된 국가가 없고 로그인유저이면 유저의 국가로 설정
 			if("".equals(StringUtil.nvl(comm_nation))) {
 				comm_nation = userInfo.getUser_nation();
 				// 로그인유저의 국가가 없으면 KOR로 설정
 				if("".equals(StringUtil.nvl(comm_nation))) {
 					comm_nation = "KOR";
 				}else {
 					session.setAttribute(ConstKey.SESSION_NATION, comm_nation);
 				}
 			}

 			// 세션에 설정된 길이단위가 없고 로그인유저이면 유저의 길이단위로 설정
 			if("".equals(StringUtil.nvl(comm_size_unit))) {
 				comm_size_unit = userInfo.getUnit_cd();
 				// 로그인유저의 언어가 없으면 Metric으로 설정
 				if("".equals(StringUtil.nvl(comm_size_unit))) {
 					comm_size_unit = "CC0001"; 					
 				}else {
 					session.setAttribute(ConstKey.SESSION_SIZE_UNIT, comm_size_unit);
 				}
 			}
 			
 			// 세션에 설정된 통화단위가 없고 로그인유저이면 유저의 통화단위로 설정
 			if("".equals(StringUtil.nvl(comm_curr))) {
 				comm_curr = userInfo.getCurr_cd();
 				// 로그인유저의 언어가 없으면 USD로 설정
 				if("".equals(StringUtil.nvl(comm_curr))) {
 					comm_curr = "CC0077";
 				}else {
 					session.setAttribute(ConstKey.SESSION_CURRENCY, comm_curr);
 				}
 			}
 		}else {
 			// 세션에 설정된 국가가 없으면 KOR로 설정
 			if("".equals(StringUtil.nvl(comm_nation))) {
 				comm_nation = "KOR";
 			}
 			
			// 로그인유저의 언어가 없으면 Metric으로 설정
			if("".equals(StringUtil.nvl(comm_size_unit))) {
				comm_size_unit = "CC0001";
			}
 			
			// 로그인유저의 언어가 없으면 USD로 설정
			if("".equals(StringUtil.nvl(comm_curr))) {
				comm_curr = "CC0077";
			}
 		}
		
		for(AppInfo main: main_app_list) {
			main.setMa_nm(StringUtil.nvl(LangMng.LANG_D(main.getMa_cd(), comm_lang), main.getMa_nm()));
			List<AppInfo> mega_sub = new ArrayList<AppInfo>();
			for(AppInfo sub: sub_app_list) {
				sub.setSa_nm(StringUtil.nvl(LangMng.LANG_D(sub.getSa_cd(), comm_lang), sub.getSa_nm()));	
				if(main.getMa_cd().equals(sub.getMa_cd())) {
					mega_sub.add(sub);
				}
			}
			main.setSubAppList(mega_sub);
		}
		
		String deviceType = "";
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		
		List<CodeInfo> tmp_lang_list = new ArrayList<CodeInfo>(LangMng.getLangCode());
		List<CodeInfo> lang_list = new ArrayList<CodeInfo>();
		if("KOR".equals(comm_lang)) {
			tmp_lang_list.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_k)).forEach(
		            s -> lang_list.add(s)
		    );
		}else {
			tmp_lang_list.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_e)).forEach(
					s -> lang_list.add(s)
		    );
		}
		modelAndView.addObject("deviceType", deviceType);
		
		modelAndView.setViewName(ConstUrl.TOP_JSP_URL);
		
		modelAndView.addObject("mega_main_list", main_app_list);
		modelAndView.addObject("mega_sub_list", sub_app_list);
		modelAndView.addObject("nation_list", nation_list);
		modelAndView.addObject("cartCnt", cartCnt);
		modelAndView.addObject("assemCnt", assemCnt);
		modelAndView.addObject("comm_nation", comm_nation);
		modelAndView.addObject("comm_lang", comm_lang);
		modelAndView.addObject("comm_size_unit", comm_size_unit);
		modelAndView.addObject("comm_curr", comm_curr);
		modelAndView.addObject("lang_list", lang_list);
		
 		return modelAndView;
 	}
	
	@RequestMapping(value = ConstUrl.TOP_URL, params=ConstKey.ACTION_ID + "=" + ConstKey.ACTION_RESET_SESSION)
	@ResponseBody
 	public JSONObject setSession( HttpServletRequest   request, HttpServletResponse  response ) {     
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(session.getMaxInactiveInterval());
		
		long currTime = System.currentTimeMillis();
        String expiryTime = Long.toString(currTime + request.getSession().getMaxInactiveInterval() * 1000);
        Cookie cookie = new Cookie("serverTime", Long.toString(currTime));
        cookie.setPath("/");
        response.addCookie(cookie);
        
        Cookie cookie2 = new Cookie("sessionExpiry", "" + expiryTime);
        cookie2.setPath("/");
        response.addCookie(cookie2);

		logger.info("Session 유효시간 재할당시간 : " + new Date(session.getLastAccessedTime()).toString());
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("retCode", "00");
		jsonObject.put("retMsg", session.getMaxInactiveInterval());
		
		return jsonObject;
 	}
}
