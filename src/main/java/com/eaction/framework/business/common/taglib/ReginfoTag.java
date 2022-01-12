/*****************************************************************************
 * 프로그램명  : ReginfoTag.java
 * 설     명  : 등록정보 표시 태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.29  LHN    1.0     초기작성
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
 * 등록정보표시 설정
 * @author  eaction
 * @version 1.0
 */
public class ReginfoTag extends TagSupport {
	/** 사용자이름 */
	private String name = "";
	/** 표시날짜 */
	private String date = "";



	

	/**
     * 시작 태그 처리
     * @return String 권한별표시여부태그문자열
     */
	public int doStartTag() throws JspException {
		try {
			StringBuffer sbOut = new StringBuffer();
			
			sbOut.append("<spen>");
			sbOut.append("Registration : ");
			sbOut.append(this.date);
			sbOut.append(", ");
			sbOut.append(this.name);						
			sbOut.append("</spen>");
			JspWriter out = pageContext.getOut();
			out.print(sbOut.toString());

		} catch (Exception ex) {
			throw new JspException(ex);
		}
		return SKIP_BODY;
	}

    /**
     * 사용자이름프로퍼티 취득
     * @return String 사용자이름프로퍼티
     */
    public String getName() {
        return name;
    }
    /**
     * 사용자이름프로퍼티 설정
     * @param str 사용자이름프로퍼티
     */
    public void setName(String str) {
    	name = str;
    }
    
    /**
     * 표시날짜프로퍼티 취득
     * @return String 표시날짜프로퍼티
     */
    public String getDate() {
        return date;
    }
    /**
     * 표시날짜프로퍼티 설정
     * @param str 표시날짜프로퍼티
     */
    public void setDate(String str) {
    	date = str;
    }    


}
