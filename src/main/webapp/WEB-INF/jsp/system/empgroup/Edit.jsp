<%----------------------------------------------------------------------------------------
 - 파일이름	: system/empgroup/Edit.jsp
 - 설      명	: 사용자그룹관리 수정 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.14   3.2       Once      웹표준 적용
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.group.constant.EmpGroupConstKey"%>
<%@page import = "com.eaction.framework.business.system.group.model.EmpGroupSearchInfo"%>
<%@page import = "com.eaction.framework.common.model.User"%>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.model.UserGroupInfo" %>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<% List listAuthLevelCodeInfo = (List)request.getAttribute(EmpGroupConstKey.AUTHLEVEL_LIST); //권한레벨 정보 취득 %>

<% UserGroupInfo info = (UserGroupInfo)request.getAttribute(EmpGroupConstKey.EMPGROUP_INFO_DATA); %>

<div class="modal-dialog">
	<div class="modal-content">
				
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel"><spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_MGMT" /></h4>
		</div>
		
		<div class="modal-body">
			<form id="empGroupModForm" id="empGroupModForm" class="smart-form" novalidate="novalidate">
				<input type="hidden" name="mod_code" id="mod_code" value="<%=info.getCode() %>">
			<fieldset>
				<div class="row">
<!-- 사용자 권한 레벨 -->
					<section class="col col-10">
						<label class="label"><span class="text-warning"> * </span><spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_LEVEL"/></label>
						<label class="select">
							<eaction:select name="mod_groupLevel" id="mod_groupLevel" list="<%=listAuthLevelCodeInfo %>" selected="<%=info.getGroupLevel() %>"/>
							<i></i>
						</label>
					</section>		
				</div>
			</fieldset>
									
			<fieldset>
				<div class="row">
			<!-- 레벨명 -->
					<section class="col col-10">
						<label class="label"><span class="text-warning"> * </span><spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_NAME" /></label>
						<label class="input">
							<input type="text" name="mod_name" id="mod_name" value="<%=info.getName()%>">
						</label>												
					</section>	
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
			<!-- 설명 -->
					<section class="col col-10">
						<label class="label"><spring:message code="TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_EXPLAIN" /></label>
						<label class="input">
							<input type="text" name="mod_remark" id="mod_remark" value="<%=info.getRemark()%>">
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
			<button type="button" class="btn btn-primary" id="empGroupMod">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>

	</div>
</div> 

<script type="text/javascript">
jQuery(document).ready(function(){	
		
	var errorClass = 'invalid';
	var errorElement = 'em';
	$("#empGroupModForm").validate({
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
	    	mod_groupLevel: {
	    		required : true
	    	},
	    	mod_name:{
	    		required : true,
	    		maxlength : 50
	    	},
	    	mod_remark:{
	    		maxlength : 100
	    	}	    	
	    },
	    messages : {
	    	mod_groupLevel: {
				required : '<spring:message code='MSG.EMP.GROUP.ALERT.GROUP_LEVEL'/>'
			},
	    	mod_name: {
				required : '<spring:message code='MSG.EMP.GROUP.ALERT.REQUIRE_NAME'/>'
			}			
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {
			var param = {
					actionID:"<%=ConstKey.ACTION_UPDATE_OK%>",
					name			: $("#mod_name").val(),
					remark 			: $("#mod_remark").val(),
					groupLevel		: $("#mod_groupLevel").val(),
					code			: $("#mod_code").val()
	   			};
			console.log(param);
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_EMPGROUP_POP_MOD_URL%>?v=" + (new Date().getTime()),
                data: param,
                dataType: "json",
                async: true,
                cache: false,
                success: function (e) {
                	if (e.resCode == "0") {
                		//alert(e.resMsg);
                		$('#empGroupModModal').modal('hide');
                		goEmpGroupList();
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
	$("#empGroupMod").bind("click", function(){
		$("#empGroupModForm").submit();
	});	
}


</script>
