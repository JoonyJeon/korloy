/*****************************************************************************
 * 프로그램명  : AssemblyService.java
 * 설     명  : Assembly 서비스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01   왕윤아    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.assembly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.common.exception.UserSysException;


/**
 * Assembly 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface AssemblyService {
	
	/**
	 * Assembly 리스트+개수
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, Object> selectAssemblyListInfo(AssemblyInfo info) throws UserSysException;
	
	
	/**
	 * Assembly 개수
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyListCnt(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 개별 정보
	 * @param basketInfo
	 * @return Map
	 * @throws UserSysException
	 */
//	public Map<String, Object> selectAssemblyInfo(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 리스트
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AssemblyInfo> selectAssemblyInfoList(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 그룹 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean deleteAssembly(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 아이템 삭제 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean deleteAssemblyItem(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 아이템 Location 삭제 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean deleteAssemblyItemLoc(AssemblyInfo info) throws UserSysException;
	
	/**
	 * Assembly 등록
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertAssembly(AssemblyInfo info) throws UserSysException;

	/**
	 * Assembly 수정
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateAssembly(AssemblyInfo info) throws UserSysException;
	
	/**
	 * 장바구니 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AssemblyInfo> Cart List
	 */
    public List<AssemblyInfo> selectAssemCartList(AssemblyInfo info) throws UserSysException;  
    
	
	/**
	 * Assembly 아이템 리스트 조회
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, List<AssemblyInfo>> selectAssemblyItemListInfo(AssemblyInfo info) throws Exception;
	
	/**
	 * 장바구니 아이템 목록 전체 GTC 파일리스트 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public List<AssemblyInfo> selectAssemblyItemFileList(AssemblyInfo info) throws UserSysException;
	
	/**
	 * 아이템의 Assembly Type 조회 (ADP, TL, INS 중 확인)
	 * @return
	 * @throws Exception 
	 */
	public String getAssemType(String matnr) throws Exception;
	
	/**
	 * 장바구니 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertCartItem(AssemblyInfo info) throws UserSysException;


	
	/* ############################################################################# */
	
	/**
	 * Assembly 정보를 조회한다.
	 * 
	 * @param escalateVO - 조회할 정보가 담긴 VO
	 * @return 목록의 그리드셋
	 * @throws Exception
	 */
	public AssemblyInfo getAssembly(String matnr) throws Exception;

	/**
	 * Assembly 생성
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public void createAssembly(HttpServletRequest request, AssemblyInfo assemblyInfo) throws UserSysException;
	
	/**
	 * Assembly Type 취득
	 * @param matnr
	 * @return
	 */
	public String getAssyType(String matnr);
	
	/**
	 * 어셈블리 마스터 취득
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	public AssemblyInfo selectAssemMstInfo(AssemblyInfo info) throws Exception;	
	
	/**
	 * Assembly 생성 상태 변경
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateAssemblyState(AssemblyInfo info);
	
	/**
	 * Assembly 생성중 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyCreateIngCnt(AssemblyInfo info);
	
	/**
	 * Assembly location 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyLocationCnt(AssemblyInfo info);
	
	/**
	 *  Assembly Build Comp Location 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 * @throws Exception 
	 */
	public List<AssemblyItemInfo> selectAssemblyLocation(AssemblyInfo info) throws UserSysException, Exception;
	
		
}
