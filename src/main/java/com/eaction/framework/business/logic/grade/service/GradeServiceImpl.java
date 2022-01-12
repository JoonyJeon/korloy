/*****************************************************************************
 * 프로그램명  : GradeServiceImpl.java
 * 설     명  : Grade Guide 정보 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2021.07.30   SJY    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.grade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.common.lang.LangMng;
import com.eaction.framework.business.logic.grade.dao.GradeDao;
import com.eaction.framework.business.logic.grade.model.GradeInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.util.StringUtil;

/**
 * Grade Guide 정보 서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
public class GradeServiceImpl implements GradeService {	
	/** 로그출력 객체 */
	private static final Log logger = LogFactory.getLog( GradeServiceImpl.class );
	
	@Resource
	private GradeDao gradeDao;
	@Autowired
	MessageSourceAccessor messageSourceAccessor;

	/**
  	 * 재종가이드 검색조건 조회
  	 * @return Map<String, Object> 검색조건
	 */
	@Override
	public Map<String, Object> selectSearchConditionList(HttpServletRequest request) throws UserSysException {
		//MainApp, Substrate, ISO Grade, Grade Classification CodeInfo 형태로 한번에 조회
		List<CodeInfo> tempList = gradeDao.selectSearchConditionList();
		
		Map<String, Object> result = new HashMap<>();
		
		List<CodeInfo> isoGradeList = new ArrayList<>();
		List<CodeInfo> substList = new ArrayList<>();
		List<CodeInfo> gradeClassList = new ArrayList<>();
		List<CodeInfo> mainAppList = new ArrayList<>();
		
		//사용자 언어 취득
		HttpSession session = request.getSession(true);
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);

        if("".equals(StringUtil.nvl(session_lang))) {
            session_lang = ConfigMng.getLang(request);
        }
        
		
		//upcode가 'CM0004'이면 ISO Grade, 'CM0008'이면 Substrate, 'CM0009' 이면 Grade Classification, 공백이면 MainApp 
		for(CodeInfo codeInfo : tempList){
			if("CM0004".equals(codeInfo.getUpCode())){
				isoGradeList.add(codeInfo);
				logger.debug("isoGradeList :: "+codeInfo);
			}else if("CM0008".equals(codeInfo.getUpCode())){
				substList.add(codeInfo);
				logger.debug("substList :: "+codeInfo);
			}else if("CM0009".equals(codeInfo.getUpCode())){
				
				gradeClassList.add(codeInfo);
				logger.debug("gradeClassList :: "+codeInfo);
			}else{
				//Main App 다국어 처리
				codeInfo.setName(LangMng.LANG_D(codeInfo.getCode(), session_lang));
				mainAppList.add(codeInfo);
				logger.debug("mainAppList :: "+codeInfo);
			}
		}
		
		result.put("isoGradeList", isoGradeList);
		result.put("substList", substList);
		result.put("gradeClassList", gradeClassList);
		result.put("mainAppList", mainAppList);
		
		return result;
	}
	/**
	  * 재종가이드 검색결과 리스트 조회
	  * @return List<GradeInfo> 검색결과 리스트
	 */
	@Override
	public Map<String, Object> selectGradeList(HttpServletRequest request, GradeInfo info) throws UserSysException {

		//사용자 언어 취득 - 다국어처리를 위해
		HttpSession session = request.getSession(true);
        String session_lang = (String) session.getAttribute(ConstKey.SESSION_LANG);

        if("".equals(StringUtil.nvl(session_lang))) {
            session_lang = ConfigMng.getLang(request);
        }
        
		Map<String, Object> result = new HashMap<>();
		
		//배열로 바로 넘기면 생기는 오류를 방지하기 위해...
		if(!"".equals(info.getMa_cd())){
			String[] maCdarr = info.getMa_cd().split(",");
			info.setMa_cd_arr(maCdarr);
		}
		if(!"".equals(info.getGrade_clas())){
			String[] gradClasArr = info.getGrade_clas().split(",");
			info.setGrade_clas_arr(gradClasArr);
		}
		if(!"".equals(info.getSubst())){
			String[] substArr = info.getSubst().split(",");
			info.setSubst_arr(substArr);
		}
		if(!"".equals(info.getIso_rag())){
			String[] isoRag = info.getIso_rag().split(",");
			info.setIso_rag_arr(isoRag);
		}
		
		//더보기 버튼 관련처리
		info.setIsPageYn(ConstKey.KEY_Y);
		if(info.getPageCount() == 0){
			info.setPageCount(Integer.parseInt(ConfigMng.getValue(IPropertyKey.NORMAL_PAGE_ROWNUM)));
		}
		//전체결과 개수
		GradeInfo newInfo = new GradeInfo();
		int allTotal = gradeDao.selectGradeListCnt(newInfo);
		
		//검색결과 개수
		int nTotal = gradeDao.selectGradeListCnt(info);
		//검색결과 리스트
		List<GradeInfo> list = gradeDao.selectGradeList(info);
		
		for(GradeInfo grade: list){
			//Main Application 다국어 처리
			grade.setMa_nm(LangMng.LANG_D(grade.getMa_cd(), session_lang));
			//재종 설명 다국어 처리
			grade.setGrade_desc(LangMng.LANG_D(grade.getGrade(), session_lang));
		}
		
		result.put("nTotal", nTotal);
		result.put("allTotal", allTotal);
		result.put("list", list);
		
		return result;
	}
	@Override
	public GradeInfo selectGradeDetail(HttpServletRequest request, GradeInfo info) throws UserSysException {
		return gradeDao.selectGradeDetail(info);
	}
}
