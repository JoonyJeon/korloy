/*****************************************************************************
 * 프로그램명  : ButtonTag.java
 * 설     명  : 권한별 버튼 표시여부태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.05.09  HSI      1.0    초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.common.model.User;



/**
 * 권한별 버튼 표시여부태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class ButtonTag extends TagSupport {
	
	/** 객체이름 */
	private String name = "";
	/** 객체 아이디  **/
	private String id = "";
	/** 버튼표시이름 */
	private String title = "";
	/** 클릭이벤트 처리 */
	private String onClick = "";
	/** 이벤트 처리 */
	private String event = "";
	/** 스타일시트 클래스*/
	private String cssId = "";
	/** 스타일정의 */
	private String style = "";
	/** 메뉴URL */
	private String menuUrl = "";
	/** 보이기 허용 유저그룹레벨 */
	private String userGrpLev = "";
	/** 사용가능 여부 */
	private boolean enable = true;
	/** 표시 여부 */
	private boolean visible = true;

	
	/**
     * 시작 태그 처리
     * @return String 권한별 버튼 표시여부태그문자열
     */
	public int doStartTag() throws JspException {
		try {

			StringBuffer sbAccess = new StringBuffer();
			sbAccess.append(getButton(enable));

			JspWriter out = pageContext.getOut();
			out.print(sbAccess.toString());
		} catch (Exception ex) {
			throw new JspException(ex);
		}

		return EVAL_BODY_INCLUDE;
	}

	/**
	 * 버튼 표시 문자열을 취득한다
	 * (버튼으로 되어있으나 경우에 따라서 이미지나 택스트로 바뀔수 있다(화면디자인에 맞춤)
	 * @param bEnable 사용가능여부
	 * @return String 출력메세지
	 */
	private String getButton(boolean bEnable) {
		StringBuffer sbButton = new StringBuffer();
		
		HttpSession session = (HttpSession)pageContext.getSession();
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		
		// 유저그룹레벨에 따라 보이기 허용
		if(!"".equals(menuUrl)) {
			String editYn = MenuMng.getMenuEditYn(userInfo.getGroupLevelCod(), menuUrl, userInfo);
			if(!"Y".equals(editYn)) {
				visible = false;
			} else {
				visible = true;
			}
		}
		
		// 보이기 허용 유저그룹레벨
		if(!"".equals(userGrpLev)) {
			visible = false;
			String[] userGrpLevs = userGrpLev.split(",");
			for(int i=0; i<userGrpLevs.length; i++) {
				if(userInfo.getGroupLevelCod().equals(userGrpLevs[i])) {
					visible = true;
					break;
				} 
			}
		}
				
		
		sbButton.append("<button type=\"button\" ");
		if (!"".equals(name)) {
			sbButton.append("name=\"");
			sbButton.append(name);
			sbButton.append("\" ");
		}
		if (!"".equals(id)) {
			sbButton.append("id=\"");
			sbButton.append(id);
			sbButton.append("\" ");
		}
		if (!"".equals(cssId)){
			sbButton.append("class=\"");
			sbButton.append(cssId);
			sbButton.append("\" ");
		}
		
		if (visible) {
			if (!"".equals(style)) {
				sbButton.append("style=\"");
				sbButton.append(style);
				sbButton.append("\" ");
			}
		} else {
			sbButton.append("style=\"");
			sbButton.append("display:none");
			sbButton.append("\" ");
		}
		
		if (!"".equals(onClick)) {
			sbButton.append("onclick=\"javascript:");
			sbButton.append(onClick);
			sbButton.append("\" ");
			
		}
		
		if (!"".equals(event)) {
			sbButton.append(event);
			sbButton.append(" ");
		}
		if (!bEnable) {
			sbButton.append("disabled ");
		}
		
		
		sbButton.append(">");
		
		
		if (!"".equals(title)) {
			sbButton.append(title);
		}
		
		//sbButton.append("</button>");
		
		return sbButton.toString();	
		
	}

	
    /**
     * 종료 태그 처리
     */
    public int doEndTag() throws JspException{
        JspWriter out = pageContext.getOut();

        try {
        	StringBuffer sbHtml = new StringBuffer();
        	
        	sbHtml.append("</button>");

            out.print(sbHtml.toString());

        } catch (IOException ex) {
        	throw new JspException(ex);
        }
    	return EVAL_PAGE;
    }


	/**
     * 객체이름프로퍼티 취득
     * @return String 객체이름프로퍼티
     */
    public String getName() {
        return name;
    }
    /**
     * 객체이름프로퍼티 설정
     * @param str 객체이름프로퍼티
     */
    public void setName(String str) {
    	name = str;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    /**
     * 버튼표시타이틀프로퍼티 취득
     * @return String 버튼표시타이틀프로퍼티
     */
    public String getTitle() {
        return title;
    }
    /**
     * 버튼표시타이틀프로퍼티 설정
     * @param str 버튼표시타이틀프로퍼티
     */
    public void setTitle(String str) {
    	title = str;
    }

    /**
     * 클릭이벤트프로퍼티 취득
     * @return String 클릭이벤트프로퍼티
     */
    public String getOnClick() {
        return onClick;
    }
    /**
     * 클릭이벤트프로퍼티 설정
     * @param str 클릭이벤트프로퍼티
     */
    public void setOnClick(String str) {
    	onClick = str;
    }

    /**
     * 이벤트프로퍼티 취득
     * @return String 이벤트프로퍼티
     */
    public String getEvent() {
        return event;
    }
    /**
     * 이벤트프로퍼티 설정
     * @param str 이벤트프로퍼티
     */
    public void setEvent(String str) {
    	event = str;
    }

    /**
     * 스타일시트클래스프로퍼티 취득
     * @return String 스타일시트클래스프로퍼티
     */
    public String getCssId() {
        return cssId;
    }
    /**
     * 스타일시트클래스프로퍼티 설정
     * @param str 스타일시트클래스프로퍼티
     */
    public void setCssId(String str) {
    	cssId = str;
    }

	/**
     * 스타일정의프로퍼티 취득
     * @return String 스타일정의프로퍼티
     */
    public String getStyle() {
        return style;
    }
    /**
     * 스타일정의프로퍼티 설정
     * @param str 스타일정의프로퍼티
     */
    public void setStyle(String str) {
    	style = str;
    }

    /**
     * 사용가능여부프로퍼티 취득
     * @return String 사용가능여부프로퍼티
     */
    public boolean getEnable() {
        return enable;
    }
    /**
     * 사용가능여부프로퍼티 설정
     * @param str 사용가능여부프로퍼티
     */
    public void setEnable(boolean str) {
    	enable = str;
    }

    /**
     * 표시가능여부프로퍼티 취득
     * @return String 표시가능여부프로퍼티
     */
    public boolean getVisible() {
        return visible;
    }
    /**
     * 표시가능여부프로퍼티 설정
     * @param str 표시가능여부프로퍼티
     */
    public void setVisible(boolean str) {
    	visible = str;
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
	
	/**
	 * 보이기 허용 유저그룹레벨 설정
	 * @param menuUrl 보이기 허용 유저그룹레벨
	 */
	public void setUserGrpLev(String userGrpLev) {
		this.userGrpLev = userGrpLev;
	}
	/**
	 * 보이기 허용 유저그룹레벨 취득
	 * @return userGrpLev 보이기 허용 유저그룹레벨
	 */
	public String getUserGrpLev() {
		return this.userGrpLev;
	}
	
}
