/*****************************************************************************
 * 프로그램명  : CodeInfo.java
 * 설     명  : Code공통부품(코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import java.io.Serializable;
import java.util.Locale;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 * 코드・명칭정보
 *
 * @author  eaction
 * @version 0.1
 */
@Alias("CodeInfo")
public class CodeInfo implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = -2808061602143390692L;
	
	/** 코드 */
	private String code = "";
	/** 명칭 */
	private String name = "";
	/** 상위코드 */
	private String upCode = "";
	/** CHILD 그룹코드 */
	private String childGroup = "";
	/** 설명 */
	private String desc = "";
	/** code(국가) 담당자([한국야금(21.11.19)] jiyoo) */
	private String email_to = "";
	


	/** 종별 */
	private String kind = "";
	/** 비밀번호1 */
	private String passwd1 = "";
	/** 비밀번호2 */
	private String passwd2 = "";
	/** 작업형태 */
	private String workType = "";


	/** 코드값-한국어 */
	private String codenm_k = "";
	/** 코드값-영어 */
	private String codenm_e = "";
	/** 코드값-일본어 */
	private String codenm_j = "";

	/** 코드값 */
	private String codenm = "";
	/** 초기값 포함 여부 */
	private String is_init = "";
	/** 그룹코드 */
	private String group = "";
	/**
	 * 초기값 포함 여부 설정
	 * @param is_init 초기값 포함 여부
	 */
	public void setIs_init(String is_init) {
		this.is_init = is_init;
	}
	/**
	 * 초기값 포함 여부 취득
	 * @return is_init 초기값 포함 여부
	 */
	public String getIs_init() {
		return StringUtil.nvl(this.is_init);
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
	public CodeInfo() {}

	/**
	 * 생성자(기본값을 설정한다)
	 * @param strCode　코드
	 * @param strName　코드명칭
	 */
    public CodeInfo (String strCode, String strDescr, String strDescrKor, String strDescrEng, String strDescrJpn) {
		setCode(strCode);
		setCodenm(strDescr);
		setCodenm_k(strDescrKor);
		setCodenm_e(strDescrEng);
		setCodenm_j(strDescrJpn);
	}
    
    /**
	 * 생성자(기본값을 설정한다)
	 * @param strCode　코드
	 * @param strName　코드명칭
	 */
    public CodeInfo (String strCode, String strName) {
		setCode(strCode);
		setName(strName);
	}

    /**
	 * 생성자(기본값을 설정한다)
	 * @param strCode　코드
	 * @param strName　코드명칭
	 * @param childGroup　CHILD 그룹코드
	 */
    public CodeInfo (String strCode, String strName, String childGroup) {
		setCode(strCode);
		setName(strName);
		setChildGroup(childGroup);
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
	 * @param code 코드값
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 코드명칭취득
	 * @return String　코드명칭
	 */
	public String getName() {
		return StringUtil.nvl(name);
	}
	/**
	 * 코드명칭설정
	 * @param name 코드명칭
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 상위코드값취득
	 * @return String　코드값
	 */
	public String getUpCode() {
		return upCode;
	}
	/**
	 * 상위코드값설정
	 * @param code 코드값
	 */
	public void setUpCode(String upCode) {
		this.upCode = upCode;
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
	 * 설명 값취득
	 * @return String　설명
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 설명설정
	 * @param str CHILD 설명
	 */
	public void setDesc(String str) {
		this.desc = str;
	}

	/**
	 * code(국가) 담당자([한국야금(21.11.19)] jiyoo) 설정
	 * @param email_to code(국가) 담당자([한국야금(21.11.19)] jiyoo)
	 */
	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}
	/**
	 * code(국가) 담당자([한국야금(21.11.19)] jiyoo) 취득
	 * @return email_to code(국가) 담당자([한국야금(21.11.19)] jiyoo)
	 */
	public String getEmail_to() {
		return StringUtil.nvl(this.email_to);
	}


	/**
	 * 종별 취득
	 * @return String　종별
	 */
	public String getKind() {
		return kind;
	}
	/**
	 * 종별
	 * @param str CHILD 종별
	 */
	public void setKind(String str) {
		this.kind = str;
	}

	/**
	 * 비밀번호1 취득
	 * @return String　비밀번호1
	 */
	public String getPasswd1() {
		return passwd1;
	}
	/**
	 * 비밀번호1
	 * @param str  비밀번호1
	 */
	public void setPasswd1(String str) {
		this.passwd1 = str;
	}

	/**
	 * 비밀번호2 취득
	 * @return String　비밀번호2
	 */
	public String getPasswd2() {
		return StringUtil.nvl(passwd2);
	}
	/**
	 * 비밀번호2
	 * @param str  비밀번호2
	 */
	public void setPasswd2(String str) {
		this.passwd2 = str;
	}

	/**
	 * 작업형태 취득
	 * @return String　작업형태
	 */
	public String getWorkType() {
		return StringUtil.nvl(workType);
	}
	/**
	 * 작업형태
	 * @param str  작업형태
	 */
	public void setWorkType(String str) {
		this.workType = str;
	}

	 /**
     * 메뉴이름 취득
     * @return String 메뉴이름
     */
    public String getCodeName(Locale locale){
    	String ret = "";
    	String lang = ConfigMng.getLanguage(locale);
    	if(ConstKey.LANG_KOR.equals(lang)) {
    		ret = this.codenm_k;
    	} else if(ConstKey.LANG_ENG.equals(lang)) {
        	ret = this.codenm_e;
    	} else if(ConstKey.LANG_JPN.equals(lang)) {
        	ret = this.codenm_j;
        }
    	if("".equals(ret)){
    		ret = this.name;
    	}
    	
        return ret ;
    }	
	
	/** 자재번호 */
	private String matnr = "";


	/** 코드키 */
	private String code_key = "";



	/**
	 * 코드키 설정
	 * @param code_key 코드키
	 */
	public void setCode_key(String code_key) {
		this.code_key = code_key;
	}
	/**
	 * 코드키 취득
	 * @return code_key 코드키
	 */
	public String getCode_key() {
		return StringUtil.nvl(this.code_key);
	}
}
