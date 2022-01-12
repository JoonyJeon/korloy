/*****************************************************************************
 * 프로그램명  : SearchDao.java
 * 설     명  : 검색 처리를 위한 관리 DAO
 * 참고  사항  : 자동Mapping 처리로 xml에 정의한 대로 구현체 클래스는 자동생성 된다
 *          xml의 namespace에 구현체 클래스 생성을 위한 본 인터페이스의 풀패키지와 인터페이스 명칭을 기술한다
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.02   정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.search.dao;

import java.util.List;

import com.eaction.framework.business.logic.search.model.SearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;


/**
 * 검색 처리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface SearchDao {
	
	/**
	 * 검색조건 Main App 조회
	 * @return  List<CodeInfo>
	 */
	public List<SearchInfo> selectSearchCondMainApp() throws UserSysException;
	
	/**
	 * 검색조건 Sub App 조회
	 * @param SearchInfo info
	 * @return  List<SearchInfo>
	 */
	public List<SearchInfo> selectSearchCondSubApp(SearchInfo info) throws UserSysException;
	
	/**
	 * 검색조건 Item Group 조회
	 * @param SearchInfo info
	 * @return  List<SearchInfo>
	 */
	public List<SearchInfo> selectSearchCondItemGroup(SearchInfo info) throws UserSysException;

	/**
	 * 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	public int selectSearchItemListCnt(SearchInfo info) throws UserSysException;
	
	/**
	 * 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	public List<SearchInfo> selectSearchItemList(SearchInfo info) throws UserSysException;
	
	/**
	 * Parametric 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	public int selectParaSearchItemListCnt(SearchInfo info) throws UserSysException;
	
	/**
	 * Parametric 검색 조회
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	public List<SearchInfo> selectParaSearchItemList(SearchInfo info) throws UserSysException;
	
	/**
	 * GTC 계층조회
	 * @param SearchInfo info
	 * @return  GTC 계층
	 */
	public List<SearchInfo> selectGTCHierList(SearchInfo info) throws UserSysException;
	
	/**
	 * Parametric Symbol 리스트
	 * @param SearchInfo info
	 * @return  Parametric Symbol 리스트
	 */
	public List<SearchInfo> selectParametricSymbolList(SearchInfo info) throws UserSysException;
	
	
	
}
