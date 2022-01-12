/*****************************************************************************
 * 프로그램명  : BasketDao.java
 * 설     명  : 견적 요청  관리 관리 DAO
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.01   YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.order.dao;

import java.util.List;

import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.order.model.OrderAssemblyInfo;
import com.eaction.framework.business.logic.order.model.OrderInfo;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 장바구니관리  DAO
 * @author  eaction
 * @version 1.0
 */
public interface OrderDao {
	
	/**
	 * 주문요청서 작성 페이지 주문자 초기 표시값
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public OrderInfo selectOrderUserInfo(OrderInfo paramInfo) throws UserSysException;

	/**
	 * 선택 장바구니 리스트 갯수
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
    public int selectOrderBasketListCnt(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 장바구니 리스트
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo>selectOrderBasketList(OrderInfo paramInfo) throws UserSysException;

	/**
	 * 선택 장바구니 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo>selectOrderBasketItemList(OrderInfo paramInfo) throws UserSysException;
	
	
	/**
	 * 선택 어셈블리 그룹 리스트 갯수
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectOrderAssemListCnt(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 어셈블리 그룹 리스트
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo>selectOrderAssemList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 선택 어셈블리 그룹 아이템 리스트(EXCEL)
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<OrderAssemblyInfo>selectOrderAssemItemList(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 메일 제목에 표시할 장바구니 명
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public BasketInfo selectOrderInsertBasketInfo(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 메일 제목에 표시할 어셈블리 명
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public AssemblyInfo selectOrderInsertAssemblyInfo(OrderInfo paramInfo) throws UserSysException;
	
	/**
	 * 견적요청서 추가
	 * @param orderInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertOrder(OrderInfo orderInfo) throws UserSysException;

	/**
	 * 메일 수신자 참조자 
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public OrderInfo selectOrderMailReceiver(OrderInfo paramInfo) throws UserSysException;
}
