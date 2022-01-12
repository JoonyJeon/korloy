/*****************************************************************************
 * 프로그램명  : AppDao.java
 * 설     명  : Main/Sub/Item 처리를 위한 관리 DAO
 * 참고  사항  : 자동Mapping 처리로 xml에 정의한 대로 구현체 클래스는 자동생성 된다
 *          xml의 namespace에 구현체 클래스 생성을 위한 본 인터페이스의 풀패키지와 인터페이스 명칭을 기술한다
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.06   정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.app.dao;

import java.util.List;
import java.util.Map;

import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;


/**
 * Main/Sub/Item 처리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface AppDao {

	/**
	 * ItemGroup 상세 정보
	 * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 */
    public AppInfo selectItemGroupInfo(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * More Info 정보
	 * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 */
    public AppInfo selectMoreInfo(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * Item Group 대표 이미지, ISO도면, Non-ISO도면 정보
	 * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 */
    public AppInfo selectIgImageInfo(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * Item Group이 속한 Sub App 이미지 취득
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Property Symbol 목록
	 */
    public List<AppInfo> selectSubImageList(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * Main App 테일러드 정보 조회
	 * @param AppInfo 데이타
	 * @return List<AppInfo> Main App 테일러드 정보 목록
	 */
    public List<AppInfo> selectTailInfo(AppInfo info) throws UserSysException;
    
    /**
	 * Item 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<Map<String, Object>> Item 목록
	 */
    public List<Map<String, Object>> selectItemList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Insert Item 갯수 조회
	 * @param AppSearchInfo 데이타
	 * @return String 아이템 갯수
	 */
    public String selectItemInsertsCnt(AppSearchInfo searchInfo) throws UserSysException;    
    
    /**
	 * Item 상세 정보
	 * @param AppSearchInfo 데이타
	 * @return Map<String, Object> Item정보
	 */
    public Map<String, Object> selectItemInfo(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * Item 상세 정보 프로시저 조회
	 * @param AppSearchInfo 데이타
	 * @return Map<String, Object> Item정보
	 */
    public Map<String, Object> selectSPItemInfo(AppSearchInfo searchInfo) throws UserSysException;
    
    /**
	 * Item이 담긴 마지막 카트번호 조회
	 * @param AppSearchInfo 데이타
	 * @return String Cart 번호
	 */
    public String selectItemLastCartNo(AppSearchInfo searchInfo) throws UserSysException;    
    
    /**
	 * 선택한 Item Property Symbol 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Property Symbol 목록
	 */
    public List<AppInfo> selectItemPropSymbolList(AppSearchInfo info) throws UserSysException;

    /**
	 * Main Application 목록 조회
	 * @return List<AppInfo> Main Application 목록
	 */
    public List<AppInfo> selectMainApplicationList(AppInfo info) throws UserSysException ;
    
    /**
	 * Sub Application 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Main Application 목록
	 */
    public List<AppInfo> selectSubApplicationList(AppInfo info) throws UserSysException ;

    /**
	 * Sub Application 목록 개수 조회
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
    public int selectSubApplicationListCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * Sub Application의 전체 아이템 개수
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
    public int selectSubApplicationOfItemCnt(AppInfo info) throws UserSysException ;    
    
    /**
	 * Related Inserts 목록 조회
	 * @return List<Map<String, Object>> Related Inserts 목록
	 */
    public List<Map<String, Object>> selectRelatedInsertsList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Related Holders 목록 조회
	 * @return List<Map<String, Object>> Related Holders 목록
	 */
    public List<Map<String, Object>> selectRelatedHoldersList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Recommended Cutting Speeds 목록 갯수 조회
	 * @param AppInfo 검색조건
	 * @return int Recommended Cutting Speeds 목록 개수
	 */
    public int selectCuttingSpeedListCnt(AppSearchInfo searchInfo) throws UserSysException ;    
    
    /**
	 * Recommended Cutting Speeds 목록 조회
	 * @return List<AppInfo> Cutting Speeds 목록
	 */
    public List<AppInfo> selectCuttingSpeedList(AppSearchInfo searchInfo) throws UserSysException ;

    /**
	 * Spare Parts 목록 갯수 조회
	 * @return int Spare Parts 목록 개수
	 */
    public int selectSparePartsListCnt(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Spare Parts 목록 조회
	 * @return List<AppInfo> Spare Parts 목록
	 */
    public List<AppInfo> selectSparePartsList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Item Group List 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Item Group List
	 */
    public List<AppInfo> selectItemGroupList(AppInfo info) throws UserSysException ;
    
    /**
	 * Item Group List 목록 개수 조회
	 * @param AppInfo 검색조건
	 * @return int Item Group List 목록 개수
	 */
    public int selectItemGroupListCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * Item Group Filter List 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Item Group Filter List
	 */
    public List<AppInfo> selectItemGroupFilterList(AppSearchInfo info) throws UserSysException ;
    
    /**
	 * Item Group List 필터에 Combo Code/Value 조회 
	 * @param AppInfo 검색조건
	 * @return List<CodeInfo> Item Group List 필터에 Combo Code/Value 목록
	 */
    public List<CodeInfo> selectItemPropComboList(AppInfo info) throws UserSysException ;
    
    /**
	 * Item 전체개수 조회
	 * @return Item 전체개수
	 */
    public int selectItemTotalCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * 카트목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Cart List
	 */
    public List<AppInfo> selectCartList(AppSearchInfo searchInfo) throws UserSysException;    
    
    /**
	 * 장바구니 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertCart(AppInfo info) throws UserSysException ;
	
    /**
	 * 장바구니 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertCartItem(AppInfo info) throws UserSysException ;
	/**
	 * 장바구니 아이템 수정 - 수량 업데이트
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateCartItemQty(AppInfo info) throws UserSysException ;
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
	public int selectDuplicatedItemCnt(AppInfo info) throws UserSysException ;
	/**
	 * 장바구니 아이템리스트 정보 취득 -> 장바구니 총 가격 수정을 위해
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectCartItemList(AppInfo info) throws UserSysException ;
	
	/**
	 * Assembly 그룹 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Assembly List
	 */
    public List<AppInfo> selectAssemblyList(AppSearchInfo searchInfo) throws UserSysException;    
    
    /**
	 * Assembly 그룹 아이템 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Assembly List
	 */
    public List<AppInfo> selectAssemblyItemList(AppInfo info) throws UserSysException;    
    
    /**
	 * Assembly 그룹 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssembly(AppInfo info) throws UserSysException ;
	
    /**
	 * Assembly 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssemblyItem(AppInfo info) throws UserSysException ;

	/**
	 * 빌드 어셈블리 파일 초기화
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int updateAssemblyFile(AppInfo info) throws UserSysException ;	
	
	/**
	 * Item Group Code로 Sub Applicatioin Code 조회
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public String selectIgCodeToSaCode(AppInfo info) throws UserSysException ;
	
	/**
	 * Sub Applicatioin Code로 Main Applicatioin Code 조회
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public String selectSaCodeToMaCode(AppInfo info) throws UserSysException ;
	
	/**
	 * ISO metric/inch 계산식 단위 및 값 조회
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectIsoCalList(AppSearchInfo info) throws UserSysException ;
	
	/**
	 * 아이템 그룹의 프로퍼티 그룹코드 조회
	 * @param ig_cd
	 * @return 프로퍼티 그룹코드
	 * @throws UserSysException
	 */
	public String selectIGPropGrpCd(String ig_cd) throws UserSysException ;
}
