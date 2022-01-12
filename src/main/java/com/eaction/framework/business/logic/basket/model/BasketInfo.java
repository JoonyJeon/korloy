/*****************************************************************************
 * 프로그램명  : BasketInfo.java
 * 설     명  : 장바구니 관리 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.28   YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basket.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;

/**
 * 장바구니관리 데이터 빈
 * @author  eaction
 * @version 1.0
 */
@Alias("BasketInfo")
public class BasketInfo extends EactReqInfo {
	
	/** 디바이스타입(M:모바일,T:태블릿,P:데스트탑) */
	private String device_type = "";

// TB_ECAT_BASKET_M
	/** 카트번호 */
	private String cart_no = "";
	/** 사용자ID */
	private String user_id = "";
	/** 카트설명 */
	private String cart_nm = "";
	/** 카트설명 */
	private String cart_desc = "";
	/** 총가격 */
	private String total_price = "";
	/** 사용여부 */
	private String m_use_yn = "";
// TB_ECAT_BASKET_M----------

// TB_ECAT_BASKET_D
	/** WERKS */
	private String werks = "";
	/** 자재코드 */
	private String matnr = "";
	/** 형번 */
	private String designation = "";
	/** 가격 */
	private String price = "";
	/** 등급 */
	private String grade = "";
	/** 통화 */
	private String cur = "";
	/** 수량 */
	private String qty = "";
	/** 재고여부 */
	private String stock_yn = "";
	/** 사용여부 */
	private String d_use_yn = "";
// TB_ECAT_BASKET_D----------
	//카트그룹에 해당하는 아이템 목록취득을 위한 collection
	List<BasketInfo> basket_detail_list = new ArrayList<>();
// TB_ECAT_CONT_F S -------------------
	
	/** 파일구분 */
	private String file_cd = "";
	/** 일련번호 */
	private int file_seq = 0;
	/** 파일형식 */
	private String file_typ = "";
	/** 물리적파일경로(전체) */
	private String file_phy_path = "";
	/** 다운로드파일경로(웹) */
	private String file_dwl_path = "";
	/** 원본파일명 */
	private String file_org_nm = "";
	/** 썸네일파일명 */
	private String file_thn_nm = "";
	/** 삭제할엑셀파일경로 */
	private String excel_file_path = "";
	/** 파일명 */
	private String atch_file_name = "";
	/** 삭제 혹은 취득할 gtc파일경로 */
	private String gtc_file_path = "";
	/** gtc 파일명 */
	private String gtc_file_name = "";
	/** gtc 파일명 */
	private String comm_cur = "";
// TB_ECAT_CONT_F E -------------------
	
	/*다중 추가 수정 삭제를 위한 파라미터*/
	private String addParam = "";
	private String editParam = "";
	private String[] deleteParam = null;
	
	
	public String getComm_cur() {
		return StringUtil.nvl(this.comm_cur);
	}
	public void setComm_cur(String comm_cur) {
		this.comm_cur = comm_cur;
	}
	//아이템 레이어 팝업을 위한 변수
	private String ig_cd = "";
	

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

	
	public String getGtc_file_path() {
		return gtc_file_path;
	}
	public void setGtc_file_path(String gtc_file_path) {
		this.gtc_file_path = gtc_file_path;
	}
	public String getGtc_file_name() {
		return gtc_file_name;
	}
	public void setGtc_file_name(String gtc_file_name) {
		this.gtc_file_name = gtc_file_name;
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

	
// TB_ECAT_BASKET_M
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
	 * 사용자ID 설정
	 * @param user_id 사용자ID
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 사용자ID 취득
	 * @return user_id 사용자ID
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
	}
	/**
	 * 카트이름 취득
	 * @return cart_nm 카트이름
	 */
	public String getCart_nm() {
		return StringUtil.nvl(cart_nm);
	}
	/**
	 * 카트이름 설정
	 * @param cart_nm 카트이름
	 */
	public void setCart_nm(String cart_nm) {
		this.cart_nm = cart_nm;
	}
	/**
	 * 카트설명 설정
	 * @param cart_desc 카트설명
	 */
	public void setCart_desc(String cart_desc) {
		this.cart_desc = cart_desc;
	}
	/**
	 * 카트설명 취득
	 * @return cart_desc 카트설명
	 */
	public String getCart_desc() {
		return StringUtil.nvl(this.cart_desc);
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
		return this.total_price;
	}

	/**
	 * 사용여부 설정
	 * @param use_yn 사용여부
	 */
	public void setM_use_yn(String m_use_yn) {
		this.m_use_yn = m_use_yn;
	}
	/**
	 * 사용여부 취득
	 * @return use_yn 사용여부
	 */
	public String getM_use_yn() {
		return StringUtil.nvl(this.m_use_yn);
	}
// TB_ECAT_BASKET_M----------
	
// TB_ECAT_BASKET_D
	/**
	 * WERKS 설정
	 * @param werks WERKS
	 */
	public void setWerks(String werks) {
		this.werks = werks;
	}
	/**
	 * WERKS 취득
	 * @return werks WERKS
	 */
	public String getWerks() {
		return StringUtil.nvl(this.werks);
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
	 * 가격 설정
	 * @param price 가격
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * 가격 취득
	 * @return price 가격
	 */
	public String getPrice() {
		return this.price;
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
	 * 사용여부 설정
	 * @param use_yn 사용여부
	 */
	public void setD_use_yn(String d_use_yn) {
		this.d_use_yn = d_use_yn;
	}
	/**
	 * 사용여부 취득
	 * @return use_yn 사용여부
	 */
	public String getD_use_yn() {
		return StringUtil.nvl(this.d_use_yn);
	}
// TB_ECAT_BASKET_D----------
	/**
	 * 카트그룹에 해당하는 아이템 목록 취득
	 * @return itemList 카트그룹에 해당하는 아이템 목록
	 */
	public List<BasketInfo> getBasket_detail_list() {
		return basket_detail_list;
	}
	/**
	 * 카트그룹에 해당하는 아이템 목록 설정
	 * @param itemList 카트그룹에 해당하는 아이템 목록
	 */
	public void setBasket_detail_list(List<BasketInfo> basket_detail_list) {
		this.basket_detail_list = basket_detail_list;
	}
	
//	TB_ECAT_CONT_F ---------------------------------
	/**
	 * 파일구분 설정
	 * @param file_cd 파일구분
	 */
	public void setFile_cd(String file_cd) {
		this.file_cd = file_cd;
	}
	/**
	 * 파일구분 취득
	 * @return file_cd 파일구분
	 */
	public String getFile_cd() {
		return StringUtil.nvl(this.file_cd);
	}

	/**
	 * 일련번호 설정
	 * @param file_seq 일련번호
	 */
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	/**
	 * 일련번호 취득
	 * @return file_seq 일련번호
	 */
	public int getFile_seq() {
		return this.file_seq;
	}

	/**
	 * 파일형식 설정
	 * @param file_typ 파일형식
	 */
	public void setFile_typ(String file_typ) {
		this.file_typ = file_typ;
	}
	/**
	 * 파일형식 취득
	 * @return file_typ 파일형식
	 */
	public String getFile_typ() {
		return StringUtil.nvl(this.file_typ);
	}

	/**
	 * 물리적파일경로(전체) 설정
	 * @param file_phy_path 물리적파일경로(전체)
	 */
	public void setFile_phy_path(String file_phy_path) {
		this.file_phy_path = file_phy_path;
	}
	/**
	 * 물리적파일경로(전체) 취득
	 * @return file_phy_path 물리적파일경로(전체)
	 */
	public String getFile_phy_path() {
		return StringUtil.nvl(this.file_phy_path);
	}

	/**
	 * 다운로드파일경로(웹) 설정
	 * @param file_dwl_path 다운로드파일경로(웹)
	 */
	public void setFile_dwl_path(String file_dwl_path) {
		this.file_dwl_path = file_dwl_path;
	}
	/**
	 * 다운로드파일경로(웹) 취득
	 * @return file_dwl_path 다운로드파일경로(웹)
	 */
	public String getFile_dwl_path() {
		return StringUtil.nvl(this.file_dwl_path);
	}

	/**
	 * 원본파일명 설정
	 * @param file_org_nm 원본파일명
	 */
	public void setFile_org_nm(String file_org_nm) {
		this.file_org_nm = file_org_nm;
	}
	/**
	 * 원본파일명 취득
	 * @return file_org_nm 원본파일명
	 */
	public String getFile_org_nm() {
		return StringUtil.nvl(this.file_org_nm);
	}

	/**
	 * 썸네일파일명 설정
	 * @param file_thn_nm 썸네일파일명
	 */
	public void setFile_thn_nm(String file_thn_nm) {
		this.file_thn_nm = file_thn_nm;
	}
	/**
	 * 썸네일파일명 취득
	 * @return file_thn_nm 썸네일파일명
	 */
	public String getFile_thn_nm() {
		return StringUtil.nvl(this.file_thn_nm);
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
	 * 추가할 장바구니 정보 취득
	 * @return addParam 추가할 장바구니 정보
	 */
	public String getAddParam() {
		return StringUtil.nvl(addParam);
	}
	/**
	 * 추가할 장바구니 정보 설정
	 * @return addParam 추가할 장바구니 정보
	 */
	public void setAddParam(String addParam) {
		this.addParam = addParam;
	}
	/**
	 * 수정할 장바구니 정보 취득
	 * @return editParam 수정할 장바구니 정보
	 */
	public String getEditParam() {
		return StringUtil.nvl(editParam);
	}
	/**
	 * 수정할 장바구니 정보 설정
	 * @return editParam 수정할 장바구니 정보
	 */
	public void setEditParam(String editParam) {
		this.editParam = editParam;
	}
	/**
	 * 삭제할 장바구니 배열 설정
	 * @param ma_cd_arr Main Application 검색 배열
	 */
	public String[] getDeleteParam() {
		if(this.deleteParam != null){
			return Arrays.copyOf(this.deleteParam, this.deleteParam.length);
		} else {
			return null;
		}
	}
	/**
	 * 삭제할 장바구니 배열 설정
	 * @param deleteParam Main Application 검색 배열
	 */
	public void setDeleteParam(String[] deleteParam) {
		if(deleteParam != null){
			this.deleteParam = Arrays.copyOf(deleteParam, deleteParam.length);
		}
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

}
