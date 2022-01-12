/*****************************************************************************
 * 프로그램명  : UserService.java
 * 설     명  : 회원정보관리 business-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.21  YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.business.logic.user.model.UserInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;

/**
 * 회원정보관리 서비스
 * @author  eaction
 * @version 1.0
 */
public interface UserService {

  	/**
  	 * 회원정보 조회 
  	 * @param userInfo
  	 * @return
  	 * @throws UserSysException
  	 */
  	public UserInfo selectUserInfo(UserInfo userInfo) throws UserSysException;
  	
	/**
	 * 회원정보 등록 
	 * @param userInfo
	 * @return 처리결과 값
	 * @throws UserSysException
	 */
	public boolean insertUser(HttpServletRequest request, UserInfo userInfo) throws UserSysException, UnsupportedEncodingException;
	
	/**
	 * 회원가입 시 이메일 중복 체크 
	 * @param userInfo
	 * @return 조회 된 row 갯수
	 * @throws UserSysException
	 */
	public String selectUserEmailDuplChk(UserInfo userInfo) throws UserSysException;

	/**
	 * "PRE" 회원 email_cert_key 조회 (메일발송용)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean selectUserEmailCertInfo(HttpServletRequest request, UserInfo userInfo) throws UserSysException;
	
	/**
	 * 회원 이메일 인증 여부 값
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean selectUserAuthYN(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 'PRE' 회원 이메일 인증 전에 가입 재시도 시 name, pw 수정
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateUserJoinInfo(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 이메일 인증 처리(일치하는 user_id와 email_cert_key쌍 존재시 EMAIL_CERT_YN = 'Y')
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateUserEmailAuthCd(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 회원가입 인증 완료 (성공시 USE_YN = 'Y'_)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateUserJoinConf(UserInfo userInfo) throws UserSysException;
    
	/**
	 * email_cert_key update (reset password)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateUserEmailCertKey(HttpServletRequest request, UserInfo userInfo) throws UserSysException;

	/**
	 * 로그인 회원 비밀번호 변경 입력비밀번호 일치 확인
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean selectChkUserPwd(UserInfo userInfo) throws UserSysException;
	
    /**
     * 마이페이지 정보 수정
     * @param mypageInfo
     * @return
     * @throws UserSysException
     */
    public boolean updateUserInfo(UserInfo paramUserInfo) throws UserSysException;
    
    /**
     * 회원탈퇴 처리 STEP1 ~ STEP5
     * @param paramUserInfo
     * @return
     * @throws UserSysException
     */
    public boolean deleteUserInfo(UserInfo paramUserInfo) throws UserSysException;
    
    /**
     * VIP 승급요청
     * @param paramUserInfo
     * @return
     * @throws UserSysException
     */
    public boolean reqUpgradeUserGrp(HttpServletRequest request, UserInfo paramUserInfo) throws UserSysException;

	/**
	 * 국가 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserNationCodeList() throws UserSysException;
	
	/**
	 * 단위 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserUnitCodeList() throws UserSysException;
	
	/**
	 * 통화 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserCurrencyCodeList() throws UserSysException;

	/**
	 * 언어 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserLangCodeList() throws UserSysException;
	
	/**
	 * 통화 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectEmpbyNationCdList() throws UserSysException;
	
	/**
	 * 비밀번호 재설정 및 비밀번호 변경
	 * @return
	 */
	public boolean updateUserPassword(UserInfo userInfo) throws UserSysException;
}
