/*****************************************************************************
 * 프로그램명  : CommMenuDao.java
 * 설     명  : 메뉴정보 DAO인터페이스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.menu.dao;

import java.util.List;

import com.eaction.framework.common.exception.UserSysException;

/**
 * 메뉴정보 DAO인터페이스
 *
 * @author  eaction
 * @version 1.0
 */
public interface CommMenuDao {
	
	/**
	 * 그룹코드정보 취득
	 * @param inParam 입력파라메터
	 * @return List 결과그룹코드리스트정보
	 */	
    public List getMenuInfo() throws UserSysException;
    
    /**
	 * 메뉴관리리스트 취득
	 * @param prefix 메뉴관리정보
	 * @return List 메뉴관리리스트
	 */	
    public List selectMenuInfoList() throws UserSysException ;
}
