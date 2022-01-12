/*****************************************************************************
 * 프로그램명  : AssemblyUrlController.java
 * 설     명  : Assembly 화면 이동 컨트롤러 controller-layer class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.09.01  왕윤아    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.assembly.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.assembly.constant.AssemblyConstUrl;
import com.eaction.framework.business.logic.assembly.model.AssemblyInfo;
import com.eaction.framework.business.logic.assembly.service.AssemblyService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;

import net.sf.json.JSONObject;

/**
 * AssemblyUrlController controller-layer class.
 * @author eaction
 *@version 1.0
 */
@Controller
public class AssemblyUrlController {
	@Resource
	private AssemblyService assemblyService;
	@Autowired
	MessageSourceAccessor MessageSourceAccessor;
	
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(AssemblyUrlController.class); 
    
	/**
	 * Assembly 화면
	 * @param request Request객체
	 * @param response Response객체
	 * @exception UserSysException 시스템예외
	 */
	@RequestMapping(value=ConstUrl.ASSEMBLY_URL)
	@ResponseBody
	public ModelAndView goAssemblyList(HttpServletRequest request, HttpServletResponse response, Device device, AssemblyInfo info) throws UserSysException {
		
		// 언어가 설정되지 않았으면 설정해서 표시
		HttpSession session = request.getSession(true);
		String session_lang = ConfigMng.getLang(request);
		
		String deviceType="";
		
		//디바이스 종류 전달
		if(device.isMobile()){
			deviceType="M";
		}else if(device.isTablet()){
			deviceType="T";
		}else{
			deviceType="P";
		}
		
		logger.debug("DEVICE TYPE :: "+deviceType);
		
		ModelAndView modelAndView = new ModelAndView(AssemblyConstUrl.JSP_ASSEMBLY_URL);
		modelAndView.addObject("deviceType", deviceType);
		modelAndView.addObject("item_assem_no", info.getAssem_no());
		return modelAndView;
		
	}
	
}
