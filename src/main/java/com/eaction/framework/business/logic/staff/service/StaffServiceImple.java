/*****************************************************************************
 * 프로그램명  : StaffServiceImpl.java
 * 설     명  : 스텝정보관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.staff.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.logic.staff.dao.StaffDao;
import com.eaction.framework.business.logic.staff.model.StaffInfo;
import com.eaction.framework.business.logic.staff.model.StaffSearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.util.PagingUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 스텝정보관리 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class StaffServiceImple implements StaffService {
	
	@Resource
	private StaffDao staffDao;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * 스텝정보 리스트 카운트 취득
	 * @param Info 검색정보빈
	 * @return int 스텝정보리스트갯수
	 */
	@Override
	public int selectStaffListCnt(StaffSearchInfo searchInfo) throws UserSysException {
		int nTotal = staffDao.selectStaffListCnt(searchInfo);
		return nTotal;
	}
	
	/**
	 * 스텝정보 리스트 취득
	 * @param Info 검색정보빈
	 * @return PagingUtil 스텝정보리스트
	 */
	@Override
	public PagingUtil selectStaffList(StaffSearchInfo searchInfo) throws UserSysException {
		
		if(searchInfo.getJqFilters()!= null && !searchInfo.getJqFilters().isEmpty()){
			JSONArray arr = (JSONArray) searchInfo.getJqFilters().get("rules");			
			for(Object obj :arr){
				JSONObject jo = (JSONObject) obj;
				if(jo.get("field").equals("empnm")){
					searchInfo.setSearch_name((String) jo.get("data"));
				}
				if(jo.get("field").equals("emp_id")){
					searchInfo.setSearch_id((String) jo.get("data"));
				}
				if(jo.get("field").equals("user_group_id")){
					searchInfo.setSearch_auth((String) jo.get("data"));
				}
				if(jo.get("field").equals("status")){
					searchInfo.setSearch_status((String) jo.get("data"));
				}
			}
			
		}
		
		int nTotal = staffDao.selectStaffListCnt(searchInfo);
		
		List result = staffDao.selectStaffList(searchInfo);

		PagingUtil pageTable = new PagingUtil(nTotal, result);
		
		return pageTable;
	}

	/**
	  * 스텝정보 등록처리
	  * @param info 스텝정보
	  * @return ProcessResult 결과데이터
	  */
	@Override
	@Transactional
	public boolean insertStaff(StaffInfo info) throws UserSysException {
		boolean result = true;

	   	//ID 중복 체크(테이블 내 동일 ID는 입력 불가, Uniq제약 걸려있음)
		int nCnt = staffDao.selectStaffIdCnt(info);
		if(nCnt > 0) {
			result = false;
		} else {
			//중복 체크 통과한 경우에만 등록
			staffDao.insertStaff(info);
		}		
		
		return result;
	}
	
	/**
	 * 스텝정보 상세 정보
	 * @param staffId 검색스테아이디
	 * @return User 스텝정보 상세보기
	 */
	@Override
	public StaffInfo selectStaffDetail(StaffInfo info) throws UserSysException {
		return staffDao.selectStaffDetail(info);
	}

	/*
	 * 스텝정보 수정처리
	 * @param staffInfo 수정할 스텝정보
	 */
	@Override
	@Transactional
	public boolean updateStaff(StaffInfo info) throws UserSysException {
		
		staffDao.updateStaff(info);

		return true;
	}

	/**
	  * Staff 추가시 아이디 중복체크
	  * @param userId 유저 아이디
	  * @return boolean (true:유저가 존재하지 않을때,false:유저가 존재할때)
     */
	@Override
	public String selectDuplicateStaffIdCnt(String emp_id) throws UserSysException {
		// 유저 정보 취득
        String checkId = staffDao.selectDuplicateStaffIdCnt(emp_id);

        if ("1".equals(checkId)) {
        	checkId = "NG";
        } else {
        	checkId = "OK";
        }

        return checkId;
	}	
	
}
