/*****************************************************************************
 * 프로그램명  : LanguageInfo.java
 * 설     명  : 언어공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.02    정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import java.io.Serializable;
import java.util.Locale;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.util.StringUtil;

/**
 *  언어정보
 *
 * @author  eaction
 * @version 0.1
 */
@Alias("LanguageInfo")
public class LanguageInfo implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = -2808061602143390692L;
	

	/** 특성(Property) */
	private String prop = "";
	/** 번역코드 */
	private String tr_cd = "";
	/** 영문 */
	private String en_ln = "";
	/** 국문 */
	private String kr_ln = "";
	/** 최대글자수 */
	private String len = "";
	/** 사용여부 */
	private String use_yn = "";
	/** 언어코드 */
	private String lang_cd = "";
	/** 번역내용 */
	private String tran_ln = "";
	/** 영문 */
	private String tr_nm = "";



	/**
	 * 영문 설정
	 * @param tr_nm 영문
	 */
	public void setTr_nm(String tr_nm) {
		this.tr_nm = tr_nm;
	}
	/**
	 * 영문 취득
	 * @return tr_nm 영문
	 */
	public String getTr_nm() {
		return StringUtil.nvl(this.tr_nm);
	}


	/**
	 * 특성(Property) 설정
	 * @param prop 특성(Property)
	 */
	public void setProp(String prop) {
		this.prop = prop;
	}
	/**
	 * 특성(Property) 취득
	 * @return prop 특성(Property)
	 */
	public String getProp() {
		return StringUtil.nvl(this.prop);
	}

	/**
	 * 번역코드 설정
	 * @param tr_cd 번역코드
	 */
	public void setTr_cd(String tr_cd) {
		this.tr_cd = tr_cd;
	}
	/**
	 * 번역코드 취득
	 * @return tr_cd 번역코드
	 */
	public String getTr_cd() {
		return StringUtil.nvl(this.tr_cd);
	}

	/**
	 * 영문 설정
	 * @param en_ln 영문
	 */
	public void setEn_ln(String en_ln) {
		this.en_ln = en_ln;
	}
	/**
	 * 영문 취득
	 * @return en_ln 영문
	 */
	public String getEn_ln() {
		return StringUtil.nvl(this.en_ln);
	}

	/**
	 * 국문 설정
	 * @param kr_ln 국문
	 */
	public void setKr_ln(String kr_ln) {
		this.kr_ln = kr_ln;
	}
	/**
	 * 국문 취득
	 * @return kr_ln 국문
	 */
	public String getKr_ln() {
		return StringUtil.nvl(this.kr_ln);
	}

	/**
	 * 최대글자수 설정
	 * @param len 최대글자수
	 */
	public void setLen(String len) {
		this.len = len;
	}
	/**
	 * 최대글자수 취득
	 * @return len 최대글자수
	 */
	public String getLen() {
		return StringUtil.nvl(this.len);
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
	 * 언어코드 설정
	 * @param lang_cd 언어코드
	 */
	public void setLang_cd(String lang_cd) {
		this.lang_cd = lang_cd;
	}
	/**
	 * 언어코드 취득
	 * @return lang_cd 언어코드
	 */
	public String getLang_cd() {
		return StringUtil.nvl(this.lang_cd);
	}

	/**
	 * 번역내용 설정
	 * @param tran_ln 번역내용
	 */
	public void setTran_ln(String tran_ln) {
		this.tran_ln = tran_ln;
	}
	/**
	 * 번역내용 취득
	 * @return tran_ln 번역내용
	 */
	public String getTran_ln() {
		return StringUtil.nvl(this.tran_ln);
	}
}
