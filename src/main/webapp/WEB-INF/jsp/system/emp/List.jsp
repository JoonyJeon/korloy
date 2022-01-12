<%----------------------------------------------------------------------------------------
 - 파일이름	: admin/emp/List.jsp
 - 설      명	: 사용자관리 화면
 - 작 성 자	: WYA
 - 작성날짜	: 2017.12.13
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  -----------  ---------  --------------------------
 - 2018.11.13    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstUrl"%>
<%@page import = "com.eaction.framework.business.common.constant.ConstKey"%>
<%@page import = "java.util.List"%>
<%@page import="com.eaction.framework.common.util.PagingUtil" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.system.emp.constant.EmpConstKey"%>
<%@page import = "com.eaction.framework.common.model.User"%>
<%@page import = "com.eaction.framework.business.system.emp.model.EmpSearchInfo"%>
<%@page import = "com.eaction.framework.business.common.code.CodeTableMng"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>


<%
	//검색정보
	EmpSearchInfo searchInfo = (EmpSearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
%>

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
						<%=MenuMng.getMenuTitle(menu_url, userInfo) %>
					</h1>
				</div>
				
				<!-- 상단 버튼들 -->
				<div class="col-lg-4" style="float: right;height: 66px;padding-top: 10px;text-align: right;">
					
					
					<a  href="#" data-target="#empModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.REG"/>
					</a>
					<a href="#" data-target="#empModModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.UPDATE"/>
					</a>					
					<button type="button" class="btn btn-primary btn-sm" id="empDel">
						<spring:message code="TEXT.COMM.BTN.DELETE"/>
					</button>
				</div>
								
			</div>
			<!-- 상단화면명 END -->
			
			<form name="myForm" method="POST" action="<%=ConstUrl.SYSTEM_EMP_URL%>">
			<input type="hidden" name="menuUrl" value="<%=menu_url%>"/>
			<input type="hidden" name="srchSidx" />
			<input type="hidden" name="srchSord" />
			<input type="hidden" name="emp_id" id="emp_id" />
			<input type="hidden" name="<%=ConstKey.ACTION_ID %>">
			<input type="hidden" name="page" id="page"/>
			<input type="hidden" name="rowNum" id="rowNum"/>
			<input type="hidden" name="search_flag" id="search_flag" value="<%=searchInfo.getSearch_flag()%>"/>
			<eaction:select name="searchStatus" id="searchStatus" group="STATUS" style="display:none;"/>
				

			
			<!-- 검색박스 START -->
			<div class="row">				
				<div class="col-sm-12 col-md-12 col-lg-12">												
					<div class="well">
						<div class="smart-form">
							<section class="col">
								<label class="input">
									<input type="text" name="searchVal" id="searchVal" style="width:140px">
								</label>
							</section>
															
							<button type="button" class="btn btn-primary btn-sm" id="srch">
								<spring:message code="TEXT.COMM.BTN.SEARCH"/>
							</button>
							</div>
						</div>
				</div>						
			</div>
			<!-- 검색박스 END -->
			</form>			
						
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
											<table id="gridEmpList"></table>
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
<eaction:script common="<%=ConstKey.KEY_YES %>" picker="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>"/>

<script type="text/javascript">
var dbSrchGb = "";
var dbSrchSt = "";
jQuery(document).ready(function(){

    $(document).on("contextmenu dragstart selectstart",function(e){
        return false;
    });
    
	pageSetUp();
	
	bindClickEvent();
	
	jQuery.ajaxSettings.traditional = true;
	jQuery("#gridEmpList").jqGrid({
		url: "<%=ConstUrl.SYSTEM_EMP_URL %>",
		postData: {
			actionID: "<%=EmpConstKey.ACTION_EMP_LIST%>"			
			,searchKey:dbSrchGb
			,searchVal:$("#searchVal").val()
			,searchStatus:dbSrchSt
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
			  //"유저아이디",
			  "<spring:message code="TEXT.EMP.MGMT.GRID.USER_ID"/>",			
	  	      //"유저이름",
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.USER_NAME"/>",
   			  //"사용자그룹",
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.EMP_GROUP"/>",
   			  //"권한레벨코드",
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.AUTH_LEVEL_CODE"/>",   			  
   			  //"이메일",
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.EMAIL"/>",
   			  //"전화번호"
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.TEL"/>",
   			  //"사용상태"
   			  "<spring:message code="TEXT.EMP.MGMT.GRID.STATUS"/>"
	   	],
	   	colModel:[
				{name:"emp_id"				,index:"emp_id"			,align:"center"	,width:205},
				{name:"empNm"				,index:"empNm"			,align:"center"	,width:205},
	   	        {name:"user_group_nm"		,index:"user_group_nm"	,align:"center",	width:205},
	   	        {name:"levelCod"				,index:"levelCod"			,align:"center",	hidden:true},	   	        
	   	        {name:"email"					,index:"email"				,align:"center",	width:213},
	   	        {name:"smsPhone"			,index:"smsPhone"		,align:"center",	width:205},
	   	        {name:"status"		        ,index:"status"		    ,align:"center"	,width:90,	sortable:false, editable: false, formatter:"select", edittype: "select", editoptions:{value: getJqgridSelectData($("#searchStatus > option"))}}
	   	],
	   	sortname: "empNm",
	   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
	   	rowList : [15, 30, 50],
	   	sortorder: "asc",
	    shrinkToFit: true,
	    toolbarfilter : true,
		viewrecords: true,
	   	pager: "#gridToolbar",
	   	autowidth : true,
	   	onSelectRow: function(id){
	   		var id = jQuery("#gridEmpList").getGridParam("selrow");		
	   		if(id){
	   			var ret = jQuery("#gridEmpList").getRowData(id);
				var emp_id = ret.emp_id;
				var groupLevel = ret.levelCod;
				deatilInfo(emp_id, groupLevel);
	   		}
	   	},
	   	ondblClickRow: function (rowid, iRow, iCol) { 
	   		var ret = jQuery("#gridEmpList").getRowData(rowid);
	   		var emp_id = ret.emp_id;
			var groupLevel = ret.levelCod;
			deatilInfo(emp_id, groupLevel);
			//수정버튼클릭처리(팝업표시)
			$('a[data-target=#empModModal]').click();	   		
	   	}
	});
	jQuery("#gridEmpList").jqGrid("navGrid","#gridToolbar",{
		edit:false,add:false,del:false,view:false,search:false,refresh:false
			},// edit,add,delete,view,search,options
			{},{},
			{},{},{
				width:500
			}
		);
	
	//JQGrid 포맷을 변경 함
	resetCustomJQgrid(); 	

	$(window).on('resize.jqGrid', function() {
		$("#gridEmpList").jqGrid('setGridWidth', $(".jarviswidget").width());
	})	
});

//버튼 드랍다운 체인지 이벤트 무조건 있어야 함.
function changeBtnDdVal(obj, btn_target){
	if(btn_target === "btn_select_srch_st"){
		dbSrchSt = obj.id;
	}else if(btn_target === "btn_select_srch_gb"){
		dbSrchGb = obj.id;
	}
	var btn_target = $("#"+btn_target);
	btn_target.html(obj.text + " <span class=\"caret\"></span>");
}

function bindClickEvent(){
	//클릭이벤트
  	$("#srch").bind("click", function(){
  		goEmpList();
     });
	$("#searchVal").keydown(function (key) {
        if(key.keyCode == 13){
        	goEmpList();
        }             
    });
	//삭제
	$("#empDel").bind("click", function(){
		if ($("#emp_id").val() == "") {
        	alert("<spring:message code="MSG.NONE.SELECT"/>");
		}else{
			if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
				delEmp();
			}
		}
     });
	
	//등록 모달 호출
	$('a[data-target=#empModal]').on('click', function (ev) {
        ev.preventDefault();
  
            $(this).attr('href', '<%=ConstUrl.SYSTEM_EMP_POP_URL%>');
            var target = $(this).attr("href");

            // load the url and show modal on success
            $("#empModal").load(target, function () {
                $("#empModal").modal({backdrop: 'static'});
            });
        
    });
	
	//수정 모달 호출
	 $('a[data-target=#empModModal]').on('click', function (ev) {
	        ev.preventDefault();
	        if ($("#emp_id").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
	        	$("#empModModal").modal("hide");
	        }else {
	        	var param = {
	        			emp_id : $("#emp_id").val()
	        	}
	            $(this).attr('href', '<%=ConstUrl.SYSTEM_EMP_POP_MOD_URL%>');
	            var target = $(this).attr("href");

	            // load the url and show modal on success
	            $("#empModModal").load(target,param, function () {
	                $("#empModModal").modal({backdrop: 'static'});
	            });
	        }
	    });
	//모달 내부 지우기
	 $("#empModal").on('hidden.bs.modal', function () {
	 	$(".modal-body").empty()
	});
	 $("#empModModal").on('hidden.bs.modal', function () {
		 $(".modal-body").empty()
		});
}
//삭제
function delEmp(){
	var param = {
			actionID	:"<%=EmpConstKey.ACTION_EMP_DELETE_OK%>",			
			emp_id 	: $("#emp_id").val()
			};
	
     $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SYSTEM_EMP_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        success: function (e) {
        	if (e.resCode == "0") {
        		//alert(e.resMsg);
        		$("#emp_id").val("")
        		goEmpList();
        	}else{
        		alert(e.resMsg);
			}
        }, error: function () {
        	alert('<spring:message code="MSG.FAIL"/>');
        }
    }); 
	
}

function deatilInfo(emp_id){
	$("#emp_id").val(emp_id);
}
//사용자 정보 취득
function goEmpList(){		
	$("#gridEmpList").jqGrid('clearGridData');
	jQuery("#gridEmpList").setGridParam({url: "<%=ConstUrl.SYSTEM_EMP_URL%>?v=" + (new Date().getTime()),
		postData: {
			actionID: "<%=EmpConstKey.ACTION_EMP_LIST%>"
			,searchKey: dbSrchGb
			,searchVal: $("#searchVal").val()
			,searchStatus:dbSrchSt
	}}).trigger("reloadGrid");
}


</script>

<!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
<!-- content 까지 유지하라는 글을 봤는데 창을 열면 없어지는 현상때문에 팝업 화면에 이동시킴. -->
	<div class="modal fade" id="empModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>
	<div class="modal fade" id="empModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>