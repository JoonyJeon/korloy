/*****************************************************************************
 * 프로그램명  : BasicDao.java
 * 설     명  : 공통 처리 연관 Database처리를 위한 관리 DAO
 * 참고  사항  : 자동Mapping 처리로 xml에 정의한 대로 구현체 클래스는 자동생성 된다
 *          xml의 namespace에 구현체 클래스 생성을 위한 본 인터페이스의 풀패키지와 인터페이스 명칭을 기술한다
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.07.07   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basic.dao;

import java.util.List;

import com.eaction.framework.business.logic.basic.model.BasicLogInfo;
import com.eaction.framework.business.logic.basic.model.BasicSearchInfo;
import com.eaction.framework.business.logic.basic.model.LogInfo;
import com.eaction.framework.common.exception.BizException;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.model.AuthInfo;
import com.eaction.framework.common.model.CodeInfo;


/**
 * 공통처리를 위한  관리 DAO
 *
 * @author  eaction
 * @version 1.0
 */
public interface BasicDao {
		
    /**
	 * 사용자권한  취득
	 * @param user_group 검색데이타
	 * @return List 사용자권한정보
	 * 
	 */
    public AuthInfo selectUserAuthInfo(String user_group);
    
    /**
	 * 로그 저장
	 * @param LogInfo 로그정보
	 */
    public int doLog(LogInfo info);
}
