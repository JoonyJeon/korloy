/*****************************************************************************
 * 프로그램명  : BasicInfo.java
 * 설     명  : 기본데이터 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.07.07   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basic.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.util.StringUtil;


@Alias("basicLogInfo")
public class BasicLogInfo extends BasicInfo {
	
	/** SMS이력번호 */
	private String sms_seq = "";
	/** SMS 요청자 ID */
	private String sms_log_id = "";
	/** SMS 요청자명 */
	private String sms_log_nm = "";
	/** SMS 요청목적 */
	private String sms_purpose = "";
	/** 결과코드 */
	private String sms_result_cd = "";
	/** 결과명 */
	private String sms_result_nm = "";
	/** MSG내용 */
	private String sms_msg = "";
	/** 보내는전화 */
	private String send_num = "";
	/** 받는전화 */
	private String rcv_num = "";
	/** 보낸일시 */
	private String send_time = "";
	/** 등록일시 */
	private String reg_time = "";
	/** 등록자IP */
	private String reg_ip = "";
	/** 보낸사람명 */
	private String reg_nm = "";



	/**
	 * SMS이력번호 설정
	 * @param sms_seq SMS이력번호
	 */
	public void setSms_seq(String sms_seq) {
		this.sms_seq = sms_seq;
	}
	/**
	 * SMS이력번호 취득
	 * @return sms_seq SMS이력번호
	 */
	public String getSms_seq() {
		return StringUtil.nvl(this.sms_seq);
	}

	/**
	 * SMS 요청자 ID 설정
	 * @param sms_log_id SMS 요청자 ID
	 */
	public void setSms_log_id(String sms_log_id) {
		this.sms_log_id = sms_log_id;
	}
	/**
	 * SMS 요청자 ID 취득
	 * @return sms_log_id SMS 요청자 ID
	 */
	public String getSms_log_id() {
		return StringUtil.nvl(this.sms_log_id);
	}

	/**
	 * SMS 요청자명 설정
	 * @param sms_log_nm SMS 요청자명
	 */
	public void setSms_log_nm(String sms_log_nm) {
		this.sms_log_nm = sms_log_nm;
	}
	/**
	 * SMS 요청자명 취득
	 * @return sms_log_nm SMS 요청자명
	 */
	public String getSms_log_nm() {
		return StringUtil.nvl(this.sms_log_nm);
	}

	/**
	 * SMS 요청목적 설정
	 * @param sms_purpose SMS 요청목적
	 */
	public void setSms_purpose(String sms_purpose) {
		this.sms_purpose = sms_purpose;
	}
	/**
	 * SMS 요청목적 취득
	 * @return sms_purpose SMS 요청목적
	 */
	public String getSms_purpose() {
		return StringUtil.nvl(this.sms_purpose);
	}

	/**
	 * 결과코드 설정
	 * @param sms_result_cd 결과코드
	 */
	public void setSms_result_cd(String sms_result_cd) {
		this.sms_result_cd = sms_result_cd;
	}
	/**
	 * 결과코드 취득
	 * @return sms_result_cd 결과코드
	 */
	public String getSms_result_cd() {
		return StringUtil.nvl(this.sms_result_cd);
	}

	/**
	 * 결과명 설정
	 * @param sms_result_nm 결과명
	 */
	public void setSms_result_nm(String sms_result_nm) {
		this.sms_result_nm = sms_result_nm;
	}
	/**
	 * 결과명 취득
	 * @return sms_result_nm 결과명
	 */
	public String getSms_result_nm() {
		return StringUtil.nvl(this.sms_result_nm);
	}

	/**
	 * MSG내용 설정
	 * @param sms_msg MSG내용
	 */
	public void setSms_msg(String sms_msg) {
		this.sms_msg = sms_msg;
	}
	/**
	 * MSG내용 취득
	 * @return sms_msg MSG내용
	 */
	public String getSms_msg() {
		return StringUtil.nvl(this.sms_msg);
	}

	/**
	 * 보내는전화 설정
	 * @param send_num 보내는전화
	 */
	public void setSend_num(String send_num) {
		this.send_num = send_num;
	}
	/**
	 * 보내는전화 취득
	 * @return send_num 보내는전화
	 */
	public String getSend_num() {
		return StringUtil.nvl(this.send_num);
	}

	/**
	 * 받는전화 설정
	 * @param rcv_num 받는전화
	 */
	public void setRcv_num(String rcv_num) {
		this.rcv_num = rcv_num;
	}
	/**
	 * 받는전화 취득
	 * @return rcv_num 받는전화
	 */
	public String getRcv_num() {
		return StringUtil.nvl(this.rcv_num);
	}

	/**
	 * 보낸일시 설정
	 * @param send_time 보낸일시
	 */
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	/**
	 * 보낸일시 취득
	 * @return send_time 보낸일시
	 */
	public String getSend_time() {
		return StringUtil.nvl(this.send_time);
	}

	/**
	 * 등록일시 설정
	 * @param reg_time 등록일시
	 */
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	/**
	 * 등록일시 취득
	 * @return reg_time 등록일시
	 */
	public String getReg_time() {
		return StringUtil.nvl(this.reg_time);
	}

	/**
	 * 등록자IP 설정
	 * @param reg_ip 등록자IP
	 */
	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}
	/**
	 * 등록자IP 취득
	 * @return reg_ip 등록자IP
	 */
	public String getReg_ip() {
		return StringUtil.nvl(this.reg_ip);
	}

	/**
	 * 보낸사람명 설정
	 * @param reg_nm 보낸사람명
	 */
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	/**
	 * 보낸사람명 취득
	 * @return reg_nm 보낸사람명
	 */
	public String getReg_nm() {
		return StringUtil.nvl(this.reg_nm);
	}
	
}
