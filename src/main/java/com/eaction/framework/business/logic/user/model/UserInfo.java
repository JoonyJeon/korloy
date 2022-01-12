/*****************************************************************************
 * 프로그램명  : UserInfo.java
 * 설     명  : 회원 관리 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.21  YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.user.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;

/**
 * 회원관리 데이터 빈
 * @author eaction
 * @version 1.0
 */
@Alias("UserInfo")
public class UserInfo extends EactReqInfo {

	/** 사용자아이디*/
	private String user_id = "";
	/** 고객명 */
	private String user_name = "";
	/** 고객암호 */
	private String user_pwd = "";
	/** 고객무선번호 */
	private String user_mobile = "";
	/** 고객회사 */
	private String user_company = "";
	/** 고객이메일 */
	private String user_com_mail = "";
	/** 고객회사주소 */
	private String user_com_addr = "";
	/** 고객직책 */
	private String user_position = "";
	/** 고객그룹 */
	private String user_grp = "";
	/** 가입지역 */
	private String user_nation = "";
	/** 단위코드 */
	private String unit_cd = "";
	/** 통화코드 */
	private String curr_cd = "";
	/** 통화코드명 */
	private String curr_nm = "";
	/** 유저담당자 */
	private String emp_id = "";
	/** 거래처코드 */
	private String account_cd = "";
	/** 추가정보 */
	private String bigo = "";
	/** 사용여부 */
	private String use_yn = "";
	/** 회원PWD일치여부 */
	private String pw_true_false = "";
	
	/** confirm URL */
	private String confirm_url = "";
	/** 이메일인증키 */
	private String email_cert_key = "";
	/** 이메일인증일시 */
	private String email_cert_date = "";
	/** 이메일인증여부 */
	private String email_cert_yn = "";

	/** 승급요청 담당자 */
	private String ug_emp = "";
	/** 회원 변경요청 비밀번호 */
	private String old_user_pwd = "";
	/** 고객회사 전화번호 */
	private String user_com_tel = "";

	/** 수신자 */
	private String email_to = "";
	/** 참조자1 */
	private String email_cc1 = "";
	/** 참조자2 */
	private String email_cc2 = "";

	/** 국가 명 */
	private String nation_txt = "";
	/** 언어 명 */
	private String lang_txt = "";
	/** 단위 명 */
	private String unit_txt = "";
	/** 통화 명 */
	private String curr_txt = "";


	/**
	 * 사용자아이디 설정
	 * @param str 사용자아이디
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * 사용자아이디 취득
	 * @return String 사용자아이디
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
	}
	
	/**
	 * 고객명 설정
	 * @param user_name 고객명
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * 고객명 취득
	 * @return user_name 고객명
	 */
	public String getUser_name() {
		return StringUtil.nvl(this.user_name);
	}

	/**
	 * 고객암호 설정
	 * @param user_pwd 고객암호
	 */
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	/**
	 * 고객암호 취득
	 * @return user_pwd 고객암호
	 */
	public String getUser_pwd() {
		return StringUtil.nvl(this.user_pwd);
	}

	/**
	 * 고객무선번호 설정
	 * @param user_mobile 고객무선번호
	 */
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	/**
	 * 고객무선번호 취득
	 * @return user_mobile 고객무선번호
	 */
	public String getUser_mobile() {
		return StringUtil.nvl(this.user_mobile);
	}

	/**
	 * 고객회사 설정
	 * @param user_company 고객회사
	 */
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}
	/**
	 * 고객회사 취득
	 * @return user_company 고객회사
	 */
	public String getUser_company() {
		return StringUtil.nvl(this.user_company);
	}

	/**
	 * 고객이메일 설정
	 * @param user_com_mail 고객이메일
	 */
	public void setUser_com_mail(String user_com_mail) {
		this.user_com_mail = user_com_mail;
	}
	/**
	 * 고객이메일 취득
	 * @return user_com_mail 고객이메일
	 */
	public String getUser_com_mail() {
		return StringUtil.nvl(this.user_com_mail);
	}

	/**
	 * 고객회사주소 설정
	 * @param user_com_addr 고객회사주소
	 */
	public void setUser_com_addr(String user_com_addr) {
		this.user_com_addr = user_com_addr;
	}
	/**
	 * 고객회사주소 취득
	 * @return user_com_addr 고객회사주소
	 */
	public String getUser_com_addr() {
		return StringUtil.nvl(this.user_com_addr);
	}

	/**
	 * 고객직책 설정
	 * @param user_position 고객직책
	 */
	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}
	/**
	 * 고객직책 취득
	 * @return user_position 고객직책
	 */
	public String getUser_position() {
		return StringUtil.nvl(this.user_position);
	}

	/**
	 * 고객그룹 설정
	 * @param user_grp 고객그룹
	 */
	public void setUser_grp(String user_grp) {
		this.user_grp = user_grp;
	}
	/**
	 * 고객그룹 취득
	 * @return user_grp 고객그룹
	 */
	public String getUser_grp() {
		return StringUtil.nvl(this.user_grp);
	}

	/**
	 * 가입지역 설정
	 * @param user_nation 가입지역
	 */
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	/**
	 * 가입지역 취득
	 * @return user_nation 가입지역
	 */
	public String getUser_nation() {
		return StringUtil.nvl(this.user_nation);
	}

	/**
	 * 단위코드 설정
	 * @param unit_cd 단위코드
	 */
	public void setUnit_cd(String unit_cd) {
		this.unit_cd = unit_cd;
	}
	/**
	 * 단위코드 취득
	 * @return unit_cd 단위코드
	 */
	public String getUnit_cd() {
		return StringUtil.nvl(this.unit_cd);
	}

	/**
	 * 통화코드 설정
	 * @param curr_cd 통화코드
	 */
	public void setCurr_cd(String curr_cd) {
		this.curr_cd = curr_cd;
	}
	/**
	 * 통화코드 취득
	 * @return curr_cd 통화코드
	 */
	public String getCurr_cd() {
		return StringUtil.nvl(this.curr_cd);
	}

	/**
	 * 통화코드명 설정
	 * @param curr_nm 통화코드명
	 */
	public void setCurr_nm(String curr_nm) {
		this.curr_nm = curr_nm;
	}
	/**
	 * 통화코드명 취득
	 * @return curr_nm 통화코드명
	 */
	public String getCurr_nm() {
		return StringUtil.nvl(this.curr_nm);
	}

	/**
	 * 유저담당자 설정
	 * @param emp_id 유저담당자
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	/**
	 * 유저담당자 취득
	 * @return emp_id 유저담당자
	 */
	public String getEmp_id() {
		return StringUtil.nvl(this.emp_id);
	}

	/**
	 * 거래처코드 설정
	 * @param account_cd 거래처코드
	 */
	public void setAccount_cd(String account_cd) {
		this.account_cd = account_cd;
	}
	/**
	 * 거래처코드 취득
	 * @return account_cd 거래처코드
	 */
	public String getAccount_cd() {
		return StringUtil.nvl(this.account_cd);
	}

	/**
	 * 추가정보 설정
	 * @param bigo 추가정보
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * 추가정보 취득
	 * @return bigo 추가정보
	 */
	public String getBigo() {
		return StringUtil.nvl(this.bigo);
	}

	/**
	 * 사용여부 설정
	 * @param use_yn 사용여부
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * 사용여부 취득
	 * @return use_yn 사용여부
	 */
	public String getUse_yn() {
		return StringUtil.nvl(this.use_yn);
	}

	/**
	 * 회원PWD일치여부 설정
	 * @param pw_true_false 회원PWD일치여부
	 */
	public void setPw_true_false(String pw_true_false) {
		this.pw_true_false = pw_true_false;
	}
	/**
	 * 회원PWD일치여부 취득
	 * @return pw_true_false 회원PWD일치여부
	 */
	public String getPw_true_false() {
		return StringUtil.nvl(this.pw_true_false);
	}


	/**
	 * confirm URL 설정
	 * @param confirm_url confirm URL
	 */
	public void setConfirm_url(String confirm_url) {
		this.confirm_url = confirm_url;
	}
	/**
	 * confirm URL 취득
	 * @return confirm_url confirm URL
	 */
	public String getConfirm_url() {
		return StringUtil.nvl(this.confirm_url);
	}
	
	/**
	 * 이메일인증키 설정
	 * @param email_cert_key 이메일인증키
	 */
	public void setEmail_cert_key(String email_cert_key) {
		this.email_cert_key = email_cert_key;
	}
	/**
	 * 이메일인증키 취득
	 * @return email_cert_key 이메일인증키
	 */
	public String getEmail_cert_key() {
		return StringUtil.nvl(this.email_cert_key);
	}

	/**
	 * 이메일인증일시 설정
	 * @param email_cert_date 이메일인증일시
	 */
	public void setEmail_cert_date(String email_cert_date) {
		this.email_cert_date = email_cert_date;
	}
	/**
	 * 이메일인증일시 취득
	 * @return email_cert_date 이메일인증일시
	 */
	public String getEmail_cert_date() {
		return StringUtil.nvl(this.email_cert_date);
	}

	/**
	 * 이메일인증여부 설정
	 * @param email_cert_yn 이메일인증여부
	 */
	public void setEmail_cert_yn(String email_cert_yn) {
		this.email_cert_yn = email_cert_yn;
	}
	/**
	 * 이메일인증여부 취득
	 * @return email_cert_yn 이메일인증여부
	 */
	public String getEmail_cert_yn() {
		return StringUtil.nvl(this.email_cert_yn);
	}

	/**
	 * 승급요청 담당자 설정
	 * @param ug_emp 승급요청 담당자
	 */
	public void setUg_emp(String ug_emp) {
		this.ug_emp = ug_emp;
	}
	/**
	 * 승급요청 담당자 취득
	 * @return ug_emp 승급요청 담당자
	 */
	public String getUg_emp() {
		return StringUtil.nvl(this.ug_emp);
	}

	/**
	 * 회원 변경요청 비밀번호 설정
	 * @param new_user_pwd 회원 변경요청 비밀번호
	 */
	public void setOld_user_pwd(String old_user_pwd) {
		this.old_user_pwd = old_user_pwd;
	}
	/**
	 * 회원 변경요청 비밀번호 취득
	 * @return new_user_pwd 회원 변경요청 비밀번호
	 */
	public String getOld_user_pwd() {
		return StringUtil.nvl(this.old_user_pwd);
	}

	/**
	 * 고객회사 전화번호 설정
	 * @param user_com_tel 고객회사 전화번호
	 */
	public void setUser_com_tel(String user_com_tel) {
		this.user_com_tel = user_com_tel;
	}
	/**
	 * 고객회사 전화번호 취득
	 * @return user_com_tel 고객회사 전화번호
	 */
	public String getUser_com_tel() {
		return StringUtil.nvl(this.user_com_tel);
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

	/**
	 * 국가 명 설정
	 * @param nation_txt 국가 명
	 */
	public void setNation_txt(String nation_txt) {
		this.nation_txt = nation_txt;
	}
	/**
	 * 국가 명 취득
	 * @return nation_txt 국가 명
	 */
	public String getNation_txt() {
		return StringUtil.nvl(this.nation_txt);
	}

	/**
	 * 언어 명 설정
	 * @param lang_txt 언어 명
	 */
	public void setLang_txt(String lang_txt) {
		this.lang_txt = lang_txt;
	}
	/**
	 * 언어 명 취득
	 * @return lang_txt 언어 명
	 */
	public String getLang_txt() {
		return StringUtil.nvl(this.lang_txt);
	}

	/**
	 * 단위 명 설정
	 * @param unit_txt 단위 명
	 */
	public void setUnit_txt(String unit_txt) {
		this.unit_txt = unit_txt;
	}
	/**
	 * 단위 명 취득
	 * @return unit_txt 단위 명
	 */
	public String getUnit_txt() {
		return StringUtil.nvl(this.unit_txt);
	}

	/**
	 * 통화 명 설정
	 * @param curr_txt 통화 명
	 */
	public void setCurr_txt(String curr_txt) {
		this.curr_txt = curr_txt;
	}
	/**
	 * 통화 명 취득
	 * @return curr_txt 통화 명
	 */
	public String getCurr_txt() {
		return StringUtil.nvl(this.curr_txt);
	}

}
