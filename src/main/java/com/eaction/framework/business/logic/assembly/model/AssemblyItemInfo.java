/*****************************************************************************
 * 프로그램명  : AssemblyItemInfo.java
 * 설     명  : AssemblyItemInfo 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 *2021.09.03   왕윤아    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("AssemblyItemInfo")
public class AssemblyItemInfo extends EactReqInfo {
	
	private static final long serialVersionUID = 1L;
	
	/*****************************************************
	 * GTC Hierarchy 구조
	 * : Assembly 순서 : 1 + 2 + 3
	 *****************************************************
	 * 1. Adaptive 	: CTL > ADP 
	 *****************************************************
	 * 2. Tool     	: CTL > TL
	 *****************************************************
	 *    Turning  	: CTL > TL > TRN 인경우
	 *    Solid    	: GTC_VD_ROOT값의 마지막 문자가 "S" 인경우 
	 *    Indexable	: GTC_VD_ROOT값의 마지막 문자가 "S" 인경우
	 *****************************************************
	 * 3. Cutting   : CTL > INS 
	 *****************************************************/
	
	/** 어셈블리 일련번호 */
	private String assem_no = "";
	/**  */
	private String assy_type = "";
	/** 자재번호 */
	private String matnr = "";
	/** 형번 */
	private String designation = "";
	/** 3D(Detail) */
	private String stp_detail = "";
	/** 3D(Basic) */
	private String stp_basic = "";
	/** overhang */
	private String overhang = "";
	/** location */
	private String location = "";
	/** p21 파일경로 */
	private String p21_file = "";
	
	/** 아이템그룹 코드 */
	private String ig_cd = "";
	
	/** 자재번호 배열 */
	private String[] matnr_arr = null;
	
	/** 아이템그룹Array */
	private String[] ig_cd_arr = null;
	
	/** SubApp코드 */
	private String sa_cd = "";


	
	/**
	 * 어셈블리 일련번호 설정
	 * @param assem_no 어셈블리 일련번호
	 */
	public void setAssem_no(String assem_no) {
		this.assem_no = assem_no;
	}
	/**
	 * 어셈블리 일련번호 취득
	 * @return assem_no 어셈블리 일련번호
	 */
	public String getAssem_no() {
		return StringUtil.nvl(this.assem_no);
	}
	
	/**
	 * 아이템그룹 코드 설정
	 * @param ig_cd 아이템그룹 코드
	 */
	public void setIg_cd(String ig_cd) {
		this.ig_cd = ig_cd;
	}
	/**
	 * 아이템그룹 코드 취득
	 * @return ig_cd 아이템그룹 코드
	 */
	public String getIg_cd() {
		return StringUtil.nvl(this.ig_cd);
	}
	
	
	/**
	 *  설정
	 * @param assy_type 
	 */
	public void setAssy_type(String assy_type) {
		this.assy_type = assy_type;
	}
	/**
	 *  취득
	 * @return assy_type 
	 */
	public String getAssy_type() {
		return StringUtil.nvl(this.assy_type);
	}

	/**
	 * 자재번호 설정
	 * @param matnr 자재번호
	 */
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	/**
	 * 자재번호 취득
	 * @return matnr 자재번호
	 */
	public String getMatnr() {
		return StringUtil.nvl(this.matnr);
	}

	/**
	 * 형번 설정
	 * @param designation 형번
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * 형번 취득
	 * @return designation 형번
	 */
	public String getDesignation() {
		return StringUtil.nvl(this.designation);
	}

	/**
	 * 3D(Detail) 설정
	 * @param stp_detail 3D(Detail)
	 */
	public void setStp_detail(String stp_detail) {
		this.stp_detail = stp_detail;
	}
	/**
	 * 3D(Detail) 취득
	 * @return stp_detail 3D(Detail)
	 */
	public String getStp_detail() {
		return StringUtil.nvl(this.stp_detail);
	}

	/**
	 * 3D(Basic) 설정
	 * @param stp_basic 3D(Basic)
	 */
	public void setStp_basic(String stp_basic) {
		this.stp_basic = stp_basic;
	}
	/**
	 * 3D(Basic) 취득
	 * @return stp_basic 3D(Basic)
	 */
	public String getStp_basic() {
		return StringUtil.nvl(this.stp_basic);
	}

	/**
	 * overhang 설정
	 * @param overhang overhang
	 */
	public void setOverhang(String overhang) {
		this.overhang = overhang;
	}
	/**
	 * overhang 취득
	 * @return overhang overhang
	 */
	public String getOverhang() {
		return StringUtil.nvl(this.overhang);
	}

	/**
	 * location 설정
	 * @param location location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * location 취득
	 * @return location location
	 */
	public String getLocation() {
		return StringUtil.nvl(this.location);
	}

	/**
	 * p21 파일경로 설정
	 * @param p21_file p21 파일경로
	 */
	public void setP21_file(String p21_file) {
		this.p21_file = p21_file;
	}
	/**
	 * p21 파일경로 취득
	 * @return p21_file p21 파일경로
	 */
	public String getP21_file() {
		return StringUtil.nvl(this.p21_file);
	}
	
	/**
	 * 자재번호 배열 설정
	 * @param matnr_arr 자재번호 배열
	 */
	public void setMatnr_arr(String[] matnr_arr) {
		if(matnr_arr != null){
			this.matnr_arr = Arrays.copyOf(matnr_arr, matnr_arr.length);
		}
	}
	/**
	 * 자재번호 배열 취득
	 * @return matnr_arr 자재번호 배열
	 */
	public String[] getMatnr_arr() {
		if(this.matnr_arr != null){
			return Arrays.copyOf(this.matnr_arr, this.matnr_arr.length);
		} else {
			return null;
		}
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
	 * SubApp코드 설정
	 * @param sa_cd SubApp코드
	 */
	public void setSa_cd(String sa_cd) {
		this.sa_cd = sa_cd;
	}
	/**
	 * SubApp코드 취득
	 * @return sa_cd SubApp코드
	 */
	public String getSa_cd() {
		return StringUtil.nvl(this.sa_cd);
	}
	
}
