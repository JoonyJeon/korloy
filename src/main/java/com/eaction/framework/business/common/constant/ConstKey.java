/*****************************************************************************
 * 프로그램명  : ConstKey.java
 * 설     명  : 상수정의 클래스(일반상수)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 상수정의 클래스
 * @author  eaction
 * @version 1.0
 */

public class ConstKey  {
	/** 유저아이디 */
	public static final String USER_ID = "USER_ID";
	/** 세션의유저데이터Bean정보취득키 */
    public static final String USER_INFO = "USER_INFO";

    /** 프레임 이름(탑프레임) */
	public static final String FRAME_TOP = "TOP";
	/** 프레임 이름(왼쪽프레임) */
    public static final String FRAME_LEFT = "LEFT";
    /** 프레임 이름(메인프레임) */
    public static final String FRAME_MAIN= "MAIN";
    /** 프레임 이름(아래프레임) */
    public static final String FRAME_BUTTOM= "BUTTOM";
    /** 프레임 이름(풋터프레임) */
    public static final String FRAME_FOOTER= "FOOTER";

    /** 처리결과 저장 */
	public static final String PROCESS_RESULT = "PROCESS_RESULT";
	/** 예외저장 */
	public static final String EX_ERR_OBJ = "EX_ERR_OBJ";
	/** 메소드개시로그 */
	public static final String START_LOG = "START";
	/** 메소드종료로그 */
	public static final String END_LOG = "END";

	/** 메인메뉴ID */
	public static final String MAIN_MENU_ID = "mainMenuId";
	/** 서브메뉴ID */
	public static final String SUB_MENU_ID = "subMenuId";

	/** 액션변수명칭 */
	public static final String ACTION_ID = "actionID";
	
	/** 삭제 처리 */
	public static final String ACTION_DELETE = "ACTION_DELETE";	
	/** 엑셀다운로드 처리 */
	public static final String ACTION_GET_EXCEL = "ACTION_GET_EXCEL";	
	/** 파일다운로드 처리 */
	public static final String ACTION_GET_FILE = "ACTION_GET_FILE";	
	/** 파일업로드 처리 */
	public static final String ACTION_PUT_FILE = "ACTION_PUT_FILE";
	/** 파일 로딩 */
	public static final String ACTION_LOAD_FILE = "ACTION_LOAD_FILE";

	/** 특정 사용자그룹에 속한 사용자 리스트를 취득 */
	public static final String ACTION_GROUP_USER = "ACTION_GROUP_USER";
	
	/** 전체 */
	public static final String SELECT_ALL = "전체";
	/** 선택 */
	public static final String SELECT_ONE = "선택";
	
	/** 결과데이터 저장 이름 */
	public static final String RESULT_DATA = "RESULT_DATA";
	/** 결과데이터 저장 이름(JSON) */
	public static final String RESULT_JSON_DATA = "RESULT_JSON_DATA";
	/** 결과데이터 저장 이름(PagingUtil) */
	public static final String RESULT_PAGE_DATA = "RESULT_PAGE_DATA";
	/** 결과데이터 저장 이름(List) */
	public static final String RESULT_LIST_DATA = "RESULT_LIST_DATA";
	/** 검색조건 데이타 저장 이름 */
	public static final String SEARCH_DATA = "SEARCH_DATA";
	/** INFO 데이타 저장 이름 */
	public static final String INFO_DATA = "INFO_DATA";
	
	public static final String VO = "VO";
		
	/** 디폴트키YES */
	public static final String KEY_YES = "YES";
	/** 디폴트키NO */
	public static final String KEY_NO = "NO";
	/** 디폴트키Y */
	public static final String KEY_Y = "Y";
	/** 디폴트키N */
	public static final String KEY_N = "N";
	
	/** jqGrid 데이타 저장 이름 */
	public static final String JQGRID_DATA = "JQGRID_DATA";
	/** jqGrid 처리 결과 */
	public static final String JQGRID_RESULT = "JQGRID_RESULT";
	/** jqGrid 처리 메시지 */
	public static final String JQGRID_MSG = "JQGRID_MSG";
	/** jqGrid 처리 성공 */
	public static final boolean JQGRID_SUCCESS = true;
	/** jqGrid 처리 실패 */
	public static final boolean JQGRID_FAIL = false;
	
	/** 메뉴타입이 G인 경우 */
	public static final String MENU_TYPE_G = "G";
	/** 메뉴타입이 G인 경우 */
	public static final String MENU_TYPE_R = "R";
	/** 메뉴타입이 G인 경우 */
	public static final String MENU_TYPE_P = "P";
	/** 메뉴타입이 G인 경우 */
	public static final String MENU_TYPE_I = "I";
	/** 메뉴타입이 G인 경우 */
	public static final String MENU_TYPE_H = "H";
	
	/** 리스트 취득 */
	public static final String ACTION_LIST = "ACTION_LIST";
	/** 상세정보 취득 */
	public static final String ACTION_VIEW = "ACTION_VIEW";
	/** 등록 폼 표시 */
	public static final String ACTION_INSERT = "ACTION_INSERT";
	/** 등록 처리 */
	public static final String ACTION_INSERT_OK = "ACTION_INSERT_OK";
	/** 갱신 폼 표시 */
	public static final String ACTION_UPDATE = "ACTION_UPDATE";
	/** 갱신 처리 */
	public static final String ACTION_UPDATE_OK = "ACTION_UPDATE_OK";
	/** 삭제 처리 */
	public static final String ACTION_DELETE_OK = "ACTION_DELETE_OK";
	/** 메모리등을 재설정한다 */
	public static final String ACTION_RELOAD_OK = "ACTION_RELOAD_OK";
	/** 유저리스트 삭제한다 */
	public static final String ACTION_DELETE_LIST_OK = "ACTION_DELETE_LIST_OK";
	/** 유저아이디 중복체크한다 */
	public static final String ACTION_CHECK_USERID = "ACTION_CHECK_USERID";

	/** 유저아이디 중복체크한다 */
	public static final String ACTION_RESET_SESSION = "ACTION_RESET_SESSION";
	
	/** 날짜데이터구분자 */
	public static final String SEP_DATE = "-";
	/** 시간데이터구분자 */
	public static final String SEP_TIME = ":";
	/** 전화번호데이터구분자 */
	public static final String SEP_TEL = "-";
	/** 메일데이터구분자 */
	public static final String SEP_MAIL = "@";
	/** 이퀄 */
	public static final String SEP_EQUAL = "=";
	/** 쿼스천 */
	public static final String SEP_QUEST = "?";
	/** 세미콜론 */
	public static final String SEP_SEMI_COLON = ";";
	/** 콜론 */
	public static final String SEP_COLON = ":";
	/** 퍼센트 */
	public static final String SEP_PERCENT = "%";
	/** 마침표 */
	public static final String SEP_DOT = ".";
	/** 마침표 */
	public static final String SEP_UNDER = "_";
	/** 마침표 */
	public static final String SEP_NEW_LINE = System.getProperty("line.separator");

	/** CalendarTag의 요일표시언어 영문 */
	public static final String WEEK_ENG = "WEEK_ENG";
	/** CalendarTag의 요일표시언어 한글 */
	public static final String WEEK_KOR = "WEEK_KOR";
	/** CalendarTag의 요일표시언어 한자 */
	public static final String WEEK_CHNA = "WEEK_CHNA";

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
	/** 중복로그인 */
	public static final String DUP_LOGIN = "DUP_LOGIN";
	/** 사용자 상태 로그인권한 없음 상태 */
	public static final String LOGIN_STATUS_FAIL = "LOGIN_STATUS_FAIL";

	/** 쿠키저장명칭(아이디) */
	public static final String COOKIE_ID = "COOKIE_ID";
	/** 쿠키저장명칭(비밀번호) */
	public static final String COOKIE_PASSWD = "COOKIE_PASSWD";
	/** 쿠키저장명칭(저장여부) */
	public static final String COOKIE_SAVE = "COOKIE_SAVE";

	/** 페이징처리에서 사용할 메소드명칭1 */
	public static final String PAGE_MOVEPAGE1 = "movePage1";
	/** 페이징처리에서 사용할 메소드명칭2 */
	public static final String PAGE_MOVEPAGE2 = "movePage2";
	/** 페이징처리에서 사용할 메소드명칭3 */
	public static final String PAGE_MOVEPAGE3 = "movePage3";
	/** 페이징처리에서 사용할 메소드명칭4 */
	public static final String PAGE_MOVEPAGE4 = "movePage4";
	
	/** 등록처리  */
	public static final String INSERT_MODE = "I";
	/** 수정처리  */
	public static final String UPDATE_MODE = "U";
	/** 삭제처리  */
	public static final String DELETE_MODE = "D";

	/** 슈퍼관리자 */
	public static final String ADMIN_SUPER = "1";
	/** 관리자 */
	public static final String ADMIN_USER = "2";
	/** 관리자모드URL */
	public static final String ADMIN_URL = "/admin/";

	/** 성공의 경우 */
	public static final String KEY_OK = "OK";
	/** 실패의 경우 */
	public static final String KEY_NG = "NG";
	/** 디폴트키위치장소（라디오버튼, 체크박스의 앞） */
	public static final String KEY_PLACE_BEFORE = "BEFORE";
	/** 디폴트키위치장소（라디오버튼, 체크박스의 뒤） */
	public static final String KEY_PLACE_AFTER = "AFTER";
	/** 읽기전용값 설정 */
	public static final String KEY_READONLY = "readOnly";
	/** 체크박스의 checked 상태 */
	public static final String KEY_CHECKED = "checked";

	/** HTML의 공백처리 */
	public static final String KEY_BLANK = "&nbsp;";
	
	/** 접근권한 체크 오브젝트 */
	public static final String ACCESS_BUTTON = "BUTTON";
	
	/** AJAX 데이타 저장 이름 */
	public static final String AJAX_RES_DATA = "ajaxResponse";

	/** JQUERY 데이타 저장 이름 */
	public static final String JQUERY_RES_DATA = "jsonResponse";
	
	/** 처리권한 저장키 */
	public static final String PROC_AUTH = "PROC_AUTH";
	/** 처리결과저장키 */
	public static final String RESULT_KEY = "RESULT_KEY";
	/** 처리결과가 성공인 경우 */
	public static final String RESULT_SUCCESS = "SUCCESS";
	/** 처리결과가 비즈니스에러인 경우 */
	public static final String RESULT_BIZ_FAIL = "BIZ_FAIL";
	/** 처리결과가 실패인 경우 */
	public static final String RESULT_FAIL = "FAIL";
	
	/** 언어설정 */
	public static final String LOCALE_KOR = "ko";
	public static final String LOCALE_ENG = "en";
	public static final String LOCALE_JPN = "ja";
	public static final String LOCALE_DEU = "de";
	public static final String LOCALE_FRA = "fr";
	public static final String LOCALE_POR = "pt";
	public static final String LOCALE_RUS = "ru";
	public static final String LOCALE_SPA = "es";
	public static final String LOCALE_THA = "th";
	public static final String LOCALE_TUR = "tr";
	public static final String LOCALE_VIE = "vi";
	public static final String LOCALE_ZHO = "	zh";
	
	/** 언어코드 -  독일어 */
	public static final String LANG_DEU = "DEU";
	/** 언어코드 -  영어 */
	public static final String LANG_ENG = "ENG";
	/** 언어코드 -  프랑스어 */
	public static final String LANG_FRA = "FRA";
	/** 언어코드 -  한국어 */
	public static final String LANG_KOR = "KOR";
	/** 언어코드 -  포르투갈어 */
	public static final String LANG_POR = "POR";
	/** 언어코드 -  러시아어 */
	public static final String LANG_RUS = "RUS";
	/** 언어코드 -  스페인어 */
	public static final String LANG_SPA = "SPA";
	/** 언어코드 -  태국어 */
	public static final String LANG_THA = "THA";
	/** 언어코드 -  터키어 */
	public static final String LANG_TUR = "TUR";
	/** 언어코드 -  베트남어 */
	public static final String LANG_VIE = "VIE";
	/** 언어코드 -  중국어 */
	public static final String LANG_ZHO = "ZHO";
	/** 언어코드 -  일본어 */
	public static final String LANG_JPN = "JPN";
	/** 세션국가코드 */
	public static final String SESSION_NATION = "SESSION_NATION";
	/** 세션언어코드 */
	public static final String SESSION_LANG = "SESSION_LANG";
	/** 세션길이단위코드 */
	public static final String SESSION_SIZE_UNIT = "SESSION_SIZE_UNIT";
	/** 세션통화코드 */
	public static final String SESSION_CURRENCY = "SESSION_CURRENCY";
	/** 세션사용자권한 */
	public static final String SESSION_AUTH = "SESSION_AUTH";
	/** 세션사용자권한 */
	public static final String SESSION_LANGCODE = "SESSION_LANGCODE";
	/** 코드정보저장 */
	public static final String CODE_VALUE = "CODE_VALUE";
	
	/** 사용자 그룹 레벨코드 000 */
	public static final String KEY_LEVEL_000 = "000";
	/** 사용자 그룹 레벨코드 100 */
	public static final String KEY_LEVEL_100 = "100";
	/** 사용자 그룹 레벨코드 101 */
	public static final String KEY_LEVEL_101 = "101";
	/** 사용자 그룹 레벨코드 101 */
	public static final String KEY_LEVEL_999 = "999";
	
	/*************************** 공통 변수값 정의 ***************************/
	/** Main Application 코드 */
	public static final String VAL_MAIN_APP_CD = "ma_cd";
	/** Sub Application 코드 */
	public static final String VAL_SUB_APP_CD = "sa_cd";
	/** Item Group 코드 */
	public static final String VAL_ITEM_GRP_CD = "ig_cd";
	/** Item 코드 */
	public static final String VAL_ITEM_CD = "matnr";
	
	/*************************** 공통코드 정의 - File Type ***************************/
	/** ITEM(대표이미지) */
	public static final String CODE_FILE_TYPE_ITEM = "CC0017";
	/** ISO 치수도면(Dimension drawing) */
	public static final String CODE_FILE_TYPE_ISO_DD = "CC0018";
	/** Non-ISO 치수도면(Dimension drawing) */
	public static final String CODE_FILE_TYPE_NON_DD = "CC0019";
	/** Basic STP */
	public static final String CODE_FILE_TYPE_STP_B = "CC0020";
	/** Detail STP */
	public static final String CODE_FILE_TYPE_STP_D = "CC0021";
	/** DXF */
	public static final String CODE_FILE_TYPE_DXF = "CC0022";
	/** Single GTC Package */
	public static final String CODE_FILE_TYPE_GTC_S = "CC0023";
	/** Assembly GTC Package */
	public static final String CODE_FILE_TYPE_GTC_A = "CC0024";
	/** Full GTC Package */
	public static final String CODE_FILE_TYPE_GTC_F = "CC0025";
	/** 상표(Header1)이미지 */
	public static final String CODE_FILE_TYPE_TM = "CC0026";
	/** Main, Sub Application 아이콘 */
	public static final String CODE_FILE_TYPE_ICON = "CC0027";
	/** ISO-13399 P21 파일 */
	public static final String CODE_FILE_TYPE_P21 = "CC0028";
	
	
	/** 로그인안해도 되는 URL정의 */
	public static final List LOGIN_URL_LIST ;
	/** 관리자모드만 접근할수 있는 URL 정의 */
	public static final List LOGIN_ADMIN_URL_LIST ;
    static {
    	/* 로그인이 필요한 화면*/
    	LOGIN_URL_LIST = new ArrayList();
    	
//    	LOGIN_URL_LIST.add(ConstUrl.LOGIN_URL);
//    	LOGIN_URL_LIST.add(ConstUrl.FORWARD_URL);    	
//    	LOGIN_URL_LIST.add(ConstUrl.MOBILE_LOGIN_URL);
//    	LOGIN_URL_LIST.add(ConstUrl.MAIN_URL);
//    	LOGIN_URL_LIST.add(ConstUrl.MAIN_LANG_CHANGE_URL);
    	
    	/* 관리자만 접근가능 */
    	LOGIN_ADMIN_URL_LIST = new ArrayList();
    }
}