/*****************************************************************************
 * 프로그램명  : UserConstKey.java
 * 설     명  : 회원 관리 상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.06  YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.user.constant;

/**
 * 회원 관리 상수 정의
 * @author  ace
 * @version 1.0
 */
public class UserConstKey {
	
	/** 회원가입 */
	public static final String ACTION_USER_JOIN_OK = "ACTION_USER_JOIN_OK";
	
	/** 회원가입 이메일 인증 메일 재발송 */
	public static final String ACTION_USER_RESEND_AUTH_MAIL_OK = "ACTION_USER_RESEND_AUTH_MAIL_OK";

	/** 회원가입 이메일 인증 OK */
	public static final String ACTION_USER_MAIL_AUTH_OK = "ACTION_USER_MAIL_AUTH_OK";
	
	/** 회원정보 변경 */
	public static final String ACTION_EDIT_USER_INFO_OK = "ACTION_EDIT_USER_INFO_OK";
	
	/** 회원탈퇴 */
	public static final String ACTION_DELETE_USER_INFO_OK = "ACTION_DELETE_USER_INFO_OK";
	
	/** 비밀번호 변경 이메일 발송 OK */
	public static final String ACTION_USER_RESET_PWD_MAIL_SEND_OK = "ACTION_USER_RESET_PWD_MAIL_SEND_OK";
	
	/** 비밀번호 변경 이메일 인증 OK */
	public static final String ACTION_USER_RESET_PWD_MAIL_AUTH_OK = "ACTION_USER_RESET_PWD_MAIL_AUTH_OK";
	
	/** 비밀번호 변경 OK */
	public static final String ACTION_EDIT_USER_PASSWORD_OK = "ACTION_EDIT_USER_PASSWORD_OK";
	
	/** 비밀번호 변경 - 비밀번호 재설정 시  */
	public static final String ACTION_RESET_PASSWORD_OK = "ACTION_RESET_PASSWORD_OK";
	
	/** 비밀번호 찾기 ID 검증 OK */
	public static final String ACTION_SELECT_USER_BY_EMAIL = "ACTION_SELECT_USER_BY_EMAIL";

	/** VIP 승급요청 */
	public static final String ACTION_USER_REQ_UPGRADE = "ACTION_USER_REQ_UPGRADE";
	
}
