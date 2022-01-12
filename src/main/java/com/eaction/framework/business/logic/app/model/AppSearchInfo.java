/*****************************************************************************
 * 프로그램명  : AppSearchInfo.java
 * 설     명  : Main/Sub/Item 검색 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.jqgrid.DatatablesVO;
import com.eaction.framework.common.util.StringUtil;


@Alias("AppSearchInfo")
public class AppSearchInfo extends DatatablesVO {
	
	/** MainApp코드 */
	private String search_ma_cd = "";
	/** MainApp명 */
	private String search_ma_nm = "";
	/** 메인이미지 */
	private String search_main_img = "";
	/** 아이콘이미지 */
	private String search_icon_img = "";
	/** 하위아이템 개수 */
	private String search_sub_cnt = "";
	/** SubApp코드 */
	private String search_sa_cd = "";
	/** SubApp명 */
	private String search_sa_nm = "";
	/** 아이템그룹코드 */
	private String search_ig_cd = "";
	/** 아이템그룹명 */
	private String search_ig_nm = "";
	/** 자재번호 */
	private String search_matnr = "";
	/** 검색 속성 */
	private String[] search_prop_symbol = null;
	/** SubApplication Code */
	private String[] search_ar_sa_cd = null;
	/** 아이템그룹Array */
	private String[] ig_cd_arr = null;
	/** 검색 어셈블리번호 */
	private String search_assem_no = "";
	/** 자재번호 리스트 */
	private String matnr_list = "";
	/** 자재번호 Array */
	private String[] matnr_arr = null;
	/** 검색_프로퍼티그룹코드 */
	private String search_prop_grp_cd = "";
	/** 검색_유닛코드 */
	private String search_unit_cd = "";


	/**
	 * 검색_유닛코드 설정
	 * @param search_prop_grp_cd 검색_프로퍼티그룹코드
	 */
	public void setSearch_unit_cd(String search_unit_cd) {
		this.search_unit_cd = search_unit_cd;
	}
	/**
	 * 검색_유닛코드 취득
	 * @return search_prop_grp_cd 검색_프로퍼티그룹코드
	 */
	public String getSearch_unit_cd() {
		return StringUtil.nvl(this.search_unit_cd);
	}

	/**
	 * 검색_프로퍼티그룹코드 설정
	 * @param search_prop_grp_cd 검색_프로퍼티그룹코드
	 */
	public void setSearch_prop_grp_cd(String search_prop_grp_cd) {
		this.search_prop_grp_cd = search_prop_grp_cd;
	}
	/**
	 * 검색_프로퍼티그룹코드 취득
	 * @return search_prop_grp_cd 검색_프로퍼티그룹코드
	 */
	public String getSearch_prop_grp_cd() {
		return StringUtil.nvl(this.search_prop_grp_cd);
	}
	

	/**
	 * 검색 어셈블리번호 설정
	 * @param search_assem_no 검색 어셈블리번호
	 */
	public void setSearch_assem_no(String search_assem_no) {
		this.search_assem_no = search_assem_no;
	}
	/**
	 * 검색 어셈블리번호 취득
	 * @return search_assem_no 검색 어셈블리번호
	 */
	public String getSearch_assem_no() {
		return StringUtil.nvl(this.search_assem_no);
	}	

	/**
	 * 아이템그룹Array 설정
	 * @param ig_cd_arr 아이템그룹Array
	 */
	public void setIg_cd_arr(String[] ig_cd_arr) {
		if(ig_cd_arr != null){
			this.ig_cd_arr = Arrays.copyOf(ig_cd_arr, ig_cd_arr.length);
		}
	}
	/**
	 * 아이템그룹Array 취득
	 * @return ig_cd_arr 아이템그룹Array
	 */
	public String[] getIg_cd_arr() {
		if(this.ig_cd_arr != null){
			return Arrays.copyOf(this.ig_cd_arr, this.ig_cd_arr.length);
		} else {
			return null;
		}
	}


	/**
	 * SubApplication Code 설정
	 * @param search_ar_sa_cd SubApplication Code
	 */
	public void setSearch_ar_sa_cd(String[] search_ar_sa_cd) {
		if(search_ar_sa_cd != null){
			this.search_ar_sa_cd = Arrays.copyOf(search_ar_sa_cd, search_ar_sa_cd.length);
		}
	}
	/**
	 * SubApplication Code 취득
	 * @return search_ar_sa_cd SubApplication Code
	 */
	public String[] getSearch_ar_sa_cd() {
		if(this.search_ar_sa_cd != null){
			return Arrays.copyOf(this.search_ar_sa_cd, this.search_ar_sa_cd.length);
		} else {
			return null;
		}
	}

	/**
	 * MainApp코드 설정
	 * @param search_ma_cd MainApp코드
	 */
	public void setSearch_ma_cd(String search_ma_cd) {
		this.search_ma_cd = search_ma_cd;
	}
	/**
	 * MainApp코드 취득
	 * @return search_ma_cd MainApp코드
	 */
	public String getSearch_ma_cd() {
		return StringUtil.nvl(this.search_ma_cd);
	}

	/**
	 * MainApp명 설정
	 * @param search_ma_nm MainApp명
	 */
	public void setSearch_ma_nm(String search_ma_nm) {
		this.search_ma_nm = search_ma_nm;
	}
	/**
	 * MainApp명 취득
	 * @return search_ma_nm MainApp명
	 */
	public String getSearch_ma_nm() {
		return StringUtil.nvl(this.search_ma_nm);
	}

	/**
	 * 메인이미지 설정
	 * @param search_main_img 메인이미지
	 */
	public void setSearch_main_img(String search_main_img) {
		this.search_main_img = search_main_img;
	}
	/**
	 * 메인이미지 취득
	 * @return search_main_img 메인이미지
	 */
	public String getSearch_main_img() {
		return StringUtil.nvl(this.search_main_img);
	}

	/**
	 * 아이콘이미지 설정
	 * @param search_icon_img 아이콘이미지
	 */
	public void setSearch_icon_img(String search_icon_img) {
		this.search_icon_img = search_icon_img;
	}
	/**
	 * 아이콘이미지 취득
	 * @return search_icon_img 아이콘이미지
	 */
	public String getSearch_icon_img() {
		return StringUtil.nvl(this.search_icon_img);
	}

	/**
	 * 하위아이템 개수 설정
	 * @param search_sub_cnt 하위아이템 개수
	 */
	public void setSearch_sub_cnt(String search_sub_cnt) {
		this.search_sub_cnt = search_sub_cnt;
	}
	/**
	 * 하위아이템 개수 취득
	 * @return search_sub_cnt 하위아이템 개수
	 */
	public String getSearch_sub_cnt() {
		return StringUtil.nvl(this.search_sub_cnt);
	}

	/**
	 * SubApp코드 설정
	 * @param search_sa_cd SubApp코드
	 */
	public void setSearch_sa_cd(String search_sa_cd) {
		this.search_sa_cd = search_sa_cd;
	}
	/**
	 * SubApp코드 취득
	 * @return search_sa_cd SubApp코드
	 */
	public String getSearch_sa_cd() {
		return StringUtil.nvl(this.search_sa_cd);
	}

	/**
	 * SubApp명 설정
	 * @param search_sa_nm SubApp명
	 */
	public void setSearch_sa_nm(String search_sa_nm) {
		this.search_sa_nm = search_sa_nm;
	}
	/**
	 * SubApp명 취득
	 * @return search_sa_nm SubApp명
	 */
	public String getSearch_sa_nm() {
		return StringUtil.nvl(this.search_sa_nm);
	}
	
	/**
	 * 아이템그룹코드 설정
	 * @param search_ig_cd 아이템그룹코드
	 */
	public void setSearch_ig_cd(String search_ig_cd) {
		this.search_ig_cd = search_ig_cd;
	}
	/**
	 * 아이템그룹코드 취득
	 * @return search_ig_cd 아이템그룹코드
	 */
	public String getSearch_ig_cd() {
		return StringUtil.nvl(this.search_ig_cd);
	}

	/**
	 * 아이템그룹명 설정
	 * @param search_ig_nm 아이템그룹명
	 */
	public void setSearch_ig_nm(String search_ig_nm) {
		this.search_ig_nm = search_ig_nm;
	}
	/**
	 * 아이템그룹명 취득
	 * @return search_ig_nm 아이템그룹명
	 */
	public String getSearch_ig_nm() {
		return StringUtil.nvl(this.search_ig_nm);
	}
	
	/**
	 * 자재번호 설정
	 * @param search_matnr 자재번호
	 */
	public void setSearch_matnr(String search_matnr) {
		this.search_matnr = search_matnr;
	}
	/**
	 * 자재번호 취득
	 * @return search_matnr 자재번호
	 */
	public String getSearch_matnr() {
		return StringUtil.nvl(this.search_matnr);
	}
	
	/**
	 * 검색 속성 설정
	 * @param search_prop_symbol 검색 속성
	 */
	public void setSearch_prop_symbol(String[] search_prop_symbol) {
		if(search_prop_symbol != null){
			this.search_prop_symbol = Arrays.copyOf(search_prop_symbol, search_prop_symbol.length);
		}
	}
	/**
	 * 검색 속성 취득
	 * @return search_prop_symbol 검색 속성
	 */
	public String[] getSearch_prop_symbol() {
		if(this.search_prop_symbol != null){
			return Arrays.copyOf(this.search_prop_symbol, this.search_prop_symbol.length);
		} else {
			return null;
		}
	}
	
	/**
	 * 자재번호 리스트 설정
	 * @param matnr_list 자재번호 리스트
	 */
	public void setMatnr_list(String matnr_list) {
		this.matnr_list = matnr_list;
	}
	/**
	 * 자재번호 리스트 취득
	 * @return matnr_list 자재번호 리스트
	 */
	public String getMatnr_list() {
		return StringUtil.nvl(this.matnr_list);
	}
	
	/**
	 * 자재번호 Array 설정
	 * @param matnr_arr 자재번호 Array
	 */
	public void setMatnr_arr(String[] matnr_arr) {
		if(matnr_arr != null){
			this.matnr_arr = Arrays.copyOf(matnr_arr, matnr_arr.length);
		}
	}
	/**
	 * 자재번호 Array 취득
	 * @return matnr_arr 자재번호 Array
	 */
	public String[] getMatnr_arr() {
		if(this.matnr_arr != null){
			return Arrays.copyOf(this.matnr_arr, this.matnr_arr.length);
		} else {
			return null;
		}
	}
	
}
