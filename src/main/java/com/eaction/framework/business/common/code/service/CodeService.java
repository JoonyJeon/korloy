/*****************************************************************************
 * 프로그램명  : CodeService.java
 * 설     명  : 코드정보처리
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.code.service;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;

/**
 * 코드정보 처리
 * @author  eaction
 * @version 1.0
 */
public interface CodeService {
    
	/**
	 * 코드리스트 취득
	 * @return List 코드리스트를 담은 객체
	 */
    public List getCodeInfo() throws UserSysException ;
    
    /**
     * 코드그룹정보 취득
	 * @return List 코드그룹정보
	 */
    public List getCodeGroup() throws UserSysException ;

    /**
	 * 상위가 있는 코드리스트 취득
	 * @return List 상위가 있는 코드리스트를 담은 객체
	 */
	public List getUpCodeInfo() throws UserSysException ;
	
	/**
	 * 기타 마스터 코드리스트 취득
	 * @return List 코드리스트를 담은 객체
	 */
    public Properties getOhterCodeInfo() throws UserSysException ;
    
    
	/**
	 * 메모리 리로드
	 */
    public void reloadCode() throws UserSysException ;
    
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
