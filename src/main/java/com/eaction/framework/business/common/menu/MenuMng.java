/*****************************************************************************
 * 프로그램명  : MenuMng.java
 * 설     명  : 메뉴관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.menu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.MenuInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;
import com.eaction.framework.business.common.menu.service.CommMenuService;

/**
 * 메뉴정보 데이터관리（레벨별메뉴정보）Bean
 * @author eaction
 * @version 1.0
 */
@Component
public class MenuMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(MenuMng.class);    
	/** 예외처리용 클래스 이름 */
//	private static final String CLASS_NAME = "MenuMng";
	/** 리스트데이터를 취득하기 위한 리스트객체 */
    private static Properties listProps = new Properties();
    /** 모든 메뉴정보를 담고 있는 해쉬맵 */
    private static Hashtable hMenuList = new Hashtable();
    private static Hashtable uMenuList = new Hashtable();
  
    
	@Autowired
    private CommMenuService commMenuService;

    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setProps(Properties props) {
    	MenuMng.listProps = props;
	}

	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static Properties getProps() {
		return listProps;
	}

    public static Hashtable gethMenuList() {
		return hMenuList;
	}

	public static void sethMenuList(Hashtable hMenuList) {
		MenuMng.hMenuList = hMenuList;
	}
	
	public static Hashtable getuMenuList() {
		return uMenuList;
	}

	public static void setuMenuList(Hashtable uMenuList) {
		MenuMng.uMenuList = uMenuList;
	}
	
	/**
     * 메뉴테이블 데이터를 사용자 레별별로 전부 취득한다
     * @throws UserSysException 예외
     */
    public void initialize(){
    	try {
    		//메뉴정보데이터 취득처리
    		setTableData();
	    } catch (UserSysException ex) {
	    	logger.error("Cannnot Get Menu Data!!");
	    	logger.error(StringUtil.stackTraceToString(ex));
	    	throw new RuntimeException (ex);
	    }
    }

    /**
     * 메뉴테이블 데이터를 메뉴정보등의 관리자 메뉴에서 변경되었을경우 재기동하지 않고 즉시 데이터를 반영한다
     * @throws UserSysException 예외
     */
    public void reloadMenuInfo() throws UserSysException{
    	try{
    		listProps = new Properties();
    		hMenuList = new Hashtable();
    		uMenuList = new Hashtable();    		
        	//회사코드,명칭데이터 취득하여 설정
    		setTableData();

        }catch(UserSysException e){
            throw e;
        }
    }

    /**
     * 테이블에서 메뉴 관련 데이터를 취득해서 프로퍼티에 설정한다
     */
    private void setTableData() throws UserSysException {
    	List arList = null;
    	List arAll = null;
        try {
        	/*
        	 * 전체 메뉴 데이터 취득
        	 */
        	arAll = commMenuService.selectMenuInfoList();
        	if (arAll != null) {
        		MenuInfo menuInfo = null;
        		for (int i = 0; i < arAll.size(); i++) {
        			menuInfo = (MenuInfo)arAll.get(i);
        			hMenuList.put(menuInfo.getMenuId(), menuInfo);
        			uMenuList.put(menuInfo.getUrl(), menuInfo);
        		}
        	}

        	/*
        	 * 권한별 메뉴 데이터 취득
        	 */
        	arList = commMenuService.getMenuInfo();
        	if (arList != null) {
        	    //메뉴정보 데이터를 레벨코드를 키로 프로퍼티에 설정한다
        	    setMenuInfo(arList);
        	}
        	       	
    	} catch (UserSysException ex) {
    		throw ex;
    	}
    }

    /**
     * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
     * @param : conf_File 파일 객체
     */
    private static void setMenuInfo(List arList) {
    	MenuInfo menuInfo = null;
    	List arSub = null;
    	boolean bCheck = false;

    	for (int i = 0; i < arList.size(); i++){
    		menuInfo = (MenuInfo)arList.get(i);

    	    /*
    		 * 메뉴정보를 그룹코드별로 설정한다
    		 */
    	    arSub = (List)listProps.get(menuInfo.getGroupLevel());
    	    bCheck = false;
    	    if (arSub == null) {
    	    	arSub = new ArrayList();
    	    	bCheck = true;
    		}
    	    arSub.add(menuInfo);

    	    if (bCheck) {
    	    	listProps.put(menuInfo.getGroupLevel(), arSub);
    	    }
    	}
    }

    /**
     * 업무키에대한코드명칭데이터BeanObject의리스트데이터취득
     * @param user 유저정보
     * @param group 그룹코드
     * @return List 메뉴정보데이터빈의 리스트
     */
    public static List getMenuList(User user) {

    	String groupLevel = StringUtil.nvl(user.getGroupLevelCod(), ConstKey.KEY_LEVEL_101);
    	List arSub = (List)listProps.get(groupLevel);

        return arSub;
    }

    /**
     * 메뉴아이디의 메뉴이름을 취득한다
     * @param menuId 메뉴아이디
     * @return String 메뉴이름
     */
    public static String getMenuName(String menuId, User userInfo) {
    	String name = "";

    	if (!"".equals(menuId)) {
    	    MenuInfo menuInfo = (MenuInfo)hMenuList.get(menuId);
    	    if(userInfo.getUser_lang().equals("KOR")){
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmKor());
    	    }else if(userInfo.getUser_lang().equals("ENG")){
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmEng());
    	    }else{
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmJpn());
    	    }
    	    name = menuInfo.getMenuNm();
    	}
        return name;
    }
    
    /**
     * 메뉴아이디의 메뉴URL을 취득한다
     * @param menuId 메뉴아이디
     * @return String 메뉴이름
     */
    public static String getMenuUrl(String menuId) {
    	String url = "";

    	if (!"".equals(menuId)) {
    	    MenuInfo menuInfo = (MenuInfo)hMenuList.get(menuId);    	  
    	    url = menuInfo.getUrl();
    	}
        return url;
    }
    
    /**
     * 메뉴URL의 메뉴아이디을 취득한다
     * @param menuUrl 메뉴URL
     * @return String 메뉴아이디
     */
    public static String getMenuId(String menuUrl) {
    	String name = "";

    	if (!"".equals(menuUrl) /*&& (!ConstUrl.MAIN_URL.equals(menuUrl))*/) {
    	    MenuInfo menuInfo = (MenuInfo)uMenuList.get(menuUrl);
    	    if(menuInfo != null) {
    	    	name = menuInfo.getMenuId();
    	    }
    	    
    	}
        return name;
    }
    
    /**
     * 메뉴URL의 메뉴타입을 취득한다
     * @param menuUrl 메뉴URL
     * @return String 메뉴아이디
     */
    public static String getMenuType(String menuUrl) {
    	String name = "";

    	if (!"".equals(menuUrl)) {
    	    MenuInfo menuInfo = (MenuInfo)uMenuList.get(menuUrl);
    	    name = menuInfo.getMenuType();
    	}
        return name;
    }
    
    /**
     * 메뉴URL의 수정권한여부를 취득한다
     * @param menuUrl 메뉴URL
     * @param userInfo 사용자정보
     * @return String 수정권한여부
     */
    public static String getMenuEditYn(String groupLevel, String menuUrl, User userInfo) {
    	String edit_yn = "";

    	if (!"".equals(groupLevel)) {
    		List<MenuInfo> arSub = (List<MenuInfo>)listProps.get(groupLevel);
    		for(int i=0; i<arSub.size(); i++) {
    			MenuInfo menuInfo = (MenuInfo)arSub.get(i);
    			if(menuUrl.equals(menuInfo.getUrl())) {
    				edit_yn = menuInfo.getEdit_yn();
    				break;
    			}
    		}

    	}
        return edit_yn;
    }
    
    /**
     * 메뉴URL의 메뉴제목을 취득한다
     * @param menuUrl 메뉴URL
     * @param userInfo 사용자정보
     * @return String 메뉴아이디
     */
    public static String getMenuTitle(String menuUrl, User userInfo) {
    	String name = "";

    	name = getMenuName(getMenuId(menuUrl), userInfo);
        return name;
    }
    
    
    /**
     * 메뉴해쉬데이터취득
     * @return Hashtable 메뉴정보데이터빈의 리스트
     */
    public static Hashtable getMenuMap() {
    	return hMenuList;
    }
}
