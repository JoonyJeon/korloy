/*****************************************************************************
 * 프로그램명  : OrderService.java
 * 설     명  : 견적 요청 관리 business-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.01   YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.order.model.OrderAssemblyInfo;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 견적 요청 관리 서비스
 * @author  eaction
 * @version 1.0
 */
public interface OrderService {

	/**
	 * 주문요청서 작성 페이지 주문자 초기 표시값
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public OrderInfo selectOrderUserInfo(OrderInfo paramInfo) throws UserSysException;

	/**
	 * 선택 장바구니 리스트
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, Object>selectOrderBasketList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 장바구니 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectOrderBasketItemList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 어셈블리 그룹 리스트
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, Object>selectOrderAssemList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 어셈블리 그룹 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<OrderAssemblyInfo> selectOrderAssemItemList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 견적요청서 등록(DB insert + 메일발송)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertOrder(HttpServletRequest request, OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 어셈블리 견적요청서 메일발송(메일발송만)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean sendAssemblyOrderMail(HttpServletRequest request, OrderInfo paramInfo) throws UserSysException;
}
