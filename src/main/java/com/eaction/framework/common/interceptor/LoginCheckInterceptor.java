/*****************************************************************************
 * 프로그램명  : LoginCheckInterceptor.java
 * 설     명  : 로그인된 유효한 연결인지 체크 하는 처리(ACE-servlet에서 정의 됨)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.09  LYS      1.0
 *****************************************************************************/
package com.eaction.framework.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eaction.framework.business.common.code.service.CodeService;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.common.menu.MenuMng;
import com.eaction.framework.business.logic.basic.service.BasicService;
import com.eaction.framework.business.logic.login.service.LoginService;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.filter.RequestModifyParameter;
import com.eaction.framework.common.model.AuthInfo;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.MenuInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

//	/** 클래스명칭 */
//	private static final String CLASS_NAME = "LoginCheckInterceptor";
	/** 로그출력객체 */
	protected final Log logger = LogFactory.getLog(getClass());
	/** 처리성공시 이동 페이지 URL */
	protected String successCallPage = null;
	/** 에러발생시 이동할 페이지 URL */
	protected String errCallBackPage = ConstUrl.JSP_ERROR_URL;
	
	@Autowired
	private BasicService basicService;	
	@Autowired
	private CodeService codeService;	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Map paramMap = request.getParameterMap();
		String param = getParammetersString(paramMap);  //파라메타 조합
		
		StringBuffer url = request.getRequestURL(); //URL 주소 가져오기
		url.append(param);
		logger.info(url.toString());
		
		logger.debug(ConstKey.START_LOG);

 		HttpSession session = request.getSession(true);
 		 
 		
 		StringBuffer sbUrlBuffer = new StringBuffer();
 		
 		/*****/
 		//session.setMaxInactiveInterval(10);
 		/*****/

//        //로그인 해야만 접근가능한 곳에의 요청인 경우
//	    if (checkNeedLoginFromUrl(request)) {
//	    	//로그인 하지 않은경우는 로그인 화면으로 보낸다.
//	    	if (!AuthorityUtil.checkLogin(user)) {
//                //유저정보 삭제
//				session.removeAttribute(ConstKey.USER_INFO);
//				//세션무효화
//				session.invalidate();
//	    		//sbUrlBuffer.append(ConstUrl.LOGIN_URL);
//				sbUrlBuffer.append(ConstUrl.FORWARD_URL);
//	    		sbUrlBuffer.append(ConstKey.SEP_QUEST);
//	    		sbUrlBuffer.append(ConstKey.LOGIN_RESULT);
//	    		sbUrlBuffer.append(ConstKey.SEP_EQUAL);
//	    		sbUrlBuffer.append(ConstKey.LOGIN_NEED);
//	    		response.sendRedirect(sbUrlBuffer.toString());
//
//			    return false;
//	    	} else {
//	    		
//	    		if("YES".equals(ConfigMng.getValue(IPropertyKey.CHK_DUP_LOGIN))){
//	    			//중복로그인 방지
//		     		String sessionId = loginService.selectUserSessionId(user);
//		     		if(sessionId != null && !"".equals(sessionId)) {
//		     			if(!session.getId().equals(sessionId)) {
//		     				//유저정보 삭제
//		    				session.removeAttribute(ConstKey.USER_INFO);
//		    				//세션무효화
//		    				session.invalidate();
//		    				sbUrlBuffer.append(ConstUrl.FORWARD_URL);
//		    	    		sbUrlBuffer.append(ConstKey.SEP_QUEST);
//		    	    		sbUrlBuffer.append(ConstKey.LOGIN_RESULT);
//		    	    		sbUrlBuffer.append(ConstKey.SEP_EQUAL);
//		    	    		sbUrlBuffer.append(ConstKey.DUP_LOGIN);
//		    	    		response.sendRedirect(sbUrlBuffer.toString());
//
//		    			    return false;
//		     	 		}
//		     		}
//	    		}
//	    			    		
////	    		//어드민만 접근가능한 URL일경우
////	    		if (checkNeedAdminLoginFromUrl(request)) {
////	    			//어드민 권한이 아닌 경우 로그인 화면으로 이동
////	    			if (!AuthorityUtil.checkAdmin(user)) {
////                        //유저정보 삭제
////						session.removeAttribute(ConstKey.USER_INFO);
////						//세션무효화
////						session.invalidate();
////						//sbUrlBuffer.append(ConstUrl.LOGIN_URL);
////						sbUrlBuffer.append(ConstUrl.FORWARD_URL);
////						sbUrlBuffer.append(ConstKey.SEP_QUEST);
////						sbUrlBuffer.append(ConstKey.LOGIN_RESULT);
////						sbUrlBuffer.append(ConstKey.SEP_EQUAL);
////						sbUrlBuffer.append(ConstKey.LOGIN_ADMIN_NEED);
////			    		response.sendRedirect(sbUrlBuffer.toString());
////	    			    return false;
////	    			}
////	    		}
//	    		if(checkPermissionMenu(request,user)){
//	    			sbUrlBuffer.append(ConstUrl.FIRST_URL);
//		    		sbUrlBuffer.append(ConstKey.SEP_QUEST);
//		    		sbUrlBuffer.append(ConstKey.LOGIN_RESULT);
//		    		sbUrlBuffer.append(ConstKey.SEP_EQUAL);
//		    		sbUrlBuffer.append(ConstKey.LOGIN_ROLE);
//		    		response.sendRedirect(sbUrlBuffer.toString());
//	    			return false;
//	    		}	    		
//	    	}
//	    	
// 		}
	    
	    //로그인시 레벨별 메인 화면 처리시 받는 파라미터 처리
	    Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
	    String valMenuUrl = "";
	    if(flashMap != null){
	    	valMenuUrl = (String)flashMap.get("MENU_URL");
	    }
	    
	    String root 			= request.getParameter("root");
 		request.setAttribute("ROOT_MENU", root);
 		String menuUrl 	= StringUtil.nvl(request.getRequestURI());
 		//CDR 조회 통합은 뒤에 index_type이 붙어서 옴.
 		String indx_type 	= StringUtil.nvl(request.getParameter("index_type"));
 		String decorator 	= StringUtil.nvl(request.getParameter("decorator"));
 		//로그인시 URL 받은게 있으면  세팅
 		if(!"".equals(valMenuUrl)){
 			request.setAttribute("MENU_URL", valMenuUrl);	
 		}else{
 			if("".equals(menuUrl)){
 				menuUrl = request.getServletPath();
 			}
 			if(!"".equals(indx_type)){
 				menuUrl = menuUrl + "?index_type=" +indx_type;
 			}
 			if(!"".equals(decorator)){
 				menuUrl = menuUrl + "&decorator=" +decorator + "&confirm=true";
 			}
 			request.setAttribute("MENU_URL", menuUrl);
 		}
	
 		String locale = request.getLocale().toString();
 		String used_locale="";
 		
 		//지원하는 언어 아니면 모두 en 으로 설정
 		if(locale.contains("ko") || locale.contains("ja")|| locale.contains("en")){
 			Locale locale2 = new Locale(used_locale);
 			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request); 			
 			localeResolver.setLocale(request, response, locale2);
 		}else{
 			Locale locale2 = new Locale("en");
 			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request); 			
 			localeResolver.setLocale(request, response, locale2);
 		}
 		
 		//디바이스 종류 체크
 		String device = isDevice(request);
 		request.setAttribute("DEVICE", device);
 		
 		response.setHeader("Cache-Control","no-store");
 		response.setHeader("Pragma","no-cache");
 		response.setDateHeader("Expires",0);
 		if (request.getProtocol().equals("HTTP/1.1")){
 		    response.setHeader("Cache-Control", "no-cache");
 		}

 		// 사용자 권한을 가져와 셋팅한다.
 		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
 		String user_group = "";
 		RequestModifyParameter requestWrapper = new RequestModifyParameter((HttpServletRequest)request);
 		String login_user_id = "";
 		// 로그인사용자의 경우
 		if(userInfo != null && !"".equals(userInfo.getUser_id())) {
 			user_group = userInfo.getUser_grp();
 	        if(request.getParameter("login_user_id") == null || "".equals(request.getParameter("login_user_id"))) {
 	    	 	requestWrapper.setParameter("login_user_id", login_user_id);
 	    	}
 		// 일반접속자의 경우
 		}else {
 			user_group = "UG0005";
 		}
 		
 		AuthInfo authInfo = basicService.selectUserAuthInfo(user_group);
 		session.setAttribute(ConstKey.SESSION_AUTH, authInfo);
 		JSONObject json_auth = JSONObject.fromObject(authInfo);
 		session.setAttribute("json_auth", json_auth);
 		
 		String session_lang = ConfigMng.getLang(request);
 		String session_nation = ConfigMng.getNation(request);
 		String session_unit = ConfigMng.getSizeUnit(request);
 		String session_curr = ConfigMng.getCurrency(request);
 		JSONObject json_combo = new JSONObject();
 		json_combo.put(ConstKey.SESSION_NATION, session_nation);
 		json_combo.put(ConstKey.SESSION_LANG, session_lang);
 		json_combo.put(ConstKey.SESSION_SIZE_UNIT, session_unit);
 		json_combo.put(ConstKey.SESSION_CURRENCY, session_curr);
 		// 개인정보내용조회
		// 개인정보동의서 내용조회
		CodeInfo privacy_info = codeService.getPrivacyInfo(session_lang);
		session.setAttribute("privacy_info", privacy_info.getName());
 		JSONObject json_lang = LangMng.LANG_DISPLAY(session_lang);
 		session.setAttribute(ConstKey.SESSION_LANGCODE, json_lang);
 		session.setAttribute("main_combo", json_combo);
		logger.debug(ConstKey.END_LOG);
		
		return true;
	}
	
	@Override
	/**
	 * 클라이언트의 요청을 처리한 뒤에 호출됨. 컨트롤러에서 예외가 발생되면 실행하지 않는다.
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	
	@Override
	/**
	 * 클라이언트 요청 처리뒤 클리이언트에 뷰를 통해 응답을 전송한뒤 실행 됨. 뷰를 생설항때 예외가 발생해도 실행된다
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

 	/**
 	 * 로그인을 반드시 해야만 접근할수 있는 기능인지 확인
 	 * @param request
 	 * @return boolean (treu:로그인해야사용하는 화면 , false:일반화면)
 	 */
 	private boolean checkNeedLoginFromUrl (HttpServletRequest request) {
 		boolean bCheck = false;
 		String url = StringUtil.nvl(request.getRequestURL().toString());

 		for (int i = 0 ; i < ConstKey.LOGIN_URL_LIST.size() ; i++) {
 			String temp = (String)ConstKey.LOGIN_URL_LIST.get(i);
	 		//회원정보수정일경우
	 		if (url.contains(temp)) {
	 			bCheck = true;
	 			break;
	 		}
 		}

 		return bCheck;
 	}
 	
 	/**
 	 * 권한 체크 처리
 	 * @param request
 	 * @param userInfo
 	 * @return
 	 */
 	private boolean checkPermissionMenu(HttpServletRequest request,User userInfo){
 		boolean bCheck = false;
 		String callUrl = StringUtil.nvl(request.getRequestURL().toString());
 		//메인페이지가 아니면
 		//메인페이지면 무조건 true 로 권한 따지지 않음
 		if (callUrl.indexOf(ConstUrl.FIRST_URL) == -1) {
 			//그룹별 허용 메뉴목록
 			List arList = MenuMng.getMenuList(userInfo);
			/*전체 메뉴들*/
 			Hashtable tmpallList = MenuMng.getMenuMap();
 			/*리스트로 벼경*/
 			ArrayList arrNonList = new ArrayList(tmpallList.values());
 			ArrayList tmpNonList = new ArrayList(tmpallList.values());
 			//전체메뉴에서 그룹에 허용된 메뉴 제거
 			for (int n = 0 ; n < arrNonList.size(); n++) {
 				MenuInfo menuInfo1 = (MenuInfo)arrNonList.get(n);
 				for (int i = 0 ; i < arList.size(); i++) {
 					MenuInfo menuInfo2 = (MenuInfo)arList.get(i);
 					if(menuInfo2.getMenuId().equals(menuInfo1.getMenuId())){
 						tmpNonList.remove(menuInfo1);
 						break;
 					}
 				}
 			}
 			//허용안된 메뉴에 접근시 메인화면으로 보내기.
 	 		MenuInfo menuInfo = new MenuInfo();
 	 		for (int i = 0 ; i < tmpNonList.size(); i++) {
 				menuInfo = (MenuInfo)tmpNonList.get(i);
 				String temp = StringUtil.nvl(menuInfo.getUrl()); 				
				if (!"".equals(temp) && callUrl.indexOf(temp) != -1) {
					bCheck = true;
 					break;
 				}
 	 		}	
 		} else{
 			//bCheck = true;
 		}
 		
 		return bCheck;
 	}

 	/**
 	 * 어드민 권한일 경우 만 접근할수 있는 기능인지 확인
 	 * @param request
 	 * @return boolean (treu:어드민만 사용하는 화면 , false:일반화면)
 	 */
// 	private boolean checkNeedAdminLoginFromUrl (HttpServletRequest request) {
// 		boolean bCheck = false;
// 		String url = StringUtil.nvl(request.getRequestURL().toString());
//
// 		for (int i = 0 ; i < ConstKey.LOGIN_ADMIN_URL_LIST.size() ; i++) {
// 			String temp = (String)ConstKey.LOGIN_ADMIN_URL_LIST.get(i);
//	 		//회원정보수정일경우
//	 		if (url.indexOf(temp) != -1) {
//	 			bCheck = true;
//	 			break;
//	 		}
// 		}
//
// 		return bCheck;
// 	}
 	
    /**
     * <PRE>
     * Comment : 파라미터 KEY, VALUE를 정렬한다.
     * </PRE>
     *   @return String
     *   @param map
     *   @return
     */
	public static String getParammetersString(Map map) {
    	SortedMap smap = Collections.synchronizedSortedMap(new TreeMap(map));  
        
		Iterator it=smap.keySet().iterator();
		Object key=null;
		String[] value=null;
		StringBuffer param=new StringBuffer();
		int count=0;
		while(it.hasNext()) {
			key=it.next();
			value=(String[]) smap.get(key);

			for(int i=0; i<value.length; i++) {
				if(count > 0)
					param.append("&");
				else
					param.append("?");
		 		// 2020-10-19 정세연 : 보안 취약점 대응
		 		// 파라미터 값에 특정 문자, 문자열이 있는 경우 해당 파라미터 값을 공백으로 만든다.
				checkSecurity(value[i]);
				param.append(key);
				param.append("=");
				param.append(value[i]);
				count++;
			}
		}
		return param.toString();
	}
	
	/**
	 * 2020-10-19 정세연 : 보안 취약점 대응
	 * 파라미터 값에 특정 문자, 문자열이 있는 경우 해당 파라미터 값을 공백으로 만든다.
	 */
	private static void checkSecurity(String value) {
		String[] arrSecurityStr = {"!", "@", "#", "%", "^", "&", "*", "(", ")", "-", "=", "+", "|", "\\", "<", ">"};
		List<String> lstSecurityStr = new ArrayList<>(Arrays.asList(arrSecurityStr));
		if(lstSecurityStr.contains(value)) {
			value = "";
		}
	}
	public static final String IS_MOBILE = "MOBILE";
	public static final String IS_IOS = "IOS";
	public static final String IS_ANDROID = "ANDROID";
	private static final String IS_PHONE = "PHONE";
	public static final String IS_TABLET = "TABLET";
	public static final String IS_PC = "PC";
	
	public static String isDevice(HttpServletRequest req) {
	    String userAgent = req.getHeader("User-Agent").toUpperCase();
		
	    if(userAgent.indexOf(IS_MOBILE) > -1 || userAgent.indexOf(IS_ANDROID) > -1 || userAgent.indexOf(IS_IOS) > -1) {
	        if(userAgent.indexOf(IS_PHONE) > -1)
		    return IS_MOBILE;
		else
		    return IS_TABLET;
	    } else
	    	return IS_PC;
	    
	}
}
