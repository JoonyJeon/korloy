/*****************************************************************************
 * 프로그램명  : LoginService.java
 * 설     명  : 로그인 정보
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.login.service;

import java.util.List;

import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;

/**
 * 로그인 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface LoginService {

// 한국야금
	/**
	 * 회원 정보 취득
	 * @param paramUserInfo ȸ�� 회원 정보
	 * @return User 로그인 유저정보
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
    public User selectUserInfo(User paramUserInfo) ;

	/**
	 * 회원 비밀번호 변경
	 * @param paramUserInfo
	 * @return 처리 성공 row 수
	 * @throws UserSysException
	 */
	public boolean updateUserPw(User paramUserInfo) throws UserSysException;
// 한국야금 ----------------------
    /**
	 * 로그인/로그아웃시 로그 생성
	 * @param str  입력한 비밀번호
	 * @return String 암호화된 비밀번호
	 */
    public boolean setConnLog(User user, String str) throws BizException, UserSysException ;
    
    /**
	 * 회원 세션ID 취득
	 * @param paramUserInfo 회원 정보
	 * @return String 세션ID
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
    public String selectUserSessionId(User paramUserInfo) ;

    /**
	 * ip조회
	 * @return List<String> 아이피 리스트
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
	public List<String> selectUserIp();
	
	/**
   	 *  로그인실패 카운트 수정 처리
   	 * @param paramUserInfo 수정할 유저정보
   	 */
    public boolean updateLoginFailCnt(User paramUserInfo) throws UserSysException ;
      
    /**
   	 *  로그인성공 카운트 수정 처리
   	 * @param paramUserInfo 수정할 유저정보
   	 */
    public boolean updateLoginSuccessCnt(User paramUserInfo) throws UserSysException ;
  
    
    
}
