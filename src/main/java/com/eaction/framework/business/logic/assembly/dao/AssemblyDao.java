/*****************************************************************************
 * 프로그램명  : AssemblyDao.java
 * 설     명  : Assembly 처리를 위한 관리 DAO
 * 참고  사항  : 자동Mapping 처리로 xml에 정의한 대로 구현체 클래스는 자동생성 된다
 *          xml의 namespace에 구현체 클래스 생성을 위한 본 인터페이스의 풀패키지와 인터페이스 명칭을 기술한다
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01   왕윤아    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.assembly.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eaction.framework.business.logic.assembly.model.AssemblyFileInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyItemInfo;
import com.eaction.framework.business.logic.assembly.model.GtcXmlVO;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;



/**
 * Assembly 관리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface AssemblyDao {
	
	/**
	 * Assembly 리스트 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyListCnt(AssemblyInfo info);
	
	/**
	 * Assembly 리스트 조회 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AssemblyInfo> selectAssemblyList(AssemblyInfo info);
	
	/**
	 * Assembly 아이템 리스트 갯수 조회 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyItemListCnt(AssemblyInfo info);
	
	/**
	 * Assembly 아이템 리스트 조회 
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AssemblyInfo> selectAssemblyItemList (AssemblyInfo info);
	
	/**
	 * Assembly 그룹 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteAssembly(AssemblyInfo info);
	
	/**
	 * Assembly 아이템 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteAssemblyItem(AssemblyInfo info);
	
	/**
	 * Assembly 아이템 Location 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteAssemblyItemLoc(AssemblyInfo info);
	
	/**
	 * Assembly 등록
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssembly(AssemblyInfo info);
	
	/**
	 * Assembly 수정
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateAssembly(AssemblyInfo info);
	
	 /**
	 * 카트목록 조회
	 * @param AssemblyInfo 데이타
	 * @return List<AssemblyInfo> Cart List
	 */
    public List<AssemblyInfo> selectAssemCartList(AssemblyInfo info) throws UserSysException;    
    
    /**
	 * Assembly 아이템 파일 정보 취득
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public AssemblyInfo selectAssemblyItemFile(AssemblyInfo info) throws UserSysException;
	
	/**
	 * 빌드 어셈블리 파일 초기화
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateAssemblyFileInit(AssemblyInfo info) throws UserSysException ;	
	/**
	 * 장바구니 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertCart(AssemblyInfo info) throws UserSysException ;
    /**
	 * 장바구니 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertCartItem(AssemblyInfo info) throws UserSysException ;
	/**
	 * 장바구니 아이템 수정 - 수량 업데이트
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateCartItemQty(AssemblyInfo info) throws UserSysException ;
	/**
	 * 장바구니  수정 - 총 가격 업데이트
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateCart(BasketInfo info) throws UserSysException ;
	
	/**
	 * 장바구니 중복 아이템 체크 
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectDuplicatedItemCnt(AssemblyInfo info) throws UserSysException ;
	/**
	 * 장바구니 아이템리스트 정보 취득 -> 장바구니 총 가격 수정을 위해
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectCartItemList(AssemblyInfo info) throws UserSysException ;
	
	
	/* ###################################################################### */
	
	/**
	 * 자재번호에 해당하는 GTC 계층구조 목록을 조회한다.
	 * 
	 * @param matnr
	 * @return
	 * @throws Exception
	 */
	List<AssemblyInfo> getGtcHierarchyList(String matnr) throws Exception;	
	
	/**
	 * 자재번호에 해당하는 PLM 계층구조 목록을 조회한다.
	 * 
	 * @param matnr
	 * @return
	 * @throws Exception
	 */
	List<AssemblyInfo> getPlmHierarchyList(String matnr) throws Exception;
	
	/**
	 * 자재번호에 대한 Assembly정보 가져오기.
	 * 
	 * @param matnr
	 * @return
	 * @throws Exception
	 */
	List<AssemblyInfo> getAssemblyInfo(String matnr) throws Exception;
		
	/**
	 * 체결된 Adaptive 자재번호 : CCMS > CCWS
	 * @param ccws
	 * @return
	 * @throws Exception
	 */
	List<AssemblyItemInfo> getAssyAdaptive(Map<String, String> map) throws Exception;
	
	/**
	 * 체결된 Adaptive 아이템 그룹번호 : CCMS > CCWS
	 * @param ccws
	 * @return
	 * @throws Exception
	 */
	List<AssemblyItemInfo> getAssyAdaptiveIgGroup(Map<String, String> map) throws Exception;
	
	/**
	 * 체결된 Item 자재번호 : IIC > IIC, CCMS
	 * @param ccws
	 * @return
	 * @throws Exception
	 */	
	List<AssemblyItemInfo> getAssyTool(Map<String, String> map) throws Exception;
	
	/**
	 * 체결된 아이템 그룹번호 : IIC > IIC, CCMS
	 * @param ccws
	 * @return
	 * @throws Exception
	 */	
	List<AssemblyItemInfo> getAssyToolIgGroup(Map<String, String> map) throws Exception;	
	
	/**
	 * 체결될 Insert 자재번호 : IIC
	 * 
	 * @param iic
	 * @return
	 * @throws Exception
	 */
	List<AssemblyItemInfo> getAssyInsert(String iic) throws Exception;	

	/**
	 * 체결될 Insert 아이템 그룹번호 : IIC
	 * 
	 * @param iic
	 * @return
	 * @throws Exception
	 */
	List<AssemblyItemInfo> getAssyInsertIgGroup(String iic) throws Exception;	
	
	/**
	 * 자재번호로 detail stp 파일 전체 경로 반환.
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	String getDetailStpPath(String matnr) throws Exception;	
	
	/**
	 * 자재번호로 stp 파일 전체 경로 반환.
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	AssemblyItemInfo getSTPFiles(String matnr) throws Exception;	
	
	/**
	 * 컨텐츠별 파일 관리 반환.
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	String getAssyFilePath(AssemblyFileInfo info) throws Exception ;
	
	/**
	 * 컨텐츠별 파일 관리 갯수 반환.
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	int getAssyFileCnt(AssemblyFileInfo info) throws Exception ;
	
	/**
	 * 컨텐츠별 파일 관리 데이터 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteAssyFile(AssemblyFileInfo info) throws Exception ;
	
	/**
	 * 컨텐츠별 파일 관리 데이터 추가
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssyFile(AssemblyFileInfo info) throws Exception ;
		
	/**
	 * P21 파일 데이터 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteP21Data(GtcXmlVO info) throws Exception ;
	
	/**
	 * P21 파일 데이터 반환.
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	public List<String> selectP21Data() throws Exception ;
	
	/**
	 * P21 파일 데이터 추가
	 * @param matnr 자재번호
	 * @return
	 * @throws UserSysException
	 */
	public int insertP21Data(String matnr) throws Exception ;

	/**
	 * 각 자재번호로 GTC 패키지 정보 취득
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public GtcXmlVO selectGtcXmlData(GtcXmlVO info) throws Exception ;
	
	/**
	 * 제너릭 아이디로 버전 취득
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public String selectGtcVer(String gtc_gen_id) throws Exception ;
	
	/**
	 * 단품, 어셈블리용.
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectGtcPkgInfo(String gtc_gen_id) throws Exception ;
	
	/**
	 * 언어코드 취득
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public String selectLangCode(String lang_pack) throws Exception ;
	
	/**
	 * 어셈블리 마스터 취득
	 * @param matnr 자재번호.
	 * @return
	 * @throws Exception
	 */
	public AssemblyInfo selectAssemMstInfo(AssemblyInfo info) throws Exception;	
	
	/**
	 * 아이템 그룹 취득
	 * @param ccws
	 * @return
	 * @throws Exception
	 */	
	List<AssemblyItemInfo> getAssyIgGroup(AssemblyItemInfo info) throws Exception;
	
	/**
	 * 아이템 그룹 취득
	 * @param ccws
	 * @return
	 * @throws Exception
	 */	
	List<AssemblyItemInfo> selectSaCd(AssemblyItemInfo info) throws Exception;
	
	/**
	 * Assembly 생성 상태 변경
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateAssemblyState(AssemblyInfo info);
	
	/**
	 * Assembly 생성중 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyCreateIngCnt(AssemblyInfo info);
	
	/**
	 * Assembly 생성후 Location등록
	 * @param AssemblyItemInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssemblyLocation(AssemblyItemInfo info);
		
	/**
	 * Assembly Location 삭제
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteAssemblyLocation(AssemblyInfo info);
	
	/**
	 * Assembly location 갯수 조회
	 * @param AssemblyInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectAssemblyLocationCnt(AssemblyInfo info);
	
	/**
	 * Assembly Build Comp Location 조회
	 * @param AssemblyInfo
	 * @return List<AssemblyItemInfo>
	 * @throws Exception
	 */	
	public List<AssemblyItemInfo> selectAssemblyLocation(AssemblyInfo info) throws Exception;
}
