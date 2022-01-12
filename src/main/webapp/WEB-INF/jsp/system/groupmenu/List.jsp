<%----------------------------------------------------------------------------------------
 - 파일이름	:	system/groupmenu/List.jsp
 - 설    명	:	권한별메뉴관리 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.12    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="com.eaction.framework.business.common.menu.MenuMng"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Locale" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.common.code.CodeTableMng" %>
<%@page import="com.eaction.framework.business.system.groupmenu.constant.GroupMenuConstKey" %>
<%@page import="com.eaction.framework.common.model.MenuInfo" %>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>


<% List listAuthLevelCodeInfo = (List)request.getAttribute(GroupMenuConstKey.AUTHLEVEL_LIST); //권한레벨 정보 취득 %>

<%--취득한 게시물 리스트 관리 빈(데이터가 없는경우는 null로 설정되어있다) --%>
<% List listData = (List)request.getAttribute(GroupMenuConstKey.GROUPMENU_LIST); %>
<% String groupLevel = StringUtil.nvl(request.getParameter("groupLevel")); %>
<%
	String currMenuId = MenuMng.getMenuId(menu_url);
	String curr_menu_name = StringUtil.nvl(MenuMng.getMenuName(currMenuId, userInfo));
	
	Locale locale = ConfigMng.getLocale(request);
	String user_lang = ConfigMng.getLanguage(locale);
%>
<form name="myForm" id="myForm" method="post" action="<%=ConstUrl.SYSTEM_GROUPMENU_URL %>">
<input type="hidden" name="<%=ConstKey.ACTION_ID%>" value="">
<input type="hidden" id="groupLevel" name="groupLevel">


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
					<button type="button" class="btn btn-primary btn-sm" id="groupMenuAdd">
						<spring:message code="TEXT.GROUPMENU.MGMT.BUTTON.GROUPMENU_MENU_REGIST"/>
					</button>
					<button type="button" class="btn btn-primary btn-sm" id="groupMenuReload">
						<spring:message code="TEXT.COMM.BTN.RELOAD"/>
					</button>
				</div>
								
			</div>
			<!-- 상단화면명 END -->
			
			<!-- 검색박스 START -->
			<div class="row">				
				<div class="col-sm-12 col-md-12 col-lg-12">												
					<div class="well">
						<!-- <div class="smart-form"> -->						
								<section class="col" style="width: 10.33%;">
									<div class="btn-group" style="margin-right:19px;">
										<button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" id="btn_select_groupLevel"></button>
										<ul class="dropdown-menu" id="select_groupLevel">
										</ul>
									</div>
								</section>
							<!-- </div> -->
						</div>
				</div>						
			</div>
			<!-- 검색박스 END -->
			
			
									
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
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_NO" /></th>
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_TYPE" /></th>
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_ID" /></th>
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_P_MENU_ID" /></th>
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_MENU_NAME" /></th>
															<%-- 
															<th style="font-size:14px"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.GROUPMENU_EDIT" /></th>
															--%>
														</tr>
													</thead>
													<tbody>
														<% if ((listData != null) && (listData.size() > 0)) { %>
														    <% for ( int i=0 ; i < listData.size() ; i++) { %>
														        <% MenuInfo menuInfo = (MenuInfo)listData.get(i); %>
														    <tr>
														      <td><%=menuInfo.getSeq() %></td>
														      <td style="text-align:center"><%=CodeTableMng.getName(userInfo, "W004", menuInfo.getMenuType(), locale) %></td>
														      <td ><%=menuInfo.getMenuId() %></td>
														      <td ><%=menuInfo.getUpMenuId() %></td>
														      <td  style="text-align:left;"><%=StringUtil.printMenuTitle(menuInfo.getLvl())%><input type="checkbox" name="menuId" value="<%=menuInfo.getMenuId()%>" <%=menuInfo.getIsChecked() %> onClick="doCheckRelation(<%=i%>);">
												                                             <span style="vertical-align: middle;"><%=StringUtil.nvl(menuInfo.getMenuNm(), ConstKey.KEY_BLANK) %></span>
												                                             <input type="hidden" name="level" value="<%=menuInfo.getLvl() %>">
												                                             <input type="hidden" name="upMenuId" value="<%=menuInfo.getUpMenuId() %>">
												                                             <input type="hidden" name="menuIdStr">
												              </td>
												              <%--
														      <td  style="text-align:left;">
														      <% if(!"G".equals(menuInfo.getMenuType())) { %>
														      	<input type="checkbox" id="arEdit_yn" name="arEdit_yn" value="<%=menuInfo.getMenuId()%>" <%=menuInfo.IsEdit_yn_checked() %> >
												                <span style="vertical-align: middle;"><spring:message code="TEXT.GROUPMENU.MGMT.TITLE.OPERATION_EDIT" /></span>
												              <% } %>
												              </td>
												              --%>
														    </tr>
															<% } %>
														<% } else { %>
														    <tr height="21">
														      <td colspan="5" style="text-align:center">
														          <spring:message code="TEXT.EMPGROUPMENU.MGMT.LIST.MENU_NO_DATA" />
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
</form>

<eaction:script common="<%=ConstKey.KEY_YES %>"/>

<script type="text/javascript">
var selgrlvl  = "<%=groupLevel%>";
var groupLvl = "";

jQuery(document).ready(function(){
	pageSetUp();
	
	var combo_url = "<%=ConstUrl.SYSTEM_GROUPMENU_URL%>";
	var combo_actId = "<%=GroupMenuConstKey.AUTHLEVEL_LIST%>"
	groupLvl = getListBtnDropDown(combo_url,combo_actId, "select_groupLevel", "<spring:message code="TEXT.COMM.SEL.CHOICE"/>", selgrlvl, "<%=userInfo.getUser_lang()%>");
	
	bindClickEvent();	
});
//버튼 드랍다운 체인지 이벤트 무조건 있어야 함.
function changeBtnDdVal(obj, btn_target){	
	groupLvl = obj.id;	
	var btn_target = $("#"+btn_target);
	btn_target.html(obj.text + " <span class=\"caret\"></span>");
	searchList();
}

//검색
function searchList() {
	$("#myForm input[name=actionID]").val("<%=GroupMenuConstKey.GROUPMENU_ACTION_LIST%>");
	$("#groupLevel").val(groupLvl);
	$('#myForm').submit();
}

function bindClickEvent(){	
	$("#groupMenuAdd").bind("click", function(){
		doGroupMenuSetting();
     });
	$("#groupMenuReload").bind("click", function(){
		doReload();
    });
}
<%-- 새로운 메뉴를 등록하는 화면으로 이동 --%>
function doGroupMenuSetting() {
	var total = 0;					
	if(document.myForm.menuId){
		total = 1;
	}
	if(document.myForm.menuId.length){
		total = document.myForm.menuId.length;
	}
	if (total>0) {
		var str = "";
        for (var i=0;i<total;i++) {
            if(document.myForm.menuId[i].checked){
            	document.myForm.menuIdStr[i].value = document.myForm.menuId[i].value+";"+document.myForm.upMenuId[i].value+";";
            }
        }
    }
	$("#myForm input[name=actionID]").val("<%=GroupMenuConstKey.GROUPMENU_ACTION_MENU_SETTING%>");
	$("#groupLevel").val(groupLvl);
	$('#myForm').submit();	
}

 //변경반영
function doReload() {
	if (confirm("<spring:message code='MSG.ALERT.PROCESS'/>")) {
		$("#myForm input[name=actionID]").val("<%=GroupMenuConstKey.GROUPMENU_ACTION_RELOAD_OK%>");
		$("#groupLevel").val(groupLvl);
		$('#myForm').submit();
	}	
}

//트리메뉴 체크
function doCheckRelation(index) {
    //여러개의 체크박스가 있는 경우만 위아래 노드 체크 처리를 수행한다
    if (document.myForm.menuId.length) {
        var total = document.myForm.menuId.length;
        var level = document.myForm.level[index].value;
        var upMenuId = document.myForm.upMenuId[index].value;
        var menuId = document.myForm.menuId[index].value;
        var bCheck =  document.myForm.menuId[index].checked;
        //하위노드 체크처리
        doCheckDownNode(bCheck, menuId, level, index, total);
        //상위노드 체크처리
        doCheckUpNode(bCheck, upMenuId, index, total);
     }
}

//자식노드가하나라도 존재하는지 체크한다
function checkEndNodeExist(upMenuId, j, total) {
    var bCheck = false;
    for(var k = j ; k < total ; k++) {
        if (upMenuId == document.myForm.upMenuId[k].value) {
            if (document.myForm.menuId[k].checked) {
                bCheck = true;
                break;
            }
        }
    }

    return bCheck;
}


//자식항목들을 모두 체크상태나 비 체크 상태로 바꾼다
function doCheckUpNode(bCheck, upMenuId, idx, total) {
    //체크된 상태의 경우 위의 부모항목도 체크한다
    if (bCheck) {
        for (var j = idx-1 ; j >= 0 ; j--) {
            if (upMenuId == document.myForm.menuId[j].value) {
            	document.myForm.menuId[j].checked = bCheck;
               doCheckUpNode(bCheck, document.myForm.upMenuId[j].value, j, total);
               break;
            }
        }
    //체크를 해제한 경우는 다른 하위항목이 없을경우 체크를 해제한다
    } else {
        for (var j = idx-1 ; j >= 0 ; j--) {
            if (upMenuId == document.myForm.menuId[j].value) {
               if (!checkEndNodeExist(upMenuId, j, total)) {
            	   document.myForm.menuId[j].checked = bCheck;
                   doCheckUpNode(bCheck, document.myForm.upMenuId[j].value, j, total);
                   break;
               } else {
                   break;
               }
            }
        }
    }
}

//자식항목들을 모두 체크상태나 비 체크 상태로 바꾼다
function doCheckDownNode(bCheck, menuId, level, idx, total) {
     //마지막항목이 아닌경우는 계속 수행
    if (idx < (total-1)) {
        for (var j = (idx+1) ; j < total ; j++) {
            if (level >= document.myForm.level[j].value) {
                break;
            } else {
                if (menuId == document.myForm.upMenuId[j].value) {
                	document.myForm.menuId[j].checked = bCheck;
                    doCheckDownNode(bCheck,document.myForm.menuId[j].value, document.myForm.level[j].value, j, total);
                } //else {
                  //  break;
                //}
            }
        }
    }
}
</script>


