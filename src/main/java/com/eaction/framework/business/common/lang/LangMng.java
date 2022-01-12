/*****************************************************************************
 * 프로그램명  : LangMng.java
 * 설     명  : 언어관리 공통부품
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.02  정세연    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.LanguageInfo;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

import com.eaction.framework.business.common.lang.service.LanguageService;


/**
 * 언어 데이터관리
 * @author eaction
 * @version 1.0
 */
/**
 * @author 정세연
 *
 */
@Component
public class LangMng {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(LangMng.class);    
	/** 예외처리용 클래스 이름 */
	private static final String CLASS_NAME = "LangMng";
	/** 파일의 키와 값의 쌍을 갖는 프로퍼티객체(TB_ECAT_LANG_TRAN_M) */
    private static Properties propTB_ECAT_LANG_TRAN_M = new Properties();
	/** 파일의 키와 값의 쌍을 갖는 프로퍼티객체(TB_ECAT_LANG_TRAN_D) */
    private static Properties propTB_ECAT_LANG_TRAN_D = new Properties();
    /** 파일의 키와 값의 쌍을 갖는 프로퍼티객체(TB_ECAT_LANG_DISPLAY) */
    private static Properties propTB_ECAT_LANG_DISPLAY = new Properties();
	/** 언어코드 기준정보 */
    private static List<CodeInfo> langCode = new ArrayList<CodeInfo>();
    @Autowired
    MessageSourceAccessor messageSourceAccessor;
    @Autowired
    private LanguageService languageService;


    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setTB_ECAT_LANG_TRAN_MProps(Properties props) {
		LangMng.propTB_ECAT_LANG_TRAN_M = props;
	}

	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static Properties getTB_ECAT_LANG_TRAN_MProps() {
		return propTB_ECAT_LANG_TRAN_M;
	}
	
    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setTB_ECAT_LANG_TRAN_DProps(Properties props) {
		LangMng.propTB_ECAT_LANG_TRAN_D = props;
	}

	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static Properties getTB_ECAT_LANG_TRAN_DProps() {
		return propTB_ECAT_LANG_TRAN_D;
	}

    /**
     * 프로퍼티 저장
     * @param props
     */
    public static void setLangCode(List<CodeInfo> langCode) {
		LangMng.langCode = langCode;
	}

	/**
	 * 프로퍼티 조회
	 * @return
	 */
	public static List<CodeInfo> getLangCode() {
		return langCode;
	}
	
	/**
     * 초기화
     * @throws UserSysException 예외
     */
    public void initialize(){
    	try {
    		//코드데이터 취득처리
    		setTableData();
	    } catch (UserSysException ex) {
	    	logger.error("Cannnot Get Language Table Data!!");
	    	logger.error(StringUtil.stackTraceToString(ex));
	    	throw new RuntimeException (ex);
	    }
    }

    /**
     * 코드테이블 데이터를 관리자 메뉴에서 변경되었을경우 재기동하지 않고 즉시 데이터를 반영한다
     * @throws UserSysException 예외
     */
    public void reloadTableInfo() throws UserSysException{
    	try{
    		
    		if(propTB_ECAT_LANG_TRAN_M != null) {
    			propTB_ECAT_LANG_TRAN_M = null;
    		}
    		propTB_ECAT_LANG_TRAN_M = new Properties();
    		
    		if(propTB_ECAT_LANG_TRAN_D != null) {
        		propTB_ECAT_LANG_TRAN_D = null;
    		}
    		propTB_ECAT_LANG_TRAN_D = new Properties();
    		
    		if(propTB_ECAT_LANG_DISPLAY != null) {
    			propTB_ECAT_LANG_DISPLAY = null;
    		}
    		propTB_ECAT_LANG_DISPLAY = new Properties();
    		
    
    		if(langCode != null) {
    			langCode = null;
    		}
    		langCode = new ArrayList<CodeInfo>();
    		
    		setTableData();

        }catch(UserSysException e){
            throw e;
        }
    }


    /**
     * 테이블에서 시스템그룹코드 관련 데이터를 취득해서 프로퍼티에 설정한다
     */
    private void setTableData() throws UserSysException {
    	List<LanguageInfo> arDList = null;
    	List<LanguageInfo> arMList = null;
    	List<LanguageInfo> arDisplayList = null;
        try {
        	//Language Code 취득
        	langCode = languageService.getLangCode();

        	//테이블 데이터 취득
        	arDList = languageService.getLangDInfo();
        	
        	if (arDList != null) {
        	    //코드,명칭데이터를 프로퍼티에 설정한다
        	    setLangDNameValues(arDList);
        	}
        	
        	arMList = languageService.getLangMInfo();
        	
        	if (arMList != null) {
        	    //메세지코드,명칭데이터를 프로퍼티에 설정한다
        	    setLangMNameValues(arMList);
        	}
        	
//        	arDisplayList = languageService.getLangDisplayInfo();
//        	
//        	if (arDisplayList != null) {
//        	    //메세지코드,명칭데이터를 프로퍼티에 설정한다
       	    setLangDisplayNameValues(arMList, arDList);
//        	}
    	} catch (UserSysException ex) {
    		throw ex;
    	}
    }

    
    /**
     * key와 값의 쌍을 프로퍼티 객체설정
     * @param : arList DB에서 조회한 정보
     */
    private static void setLangDNameValues(List<LanguageInfo> arList) {
    	
    	LanguageInfo languageInfo = null;
		Properties defLang = null;

		// 영어가 기본값 영어부터 셋팅
		List<LanguageInfo> defaultLang = arList.stream().filter(a -> a.getLang_cd().equals(ConstKey.LANG_ENG)).collect(Collectors.toList());
		defLang = new Properties();
		for (int i = 0; i < defaultLang.size(); i++){
    		languageInfo = (LanguageInfo)defaultLang.get(i);
    		defLang.put(languageInfo.getProp(), languageInfo.getTran_ln());
    	}
		propTB_ECAT_LANG_TRAN_D.put(ConstKey.LANG_ENG, defLang);
		
    	for(CodeInfo lang : langCode) {
    		if(ConstKey.LANG_ENG.equals(lang.getCode())) {
    			continue;
    		}
    		List<LanguageInfo> result = arList.stream().filter(a -> a.getLang_cd().equals(lang.getCode())).collect(Collectors.toList());
    		Properties propLang = new Properties();
    		for (int i = 0; i < result.size(); i++){
    			languageInfo = (LanguageInfo)result.get(i);
        		if(!"".equals(StringUtil.nvl((String)defLang.get(languageInfo.getProp()))) || !"".equals(StringUtil.nvl(languageInfo.getTran_ln()))) {
        			propLang.put(languageInfo.getProp(), StringUtil.nvl(languageInfo.getTran_ln(), (String)defLang.get(languageInfo.getProp())));
        		}
        	}
    		propTB_ECAT_LANG_TRAN_D.put(lang.getCode(), propLang);
    	}
    }
    
    /**
     * key와 값의 쌍을 프로퍼티 객체설정
     * @param : arList DB에서 조회한 정보
     */
    private static void setLangMNameValues(List<LanguageInfo> arList) {

		for (int i = 0; i < arList.size(); i++){
			LanguageInfo languageInfo = (LanguageInfo)arList.get(i);
			propTB_ECAT_LANG_TRAN_M.put(languageInfo.getProp(), languageInfo.getTr_nm());
    	}
		
    }

    /**
     * key와 값의 쌍을 프로퍼티 객체설정
     * @param : arList DB에서 조회한 정보
     */
    private static void setLangDisplayNameValues(List<LanguageInfo> arMList, List<LanguageInfo> arDList) {
    	
    	LanguageInfo languageInfo = null;
		Properties defLang = null;
		try {
	    	for(int nLang= 0; nLang<getLangCodeList().size(); nLang++) {
	    		CodeInfo lang = getLangCodeList().get(nLang);
	    		List<LanguageInfo> langD = arDList.stream().filter(a -> a.getLang_cd().equals(lang.getCode())).collect(Collectors.toList());
	    		JSONObject propLang = new JSONObject();
	    		for (int i = 0; i < arMList.size(); i++){
	    			LanguageInfo m_lang = (LanguageInfo)arMList.get(i);
	    			List<LanguageInfo> d_lang = langD.stream().filter(a -> a.getProp().equals(m_lang.getProp())).collect(Collectors.toList());
	        		if(d_lang != null && d_lang.size() > 0 && !"".equals(d_lang.get(0).getTran_ln())) {
	        			propLang.put(d_lang.get(0).getProp(), d_lang.get(0).getTran_ln());
	        		}else {
	        			propLang.put(m_lang.getProp(), m_lang.getTr_nm());
	        		}
	        	}
	    		propTB_ECAT_LANG_DISPLAY.put(lang.getCode(), propLang);
	    	}
		}catch(Exception ex) {
			logger.debug(ex.getStackTrace());
		}
    }
    
    /**
     * TB_ECAT_LANG_TRAN_M 언어조회
     * @param : propKey
     * @param : locale
     * @return : 언어값 
     */
    public static String LANG_M(String propKey) {
    	
    	String language = (String) propTB_ECAT_LANG_TRAN_M.get(propKey);
		return language;
    }
    
    /**
     * TB_ECAT_LANG_TRAN_D 언어조회
     * @param : propKey
     * @param : locale
     * @return : 언어값 
     */
    public static String LANG_D(String propKey, String locale) {    	
    	String language = null;
    	if(propTB_ECAT_LANG_TRAN_D != null && propTB_ECAT_LANG_TRAN_D.containsKey(locale)) {
    		Properties propLang = (Properties) propTB_ECAT_LANG_TRAN_D.get(locale);
    		if(propLang == null || propLang.size() == 0 || !propLang.containsKey(propKey) || "".equals(StringUtil.nvl((String)propLang.get(propKey)))) {
    			language = LANG_M(propKey);
    		}else {
    			language = (String)propLang.get(propKey);
    		}
    	}else {
    		language = LANG_M(propKey);
    	}

		return language;
    }
    
    /**
     * TB_ECAT_LANG_TRAN_D 언어조회
     * @param : tr_cd 언어정의코드값
     * @param : locale 언어
     * @return : 언어값 
     */
    public static JSONObject LANG_DISPLAY(String locale) {

    	JSONObject language = (JSONObject) propTB_ECAT_LANG_DISPLAY.get(locale);

		return language;
    }
    
    /**
     * Language Code 리스트 조회
     * @param : propKey
     * @param : locale
     * @return : 언어값 
     */
    private static List<CodeInfo> getLangCodeList() {
		return langCode;
    }
}
