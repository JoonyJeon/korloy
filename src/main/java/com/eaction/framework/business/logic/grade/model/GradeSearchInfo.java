/*****************************************************************************
 * 프로그램명  : AppSearchInfo.java
 * 설     명  : Main/Sub/Item 검색 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.grade.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.jqgrid.DatatablesVO;
import com.eaction.framework.common.util.StringUtil;

//해당프로젝트에서는 searchInfo 사용하지 않음
@Alias("GradeSearchInfo") 
public class GradeSearchInfo extends DatatablesVO {
	
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
	
}
