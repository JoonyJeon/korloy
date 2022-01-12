package com.eaction.framework.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eaction.framework.common.model.User;


 
public class SessionTimeoutCookieFilter implements Filter {
 
    @SuppressWarnings("unused")
    private FilterConfig config;
 
    /**
     * doFilter
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
//    	HttpServletResponse httpResp = (HttpServletResponse) response;
//        HttpServletRequest httpReq = (HttpServletRequest) request;
//
//        long currTime = System.currentTimeMillis();
//        String expiryTime = Long.toString(currTime + httpReq.getSession().getMaxInactiveInterval() * 1000);
//        Cookie cookie = new Cookie("serverTime", Long.toString(currTime));
//        cookie.setPath("/");
//        httpResp.addCookie(cookie);
//        User userInfo = AuthorityUtil.getUserInfo(httpReq);
//        if (userInfo == null) {
//            cookie = new Cookie("sessionExpiry", expiryTime);
//            cookie.setPath("/");
//            httpResp.addCookie(cookie);
//        }
//        
//        chain.doFilter(request, response);
    	HttpServletResponse httpResp = (HttpServletResponse) response;
         
        long currTime = System.currentTimeMillis();
        long expiryTime = currTime + (((HttpServletRequest) request).getSession().getMaxInactiveInterval() * 1000);
 
        Cookie cookie = new Cookie("serverTime", "" + currTime);
 
        cookie.setPath("/");
        httpResp.addCookie(cookie);
 
 
        Cookie cookie2 = new Cookie("sessionExpiry", "" + expiryTime);
        
        cookie2.setPath("/");
 
        httpResp.addCookie(cookie2);
 
        chain.doFilter(request, response);
    }
 
    /**
     * init
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
 
    /**
     * destroy
     */
    @Override
    public void destroy() {
 
    }
}
 
