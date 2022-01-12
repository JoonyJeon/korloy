/*****************************************************************************
 * 프로그램명  : ProcessResultForJson.java
 * 설     명  : 처리결과Json
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23    LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.model;

import net.sf.json.JSONObject;
import net.sf.json.spring.web.servlet.view.JsonView;

import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.OtherCodeTableMng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

public class ProcessResultForJson extends Exception {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(ProcessResultForJson.class);    
	private int code;
	private String ret;
	private String errMessage;

	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	public ProcessResultForJson(int code, String errMessage) {
		this.code = code;
		this.errMessage = errMessage;
	}

	@Override
	public String getMessage() {
		if( this.code < 0){
			//실패
			this.ret = messageSourceAccessor.getMessage("MSG.FAIL");
		}else{
			//성공
			this.ret = messageSourceAccessor.getMessage("MSG.SUCCESS");
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"code\": \"" + Integer.toString(this.code) + "\",");
		sb.append("\"result\": \""+ this.ret +"\",");
		sb.append("\"resultMsg\": \"" + this.errMessage.replaceAll("\"", "'") + "\"");
		sb.append("}");
		
//		logger.debug(sb.toString());
		
		return sb.toString();
	}
	
	public JSONObject getJsonMessage() {
		return JSONObject.fromObject(getMessage());
	}
	
	public ModelAndView getModelAndView() {
		JsonView jsonView = new JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(jsonView);
		jsonView.setContentType("text/json; charset=utf-8");
		
		if( this.code < 0){
			// http request 400번을 본따서... -400....ㅜㅜ by bestheroz 130809
			if(this.code == -400){
				//잘못된 입력항목
				this.ret = messageSourceAccessor.getMessage("MSG.ALERT.WRONG_INPUT");	
			}else{
				//실패
				this.ret = messageSourceAccessor.getMessage("MSG.FAIL");	
			}
		}else{
			//성공
			this.ret = messageSourceAccessor.getMessage("MSG.SUCCESS");
		}
		
		modelAndView.addObject("code", String.valueOf(this.code));
		modelAndView.addObject("result", this.ret);
		modelAndView.addObject("resultMsg", this.errMessage.replaceAll("\"", "'"));
		
		return modelAndView;
	}
}