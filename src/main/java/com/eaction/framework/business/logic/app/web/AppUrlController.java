/*****************************************************************************
 * 프로그램명  : AppUrlController.java
 * 설     명  : Application 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.20  정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.basket.constant.BasketConstKey;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.search.model.ESInfo;
import com.eaction.framework.business.logic.app.constant.AppConstUrl;
import com.eaction.framework.business.logic.app.constant.AppConstKey;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.spring.web.servlet.view.JsonView;

/**
 * Application controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class AppUrlController {
	/** 로그처리 객체 */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private AppService appService;
	@Resource
	private BasicService basicService;
	@Resource
	private AssemblyService assemblyService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	
	
	/**
	 * Home 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_MAIN_URL)
	@ResponseBody
	public ModelAndView goHome(HttpServletRequest request, HttpServletResponse response, Device device) throws UserSysException {
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		AppInfo searchinfo = new AppInfo();
		searchinfo.setInch_use_yn(inch_use_yn);
		List<AppInfo> main_app_list = appService.selectMainApplicationList(searchinfo);
		for(AppInfo info : main_app_list) {
			info.setMa_nm(StringUtil.nvl(LangMng.LANG_D(info.getMa_cd(), session_lang), info.getMa_nm()));
		}
		ModelAndView modelAndView = new ModelAndView(AppConstUrl.JSP_APP_MAIN_URL, "main_app_list", main_app_list);
		String deviceType = "";
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		modelAndView.addObject("deviceType", deviceType);
		return modelAndView;
		
	}
	
	/**
	 * Sub Application 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_SUB_URL)
	@ResponseBody
	public ModelAndView goSubApplicaion(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo, Device device) throws UserSysException{
		searchinfo.setStartIndex(0);
		searchinfo.setPageCount(Integer.parseInt(ConfigMng.getValue(IPropertyKey.NORMAL_PAGE_ROWNUM)));
		searchinfo.setIsPageYn(ConstKey.KEY_Y);
		searchinfo.setOrderSort("SA_HIER.SEQ");	
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int sub_app_list_cnt = appService.selectSubApplicationListCnt(searchinfo);
		List<AppInfo> sub_app_list = appService.selectSubApplicationList(searchinfo);
		List<AppInfo> tail_info = appService.selectTailInfo(searchinfo);
		JSONObject json_tail = new JSONObject();
		for(AppInfo info : tail_info) {
			json_tail.put(info.getLang_cd(), info.getTail_file());
		}
		String session_lang = ConfigMng.getLang(request);
		for(AppInfo info : sub_app_list) {
			info.setSa_nm(StringUtil.nvl(LangMng.LANG_D(info.getSa_cd(), session_lang), info.getSa_nm()));
		}
		List<AppInfo> main_app_list = appService.selectMainApplicationList(searchinfo);
		for(AppInfo info : main_app_list) {
			info.setMa_nm(StringUtil.nvl(LangMng.LANG_D(info.getMa_cd(), session_lang), info.getMa_nm()));
		}
		ModelAndView modelAndView = new ModelAndView(AppConstUrl.JSP_APP_SUB_URL, ConstKey.SEARCH_DATA, searchinfo);
		String deviceType = "";
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		modelAndView.addObject("json_tail", json_tail);
		modelAndView.addObject("main_app_list", main_app_list);
		modelAndView.addObject("sub_app_list_cnt", sub_app_list_cnt);
		modelAndView.addObject("sub_app_list", sub_app_list);
		modelAndView.addObject("deviceType", deviceType);
		return modelAndView;
		
	}
	
	/**
	 * Item Group List 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_GRP_LIST_URL)
	@ResponseBody
	public ModelAndView goItemGroupList(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo,  Device device) throws UserSysException{
		//#########################################
		//Assembly에서 넘어오는 IG_CD
		if("".equals(searchinfo.getItem_view_yn()) && !"".equals(searchinfo.getIg_cd())) {
			searchinfo.setAssem_ig_cd(searchinfo.getIg_cd());
			String[] igCdArr = searchinfo.getIg_cd().split(",");
			searchinfo.setIg_cd_arr(igCdArr);
		}
		//#########################################
		
		// 검색에서 넘어온 경우
		if("Y".equals(searchinfo.getItem_view_yn())){
			// 검색에서 넘어왔는데 키워드검색을 해서 선택된 Sub Application이 없는경우
			if("".equals(searchinfo.getSa_cd())) {
				String sa_cd = appService.selectIgCodeToSaCode(searchinfo);
				searchinfo.setSa_cd(sa_cd);
			}
			
			// 검색에서 넘어왔는데 키워드검색을 해서 선택된 Main Application이 없는경우
			if("".equals(searchinfo.getMa_cd())) {
				String ma_cd = appService.selectSaCodeToMaCode(searchinfo);
				searchinfo.setMa_cd(ma_cd);
			}
		}
		
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		
		searchinfo.setOrderSort("SA_HIER.SEQ");	
		List<AppInfo> sub_app_list = appService.selectSubApplicationList(searchinfo);
		List<AppInfo> main_app_list = appService.selectMainApplicationList(searchinfo);
		String[] ar_sa_cd = null;
		if(!"".equals(searchinfo.getSa_cd())) {
			ar_sa_cd = searchinfo.getSa_cd().split(",");
			StringBuffer sbSA_SQL = new StringBuffer();
			if(ar_sa_cd != null && ar_sa_cd.length > 0) {
				sbSA_SQL.append(" AND EXISTS(");
				for(int nSql=0; nSql < ar_sa_cd.length; nSql++) {
					String sa_cd = ar_sa_cd[nSql];
					if(nSql > 0) {
						sbSA_SQL.append(" INTERSECT");	
					}
					sbSA_SQL.append(" SELECT 1");
					sbSA_SQL.append(" FROM TB_ECAT_CONT_SA SA_APP");
					sbSA_SQL.append(" WHERE SA_APP.SA_CD = IG_HIER.SA_CD");
					sbSA_SQL.append(" AND SA_APP.USE_YN = 'Y'");
					sbSA_SQL.append(String.format(" AND SA_APP.SA_CD = '%s'", sa_cd));
				}
				sbSA_SQL.append(")");
			}
			searchinfo.setSa_multi_sql(sbSA_SQL.toString());
			searchinfo.setAr_sa_cd(ar_sa_cd);
		}
		searchinfo.setStartIndex(0);
		searchinfo.setPageCount(Integer.parseInt(ConfigMng.getValue(IPropertyKey.NORMAL_PAGE_ROWNUM)));
		searchinfo.setOrderSort("ISNULL(TB.SEQ, 999999), TB.IG_NM");
		List<AppInfo> item_grp_list = appService.selectItemGroupList(searchinfo);
		int item_grp_list_cnt = appService.selectItemGroupListCnt(searchinfo);
		int nItemTotalCnt = appService.selectItemTotalCnt(searchinfo);
		AppSearchInfo srh = new AppSearchInfo();
		srh.setSearch_ar_sa_cd(searchinfo.getAr_sa_cd());
		List<AppInfo> filterList = appService.selectItemGroupFilterList(srh);
		List<AppInfo> duplChkfilterList = new ArrayList<AppInfo>(filterList);
		List<AppInfo> resultfilterList = new ArrayList<AppInfo>();
		List<String> prop_list = new ArrayList<String>();
		for(AppInfo filterInfo : duplChkfilterList) {
			if(!prop_list.contains(filterInfo.getProp_iso())) {
				List<AppInfo> chkList = filterList.stream().filter(a -> a.getProp_iso().equals(filterInfo.getProp_iso())).collect(Collectors.toList());
				resultfilterList.add(chkList.get(0));
				prop_list.add(filterInfo.getProp_iso());
			}
		}
		for(AppInfo filterInfo : resultfilterList) {
			if("SB".equals(filterInfo.getSch_typ())) {
				List<CodeInfo> cbList = appService.selectItemPropComboList(filterInfo);
				filterInfo.setCombo_list(cbList);
			}
		}
		JSONArray jsonFilter = AjaxJsonUtil.getJsonData(resultfilterList);
		String deviceType="";		
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		
		ModelAndView modelAndView = new ModelAndView(AppConstUrl.JSP_APP_ITEM_GRP_LIST_URL, ConstKey.SEARCH_DATA, searchinfo);
		modelAndView.addObject("sub_app_list", sub_app_list);
		modelAndView.addObject("main_app_list", main_app_list);
		modelAndView.addObject("item_grp_list", item_grp_list);
		modelAndView.addObject("itemTotalCnt", nItemTotalCnt);
		modelAndView.addObject("item_grp_list_cnt", item_grp_list_cnt);
		modelAndView.addObject("filterList", resultfilterList);
		modelAndView.addObject("jsonFilter", jsonFilter.toString());
		modelAndView.addObject("deviceType", deviceType);
		
		return modelAndView;
		
	}
	
	/**
	 * Item Group 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_GRP_URL)
	@ResponseBody
	public ModelAndView goItemGroup(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo) throws UserSysException{
		
		ModelAndView modelAndView = new ModelAndView(AppConstUrl.JSP_APP_ITEM_GRP_URL, ConstKey.SEARCH_DATA, searchinfo);

		return modelAndView;
		
	}
	
	/**
	 * Item Group 정보 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ITEM_GROUP_INFO)
	@ResponseBody
	private ModelAndView getItemGroupInfo(HttpServletRequest request, AppSearchInfo searchinfo, Device device) throws UserSysException {

		String session_lang = ConfigMng.getLang(request);
		searchinfo.setUser_lang(session_lang);
		AppInfo itemGroupInfo = appService.selectItemGroupInfo(searchinfo); // Item Group 정보 취득 (Brand Logo, ItemGroup 명칭, Header1)
		// 다국어 처리
		// 아이템 그룹 설명
		String ig_desc = StringUtil.nvl(LangMng.LANG_D(itemGroupInfo.getIg_cd(), session_lang), itemGroupInfo.getIg_dscr());
		itemGroupInfo.setIg_dscr(ig_desc);
		// TM
		String tm_nm = StringUtil.nvl(LangMng.LANG_D(itemGroupInfo.getTm_cd(), session_lang), itemGroupInfo.getTm_nm());
		itemGroupInfo.setTm_nm(tm_nm);
		AppInfo igImgInfo = appService.selectIgImageInfo(searchinfo); // Item Group 대표 이미지, ISO도면, Non-ISO도면 취득
		List<AppInfo> subAppImgList = appService.selectSubImageList(searchinfo); // Sub App 이미지 목록 취득
		List<AppInfo> propList = getItemPropSymbolList(searchinfo); // Filter Property 목록 취득
		//프로퍼티 툴팁 값 활용을 위해서 setting
		for(AppInfo props : propList){
			props.setProp_title(LangMng.LANG_D(props.getProp_iso(), session_lang));
			props.setProp_n_title(LangMng.LANG_D(props.getProp_n_iso(), session_lang));
		}
		//List<AppInfo> itemList = appService.selectItemList(searchinfo); // Item 목록 취득		
		List<Map<String, Object>> itemList = appService.selectItemList(searchinfo);
		//String insertCnt = appService.selectItemInsertsCnt(searchinfo); // Insert Item 갯수 취득		
		List<AppInfo> isoCalList = appService.selectIsoCalList(searchinfo); // ISO metric/inch 계산식 단위 및 값 취득
		
		String inchYN = CodeTableMng.getCodeName("CM0019", "CC0204"); // 인치 사용여부
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("itemGroupInfo", itemGroupInfo);
		modelAndView.addObject("igImgInfo", igImgInfo);
		modelAndView.addObject("subAppImgList", subAppImgList);
		modelAndView.addObject("propList", propList);
		modelAndView.addObject("itemList", itemList);		
		//modelAndView.addObject("insertCnt", insertCnt);		
		modelAndView.addObject("isoCalList", isoCalList);
		modelAndView.addObject("inchYN", inchYN);
		
		return modelAndView;
	}
	
	/**
	 * More Info 정보 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_IG_MORE_INFO)
	@ResponseBody
	private ModelAndView getIgMoreInfo(HttpServletRequest request, AppSearchInfo searchinfo, Device device) throws UserSysException {
		// 다국어 처리
		String session_lang = ConfigMng.getLang(request);
		searchinfo.setUser_lang(session_lang);
		
		AppInfo moreInfo = appService.selectMoreInfo(searchinfo); // More Info 정보 취득
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("moreInfo", moreInfo);		
		
		return modelAndView;
	}
	
	/**
	 * Item 정보 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ITEM_INFO)
	@ResponseBody
	private ModelAndView getItemInfo(AppSearchInfo searchinfo) throws UserSysException {
		
		Map<String, Object> itemInfo = appService.selectItemInfo(searchinfo);
		// 로그인상태인 경우 해당 아이템의 마지막 카드번호 취득
		String cartNo = "";
		if(!"".equals(searchinfo.getLogin_user_id())) {
			cartNo = appService.selectItemLastCartNo(searchinfo);	
			if(cartNo == null) {
				cartNo = "";
			}
		}else {
			cartNo = "";
		}
		
		String priceYN = CodeTableMng.getCodeName("CM0019", "CC0202"); // 가격 사용여부
		String stockYN = CodeTableMng.getCodeName("CM0019", "CC0203"); // 재고 사용여부		
		String inchYN = CodeTableMng.getCodeName("CM0019", "CC0204"); // 인치 사용여부		
		
		// Related Inserts 리스트 갯수
		List<Map<String, Object>> insertsList = appService.selectRelatedInsertsList(searchinfo);
		// Related Holders 리스트 갯수
		List<Map<String, Object>> holdersList = appService.selectRelatedHoldersList(searchinfo);
		// Recommened Cutting Speeds 리스트 갯수
		int cuttingSpeedCnt = appService.selectCuttingSpeedListCnt(searchinfo);
		// Spare parts 리스트 갯수
		int sparePartsCnt = appService.selectSparePartsListCnt(searchinfo);	
				
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("itemInfo", itemInfo);
		modelAndView.addObject("cartNo", cartNo);
		modelAndView.addObject("insertsCnt", insertsList.size());
		modelAndView.addObject("holdersCnt", holdersList.size());
		modelAndView.addObject("cuttingSpeedCnt", cuttingSpeedCnt);
		modelAndView.addObject("sparePartsCnt", sparePartsCnt);
		modelAndView.addObject("priceYN", priceYN);
		modelAndView.addObject("stockYN", stockYN);		
		modelAndView.addObject("inchYN", inchYN);		
		
		return modelAndView;
		
	}
	
	/**
	 * 선택한 Item Property Symbol 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	private List<AppInfo> getItemPropSymbolList(AppSearchInfo searchinfo) throws UserSysException {
		
		List<AppInfo> resultInfo = appService.selectItemPropSymbolList(searchinfo);
		
		return resultInfo;
		
	}
	
	/**
	 * Related Inserts 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_RELATED_INSERTS_LIST)
	@ResponseBody
	private ModelAndView getRelatedInsertsList(AppSearchInfo searchinfo) throws UserSysException {
		
		List<Map<String, Object>> insertsList = appService.selectRelatedInsertsList(searchinfo);
		// 프로퍼티 목록조회
		List<String> arr_ig_cd = new ArrayList<String>();
		for(Map map : insertsList) {
			if(!arr_ig_cd.contains(map.get("ITEM_GRP"))) {
				arr_ig_cd.add((String)map.get("ITEM_GRP"));
			}
		}
		AppSearchInfo search_prop = new AppSearchInfo();
		search_prop.setIg_cd_arr(arr_ig_cd.toArray(new String[arr_ig_cd.size()]));
		List<AppInfo> prop_list = null;
		if(arr_ig_cd.size() > 0) {
			prop_list = appService.selectItemPropSymbolList(search_prop);
		}
		JSONArray json_prop = AjaxJsonUtil.getJsonData(prop_list);
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("insertsList", insertsList);
		modelAndView.addObject("prop_list", json_prop);
		
		return modelAndView;
		
	}
	
	/**
	 * Related Holders 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_RELATED_HOLDERS_LIST)
	@ResponseBody
	private ModelAndView getRelatedHoldersList(AppSearchInfo searchinfo) throws UserSysException {
		
		List<Map<String, Object>> holdersList = appService.selectRelatedHoldersList(searchinfo);
		// 프로퍼티 목록조회
		List<String> arr_ig_cd = new ArrayList<String>();
		for(Map map : holdersList) {
			if(!arr_ig_cd.contains(map.get("ITEM_GRP"))) {
				arr_ig_cd.add((String)map.get("ITEM_GRP"));
			}
		}
		AppSearchInfo search_prop = new AppSearchInfo();
		search_prop.setIg_cd_arr(arr_ig_cd.toArray(new String[arr_ig_cd.size()]));
		List<AppInfo> prop_list = null;
		if(arr_ig_cd.size() > 0) {
			prop_list = appService.selectItemPropSymbolList(search_prop);
		}
		JSONArray json_prop = AjaxJsonUtil.getJsonData(prop_list);
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("holdersList", holdersList);		
		modelAndView.addObject("prop_list", json_prop);
		
		return modelAndView;
		
	}
	
	/**
	 * Recommended Cutting Speeds 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_RECOMMENDED_CUTTING_SPEEDS_LIST)
	@ResponseBody	
	private ModelAndView getCuttingSpeedList(AppSearchInfo searchinfo) throws UserSysException {

		List<AppInfo> cuttingSpeedList = appService.selectCuttingSpeedList(searchinfo);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("cuttingSpeedList", cuttingSpeedList);		
		
		return modelAndView;
		
	}
	
	/**
	 * Spare Parts 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_SPARE_PARTS_LIST)
	@ResponseBody	
	private ModelAndView getSparePartsList(AppSearchInfo searchinfo) throws UserSysException {
		
		List<AppInfo> sparePartsList = appService.selectSparePartsList(searchinfo);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("sparePartsList", sparePartsList);		
		
		return modelAndView;
		
	}
	
	/**
	 * Cart 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws UserSysException
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_CART_LIST)
	@ResponseBody	
	private ModelAndView getCartList(HttpServletRequest request, AppSearchInfo searchinfo) throws UserSysException {

		HttpSession session = request.getSession();
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);       
		
		List<AppInfo> cartList = appService.selectCartList(searchinfo);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("cartList", cartList);		
		
		return modelAndView;
		
	}
	
	/*
	 * 카트등록 및 카트에 아이템 등록
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_CART_ADD_OK)
	@ResponseBody
    public String doInsertCartItem(HttpServletRequest request, HttpServletResponse response, AppInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();    	

    	boolean bResult = true;
    	
		try{
			bResult = appService.insertCartItem(info);
			AppSearchInfo searchinfo = new AppSearchInfo();
			searchinfo.setLogin_user_id(info.getLogin_user_id());
			List<AppInfo> cartList = appService.selectCartList(searchinfo);
			if (bResult) {
				jsonObject.put("resCode", "0");
				jsonObject.put("resCartCnt", cartList.size());
				jsonObject.put("resMsg", "SUCCESS");
			} else {
				jsonObject.put("resCode", "1");
				jsonObject.put("resMsg",  "FAIL");
			}
			
		}catch(Exception e){
			jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  "MSG.FAIL");
		}
		return jsonObject.toString();
	}	
	
	/*
	 * 아이템의 마지막 Cart No 취득 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ITEM_LAST_CART_NO)
	@ResponseBody
    public String doSelectItemLastCartNo(HttpServletRequest request, HttpServletResponse response, AppSearchInfo searchinfo) throws UserSysException{
				
		String cartNo = "";
		cartNo = appService.selectItemLastCartNo(searchinfo);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("cartNo", cartNo);
		
		return cartNo;

	}	
	
	/**
	 * Assembly 목록 취득 처리 
	 * @param searchinfo 검색데이터
	 * @return AppInfo 아이템정보
	 * @throws Exception 
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ASSEMBLY_LIST)
	@ResponseBody	
	private ModelAndView getAssemblyList(HttpServletRequest request, AppSearchInfo searchinfo) throws Exception {
		
		HttpSession session = request.getSession();
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);       
		
		//선택한 아이템의 Assembly정보를 가지고온다.
		//TL, INS, ADP 여부
		//itemTL, itemINS, itemADP 리스트 취득
		AssemblyInfo itemAssemInfo = assemblyService.getAssembly(searchinfo.getSearch_matnr());
		
		List<AppInfo> assemblyList = appService.selectAssemblyList(searchinfo);
		
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		modelAndView.addObject("itemAssemInfo", itemAssemInfo);
		modelAndView.addObject("assemblyList", assemblyList);
		
		
		return modelAndView;
		
	}
	
	/*
	 * 카트등록 및 카트에 아이템 등록
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ASSEMBLY_ADD_OK)
	@ResponseBody
    public String doInsertAssemblyItem(HttpServletRequest request, HttpServletResponse response, AppInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();    	

    	int bResult = 0;
    	
		try{
			bResult = appService.insertAssemblyItem(info);
			if(bResult == 0){
				jsonObject.put("resCode", "0");
				jsonObject.put("resMsg",  "FAIL");
			}else if(bResult == 1){
				jsonObject.put("resCode", "1");
				jsonObject.put("resMsg",  "SUCCESS");
				
			}else if(bResult == 3){
				jsonObject.put("resCode", "3");
				jsonObject.put("resMsg",  "NOT ASSEMVLY ITEM");
			}
			
			/*if (bResult) {
				jsonObject.put("resCode", "0");
				jsonObject.put("resMsg", "SUCCESS");
			} else {
				jsonObject.put("resCode", "1");
				jsonObject.put("resMsg",  "FAIL");
			}*/
			
		}catch(Exception e){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg",  "MSG.FAIL");
		}
		return jsonObject.toString();
	}	
//	/*
//	 * 카트등록 및 카트에 아이템 등록
//	 * @param request Request객체
//	 * @param response Response객체
//	 * @exception UserSysException 시스템예외
//	 */
//	@RequestMapping(value=ConstUrl.APP_ITEM_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_)
//	@ResponseBody
//	public String changeProppertyList(HttpServletRequest request, HttpServletResponse response, AppInfo info) throws UserSysException{
//		
//		JSONObject jsonObject = new JSONObject();    	
//		
//		int bResult = 0;
//		
//		try{
//			bResult = appService.insertAssemblyItem(info);
//			if(bResult == 0){
//				jsonObject.put("resCode", "0");
//				jsonObject.put("resMsg",  "FAIL");
//			}else if(bResult == 1){
//				jsonObject.put("resCode", "1");
//				jsonObject.put("resMsg",  "SUCCESS");
//				
//			}else if(bResult == 3){
//				jsonObject.put("resCode", "3");
//				jsonObject.put("resMsg",  "NOT ASSEMVLY ITEM");
//			}
//			
//			/*if (bResult) {
//				jsonObject.put("resCode", "0");
//				jsonObject.put("resMsg", "SUCCESS");
//			} else {
//				jsonObject.put("resCode", "1");
//				jsonObject.put("resMsg",  "FAIL");
//			}*/
//			
//		}catch(Exception e){
//			jsonObject.put("resCode", "0");
//			jsonObject.put("resMsg",  "MSG.FAIL");
//		}
//		return jsonObject.toString();
//	}	
//	
//	private List<AppInfo> getPropList(List<AppInfo> propList, String sessionLang) throws UserSysException{
//		List<AppInfo>
//	}
	
}
