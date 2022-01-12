/*****************************************************************************
 * 프로그램명  : SelectTag.java
 * 설     명  : Select 태그라이브러리 설정
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
import com.eaction.framework.business.common.code.UpCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

/**
 * Select 태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class SelectTag extends TagSupport {
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
	private String selected  = "";
	/** OPTION대상리스트프로퍼티 */
	private List list = null;
    /** 시스템코드 처리 객체에서 리스트를 취득할 경우는 취득할 그룹코드를 지정한다 */
	private String group = "";
	/** 시스템코드 처리 객체에서 상위코드가 있는 리스트를 취득할 경우는 취득할 상위코드를 지정한다 */
	private String upCode = "";
	/** 체크후 항목 타이틀 */
	private String fname = "";
	/** OPTION에 코드포함여부 */
	private String withCode = "";
	/** 보여주지않는코드를 ',' 구분자로 설정 */
	private String notInCodes = "";
	
    /**
     * 시작 태그 처리
     * @return String SELECT태그문자열
     */
    public int doStartTag() throws JspException{
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        String session_lang = ConfigMng.getLang(request);
        
        if(!"".equals(StringUtil.nvl(upCode))){
        	if (!"".equals(StringUtil.nvl(group))) {
    			setSystemUpGroupCode();
        	}
        }else{
	        if (!"".equals(StringUtil.nvl(group))) {
	    		setSystemGroupCode();
	    	}
        }

        try {
        	StringBuffer sbSelect = new StringBuffer();

        	sbSelect.append("<select name=\"" );
    		sbSelect.append(name);
    		sbSelect.append("\" ");

    		if (!"".equals(id)){
    			sbSelect.append("id=\"");
    		    sbSelect.append(id);
    		    sbSelect.append("\" ");
    		}
    		if (!"".equals(cssId)){
    			sbSelect.append("class=\"");
    		    sbSelect.append(cssId);
    		    sbSelect.append("\" ");
    		}
    		if (!"".equals(style)){
    			sbSelect.append("style=\"");
    		    sbSelect.append(style);
    		    sbSelect.append("\" ");
    		}
    		if (!"".equals(fname)){
    			sbSelect.append("fname=\"");
    		    sbSelect.append(fname);
    		    sbSelect.append("\" ");
    		}
    		if(ConstKey.KEY_YES.equals(init) ){
    			sbSelect.append("init=\"");
    		    sbSelect.append(ConstKey.KEY_YES);
    		    sbSelect.append("\" ");
    		}
    		sbSelect.append(option);
    		sbSelect.append(" ");
    		sbSelect.append(event);
    		sbSelect.append(" ");
    		sbSelect.append(">");

    		if(ConstKey.KEY_YES.equals(init) ){
    			sbSelect.append("<option value=\"");
    			sbSelect.append(initCode);
    			sbSelect.append("\">");
    			sbSelect.append(initName);
    			sbSelect.append("</option>");
    		}
    		if (list != null) {
    			CodeInfo codeInfo = null;
    			for( int i=0; i<list.size(); i++ ){

    				codeInfo = (CodeInfo)list.get(i);
    				//2020-10-07 정세연 : 콤보에서 제외하는 코드인지 확인
    				if(!"".equals(StringUtil.nvl(notInCodes)) && notInCodes.contains(codeInfo.getCode())) {
    					continue;
    				}
    				sbSelect.append("<option value=\"");
    				sbSelect.append(codeInfo.getCode());
    				sbSelect.append("\"");

    				if((StringUtil.nvl(selected)).equals(codeInfo.getCode())){
    					sbSelect.append( " selected " );
    				}

    				sbSelect.append(" >");
    				if(withCode.equals(ConstKey.KEY_YES)){
    					sbSelect.append("[");
    					sbSelect.append(codeInfo.getCode());
    					sbSelect.append("]");
    				}
					if(session_lang.equals("KOR")){
    					codeInfo.setName(codeInfo.getCodenm_k());
    				}else{
    					codeInfo.setName(codeInfo.getCodenm_e());
    				}
    				
    				sbSelect.append(codeInfo.getName());
    				sbSelect.append("</option>");
    			}
    		}
    		sbSelect.append("</select>");

            out.print(sbSelect.toString());

        } catch (IOException ex) {
        	throw new JspException(ex);
        }
        return SKIP_BODY;
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     * @param userInfo 유저정보데이터
     */
    private void setSystemGroupCode() {
        setList(CodeTableMng.getCodeList(group));
    }

    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     * @param userInfo 유저정보데이터
     */
    private void setSystemUpGroupCode() {
        setList(UpCodeTableMng.getCodeList(group, upCode));
    }
    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하여 설정한다
     * @param userInfo 유저정보데이터
     */
    public String getId() {
		return id;
	}
    /**
     * 시스템그룹코드정보에서 그룹별 리스트를 취득하 여설정한다
     * @param userInfo 유저정보데이터
     */
	public void setId(String id) {
		this.id = id;
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
    public String getSelected() {
        return selected;
    }
    /**
     * 선택값프로퍼티 설정
     * @param str 선택값프로퍼티
     */
    public void setSelected(String str) {
    	selected = str;
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
     * 상위코드값프로퍼티 취득
     * @return String 상위코드값프로퍼티
     */
    public String getUpCode() {
        return upCode;
    }
    /**
     * 상위코드값프로퍼티 설정
     * @param str 상위코드값프로퍼티
     */
    public void setUpCode(String str) {
    	upCode = str;
    }

    /**
     * 항목명칭값프로퍼티 취득
     * @return String 항목명칭값프로퍼티
     */
    public String getFname() {
        return fname;
    }
    /**
     * 항목명칭값프로퍼티 설정
     * @param str 항목명칭값프로퍼티
     */
    public void setFname(String str) {
    	fname = str;
    }

    /**
     * OPTION에 코드포함여부 프로퍼티 취득
     * @return String OPTION에 코드포함여부 프로퍼티
     */
    public String getWithCode() {
        return withCode;
    }
    /**
     * OPTION에 코드포함여부 프로퍼티 설정
     * @param str OPTION에 코드포함여부 프로퍼티
     */
    public void setWithCode(String str) {
    	withCode = str;
    }

	/**
	 * 보여주지않는코드를 ',' 구분자로 설정 설정
	 * @param notInCodes 보여주지않는코드를 ',' 구분자로 설정
	 */
	public void setNotInCodes(String notInCodes) {
		this.notInCodes = notInCodes;
	}
	/**
	 * 보여주지않는코드를 ',' 구분자로 설정 취득
	 * @return notInCodes 보여주지않는코드를 ',' 구분자로 설정
	 */
	public String getNotInCodes() {
		return StringUtil.nvl(this.notInCodes);
	}
}
