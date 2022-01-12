/*****************************************************************************
 * 프로그램명  : UserDao.java
 * 설     명  : 회원관리 persistence-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.21  YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.user.dao;

import java.util.List;

import com.eaction.framework.business.logic.user.model.UserInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;

/**
 * 회원관리 DAO
 * @author  eaction
 * @version 1.0
 */
public interface UserDao {

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
	 * @return
	 * @throws UserSysException
	 */
	public int insertUser(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 회원가입 시 이메일 중복 체크 
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public String selectUserEmailDuplChk(UserInfo userInfo) throws UserSysException;

	/**
	 * "PRE" 회원 email_cert_key 조회 (메일발송용)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public UserInfo selectUserEmailCertInfo(UserInfo userInfo) throws UserSysException;

	/**
	 * 로그인 회원 비밀번호 변경 입력비밀번호 일치 확인 
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectChkUserPwd(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 'PRE' 회원 이메일 인증 전에 가입 재시도 시 name, pw 수정
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateUserJoinInfo(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 이메일 인증 처리(일치하는 user_id와 email_cert_key쌍 존재시 EMAIL_CERT_YN = 'Y')
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateUserEmailAuthCd(UserInfo userInfo) throws UserSysException;
	
	/**
	 * 회원가입 인증 완료 (성공시 USE_YN = 'Y'_)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateUserJoinConf(UserInfo userInfo) throws UserSysException;

	/**
	 * email_cert_key update (reset password)
	 * @param userInfo
	 * @return
	 * @throws UserSysException
	 */
//	public int updateUserEmailCertKey(UserInfo userInfo) throws UserSysException;
	
    /**
     * 마이페이지 정보 수정 
     * 비밀번호 변경 
     * @param mypageInfo
     * @return
     * @throws UserSysException
     */
    public int updateUserInfo(UserInfo userInfo) throws UserSysException;

    /**
     * 승급요청
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int updateUserGrp(UserInfo userInfo) throws UserSysException;
    
    // 회원탈퇴 처리 STEP1 ~ STEP5
    /**
     * STEP1 : 회원탈퇴처리 (어셈블리 상세 fk.assem_no 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfoStepDeleteAssem_d(UserInfo userInfo) throws UserSysException;
    
    /**
     * STEP2 : 회원탈퇴처리 (어셈블리 마스터 fk.user_id 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfoStepDeleteAssem_m(UserInfo userInfo) throws UserSysException;
    
    /**
     * STEP3 : 회원탈퇴처리 (장바구니 견적서 fk.user_no 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfoStepDeleteBasket_o(UserInfo userInfo) throws UserSysException;
    
    /**
     * STEP4 : 회원탈퇴처리 (장바구니 상세 fk.cart_no 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfoStepDeleteBasket_d(UserInfo userInfo) throws UserSysException;
    
    /**
     * STEP5 : 회원탈퇴처리 (장바구니 마스터 fk.user_id 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfoStepDeleteBasket_m(UserInfo userInfo) throws UserSysException;
    
    /**
     * STEP6 : 회원탈퇴처리 (user테이블 데이타 삭제)
     * @param userInfo
     * @return
     * @throws UserSysException
     */
    public int deleteUserInfo(UserInfo userInfo) throws UserSysException;
    
    // 회원탈퇴 처리 STEP1 ~ STEP5 END ----------------------
    
	/**
	 * 국가 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserNationCodeList();
	
	/**
	 * 단위 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserUnitCodeList();
	
	/**
	 * 통화 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserCurrencyCodeList();

	/**
	 * 언어 정보 코드인포 리스트
	 * @return
	 */
	public List<CodeInfo>selectUserLangCodeList();
	
	/**
	 * 국가코드와 담당자이메일
	 * @return
	 */
	public List<CodeInfo>selectEmpbyNationCdList();
	/**
	 * 국가코드와 담당자이메일
	 * @return
	 */
	public int updateUserPassword(UserInfo userInfo);

	/**
	 * 메일 수신자 참조자 
	 * @param paramInfo
	 * @return
	 * @throws UserSysException
	 */
	public UserInfo selectUserMailReceiver(String nation_cd) throws UserSysException;
}
