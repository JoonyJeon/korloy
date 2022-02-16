/*****************************************************************************
 * 프로그램명  : RESTfulUtil.java
 * 설     명  : RESTful API 관련 클래스 util class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2020.09.07  JSY    1.0     초기작성
 *****************************************************************************/
package com.eaction.framework.common.util;

import net.sf.json.JSONObject;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.eaction.framework.business.common.constant.ConstKey;
import com.eaction.framework.common.exception.UserSysException;
import com.eaction.framework.common.file.ConfigMng;
import com.eaction.framework.common.file.IPropertyKey;
import com.eaction.framework.common.model.ErrorInfo;
import com.eaction.framework.common.model.User;

import java.text.DecimalFormat;

/**
 * RESTful API 관련 클래스 util class.
 * @author  eaction
 * @version 1.0
 */
public class RESTfulUtil {
	/** 로그처리 객체 */
    protected static Log logger = LogFactory.getLog(RESTfulUtil.class);

	/**
	 * 예외를 HTML형식으로 출력한다
	 * @param ex 예외
	 * @return String 변환된 String
	 */
	public static JSONObject RESTfulAPICall(JSONObject param, String callUrl) {
		JSONObject result = new JSONObject();
		HttpURLConnection conn = null;
		try {
			URL url = new URL(callUrl);
			conn = (HttpURLConnection) url.openConnection();
	        //Request 형식 설정
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        conn.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	        wr.write(param.toString().getBytes("utf-8"));
	        wr.flush();
	        wr.close();
	        //보내고 결과값 받기
	        int responseCode = conn.getResponseCode();
	        String responseMsg = conn.getResponseMessage();
	        if (responseCode == 400) {
	        	logger.debug(url.getHost() + "400 Error");
	        	logger.debug( responseMsg);
	        } else if (responseCode == 401) {
	        	logger.debug( url.getHost() + "401 Error");
	        	logger.debug( responseMsg);
	        } else if (responseCode == 500) {
	        	logger.debug( url.getHost() + "500 Error");
	        	logger.debug( responseMsg);
	        } else { // 성공 후 응답 JSON 데이터받기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            StringBuilder sb = new StringBuilder();
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            result = JSONObject.fromObject(sb.toString());
	        }
		} catch (Exception e) {
			logger.error(e.toString());
		}finally {
			if(conn != null) {
	            conn.disconnect();
			}
		}

		return result;
	}
}
