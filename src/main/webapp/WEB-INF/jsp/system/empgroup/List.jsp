<%----------------------------------------------------------------------------------------
 - 파일이름	: system/empgroup/List.jsp
 - 설      명	: 사용자그룹관리 목록 page
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
<%@page import = "java.util.List"%>
<%@page import="com.eaction.framework.common.util.PagingUtil" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.group.constant.EmpGroupConstKey"%>
<%@page import = "com.eaction.framework.business.system.group.model.EmpGroupSearchInfo"%>
<%@page import = "com.eaction.framework.common.util.AuthorityUtil"%>
<%@page import = "com.eaction.framework.business.common.code.CodeTableMng" %>
<%@page import = "com.eaction.framework.common.model.UserGroupInfo" %>
<%@page import="net.sf.json.JSONArray"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
	EmpGroupSearchInfo searchInfo  = (EmpGroupSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
	String currMenuId = MenuMng.getMenuId(menu_url);
	String curr_menu_name = StringUtil.nvl(MenuMng.getMenuName(currMenuId, userInfo));
%>

<form name="myForm" method="POST" action="<%=ConstUrl.SYSTEM_EMPGROUP_URL%>">
<input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.ACTION_LIST%>">
<input type="hidden" name="groupLevel" id="groupLevel" />
<input type="hidden" name="chkGroupId" id="chkGroupId" value="">
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
				
				<!-- 상단 버튼들 -->
				<div class="col-lg-4" style="float: right;height: 66px;padding-top: 10px;text-align: right;">					
					<a  href="#" data-target="#empGroupModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.REG"/>
					</a>
					<a href="#" data-target="#empGroupModModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.UPDATE"/>
					</a>
					<button type="button" class="btn btn-primary btn-sm" id="groupDel">
						<spring:message code="TEXT.COMM.BTN.DELETE"/>
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
									<!-- 위젯 타이틀 변경 브라우져 닫으면 없어짐. -->
										<div class="jarviswidget-editbox">																					
										</div>
										<div class="widget-body no-padding">																								
											<table id="gridGroupList"></table>
											<div id="gridGroupToolbar"></div>
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
<eaction:script common="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>"/>

<script type="text/javascript">

jQuery(document).ready(function(){
	pageSetUp();	
	bindClickEvent();
	
	jQuery.ajaxSettings.traditional = true;
	jQuery("#gridGroupList").jqGrid({
		url: "<%=ConstUrl.SYSTEM_EMPGROUP_URL %>",
		postData: {
			actionID: "<%=ConstKey.ACTION_LIST%>"
		},
		page:"<%=searchInfo.getJqPage()%>",
		datatype: "json",
		jsonReader: {
            root: "root",
            page: "page",
            records: "records",
            repeatitems: false
        },
		mtype: 'POST',
		autoencode:true,
		height: 460,
	   	colNames:[	
						//그룹ID
						"",
						//메뉴그룹종별
						"<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_LEVEL'/>",	
						//메뉴그룹등록이름
						"<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_NAME'/>",		
						//사용자그룹설명
						"<spring:message code='TEXT.EMPGROUP.MGMT.TITLE.EMP_GROUP_EXPLAIN'/>"
	   	],
	   	colModel:[				
					{name:"code"		,index:"code"		,hidden: true},
					{name:"levelcodnm"	,index:"groupLevel"	,width:140, align:"center", sortable:false},
					{name:"name"		,index:"name"	    ,width:140, align:"center", sortable:false},	
					{name:"remark"		,index:"remark"	, sortable:false}
	   	],
	   	sortname: "groupLevel",
	    sortorder: "asc",
	   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
	   	rowList : [15, 30, 50],	   	
	    shrinkToFit: true,
	    toolbarfilter : true,
		viewrecords: true,		
	   	pager: "#gridGroupToolbar",
	   	autowidth : true,
	   	onSelectRow: function(){  //row가 선택되었을 경우만 삭제할수 있다.
	   		var id = jQuery("#gridGroupList").getGridParam("selrow");
	   		if(id){
	   			var ret = jQuery("#gridGroupList").getRowData(id);
		   		$("#groupLevel").val(ret.groupLevel);
		   		$("#chkGroupId").val(ret.code);
	   		}
	   	},
	   	ondblClickRow: function (rowid, iRow, iCol) {
	   		var ret = jQuery("#gridGroupList").getRowData(rowid);
		   	$("#groupLevel").val(ret.groupLevel);
		   	$("#chkGroupId").val(ret.code);
		   	//수정버튼클릭처리(팝업표시)
		   	$('a[data-target=#empGroupModModal]').click();
	   	}	   
	});
	jQuery("#gridGroupList").jqGrid("navGrid","#gridGroupToolbar",{
		edit:false,add:false,del:false,view:false,search:false,refresh:false
			},// edit,add,delete,view,search,options
			{},{},
			{},{},{
				width:500
			}
		);
	
	// remove classes
	$(".ui-jqgrid").removeClass("ui-widget ui-widget-content");
	$(".ui-jqgrid-view").children().removeClass("ui-widget-header ui-state-default");
	$(".ui-jqgrid-labels, .ui-search-toolbar").children().removeClass("ui-state-default ui-th-column ui-th-ltr");
	$(".ui-jqgrid-pager").removeClass("ui-state-default");
	$(".ui-jqgrid").removeClass("ui-widget-content");

	// add classes
	$(".ui-jqgrid-htable").addClass("table table-bordered table-hover");
	$(".ui-jqgrid-btable").addClass("table table-bordered table-striped");

	$(".ui-pg-div").removeClass().addClass("btn btn-sm btn-primary");
	$(".ui-icon.ui-icon-plus").removeClass().addClass("fa fa-plus");
	$(".ui-icon.ui-icon-pencil").removeClass().addClass("fa fa-pencil");
	$(".ui-icon.ui-icon-trash").removeClass().addClass("fa fa-trash-o");
	$(".ui-icon.ui-icon-search").removeClass().addClass("fa fa-search");
	$(".ui-icon.ui-icon-refresh").removeClass().addClass("fa fa-refresh");
	$(".ui-icon.ui-icon-disk").removeClass().addClass("fa fa-save").parent(".btn-primary").removeClass("btn-primary").addClass("btn-success");
	$(".ui-icon.ui-icon-cancel").removeClass().addClass("fa fa-times").parent(".btn-primary").removeClass("btn-primary").addClass("btn-danger");

	$(".ui-icon.ui-icon-seek-prev").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-prev").removeClass().addClass("fa fa-backward");

	$(".ui-icon.ui-icon-seek-first").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-first").removeClass().addClass("fa fa-fast-backward");

	$(".ui-icon.ui-icon-seek-next").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-next").removeClass().addClass("fa fa-forward");

	$(".ui-icon.ui-icon-seek-end").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-end").removeClass().addClass("fa fa-fast-forward");
	

	$(window).on('resize.jqGrid', function() {
		$("#gridGroupList").jqGrid('setGridWidth', $(".jarviswidget").width());
	})	
});

//사용자 정보 취득
function goEmpGroupList(){		
	$("#gridGroupList").jqGrid('clearGridData');
	jQuery("#gridGroupList").setGridParam({url: "<%=ConstUrl.SYSTEM_EMPGROUP_URL%>?v=" + (new Date().getTime()),
		postData: {
			actionID: "<%=ConstKey.ACTION_LIST%>"
	}}).trigger("reloadGrid");
}

//클릭이벤트
function bindClickEvent(){
	//삭제
	$("#groupDel").bind("click", function(){
		if ($("#chkGroupId").val() == "") {
        	alert("<spring:message code="MSG.NONE.SELECT"/>");
		}else{
			if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
				delEmpGroup();	
			}			
		}  		
     });
	
	//등록 모달 호출
	$('a[data-target=#empGroupModal]').on('click', function (ev) {
        ev.preventDefault();
  
            $(this).attr('href', '<%=ConstUrl.SYSTEM_EMPGROUP_POP_URL%>');
            var target = $(this).attr("href");

            // load the url and show modal on success
            $("#empGroupModal").load(target, function () {
                $("#empGroupModal").modal({backdrop: 'static'});
            });
        
    });
	
	//수정 모달 호출
	 $('a[data-target=#empGroupModModal]').on('click', function (ev) {
	        ev.preventDefault();
	        if ($("#chkGroupId").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
	        	$("#empGroupModModal").modal("hide");
	        }else {
	        	var param = {
	        			groupId : $("#chkGroupId").val()		
	        	}
	            $(this).attr('href', '<%=ConstUrl.SYSTEM_EMPGROUP_POP_MOD_URL%>');
	            var target = $(this).attr("href");

	            // load the url and show modal on success
	            $("#empGroupModModal").load(target,param, function () {
	                $("#empGroupModModal").modal({backdrop: 'static'});
	            });
	        }
	    });
}
//삭제
function delEmpGroup(){
	var param = {
				actionID	:"<%=ConstKey.ACTION_DELETE_OK%>",			
				groupId 	: $("#chkGroupId").val()
			};
	
     $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SYSTEM_EMPGROUP_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        success: function (e) {
        	if (e.resCode == "0") {
        		//alert(e.resMsg);
        		$("#chkGroupId").val("");
        		goEmpGroupList();
        	}else{
        		alert(e.resMsg);
			}
        	
        }, error: function () {
        	alert('<spring:message code="MSG.FAIL"/>');
        }
    }); 	
}

</script>

<div class="modal fade" id="empGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
</div>
<div class="modal fade" id="empGroupModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
</div>