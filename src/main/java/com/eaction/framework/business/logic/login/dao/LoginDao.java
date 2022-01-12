/*****************************************************************************
 * 프로그램명  : LoginDAO.java
 * 설     명  : 회원 로그인 관리 DAO
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.login.dao;

import java.util.List;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.User;


/**
 * 회원 로그인 관리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface LoginDao {
// 한국야금
	/**
	 * 회원 정보 취득
	 * @param paramUserInfo 회원 정보
	 * @return User 유저정보
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
	public int updateUserPw(User paramUserInfo) throws UserSysException;
    
// 한국야금 ----------------------
    /**
	 * 로그인/로그아웃시 로그 생성
	 * @param str  입력한 비밀번호
	 * @return String 암호화된 비밀번호
	 */
    public int setConnLog(User user) throws BizException, UserSysException ;  
    
    /**
	 * 회원 세션ID 취득
	 * @param paramUserInfo 회원 정보
	 * @return String 세션ID
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
    public String selectUserSessionId(User paramUserInfo) ;


    /**
	 * ip address 조회
	 * @return String 아이피 리스트
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
	public List<String> selectUserIp();
	
	
	/**
	 * 로그인실패 카운트 처리
     * @param User 유저정보
	 * @return ProcessResult 결과데이터
	 */
    public int updateLoginFailCnt(User paramUserInfo) throws UserSysException;
    
    
    /**
	 * 로그인성공 카운트 처리
     * @param User 유저정보
	 * @return ProcessResult 결과데이터
	 */
    public int updateLoginSuccessCnt(User paramUserInfo) throws UserSysException;
    
    
         
}
