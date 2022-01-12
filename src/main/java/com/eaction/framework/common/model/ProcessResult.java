/*****************************************************************************
 * 프로그램명  : ProcessResult.java
 * 설     명  : 처리결과
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.23    LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.model;

import com.eaction.framework.common.util.DateTimeUtil;

/**
 * 처리결과 정의한다
 *
 * @author eaction
 * @version 1.0
 */
public class ProcessResult extends Exception {

	private String time = null;
	private String className = null;
	private String methodName = null;
	private int retCod = 0;
	private String retId = null;
	private String retMsg = null;
	private Object result = null;
	private String successScript = "";
	private String retMsgCd = "";
	/** COUNT */
	private int retCnt = 0;



	
	/**
     * ProcessResult
     */
    public ProcessResult() {
        super();
        this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    }
    /**
     * ProcessResult
     * @param retId 리턴아이디
     */
    public ProcessResult(String retId) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
        this.retId = retId;
    }

    public ProcessResult(String retId, String retMsg) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    	this.retId = retId;
    	this.retMsg = retMsg;
    }

    public ProcessResult(String calssName, String methodName, int retCod, String retMsg) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    	this.className = calssName;
    	this.methodName = methodName;
    	this.retCod = retCod;
    	this.retMsg = retMsg;
    }
    
    public ProcessResult(String calssName, String methodName, int retCod,int retCnt, String retMsg) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    	this.className = calssName;
    	this.methodName = methodName;
    	this.retCod = retCod;
    	this.retCnt = retCnt;
    	this.retMsg = retMsg;
    }

    public ProcessResult(String calssName, String methodName, int retCod, String retMsg, Object result) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    	this.className = calssName;
    	this.methodName = methodName;
    	this.retCod = retCod;
    	this.retMsg = retMsg;
    	this.result = result;
    }

    public ProcessResult(int retCod, String retMsg) {
    	super();
    	this.time = DateTimeUtil.getDateTimeByPattern("yyyy-MM-dd-HH:mm");
    	this.retCod = retCod;
    	this.retMsg = retMsg;
    }

    /**
	 * COUNT 설정
	 * @param retCnt COUNT
	 */
	public void setRetCnt(int retCnt) {
		this.retCnt = retCnt;
	}
	/**
	 * COUNT 취득
	 * @return retCnt COUNT
	 */
	public int getRetCnt() {
		return this.retCnt;
	}
	/**
	 * 리턴코드 취득
	 *
	 * @return int 리턴코드
	 */
	public int getRetCod() {
		return retCod;
	}
	/**
	 * 리턴아이디 취득
	 *
	 * @return java.lang.String 리턴아이디
	 */
	public java.lang.String getRetId() {
		return retId;
	}
	/**
	 * 리턴메세지 취득
	 *
	 * @return java.lang.String 리턴메세지
	 */
	public java.lang.String getRetMsg() {
		return retMsg;
	}
	/**
	 * 결과 발생 시간 취득
	 *
	 * @return java.lang.String 결과 발생 시간
	 */
	public java.lang.String getTime() {
		return time;
	}
	/**
	 * 클래스명 취득
	 *
	 * @return java.lang.String 클래스명
	 */
	public java.lang.String getClassName() {
		return className;
	}
	/**
	 * 메소드명 취득
	 *
	 * @return java.lang.String 메소드명
	 */
	public java.lang.String getMethodName() {
		return methodName;
	}
	/**
	 * 결과OBJ  취득
	 *
	 * @return Object 결과OBJ
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * 메세지 표시후에 처리할 함수 명칭 취득
	 *
	 * @return String 메세지 표시후에 처리할 함수 명칭
	 */
	public String getSuccessScript() {
		return successScript;
	}
	/**
	 * 메세지 표시후에 처리할 함수 명칭 취득
	 *
	 * @return String 메세지 표시후에 처리할 함수 명칭
	 */
	public void setSuccessScript(String str) {
		successScript = str;
	}

	/**
	 * 메세지코드 취득
	 *
	 * @return String 메세지코드
	 */
	public String getRetMsgCd() {
		return retMsgCd;
	}
	/**
	 * 메세지코드 취득
	 *
	 * @return String 메세지코드
	 */
	public void setRetMsgCd(String str) {
		retMsgCd = str;
	}
}