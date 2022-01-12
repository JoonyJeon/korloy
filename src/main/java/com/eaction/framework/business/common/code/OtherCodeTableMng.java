/*****************************************************************************
 * 프로그램명  : OtherCodeTableMng.java
 * 설     명  : 그룹별코드테이블 이외의 마스터 정보에 해당하는 리스트 관리 
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

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.UserGroupInfo;
import com.eaction.framework.common.util.StringUtil;
import com.eaction.framework.business.common.code.service.CodeService;

/**
 * 그룹별코드테이블 이외의 마스터 정보에 해당하는 리스트 관리Bean
 * @author eaction
 * @version 1.0
 */
@Component
public class OtherCodeTableMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(OtherCodeTableMng.class);    
	/** 파일의 키와 값의 쌍을 갖는 메세지프로퍼티객체(코드/명칭) */
    private static Properties props = new Properties();  
    /** 리스트데이터를 취득하기 위한 리스트객체 */
    private static Properties listProps = new Properties();  
    /** 유저그룹코드 */
    public static String USER_GROUP_CODE = "USER_GROUP_CODE";
    @Autowired
    private CodeService codeService;
        
    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setProps(Properties props) {
    	OtherCodeTableMng.props = props;
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
		OtherCodeTableMng.listProps = listProps;
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
     * 메뉴테이블 데이터를 메뉴정보등의 관리자 메뉴에서 변경되었을경우 재기동하지 않고 즉시 데이터를 반영한다
     * @throws UserSysException 예외
     */
    public void reloadMenuInfo() throws UserSysException{        
    	try{
    		listProps = new Properties();
    		props = new Properties();
    		
        	//회사코드,명칭데이터 취득하여 설정
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
        	Properties tempProps = codeService.getOhterCodeInfo();	
        	if (listProps != null) {
        	    //메세지코드,명칭데이터를 프로퍼티에 설정한다
        	    setCodeNameValues(tempProps); 
        	}
    	} catch (UserSysException ex) {
    		throw ex;
    	}
    }
    
    /**
     * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
     */
    private static void setCodeNameValues(Properties tempProps) {  
    	UserGroupInfo userGroupInfo = null;
    	
    	/*
    	 * 유저그룹코드 리스트 데이터 처리
    	 */
    	List arUserGroupList = (List)tempProps.get(USER_GROUP_CODE);
    	    	
    	Properties subProp = new Properties(); 
    	List arList = new ArrayList();
    	for (int i = 0; i < arUserGroupList.size(); i++){
    		userGroupInfo = (UserGroupInfo)arUserGroupList.get(i);    	
            //코드로 명칭을 취득할 수 있는 프로퍼티설정 로직
    		subProp.put(userGroupInfo.getCode(), userGroupInfo); 
    		arList.add(new CodeInfo(userGroupInfo.getCode(), userGroupInfo.getName()));
    	}        
    	props.put(USER_GROUP_CODE, subProp); 
    	listProps.put(USER_GROUP_CODE, arList);
    }
     
    /**
     * 키에 대한 값을 취득(코드명칭)
     * @param user 유저정보 
     * @param group 그룹코드
     * @param key 키값
     * @return strReturn 키에대한 값
     */
    public static String getName(String userGroup, String key) {
    	String strReturn = "";
    	
    	Properties subProp = (Properties)props.get(userGroup);
        if (subProp != null) {
    		UserGroupInfo userGroupInfo = (UserGroupInfo)subProp.get(key);
    		if (userGroupInfo != null) {
                strReturn = userGroupInfo.getName(); 
            } else {
            	strReturn = "";
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
    public static List getCodeList(String userGroup) {
    	List returnTable = (List)listProps.get(userGroup);    	
    	
    	if (returnTable == null) {
    		returnTable = new ArrayList();
    	}
    	
        return returnTable;
    }
    
}
