/*****************************************************************************
 * 프로그램명  : StaffService.java
 * 설     명  : 스텝정보관리 business-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.staff.service;

import com.eaction.framework.business.logic.staff.model.StaffInfo;
import com.eaction.framework.business.logic.staff.model.StaffSearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.util.PagingUtil;

/**
 * 스텝정보관리 서비스
 * @author eaction
 *@version 1.0
 */
public interface StaffService {
	
	/**
	 * 회사정보 리스트 카운트 취득
	 * @param Info 검색정보빈
	 * @return PagingUtil 일반정보리스트
	 */
	public int selectStaffListCnt(StaffSearchInfo searchInfo) throws UserSysException;

	/**
	 * 스텝정보 리스트 취득
	 * @param Info 검색정보빈
	 * @return PagingUtil 일반정보관리 리스트
	 */
	public PagingUtil selectStaffList(StaffSearchInfo searchInfo) throws UserSysException;

    /**
	 * 스텝정보 등록처리
	 * @param staffInfo 등록할 스텝정보
	 */
    public boolean insertStaff(StaffInfo info) throws UserSysException ;

    /**
	 * 스텝상세 정보
     * @param User 데이타
	 * @return User 스텝정보 상세보기
	 */
    public StaffInfo selectStaffDetail(StaffInfo info) throws UserSysException;
    
    /**
   	 * 스텝정보 수정처리
   	 * @param staffInfo 수정할 스텝정보
   	 */
    public boolean updateStaff(StaffInfo Info) throws UserSysException ;

	 /**
     * 유저아이디 존재 체크 
     * @param userId ȸ��유저 아이디
     * @return boolean (true:유저가 존재하지 않을때,false:유저가 존재할때)
	 */
    public String selectDuplicateStaffIdCnt(String userId) throws UserSysException;    
}
