/*****************************************************************************
 * 프로그램명  : UserGroupInfo.java
 * 설     명  : 사용자그룹공통부품(그룹코드/코드/이름데이터빈)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23   LYS    1.0     초기작성
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
@Alias("UserGroupInfo")
public class UserGroupInfo implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5361477895699981708L;
	
	/** 그룹 레벨 */
	private String groupLevel = "";
	/** 코드 */
	private String code = ""; 
	/** 명칭 */
	private String name = ""; 
	/** 비고 */
	private String remark = "";
	/** 등록유저 */
	private String crtUser = "";
	/** 갱신유저 */
	private String updtUser = "";
	/** 수정자명 */
	private String updtUserNm = "";
	/** 등록날짜 */
	private String crtDt = "";
	/** 수정날짜 */
	private String updtDt = "";
	/** 권한레벨코드명 */
	private String levelcodnm = "";
	/** 부서코드 */
	private String cd_dept = "";
	/** 부서명칭 */
	private String nm_dept = "";
	/** 팀장유무 */
	private String team_chief_yn = "";
	/** 팀장유무명칭 */
	private String team_chief_nm = "";
	
	/**
	 * 생성자
	 */
	public UserGroupInfo() {}
	
	/**
	 * 생성자(기본값을 설정한다)
	 * @param strGroup　그룹코드
	 * @param strCode　코드
	 * @param strName　코드명칭
	 */
    public UserGroupInfo (String groupLevel, String strCode, String strName) {
    	setGroupLevel(groupLevel);
		setCode(strCode);
		setName(strName);
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
	 * 비고취득
	 * @return String　비고
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 비고설정
	 * @param str 비고
	 */
	public void setRemark(String str) {
		this.remark = str;
	}
	
	/**
	 * 등록유저취득
	 * @return String　등록유저
	 */
	public String getCrtUser() {
		return crtUser;
	}
	/**
	 * 등록유저설정
	 * @param str 등록유저
	 */
	public void setCrtUser(String str) {
		this.crtUser = str;
	}
	
	/**
	 * 갱신유저 값취득
	 * @return String　갱신유저
	 */
	public String getUpdtUser() {
		return updtUser;
	}
	/**
	 * 갱신유저 값설정
	 * @param str 갱신유저
	 */
	public void setUpdtUser(String str) {
		this.updtUser = str;
	}
	
	/**
	 * 수정자명 설정
	 * @param updtUserNm 수정자명
	 */
	public void setUpdtUserNm(String updtUserNm) {
		this.updtUserNm = updtUserNm;
	}
	/**
	 * 수정자명 취득
	 * @return updtUserNm 수정자명
	 */
	public String getUpdtUserNm() {
		return (this.updtUserNm);
	}
	
	/**
	 * 등록날짜 값취득
	 * @return String　등록날짜
	 */
	public String getCrtDt() {
		return crtDt;
	}
	/**
	 * 등록날짜 값설정
	 * @param str 등록날짜
	 */
	public void setCrtDt(String str) {
		this.crtDt = str;
	}
	
	/**
	 * 수정날짜 값취득
	 * @return String　수정날짜
	 */
	public String getUpdtDt() {
		return updtDt;
	}
	/**
	 * 수정날짜 값설정
	 * @param str 수정날짜
	 */
	public void setUpdtDt(String str) {
		this.updtDt = str;
	}
	
	/**
	 * @return the levelcodnm
	 */
	public String getLevelcodnm() {
		return StringUtil.nvl(levelcodnm);
	}
	/**
	 * @param levelcod the levelcodnm to set
	 */
	public void setLevelcodnm(String levelcodnm) {
		this.levelcodnm = levelcodnm;
	}
	
	/**
	 * @return String
	 */
	public String getCd_dept() {
		return StringUtil.nvl(cd_dept);
	}
	/**
	 * @param str
	 */
	public void setCd_dept(String str) {
		this.cd_dept = str;
	}
	
	/**
	 * @return String
	 */
	public String getNm_dept() {
		return StringUtil.nvl(nm_dept);
	}
	/**
	 * @param str
	 */
	public void setNm_dept(String str) {
		this.nm_dept = str;
	}
	/**
	 * @return String
	 */
	public String getTeam_chief_yn() {
		return StringUtil.nvl(team_chief_yn);
	}
	/**
	 * @param str
	 */
	public void setTeam_chief_yn(String str) {
		this.team_chief_yn = str;
	}
	
	/**
	 * @return String
	 */
	public String getTeam_chief_nm() {
		return StringUtil.nvl(team_chief_nm);
	}
	/**
	 * @param str
	 */
	public void setTeam_chief_nm(String str) {
		this.team_chief_nm = str;
	}
	
}
