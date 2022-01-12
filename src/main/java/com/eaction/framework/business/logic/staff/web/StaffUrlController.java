/*****************************************************************************
 * 프로그램명  : StaffUrlController.java
 * 설     명  : 스텝관리 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.04.24  KSH    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.staff.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.staff.constant.StaffConstKey;
import com.eaction.framework.business.logic.staff.constant.StaffConstUrl;
import com.eaction.framework.business.logic.staff.model.StaffInfo;
import com.eaction.framework.business.logic.staff.model.StaffSearchInfo;
import com.eaction.framework.business.logic.staff.service.StaffService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.User;

/**
 * 스텝 관리 리스트 controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class StaffUrlController {
	@Resource
	private StaffService staffService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	/**
	 * 스텝 관리 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_URL)
	@ResponseBody
	public ModelAndView goSelectStaffList(HttpServletRequest request, HttpServletResponse response, StaffSearchInfo searchinfo) {
		
		ModelAndView modelAndView = new ModelAndView(StaffConstUrl.JSP_STAFF_LIST_URL, ConstKey.SEARCH_DATA, searchinfo);

		return modelAndView;
		
	}
	
	/**
	 * 스텝 관리정보 입력 화면으로 이동
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_POP_ADD_URL)
	@ResponseBody
	public ModelAndView goInsertStaff(HttpServletRequest request, HttpServletResponse response, User info) throws UserSysException {
		//입력화면이동
 		ModelAndView modelAndView = new ModelAndView(StaffConstUrl.JSP_STAFF_ADD_URL);
		return modelAndView;
		
	}
	
	/**
	 * 스텝정보 수정 화면으로 이동
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.STAFF_MEMBER_POP_EDIT_URL)
	@ResponseBody
	public ModelAndView goUpdateStaff(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException {

		//조회, 갱신처리는 기본정보데이터 취득후 화면이동
		StaffInfo resultInfo = getStaffInfoData(info);
		
		ModelAndView modelAndView = new ModelAndView(StaffConstUrl.JSP_STAFF_EDIT_URL);
		modelAndView.addObject(StaffConstKey.STAFF_DETAIL_DATA, resultInfo);
		return modelAndView;
		
	}
	
	/**
	 * 스텝 조회 데이터 취득 처리 
	 * @param info 검색데이터
	 * @return StaffInfo 회사정보
	 * @throws UserSysException
	 */
	private StaffInfo getStaffInfoData(StaffInfo info) throws UserSysException {
		
		StaffInfo resultInfo = staffService.selectStaffDetail(info);
		
		return resultInfo;
		
	}
	
	/**
	 * 마이페이지 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=StaffConstUrl.MYPAGE_POP_UP_URL)
	@ResponseBody
	public ModelAndView goStaffMypage(HttpServletRequest request, HttpServletResponse response, StaffInfo info) throws UserSysException {
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute(ConstKey.USER_INFO);
		info.setEmp_id(user.getUser_id());
		
		//조회, 갱신처리는 기본정보데이터 취득후 화면이동
		StaffInfo resultInfo = getStaffInfoData(info);
		
		ModelAndView modelAndView = new ModelAndView(StaffConstUrl.JSP_MYPAGE_URL);
		modelAndView.addObject(StaffConstKey.STAFF_DETAIL_DATA, resultInfo);
		return modelAndView;
		
	}
	
}
