<%----------------------------------------------------------------------------------------
 - 파일이름	: system/syscod/CodeAdd.jsp
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
<%@page import="com.eaction.framework.common.model.CodeInfo" %>
<%@page import = "com.eaction.framework.business.system.code.constant.SysCodConstKey"%>
<%@page import = "com.eaction.framework.business.system.code.constant.SysCodConstUrl"%>
<%@page import = "com.eaction.framework.business.system.ipMgmt.constant.IpMgmtConstKey"%>
<%@page import = "com.eaction.framework.business.system.ipMgmt.constant.IpMgmtConstUrl"%>
<%@page import = "com.eaction.framework.business.system.code.model.SysCod"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
List listAuthLevelCodeInfo = (List)request.getAttribute(SysCodConstKey.AUTHLEVEL_LIST); //권한레벨 정보 취득	
SysCod VO = (SysCod)request.getAttribute(SysCodConstKey.SYSCODDTL_INSERT_DATA);
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
			<form id="grCdDtAddForm" class="smart-form" novalidate="novalidate">
		
			<fieldset>
				<div class="row">
<!-- ip -->
					<section class="col col-5">
						<label class="label"><span class="text-warning"> * </span>ip</label>
						<label class="input">							
							<input type="text" name="add_code" id="add_code">
							<i></i>
						</label>
					</section>	
				</div>
			</fieldset>
			
			</form>		
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">				
				<spring:message code="TEXT.CANCEL" />
			</button>
			<button type="button" class="btn btn-primary" id="grCdDtAdd">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>

	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#add_levelcod").val("999");
	
	var errorClass = 'invalid';
	var errorElement = 'em';
	$("#grCdDtAddForm").validate({
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
	    	add_code:{
	    		required : true,
	    		maxlength : 50
	    	}   	
	    },
	    messages : {
	    	add_code: {
				required : 'ip 주소를 입력해주세요'
			}	
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			var param = {
					actionID:"<%=IpMgmtConstKey.ACTION_SYSTEM_IPADMIN_INSERT_OK%>",
					grcode		: 'MGMT_USER_IP_LIST',
					code 		: $("#add_code").val(),
					codenm		: 'ip',
					levelcod	: '999',
        			use_yn		: 'Y'
	   			};
			
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_IPMGMT_POP_ADD_URL%>?v=" + (new Date().getTime()),
                data: param,
                dataType: "json",
                async: true,
                cache: false,
                success: function (e) {                	
                	if (e.resCode == "0") {
                		alert(e.resMsg);
                		goList();
                		$('#myModal').modal('hide');
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
	$("#grCdDtAdd").bind("click", function(){
		$("#grCdDtAddForm").submit();
	});	
	
}


</script>
