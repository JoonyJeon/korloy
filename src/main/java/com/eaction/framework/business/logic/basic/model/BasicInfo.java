/*****************************************************************************
 * 프로그램명  : BasicInfo.java
 * 설     명  : 기본데이터 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basic.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("basicInfo")
public class BasicInfo extends EactReqInfo {
	
	/** Select Value */
	private String id = "";
	/** Select Text */
	private String text = "";
	
	/** 회사코드 */
	private String cd_company = "";
	/** 사업장코드 */
	private String cd_bizarea = "";
	
	/** 휴일 */
	private String holiday = "";
	/** 날짜한도 */
	private String limit_dt = "";

	
	
	
	/**
	 * Select Value 설정
	 * @param id Select Value
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Select Value 취득
	 * @return id Select Value
	 */
	public String getId() {
		return StringUtil.nvl(this.id);
	}

	/**
	 * Select Text 설정
	 * @param text Select Text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Select Text 취득
	 * @return text Select Text
	 */
	public String getText() {
		return StringUtil.nvl(this.text);
	}
	
	/**
	 * 회사코드 설정
	 * @param cd_company 회사코드
	 */
	public void setCd_company(String cd_company) {
		this.cd_company = cd_company;
	}
	/**
	 * 회사코드 취득
	 * @return cd_company 회사코드
	 */
	public String getCd_company() {
		return StringUtil.nvl(this.cd_company);
	}

	/**
	 * 사업장코드 설정
	 * @param cd_bizarea 사업장코드
	 */
	public void setCd_bizarea(String cd_bizarea) {
		this.cd_bizarea = cd_bizarea;
	}
	/**
	 * 사업장코드 취득
	 * @return cd_bizarea 사업장코드
	 */
	public String getCd_bizarea() {
		return StringUtil.nvl(this.cd_bizarea);
	}
	
	/**
	 * 휴일 설정
	 * @param holiday 휴일
	 */
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	/**
	 * 휴일 취득
	 * @return holiday 휴일
	 */
	public String getHoliday() {
		return StringUtil.nvl(this.holiday);
	}

	/**
	 * 날짜한도 설정
	 * @param limit_dt 날짜한도
	 */
	public void setLimit_dt(String limit_dt) {
		this.limit_dt = limit_dt;
	}
	/**
	 * 날짜한도 취득
	 * @return limit_dt 날짜한도
	 */
	public String getLimit_dt() {
		return StringUtil.nvl(this.limit_dt);
	}
	
}
