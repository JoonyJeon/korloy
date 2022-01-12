/*****************************************************************************
 * 프로그램명  : MemoryLoadMultiController.java
 * 설     명  : 메모리 로드 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.business.system.memory.web;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.code.UpCodeTableMng;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.business.system.memory.MemoryComponent;
import com.eaction.framework.business.system.memory.MemoryConstant;
import com.eaction.framework.business.system.memory.constant.MemoryLoadConstKey;
import com.eaction.framework.business.system.memory.constant.MemoryLoadConstUrl;


/**
 * 메모리 로드 controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class MemoryLoadMultiController{
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MemoryComponent memoryComponent;
	
	
	@Autowired
	private CodeTableMng codeTableMng;
	@Autowired
	private LangMng langMng;
	@Autowired
	private MenuMng menuMng;
		
//	/** 클래스 명칭 */
//	private final String CLASS_NAME = getClass().getName();
	/** 메소드명칭 */
	private String METHOD_NAME = "";
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * 메모리 리로드 관리 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SYSTEM_MEMORYLOAD_URL)
	@ResponseBody
    public ModelAndView goMemoryLoad(HttpServletRequest request, HttpServletResponse response) throws UserSysException{		
								
		ModelAndView modelAndView = new ModelAndView(MemoryLoadConstUrl.JSP_SYSTEM_MEMORY_RELOAD_URL);		
		
		return modelAndView;
	}
	
	/**
	 * 메모리 리로드 - 그룹 코드 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SYSTEM_MEM_RELOAD_URL)
	@ResponseBody
    public String doMemortReloadGrCode(HttpServletRequest request, HttpServletResponse response) throws UserSysException{
		
    	METHOD_NAME = "reloadCodeTableInfo";
    	
		JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
		
		try{
			codeTableMng.reloadCodeTableInfo();
			langMng.reloadTableInfo();
			memoryComponent.run(MemoryConstant.MEM_STATE_RELOAD);
				
	   	}catch(Exception e){
	   		bResult = false;
	   	}
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", "SUCCESS");
	   	} else {
	   		jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  "FAIL");
	   	}
		
   		return jsonObject.toString();
	}
	
	/**
	 * 메모리 리로드 - 메뉴 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.SYSTEM_MEMORYLOAD_URL, params = ConstKey.ACTION_ID + "=" + MemoryLoadConstKey.ACTION_RELOAD_MENU)
	@ResponseBody
    public String doMemortReloadMenu(HttpServletRequest request, HttpServletResponse response) throws UserSysException{
		
    	METHOD_NAME = "reloadMenuInfo";
    	
    	JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
		
		try{
			menuMng.reloadMenuInfo();
			memoryComponent.run(MemoryConstant.MEM_STATE_RELOAD);
				
	   	}catch(Exception e){
	   		bResult = false;
	   	}
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.SUCCESS"));
	   	} else {
	   		jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL"));
	   	}
		
   		return jsonObject.toString();
	}
    
}