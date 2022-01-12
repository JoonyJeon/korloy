/*****************************************************************************
 * 프로그램명  : ConfigurationException.java
 * 설     명  : 처리가능한 예외 정의 클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.exception;

public class ConfigurationException extends Exception{

	/**
	 *
	 */
	public ConfigurationException() {
		super();
	}

	/**
	 * @param s java.lang.String
	 */
	public ConfigurationException(String s) {
		super(s);
	}
}
