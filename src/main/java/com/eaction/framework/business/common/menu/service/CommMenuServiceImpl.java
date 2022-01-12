/*****************************************************************************
 * 프로그램명  : CommMenuServiceImpl.java
 * 설     명  : 메뉴정보처리 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.business.common.menu.dao.CommMenuDao;


/**
 * 메뉴정보관리 비즈니스 로직
 * @author eaction
 * @version 1.0
 */
@Service
public class CommMenuServiceImpl implements CommMenuService {
	@Resource
    private CommMenuDao commMenuDao;
	
    /**
	 * 메뉴 리스트 취득
	 * @param nPage 현재페이지번호
	 * @param searchInfo 검색조건
	 * @return PagingTable 코드리스트를 담은 페이징처리 객체
	 */	
    @Override
	public List getMenuInfo() throws UserSysException {
		        
		//코드의 총갯수를 취득
		List menuList = commMenuDao.getMenuInfo();		
        
        return menuList;
	}		
	
	/**
	 * 메뉴관리리스트 취득
	 * @return List 메뉴관리리스트를 담은 페이징처리 객체
	 */	
	@Override
	public List selectMenuInfoList() throws UserSysException {
		
		//DB에서 취득한 해당 페이지의 리스트만 설정되어 넘어온다
        List arList = commMenuDao.selectMenuInfoList();
                
        return arList;
	}
}