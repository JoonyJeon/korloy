/*****************************************************************************
 * 프로그램명  : ESInfo.java
 * 설     명  : 엘라스틱서치 정보 객체
 * 
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.08.02   정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.search.model;
import com.eaction.framework.common.util.StringUtil;


public class ESInfo {

	/** werks */
	private String werks = "";
	/** ma_cd */
	private String ma_cd = "";
	/** ma_nm */
	private String ma_nm = "";
	/** sa_cd */
	private String sa_cd = "";
	/** sa_nm */
	private String sa_nm = "";
	/** ig_cd */
	private String ig_cd = "";
	/** ig_nm */
	private String ig_nm = "";
	/** matnr */
	private String matnr = "";
	/** maktx */
	private String maktx = "";
	/** designation */
	private String designation = "";
	/** designation_inch */
	private String designation_inch = "";
	/** cb */
	private String cb = "";
	/** cb_inch */
	private String cb_inch = "";
	/** grade */
	private String grade = "";
	/** grade_os */
	private String grade_os = "";
	/** unit_cd */
	private String unit_cd = "";
	/** ma_deu */
	private String ma_deu = "";
	/** ma_fra */
	private String ma_fra = "";
	/** ma_kor */
	private String ma_kor = "";
	/** ma_por */
	private String ma_por = "";
	/** ma_rus */
	private String ma_rus = "";
	/** ma_spa */
	private String ma_spa = "";
	/** ma_tha */
	private String ma_tha = "";
	/** ma_tur */
	private String ma_tur = "";
	/** ma_vie */
	private String ma_vie = "";
	/** ma_zho */
	private String ma_zho = "";
	/** sa_deu */
	private String sa_deu = "";
	/** sa_fra */
	private String sa_fra = "";
	/** sa_kor */
	private String sa_kor = "";
	/** sa_por */
	private String sa_por = "";
	/** sa_rus */
	private String sa_rus = "";
	/** sa_spa */
	private String sa_spa = "";
	/** sa_tha */
	private String sa_tha = "";
	/** sa_tur */
	private String sa_tur = "";
	/** sa_vie */
	private String sa_vie = "";
	/** sa_zho */
	private String sa_zho = "";



	/**
	 * ma_deu 설정
	 * @param ma_deu ma_deu
	 */
	public void setMa_deu(String ma_deu) {
		this.ma_deu = ma_deu;
	}
	/**
	 * ma_deu 취득
	 * @return ma_deu ma_deu
	 */
	public String getMa_deu() {
		return StringUtil.nvl(this.ma_deu, this.ma_nm);
	}

	/**
	 * ma_fra 설정
	 * @param ma_fra ma_fra
	 */
	public void setMa_fra(String ma_fra) {
		this.ma_fra = ma_fra;
	}
	/**
	 * ma_fra 취득
	 * @return ma_fra ma_fra
	 */
	public String getMa_fra() {
		return StringUtil.nvl(this.ma_fra, this.ma_nm);
	}

	/**
	 * ma_kor 설정
	 * @param ma_kor ma_kor
	 */
	public void setMa_kor(String ma_kor) {
		this.ma_kor = ma_kor;
	}
	/**
	 * ma_kor 취득
	 * @return ma_kor ma_kor
	 */
	public String getMa_kor() {
		return StringUtil.nvl(this.ma_kor, this.ma_nm);
	}

	/**
	 * ma_por 설정
	 * @param ma_por ma_por
	 */
	public void setMa_por(String ma_por) {
		this.ma_por = ma_por;
	}
	/**
	 * ma_por 취득
	 * @return ma_por ma_por
	 */
	public String getMa_por() {
		return StringUtil.nvl(this.ma_por, this.ma_nm);
	}

	/**
	 * ma_rus 설정
	 * @param ma_rus ma_rus
	 */
	public void setMa_rus(String ma_rus) {
		this.ma_rus = ma_rus;
	}
	/**
	 * ma_rus 취득
	 * @return ma_rus ma_rus
	 */
	public String getMa_rus() {
		return StringUtil.nvl(this.ma_rus, this.ma_nm);
	}

	/**
	 * ma_spa 설정
	 * @param ma_spa ma_spa
	 */
	public void setMa_spa(String ma_spa) {
		this.ma_spa = ma_spa;
	}
	/**
	 * ma_spa 취득
	 * @return ma_spa ma_spa
	 */
	public String getMa_spa() {
		return StringUtil.nvl(this.ma_spa, this.ma_nm);
	}

	/**
	 * ma_tha 설정
	 * @param ma_tha ma_tha
	 */
	public void setMa_tha(String ma_tha) {
		this.ma_tha = ma_tha;
	}
	/**
	 * ma_tha 취득
	 * @return ma_tha ma_tha
	 */
	public String getMa_tha() {
		return StringUtil.nvl(this.ma_tha, this.ma_nm);
	}

	/**
	 * ma_tur 설정
	 * @param ma_tur ma_tur
	 */
	public void setMa_tur(String ma_tur) {
		this.ma_tur = ma_tur;
	}
	/**
	 * ma_tur 취득
	 * @return ma_tur ma_tur
	 */
	public String getMa_tur() {
		return StringUtil.nvl(this.ma_tur, this.ma_nm);
	}

	/**
	 * ma_vie 설정
	 * @param ma_vie ma_vie
	 */
	public void setMa_vie(String ma_vie) {
		this.ma_vie = ma_vie;
	}
	/**
	 * ma_vie 취득
	 * @return ma_vie ma_vie
	 */
	public String getMa_vie() {
		return StringUtil.nvl(this.ma_vie, this.ma_nm);
	}

	/**
	 * ma_zho 설정
	 * @param ma_zho ma_zho
	 */
	public void setMa_zho(String ma_zho) {
		this.ma_zho = ma_zho;
	}
	/**
	 * ma_zho 취득
	 * @return ma_zho ma_zho
	 */
	public String getMa_zho() {
		return StringUtil.nvl(this.ma_zho, this.ma_nm);
	}

	/**
	 * sa_deu 설정
	 * @param sa_deu sa_deu
	 */
	public void setSa_deu(String sa_deu) {
		this.sa_deu = sa_deu;
	}
	/**
	 * sa_deu 취득
	 * @return sa_deu sa_deu
	 */
	public String getSa_deu() {
		return StringUtil.nvl(this.sa_deu, this.sa_nm);
	}

	/**
	 * sa_fra 설정
	 * @param sa_fra sa_fra
	 */
	public void setSa_fra(String sa_fra) {
		this.sa_fra = sa_fra;
	}
	/**
	 * sa_fra 취득
	 * @return sa_fra sa_fra
	 */
	public String getSa_fra() {
		return StringUtil.nvl(this.sa_fra, this.sa_nm);
	}

	/**
	 * sa_kor 설정
	 * @param sa_kor sa_kor
	 */
	public void setSa_kor(String sa_kor) {
		this.sa_kor = sa_kor;
	}
	/**
	 * sa_kor 취득
	 * @return sa_kor sa_kor
	 */
	public String getSa_kor() {
		return StringUtil.nvl(this.sa_kor, this.sa_nm);
	}

	/**
	 * sa_por 설정
	 * @param sa_por sa_por
	 */
	public void setSa_por(String sa_por) {
		this.sa_por = sa_por;
	}
	/**
	 * sa_por 취득
	 * @return sa_por sa_por
	 */
	public String getSa_por() {
		return StringUtil.nvl(this.sa_por, this.sa_nm);
	}

	/**
	 * sa_rus 설정
	 * @param sa_rus sa_rus
	 */
	public void setSa_rus(String sa_rus) {
		this.sa_rus = sa_rus;
	}
	/**
	 * sa_rus 취득
	 * @return sa_rus sa_rus
	 */
	public String getSa_rus() {
		return StringUtil.nvl(this.sa_rus, this.sa_nm);
	}

	/**
	 * sa_spa 설정
	 * @param sa_spa sa_spa
	 */
	public void setSa_spa(String sa_spa) {
		this.sa_spa = sa_spa;
	}
	/**
	 * sa_spa 취득
	 * @return sa_spa sa_spa
	 */
	public String getSa_spa() {
		return StringUtil.nvl(this.sa_spa, this.sa_nm);
	}

	/**
	 * sa_tha 설정
	 * @param sa_tha sa_tha
	 */
	public void setSa_tha(String sa_tha) {
		this.sa_tha = sa_tha;
	}
	/**
	 * sa_tha 취득
	 * @return sa_tha sa_tha
	 */
	public String getSa_tha() {
		return StringUtil.nvl(this.sa_tha, this.sa_nm);
	}

	/**
	 * sa_tur 설정
	 * @param sa_tur sa_tur
	 */
	public void setSa_tur(String sa_tur) {
		this.sa_tur = sa_tur;
	}
	/**
	 * sa_tur 취득
	 * @return sa_tur sa_tur
	 */
	public String getSa_tur() {
		return StringUtil.nvl(this.sa_tur, this.sa_nm);
	}

	/**
	 * sa_vie 설정
	 * @param sa_vie sa_vie
	 */
	public void setSa_vie(String sa_vie) {
		this.sa_vie = sa_vie;
	}
	/**
	 * sa_vie 취득
	 * @return sa_vie sa_vie
	 */
	public String getSa_vie() {
		return StringUtil.nvl(this.sa_vie, this.sa_nm);
	}

	/**
	 * sa_zho 설정
	 * @param sa_zho sa_zho
	 */
	public void setSa_zho(String sa_zho) {
		this.sa_zho = sa_zho;
	}
	/**
	 * sa_zho 취득
	 * @return sa_zho sa_zho
	 */
	public String getSa_zho() {
		return StringUtil.nvl(this.sa_zho, this.sa_nm);
	}


	/**
	 * werks 설정
	 * @param werks werks
	 */
	public void setWerks(String werks) {
		this.werks = werks;
	}
	/**
	 * werks 취득
	 * @return werks werks
	 */
	public String getWerks() {
		return StringUtil.nvl(this.werks);
	}

	/**
	 * ma_cd 설정
	 * @param ma_cd ma_cd
	 */
	public void setMa_cd(String ma_cd) {
		this.ma_cd = ma_cd;
	}
	/**
	 * ma_cd 취득
	 * @return ma_cd ma_cd
	 */
	public String getMa_cd() {
		return StringUtil.nvl(this.ma_cd);
	}

	/**
	 * ma_nm 설정
	 * @param ma_nm ma_nm
	 */
	public void setMa_nm(String ma_nm) {
		this.ma_nm = ma_nm;
	}
	/**
	 * ma_nm 취득
	 * @return ma_nm ma_nm
	 */
	public String getMa_nm() {
		return StringUtil.nvl(this.ma_nm);
	}

	/**
	 * sa_cd 설정
	 * @param sa_cd sa_cd
	 */
	public void setSa_cd(String sa_cd) {
		this.sa_cd = sa_cd;
	}
	/**
	 * sa_cd 취득
	 * @return sa_cd sa_cd
	 */
	public String getSa_cd() {
		return StringUtil.nvl(this.sa_cd);
	}

	/**
	 * sa_nm 설정
	 * @param sa_nm sa_nm
	 */
	public void setSa_nm(String sa_nm) {
		this.sa_nm = sa_nm;
	}
	/**
	 * sa_nm 취득
	 * @return sa_nm sa_nm
	 */
	public String getSa_nm() {
		return StringUtil.nvl(this.sa_nm);
	}

	/**
	 * ig_cd 설정
	 * @param ig_cd ig_cd
	 */
	public void setIg_cd(String ig_cd) {
		this.ig_cd = ig_cd;
	}
	/**
	 * ig_cd 취득
	 * @return ig_cd ig_cd
	 */
	public String getIg_cd() {
		return StringUtil.nvl(this.ig_cd);
	}

	/**
	 * ig_nm 설정
	 * @param ig_nm ig_nm
	 */
	public void setIg_nm(String ig_nm) {
		this.ig_nm = ig_nm;
	}
	/**
	 * ig_nm 취득
	 * @return ig_nm ig_nm
	 */
	public String getIg_nm() {
		return StringUtil.nvl(this.ig_nm);
	}

	/**
	 * matnr 설정
	 * @param matnr matnr
	 */
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	/**
	 * matnr 취득
	 * @return matnr matnr
	 */
	public String getMatnr() {
		return StringUtil.nvl(this.matnr);
	}

	/**
	 * maktx 설정
	 * @param maktx maktx
	 */
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	/**
	 * maktx 취득
	 * @return maktx maktx
	 */
	public String getMaktx() {
		return StringUtil.nvl(this.maktx);
	}

	/**
	 * designation 설정
	 * @param designation designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * designation 취득
	 * @return designation designation
	 */
	public String getDesignation() {
		return StringUtil.nvl(this.designation);
	}

	/**
	 * designation_inch 설정
	 * @param designation_inch designation_inch
	 */
	public void setDesignation_inch(String designation_inch) {
		this.designation_inch = designation_inch;
	}
	/**
	 * designation_inch 취득
	 * @return designation_inch designation_inch
	 */
	public String getDesignation_inch() {
		return StringUtil.nvl(this.designation_inch);
	}

	/**
	 * cb 설정
	 * @param cb cb
	 */
	public void setCb(String cb) {
		this.cb = cb;
	}
	/**
	 * cb 취득
	 * @return cb cb
	 */
	public String getCb() {
		return StringUtil.nvl(this.cb);
	}

	/**
	 * cb_inch 설정
	 * @param cb_inch cb_inch
	 */
	public void setCb_inch(String cb_inch) {
		this.cb_inch = cb_inch;
	}
	/**
	 * cb_inch 취득
	 * @return cb_inch cb_inch
	 */
	public String getCb_inch() {
		return StringUtil.nvl(this.cb_inch);
	}

	/**
	 * grade 설정
	 * @param grade grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * grade 취득
	 * @return grade grade
	 */
	public String getGrade() {
		return StringUtil.nvl(this.grade);
	}

	/**
	 * grade_os 설정
	 * @param grade_os grade_os
	 */
	public void setGrade_os(String grade_os) {
		this.grade_os = grade_os;
	}
	/**
	 * grade_os 취득
	 * @return grade_os grade_os
	 */
	public String getGrade_os() {
		return StringUtil.nvl(this.grade_os);
	}

	/**
	 * unit_cd 설정
	 * @param unit_cd unit_cd
	 */
	public void setUnit_cd(String unit_cd) {
		this.unit_cd = unit_cd;
	}
	/**
	 * unit_cd 취득
	 * @return unit_cd unit_cd
	 */
	public String getUnit_cd() {
		return StringUtil.nvl(this.unit_cd);
	}
}
