/*****************************************************************************
 * 프로그램명  : StaffConstUrl.java
 * 설     명  : 스텝관리 URL상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.staff.constant;

/**
 * 스텝관리 URL상수 정의
 * @author  eaction
 * @version 1.0
 */

public class StaffConstUrl {
	
	/** 스텝 관리 URL */
	public static final String STAFF_MEMBER_URL= "/company/staff.do";
	
	/** 스텝관리 리스트 표시 jspURL */
	public static final String JSP_STAFF_LIST_URL= "/staff/List";

	/** 스텝 등록 관리 팝업 URL */
	public static final String STAFF_MEMBER_POP_ADD_URL= "/company/staffAddPopup.do";
	
	/** 스텝관리 등록 표시 jspURL */
	public static final String JSP_STAFF_ADD_URL = "/staff/Add";

	/** 스텝 수정 관리 팝업 URL */
	public static final String STAFF_MEMBER_POP_EDIT_URL= "/company/staffEditPopup.do";
	
	/** 스텝관리 갱신 표시 jspURL */
	public static final String JSP_STAFF_EDIT_URL = "/staff/Edit";
	
	/** 마이페이지 수정 관리 팝업 URL */
	public static final String MYPAGE_POP_UP_URL = "/company/mypagePopup.do";
	
	/** 마이페이지 갱신 표시 jspURL */
	public static final String JSP_MYPAGE_URL = "/staff/Mypage";
	
}