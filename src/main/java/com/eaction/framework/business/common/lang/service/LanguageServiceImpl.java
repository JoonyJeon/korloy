/*****************************************************************************
 * 프로그램명  : CodeServiceImpl.java
 * 설     명  : 코드정보관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.lang.service;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.code.OtherCodeTableMng;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.LanguageInfo;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

import com.eaction.framework.business.common.code.dao.CodeDao;
import com.eaction.framework.business.common.lang.dao.LanguageDao;
import com.eaction.framework.business.system.memory.MemoryConstant;


/**
 * 코드정보관리 비즈니스 로직
 * @author eaction
 * @version 1.0
 */
@Service
public class LanguageServiceImpl implements LanguageService {
	@Resource
    private LanguageDao languageDao;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CodeTableMng codeTableMng;
	
	/**
	 * TB_ECAT_LANG_TRAN_M리스트 취득
	 * @return List TB_ECAT_LANG_TRAN_M 객체
	 */
	@Override
    public List<LanguageInfo> getLangMInfo() throws UserSysException {
    	return languageDao.getLangMInfo();
    }

    /**
	 * TB_ECAT_LANG_TRAN_D리스트 취득
	 * @return List TB_ECAT_LANG_TRAN_D 객체
	 */
    @Override
	public List<LanguageInfo> getLangDInfo() throws UserSysException {
		return languageDao.getLangDInfo();
	}
	
    /**
	 * TB_ECAT_LANG_DISPLAY리스트 취득
	 * @return List TB_ECAT_LANG_DISPLAY 객체
	 */
//    @Override
//	public List<LanguageInfo> getLangDisplayInfo() throws UserSysException {
//		return languageDao.getLangDisplayInfo();
//	}
	
    /**
	 * TB_ECAT_LANG리스트 취득
	 * @return List TB_ECAT_LANG 객체
	 */
    @Override
	public List<CodeInfo> getLangCode() throws UserSysException {
    	return languageDao.getLangCode();
	}
	/**
	 * 메모리 리로드
	 */
	@Override
//	@Scheduled(cron="0/5 * * * * ?")
    public void reloadLanguage() throws UserSysException {
		logger.debug("Memory Loaded Start!!");

		
		try{
			codeTableMng.reloadCodeTableInfo();
				
	   	}catch(Exception e){

	   	}
		logger.debug("Memory Loaded End!!");
    }
}