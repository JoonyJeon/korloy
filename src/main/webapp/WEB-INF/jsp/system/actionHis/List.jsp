<%----------------------------------------------------------------------------------------
 - 파일이름	: org/mgmt/org.jsp
 - 설      명	: 조직 관리 화면
 - 추가내용 	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2020.07.15    1.0       정세연      신규작성
------------------------------------------------------------------------------------------%>

<%@page import="com.eaction.framework.business.system.actionHis.model.ActionHisSearchInfo"%>
<%@page import="com.eaction.framework.business.system.actionHis.constant.ActionHisConstKey"%>
<%@page import="com.eaction.framework.business.system.actionHis.constant.ActionHisConstUrl"%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.actionHis.model.ActionHisInfo"%>
<%@page import = "com.eaction.framework.business.system.actionHis.model.ActionHisSearchInfo"%>
<%@page import = "com.eaction.framework.common.model.User"%>
<%@page import = "com.eaction.framework.business.common.code.CodeTableMng"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
	ActionHisSearchInfo searchInfo = (ActionHisSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
	List<CodeInfo> resultInfo = (List<CodeInfo>)request.getAttribute(ConstKey.RESULT_DATA);
%>

<div id="main" role="main">
	<!-- 타이틀 네비게이터 -->
	<div id="ribbon">
		<!-- breadcrumb -->
		<ol class="breadcrumb">
			<eaction:navi  menuUrl="<%=menu_url%>"></eaction:navi>
		</ol>
		<!-- end breadcrumb -->
	</div>
	<div id="content">
		<!-- 상단화면명 START -->
		<div class="row">
			
			<!-- 상단화면 기능 명칭 -->
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark">
					<!-- PAGE HEADER -->
					<i class="fa-fw fa fa-user"></i> 
					<%=MenuMng.getMenuTitle(menu_url, userInfo)%>
				</h1>
			</div>
		</div>
		<div class="row">
			<!-- 검색박스 -->
			<!-- 상단 버튼들 -->
			<div class="col-lg-12" style="padding-top: 10px;padding-bottom:10px;text-align: right;">
				<!-- 조회 -->
				<label class="input" style="position:relative"> <i class="icon-append fa fa-calendar icon-attach"></i>
					<input id="search_start_dt" type="search" class="form-control" placeholder="시작일" class="datepicker"  >
				</label> ~
				<label class="input" style="position:relative"> <i class="icon-append fa fa-calendar icon-attach"></i>
					<input id="search_end_dt" type="search" class="form-control" placeholder="종료일" class="datepicker"  >
				</label>
				<button type="button" class="btn btn-danger btn-sm" id="btnSearch" style="margin-bottom:2px">
				 	<spring:message code="TEXT.COMM.BTN.SEARCH"/>
				</button>
			</div>
		</div>
		<!-- 상단화면명 END -->
		<form name="myForm" id="myForm" method="POST" action="<%=ActionHisConstUrl.JSP_SYSTEM_ACTIONHIS_URL%>">
			<!-- 공통 객체 정의 -->
			<input type="hidden" name="menuUrl" id="menuUrl" value="<%=menu_url%>"/>
			<input type="hidden" name="<%=ConstKey.ACTION_ID %>">
			<input type="hidden" name="srchSidx" id="srchSidx"/>
			<input type="hidden" name="srchSord" id="srchSord"/>
			<input type="hidden" name="rowNum" id="rowNum"/>
			<input type="hidden" name="page" id="page"/>
			<input type="hidden" name="emp_id" id="emp_id"/>
			<!-- 업무별 사용 객체 정의 -->
			<!-- 메시지 유형 -->
			<eaction:select name="search_action_code" id="search_action_code" group="MGMT_ACTION_TYPE"
			init="<%=ConstKey.KEY_YES %>" initCode="" initName="" style="display:none;" />
			<eaction:select name="search_menu_nm_kor" id="search_menu_nm_kor"  init="<%=ConstKey.KEY_YES %>" list="<%=resultInfo%>"  style="display:none;"/>
		</form>
		
		<section id="widget-grid" class="">
			<!-- 위젯 아이디 숫자를 맞춰야 순서대로 나옴. -->
			<div class="row">
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				    <div class="jarviswidget" id="wid-id-0" data-widget-custombutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="true" style="margin:0;">
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
								<table id="gridList"></table>
								<div id="gridToolbar"></div>
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
<eaction:script common="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>" bootstraptree="<%=ConstKey.KEY_YES %>" picker="<%=ConstKey.KEY_YES %>" />

<script type="text/javascript">
jQuery(document).ready(function(){
	// 공통 화면 조정 처리
	pageSetUp();
	//화면이벤트를 모두 정의함
	bindClickEvent();
	
	jQuery.ajaxSettings.traditional = true;
	jQuery("#gridList").jqGrid({
		url: "<%=ConstUrl.SYSTEM_ACTIONHIS_URL%>",
		postData: {
			 actionID: "<%=ActionHisConstKey.ACTION_SYSTEM_ACTION_HIS_LIST%>"
			,search_start_dt : $("#search_start_dt").val()
			,search_end_dt : $("#search_end_dt").val()
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
			  "아이디",
			  "이름",
			  "메뉴명",
			  "활동구분",
			  "활동일시",
			  "",
			  ""
		],
		colModel:[
			{name:"action_user_id"		,index:"action_user_id"		,align:"left"	,width:100},
			{name:"empnm"				,index:"empnm"				,align:"left"	,width:100},
			{name:"menu_id"			,index:"menu_id"		,align:"left"	,width:100,
				stype: "select"			,searchoptions: {value: getJqgridSelectData($("#search_menu_nm_kor > option"))},
				edittype: "select"		,editoptions:{value: getJqgridSelectData($("#search_menu_nm_kor > option"))}, formatter:'select'},
			{name:"action_code"	,index:"action_code"		,align:"center"	,width:50,
				stype: "select"			,searchoptions: {value: getJqgridSelectData($("#search_action_code > option"))},
				edittype: "select"		,editoptions:{value: getJqgridSelectData($("#search_action_code > option"))}, formatter:'select'},
			{name:"action_date"			,index:"action_date"		,align:"center"	,width:100, search:false},
			{name:"action_ctnt"			,index:"action_ctnt"	,align:"center"	, hidden:true},
			{name:"action_no"			,index:"action_no"	,align:"center"	, hidden:true}
		],
		sortname: "action_date",
		rowNum: <%=JQGRID_NORMAL_ROWNUM%>,
		rowList : [10, 20, 30],
		sortorder: "desc",
		shrinkToFit: true,
		toolbarfilter : true,
		viewrecords: true,
		pager: "#gridToolbar",
		autowidth : true,
		ondblClickRow: function (rowid, iRow, iCol) {
			showEditModal();
		},
		beforeSelectRow: function (rowid, e) {			
	        $("#gridList").jqGrid('resetSelection'); //멀티셀렉트 그리드에서 체크박스 전체해제
	        return true;
		}
	});
	jQuery("#gridList").jqGrid("navGrid","#gridToolbar",{
		edit:false,add:false,del:false,view:false,search:false,refresh:false
		},// edit,add,delete,view,search,options
		{},{},
		{},{},{
			width:500
		}
	);
	// 필터 Search 기능
	jQuery("#gridList").jqGrid("filterToolbar", {stringResult: true, searchOnEnter: true, defaultSearch: "cn", ignoreCase: true});
	
	//JQGrid 포맷을 변경 함
	resetCustomJQgrid(); 	

	$(window).on('resize.jqGrid', function() {
		$("#gridList").jqGrid('setGridWidth', $(".jarviswidget").width());
	});
	
});

// 검색버튼을 눌러서 리스트를 재조회 처리 함(JQGRrid 리로드 처리)
function goList(){
	$("#gridList").jqGrid('clearGridData');
	jQuery("#gridList").setGridParam({url: "<%=ConstUrl.SYSTEM_ACTIONHIS_URL%>?v=" + (new Date().getTime()),
		postData: {
			actionID: "<%=ActionHisConstKey.ACTION_SYSTEM_ACTION_HIS_LIST%>"
			,search_org_tree : $("#searchOrgTreeBox").val()
			,search_start_dt : $("#search_start_dt").val()
			,search_end_dt : $("#search_end_dt").val()
	}}).trigger("reloadGrid");
}



function bindClickEvent(){
	datePickerInit();

	//검색 버튼 클릭 이벤트 처리
	$("#btnSearch").bind("click", function() {
		goList();
	 });	


	$("#btnEdit").on('hidden.bs.modal', function() {
		$(".modal-body").empty();
	});
	
	// 수정 모달 호출
	$('#btnEdit').on('click', function(ev) {
		ev.preventDefault();
		showEditModal();
	});

	// 모달 내부 지우기
	$(".modal").on('hidden.bs.modal', function() {
		$(".modal-body").empty();
	});
}

//편집모달창 띄우기
function showEditModal() {
	var target = '<%=DOMAIN_NAME%><%=ConstUrl.SYSTEM_ACTIONHIS_POP_DETAIL_URL%>'; 
	var selrow = $("#gridList").jqGrid('getGridParam', 'selrow');
	if(selrow == null) {
		alert("<spring:message code='MSG.NONE.SELECT'/>");
		$("#myModal").modal("hide");
	} else {
		var param = {
				search_action_no : $('#gridList').getCell(selrow, 'action_no'),
		}
		//URL불러오고 모달창 띄우기 성공시
		$("#myModal").load(target, param, function() {
			$("#myModal").modal();
		});
	}
}
function datePickerInit(){
	 $('#search_start_dt').datetimepicker({
		 viewMode : 'days',
		 format: 'YYYY-MM-DD',
		 locale : 'ko',
	     showClear:true,
	     showClose:true, 
	     defaultDate: new Date()
	 });	
	 $('#search_end_dt').datetimepicker({
		 viewMode : 'days',
		 format: 'YYYY-MM-DD',
		 locale : 'ko',
	     showClear:true,
	     showClose:true,
	     defaultDate: new Date()
	 });
}	

</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel" aria-hidden="true"></div>