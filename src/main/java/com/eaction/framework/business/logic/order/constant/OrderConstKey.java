/*****************************************************************************
 * 프로그램명  : OrderConstKey.java
 * 설     명  : 주문 상수정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.25   SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.order.constant;

/**
 * 주문화면 상수 정의
 * @author  eaction
 * @version 1.0
 */

public class OrderConstKey {
	/** 주문 처리 (테이블 인서트 및 메일전송까지) */
	public static final String ACTION_PROCEED_TO_ORDER = "ACTION_PROCEED_TO_ORDER";
	/** 주문할 목록 처리 */
	public static final String ACTION_GET_ORDER_ITEM_LIST = "ACTION_GET_ORDER_ITEM_LIST";
	
	/** 주문서 화면(선택한 장바구니 목록) */
	public static final String ORDER_CART_LIST_OK = "ORDER_CART_LIST_OK";
	
	/** 주문서 화면(선택한 장바구니 정보 리스트) */
	public static final String ORDER_INSERT_OK = "ORDER_INSERT_OK";
	
	/** 주문서 카트그룹 엑셀 */
	public static final String ORDER_CART_LIST_EXCEL_OK = "ORDER_CART_LIST_EXCEL_OK";
	
	
	/** 주문 타입 (장바구니 주문서 or 어셈블리 주문서) */
	public static final String ORDER_TYPE = "ORDER_TYPE";
	
	/** 최근 주문서 조회하여 화면이동시 주문자DATA */
	public static final String LAST_ORDER_DATA = "LAST_ORDER_DATA";

	/** 주문서 화면(선택한 장바구니 정보 리스트) */
	public static final String ORDER_SEL_BASKET_DATA = "ORDER_SEL_BASKET_DATA";
	public static final String ORDER_SELECT_LIST_DATA = "ORDER_SELECT_LIST_DATA";
	
	
}