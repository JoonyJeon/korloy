<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/include.inc
 - 설    명	:	JSP에서 필수로 사용하는 변수들을 정의하여 공유
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2020.08.01    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>

<%@page import="com.eaction.framework.common.file.ConfigMng" %>
<%@page import="com.eaction.framework.common.file.IPropertyKey" %>
<%@page import="com.eaction.framework.common.model.User" %>
<%@page import="com.eaction.framework.common.model.AuthInfo" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.common.exception.BizException" %>
<%@page import="com.eaction.framework.common.model.ProcessResult" %>
<%@page import="com.eaction.framework.business.common.constant.ConstKey" %>
<%@page import="com.eaction.framework.business.common.constant.ConstUrl" %>
<%@page import="com.eaction.framework.business.common.code.CodeTableMng"%>
<%@page import="com.eaction.framework.common.model.CodeInfo"%>
<%@page import="com.eaction.framework.business.common.lang.LangMng"%>
<%@page import="com.eaction.framework.business.common.menu.MenuMng"%>
<%@page import="java.util.Locale"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.List"%>
<%
String root = (String)request.getAttribute("ROOT_MENU");
String menu_url = (String)request.getAttribute("MENU_URL");
String session_lang = ConfigMng.getLang(request);

%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/tld/eaction.tld" prefix="eaction" %>

<% String DOMAIN_NAME = ConfigMng.getValue(IPropertyKey.DOMAIN_NAME); %>
<% String MOBILE_DOMAIN_NAME = ConfigMng.getValue(IPropertyKey.MOBILE_DOMAIN_NAME); %>
<% String PATH_SCRIPT = ConfigMng.getValue(IPropertyKey.PATH_SCRIPT); %>
<% String PATH_IMG = ConfigMng.getValue(IPropertyKey.PATH_IMG); %>
<% String PATH_CSS = ConfigMng.getValue(IPropertyKey.PATH_CSS); %>
<% String AJAX_CONNECTION_ERROR = ConfigMng.getValue(IPropertyKey.AJAX_CONNECTION_ERROR); %>
<% String UPLOAD_ROOT_PATH = ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH); %>
<% String PRIVACY_INFO = (String)session.getAttribute("privacy_info"); %>
<%
User userInfo = (User)session.getAttribute(ConstKey.USER_INFO);
if(userInfo == null) {
	userInfo = new User();
}
JSONObject json_user = JSONObject.fromObject(userInfo);
AuthInfo authInfo = (AuthInfo)session.getAttribute(ConstKey.SESSION_AUTH);
JSONObject lang_code = (JSONObject)session.getAttribute(ConstKey.SESSION_LANGCODE);
JSONObject main_combo = (JSONObject)session.getAttribute("main_combo");
JSONObject json_auth = (JSONObject)session.getAttribute("json_auth");
%>
