/*****************************************************************************
 * 프로그램명  : OrderUrlController.java
 * 설     명  : 주문 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.25  SJY    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.order.web;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.order.constant.OrderConstKey;
import com.eaction.framework.business.logic.order.constant.OrderConstUrl;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.business.logic.order.service.OrderService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 * 장바구니 controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class OrderUrlController {
	@Resource
	private OrderService orderService;
	@Resource
	private AppService appService;
	@Resource
	private BasicService basicService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(OrderUrlController.class); 
	
	/**
	 * 주문 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ORDER_URL)
	@ResponseBody
	public ModelAndView goOrderList(HttpServletRequest request, HttpServletResponse response, Device device, OrderInfo info) throws UserSysException {
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		info.setComm_cur(ConfigMng.getCurrency(request));
		info.setUser_id(info.getLogin_user_id());
		String order_type = "CART";	// 어셈블리 or 장바구니
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부		
		
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
		
		ModelAndView modelAndView = new ModelAndView(OrderConstUrl.JSP_ORDER_URL);
		Map<String, Object> map= new HashMap<>();	// 선택한 장바구니 정보 리스트 map
		modelAndView.addObject("deviceType", deviceType);

		OrderInfo resultInfo = new OrderInfo();
		//cart_no가 여러 개일 수 이거나 없을 수있음
		if(!"".equals(info.getCart_no())){
			String[] tmpArr = info.getCart_no().split(",");
			info.setCart_no_arr(tmpArr);
			
			// 최근 주문서 조회하여 화면이동시 info 전달
			resultInfo = orderService.selectOrderUserInfo(info);
			map = orderService.selectOrderBasketList(info);
			String event_code = "";
			if(tmpArr.length > 1) {
				event_code = "EC0037";
			}else {
				event_code = "EC0037";
			}
			List<BasketInfo> order_list = (List<BasketInfo>)map.get("list");
			List<String> cart_nm = new ArrayList<String>();
			List<String> matnr = new ArrayList<String>();
			for(BasketInfo basket : order_list){
				cart_nm.add(basket.getCart_nm());
				matnr.add(basket.getMatnr());
			}
			basicService.doLog(request, event_code, String.join(",", cart_nm), String.join(",", matnr));
		}
		//assem_no가 여러 개이거나 없을 수있음
		if(!"".equals(info.getAssem_no())){
			String event_code = "";
			String[] assemArr = info.getAssem_no().split(",");
			info.setAssem_no_arr(assemArr);
			order_type = "ASSEMBLY";
			
			if(assemArr.length > 1) {
				event_code = "EC0013";
			}else {
				event_code = "EC0012";
			}
			map = orderService.selectOrderAssemList(info);
			basicService.doLog(request, event_code, (String)map.get("assem_nm"), (String)map.get("matnr"));
		}
		
		modelAndView.addObject(OrderConstKey.ORDER_TYPE, order_type);	// 주문 타입 (장바구니 주문서 or 어셈블리 주문서)
		modelAndView.addObject(OrderConstKey.LAST_ORDER_DATA, resultInfo);	// 주문자 정보(최근 주문서 정보)
		modelAndView.addObject(OrderConstKey.ORDER_SELECT_LIST_DATA, map);	// 선택한 장바구니 or 어셈블리 정보 리스트

		modelAndView.addObject("priceYN", priceYN);
		modelAndView.addObject("stockYN", stockYN);
		
		return modelAndView;
		
	}
	
}
