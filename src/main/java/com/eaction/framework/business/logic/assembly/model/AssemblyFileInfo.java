/*****************************************************************************
 * 프로그램명  : AssemblyFileInfo.java
 * 설     명  : Assembly File 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 *2021.09.08   한승일    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.assembly.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("AssemblyFileInfo")
public class AssemblyFileInfo extends EactReqInfo {
	
	/** 플랜트 */
	private String werks = "";
	/** 파일구분(1:MA,2:SA,3:IG,4:자재번호) */
	private String file_cd = "";
	/** 일련번호 */
	private String file_seq = "";
	/** 파일형식 */
	private String file_typ = "";
	/** 물리적파일경로(전체경로) */
	private String file_phy_path = "";
	/** 다운로드파일경로(웹) */
	private String file_dwl_path = "";
	/** 원본파일명 */
	private String file_org_nm = "";
	/** 썸네일파일명 */
	private String file_thn_nm = "";
	/** 사용여부 */
	private String use_yn = "";
	/** 등록구분 */
	private String ins_typ = "";
	/** 파일수량 */
	private int file_cnt = 0;



	/**
	 * 파일수량 설정
	 * @param file_cnt 파일수량
	 */
	public void setFile_cnt(int file_cnt) {
		this.file_cnt = file_cnt;
	}
	/**
	 * 파일수량 취득
	 * @return file_cnt 파일수량
	 */
	public int getFile_cnt() {
		return this.file_cnt;
	}


	/**
	 * 플랜트 설정
	 * @param werks 플랜트
	 */
	public void setWerks(String werks) {
		this.werks = werks;
	}
	/**
	 * 플랜트 취득
	 * @return werks 플랜트
	 */
	public String getWerks() {
		return StringUtil.nvl(this.werks);
	}

	/**
	 * 파일구분(1:MA,2:SA,3:IG,4:자재번호) 설정
	 * @param file_cd 파일구분(1:MA,2:SA,3:IG,4:자재번호)
	 */
	public void setFile_cd(String file_cd) {
		this.file_cd = file_cd;
	}
	/**
	 * 파일구분(1:MA,2:SA,3:IG,4:자재번호) 취득
	 * @return file_cd 파일구분(1:MA,2:SA,3:IG,4:자재번호)
	 */
	public String getFile_cd() {
		return StringUtil.nvl(this.file_cd);
	}

	/**
	 * 일련번호 설정
	 * @param file_seq 일련번호
	 */
	public void setFile_seq(String file_seq) {
		this.file_seq = file_seq;
	}
	/**
	 * 일련번호 취득
	 * @return file_seq 일련번호
	 */
	public String getFile_seq() {
		return StringUtil.nvl(this.file_seq);
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
	 * 물리적파일경로(전체경로) 설정
	 * @param file_phy_path 물리적파일경로(전체경로)
	 */
	public void setFile_phy_path(String file_phy_path) {
		this.file_phy_path = file_phy_path;
	}
	/**
	 * 물리적파일경로(전체경로) 취득
	 * @return file_phy_path 물리적파일경로(전체경로)
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
	 * 등록구분 설정
	 * @param ins_typ 등록구분
	 */
	public void setIns_typ(String ins_typ) {
		this.ins_typ = ins_typ;
	}
	/**
	 * 등록구분 취득
	 * @return ins_typ 등록구분
	 */
	public String getIns_typ() {
		return StringUtil.nvl(this.ins_typ);
	}
}
