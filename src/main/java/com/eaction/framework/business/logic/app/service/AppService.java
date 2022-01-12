/*****************************************************************************
 * 프로그램명  : AppService.java
 * 설     명  : Main/Sub/Item 정보
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.22   WYA    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;

/**
 * Main/Sub/Item 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface AppService {
	

    /**
	 * Main Application 목록 조회
	 * @return List<AppInfo> Main Application 목록
	 */
    public List<AppInfo> selectMainApplicationList(AppInfo info) throws UserSysException ;
    
    /**
	 * Sub Application 목록 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Sub Application 목록
	 */
    public List<AppInfo> selectSubApplicationList(AppInfo info) throws UserSysException ;
	
    /**
	 * Sub Application 목록 개수 조회
	 * @param AppInfo 검색조건
	 * @return Sub Application 목록 개수 
	 */
    public int selectSubApplicationListCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * Item Group List 조회
	 * @param AppInfo 검색조건
	 * @return List<AppInfo> Item Group List
	 */
    public List<AppInfo> selectItemGroupList(AppInfo info) throws UserSysException ;
	
    /**
	 * Item Group List 개수 조회
	 * @param AppInfo 검색조건
	 * @return Item Group List 개수 
	 */
    public int selectItemGroupListCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * ItemGroup 상세 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
    public AppInfo selectItemGroupInfo(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * More Info 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
    public AppInfo selectMoreInfo(AppSearchInfo searchInfo) throws UserSysException ;
        
    /**
	 * Item Group 대표 이미지, ISO도면, Non-ISO도면 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
    public AppInfo selectIgImageInfo(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Item Group이 속한 Sub App 이미지 취득
     * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Sub App 이미지 목록
	 * @throws UserSysException 
	 */
    public List<AppInfo> selectSubImageList(AppSearchInfo info) throws UserSysException;
    
    /**
	 * Item 목록 조회
     * @param AppSearchInfo 데이타
	 * @return List<Map<String, Object>> Item 목록
	 * @throws UserSysException 
	 */
    public List<Map<String, Object>> selectItemList(AppSearchInfo info) throws UserSysException;

    /**
	 * Sub Application의 전체 아이템 개수
	 * @param AppInfo 검색조건
	 * @return int Sub Application 목록 개수
	 */
    public int selectSubApplicationOfItemCnt(AppInfo info) throws UserSysException ;    
    
    /**
	 * Insert Item 갯수 조회
     * @param AppSearchInfo 데이타
	 * @return String 아이템 갯수
	 * @throws UserSysException 
	 */
    public String selectItemInsertsCnt(AppSearchInfo searchInfo) throws UserSysException ;    
    
    /**
	 * Item 상세 정보
     * @param AppSearchInfo 데이타
	 * @return AppInfo 상세정보
	 * @throws UserSysException 
	 */
    public Map<String, Object> selectItemInfo(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Item이 담긴 마지막 카트번호 조회
     * @param AppSearchInfo 데이타
	 * @return String Cart 번호
	 * @throws UserSysException 
	 */
    public String selectItemLastCartNo(AppSearchInfo searchInfo) throws UserSysException ;    
    
    /**
   	 * 선택한 Item Property Symbol 목록 조회
   	 * @param AppInfo 검색조건
   	 * @return List<AppInfo> Property Symbol 목록
   	 */
       public List<AppInfo> selectItemPropSymbolList(AppSearchInfo searchInfo) throws UserSysException ;
       
    /**
	 * Related Inserts 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<Map<String, Object>> Related Inserts 목록
	 */
    public List<Map<String, Object>> selectRelatedInsertsList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Related Holders 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<Map<String, Object>> Related Holders 목록
	 */
    public List<Map<String, Object>> selectRelatedHoldersList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Recommended Cutting Speeds 목록 갯수 조회
	 * @param AppSearchInfo 검색조건
	 * @return int Cutting Speeds 목록 갯수
	 */
    public int selectCuttingSpeedListCnt(AppSearchInfo searchInfo) throws UserSysException ;

    /**
	 * Recommended Cutting Speeds 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<AppInfo> Cutting Speeds 목록
	 */
    public List<AppInfo> selectCuttingSpeedList(AppSearchInfo searchInfo) throws UserSysException ;

    /**
	 * Spare PartsList 목록 갯수 조회
	 * @param AppSearchInfo 검색조건
	 * @return int Spare Parts 목록 갯수
	 */
    public int selectSparePartsListCnt(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Spare PartsList 목록 조회
	 * @param AppSearchInfo 검색조건
	 * @return List<AppInfo> Spare Parts 목록
	 */
    public List<AppInfo> selectSparePartsList(AppSearchInfo searchInfo) throws UserSysException ;
    
    /**
	 * Item 전체개수 조회
	 * @return Item 전체개수
	 */
    public int selectItemTotalCnt(AppInfo info) throws UserSysException ;
    
    /**
	 * 필터에 Combo Code/Value 조회
	 * @return List<CodeInfo> Combo Code/Value
	 */
    public List<CodeInfo> selectItemPropComboList(AppInfo info) throws UserSysException ;
    
    /**
	 * 장바구니 목록 조회
	 * @param AppSearchInfo 데이타
	 * @return List<AppInfo> Cart List
	 */
    public List<AppInfo> selectCartList(AppSearchInfo searchInfo) throws UserSysException;  
    
    /**
	 * 장바구니 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertCartItem(AppInfo info) throws UserSysException;
    
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
    //public List<AppInfo> selectAssemblyItemList(AppSearchInfo searchInfo) throws UserSysException;      
    
    /**
	 * Assembly 아이템 추가
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public int insertAssemblyItem(AppInfo info) throws Exception;

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
	 * 아이템 그룹 필터목록
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectItemGroupFilterList(AppSearchInfo info) throws UserSysException ;
	
	/**
	 * ISO metric/inch 계산식 단위 및 값 목록
	 * @param AppInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectIsoCalList(AppSearchInfo info) throws UserSysException ;
	
	/**
	 * Main App 테일러드 정보 조회
	 * @param AppInfo 데이타
	 * @return List<AppInfo> Main App 테일러드 정보 목록
	 */
	public List<AppInfo> selectTailInfo(AppInfo info) throws UserSysException;
	
}
