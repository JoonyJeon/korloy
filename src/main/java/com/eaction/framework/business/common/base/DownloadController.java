/*****************************************************************************
 * 프로그램명  : DownloadController.java
 * 설     명  : 다운로드 처리 
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2018.11.07  LYS    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.business.common.base;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
import com.eaction.framework.business.common.constant.ConstUrl;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.util.Base64Coder;
import com.eaction.framework.common.util.FileDownload;
import com.eaction.framework.common.util.StringUtil;

/**
 * 다운로드 처리
 * @author  eaction
 * @version 1.0
 */
@Controller
public class DownloadController {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(DownloadController.class);    

	@RequestMapping(value = ConstUrl.FILE_DOWNLOAD_URL_BOARD)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UserSysException {

		
		HttpSession session = request.getSession();

 		String keyPath = request.getParameter("keyPath");
 		String filePath = ConfigMng.getValue(keyPath);
 		filePath = session.getServletContext().getRealPath(filePath);
 		String pathSub = StringUtil.nvl(request.getParameter("pathSub"));
 		String realFileName = StringUtil.nvl(request.getParameter("realFileName"),"");
 		if(realFileName.equals("")){
 			realFileName = StringUtil.nvl(request.getParameter("fileName"));
 		}
 		String refFileName = StringUtil.nvl(request.getParameter("refFileName"),"");
 		if(refFileName.equals("")){
 			refFileName = StringUtil.nvl(request.getParameter("fileName"));
 		}

 		//파일 전체 경로설정
 		StringBuffer sbFileName = new StringBuffer("");
 		sbFileName.append(filePath);
 		sbFileName.append(System.getProperty("file.separator"));
 		if (!"".equals(pathSub)){
	 		sbFileName.append(pathSub);
	 		/*sbFileName.append(System.getProperty("file.separator"));*/
 		}
 		/*sbFileName.append(realFileName);*/

 		// 파일다운로드 실행
 		try {
 		    FileDownload fileDownload = new FileDownload(response);
 		    fileDownload.download(sbFileName.toString(), refFileName, request);
 		} catch (UserSysException ex) {
 			//에러메세지 출력
 			logger.error(StringUtil.printUserSysMessage(ex));
 			logger.error(StringUtil.stackTraceToString(ex));

            // 시스템에러 발생상태
			request.setAttribute(ConstKey.RESULT_KEY, ConstKey.RESULT_FAIL);
 			request.setAttribute(ConstKey.EX_ERR_OBJ, ex);

 			return new ModelAndView(ConstUrl.JSP_ERROR_URL);
 		} catch (Exception ex) {
 			//에러메세지 출력
 			logger.error(ex.getMessage() + " : " +  ex);

 			//시스템에러 발생상태
			request.setAttribute(ConstKey.RESULT_KEY, ConstKey.RESULT_FAIL);
 			request.setAttribute(ConstKey.EX_ERR_OBJ, ex);

 			return new ModelAndView(ConstUrl.JSP_ERROR_URL);
 		}

		return null;
	}

}
