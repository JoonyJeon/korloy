/*****************************************************************************
 * 프로그램명  : MainServiceImpl.java
 * 설     명  : 메인화면 표시 데이터 취득 서비스구현객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.02   LYS    1.0     초기작성
 ******************************************************************************/
package com.eaction.framework.business.logic.main.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.util.StringUtil;

import net.sf.json.JSONArray;

import com.eaction.framework.business.logic.main.dao.MainDao;
import com.eaction.framework.business.logic.main.model.MainInfo;
import com.eaction.framework.business.logic.main.model.MainSearchInfo;

@Service
public class MainServiceImpl implements MainService{
	@Resource
	private MainDao mainDao;
	

}
