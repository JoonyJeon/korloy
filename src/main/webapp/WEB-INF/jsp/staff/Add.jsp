<%----------------------------------------------------------------------------------------
 - 파일이름	: staff/Add.jsp
 - 설      명	: 스텝 등록 page
 - 추가내용        :
 - 버전관리        : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.04.24    1.0       KSH      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil"%>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstUrl" %>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstKey"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

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
			<form id="staffAddForm" name="staffAddForm" class="smart-form" novalidate="novalidate">
				<!-- 아이디 중복 체크를 위한 값 -->
				<input type="hidden" name="add_checkId" id="add_checkId" value="N">
				<input type="hidden" name="add_checkId_id" id="add_checkId_id" value="">
				<input type="hidden" name="add_user_group_id" id="add_user_group_id" value="0">
				<fieldset>
<!-- ID 이메일 -->
					<section>
						
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMP_ID" />
						</label>
						<div class="row">
						<label class="col-md-8 input" style="padding-left:3%; padding-right:3%;">
							<input type="text" name="add_emp_id" id="add_emp_id" maxlength="100" />
						</label>
						<label class="col-md-2" style="margin-left: 5%;">
							<button type="button" class="btn btn-primary btn-sm" id="empIDcheck"><spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.ID_CHECK" /></button>
						</label>
						</div>
					</section>				
<!-- 이름 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMPNM" />
						</label>
						<label class="input">
							<input type="text" name="add_empnm" id="add_empnm" maxlength="60" />
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
							<eaction:select name="add_user_group_id" id="add_user_group_id" group="AUTH_TYPE" selected="5"/>
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
							<eaction:select name="add_status" id="add_status" group="STATUS" />
							<i></i>
						</label>
					</section>
								
<!-- 비밀번호 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.ADD_STAFF.PASSWORD" />								
						</label>
						<label class="input">
							<input type="password" name="add_password" id="add_password" value="" maxlength="100" />
						</label>
					</section>
<!-- 비밀번호 확인 -->
					<section>
						<label class="label">
							<span class="text-warning"> * </span>
							<spring:message code="TEXT.STAFF.MGMT.ADD_STAFF.REPASSWORD" />
						</label>
						<label class="input">
							<input type="password" name="add_passwordCheck" id="add_passwordCheck" value="" maxlength="100" />
						</label>
						<p id="pwsame" style="color:red;"></p>
					</section>
<!-- 이메일 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMAIL" />
						</label>
						<label class="input">
							<input type="text" name="add_email" id="add_email" maxlength="60" />
						</label>												
					</section>	
<!-- 휴대전화 -->
					<section>
						<label class="label">
							<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.SMSPHONE" />
						</label>
						<label class="input">
							<input type="text" name="add_smsphone" id="add_smsphone" maxlength="20" />
						</label>												
					</section>						
				</fieldset>
			</form>
		</div>

		<div class="modal-footer" align="center">
			<button type="button" class="btn btn-default" data-dismiss="modal">
				<spring:message code="TEXT.CANCEL" />
			</button>
			<eaction:button name="addStaff" id="addStaff" cssId="btn btn-primary btn-sm" menuUrl="<%=menu_url %>" >
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</eaction:button>
		</div>
	</div>
</div>

<!-- END MAIN PANEL -->
<script src="<%=PATH_SCRIPT%>/sha256.js"></script>	
<script type="text/javascript">
jQuery(document).ready(function(){

	//필수값인 input의 label bold처리
	$(".text-warning").parent().css("font-weight","bold");
	
	var errorClass = 'invalid';
	var errorElement = 'em';

	$("#staffAddForm").validate({
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
	    rules: {
	    	add_empnm :{
	    		required : true
	    	},
	    	add_emp_id :{
	    		required : true
	    	},
	    	/*
	    	add_user_group_id:{
	    		required : true
	    	},
	    	*/
	    	add_status:{
	    		required : true
	    	},
	    	add_password:{
	    		required : true
	    	},
	    	add_passwordCheck:{
	    		equalTo : '#add_password'
	    	},
	    	add_email : {				
				email : true
			}
	    },
	    messages : {
	    	add_empnm :{
	    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.EMPNM" />'
	    	},
	    	add_emp_id :{
	    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.EMP_ID" />'	    		
	    	},
	    	/*
	    	add_user_group_id:{
	    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.USER_GROUP_ID" />'
	    	},
	    	*/
	    	add_status :{
	    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.STATUS" />'
	    	},
	    	add_password:{
	    		required : '<spring:message code="MSG.STAFF.MGMT.REQ.PWD" />'
	    	},
	    	add_passwordCheck:{
	    		equalTo : '<spring:message code="MSG.STAFF.MGMT.PASSWORD_CHECK" />'
	    	},
	    	add_email : {				
	    		email : '<spring:message code="MSG.STAFF.MGMT.REQ.EMAIL_FORM" />'
			}
	    },

		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element); 
		},
		submitHandler: function (form) {
			if ($("#add_checkId").val() != "Y") {
				alert("<spring:message code='MSG.STAFF.MGMT.DUP_CHECK'/>");
				$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
   		        $("#add_emp_id").removeClass('valid');
				return false;
			} else if ($("#add_emp_id").val() != $("#add_checkId_id").val()) {
				alert("<spring:message code='MSG.STAFF.MGMT.DUP_CHECK'/>");
				$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
   		        $("#add_emp_id").removeClass('valid');
				return false;
			} 			
			
			var param = {
					actionID:"<%=StaffConstKey.ACTION_STAFF_ADD_OK%>",
					empnm 	    : $("#add_empnm").val(),
					emp_id 		: $("#add_emp_id").val(),
					user_group_id	: $("#add_user_group_id").val(),
					status 			: $("#add_status").val(),
					password 		: sha256_digest($("#add_password").val()),
					email : $("#add_email").val(),
					smsphone :  $("#add_smsphone").val()
			};

            $.ajax({
                type: "POST",
                url: "<%=StaffConstUrl.STAFF_MEMBER_POP_ADD_URL%>?v=" + (new Date().getTime()),
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
                	alert('<spring:message code="MSG.STAFF.MGMT.ALERT.ADD_FAIL" />');
                }
            });
        }

	});
	
	bindEvent();

})

//이벤트 매핑 정의
function bindEvent(){	
	//저장
	$("#addStaff").bind("click", function(){
		$("#staffAddForm").submit();
	});
	
	//중복 체크
	$("#empIDcheck").bind("click", function(){
		var email = $("#add_emp_id").val();
		var checkEmail =  RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
		if(email == null || email == ""){
	        return false;
	    <%--
		}else if(!checkEmail.test($("#add_emp_id").val())){
			alert('<spring:message code="MSG.STAFF.MGMT.REQ.EMAIL_FORM" />');
	        $("#add_emp_id").focus();
	        return false;
	    --%>
		}else{
			checkEmpId();
		}
	});		
	
}
//아이디 중복확인
function checkEmpId() {
		
	if ($("#add_emp_id").val()) {
	   	var param = {actionID:"<%=StaffConstKey.ACTION_CHECK_STAFF_ID%>",
					id : $("#add_emp_id").val()
    	};
	   	
   	$.ajax({
	   		type:"POST",
	   	    dataType: "json",
   	        data: param,
   			async: true,
   			global : false,
   			url : "<%=StaffConstUrl.STAFF_MEMBER_URL%>",
   	        success: function(e){
   	   	   		var str = e.checkId;
   	   			if (str == "NG") {
   	   				alert($("#add_emp_id").val() + " <spring:message code='MSG.STAFF.MGMT.ALERT.ALREADY.STAFF_ID'/>");
   	   				$("#add_checkId").val("N");
   	   		        $("#add_checkId_id").val("");
   	   	   			$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
   		        	$("#add_emp_id").removeClass('valid');
 	   	   			}else{
 	   	   				alert($("#add_emp_id").val() + " <spring:message code='MSG.STAFF.MGMT.ALERT.POSSIBLE.STAFF_ID'/>");
 	   	   				$("#add_checkId").val("Y");
   	   	   				$("#add_checkId_id").val($("#add_emp_id").val());
		   	   		 	$("#add_emp_id").parent().removeClass('state-error').addClass("state-success");
               		 	$("#add_emp_id").addClass('valid');	   	   	   
 	   	   			}				
 	   			}, 
 	   			error: function(){
 	   			}
  	});
    }
}


</script>