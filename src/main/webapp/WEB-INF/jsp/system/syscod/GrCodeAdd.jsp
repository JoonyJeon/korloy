<%----------------------------------------------------------------------------------------
 - 파일이름	: system/syscod/GrCodeAdd.jsp
 - 설      명	: 시스템그룹코드관리 수정 / 추가 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2018.11.21    1.0     LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.common.util.DisplayUtil"%>
<%@page import="com.eaction.framework.common.model.CodeInfo" %>
<%@page import = "com.eaction.framework.business.system.code.constant.SysCodConstKey"%>
<%@page import = "com.eaction.framework.business.system.code.constant.SysCodConstUrl"%>
<%@page import = "com.eaction.framework.business.system.code.model.SysCod"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
SysCod VO = (SysCod)request.getAttribute(SysCodConstKey.SYSCOD_INSERT_DATA);
%>


<div class="modal-dialog">
	<div class="modal-content">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel"><spring:message code="TEXT.SYS.MGMT.TITLE.SYSCOD_MGMT" /></h4>
		</div>
		
		<div class="modal-body">
			<form id="grCdAddForm" id="grCdAddForm" class="smart-form" novalidate="novalidate">						
						
			<fieldset>
				<div class="row">
<!-- 그룹 코드 -->
					<section class="col col-5">
						<label class="label"><span class="text-warning"> * </span><spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE"/></label>
						<label class="input">							
							<input type="text" name="add_grcode" id="add_grcode">
							<i></i>
						</label>
					</section>
<!-- 코드명 한글 -->
					<section class="col col-5">
						<label class="label"><span class="text-warning"> * </span><spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_K" /></label>
						<label class="input">
							<input type="text" name="add_grcodenm_k" id="add_grcodenm_k">							
						</label>												
					</section>			
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 코드명 영어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_E" /></label>
						<label class="input">
							<input type="text" name="add_grcodenm_e" id="add_grcodenm_e">
						</label>												
					</section>
<!-- 코드명 일어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_J" /></label>
						<label class="input">
							<input type="text" name="add_grcodenm_j" id="add_grcodenm_j">
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
			<button type="button" class="btn btn-primary" id="grCdAdd">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>

	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	
	var errorClass = 'invalid';
	var errorElement = 'em';
	$("#grCdAddForm").validate({
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
	    	add_grcode:{
	    		required : true,
	    		maxlength : 32
	    	},
	    	add_grcodenm_k : {
	    		required : true,
	    		maxlength : 100
	    	}	    	
	    },
	    messages : {
	    	add_grcode: {
				required : '<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODE_REQUIRE'/>'
			},
			add_grcodenm_k : {
				required : '<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODENM_K_REQUIRE'/>'
			}			
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			var param = {
					actionID:"<%=SysCodConstKey.ACTION_SYSCOD_INSERT_OK%>",
					grcode 			: $("#add_grcode").val(),
					grcodenm_k 	: $("#add_grcodenm_k").val(),
					grcodenm_e 	: $("#add_grcodenm_e").val(),
					grcodenm_j 	: $("#add_grcodenm_j").val()
	   			};
			
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_SYSCOD_GRCODE_POP_URL%>?v=" + (new Date().getTime()),
                data: param,
                dataType: "json",
                async: true,
                cache: false,
                success: function (e) {        
                	if (e.resCode == "0") {
                		//alert(e.resMsg);
                		$('#grCdModal').modal('hide');
                		GridReload('upd_dt','desc');
                	}else{
                		alert(e.resMsg);
					}
                	
                }, error: function () {
                	alert('<spring:message code="MSG.FAIL"/>');
                }
            }); 
        }
		
	});
	//클릭 이벤트	
	bindClick();
	
})

function bindClick(){
	//저장
	$("#grCdAdd").bind("click", function(){
		$("#grCdAddForm").submit();
	});	
	
}


</script>