/*****************************************************************************
 * 프로그램명  : JqGridUtil.java
 * 설     명  : JqGrid Util 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/

package com.eaction.framework.common.jqgrid;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.common.util.PagingUtil;

public class JqGridUtil{
	static  String contentType = "text/json; charset=utf-8";
	
	public static HashMap getJsonJqGridData(JqGridVO jqGridInfo, PagingUtil listPage){
		HashMap hashMap = new HashMap();
		
		hashMap.put("page", jqGridInfo.getJqPage());
		hashMap.put("records", listPage.getTotalSize());
		if(listPage.getTotalSize()%jqGridInfo.getJqRows()==0){
			hashMap.put("total",listPage.getTotalSize()/jqGridInfo.getJqRows());
		}else{
			hashMap.put("total",listPage.getTotalSize()/jqGridInfo.getJqRows()+1);
		}
		hashMap.put("root",JSONArray.fromObject(listPage.getCurrList()));
		
		return hashMap;
	}
	
	public static ModelAndView getJsonData(JqGridVO jqGridInfo, PagingUtil listPage){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("page", jqGridInfo.getJqPage());
		modelAndView.addObject("records", listPage.getTotalSize());
		if(listPage.getTotalSize()%jqGridInfo.getJqRows()==0){
			modelAndView.addObject("total",listPage.getTotalSize()/jqGridInfo.getJqRows());
		}else{
			modelAndView.addObject("total",listPage.getTotalSize()/jqGridInfo.getJqRows()+1);
		}
		modelAndView.addObject("root",JSONArray.fromObject(listPage.getCurrList()));
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonData(JqGridVO jqGridInfo, PagingUtil listPage, JSONArray jArray){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("page", jqGridInfo.getJqPage());
		modelAndView.addObject("records", listPage.getTotalSize());
		if(listPage.getTotalSize()%jqGridInfo.getJqRows()==0){
			modelAndView.addObject("total",listPage.getTotalSize()/jqGridInfo.getJqRows());
		}else{
			modelAndView.addObject("total",listPage.getTotalSize()/jqGridInfo.getJqRows()+1);
		}
		modelAndView.addObject("root",jArray);
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonData(JqGridVO jqGridInfo, List listData,long total){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("page", jqGridInfo.getJqPage());
		modelAndView.addObject("records", total);
		if(total%jqGridInfo.getJqRows()==0){
			modelAndView.addObject("total",total/jqGridInfo.getJqRows());
		}else{
			modelAndView.addObject("total",total/jqGridInfo.getJqRows()+1);
		}
		modelAndView.addObject("root",JSONArray.fromObject(listData));
		
		return modelAndView;
	}
	
	
	
	public static ModelAndView getJsonData(JqGridVO jqGridInfo, List listData){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("page", jqGridInfo.getJqPage());
		modelAndView.addObject("records", listData.size());
		modelAndView.addObject("root",JSONArray.fromObject(listData));
		
		return modelAndView;
	}
	
	public static ModelAndView getJsonDataView(List listData){
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType(contentType);
		
		modelAndView.addObject("root",JSONArray.fromObject(listData));
		
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
}