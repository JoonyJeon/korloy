/*****************************************************************************
 * 프로그램명  : ExcelInfo.java
 * 설     명  : Excel처리 데이터
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.model;

import com.eaction.framework.common.util.StringUtil;

public class ExcelInfo{
	private String title;
	private String dbColName;
	private double charByte=1.0;	//바이트수를 체크해서 엑셀다운로드시 셀넓이를 추가로 주기 위함..
	private int cellType;
	
	public String getTitle() {
		return StringUtil.nvl(title);
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDbColName() {
		return StringUtil.nvl(dbColName);
	}
	public void setDbColName(String dbColName) {
		this.dbColName = dbColName;
	}
	public double getCharByte() {
		return charByte;
	}
	public void setCharByte(double charByte) {
		this.charByte = charByte;
	}
	public int getCellType() {
		return cellType;
	}
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}
	
}