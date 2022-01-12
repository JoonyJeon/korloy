/*****************************************************************************
 * 프로그램명  : BasketUrlController.java
 * 설     명  : GradesGuide 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.09  SJY    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basket.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.basket.constant.BasketConstKey;
import com.eaction.framework.business.logic.basket.constant.BasketConstUrl;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 * 장바구니 controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class BasketUrlController {
	@Resource
	private BasketService basketService;
	@Resource
	private AppService appService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(BasketUrlController.class); 
	
	/**
	 * 장바구니 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.BASKET_URL)
	@ResponseBody
	public ModelAndView goBasketList(HttpServletRequest request, HttpServletResponse response, Device device) throws UserSysException {
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		
		String deviceType="";
		
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		
		logger.debug("DEVICE TYPE :: "+deviceType);
		
		ModelAndView modelAndView = new ModelAndView(BasketConstUrl.JSP_BASKET_LIST_URL);
		modelAndView.addObject("deviceType", deviceType);
		
		return modelAndView;
		
	}
	
	/*
	 * 장바구니 아이템 디테일 
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ITEM_DETAIL_URL)
	@ResponseBody
	public ModelAndView selectBasketItemInfo(HttpServletRequest request, HttpServletResponse response, BasketInfo info, AppSearchInfo searchinfo) throws UserSysException{
		
		ModelAndView modelAndView = new ModelAndView(BasketConstUrl.JSP_BASKET_ITEM_DETAIL_URL);
		//이벤트 코드 구분을 위해 추가 - HD - holder, IS - insert, SP - spareParts
		String requestFrom = (String) request.getParameter("requestFrom");
		modelAndView.addObject("cartNo",info.getCart_no());
		modelAndView.addObject("ig_cd",searchinfo.getSearch_ig_cd());
		modelAndView.addObject("search_matnr",searchinfo.getSearch_matnr());
		modelAndView.addObject("requestFrom",requestFrom);
		
		return modelAndView;
	}	
}
