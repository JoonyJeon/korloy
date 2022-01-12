/*****************************************************************************
 * 프로그램명  : BasketServiceImpl.java
 * 설     명  : 장바구니 관리  persistence-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.29   YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basket.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.basket.dao.BasketDao;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 장바구니 관리 서비스
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class BasketServiceImpl implements BasketService {
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( BasketServiceImpl.class );
	
	/** 로그인 처리 DAO */
	@Resource
	private BasketDao basketDao;

	/**
	 * 장바구니 리스트+개수
	 * @param basketInfo
	 * @return Map
	 * @throws UserSysException
	 */
	@Override
	public Map<String, Object> selectBasketListInfo(BasketInfo paramBasketInfo) throws UserSysException {
		logger.debug("selectBasketList--{}", ConstKey.START_LOG);
		Map<String, Object> result = new HashMap<>();
		
		int nTotal=0;
		List<BasketInfo> list = new ArrayList<>();
		
		//더보기 버튼 관련 공통 처리
		paramBasketInfo.setIsPageYn(ConstKey.KEY_Y);
		paramBasketInfo.setPageCount(Integer.parseInt(ConfigMng.getValue(IPropertyKey.NORMAL_PAGE_ROWNUM)));
		try{
			nTotal = basketDao.selectBasketListCnt(paramBasketInfo);
			list = basketDao.selectBasketList(paramBasketInfo);
			
			//장바구니별 아이템리스트 set
			for(BasketInfo basket : list){
				BigDecimal cartTotal = BigDecimal.ZERO;
				basket.setComm_cur(paramBasketInfo.getCur());
				List<BasketInfo> itemList = basketDao.selectBasketItemList(basket);
				//장바구니 아이템별 total 계산 
				for(BasketInfo item :itemList){
					String total = StringUtil.getMultipleQtyAndPrice(item.getQty(), item.getPrice(), item.getCur());
					item.setTotal_price(total);
					BigDecimal ttl = BigDecimal.valueOf(Double.parseDouble(total));
					cartTotal = cartTotal.add(ttl);
				}
				basket.setBasket_detail_list(itemList);
				String finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", cartTotal.toString(), basket.getCur());
				basket.setTotal_price(finalTtlPrice);
			}
		}catch (Exception e){
			logger.debug("ERROR IN SELECT BASKET LIST INFO SERVICE :: "+ e.getMessage());
		}
		result.put("nTotal", nTotal);
		result.put("list", list);
		
		logger.debug("selectBasketList--{}", ConstKey.END_LOG);

    	return result;
	}
	
	/**
	 * 장바구니 개수
	 * @param basketInfo
	 * @return int
	 * @throws UserSysException
	 */
	@Override
	public int selectBasketListCnt(BasketInfo paramBasketInfo) throws UserSysException {
		return basketDao.selectBasketListCnt(paramBasketInfo);
	}
	
	/**
	 * 장바구니 리스트
	 * @param basketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public List<BasketInfo> selectBasketList(BasketInfo paramBasketInfo) throws UserSysException {
		List<BasketInfo> list = basketDao.selectBasketList(paramBasketInfo);
		//장바구니별 아이템리스트 set
		for(BasketInfo itemList : list){
			List<BasketInfo> tmpList = basketDao.selectBasketItemList(itemList);
			itemList.setBasket_detail_list(tmpList);
		}
		return list;
	}
	
	/**
	 * 장바구니 등록
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertBasket(BasketInfo paramBasketInfo) throws UserSysException {
 		boolean bResult = true;
		String result = "";
		int arrLength = 0;
		int iResult = 0;
		if(!"".equals(paramBasketInfo.getAddParam())){
			JSONArray jsonFilter = JSONArray.fromObject(paramBasketInfo.getAddParam());
			arrLength = jsonFilter.size();
			for(int n=0; n<jsonFilter.size(); n++) {
				JSONObject jo = (JSONObject)jsonFilter.get(n);
				String cart_nm = jo.getString("cart_nm");
				String cart_desc = jo.getString("cart_desc");
				BasketInfo insertNewBasket = new BasketInfo();
				insertNewBasket.setCart_nm(cart_nm);
				insertNewBasket.setCart_desc(cart_desc);
				insertNewBasket.setUser_id(paramBasketInfo.getUser_id());
				insertNewBasket.setM_use_yn("Y");
				result = basketDao.insertBasket(insertNewBasket);
				if(result == null){iResult++;}
		    	logger.debug("Basket Insert JSONArray : :" + cart_nm +" : "+ cart_desc);
			}
		}
		
    	if(iResult != arrLength) {
    		bResult = false;
    	}
    	
    	return bResult;
	}


	/**
	 * 장바구니 수정
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateBasket(BasketInfo paramBasketInfo) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		int arrLength = 0;
		if(!"".equals(paramBasketInfo.getEditParam())){
			JSONArray jsonFilter = JSONArray.fromObject(paramBasketInfo.getEditParam());
			arrLength = jsonFilter.size();
			for(int n=0; n<jsonFilter.size(); n++) {
				JSONObject jo = (JSONObject)jsonFilter.get(n);
				String cart_no = jo.getString("cart_no");
				String cart_nm = jo.getString("cart_nm");
				String cart_desc = jo.getString("cart_desc");
				BasketInfo insertNewBasket = new BasketInfo();
				insertNewBasket.setCart_no(cart_no);
				insertNewBasket.setCart_nm(cart_nm);
				insertNewBasket.setCart_desc(cart_desc);
				insertNewBasket.setUser_id(paramBasketInfo.getUser_id());
				iResult += basketDao.updateBasket(insertNewBasket);
				logger.debug("Basket Insert JSONArray : :" + cart_no +" : "+ cart_nm+" : "+cart_desc);
			}
		}
    	if(iResult != arrLength) {
    		bResult = false;
    	}
    	
    	return bResult;
	}

	/**
	 * 장바구니 삭제
	 * @param basketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean deleteBasket(BasketInfo paramBasketInfo) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		if(paramBasketInfo.getDeleteParam() !=null){
			for(String no : paramBasketInfo.getDeleteParam()){
				paramBasketInfo.setCart_no(no);
				//장바구니 아이템이 있는 경우 아이템 먼저 삭제 후 장바구니 삭제해야
				int itemCnt = basketDao.selectBasketItemListCnt(paramBasketInfo);
				int orderCnt = basketDao.hasOrderHistoryCnt(paramBasketInfo);
				
				if(itemCnt > 0){
					for(int i =0; i<itemCnt; i++){
						deleteBasketItem(paramBasketInfo);
					}
				}
				if(orderCnt >0){
					basketDao.deleteOrder(paramBasketInfo);
				}
				
				iResult += basketDao.deleteBasket(paramBasketInfo);
			}
			
		}
		

    	if(iResult != paramBasketInfo.getDeleteParam().length) {
    		bResult = false;
    	}
    	
    	return bResult;
	}
	

	/**
	 * 장바구니 아이템 리스트 조회
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public Map<String, Object> selectBasketItemList(BasketInfo paramBasketInfo) throws UserSysException {
		logger.debug("selectBasketItemList--{}", ConstKey.START_LOG);
		Map<String, Object> result = new HashMap<>();
		
		int nTotal = basketDao.selectBasketItemListCnt(paramBasketInfo);
		List list = basketDao.selectBasketItemList(paramBasketInfo);
		
		result.put("nTotal", nTotal);
		result.put("list", list);
		
		logger.debug("selectBasketItemList--{}", ConstKey.END_LOG);

    	return result;
	}
	
	
	
	/**
	 * 장바구니 아이템 추가
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean insertBasketItem(HttpServletRequest request,BasketInfo paramBasketInfo) throws UserSysException {
		boolean bResult = true;
		int iResult = 0;
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String user_curr = (String) session.getAttribute(ConstKey.USER_INFO);
		
		//장바구니 아이템에 추가할 때 통화에 대한 정보가 없을 떄 
		if("".equals(paramBasketInfo.getCur()) && "".equals(user_curr)){
			paramBasketInfo.setCur("CC0077");
		}
				
    	iResult = basketDao.insertBasketItem(paramBasketInfo);
    	//장바구니 수정일 업데이트를 위하여
    	basketDao.updateBasket(paramBasketInfo);
    	
    	if(iResult < 1) {
    		bResult = false;
    	}
    	
    	return bResult;
	}
	
	/**
	 * 장바구니 아이템 삭제 
	 * @param paramBasketInfo
	 * @return
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean deleteBasketItem(BasketInfo info) throws UserSysException {
		boolean bResult = true;
    	int iResult = basketDao.deleteBasketItem(info);
		//계산
		if(iResult == 1){
			info.setComm_cur(info.getCur());
			List<BasketInfo> tmpList = basketDao.selectBasketItemList(info);
			for(BasketInfo item : tmpList){
				item.setCur(info.getCur());
				String total = StringUtil.getMultipleQtyAndPrice(item.getQty(), item.getPrice(), info.getCur());
				item.setTotal_price(total);
			}
			//장바구니 수정일 업데이트를 위하여
			basketDao.updateBasket(info);
		}else{
			bResult = false;
		}
		
		
    	return bResult;
	}
	/**
	 * 장바구니 아이템 목록 전체 GTC 파일리스트 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public List<BasketInfo> selectBasketItemFileList(BasketInfo info) throws UserSysException {
		List<BasketInfo> itemList = basketDao.selectBasketItemList(info);
		List<BasketInfo> gtcFileList = new ArrayList<>();
		if(itemList.size() !=0){
			for(BasketInfo item : itemList){
					BasketInfo file = basketDao.selectBasketItemFile(item);
					gtcFileList.add(file);
			}
		}
		return gtcFileList;
	}
	
	/**
	 * 장바구니 아이템 목록 전체 GTC 파일
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public BasketInfo selectBasektItemFile(BasketInfo info) throws UserSysException {
		BasketInfo baksetInfo = basketDao.selectBasketItemFile(info);
		return baksetInfo;
	}

	/**
	 * 장바구니 아이템 수량 수정
	 * @param paramBasketInfo
	 * @return int
	 * @throws UserSysException
	 */
	@Override
	@Transactional
	public boolean updateBasketItemInfo(BasketInfo info) throws UserSysException {
		boolean bResult = false;
		int count = basketDao.updateBasketItem(info);
//		String totalPrice = "";
		//카트의 토탈
		BigDecimal cartTotal = BigDecimal.ZERO;
		//계산
		if(count==1){
			List<BasketInfo> tmpList = basketDao.selectBasketItemList(info);
			for(BasketInfo item : tmpList){
				item.setComm_cur(info.getCur());
				String itemTotal = StringUtil.getMultipleQtyAndPrice(item.getQty(), item.getPrice(), item.getCur());
				item.setTotal_price(itemTotal);
				BigDecimal ttl = BigDecimal.valueOf(Double.parseDouble(itemTotal));
				cartTotal = cartTotal.add(ttl);
				if(item.getMatnr().equals(info.getMatnr())){
					info.setPrice(itemTotal);
				}
			}
			
			String finalTtlPrice = StringUtil.getMultipleQtyAndPrice("1", cartTotal.toString(), info.getCur());
			info.setTotal_price(finalTtlPrice);
			//장바구니 수정일 업데이트를 위하여
			basketDao.updateBasket(info);
			bResult = true;
		}
		
		return bResult;
	}
	/**
	 * 장바구니 아이템 리스트 취득 
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public List<BasketInfo> selectBasketItemListInfo(BasketInfo paramBasketInfo) throws UserSysException {
		return basketDao.selectBasketItemList(paramBasketInfo);
	}
	/**
	 * 장바구니 아이템 팝업에서 사용
	 * 아이템 프로버티 리스트 취득
	 * @param paramBasketInfo
	 * @return List
	 * @throws UserSysException
	 */
	@Override
	public List<AppInfo> selectItemPropSymbolList(AppSearchInfo searchInfo) throws UserSysException {
		return basketDao.selectItemPropSymbolList(searchInfo);
	}
	
	/**
	 * 장바구니 아이템 팝업에서 사용
	 * 아이템의 그룹코드 취득
	 * @param paramBasketInfo
	 * @return String
	 * @throws UserSysException
	 */
	@Override
	public String selectIgCd(AppSearchInfo searchInfo) throws UserSysException {
		return basketDao.selectIgCd(searchInfo);
	}

	/**
	 * 통화취득
	 * @param info
	 * @return String
	 * @throws UserSysException
	 */
	@Override
	public String selectCurrencyName(BasketInfo info) throws UserSysException {
		return basketDao.selectCurrencyName(info);
	}

}
