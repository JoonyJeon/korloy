/*****************************************************************************
 * 프로그램명  : StaffDao.java
 * 설     명  : 사용자정보관리 persistence-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.staff.dao;

import java.util.List;

import com.eaction.framework.business.logic.staff.model.StaffInfo;
import com.eaction.framework.business.logic.staff.model.StaffSearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.ProcessResult;

/**
 * 스텝정보관리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface StaffDao {
	
	/**
	 * 스텝 정보  갯수 취득
	 * @param ProfileSettingInfo 검색데이타
	 * @return List 일반정보리스트
	 */
    public int selectStaffListCnt(StaffSearchInfo Info) throws UserSysException ;

	/**
	 * 스텝정보  리스트 취득
	 * @param ProfileSettingInfo 검색데이타
	 * @return List 일반정보리스트
	 */
    public List selectStaffList(StaffSearchInfo Info) throws UserSysException ;
    
    /**
	 * Staff Id 갯수 취득
	 * @param ProfileSettingInfo 검색데이타
	 * @return List 일반정보리스트
	 */
    public int selectStaffIdCnt(StaffInfo info) throws UserSysException ;
    
    /**
     * 스텝정보  등록처리
     * @param ProfileSettingInfo 검색데이타
     * @return List 일반정보리스트
     */
    public int insertStaff(StaffInfo info) throws UserSysException ;
    
    /**
	 * 스텝 상세 정보
	 * @param staffId 검색아이디
	 * @return User 회원사정보 상세보기
	 */
    public StaffInfo selectStaffDetail(StaffInfo info) throws UserSysException;
    
    /**
	 * 스텝정보 수정처리
     * @param User 입력데이타
	 * @return ProcessResult 결과데이터
	 */
    public int updateStaff(StaffInfo info) throws UserSysException;

    // Staff 추가시 아이디 중복체크
    public String selectDuplicateStaffIdCnt(String userId) throws UserSysException;	
}
