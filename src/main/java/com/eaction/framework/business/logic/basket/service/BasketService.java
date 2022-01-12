/*****************************************************************************
 * 프로그램명  : BasketService.java
 * 설     명  : 장바구니 관리 business-layer interface.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.29   YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basket.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 장바구니 관리 서비스
 * @author  eaction
 * @version 1.0
 */
public interface BasketService {

	/**
	 * 장바구니 리스트+개수
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, Object> selectBasketListInfo(BasketInfo paramBasketInfo) throws UserSysException;
	/**
	 * 장바구니 개수
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectBasketListCnt(BasketInfo paramBasketInfo) throws UserSysException;
	/**
	 * 장바구니 리스트
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectBasketList(BasketInfo paramBasketInfo) throws UserSysException;
	
	/**
	 * 장바구니 등록
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertBasket(BasketInfo paramBasketInfo) throws UserSysException;

	/**
	 * 장바구니 수정
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean updateBasket(BasketInfo paramBasketInfo) throws UserSysException;

	/**
	 * 장바구니 삭제
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean deleteBasket(BasketInfo paramBasketInfo) throws UserSysException;

// ↙  장바구니 아이템
	/**
	 * 장바구니 아이템 리스트 조회
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	public Map<String, Object> selectBasketItemList(BasketInfo paramBasketInfo) throws UserSysException;
	/**
	 * 장바구니 아이템 리스트 조회
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectBasketItemListInfo(BasketInfo paramBasketInfo) throws UserSysException;
	
	/**
	 * 장바구니 아이템 추가
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean insertBasketItem(HttpServletRequest request, BasketInfo paramBasketInfo) throws UserSysException;
	
	/**
	 * 장바구니 아이템 삭제 
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	public boolean deleteBasketItem(BasketInfo paramBasketInfo) throws UserSysException;
// ↙  장바구니 아이템 ---------------------------- 

// 장바구니 파일
	/**
	 * 장바구니 아이템 목록 전체 GTC 파일리스트 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public List<BasketInfo> selectBasketItemFileList(BasketInfo info) throws UserSysException;
	
	/**
	 * 장바구니 아이템 하나에 해당하는 GTC 파일 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public BasketInfo selectBasektItemFile(BasketInfo info) throws UserSysException;
	
	/**
	 * 장바구니 아이템 수량 수정
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public boolean updateBasketItemInfo(BasketInfo info) throws UserSysException;
	
	/**
	 * 아이템 팝업 심볼 리스트
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public List<AppInfo> selectItemPropSymbolList(AppSearchInfo searchInfo) throws UserSysException;
	
	/**
	 * 아이템 그룹 코드 취득
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public String selectIgCd(AppSearchInfo searchInfo) throws UserSysException;
	
	/**
	 * 아이템 그룹 코드 취득
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	public String selectCurrencyName(BasketInfo info) throws UserSysException;
	
}

