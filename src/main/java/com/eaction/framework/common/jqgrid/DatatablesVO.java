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

import java.util.List;
import java.util.Map;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


/**
 * JqGridInfo
 * @author eaction
 * @version 1.0
 */
public class DatatablesVO extends EactReqInfo{
	
	private int draw = 0;
	private int start = 0;
	private int length = 0;
	
    private Map<String, String> search;
    private List<Map<String, String>> order = null;
    String sortCol = "";
    String sortDir = "";
	
    public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public int getStart() {
		return start+1;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return start + length;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getSortColNum() {
		String sortColNum = "";
		if(order != null && order.size() > 0) {
			sortColNum = order.get(0).get("column");	
		}
		return sortColNum;
	}
	
	public String getSortDir() {
		String sortCol = "";
		if(order != null && order.size() > 0) {
			sortCol = order.get(0).get("dir");	
		}else{
			sortCol = this.sortDir;
		}
		return sortCol;
	}
	
	public void setSortDir(String sortDir){
		this.sortDir = sortDir;
	}
	
	
	public void setSearch(Map<String, String> search) {
		this.search = search;
	}
	public Map<String, String> getSearch() {
		return this.search;
	}

	public void setOrder(List<Map<String, String>> order) {
		this.order = order;
	}
	public List<Map<String, String>> getOrder() {
		return this.order;
	}
	

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public String getSortCol() {
		return StringUtil.nvl(this.sortCol);
	}

	
}