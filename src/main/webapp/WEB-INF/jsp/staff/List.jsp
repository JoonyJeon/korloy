<%----------------------------------------------------------------------------------------
 - 파일이름	: staff/List.jsp
 - 설      명	: 스텝 관리 화면
 - 추가내용 	:
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2019.03.28    1.0       KSH      신규작성
------------------------------------------------------------------------------------------%>

<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import = "java.util.List"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstUrl" %>
<%@page import = "com.eaction.framework.business.logic.staff.constant.StaffConstKey"%>
<%@page import = "com.eaction.framework.business.logic.staff.model.StaffInfo"%>
<%@page import = "com.eaction.framework.business.logic.staff.model.StaffSearchInfo"%>
<%@page import = "com.eaction.framework.common.model.User"%>
<%@page import = "com.eaction.framework.business.common.code.CodeTableMng"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<% StaffSearchInfo searchinfo = (StaffSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA); %>

<div id="main" role="main">
	<!-- 타이틀 네비게이터 -->
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
			
			<!-- 상단화면 기능 명칭 -->
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark">
					<!-- PAGE HEADER -->
					<i class="fa-fw fa fa-user"></i> 
					<%=MenuMng.getMenuTitle(menu_url, userInfo) %>
				</h1>
			</div>
			
			<!-- 상단 버튼들 -->
			<div class="col-lg-4" style="float: right;height: 66px;padding-top: 10px;text-align: right;">
				<eaction:button name="addModal" id="addModal" cssId="btn btn-primary btn-sm" menuUrl="<%=menu_url %>" >
					<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.ADD_BTN" />
				</eaction:button>
				<eaction:button name="editModal" id="editModal" cssId="btn btn-primary btn-sm" menuUrl="<%=menu_url %>" >
					<spring:message code="TEXT.STAFF.MGMT.STAFF_MANAGEMENT.MOD_BTN" />
				</eaction:button>
			</div>
		</div>
		<!-- 상단화면명 END -->
		<form name="myForm" id="myForm" method="POST" action="<%=StaffConstUrl.STAFF_MEMBER_URL%>">
			<!-- 공통 객체 정의 -->
			<input type="hidden" name="menuUrl" id="menuUrl" value="<%=menu_url%>"/>
			<input type="hidden" name="<%=ConstKey.ACTION_ID %>">
			<input type="hidden" name="srchSidx" id="srchSidx"/>
			<input type="hidden" name="srchSord" id="srchSord"/>
			<input type="hidden" name="rowNum" id="rowNum"/>
			<input type="hidden" name="page" id="page"/>
			<input type="hidden" name="emp_id" id="emp_id"/>
			<!-- 업무별 사용 객체 정의 -->
			
			<!-- 필터검색 select
			<eaction:select name="searchAuth" id="searchAuth" group="AUTH_TYPE" init="<%=ConstKey.KEY_YES %>" initCode="" initName="ALL" style="display:none;" />
			<eaction:select name="searchStatus" id="searchStatus" group="STATUS" init="<%=ConstKey.KEY_YES %>" initCode="" initName="ALL" style="display:none;" />
			 -->
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
								<table id="gridStaffList"></table>
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
<eaction:script common="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>" />

<script type="text/javascript">

	jQuery(document).ready(function(){
		//공통 화면 조정 처리
		pageSetUp();
		
		//화면이벤트를 모두 정의함
		bindClickEvent();
		
		//jQuery로 ajax 통신 처리 시 데이터를 배열로 넘겨줄 때 설정
		jQuery.ajaxSettings.traditional = true;
		
		jQuery("#gridStaffList").jqGrid({
			url: "<%=StaffConstUrl.STAFF_MEMBER_URL%>",
			postData: {
				actionID: "<%=StaffConstKey.ACTION_STAFF_LIST %>"
			},
  			page:"<%=searchinfo.getJqPage()%>",
			datatype: "json",
			jsonReader: {
	            root: "root",
	            page: "page",
	            records: "records",
	            repeatitems: false
	        },
			mtype: 'POST',
			autoencode:true,
			height: 520,
		   	colNames:[
		   		//"이름"
		   		"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMPNM'/>",
		   		//이메일 아이디
		   		"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMP_ID'/>",
		   		
		   		//등급
		   		//"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.USER_GROUP_ID'/>",		   		
		   		//최종수정자
		   		"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.EMAIL'/>",
		   		//최종수정일
		   		"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.SMSPHONE'/>",
		   	//사용상태
		   		"<spring:message code='TEXT.STAFF.MGMT.STAFF_MANAGEMENT.STATUS'/>"
			],
		   	colModel:[
					{name:"empnm"			,index:"empnm"			,align:"left"	,width:140},
					{name:"emp_id"			,index:"emp_id"			,align:"left"	,width:140},
		   	        //{name:"user_group_id"	,index:"user_group_id"	,align:"center" ,width:80,
	                //	stype: "select"			,searchoptions: {value: getJqgridSelectData($("#searchAuth > option"))},
					//    edittype: "select"		,editoptions: {value: getJqgridSelectData($("#searchAuth > option"))},	formatter:'select'},		   	        
		   	        {name:"email"			,index:"email"			,align:"center"	,width:140 },
		   	        {name:"smsphone"			,index:"smsphone"			,align:"center"	,width:110 },
		   	        {name:"status"			,index:"status"			,align:"center" ,width:60,
	                	stype: "select"			,searchoptions: {value: getJqgridSelectData($("#searchStatus > option"))},
					    edittype: "select"		,editoptions:{value: getJqgridSelectData($("#searchStatus> option"))},	formatter:'select'}
		   	],
		   	sortname: "empnm",
		    rownumWidth:40,
		   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
		   	rowList : [15, 30, 50],
		   	sortorder: "asc",
		    shrinkToFit: true,
		    toolbarfilter : true,
			viewrecords: true,
		   	pager: "#gridToolbar",
		   	autowidth : true,
		   	onSelectRow: function(rowid){
		   		var ret = jQuery("#gridStaffList").getRowData(rowid);
		   		$("#emp_id").val(ret.emp_id);
		   	},
		   	ondblClickRow: function (rowid, iRow, iCol) {
		   		var ret = jQuery("#gridStaffList").getRowData(rowid);
		   		$("#emp_id").val(ret.emp_id);
		   		
		   		showEditModal();
		   	}
		   	
		})
		jQuery("#gridStaffList").jqGrid("navGrid","#gridToolbar",{
			edit:false,add:false,del:false,view:false,search:false,refresh:false
				},// edit,add,delete,view,search,options
				{},{},
				{},{},{
					width:500
				}
			);
		
		//필터 Search기능(모두text박스)
		jQuery("#gridStaffList").jqGrid("filterToolbar",{stringResult: true,searchOnEnter : true, defaultSearch : "cn", ignoreCase: true});	
				
		//JQGrid 포맷을 변경 함
		resetCustomJQgrid(); 

		$(window).on('resize.jqGrid', function() {
			$("#gridStaffList").jqGrid('setGridWidth', $(".jarviswidget").width());
		})
	});
	
	function bindClickEvent() {
		
		//등록 모달 호출
		$('#addModal').on('click', function(ev) {
			ev.preventDefault();
			showAddModal();
		});

		//등록 모달 호출
		$('#editModal').on('click', function(ev) {
			ev.preventDefault();
			showEditModal();
		});
		
		//모달 내부 지우기
		$("#staffModal").on('hidden.bs.modal', function() {
			$(".modal-body").empty();
		});
		
	}
			
		//추가모달창 띄우기
	function showAddModal() {
		var target = '<%=StaffConstUrl.STAFF_MEMBER_POP_ADD_URL%>';
		var param = {
				menuUrl : "<%=menu_url%>"
		}
		
		$("#staffModal").load(target, param, function() {
			$("#staffModal").modal({backdrop: 'static'});
		})
		
	}

	//편집모달창 띄우기
	function showEditModal() {
		if ($("#emp_id").val() == ""){
			alert("<spring:message code='MSG.NONE.SELECT'/>")
			$("#staffModal").modal("hide");
		} else {
			var target = '<%=StaffConstUrl.STAFF_MEMBER_POP_EDIT_URL%>';
			var param = {
					menuUrl : "<%=menu_url%>",
					emp_id : $("#emp_id").val()
			}
			//URL불러오고 모달창 띄우기 성공시
			$("#staffModal").load(target, param, function() {
				$("#staffModal").modal({backdrop: 'static'});
			});
		}
		
	}

	//재조회처리(JQGrid 리로드 처리)
	function goStaffList() {
		
		var crtDate = $("#gridStaffList").jqGrid('clearGridData');
		jQuery("#gridStaffList").setGridParam({url: "<%=StaffConstUrl.STAFF_MEMBER_URL%>?v=" + (new Date().getTime()),
 			postData: {
  				actionID: "<%=StaffConstKey.ACTION_STAFF_LIST %>"
			}
		}).trigger("reloadGrid");
		
	}
	
</script>
<div class="modal fade" id="staffModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel" aria-hidden="true"></div>