/*****************************************************************************
 * 프로그램명  : CodeDao.java
 * 설     명  : 코드정보 DAO인터페이스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.code.dao;

import java.util.List;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;

/**
 * 코드정보 DAO인터페이스
 *
 * @author  eaction
 * @version 1.0
 */
public interface CodeDao {

	/**
	 * 그룹코드정보 취득
	 * @param inParam 입력파라메터
	 * @return List 결과그룹코드리스트정보
	 */
    public List getCodeInfo() throws UserSysException;
    
    /**
	 * 기타 마스터 코드정보 취득
	 * @param inParam 입력파라메터
	 * @return List 결과그룹코드리스트정보
	 */
    public List getUserGroupCodeInfo(String inParam) throws UserSysException;

    /**
     * 상위코드가 있는 코드정보 취득
	 * @return List 결과화면표시정보리스트
	 */
    public List selectUpCodeInfo() throws UserSysException ;
    
    /**
     * 코드그룹정보
	 * @return List 코드그룹정보리스트
	 */
    public List getCodeGroup() throws UserSysException ;
    
	/**
	 * 국가코드 콤보 조회
	 * @return List 국가코드정보
	 */
    public List getNationCodeInfo() throws UserSysException;
    
    /**
	 * 개인정보동의서 조회
	 * @return CodeInfo 개인정보동의서
	 */
    public CodeInfo getPrivacyInfo(String lang_code) throws UserSysException;
    
}
