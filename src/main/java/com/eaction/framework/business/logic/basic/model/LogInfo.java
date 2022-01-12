/*****************************************************************************
 * 프로그램명  : BasicInfo.java
 * 설     명  : 기본데이터 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.01.07   HSI    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.basic.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("logInfo")
public class LogInfo extends EactReqInfo {
	

	/** evt_cd */
	private String evt_cd = "";
	/** item */
	private String item = "";
	/** ip_addr */
	private String ip_addr = "";
	/** user_id */
	private String user_id = "";
	/** evt_desc */
	private String evt_desc = "";
	/** evt_time */
	private String evt_time = "";
	/** evt_seqno */
	private long evt_seqno = 0;



	/**
	 * evt_seqno 설정
	 * @param evt_seqno evt_seqno
	 */
	public void setEvt_seqno(long evt_seqno) {
		this.evt_seqno = evt_seqno;
	}
	/**
	 * evt_seqno 취득
	 * @return evt_seqno evt_seqno
	 */
	public long getEvt_seqno() {
		return this.evt_seqno;
	}


	/**
	 * evt_cd 설정
	 * @param evt_cd evt_cd
	 */
	public void setEvt_cd(String evt_cd) {
		this.evt_cd = evt_cd;
	}
	/**
	 * evt_cd 취득
	 * @return evt_cd evt_cd
	 */
	public String getEvt_cd() {
		return StringUtil.nvl(this.evt_cd);
	}
	
	/**
	 * item 설정
	 * @param item item
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * item 취득
	 * @return item item
	 */
	public String getItem() {
		return StringUtil.nvl(this.item);
	}

	/**
	 * ip_addr 설정
	 * @param ip_addr ip_addr
	 */
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	/**
	 * ip_addr 취득
	 * @return ip_addr ip_addr
	 */
	public String getIp_addr() {
		return StringUtil.nvl(this.ip_addr);
	}

	/**
	 * user_id 설정
	 * @param user_id user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * user_id 취득
	 * @return user_id user_id
	 */
	public String getUser_id() {
		return StringUtil.nvl(this.user_id);
	}

	/**
	 * evt_desc 설정
	 * @param evt_desc evt_desc
	 */
	public void setEvt_desc(String evt_desc) {
		this.evt_desc = evt_desc;
	}
	/**
	 * evt_desc 취득
	 * @return evt_desc evt_desc
	 */
	public String getEvt_desc() {
		return StringUtil.nvl(this.evt_desc);
	}

	/**
	 * evt_time 설정
	 * @param evt_time evt_time
	 */
	public void setEvt_time(String evt_time) {
		this.evt_time = evt_time;
	}
	/**
	 * evt_time 취득
	 * @return evt_time evt_time
	 */
	public String getEvt_time() {
		return StringUtil.nvl(this.evt_time);
	}
}
