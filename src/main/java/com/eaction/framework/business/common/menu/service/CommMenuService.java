/*****************************************************************************
 * 프로그램명  : CommMenuService.java
 * 설     명  : 메뉴정보처리 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.menu.service;

import java.util.List;

import com.eaction.framework.common.exception.UserSysException;

/**
 * 메뉴정보 처리
 * @author  eaction
 * @version 1.0
 */
public interface CommMenuService {
	/**
	 * 메뉴리스트 취득
	 * @param nPage 현재 페이지
	 * @param SearchInfo 검색조건
	 * @return PagingTable 게시판리스트를 담은 페이징처리 객체
	 */	
    public List getMenuInfo() throws UserSysException ;
	
    /**
	 * 메뉴관리리스트 취득
	 * @return List 메뉴관리리스트를 담은 페이징처리 객체
	 */	
	public List selectMenuInfoList() throws UserSysException ;
}
