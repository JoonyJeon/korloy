/*****************************************************************************
 * 프로그램명  : DownloadTag.java
 * 설     명  : 다운로드태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;

/**
 * 다운로드태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class DownloadTag extends TagSupport {
	/** 첨부파일명칭프로퍼티 */
	private String file = "";
	/** 첨부파일기본경로 */
	private String path = "";
	/** 첨부파일서브경로 */
	private String subPath = "";

	/**
     * 시작 태그 처리
     * @return String 언어별택스트표시태그문자열
     */
	public int doStartTag() throws JspException {
		try {

			StringBuffer sbDownload = new StringBuffer();

			if (!"".equals(file)) {
				sbDownload.append("ㆍ<a href=\"#\" onClick=\"downloadFile(downloadForm, '");
			    sbDownload.append(file);
			    sbDownload.append("');\">");
			    sbDownload.append(file);
			    sbDownload.append("</a>");
			} else {
				sbDownload.append("<form name=\"downloadForm\" method=\"post\" action=\"");
				sbDownload.append(ConfigMng.getValue(IPropertyKey.ROOT_PATH));
				sbDownload.append(ConstUrl.FILE_DOWNLOAD_URL_BOARD);
				sbDownload.append("\" class=\"nomargin\">");
				sbDownload.append("  <input type=\"hidden\" name=\"keyPath\" value=\"");
				if (!"".equals(path)) {
					sbDownload.append(path);
				} else {
					sbDownload.append(IPropertyKey.ROOT_FILE_PATH);
				}
				sbDownload.append("\">");
				sbDownload.append("  <input type=\"hidden\" name=\"pathSub\" value=\"");
				if (!"".equals(subPath)){
					sbDownload.append(subPath);
				}
				sbDownload.append("\">");
				sbDownload.append("  <input type=\"hidden\" name=\"fileName\" value=\"\">");
				sbDownload.append("</form>");
			}

			JspWriter out = pageContext.getOut();

			out.print(sbDownload.toString());
		} catch (Exception ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}

	/**
     * 첨부파일명칭프로퍼티 취득
     * @return String 첨부파일명칭프로퍼티
     */
    public String getFile() {
        return file;
    }
    /**
     * 첨부파일명칭프로퍼티 설정
     * @param str 첨부파일명칭프로퍼티
     */
    public void setFile(String str) {
    	file = str;
    }

    /**
     * 첨부파일기본경로 취득
     * @return String 첨부파일기본경로
     */
    public String getPath() {
        return path;
    }
    /**
     * 첨부파일기본경로 설정
     * @param str 첨부파일기본경로
     */
    public void setPath(String str) {
    	path = str;
    }

    /**
     * 첨부파일서브경로 취득
     * @return String 첨부파일서브경로
     */
    public String getSubPath() {
        return subPath;
    }
    /**
     * 첨부파일서브경로 설정
     * @param str 첨부파일서브경로
     */
    public void setSubPath(String str) {
    	subPath = str;
    }

}
