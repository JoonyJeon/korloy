/*****************************************************************************
 * 프로그램명  : StaffMultiController.java
 * 설     명  : 스텝관리 데이터처리 컨트롤러 (JSON데이터리턴) controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.staff.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.staff.constant.StaffConstKey;
import com.eaction.framework.business.logic.staff.constant.StaffConstUrl;
import com.eaction.framework.business.logic.staff.model.StaffInfo;
import com.eaction.framework.business.logic.staff.model.StaffSearchInfo;
import com.eaction.framework.business.logic.staff.service.StaffService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.jqgrid.JqGridUtil;
import com.eaction.framework.common.model.ProcessResult;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.PagingUtil;

import net.sf.json.JSONObject;

/**
 * 스텝 관리 처리  controller-layer class.
 * @author  eaction
 * @version 1.0
 */
@Controller
public class StaffMultiController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(StaffMultiController.class);    

	@Resource
	private StaffService staffService;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	/*
	 * 스텝 목록 조회
	 * 일단 목록 화면으로 이동하면 jqGrid내에 매핑된 URL 과 파라미터로 이곳으로 옴
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_URL, params = ConstKey.ACTION_ID + "=" + StaffConstKey.ACTION_STAFF_LIST)
	@ResponseBody
    public ModelAndView selectStaffList(HttpServletRequest request, HttpServletResponse response, StaffSearchInfo searchInfo) throws UserSysException{
    	ModelAndView modelAndView = null;
    	
    	//목록 취득
    	PagingUtil listPage = staffService.selectStaffList(searchInfo);
    	
		modelAndView = JqGridUtil.getJsonData(searchInfo, listPage);
		modelAndView.addObject(ConstKey.SEARCH_DATA, searchInfo);
		
		return modelAndView;
		
	}
	
	/**
	 * 스텝관리정보 입력 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_POP_ADD_URL, params = ConstKey.ACTION_ID + "=" + StaffConstKey.ACTION_STAFF_ADD_OK)
	@ResponseBody
    public String doInsertStaff(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
    	String exceptionMsg = "";

		try{
			bResult = staffService.insertStaff(info);
				
	   	}catch(Exception e){
	   		bResult = false;
	   		exceptionMsg = e.getMessage();
	   	}
		
		//insert 성공:0,  실패 1
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.INSERT.SUCCESS"));
	   	} else {
	   		jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL") + "\n\r" + exceptionMsg);
	   	}
		
   		return jsonObject.toString();
   		
	}
	
	/**
	 * 스텝정보 수정 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_POP_EDIT_URL, params = ConstKey.ACTION_ID + "=" + StaffConstKey.ACTION_STAFF_EDIT_OK)
	@ResponseBody
    public String doUpdateStaff(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
    	String exceptionMsg = "";
		
		try{
			bResult = staffService.updateStaff(info);
				
	   	}catch(Exception e){
	   		bResult = false;
	   		exceptionMsg = e.getMessage();
	   	}
		
		//insert 성공:0,  실패 1
		if(bResult){
			jsonObject.put("resCode", "0");
			jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.UPDATE.SUCCESS"));
	   	} else {
	   		jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL")+"\n\r"+exceptionMsg);
	   	}

   		return jsonObject.toString();
	}

	/**
	 * 마이페이지 정보 수정 처리
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.MYPAGE_POP_UP_URL, params = ConstKey.ACTION_ID + "=" + StaffConstKey.ACTION_MYPAGE_EDIT_OK)
	@ResponseBody
	public JSONObject doUpdateMypage(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException{
		
		JSONObject jsonObject = new JSONObject();
    	boolean bResult = true;
    	String exceptionMsg = "";
		
		try{
			StaffInfo userInfo = staffService.selectStaffDetail(info);
			if(userInfo.getPassword().equals(info.getPre_password())) {
				bResult = staffService.updateStaff(info);
				//세션의 유저정보 취득
				HttpSession session = request.getSession(true);
				User user = (User)session.getAttribute(ConstKey.USER_INFO);
				//유저 정보 변경
				user.setUser_nm(info.getEmpnm());
				//유저정보 세션에 설정
				session.setAttribute(ConstKey.USER_INFO, user);
				jsonObject.put("resCode", "0");
				jsonObject.put("resMsg", messageSourceAccessor.getMessage("MSG.UPDATE.SUCCESS"));
			}else {
				jsonObject.put("resCode", "1");
				jsonObject.put("resMsg",  "이전 비밀번호가 일치 하지 않습니다.");
			}			
	   	}catch(Exception e){
	   		bResult = false;
	   		exceptionMsg = e.getMessage();
	   		jsonObject.put("resCode", "1");
			jsonObject.put("resMsg",  messageSourceAccessor.getMessage("MSG.FAIL"));
	   	}
   		return jsonObject;

	}

	/**
	 * Staff 가입시 ID 중복체크  
	 * @param String id
	 * @return int 중복갯수
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_URL, params = ConstKey.ACTION_ID + "=" + StaffConstKey.ACTION_CHECK_STAFF_ID)
	@ResponseBody
	public String goCheckUserId(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException, UnsupportedEncodingException {
		   
		   JSONObject jsonObject = new JSONObject();
		   String userId = request.getParameter("id");
		   
		   userId = new String(userId.getBytes("UTF-8"),"EUC-JP");
		   
		   logger.debug(" incoding  :    "+request.getCharacterEncoding());
		   logger.debug("userId   :  "+userId);
		   
		   //갱신처리를 수행(OK:중복없음, NG:중복)1
		   String checkId = staffService.selectDuplicateStaffIdCnt(userId);
		   
	       //처리후 이동할 화면을 지정
		   jsonObject.put("checkId", checkId);
	   	   return jsonObject.toString();
	}		
	
	
}
