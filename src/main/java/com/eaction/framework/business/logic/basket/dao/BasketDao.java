/*****************************************************************************
 * 프로그램명  : BasketDao.java
 * 설     명  : 장바구니 관리 관리 DAO
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.28   YJI    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basket.dao;

import java.util.List;

import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;

/**
 * 장바구니관리  DAO
 * @author  eaction
 * @version 1.0
 */
public interface BasketDao {
	
	/**
	 * 장바구니 리스트 갯수 조회
	 * @param basketInfo
	 * @return
	 */
	public int selectBasketListCnt(BasketInfo basketInfo);
	
	/**
	 * 장바구니 리스트 조회 
	 * @param basketInfo
	 * @return
	 */
	public List<BasketInfo> selectBasketList(BasketInfo basketInfo);
	
	/**
	 * 장바구니 등록
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public String insertBasket(BasketInfo basketInfo);

	/**
	 * 장바구니 수정
	 * @param basketInfo
	 * @return
	 */
	public int updateBasket(BasketInfo basketInfo);

	/**
	 * 장바구니 삭제
	 * @param basketInfo
	 * @return
	 */
	public int deleteBasket(BasketInfo basketInfo);
// ↙  장바구니 아이템 

	/**
	 * 장바구니 아이템 리스트 갯수 조회 
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public int selectBasketItemListCnt(BasketInfo basketInfo);
	
	/**
	 * 장바구니 아이템 리스트 조회 
	 * @param basketInfo
	 * @return
	 */
	public List<BasketInfo> selectBasketItemList (BasketInfo basketInfo);

	/**
	 * 장바구니 아이템 추가
	 * @param basketInfo
	 * @return
	 */
	public int insertBasketItem(BasketInfo basketInfo) ;
	/**
	 * 장바구니 아이템 수정 (수량)
	 * @param basketInfo
	 * @return
	 */
	public int updateBasketItem(BasketInfo basketInfo) ;

	/**
	 * 장바구니 아이템 삭제
	 * @param basketInfo
	 * @return
	 */
	public int deleteBasketItem(BasketInfo basketInfo);
// ↙  장바구니 아이템 ---------------------------- 
	/**
	 * 장바구니 그룹 파일 리스트 취득
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public List<AppInfo> selectItemPropSymbolList(AppSearchInfo searchInfo);
	
	/**
	 * 장바구니 아이템 파일 정보 취득
	 * @param basketInfo
	 * @return
	 */
	public BasketInfo selectBasketItemFile(BasketInfo info);
	
	/*
	 * 아이템 그룹 코드 취득
	 * 
	 * */
	public String selectIgCd(AppSearchInfo searchInfo);
	/**
	 * 주문내역 있는지 확인
	 * @param basketInfo
	 * @return
	 */
	public int hasOrderHistoryCnt(BasketInfo basketInfo) ;
	
	/**
	 * 주문내역 삭제
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	public int deleteOrder(BasketInfo basketInfo);
	
	/*
	 * 통화 취득 
	 * @param basketInfo 
	 * @return String
	 * 
	 * */
	public String selectCurrencyName(BasketInfo basketInfo);
}
