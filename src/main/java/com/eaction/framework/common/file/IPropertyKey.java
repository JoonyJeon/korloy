/*****************************************************************************
 * 프로그램명  : IPropertyKey.java
 * 설     명  : 프로퍼티 파일이 키값 정의
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.file;

/**
 * 프로퍼티 키값정의
 * @author eaction
 * @version 1.0
 * @see PropertyDataMng
 */
public interface IPropertyKey {

	/**  도메인 KEY  */
	public static final String DOMAIN_NAME = "DOMAIN_NAME";
	/**  도메인 KEY  */
	public static final String DOMAIN_NAME_MAIL = "DOMAIN_NAME_MAIL";
	/**  도메인 KEY  */
	public static final String MOBILE_DOMAIN_NAME = "MOBILE_DOMAIN_NAME";

	/** 파일 업로드 패스 설정 */
	public static final String FILE_UPLOAD_ROOT = "FILE_UPLOAD_ROOT";
	/** 파일 업로드 패스 설정(실제 저장 경로) */
	public static final String FILE_UPLOAD_ROOT_REAL_PATH = "FILE_UPLOAD_ROOT_REAL_PATH";
	/** 첨부파일 최대 싸이즈 */
	public static final String FILE_UPLOAD_MAX_SIZE = "FILE_UPLOAD_MAX_SIZE";

	/** 프로시저 파라미터 데이타 구분자 */
	public static final String PROC_DATA_SEP = "PROC_DATA_SEP";
	/** 프로시저 파라미터 항목구분자 */
	public static final String PROC_ITEM_SEP = "PROC_ITEM_SEP";

	/**  쿠키설정시간 설정  */
	public static final String COOKIE_MAX_TIME = "COOKIE_MAX_TIME";

	/** 타임존 설정  */
	public static final String TIME_ZONE = "TIME_ZONE";
	/**  언어설정[ KOR | JPN | ENG] 설정  */
	public static final String LANGUAGE = "LANGUAGE";
	/**  기본 언어 Locale 설정  */
	public static final String DEF_LOCALE = "DEF_LOCALE";

	/**  로그출력레벨[ DEBUG(1) | INFO(2) | WARN(3) | ERROR(4) | FATAL(5) ] 설정  */
	public static final String LOG_LEVEL = "LOG_LEVEL";

	/** 접속로그 출력 */
	public static final String  SET_CONNLOG = "SET_CONNLOG";

	/**  SCRIPT파일경로 */
	public static final String PATH_SCRIPT = "PATH_SCRIPT";
	/**  CSS파일경로  */
	public static final String PATH_CSS = "PATH_CSS";
	/**  Image파일경로  */
	public static final String PATH_IMG = "PATH_IMG";

	/** 관리자 지정 유저그룹레벨 */
	public static final String ADMIN_GROUP_LEVEL = "ADMIN_GROUP_LEVEL";
	/** 슈퍼관리자 지정 유저그룹레벨 */
	public static final String SUPERADMIN_GROUP_LEVEL = "SUPERADMIN_GROUP_LEVEL";
    /**  관리자 유무(일반 : 3) */
	public static final String ADMIN_KIND_USER = "ADMIN_KIND_USER";
	/**  관리자 유무(일반관리자 : 2) */
	public static final String ADMIN_KIND_ADMIN = "ADMIN_KIND_ADMIN";
	/**  관리자 유무(슈퍼관리자 : 1) */
	public static final String ADMIN_KIND_SUPERADMIN = "ADMIN_KIND_SUPERADMIN";

	/**  Image파일경로  */
	public static final String AJAX_CONNECTION_ERROR = "AJAX_CONNECTION_ERROR";
	/**  Normal Paging Count  */
	public static final String NORMAL_PAGE_ROWNUM = "NORMAL_PAGE_ROWNUM";

	/**  DTD 타입 설정  */
	public static final String DTD_TYPE = "DTD_TYPE";

	/** 루트패스  */
	public static final String ROOT_PATH  = "ROOT_PATH";
	public static final String ROOT_FILE_PATH  = "ROOT_FILE_PATH";

	public static final String FULL_DATE_FORMAT_ENG = "FULL_DATE_FORMAT_ENG";
	public static final String SHORT_DATE_FORMAT_ENG = "SHORT_DATE_FORMAT_ENG";
	public static final String CAL_DATE_FORMAT_ENG = "CAL_DATE_FORMAT_ENG";
	public static final String CAL_DATE_LOCALE_ENG = "CAL_DATE_LOCALE_ENG";
	public static final String JQGRID_CAL_DATE_FORMAT_ENG = "JQGRID_CAL_DATE_FORMAT_ENG";

	public static final String FULL_DATE_FORMAT_KOR = "FULL_DATE_FORMAT_KOR";
	public static final String SHORT_DATE_FORMAT_KOR = "SHORT_DATE_FORMAT_KOR";
	public static final String CAL_DATE_FORMAT_KOR = "CAL_DATE_FORMAT_KOR";
	public static final String CAL_DATE_LOCALE_KOR = "CAL_DATE_LOCALE_KOR";
	public static final String JQGRID_CAL_DATE_FORMAT_KOR = "JQGRID_CAL_DATE_FORMAT_KOR";
	
	/** 업로드 경로 설정  */
	public static final String UPLOAD_ROOT_PATH = "UPLOAD_ROOT_PATH";
	public static final String FILE_ROOT_PATH = "FILE_ROOT_PATH";

	/** 중복로그인 체크유무 */
	public static final String CHK_DUP_LOGIN = "CHK_DUP_LOGIN";
	
	public static final String BIZERR_RETCOD_STR = "BIZERR_RETCOD_STR";
	public static final String BIZERR_RETCOD_END = "BIZERR_RETCOD_END";
	public static final String SYSERR_RETCOD_STR = "SYSERR_RETCOD_STR";
	public static final String SYSERR_RETCOD_END = "SYSERR_RETCOD_END";
	
	/** 메일발송 */
	public static final String SMTP_HOST_NAME = "SMTP_HOST_NAME";
	public static final String SMTP_PORT = "SMTP_PORT";
	public static final String SMTP_USERNAME = "SMTP_USERNAME";
	public static final String SMTP_PASSWORD = "SMTP_PASSWORD";
	
	
	/** Google reCAPTCHA */
	public static final String SITE_KEY = "SITE_KEY"; 
	
	/** 회원 권한 */
	public static final String UG0001 = "UG0001"; 
	public static final String UG0002 = "UG0002"; 
	public static final String UG0003 = "UG0003"; 
	public static final String UG0004 = "UG0004"; 
	public static final String UG0005 = "UG0005"; 
	public static final String UG0007 = "UG0007"; 
}
