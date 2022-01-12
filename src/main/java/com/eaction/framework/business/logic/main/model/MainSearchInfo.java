/*****************************************************************************
 * 프로그램명  : MainSearchInfo.java
 * 설     명  : 메인화면 표시 데이터 검색객체
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.02   LYS    1.0     초기작성
 ******************************************************************************/

package com.eaction.framework.business.logic.main.model;

import org.apache.ibatis.type.Alias;

import com.eaction.framework.common.jqgrid.JqGridVO;
import com.eaction.framework.common.util.StringUtil;

/**
 * 메인대쉬보드 검색조건 정의
 * @author eaction
 *
 */
@Alias("MainSearchInfo")
public class MainSearchInfo extends JqGridVO{

}
