/*****************************************************************************
 * 프로그램명  : RightwrapMenuController.java
 * 설     명  : 상단팝업 제어 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.08.03  YJI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eaction.framework.business.common.code.service.CodeService;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.logic.basket.model.BasketInfo;
import com.eaction.framework.business.logic.basket.service.BasketService;
import com.eaction.framework.business.logic.user.service.UserService;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;

/**
 * 상단팝업 제어 
 * @author  eaction
 * @version 1.0
 */
@Controller
public class RightwrapMenuController {

	// 국적,단위,통화 코드인포리스트 조회
	@Resource
	private UserService userService;
	@Resource
	private BasketService basketService;
	@Resource
	private CodeService codeService;
	
    /**
     * 상단팝업 표시처리 
     * @param request 요청
     * @param response 응답
     * @return ModelAndView 뷰천이처리객체 
     */
	@RequestMapping(value = ConstUrl.RIGHT_WRAP_URL)
	public ModelAndView goController(HttpServletRequest request, HttpServletResponse response, Device device) throws UserSysException {
        ModelAndView modelAndView = new ModelAndView();
        
        List<CodeInfo> tmp_nationList = userService.selectUserNationCodeList();
        List<CodeInfo> unitList = userService.selectUserUnitCodeList();
        List<CodeInfo> currencyList = userService.selectUserCurrencyCodeList();
        List<CodeInfo> tmp_languageList = userService.selectUserLangCodeList();
        List<CodeInfo> empByNationCdList = userService.selectEmpbyNationCdList();

        //장바구니 개수 취득을 위한 userInfo 취득
		HttpSession session = request.getSession(true);
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		
		String comm_nation = (String)session.getAttribute(ConstKey.SESSION_NATION);
 		String comm_lang = ConfigMng.getLang(request);
 		String comm_size_unit = (String)session.getAttribute(ConstKey.SESSION_SIZE_UNIT);
 		String comm_curr = (String)session.getAttribute(ConstKey.SESSION_CURRENCY);
 		// 언어코드 재정렬
        List<CodeInfo> languageList = new ArrayList<CodeInfo>();
		if("KOR".equals(comm_lang)) {
			tmp_languageList.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_k)).forEach(
		            s -> languageList.add(s)
		    );
		}else {
			tmp_languageList.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_e)).forEach(
					s -> languageList.add(s)
		    );
		}
		// 국가코드 재정렬
        List<CodeInfo> nationList = new ArrayList<CodeInfo>();
		if("KOR".equals(comm_lang)) {
			tmp_nationList.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_k)).forEach(
		            s -> nationList.add(s)
		    );
		}else {
			tmp_nationList.stream().sorted(Comparator.comparing(CodeInfo::getCodenm_e)).forEach(
					s -> nationList.add(s)
		    );
		}
		
		int cartCnt = 0;
		if(userInfo !=null){
			BasketInfo basketInfo = new BasketInfo();
			basketInfo.setUser_id(userInfo.getUser_id());
			cartCnt = basketService.selectBasketListCnt(basketInfo);
			
 			// 세션에 설정된 국가가 없고 로그인유저이면 유저의 국가로 설정
 			if("".equals(comm_nation)) {
 				comm_nation = userInfo.getUser_nation();
 				// 로그인유저의 국가가 없으면 KOR로 설정
 				if("".equals(comm_nation)) {
 					comm_nation = "KOR";
 				}
 			}

 			// 세션에 설정된 길이단위가 없고 로그인유저이면 유저의 길이단위로 설정
 			if("".equals(comm_size_unit)) {
 				comm_size_unit = userInfo.getUnit_cd();
 				// 로그인유저의 언어가 없으면 Metric으로 설정
 				if("".equals(comm_size_unit)) {
 					comm_size_unit = "CC0001"; 					
 				}
 			}
 			
 			// 세션에 설정된 통화단위가 없고 로그인유저이면 유저의 통화단위로 설정
 			if("".equals(comm_curr)) {
 				comm_curr = userInfo.getCurr_cd();
 				// 로그인유저의 언어가 없으면 USD로 설정
 				if("".equals(comm_curr)) {
 					comm_curr = "CC0077";
 				}
 			}
		}else {
 			// 세션에 설정된 국가가 없으면 KOR로 설정
 			if("".equals(comm_nation)) {
 				if("".equals(comm_nation)) {
 					comm_nation = "KOR";
 				}
 			}
 			
			// 로그인유저의 언어가 없으면 Metric으로 설정
			if("".equals(comm_size_unit)) {
				comm_size_unit = "CC0001";
			}
 			
			// 로그인유저의 언어가 없으면 USD로 설정
			if("".equals(comm_curr)) {
				comm_curr = "CC0077";
			}
		}
        String deviceType = "";
        if(device.isNormal()) {
        	deviceType = "P";
        }else {
        	deviceType = "M";
        }
        List<CodeInfo> nation_list = codeService.getNationCodeInfo();
//        modelAndView.addObject(ConstKey.RESULT_DATA);
        modelAndView.addObject("nationList", nationList);
        modelAndView.addObject("unitList", unitList);
        modelAndView.addObject("currencyList", currencyList);
        modelAndView.addObject("languageList", languageList);
        modelAndView.addObject("empByNationCdList", empByNationCdList);
        modelAndView.addObject("cartCnt", cartCnt);
        modelAndView.addObject("deviceType", deviceType);
        modelAndView.addObject("nation_list", nation_list);
        modelAndView.addObject("comm_nation", comm_nation);
        modelAndView.addObject("comm_lang", comm_lang);
		modelAndView.addObject("comm_size_unit", comm_size_unit);
		modelAndView.addObject("comm_curr", comm_curr);
        modelAndView.setViewName(ConstUrl.RIGHT_WRAP_JSP_URL);
		
		return modelAndView;
	}
}
