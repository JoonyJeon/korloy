/*****************************************************************************
 * 프로그램명  : LanguageDao.java
 * 설     명  : 언어정보 DAO인터페이스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.02  정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.lang.dao;

import java.util.List;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.LanguageInfo;

/**
 * 언어정보 DAO인터페이스
 *
 * @author  eaction
 * @version 1.0
 */
public interface LanguageDao {

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
	
    
    
}
