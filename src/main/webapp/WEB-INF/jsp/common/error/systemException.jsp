<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/error/systemException.jsp
 - 설    명	:	시스템에러시 표시하는화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.08.01    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@taglib uri="/WEB-INF/tld/eaction.tld" prefix="eaction" %>

<%-- <eaction:html bodyExist="<%=ConstKey.KEY_NO %>" mainCss="<%=ConstKey.KEY_YES %>">
<eaction:script blockUI="<%=ConstKey.KEY_YES %>" mask="<%=ConstKey.KEY_YES %>" validationEngine="<%=ConstKey.KEY_YES %>" move="<%=ConstKey.KEY_YES %>"/> --%>
<table width="800" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left">
        <%-- <eaction:messageBox result="<%=ConstKey.RESULT_FAIL %>" messageId="<spring:message code='MSG.ALERT.PROCESS.ERROR' />" /> --%>
    </td>
  </tr>  
</table>

<%-- </eaction:html> --%>
