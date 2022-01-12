/*****************************************************************************
 * 프로그램명  : JsonUtil.java
 * 설     명  : Ajax 처리 결과 Return Json 데이터 관련 Util
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author      Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.04  LYS    1.0
 *****************************************************************************/

package com.eaction.framework.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.web.servlet.ModelAndView;

public class AjaxJsonUtil{
	static  String contentType = "text/json; charset=utf-8";
	
	/**
	 * Ajax처리 결과를 Json 데이터로로 취득함
	 * @param resCode 결과코드
	 * @param resMsg 결과메세지
	 * @return JSONObject 결과Json데이터
	 */
	public static JSONObject getReturnJson(String resCode, String resMsg){
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("resCode", resCode);
		jsonObject.put("resMsg", resMsg);
		
		return jsonObject;
	}
	
	/**
	 * Ajax처리 결과를 Tree Json 데이터로로 취득함
	 * @param strText 노드 명
	 * @param strId 노드 아이디
	 * @return JSONObject 결과Json데이터
	 */
	public static JSONObject getReturnTreeJson(String strId, String strText){
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("id", strId);
		jsonObject.put("text", strText);
		
		return jsonObject;
	}
	
	/**
	 * Ajax처리 결과를 Json 데이터로로 취득함
	 * @param resCode 결과코드
	 * @param resMsg 결과메세지
	 * @param obj 결과데이터
	 * @return JSONObject 결과Json데이터
	 */
	public static JSONObject getReturnJson(String resCode, String resMsg, Object obj){
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("resCode", resCode);
		jsonObject.put("resMsg", resMsg);
		jsonObject.put("data", obj);
		
		return jsonObject;
	}
	
	public static JSONArray getJsonData(List listData){
		JSONArray jsonArray = new JSONArray();
		if(listData != null){
			jsonArray = JSONArray.fromObject(listData);
		}
		
		return jsonArray;
	}
	
	/**
	 * JSONArray 의 Value값으로 정렬한다.
	 * @param JSONArray
	 * @return Value값으로 정렬된 JSONArray
	 */
	public static JSONArray getJsonValueSort(JSONArray jsonArr){
	    JSONArray sortedJsonArray = new JSONArray();

	    List<JSONObject> jsonValues = new ArrayList<JSONObject>();
	    for (int i = 0; i < jsonArr.size(); i++) {
	        jsonValues.add(jsonArr.getJSONObject(i));
	    }
	    Collections.sort( jsonValues, new Comparator<JSONObject>() {
	        //You can change "Name" with "ID" if you want to sort by ID
	        private static final String KEY_NAME = "Name";

	        public int compare(JSONObject a, JSONObject b) {
	            String valA = new String();
	            String valB = new String();

	            try {
	                valA = (String) a.get(KEY_NAME);
	                valB = (String) b.get(KEY_NAME);
	            } 
	            catch (JSONException e) {
	                //do something
	            }

	            return valA.compareTo(valB);
	            //if you want to change the sort order, simply use the following:
	            //return -valA.compareTo(valB);
	        }
	    });

	    for (int i = 0; i < jsonArr.size(); i++) {
	        sortedJsonArray.add(jsonValues.get(i));
	    }
	    
	    return sortedJsonArray;
	}
}