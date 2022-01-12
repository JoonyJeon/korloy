/*****************************************************************************
 * 프로그램명  : StaffInfo.java
 * 설     명  : 스태프 정보 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.staff.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;

/**
 * 회원사정보 데이터 빈
 * @author eaction
 * @version 1.0
 */
@Alias("StaffInfo")
public class StaffInfo extends EactReqInfo {

	/* 스텝ID */
	private String emp_id = "";
	/* 스탭 이름 */
	private String empnm = "";
	/* Password */
	private String password = "";
	/* 스탭종류 */
	private String user_group_id = "";
	/* 이메일 */
	private String email = ""; 
	/* SMS휴대폰 */
	private String smsphone = "";
	/* 우편번호 */
	private String home_zip = "";
	/* 주소 */
	private String home_addr1 = "";
	/* 상세주소 */
	private String home_addr2 = "";
	/* 사용자 상태 */
	private String status = "";
	/* 로그인 사용자의 GROUP_ID  (grcode: AUTH_TYPE) */
	private String group_id = "";
	/* 그룹의 이름 */
	private String group_nm = "";
	/* 그룹의 권한 레벨 코드. - 그룹코드 P104 */
	private String levelcod = "";
	/* 그룹 비고 */
	private String remark = "";
	/** 이전비밀번호 */
	private String pre_password = "";
	
	/**
	 * 이전비밀번호 설정
	 * @param pre_password 이전비밀번호
	 */
	public void setPre_password(String pre_password) {
		this.pre_password = pre_password;
	}
	/**
	 * 이전비밀번호 취득
	 * @return pre_password 이전비밀번호
	 */
	public String getPre_password() {
		return StringUtil.nvl(this.pre_password);
	}
	
	public String getEmp_id() {
		return StringUtil.nvl(this.emp_id);
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getEmpnm() {
		return StringUtil.nvl(this.empnm);
	}
	public void setEmpnm(String empnm) {
		this.empnm = empnm;
	}
	
	public String getPassword() {
		return StringUtil.nvl(this.password);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUser_group_id() {
		return StringUtil.nvl(this.user_group_id);
	}
	public void setUser_group_id(String user_group_id) {
		this.user_group_id = user_group_id;
	}
	
	public String getEmail() {
		return StringUtil.nvl(this.email);
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSmsphone() {
		return StringUtil.nvl(this.smsphone);
	}
	public void setSmsphone(String smsphone) {
		this.smsphone = smsphone;
	}
		
	public String getHome_zip() {
		return StringUtil.nvl(this.home_zip);
	}
	public void setHome_zip(String home_zip) {
		this.home_zip = home_zip;
	}
	
	public String getHome_addr1() {
		return StringUtil.nvl(this.home_addr1);
	}
	public void setHome_addr1(String home_addr1) {
		this.home_addr1 = home_addr1;
	}
	
	public String getHome_addr2() {
		return StringUtil.nvl(this.home_addr2);
	}
	public void setHome_addr2(String home_addr2) {
		this.home_addr2 = home_addr2;
	}
	
	public String getStatus() {
		return StringUtil.nvl(this.status);
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getGroup_id() {
		return StringUtil.nvl(this.group_id);
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	public String getGroup_nm() {
		return StringUtil.nvl(this.group_nm);
	}
	public void setGroup_nm(String group_nm) {
		this.group_nm = group_nm;
	}
	
	public String getLevelcod() {
		return StringUtil.nvl(this.levelcod);
	}
	public void setLevelcod(String levelcod) {
		this.levelcod = levelcod;
	}
	
	public String getRemark() {
		return StringUtil.nvl(this.remark);
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
