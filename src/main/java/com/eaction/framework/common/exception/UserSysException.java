/*****************************************************************************
 * 프로그램명  : UserSysException.java
 * 설     명  : System Exception 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.exception;


import com.eaction.framework.common.model.ErrorInfo;
import com.eaction.framework.common.util.DateTimeUtil;

/**
 * 시스템 예외
 * 
 * @author eaction
 * @version 1.0
 */
public class UserSysException extends Exception {	
	/** 에러정보 객체  */
	private ErrorInfo errorInfo =  new ErrorInfo();
	
    /**
     * UserSysException 
     * @param className 클래스명
     * @param methodName 메소드명
     * @param errId 에러아이디   
     */
    public UserSysException(String className, String methodName) {
    	super();
    	errorInfo.setClassName(className);
    	errorInfo.setMethodName(methodName);    	
    	errorInfo.setDateTime(DateTimeUtil.getDateTime());
    } 
    
    /**
     * UserSysException 
     * @param className 클래스명
     * @param methodName 메소드명
     * @param errId 에러아이디   
     * @param e 예외 
     */
    public UserSysException(String className, String methodName,Exception originException) {
    	super(originException);
    	errorInfo.setClassName(className);
    	errorInfo.setMethodName(methodName);    	
    	errorInfo.setDateTime(DateTimeUtil.getDateTime());
    	errorInfo.setOriginException(originException);
    } 
        

    /**
     * UserSysException 
     * @param className 클래스명
     * @param methodName 메소드명
     * @param errId 에러아이디   
     */
    public UserSysException(ErrorInfo errInfo) {
    	super();
    	this.errorInfo = errInfo;
    	this.errorInfo.setDateTime(DateTimeUtil.getDateTime());
    }     
    
    /**
	 * 에러정보객체 취득
	 * @return ErrorInfo 에러정보객체
	 */
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	
	/**
	 * 에러정보객체 취득
	 * @param errorInfo 에러정보객체
	 */
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}	
}