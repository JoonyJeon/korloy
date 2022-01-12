/*****************************************************************************
 * 프로그램명  : CookieMng.java
 * 설     명  : 쿠키관리클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 쿠키관리 클래스
 * @author eaction
 * @version 1.0
 */
public class CookieMng {

	/**
	 * 쿠키리스트 (CookieWrapper객체의 리스트 저장)
	 */
	private List<Cookie> cookies = new ArrayList<Cookie>();

	/**
	 * 생성자
	 */
	public CookieMng() {		
	}
	
	/**
	 * request정보로 부터 쿠키정보를 모두 취득해서 리스트에 담아놓는다 
	 * @param request 요청정보
	 */
	public CookieMng(HttpServletRequest request) {
		if (cookies != null) {
			setCookies(request.getCookies());
		}
	}
	
	/**
	 * response정보에 쿠키리스트 정보를 모두 설정한다
	 * @param response 응답객체
	 */
	public void setCookieList(HttpServletResponse response){		
		Iterator i = this.cookies.iterator();
		Cookie cw = null;
		while (i.hasNext()) {
			cw = (Cookie)i.next();
			response.addCookie(cw);
		}
	}
	
	/**
	 * 쿠키리스트에 쿠키래퍼객체를 하나 추가한다
	 * @param wrappedCookie 쿠키래퍼객체
	 */
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}

	/**
	 * 쿠키래퍼를 설정해서 관리리스트에 추가한다
	 * @param name 쿠키명칭
	 * @param value 쿠키값
	 * @param maxAge 쿠키보관기간
	 */
	public void addCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		addCookie(cookie);
	}
	
	/**
	 * 쿠키래퍼를 설정해서 관리리스트에 추가한다
	 * @param name 쿠키명칭
	 * @param value 쿠키값
	 * @param maxAge 쿠키보관기간
	 */
	public void addCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		addCookie(cookie);
	}
	
	/**
	 * 쿠키를 쿠키래퍼에 설정해서 보관리스트에 저장한다
	 * @param cookies 쿠키리스트
	 */
	public void setCookies(Cookie[] cookies) {
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				addCookie(cookies[i]);
			}
		}
	}

	/**
	 * 쿠키래퍼클래스 보관 리스트로 부터 쿠키의 리스트를 취득한다
	 * @return ArrayList 쿠키리스트
	 */
	private List getCookieList() {
		return cookies;
	}

	/**
	 * 쿠키벼열를 취득한다
	 * @return Cookie[]쿠키배열을 취득
	 */
	public Cookie[] getCookies() {
		return (Cookie[])getCookieList().toArray();
	}

	/**
	 * 쿠키리스트로 부터 쿠키를 검색한다
	 * @param name 검색할 쿠키명칭
	 * @return CookieWrapper 쿠키저장객체
	 */
	private Cookie searchCookie(String name) {
		Iterator i = cookies.iterator();
		Cookie cw = null;
		boolean chk = false;
		while (i.hasNext()) {
			cw = (Cookie)i.next();
			if (cw.getName().equals(name)) {
				chk = true;
				break;
			}
		}
		if(chk == false){
			cw = null;
		}
		return cw;
	}

	/**
	 * 쿠키이름으로 검색하여 해당 쿠키의 값을 취득한다
	 * @param name 쿠키이름
	 * @return String 쿠키값ꍇ
	 */
	public String getValue(String name) { 
        String strResult = "";
		Cookie cw = searchCookie(name);

		if (cw != null) {
			strResult = cw.getValue();
		}

		return strResult;
	}

	/**
	 * 쿠키이름으로 쿠키를 검색해서 쿠키값을 수정한다 
	 * @param name 쿠키이름
	 * @param value 쿠키이름ꍇ
	 */
	public void setValue(String name, String value) {

		Cookie cw = searchCookie(name);

		if (cw != null) {
			cw.setValue(value);
		}
	}

	/**
	 * 쿠키이름으로 검색해서 값과 보관기간을 수정한다
	 * @param name 쿠키이름
	 * @param value 쿠키값
	 * @param maxAge 쿠키저장기간ꍇ
	 */
	public void setValue(String name, String value, int maxAge) {

		this.setValue(name, value);
		this.setMaxAge(name, maxAge);
	}

	/**
	 * 쿠키저장기간
	 * @param name 쿠키명칭
	 * @return int 쿠키저장기간ꍇ
	 */
	public int getMaxAge(String name) {
        int age = 0;
		Cookie cw = searchCookie(name);

		if (cw != null) {
			age = cw.getMaxAge();
		}

		return age;
	}

	/**
	 * 쿠키명칭으로 쿠키를 검색해서 쿠키저장기간을 갱신한다
	 * @param name 쿠키명칭
	 * @param maxAge 쿠키저장기간ꍇ
	 */
	public void setMaxAge(String name, int maxAge) {

		Cookie cw = searchCookie(name);

		if (cw != null) {
			cw.setMaxAge(maxAge);
		}
	}

}
