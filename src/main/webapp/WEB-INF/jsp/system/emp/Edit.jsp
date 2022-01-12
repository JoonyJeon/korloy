<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/emp/Edit.jsp
 - 설      명	: 사용자관리 수정 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.15    1.0      KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstUrl"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstKey"%>
<%@page import = "com.eaction.framework.business.system.emp.constant.EmpConstKey"%>
<%@page import = "com.eaction.framework.business.system.emp.model.EmpInfo"%>
<%@page import = "com.eaction.framework.business.system.emp.model.EmpSearchInfo"%>
<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<% 
//사용자그룹정보 취득
List listUserGroupCodeInfo = (List)request.getAttribute(EmpConstKey.EMP_USER_GROUP_DATA);
//검색정보
EmpSearchInfo searchInfo = (EmpSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
//상세정보
EmpInfo resultInfo = (EmpInfo)request.getAttribute(EmpConstKey.EMP_DETAIL_DATA);
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
			<form id="empModForm" id="empModForm" class="smart-form" novalidate="novalidate">
			<input type="hidden" name="mod_password" id="mod_password" value="">
			<input type="hidden" name="mod_password_chg" id="mod_password_chg" value="N"/>
			
			
			<fieldset>
				<div class="row">
<!-- 사용자 그룹 -->
					<section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_GROUP"/></label>
						<label class="select">
							<eaction:select name="mod_user_group_id" id="mod_user_group_id" list="<%=listUserGroupCodeInfo %>" selected="<%=resultInfo.getUser_group_id() %>"/>
							<i></i>
						</label>
					</section>
<!-- 아이디 -->
					<section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_ID" /></label>
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="mod_emp_id" id="mod_emp_id" value="<%=resultInfo.getEmp_id() %>" disabled="disabled">							
						</label>												
					</section>
				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 이전 비밀번호 -->
					<section class="col col-4">
						<label class="label">이전 비밀 번호</label>
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="pre_password" id="pre_password" >
						</label>												
					</section>
<!-- 비밀번호 -->
					<section class="col col-4">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD" /></label>
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="mod_passwd1" id="mod_passwd1" placeholder="<spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD.INPUT" />" onchange="javascrpt:onChangePass(this)">
						</label>												
					</section>
<!-- 비밀번호 확인 -->
					<section class="col col-4">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.REPASSWD" /></label>
						<label class="input"> <i class="icon-append fa fa-lock"></i>
							<input type="password" name="mod_passwd2" id="mod_passwd2" placeholder="<spring:message code="TEXT.EMP.MGMT.DETAIL.PASSWD.INPUT" />">
						</label>												
					</section>				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 사용자이름 -->
					<section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMP_NAME" /></label>
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" name="mod_empNm" id="mod_empNm" value="<%=resultInfo.getEmpNm() %>">
						</label>												
					</section>
<!-- 사용상태 -->
                    <section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.STATUS"/></label>
						<label class="select">
							<eaction:select name="mod_status" id="mod_status" group="STATUS" selected="<%=resultInfo.getStatus() %>"/>
							<i></i>
						</label>
					</section>		
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 이메일 -->
					<section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.EMAIL" /></label>
						<label class="input"> <i class="icon-append fa fa-envelope-o"></i>
							<input type="text" name="mod_email" id="mod_email" value="<%=resultInfo.getEmail() %>">
						</label>												
					</section>							
<!-- 휴대폰 -->
					<section class="col col-6">
						<label class="label"><spring:message code="TEXT.EMP.MGMT.DETAIL.PHONE" /></label>
						<label class="input"> <i class="icon-append fa fa-phone"></i>
							<input type="text" name="mod_smsPhone" id="mod_smsPhone" value="<%=resultInfo.getSmsPhone() %>" data-mask="999-999-9999" data-mask-placeholder= "X">
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
			<button type="button" class="btn btn-primary" id="empMod">
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
	$("#empModForm").validate({
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
	    	mod_passwd2 : {	    		
	    		equalTo : '#mod_passwd1'
	    	},
	    	mod_empNm :{
	    		required : true
	    	},
	    	mod_email : {				
				email : true
			},
	    },
	    messages : {
	    	
			mod_passwd2: {
				equalTo : '<spring:message code='MSG.EMP.MGMT.ALERT.PASSWORD'/>'
			},
			mod_empNm: {
				required : '<spring:message code='MSG.EMP.MGMT.ALERT.REQUIRE_NAME'/>'
			},
			mod_email : {
				email : '<spring:message code='MSG.EMP.MGMT.ALERT.EMAIL'/>'
			}
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			if($("#mod_password_chg").val() == "Y") {
				if(chkPW()){
					$("#mod_password").val(sha256_digest($("#mod_passwd1").val()))
					if($("#pre_password").val() == ""){
						alert('이전 비밀번호를 입력해 주세요');
						return false;
					}
					$("#pre_password").val(sha256_digest($("#pre_password").val()))
				}else{
					return false;
				}
			}else{
				$("#mod_password").val($("#mod_passwd1").val())
				$("#pre_password").val($("#pre_password").val())
			}
			var param = {
					actionID:"<%=EmpConstKey.ACTION_EMP_UPDATE_OK%>",
					user_group_id 	: $("#mod_user_group_id").val(),
					emp_id 			: $("#mod_emp_id").val(),
					pre_password 	: $("#pre_password").val(),
					password 		: $("#mod_password").val(),
					empNm 			: $("#mod_empNm").val(),
					email 				: $("#mod_email").val(),
					smsPhone		: $("#mod_smsPhone").val(),
					status		    : $("#mod_status").val(),
	   			};
			
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_EMP_POP_MOD_URL%>?v=" + (new Date().getTime()),
                data: param,
                dataType: "json",
                async: true,
                cache: false,
                success: function (e) { 
                	if (e.resCode == "0") {
                		//alert(e.resMsg);
                		$('#empModModal').modal('hide');
                		goEmpList();
                	}else{
                		alert(e.resMsg);
					}
                }, error: function () {
                	alert('<spring:message code="MSG.FAIL"/>');
                }
            }); 
        }
		
	});
	
	
	bindEvent();

})

//각 클릭 이벤트
function bindEvent(){
	//저장
	$("#empMod").bind("click", function(){
		$("#empModForm").submit();
	});	
}
//비밀번호 변경
function onChangePass(obj){
	$("#mod_passwd2").val("");
	$("#mod_password_chg").val("Y");
}

function chkPW(){
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	var pw = $("#mod_passwd1").val();
	if(false == reg.test(pw)) {
		alert('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
		return false;
	}else {
		return true;
	}
}
</script>