/*****************************************************************************
 * 프로그램명  : User.java
 * 설     명  : 유저 DataBean
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.util.StringUtil;

/**
 * 유저 데이터 빈
 * @author eaction
 * @version 1.0
 */
@Alias("User")
public class User extends EactReqInfo implements Serializable, HttpSessionBindingListener   {
// 한국야금
	/** 고객명 */
	private String user_name = "";
	/** 고객암호 */
	private String user_pwd = "";
	/** 고객무선번호 */
	private String user_mobile = "";
	/** 고객회사 */
	private String user_company = "";
	/** 고객이메일 */
	private String user_com_mail = "";
	/** 고객회사주소 */
	private String user_com_addr = "";
	/** 고객직책 */
	private String user_position = "";
	/** 고객그룹 */
	private String user_grp = "";
	/** 가입지역 */
	private String user_nation = "";
	/** 단위코드 */
	private String unit_cd = "";
	/** 통화코드 */
	private String curr_cd = "";
	/** 통화코드명 */
	private String curr_nm = "";
	/** 유저담당자 */
	private String emp_id = "";
	/** 거래처코드 */
	private String account_cd = "";
	/** 추가정보 */
	private String bigo = "";
	/** 사용여부 */
	private String use_yn = "";
	/** 회원PWD일치여부 */
	private String pw_true_false = "";

	/** 승급요청 담당자 */
	private String ug_emp = "";

	/** 고객회사 전화번호 */
	private String user_com_tel = "";

// 한국야금 ---------------------------------------
	/** 사용자아이디*/
	private String user_id;
	/** 비밀번호 */
	private String password;
	/** 사용자이름 */
	private String user_nm;
	/** 사용자구분 (내부:INT, 외부:EXT, 관리자:ADM)*/
	private String user_kind;
	/** 사용자번호 */
	private String no_emp;
	/** 회사코드 */
	private String cd_company = "";
	/** 회사이름 */
	private String nm_company = "";
	/** 부서코드 */
	private String cd_dept = "";
	/** 부서이름 */
	private String nm_dept = "";
	/** 전화번호 */
	private String no_tel = "";
	/** 영업분야 */
	private String cd_bizarea = "";
	/** 파트너코드 */
	private String cd_partner = "";
	/** 영업그룹 */
	private String cd_salegrp = "";
	/** 파트너이름 */
	private String nm_partner = "";	
	/** 사용자메뉴 그룹의 부서 아이디 */
	private String group_id = "";
	/** 조회권한(0:전체조회, 1:팀내조회, 2:본인것조회, 3:파트너)*/
	private String search_kind = "";
	/** 팀장여부 (1:팀장, 2:사원, 3:파트너,관리자) */
	private String team_chief_yn = "";
	/** 그룹레벨코드(P104) */
	private String groupLevelCod = "";
	/** 배송코드 */
	private String cd_area = "";
	/** 로그인상태 */
	private String fg_login = "";
	/** 세션ID */
	private String session_id = "";
	/** 사용자대리인번호 */
	private String no_emp_agent = "";
	/** 검색할 모든 사원번호 리스트 */
	private List<String> list_emp_agent = new ArrayList<String>();
	/** 병원담당자일시정지유무 */
	private String hospital_use_yn = "";
	/** 로그인아이피 */
	private String login_ip = "";
	/** 그룹명 */
	private String group_nm = "";
	/** 사용자상태 */
	private String status = "";


// 한국야금	
	/**
	 * 고객명 설정
	 * @param user_name 고객명
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * 고객명 취득
	 * @return user_name 고객명
	 */
	public String getUser_name() {
		return StringUtil.nvl(this.user_name);
	}

	/**
	 * 고객암호 설정
	 * @param user_pwd 고객암호
	 */
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	/**
	 * 고객암호 취득
	 * @return user_pwd 고객암호
	 */
	public String getUser_pwd() {
		return StringUtil.nvl(this.user_pwd);
	}

	/**
	 * 고객무선번호 설정
	 * @param user_mobile 고객무선번호
	 */
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	/**
	 * 고객무선번호 취득
	 * @return user_mobile 고객무선번호
	 */
	public String getUser_mobile() {
		return StringUtil.nvl(this.user_mobile);
	}

	/**
	 * 고객회사 설정
	 * @param user_company 고객회사
	 */
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}
	/**
	 * 고객회사 취득
	 * @return user_company 고객회사
	 */
	public String getUser_company() {
		return StringUtil.nvl(this.user_company);
	}

	/**
	 * 고객이메일 설정
	 * @param user_com_mail 고객이메일
	 */
	public void setUser_com_mail(String user_com_mail) {
		this.user_com_mail = user_com_mail;
	}
	/**
	 * 고객이메일 취득
	 * @return user_com_mail 고객이메일
	 */
	public String getUser_com_mail() {
		return StringUtil.nvl(this.user_com_mail);
	}

	/**
	 * 고객회사주소 설정
	 * @param user_com_addr 고객회사주소
	 */
	public void setUser_com_addr(String user_com_addr) {
		this.user_com_addr = user_com_addr;
	}
	/**
	 * 고객회사주소 취득
	 * @return user_com_addr 고객회사주소
	 */
	public String getUser_com_addr() {
		return StringUtil.nvl(this.user_com_addr);
	}

	/**
	 * 고객직책 설정
	 * @param user_position 고객직책
	 */
	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}
	/**
	 * 고객직책 취득
	 * @return user_position 고객직책
	 */
	public String getUser_position() {
		return StringUtil.nvl(this.user_position);
	}

	/**
	 * 고객그룹 설정
	 * @param user_grp 고객그룹
	 */
	public void setUser_grp(String user_grp) {
		this.user_grp = user_grp;
	}
	/**
	 * 고객그룹 취득
	 * @return user_grp 고객그룹
	 */
	public String getUser_grp() {
		return StringUtil.nvl(this.user_grp);
	}

	/**
	 * 가입지역 설정
	 * @param user_nation 가입지역
	 */
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	/**
	 * 가입지역 취득
	 * @return user_nation 가입지역
	 */
	public String getUser_nation() {
		return StringUtil.nvl(this.user_nation);
	}

	/**
	 * 단위코드 설정
	 * @param unit_cd 단위코드
	 */
	public void setUnit_cd(String unit_cd) {
		this.unit_cd = unit_cd;
	}
	/**
	 * 단위코드 취득
	 * @return unit_cd 단위코드
	 */
	public String getUnit_cd() {
		return StringUtil.nvl(this.unit_cd);
	}

	/**
	 * 통화코드 설정
	 * @param curr_cd 통화코드
	 */
	public void setCurr_cd(String curr_cd) {
		this.curr_cd = curr_cd;
	}
	/**
	 * 통화코드 취득
	 * @return curr_cd 통화코드
	 */
	public String getCurr_cd() {
		return StringUtil.nvl(this.curr_cd);
	}

	/**
	 * 통화코드명 설정
	 * @param curr_nm 통화코드명
	 */
	public void setCurr_nm(String curr_nm) {
		this.curr_nm = curr_nm;
	}
	/**
	 * 통화코드명 취득
	 * @return curr_nm 통화코드명
	 */
	public String getCurr_nm() {
		return StringUtil.nvl(this.curr_nm);
	}

	/**
	 * 유저담당자 설정
	 * @param emp_id 유저담당자
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	/**
	 * 유저담당자 취득
	 * @return emp_id 유저담당자
	 */
	public String getEmp_id() {
		return StringUtil.nvl(this.emp_id);
	}

	/**
	 * 거래처코드 설정
	 * @param account_cd 거래처코드
	 */
	public void setAccount_cd(String account_cd) {
		this.account_cd = account_cd;
	}
	/**
	 * 거래처코드 취득
	 * @return account_cd 거래처코드
	 */
	public String getAccount_cd() {
		return StringUtil.nvl(this.account_cd);
	}

	/**
	 * 추가정보 설정
	 * @param bigo 추가정보
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * 추가정보 취득
	 * @return bigo 추가정보
	 */
	public String getBigo() {
		return StringUtil.nvl(this.bigo);
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
	 * 회원PWD일치여부 설정
	 * @param pw_true_false 회원PWD일치여부
	 */
	public void setPw_true_false(String pw_true_false) {
		this.pw_true_false = pw_true_false;
	}
	/**
	 * 회원PWD일치여부 취득
	 * @return pw_true_false 회원PWD일치여부
	 */
	public String getPw_true_false() {
		return StringUtil.nvl(this.pw_true_false);
	}

	/**
	 * 승급요청 담당자 설정
	 * @param ug_emp 승급요청 담당자
	 */
	public void setUg_emp(String ug_emp) {
		this.ug_emp = ug_emp;
	}
	/**
	 * 승급요청 담당자 취득
	 * @return ug_emp 승급요청 담당자
	 */
	public String getUg_emp() {
		return StringUtil.nvl(this.ug_emp);
	}

	/**
	 * 고객회사 전화번호 설정
	 * @param user_com_tel 고객회사 전화번호
	 */
	public void setUser_com_tel(String user_com_tel) {
		this.user_com_tel = user_com_tel;
	}
	/**
	 * 고객회사 전화번호 취득
	 * @return user_com_tel 고객회사 전화번호
	 */
	public String getUser_com_tel() {
		return StringUtil.nvl(this.user_com_tel);
	}

// 한국야금 ---------------------------------------

	/**
	 * 사용자상태 설정
	 * @param status 사용자상태
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 사용자상태 취득
	 * @return status 사용자상태
	 */
	public String getStatus() {
		return StringUtil.nvl(this.status);
	}
	
	/**
	 * 그룹명 설정
	 * @param group_nm 그룹명
	 */
	public void setGroup_nm(String group_nm) {
		this.group_nm = group_nm;
	}
	/**
	 * 그룹명 취득
	 * @return group_nm 그룹명
	 */
	public String getGroup_nm() {
		return StringUtil.nvl(this.group_nm);
	}


	/**
	 * 로그인아이피 설정
	 * @param login_ip 로그인아이피
	 */
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	/**
	 * 로그인아이피 취득
	 * @return login_ip 로그인아이피
	 */
	public String getLogin_ip() {
		return StringUtil.nvl(this.login_ip);
	}
	/**
	 * 사용자아이디 설정
	 * @param str 사용자아이디
	 */
	public void setUser_id(String str) {
		this.user_id = str;
	}
	/**
	 * 사용자아이디 취득
	 * @return String 사용자아이디
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
	}
	
	/**
	 * 비밀번호 설정
	 * @param str 비밀번호
	 */
	public void setPassword(String str) {
		this.password = str;
	}
	/**
	 * 비밀번호 취득
	 * @return String 비밀번호
	 */
	public String getPassword() {
		return StringUtil.nvl(this.password);
	}
	
	/**
	 * 사용자이름 설정
	 * @param str 사용자이름
	 */
	public void setUser_nm(String str) {
		this.user_nm = str;
	}
	/**
	 * 사용자이름 취득
	 * @return String사용자이름
	 */
	public String getUser_nm() {
		return StringUtil.nvl(this.user_nm);
	}

	/**
	 * 사용자구분(내부,외부,관리자) 설정
	 * @param str 사용자이름
	 */
	public void setUser_kind(String str) {
		this.user_kind = str;
	}
	/**
	 * 사용자이름(내부,외부,관리자) 취득
	 * @return String사용자이름
	 */
	public String getUser_kind() {
		return StringUtil.nvl(this.user_kind);
	}
	
	/**
	 * 사용자번호 설정
	 * @param str 사용자번호
	 */
	public void setNo_emp(String str) {
		this.no_emp = str;
	}
	/**
	 * 사용자번호 취득
	 * @return String사용자번호
	 */
	public String getNo_emp() {
		return StringUtil.nvl(this.no_emp);
	}
	/**
	 * 회사코드 설정
	 * @param str 회사코드
	 */
	public void setCd_company(String str) {
		this.cd_company = str;
	}
	/**
	 * 회사코드 취득
	 * @return String 회사코드
	 */
	public String getCd_company() {
		return StringUtil.nvl(this.cd_company);
	}
	
	/**
	 * 회사이름 설정
	 * @param str 부서코드
	 */
	public void setNm_company(String str) {
		this.nm_company = str;
	}
	/**
	 * 회사이름 취득
	 * @return String 회사이름
	 */
	public String getNm_company() {
		return StringUtil.nvl(this.nm_company);
	}

	/**
	 * 부서코드 설정
	 * @param str 부서코드
	 */
	public void setCd_dept(String str) {
		this.cd_dept = str;
	}
	/**
	 * 부서코드 취득
	 * @return String 부서코드
	 */
	public String getCd_dept() {
		return StringUtil.nvl(this.cd_dept);
	}
	
	/**
	 * 부서이름 설정
	 * @param str 부서이름
	 */
	public void setNm_dept(String str) {
		this.nm_dept = str;
	}
	/**
	 * 부서이름 취득
	 * @return String 부서이름
	 */
	public String getNm_dept() {
		return StringUtil.nvl(this.nm_dept);
	}
	
	/**
	 * 전화번호 설정
	 * @param no_tel 전화번호
	 */
	public void setNo_tel(String no_tel) {
		this.no_tel = no_tel;
	}
	/**
	 * 전화번호 취득
	 * @return no_tel 전화번호
	 */
	public String getNo_tel() {
		return StringUtil.nvl(this.no_tel);
	}
	
	/**
	 * 영업분야 설정
	 * @param str 영업분야
	 */
	public void setCd_bizarea(String str) {
		this.cd_bizarea = str;
	}
	/**
	 * 영업분야 취득
	 * @return String 영업분야
	 */
	public String getCd_bizarea() {
		return StringUtil.nvl(this.cd_bizarea);
	}
	/**
	 * 파트너코드 설정
	 * @param str 파트너코드
	 */
	public void setCd_partner(String str) {
		this.cd_partner = str;
	}
	/**
	 * 파트너코드 취득
	 * @return String 파트너코드
	 */
	public String getCd_partner() {
		return StringUtil.nvl(this.cd_partner);
	}
	/**
	 * 영업그룹 설정
	 * @param str 영업그룹 
	 */
	public void setCd_salegrp(String str) {
		this.cd_salegrp = str;
	}
	/**
	 * 영업그룹 취득
	 * @return String 영업그룹 
	 */
	public String getCd_salegrp() {
		return StringUtil.nvl(this.cd_salegrp);
	}
	
	
	/**
	 * 파트너이름 설정
	 * @param str 파트너이름
	 */
	public void setNm_partner(String str) {
		this.nm_partner = str;
	}
	/**
	 * 파트너이름 취득
	 * @return String 파트너이름
	 */
	public String getNm_partner() {
		return StringUtil.nvl(this.nm_partner);
	}
	
	/**
	 * 사용자메뉴 그룹의 부서 아이디 설정
	 * @param str 사용자메뉴 그룹의 부서 아이디
	 */
	public void setGroup_id(String str) {
		this.group_id = str;
	}
	/**
	 * 사용자메뉴 그룹의 부서 아이디 취득
	 * @return String 사용자메뉴 그룹의 부서 아이디
	 */
	public String getGroup_id() {
		return StringUtil.nvl(this.group_id);
	}
	
	/**
	 * 조회권한 설정
	 * @param str 조회권한
	 */
	public void setSearch_kind(String str) {
		this.search_kind = str;
	}
	/**
	 * 조회권한 취득
	 * @return String 조회권한
	 */
	public String getSearch_kind() {
		return StringUtil.nvl(this.search_kind);
	}
		
	/**
	 * 팀장여부 설정
	 * @param str 팀장여부
	 */
	public void setTeam_chief_yn(String str) {
		this.team_chief_yn = str;
	}
	/**
	 * 팀장여부 취득
	 * @return String 팀장여부
	 */
	public String getTeam_chief_yn() {
		return StringUtil.nvl(this.team_chief_yn);
	}
	
	/**
	 * 그룹레벨코드 설정
	 * @param str 그룹레벨코드
	 */
	public void setGroupLevelCod(String str) {
		this.groupLevelCod = str;
	}
	/**
	 * 그룹레벨코드 취득
	 * @return String 그룹레벨코드
	 */
	public String getGroupLevelCod() {
		return StringUtil.nvl(this.groupLevelCod);
	}
	
	/**
	 * 배송코드 설정
	 * @param cd_area 배송코드
	 */
	public void setCd_area(String cd_area) {
		this.cd_area = cd_area;
	}
	/**
	 * 배송코드 취득
	 * @return cd_area 배송코드
	 */
	public String getCd_area() {
		return StringUtil.nvl(this.cd_area);
	}
	
	/**
	 * 로그인상태 설정
	 * @param fg_login 로그인상태
	 */
	public void setFg_login(String fg_login) {
		this.fg_login = fg_login;
	}
	/**
	 * 로그인상태 취득
	 * @return fg_login 로그인상태
	 */
	public String getFg_login() {
		return StringUtil.nvl(this.fg_login);
	}

	/**
	 * 세션ID 설정
	 * @param session_id 세션ID
	 */
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	/**
	 * 세션ID 취득
	 * @return session_id 세션ID
	 */
	public String getSession_id() {
		return StringUtil.nvl(this.session_id);
	}
	
	/**
	 * 사용자대리인번호 설정
	 * @param no_emp_agent 사용자대리인번호
	 */
	public void setNo_emp_agent(String no_emp_agent) {
		this.no_emp_agent = no_emp_agent;
	}
	/**
	 * 사용자대리인번호 취득
	 * @return no_emp_agent 사용자대리인번호
	 */
	public String getNo_emp_agent() {
		return StringUtil.nvl(this.no_emp_agent);
	}
	
	/**
	 * 검색대상사원리스트 설정
	 * @param list_emp_agent 검색대상사원리스트
	 */
	public void setList_emp_agent(List<String> list_emp_agent) {
		this.list_emp_agent = list_emp_agent;
	}
	/**
	 * 검색대상사원리스트 취득
	 * @return list_emp_agent 검색대상사원리스트
	 */
	public List<String> getList_emp_agent() {
		return this.list_emp_agent;
	}
		
	/**
	 * 병원일지정지여부 설정
	 * @param hospital_use_yn 병원일지정지여부
	 */
	public void setHospital_use_yn(String hospital_use_yn) {
		this.hospital_use_yn = hospital_use_yn;
	}
	/**
	 * 병원일지정지여부 취득
	 * @return hospital_use_yn 병원일지정지여부
	 */
	public String getHospital_use_yn() {
		return StringUtil.nvl(this.hospital_use_yn);
	}
	
	
	/**
	 * @Override
	 * 세션에 추가되었을 때
	 */
    public void valueBound(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub
    	Log logger = LogFactory.getLog(getClass());
    	logger.debug("set Session");
    }

	/**
	 * @Override
	 * 세션에서 제거 되었을 때(타임아웃으로 세션에서 제거될때)
	 */

    public void valueUnbound(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub
    	Log logger = LogFactory.getLog(getClass());
    	logger.debug("remove Session");
    }
   
}
