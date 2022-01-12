/*****************************************************************************
 * 프로그램명  : BasicService.java
 * 설     명  : 기본데이터 관리 정보
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.07.07   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basic.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.eaction.framework.business.logic.basic.model.BasicLogInfo;
import com.eaction.framework.business.logic.basic.model.BasicSearchInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.AuthInfo;
import com.eaction.framework.common.model.CodeInfo;

import net.sf.json.JSONObject;


/**
 * 기본데이터 서비스
 *
 * @author  eaction
 * @version 1.0
 */
public interface BasicService {

    /**
	 * 사용자권한   취득
	 * @param SearchInfo 검색데이타
	 * @return List 사용자권한정보
	 */
    public AuthInfo selectUserAuthInfo(String user_group);
    
    public void doLog(HttpServletRequest request, String eventCode, String item, String content);
    
    public void doLog(HttpServletRequest request, String eventCode, String item, String content, String user_id);
    
    public String getClientIP(HttpServletRequest request);
}
