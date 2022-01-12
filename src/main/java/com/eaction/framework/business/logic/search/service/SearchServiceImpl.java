/*****************************************************************************
 * 프로그램명  : SearchServiceImpl.java
 * 설     명  : 검색 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.30   정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.search.dao.SearchDao;
import com.eaction.framework.business.logic.search.model.SearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 검색 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {	
	/** 로그출력 객체 */
	private static final Log logger = LogFactory.getLog( SearchServiceImpl.class );
	
	@Resource
	private SearchDao searchDao;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	/**
	 * 검색조건 Main App 조회
	 * @return  List<CodeInfo>
	 */
	@Override
	public List<SearchInfo> selectSearchCondMainApp() throws UserSysException{
		return searchDao.selectSearchCondMainApp();
	}
	
	/**
	 * 검색조건 Sub App 조회
	 * @return  List<SearchInfo>
	 */
	@Override
	public List<SearchInfo> selectSearchCondSubApp(SearchInfo info) throws UserSysException{
		return searchDao.selectSearchCondSubApp(info);
	}
	
	/**
	 * 검색조건 Item Group 조회
	 * @return  List<SearchInfo>
	 */
	@Override
	public List<SearchInfo> selectSearchCondItemGroup(SearchInfo info) throws UserSysException{
		return searchDao.selectSearchCondItemGroup(info);
	}
	
	/**
	 * 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	@Override
	public int selectSearchItemListCnt(SearchInfo info) throws UserSysException{
		return searchDao.selectSearchItemListCnt(info);
	}
	
	/**
	 * 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	@Override
	public List<SearchInfo> selectSearchItemList(SearchInfo info) throws UserSysException{
		return searchDao.selectSearchItemList(info);
	}
	
	/**
	 * Parametric 검색 조회 개수
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	@Override
	public int selectParaSearchItemListCnt(SearchInfo info) throws UserSysException{
		return searchDao.selectParaSearchItemListCnt(info);
	}
	
	/**
	 * Parametric 검색 조회
	 * @param SearchInfo info
	 * @return  검색 조회 개수
	 */
	@Override
	public List<SearchInfo> selectParaSearchItemList(SearchInfo info) throws UserSysException{
		return searchDao.selectParaSearchItemList(info);
	}
	
	/**
	 * GTC 계층조회
	 * @param SearchInfo info
	 * @return  GTC 계층
	 */
	@Override
	public Map selectGTCHierList(SearchInfo info) throws UserSysException{
		List<SearchInfo> result = searchDao.selectGTCHierList(info);
		// VD_ID별 하위아이템 개수, 아이템에 속해있는 VD_ID리스트
		HashMap<String, String> vd_id_cnt = new HashMap<String, String>();
		Map resultMap = new HashMap();
		JSONObject jsonParentArr = new JSONObject();
		if(result != null && result.size() > 0) {
			// 원본을 임시리스트에 복사
			List<SearchInfo> tmpResult = new ArrayList<SearchInfo>(result);
			// 원본리스트
			for(SearchInfo resultInfo : result) {
				JSONObject jsonParent = new JSONObject();
				jsonParent.put("id", resultInfo.getGtc_vd_id());
				jsonParent.put("name", resultInfo.getGtc_vd_node_nm());
				jsonParent.put("icon", resultInfo.getGtc_vd_icon());
				jsonParent.put("sub_cnt", resultInfo.getSub_cnt());
				int sub_cnt = Integer.parseInt(resultInfo.getSub_cnt());
				int nItem_cnt = Integer.parseInt(resultInfo.getItem_cnt());
				vd_id_cnt.put(resultInfo.getGtc_vd_id(), Integer.toString(nItem_cnt));
				// 하위 노드가 있으면
				if(sub_cnt > 0) {
					String pid = resultInfo.getGtc_vd_id();
					JSONArray jsonChildArr = new JSONArray();
					JSONObject jsonChild = new JSONObject();
					for(SearchInfo tmpInfo : tmpResult) {
						// 나를 상위아이디로 가지고 있으면
						if(tmpInfo.getGtc_vd_pid().equals(pid)) {
							jsonChild.clear();
							jsonChild.put("id", tmpInfo.getGtc_vd_id());
							jsonChild.put("name", tmpInfo.getGtc_vd_node_nm());
							jsonChild.put("icon", tmpInfo.getGtc_vd_icon());
							jsonChild.put("sub_cnt", tmpInfo.getSub_cnt());
							jsonChildArr.add(jsonChild);
							// 하위노드 아이템 개수
							int nSubItemCnt = Integer.parseInt(tmpInfo.getItem_cnt());
							// CTL\ACC\ACC_CL\ACC_CLA 제일 마지막이 내아이디이므로 내꺼 빼고 상위 아이디에 카운트 ++
							String[] ar_pid = tmpInfo.getSort().split("\\\\");
							if(ar_pid != null && ar_pid.length > 1) {
								String tmp_key = "";
								boolean isPidSum = false;
								for(int nSort=0; nSort < ar_pid.length-1; nSort ++) {
									// 자기 상위아이디 Sum 여부
									if(pid.equals(ar_pid[nSort])) {
										isPidSum = true;
									}
									if(vd_id_cnt.containsKey(ar_pid[nSort])) {
										int nTmpItemCnt = Integer.parseInt(vd_id_cnt.get(ar_pid[nSort]));
										
										if(!"".equals(StringUtil.nvl(ar_pid[nSort]))) {
											vd_id_cnt.replace(ar_pid[nSort], Integer.toString(nTmpItemCnt + nSubItemCnt));
										}
									}else {
										vd_id_cnt.put(ar_pid[nSort], Integer.toString(nSubItemCnt));
									}
								}
								/*SORT / VD_ID / VD_PID
								 * CTL\TL\BLT\BLTB\BLTBB\BLTBBI\B\BLTBBI_WISO$T_MVKT01	/ BLTBBI_WISO$T_MVKT01	/ BLTBBI_WISO$T
								 * SORT에 PID가 포함되지 않는 경우가 생겨 카운트에서 빠짐. 예외처리
								 */
								if(!isPidSum && ar_pid[ar_pid.length-1].lastIndexOf("_") > -1) {
									tmp_key = ar_pid[ar_pid.length-1].substring(0, ar_pid[ar_pid.length-1].lastIndexOf("_"));
									if(!"".equals(StringUtil.nvl(tmp_key)) && vd_id_cnt.containsKey(tmp_key)) {
										int nTmpItemCnt = Integer.parseInt(vd_id_cnt.get(tmp_key));
										vd_id_cnt.replace(tmp_key, Integer.toString(nTmpItemCnt + nSubItemCnt));
									}
								}
							}
						}
					}
					jsonParent.put("list", jsonChildArr);
				}
				jsonParentArr.put(resultInfo.getGtc_vd_id(), jsonParent);
			}
		}
		resultMap.put("list", jsonParentArr);
		resultMap.put("cnt", vd_id_cnt);
		return resultMap;
	}
	
	/**
	 * Parametric Symbol 리스트
	 * @param SearchInfo info
	 * @return  Parametric Symbol 리스트
	 */
	@Override
	public List<SearchInfo> selectParametricSymbolList(SearchInfo info) throws UserSysException{
		return searchDao.selectParametricSymbolList(info);
	}
}
