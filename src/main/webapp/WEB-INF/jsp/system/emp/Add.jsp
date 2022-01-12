<%----------------------------------------------------------------------------------------
 - 파일이름	: system/emp/Add.jsp
 - 설      명	: 사용자관리 등록 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.15    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstUrl"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstKey"%>
<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.emp.constant.EmpConstKey"%>
<%@page import = "com.eaction.framework.business.system.emp.model.EmpSearchInfo"%>
<%@page import="com.eaction.framework.common.util.DateTimeUtil"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>


<% 
// 사용자그룹정보 취득
List listUserGroupCodeInfo = (List)request.getAttribute(EmpConstKey.EMP_USER_GROUP_DATA);
//검색정보
EmpSearchInfo searchInfo = (EmpSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
%>
<div class="modal-dialog">
	<div class="modal-content">
				


		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel"><spring:message code="TEXT.EMP.MGMT.SUBTITLE.EMP_MGMT_DETAIL" /></h4>
		</div>
		

		
		<div class="modal-body">
			<form id="empAddForm" id="empAddForm" class="smart-form" novalidate="novalidate">						
			<input type="hidden" name="add_password" id="add_password" value="">
			<input type="hidden" name="add_checkId" id="add_checkId" value="N">
			<input type="hidden" name="add_checkId_id" id="add_checkId_id" value="">
			<fieldset>
				<div class="row">
<!-- 사용자 그룹 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_GROUP"/></label>
						<label class="select">
							<eaction:select name="add_user_group_id" id="add_user_group_id" init="<%=ConstKey.KEY_YES %>" initCode="" initName="" list="<%=listUserGroupCodeInfo %>"/>
							<i></i>
						</label>
					</section>
<!-- 아이디 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_ID" /></label>
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="add_emp_id" id="add_emp_id">							
						</label>												
					</section>
<!-- 체크버튼 -->
					<section class="col col-2">
						<label class="label">&nbsp;</label>
						<button type="button" class="btn btn-primary btn-sm" id="idDupChk">
						<spring:message code="TEXT.COMM.BTN.DUP" />
						</button>
					</section>					
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 비밀번호 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD" /></label>
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="add_passwd1" id="add_passwd1">
						</label>												
					</section>
<!-- 비밀번호 확인 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.REPASSWD" /></label>
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="add_passwd2" id="add_passwd2">
						</label>												
					</section>				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 사용자이름 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_NAME" /></label>
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="add_empNm" id="add_empNm">
						</label>												
					</section>
<!-- 이메일 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMAIL" /></label>
						<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
							<input type="text" name="add_email" id="add_email">
						</label>												
					</section>				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 휴대폰 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.PHONE" /></label>
						<label class="input"> <i class="icon-append fa fa-phone"></i>
							<input type="text" name="add_smsPhone" id="add_smsPhone" data-mask="999-999-9999" data-mask-placeholder= "X">
						</label>												
					</section>		
				</div>
			</fieldset>			
			</from>		
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">				
				<spring:message code="TEXT.CANCEL" />
			</button>
			<button type="button" class="btn btn-primary" id="empAdd">
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
	$("#empAddForm").validate({
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
	    	add_user_group_id :{
	    		required : true
	    	},	
	    	add_emp_id:{
	    		required : true
	    	},
	    	add_passwd1 : {
	    		required : true
	    	},
	    	add_passwd2 : {
	    		required : true,
	    		equalTo : '#add_passwd1'
	    	},
	    	add_empNm :{
	    		required : true
	    	},	    	
	    	add_email : {				
				email : true
			},
	    },
	    messages : {
	    	add_user_group_id: {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.GROUP_ID'/>'
			},
	    	add_emp_id: {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_ID'/>'
			},
			add_passwd1 : {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_PASSWORD'/>'
			},
			add_passwd2: {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_PASSWORD'/>',
				equalTo : '<spring:message code='MSG.EMP.MGMT.ALERT.PASSWORD'/>'
			},
			add_empNm: {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_NAME'/>'
			},
			add_email : {
				email : '<spring:message code='MSG.EMP.MGMT.ALERT.EMAIL'/>'
			}
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			if ($("#add_checkId").val() != "Y") {
				alert("<spring:message code='MSG.EMP.MGMT.ALERT.EMPID_DUP'/>");
				$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
   		        $("#add_emp_id").removeClass('valid');
				return false;
			} else if ($("#add_emp_id").val() != $("#add_checkId_id").val()) {
				alert("<spring:message code='MSG.EMP.MGMT.ALERT.CHG.CHECK_ID'/>");
				$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
   		        $("#add_emp_id").removeClass('valid');
				return false;
			}
			if(!chkPW()){
				return false;
			}
			var param = {
					actionID:"<%=EmpConstKey.ACTION_EMP_INSERT_OK%>",
					user_group_id 	: $("#add_user_group_id").val(),
					emp_id 			: $("#add_emp_id").val(),
					password 		: sha256_digest($("#add_passwd1").val()),
					empNm 			: $("#add_empNm").val(),
					email 				: $("#add_email").val(),
					smsPhone		: $("#add_smsPhone").val(),
	   			};
			
             $.ajax({
    	   			type:"POST",
       	   	        dataType: "json",
       	   	        data: param,
       	   			async: true,
       	   			global : false,
       	   			url : "<%=ConstUrl.SYSTEM_EMP_POP_URL%>",
                success: function (e) {      
                	if (e.resCode == "0") {
                		//alert(e.resMsg);
                		$('#empModal').modal('hide');
                		goEmpList();
                	}else{
                		alert(e.resMsg);
                		console.log('서버 요청 후 실패');
					}
                }, error: function (request, error) {
                	alert('<spring:message code="MSG.FAIL"/>');
                	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            }); 
        }
		
	});
	
	
	bindEvent();

})

//각 클릭 이벤트
function bindEvent(){
	//중복 체크
	$("#idDupChk").bind("click", function(){
		checkBasicUserId();
	});
	//저장
	$("#empAdd").bind("click", function(){
		$("#empAddForm").submit();
	});	
}

//아이디 중복확인
function checkBasicUserId() {
		
	if ($("#add_emp_id").val()) {
	   	var param = {actionID:"<%=ConstKey.ACTION_CHECK_USERID%>",
					id : $("#add_emp_id").val()
	   			};
    	$.ajax({
   	   			type:"POST",
   	   	        dataType: "json",
   	   	        data: param,
   	   			async: true,
   	   			global : false,
   	   			url : "<%=ConstUrl.SYSTEM_EMP_POP_URL%>",
   	   	        success: function(e){
   	   	   	   		var str = e.checkId;
   	   	   			if (str == "NG") {
   	   	   				alert($("#add_emp_id").val() + " <spring:message code='MSG.EMP.MGMT.ALERT.ALREADY_EMPID'/>");
   	   	   				$("#add_checkId").val("N");
   	   	   		        $("#add_checkId_id").val("");
   	   	   				//$("#add_emp_id").val("");
   	   	   				//$("#add_emp_id").focus();   
		   	   	   		$("#add_emp_id").parent().removeClass('state-success').addClass("state-error");
		   		        $("#add_emp_id").removeClass('valid');
   	   	   			}else{
   	   	   				alert($("#add_emp_id").val() + " <spring:message code='MSG.EMP.MGMT.ALERT.POSSIBLE_EMPID'/>");
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

function chkPW(){
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var pw = $("#add_passwd1").val();
	if(false == reg.test(pw)) {
		alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
		return false;
	}else {
		return true;
	}
}
</script>