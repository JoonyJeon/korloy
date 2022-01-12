/*****************************************************************************
 * 프로그램명  : GradeConstKey.java
 * 설     명  : 재종가이드 상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.07.30  SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basket.constant;

/**
 * 재종가이드 상수 정의
 * @author  ace
 * @version 1.0
 */

public class BasketConstKey {
	
	/** 장바구니 목록 취득 처리 */
	public static final String ACTION_BASKET_LIST = "ACTION_BASKET_LIST";
	/** 장바구니 목록 개수 처리 */
	public static final String ACTION_BASKET_LIST_CNT = "ACTION_BASKET_LIST_CNT";
	/** 장바구니 등록 처리 */
	public static final String ACTION_BASKET_ADD_OK = "ACTION_BASKET_ADD_OK";
	/** 장바구니 수정 처리 */
	public static final String ACTION_BASKET_EDIT_OK = "ACTION_BASKET_EDIT_OK";
	/** 장바구니 아이템 수정 처리 -> (수량 수정시) */
	public static final String ACTION_BASKET_ITEM_EDIT_OK = "ACTION_BASKET_ITEM_EDIT_OK";
	/** 장바구니 삭제 처리 */
	public static final String ACTION_BASKET_DEL_OK = "ACTION_BASKET_DEL_OK";
	/** 장바구니 아이템 삭제 처리 */
	public static final String ACTION_BASKET_ITEM_DEL_OK = "ACTION_BASKET_ITEM_DEL_OK";
	
	/** 장바구니 아이템 디테일 팝업 처리 */
	public static final String ACTION_SHOW_ITEM_DETAIL = "ACTION_SHOW_ITEM_DETAIL";
	
	/** 장바구니 그룹의 GTC 파일 다운로드 파일 생성 */
	public static final String ACTION_GET_GTC_ZIP_FILE_PATH= "ACTION_GET_GTC_ZIP_FILE_PATH";
	/** 장바구니 그룹의 GTC 파일 다운로드 실제 다운로드 처리  */
	public static final String ACTION_GET_GTC_ZIP_FILE= "ACTION_GET_GTC_ZIP_FILE";
	/** 장바구니 그룹 아이템의 하나의 GTC 파일 다운로드 */
	public static final String ACTION_GET_GTC_FILE= "ACTION_GET_GTC_FILE";
	
	
}