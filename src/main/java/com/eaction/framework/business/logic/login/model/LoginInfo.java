/*****************************************************************************
 * 프로그램명  : PersonInfo.java
 * 설     명  : 회원정보 객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.login.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.util.StringUtil;

@Alias("LoginInfo")
public class LoginInfo {
	private String id; 
	private String password; 
	private String name; 
	private String company; 
	
	public String getId() {
		return StringUtil.nvl(id);
	}
	public void setId(String str) {
		this.id = str;
	}
	public String getPassword() {
		return StringUtil.nvl(password);
	}
	public void setPassword(String str) {
		this.password = str;
	}
	public String getName() {
		return StringUtil.nvl(name);
	}
	public void setName(String str) {
		this.name = str;
	}
	public String getCompany() {
		return StringUtil.nvl(company);
	}
	public void setCompany(String str) {
		this.company = str;
	}
}
