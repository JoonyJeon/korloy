<%----------------------------------------------------------------------------------------
 - 파일이름	:	login/Login.jsp
 - 설    명	:	로그인 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2008.09.29    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%

String url = StringUtil.nvl(request.getRequestURL().toString());
String result = request.getParameter(ConstKey.LOGIN_RESULT);

String action = "";
// 모바일에서 접속한 경우는 모바일 로그인 페이지로 이동 함
action = ConstUrl.LOGIN_URL;
//}
%>
<eaction:html>
<form name="myForm" method="POST" action="<%=action%>">
<input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.LOGIN_INIT %>">
<input type="hidden" name="<%=ConstKey.LOGIN_RESULT %>" value="<%=ConstKey.LOGIN_NEED %>">
</form>
<script type="text/javascript">
<% if(ConstKey.DUP_LOGIN.equals(result)){ %>
//중복로그인 메세지
alert("<spring:message code='MSG.ALERT.DUP_LOGOUT'/>.");
<% } %>
document.myForm.submit();
</script>
</eaction:html>
