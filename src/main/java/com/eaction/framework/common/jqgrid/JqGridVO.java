/*****************************************************************************
 * 프로그램명  : JqGridInfo.java
 * 설     명  : JqGrid DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author      Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/

package com.eaction.framework.common.jqgrid;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JqGridInfo
 * @author eaction
 * @version 1.0
 */
public class JqGridVO extends EactReqInfo{
	/** 요청페이지 */
	private int jqPage = 1;
	/** 한페이지에 보여줄 rows 수 */
	private int jqRows = 100;
	/** 정렬 대상 컬럼값 */
	private String jqSidx = "";
	/** 정렬기준(오름차순/내림차순) */
	private String jqSord = "";
	/** 검색파라미터 전체 */
	private JSONObject jqFilters = null;
	/** 검색그룹 (AND/OR)*/
	private String jqGroupOp = "";
	/** 검색필드(rules) */
	private JSONArray jqRules = null;
	/** 검색필드(rules Size) */
	private int jqRuleSize = 0;
	/** 검색필드(컬럼명) */
	private String[] jqField = null;
	/** 검색Operation(like, equil..) */
	private String[] jqOp = null;
	/** 검색Value */
	private String[] jqData = null;
	/** FCS read count */
	private int fcsReadCount = 1;
//	/** 검색 타임존 */
//	private String srch_tz = "";
	/** 초기값 포함 여부 */
	private String is_init = "";
	
	//20190415 JQGrid용 파라메터 처리 수정 - S
	/** JQGrid 검색 Filter */
	private String filters = "";
	/** JQGrid 검색 정렬 컬럼명 */
	private String sidx = "";
	/** JQGrid 검색 정렬순 (asc/desc)*/
	private String sord = "";
	/** JQGrid 검색 페이지 */
	private String page = "";
	/** JQGrid 검색 페이지 당 표시행 */
	private String rows = "";
	/** JQGrid 검색 Op */
	private String groupOp = "";

	/**
	 * JQGrid 검색 Filter 설정
	 * @param filters JQGrid 검색 Filter
	 */	
	final public void setFilters(String filters) {
		this.filters = filters;
		if(this.filters != null) {
			filters = filters.replace("%22", "\"");
			filters = filters.replace("%27", "\'");
		}
		this.setJqFilters(StringUtil.nvl(filters, ""));
		if(this.getJqFilters() != null && !this.getJqFilters().equals("") && this.getJqFilters().size()>0){
			this.setJqGroupOp(jqFilters.getString("groupOp"));
			this.setJqRules(jqFilters.getJSONArray("rules"));
			this.setJqRuleSize(this.getJqRules().size());
			//rule사이즈가 1보다 크면 jqField, jqOp, jqData를 배열로 재선언한다.
			if(this.getJqRuleSize()>0){
				jqField = new String[this.getJqRuleSize()];
				jqOp = new String[this.getJqRuleSize()];
				jqData = new String[this.getJqRuleSize()];
			}
			for(int i=0;i<this.getJqRuleSize();i++){
				JSONObject jo = jqRules.getJSONObject(i);
				this.setJqField(i, jo.getString("field"));
				this.setJqOp(i, jo.getString("op"));
				this.setJqData(i, jo.getString("data"));
			}			
		}		
	}
	
	/**
	 * JQGrid 검색 정렬 컬럼명 설정
	 * @param sidx JQGrid 검색 정렬 컬럼명
	 */	
	final public void setSidx(String sidx) {
		this.sidx = sidx;
		String jqSidxs = StringUtil.nvlOrder(this.sidx,"");
		if(jqSidxs != null) {
			jqSidxs = jqSidxs.replace("%22", "\"");
			jqSidxs = jqSidxs.replace("%27", "\'");
		}
		this.setJqSidx(jqSidxs);		
	}
	
	/**
	 * JQGrid 검색 정렬순 설정
	 * @param sord JQGrid 검색 정렬순
	 */	
	final public void setSord(String sord) {
		this.sord = sord;
		this.setJqSord(StringUtil.nvl(this.sord, ""));
	}
	
	/**
	 * JQGrid 검색 페이지 설정
	 * @param page JQGrid 검색 페이지
	 */	
	final public void setPage(String page) {
		this.page = page;
		this.setJqPage(Integer.parseInt(StringUtil.nvl(this.page, "1")));
	}
	
	/**
	 * JQGrid 검색 페이지 당 표시행 설정
	 * @param rows JQGrid 검색 페이지 당 표시행
	 */	
	final public void setRows(String rows) {
		this.rows = rows;
		this.setJqRows(Integer.parseInt(StringUtil.nvl(this.rows, "100")));		
	}
	
	/**
	 * JQGrid 검색 Op 설정
	 * @param groupOp JQGrid 검색 Op
	 */	
	final public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
		this.setJqGroupOp(StringUtil.nvl(this.groupOp, ""));
	}
	//20190415 JQGrid용 파라메터 처리 수정 - E

		
//	public JqGridVO doJqGridBind(HttpServletRequest request){
//		JqGridVO jqGridInfo = new JqGridVO();
//		String filters = request.getParameter("filters");
//		if(filters != null) {
//			filters = filters.replace("%22", "\"");
//			filters = filters.replace("%27", "\'");
//		}
////		if(filters != null)
////			try {
////				filters = new String(filters.getBytes("8859_1"), "UTF-8");
////			} catch (UnsupportedEncodingException e) {
////				// TODO Auto-generated catch block
////			}
//		
//		//String jqSidxs = StringUtil.nvlOrder(request.getParameter("sidx"), "");
//		String jqSidxs = StringUtil.nvlOrder(request.getParameter("sidx"),"");
//		if(jqSidxs != null) {
//			jqSidxs = jqSidxs.replace("%22", "\"");
//			jqSidxs = jqSidxs.replace("%27", "\'");
//		}
//		
//		this.setJqPage(Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1")));
//		this.setJqRows(Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "100")));
//		this.setJqSidx(jqSidxs);
//		this.setJqSord(StringUtil.nvl(request.getParameter("sord"), ""));
//		this.setJqGroupOp(StringUtil.nvl(request.getParameter("groupOp"), ""));
//		this.setJqFilters(StringUtil.nvl(filters, ""));
//		if(this.getJqFilters() != null && !this.getJqFilters().equals("") && this.getJqFilters().size()>0){
//			this.setJqGroupOp(jqFilters.getString("groupOp"));
//			this.setJqRules(jqFilters.getJSONArray("rules"));
//			this.setJqRuleSize(this.getJqRules().size());
//			//rule사이즈가 1보다 크면 jqField, jqOp, jqData를 배열로 재선언한다.
//			if(this.getJqRuleSize()>0){
//				jqField = new String[this.getJqRuleSize()];
//				jqOp = new String[this.getJqRuleSize()];
//				jqData = new String[this.getJqRuleSize()];
//			}
//			for(int i=0;i<this.getJqRuleSize();i++){
//				JSONObject jo = jqRules.getJSONObject(i);
//				this.setJqField(i, jo.getString("field"));
//				this.setJqOp(i, jo.getString("op"));
//				this.setJqData(i, jo.getString("data"));
//			}			
//		}
//		User userInfo = AuthorityUtil.getUserInfo(request);
//		//this.setSrch_tz(userInfo.getTime_zone());
//		
//		return jqGridInfo;
//	}
	
	/** limit 쿼리는 한페이지 5개인경우 0,5 & 5,5 뒷자리는 고정으로 알고 있으나 기존 BSS에서는 뒷자리가 변함
	 * 일단 뒷자리는 한 페이지에서 보여주는 것으로 고정함 
	 * 2015-12-29 KYM
	 * */
	final public int getStartPageIndex(){
		
		// Oracle rownum 인경우 between 11 and 20 
		//return ((this.getJqPage() - 1) * this.getJqRows()) + 1;
		
		// Limit 쓰는 MySQL 의 경우 Limit 10, 10 이면 11번째 부터 10개를 취득하기 위한 처리
		return ( (this.getJqPage() - 1) * this.getJqRows() + 1) - 1;
						
		/*end = String.valueOf( Integer.parseInt(start)  + Integer.parseInt(rows)) ;*/		
	}
	
	final public int getEndPageIndex(){
		// Oracle rownum 인경우 between 11 and 20 인경우 11에서 부터 20까지를 취득 
		return this.getJqRows() * this.getJqPage();
		
		// Limit 쓰는 MySQL의 경우 Limit 10, 10 이면 (뒤의 10인 10개를 취득)
		//return this.getJqRows() ;
	}
	

	/**
	 * 초기값 포함 여부 설정
	 * @param is_init 초기값 포함 여부
	 */
	final public void setIs_init(String is_init) {
		this.is_init = is_init;
	}
	/**
	 * 초기값 포함 여부 취득
	 * @return is_init 초기값 포함 여부
	 */
	final public String getIs_init() {
		return StringUtil.nvl(this.is_init);
	}
//	/**
//	 * 검색 타임존 설정
//	 * @param srch_tz 검색 타임존
//	 */
//	public void setSrch_tz(String srch_tz) {
//		this.srch_tz = srch_tz;
//	}
//	/**
//	 * 검색 타임존 취득
//	 * @return srch_tz 검색 타임존
//	 */
//	public String getSrch_tz() {
//		return StringUtil.nvl(this.srch_tz);
//	}
	/**
	 * @return the jqSidx
	 */
	final public String getJqSidx() {
		return jqSidx;
	}
	/**
	 * @param jqSidx the jqSidx to set
	 */
	final public void setJqSidx(String jqSidx) {
		this.jqSidx = jqSidx;
	}
	/**
	 * @return the jqSord
	 */
	final public String getJqSord() {
		return StringUtil.nvl(jqSord);
	}
	/**
	 * @param jqSord the jqSord to set
	 */
	final public void setJqSord(String jqSord) {
		this.jqSord = jqSord;
	}
	/**
	 * @return the jqGroupOp
	 */
	final public String getJqGroupOp() {
		return StringUtil.nvl(jqGroupOp);
	}
	/**
	 * @param jqGroupOp the jqGroupOp to set
	 */
	final public void setJqGroupOp(String jqGroupOp) {
		this.jqGroupOp = jqGroupOp;
	}
	/**
	 * @return the jqField
	 */
	final public String getJqField(int index) {
		return StringUtil.nvl(jqField[index]);
	}
	/**
	 * @param jqField the jqField to set
	 */
	final public void setJqField(int index, String jqField) {
		this.jqField[index] = jqField;
	}
	/**
	 * @return the jqOp
	 */
	final public String getJqOp(int index) {
		return StringUtil.nvl(jqOp[index]);
	}
	/**
	 * @param jqOp the jqOp to set
	 */
	final public void setJqOp(int index, String jqOp) {
		this.jqOp[index] = jqOp;
	}
	/**
	 * @return the jqData
	 */
	final public String getJqData(int index) {
		return StringUtil.nvl(jqData[index]);
	}
	/**
	 * @param jqData the jqData to set
	 */
	final public void setJqData(int index, String jqData) {
		this.jqData[index] = jqData;
	}


	/**
	 * @return the jqPage
	 */
	final public int getJqPage() {
		return jqPage;
	}


	/**
	 * @param jqPage the jqPage to set
	 */
	final public void setJqPage(int jqPage) {
		this.jqPage = jqPage;
	}


	/**
	 * @return the jqRows
	 */
	final public int getJqRows() {
		return jqRows;
	}


	/**
	 * @param jqRows the jqRows to set
	 */
	final public void setJqRows(int jqRows) {
		this.jqRows = jqRows;
	}


	/**
	 * @return the jqFilters
	 */
	final public JSONObject getJqFilters() {
		return jqFilters;
	}


	/**
	 * @param jqFilters the jqFilters to set
	 */
	final public void setJqFilters(String jqFilters) {
		jqFilters = StringUtil.nvl(jqFilters);
		if(jqFilters.indexOf("{") < 0){
			jqFilters = "{" + jqFilters + "}"; 
		}
		this.jqFilters = JSONObject.fromObject(jqFilters);
	}


	/**
	 * @return the jqRules
	 */
	final public JSONArray getJqRules() {
		return jqRules;
	}


	/**
	 * @param jqRules the jqRules to set
	 */
	final public void setJqRules(JSONArray jqRules) {
		this.jqRules = jqRules;
	}
	
	/**
	 * @return the jqRules
	 */
	final public int getJqRuleSize() {
		return jqRuleSize;
	}


	/**
	 * @param jqRules the jqRules to set
	 */
	final public void setJqRuleSize(int jqRuleSize) {
		this.jqRuleSize = jqRuleSize;
	}

	final public int getFcsReadCount() {
		return fcsReadCount;
	}

	final public void setFcsReadCount(int fcsReadCount) {
		this.fcsReadCount = fcsReadCount;
	}

	
	
}