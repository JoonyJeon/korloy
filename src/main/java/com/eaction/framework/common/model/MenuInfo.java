/*****************************************************************************
 * 프로그램명  : MenuInfo.java
 * 설     명  : 메뉴관리  데이터빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.util.StringUtil;

/**
 * 메뉴관리 데이터 빈
 * @author eaction
 * @version 1.0
 */
@Alias("MenuInfo")
public class MenuInfo implements Serializable{
    /**
	 * UID
	 */
	private static final long serialVersionUID = 3046483563644426095L;
	
	/** 메뉴아이디 */
    private String menuId = "";
    /** 페이지아이디 */
    private String pageId = "";
    /** 메뉴이름 */
    private String menuNm = "";
    /** 메뉴이름한글 */
    private String menuNmKor = "";
    /** 메뉴이름영문 */
    private String menuNmEng = "";
    /** 메뉴이름일본어 */
    private String menuNmJpn = "";
    /** 상위메뉴아이디 */
    private String upMenuId = "";
    /** 메뉴타입 */
    private String menuType = "";
    /** 일련번호 */
    private int seq = 0;
    /** 메뉴의 URL */
    private String url = "";
    /** 등록유저아이디 */
    private String crtUser = "";
    /** 업데이터유저 */
    private String updtUser = "";
    /** 수정자명 */
	private String updtUserNm = "";
    /** 등록날짜 */
    private String crtDt = "";
    /** 갱신날짜 */
    private String updtDt = "";
    /** 메뉴레벨 */
    private String lvl = "";
    /** 말단 항목여부 */
    private String isEnd = "";
    /** 그룹 레벨 */
	private String groupLevel = "";
	/** 유저그룹별 메뉴관리의 체크정보 */
	private String isChecked = "";

	 /** 메뉴종류 */
    private String menuKind = "";
    /** 메뉴길이 */
    private String menuWidth = "";
    /** 메뉴아이콘 */
	private String menu_icon = "";

    /** 수정권한 */
    private String edit_yn = "";


	/**
	 * 메뉴아이콘 설정
	 * @param menu_icon 메뉴아이콘
	 */
	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}
	/**
	 * 메뉴아이콘 취득
	 * @return menu_icon 메뉴아이콘
	 */
	public String getMenu_icon() {
		return StringUtil.nvl(this.menu_icon);
	}

    /**
     * 메뉴아이디 설정
     * @param str 메뉴아이디
     */
    public void setMenuId(String str){
    	menuId  = StringUtil.nvl(str);
    }

    /**
     * 메뉴아이디 취득
     * @return String 메뉴아이디
     */
    public String getMenuId(){
        return menuId ;
    }

    /**
     * 메뉴이름 설정
     * @param str 메뉴이름
     */
    public void setMenuNm(String str){
    	menuNm  = StringUtil.nvl(str);
    }

    /**
     * 메뉴이름 취득
     * @return String 메뉴이름
     */
    public String getMenuNm(){
        return menuNm ;
    }

    /**
     * 메뉴이름한글 설정
     * @param str 메뉴이름한글
     */
    public void setMenuNmKor(String str){
    	menuNmKor  = StringUtil.nvl(str);
    }

    /**
     * 메뉴이름한글 취득
     * @return String 메뉴이름한글
     */
    public String getMenuNmKor(){
        return menuNmKor ;
    }

    /**
     * 메뉴이름영문 설정
     * @param str 메뉴이름영문
     */
    public void setMenuNmEng(String str){
    	menuNmEng  = StringUtil.nvl(str);
    }

    /**
     * 메뉴이름영문 취득
     * @return String 메뉴이름영문
     */
    public String getMenuNmEng(){
        return menuNmEng ;
    }
    /**
     * 메뉴이름일본어 설정
     * @param str 메뉴이름일본어
     */
    public void setMenuNmJpn(String str){
    	menuNmJpn  = StringUtil.nvl(str);
    }

    /**
     * 메뉴이름일본어 취득
     * @return String 메뉴이름일본어
     */
    public String getMenuNmJpn(){
        return menuNmJpn ;
    }

    /**
     * 상위메뉴아이디 설정
     * @param str 상위메뉴아이디
     */
    public void setUpMenuId(String str){
    	upMenuId  = StringUtil.nvl(str);
    }

    /**
     * 상위메뉴아이디 취득
     * @return String 상위메뉴아이디
     */
    public String getUpMenuId(){
        return upMenuId ;
    }

    /**
     * 메뉴타입 설정
     * @param str 메뉴타입
     */
    public void setMenuType(String str){
    	menuType  = StringUtil.nvl(str);
    }

    /**
     * 메뉴타입 취득
     * @return String 메뉴타입
     */
    public String getMenuType(){
        return menuType ;
    }

    /**
     * 일련번호설정
     * @param str 일련번호
     */
    public void setSeq(int str){
    	seq = str;
    }

    /**
     * 일련번호취득
     * @return no 일련번호
     */
    public int getSeq(){
        return seq ;
    }

    /**
     * 메뉴의 URL 설정
     * @param str 메뉴의 URL
     */
    public void setUrl(String str){
    	url  = StringUtil.nvl(str);
    }

    /**
     * 메뉴의 URL 취득
     * @return String 메뉴의 URL
     */
    public String getUrl(){
        return url ;
    }

    /**
     * 등록유저아이디 설정
     * @param str 등록유저아이디
     */
    public void setCrtUser(String str){
    	crtUser  = StringUtil.nvl(str);
    }

    /**
     * 등록유저아이디 취득
     * @return String 등록유저아이디
     */
    public String getCrtUser(){
        return crtUser ;
    }

    /**
     * 업데이터유저 설정
     * @param str 업데이터유저
     */
    public void setUpdtUser(String str){
    	updtUser  = StringUtil.nvl(str);
    }

    /**
     * 업데이터유저 취득
     * @return String 업데이터유저
     */
    public String getUpdtUser(){
        return updtUser ;
    }

    /**
	 * 수정자명 설정
	 * @param updtUserNm 수정자명
	 */
	public void setUpdtUserNm(String updtUserNm) {
		this.updtUserNm = updtUserNm;
	}
	/**
	 * 수정자명 취득
	 * @return updtUserNm 수정자명
	 */
	public String getUpdtUserNm() {
		return StringUtil.nvl(this.updtUserNm);
	}

    /**
     * 등록날짜 설정
     * @param str 등록날짜
     */
    public void setCrtDt(String str){
    	crtDt  = StringUtil.nvl(str);
    }

    /**
     * 등록날짜 취득
     * @return String 등록날짜
     */
    public String getCrtDt(){
        return crtDt ;
    }

    /**
     * 갱신날짜 설정
     * @param str 갱신날짜
     */
    public void setUpdtDt(String str){
    	updtDt  = StringUtil.nvl(str);
    }

    /**
     * 갱신날짜 취득
     * @return String 갱신날짜
     */
    public String getUpdtDt(){
        return updtDt ;
    }

    /**
     * 메뉴레벨 설정
     * @param str 메뉴레벨
     */
    public void setLvl(String str){
    	lvl  = StringUtil.nvl(str);
    }

    /**
     * 메뉴레벨 취득
     * @return String 메뉴레벨
     */
    public String getLvl(){
        return lvl ;
    }

    /**
     * 말단항목여부체크 설정
     * @param str 말단항목여부체크
     */
    public void setIsEnd(String str){
    	isEnd  = str;
    }

    /**
     * 말단항목여부체크 취득
     * @return String 말단항목여부체크
     */
    public String getIsEnd(){
        return isEnd ;
    }

    /**
     * 하위메뉴입력버튼을 사용가능하게 할지 사용불가로 할지 결정
     * @return disabled문자열
     */
    public boolean checkDownMenu(){
    	boolean bReturn = false;
    	if (ConstKey.MENU_TYPE_G.equals(getMenuType())) {
    		bReturn = true;
    	}
        return bReturn;
    }

    /**
     * 메뉴입력버튼을 사용가능하게 할지 사용불가로 할지 결정
     * @return disabled문자열
     */
    public boolean checkInputMenu(){
    	boolean bReturn = false;
    	if (getSeq() != 1) {
    		bReturn = true;
    	}
    	return bReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     * @return disabled문자열
     */
    public boolean checkDelete(){
    	boolean bReturn = false;
    	if (ConstKey.KEY_YES.equals(getIsEnd())) {
    		bReturn = true;
    	}
    	return bReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     * @return disabled문자열
     */
    public String checkMenuType(){
    	String strReturn = ConstKey.KEY_READONLY;
    	if (ConstKey.KEY_YES.equals(getIsEnd())) {
    		strReturn = "";
    	}
    	return strReturn;
    }

    /**
     * 삭제버튼을 사용가능하게 할지 사용불가로 할지 결정
     * @return disabled문자열
     */
    public String checkUrl(){
    	String strReturn = "";
    	if (ConstKey.MENU_TYPE_G.equals(getMenuType())) {
    		strReturn = ConstKey.KEY_READONLY;
    	}
    	return strReturn;
    }

    /**
	 * 그룹레벨값취득
	 * @return String　그룹레벨값
	 */
	public String getGroupLevel() {
		return groupLevel;
	}
	/**
	 * 그룹레벨값설정
	 * @param str 그룹레벨값
	 */
	public void setGroupLevel(String str) {
		this.groupLevel = str;
	}

	/**
	 * 유저그룹별 메뉴관리의 체크정보취득
	 * @return String　유저그룹별 메뉴관리의 체크정보
	 */
	public String getIsChecked() {
		return isChecked;
	}
	/**
	 * 유저그룹별 메뉴관리의 체크정보설정
	 * @param str 유저그룹별 메뉴관리의 체크정보
	 */
	public void setIsChecked(String str) {
		this.isChecked = str;
	}



    /**
     * 메뉴종류 설정
     * @param str 메뉴종류
     */
    public void setMenuKind(String str){
    	menuKind  = StringUtil.nvl(str);
    }

    /**
     * 메뉴종류 취득
     * @return String 메뉴종류
     */
    public String getMenuKind(){
        return menuKind ;
    }

    /**
     * 메뉴길이 설정
     * @param str 메뉴길이
     */
    public void setMenuWidth(String str){
    	menuWidth  = StringUtil.nvl(str);
    }

    /**
     * 메뉴길이 취득
     * @return String 메뉴길이
     */
    public String getMenuWidth(){
        return menuWidth ;
    }

	public String getPageId() {
		return StringUtil.nvl(pageId);
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	
    /**
     * 수정권한 설정
     * @param str 수정권한
     */
    public void setEdit_yn(String str){
    	edit_yn = str;
    }

    /**
     * 수정권한 취득
     * @return String 수정권한
     */
    public String getEdit_yn(){
        return edit_yn ;
    }
 
    /**
     * 수정권한 체크 여부
     * @return String 수정권한 체크 여부
     */
    public String IsEdit_yn_checked(){
    	if("Y".equals(edit_yn))
    		return "checked";
    	else 
    		return "";
    }
    
}