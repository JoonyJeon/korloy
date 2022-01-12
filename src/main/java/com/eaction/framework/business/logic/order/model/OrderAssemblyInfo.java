/*****************************************************************************
 * 프로그램명  : OrderAssemblyInfo.java
 * 설     명  : 어셈블리 견적 요청 관리 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.28   YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.order.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.util.StringUtil;

/**
 * 어셈블리 견적 요청 데이터 빈
 * @author  eaction
 * @version 1.0
 */
@Alias("OrderAssemblyInfo")
public class OrderAssemblyInfo {
/* ASSEMBLY */ 

	/** 어셈블리 일련번호 */
	private String assem_no = "";
	/** 사용자 ID */
	private String user_id = "";
	/** 어셈블리명 */
	private String assem_nm = "";
	/** 어셈블리 설명 */
	private String assem_desc = "";
	/** 자재코드 */
	private String matnr = "";
	/** 형번 */
	private String designation = "";
	/** 등급 */
	private String grade = "";
	/** Basic STP(3D)파일 */
	private String stp_b_file = "";
	/** Detail STP(3D)파일 */
	private String stp_d_file = "";
	/** DXF(2D)파일 */
	private String dxf_file = "";
	/** GTC파일 생성 경로 */
	private String gtc_file = "";
	/** P21파일 생성 경로 */
	private String p21_file = "";
	/** 사용 여부 */
	private String use_yn = "";
/* ASSEMBLY ----------------------- */
	
/* 가격금액수량등 */
	/** 통화 */
	private String cur = "";
	/** 수량 */
	private String qty = "";
	/** 재고여부 */
	private String stock_yn = "";
	/** 가격 */
	private String price = "";
	/** 정가(원화) */
	private String k_kbetr = "";
	/** 정가(달러화) */
	private String u_kbetr = "";
	/** 정가(유로화) */
	private String e_kbetr = "";
	/** 합계액(원화) */
	private String total_k_kbetr = "";
	/** 합계액(달러화) */
	private String total_u_kbetr = "";
	/** 합계액(유로화) */
	private String total_e_kbetr = "";
/* 가격금액수량등  ----------------------- */

/* ASSEMBLY */ 
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
	 * 사용자 ID 설정
	 * @param user_id 사용자 ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 사용자 ID 취득
	 * @return user_id 사용자 ID
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
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
	 * 어셈블리 설명 설정
	 * @param assem_desc 어셈블리 설명
	 */
	public void setAssem_desc(String assem_desc) {
		this.assem_desc = assem_desc;
	}
	/**
	 * 어셈블리 설명 취득
	 * @return assem_desc 어셈블리 설명
	 */
	public String getAssem_desc() {
		return StringUtil.nvl(this.assem_desc);
	}

	/**
	 * 자재코드 설정
	 * @param matnr 자재코드
	 */
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	/**
	 * 자재코드 취득
	 * @return matnr 자재코드
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
	 * 등급 설정
	 * @param grade 등급
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 등급 취득
	 * @return grade 등급
	 */
	public String getGrade() {
		return StringUtil.nvl(this.grade);
	}

	/**
	 * Basic STP(3D)파일 설정
	 * @param stp_b_file Basic STP(3D)파일
	 */
	public void setStp_b_file(String stp_b_file) {
		this.stp_b_file = stp_b_file;
	}
	/**
	 * Basic STP(3D)파일 취득
	 * @return stp_b_file Basic STP(3D)파일
	 */
	public String getStp_b_file() {
		return StringUtil.nvl(this.stp_b_file);
	}

	/**
	 * Detail STP(3D)파일 설정
	 * @param stp_d_file Detail STP(3D)파일
	 */
	public void setStp_d_file(String stp_d_file) {
		this.stp_d_file = stp_d_file;
	}
	/**
	 * Detail STP(3D)파일 취득
	 * @return stp_d_file Detail STP(3D)파일
	 */
	public String getStp_d_file() {
		return StringUtil.nvl(this.stp_d_file);
	}

	/**
	 * DXF(2D)파일 설정
	 * @param dxf_file DXF(2D)파일
	 */
	public void setDxf_file(String dxf_file) {
		this.dxf_file = dxf_file;
	}
	/**
	 * DXF(2D)파일 취득
	 * @return dxf_file DXF(2D)파일
	 */
	public String getDxf_file() {
		return StringUtil.nvl(this.dxf_file);
	}

	/**
	 * GTC파일 생성 경로 설정
	 * @param gtc_file GTC파일 생성 경로
	 */
	public void setGtc_file(String gtc_file) {
		this.gtc_file = gtc_file;
	}
	/**
	 * GTC파일 생성 경로 취득
	 * @return gtc_file GTC파일 생성 경로
	 */
	public String getGtc_file() {
		return StringUtil.nvl(this.gtc_file);
	}

	/**
	 * P21파일 생성 경로 설정
	 * @param p21_file P21파일 생성 경로
	 */
	public void setP21_file(String p21_file) {
		this.p21_file = p21_file;
	}
	/**
	 * P21파일 생성 경로 취득
	 * @return p21_file P21파일 생성 경로
	 */
	public String getP21_file() {
		return StringUtil.nvl(this.p21_file);
	}

	/**
	 * 사용 여부 설정
	 * @param use_yn 사용 여부
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * 사용 여부 취득
	 * @return use_yn 사용 여부
	 */
	public String getUse_yn() {
		return StringUtil.nvl(this.use_yn);
	}
/* ASSEMBLY ----------------------- */ 

/* 가격금액수량등 */
	/**
	 * 통화 설정
	 * @param cur 통화
	 */
	public void setCur(String cur) {
		this.cur = cur;
	}
	/**
	 * 통화 취득
	 * @return cur 통화
	 */
	public String getCur() {
		return StringUtil.nvl(this.cur);
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
	 * 재고여부 설정
	 * @param stock_yn 재고여부
	 */
	public void setStock_yn(String stock_yn) {
		this.stock_yn = stock_yn;
	}
	/**
	 * 재고여부 취득
	 * @return stock_yn 재고여부
	 */
	public String getStock_yn() {
		return StringUtil.nvl(this.stock_yn);
	}

	/**
	 * 가격 설정
	 * @param price 총가격
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 가격 취득
	 * @return price 총가격
	 */
	public String getPrice() {
		return StringUtil.nvl(this.price);
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
	 * 합계액(원화) 설정
	 * @param total_k_kbetr 합계액(원화)
	 */
	public void setTotal_k_kbetr(String total_k_kbetr) {
		this.total_k_kbetr = total_k_kbetr;
	}
	/**
	 * 합계액(원화) 취득
	 * @return total_k_kbetr 합계액(원화)
	 */
	public String getTotal_k_kbetr() {
		return StringUtil.nvl(this.total_k_kbetr);
	}

	/**
	 * 합계액(달러화) 설정
	 * @param total_u_kbetr 합계액(달러화)
	 */
	public void setTotal_u_kbetr(String total_u_kbetr) {
		this.total_u_kbetr = total_u_kbetr;
	}
	/**
	 * 합계액(달러화) 취득
	 * @return total_u_kbetr 합계액(달러화)
	 */
	public String getTotal_u_kbetr() {
		return StringUtil.nvl(this.total_u_kbetr);
	}

	/**
	 * 합계액(유로화) 설정
	 * @param total_e_kbetr 합계액(유로화)
	 */
	public void setTotal_e_kbetr(String total_e_kbetr) {
		this.total_e_kbetr = total_e_kbetr;
	}
	/**
	 * 합계액(유로화) 취득
	 * @return total_e_kbetr 합계액(유로화)
	 */
	public String getTotal_e_kbetr() {
		return StringUtil.nvl(this.total_e_kbetr);
	}
/* 가격금액수량등  ----------------------- */
}
