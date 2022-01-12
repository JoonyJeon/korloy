/*****************************************************************************
 * 프로그램명  : AppInfo.java
 * 설     명  : Main/Sub/Item 정보 객체
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

import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("AppInfo")
public class AppInfo extends EactReqInfo {
	
	/** MainApp코드 */
	private String ma_cd = "";
	/** MainApp명 */
	private String ma_nm = "";
	/** 메인이미지 */
	private String main_img = "";
	/** 아이콘이미지 */
	private String icon_img = "";
	/** 하위아이템 개수 */
	private String sub_cnt = "";
	/** SubApp코드 */
	private String sa_cd = "";
	/** SubApp명 */
	private String sa_nm = "";
	/** SubApp이미지 */
	private String sub_img = "";
	/** 아이템그룹코드 */
	private String ig_cd = "";
	/** 아이템그룹코드명 */
	private String ig_nm = "";
	/** 자재번호 */
	private String matnr = "";
	/** 자재내역 */
	private String maktx = "";
	/** 형변+CB(Metric) */
	private String designation = "";
	/** 형변+CB(Inch) */
	private String designation_inch = "";
	/** 규격코드 */
	private String cb = "";
	/** 규격코드(Inch) */
	private String cb_inch = "";
	/** 재종(재질) */
	private String grade = "";
	/** 해외재종 */
	private String grade_os = "";
	/** Metric/Inch/Both Code */
	private String unit_cd = "";
	/** Tipped tool */
	private String tips_cd = "";
	/** 정가(원화) */
	private String k_kbetr = "";
	/** 정가(달러화) */
	private String u_kbetr = "";
	/** 정가(유로화) */
	private String e_kbetr = "";
	/** 지사별 재고수량 */
	private String labst = "";
	/** 최소주문수량코드 */
	private String moq = "";
	/** 최소주문수량 */
	private String moq_qty = "";
	/** LCS코드 */
	private String lcs = "";
	/** LCS코드설명 */
	private String lcs_txt = "";
	/** SC코드 */
	private String labor = "";
	/** 신제품출시일 */
	private String new_fr_dt = "";
	/** 신제품종료일 */
	private String new_to_dt = "";
	/** 이관구분 */
	private String esc_gb = "";
	/** 이관일자 */
	private String esc_dt = "";
	/** KORLOY(GTC_vender_id) */
	private String vender_id = "";
	/** 사용 및 노출여부 */
	private String use_yn = "";
	/** 컨텐츠 유입경로 구분 */
	private String gubun = "";
	/** 인치구분 */
	private String inch_gb = "";
	/** Header1 코드 */
	private String tm_cd = "";
	/** Header1 Name */
	private String tm_nm = "";
	/** 브랜드 로고 */
	private String brand_logo = "";
	/** Header1 Name(KR) */
	private String tm_kr_nm = "";
	/** Header1 Name(EN) */
	private String tm_en_nm = "";
	/** 속성 */
	private String symbol = "";
	/** 속성값 */
	private String val = "";	
	/** 2D이미지(DXF) */
	private String dxf_img = "";
	/** 2D이미지(DXF) 썸네일 */
	private String dxf_img_thumb = "";
	/** PC표시여부 */
	private String disp_pc_yn = "";
	/** 모바일표시여부 */
	private String disp_mb_yn = "";
	/** 검색유형 */
	private String sch_typ = "";
	/** ISO Property Symbol */
	private String prop_iso = "";
	/** Non-ISO Property Symbol */
	private String prop_n_iso = "";
	/** Property title */
	private String prop_title = "";
	/** Property title */
	private String prop_n_title = "";
	
	/** 필터조회SQL */
	private String filter_sch_sql = "";

	/** 아이템이미지 */
	private String item_image = "";
	/** ISO이미지 */
	private String iso_image = "";
	/** NonISO이미지 */
	private String non_image = "";
	/** 아이템이미지 썸네일 */
	private String item_image_thumb = "";
	/** ISO이미지 썸네일 */
	private String iso_image_thumb = "";
	/** NonISO이미지 썸네일 */
	private String non_image_thumb = "";	
	/** 디바이스타입(M:모바일,T:태블릿,P:데스트탑) */
	private String device_type = "";
	/** Sub Application Code */
	private String[] ar_sa_cd = null;
	/** 콤보코드리스트 */
	private List<CodeInfo> combo_list = null;
	/** 필터파라미터정보 */
	private String filterParam = "";
	
	/** METRIC단위 */
	private String unit1 = "";
	/** INCH단위 */
	private String unit2 = "";
	/** 프린트용METRIC값 */
	private String print_mm = "";
	/** 프린트용INCH값 */
	private String print_inch = "";
	/** BOM품번(완제품) */
	private String bom_matnr = "";
	/** 품번(형번) */
	private String idnrk_hyung = "";
	/** 수량 */
	private String qty = "";
	/** 아이템이미지(Spare Parts) */
	private String item_img = "";
	/** 신제품여부 */
	private String new_yn = "";

	/** STP File */
	private String stp_file = "";
	/** DXF File */
	private String dxf_file = "";
	/** 전략그룹(40:Stock, 50:Non Stock) */
	private String strgr = "";
	/** 아이템그룹 설명 descr */
	private String descr = "";
	/** 구성품목 단위 */
	private String meins = "";
	/** 구성품목 수량 */
	private String menge = "";
	/** 카트번호 */
	private String cart_no = "";
	/** 카트명 */
	private String cart_nm = "";
	/** Insert 아이템 갯수 */
	private String iic_cnt = "";
	/** String */
	private String isSubAppChange = "";
	/** 아이템그룹설명 */
	private String ig_dscr = "";
	/** STP파일명 */
	private String stp_file_name = "";
	/** DXF파일명 */
	private String dxf_file_name = "";
	/** P21파일설정 */
	private String p21_file = "";
	/** GTC파일설정 */
	private String gtc_file = "";
	/** Sub App List */
	private List subAppList = null;
	/** Item Group List Sub App SQL */
	private String sa_multi_sql = "";

	/** 어셈블리 일련번호 */
	private String assem_no = "";
	/** 어셈블리명 */
	private String assem_nm = "";
	
	/** 아이템그룹Array */
	private String[] ig_cd_arr = null;
	
	/** 어셈블리 그룹 아이템 목록 */
	List<AppInfo> assem_item_list = new ArrayList<>();
	/** 어셈블리 타입 */
	private String assy_type = "";
	
	/** Metric Unit */
	private String unit_metr = "";
	/** Inch Unit */
	private String unit_inch = "";
	/** More Info */
	private String more_info = "";
	/** 아이템바로표시여부 */
	private String item_view_yn = "";
	
	/** 자재번호 리스트 */
	private String matnr_list = "";
	/** 자재번호 Array */
	private String[] matnr_arr = null;
	/** 검색 어셈블리번호 */
	private String search_assem_no = "";
	

	/** ISO 기호 */
	private String iso = "";
	/** 계산식단위 */
	private String cal_unit = "";
	/** 계산식값 */
	private String cal_val = "";	
	/** 재고수량 */
	private String st_labst = "";
	/** 인치사용여부 */
	private String inch_use_yn = "";
	/*GTIN(EAN)*/
	private String zaddean = "";
	/** 어셈블리에서 넘어온 IG_CD */
	private String assem_ig_cd = "";
	/** 테일러드파일 */
	private String tail_file = "";
	/** 언어코드 */
	private String lang_cd = "";
	
	/** Workpiece Side (오른쪽) */
	private List<AssemblyItemInfo> itemWS = null;
	/** Machine Side (왼쪽) */
	private List<AssemblyItemInfo> itemMS = null;


	/**
	 * Workpiece Side (오른쪽) 설정
	 * @param itemWS Workpiece Side (오른쪽)
	 */
	public void setItemWS(List<AssemblyItemInfo> itemWS) {
		this.itemWS = itemWS;
	}
	
	/**
	 * Workpiece Side (오른쪽) 취득
	 * @return itemWS Workpiece Side (오른쪽)
	 */
	public List<AssemblyItemInfo> getItemWS() {
		return this.itemWS;
	}
	
	/**
	 * Machine Side (왼쪽) 설정
	 * @param itemMS Machine Side (왼쪽)
	 */
	public void setItemMS(List<AssemblyItemInfo> itemMS) {
		this.itemMS = itemMS;
	}
	
	/**
	 * Machine Side (왼쪽) 취득
	 * @return itemMS Machine Side (왼쪽)
	 */
	public List<AssemblyItemInfo> getItemMS() {
		return this.itemMS;
	}
	
	
	/**
	 * 언어코드 설정
	 * @param lang_cd 언어코드
	 */
	public void setLang_cd(String lang_cd) {
		this.lang_cd = lang_cd;
	}
	/**
	 * 언어코드 취득
	 * @return lang_cd 언어코드
	 */
	public String getLang_cd() {
		return StringUtil.nvl(this.lang_cd);
	}


	/**
	 * 테일러드파일 설정
	 * @param tail_file 테일러드파일
	 */
	public void setTail_file(String tail_file) {
		this.tail_file = tail_file;
	}
	/**
	 * 테일러드파일 취득
	 * @return tail_file 테일러드파일
	 */
	public String getTail_file() {
		return StringUtil.nvl(this.tail_file);
	}


	/**
	 * 어셈블리에서 넘어온 IG_CD 설정
	 * @param assem_ig_cd 어셈블리에서 넘어온 IG_CD
	 */
	public void setAssem_ig_cd(String assem_ig_cd) {
		this.assem_ig_cd = assem_ig_cd;
	}
	/**
	 * 어셈블리에서 넘어온 IG_CD 취득
	 * @return assem_ig_cd 어셈블리에서 넘어온 IG_CD
	 */
	public String getAssem_ig_cd() {
		return StringUtil.nvl(this.assem_ig_cd);
	}

	/**
	 * 인치사용여부 설정
	 * @param inch_use_yn 인치사용여부
	 */
	public void setInch_use_yn(String inch_use_yn) {
		this.inch_use_yn = inch_use_yn;
	}
	/**
	 * 인치사용여부 취득
	 * @return inch_use_yn 인치사용여부
	 */
	public String getInch_use_yn() {
		return StringUtil.nvl(this.inch_use_yn);
	}


	/**
	 * 재고수량 설정
	 * @param st_labst 재고수량
	 */
	public void setSt_labst(String st_labst) {
		this.st_labst = st_labst;
	}
	/**
	 * 재고수량 취득
	 * @return st_labst 재고수량
	 */
	public String getSt_labst() {
		return StringUtil.nvl(this.st_labst);
	}

	/**
	 * ISO 기호 설정
	 * @param iso ISO 기호
	 */
	public void setIso(String iso) {
		this.iso = iso;
	}
	/**
	 * ISO 기호 취득
	 * @return iso ISO 기호
	 */
	public String getIso() {
		return StringUtil.nvl(this.iso);
	}

	/**
	 * 계산식단위 설정
	 * @param cal_unit 계산식단위
	 */
	public void setCal_unit(String cal_unit) {
		this.cal_unit = cal_unit;
	}
	/**
	 * 계산식단위 취득
	 * @return cal_unit 계산식단위
	 */
	public String getCal_unit() {
		return StringUtil.nvl(this.cal_unit);
	}

	/**
	 * 계산식값 설정
	 * @param cal_val 계산식값
	 */
	public void setCal_val(String cal_val) {
		this.cal_val = cal_val;
	}
	/**
	 * 계산식값 취득
	 * @return cal_val 계산식값
	 */
	public String getCal_val() {
		return StringUtil.nvl(this.cal_val);
	}

	/**
	 * 아이템바로표시여부 설정
	 * @param item_view_yn 아이템바로표시여부
	 */
	public void setItem_view_yn(String item_view_yn) {
		this.item_view_yn = item_view_yn;
	}
	/**
	 * 아이템바로표시여부 취득
	 * @return item_view_yn 아이템바로표시여부
	 */
	public String getItem_view_yn() {
		return StringUtil.nvl(this.item_view_yn);
	}


	/**
	 * More Info 설정
	 * @param more_info More Info
	 */
	public void setMore_info(String more_info) {
		this.more_info = more_info;
	}
	/**
	 * More Info 취득
	 * @return more_info More Info
	 */
	public String getMore_info() {
		return StringUtil.nvl(this.more_info);
	}
	
	/**
	 * Metric Unit 설정
	 * @param unit_metr Metric Unit
	 */
	public void setUnit_metr(String unit_metr) {
		this.unit_metr = unit_metr;
	}
	/**
	 * Metric Unit 취득
	 * @return unit_metr Metric Unit
	 */
	public String getUnit_metr() {
		return StringUtil.nvl(this.unit_metr);
	}

	/**
	 * Inch Unit 설정
	 * @param unit_inch Inch Unit
	 */
	public void setUnit_inch(String unit_inch) {
		this.unit_inch = unit_inch;
	}
	/**
	 * Inch Unit 취득
	 * @return unit_inch Inch Unit
	 */
	public String getUnit_inch() {
		return StringUtil.nvl(this.unit_inch);
	}

	/**
	 * 어셈블리 타입 설정
	 * @param assy_type 어셈블리 타입
	 */
	public void setAssy_type(String assy_type) {
		this.assy_type = assy_type;
	}
	/**
	 * 어셈블리 타입 취득
	 * @return assy_type 어셈블리 타입
	 */
	public String getAssy_type() {
		return StringUtil.nvl(this.assy_type);
	}
	
	/**
	 * Assembly그룹에 해당하는 아이템 목록 설정
	 * @param assem_item_list Assembly그룹에 해당하는 아이템 목록
	 */
	public void setAssem_item_list(List<AppInfo> assem_item_list) {
		this.assem_item_list = assem_item_list;
	}
	/**
	 * Assembly그룹에 해당하는 아이템 목록 취득
	 * @return assem_item_list Assembly그룹에 해당하는 아이템 목록
	 */
	public List<AppInfo> getAssem_item_list() {
		return this.assem_item_list;
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
	 * 어셈블리명 설정
	 * @param assem_nm 어셈블리명
	 */
	public void setAssem_nm(String assem_nm) {
		this.assem_nm = assem_nm;
	}
	/**
	 * 어셈블리명 취득
	 * @return assem_nm 어셈블리명
	 */
	public String getAssem_nm() {
		return StringUtil.nvl(this.assem_nm);
	}


	/**
	 * Item Group List Sub App SQL 설정
	 * @param sa_multi_sql Item Group List Sub App SQL
	 */
	public void setSa_multi_sql(String sa_multi_sql) {
		this.sa_multi_sql = sa_multi_sql;
	}
	/**
	 * Item Group List Sub App SQL 취득
	 * @return sa_multi_sql Item Group List Sub App SQL
	 */
	public String getSa_multi_sql() {
		return StringUtil.nvl(this.sa_multi_sql);
	}


	/**
	 * Sub App List 설정
	 * @param subAppList Sub App List
	 */
	public void setSubAppList(List subAppList) {
		this.subAppList = subAppList;
	}
	/**
	 * Sub App List 취득
	 * @return subAppList Sub App List
	 */
	public List getSubAppList() {
		return this.subAppList;
	}


	/**
	 * P21파일설정 설정
	 * @param p21_file P21파일설정
	 */
	public void setP21_file(String p21_file) {
		this.p21_file = p21_file;
	}
	/**
	 * P21파일설정 취득
	 * @return p21_file P21파일설정
	 */
	public String getP21_file() {
		return StringUtil.nvl(this.p21_file);
	}

	/**
	 * GTC파일설정 설정
	 * @param gtc_file GTC파일설정
	 */
	public void setGtc_file(String gtc_file) {
		this.gtc_file = gtc_file;
	}
	/**
	 * GTC파일설정 취득
	 * @return gtc_file GTC파일설정
	 */
	public String getGtc_file() {
		return StringUtil.nvl(this.gtc_file);
	}
	
	/**
	 * STP파일명 설정
	 * @param stp_file_name STP파일명
	 */
	public void setStp_file_name(String stp_file_name) {
		this.stp_file_name = stp_file_name;
	}
	/**
	 * STP파일명 취득
	 * @return stp_file_name STP파일명
	 */
	public String getStp_file_name() {
		return StringUtil.nvl(this.stp_file_name);
	}

	/**
	 * DXF파일명 설정
	 * @param dxf_file_name DXF파일명
	 */
	public void setDxf_file_name(String dxf_file_name) {
		this.dxf_file_name = dxf_file_name;
	}
	/**
	 * DXF파일명 취득
	 * @return dxf_file_name DXF파일명
	 */
	public String getDxf_file_name() {
		return StringUtil.nvl(this.dxf_file_name);
	}


	/**
	 * 아이템그룹설명 설정
	 * @param ig_dscr 아이템그룹설명
	 */
	public void setIg_dscr(String ig_dscr) {
		this.ig_dscr = ig_dscr;
	}
	/**
	 * 아이템그룹설명 취득
	 * @return ig_dscr 아이템그룹설명
	 */
	public String getIg_dscr() {
		return StringUtil.nvl(this.ig_dscr);
	}


	/**
	 * String 설정
	 * @param isSubAppChange String
	 */
	public void setIsSubAppChange(String isSubAppChange) {
		this.isSubAppChange = isSubAppChange;
	}
	/**
	 * String 취득
	 * @return isSubAppChange String
	 */
	public String getIsSubAppChange() {
		return StringUtil.nvl(this.isSubAppChange);
	}


	/**
	 * Insert 아이템 갯수 설정
	 * @param iic_cnt Insert 아이템 갯수
	 */
	public void setIic_cnt(String iic_cnt) {
		this.iic_cnt = iic_cnt;
	}
	/**
	 * Insert 아이템 갯수 취득
	 * @return iic_cnt Insert 아이템 갯수
	 */
	public String getIic_cnt() {
		return StringUtil.nvl(this.iic_cnt);
	}
	
	/**
	 * 카트명 설정
	 * @param cart_nm 카트명
	 */
	public void setCart_nm(String cart_nm) {
		this.cart_nm = cart_nm;
	}
	/**
	 * 카트명 취득
	 * @return cart_nm 카트명
	 */
	public String getCart_nm() {
		return StringUtil.nvl(this.cart_nm);
	}
	
	/**
	 * 카트번호 설정
	 * @param cart_no 카트번호
	 */
	public void setCart_no(String cart_no) {
		this.cart_no = cart_no;
	}
	/**
	 * 카트번호 취득
	 * @return cart_no 카트번호
	 */
	public String getCart_no() {
		return StringUtil.nvl(this.cart_no);
	}
	
	/**
	 * 구성품목 단위 설정
	 * @param meins 구성품목 단위
	 */
	public void setMeins(String meins) {
		this.meins = meins;
	}
	/**
	 * 구성품목 단위 취득
	 * @return meins 구성품목 단위
	 */
	public String getMeins() {
		return StringUtil.nvl(this.meins);
	}

	/**
	 * 구성품목 수량 설정
	 * @param menge 구성품목 수량
	 */
	public void setMenge(String menge) {
		this.menge = menge;
	}
	/**
	 * 구성품목 수량 취득
	 * @return menge 구성품목 수량
	 */
	public String getMenge() {
		return StringUtil.nvl(this.menge);
	}
	
	/**
	 * 아이템그룹 설명 descr 설정
	 * @param descr 아이템그룹 설명 descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	/**
	 * 아이템그룹 설명 descr 취득
	 * @return descr 아이템그룹 설명 descr
	 */
	public String getDescr() {
		return StringUtil.nvl(this.descr);
	}
	
	/**
	 * 전략그룹(40:Stock, 50:Non Stock) 설정
	 * @param strgr 전략그룹(40:Stock, 50:Non Stock)
	 */
	public void setStrgr(String strgr) {
		this.strgr = strgr;
	}
	/**
	 * 전략그룹(40:Stock, 50:Non Stock) 취득
	 * @return strgr 전략그룹(40:Stock, 50:Non Stock)
	 */
	public String getStrgr() {
		return StringUtil.nvl(this.strgr);
	}
	
	/**
	 * STP File 설정
	 * @param stp_file STP File
	 */
	public void setStp_file(String stp_file) {
		this.stp_file = stp_file;
	}
	/**
	 * STP File 취득
	 * @return stp_file STP File
	 */
	public String getStp_file() {
		return StringUtil.nvl(this.stp_file);
	}

	/**
	 * DXF File 설정
	 * @param dxf_file DXF File
	 */
	public void setDxf_file(String dxf_file) {
		this.dxf_file = dxf_file;
	}
	/**
	 * DXF File 취득
	 * @return dxf_file DXF File
	 */
	public String getDxf_file() {
		return StringUtil.nvl(this.dxf_file);
	}

	/**
	 * 신제품여부 설정
	 * @param new_yn 신제품여부
	 */
	public void setNew_yn(String new_yn) {
		this.new_yn = new_yn;
	}
	/**
	 * 신제품여부 취득
	 * @return new_yn 신제품여부
	 */
	public String getNew_yn() {
		return StringUtil.nvl(this.new_yn);
	}
	
	/**
	 * 아이템이미지(Spare Parts) 설정
	 * @param item_img 아이템이미지(Spare Parts)
	 */
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	/**
	 * 아이템이미지(Spare Parts) 취득
	 * @return item_img 아이템이미지(Spare Parts)
	 */
	public String getItem_img() {
		return StringUtil.nvl(this.item_img);
	}

	/**
	 * BOM품번(완제품) 설정
	 * @param bom_matnr BOM품번(완제품)
	 */
	public void setBom_matnr(String bom_matnr) {
		this.bom_matnr = bom_matnr;
	}
	/**
	 * BOM품번(완제품) 취득
	 * @return bom_matnr BOM품번(완제품)
	 */
	public String getBom_matnr() {
		return StringUtil.nvl(this.bom_matnr);
	}

	/**
	 * 품번(형번) 설정
	 * @param idnrk_hyung 품번(형번)
	 */
	public void setIdnrk_hyung(String idnrk_hyung) {
		this.idnrk_hyung = idnrk_hyung;
	}
	/**
	 * 품번(형번) 취득
	 * @return idnrk_hyung 품번(형번)
	 */
	public String getIdnrk_hyung() {
		return StringUtil.nvl(this.idnrk_hyung);
	}

	/**
	 * 수량 설정
	 * @param qty 수량
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}
	/**
	 * 수량 취득
	 * @return qty 수량
	 */
	public String getQty() {
		return StringUtil.nvl(this.qty);
	}
	
	/**
	 * METRIC단위 설정
	 * @param unit1 METRIC단위
	 */
	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}
	/**
	 * METRIC단위 취득
	 * @return unit1 METRIC단위
	 */
	public String getUnit1() {
		return StringUtil.nvl(this.unit1);
	}

	/**
	 * INCH단위 설정
	 * @param unit2 INCH단위
	 */
	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}
	/**
	 * INCH단위 취득
	 * @return unit2 INCH단위
	 */
	public String getUnit2() {
		return StringUtil.nvl(this.unit2);
	}

	/**
	 * 프린트용METRIC값 설정
	 * @param print_mm 프린트용METRIC값
	 */
	public void setPrint_mm(String print_mm) {
		this.print_mm = print_mm;
	}
	/**
	 * 프린트용METRIC값 취득
	 * @return print_mm 프린트용METRIC값
	 */
	public String getPrint_mm() {
		return StringUtil.nvl(this.print_mm);
	}

	/**
	 * 프린트용INCH값 설정
	 * @param print_inch 프린트용INCH값
	 */
	public void setPrint_inch(String print_inch) {
		this.print_inch = print_inch;
	}
	/**
	 * 프린트용INCH값 취득
	 * @return print_inch 프린트용INCH값
	 */
	public String getPrint_inch() {
		return StringUtil.nvl(this.print_inch);
	}


	/**
	 * 필터파라미터정보 설정
	 * @param filterParam 필터파라미터정보
	 */
	public void setFilterParam(String filterParam) {
		this.filterParam = filterParam;
	}
	/**
	 * 필터파라미터정보 취득
	 * @return filterParam 필터파라미터정보
	 */
	public String getFilterParam() {
		return StringUtil.nvl(this.filterParam);
	}


	/**
	 * 콤보코드리스트 설정
	 * @param combo_list 콤보코드리스트
	 */
	public void setCombo_list(List<CodeInfo> combo_list) {
		this.combo_list = combo_list;
	}
	/**
	 * 콤보코드리스트 취득
	 * @return combo_list 콤보코드리스트
	 */
	public List<CodeInfo> getCombo_list() {
		return this.combo_list;
	}

	/**
	 * Sub Application Code 설정
	 * @param ar_sa_cd Sub Application Code
	 */
	public void setAr_sa_cd(String[] ar_sa_cd) {
		if(ar_sa_cd != null){
			this.ar_sa_cd = Arrays.copyOf(ar_sa_cd, ar_sa_cd.length);
		}
	}
	/**
	 * Sub Application Code 취득
	 * @return ar_sa_cd Sub Application Code
	 */
	public String[] getAr_sa_cd() {
		if(this.ar_sa_cd != null){
			return Arrays.copyOf(this.ar_sa_cd, this.ar_sa_cd.length);
		} else {
			return null;
		}
	}
	
	/**
	 * 디바이스타입(M:모바일,T:태블릿,P:데스트탑) 설정
	 * @param device_type 디바이스타입(M:모바일,T:태블릿,P:데스트탑)
	 */
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	/**
	 * 디바이스타입(M:모바일,T:태블릿,P:데스트탑) 취득
	 * @return device_type 디바이스타입(M:모바일,T:태블릿,P:데스트탑)
	 */
	public String getDevice_type() {
		return StringUtil.nvl(this.device_type);
	}

	/**
	 * 아이템이미지 설정
	 * @param item_image 아이템이미지
	 */
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}
	/**
	 * 아이템이미지 취득
	 * @return item_image 아이템이미지
	 */
	public String getItem_image() {
		return StringUtil.nvl(this.item_image);
	}

	/**
	 * ISO이미지 설정
	 * @param iso_image ISO이미지
	 */
	public void setIso_image(String iso_image) {
		this.iso_image = iso_image;
	}
	/**
	 * ISO이미지 취득
	 * @return iso_image ISO이미지
	 */
	public String getIso_image() {
		return StringUtil.nvl(this.iso_image);
	}

	/**
	 * NonISO이미지 설정
	 * @param non_image NonISO이미지
	 */
	public void setNon_image(String non_image) {
		this.non_image = non_image;
	}
	/**
	 * NonISO이미지 취득
	 * @return non_image NonISO이미지
	 */
	public String getNon_image() {
		return StringUtil.nvl(this.non_image);
	}
	
	/**
	 * 아이템이미지 썸네일 설정
	 * @param item_image_thumb 아이템이미지 썸네일
	 */
	public void setItem_image_thumb(String item_image_thumb) {
		this.item_image_thumb = item_image_thumb;
	}
	/**
	 * 아이템이미지 썸네일 취득
	 * @return item_image_thumb 아이템이미지 썸네일
	 */
	public String getItem_image_thumb() {
		return StringUtil.nvl(this.item_image_thumb);
	}

	/**
	 * ISO이미지 썸네일 설정
	 * @param iso_image_thumb ISO이미지 썸네일
	 */
	public void setIso_image_thumb(String iso_image_thumb) {
		this.iso_image_thumb = iso_image_thumb;
	}
	/**
	 * ISO이미지 썸네일 취득
	 * @return iso_image_thumb ISO이미지 썸네일
	 */
	public String getIso_image_thumb() {
		return StringUtil.nvl(this.iso_image_thumb);
	}

	/**
	 * NonISO이미지 썸네일 설정
	 * @param non_image_thumb NonISO이미지 썸네일
	 */
	public void setNon_image_thumb(String non_image_thumb) {
		this.non_image_thumb = non_image_thumb;
	}
	/**
	 * NonISO이미지 썸네일 취득
	 * @return non_image_thumb NonISO이미지 썸네일
	 */
	public String getNon_image_thumb() {
		return StringUtil.nvl(this.non_image_thumb);
	}

	/**
	 * 필터조회SQL 설정
	 * @param filter_sch_sql 필터조회SQL
	 */
	public void setFilter_sch_sql(String filter_sch_sql) {
		this.filter_sch_sql = filter_sch_sql;
	}
	/**
	 * 필터조회SQL 취득
	 * @return filter_sch_sql 필터조회SQL
	 */
	public String getFilter_sch_sql() {
		return StringUtil.nvl(this.filter_sch_sql);
	}


	/**
	 * ISO Property Symbol 설정
	 * @param prop_iso ISO Property Symbol
	 */
	public void setProp_iso(String prop_iso) {
		this.prop_iso = prop_iso;
	}
	/**
	 * ISO Property Symbol 취득
	 * @return prop_iso ISO Property Symbol
	 */
	public String getProp_iso() {
		return StringUtil.nvl(this.prop_iso);
	}
	
	/**
	 * Non-ISO Property Symbol 설정
	 * @param prop_n_iso Non-ISO Property Symbol
	 */
	public void setProp_n_iso(String prop_n_iso) {
		this.prop_n_iso = prop_n_iso;
	}
	/**
	 * Non-ISO Property Symbol 취득
	 * @return prop_n_iso Non-ISO Property Symbol
	 */
	public String getProp_n_iso() {
		return StringUtil.nvl(this.prop_n_iso);
	}


	/**
	 * PC표시여부 설정
	 * @param disp_pc_yn PC표시여부
	 */
	public void setDisp_pc_yn(String disp_pc_yn) {
		this.disp_pc_yn = disp_pc_yn;
	}
	/**
	 * PC표시여부 취득
	 * @return disp_pc_yn PC표시여부
	 */
	public String getDisp_pc_yn() {
		return StringUtil.nvl(this.disp_pc_yn);
	}

	/**
	 * 모바일표시여부 설정
	 * @param disp_mb_yn 모바일표시여부
	 */
	public void setDisp_mb_yn(String disp_mb_yn) {
		this.disp_mb_yn = disp_mb_yn;
	}
	/**
	 * 모바일표시여부 취득
	 * @return disp_mb_yn 모바일표시여부
	 */
	public String getDisp_mb_yn() {
		return StringUtil.nvl(this.disp_mb_yn);
	}

	/**
	 * 검색유형 설정
	 * @param sch_typ 검색유형
	 */
	public void setSch_typ(String sch_typ) {
		this.sch_typ = sch_typ;
	}
	/**
	 * 검색유형 취득
	 * @return sch_typ 검색유형
	 */
	public String getSch_typ() {
		return StringUtil.nvl(this.sch_typ);
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
	 * 아이템그룹코드 설정
	 * @param ig_cd 아이템그룹코드
	 */
	public void setIg_cd(String ig_cd) {
		this.ig_cd = ig_cd;
	}
	/**
	 * 아이템그룹코드 취득
	 * @return ig_cd 아이템그룹코드
	 */
	public String getIg_cd() {
		return StringUtil.nvl(this.ig_cd);
	}

	/**
	 * 아이템그룹코드명 설정
	 * @param ig_nm 아이템그룹코드명
	 */
	public void setIg_nm(String ig_nm) {
		this.ig_nm = ig_nm;
	}
	/**
	 * 아이템그룹코드명 취득
	 * @return ig_nm 아이템그룹코드명
	 */
	public String getIg_nm() {
		return StringUtil.nvl(this.ig_nm);
	}


	/**
	 * MainApp코드 설정
	 * @param ma_cd MainApp코드
	 */
	public void setMa_cd(String ma_cd) {
		this.ma_cd = ma_cd;
	}
	/**
	 * MainApp코드 취득
	 * @return ma_cd MainApp코드
	 */
	public String getMa_cd() {
		return StringUtil.nvl(this.ma_cd);
	}

	/**
	 * MainApp명 설정
	 * @param ma_nm MainApp명
	 */
	public void setMa_nm(String ma_nm) {
		this.ma_nm = ma_nm;
	}
	/**
	 * MainApp명 취득
	 * @return ma_nm MainApp명
	 */
	public String getMa_nm() {
		return StringUtil.nvl(this.ma_nm);
	}

	/**
	 * 메인이미지 설정
	 * @param main_img 메인이미지
	 */
	public void setMain_img(String main_img) {
		this.main_img = main_img;
	}
	/**
	 * 메인이미지 취득
	 * @return main_img 메인이미지
	 */
	public String getMain_img() {
		return StringUtil.nvl(this.main_img);
	}

	/**
	 * 아이콘이미지 설정
	 * @param icon_img 아이콘이미지
	 */
	public void setIcon_img(String icon_img) {
		this.icon_img = icon_img;
	}
	/**
	 * 아이콘이미지 취득
	 * @return icon_img 아이콘이미지
	 */
	public String getIcon_img() {
		return StringUtil.nvl(this.icon_img);
	}

	/**
	 * 하위아이템 개수 설정
	 * @param sub_cnt 하위아이템 개수
	 */
	public void setSub_cnt(String sub_cnt) {
		this.sub_cnt = sub_cnt;
	}
	/**
	 * 하위아이템 개수 취득
	 * @return sub_cnt 하위아이템 개수
	 */
	public String getSub_cnt() {
		return StringUtil.nvl(this.sub_cnt);
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

	/**
	 * SubApp명 설정
	 * @param sa_nm SubApp명
	 */
	public void setSa_nm(String sa_nm) {
		this.sa_nm = sa_nm;
	}
	/**
	 * SubApp명 취득
	 * @return sa_nm SubApp명
	 */
	public String getSa_nm() {
		return StringUtil.nvl(this.sa_nm);
	}
	
	/**
	 * SubApp이미지 설정
	 * @param sub_img SubApp이미지
	 */
	public void setSub_img(String sub_img) {
		this.sub_img = sub_img;
	}
	/**
	 * SubApp이미지 취득
	 * @return sub_img SubApp이미지
	 */
	public String getSub_img() {
		return StringUtil.nvl(this.sub_img);
	}
	
	/**
	 * 자재내역 설정
	 * @param maktx 자재내역
	 */
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	/**
	 * 자재내역 취득
	 * @return maktx 자재내역
	 */
	public String getMaktx() {
		return StringUtil.nvl(this.maktx);
	}

	/**
	 * 형변+CB(Metric) 설정
	 * @param designation 형변+CB(Metric)
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * 형변+CB(Metric) 취득
	 * @return designation 형변+CB(Metric)
	 */
	public String getDesignation() {
		return StringUtil.nvl(this.designation);
	}

	/**
	 * 형변+CB(Inch) 설정
	 * @param designation_inch 형변+CB(Inch)
	 */
	public void setDesignation_inch(String designation_inch) {
		this.designation_inch = designation_inch;
	}
	/**
	 * 형변+CB(Inch) 취득
	 * @return designation_inch 형변+CB(Inch)
	 */
	public String getDesignation_inch() {
		return StringUtil.nvl(this.designation_inch);
	}

	/**
	 * 규격코드 설정
	 * @param cb 규격코드
	 */
	public void setCb(String cb) {
		this.cb = cb;
	}
	/**
	 * 규격코드 취득
	 * @return cb 규격코드
	 */
	public String getCb() {
		return StringUtil.nvl(this.cb);
	}

	/**
	 * 규격코드(Inch) 설정
	 * @param cb_inch 규격코드(Inch)
	 */
	public void setCb_inch(String cb_inch) {
		this.cb_inch = cb_inch;
	}
	/**
	 * 규격코드(Inch) 취득
	 * @return cb_inch 규격코드(Inch)
	 */
	public String getCb_inch() {
		return StringUtil.nvl(this.cb_inch);
	}

	/**
	 * 재종(재질) 설정
	 * @param grade 재종(재질)
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 재종(재질) 취득
	 * @return grade 재종(재질)
	 */
	public String getGrade() {
		return StringUtil.nvl(this.grade);
	}

	/**
	 * 해외재종 설정
	 * @param grade_os 해외재종
	 */
	public void setGrade_os(String grade_os) {
		this.grade_os = grade_os;
	}
	/**
	 * 해외재종 취득
	 * @return grade_os 해외재종
	 */
	public String getGrade_os() {
		return StringUtil.nvl(this.grade_os);
	}

	/**
	 * Metric/Inch/Both Code 설정
	 * @param unit_cd Metric/Inch/Both Code
	 */
	public void setUnit_cd(String unit_cd) {
		this.unit_cd = unit_cd;
	}
	/**
	 * Metric/Inch/Both Code 취득
	 * @return unit_cd Metric/Inch/Both Code
	 */
	public String getUnit_cd() {
		return StringUtil.nvl(this.unit_cd);
	}

	/**
	 * Tipped tool 설정
	 * @param tips_cd Tipped tool
	 */
	public void setTips_cd(String tips_cd) {
		this.tips_cd = tips_cd;
	}
	/**
	 * Tipped tool 취득
	 * @return tips_cd Tipped tool
	 */
	public String getTips_cd() {
		return StringUtil.nvl(this.tips_cd);
	}

	/**
	 * 정가(원화) 설정
	 * @param k_kbetr 정가(원화)
	 */
	public void setK_kbetr(String k_kbetr) {
		this.k_kbetr = k_kbetr;
	}
	/**
	 * 정가(원화) 취득
	 * @return k_kbetr 정가(원화)
	 */
	public String getK_kbetr() {
		return StringUtil.nvl(this.k_kbetr);
	}

	/**
	 * 정가(달러화) 설정
	 * @param u_kbetr 정가(달러화)
	 */
	public void setU_kbetr(String u_kbetr) {
		this.u_kbetr = u_kbetr;
	}
	/**
	 * 정가(달러화) 취득
	 * @return u_kbetr 정가(달러화)
	 */
	public String getU_kbetr() {
		return StringUtil.nvl(this.u_kbetr);
	}

	/**
	 * 정가(유로화) 설정
	 * @param e_kbetr 정가(유로화)
	 */
	public void setE_kbetr(String e_kbetr) {
		this.e_kbetr = e_kbetr;
	}
	/**
	 * 정가(유로화) 취득
	 * @return e_kbetr 정가(유로화)
	 */
	public String getE_kbetr() {
		return StringUtil.nvl(this.e_kbetr);
	}

	/**
	 * 지사별 재고수량 설정
	 * @param labst 지사별 재고수량
	 */
	public void setLabst(String labst) {
		this.labst = labst;
	}
	/**
	 * 지사별 재고수량 취득
	 * @return labst 지사별 재고수량
	 */
	public String getLabst() {
		return StringUtil.nvl(this.labst);
	}

	/**
	 * 최소주문수량코드 설정
	 * @param moq 최소주문수량코드
	 */
	public void setMoq(String moq) {
		this.moq = moq;
	}
	/**
	 * 최소주문수량코드 취득
	 * @return moq 최소주문수량코드
	 */
	public String getMoq() {
		return StringUtil.nvl(this.moq);
	}

	/**
	 * 최소주문수량 설정
	 * @param moq_qty 최소주문수량
	 */
	public void setMoq_qty(String moq_qty) {
		this.moq_qty = moq_qty;
	}
	/**
	 * 최소주문수량 취득
	 * @return moq_qty 최소주문수량
	 */
	public String getMoq_qty() {
		return StringUtil.nvl(this.moq_qty);
	}

	/**
	 * LCS코드 설정
	 * @param lcs LCS코드
	 */
	public void setLcs(String lcs) {
		this.lcs = lcs;
	}
	/**
	 * LCS코드 취득
	 * @return lcs LCS코드
	 */
	public String getLcs() {
		return StringUtil.nvl(this.lcs);
	}

	/**
	 * LCS코드설명 설정
	 * @param lcs_txt LCS코드설명
	 */
	public void setLcs_txt(String lcs_txt) {
		this.lcs_txt = lcs_txt;
	}
	/**
	 * LCS코드설명 취득
	 * @return lcs_txt LCS코드설명
	 */
	public String getLcs_txt() {
		return StringUtil.nvl(this.lcs_txt);
	}

	/**
	 * SC코드 설정
	 * @param labor SC코드
	 */
	public void setLabor(String labor) {
		this.labor = labor;
	}
	/**
	 * SC코드 취득
	 * @return labor SC코드
	 */
	public String getLabor() {
		return StringUtil.nvl(this.labor);
	}

	/**
	 * 신제품출시일 설정
	 * @param new_fr_dt 신제품출시일
	 */
	public void setNew_fr_dt(String new_fr_dt) {
		this.new_fr_dt = new_fr_dt;
	}
	/**
	 * 신제품출시일 취득
	 * @return new_fr_dt 신제품출시일
	 */
	public String getNew_fr_dt() {
		return StringUtil.nvl(this.new_fr_dt);
	}

	/**
	 * 신제품종료일 설정
	 * @param new_to_dt 신제품종료일
	 */
	public void setNew_to_dt(String new_to_dt) {
		this.new_to_dt = new_to_dt;
	}
	/**
	 * 신제품종료일 취득
	 * @return new_to_dt 신제품종료일
	 */
	public String getNew_to_dt() {
		return StringUtil.nvl(this.new_to_dt);
	}

	/**
	 * 이관구분 설정
	 * @param esc_gb 이관구분
	 */
	public void setEsc_gb(String esc_gb) {
		this.esc_gb = esc_gb;
	}
	/**
	 * 이관구분 취득
	 * @return esc_gb 이관구분
	 */
	public String getEsc_gb() {
		return StringUtil.nvl(this.esc_gb);
	}

	/**
	 * 이관일자 설정
	 * @param esc_dt 이관일자
	 */
	public void setEsc_dt(String esc_dt) {
		this.esc_dt = esc_dt;
	}
	/**
	 * 이관일자 취득
	 * @return esc_dt 이관일자
	 */
	public String getEsc_dt() {
		return StringUtil.nvl(this.esc_dt);
	}

	/**
	 * KORLOY(GTC_vender_id) 설정
	 * @param vender_id KORLOY(GTC_vender_id)
	 */
	public void setVender_id(String vender_id) {
		this.vender_id = vender_id;
	}
	/**
	 * KORLOY(GTC_vender_id) 취득
	 * @return vender_id KORLOY(GTC_vender_id)
	 */
	public String getVender_id() {
		return StringUtil.nvl(this.vender_id);
	}

	/**
	 * 사용 및 노출여부 설정
	 * @param use_yn 사용 및 노출여부
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * 사용 및 노출여부 취득
	 * @return use_yn 사용 및 노출여부
	 */
	public String getUse_yn() {
		return StringUtil.nvl(this.use_yn);
	}

	/**
	 * 컨텐츠 유입경로 구분 설정
	 * @param gubun 컨텐츠 유입경로 구분
	 */
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	/**
	 * 컨텐츠 유입경로 구분 취득
	 * @return gubun 컨텐츠 유입경로 구분
	 */
	public String getGubun() {
		return StringUtil.nvl(this.gubun);
	}

	/**
	 * 인치구분 설정
	 * @param inch_gb 인치구분
	 */
	public void setInch_gb(String inch_gb) {
		this.inch_gb = inch_gb;
	}
	/**
	 * 인치구분 취득
	 * @return inch_gb 인치구분
	 */
	public String getInch_gb() {
		return StringUtil.nvl(this.inch_gb);
	}
	
	/**
	 * Header1 코드 설정
	 * @param tm_cd Header1 코드
	 */
	public void setTm_cd(String tm_cd) {
		this.tm_cd = tm_cd;
	}
	/**
	 * Header1 코드 취득
	 * @return tm_cd Header1 코드
	 */
	public String getTm_cd() {
		return StringUtil.nvl(this.tm_cd);
	}
	
	/**
	 * Header1 Name 설정
	 * @param tm_nm Header1 Name
	 */
	public void setTm_nm(String tm_nm) {
		this.tm_nm = tm_nm;
	}
	/**
	 * Header1 Name 취득
	 * @return tm_nm Header1 Name
	 */
	public String getTm_nm() {
		return StringUtil.nvl(this.tm_nm);
	}

	/**
	 * 브랜드 로고 설정
	 * @param brand_logo 브랜드 로고
	 */
	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}
	/**
	 * 브랜드 로고 취득
	 * @return brand_logo 브랜드 로고
	 */
	public String getBrand_logo() {
		return StringUtil.nvl(this.brand_logo);
	}

	/**
	 * Header1 Name(KR) 설정
	 * @param tm_kr_nm Header1 Name(KR)
	 */
	public void setTm_kr_nm(String tm_kr_nm) {
		this.tm_kr_nm = tm_kr_nm;
	}
	/**
	 * Header1 Name(KR) 취득
	 * @return tm_kr_nm Header1 Name(KR)
	 */
	public String getTm_kr_nm() {
		return StringUtil.nvl(this.tm_kr_nm);
	}

	/**
	 * Header1 Name(EN) 설정
	 * @param tm_en_nm Header1 Name(EN)
	 */
	public void setTm_en_nm(String tm_en_nm) {
		this.tm_en_nm = tm_en_nm;
	}
	/**
	 * Header1 Name(EN) 취득
	 * @return tm_en_nm Header1 Name(EN)
	 */
	public String getTm_en_nm() {
		return StringUtil.nvl(this.tm_en_nm);
	}
	
	/**
	 * 속성 설정
	 * @param symbol 속성
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * 속성 취득
	 * @return symbol 속성
	 */
	public String getSymbol() {
		return StringUtil.nvl(this.symbol);
	}

	/**
	 * 속성값 설정
	 * @param val 속성값
	 */
	public void setVal(String val) {
		this.val = val;
	}
	/**
	 * 속성값 취득
	 * @return val 속성값
	 */
	public String getVal() {
		return StringUtil.nvl(this.val);
	}
	
	/**
	 * 2D이미지(DXF) 설정
	 * @param dxf_img 2D이미지(DXF)
	 */
	public void setDxf_img(String dxf_img) {
		this.dxf_img = dxf_img;
	}
	/**
	 * 2D이미지(DXF) 취득
	 * @return dxf_img 2D이미지(DXF)
	 */
	public String getDxf_img() {
		return StringUtil.nvl(this.dxf_img);
	}
	
	/**
	 * 2D이미지(DXF) 썸네일 설정
	 * @param dxf_img_thumb 2D이미지(DXF) 썸네일
	 */
	public void setDxf_img_thumb(String dxf_img_thumb) {
		this.dxf_img_thumb = dxf_img_thumb;
	}
	/**
	 * 2D이미지(DXF) 썸네일 취득
	 * @return dxf_img_thumb 2D이미지(DXF) 썸네일
	 */
	public String getDxf_img_thumb() {
		return StringUtil.nvl(this.dxf_img_thumb);
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
	 * zaddean 취득
	 * @return zaddean zaddean
	 */
	public String getZaddean() {
		return StringUtil.nvl(zaddean);
	}
	/**
	 * zaddean 설정
	 * @param zaddean 검색 zaddean
	 */
	public void setZaddean(String zaddean) {
		this.zaddean = zaddean;
	}
	/**
	 * prop_title 취득
	 * @return prop_title prop_title
	 */
	public String getProp_title() {
		return prop_title;
	}
	/**
	 * prop_title 설정
	 * @param prop_title 검색 prop_title
	 */
	public void setProp_title(String prop_title) {
		this.prop_title = prop_title;
	}
	/**
	 * prop_n_title 취득
	 * @return prop_n_title prop_n_title
	 */
	public String getProp_n_title() {
		return prop_n_title;
	}
	/**
	 * prop_n_title 설정
	 * @param prop_n_title 검색 prop_n_title
	 */
	public void setProp_n_title(String prop_n_title) {
		this.prop_n_title = prop_n_title;
	}
	
	

}
