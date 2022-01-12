/*****************************************************************************
 * 프로그램명  : CodeTableMng.java
 * 설     명  : 그룹별코드테이블관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eaction.framework.common.model.GroupCodeInfo;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;
import com.eaction.framework.business.common.base.DownloadController;
import com.eaction.framework.business.common.code.service.CodeService;


/**
 * 그룹별코드테이블 데이터관리（코드테이블,회사정보용）Bean
 * @author eaction
 * @version 1.0
 */
/**
 * @author user
 *
 */
@Component
public class CodeTableMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(CodeTableMng.class);    
	/** 예외처리용 클래스 이름 */
//	private static final String CLASS_NAME = "CodeTableMng";
	/** 파일의 키와 값의 쌍을 갖는 메세지프로퍼티객체(코드/명칭) */
    private static Properties props = new Properties();

    @Autowired
    private CodeService codeService;


    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setProps(Properties props) {
		CodeTableMng.props = props;
	}

	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static Properties getProps() {
		return props;
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
    public void reloadCodeTableInfo() throws UserSysException{
    	try{
    		props = new Properties();
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
    	List codeGroupList = null;
        try {
        	//테이블 데이터 취득
        	arList = codeService.getCodeInfo();
        	codeGroupList = codeService.getCodeGroup();
        	if (arList != null) {
        	    //메세지코드,명칭데이터를 프로퍼티에 설정한다
        	    setCodeNameValues(arList, codeGroupList);
        	}
    	} catch (UserSysException ex) {
    		throw ex;
    	}
    }

    /**
     * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
     * @param : conf_File 파일 객체
     */
    private static void setCodeNameValues(List<CodeInfo> arList, List codeGroupList) {
    	if(arList != null && arList.size() > 0) {
	    	for (int i = 0; i < codeGroupList.size(); i++){
	    		String codeGroup = (String)codeGroupList.get(i);
	    		if(!"".equals(StringUtil.nvl(codeGroup))) {
		    		List<CodeInfo> result = arList.stream().filter(a -> a.getUpCode().equals(codeGroup)) .collect(Collectors.toList());
		    		props.put(codeGroup, result);
	    		}
	    	}
    	}
    }
    
    /**
     * 키에 대한 값을 취득
     * @param group 그룹코드
     * @param key 키값
     * @return strReturn 키에대한 값
     */
    public static String getCodeName(String group, String key) {
    	String strReturn = "";
    	List<CodeInfo> returnTable = (List)props.get(group);
		for( int i=0; i<returnTable.size(); i++ ){
			CodeInfo codeInfo = (CodeInfo)returnTable.get(i);
			if(codeInfo.getCode().equals(key)) {
				strReturn = codeInfo.getName();
			}
		}
        return strReturn;
    }

    public static List<CodeInfo> getCodeList(String group) {
    	List<CodeInfo> returnTable = (List)props.get(group);

    	if (returnTable == null) {
    		returnTable = new ArrayList<CodeInfo>();
    	}

        return returnTable;
    }
    

    /**
     * 명칭에 대한 키를 취득
     * @param user 유저정보
     * @param group 그룹코드
     * @param name 명칭
     * @return strReturn 키에대한 값
     */
    public static String getCode(String group, String name) {
    	String strReturn = "";
    	List<CodeInfo> returnTable = (List)props.get(group);
		for( int i=0; i<returnTable.size(); i++ ){
			CodeInfo codeInfo = (CodeInfo)returnTable.get(i);
			if(codeInfo.getName().equals(name)) {
				strReturn = codeInfo.getCode();
			}
		}
        return strReturn;
    }

}
