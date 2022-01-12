/*****************************************************************************
 * 프로그램명  : StaffSearchInfo.java
 * 설     명  : 스텝정보 검색조건 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.staff.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.jqgrid.JqGridVO;
import com.eaction.framework.common.util.StringUtil;

/**
 * 스텝정보 검색조건의 데이터 빈
 * @author eaction
 * @version 1.0
 */
@Alias("StaffSearchInfo")
public class StaffSearchInfo extends JqGridVO {
	
	/* 검색이름 */
	private String search_name = "";
	/* 검색아이디 */
	private String search_id = "";
	/* 검색 스탭종류 */
	private String search_auth = "";
	/* 검색사용상태 */
	private String search_status = "";
	/* 검색사용상태 */
	private String search_email = "";
	/* 검색사용상태 */
	private String search_smsphone = "";
    /**
     * 생성자
     */
    public StaffSearchInfo() {
    	
    }

    /*
     * 검색이름 설정
     * @param str 검색이름 
     */
    public void setSearch_name(String search_name) {
    	this.search_name = search_name;
    }

    /*
     * 검색이름 취득
     * @return str 검색이름 
     */
    public String getSearch_name() {
    	return StringUtil.nvl(search_name);
    }
    
    /*
     * 검색아이디 취득
     * @return str 검색아이디
     */
    public String getSearch_id() {
    	return StringUtil.nvl(search_id);
    }
    
    /*
     * 검색아이디 설정
     * @param str 검색아이디
     */
    public void setSearch_id(String search_id) {
    	this.search_id = search_id;
    }
    
    /*
     * 검색 권한등급 취득
     * @return str 검색아이디
     */
    public String getSearch_auth() {
    	return StringUtil.nvl(search_auth);
    }
    
    /*
     * 검색 권한등급 설정
     * @param str 검색아이디
     */
    public void setSearch_auth(String search_auth) {
    	this.search_auth = search_auth;
    }
    
    /*
     * 검색 사용상태 설정
     * @param str 검색아이디
     */
    public void setSearch_status(String search_status) {
    	this.search_status = search_status;
    }
    
    /*
     * 검색 사용상태 취득
     * @return str 검색아이디
     */
    public String getSearch_status() {
    	return StringUtil.nvl(search_status);
    }
    
    /*
     * 검색이메일 설정
     * @param str 검색이메일
     */
    public void setSearch_email(String search_email) {
    	this.search_email = search_email;
    }
    
    /*
     * 검색이메일 취득
     * @return str 검색이메일
     */
    public String getSearch_email() {
    	return StringUtil.nvl(search_email);
    }
    
    /*
     * 검색전화번호 설정
     * @param str 검색전화번호
     */
    public void setSearch_smsphone(String search_smsphone) {
    	this.search_smsphone = search_smsphone;
    }
    
    /*
     * 검색전화번호 취득
     * @return str 검색전화번호
     */
    public String getSearch_smsphone() {
    	return StringUtil.nvl(search_smsphone);
    }
}