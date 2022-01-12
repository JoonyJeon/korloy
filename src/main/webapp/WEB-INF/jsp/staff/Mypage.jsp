<%----------------------------------------------------------------------------------------
 - 파일이름	: staff/Mypage.jsp
 - 설      명	: 마이페이지 팝업 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.04.30    1.0       KSH      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<script>
	//필수값인 input의 label bold처리
	$(".text-warning").parent().css("font-weight","bold");
</script>
<div class = "modal-dialog">
	<div class = "modal-content">
		<div class = "modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel">
				<spring:message code="TEXT.STAFF.MGMT.POPUP.MODAL.MYPAGE.TITLE" />
			</h4>
		</div>
		<div class="modal-body">
			<form id="myInfoEditForm" name="myInfoEditForm" class="smart-form" novalidate="novalidate">
				
				<fieldset>
<!-- ID 이메일 -->
					<section>
						<label class="label">
						    <span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMP_ID" />
						</label>
						<label class="input">
							<input type="text" name="editx_emp_id" id="editx_emp_id" maxlength="100" value="<%=resultInfo.getEmp_id() %>" readonly="readonly" />
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
 							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.USER_GROUP_ID" />
						</label>
						<label class="select">
							<eaction:select name="editx_user_group_id" id="editx_user_group_id" group="AUTH_TYPE" selected="<%=resultInfo.getUser_group_id() %>" option="disabled" />
							<i></i>
						</label>
					</section>
<!-- 사용상태 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.STATUS" />
						</label>
						<label class="select">
							<eaction:select name="editx_status" id="editx_status" group="STATUS" selected="<%=resultInfo.getStatus() %>" option="disabled" />
							<i></i>
						</label>
					</section>
--%>				
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
<!-- 이전 비밀번호 -->
					<section>
						<label class="label">
							이전 비밀 번호
						</label>
						<label class="input">
							<input type="password" name="pre_password" id="pre_password" maxlength="100"  value="">
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
						
				</fieldset>
			</form>
		</div>

		<div class="modal-footer" align="center">
			<button type="button" class="btn btn-default" data-dismiss="modal">
				<spring:message code="TEXT.CANCEL" />
			</button>
			<button type="button" class="btn btn-primary" id="editmyInfo">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>
	</div>
</div>

<script src="<%=PATH_SCRIPT%>/sha256.js"></script>	
<script type="text/javascript">
	jQuery(document).ready(function(){
	    $(document).on("contextmenu dragstart selectstart",function(e){
	        return false;
	    });
	    
		var errorClass = 'invalid';
		var errorElement = 'em';
 		$("#myInfoEditForm").validate({
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
		    	edit_passwordCheck:{
		    		equalTo : '#edit_password'
		    	},
		    	edit_email : {				
					email : true
				}
		    },
		    messages : {
		    	add_empnm :{
		    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.EMPNM" />'
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
					var passwd = ""
					if ($("#edit_password").val() != null && $("#edit_password").val() != "") {
						passwd = sha256_digest($("#edit_password").val());
						if(chkPW()) {
							if($("#pre_password").val() == ""){
								alert('이전 비밀번호를 입력해 주세요');
								return false;
							}
							if ($("#pre_password").val() != null && $("#pre_password").val() != "") {
								$("#pre_password").val(sha256_digest($("#pre_password").val()));
							}
						}else{
							return false;
						}
					}
					var param = {
							actionID		:"<%=StaffConstKey.ACTION_MYPAGE_EDIT_OK%>",
							empnm			: $("#edit_empnm").val(),
							emp_id			: $("#editx_emp_id").val(),
							user_group_id	: "<%=resultInfo.getUser_group_id() %>",
							status 			: "<%=resultInfo.getStatus() %>",
							password		: passwd,
							pre_password	: $("#pre_password").val(),
							email		    : $("#edit_email").val(),
							smsphone		: $("#edit_smsphone").val()
					};
					
		            $.ajax({
		                type: "POST",
		                url: "<%=StaffConstUrl.MYPAGE_POP_UP_URL%>?v=" + (new Date().getTime()),
		                data: param,
		                dataType: "json",
		                async: true,
		                cache: false,
                		success: function (e) {
                			if (e.resCode == "0") {
		                		//alert(e.resMsg);
		                		$('#mypageModal').modal('hide');
		                		document.getElementById('leftmenu-usernm').innerHTML = $("#edit_empnm").val();
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
		$("#editmyInfo").bind("click", function(){
				$("#myInfoEditForm").submit();
	   	});
	}

function chkPW(){
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var pw = $("#edit_password").val();
	if(false == reg.test(pw)) {
		alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
		return false;
	}else {
		return true;
	}
}
</script>