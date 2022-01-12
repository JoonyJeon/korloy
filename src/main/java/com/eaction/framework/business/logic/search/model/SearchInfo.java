/*****************************************************************************
 * 프로그램명  : SearchInfo.java
 * 설     명  : 검색 정보 객체
 * 참고  사항  : Mapper xml에서 사용할 Model 클래스의 Alias를 어노테이션으로 정의함
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.08.02   정세연    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.logic.search.model;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.business.logic.app.model.AppInfo;
import com.eaction.framework.common.model.EactReqInfo;
import com.eaction.framework.common.util.StringUtil;


@Alias("SearchInfo")
public class SearchInfo extends AppInfo  implements Cloneable {

	/** 자동완성입력글자 */
	private String auto_input_text = "";
	/** 자동완성검색파라미터 */
	private String auto_param = "";

	/** 파일경로 */
	private String atch_file_url = "";
	/** 파일명 */
	private String atch_file_name = "";

	/** 자동완성문자열 */
	private String auto_complete_text = "";

	/** sort */
	private String sort = "";
	/** gtc_vd_id */
	private String gtc_vd_id = "";
	/** item_cnt */
	private String item_cnt = "";
	/** gtc_vd_pid */
	private String gtc_vd_pid = "";
	/** gtc_vd_node_nm */
	private String gtc_vd_node_nm = "";
	/** 아이템리스트 */
	private String[] ar_item_cd = null;
	/** GTC아이콘 */
	private String gtc_vd_icon = "";
	/** GTC스텝 */
	private String gtc_step = "";
	/** 하위노드리스트 */
	private List sub_node_list = null;
	/** 자동완성모드(A:자동완성, M:키워드입력) */
	private String auto_mode = "";



	/**
	 * 자동완성모드(A:자동완성, M:키워드입력) 설정
	 * @param auto_mode 자동완성모드(A:자동완성, M:키워드입력)
	 */
	public void setAuto_mode(String auto_mode) {
		this.auto_mode = auto_mode;
	}
	/**
	 * 자동완성모드(A:자동완성, M:키워드입력) 취득
	 * @return auto_mode 자동완성모드(A:자동완성, M:키워드입력)
	 */
	public String getAuto_mode() {
		return StringUtil.nvl(this.auto_mode);
	}


	/**
	 * 하위노드리스트 설정
	 * @param sub_node_list 하위노드리스트
	 */
	public void setSub_node_list(List sub_node_list) {
		this.sub_node_list = sub_node_list;
	}
	/**
	 * 하위노드리스트 취득
	 * @return sub_node_list 하위노드리스트
	 */
	public List getSub_node_list() {
		return this.sub_node_list;
	}


	/**
	 * GTC스텝 설정
	 * @param gtc_step GTC스텝
	 */
	public void setGtc_step(String gtc_step) {
		this.gtc_step = gtc_step;
	}
	/**
	 * GTC스텝 취득
	 * @return gtc_step GTC스텝
	 */
	public String getGtc_step() {
		return StringUtil.nvl(this.gtc_step);
	}


	/**
	 * GTC아이콘 설정
	 * @param gtc_vd_icon GTC아이콘
	 */
	public void setGtc_vd_icon(String gtc_vd_icon) {
		this.gtc_vd_icon = gtc_vd_icon;
	}
	/**
	 * GTC아이콘 취득
	 * @return gtc_vd_icon GTC아이콘
	 */
	public String getGtc_vd_icon() {
		return StringUtil.nvl(this.gtc_vd_icon);
	}

    @Override
    public SearchInfo clone(){
        Object obj = null;
        try{
            obj = super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return (SearchInfo) obj;
    }

	/**
	 * 아이템리스트 설정
	 * @param ar_item_cd 아이템리스트
	 */
	public void setAr_item_cd(String[] ar_item_cd) {
		if(ar_item_cd != null){
			this.ar_item_cd = Arrays.copyOf(ar_item_cd, ar_item_cd.length);
		}
	}
	/**
	 * 아이템리스트 취득
	 * @return ar_item_cd 아이템리스트
	 */
	public String[] getAr_item_cd() {
		if(this.ar_item_cd != null){
			return Arrays.copyOf(this.ar_item_cd, this.ar_item_cd.length);
		} else {
			return null;
		}
	}


	/**
	 * sort 설정
	 * @param sort sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * sort 취득
	 * @return sort sort
	 */
	public String getSort() {
		return StringUtil.nvl(this.sort);
	}

	/**
	 * gtc_vd_id 설정
	 * @param gtc_vd_id gtc_vd_id
	 */
	public void setGtc_vd_id(String gtc_vd_id) {
		this.gtc_vd_id = gtc_vd_id;
	}
	/**
	 * gtc_vd_id 취득
	 * @return gtc_vd_id gtc_vd_id
	 */
	public String getGtc_vd_id() {
		return StringUtil.nvl(this.gtc_vd_id);
	}

	/**
	 * item_cnt 설정
	 * @param item_cnt item_cnt
	 */
	public void setItem_cnt(String item_cnt) {
		this.item_cnt = item_cnt;
	}
	/**
	 * item_cnt 취득
	 * @return item_cnt item_cnt
	 */
	public String getItem_cnt() {
		return StringUtil.nvl(this.item_cnt);
	}

	/**
	 * gtc_vd_pid 설정
	 * @param gtc_vd_pid gtc_vd_pid
	 */
	public void setGtc_vd_pid(String gtc_vd_pid) {
		this.gtc_vd_pid = gtc_vd_pid;
	}
	/**
	 * gtc_vd_pid 취득
	 * @return gtc_vd_pid gtc_vd_pid
	 */
	public String getGtc_vd_pid() {
		return StringUtil.nvl(this.gtc_vd_pid);
	}

	/**
	 * gtc_vd_node_nm 설정
	 * @param gtc_vd_node_nm gtc_vd_node_nm
	 */
	public void setGtc_vd_node_nm(String gtc_vd_node_nm) {
		this.gtc_vd_node_nm = gtc_vd_node_nm;
	}
	/**
	 * gtc_vd_node_nm 취득
	 * @return gtc_vd_node_nm gtc_vd_node_nm
	 */
	public String getGtc_vd_node_nm() {
		return StringUtil.nvl(this.gtc_vd_node_nm);
	}



	/**
	 * 자동완성문자열 설정
	 * @param auto_complete_text 자동완성문자열
	 */
	public void setAuto_complete_text(String auto_complete_text) {
		this.auto_complete_text = auto_complete_text;
	}
	/**
	 * 자동완성문자열 취득
	 * @return auto_complete_text 자동완성문자열
	 */
	public String getAuto_complete_text() {
		return StringUtil.nvl(this.auto_complete_text);
	}


	/**
	 * 파일경로 설정
	 * @param atch_file_url 파일경로
	 */
	public void setAtch_file_url(String atch_file_url) {
		this.atch_file_url = atch_file_url;
	}
	/**
	 * 파일경로 취득
	 * @return atch_file_url 파일경로
	 */
	public String getAtch_file_url() {
		return StringUtil.nvl(this.atch_file_url);
	}

	/**
	 * 파일명 설정
	 * @param atch_file_name 파일명
	 */
	public void setAtch_file_name(String atch_file_name) {
		this.atch_file_name = atch_file_name;
	}
	/**
	 * 파일명 취득
	 * @return atch_file_name 파일명
	 */
	public String getAtch_file_name() {
		return StringUtil.nvl(this.atch_file_name);
	}

	/**
	 * 자동완성검색파라미터 설정
	 * @param auto_param 자동완성검색파라미터
	 */
	public void setAuto_param(String auto_param) {
		this.auto_param = auto_param;
	}
	/**
	 * 자동완성검색파라미터 취득
	 * @return auto_param 자동완성검색파라미터
	 */
	public String getAuto_param() {
		return StringUtil.nvl(this.auto_param);
	}


	/**
	 * 자동완성입력글자 설정
	 * @param auto_input_text 자동완성입력글자
	 */
	public void setAuto_input_text(String auto_input_text) {
		this.auto_input_text = auto_input_text;
	}
	/**
	 * 자동완성입력글자 취득
	 * @return auto_input_text 자동완성입력글자
	 */
	public String getAuto_input_text() {
		return StringUtil.nvl(this.auto_input_text);
	}

}
