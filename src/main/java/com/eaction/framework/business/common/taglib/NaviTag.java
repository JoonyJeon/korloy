/*****************************************************************************
 * 프로그램명  : NaviTag.java
 * 설     명  : 네비게이션 표시 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.MenuInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

/**
 * 왼쪽보드메뉴표시태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class NaviTag extends TagSupport {
	/** 이미지 경로를 설정한다 */
	private final static String PATH_IMAGE = ConfigMng.getValue(IPropertyKey.PATH_IMG);
	/** 검색대상 게시판 메뉴 아이디 */
	private String pageId = "";
	/** EXCEL에서 사용하는  navi 여부 */
	private String excel = "";
	/** 고유 id  value */
	private String id = "";
	/** 팝업 navi 여부 */
	private String popYn = "";
	/** 메뉴URL */
	private String menuUrl = "";



	

	/**
     * 시작 태그 처리
     * @return String 권한별표시여부태그문자열
     */
	public int doStartTag() throws JspException {
		try {
			HttpSession session = (HttpSession)pageContext.getSession();
			User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
			
			StringBuffer sbBoardMenu = new StringBuffer();
			String menuId 		= "";
			String pageNm 	= "";			
			//menu_url로 검색
			menuId = MenuMng.getMenuId(menuUrl);
			pageNm = StringUtil.nvl(MenuMng.getMenuName(menuId, userInfo));
			
			sbBoardMenu.append("<li>Home</li>");
						
			if (!"".equals(StringUtil.nvl(menuId)) && !popYn.equals("Y")) {
				//페이지의 메뉴명칭과 상위메뉴들을 구성한다
			    sbBoardMenu.append(getNavigationName(menuId, menuId, pageNm, 0, userInfo));
			}else{
				sbBoardMenu.append(getNavigationName2());
			}
			sbBoardMenu.append("<li>");
			/******************마지막 화면 이름*************************/
			sbBoardMenu.append(pageNm.substring(0,pageNm.lastIndexOf(">")+1));
			
			//화면타이틀 추가
			sbBoardMenu.append(pageNm.substring(pageNm.lastIndexOf(">")+1));			
			/******************마지막 화면 이름*************************/
			sbBoardMenu.append("</li>");
			JspWriter out = pageContext.getOut();
			out.print(sbBoardMenu.toString());
		} catch (Exception ex) {
			throw new JspException(ex);
		}
		return SKIP_BODY;
	}
	/**
     * 최종 메뉴명 직전 카테고리까지만 취득
     * @return String 권한별표시여부태그문자열
     */
	private String getNavigationName(String menuId, String me, String pageNm, int idx, User userInfo) {
		StringBuffer name = new StringBuffer();
		try {
			Hashtable arMenu = MenuMng.getMenuMap();
			MenuInfo menuInfo = (MenuInfo)arMenu.get(menuId);
			String upMenuId = menuInfo.getUpMenuId();
			if(userInfo.getUser_lang().equals("KOR")){
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmKor());
    	    }else if(userInfo.getUser_lang().equals("ENG")){
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmEng());
    	    }else{
    	    	menuInfo.setMenuNm(menuInfo.getMenuNmJpn());
    	    }
					
			if (!"".equals(StringUtil.nvl(upMenuId)) && !"-1".equals(StringUtil.nvl(upMenuId))) {
				int nextIdx = idx + 1;
				String tmpNavtName = getNavigationName(upMenuId, me, pageNm, nextIdx, userInfo);
				if(idx==0){
					name.append(tmpNavtName);
				}else{
					name.append(tmpNavtName);
					name.append("<li>");					
					name.append(menuInfo.getMenuNm());
					name.append("&nbsp;");
					name.append("</li>");					
				}
			} else {				
			}
			
		} catch (Exception ex) {

		}
		return name.toString();

	}
	private String getNavigationName2() {
		StringBuffer name = new StringBuffer();
		try {
				name.append("<img src=\"");
				name.append(PATH_IMAGE);
				name.append("/icon_home.gif\" align=\"absmiddle\">&nbsp;");
	//			name.append(pageNm);
	//			name.append("&gt;");
		} catch (Exception ex) {

		}
		return name.toString();

	}

	/**
     * 페이지아이디프로퍼티 취득
     * @return String 페이지아이디프로퍼티
     */
    public String getPageId() {
        return pageId;
    }
    /**
     * 페이지아이디프로퍼티 설정
     * @param str 페이지아이디프로퍼티
     */
    public void setPageId(String str) {
    	pageId = str;
    }
    /**
     * 페이지아이디프로퍼티 취득
     * @return String 페이지아이디프로퍼티
     */
    public String getExcel() {
        return excel;
    }
    /**
     * 페이지아이디프로퍼티 설정
     * @param str 페이지아이디프로퍼티
     */
    public void setExcel(String str) {
    	excel = str;
    }
    /**
     * 페이지아이디프로퍼티 취득
     * @return String 페이지아이디프로퍼티
     */
    public String getId() {
        return id;
    }
    /**
     * 페이지아이디프로퍼티 설정
     * @param str 페이지아이디프로퍼티
     */
    public void setId(String str) {
    	id = str;
    }
    /**
     * 페이지아이디프로퍼티 취득
     * @return String 페이지아이디프로퍼티
     */
    public String getPopYn() {
        return popYn;
    }
    /**
     * 페이지아이디프로퍼티 설정
     * @param str 페이지아이디프로퍼티
     */
    public void setPopYn(String str) {
    	popYn = str;
    }
    /**
	 * 메뉴URL 설정
	 * @param menuUrl 메뉴URL
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * 메뉴URL 취득
	 * @return menuUrl 메뉴URL
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}
}
