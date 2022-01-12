/*****************************************************************************
 * 프로그램명  : UserConstKey.java
 * 설     명  : 회원 관리  URL 상수 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.06  YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.user.constant;

/**
 * 회원 관리 URL 상수 정의
 * @author  eaction
 * @version 1.0
 */
public class UserConstUrl {

	
	/** 회원 업무 URL */
	public static final String USER_URL = "/user.do";
	
	/** 회원가입 인증 메일 발송 요청 URL */
	public static final String USER_JOIN_SEND_MAIL = "/user/join/sendEmail.do";
	
	/** 회원가입 인증 번호 검증 URL */
	public static final String USER_JOIN_AUTH = "/user/join/auth.do";
	
	/** 이메일 인증 로직 */
	public static final String USER_EMAIL_AUTH = "/user/email/auth.go";
	
	
	/** 회원가입 인증 번호 입력 JSP URL */
	public static final String JSP_USER_JOIN_AUTH = "/user/emailAuth";
	
}
