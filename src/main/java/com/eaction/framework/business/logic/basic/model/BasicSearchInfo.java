/*****************************************************************************
 * 프로그램명  : BasicSearchInfo.java
 * 설     명  : 기본데이터 리스트 검색 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basic.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.jqgrid.DatatablesVO;
import com.eaction.framework.common.util.StringUtil;


@Alias("basicSearchInfo")
public class BasicSearchInfo extends DatatablesVO {
	
	/** 검색 값 */
	private String search_val = "";
	/** 검색 회사코드 */
	private String search_cd_company = "";
	/** 검색 사업장코드 */
	private String search_cd_bizarea = "";
	/** 검색 공장코드 */
	private String search_cd_plant = "";
	/** 검색 파트너코드 */
	private String search_cd_partner = "";
	/** 검색 상품구분 */
	private String search_ord_tp = "";
	/** 검색 CAPA 구분 */
	private String search_fg_capa = "";
	/** 검색 CAPA 순번 */
	private String search_capa_num = "";
	/** 검색 날짜 */
	private String search_date = "";
	/** 검색 최소날짜 */
	private String search_min_date = "";
	/** 검색 최대날짜 */
	private String search_max_date = "";
	/** 검색 갯수 */
	private String search_row_num = "";
	/** 검색 영업사원번호 */
	private String search_cd_emp_sale = "";
	/** 검색 h_dept */
	private String search_h_dept = "";
	/** 검색 부서 */
	private String search_cd_dept = "";
	
	private String search_kind = "";
	
	/** 검색할 모든 사원번호 리스트 */
	private String search_cd_emp_agent = "";
	/** 검색할 모든 사원번호 리스트 */
	private List<String> list_emp_agent = new ArrayList<String>();

	/**
	 * 검색 값 설정
	 * @param search_val 검색 값
	 */
	public void setSearch_val(String search_val) {
		this.search_val = search_val;
	}
	/**
	 * 검색 값 취득
	 * @return search_val 검색 값
	 */
	public String getSearch_val() {
		return StringUtil.nvl(this.search_val);
	}
	
	/**
	 * 검색 회사코드 설정
	 * @param search_cd_company 검색 회사코드
	 */
	public void setSearch_cd_company(String search_cd_company) {
		this.search_cd_company = search_cd_company;
	}
	/**
	 * 검색 회사코드 취득
	 * @return search_cd_company 검색 회사코드
	 */
	public String getSearch_cd_company() {
		return StringUtil.nvl(this.search_cd_company);
	}

	/**
	 * 검색 사업장코드 설정
	 * @param search_cd_bizarea 검색 사업장코드
	 */
	public void setSearch_cd_bizarea(String search_cd_bizarea) {
		this.search_cd_bizarea = search_cd_bizarea;
	}
	/**
	 * 검색 사업장코드 취득
	 * @return search_cd_bizarea 검색 사업장코드
	 */
	public String getSearch_cd_bizarea() {
		return StringUtil.nvl(this.search_cd_bizarea);
	}
	
	/**
	 * 검색 공장코드 설정
	 * @param search_cd_plant 검색 공장코드
	 */
	public void setSearch_cd_plant(String search_cd_plant) {
		this.search_cd_plant = search_cd_plant;
	}
	/**
	 * 검색 공장코드 취득
	 * @return search_cd_plant 검색 공장코드
	 */
	public String getSearch_cd_plant() {
		return StringUtil.nvl(this.search_cd_plant);
	}
	
	/**
	 * 검색 파트너코드 설정
	 * @param search_cd_partner 검색 파트너코드
	 */
	public void setSearch_cd_partner(String search_cd_partner) {
		this.search_cd_partner = search_cd_partner;
	}
	/**
	 * 검색 파트너코드 취득
	 * @return search_cd_partner 검색 파트너코드
	 */
	public String getSearch_cd_partner() {
		return StringUtil.nvl(this.search_cd_partner);
	}

	/**
	 * 검색 상품구분 설정
	 * @param search_ord_tp 검색 상품구분
	 */
	public void setSearch_ord_tp(String search_ord_tp) {
		this.search_ord_tp = search_ord_tp;
	}
	/**
	 * 검색 상품구분 취득
	 * @return search_ord_tp 검색 상품구분
	 */
	public String getSearch_ord_tp() {
		return StringUtil.nvl(this.search_ord_tp);
	}
	
	/**
	 * 검색 CAPA 구분 설정
	 * @param search_fg_capa 검색 CAPA 구분
	 */
	public void setSearch_fg_capa(String search_fg_capa) {
		this.search_fg_capa = search_fg_capa;
	}
	/**
	 * 검색 CAPA 구분 취득
	 * @return search_fg_capa 검색 CAPA 구분
	 */
	public String getSearch_fg_capa() {
		return StringUtil.nvl(this.search_fg_capa);
	}
	
	/**
	 * 검색 CAPA 순번 설정
	 * @param search_capa_num 검색 CAPA 순번
	 */
	public void setSearch_capa_num(String search_capa_num) {
		this.search_capa_num = search_capa_num;
	}
	/**
	 * 검색 CAPA 순번 취득
	 * @return search_capa_num 검색 CAPA 순번
	 */
	public String getSearch_capa_num() {
		return StringUtil.nvl(this.search_capa_num);
	}
	
	/**
	 * 검색 날짜 설정
	 * @param search_date 검색 날짜
	 */
	public void setSearch_date(String search_date) {
		this.search_date = search_date;
	}
	/**
	 * 검색 날짜 취득
	 * @return search_date 검색 날짜
	 */
	public String getSearch_date() {
		return StringUtil.nvl(this.search_date);
	}
	
	/**
	 * 검색 최소날짜 설정
	 * @param search_min_date 검색 최소날짜
	 */
	public void setSearch_min_date(String search_min_date) {
		this.search_min_date = search_min_date;
	}
	/**
	 * 검색 최소날짜 취득
	 * @return search_min_date 검색 최소날짜
	 */
	public String getSearch_min_date() {
		return StringUtil.nvl(this.search_min_date);
	}

	/**
	 * 검색 최대날짜 설정
	 * @param search_max_date 검색 최대날짜
	 */
	public void setSearch_max_date(String search_max_date) {
		this.search_max_date = search_max_date;
	}
	/**
	 * 검색 최대날짜 취득
	 * @return search_max_date 검색 최대날짜
	 */
	public String getSearch_max_date() {
		return StringUtil.nvl(this.search_max_date);
	}
	
	/**
	 * 검색 갯수 설정
	 * @param search_row_num 검색 갯수
	 */
	public void setSearch_row_num(String search_row_num) {
		this.search_row_num = search_row_num;
	}
	/**
	 * 검색 갯수 취득
	 * @return search_row_num 검색 갯수
	 */
	public String getSearch_row_num() {
		return StringUtil.nvl(this.search_row_num);
	}

	/**
	 * 검색 영업사원번호 설정
	 * @param search_cd_emp_sale 검색 영업사원번호
	 */
	public void setSearch_cd_emp_sale(String search_cd_emp_sale) {
		this.search_cd_emp_sale = search_cd_emp_sale;
	}
	/**
	 * 검색 영업사원번호 취득
	 * @return search_cd_emp_sale 검색 영업사원번호
	 */
	public String getSearch_cd_emp_sale() {
		return StringUtil.nvl(this.search_cd_emp_sale);
	}
	
	/**
	 * 검색 h_dept 설정
	 * @param search_h_dept 검색 h_dept
	 */
	public void setSearch_h_dept(String search_h_dept) {
		this.search_h_dept = search_h_dept;
	}
	/**
	 * 검색 h_dept 취득
	 * @return search_h_dept 검색 h_dept
	 */
	public String getSearch_h_dept() {
		return StringUtil.nvl(this.search_h_dept);
	}
	
	/**
	 * 검색 search_cd_dept 설정
	 * @param search_cd_dept 검색 search_cd_dept
	 */
	public void setSearch_cd_dept(String search_cd_dept) {
		this.search_cd_dept = search_cd_dept;
	}
	/**
	 * 검색 search_cd_dept 취득
	 * @return search_cd_dept 검색 search_cd_dept
	 */
	public String getSearch_cd_dept() {
		return StringUtil.nvl(this.search_cd_dept);
	}
	
	
	/**
	 * 검색 search_kind 설정
	 * @param search_kind 검색 search_kind
	 */
	public void setSearch_kind(String search_kind) {
		this.search_kind = search_kind;
	}
	/**
	 * 검색 search_kind 취득
	 * @return search_kind 검색 search_kind
	 */
	public String getSearch_kind() {
		return StringUtil.nvl(this.search_kind);
	}
	
	/**
	 * 검색대상사원리스트 설정
	 * @param search_cd_emp_agent 검색대상사원리스트
	 */
	public void setSearch_cd_emp_agent(String search_cd_emp_agent) {
		this.search_cd_emp_agent = search_cd_emp_agent;
	}
	/**
	 * 검색대상사원리스트 취득
	 * @return search_cd_emp_agent 검색대상사원리스트
	 */
	public String getSearch_cd_emp_agent() {
		return this.search_cd_emp_agent;
	}
	
	/**
	 * 검색대상사원리스트 설정
	 * @param list_emp_agent 검색대상사원리스트
	 */
	public void setList_emp_agent(List<String> list_emp_agent) {
		this.list_emp_agent = list_emp_agent;
	}
	/**
	 * 검색대상사원리스트 취득
	 * @return list_emp_agent 검색대상사원리스트
	 */
	public List<String> getList_emp_agent() {
		return this.list_emp_agent;
	}
	
}
