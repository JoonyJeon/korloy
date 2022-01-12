<%----------------------------------------------------------------------------------------
 - 파일이름	:	system/menu/List.jsp
 - 설    명	:	메뉴데이터관리 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.18    1.0       KYM      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.common.code.CodeTableMng" %>
<%@page import="com.eaction.framework.business.system.menu.constant.MenuConstKey" %>
<%@page import="com.eaction.framework.common.model.MenuInfo" %>
<%@include file="/WEB-INF/jsp/common/include.inc" %>


<%--취득한 게시물 리스트 관리 빈(데이터가 없는경우는 null로 설정되어있다) --%>
<% List listData = (List)request.getAttribute(MenuConstKey.MENU_LIST); %>
<%
	String currMenuId = MenuMng.getMenuId(menu_url);
	String curr_menu_name = StringUtil.nvl(MenuMng.getMenuName(currMenuId, userInfo));
%>



<form name="myForm" id="myForm" method="post" action="<%=ConstUrl.SYSTEM_MENU_URL %>">
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
		<!-- 상단화면명 START -->
			<div class="row">
				
				<!-- 상단화면명 -->
				<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
					<h1 class="page-title txt-color-blueDark">						
						<!-- PAGE HEADER -->
						<i class="fa-fw fa fa-pencil-square-o"></i> 
						<%=curr_menu_name %>
					</h1>
				</div>
				
				<!-- 상단 버튼 -->
				<div class="col-lg-4" style="float: right;height: 66px;padding-top: 10px;text-align: right;">									
					<button type="button" class="btn btn-primary btn-sm" id="menuReload">
						<spring:message code="TEXT.COMM.BTN.RELOAD"/>
					</button>
				</div>
								
			</div>
			<!-- 상단화면명 END -->
			
									
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
										<div class="jarviswidget-editbox">											
										</div>
										<div class="widget-body no-padding">																								
											<div class="table-responsive">
												<table class="table table-bordered">
													<thead>
														<tr>
<%--번호--%>											<th width="50" ><spring:message code="TEXT.MENU.MGMT.LIST.NO"/></td>
<%--타입--%>											<th width="80" ><spring:message code="TEXT.MENU.MGMT.LIST.TYPE"/></td>
<%--메뉴아이디--%>									    <th><spring:message code="TEXT.MENU.MGMT.LIST.MENU_ID"/></td>
<%--상위메뉴아이디--%>								        <th><spring:message code="TEXT.MENU.MGMT.LIST.P_MENU_ID"/></td>
<%--메뉴명칭 --%>									    <th><spring:message code="TEXT.MENU.MGMT.LIST.MENU_NAME"/></td>
<%--메뉴순차번호--%>									    <th width="150" ><spring:message code="TEXT.MENU.MGMT.LIST.MENU_ORDER_NO"/></td>
<%--기능 --%>											<th width="300" ><spring:message code="TEXT.MENU.MGMT.LIST.MENU_FUNC"/></td>
														</tr>
													</thead>
													<tbody>
														<% if ((listData != null) && (listData.size() > 0)) { %>
														    <% for ( int i=0 ; i < listData.size() ; i++) { %>
														        <% MenuInfo menuInfo = (MenuInfo)listData.get(i); %>
														<tr>
															<td style="text-align: center;"><%=i+1 %></td>
													      <td style="text-align: center;"><%=CodeTableMng.getName2(userInfo, "W004", menuInfo.getMenuType()) %></td>
													      <td><%=menuInfo.getMenuId() %></td>
													      <td><%=menuInfo.getUpMenuId() %></td>
													      <td><%=StringUtil.printMenuTitle(menuInfo.getLvl())%><%=StringUtil.nvl(menuInfo.getMenuNm(), ConstKey.KEY_BLANK) %></td>
													      <td style="text-align: center;"><%=menuInfo.getSeq() %></td>
													      <td style="text-align: center;">
													      	<a  href="#" data-target="#menuModal" data-id="<%=menuInfo.getMenuId() %>|<%=Integer.toString(menuInfo.getSeq()) %>|<%=menuInfo.getLvl() %>" class="btn btn-primary btn-sm">
															 	<spring:message code="TEXT.COMM.BTN.INPUT.DOWN" />
															</a>
															<a  href="#" data-target="#menuModModal" data-id="<%=menuInfo.getMenuId() %>" class="btn btn-success btn-sm">
															 	<spring:message code="TEXT.COMM.BTN.UPDATE" />
															</a>
															<button type="button" class="btn btn-warning btn-sm" id="delete_menu" onClick="doDelete('<%=menuInfo.getMenuId()%>' , '<%=Integer.toString(menuInfo.getSeq())%>');return false;">
																<spring:message code="TEXT.COMM.BTN.DELETE" />
															</button>
													      </td>
														</tr>
														<% } %>
													<% } else { %>
													    <tr>
													      <td colspan="7" style="text-align:center">
													      	<button type="button" class="btn btn-warning btn-sm" id="add_menu" onClick="moveDownInput('','0','0');return false;">
																<spring:message code="TEXT.COMM.BTN.INPUT.MENU" />
															</button>													          
													      </td>
													    </tr>
													<% } %>
													</tbody>
												</table>
											</div>
										</div>								
									</div>
								</div>
						</article>						
					</div>													
			</section>
						
	</div>
	<!-- content -->
	
</div>
<!-- main -->
 


<!-- 모든 페이지에 삽입?  -->
<eaction:script common="<%=ConstKey.KEY_YES %>"/>

<script type="text/javascript">
jQuery(document).ready(function(){
	pageSetUp();
	bindClickEvent();
});


function bindClickEvent(){
	//등록 모달 호출
	$('a[data-target=#menuModal]').on('click', function (ev) {
        	ev.preventDefault();
            
        	var my_id_value 	= $(this).data('id').split("|");
            var upmenuId 		= my_id_value[0];
            var seq 				= Number(my_id_value[1]) + 1;
            var level				= Number(my_id_value[2]) + 1;        	
        	
            var param = {
            		upMenuId 	: upmenuId,
            		seq			: seq,
            		lvl			: level
        	}                        
        	$(this).attr('href', '<%=ConstUrl.SYSTEM_MENU_POP_URL%>');
            var target = $(this).attr("href");
            
            // load the url and show modal on success
             $("#menuModal").load(target,param, function () {
                $("#menuModal").modal({backdrop: 'static'});
            });        
    });
	//수정 모달 호출
	$('a[data-target=#menuModModal]').on('click', function (ev) {
    	ev.preventDefault();
        
    	var my_id_value 	= $(this).data('id');
    	var param = {
    			menuId :my_id_value 
    	}
    	
    	$(this).attr('href', '<%=ConstUrl.SYSTEM_MENU_POP_MOD_URL%>');
        var target = $(this).attr("href");
                        
        // load the url and show modal on success
         $("#menuModModal").load(target,param, function () {
            $("#menuModModal").modal({backdrop: 'static'});
        });        
	});
	
	//변경반영
	$("#menuReload").bind("click", function(){
  		doReload();
     });	
}

function doReload(){
	console.log($("#myForm").attr("action"))
	if (confirm("<spring:message code='MSG.CONFIRM.RELOAD'/>")) {
		$("#myForm input[name=actionID]").val("<%=ConstKey.ACTION_RELOAD_OK%>");						
		$('#myForm').submit();
	}
}

function doDelete(menuId) {
	if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
		
		var param = {
				actionID:"<%=ConstKey.ACTION_DELETE_OK%>",
				menuId 			: menuId								
   			};
				
         $.ajax({
            type: "POST",
            url: "<%=ConstUrl.SYSTEM_MENU_URL%>?v=" + (new Date().getTime()),
            data: param,
            dataType: "json",
            async: true,
            cache: false,
            success: function (e) {
            	if (e.resCode == "0") {
            		//alert(e.resMsg);
            		window.location.reload();
            	}else{
            		alert(e.resMsg);
				}
            	
            }, error: function () {
            	alert('<spring:message code="MSG.FAIL"/>');
            }
        });
	}		
}	 




</script>

<!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
<div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
</div>
<div class="modal fade" id="menuModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
</div>
