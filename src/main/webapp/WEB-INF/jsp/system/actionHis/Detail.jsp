<%----------------------------------------------------------------------------------------
 - 파일이름	: system/syscod/CodeMod.jsp
 - 설      명	: 시스템그룹코드관리 수정 / 추가 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2018.11.21    1.0     LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page import="com.eaction.framework.business.system.actionHis.model.ActionHisSearchInfo"%>
<%@page import="com.eaction.framework.business.system.actionHis.constant.ActionHisConstKey"%>
<%@page import="com.eaction.framework.business.system.actionHis.constant.ActionHisConstUrl"%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.actionHis.model.ActionHisInfo"%>
<%@page import = "com.eaction.framework.business.system.actionHis.model.ActionHisSearchInfo"%>
<%@page import = "com.eaction.framework.common.model.User"%>
<%@page import = "com.eaction.framework.business.common.code.CodeTableMng"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
	ActionHisSearchInfo searchInfo = (ActionHisSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
	ActionHisInfo resultInfo = (ActionHisInfo)request.getAttribute(ConstKey.RESULT_DATA);
%>

<div class="modal-dialog">
	<div class="modal-content">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel">System Management</h4>
		</div>
		
		<div class="modal-body">
			<form id="grCdDtModForm" id="grCdDtModForm" class="smart-form" novalidate="novalidate">
				<fieldset>
					<div class="row">
<!-- id -->
						<section class="col col-6">
							<label class="label">아이디</label>
							<label class="input">							
								<input type="text" name="detail_action_user_id" id="detail_action_user_id" value="<%=resultInfo.getAction_user_id()%>" readOnly/>
								<i></i>
							</label>
						</section>
						<section class="col col-6">
							<label class="label">이름</label>
							<label class="input">							
								<input type="text" name="detail_empnm" id="detail_empnm" value="<%=resultInfo.getEmpnm()%>" readOnly/>
								<i></i>
							</label>
						</section>
					</div>
					<div class="row">
						<section class="col col-6">
							<label class="label">메뉴명</label>
							<label class="input">							
								<input type="text" name="detail_menu_nm_kor" id="detail_menu_nm_kor" value="<%=resultInfo.getMenu_nm_kor()%>" readOnly/>
								<i></i>
							</label>
						</section>
						<section class="col col-6">
							<label class="label">활동구분</label>
							<label class="input">							
								<input type="text" name="detail_action_code" id="detail_action_code" value="<%=resultInfo.getAction_code_nm()%>" readOnly/>
								<i></i>
							</label>
						</section>
					</div>
					<section class="col-12" style="margin-left: 15px;;margin-right:15px;">
						<div class="row">
							<label class="label" style="font-weight: bold;"> 
									내용
							</label> 
							<label class="textarea">
								<textarea rows="5" style="width:100%;" name="detail_action_ctnt" id="detail_action_ctnt" readonly><%=resultInfo.getAction_ctnt()%></textarea>							
							</label>
						</div>
					</section>
				</fieldset>
			</form>		
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">				
				<spring:message code="TEXT.CANCEL" />
			</button>
		</div>

	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//클릭 이벤트	
	bindClick();
});

function bindClick(){		
	
}


</script>
