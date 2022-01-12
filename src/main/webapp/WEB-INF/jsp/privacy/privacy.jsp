 <%----------------------------------------------------------------------------------------
 - 파일이름	: search/search.jsp
 - 설    명		: 검색
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2019.05.10    1.0       정세연      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8"
	errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@page import="com.eaction.framework.business.logic.privacy.constant.PrivacyConstUrl"%>
<%@include file="/WEB-INF/jsp/common/include.inc"%>
<div id="privacy_info">
<%=StringEscapeUtils.unescapeHtml3(PRIVACY_INFO) %>
</div>
<script type="text/javascript">

jQuery(document).ready(function(){
	$('.loading').delay('1000').fadeOut();

    $("#btnLang").change(function(){
        var param = {
        		changeLang : this.value,
        };
                    
        $.ajax({
            type:"POST",
            dataType: "json",
            data: param,
            async: true,
            global : false,
            url : "<%=PrivacyConstUrl.PRIVACY_CHANGE_URL%>",
            beforeSend:function(){
                $('#loadbar').show();
            },
            success: function(e){
            	$("#privacy_info").html(e.result);
            }, 
            error: function(){
            },
            complete: function(){
                $('#loadbar').fadeOut();
            }
    });
	});
});
</script>