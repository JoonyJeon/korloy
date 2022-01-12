/*****************************************************************************
 * 프로그램명  : SearchService.java
 * 설     명  : 검색 서비스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.30   정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.search.service;

import java.util.List;
import java.util.Map;

import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.business.logic.search.model.SearchInfo;
import com.eaction.framework.common.exception.UserSysException;

import net.sf.json.JSONObject;

/**
 * 검색 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface SearchService {
	
	/**
	 * 검색조건 Main App 조회
	 * @return  List<CodeInfo>
	 */
	public List<SearchInfo> selectSearchCondMainApp() throws UserSysException;
	
	/**
	 * 검색조건 Sub App 조회
	 * @return  List<SearchInfo>
	 */
	public List<SearchInfo> selectSearchCondSubApp(SearchInfo info) throws UserSysException;
	
	/**
	 * 검색조건 Item Group 조회
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
	 * 검색 조회
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
	public Map selectGTCHierList(SearchInfo info) throws UserSysException;
	
	/**
	 * Parametric Symbol 리스트
	 * @param SearchInfo info
	 * @return  Parametric Symbol 리스트
	 */
	public List<SearchInfo> selectParametricSymbolList(SearchInfo info) throws UserSysException;
}
