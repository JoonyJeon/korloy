/*****************************************************************************
 * 프로그램명  : User.java
 * 설     명  : 유저 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.util.StringUtil;

/**
 * 유저 데이터 빈
 * @author eaction
 * @version 1.0
 */
public class EactReqInfo implements java.io.Serializable {
	
	/** 사용자언어(브라우저) */
	private String user_lang = "";
	/** time zone(브라우저) */
	private String time_zone = "";
	/** 로그인사용자id */
	private String login_user_id;
	/** 로그인회사id */
	private String login_company_id = "";
		
	/** 등록일자 */
	private String dts_insert = "";
	/** 추가자 */
	private String id_insert = "";
	/** 수정일 */
	private String dts_update = "";
	/** 수정자 */
	private String id_update = "";
	
	/** 등록자 */
	private String crt_id = "";
	/** 등록자이름 */
	private String crt_id_nm = "";
	/** 수정자 */
	private String upd_id = "";
	/** 수정자이름 */
	private String upd_id_nm = "";
	/** 생성일시 */
	private String crt_dt = "";
	/** 수정일시 */
	private String upd_dt = "";
	
	/** 시작페이지번호 */
	private int startIndex = 0;
	/** 페이지개수 */
	private int pageCount = 0;
	/** 정렬순서 */
	private String orderSort = "";
	/** 페이징여부 */
	private String isPageYn = "";



	/**
	 * 페이징여부 설정
	 * @param isPageYn 페이징여부
	 */
	public void setIsPageYn(String isPageYn) {
		this.isPageYn = isPageYn;
	}
	/**
	 * 페이징여부 취득
	 * @return isPageYn 페이징여부
	 */
	public String getIsPageYn() {
		return StringUtil.nvl(this.isPageYn);
	}


	/**
	 * 시작페이지번호 설정
	 * @param startIndex 시작페이지번호
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * 시작페이지번호 취득
	 * @return startIndex 시작페이지번호
	 */
	public int getStartIndex() {
		return this.startIndex;
	}

	/**
	 * 페이지개수 설정
	 * @param pageCount 페이지개수
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	/**
	 * 페이지개수 취득
	 * @return pageCount 페이지개수
	 */
	public int getPageCount() {
		return this.pageCount;
	}

	/**
	 * 정렬순서 설정
	 * @param orderSort 정렬순서
	 */
	public void setOrderSort(String orderSort) {
		this.orderSort = orderSort;
	}
	/**
	 * 정렬순서 취득
	 * @return orderSort 정렬순서
	 */
	public String getOrderSort() {
		return StringUtil.nvl(this.orderSort);
	}
	
	/**
	 * 사용자언어(브라우저) 설정
	 * @param user_lang 사용자언어(브라우저)
	 */
	final public void setUser_lang(String user_lang) {
		this.user_lang = user_lang;		
	}
	/**
	 * 사용자언어(브라우저) 취득
	 * @return user_lang 사용자언어(브라우저)
	 */
	final public String getUser_lang() {
		return StringUtil.nvl(this.user_lang);
	}
	
	/**
	 * time zone(브라우저) 설정
	 * @param time_zone time zone(브라우저)
	 */
	final public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	/**
	 * time zone(브라우저) 취득
	 * @return time_zone time zone(브라우저)
	 */
	final public String getTime_zone() {
		return StringUtil.nvl(this.time_zone);
	}
	
	/**
	 * 사용자언어 소문자 2글자(브라우저) 취득
	 * @return cal_date_format 사용자언어 소문자 2글자
	 */
	final public String getUser_lang_two_char() {
		if("KOR".equals(this.user_lang)) {
			return "ko";
		} else if("JPN".equals(this.user_lang)) {
			return "jp";
		} else {
			return "en";
		}
	}
	
	
	/**
	 * 날짜포맷(DB Full) 취득
	 * @return full_date_format 날짜포맷(DB Full)
	 */
	final public String getFull_date_format() {
		if("KOR".equals(this.user_lang)) {
			return ConfigMng.getValue(IPropertyKey.FULL_DATE_FORMAT_KOR);
		} else {
			return ConfigMng.getValue(IPropertyKey.FULL_DATE_FORMAT_ENG);
		}
	}

	/**
	 * 날짜포맷(DB 년월일) 취득
	 * @return short_date_format 날짜포맷(DB 년월일)
	 */
	final public String getShort_date_format() {
		if("KOR".equals(this.user_lang)) {
			return ConfigMng.getValue(IPropertyKey.SHORT_DATE_FORMAT_KOR);
		} else {
			return ConfigMng.getValue(IPropertyKey.SHORT_DATE_FORMAT_ENG);
		}
	}

	/**
	 * 달력 날짜포맷(브라우저) 취득
	 * @return cal_date_format 달력 날짜포맷(브라우저)
	 */
	final public String getCal_date_format() {
		if("KOR".equals(this.user_lang)) {
			return ConfigMng.getValue(IPropertyKey.CAL_DATE_FORMAT_KOR);
		} else {
			return ConfigMng.getValue(IPropertyKey.CAL_DATE_FORMAT_ENG);
		}
	}
	
	/**
	 * JQGrid달력 날짜포맷(브라우저) 취득
	 * @return cal_date_format 달력 날짜포맷(브라우저)
	 */
	final public String getJqgrid_Cal_date_format() {
		if("KOR".equals(this.user_lang)) {
			return ConfigMng.getValue(IPropertyKey.JQGRID_CAL_DATE_FORMAT_KOR);
		} else {
			return ConfigMng.getValue(IPropertyKey.JQGRID_CAL_DATE_FORMAT_ENG);
		}
	}
		
	/**
	 * 로그인사용자id 설정
	 * @param login_user_id 로그인사용자id
	 */
	public void setLogin_user_id(String login_user_id) {
		this.login_user_id = login_user_id;
	}

	/**
	 * 로그인사용자id 취득
	 * @return login_user_id 로그인사용자id
	 */
	public String getLogin_user_id() {
		return StringUtil.nvl(this.login_user_id);
	}

	/**
	 * 회사id 설정
	 * @param login_company_id 회사id
	 */
	public void setLogin_company_id(String login_company_id) {
		this.login_company_id = login_company_id;
	}
	/**
	 * 회사id 취득
	 * @return login_company_id 회사id
	 */
	public String getLogin_company_id() {
		return StringUtil.nvl(this.login_company_id);
	}
	
	/**
	 * 등록일자 설정
	 * @param dts_insert 등록일자
	 */
	public void setDts_insert(String dts_insert) {
		this.dts_insert = dts_insert;
	}
	/**
	 * 등록일자 취득
	 * @return dts_insert 등록일자
	 */
	public String getDts_insert() {
		return StringUtil.nvl(this.dts_insert);
	}

	/**
	 * 추가자 설정
	 * @param id_insert 추가자
	 */
	public void setId_insert(String id_insert) {
		this.id_insert = id_insert;
	}
	/**
	 * 추가자 취득
	 * @return id_insert 추가자
	 */
	public String getId_insert() {
		return StringUtil.nvl(this.id_insert);
	}

	/**
	 * 수정일 설정
	 * @param dts_update 수정일
	 */
	public void setDts_update(String dts_update) {
		this.dts_update = dts_update;
	}
	/**
	 * 수정일 취득
	 * @return dts_update 수정일
	 */
	public String getDts_update() {
		return StringUtil.nvl(this.dts_update);
	}

	/**
	 * 수정자 설정
	 * @param id_update 수정자
	 */
	public void setId_update(String id_update) {
		this.id_update = id_update;
	}
	/**
	 * 수정자 취득
	 * @return id_update 수정자
	 */
	public String getId_update() {
		return StringUtil.nvl(this.id_update);
	}
	
	/**
	 * 등록자 설정
	 * @param crt_id 등록자
	 */
	final public void setCrt_id(String crt_id) {
		this.crt_id = crt_id;
	}
	/**
	 * 등록자 취득
	 * @return String 등록자
	 */
	final public String getCrt_id() {
		return StringUtil.nvl(this.crt_id, this.login_user_id);
	}

	/**
	 * 등록자이름 설정
	 * @param crt_id_nm 등록자이름
	 */
	final public void setCrt_id_nm(String crt_id_nm) {
		this.crt_id_nm = crt_id_nm;
	}
	/**
	 * 등록자이름 취득
	 * @return String 등록자이름
	 */
	final public String getCrt_id_nm() {
		return StringUtil.nvl(this.crt_id_nm, this.crt_id);
	}

	/**
	 * 등록시간 설정
	 * @param crt_dt 등록시간
	 */
	final public void setCrt_dt(String crt_dt) {
		this.crt_dt = crt_dt;
	}
	/**
	 * 등록시간 취득
	 * @return String 등록시간
	 */
	final public String getCrt_dt() {
		return StringUtil.nvl(this.crt_dt);
	}

	/**
	 * 수정자 설정
	 * @param upd_id 수정자
	 */
	final public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	/**
	 * 수정자 취득
	 * @return upd_id 수정자
	 */
	final public String getUpd_id() {
		return StringUtil.nvl(this.upd_id, this.login_user_id);
	}

	/**
	 * 수정자이름 설정
	 * @param upd_id_nm 수정자이름
	 */
	final public void setUpd_id_nm(String upd_id_nm) {
		this.upd_id_nm = upd_id_nm;
	}
	/**
	 * 수정자이름 취득
	 * @return upd_id_nm 수정자이름
	 */
	final public String getUpd_id_nm() {
		return StringUtil.nvl(this.upd_id_nm, this.upd_id);
	}

	/**
	 * 수정일시 설정
	 * @param upd_dt 수정일시
	 */
	final public void setUpd_dt(String upd_dt) {
		this.upd_dt = upd_dt;
	}
	/**
	 * 수정일시 취득
	 * @return upd_dt 수정일시
	 */
	final public String getUpd_dt() {
		return StringUtil.nvl(this.upd_dt);
	}	

}
