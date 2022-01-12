<%----------------------------------------------------------------------------------------
 - 파일이름	:	system/menu/Update.jsp
 - 설    명	:	메뉴데이터관리 메슈수정 화면
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

<script type="text/javascript">

	<%-- 체크처리후 등록처리 --%>
	<%-- function doUpdate() {
	    // 기본입력체크
	    $("#myForm").validationEngine('attach', { prettySelect: true, usePrefix: 's2id_',promptPosition: "bottomRight" ,scroll:false});
		var validateForm  = $(document.myForm).validationEngine('validate');	
		if(validateForm){
	    	goPageTwo(myForm,    "<%=ConstKey.ACTION_UPDATE_OK%>");
	    }
	} --%>

	<%-- 글목록으로 이동 --%>
	<%-- function moveList() {
		goPageTwo(myForm,    "<%=ConstKey.ACTION_LIST%>");
	} --%>


</SCRIPT>

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
			<form id="menuModForm" id="menuModForm" class="smart-form" novalidate="novalidate">
			
			<fieldset>
				<div class="row">
<!-- 상위메뉴아이디 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.LIST.P_MENU_ID" /></label>
						<label class="input">
							<input name="modUpMenuId" id="modUpMenuId" type="text" value="<%=menuInfo.getUpMenuId() %>" disabled="disabled">							
						</label>												
					</section>
<!-- 메뉴타입 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.TYPE_TEXT" /></label>
						<label class="select">
							<eaction:select name="modMenuType" id="modMenuType"  group="W004" event="onChange=\"checkInputCondition();\"" selected="<%=menuInfo.getMenuType() %>"/>
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
							<input name="modMenuId" id="modMenuId" type="text" value="<%=menuInfo.getMenuId() %>" disabled="disabled">
						</label>												
					</section>
<!-- 메뉴명칭 한국어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_KOR" /></label>
						<label class="input">
							<input name="modMenuNmKor" id="modMenuNmKor" type="text" style="ime-mode:active;" value="<%=menuInfo.getMenuNmKor() %>">
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
							<input name="modMenuNmEng" id="modMenuNmEng" type="text" value="<%=menuInfo.getMenuNmEng() %>">
						</label>												
					</section>
<!-- 메뉴명칭 일어 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.MENU_NAME_TEXT_JPN" /></label>
						<label class="input">
							<input name="modMenuNmJpn" id="modMenuNmJpn" type="text" value="<%=menuInfo.getMenuNmJpn() %>">
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
							<input name="modSeq" id="modSeq" type="text" value="<%=menuInfo.getSeq() %>">
						</label>												
					</section>
<!-- 화면링크 -->
					<section class="col col-5">
						<label class="label"><spring:message code="TEXT.MENU.MGMT.TITLE.URL_TEXT" /></label>
						<label class="input">
							<input name="modUrl" id="modUrl" type="text" value="<%=menuInfo.getUrl() %>">
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
							<input name="modMenuIcon" id="modMenuIcon" type="text" value="<%=menuInfo.getMenu_icon() %>">
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
			<button type="button" class="btn btn-primary" id="menuMod">
				<spring:message code="TEXT.COMM.BTN.SAVE" />
			</button>
		</div>

	</div>
</div>

<script type="text/javascript">
jQuery(document).ready(function(){
	
	var errorClass = 'invalid';
	var errorElement = 'em';
	$("#menuModForm").validate({
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
	    	modMenuId : {
	    		required : true
	    	},
	    	modMenuNmKor :{
	    		required : true
	    	},	    	
	    	modMenuNmEng : {				
	    		required : true
			},
	    },
	    messages : {
	    	modMenuId: {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_ID'/>'
			},
			modMenuNmKor: {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_KR'/>'
			},
			modMenuNmEng : {
				required : '<spring:message code='MSG.MENU.ALERT.REQUIRE_NAME_ENG'/>'
			}
	    },
		// Do not change code below
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		},
		submitHandler: function (form) {			
			var param = {
					actionID:"<%=ConstKey.ACTION_UPDATE_OK%>",
					menuId 			: $("#modMenuId").val(),
					menuNmKor		: $("#modMenuNmKor").val(),
					menuNmEng		: $("#modMenuNmEng").val(),
					menuNmJpn		: $("#modMenuNmJpn").val(),
					upMenuId		: $("#modUpMenuId").val(),
					menuType		: $("#modMenuType").val(),
					seq				: $("#modSeq").val(),
					url				: $("#modUrl").val(),
					menu_icon		: $("#modMenuIcon").val()					
	   			};
			
			
             $.ajax({
                type: "POST",
                url: "<%=ConstUrl.SYSTEM_MENU_POP_MOD_URL%>?v=" + (new Date().getTime()),
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
	$("#menuMod").bind("click", function(){
		$("#menuModForm").submit();
	});
}
/* 선택된 메뉴 타입에 따라 화면링크를 쓰거나 쓸수 없게 만든다 */
function checkInputCondition() {
    if ($("#modMenuType").val() == "<%=ConstKey.MENU_TYPE_G%>" ) {
    	$("#modUrl").val("");
    	$("#modUrl").prop("readonly", true);    	
    } else {
    	$("#modUrl").prop("readonly", false);
    }
}
</script>