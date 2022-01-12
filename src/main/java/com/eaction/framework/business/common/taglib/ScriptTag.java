/*****************************************************************************
 * 프로그램명  : ScriptTag.java
 * 설     명  :메세지(스크립트메세지)표시태그라이브러리 설정
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.taglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;
/**
 * 언어별택스트표시태그라이브러리 설정
 * @author  eaction
 * @version 1.0
 */
public class ScriptTag extends TagSupport {
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/** 자바스크립트 경로를 설정한다 */
	private final static String PATH_SCRIPT = ConfigMng.getValue(IPropertyKey.PATH_SCRIPT);
	
	
	/** 스타일시트 경로를 설정한다 */
	private final static String PATH_CSS = ConfigMng.getValue(IPropertyKey.PATH_CSS);
	/** 이미지 경로를 설정한다 */
//	private final static String PATH_IMAGE = ConfigMng.getValue(IPropertyKey.PATH_IMG);
	
	/** 공통처리스크립트 */
	private String common = "";
	 
	
	/** 달력처리스크립트 */
	private String picker = "";
	
	/** jqGrid 스크립트추가 여부 */
	private String jqGrid = "";
	
	/** dataTables 스크립트추가 여부 */
	private String dataTables = "";
		
	/** 차트 스크립트 */
	private String chart = "";
		
	/** jstree */
	private String jsTree = "";
	
	/** idle-timer */
	private String idleTimer = "";
		
	/** jqplot */
	private String jqPlot = "";
    /** sha256 */
	private String sha256 = "";
	/** fullcalendar*/
	private String fullCalendar = "";
	/** signaturePad*/
	private String signaturePad = "";
	/** fabric*/
	private String fabric = "";
	
	private String jspdf = "";
	
	private String html2canvas = "";
	
	//2020-07-24 정세연 : bootstrap tree 추가
	/** bootstrap-tree */
	private String bootstraptree = "";
	/** bootstrap2-toggle */
	private String bootstraptoggle = "";
	
	/**
     * 시작 태그 처리
     * @return String 언어별택스트표시태그문자열
     */
	public int doStartTag() throws JspException {
		try {

			StringBuffer sbScript = new StringBuffer();	
			
			HttpSession session = (HttpSession)pageContext.getSession();
			User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);			

			//sbScript.append("<script type='text/javascript'>");        	
        	//sbScript.append(" var DOMAIN_NAME = \"");
        	//sbScript.append(ConfigMng.getValue(IPropertyKey.DOMAIN_NAME));
        	//sbScript.append("\"; ");
        	//sbScript.append(" var PATH_SCRIPT = \"");
        	//sbScript.append(ConfigMng.getValue(IPropertyKey.PATH_SCRIPT));
        	//sbScript.append("\"; ");
        	//sbScript.append("</script>");
        	
    		sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery-3.5.1.min.js' ></script>");
    		sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.easing.1.3.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.placeholder.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery-ui.min.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.dataTables.min.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/dropify.min.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/dropzone.min.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/swiper.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.bxslider.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.nice-select.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/script.js' async></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/common1.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/sha256.js' ></script>");
        	sbScript.append("		<script src='"+ PATH_SCRIPT +"/jquery.validate.min.js' ></script>");
        	sbScript.append("		<script src='https://www.google.com/recaptcha/api.js' ></script>");
        	sbScript.append("		<script type=\"text/javascript\"></script>");
            
			JspWriter out = pageContext.getOut();
			out.print(sbScript.toString());
		} catch (Exception ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}


	public String getJqGrid() {
		return StringUtil.nvl(jqGrid);
	}
	public void setJqGrid(String jqGrid) {
		this.jqGrid = jqGrid;
	}

	public String getCommon() {
		return StringUtil.nvl(common);
	}
	public void setCommon(String common) {
		this.common = common;
	}

	public String getChart() {
		return StringUtil.nvl(chart);
	}
	public void setChart(String chart) {
		this.chart = chart;
	}
	
	public void setJsTree(String jsTree) {
		this.jsTree = jsTree;
	}	
	public String getJsTree() {
		return StringUtil.nvl(this.jsTree);
	}	
	public void setIdleTimer(String idleTimer) {
		this.idleTimer = idleTimer;
	}	
	public String getIdleTimer() {
		return StringUtil.nvl(this.idleTimer);
	}
		
	public String getPicker() {
		return picker;
	}

	public void setPicker(String picker) {
		this.picker = picker;
	}
	
	public void setJqPlot(String jqPlot) {
		this.jqPlot = jqPlot;
	}	
	public String getJqPlot() {
		return StringUtil.nvl(this.jqPlot);
	}	
	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}	
	public String getSha256() {
		return StringUtil.nvl(this.sha256);
	}	
	public void setFullCalendar(String fullCalendar) {
		this.fullCalendar = fullCalendar;
	}	
	public String getFullCalendar() {
		return StringUtil.nvl(this.fullCalendar);
	}	
	public String getDataTables() {
		return StringUtil.nvl(dataTables);
	}
	public void setDataTables(String dataTables) {
		this.dataTables = dataTables;
	}
	public String getSignaturePad() {
		return StringUtil.nvl(signaturePad);
	}
	public void setSignaturePad(String signaturePad) {
		this.signaturePad = signaturePad;
	}
	public String getFabric() {
		return StringUtil.nvl(fabric);
	}
	public void setFabric(String fabric) {
		this.fabric = fabric;
	}
	public String getJspdf() {
		return StringUtil.nvl(jspdf);
	}
	public void setJspdf(String jspdf) {
		this.jspdf = jspdf;
	}
	public String getHtml2canvas() {
		return StringUtil.nvl(html2canvas);
	}
	public void setHtml2canvas(String html2canvas) {
		this.html2canvas = html2canvas;
	}
	/**
	 * bootstraptree 설정
	 * @param bootstraptree bootstraptree
	 */
	public void setBootstraptree(String bootstraptree) {
		this.bootstraptree = bootstraptree;
	}
	/**
	 * bootstraptree 취득
	 * @return bootstraptree bootstraptree
	 */
	public String getBootstraptree() {
		return StringUtil.nvl(this.bootstraptree);
	}
	
	/**
	 * bootstrap2-toggle 설정
	 * @param bootstraptoggle bootstrap2-toggle
	 */
	public void setBootstraptoggle(String bootstraptoggle) {
		this.bootstraptoggle = bootstraptoggle;
	}
	/**
	 * bootstrap2-toggle 취득
	 * @return bootstraptoggle bootstrap2-toggle
	 */
	public String getBootstraptoggle() {
		return StringUtil.nvl(this.bootstraptoggle);
	}

}
