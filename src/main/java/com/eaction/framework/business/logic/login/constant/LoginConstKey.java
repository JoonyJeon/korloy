/*****************************************************************************
 * 프로그램명  : LoginConstKey.java
 * 설     명  : 로그인 상수정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.login.constant;

/**
 * 메인화면 상수 정의
 * @author  eaction
 * @version 1.0
 */

public class LoginConstKey {
	/** 메인화면글 데이타 */
	public static final String LOGIN_INFO_DATA = "LOGIN_INFO_DATA";
	
	/** 메인화면목록 데이타 */
	public static final String LOGIN_LIST_PAGE = "LOGIN_LIST_PAGE";	
	
	/** 로그인하고이동한이벤트가발생한곳의URL */
	public static final String BACK_URL = "BACK_URL";
	/** 로그인 초기화면 요청 */
	public static final String LOGIN_INIT = "LOGIN_INIT";
	/** 로그인 처리 요청 */
	public static final String LOGIN_OK = "LOGIN_OK";
	/** 로그인처리결과 */
	public static final String LOGIN_RESULT = "LOGIN_RESULT";
	/** 강제로그인 처리 */
	public static final String LOGIN_FORCE = "LOGIN_FORCE";
	/** 강제로그아웃 처리 */
	public static final String LOGOUT_FORCE = "LOGOUT_FORCE";

	/** 로그인화면에서처리상태(로그인이필요한상태)*/
	public static final String LOGIN_NEED = "LOGIN_NEED";
	/** 로그인 화면에서 처리상태(관리자권한이 필요한 상태) */
	public static final String LOGIN_ADMIN_NEED = "LOGIN_ADMIN_NEED";
	/** 로그인화면에서처리상태(허용되지 않은 ip 주소) */
	public static final String IP_ADDR_NOT_ALLOWED = "IP_ADDR_NOT_ALLOWED";
	/** 로그인화면에서처리상태(로그인실패상태) */
	public static final String LOGIN_FAIL = "LOGIN_FAIL";
	/** 로그인화면에서처리상태(로그인성공상태) */
	public static final String LOGOUT_OK = "LOGOUT_OK";
	/** 권한이 없는 화면 접근*/
	public static final String LOGIN_ROLE = "LOGIN_ROLE";
	/** 타임아웃 로그아웃 */
	public static final String TIMEOUT_LOGOUT_OK = "TIMEOUT_LOGOUT_OK";
	/** 사용자 상태 로그인권한 없음 상태 */
	public static final String LOGIN_STATUS_FAIL = "LOGIN_STATUS_FAIL";
	
	/** 로그인실패 카운트 */
	public static final String LOGIN_FAIL_COUNT = "LOGIN_FAIL_COUNT";
	/** 로그인성공 카운트 */
	public static final String LOGIN_SUCCESS_COUNT = "LOGIN_SUCCESS_COUNT";	

	
}