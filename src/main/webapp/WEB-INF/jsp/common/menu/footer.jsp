<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/footer.jsp
 - 설    명	:	풋터화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2021.07.14    1.0       정세연      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="footer">
    <div class="inner">
        <%-- Terms of use and Legal disclaimer, Privacy Statement --%>
        <span class="left" data-lang="FR000007" style="cursor:pointer;float: left;" onclick="location.href='/privacy.do'"><%=LangMng.LANG_D("FR000007", session_lang) %></span>
        <%-- Copyright ⓒ KORLOY --%>
        <span class="right"  data-lang="FR000008" style="float: right;"><%=LangMng.LANG_D("FR000008", session_lang) %></span>
    </div>
</div>