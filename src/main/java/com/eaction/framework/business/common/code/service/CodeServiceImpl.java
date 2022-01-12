/*****************************************************************************
 * 프로그램명  : CodeServiceImpl.java
 * 설     명  : 코드정보관리 비즈니스 로직구현체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.common.code.service;

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
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONObject;

import com.eaction.framework.business.common.code.dao.CodeDao;
import com.eaction.framework.business.system.memory.MemoryConstant;


/**
 * 코드정보관리 비즈니스 로직
 * @author eaction
 * @version 1.0
 */
@Service
public class CodeServiceImpl implements CodeService {
	@Resource
    private CodeDao codeDao;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CodeTableMng codeTableMng;
	
    /**
	 * 코드 리스트 취득
	 * @return List 코드리스트를 담은 객체
	 */
	@Override
	public List getCodeInfo() throws UserSysException {
		//코드의 총갯수를 취득
		List codeList = codeDao.getCodeInfo();

        return codeList;
	}

	/**
	 * 상위가 있는 코드 리스트 취득
	 * @return List 상위가 있는 코드리스트를 담은 객체
	 */
	@Override
	public List getUpCodeInfo() throws UserSysException {

        //코드를 취득
		List codeList = codeDao.selectUpCodeInfo();

        return codeList;
	}


	/**
     * 코드그룹정보 취득
	 * @return List 코드그룹정보
	 */
    public List getCodeGroup() throws UserSysException {
    	return codeDao.getCodeGroup();
    }
    
	/**
	 * 기타 마스터 코드 리스트 취득
	 * @return List 코드리스트를 담은 객체
	 */
	@Override
	public Properties getOhterCodeInfo() throws UserSysException {

		Properties props = new Properties();

        //IN파라메터의 공통 Prefix언어정보 취득
		String prefix = StringUtil.getCommonPrefix();

		/*
		 * 필요한 기타코드 관리 로직을 추가한다
		 */
		//코드의 총갯수를 취득
		List codeList = codeDao.getUserGroupCodeInfo(prefix);
		props.put(OtherCodeTableMng.USER_GROUP_CODE, codeList);

        return props;
	}
	
	/**
	 * 국가코드 콤보 조회
	 * @return List 국가코드정보
	 */
	@Override
    public List getNationCodeInfo() throws UserSysException{
    	return codeDao.getNationCodeInfo();
    }
	
	/**
	 * 메모리 리로드
	 */
	@Override
	//@Scheduled(cron="0/5 * * * * ?")
    public void reloadCode() throws UserSysException {
		logger.debug("Memory Loaded Start!!");
		
		try{
			codeTableMng.reloadCodeTableInfo();
				
	   	}catch(Exception e){

	   	}
		logger.debug("Memory Loaded End!!");
    }
	
    /**
	 * 개인정보동의서 조회
	 * @return CodeInfo 개인정보동의서
	 */
	@Override
    public CodeInfo getPrivacyInfo(String lang_code) throws UserSysException{
		return codeDao.getPrivacyInfo(lang_code);
    }
}