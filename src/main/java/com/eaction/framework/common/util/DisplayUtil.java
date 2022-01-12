/*****************************************************************************
 * 프로그램명  : DisplayUtil.java
 * 설     명  : 화면표시부품(권한에따라화면표시제어)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.eaction.framework.business.common.code.CodeTableMng;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.User;


/**
 * 화면에출력하는HTML문자열을구성하고편집관리하는기능
 * @author  eaction
 * @version 1.0
 */
public class DisplayUtil {
	
	/**
	 * 메뉴에서 레벨당 들여쓰기를 설정한다
	 * @param level 메뉴레벨
	 * @return String 결과문자열
	 */
	public static String printMenuTitle(int level) {
		StringBuffer sbLevel = new StringBuffer();

		for (int i = 1; i < level; i++) {
			sbLevel.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		}

		return sbLevel.toString();
	}

	/**
	 * 게시판에서 레벨당 들여쓰기를 설정한다
	 * @param depth 메뉴레벨
	 * @return String 결과문자열
	 */
	public static String printBoardTitle(int depth) {
		StringBuffer sbLevel = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			sbLevel.append("&nbsp;&nbsp;");
		}
		if(depth >= 1) {
			sbLevel.append("<img src=\"");
			sbLevel.append(ConfigMng.getValue(IPropertyKey.PATH_IMG));
			sbLevel.append("/icon_reply01.gif\">");
			sbLevel.append("<font style=\"font:7pt Tahoma;color:#ff5555;\">Re)</font>");
        }
		return sbLevel.toString();
	}

	/**
	 * 게시판에서 첨부파일이 있는 경우를 설정한다
	 * @param depth 메뉴레벨
	 * @return String 결과문자열
	 */
	public static String printAttachImage(int refFilesCnt) {
		StringBuffer sbAttach = new StringBuffer();
		if(refFilesCnt > 0) {
			sbAttach.append("<img src=\"");
			sbAttach.append(ConfigMng.getValue(IPropertyKey.PATH_IMG));
			sbAttach.append("/s_attach.gif\" align=\"absmiddle\">");
	    } else {
	    	sbAttach.append("&nbsp;");
	    }
		return sbAttach.toString();
	}

	/**
	 * 게시판에서 첨부파일이 있는 경우를 설정한다
	 * @param depth 메뉴레벨
	 * @return String 결과문자열
	 */
	public static String printLineCount(int bbsLinesCnt) {
		StringBuffer sbLine = new StringBuffer();
		if(bbsLinesCnt > 0){
			sbLine.append("<span style=\"font-size:10px;color:#E76322\">(");
			sbLine.append(bbsLinesCnt);
			sbLine.append(")</span>");
		}
		return sbLine.toString();
	}

	/**
	 * 게시판에서 첨부파일이 있는 경우를 설정한다
	 * @param depth 메뉴레벨
	 * @return String 결과문자열
	 */
	public static String printDeptName(String deptName, String upDeptName) {
		StringBuffer sbName = new StringBuffer();
		if(!"".equals(StringUtil.nvl(deptName))){
			sbName.append(" (");
			sbName.append(upDeptName);
			sbName.append(" ");
			sbName.append(deptName);
			sbName.append(")");
		}
		return sbName.toString();
	}

	/**
	 * 공지사항 여부에 따라 게시판의 글분류를 정한다
	 * @param notice 공지사항여부(1:공지사항지정,0:일반)
	 * @return String 결과문자열
	 */
	public static String printNotice(String notice) {
		String name = "일반";
		if("1".equals(StringUtil.nvl(notice))){
			name = "공지";
		}
		return name;
	}

	/**
	 * 관리자 여부에 따라 권한종류를 정한다
	 * @param notice 관리자여부(1관리자,0:일반)
	 * @return String 결과문자열
	 */
	public static String printAdminName(String admin) {
		String name = "관리자";
		if("0".equals(StringUtil.nvl(admin))){
			name = "일반";
		}
		return name;
	}

	/**
	 * 상태명을 정한다
	 * @param notice 상태(0관리자,0:일반)
	 * @return String 결과문자열
	 */
	public static String printStatusName(String status) {
		String name = "마스터";
		if("0".equals(StringUtil.nvl(status))){
			name = "온라인";
		}
		return name;
	}

	/**
	 * 상태명을 정한다
	 * @param notice 상태(0관리자,0:일반)
	 * @return String 결과문자열
	 */
	public static String printLineColor(int no) {
		String name = "";
		if((no % 2) == 1){
			name = "class=\"bg_Gray\"";
		}
		return name;
	}

	/**
	 * 라디오버튼을 체크할지를 정한다
	 * @param selected 선택된 값
	 * @param value 자기객체의값
	 * @return String 결과문자열
	 */
	public static String printRadioCheck(String selected, String value) {
		String name = "";
		if(selected.equals(value)){
			name = "checked";
		}
		return name;
	}

	/**
	 * 사용기간을 출력한다. (from~to)
	 * @param fromDt
	 * @param toDt
     * @param pattern (~,-)구분형식
	 * @return String 결과문자열
	 */
	public static String printDateFormat(String fromDt, String toDt, String pattern) {
    	StringBuffer sbDate = new StringBuffer("");
		if (fromDt.equals("")) {
			return "";
		}
		if (toDt.equals("")) {
			toDt = fromDt;
		}

		sbDate.append(fromDt);
		sbDate.append(" ~ ");
		sbDate.append(toDt);

		return sbDate.toString();
	}


	/**
	 * 알람레벨에 따라 스타일스트를 적용한다.
	 * @param status 상태값
	 * @return String 결과문자열
	 */
	public static String printAlamLevelRowCss(String code) {
		String name = "";
		//심각한에러
		if("CRI".equals(code)){
			name = "class=\"bg_fatal\"";
		//DB알림
		} else if("DBALERT".equals(code)){
			name = "class=\"bg_alert\"";
		//에러
		} else if("MAJ".equals(code)){
			name = "class=\"bg_error\"";
		//경고
		} else if("MIN".equals(code)){
			name = "class=\"bg_warn\"";
		//정상(Info)
		} else if("NOR".equals(code)){
			name = "";
		//시스템로그
		} else if("SYS".equals(code)){
			name = "class=\"bg_Gray\"";
		}

		return name;
	}

	/**
	 * 시간대 리스트
	 * @return List 시간대 리스트
	 */
	public static List selectHours(){
		List<CodeInfo> arTime = new ArrayList<CodeInfo>();		
		for (int i = 0 ; i < 24 ; i++) {
			if (i < 10) {
				StringBuffer sbTemp = new StringBuffer("");
				sbTemp.append("0");
				sbTemp.append(i);
				String code = sbTemp.toString();
//				sbTemp.append("시");				
				arTime.add(new CodeInfo(code, sbTemp.toString()));
			} else {
				StringBuffer sbTemp = new StringBuffer("");
				String code = String.valueOf(i);
				sbTemp.append(code);
//				sbTemp.append("시");
		        arTime.add(new CodeInfo(code, sbTemp.toString()));
			}
		}
		return arTime;
	}

	
	/**
	 * 분 리스트
	 * @return List 분 리스트
	 */
	public static List selectMinutes() throws UserSysException {
		List<CodeInfo> arMinutes = new ArrayList<CodeInfo>();
		for (int i = 0 ; i < 6 ; i++) {
			StringBuffer sbTemp = new StringBuffer("");
			sbTemp.append(i);
			sbTemp.append("0");
			String code = sbTemp.toString();
//			sbTemp.append("분");
			arMinutes.add(new CodeInfo(code, sbTemp.toString()));
		}
        return arMinutes;
	}
	
	/**
	 * 분 리스트
	 * @return List 분 리스트
	 */
	public static List selectMinutes2() throws UserSysException {
		List<CodeInfo> arMinutes = new ArrayList<CodeInfo>();
		for (int i = 0 ; i < 60 ; i++) {
			if (i < 10) {
				StringBuffer sbTemp = new StringBuffer("");
				sbTemp.append("0");
				sbTemp.append(i);
				String code = sbTemp.toString();
//				sbTemp.append("분");
				arMinutes.add(new CodeInfo(code, sbTemp.toString()));
			} else {
				StringBuffer sbTemp = new StringBuffer("");
				String code = String.valueOf(i);
				sbTemp.append(code);
//				sbTemp.append("분");
				arMinutes.add(new CodeInfo(code, sbTemp.toString()));
			}
		}
        return arMinutes;
	}

	/**
	 * DB에 저장되어 있지 않거나 enable값이 N이면 읽기 전용속성을 지정한다 enable 이 Y 인경우만 쓰기가능
	 * @param userInfo 유저정보
	 * @param pageId 페이지ID
	 * @param objId 오브젝트ID
	 * @return String DISABLE 여부
	 */
	public static String printCssCriticalCheck(String critical, String per, String before, String target) {
        String result = "table_type1_text";
        double nCritical = StringUtil.parseDouble(critical, -1);
        double nPer = StringUtil.parseDouble(per, -1);
        double nBefore = StringUtil.parseDouble(before, -1);
        double nTarget = StringUtil.parseDouble(target, -2);
        boolean bCheck = true;
        if (nCritical != -1) {
        	if (nCritical <= nTarget) {
				result = "bg_critical_over";
				bCheck = false;
			}
        }
        if (bCheck){
        	if (nPer != -1) {
        		double temp = Double.parseDouble(getUpdatePer (before, target));
        		if (nPer <= temp) {
        			result = "bg_critical_over";
        			bCheck = false;
        		} else {
        			nPer = nPer * -1;
        			if (nPer >= temp) {
        				result = "bg_critical_over";
            			bCheck = false;
        			}
        		}
            }
        }

		return result;
	}

	/**
	 * 변경률을 구한다 (전데이터에서 변경한비율)
	 * @param before 전데이터
	 * @param target 현재데이터
	 * @return int 변경율
	 */
	public static String getUpdatePer(String before, String target) {
		double nResult = 0;
		if (!"".equals(before)) {
			double nBefore = StringUtil.parseDouble(before, -1);
			double nTarget = StringUtil.parseDouble(target, -2);
			nResult = nTarget;
			if (nBefore != 0) {
				//nResult = (nTarget-nBefore)/nBefore * 100;
				nResult = nTarget-nBefore;
			}

			//String temp = String.valueOf(nResult);

		}
		return StringUtil.round(nResult);
	}

	/**
	 * DB에 저장되어 있지 않거나 enable값이 N이면 읽기 전용속성을 지정한다 enable 이 Y 인경우만 쓰기가능
	 * @param userInfo 유저정보
	 * @param pageId 페이지ID
	 * @param objId 오브젝트ID
	 * @return String DISABLE 여부
	 */
	public static String printCssCriticalMinusCheck(String critical, String per, String before, String target) {
        String result = "table_type1_text";
        double nCritical = StringUtil.parseDouble(critical, -1);
        double nPer = StringUtil.parseDouble(per, -1);
        double nBefore = StringUtil.parseDouble(before, -1);
        double nTarget = StringUtil.parseDouble(target, -2);
        boolean bCheck = true;
        if (nCritical != -1) {
        	if (nCritical <= nTarget) {
				result = "bg_critical_over";
				bCheck = false;
			}
        }
        if (bCheck){
        	if (nPer != -1) {
        		double temp = Double.parseDouble(getUpdateMinusPer (before, target));
        		if (nPer <= temp) {
        			result = "bg_critical_over";
        			bCheck = false;
        		} else {
        			nPer = nPer * -1;
        			if (nPer >= temp) {
        				result = "bg_critical_over";
            			bCheck = false;
        			}
        		}
            }
        }

		return result;
	}

	/**
	 * 변경률을 구한다 (전데이터에서 변경한비율)
	 * @param before 전데이터
	 * @param target 현재데이터
	 * @return int 변경율
	 */
	public static String getUpdateMinusPer(String before, String target) {
		double nResult = 0;
		if (!"".equals(before)) {
			double nBefore = StringUtil.parseDouble(before, -1);
			double nTarget = StringUtil.parseDouble(target, -2);
			nResult = nTarget;
			if (nBefore != 0) {
				nResult = nTarget-nBefore;
			}

			//String temp = String.valueOf(nResult);

		}
		return StringUtil.round(nResult);
	}


	
	/**
	 * 1~10 볼륨 밝기등  리스트로 리턴한다.
	 * @return List 1~10 분 리스트 
	 */
	public static List<CodeInfo> getSettingList(){
		StringBuffer buf = null;
		List<CodeInfo> rtn = new ArrayList<CodeInfo>();
		CodeInfo info = null;
		for(int n=1; n<11; n++){
			buf = new StringBuffer();
			info = new CodeInfo();			
			buf.append(Integer.toString(n));
			info.setCode(buf.toString());
			info.setName(buf.toString());
			rtn.add(info);
		}
		return rtn;
	}
	
	/**
	 * 1~100 정렬순서  리스트로 리턴한다.
	 * @return List 1~100 리스트 
	 */
	public static List<CodeInfo> getSettingOrderList(){
		StringBuffer buf = null;
		List<CodeInfo> rtn = new ArrayList<CodeInfo>();
		CodeInfo info = null;
		for(int n=1; n<100; n++){
			buf = new StringBuffer();
			info = new CodeInfo();			
			buf.append(Integer.toString(n));
			info.setCode(buf.toString());
			info.setCodenm_k(buf.toString());
			info.setCodenm_e(buf.toString());
			info.setCodenm_j(buf.toString());
			rtn.add(info);
		}
		return rtn;
	}
	
	/**
	 * 알람가청레벨설정
	 * @param basic 알람레벨기준
	 * @param status 장애알람
	 * @return boolean 가청여부
	 */
	public static boolean checkSoundLevel(String basic, String status, String step){
        boolean bCheck = false;
        try{
            if(!"".equals(basic) && !"".equals(status) && !"0".equals(status) && "0".equals(step))
                bCheck = true;
        }
        catch(Exception exception) { }
        return bCheck;
    }
		

}
