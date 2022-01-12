<%----------------------------------------------------------------------------------------
 - 파일이름	:	system/menu/Write.jsp
 - 설    명	:	메뉴데이터관리 등록 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.29    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.system.menu.constant.MenuConstKey" %>
<%@page import="com.eaction.framework.common.model.MenuInfo" %>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<% MenuInfo menuInfo = (MenuInfo)request.getAttribute(MenuConstKey.MENU_INFO_DATA); %>


<!--내용시작 -->
<div class="modal-dialog">
	<div class="modal-content">

		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
			</button>
			<h4 class="modal-title" id="myModalLabel"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_MGMT" /></h4>
		</div>
		
		
		<div class="modal-body">
			<form id="menuAddForm" id="menuAddForm" class="smart-form" novalidate="novalidate">				
				<input type="hidden" name="addLevel" id="addLevel" value="<%=menuInfo.getLvl() %>">
			
			<fieldset>
				<div class="row">
<!-- 상위메뉴아이디 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.LIST.P_MENU_ID" /></label>
						<label class="input">
							<input name="addUpMenuId" id="addUpMenuId" type="text" value="<%=menuInfo.getUpMenuId() %>" disabled="disabled">							
						</label>												
					</section>
<!-- 메뉴타입 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.TYPE_TEXT" /></label>
						<label class="select">
							<eaction:select name="addMenuType" id="addMenuType"  group="W004" init="<%=ConstKey.KEY_YES %>" initCode="" initName="" event="onChange=\"checkInputCondition();\""/>
							<i></i>
						</label>
					</section>
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 메뉴아이디 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_ID_TEXT" /></label>
						<label class="input">
							<input name="addMenuId" id="addMenuId" type="text">
						</label>												
					</section>
<!-- 메뉴명칭 한국어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_KOR" /></label>
						<label class="input">
							<input name="addMenuNmKor" id="addMenuNmKor" type="text" style="ime-mode:active;">
						</label>												
					</section>				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 메뉴명칭 영어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_ENG" /></label>
						<label class="input">
							<input name="addMenuNmEng" id="addMenuNmEng" type="text">
						</label>												
					</section>
<!-- 메뉴명칭 일어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_JPN" /></label>
						<label class="input">
							<input name="addMenuNmJpn" id="addMenuNmJpn" type="text">
						</label>												
					</section>				
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 순번 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.SIBLING_NO_TEXT" /></label>
						<label class="input">
							<input name="addSeq" id="addSeq" type="text" value="<%=menuInfo.getSeq() %>">
						</label>												
					</section>
<!-- 화면링크 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.URL_TEXT" /></label>
						<label class="input">
							<input name="addUrl" id="addUrl" type="text">
						</label>
					</section>
				</div>
			</fieldset>
			
			<fieldset>
				<div class="row">
<!-- 아이콘 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_ICON" /></label>
						<label class="input">
							<input name="addMenuIcon" id="addMenuIcon" type="text">
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
			<button type="button" class="btn btn-primary" id="menuAdd">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>

	</div>
</div>

<script type="text/javascript">
jQuery(document).ready(function(){
	
	var errorClass = 'invalid';
	var errorElement = 'em';
	$("#menuAddForm").validate({
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
	    	addMenuType : {
	    		required : true
	    	},
	    	addMenuId : {
	    		required : true
	    	},
	    	addMenuNmKor :{
	    		required : true
	    	},	    	
	    	addMenuNmEng : {				
	    		required : true
			},
	    },
	    messages : {
	    	addMenuType: {
				required : '<spring:message code='MSG.MENU.ALERT.MENU_TYPE'/>'
			},
	    	addMenuId: {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_ID'/>'
			},
			addMenuNmKor: {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_KR'/>'
			},
			addMenuNmEng : {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_ENG'/>'
			}
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {			
			var param = {
					actionID:"<%=ConstKey.ACTION_INSERT_OK%>",
					menuId 			: $("#addMenuId").val(),
					menuNmKor		: $("#addMenuNmKor").val(),
					menuNmEng		: $("#addMenuNmEng").val(),
					menuNmJpn		: $("#addMenuNmJpn").val(),
					upMenuId		: $("#addUpMenuId").val(),
					menuType		: $("#addMenuType").val(),
					seq				: $("#addSeq").val(),
					url				: $("#addUrl").val(),
					menu_icon		: $("#addMenuIcon").val(),
					lvl				: $("#addLevel").val()
	   			};
			console.log("MENU___PARAM~~",JSON.stringify(param))
			
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_MENU_POP_URL%>?v=" + (new Date().getTime()),
                data: param,
                dataType: "json",
                async: true,
                cache: false,
                success: function (e) {   
                	if (e.resCode == "0") {
                		//alert(e.resMsg);
                		$('#menuModal').modal('hide');
                		window.location.reload();
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
	$("#menuAdd").bind("click", function(){
		$("#menuAddForm").submit();
	});
}
/* 선택된 메뉴 타입에 따라 화면링크를 쓰거나 쓸수 없게 만든다 */
function checkInputCondition() {
    if ($("#addMenuType").val() == "<%=ConstKey.MENU_TYPE_G%>" ) {
    	$("#addUrl").val("");
    	$("#addUrl").prop("readonly", true);    	
    } else {
    	$("#addUrl").prop("readonly", false);
    }
}
</script>