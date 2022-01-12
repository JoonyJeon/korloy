/*****************************************************************************
 * 프로그램명  : CheckboxTag.java
 * 설     명  : Checkbox 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

/**
 * Checkbox 태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class CheckboxTag extends TagSupport {
	/** 셀렉트박스명칭프로퍼티 */
	private String name = "";
	/** 셀렉트박스ID프로퍼티 */
	private String id = "";
	/** 이벤트정의프로퍼티 */
	private String event = "";
	/** 스타일클래스프로퍼티지정 */
	private String cssId = "";
	/** 스타일SHEET프로퍼티 */
	private String style = "";
	/** 기타OPTION프로퍼티 */
	private String option = "";
	/** 초기값유무프로퍼티 */
	private String init = "";
	/** 초기값코드프로퍼티 */
	private String initCode = "";
	/** 초기값명칭프로퍼티 */
	private String initName = "";
	/** 선택값프로퍼티 */
	private String checked = "";
	/** 초기값존재위치프로퍼티 */
	private String place = "";
	/** 값들사이의구분자프로퍼티 */
	private String format = "";
	/** OPTION대상리스트프로퍼티 */
	private List list = null;
	/** 시스템코드 처리 객체에서 리스트를 취득할 경우는 취득할 그룹코드를 지정한다 */
	private String group = "";
	/** 라디오버튼 inline */
	private String inline = "";

    /**
     * 시작 태그 처리
     * @return String checkbox태그문자열
     */
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

		HttpSession session = (HttpSession)pageContext.getSession();
        String session_lang = (String)session.getAttribute(ConstKey.SESSION_LANG);
        if("".equals(StringUtil.nvl(session_lang))){
        	session_lang = ConfigMng.getLang((HttpServletRequest)pageContext.getRequest());
        }

        try {
        	StringBuffer sbCheckbox = new StringBuffer();
        	
        	if (ConstKey.KEY_YES.equals(init)) {
    			if (ConstKey.KEY_PLACE_BEFORE.equals(place)){
    				
    				//인라인 뷰
    	        	if (ConstKey.KEY_YES.equals(inline)) {
    	        		sbCheckbox.append("<label class=\"checkbox\">");        		
    	        	}else{
    	        		sbCheckbox.append("<div class=\"col\">");
    	        		sbCheckbox.append("<label class=\"checkbox\">");
    	        	}
    				
    	        	sbCheckbox.append("<input type=\"checkbox\" name=\"" );
    	        	sbCheckbox.append(name);
    	        	sbCheckbox.append("\" ");
    	    		if (!"".equals(id)){
    	    			sbCheckbox.append("id=\"");
    	    			sbCheckbox.append(id);
    	    		}
    	    		sbCheckbox.append("\" value=\"");
    	    		sbCheckbox.append(initCode);
    	    		sbCheckbox.append("\" ");
    				if(checked.equals(initCode) ){
    					sbCheckbox.append( "checked " );
    				}
    				sbCheckbox.append(getCommonString());
    				sbCheckbox.append("<i></i>" );
    				sbCheckbox.append(initName);
    				sbCheckbox.append("&nbsp;&nbsp;" );
    				sbCheckbox.append(format);
    				
    				//인라인 뷰
    	        	if (ConstKey.KEY_YES.equals(inline)) {
    	        		sbCheckbox.append("</label>");        		
    	        	}else{
    	        		sbCheckbox.append("</label>");
    	        		sbCheckbox.append("</div>");    	        		
    	        	}    				
    			}
    		}

        	if (list != null) {
	        	CodeInfo codeInfo = null;
	    		for( int i = 0; i < list.size() ; i++ ){

	    			codeInfo = (CodeInfo)list.get(i);

	    			//인라인 뷰
	            	if (ConstKey.KEY_YES.equals(inline)) {
	            		sbCheckbox.append("<label class=\"checkbox\">");        		
	            	}else{
	            		sbCheckbox.append("<div class=\"col\">");
    	        		sbCheckbox.append("<label class=\"checkbox\">");
	            	}
	    			
	            	sbCheckbox.append("<input type=\"checkbox\" name=\"" );
	            	sbCheckbox.append(name);
	            	sbCheckbox.append("\" ");
    	    		if (!"".equals(id)){
    	    			sbCheckbox.append("id=\"");
    	    			sbCheckbox.append(id);
    	    		}
    	    		sbCheckbox.append("\" value=\"");
    	    		sbCheckbox.append(codeInfo.getCode());
    	    		sbCheckbox.append("\" ");
	    			if(checked.equals(codeInfo.getCode()) ){
	    				sbCheckbox.append(" checked ");
	    			}
	    			sbCheckbox.append(getCommonString());
	    			sbCheckbox.append("<i></i>" );
					if(session_lang.equals("KOR")){
						sbCheckbox.append(codeInfo.getCodenm_k());
    				}else{
    					sbCheckbox.append(codeInfo.getCodenm_e());
    				}
	    			sbCheckbox.append("&nbsp;&nbsp;" );
	    			if (i < (list.size()-1)) {
	    				sbCheckbox.append(format);
	    			}
	    			//인라인 뷰
    	        	if (ConstKey.KEY_YES.equals(inline)) {
    	        		sbCheckbox.append("</label>");        		
    	        	}else{
    	        		sbCheckbox.append("</label>");
    	        		sbCheckbox.append("</div>");    	        		
    	        	}
	    		}
        	}

    		
            out.print(sbCheckbox.toString());

        } catch (IOException ex) {
        	throw new JspException(ex);
        }
        return SKIP_BODY;
    }

    /**
     * 공통으로 들어갈 문자열들을 취득한다
     * @return String 공통문자열
     */
    private String getCommonString() {
    	StringBuffer sbCheckbox = new StringBuffer();

    	sbCheckbox.append(event);
    	sbCheckbox.append(" ");
		if (!"".equals(cssId)){
			sbCheckbox.append("class=\"");
			sbCheckbox.append(cssId);
			sbCheckbox.append("\" ");
		}
		if (!"".equals(style)){
			sbCheckbox.append("style=\"");
			sbCheckbox.append(style);
			sbCheckbox.append("\" ");
		}
		sbCheckbox.append(option);
		sbCheckbox.append(">");

    	return sbCheckbox.toString();
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     * @param userInfo 유저정보데이터
     */
    private void setSystemGroupCode(User userInfo) {
        setList(CodeTableMng.getCodeList(group));
    }

    /**
     * 셀렉트박스명칭프로퍼티 취득
     * @return String 셀렉트박스명칭프로퍼티
     */
    public String getName() {
        return name;
    }
    /**
     * 셀렉트박스명칭프로퍼티 설정
     * @param str 셀렉트박스명칭프로퍼티
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
     * 이벤트정의프로퍼티 취득
     * @return String 이벤트정의프로퍼티
     */
    public String getEvent() {
        return event;
    }
    /**
     * 이벤트정의프로퍼티 설정
     * @param str 이벤트정의프로퍼티
     */
    public void setEvent(String str) {
    	event = str;
    }

    /**
     * 스타일클래스프로퍼티 취득
     * @return String 스타일클래스프로퍼티
     */
    public String getCssId() {
        return cssId;
    }
    /**
     * 스타일클래스프로퍼티 설정
     * @param str 스타일클래스프로퍼티
     */
    public void setCssId(String str) {
    	cssId = str;
    }

    /**
     * 스타일SHEET프로퍼티 취득
     * @return String 스타일SHEET프로퍼티
     */
    public String getStyle() {
        return style;
    }
    /**
     * 스타일SHEET프로퍼티 설정
     * @param str 스타일SHEET프로퍼티
     */
    public void setStyle(String str) {
    	style = str;
    }

    /**
     * 기타OPTION프로퍼티 취득
     * @return String 기타OPTION프로퍼티
     */
    public String getOption() {
        return option;
    }
    /**
     * 기타OPTION프로퍼티 설정
     * @param str 기타OPTION프로퍼티
     */
    public void setOption(String str) {
    	option = str;
    }

    /**
     * 초기값유무프로퍼티 취득
     * @return String 초기값유무프로퍼티
     */
    public String getInit() {
        return init;
    }
    /**
     * 초기값유무프로퍼티 설정
     * @param str 초기값유무프로퍼티
     */
    public void setInit(String str) {
    	init = str;
    }

    /**
     * 초기값코드프로퍼티 취득
     * @return String 초기값코드프로퍼티
     */
    public String getInitCode() {
        return initCode;
    }
    /**
     * 초기값코드프로퍼티 설정
     * @param str 초기값코드프로퍼티
     */
    public void setInitCode(String str) {
    	initCode = str;
    }

    /**
     * 초기값명칭프로퍼티 취득
     * @return String 초기값명칭프로퍼티
     */
    public String getInitName() {
        return initName;
    }
    /**
     * 초기값명칭프로퍼티 설정
     * @param str 초기값명칭프로퍼티
     */
    public void setInitName(String str) {
    	initName = str;
    }

    /**
     * 선택값프로퍼티 취득
     * @return String 선택값프로퍼티
     */
    public String getChecked() {
        return checked;
    }
    /**
     * 선택값프로퍼티 설정
     * @param str 선택값프로퍼티
     */
    public void setChecked(String str) {
    	checked = str;
    }

    /**
     * 초기값존재위치프로퍼티 취득
     * @return String 초기값존재위치프로퍼티
     */
    public String getPlace() {
        return place;
    }
    /**
     * 초기값존재위치프로퍼티 설정
     * @param str 초기값존재위치프로퍼티
     */
    public void setPlace(String str) {
    	place = str;
    }

    /**
     * 값들사이의구분자프로퍼티 취득
     * @return String 값들사이의구분자프로퍼티
     */
    public String getFormat() {
        return format;
    }
    /**
     * 값들사이의구분자프로퍼티 설정
     * @param str 값들사이의구분자프로퍼티
     */
    public void setFormat(String str) {
    	format = str;
    }

    /**
     * OPTION대상리스트프로퍼티 취득
     * @return List OPTION대상리스트프로퍼티
     */
    public List getList() {
        return list;
    }
    /**
     * OPTION대상리스트프로퍼티 설정
     * @param obj OPTION대상리스트프로퍼티
     */
    public void setList(List obj) {
    	list = obj;
    }

    /**
     * 시스템그룹코드값프로퍼티 취득
     * @return String 시스템그룹코드값프로퍼티
     */
    public String getGroup() {
        return group;
    }
    /**
     * 시스템그룹코드값프로퍼티 설정
     * @param str 시스템그룹코드값프로퍼티
     */
    public void setGroup(String str) {
    	group = str;
    }
    /**
	 * 라디오버튼 inline 설정
	 * @param inline 라디오버튼 inline
	 */
	public void setInline(String inline) {
		this.inline = inline;
	}
	/**
	 * 라디오버튼 inline 취득
	 * @return inline 라디오버튼 inline
	 */
	public String getInline() {
		return StringUtil.nvl(this.inline);
	}
}
