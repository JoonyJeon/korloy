/*****************************************************************************
 * 프로그램명  : GradeDao.java
 * 설     명  : 재종가이드 처리를 위한 관리 DAO
 * 참고  사항  : 자동Mapping 처리로 xml에 정의한 대로 구현체 클래스는 자동생성 된다
 *          xml의 namespace에 구현체 클래스 생성을 위한 본 인터페이스의 풀패키지와 인터페이스 명칭을 기술한다
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.02   SJY    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.grade.dao;

import java.util.List;

import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.common.model.CodeInfo;


/**
 * 재종 가이드 처리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface GradeDao {
	
	/**
	 * 재종가이드 검색조건 항목 취득
	 * @return  List<CodeInfo>
	 */
	public List<CodeInfo> selectSearchConditionList();
	
	/**
	 * 재종가이드 검색결과 리스트 개수 취득
	 * @return  int
	 */
	public int selectGradeListCnt(GradeInfo info);
	
	/**
	 * 재종가이드 검색결과 리스트 취득
	 * @return  List<GradeInfo>
	 */
	public List<GradeInfo> selectGradeList(GradeInfo info);
	
	/**
	 * 재종가이드 & 아이템
	 * 재종 설명 팝업내용
	 * @return  GradeInfo
	 */
	public GradeInfo selectGradeDetail(GradeInfo info);
	
}
