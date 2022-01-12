/*****************************************************************************
 * 프로그램명  : UpCodeTableMng.java
 * 설     명  : 상위가 있는 코드테이블관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eaction.framework.common.model.GroupCodeInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.util.StringUtil;
import com.eaction.framework.business.common.code.service.CodeService;

/**
 * 상위가 있는 코드테이블 데이터관리（코드테이블,회사정보용）Bean
 * @author eaction
 * @version 1.0
 */
@Component
public class UpCodeTableMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(UpCodeTableMng.class);    
	/** 파일의 키와 값의 쌍을 갖는 메세지프로퍼티객체 */
    private static Properties props = new Properties();
    /** 리스트데이터를 취득하기 위한 리스트객체 */
    private static Properties listProps = new Properties();
    @Autowired
    private CodeService codeService;

    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setProps(Properties props) {
    	UpCodeTableMng.props = props;
	}
    
	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static Properties getProps() {
		return props;
	}
	
    public static Properties getListProps() {
		return listProps;
	}

	public static void setListProps(Properties listProps) {
		UpCodeTableMng.listProps = listProps;
	}

	/**
     * 코드테이블 데이터를 코드 종류별로 전부 취득한다
     * @throws UserSysException 예외
     */
    public void initialize(){
    	try {
    		//코드데이터 취득처리
    		setTableData();
	    } catch (UserSysException ex) {
	    	logger.error("Cannnot Get Group Code Table Data!!");
	    	logger.error(StringUtil.stackTraceToString(ex));
	    	throw new RuntimeException (ex);
	    }
    }

    /**
     * 코드테이블 데이터를 관리자 메뉴에서 변경되었을경우 재기동하지 않고 즉시 데이터를 반영한다
     * @throws UserSysException 예외
     */
    public void reloadUpCodeTableInfo() throws UserSysException{
    	try{
    	    props = new Properties();
    	    listProps = new Properties();
    		setTableData();

        }catch(UserSysException e){
            throw e;
        }
    }

    /**
     * 테이블에서 시스템그룹코드 관련 데이터를 취득해서 프로퍼티에 설정한다
     */
    private void setTableData() throws UserSysException {
    	List arList = null;
        try {
        	//테이블 데이터 취득
        	arList = codeService.getUpCodeInfo();
        	if (arList != null) {
        	    //메세지코드,명칭데이터를 프로퍼티에 설정한다.
        		setCodeNameValues(arList);
        	}
    	} catch (UserSysException ex) {
    		throw ex;
    	}
    }

    /**
     * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
     * @param : conf_File 파일 객체
     */
    private static void setCodeNameValues(List arList) {
    	GroupCodeInfo groupCodeInfo = null;
    	Properties levProp = null;
    	Properties subProp = null;
    	Properties levArProp = null;
    	List arSub = null;
    	boolean bPCheck = false;
    	boolean bCheck = false;
    	boolean bPListCheck = false;
    	boolean bListCheck = false;
    	for (int i = 0; i < arList.size(); i++){
    		groupCodeInfo = (GroupCodeInfo)arList.get(i);
    		/*
    		 * 코드로 명칭을 취득할 수 있는 프로퍼티설정 로직
    		 */
    		levProp = (Properties)props.get(groupCodeInfo.getGroup());
    		bPCheck = false;
    		if (levProp == null) {
    			levProp = new Properties();
    			bPCheck = true;
    		}
    		subProp = (Properties)levProp.get(groupCodeInfo.getUpCode());
    		bCheck = false;
    		if (subProp == null) {
    			subProp = new Properties();
    			bCheck = true;
    		}
    		subProp.put(groupCodeInfo.getCode(), StringUtil.nvl(groupCodeInfo.getName()));

    	    if (bCheck) {
    	    	levProp.put(groupCodeInfo.getUpCode(), subProp);
    	    }

    	    if (bPCheck) {
    	    	props.put(groupCodeInfo.getGroup(), levProp);
    	    }

    	    /*
    		 * 그룹코드로 코드,명칭객체의 쌍을 취득할 수 있는 ArrayList설정 로직
    		 */
    	    levArProp = (Properties)listProps.get(groupCodeInfo.getGroup());
    	    bPListCheck = false;
    	    if (levArProp == null) {
    	    	levArProp = new Properties();
    	    	bPListCheck = true;
    		}

    	    arSub = (ArrayList)levArProp.get(groupCodeInfo.getUpCode());
    	    bListCheck = false;
    	    if (arSub == null) {
    	    	arSub = new ArrayList();
    			bListCheck = true;
    		}
    	    arSub.add(new CodeInfo(groupCodeInfo.getCode(), groupCodeInfo.getName()));

    	    if (bListCheck) {
    	    	levArProp.put(groupCodeInfo.getUpCode(), arSub);
    	    }

    	    if (bPListCheck) {
    	    	listProps.put(groupCodeInfo.getGroup(), levArProp);
    	    }
    	}
    }

    /**
     * 키에 대한 값을 취득
     * @param user 유저정보
     * @param group 그룹코드
     * @param key 키값
     * @return strReturn 키에대한 값
     */
    public static String getName(String group, String upCode, String key) {
    	String strReturn = "";

    	Properties levelProp = (Properties)props.get(group);
        if (levelProp != null) {
        	Properties subProp = (Properties)levelProp.get(upCode);
        	if (subProp != null) {
        		strReturn = (String)subProp.get(key);
        		if (strReturn != null) {
                    strReturn = strReturn.trim();
                } else {
                	strReturn = "";
                }
        	}
        }
        return strReturn;
    }

    /**
     * 업무키에대한코드명칭데이터BeanObject의리스트데이터취득
     * @param user 유저정보
     * @param group 그룹코드
     * @return List 업무의코드/명칭데이터Bean리스트
     */
    public static List getCodeList(String group, String upCode) {
    	List returnTable = new ArrayList();

    	Properties levArProp = (Properties)listProps.get(group);

    	if (levArProp != null) {
    		returnTable = (List)levArProp.get(upCode);

        	if (returnTable == null) {
        		returnTable = new ArrayList();
        	}
    	}
        return returnTable;
    }

}
