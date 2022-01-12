/*****************************************************************************
 * 프로그램명  : GtcXmlVO.java
 * 설     명  : Assembly 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 *2021.09.01   왕윤아    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("GtcXmlVO")
public class GtcXmlVO extends EactReqInfo {
	
	
	/** 자재번호 */
	private String matnr = "";
	/** 단위(Metric, Inch, Both) */
	private String unit = "";
	/** vendor id */
	private String vender_id = "";
	/** product_pictures */
	private String item_image_nm = "";
	/** product_family_drawings */
	private String iso_dd_image_nm = "";
	/** product_3d_models_basic */
	private String stp_file_image_nm = "";
	/** product_3d_models_detailed */
	private String stp_file_detail_image_nm = "";
	/** product_2d_drawings */
	private String dxf_file_image_nm = "";
	/** product_data_files */
	private String p21_image_nm = "";
	/** gtc generic id */
	private String gtc_gen_id = "";
	/** gtc version */
	private String gtc_ver = "";
	/** package logo */
	private String package_image_nm = "";
	/** p21생성일자 */
	private String cdate_executed = "";
	/** 패키지 종류 구분.  A : All, M : 어셈블, O : 단품 */
	private String gtc_pkg_type = "";
	/** P21 파일 생성 자재번호 배열 : 21 파일 생성시 자재번호 기준으로 만듬 */
	private List<String> arr_matnr = null;
	/** p21 value 변경 일자 */
	private String p21ValueChangeTimestamp = "";
	/** p21 구조 변경 일자 */
	private String p21StructureChangeTimestamp = "";
	/** assembly 자재번호 배열 */
	private List<String> arr_assy = null;



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
	 * 단위(Metric, Inch, Both) 설정
	 * @param unit 단위(Metric, Inch, Both)
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 단위(Metric, Inch, Both) 취득
	 * @return unit 단위(Metric, Inch, Both)
	 */
	public String getUnit() {
		return StringUtil.nvl(this.unit);
	}

	/**
	 * vendor id 설정
	 * @param vender_id vendor id
	 */
	public void setVender_id(String vender_id) {
		this.vender_id = vender_id;
	}
	/**
	 * vendor id 취득
	 * @return vender_id vendor id
	 */
	public String getVender_id() {
		return StringUtil.nvl(this.vender_id);
	}

	/**
	 * product_pictures 설정
	 * @param item_image_nm product_pictures
	 */
	public void setItem_image_nm(String item_image_nm) {
		this.item_image_nm = item_image_nm;
	}
	/**
	 * product_pictures 취득
	 * @return item_image_nm product_pictures
	 */
	public String getItem_image_nm() {
		return StringUtil.nvl(this.item_image_nm);
	}

	/**
	 * product_family_drawings 설정
	 * @param iso_dd_image_nm product_family_drawings
	 */
	public void setIso_dd_image_nm(String iso_dd_image_nm) {
		this.iso_dd_image_nm = iso_dd_image_nm;
	}
	/**
	 * product_family_drawings 취득
	 * @return iso_dd_image_nm product_family_drawings
	 */
	public String getIso_dd_image_nm() {
		return StringUtil.nvl(this.iso_dd_image_nm);
	}

	/**
	 * product_3d_models_basic 설정
	 * @param stp_file_image_nm product_3d_models_basic
	 */
	public void setStp_file_image_nm(String stp_file_image_nm) {
		this.stp_file_image_nm = stp_file_image_nm;
	}
	/**
	 * product_3d_models_basic 취득
	 * @return stp_file_image_nm product_3d_models_basic
	 */
	public String getStp_file_image_nm() {
		return StringUtil.nvl(this.stp_file_image_nm);
	}

	/**
	 * product_3d_models_detailed 설정
	 * @param stp_file_detail_image_nm product_3d_models_detailed
	 */
	public void setStp_file_detail_image_nm(String stp_file_detail_image_nm) {
		this.stp_file_detail_image_nm = stp_file_detail_image_nm;
	}
	/**
	 * product_3d_models_detailed 취득
	 * @return stp_file_detail_image_nm product_3d_models_detailed
	 */
	public String getStp_file_detail_image_nm() {
		return StringUtil.nvl(this.stp_file_detail_image_nm);
	}

	/**
	 * product_2d_drawings 설정
	 * @param dxf_file_image_nm product_2d_drawings
	 */
	public void setDxf_file_image_nm(String dxf_file_image_nm) {
		this.dxf_file_image_nm = dxf_file_image_nm;
	}
	/**
	 * product_2d_drawings 취득
	 * @return dxf_file_image_nm product_2d_drawings
	 */
	public String getDxf_file_image_nm() {
		return StringUtil.nvl(this.dxf_file_image_nm);
	}

	/**
	 * product_data_files 설정
	 * @param p21_image_nm product_data_files
	 */
	public void setP21_image_nm(String p21_image_nm) {
		this.p21_image_nm = p21_image_nm;
	}
	/**
	 * product_data_files 취득
	 * @return p21_image_nm product_data_files
	 */
	public String getP21_image_nm() {
		return StringUtil.nvl(this.p21_image_nm);
	}

	/**
	 * gtc generic id 설정
	 * @param gtc_gen_id gtc generic id
	 */
	public void setGtc_gen_id(String gtc_gen_id) {
		this.gtc_gen_id = gtc_gen_id;
	}
	/**
	 * gtc generic id 취득
	 * @return gtc_gen_id gtc generic id
	 */
	public String getGtc_gen_id() {
		return StringUtil.nvl(this.gtc_gen_id);
	}

	/**
	 * gtc version 설정
	 * @param gtc_ver gtc version
	 */
	public void setGtc_ver(String gtc_ver) {
		this.gtc_ver = gtc_ver;
	}
	/**
	 * gtc version 취득
	 * @return gtc_ver gtc version
	 */
	public String getGtc_ver() {
		return StringUtil.nvl(this.gtc_ver);
	}

	/**
	 * package logo 설정
	 * @param package_image_nm package logo
	 */
	public void setPackage_image_nm(String package_image_nm) {
		this.package_image_nm = package_image_nm;
	}
	/**
	 * package logo 취득
	 * @return package_image_nm package logo
	 */
	public String getPackage_image_nm() {
		return StringUtil.nvl(this.package_image_nm);
	}

	/**
	 * p21생성일자 설정
	 * @param cdate_executed p21생성일자
	 */
	public void setCdate_executed(String cdate_executed) {
		this.cdate_executed = cdate_executed;
	}
	/**
	 * p21생성일자 취득
	 * @return cdate_executed p21생성일자
	 */
	public String getCdate_executed() {
		return StringUtil.nvl(this.cdate_executed);
	}

	/**
	 * A : All, M : 어셈블, O : 단품 설정
	 * @param gtc_pkg_type A : All, M : 어셈블, O : 단품
	 */
	public void setGtc_pkg_type(String gtc_pkg_type) {
		this.gtc_pkg_type = gtc_pkg_type;
	}
	/**
	 * A : All, M : 어셈블, O : 단품 취득
	 * @return gtc_pkg_type A : All, M : 어셈블, O : 단품
	 */
	public String getGtc_pkg_type() {
		return StringUtil.nvl(this.gtc_pkg_type);
	}

	/**
	 * P21 파일 생성 자재번호 배열 설정
	 * @param arr_matnr P21 파일 생성 자재번호 배열
	 */
	public void setArr_matnr(List<String> arr_matnr) {
		this.arr_matnr = arr_matnr;
	}
	/**
	 * P21 파일 생성 자재번호 배열 취득
	 * @return arr_matnr P21 파일 생성 자재번호 배열
	 */
	public List<String> getArr_matnr() {
		return this.arr_matnr;
	}

	/**
	 * p21 value 변경 일자 설정
	 * @param p21ValueChangeTimestamp p21 value 변경 일자
	 */
	public void setP21ValueChangeTimestamp(String p21ValueChangeTimestamp) {
		this.p21ValueChangeTimestamp = p21ValueChangeTimestamp;
	}
	/**
	 * p21 value 변경 일자 취득
	 * @return p21ValueChangeTimestamp p21 value 변경 일자
	 */
	public String getP21ValueChangeTimestamp() {
		return StringUtil.nvl(this.p21ValueChangeTimestamp);
	}

	/**
	 * p21 구조 변경 일자 설정
	 * @param p21StructureChangeTimestamp p21 구조 변경 일자
	 */
	public void setP21StructureChangeTimestamp(String p21StructureChangeTimestamp) {
		this.p21StructureChangeTimestamp = p21StructureChangeTimestamp;
	}
	/**
	 * p21 구조 변경 일자 취득
	 * @return p21StructureChangeTimestamp p21 구조 변경 일자
	 */
	public String getP21StructureChangeTimestamp() {
		return StringUtil.nvl(this.p21StructureChangeTimestamp);
	}

	/**
	 * assembly 자재번호 배열 설정
	 * @param arr_assy assembly 자재번호 배열
	 */
	public void setArr_assy(List<String> arr_assy) {
		this.arr_assy = arr_assy;
	}
	/**
	 * assembly 자재번호 배열 취득
	 * @return arr_assy assembly 자재번호 배열
	 */
	public List<String> getArr_assy() {
		return this.arr_assy;
	}
	

}
