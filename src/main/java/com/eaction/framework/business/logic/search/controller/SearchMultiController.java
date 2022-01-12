/*****************************************************************************
 * 프로그램명  : SearchMultiController.java
 * 설     명  : 검색 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02  정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.search.constant.SearchConstKey;
import com.eaction.framework.business.logic.search.model.ESInfo;
import com.eaction.framework.business.logic.search.model.SearchInfo;
import com.eaction.framework.business.logic.search.service.SearchService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.LanguageInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.ExcelUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.FileUtil;
import com.eaction.framework.common.util.PagingUtil;
import com.eaction.framework.common.util.RESTfulUtil;
import com.eaction.framework.common.util.StringUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 검색 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class SearchMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(SearchMultiController.class);    

	@Resource
	private SearchService searchService;
	@Resource
	private AppService appService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * Sub Application 검색조건 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_GET_COND_SUB_APP)
	@ResponseBody
	public JSONObject getSubApplicationCond(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		JSONObject result = new JSONObject();
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int total_cnt = searchService.selectSearchItemListCnt(searchinfo);
		List<SearchInfo> sub_list = searchService.selectSearchCondSubApp(searchinfo);
		String session_lang = ConfigMng.getLang(request);
		
		for(SearchInfo sub : sub_list) {
			sub.setSa_nm(StringUtil.nvl(LangMng.LANG_D(sub.getSa_cd(), session_lang), sub.getSa_nm()));
		}
		JSONArray sub_app_list = AjaxJsonUtil.getJsonData(sub_list);
		result.put("sub_app_list", sub_app_list);
		result.put("total_cnt", total_cnt);
		return result;
		
	}

	/**
	 * Item Group 검색조건 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_GET_COND_ITEM_GROUP)
	@ResponseBody
	public JSONObject getItemGroupCond(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		JSONObject result = new JSONObject();
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int total_cnt = searchService.selectSearchItemListCnt(searchinfo);
		List<SearchInfo> item_group_list = searchService.selectSearchCondItemGroup(searchinfo);
		JSONArray item_group = AjaxJsonUtil.getJsonData(item_group_list);
		result.put("item_group_list", item_group);
		result.put("total_cnt", total_cnt);
		return result;
		
	}
	
	/**
	 * Item 개수 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_GET_ITEM_CNT)
	@ResponseBody
	public JSONObject getItemCnt(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		JSONObject result = new JSONObject();
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int total_cnt = searchService.selectSearchItemListCnt(searchinfo);
		result.put("total_cnt", total_cnt);
		return result;
		
	}
	
	/**
	 * 필터 조회/설정
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_GET_SEARCH_FILTER)
	@ResponseBody
	public JSONObject getFilter(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo, Device device) throws UserSysException{
		JSONObject result = new JSONObject();
		List<SearchInfo> filterList = searchService.selectParametricSymbolList(searchinfo);
		for(AppInfo filterInfo : filterList) {
			if("SB".equals(filterInfo.getSch_typ())) {
				List<CodeInfo> cbList = appService.selectItemPropComboList(filterInfo);
				filterInfo.setCombo_list(cbList);
			}
		}
		JSONArray jsonFilter = AjaxJsonUtil.getJsonData(filterList);
		HttpSession session = request.getSession(true);
		// 세션에 언어가 설정되지 않은경우 언어설정을 한다.
		String session_lang = (String)session.getAttribute(ConstKey.SESSION_LANG);
		if("".equals(StringUtil.nvl(session_lang))) {
			session_lang = ConfigMng.getLang(request);
		}
		JSONObject filterHtml = MakeFilterHtml(filterList, device, session_lang);
		result.put("filterList", jsonFilter.toString());
		result.put("filterHtml", filterHtml);
		return result;
		
	}
	
	private JSONObject MakeFilterHtml(List<SearchInfo> filterList, Device device, String session_lang) {
		JSONObject jsonHtml = new JSONObject();
		StringBuffer sbHtml = new StringBuffer();

		if (filterList != null && filterList.size() > 0) {
			for (int nFilter = 0; nFilter < filterList.size(); nFilter++) {
				AppInfo filterInfo = (AppInfo) filterList.get(nFilter);
				String sch_type = filterInfo.getSch_typ();
				if(nFilter == 10) {
					sbHtml = new StringBuffer();
				}
				// PC
				if (device.isNormal()) {
					sbHtml.append("<tr>");
					sbHtml.append(String.format("<th>%s</th>", filterInfo.getProp_iso()));
					sbHtml.append(String.format("<td data-lang=\"%s\">%s</td>", filterInfo.getProp_iso(), LangMng.LANG_D(filterInfo.getProp_iso(), session_lang)));
					sbHtml.append("<td>");
					if ("IN".equals(sch_type)) {
						sbHtml.append("<div class=\"degree\">");
						sbHtml.append("<div class=\"input\">");
						sbHtml.append(String.format("<input type=\"text\"  id=\"filter_%s\">",
								filterInfo.getProp_iso()));
						sbHtml.append("</div>");
						sbHtml.append("</div>");
					} else if ("CE".equals(sch_type)) {
						sbHtml.append("<div class=\"degree\">");
						sbHtml.append(String.format(
								"<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce1\" data-prop=\"%s\" style=\"cursor:pointer\" id=\"filter_%s_ce1\">≤</a>",
								filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append(String.format(
								"<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce2\" data-prop=\"%s\" style=\"cursor:pointer\" class=\"on\" id=\"filter_%s_ce2\">=</a>",
								filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append(String.format(
								"<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce3\" data-prop=\"%s\" style=\"cursor:pointer\" id=\"filter_%s_ce3\">≥</a>",
								filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append("<div class=\"input\">");
						sbHtml.append(
								String.format("<input type=\"text\" id=\"filter_%s_1\">",
										filterInfo.getProp_iso()));
						sbHtml.append("</div>");
						sbHtml.append(String.format("<div class=\"input last\"  id=\"filter_%s_2_div\">",
								filterInfo.getProp_iso()));
						sbHtml.append(
								String.format("<input type=\"text\"  id=\"filter_%s_2\">",
										filterInfo.getProp_iso()));
						sbHtml.append("</div>");
						sbHtml.append("</div>");
					} else if ("SB".equals(sch_type)) {
						sbHtml.append("<div class=\"select\">");
						sbHtml.append(
								String.format("<select name=\"\" id=\"filter_%s\">", filterInfo.getProp_iso()));
						sbHtml.append("<option value=\"\"></option>");
						for (CodeInfo codeInfo : filterInfo.getCombo_list()) {
							sbHtml.append(String.format("<option value=\"%s\">%s</option>", codeInfo.getCode(),
									codeInfo.getName()));
						}
						sbHtml.append("</select>");
						sbHtml.append("</div>");
					}
					sbHtml.append("</td>");
					sbHtml.append("</tr>");
				// 모바일
				}else {
					sbHtml.append("<li>");
					sbHtml.append(String.format("<span><em>%s</em><span data-lang=\"%s\">%s</span></span>", filterInfo.getProp_iso() ,filterInfo.getProp_iso(), LangMng.LANG_D(filterInfo.getProp_iso(), session_lang)));
                     if("IN".equals(sch_type)){ 
                    	 sbHtml.append("<div class=\"degree\" style=\"width:90%\">");
                    	 sbHtml.append("<div class=\"input\" style=\"width:90%\">");
                    	 sbHtml.append(String.format("<input type=\"text\"  id=\"filter_%s\">", filterInfo.getProp_iso()));
                    	 sbHtml.append("</div>");
                    	 sbHtml.append("</div>");
                    }else if("CE".equals(sch_type)){
                    	sbHtml.append("<div class=\"degree\">");
                        sbHtml.append(String.format("<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce1\" data-prop=\"%s\" style=\"cursor:pointer\" id=\"filter_%s_ce1\">≤</a>", filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append(String.format(
								"<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce2\" data-prop=\"%s\" style=\"cursor:pointer\" class=\"on\" id=\"filter_%s_ce2\">=</a>",
								filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append(String.format(
								"<a onclick=\"javascript:doFilterCeClick(this)\" data-type=\"ce3\" data-prop=\"%s\" style=\"cursor:pointer\" id=\"filter_%s_ce3\">≥</a>",
								filterInfo.getProp_iso(), filterInfo.getProp_iso()));
						sbHtml.append("<div class=\"input\"   style=\"width:70px\">");
						sbHtml.append(String.format("<input type=\"text\"  id=\"filter_%s_1\">", filterInfo.getProp_iso()));
						sbHtml.append("</div>");
						sbHtml.append(String.format("<div class=\"input last\" id=\"filter_%s_2_div\"  style=\"width:70px\">", filterInfo.getProp_iso()));
						sbHtml.append(String.format("<input type=\"text\"  id=\"filter_%s_2\" >", filterInfo.getProp_iso()));
						sbHtml.append("</div>");
						sbHtml.append("</div>");
                    }else if("SB".equals(sch_type)){ 
						sbHtml.append("<div class=\"select\">");
						sbHtml.append(
								String.format("<select name=\"\" id=\"filter_%s\">", filterInfo.getProp_iso()));
						sbHtml.append("<option value=\"\"></option>");
						for (CodeInfo codeInfo : filterInfo.getCombo_list()) {
							sbHtml.append(String.format("<option value=\"%s\">%s</option>", codeInfo.getCode(),
									codeInfo.getName()));
						}
						sbHtml.append("</select>");
						sbHtml.append("</div>");
                    } 
                     sbHtml.append("</li>");
				}
				if(nFilter < 10) {
					if(jsonHtml.containsKey("html1")) {
						jsonHtml.replace("html1", sbHtml.toString());
					}else {
						jsonHtml.put("html1", sbHtml.toString());
					}
				}else {
					if(jsonHtml.containsKey("html2")) {
						jsonHtml.replace("html2", sbHtml.toString());
					}else {
						jsonHtml.put("html2", sbHtml.toString());
					}
				}
			}
		}
		return jsonHtml;
	}
	
	/**
	 * 검색
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_SEARCH_LIST)
	@ResponseBody
	public JSONObject getSearch(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		JSONObject result = new JSONObject();
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int total_cnt = searchService.selectSearchItemListCnt(searchinfo);
		List<SearchInfo> item_list = searchService.selectSearchItemList(searchinfo);
		JSONArray list = AjaxJsonUtil.getJsonData(item_list);
		
		result.put("total_cnt", total_cnt);
		result.put("list", list);
		return result;
		
	}
	
	/**
	 * Parametric 검색
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_PARA_SEARCH_LIST)
	@ResponseBody
	public JSONObject getParaSearch(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		JSONObject result = new JSONObject();
		MakeFilterSQL(searchinfo);
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		int total_cnt = searchService.selectParaSearchItemListCnt(searchinfo);
		List<SearchInfo> item_list = searchService.selectParaSearchItemList(searchinfo);
		JSONArray list = AjaxJsonUtil.getJsonData(item_list);
		
		result.put("total_cnt", total_cnt);
		result.put("list", list);
		return result;
		
	}
	
	/**
	 * 자동완성 E/S검색
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + SearchConstKey.ACTION_SEARCH_AUTO, produces = "application/json; charset=utf8")
	@ResponseBody
	public JSONObject getSearchAuto(HttpServletRequest request, HttpServletResponse response, SearchInfo searchinfo) throws UserSysException{
		String session_lang = ConfigMng.getLang(request);
		
		String today = DateTimeUtil.getDateTimeByPattern("yyMMdd");
		String[] arcol = {
				"ma_nm", "sa_nm", "ig_nm", "matnr", "maktx", "designation", "grade"
		};
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		JSONObject json_return = new JSONObject();
		JSONObject json_query = new JSONObject();
		JSONObject json_match = new JSONObject();
		JSONObject json_col = new JSONObject();
		JSONObject json_detail = new JSONObject();
		JSONObject json_result = new JSONObject();
		json_detail.put("query", searchinfo.getAuto_input_text());
 		json_detail.put("analyzer", "autocomplete");
		json_detail.put("operator", "and");
		StringBuffer sbAutoHtml = new StringBuffer();
		int nResult = 0;
		for(String col : arcol) {
			json_col.clear();
			json_match.clear();
			json_query.clear();
			if(!"ENG".equals(session_lang)) {
				if("DEU".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_deu";
					}else if("sa_nm".equals(col)) {
						col = "sa_deu";
					}
				}else if("FRA".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_fra";
					}else if("sa_nm".equals(col)) {
						col = "sa_fra";
					}
				}else if("KOR".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_kor";
					}else if("sa_nm".equals(col)) {
						col = "sa_kor";
					}
				}else if("POR".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_por";
					}else if("sa_nm".equals(col)) {
						col = "sa_por";
					}
				}else if("RUS".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_rus";
					}else if("sa_nm".equals(col)) {
						col = "sa_rus";
					}
				}else if("SPA".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_spa";
					}else if("sa_nm".equals(col)) {
						col = "sa_spa";
					}
				}else if("THA".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_tha";
					}else if("sa_nm".equals(col)) {
						col = "sa_tha";
					}
				}else if("TUR".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_tur";
					}else if("sa_nm".equals(col)) {
						col = "sa_tur";
					}
				}else if("VIE".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_vie";
					}else if("sa_nm".equals(col)) {
						col = "sa_vie";
					}
				}else if("ZHO".equals(session_lang)) {
					if("ma_nm".equals(col)) {
						col = "ma_zho";
					}else if("sa_nm".equals(col)) {
						col = "sa_zho";
					}
				}
			}
			json_col.put(col, json_detail);
			json_match.put("match", json_col);
			json_query.put("query", json_match);
			json_query.put("from", "0");
			json_query.put("size", "20");
			int nTotalCnt = 1;
			json_result = RESTfulUtil.RESTfulAPICall(json_query, String.format("http://192.168.10.118:9200/korloy-%s/_search", today));
	        List<ESInfo> source_list = new ArrayList<ESInfo>();
			if(json_result != null && json_result.containsKey("hits")) {
				JSONObject json_hits1 = json_result.getJSONObject("hits");
				if(json_hits1 != null && json_hits1.containsKey("total")) {
					JSONObject json_total = json_hits1.getJSONObject("total");
					if(json_total != null && json_total.containsKey("value")) {
						nTotalCnt = json_total.getInt("value");
						if(nTotalCnt > 0 && json_hits1 != null && json_hits1.containsKey("hits")) {
							JSONArray jarr_hits2 = json_hits1.getJSONArray("hits");
							ObjectMapper mapper = new ObjectMapper();
							mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
							source_list.clear();
					        List<String> ar_ma_cd = new ArrayList<String>();
					        List<String> ar_sa_cd = new ArrayList<String>();
					        List<String> ar_ig_cd = new ArrayList<String>();
					        List<String> ar_matnr = new ArrayList<String>();
							for(int nRow=0; nRow<jarr_hits2.size(); nRow++) {
								JSONObject json_hits2 = jarr_hits2.getJSONObject(nRow);
								if(json_hits2 != null && json_hits2.containsKey("_source")) {
									JSONObject json_source = json_hits2.getJSONObject("_source");
									// 인치를 사용안하는 경우 Inch Only 아이템은 Skip
									if(ConstKey.KEY_N.equals(inch_use_yn)) {
										String unit_cd = json_source.getString("unit_cd");
										if("CC0002".equals(unit_cd)) {
											continue;
										}
									}
									ESInfo esInfo;
									try {
										esInfo = mapper.readValue(json_source.toString(), ESInfo.class);
										source_list.add(esInfo);
									} catch (JsonParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (JsonMappingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String ma_cd = "";
									String sa_cd = "";
									String ig_cd = "";
									String matnr = "";

									ma_cd = json_source.getString("ma_cd");
									sa_cd = json_source.getString("sa_cd");
									ig_cd = json_source.getString("ig_cd");
									matnr = json_source.getString("matnr");
									
									String key = "";
									switch(col) {
										case "ma_nm":
										case "ma_deu":
										case "ma_fra":
										case "ma_kor":
										case "ma_por":
										case "ma_rus":
										case "ma_spa":
										case "ma_tha":
										case "ma_tur":
										case "ma_vie":
										case "ma_zho":	
											key = ma_cd;
											if(!ar_ma_cd.contains(key)) {
												ar_ma_cd.add(key);
											}
											break;
										case "sa_nm":
										case "sa_deu":
										case "sa_fra":
										case "sa_kor":
										case "sa_por":
										case "sa_rus":
										case "sa_spa":
										case "sa_tha":
										case "sa_tur":
										case "sa_vie":
										case "sa_zho":
											key = String.format("%s|%s", ma_cd, sa_cd);
											if(!ar_sa_cd.contains(key)) {
												ar_sa_cd.add(key);
											}
											break;
										case "ig_nm":
											key = String.format("%s|%s|%s", ma_cd, sa_cd, ig_cd);
											if(!ar_ig_cd.contains(key)) {
												ar_ig_cd.add(key);
											}
											break;
										case "maktx":
										case "matnr":
										case "designation":
											if(!ar_matnr.contains(matnr)) {
												ar_matnr.add(matnr);
											}
										default: //grade
											break;
									}
								}
							}
							String ma_nm = "";
							String sa_nm = "";
							switch(col) {
								case "ma_nm":
								case "ma_deu":
								case "ma_fra":
								case "ma_kor":
								case "ma_por":
								case "ma_rus":
								case "ma_spa":
								case "ma_tha":
								case "ma_tur":
								case "ma_vie":
								case "ma_zho":
									for(String ma_cd : ar_ma_cd) {
										List<ESInfo> esList = source_list.stream().filter(a -> a.getMa_cd().equals(ma_cd)).collect(Collectors.toList());
										for(int n=0; n<esList.size(); n++) {
											ESInfo esInfo = (ESInfo)esList.get(n);
											if("ENG".equals(session_lang)) {
												ma_nm = esInfo.getMa_nm();
												sa_nm = esInfo.getSa_nm();
											}else {
												if("DEU".equals(session_lang)) {
													ma_nm = esInfo.getMa_deu();
													sa_nm = esInfo.getSa_deu();
												}else if("FRA".equals(session_lang)) {
													ma_nm = esInfo.getMa_fra();
													sa_nm = esInfo.getSa_fra();
												}else if("KOR".equals(session_lang)) {
													ma_nm = esInfo.getMa_kor();
													sa_nm = esInfo.getSa_kor();
												}else if("POR".equals(session_lang)) {
													ma_nm = esInfo.getMa_por();
													sa_nm = esInfo.getSa_por();
												}else if("RUS".equals(session_lang)) {
													ma_nm = esInfo.getMa_rus();
													sa_nm = esInfo.getSa_rus();
												}else if("SPA".equals(session_lang)) {
													ma_nm = esInfo.getMa_spa();
													sa_nm = esInfo.getSa_spa();
												}else if("THA".equals(session_lang)) {
													ma_nm = esInfo.getMa_tha();
													sa_nm = esInfo.getSa_tha();
												}else if("TUR".equals(session_lang)) {
													ma_nm = esInfo.getMa_tur();
													sa_nm = esInfo.getSa_tur();
												}else if("VIE".equals(session_lang)) {
													ma_nm = esInfo.getMa_vie();
													sa_nm = esInfo.getSa_vie();
												}else if("ZHO".equals(session_lang)) {
													ma_nm = esInfo.getMa_zho();
													sa_nm = esInfo.getSa_zho();
												}
											}
											ma_nm = StringUtil.replace(ma_nm, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
											if(n == 0) {
												sbAutoHtml.append(String.format("<li  class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"\" data-igcd=\"\" data-matnr=\"\">%s</a><div class=\"num\">%s</div></li>",  ma_cd, ma_nm, StringUtil.toComma(esList.size())));
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  ma_cd, esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx()));
											}else {
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  ma_cd, esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx()));												
											}
										}
									}
									break;
								case "sa_nm":
								case "sa_deu":
								case "sa_fra":
								case "sa_kor":
								case "sa_por":
								case "sa_rus":
								case "sa_spa":
								case "sa_tha":
								case "sa_tur":
								case "sa_vie":
								case "sa_zho":
									for(String sa_cd : ar_sa_cd) {
										String[] keys = sa_cd.split("[|]");
										List<ESInfo> esList = source_list.stream().filter(a -> a.getMa_cd().equals(keys[0]) && a.getSa_cd().equals(keys[1])).collect(Collectors.toList());
										for(int n=0; n<esList.size(); n++) {
											ESInfo esInfo = (ESInfo)esList.get(n);
											if("ENG".equals(session_lang)) {
												ma_nm = esInfo.getMa_nm();
												sa_nm = esInfo.getSa_nm();
											}else {
												if("DEU".equals(session_lang)) {
													ma_nm = esInfo.getMa_deu();
													sa_nm = esInfo.getSa_deu();
												}else if("FRA".equals(session_lang)) {
													ma_nm = esInfo.getMa_fra();
													sa_nm = esInfo.getSa_fra();
												}else if("KOR".equals(session_lang)) {
													ma_nm = esInfo.getMa_kor();
													sa_nm = esInfo.getSa_kor();
												}else if("POR".equals(session_lang)) {
													ma_nm = esInfo.getMa_por();
													sa_nm = esInfo.getSa_por();
												}else if("RUS".equals(session_lang)) {
													ma_nm = esInfo.getMa_rus();
													sa_nm = esInfo.getSa_rus();
												}else if("SPA".equals(session_lang)) {
													ma_nm = esInfo.getMa_spa();
													sa_nm = esInfo.getSa_spa();
												}else if("THA".equals(session_lang)) {
													ma_nm = esInfo.getMa_tha();
													sa_nm = esInfo.getSa_tha();
												}else if("TUR".equals(session_lang)) {
													ma_nm = esInfo.getMa_tur();
													sa_nm = esInfo.getSa_tur();
												}else if("VIE".equals(session_lang)) {
													ma_nm = esInfo.getMa_vie();
													sa_nm = esInfo.getSa_vie();
												}else if("ZHO".equals(session_lang)) {
													ma_nm = esInfo.getMa_zho();
													sa_nm = esInfo.getSa_zho();
												}
											}
											sa_nm = StringUtil.replace(sa_nm, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
											if(n == 0) {
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"\" data-matnr=\"\">%s > %s</a><div class=\"num\">%s</div></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), ma_nm, sa_nm, StringUtil.toComma(esList.size())));
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx()));
											}else {
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx()));												
											}
										}
									}
									break;
								case "ig_nm":
									for(String ig_cd : ar_ig_cd) {
										String[] keys = ig_cd.split("[|]");
										List<ESInfo> esList = source_list.stream().filter(a -> a.getMa_cd().equals(keys[0]) && a.getSa_cd().equals(keys[1]) && a.getIg_cd().equals(keys[2])).collect(Collectors.toList());
										for(int n=0; n<esList.size(); n++) {
											ESInfo esInfo = (ESInfo)esList.get(n);
											if("ENG".equals(session_lang)) {
												ma_nm = esInfo.getMa_nm();
												sa_nm = esInfo.getSa_nm();
											}else {
												if("DEU".equals(session_lang)) {
													ma_nm = esInfo.getMa_deu();
													sa_nm = esInfo.getSa_deu();
												}else if("FRA".equals(session_lang)) {
													ma_nm = esInfo.getMa_fra();
													sa_nm = esInfo.getSa_fra();
												}else if("KOR".equals(session_lang)) {
													ma_nm = esInfo.getMa_kor();
													sa_nm = esInfo.getSa_kor();
												}else if("POR".equals(session_lang)) {
													ma_nm = esInfo.getMa_por();
													sa_nm = esInfo.getSa_por();
												}else if("RUS".equals(session_lang)) {
													ma_nm = esInfo.getMa_rus();
													sa_nm = esInfo.getSa_rus();
												}else if("SPA".equals(session_lang)) {
													ma_nm = esInfo.getMa_spa();
													sa_nm = esInfo.getSa_spa();
												}else if("THA".equals(session_lang)) {
													ma_nm = esInfo.getMa_tha();
													sa_nm = esInfo.getSa_tha();
												}else if("TUR".equals(session_lang)) {
													ma_nm = esInfo.getMa_tur();
													sa_nm = esInfo.getSa_tur();
												}else if("VIE".equals(session_lang)) {
													ma_nm = esInfo.getMa_vie();
													sa_nm = esInfo.getSa_vie();
												}else if("ZHO".equals(session_lang)) {
													ma_nm = esInfo.getMa_zho();
													sa_nm = esInfo.getSa_zho();
												}
											}
											String ig_nm = esInfo.getIg_nm();
											ig_nm = StringUtil.replace(ig_nm, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
											if(n == 0) {
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"\">%s > %s > %s</a><div class=\"num\">%s</div></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), ma_nm, sa_nm, ig_nm, StringUtil.toComma(esList.size())));
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, ig_nm, esInfo.getMaktx()));
											}else {
												sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, ig_nm, esInfo.getMaktx()));												
											}
										}
									}
									break;
								case "maktx":
									for(int n=0; n<source_list.size(); n++) {
										ESInfo esInfo = (ESInfo)source_list.get(n);
										if("ENG".equals(session_lang)) {
											ma_nm = esInfo.getMa_nm();
											sa_nm = esInfo.getSa_nm();
										}else {
											if("DEU".equals(session_lang)) {
												ma_nm = esInfo.getMa_deu();
												sa_nm = esInfo.getSa_deu();
											}else if("FRA".equals(session_lang)) {
												ma_nm = esInfo.getMa_fra();
												sa_nm = esInfo.getSa_fra();
											}else if("KOR".equals(session_lang)) {
												ma_nm = esInfo.getMa_kor();
												sa_nm = esInfo.getSa_kor();
											}else if("POR".equals(session_lang)) {
												ma_nm = esInfo.getMa_por();
												sa_nm = esInfo.getSa_por();
											}else if("RUS".equals(session_lang)) {
												ma_nm = esInfo.getMa_rus();
												sa_nm = esInfo.getSa_rus();
											}else if("SPA".equals(session_lang)) {
												ma_nm = esInfo.getMa_spa();
												sa_nm = esInfo.getSa_spa();
											}else if("THA".equals(session_lang)) {
												ma_nm = esInfo.getMa_tha();
												sa_nm = esInfo.getSa_tha();
											}else if("TUR".equals(session_lang)) {
												ma_nm = esInfo.getMa_tur();
												sa_nm = esInfo.getSa_tur();
											}else if("VIE".equals(session_lang)) {
												ma_nm = esInfo.getMa_vie();
												sa_nm = esInfo.getSa_vie();
											}else if("ZHO".equals(session_lang)) {
												ma_nm = esInfo.getMa_zho();
												sa_nm = esInfo.getSa_zho();
											}
										}
										String maktx = esInfo.getMaktx();
										maktx = StringUtil.replace(maktx, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
										sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), maktx));
									}
									break;
								case "matnr":
									for(int n=0; n<source_list.size(); n++) {
										ESInfo esInfo = (ESInfo)source_list.get(n);
										if("ENG".equals(session_lang)) {
											ma_nm = esInfo.getMa_nm();
											sa_nm = esInfo.getSa_nm();
										}else {
											if("DEU".equals(session_lang)) {
												ma_nm = esInfo.getMa_deu();
												sa_nm = esInfo.getSa_deu();
											}else if("FRA".equals(session_lang)) {
												ma_nm = esInfo.getMa_fra();
												sa_nm = esInfo.getSa_fra();
											}else if("KOR".equals(session_lang)) {
												ma_nm = esInfo.getMa_kor();
												sa_nm = esInfo.getSa_kor();
											}else if("POR".equals(session_lang)) {
												ma_nm = esInfo.getMa_por();
												sa_nm = esInfo.getSa_por();
											}else if("RUS".equals(session_lang)) {
												ma_nm = esInfo.getMa_rus();
												sa_nm = esInfo.getSa_rus();
											}else if("SPA".equals(session_lang)) {
												ma_nm = esInfo.getMa_spa();
												sa_nm = esInfo.getSa_spa();
											}else if("THA".equals(session_lang)) {
												ma_nm = esInfo.getMa_tha();
												sa_nm = esInfo.getSa_tha();
											}else if("TUR".equals(session_lang)) {
												ma_nm = esInfo.getMa_tur();
												sa_nm = esInfo.getSa_tur();
											}else if("VIE".equals(session_lang)) {
												ma_nm = esInfo.getMa_vie();
												sa_nm = esInfo.getSa_vie();
											}else if("ZHO".equals(session_lang)) {
												ma_nm = esInfo.getMa_zho();
												sa_nm = esInfo.getSa_zho();
											}
										}
										String matnr = esInfo.getMatnr();
										matnr = StringUtil.replace(matnr, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
										sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s(%s)</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx(), matnr));
									}
									break;
								case "designation":
									for(int n=0; n<source_list.size(); n++) {
										ESInfo esInfo = (ESInfo)source_list.get(n);
										if("ENG".equals(session_lang)) {
											ma_nm = esInfo.getMa_nm();
											sa_nm = esInfo.getSa_nm();
										}else {
											if("DEU".equals(session_lang)) {
												ma_nm = esInfo.getMa_deu();
												sa_nm = esInfo.getSa_deu();
											}else if("FRA".equals(session_lang)) {
												ma_nm = esInfo.getMa_fra();
												sa_nm = esInfo.getSa_fra();
											}else if("KOR".equals(session_lang)) {
												ma_nm = esInfo.getMa_kor();
												sa_nm = esInfo.getSa_kor();
											}else if("POR".equals(session_lang)) {
												ma_nm = esInfo.getMa_por();
												sa_nm = esInfo.getSa_por();
											}else if("RUS".equals(session_lang)) {
												ma_nm = esInfo.getMa_rus();
												sa_nm = esInfo.getSa_rus();
											}else if("SPA".equals(session_lang)) {
												ma_nm = esInfo.getMa_spa();
												sa_nm = esInfo.getSa_spa();
											}else if("THA".equals(session_lang)) {
												ma_nm = esInfo.getMa_tha();
												sa_nm = esInfo.getSa_tha();
											}else if("TUR".equals(session_lang)) {
												ma_nm = esInfo.getMa_tur();
												sa_nm = esInfo.getSa_tur();
											}else if("VIE".equals(session_lang)) {
												ma_nm = esInfo.getMa_vie();
												sa_nm = esInfo.getSa_vie();
											}else if("ZHO".equals(session_lang)) {
												ma_nm = esInfo.getMa_zho();
												sa_nm = esInfo.getSa_zho();
											}
										}
										String designation = esInfo.getDesignation();
										designation = StringUtil.replace(designation, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
										sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s(%s)</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx(), designation));
									}
									break;
								default: //grade
									for(int n=0; n<source_list.size(); n++) {
										ESInfo esInfo = (ESInfo)source_list.get(n);
										if("ENG".equals(session_lang)) {
											ma_nm = esInfo.getMa_nm();
											sa_nm = esInfo.getSa_nm();
										}else {
											if("DEU".equals(session_lang)) {
												ma_nm = esInfo.getMa_deu();
												sa_nm = esInfo.getSa_deu();
											}else if("FRA".equals(session_lang)) {
												ma_nm = esInfo.getMa_fra();
												sa_nm = esInfo.getSa_fra();
											}else if("KOR".equals(session_lang)) {
												ma_nm = esInfo.getMa_kor();
												sa_nm = esInfo.getSa_kor();
											}else if("POR".equals(session_lang)) {
												ma_nm = esInfo.getMa_por();
												sa_nm = esInfo.getSa_por();
											}else if("RUS".equals(session_lang)) {
												ma_nm = esInfo.getMa_rus();
												sa_nm = esInfo.getSa_rus();
											}else if("SPA".equals(session_lang)) {
												ma_nm = esInfo.getMa_spa();
												sa_nm = esInfo.getSa_spa();
											}else if("THA".equals(session_lang)) {
												ma_nm = esInfo.getMa_tha();
												sa_nm = esInfo.getSa_tha();
											}else if("TUR".equals(session_lang)) {
												ma_nm = esInfo.getMa_tur();
												sa_nm = esInfo.getSa_tur();
											}else if("VIE".equals(session_lang)) {
												ma_nm = esInfo.getMa_vie();
												sa_nm = esInfo.getSa_vie();
											}else if("ZHO".equals(session_lang)) {
												ma_nm = esInfo.getMa_zho();
												sa_nm = esInfo.getSa_zho();
											}
										}
										String grade = esInfo.getGrade();
										grade = StringUtil.replace(grade, searchinfo.getAuto_input_text(), String.format("<em class=\"blue\">%s</em>", searchinfo.getAuto_input_text()));
										sbAutoHtml.append(String.format("<li class=\"auto_li\"><a onclick=\"doAutoSearch(this)\" style=\"cursor:pointer\" data-macd=\"%s\" data-sacd=\"%s\" data-igcd=\"%s\" data-matnr=\"%s\">%s > %s > %s > %s(%s)</a></li>",  esInfo.getMa_cd(), esInfo.getSa_cd(), esInfo.getIg_cd(), esInfo.getMatnr(), ma_nm, sa_nm, esInfo.getIg_nm(), esInfo.getMaktx(), grade));
									}
									break;
							}
						}
					}
				}
			}
		}
		json_return.put("html", sbAutoHtml.toString());
		json_return.put("total_cnt", sbAutoHtml.length());
		return json_return;
		
	}
	
	/**
	 * 파일다운로드
	 * @param request
	 * @param response
	 * @return modelAndView
	 * @throws UserSysException
	 * @throws BizException 
	 * @throws IOException
	 */
	@RequestMapping(value = ConstUrl.SEARCH_URL, params = ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_FILE)
	@ResponseBody
	public void goMsContractFileDown(HttpServletRequest request, HttpServletResponse response, SearchInfo info) throws UserSysException {

		String atch = info.getAtch_file_url();
		
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(atch, info.getAtch_file_name(), request);

	}
	
	/**
	 * 엑셀 출력
	 * @param request
	 * @param response
	 * @param searchInfo
	 * @param info
	 * @throws Exception,UserSysException 
	 */
	@RequestMapping(value=ConstUrl.SEARCH_URL, params=ConstKey.ACTION_ID + "=" + ConstKey.ACTION_GET_EXCEL)
	@ResponseBody
	public void doExcelDown(HttpServletRequest request, HttpServletResponse response, SearchInfo searchInfo) throws Exception,UserSysException {

		// 패스 및 파일명 설정
		HttpSession session = request.getSession();
		String path = ConfigMng.getValue(IPropertyKey.FILE_UPLOAD_ROOT);
		String session_lang = ConfigMng.getLang(request);
		String destDir = session.getServletContext().getRealPath(path);
		String thisTime = DateTimeUtil.getDateTimeByPattern("yyyyMMddHHmm");
		String uploadPath = destDir + System.getProperty("file.separator") + "excel" + System.getProperty("file.separator") + thisTime + System.getProperty("file.separator");
		
		String filename = "";
		
		filename = "KOROY_ECAT_SEARCH_RESULT" + ConstKey.SEP_UNDER + thisTime;
		
		String file = filename + ".xls";
		
		List<HashMap> excelList = new ArrayList<HashMap>();
		int nPage = 1;
		
		//검색조건
		searchInfo.setIsPageYn(ConstKey.KEY_N);
		
		List<SearchInfo> item_list = null;
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchInfo.setInch_use_yn(inch_use_yn);
		if(!"".equals(searchInfo.getGtc_vd_id())) {
			MakeFilterSQL(searchInfo);
			item_list = searchService.selectParaSearchItemList(searchInfo);
		}else {
			item_list = searchService.selectSearchItemList(searchInfo);	
		}		
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		
		if(item_list != null && item_list.size() > 0) {
			for (int index = 0; index < item_list.size(); index ++) {
				SearchInfo info = (SearchInfo)item_list.get(index);
				List data = new ArrayList();
				data.add(info.getMatnr());
				if(ConstKey.KEY_Y.equals(info.getNew_yn())) {
					data.add("NEW");	
				}else {
					data.add("");
				}
				data.add(info.getDesignation());
				data.add(info.getGrade());
				data.add(info.getIg_nm());				
				dataList.add(data);
			}
		}
		
		// 타이틀
		String title = "Search Result";
		// 컬럼명
		List<List<String>> colList = new ArrayList<List<String>>();
		List<String> col = new ArrayList<String>();
		// Order No
		col.add(LangMng.LANG_D("FR000038", session_lang));
		// New
		col.add(LangMng.LANG_D("FR000030", session_lang));
		// Designation
		col.add(LangMng.LANG_D("FR000039", session_lang));
		// Grade
		col.add(LangMng.LANG_D("FR000041", session_lang));
		// Item Group
		col.add(LangMng.LANG_D("FR000031", session_lang));
		
		colList.add(col);
		
		List<List<String>> footerList = null;
		
		HashMap map = new HashMap();
		map.put("title", title);
		map.put("colList", colList);
		map.put("dataList", dataList);
		map.put("footerList", footerList);
		
		excelList.add(map);
		
		// Excel 생성
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.download(uploadPath, file, file, excelList);
		
		// 파일의 전체경로 설정
		StringBuffer sbFileName = new StringBuffer("");
		sbFileName.append(uploadPath);
		sbFileName.append(file);
		
		// 파일 다운로드 실행
		FileDownload fileDownload = new FileDownload(response);
		fileDownload.download(sbFileName.toString(), file, request);
		
		// 다운로드 후에 임시생성한 다운로드 파일 삭제
		FileUtil.deleteFile(sbFileName.toString());		
		
	}
	
	private void MakeFilterSQL(SearchInfo searchinfo) {
		// 필터 검색조건 SQL 생성
		String sql = "";
		if(!"".equals(searchinfo.getFilterParam())) {
			JSONArray jsonFilter = JSONArray.fromObject(searchinfo.getFilterParam());
			for(int n=0; n<jsonFilter.size(); n++) {
				JSONObject jo = (JSONObject)jsonFilter.get(n);
				if(!jo.containsKey("key") || !jo.containsKey("type") || !jo.containsKey("value")) {
					continue;
				}
				String prop = jo.getString("key");
				String sch_type = jo.getString("type");
				String value = jo.getString("value");
				if(!"".equals(value)) {
					if("IN".equals(sch_type) || "SB".equals(sch_type)) {  
						sql = sql + String.format(" AND(SYMBOL='%s'", prop);
						sql = sql + String.format(" AND VAL='%s')", value);
					}else if(sch_type.contains("CE")) {
						if("CE1".equals(sch_type)) {
							sql = sql + String.format(" AND(SYMBOL='%s'", prop);
							sql = sql + String.format(" AND CONVERT(float,VAL)<=CONVERT(float, '%s'))", value);
						}else if("CE2".equals(sch_type)) {
							String[] arVal = value.split("[|]");
							if(arVal.length > 0) {
								sql = sql + String.format(" AND(SYMBOL='%s'", prop);
								double val_1 = Double.parseDouble(arVal[0]);
								double val_2 = 0;
								if(arVal.length == 2) {
									val_2 = Double.parseDouble(arVal[1]);
									double startVal = val_1 - val_2;
									double endVal = val_1 - val_2;
									sql = sql + String.format(" AND CONVERT(float,VAL)<=CONVERT(float, '%f')", startVal);
									sql = sql + String.format(" AND CONVERT(float,VAL)>=CONVERT(float, '%f'))", endVal);
								}else {
									sql = sql + String.format(" AND CONVERT(float,VAL)=CONVERT(float, '%f'))", val_1);
								}
							}
						}else if("CE3".equals(sch_type)) {
							sql = sql + String.format(" AND(SYMBOL='%s'", prop);
							sql = sql + String.format(" AND CONVERT(float,VAL)>=CONVERT(float, '%s'))", value);
						}
					}
				}
			}
			searchinfo.setFilter_sch_sql(sql);
		}
	}
}
