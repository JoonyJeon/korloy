/*****************************************************************************
 * 프로그램명  : GroupCodeInfo.java
 * 설     명  : 공통부품(그룹코드/코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.util.StringUtil;

/**
 * 코드・명칭정보
 * 
 * @author  eaction
 * @version 0.1
 */
@Alias("GroupCodeInfo")
public class GroupCodeInfo implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = -8727404414616095655L;
	
	/** 그룹 레벨 */
	private String groupLevel = "";
	/** 그룹코드 */
	private String group = ""; 
	/** 코드 */
	private String code = ""; 
	/** 명칭 */
	private String name = ""; 
	/** 갱신가부 */
	private String modiYn = ""; 
	/** 상위코드 */
	private String upCode = "";
	/** CHILD 그룹코드 */
	private String childGroup = "";
	
	/** 코드값-한국어 */
	private String codenm_k = "";
	/** 코드값-영어 */
	private String codenm_e = "";
	/** 코드값-일본어 */
	private String codenm_j = "";

	/** 코드값 */
	private String codenm = "";
	/** 코드값 */
	private String sortseq = "";

	/**
	 * 코드값 설정
	 * @param codenm 코드값
	 */
	public void setCodenm(String codenm) {
		this.codenm = codenm;
	}
	/**
	 * 코드값 취득
	 * @return codenm 코드값
	 */
	public String getCodenm() {
		return StringUtil.nvl(this.codenm);
	}


	/**
	 * 코드값-한국어 설정
	 * @param codenm_k 코드값-한국어
	 */
	public void setCodenm_k(String codenm_k) {
		this.codenm_k = codenm_k;
	}
	/**
	 * 코드값-한국어 취득
	 * @return codenm_k 코드값-한국어
	 */
	public String getCodenm_k() {
		return StringUtil.nvl(this.codenm_k);
	}

	/**
	 * 코드값-영어 설정
	 * @param codenm_e 코드값-영어
	 */
	public void setCodenm_e(String codenm_e) {
		this.codenm_e = codenm_e;
	}
	/**
	 * 코드값-영어 취득
	 * @return codenm_e 코드값-영어
	 */
	public String getCodenm_e() {
		return StringUtil.nvl(this.codenm_e);
	}

	/**
	 * 코드값-일본어 설정
	 * @param codenm_j 코드값-일본어
	 */
	public void setCodenm_j(String codenm_j) {
		this.codenm_j = codenm_j;
	}
	/**
	 * 코드값-일본어 취득
	 * @return codenm_j 코드값-일본어
	 */
	public String getCodenm_j() {
		return StringUtil.nvl(this.codenm_j);
	}
	
	/**
	 * 생성자
	 */
	public GroupCodeInfo() {}
	
	/**
	 * 생성자(기본값을 설정한다)
	 * @param strGroup　그룹코드
	 * @param strCode　코드
	 * @param strName　코드명칭
	 */
    public GroupCodeInfo (String strGroup, String strCode, String strName) {
    	setGroup(strGroup);
		setCode(strCode);
		setName(strName);
	}
    
    /**
	 * 생성자(기본값을 설정한다)
	 * @param strGroup　그룹코드
	 * @param strCode　코드
	 * @param strName　코드명칭
	 * @param strUpCode　상위코드
	 */
    public GroupCodeInfo (String strGroup, String strCode, String strName, String strUpCode) {
    	setGroup(strGroup);
		setCode(strCode);
		setName(strName);
		setUpCode(strUpCode);
	}
	
    /**
	 * 그룹레벨값취득
	 * @return String　그룹레벨값
	 */
	public String getGroupLevel() {
		return groupLevel;
	}
	/**
	 * 그룹레벨값설정
	 * @param str 그룹레벨값
	 */
	public void setGroupLevel(String str) {
		this.groupLevel = str;
	}
	
    /**
	 * 그룹코드값취득
	 * @return String　그룹코드값
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * 그룹코드값설정
	 * @param str 그룹코드값
	 */
	public void setGroup(String str) {
		this.group = str;
	}
	
	/**
	 * 코드값취득
	 * @return String　코드값
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 코드값설정
	 * @param str 코드값
	 */
	public void setCode(String str) {
		this.code = str;
	}
	
	/**
	 * 코드명칭취득
	 * @return String　코드명칭
	 */
	public String getName() {
		return name;
	}
	/**
	 * 코드명칭설정
	 * @param str 코드명칭
	 */
	public void setName(String str) {
		this.name = str;
	}
	
	/**
	 * 갱신가부취득
	 * @return String　갱신가부
	 */
	public String getModiYn() {
		return modiYn;
	}
	/**
	 * 갱신가부설정
	 * @param str 갱신가부
	 */
	public void setModiYn(String str) {
		this.modiYn = str;
	}
	
	/**
	 * 상위코드값취득
	 * @return String　상위코드값
	 */
	public String getUpCode() {
		return upCode;
	}
	/**
	 * 상위코드값설정
	 * @param str 상위코드값
	 */
	public void setUpCode(String str) {
		this.upCode = str;
	}
	
	/**
	 * CHILD 그룹코드 값취득
	 * @return String　CHILD 그룹코드 값
	 */
	public String getChildGroup() {
		return childGroup;
	}
	/**
	 * CHILD 그룹코드 값설정
	 * @param str CHILD 그룹코드 값
	 */
	public void setChildGroup(String str) {
		this.childGroup = str;
	}
	
	/**
	 * CHILD 그룹코드 값취득
	 * @return String　CHILD 그룹코드 값
	 */
	public String getSortseq() {
		return sortseq;
	}
	/**
	 * CHILD 그룹코드 값설정
	 * @param str CHILD 그룹코드 값
	 */
	public void setSortseq(String str) {
		this.sortseq = str;
	}
}
