<%----------------------------------------------------------------------------------------
 - 파일이름	: staff/Edit.jsp
 - 설      명	: 스텝정보 수정 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.04.25    1.0       KSH      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstUrl" %>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstKey"%>
<%@page import = "com.eaction.framework.business.logic.staff.model.StaffInfo"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%StaffInfo resultInfo = (StaffInfo)request.getAttribute(StaffConstKey.STAFF_DETAIL_DATA);%>
<style>
	input::-webkit-input-placeholder { color: #e0e0e0; }
</style>

<div class = "modal-dialog">
	<div class = "modal-content">
		<div class = "modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel">
				<spring:message code="TEXT.STAFF.MGMT.POPUP.MODAL.TITLE" />
			</h4>
		</div>
		<div class="modal-body">
			<form id="staffEditForm" name="staffEditForm" class="smart-form" novalidate="novalidate">	
			    <input type="hidden" name="edit_user_group_id" id="edit_user_group_id" value="0">			
				<fieldset>
<!-- ID 이메일 -->
					<section>
						<label class="label">
						    <span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMP_ID" />
						</label>
						<label class="input">
							<input type="text" name="edit_emp_id" id="edit_emp_id" maxlength="100" value="<%=resultInfo.getEmp_id() %>" readonly="readonly" />
						</label>
					</section>
<!-- 이름 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMPNM" />
						</label>
						<label class="input">
							<input type="text" name="edit_empnm" id="edit_empnm" maxlength="60" value="<%=resultInfo.getEmpnm() %>" />
						</label>												
					</section>
<%--
<!-- 권한등급 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
 							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.USER_GROUP_ID" />
						</label>
						<label class="select">
							<eaction:select name="edit_user_group_id" id="edit_user_group_id" group="AUTH_TYPE" selected="<%=resultInfo.getUser_group_id() %>" />
							<i></i>
						</label>
					</section>
 --%>					
<!-- 사용상태 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.STATUS" />
						</label>
						<label class="select">
							<eaction:select name="edit_status" id="edit_status" group="STATUS" selected="<%=resultInfo.getStatus() %>" />
							<i></i>
						</label>
					</section>
				
<!-- 비밀번호 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.ADD_STAFF.PASSWORD" />								
						</label>
						<label class="input">
							<input type="password" name="edit_password" id="edit_password" maxlength="100" placeholder="<spring:message code="MSG.STAFF.MGMT.UPDATE_STAFF.PWD_PH" />" value="">
						</label>
					</section>
<!-- 비밀번호 확인 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.ADD_STAFF.REPASSWORD" />
						</label>
						<label class="input">
							<input type="password" name="edit_passwordCheck" id="edit_passwordCheck" value="" maxlength="100" placeholder="<spring:message code="MSG.STAFF.MGMT.UPDATE_STAFF.PWD_PH" />"/>
						</label>
					</section>
<!-- 이메일 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMAIL" />
						</label>
						<label class="input">
							<input type="text" name="edit_email" id="edit_email" maxlength="60" value="<%=resultInfo.getEmail() %>" />
						</label>												
					</section>	
<!-- 모바일-->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.SMSPHONE" />
						</label>
						<label class="input">
							<input type="text" name="edit_smsphone" id="edit_smsphone" maxlength="20" value="<%=resultInfo.getSmsphone() %>" />
						</label>												
					</section>										
				</fieldset>
			</form>
		</div>

		<div class="modal-footer" align="center">
			<button type="button" class="btn btn-default" data-dismiss="modal">
				<spring:message code="TEXT.CANCEL" />
			</button>
			<eaction:button name="editStaff" id="editStaff" cssId="btn btn-primary btn-sm" menuUrl="<%=menu_url %>" >
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</eaction:button>			
		</div>
	</div>
</div>
<script src="<%=PATH_SCRIPT%>/sha256.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		//필수값인 input의 label bold처리
		$(".text-warning").parent().css("font-weight","bold");
		
		var errorClass = 'invalid';
		var errorElement = 'em';
 		$("#staffEditForm").validate({
			errorClass		: errorClass,
			errorElement	: errorElement,
			highlight: function(element) {
		        $(element).parent().removeClass('state-success').addClass("state-error");
		        $(element).removeClass('valid');
		    },
		    unhighlight: function(element) {
		        $(element).parent().removeClass("state-error").addClass('state-success');
		        $(element).addClass('valid');
		    },
		    rules : {
		    	edit_empnm :{
		    		required : true
		    	},
		    	edit_emp_id :{
		    		required : true
		    	},
		    	/*
		    	edit_user_group_id:{
		    		required : true
		    	},
		    	*/
		    	edit_status:{
		    		required : true
		    	},
		    	edit_passwordCheck:{
		    		equalTo : '#edit_password'
		    	},
		    	edit_email : {				
					email : true
				}
		    },
		    messages : {
		    	edit_empnm :{
		    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.EMPNM" />'
		    	},
		    	edit_emp_id :{
		    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.EMP_ID" />'		    		
		    	},
		    	/*
		    	edit_user_group_id:{
		    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.USER_GROUP_ID" />'
		    	},
		    	*/
		    	edit_status :{
		    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.STATUS" />'
		    	},
		    	edit_passwordCheck:{
		    		equalTo : '<spring:message code="MSG.STAFF.MGMT.PASSWORD_CHECK" />'
		    	},
		    	edit_email : {				
		    		email : '<spring:message code="MSG.STAFF.MGMT.REQ.EMAIL_FORM" />'
				}
		    },
			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			},
			submitHandler: function (form) {
				
				if (confirm("<spring:message code='MSG.CONFIRM.UPDATE'/>")) {
					var passwd = "";
						
					if ($("#edit_password").val() != null && $("#edit_password").val() != "") {
						passwd = sha256_digest($("#edit_password").val())
					}
					var param = {
							actionID		:"<%=StaffConstKey.ACTION_STAFF_EDIT_OK%>",
							empnm			: $("#edit_empnm").val(),
							emp_id			: $("#edit_emp_id").val(),
							user_group_id	: $("#edit_user_group_id").val(),
							status 			: $("#edit_status").val(),
							password		: passwd,
							email		    : $("#edit_email").val(),
							smsphone		: $("#edit_smsphone").val()
					};
					
		            $.ajax({
		                type: "POST",
		                url: "<%=StaffConstUrl.STAFF_MEMBER_POP_EDIT_URL%>?v=" + (new Date().getTime()),
		                data: param,
		                dataType: "json",
		                async: true,
		                cache: false,
		                success: function (e) {		                	
		                	if (e.resCode == "0") {
		                		//alert(e.resMsg);
		                		$('#staffModal').modal('hide');
		                		goStaffList();
		                	}else{
		                		alert(e.resMsg);
							}
		                }, error: function () {
		                	alert('<spring:message code="MSG.STAFF.MGMT.ALERT.UPDATE_FAIL" />');
		                }
		            });
		        }
			}
		});
		
		bindEvent();
	})
	
	//이벤트 매핑 정의
	function bindEvent(){
		//저장
		$("#editStaff").bind("click", function(){
				$("#staffEditForm").submit();
	   	});
	}

</script>