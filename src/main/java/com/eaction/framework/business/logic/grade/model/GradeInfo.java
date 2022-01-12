/*****************************************************************************
 * 프로그램명  : GradeInfo.java
 * 설     명  : 재종가이드 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.08.02   SJY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.grade.model;

import java.util.Arrays;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("GradeInfo")
public class GradeInfo extends EactReqInfo {
	
	/** coat */
	private String coat = "";
	/** grade */
	private String grade = "";
	/** grade_kor */
	private String grade_kor = "";
	/** grade_iso */
	private String grade_iso = "";
	/** iso_p */
	private String iso_p = "";
	/** iso_m */
	private String iso_m = "";
	/** iso_k */
	private String iso_k = "";
	/** iso_n */
	private String iso_n = "";
	/** iso_s */
	private String iso_s = "";
	/** iso_h */
	private String iso_h = "";
	/** iso_o */
	private String iso_o = "";
	
	/** Main Application Code */
	private String ma_cd = "";
	/** Main Application Name */
	private String ma_nm = "";
	/** Tipped tool code(Indexable, Solid, Brazed) */
	private String tips_cd = "";
	/** Tipped tool name(Indexable, Solid, Brazed) */
	private String tips_cd_nm = "";
	/** Grade Classification */
	private String grade_clas = "";
	/** Coating Layer */
	private String coat_lay = "";
	/** Substrate(기질) */
	private String subst = "";
	/** ISO Range */
	private String iso_rag = "";
	
	/** Substrate 검색 배열 */
	private String[] subst_arr = null;
	/** Main Application 검색 배열 */
	private String[] ma_cd_arr = null;
	/** Grade Classification 검색 배열 */
	private String[] grade_clas_arr = null;
	/** iso_rag 검색 배열 */
	private String[] iso_rag_arr = null;
	/** 국내/해외고객 판별 위한 국가 */
	private String nation ="";
	/** 재종 설명 */
	private String grade_desc ="";
	
	/**
	 * Main Application Code 설정
	 * @param ma_cd Main Application Code
	 */
	public void setMa_cd(String ma_cd) {
		this.ma_cd = ma_cd;
	}
	/**
	 * Main Application Code 취득
	 * @return ma_cd Main Application Code
	 */
	public String getMa_cd() {
		return StringUtil.nvl(this.ma_cd);
	}

	/**
	 * Main Application Name 설정
	 * @param ma_nn Main Application Name
	 */
	public void setMa_nm(String ma_nm) {
		this.ma_nm = ma_nm;
	}
	/**
	 * Main Application Name 취득
	 * @return ma_nn Main Application Name
	 */
	public String getMa_nm() {
		return StringUtil.nvl(this.ma_nm);
	}

	/**
	 * Tipped tool code(Indexable, Solid, Brazed) 설정
	 * @param tips_cd Tipped tool code(Indexable, Solid, Brazed)
	 */
	public void setTips_cd(String tips_cd) {
		this.tips_cd = tips_cd;
	}
	/**
	 * Tipped tool code(Indexable, Solid, Brazed) 취득
	 * @return tips_cd Tipped tool code(Indexable, Solid, Brazed)
	 */
	public String getTips_cd() {
		return StringUtil.nvl(this.tips_cd);
	}

	/**
	 * Tipped tool name(Indexable, Solid, Brazed) 설정
	 * @param tips_cd_nm Tipped tool name(Indexable, Solid, Brazed)
	 */
	public void setTips_cd_nm(String tips_cd_nm) {
		this.tips_cd_nm = tips_cd_nm;
	}
	/**
	 * Tipped tool name(Indexable, Solid, Brazed) 취득
	 * @return tips_cd_nm Tipped tool name(Indexable, Solid, Brazed)
	 */
	public String getTips_cd_nm() {
		return StringUtil.nvl(this.tips_cd_nm);
	}

	/**
	 * Grade Classification 설정
	 * @param grade_clas Grade Classification
	 */
	public void setGrade_clas(String grade_clas) {
		this.grade_clas = grade_clas;
	}
	/**
	 * Grade Classification 취득
	 * @return grade_clas Grade Classification
	 */
	public String getGrade_clas() {
		return StringUtil.nvl(this.grade_clas);
	}

	/**
	 * Coating Layer 설정
	 * @param coat_lay Coating Layer
	 */
	public void setCoat_lay(String coat_lay) {
		this.coat_lay = coat_lay;
	}
	/**
	 * Coating Layer 취득
	 * @return coat_lay Coating Layer
	 */
	public String getCoat_lay() {
		return StringUtil.nvl(this.coat_lay);
	}

	/**
	 * Substrate(기질) 설정
	 * @param subst Substrate(기질)
	 */
	public void setSubst(String subst) {
		this.subst = subst;
	}
	/**
	 * Substrate(기질) 취득
	 * @return subst Substrate(기질)
	 */
	public String getSubst() {
		return StringUtil.nvl(this.subst);
	}

	/**
	 * ISO Range 설정
	 * @param iso_rag ISO Range
	 */
	public void setIso_rag(String iso_rag) {
		this.iso_rag = iso_rag;
	}
	/**
	 * ISO Range 취득
	 * @return iso_rag ISO Range
	 */
	public String getIso_rag() {
		return StringUtil.nvl(this.iso_rag);
	}

	/**
	 * Substrate 검색 배열 설정
	 * @param subst_arr Substrate 검색 배열
	 */
	public void setSubst_arr(String[] subst_arr) {
		if(subst_arr != null){
			this.subst_arr = Arrays.copyOf(subst_arr, subst_arr.length);
		}
	}
	/**
	 * Substrate 검색 배열 취득
	 * @return subst_arr Substrate 검색 배열
	 */
	public String[] getSubst_arr() {
		if(this.subst_arr != null){
			return Arrays.copyOf(this.subst_arr, this.subst_arr.length);
		} else {
			return null;
		}
	}


	/**
	 * Main Application 검색 배열 설정
	 * @param ma_cd_arr Main Application 검색 배열
	 */
	public void setMa_cd_arr(String[] ma_cd_arr) {
		if(ma_cd_arr != null){
			this.ma_cd_arr = Arrays.copyOf(ma_cd_arr, ma_cd_arr.length);
		}
	}
	/**
	 * Main Application 검색 배열 취득
	 * @return ma_cd_arr Main Application 검색 배열
	 */
	public String[] getMa_cd_arr() {
		if(this.ma_cd_arr != null){
			return Arrays.copyOf(this.ma_cd_arr, this.ma_cd_arr.length);
		} else {
			return null;
		}
	}

	/**
	 * Grade Classification 검색 배열 설정
	 * @param grade_clas_arr Grade Classification 검색 배열
	 */
	public void setGrade_clas_arr(String[] grade_clas_arr) {
		if(grade_clas_arr != null){
			this.grade_clas_arr = Arrays.copyOf(grade_clas_arr, grade_clas_arr.length);
		}
	}
	/**
	 * Grade Classification 검색 배열 취득
	 * @return grade_clas_arr Grade Classification 검색 배열
	 */
	public String[] getGrade_clas_arr() {
		if(this.grade_clas_arr != null){
			return Arrays.copyOf(this.grade_clas_arr, this.grade_clas_arr.length);
		} else {
			return null;
		}
	}
	


	/**
	 * iso_grade 검색 배열 취득
	 * @return grade_clas_arr Grade Classification 검색 배열
	 */
	public String[] getIso_rag_arr() {
		return iso_rag_arr;
	}
	/**
	 * iso_grade 검색 배열 설정
	 * @param grade_clas_arr Grade Classification 검색 배열
	 */
	public void setIso_rag_arr(String[] iso_rag_arr) {
		this.iso_rag_arr = iso_rag_arr;
	}
	/**
	 * coat 설정
	 * @param coat coat
	 */
	public void setCoat(String coat) {
		this.coat = coat;
	}
	/**
	 * coat 취득
	 * @return coat coat
	 */
	public String getCoat() {
		return StringUtil.nvl(this.coat);
	}

	/**
	 * grade_kor 설정
	 * @param grade_kor grade_kor
	 */
	public void setGrade_kor(String grade_kor) {
		this.grade_kor = grade_kor;
	}
	/**
	 * grade_kor 취득
	 * @return grade_kor grade_kor
	 */
	public String getGrade_kor() {
		return StringUtil.nvl(this.grade_kor);
	}

	/**
	 * grade_iso 설정
	 * @param grade_iso grade_iso
	 */
	public void setGrade_iso(String grade_iso) {
		this.grade_iso = grade_iso;
	}
	/**
	 * grade_iso 취득
	 * @return grade_iso grade_iso
	 */
	public String getGrade_iso() {
		return StringUtil.nvl(this.grade_iso);
	}

	/**
	 * iso_p 설정
	 * @param iso_p iso_p
	 */
	public void setIso_p(String iso_p) {
		this.iso_p = iso_p;
	}
	/**
	 * iso_p 취득
	 * @return iso_p iso_p
	 */
	public String getIso_p() {
		return StringUtil.nvl(this.iso_p);
	}

	/**
	 * grade 취득
	 * @return grade grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * grade 설정
	 * @param grade grade
	 */
	public void setGrade(String grade) {
		this.grade = StringUtil.nvl(grade);
	}
	/**
	 * iso_m 취득
	 * @return iso_m iso_m
	 */
	public String getIso_m() {
		return StringUtil.nvl(iso_m);
	}
	/**
	 * iso_m 설정
	 * @param iso_m iso_m
	 */
	public void setIso_m(String iso_m) {
		this.iso_m = iso_m;
	}
	/**
	 * iso_k 취득
	 * @return iso_k iso_k
	 */
	public String getIso_k() {
		return StringUtil.nvl(iso_k);
	}
	/**
	 * iso_k 설정
	 * @param iso_k iso_k
	 */
	public void setIso_k(String iso_k) {
		this.iso_k = iso_k;
	}
	/**
	 * iso_n 취득
	 * @return iso_n iso_n
	 */
	public String getIso_n() {
		return StringUtil.nvl(iso_n);
	}
	/**
	 * iso_n 설정
	 * @param iso_n iso_n
	 */
	public void setIso_n(String iso_n) {
		this.iso_n = iso_n;
	}
	/**
	 * iso_s 취득
	 * @return iso_s iso_s
	 */
	public String getIso_s() {
		return StringUtil.nvl(iso_s);
	}
	/**
	 * iso_s 설정
	 * @param iso_s iso_s
	 */
	public void setIso_s(String iso_s) {
		this.iso_s = iso_s;
	}
	/**
	 * iso_h 취득
	 * @return iso_h iso_h
	 */
	public String getIso_h() {
		return StringUtil.nvl(iso_h);
	}
	/**
	 * iso_h 설정
	 * @param iso_h iso_h
	 */
	public void setIso_h(String iso_h) {
		this.iso_h = iso_h;
	}
	/**
	 * iso_o 취득
	 * @return iso_o iso_o
	 */
	public String getIso_o() {
		return StringUtil.nvl(iso_o);
	}
	/**
	 * iso_o 설정
	 * @param iso_o iso_o
	 */
	public void setIso_o(String iso_o) {
		this.iso_o = iso_o;
	}
	/**
	 * nation 취득
	 * @return nation nation
	 */
	public String getNation() {
		return StringUtil.nvl(nation);
	}
	/**
	 * nation 설정
	 * @param nation nation
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * grade_desc 취득
	 * @return grade_desc grade_desc
	 */
	public String getGrade_desc() {
		return grade_desc;
	}
	/**
	 * grade_desc 설정
	 * @param grade_desc grade_desc
	 */
	public void setGrade_desc(String grade_desc) {
		this.grade_desc = grade_desc;
	}
	
	
}
