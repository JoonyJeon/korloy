/*****************************************************************************
 * 프로그램명  : ConstUrl.java
 * 설     명  : 상수정의 클래스(URL관련)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.constant;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Const정의(전체URL관련Const정의）Bean
 *
 * @author  eaction
 * @version 1.0
 */

public class ConstUrl{

	/** 공통 - AJAX를 이용한 코드리스트 취득 */
	public static final String COMM_GROUP_CODE_URL = "/comm/code.do";
	/** 파일 다운로드 URL */
	public static final String FILE_DOWNLOAD_URL_BOARD = "/download.do";
	
	public static final String ACCEPT_FAIL_JSON = "/access_error.json";

	/*################ 화면이동 URL 정의 #################################*/
	/** 탑화면 */
	public static final String TOP_URL = "/top.do";
	/** 왼쪽화면 */
	public static final String LEFT_URL = "/left.do";
	/** 풋터화면 */
	public static final String FOOTER_URL = "/footer.do";
	/** 상단팝업화면 */
	public static final String RIGHT_WRAP_URL = "/right_wrap.do";
	/** 로그인 화면URL */
	public static final String LOGIN_URL = "/login.go";
	/** 로그인화면으로 포워드 하는 빈 화면URL */
	public static final String FORWARD_URL = "/forward.go";
	/** Ajax json object return test 화면URL */
	public static final String TEST_URL_1 = "/object.go";
	/** Ajax json list return test 화면URL */
	public static final String TEST_URL_2 = "/list.go";
	/** 첫번째 페이지 */
	public static final String FIRST_URL = "/main.do";
	/** 대쉬보드 모달  */
	public static final String MAIN_DETAIL_URL = "/mainPopup.do";
	/** 대쉬보드  */
	public static final String MAIN_LANG_CHANGE_URL = "/langChange.do";
	/** 대쉬보드  */
	public static final String MAIN_SIZE_UNIT_CHANGE_URL = "/sizeUnitChange.do";
	/** 대쉬보드  */
	public static final String MAIN_CURRENCY_CHANGE_URL = "/currencyChange.do";
	/** 대쉬보드  */
	public static final String MAIN_NATION_CHANGE_URL = "/nationChange.do";
	/** 대쉬보드  */
	public static final String MAIN_GET_LANG_URL = "/getLang.do";
	/*############################### Mobile 기능 정의 #####################################*/
	/** 탑화면 */
	public static final String MOBILE_TOP_URL = "/top.mo";
	/** 왼쪽화면 */
	public static final String MOBILE_LEFT_URL = "/left.mo";
	/** 풋터화면 */
	public static final String MOBILE_FOOTER_URL = "/footer.mo";
	/** 상단팝업화면 */
	public static final String MOBILE_RIGHT_WRAP_URL = "/right_wrap.mo";
	/** 로그인  */
	public static final String MOBILE_LOGIN_URL = "/mlogin.go";
	/** 로그인후 첫번제 표시 기본 페이지 */
	public static final String MOBILE_FIRST_URL = "/main.mo";
	/** 메인대쉬보드페이지 */
	public static final String MOBILE_MAIN_URL = "/main.mo";
	/** 대쉬보드 모달  */
	public static final String MOBILE_MAIN_DETAIL_URL = "/mainPopup.mo";

	/** 탑화면JSP*/
	public static final String MOBILE_TOP_JSP_URL = "/common/mbmenu/mTop";
	/** 왼쪽화면JSP */
	public static final String MOBILE_LEFT_JSP_URL = "/common/mbmenu/mLeft";
	/** 풋터화면JSP */
	public static final String MOBILE_FOOTER_JSP_URL = "/common/mbmenu/mFooter";
	/** 상단팝업화면JSP */
	public static final String MOBILE_RIGHT_WRAP_JSP_URL = "common/mbmenu/right_wrap";


	/*################################ 시스템관리 기능 링크 정의 #################################*/
	/** 관리자 메모리로드 화면URL */
	public static final String SYSTEM_MEMORYLOAD_URL = "/system/memoryload.do";
	/** 관리자 메모리로드 화면URL */
	public static final String SYSTEM_MEM_RELOAD_URL = "/system/memReload.go";
	/** 관리자 메뉴관리 화면URL */
	public static final String SYSTEM_MENU_URL = "/system/menu.do";
	/** 관리자 메뉴관리 등록 모달 화면URL */
	public static final String SYSTEM_MENU_POP_URL = "/system/menuPopup.do";
	/** 관리자 메뉴관리 수정 모달 화면URL */
	public static final String SYSTEM_MENU_POP_MOD_URL = "/system/menuModPopup.do";
	/** 시스템관리 - 메뉴명칭 */
	//public static final String RESC_MENU_TITLE_ACTION_URL = "/system/menuTitle/MenuTitle.do";

	/** 그룹별 관리자 메뉴관리 화면URL */
	public static final String SYSTEM_GROUPMENU_URL = "/system/groupmenu.do";
	/** 관리자 유저관리 화면URL */
	public static final String SYSTEM_EMP_URL = "/system/emp.do";
	/** 관리자 유저관리 등록 팝업 화면URL */
	public static final String SYSTEM_EMP_POP_URL = "/system/empPopup.do";
	/** 관리자 유저관리 수정 팝업 화면URL */
	public static final String SYSTEM_EMP_POP_MOD_URL = "/system/empModPopup.do";

	/** 관리자 유저그룹관리 화면URL */
	public static final String SYSTEM_EMPGROUP_URL = "/system/empgroup.do";
	/** 관리자 유저그룹관리 화면URL */
	public static final String SYSTEM_EMPGROUP_POP_URL = "/system/empgroupPopup.do";
	/** 관리자 유저그룹관리 화면URL */
	public static final String SYSTEM_EMPGROUP_POP_MOD_URL = "/system/empgroupModPopup.do";

	/** 권한레벨관리 화면 URL */
	public static final String SYSTEM_AUTH_URL = "/system/auth.do";
	/** 권한레벨관리 화면 URL */
	public static final String SYSTEM_AUTH_POP_URL = "/system/authPopup.do";
	/** 시스템설정관리 URL */
	public static final String SYSTEM_SYSCTL_URL = "/system/sysctl.do";
	/** 시스템그룹코드관리 URL */
	public static final String SYSTEM_SYSCOD_URL = "/system/syscod.do";
	/** 시스템그룹코드관리 그룹 코드 등록 팝업 URL */
	public static final String SYSTEM_SYSCOD_GRCODE_POP_URL = "/system/grcodePopup.do";
	/** 시스템그룹코드관리 그룹 코드 수정 팝업 URL */
	public static final String SYSTEM_SYSCOD_GRCODE_POP_MOD_URL = "/system/grcodeModPopup.do";
	/** 시스템그룹코드관리 그룹 코드 등록 팝업 URL */
	public static final String SYSTEM_SYSCOD_CODE_POP_URL = "/system/codePopup.do";
	/** 시스템그룹코드관리 그룹 코드 수정 팝업 URL */
	public static final String SYSTEM_SYSCOD_CODE_POP_MOD_URL = "/system/codeModPopup.do";
	/** 관리자 ip관리 URL */
	public static final String SYSTEM_IPMGMT_URL = "/system/ipMgmt.do";
	/** 관리자 ip관리 등록 팝업 화면URL */
	public static final String SYSTEM_IPMGMT_POP_ADD_URL = "/system/ipMgmtAddPopup.do";
	/** 관리자 ip관리 수정 팝업 화면URL */
	public static final String SYSTEM_IPMGMT_POP_MOD_URL = "/system/ipMgmtModPopup.do";
	/** 관리자 활동이력 관리 URL */
	public static final String SYSTEM_ACTIONHIS_URL = "/system/actionHis.do";
	/** 관리자 활동이력 상세 팝업 화면URL */
	public static final String SYSTEM_ACTIONHIS_POP_DETAIL_URL = "/system/ipMgmtDetailPopup.do";
	/*################ 공통 JSP 화면 URL 정의 #################################*/
	/** 탑화면JSP*/
	public static final String TOP_JSP_URL = "/common/menu/top";
	/** 왼쪽화면JSP */
	public static final String LEFT_JSP_URL = "/common/menu/left";
	/** 풋터화면JSP */
	public static final String FOOTER_JSP_URL = "/common/menu/footer";
	/** 상단팝업화면JSP */
	public static final String RIGHT_WRAP_JSP_URL = "/common/menu/right_wrap";
	/** 에러화면의 JSP URL */
	public static final String JSP_ERROR_URL = "/common/error/systemException";
	
	/*################ 업무화면 이동 URL 정의 #################################*/
	/** Sub Application URL */
	public static final String APP_MAIN_URL = "/app/main.do";
	/** Sub Application URL */
	public static final String APP_SUB_URL = "/app/sub.do";
	/** Item Group List URL */
	public static final String APP_ITEM_GRP_LIST_URL = "/app/itemGroupList.do";
	/** Item Group URL */
	public static final String APP_ITEM_GRP_URL = "/app/itemGroup.do";
	/** Item URL */
	public static final String APP_ITEM_URL = "/app/item.do";
	/** Grade Guide URL */
	public static final String GRADES_GUIDE_URL = "/guide.do";
	/** Cart URL */
	public static final String BASKET_URL = "/cart.do";
	/** Search URL */
	public static final String SEARCH_URL = "/search.do";
	/** Search URL */
	public static final String ORDER_URL = "/order.do";
	/** ASSEMBLY URL */
	public static final String ASSEMBLY_URL = "/assembly.do";
	/** 아이템 디테일 팝업 URL */
	public static final String ITEM_DETAIL_URL = "/detail.do";
	/** 개인정보동의 URL */
	public static final String PRIVACY_URL = "/privacy.do";
	/** 개인정보동의 URL */
	public static final String ECAT_FR_LOG_URL = "/ecatFrontLog.do";
	
}

