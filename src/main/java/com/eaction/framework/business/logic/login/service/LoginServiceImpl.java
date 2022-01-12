/*****************************************************************************
 * 프로그램명  : LoginServiceImpl.java
 * 설     명  : 회원 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.login.dao.LoginDao;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.StringUtil;

/**
 * 회원 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( LoginServiceImpl.class );
	
	/** 로그인 처리 DAO */
	@Resource
	private LoginDao loginDao;

    /**
	 * 회원 정보 저장
	 * @param paramUserInfo ȸ�� 회원 정보
	 * @return  User 로그인 유저정보
     * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
	@Override
    public User selectUserInfo(User paramUserInfo) {
		logger.debug("selectUserInfo--{}", ConstKey.START_LOG);
		
		// 회원 정보 가져오기
		User user = loginDao.selectUserInfo(paramUserInfo);

		logger.debug("selectUserInfo--{}", ConstKey.END_LOG);
    	return user;
    }
	
	/**
	 * 회원 비밀번호 변경 (수정필요)
	 * @param str  입력한 비밀번호
	 * @return String 암호화된 비밀번호
	 */
	@Override
	@Transactional
	public boolean updateUserPw(User paramUserInfo) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		
    	iResult = loginDao.updateUserPw(paramUserInfo);

    	if(iResult < 1) {
    		bResult = false;
    	}
    	
    	return bResult;
	}

	/**
	 * 로그인/로그아웃시 로그 생성
	 * @param str  입력한 비밀번호
	 * @return String 암호화된 비밀번호
	 */
	@Override
	@Transactional
    public boolean setConnLog(User user, String str) throws BizException, UserSysException {
		boolean bResult = true;
		int iResult = 0;
		
		if(user == null){
			user = new User();
			user.setUser_id("Lost Session");
		}
    	
		if("".equals(user.getCd_company())) {
			user.setCd_company("GCRC");
		}
		
    	user.setFg_login(str);
    	iResult = loginDao.setConnLog(user);

    	if(iResult < 1) {
    		bResult = false;
    	}
    	
    	return bResult;
    }
	
	/**
	 * 회원 세션ID 취득
	 * @param paramUserInfo 회원 정보
	 * @return String 세션ID
	 * @exception BizException 업무 예외
     * @exception UserSysException 시스템 예외
	 */
	@Override
    public String selectUserSessionId(User paramUserInfo) {
		// 세션 ID 가져오기
		String session_id = loginDao.selectUserSessionId(paramUserInfo);
    	return session_id;
    }
	
	/**
	 * ip address 확인
	 * @param paramUserInfo 회원 정보
	 * @return String 세션ID
	 * @exception BizException 업무 예외
	 * @exception UserSysException 시스템 예외
	 */
	@Override
	public List<String> selectUserIp() {
		// ip address 확인
		return loginDao.selectUserIp();
	}
	
	/**
   	 *  로그인실패 카운트 수정 처리
   	 * @param paramUserInfo 수정할 유저정보
   	 */
	@Override
	@Transactional
    public boolean updateLoginFailCnt(User paramUserInfo) throws UserSysException {

		loginDao.updateLoginFailCnt(paramUserInfo);

		return true;
	}
      
    /**
   	 *  로그인성공 카운트 수정 처리
   	 * @param paramUserInfo 수정할 유저정보
   	 */
	@Override
	@Transactional
    public boolean updateLoginSuccessCnt(User paramUserInfo) throws UserSysException {

		loginDao.updateLoginSuccessCnt(paramUserInfo);

		return true;
	}
  
  
	
}
