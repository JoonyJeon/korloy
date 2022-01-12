/*****************************************************************************
 * 프로그램명  : HtmlTag.java
 * 설     명  : HTML 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;

/**
 * HTML 태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class HtmlTag extends TagSupport {
	
	/** 스타일시트 경로를 설정한다 */
	private final static String PATH_CSS = ConfigMng.getValue(IPropertyKey.PATH_CSS);
	/** Transitional head tag 추가 여부 설정한다 */
	private final static String DTD_TYPE = ConfigMng.getValue(IPropertyKey.DTD_TYPE);
	/** 이미지 경로를 설정한다 */
	private final static String PATH_IMAGE = ConfigMng.getValue(IPropertyKey.PATH_IMG);
	private final static String PATH_SCRIPT = ConfigMng.getValue(IPropertyKey.PATH_SCRIPT);
	/** HTML의TITLE태그값(특별한 이름이 들어가는 경우만 설정) */
	private String title = "KORLOY";
	
	/** 브라우저 렌더링버전정보 프로퍼티지정 */
	private String render = "";
	
	/** 로그인 화면은  html에 id 부여 해야함.   */
	private String login = "";
	
	    

	/**
     * 시작 태그 처리
     * @return String HTML태그문자열
     */
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
        	StringBuffer sbHtml = new StringBuffer();
        	sbHtml.append("<!DOCTYPE html>");
        	sbHtml.append("<html lang=\"ko\">");            
        	sbHtml.append("	<head>");
        	sbHtml.append("		<title>KORLOY Digital Catalog</title>");		
        	sbHtml.append("		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        	sbHtml.append("		<meta name=\"viewport\" content=\"initial-scale=1, width=device-width\"/>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>");
        	sbHtml.append("		<link rel='shortcut icon' type='image/x-icon' href='/favicon.ico'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/reset.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/style.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/jquery-ui.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/jquery.dataTables.min.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/dropify.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/swiper.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/nice-select.css'>");
        	sbHtml.append("		<link rel='stylesheet' type='text/css' href='" + PATH_CSS + "/smartadmin-production-plugins.min.css'>");
        	sbHtml.append("		<title>KORLOY</title>");
        	sbHtml.append("	</head>");
        	            
            out.print(sbHtml.toString());
        } catch (IOException ex) {
        	throw new JspException(ex);
        }
        return EVAL_BODY_INCLUDE;
    }

    /**
     * 종료 태그 처리
     */
    public int doEndTag() throws JspException{
        JspWriter out = pageContext.getOut();

        try {
        	StringBuffer sbHtml = new StringBuffer();
        	
        	sbHtml.append("</html>");

            out.print(sbHtml.toString());

        } catch (IOException ex) {
        	throw new JspException(ex);
        }
    	return SKIP_PAGE;
    }

    public void setTitle(String title){
    	this.title = title;
    }
    public String getTitle(){
    	return this.title;
    }
    

    public void setRender(String render){
    	this.render = render;
    }
    public String getRender(){
    	return this.render;
    }

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
   
}
