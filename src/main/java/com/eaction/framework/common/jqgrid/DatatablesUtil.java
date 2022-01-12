/*****************************************************************************
 * 프로그램명  : DatatablesUtil.java
 * 설     명  : Datatables Util 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/

package com.eaction.framework.common.jqgrid;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.common.util.PagingUtil;

public class DatatablesUtil{
	static  String contentType = "text/json; charset=utf-8";
	
	public static HashMap getJsonDatatablesData(DatatablesVO datatablesInfo, PagingUtil listPage){
		HashMap hashMap = new HashMap();
		
		hashMap.put("iTotalRecords", listPage.getTotalSize());
		hashMap.put("iTotalDisplayRecords", listPage.getTotalSize());
		hashMap.put("root",JSONArray.fromObject(listPage.getCurrList()));
		
		return hashMap;
	}
	
	public static ModelAndView getJsonData(DatatablesVO datatablesInfo, PagingUtil listPage){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("iTotalRecords", listPage.getTotalSize());
		modelAndView.addObject("iTotalDisplayRecords", listPage.getTotalSize());
		modelAndView.addObject("aaData",JSONArray.fromObject(listPage.getCurrList()));
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonData(DatatablesVO datatablesInfo, PagingUtil listPage, JSONArray jArray){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("iTotalRecords", listPage.getTotalSize());
		modelAndView.addObject("iTotalDisplayRecords", listPage.getTotalSize());
		modelAndView.addObject("aaData",jArray);
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonData(DatatablesVO datatablesInfo, List listData,long total){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("iTotalRecords", total);
		modelAndView.addObject("iTotalDisplayRecords", total);
		modelAndView.addObject("aaData",JSONArray.fromObject(listData));
		
		return modelAndView;
	}
	
	
	
	public static ModelAndView getJsonData(DatatablesVO datatablesInfo, List listData){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("iTotalRecords", listData.size());
		modelAndView.addObject("iTotalDisplayRecords", listData.size());
		modelAndView.addObject("aaData",JSONArray.fromObject(listData));
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonDataView(List listData){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("aaData",JSONArray.fromObject(listData));
		
		return modelAndView;
	}
	
	public static JSONArray getJsonData(PagingUtil listPage){
		JSONArray jsonArray = new JSONArray();
		if(listPage != null){
			jsonArray = JSONArray.fromObject(listPage.getCurrList());
		}
		
		return jsonArray;
	}
	
	public static JSONArray getJsonData(List listData){
		JSONArray jsonArray = new JSONArray();
		if(listData != null){
			jsonArray = JSONArray.fromObject(listData);
		}
		
		return jsonArray;
	}
	
	public static void setSortCol(HttpServletRequest request, DatatablesVO searchInfo){
		String sortCol = request.getParameter("columns["+searchInfo.getSortColNum()+ "][data]");
	    searchInfo.setSortCol(sortCol);
	}
}