<%----------------------------------------------------------------------------------------
 - 파일이름	: user/emailAuth.jsp
 - 설      명	: 이메일 인증 완료 여부 화면
 - 추가내용 	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   	Auther   	Description
 ------------  -----------  ---------  --------------------------
 - 2021.08.06    1.0       	YJI      	신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.user.model.UserInfo"%>
<%@page import="com.eaction.framework.business.common.lang.LangMng"%>
<%@page import="com.eaction.framework.common.file.ConfigMng" %>

<%
boolean result = (boolean)request.getAttribute("result");
String session_lang = ConfigMng.getLang(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
<script type="text/javascript">

<% if (result) { %>
	<%-- email certification confirmed --%>
	if(confirm('<%=LangMng.LANG_D("FR000191", session_lang) %>')) {
		window.close();
	}
<% } else { %>
	<%-- error occured during certification. please try again --%>
	if(confirm('<%=LangMng.LANG_D("FR000192", session_lang) %>')) {
		window.close();
	}
<%} %>
</script>
</html>