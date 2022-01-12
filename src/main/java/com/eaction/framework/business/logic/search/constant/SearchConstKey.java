/*****************************************************************************
 * 프로그램명  : SearchConstKey.java
 * 설     명  : 검색 상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.07.30  정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.search.constant;

/**
 * 검색 상수 정의
 * @author  eaction
 * @version 1.0
 */

public class SearchConstKey {
	
	/** Global Search */
	public static final String ACTION_SEARCH_LIST = "ACTION_SEARCH_LIST";
	/** Parametric Search */
	public static final String ACTION_PARA_SEARCH_LIST = "ACTION_PARA_SEARCH_LIST";
	/** Filter 조회 */
	public static final String ACTION_GET_SEARCH_FILTER = "ACTION_GET_SEARCH_FILTER";	
	/** 자동완성 검색 */
	public static final String ACTION_SEARCH_AUTO = "ACTION_SEARCH_AUTO";
	/** 키워드 검색 */
	public static final String ACTION_SEARCH_KEY_WORD = "ACTION_SEARCH_KEY_WORD";
	/** Sub Application 검색조건 */
	public static final String ACTION_GET_COND_SUB_APP = "ACTION_GET_COND_SUB_APP";
	/** Item Group 검색조건 */
	public static final String ACTION_GET_COND_ITEM_GROUP = "ACTION_GET_COND_ITEM_GROUP";
	/** Item 개수조회 */
	public static final String ACTION_GET_ITEM_CNT = "ACTION_GET_ITEM_CNT";
	/** Parametric Search */
	public static final String ACTION_SEARCH_PARAMETRIC_LIST = "ACTION_SEARCH_PARAMETRIC_LIST";
}