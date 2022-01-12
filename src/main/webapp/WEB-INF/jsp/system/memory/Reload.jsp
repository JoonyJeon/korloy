<%----------------------------------------------------------------------------------------
 - 파일이름	:	system/memory/Reload.jsp
 - 설    명	:	메모리로드 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.16    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.system.menu.constant.MenuConstKey" %>
<%@page import="com.eaction.framework.business.system.memory.constant.MemoryLoadConstKey" %>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
	String currMenuId = MenuMng.getMenuId(menu_url);
	String curr_menu_name = StringUtil.nvl(MenuMng.getMenuName(currMenuId, userInfo));
%>

<form name="myForm" method="post" action="<%=ConstUrl.SYSTEM_MEMORYLOAD_URL %>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID%>" value="">   
</form>

<div id="main" role="main">

	<!-- RIBBON -->
	<div id="ribbon">
		<!-- breadcrumb -->
			<ol class="breadcrumb">
				<eaction:navi  menuUrl="<%=menu_url %>"></eaction:navi>				
			</ol>
			<!-- end breadcrumb -->
	</div>
	
	<div id="content">
		
			<div class="row">
				<div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
					<h1 class="page-title txt-color-blueDark">						
						<!-- PAGE HEADER -->
						<i class="fa-fw fa fa-pencil-square-o"></i> 
						<%=curr_menu_name %>
					</h1>
				</div>
			</div>
			
			<section id="widget-grid" class="">									
					<!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
				<div class="row">
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="true">
									<!-- widget options:																
										data-widget-colorbutton="false"	
										data-widget-editbutton="false"
										data-widget-togglebutton="false"
										data-widget-deletebutton="false"
										data-widget-fullscreenbutton="false"
										data-widget-custombutton="false"
										data-widget-collapsed="true" 
										data-widget-sortable="false"								
									-->
									<header>
										<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
										<h2></h2>
									</header>
									<div>
									<!-- 위젯 타이틀 변경 브라우져 닫으면 없어짐. -->
										<div class="jarviswidget-editbox">
											<!-- This area used as dropdown edit box -->
											<input class="form-control" type="text">
										</div>
										<div class="widget-body no-padding">																								
											<table class="table table-bordered">
												<thead>
													<tr>
						<!-- 번호 -->				<th width="80"><spring:message code="TEXT.MEMORY.MGMT.LIST.NO"/></td>
						<!-- 설명 -->				<th><spring:message code="TEXT.MEMORY.MGMT.LIST.EXPLAIN"/></td>
														<th width="250"><spring:message code="TEXT.MEMORY.MGMT.LIST.RELOAD"/></td>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>1</td>
														<td><spring:message code="TEXT.MEMORY.MGMT.LIST.GROUP_CODE_RELOAD"/></td>
														<td>
															<button type="button" class="btn btn-warning btn-default" id="grcode_reload" onClick="doReload('<%=MemoryLoadConstKey.ACTION_RELOAD_CODETABLE%>');return false;" style="width:220px">
																<spring:message code="TEXT.MEMORY.MGMT.BUTTON.GROUP_CODE_RELOAD" />
															</button>
														</td>
													</tr>
													<tr>
														<td>2</td>
														<td><spring:message code="TEXT.MEMORY.MGMT.LIST.GROUP_MENU_RELOAD"/></td>
														<td>
															<button type="button" class="btn btn-warning btn-default" id="menu_reload" onClick="doReload('<%=MemoryLoadConstKey.ACTION_RELOAD_MENU%>');return false;" style="width:220px">
																<spring:message code="TEXT.MEMORY.MGMT.BUTTON.GROUP_MENU_RELOAD"/>
															</button>
														</td>
													</tr>
												</tbody>
											</table>
										</div>								
									</div>
								</div>
						</article>						
					</div>													
			</section>
	</div>
	
</div>

<eaction:script common="<%=ConstKey.KEY_YES %>"/>
<script type="text/javascript">
jQuery(document).ready(function(){
	pageSetUp();
	//달력 세팅
  	//datePickerInit();
});


function datePickerInit(){
	var sd = new Date(), ed = new Date();
	 $('#startdate').datetimepicker({ 
	      pickTime: false,
	      format: "YYYY-MM-DD",
	      defaultDate: sd,
	      language : 'ko'
	  });
	  
    $('#finishdate').datetimepicker({
      pickTime: false,
      format: "YYYY-MM-DD",
      defaultDate: ed,
      language : 'ko',
      useCurrent: false
    });
    $("#startdate").on("dp.change", function (e) {    	
           $('#finishdate').data("DateTimePicker").setMinDate(e.date);
     });
/*      $("#finishdate").on("dp.change", function (e) {
         $('#startdate').data("DateTimePicker").setMaxDate(e.date);
     }); */
}


function doReload(actionID){
	var param = {
				actionID:actionID											
			};
			
     $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SYSTEM_MEMORYLOAD_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        success: function (e) {
        	if (e.resCode == "0") {
        		alert(e.resMsg);        		
        	}else{
        		alert(e.resMsg);
			}
        	
        }, error: function () {
        	alert('<spring:message code="MSG.FAIL"/>');
        }
    });
}
</script>