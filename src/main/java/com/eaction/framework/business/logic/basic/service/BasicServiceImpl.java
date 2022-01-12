/*****************************************************************************
 * 프로그램명  : BasicServiceImpl.java
 * 설     명  : 공통처리를 위한 관리 비즈니스로직
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.07.07   LYS    1.0     초기작성
 *****************************************************************************/

package com.eaction.framework.business.logic.basic.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.business.logic.basic.constant.BasicConstKey;
import com.eaction.framework.business.logic.basic.dao.BasicDao;
import com.eaction.framework.business.logic.basic.model.BasicLogInfo;
import com.eaction.framework.business.logic.basic.model.BasicSearchInfo;
import com.eaction.framework.business.logic.basic.model.LogInfo;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.AuthInfo;
import com.eaction.framework.common.model.CodeInfo;
import com.eaction.framework.common.model.UploadFile;
import com.eaction.framework.common.model.User;
import com.eaction.framework.common.util.DateTimeUtil;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.FileUploadUtil;
import com.eaction.framework.common.util.FileUtil;
import com.eaction.framework.common.util.StringUtil;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.IconFace;
import net.sf.json.JSONObject;


/**	
 * 공통처리를 위한 관리  서비스
 *
 * @author  eaction
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class BasicServiceImpl implements BasicService {	
	/** 로그출력 객체 */
	private static final Logger logger = LoggerFactory.getLogger( BasicServiceImpl.class );
	
	@Resource
	private BasicDao basicDao;


    /**
	 * 사용자권한 취득
	 * @param user_group 사용자권한
	 * @return List 사용자권한정보
	 */
	@Override
    public AuthInfo selectUserAuthInfo(String user_group){
    	return basicDao.selectUserAuthInfo(user_group);
    }
	
    /**
	 * 로그 저장
	 * @param request
	 * @param menu
	 * @param content
	 */
	@Override
	@Transactional
	public void doLog(HttpServletRequest request, String eventCode, String item, String content) {
		doLog(request, eventCode, item, content, "");
	}
	
    /**
	 * 로그 저장
	 * @param request
	 * @param menu
	 * @param content
	 */
	@Override
	@Transactional
	public void doLog(HttpServletRequest request, String eventCode, String item, String content, String user_id) {
		LogInfo info = new LogInfo();
		
		// 접속 아이피
		String ip = getClientIP(request);
		info.setIp_addr(ip);
		// 로그인 아이디
		HttpSession session = request.getSession(true);
		String login_id = "";
		if(!"".equals(user_id)) {
			info.setUser_id(user_id);; 
		}else {
			User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
			
			if(userInfo != null) {
				login_id = userInfo.getUser_id();
				info.setUser_id(login_id);
			}
		}

		info.setEvt_cd(eventCode);
		info.setItem(item);
		info.setEvt_desc(content);
		basicDao.doLog(info);
	}
	
	@Override
	public String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if ("".equals(StringUtil.nvl(ip))) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if ("".equals(StringUtil.nvl(ip))) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if ("".equals(StringUtil.nvl(ip))) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if ("".equals(StringUtil.nvl(ip))) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if ("".equals(StringUtil.nvl(ip))) {
	        ip = request.getRemoteAddr();
	    }
	    return ip;
	}
}
