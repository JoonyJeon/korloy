/*****************************************************************************
 * 프로그램명  : CodeService.java
 * 설     명  : 코드정보처리
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.lang.service;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.LanguageInfo;

/**
 * 코드정보 처리
 * @author  eaction
 * @version 1.0
 */
public interface LanguageService {
    
	/**
	 * TB_ECAT_LANG_TRAN_M리스트 취득
	 * @return List TB_ECAT_LANG_TRAN_M 객체
	 */
    public List<LanguageInfo> getLangMInfo() throws UserSysException ;

    /**
	 * TB_ECAT_LANG_TRAN_D리스트 취득
	 * @return List TB_ECAT_LANG_TRAN_D 객체
	 */
	public List<LanguageInfo> getLangDInfo() throws UserSysException ;
    
	/**
	 * TB_ECAT_LANG_DISPLAY리스트 취득
	 * @return List TB_ECAT_LANG_DISPLAY 객체
	 */
//	public List<LanguageInfo> getLangDisplayInfo() throws UserSysException ;
	
    /**
	 * TB_ECAT_LANG리스트 취득
	 * @return List TB_ECAT_LANG 객체
	 */
	public List<CodeInfo> getLangCode() throws UserSysException ;
	
	/**
	 * 메모리 리로드
	 */
    public void reloadLanguage() throws UserSysException ;
}
