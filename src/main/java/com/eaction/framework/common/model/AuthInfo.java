/*****************************************************************************
 * 프로그램명  : AuthorityUtil.java
 * 설     명  : 권한별 체크 설정.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.12  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.model;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.util.StringUtil;

/**
 * 권한 체크 클래스
 * @author  eaction
 * @version 1.0
 */
public class AuthInfo {
	/** 장바구니견적요청 */
	private boolean isBasketOrderAuth = false;
	/** 어셈블리 */
	private boolean isAssemblyAuth = false;
	/** Tailored Item */
	private boolean isTailoredAuth = false;
	/** 엑셀다운로드 */
	private boolean isExcelDownAuth = false;
	/** 3D다운로드 */
	private boolean isStpDownAuth = false;
	/** 2D다운로드 */
	private boolean isDxfDownAuth = false;
	/** P12다운로드 */
	private boolean isP21DownAuth = false;
	/** 단품GTC다운로드 */
	private boolean isOneGtcDownAuth = false;
	/** 전체GTC다운로드 */
	private boolean isAllGtcDownAuth = false;

	/** user_grp */
	private String user_grp = "";
	/** auth_1 */
	private String auth_1 = "";
	/** auth_2 */
	private String auth_2 = "";
	/** auth_3 */
	private String auth_3 = "";
	/** auth_4 */
	private String auth_4 = "";
	/** auth_5 */
	private String auth_5 = "";
	/** auth_6 */
	private String auth_6 = "";
	/** auth_7 */
	private String auth_7 = "";
	/** auth_8 */
	private String auth_8 = "";
	/** auth_9 */
	private String auth_9 = "";



	/**
	 * user_grp 설정
	 * @param user_grp user_grp
	 */
	public void setUser_grp(String user_grp) {
		this.user_grp = user_grp;
	}
	/**
	 * user_grp 취득
	 * @return user_grp user_grp
	 */
	public String getUser_grp() {
		return StringUtil.nvl(this.user_grp);
	}

	/**
	 * auth_1 설정
	 * @param auth_1 auth_1
	 */
	public void setAuth_1(String auth_1) {
		this.auth_1 = auth_1;
		if(ConstKey.KEY_Y.equals(auth_1)) {
			this.setIsBasketOrderAuth(true);
		}
	}
	/**
	 * auth_1 취득
	 * @return auth_1 auth_1
	 */
	public String getAuth_1() {
		return StringUtil.nvl(this.auth_1);
	}

	/**
	 * auth_2 설정
	 * @param auth_2 auth_2
	 */
	public void setAuth_2(String auth_2) {
		this.auth_2 = auth_2;
		if(ConstKey.KEY_Y.equals(auth_2)) {
			this.setIsAssemblyAuth(true);
		}
	}
	/**
	 * auth_2 취득
	 * @return auth_2 auth_2
	 */
	public String getAuth_2() {
		return StringUtil.nvl(this.auth_2);
	}

	/**
	 * auth_3 설정
	 * @param auth_3 auth_3
	 */
	public void setAuth_3(String auth_3) {
		this.auth_3 = auth_3;
		if(ConstKey.KEY_Y.equals(auth_3)) {
			this.setIsTailoredAuth(true);
		}
	}
	/**
	 * auth_3 취득
	 * @return auth_3 auth_3
	 */
	public String getAuth_3() {
		return StringUtil.nvl(this.auth_3);
	}

	/**
	 * auth_4 설정
	 * @param auth_4 auth_4
	 */
	public void setAuth_4(String auth_4) {
		this.auth_4 = auth_4;
		if(ConstKey.KEY_Y.equals(auth_4)) {
			this.setIsExcelDownAuth(true);
		}
	}
	/**
	 * auth_4 취득
	 * @return auth_4 auth_4
	 */
	public String getAuth_4() {
		return StringUtil.nvl(this.auth_4);
	}

	/**
	 * auth_5 설정
	 * @param auth_5 auth_5
	 */
	public void setAuth_5(String auth_5) {
		this.auth_5 = auth_5;
		if(ConstKey.KEY_Y.equals(auth_5)) {
			this.setIsStpDownAuth(true);
		}
	}
	/**
	 * auth_5 취득
	 * @return auth_5 auth_5
	 */
	public String getAuth_5() {
		return StringUtil.nvl(this.auth_5);
	}

	/**
	 * auth_6 설정
	 * @param auth_6 auth_6
	 */
	public void setAuth_6(String auth_6) {
		this.auth_6 = auth_6;
		if(ConstKey.KEY_Y.equals(auth_6)) {
			this.setIsDxfDownAuth(true);
		}
	}
	/**
	 * auth_6 취득
	 * @return auth_6 auth_6
	 */
	public String getAuth_6() {
		return StringUtil.nvl(this.auth_6);
	}

	/**
	 * auth_7 설정
	 * @param auth_7 auth_7
	 */
	public void setAuth_7(String auth_7) {
		this.auth_7 = auth_7;
		if(ConstKey.KEY_Y.equals(auth_7)) {
			this.setIsP21DownAuth(true);
		}
	}
	/**
	 * auth_7 취득
	 * @return auth_7 auth_7
	 */
	public String getAuth_7() {
		return StringUtil.nvl(this.auth_7);
	}

	/**
	 * auth_8 설정
	 * @param auth_8 auth_8
	 */
	public void setAuth_8(String auth_8) {
		this.auth_8 = auth_8;
		if(ConstKey.KEY_Y.equals(auth_8)) {
			this.setIsOneGtcDownAuth(true);
		}
	}
	/**
	 * auth_8 취득
	 * @return auth_8 auth_8
	 */
	public String getAuth_8() {
		return StringUtil.nvl(this.auth_8);
	}

	/**
	 * auth_9 설정
	 * @param auth_9 auth_9
	 */
	public void setAuth_9(String auth_9) {
		this.auth_9 = auth_9;
		if(ConstKey.KEY_Y.equals(auth_8)) {
			this.setIsAllGtcDownAuth(true);
		}
	}
	/**
	 * auth_9 취득
	 * @return auth_9 auth_9
	 */
	public String getAuth_9() {
		return StringUtil.nvl(this.auth_9);
	}

	/**
	 * 장바구니견적요청 설정
	 * @param isBasketOrderAuth 장바구니견적요청
	 */
	public void setIsBasketOrderAuth(boolean isBasketOrderAuth) {
		this.isBasketOrderAuth = isBasketOrderAuth;
	}
	/**
	 * 장바구니견적요청 취득
	 * @return isBasketOrderAuth 장바구니견적요청
	 */
	public boolean getIsBasketOrderAuth() {
		return this.isBasketOrderAuth;
	}

	/**
	 * 어셈블리 설정
	 * @param isAssemblyAuth 어셈블리
	 */
	public void setIsAssemblyAuth(boolean isAssemblyAuth) {
		this.isAssemblyAuth = isAssemblyAuth;
	}
	/**
	 * 어셈블리 취득
	 * @return isAssemblyAuth 어셈블리
	 */
	public boolean getIsAssemblyAuth() {
		return this.isAssemblyAuth;
	}

	/**
	 * Tailored Item 설정
	 * @param isTailoredAuth Tailored Item
	 */
	public void setIsTailoredAuth(boolean isTailoredAuth) {
		this.isTailoredAuth = isTailoredAuth;
	}
	/**
	 * Tailored Item 취득
	 * @return isTailoredAuth Tailored Item
	 */
	public boolean getIsTailoredAuth() {
		return this.isTailoredAuth;
	}

	/**
	 * 엑셀다운로드 설정
	 * @param isExcelDownAuth 엑셀다운로드
	 */
	public void setIsExcelDownAuth(boolean isExcelDownAuth) {
		this.isExcelDownAuth = isExcelDownAuth;
	}
	/**
	 * 엑셀다운로드 취득
	 * @return isExcelDownAuth 엑셀다운로드
	 */
	public boolean getIsExcelDownAuth() {
		return this.isExcelDownAuth;
	}

	/**
	 * 3D다운로드 설정
	 * @param isStpDownAuth 3D다운로드
	 */
	public void setIsStpDownAuth(boolean isStpDownAuth) {
		this.isStpDownAuth = isStpDownAuth;
	}
	/**
	 * 3D다운로드 취득
	 * @return isStpDownAuth 3D다운로드
	 */
	public boolean getIsStpDownAuth() {
		return this.isStpDownAuth;
	}

	/**
	 * 2D다운로드 설정
	 * @param isDxfDownAuth 2D다운로드
	 */
	public void setIsDxfDownAuth(boolean isDxfDownAuth) {
		this.isDxfDownAuth = isDxfDownAuth;
	}
	/**
	 * 2D다운로드 취득
	 * @return isDxfDownAuth 2D다운로드
	 */
	public boolean getIsDxfDownAuth() {
		return this.isDxfDownAuth;
	}

	/**
	 * P12다운로드 설정
	 * @param isP21DownAuth P12다운로드
	 */
	public void setIsP21DownAuth(boolean isP21DownAuth) {
		this.isP21DownAuth = isP21DownAuth;
	}
	/**
	 * P12다운로드 취득
	 * @return isP21DownAuth P12다운로드
	 */
	public boolean getIsP21DownAuth() {
		return this.isP21DownAuth;
	}

	/**
	 * 단품GTC다운로드 설정
	 * @param isOneGtcDownAuth 단품GTC다운로드
	 */
	public void setIsOneGtcDownAuth(boolean isOneGtcDownAuth) {
		this.isOneGtcDownAuth = isOneGtcDownAuth;
	}
	/**
	 * 단품GTC다운로드 취득
	 * @return isOneGtcDownAuth 단품GTC다운로드
	 */
	public boolean getIsOneGtcDownAuth() {
		return this.isOneGtcDownAuth;
	}

	/**
	 * 전체GTC다운로드 설정
	 * @param isAllGtcDownAuth 전체GTC다운로드
	 */
	public void setIsAllGtcDownAuth(boolean isAllGtcDownAuth) {
		this.isAllGtcDownAuth = isAllGtcDownAuth;
	}
	/**
	 * 전체GTC다운로드 취득
	 * @return isAllGtcDownAuth 전체GTC다운로드
	 */
	public boolean getIsAllGtcDownAuth() {
		return this.isAllGtcDownAuth;
	}
}