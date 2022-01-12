/*****************************************************************************
 * 프로그램명  : OrderInfo.java
 * 설     명  : 견적 요청 관리 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.01   YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.order.model;

import java.util.Arrays;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;

/**
 * 견적 요청 데이터 빈
 * @author  eaction
 * @version 1.0
 */
@Alias("OrderInfo")
public class OrderInfo extends EactReqInfo {

	/** 일련번호 */
	private int order_no = 0;
	/** 회원번호 */
	private String user_id = null;
	/** 카트번호 */
	private String cart_no = "";
	/** 성 */
	private String sur_name = "";
	/** 이름 */
	private String name = "";
	/** 이메일주소 */
	private String email = "";
	/** 회사명 */
	private String company = "";
	/** 회사 이메일주소 */
	private String cemail = "";
	/** 국가 */
	private String user_nation = "";
	/** 비고 */
	private String bigo = "";
	/** 장바구니에서 주문서로 이동시 선택한 cart_no를 담을 배열 */
	private String[] cart_no_arr = null;

	/** 엑셀 재다운 */
	private String first_excel = "";
	/** 삭제할엑셀파일경로 */
	private String excel_file_path = "";
	/** 파일명 */
	private String atch_file_name = "";

	/** 어셈번호 */
	private String assem_no = "";
	/** 어셈블리에서 주문서로 이동시 선택한 assem_no를 담을 배열 */
	private String[] assem_no_arr = null;
	/** 어셈블리명 */
	private String assem_nm = "";

	/** 세션통화값 */
	private String comm_cur = "";
	/** 수량 */
	private String qty = "";
	/** 총가격 */
	private String total_price = "";

	/** 정가(원화) */
	private String k_kbetr = "";
	/** 정가(달러화) */
	private String u_kbetr = "";
	/** 정가(유로화) */
	private String e_kbetr = "";
	

	/** 수신자 */
	private String email_to = "";
	/** 참조자1 */
	private String email_cc1 = "";
	/** 참조자2 */
	private String email_cc2 = "";




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
	 * 일련번호 설정
	 * @param order_no 일련번호
	 */
	public void setAssem_no(String assem_no) {
		this.assem_no = assem_no;
	}
	/**
	 * 일련번호 취득
	 * @return order_no 일련번호
	 */
	public String getAssem_no() {
		return StringUtil.nvl(this.assem_no);
	}

	/**
	 * 일련번호 설정
	 * @param order_no 일련번호
	 */
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	/**
	 * 일련번호 취득
	 * @return order_no 일련번호
	 */
	public int getOrder_no() {
		return this.order_no;
	}

	/**
	 * 회원번호 설정
	 * @param user_id 회원번호
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 회원번호 취득
	 * @return user_id 회원번호
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
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
	 * 성 설정
	 * @param sur_name 성
	 */
	public void setSur_name(String sur_name) {
		this.sur_name = sur_name;
	}
	/**
	 * 성 취득
	 * @return sur_name 성
	 */
	public String getSur_name() {
		return StringUtil.nvl(this.sur_name);
	}

	/**
	 * 이름 설정
	 * @param name 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 이름 취득
	 * @return name 이름
	 */
	public String getName() {
		return StringUtil.nvl(this.name);
	}

	/**
	 * 이메일주소 설정
	 * @param email 이메일주소
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 이메일주소 취득
	 * @return email 이메일주소
	 */
	public String getEmail() {
		return StringUtil.nvl(this.email);
	}

	/**
	 * 회사명 설정
	 * @param company 회사명
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 회사명 취득
	 * @return company 회사명
	 */
	public String getCompany() {
		return StringUtil.nvl(this.company);
	}

	/**
	 * 회사 이메일주소 설정
	 * @param cemail 회사 이메일주소
	 */
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	/**
	 * 회사 이메일주소 취득
	 * @return cemail 회사 이메일주소
	 */
	public String getCemail() {
		return StringUtil.nvl(this.cemail);
	}

	/**
	 * 국가 설정
	 * @param user_nation 국가
	 */
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	/**
	 * 국가 취득
	 * @return user_nation 국가
	 */
	public String getUser_nation() {
		return StringUtil.nvl(this.user_nation);
	}

	/**
	 * 비고 설정
	 * @param bigo 비고
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * 비고 취득
	 * @return bigo 비고
	 */
	public String getBigo() {
		return StringUtil.nvl(this.bigo);
	}

	/**
	 * 장바구니에서 주문서로 이동시 선택한 cart_no를 닮을 배열 설정
	 * @param cart_no_arr 장바구니에서 주문서로 이동시 선택한 cart_no를 닮을 배열
	 */
	public void setCart_no_arr(String[] cart_no_arr) {
		if(cart_no_arr != null){
			this.cart_no_arr = Arrays.copyOf(cart_no_arr, cart_no_arr.length);
		}
	}
	/**
	 * 장바구니에서 주문서로 이동시 선택한 cart_no를 닮을 배열 취득
	 * @return cart_no_arr 장바구니에서 주문서로 이동시 선택한 cart_no를 닮을 배열
	 */
	public String[] getCart_no_arr() {
		if(this.cart_no_arr != null){
			return Arrays.copyOf(this.cart_no_arr, this.cart_no_arr.length);
		} else {
			return null;
		}
	}

	/**
	 * 엑셀 재다운 설정
	 * @param first_excel 엑셀 재다운
	 */
	public void setFirst_excel(String first_excel) {
		this.first_excel = first_excel;
	}
	/**
	 * 엑셀 재다운 취득
	 * @return first_excel 엑셀 재다운
	 */
	public String getFirst_excel() {
		return StringUtil.nvl(this.first_excel);
	}

	/**
	 * 삭제할엑셀파일경로 설정
	 * @param excel_file_path 삭제할엑셀파일경로
	 */
	public void setExcel_file_path(String excel_file_path) {
		this.excel_file_path = excel_file_path;
	}
	/**
	 * 삭제할엑셀파일경로 취득
	 * @return excel_file_path 삭제할엑셀파일경로
	 */
	public String getExcel_file_path() {
		return StringUtil.nvl(this.excel_file_path);
	}

	/**
	 * 파일명 설정
	 * @param atch_file_name 파일명
	 */
	public void setAtch_file_name(String atch_file_name) {
		this.atch_file_name = atch_file_name;
	}
	/**
	 * 파일명 취득
	 * @return atch_file_name 파일명
	 */
	public String getAtch_file_name() {
		return StringUtil.nvl(this.atch_file_name);
	}
	/**
	 * 어셈블리에서 주문서로 이동시 선택한 assem_no를 닮을 배열 설정
	 * @param assem_no_arr 장바구니에서 주문서로 이동시 선택한 assem_no를 닮을 배열
	 */
	public void setAssem_no_arr(String[] assem_no_arr) {
		if(assem_no_arr != null){
			this.assem_no_arr = Arrays.copyOf(assem_no_arr, assem_no_arr.length);
		}
	}
	/**
	 * 어셈블리에서 주문서로 이동시 선택한 assem_no를 닮을 배열 취득
	 * @return assem_no_arr 장바구니에서 주문서로 이동시 선택한 assem_no를 닮을 배열
	 */
	public String[] getAssem_no_arr() {
		if(this.assem_no_arr != null){
			return Arrays.copyOf(this.assem_no_arr, this.assem_no_arr.length);
		} else {
			return null;
		}
	}

	/**
	 * 세션통화값 설정
	 * @param comm_cur 세션통화값
	 */
	public void setComm_cur(String comm_cur) {
		this.comm_cur = comm_cur;
	}
	/**
	 * 세션통화값 취득
	 * @return comm_cur 세션통화값
	 */
	public String getComm_cur() {
		return StringUtil.nvl(this.comm_cur);
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
	 * 총가격 설정
	 * @param total_price 총가격
	 */
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	/**
	 * 총가격 취득
	 * @return total_price 총가격
	 */
	public String getTotal_price() {
		return StringUtil.nvl(this.total_price);
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
	 * 수신자 설정
	 * @param email_to 수신자
	 */
	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}
	/**
	 * 수신자 취득
	 * @return email_to 수신자
	 */
	public String getEmail_to() {
		return StringUtil.nvl(this.email_to);
	}

	/**
	 * 참조자1 설정
	 * @param email_cc1 참조자1
	 */
	public void setEmail_cc1(String email_cc1) {
		this.email_cc1 = email_cc1;
	}
	/**
	 * 참조자1 취득
	 * @return email_cc1 참조자1
	 */
	public String getEmail_cc1() {
		return StringUtil.nvl(this.email_cc1);
	}

	/**
	 * 참조자2 설정
	 * @param email_cc2 참조자2
	 */
	public void setEmail_cc2(String email_cc2) {
		this.email_cc2 = email_cc2;
	}
	/**
	 * 참조자2 취득
	 * @return email_cc2 참조자2
	 */
	public String getEmail_cc2() {
		return StringUtil.nvl(this.email_cc2);
	}

}
