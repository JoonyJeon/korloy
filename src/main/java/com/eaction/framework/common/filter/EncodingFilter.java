/*****************************************************************************
 * 프로그램명  : EncodingFilter.java
 * 설     명  : 인코딩 필터 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.User;

/**
 * 인코딩 필터 
 * @author  eaction
 * @version 1.0
 */
public class EncodingFilter implements Filter {
    private String encoding = null;
    protected FilterConfig filterConfig = null;

    private String language = ConfigMng.getValue(IPropertyKey.LANGUAGE);
    private String time_zone = ConfigMng.getValue(IPropertyKey.TIME_ZONE);
    private String login_user_id = "";
    private String login_company_id = "";
    
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            if (encoding != null) {
                request.setCharacterEncoding(encoding);
            }
        }

    	RequestModifyParameter requestWrapper = new RequestModifyParameter((HttpServletRequest)request);
    	
    	HttpSession session = ((HttpServletRequest)request).getSession();
    	User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
    	
        if(userInfo != null) {
        	language = userInfo.getUser_lang();
        	time_zone = userInfo.getTime_zone();
        	login_user_id = userInfo.getUser_id();
        	login_company_id = userInfo.getCd_company();
        }
        
    	if(request.getParameter("user_lang") == null || "".equals(request.getParameter("user_lang"))) {    		
    	 	requestWrapper.setParameter("user_lang", language);
    	}     	
        if(request.getParameter("time_zone") == null || "".equals(request.getParameter("time_zone"))) {
    	 	requestWrapper.setParameter("time_zone", time_zone);
    	} 		
        if(request.getParameter("login_user_id") == null || "".equals(request.getParameter("login_user_id"))) {
    	 	requestWrapper.setParameter("login_user_id", login_user_id);
    	} 		
        if(request.getParameter("login_company_id") == null || "".equals(request.getParameter("login_company_id"))) {
    	 	requestWrapper.setParameter("login_company_id", login_company_id);
    	}

        chain.doFilter(requestWrapper, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig cfg) {
        filterConfig = cfg;
    }
}
