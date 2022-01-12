<%----------------------------------------------------------------------------------------
 - 파일이름	: inti/init.jsp
 - 설    명		: 첫화면
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2019.05.10    1.0       HSI      신규작성
 -							KSH
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
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstKey"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>


<div class = "modal-dialog" style="width:1000px; z-index:1300 !important;">
	<div class = "modal-content">
		<div class = "modal-header">

		</div>
		
		<div class="modal-body">
			<form id="itemInfoForm">
			
				아이템 정보 모달			
			
			</form>
		
		</div>
		
		<div class="modal-footer" align="center">
			<button type="button" class="btn btn-default" data-dismiss="modal">
				취소
			</button>
		</div>
	</div>
</div>
<script type="text/javascript">

jQuery(document).ready(function(){
	


});



</script>
