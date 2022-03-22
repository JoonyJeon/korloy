/*****************************************************************************
 * 프로그램명  : AppMultiController.java
 * 설     명  : Application 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.app.constant.AppConstUrl;
import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.business.logic.app.model.AppSearchInfo;
import com.eaction.framework.business.logic.app.service.AppService;
import com.eaction.framework.business.logic.app.constant.AppConstKey;
import com.eaction.framework.business.logic.app.constant.AppConstUrl;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.jqgrid.JqGridUtil;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.AjaxJsonUtil;
import com.eaction.framework.common.util.PagingUtil;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Application 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class AppMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(AppMultiController.class);    

	@Resource
	private AppService appService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	
	
	
	/**
	 * Main Application List 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_SUB_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_MAIN_APP_LIST)
	@ResponseBody
	public JSONArray getMainApplicationList(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo) throws UserSysException{
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		List<AppInfo> main_list = appService.selectMainApplicationList(searchinfo);
		JSONArray result = AjaxJsonUtil.getJsonData(main_list);
		
		return result;
		
	}
	
	/**
	 * Sub Application List 조회
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_SUB_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_SUB_APP_LIST)
	@ResponseBody
	public JSONObject getSubApplicationList(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo) throws UserSysException{
		
		JSONObject result = new JSONObject();
		
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		
		List<AppInfo> sub_list = appService.selectSubApplicationList(searchinfo);
		String session_lang = ConfigMng.getLang(request);
		for(AppInfo sub : sub_list) {
			String sa_nm = StringUtil.nvl(LangMng.LANG_D(sub.getSa_cd(), session_lang), sub.getSa_nm());
			sub.setSa_nm(sa_nm);
		}
		int total_cnt = appService.selectSubApplicationListCnt(searchinfo);
		JSONArray list = AjaxJsonUtil.getJsonData(sub_list);
		result.put("list", list);
		result.put("total_cnt", total_cnt);
		return result;
		
	}
	
	/**
	 * Item Group List 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_GRP_LIST_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ITEM_GROUP_LIST)
	@ResponseBody
	public JSONObject getItemGroupList(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo, Device device) throws UserSysException{
		JSONObject result = new JSONObject();
		MakeFilterSQL(searchinfo);
		//#########################################
		//Assembly에서 넘어오는 IG_CD
		if("".equals(searchinfo.getItem_view_yn()) && !"".equals(searchinfo.getAssem_ig_cd())) {
			String[] igCdArr = searchinfo.getAssem_ig_cd().split(",");
			searchinfo.setIg_cd_arr(igCdArr);
		}
		//#########################################
		String[] ar_sa_cd = null;
		if(!"".equals(searchinfo.getSa_cd())) {
			if(searchinfo.getSa_cd().substring(0, 1).equals(",")) {
				searchinfo.setSa_cd(searchinfo.getSa_cd().substring(1, searchinfo.getSa_cd().length()));
			}
			ar_sa_cd = searchinfo.getSa_cd().split(",");
		}
		String session_lang = ConfigMng.getLang(request);
		StringBuffer sbSA_SQL = new StringBuffer();
		StringBuffer sbItem_Cnt_SQL = new StringBuffer();
		if(ar_sa_cd != null && ar_sa_cd.length > 0) {
			sbSA_SQL.append(" AND EXISTS(");
			sbItem_Cnt_SQL.append(" AND EXISTS(");
			
			for(int nSql=0; nSql < ar_sa_cd.length; nSql++) {
				String sa_cd = ar_sa_cd[nSql];
				if(nSql > 0) {
					sbSA_SQL.append(" INTERSECT");	
				}
				sbSA_SQL.append(" SELECT 1");
				sbSA_SQL.append(" FROM TB_ECAT_CONT_SA SA_APP");
				sbSA_SQL.append(" WHERE IG_HIER.SA_CD = SA_APP.SA_CD");
				sbSA_SQL.append(" AND SA_APP.USE_YN = 'Y'");
				sbSA_SQL.append(String.format(" AND SA_APP.SA_CD = '%s'", sa_cd));
				
				if(nSql > 0) {
					sbItem_Cnt_SQL.append(" INTERSECT");	
				}
				sbItem_Cnt_SQL.append(" SELECT 1");
				sbItem_Cnt_SQL.append(" FROM TB_ECAT_CONT_IG_HIER IG_HIER");
				sbItem_Cnt_SQL.append(" JOIN   TB_ECAT_CONT_SA SA_APP ON(IG_HIER.SA_CD = SA_APP.SA_CD  AND SA_APP.USE_YN = 'Y')");
				sbItem_Cnt_SQL.append(" WHERE IT.IG_CD = IG_HIER.IG_CD");
				sbItem_Cnt_SQL.append(" AND IG_HIER.USE_YN = 'Y'");
				sbItem_Cnt_SQL.append(String.format(" AND SA_APP.SA_CD = '%s'", sa_cd));
			}
			sbSA_SQL.append(")");
			sbItem_Cnt_SQL.append(")");
		}
		searchinfo.setSa_multi_sql(sbSA_SQL.toString());
		searchinfo.setAr_sa_cd(ar_sa_cd);
		String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
		searchinfo.setInch_use_yn(inch_use_yn);
		List<AppInfo> item_grp_list = appService.selectItemGroupList(searchinfo);
		for(AppInfo igInfo : item_grp_list) {
			String desc = StringUtil.nvl(LangMng.LANG_D(igInfo.getIg_cd(), session_lang), igInfo.getIg_dscr());
			igInfo.setIg_dscr(desc);
		}
		int item_grp_list_cnt = appService.selectItemGroupListCnt(searchinfo);
		searchinfo.setSa_multi_sql(sbItem_Cnt_SQL.toString());
		int item_cnt = appService.selectSubApplicationOfItemCnt(searchinfo);
		
		
		JSONArray list = AjaxJsonUtil.getJsonData(item_grp_list);
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
		if(ConstKey.KEY_Y.equals(searchinfo.getIsSubAppChange())) {
			JSONArray jsonFilter = AjaxJsonUtil.getJsonData(resultfilterList);
			HttpSession session = request.getSession(true);
			String filterHtml = MakeFilterHtml(resultfilterList, device, session_lang);
			result.put("filterList", jsonFilter.toString());
			result.put("filterHtml", filterHtml);
		}
		result.put("cnt", item_grp_list_cnt);
		result.put("item_cnt", item_cnt);
		result.put("list", list);
		return result;
		
	}

	/**
	 * Item Group List 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.APP_ITEM_GRP_LIST_URL, params = ConstKey.ACTION_ID + "=" + AppConstKey.ACTION_ITEM_GROUP_FILTER)
	@ResponseBody
	public JSONObject getFilterList(HttpServletRequest request, HttpServletResponse response, AppInfo searchinfo, Device device) throws UserSysException{
		HttpSession session = request.getSession(true);
		String session_lang = (String)session.getAttribute(ConstKey.SESSION_LANG);

		JSONObject result = new JSONObject();
		String[] ar_sa_cd = searchinfo.getSa_cd().split(",");
		
		AppSearchInfo sch = new AppSearchInfo();
		sch.setSearch_ar_sa_cd(ar_sa_cd);
		List<AppInfo> filterList = appService.selectItemPropSymbolList(sch);
		List<AppInfo> duplChkfilterList = new ArrayList<AppInfo>(filterList);
		List<AppInfo> resultfilterList = new ArrayList<AppInfo>();
		for(AppInfo filterInfo : duplChkfilterList) {
			List<AppInfo> chkList = filterList.stream().filter(a -> a.getProp_iso().equals(filterInfo.getProp_iso())).collect(Collectors.toList());
			resultfilterList.add(chkList.get(0));
		}
		for(AppInfo filterInfo : resultfilterList) {
			if("SB".equals(filterInfo.getSch_typ())) {
				List<CodeInfo> cbList = appService.selectItemPropComboList(filterInfo);
				filterInfo.setCombo_list(cbList);
			}
		}

		return result;
		
	}
	
	private String MakeFilterHtml(List<AppInfo> filterList, Device device, String session_lang) {
		StringBuffer sbHtml = new StringBuffer();

		if (filterList != null && filterList.size() > 0) {
			for (int nFilter = 0; nFilter < filterList.size(); nFilter++) {
				AppInfo filterInfo = (AppInfo) filterList.get(nFilter);
				String sch_type = filterInfo.getSch_typ();
				// PC
				if (device.isNormal()) {
					sbHtml.append("<tr>");
					sbHtml.append(String.format("<td data-lang=\"%s\">%s<span>(%s)</span></td>" ,filterInfo.getProp_iso(), LangMng.LANG_D(filterInfo.getProp_iso(), session_lang), filterInfo.getProp_iso()));
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
								String.format("<input type=\"text\"  id=\"filter_%s_1\">",
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
				// 모바일
				}else {
					sbHtml.append("<li>");
					sbHtml.append(String.format("<span>%s<span>(%s)</span></span>", LangMng.LANG_D(filterInfo.getProp_iso(), session_lang), filterInfo.getProp_iso()));
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
			}
		}
		return sbHtml.toString();
	}
	
	private void MakeFilterSQL(AppInfo searchinfo) {
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
						if(prop.equals("FHA")) {
							sql = sql + String.format("AND (\r\n" + 
									"      convert(\r\n" + 
									"        float, \r\n" + 
									"        case when charindex('}{', val) > 0 then substring(\r\n" + 
									"          val, \r\n" + 
									"          2, \r\n" + 
									"          charindex('}{', val)-2\r\n" + 
									"        ) else case when left(val, 1) = '{' then substring(\r\n" + 
									"          val, \r\n" + 
									"          2, \r\n" + 
									"          len(val)-2\r\n" + 
									"        ) else '' end end\r\n" + 
									"      ) = '%s' \r\n" + 
									"      or convert(\r\n" + 
									"        float, \r\n" + 
									"        case when charindex('}{', val) > 0 then substring(\r\n" + 
									"          val, \r\n" + 
									"          2, \r\n" + 
									"          charindex('}{', val)-2\r\n" + 
									"        ) else '' end\r\n" + 
									"      ) = '%s'\r\n" + 
									"    )\r\n" + 
									"  )", value , "-"+value);	
						} else {
							sql = sql + String.format(" AND VAL='%s')", value);	
						}
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
