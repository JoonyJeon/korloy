/*****************************************************************************
 * 프로그램명  : GradeService.java
 * 설     명  : Grade Guide 정보
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.30   SJY    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.grade.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.common.exception.UserSysException;

/**
 * Grade Guide 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface GradeService {
	
    /**
	  * 재종가이드 검색조건 조회
	  * @return Map<String, Object> 검색조건
	 */
	public Map<String, Object> selectSearchConditionList(HttpServletRequest request) throws UserSysException;
	 /**
	  * 재종가이드 검색결과 리스트 조회
	  * @return List<GradeInfo> 검색결과 리스트
	 */
    public Map<String, Object> selectGradeList(HttpServletRequest request, GradeInfo info) throws UserSysException;
    /**
     * 재종가이드 검색결과 리스트 조회
     * @return List<GradeInfo> 검색결과 리스트
     */
    public GradeInfo selectGradeDetail(HttpServletRequest request, GradeInfo info) throws UserSysException;
}
