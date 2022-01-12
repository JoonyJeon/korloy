/*****************************************************************************
 * 프로그램명  : ConfigMng.java
 * 설     명  : 프로퍼티 파일 취득 빈
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2019.08.02   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.common.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 프로퍼티 파일 관리빈 
 * @author eaction
 * @version 1.0
 */
public class ConfigMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(ConfigMng.class);    
	/** 클래스 명칭 */
	private static final String CLASS_NAME = "ConfigMng";	
	/** 프로퍼티 파일 명칭 */
	private static final String DEFAULT_CONFIG_FILE_NAME = "environment.properties";	
	/** 파일의 키와 값의 쌍을 갖는 프로퍼티객체 */
    private static Properties props = new Properties();
    /** 취득하려는 파일 명칭 */
    private static String strFileName = "";    
    /** 최종갱신되었는지를 저장한다 */
    private static long last_modified = 0;
    
    /**
     * ȯ�프로퍼티 관리빈의 초기화 
     */
	static {
    	try {
    		//파일 전체경로취득
        	String fullFilePath = getFullFilePath();
        	//파일이름 설정
        	setFileName(fullFilePath);
            //없는경우 신규생성
        	initialize();
        } catch (Exception ex) {     
        	logger.error("Cannnot Get Environment File Data!!");
        	logger.error(StringUtil.stackTraceToString(ex));
    		throw new RuntimeException(ex);
        }
    }    

    /**
     * 프로퍼티 파일 풀패스 취득
     */
	private static String getFullFilePath() throws UserSysException {
		String str_filePath = "";
    	
	    URL url = ConfigMng.class.getClassLoader().getResource(DEFAULT_CONFIG_FILE_NAME);
	    str_filePath = url.getPath();  
        
        // 경로에 공백이 있으면 아스키코드 %20으로 받아온다, 그거 변환해주는 코드
        String filePath=str_filePath.replaceAll("%20"," ");
        
        try {
            if (StringUtil.nvl(filePath) == "") {            	
            	throw new UserSysException(CLASS_NAME, "getFullFilePath");
            }
    	} catch (UserSysException ex) {
    		throw ex;
    	} 
        return filePath;
    }
    
    /**
     * 프로퍼티 관리빈 초기화설정
     */
    private static void initialize() throws UserSysException {    	
    	setInitFileData();
    }
    
    /**
     * 파일을 취득하여 파일 객체를 생성
     */
    private static void setInitFileData() throws UserSysException{
        try{
            //파일객체생성
            File confFile = new File(getFileName());
            //파일을 읽을수 없는 경우 에러
            if(!confFile.canRead()){
            	throw new UserSysException(CLASS_NAME, "setInitFileData");
            }
            //ȯ�프로퍼티값들을 취득 하는 함수 호출
            setConfigValues(confFile);
        }catch(UserSysException ex){
            throw ex;
        }catch(Exception ex){
        	throw new UserSysException(CLASS_NAME, "setInitFileData",  ex);
        }// try
    }
    /**
     * 기    능 : 파일이 새로 갱신되었는지를 체크해서 갱신되었으면 새로 읽어들인다.
     */
//    private static void checkModified() throws UserSysException{        
//        try{
//            //파일객체생성
//            File confFile = new File(getFileName());
//            //파일을 읽을수 없는 경우 에러
//            if(!confFile.canRead()){
//            	throw new UserSysException(CLASS_NAME, "checkModified");
//            }
//            //프로퍼티가 설정되어있는지 파일이 새로 갱신되었는지 확인한다.
//            if((last_modified != confFile.lastModified()) || props == null ){
//                //환경설정파일
//                setConfigValues(confFile);
//            }
//        }catch(UserSysException ex){
//            throw ex;
//        }catch(Exception ex){
//        	throw new UserSysException(CLASS_NAME, "checkModified",  ex);
//        }// try
//    }
    
    /**
     * 키에 대한 값을 취득
     * @param key 키값
     * @return strReturn 키에대한 값
     */
    public static String getValue(String key) {    	
        String strReturn = "";

        if(key != null && props.get(key) != null){
        	strReturn = (String)props.get(key);
            strReturn = strReturn.trim(); 
        }
        return strReturn;
    }
    
    /**
     * 키에 대한 값을 취득(정수값취득)
     * @param key 키값
     * @return strReturn 키에대한 값
     */
    public static int getValueInt(String key) {
        int nValue = 0;
        String value = getValue(key);
        if (!value.equals("")) {
            try {
                nValue = Integer.parseInt(value);
            } catch (NumberFormatException nfe) {
                nValue = 0;
            }
        }
        return nValue;
    }
    
    /**
     * 파일로 부터 key와 값의 쌍을 프로퍼티 객체로 취득한다
     * @param : conf_File 파일 객체
     */
    private static void setConfigValues(File conf_File) throws UserSysException{
    	FileInputStream conf_in = null;
        try{
        	conf_in = new FileInputStream(conf_File);
            props.load(new BufferedInputStream(conf_in));            
            last_modified = conf_File.lastModified();
        }catch(FileNotFoundException ex){
        	throw new UserSysException(CLASS_NAME, "setConfigValues",  ex);
        }catch(IOException ex){
        	throw new UserSysException(CLASS_NAME, "setConfigValues",  ex);
        } finally {        	
        	closeResource(conf_in);
        }
    }
    
    /**
     * 파일스트림 리소스를 해제한다
     * @param conf_in 파일 스트링 객체
     */
    private static void closeResource(FileInputStream conf_in) {
    	try {
    	    if (conf_in != null) {
    	        conf_in.close();
    	    }
    	} catch (IOException ex) {
    		Log logger = LogFactory.getLog(ConfigMng.class);
            logger.error("환경정의 파일 리소스 해제 처리시 에러가 발생했습니다.");
    	}    	
    }
    
    //Locale 취득
    public static String getLang(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String comm_lang = (String)session.getAttribute(ConstKey.SESSION_LANG);
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		// 세션에 설정된 언어가 없고 
		if("".equals(StringUtil.nvl(comm_lang))) {
			// 로그인유저이면 유저의 언어로 설정
			if(userInfo != null) {
				comm_lang = userInfo.getUser_lang();				
			}
			// 로그인유저의 언어가 없으면 ENG로 설정
			if("".equals(StringUtil.nvl(comm_lang))) {
				comm_lang = "ENG";
			}else {
				session.setAttribute(ConstKey.SESSION_LANG, comm_lang);
			}
		}
		
    	return comm_lang;
    }
    
    //Currency 통화코드 취득
    public static String getCurrency(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String comm_currency = (String)session.getAttribute(ConstKey.SESSION_CURRENCY);
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		// 세션에 설정된 언어가 없고 
		if("".equals(StringUtil.nvl(comm_currency))) {
			// 로그인유저이면 유저의 언어로 설정
			if(userInfo != null) {
				comm_currency = userInfo.getCurr_cd();
			}
			// 로그인유저의 언어가 없으면 ENG로 설정
			if("".equals(StringUtil.nvl(comm_currency))) {
				comm_currency = "CC0077";
			}
		}
		
    	return comm_currency;
    }
    
    // 길이단위 취득
    public static String getSizeUnit(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String comm_size_unit = (String)session.getAttribute(ConstKey.SESSION_SIZE_UNIT);
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		// 세션에 설정된 언어가 없고 
		if("".equals(StringUtil.nvl(comm_size_unit))) {
			// 로그인유저이면 유저의 언어로 설정
			if(userInfo != null) {
				comm_size_unit = userInfo.getUnit_cd();
			}
			// 로그인유저의 언어가 없으면 ENG로 설정
			if("".equals(StringUtil.nvl(comm_size_unit))) {
				comm_size_unit = "CC0001";
			}
		}
		
    	return comm_size_unit;
    }
    
    // 국가 취득
    public static String getNation(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String comm_nation = (String)session.getAttribute(ConstKey.SESSION_NATION);
		User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
		// 세션에 설정된 언어가 없고 
		if("".equals(StringUtil.nvl(comm_nation))) {
			// 로그인유저이면 유저의 언어로 설정
			if(userInfo != null) {
				comm_nation = userInfo.getUser_nation();
			}
			// 로그인유저의 언어가 없으면 ENG로 설정
			if("".equals(StringUtil.nvl(comm_nation))) {
				comm_nation = "KOR";
			}
		}
		
    	return comm_nation;
    }
    
    public static String getLanguage(Locale locale) {    	
    	String language = ConfigMng.getValue(IPropertyKey.DEF_LOCALE);
    	
    	if(locale == null) {
    		return language;
    	}
    	
    	if(ConstKey.LOCALE_KOR.equals(locale.getLanguage())) {
    		language = ConstKey.LANG_KOR;
    	} else if(ConstKey.LOCALE_ENG.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_ENG;
    	} else if(ConstKey.LOCALE_JPN.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_JPN;
    	} else if(ConstKey.LOCALE_DEU.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_DEU;
    	} else if(ConstKey.LOCALE_FRA.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_FRA;
    	} else if(ConstKey.LOCALE_POR.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_POR;
    	} else if(ConstKey.LOCALE_RUS.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_RUS;
    	} else if(ConstKey.LOCALE_SPA.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_SPA;
    	} else if(ConstKey.LOCALE_THA.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_THA;
    	} else if(ConstKey.LOCALE_TUR.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_TUR;
    	} else if(ConstKey.LOCALE_VIE.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_VIE;
    	} else if(ConstKey.LOCALE_ZHO.equals(locale.getLanguage())) {
        	language = ConstKey.LANG_ZHO;
        // 등록된 언어가 아니면 영어
    	} else {
        	language = ConstKey.LANG_ENG;
        }
    	
    	return language;
    }
    /** 
     * 파일이름을 취득한다
     * @return String 파일명칭
     */
    private static String getFileName() {
        return strFileName;
    }
    
    /**
     * 파일 이름을 설정한다
     * @param fileName 파일이름
     */
    private static void setFileName(String fileName) {   
        strFileName = fileName;       
    }    
}
