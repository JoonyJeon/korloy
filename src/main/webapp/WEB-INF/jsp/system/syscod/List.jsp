<%----------------------------------------------------------------------------------------
 - 파일이름	: system/syscod/List.jsp
 - 설      명	: 시스템그룹코드관리 page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2018.11.21    1.0     LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@page import = "java.util.List"%>
<%@page import="com.eaction.framework.common.util.PagingUtil" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.common.code.CodeTableMng" %>
<%@page import="com.eaction.framework.business.system.code.constant.SysCodConstKey"%>
<%@page import="com.eaction.framework.business.system.code.model.SysCod"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	String currMenuId = MenuMng.getMenuId(menu_url);
	String curr_menu_name = StringUtil.nvl(MenuMng.getMenuName(currMenuId, userInfo));
	
	SysCod searchInfo = (SysCod)request.getAttribute(ConstKey.SEARCH_DATA);	
%>

<style>
.ui-jqgrid .ui-search-table .ui-search-input>input
	{
	display: block;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	font-size: 12px;
	font-family:Arial, 'Nanum Gothic' ;
	color: #ff4200;
	height: 22px;
	border: 1px solid #abadb3;
	border-left: 1px solid #dbdfe6;
	border-right: 1px solid #dbdfe6;
	border-bottom: 1px solid #e3e9ef;
	border-radius:3px;
	text-indent: 2px;
}
</style>
<form id="myForm" name="myForm">
<input type="hidden" id="sel_grcode" name="sel_grcode">
<input type="hidden" id="sel_code" name="sel_code">
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
				
				<!-- 상단 버튼들 -->
				<div class="col-lg-4" style="float: right;height: 46px;padding-top: 5px;text-align: right;padding-right: 10px;">
					<a  href="#" data-target="#grCdModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.REG"/>
					</a>
					<a href="#" data-target="#grCdModModal" class="btn btn-primary btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.UPDATE"/>
					</a>					
					<button type="button" class="btn btn-primary btn-sm" id="grCdDel">
						<spring:message code="TEXT.COMM.BTN.DELETE"/>
					</button>
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
										<div class="jarviswidget-editbox">											
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
						
			<!-- 하단 상세 그리드 -->
			<div class="row">
				
				<div class="col-lg-4" style="float: right;height: 46px;padding-top: 5px;text-align: right;padding-right: 10px;">
					<a  href="#" data-target="#grCdDtModal" class="btn btn-success btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.REG"/>
					</a>
					<a href="#" data-target="#grCdDtModModal" class="btn btn-success btn-sm">
					 	<spring:message code="TEXT.COMM.BTN.UPDATE"/>
					</a>
					<button type="button" class="btn btn-success btn-sm" id="grCdDtDel">
						<spring:message code="TEXT.COMM.BTN.DELETE"/>
					</button>
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
										<div class="jarviswidget-editbox">											
										</div>
										<div class="widget-body no-padding">																								
											<table id="gridList2"></table>
											<div id="gridToolbar2"></div>	
										</div>								
									</div>
								</div>
						</article>						
					</div>													
			</section>	
	</div>
	
</div>


<!-- Script 영역 -->


<eaction:script common="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>"/>
<script type="text/javascript">
jQuery(document).ready(function(){
	pageSetUp();	
	bindClickEvent();
	
	jQuery.ajaxSettings.traditional = true;
	jQuery("#gridList").jqGrid({
		url: "<%=ConstUrl.SYSTEM_SYSCOD_URL %>",
		postData: {
			actionID: "<%=SysCodConstKey.ACTION_SYSCOD_LIST%>"
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
		height: 200,
	   	colNames:[	
	   	          		//그룹코드
						"<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE'/>",
						 //그룹코드명
						"<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_K'/>",
						//그룹코드명
						"<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_E'/>",
						//그룹코드명
						"<spring:message code='TEXT.SYS.MGMT.POPUP.GROUP_CODE_NAME_J'/>",
						//작업일시
						"<spring:message code='TEXT.SYS.MGMT.POPUP.WORKER_DATE'/>",
   						//작업자
						"<spring:message code='TEXT.SYS.MGMT.POPUP.WORKER_NAME'/>"
	   	],
	   	colModel:[				
						{name:"grcode"			,index:"grcode"			, width:230},
						{name:"grcodenm_k"		,index:"grcodenm_k"		, width:230},
						{name:"grcodenm_e"		,index:"grcodenm_e"		, width:230},
						{name:"grcodenm_j"		,index:"grcodenm_j"		, width:230},
						{name:"upd_dt"			,index:"upd_dt"			, width:180, align:"center", search:false},
						{name:"upd_id"			,index:"upd_id"				, width:138, align:"center", search:false}
	   	],
	   	sortname: "grcode",
	   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
	   	rowList : [15, 30, 50],
	   	sortorder: "asc",
	    shrinkToFit: true,
	    toolbarfilter : true,
		viewrecords: true,
	   	pager: "#gridToolbar",
	   	autowidth : true,
	   	onSelectRow: function(id){
	   		var id = jQuery("#gridList").getGridParam("selrow");		
	   		if(id){
	   			var ret = jQuery("#gridList").getRowData(id);				
	   			//상세 그리드 리로드
	   			GridReload2(ret.grcode);
	   			//선택한 그룹 코드
	   			$("#sel_grcode").val(ret.grcode);
	   			$("#sel_code").val("");
	   		}
	   	},
	   	ondblClickRow: function (rowid, iRow, iCol) { 
	   		var ret = jQuery("#gridList").getRowData(rowid);
   			//상세 그리드 리로드
   			GridReload2(ret.grcode);
  			//선택한 그룹 코드
   			$("#sel_grcode").val(ret.grcode);
   			$("#sel_code").val("");
   			//수정버튼클릭처리(팝업표시)
			$('a[data-target=#grCdModModal]').click();
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
	jQuery("#gridList").jqGrid("filterToolbar",{stringResult: true,searchOnEnter : false,defaultSearch : "cn",ignoreCase: true});
	
	jQuery("#gridList2").jqGrid({
		url: "<%=ConstUrl.SYSTEM_SYSCOD_URL %>",
		postData: {
			actionID: "<%=SysCodConstKey.ACTION_SYSCOD_LIST_AJAX%>"
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
		height: 200,
	   	colNames:[	
	   	          		"",
	   	          		//상세 코드
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE'/>",
						 //코드명
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_K'/>",
						//코드명
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_E'/>",
						//코드명
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_CODE_NAME_J'/>",
						//사용여부
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.USEYN'/>",	
						"",						
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.LEVEL'/>",
						"",
						//작업일시
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_WORKER_DT'/>",
   						//작업자
						"<spring:message code='TEXT.SYS.DETAIL.MGMT.POPUP.DETAIL_WORKER_NAME'/>"
	   	],
	   	colModel:[				
						{name:"grcode"			,index:"code"			, width:230, hidden:true},
						{name:"code"				,index:"code"			, width:230},
						{name:"codenm_k"		,index:"codenm_k"	, width:230},
						{name:"codenm_e"		,index:"codenm_e"	, width:230},
						{name:"codenm_j"		,index:"codenm_j"		, width:230},
						{name:"use_yn_nm"		,index:"use_yn_nm"	, width:230},
						{name:"use_yn"			,index:"use_yn"		, width:230, hidden:true},
						{name:"levelcod"			,index:"levelcod"		, width:230, hidden:true},							   		
						{name:"levelcodNm"		,index:"levelcodNm"	, width:230},
						{name:"upd_dt"			,index:"upd_dt"		, width:180, align:"center"},
						{name:"upd_id"			,index:"upd_id"			, width:138, align:"center"}
	   	],
	   	sortname: "grcode",
	   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
	   	rowList : [15, 30, 50],
	   	sortorder: "asc",
	    shrinkToFit: true,
	    toolbarfilter : true,
		viewrecords: true,
	   	pager: "#gridToolbar2",
	   	autowidth : true,
	   	onSelectRow: function(id){
	   		var id = jQuery("#gridList2").getGridParam("selrow");		
	   		if(id){
	   			var ret = jQuery("#gridList2").getRowData(id);
	   			//선택한 코드값
				$("#sel_code").val(ret.code);
	   		}
	   	},
	   	ondblClickRow: function (rowid, iRow, iCol) { 
	   		var ret = jQuery("#gridList2").getRowData(rowid);
   			//선택한 코드값
			$("#sel_code").val(ret.code);
			//수정버튼클릭처리(팝업표시)
			$('a[data-target=#grCdDtModModal]').click();
	   	}
	});
	jQuery("#gridList2").jqGrid("navGrid","#gridToolbar2",{
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
		$("#gridList").jqGrid('setGridWidth', $("#jarviswidget").width());
	})
	
	$(window).on('resize.jqGrid', function() {
		$("#gridList2").jqGrid('setGridWidth', $("#jarviswidget").width());
	})
	
});

/* 그룹 코드 목록 리로드 */
function GridReload(sort, order){
	if(sort == null || sort == "") {
		sort = "grcode";
	}
	
	if(order == null || order == "") {
		order = "asc";
	}
	
	$("#gridList").jqGrid('clearGridData');
	var param = {actionID: "<%=SysCodConstKey.ACTION_SYSCOD_LIST%>"			
	};
		$("#gridList").jqGrid('setGridParam',
			{    url: "<%=ConstUrl.SYSTEM_SYSCOD_URL%>",
				datatype: "json",
				mtype : "POST",
				ignoreCase: true,
				sortname : sort,
				sortorder : order,
				page:1,
				postData: param,  //검색조건
				search: true,       //검색유무
			}).trigger("reloadGrid");
}
/* 상세 코드 목록 리로드 */
function GridReload2(grcode){
	$("#gridList2").jqGrid('clearGridData');
	var param = {actionID: "<%=SysCodConstKey.ACTION_SYSCOD_LIST_AJAX%>",
			grcode : grcode
	};
		$("#gridList2").jqGrid('setGridParam',
			{    url: "<%=ConstUrl.SYSTEM_SYSCOD_URL%>",
				datatype: "json",
				mtype : "POST",
				ignoreCase: true,
				sortname : "sortseq",
				sortorder : "asc",
				page:1,
				postData: param,
				search: true,
			}).trigger("reloadGrid");
}

function bindClickEvent(){
	//그룹 코드 등록 모달 호출
	$('a[data-target=#grCdModal]').on('click', function (ev) {
        ev.preventDefault();  
            $(this).attr('href', '<%=ConstUrl.SYSTEM_SYSCOD_GRCODE_POP_URL%>');
            var target = $(this).attr("href");

            // load the url and show modal on success
            $("#grCdModal").load(target, function () {
                $("#grCdModal").modal({backdrop: 'static'});
            });        
    });
	
	//그룹 코드 수정 모달 호출
	 $('a[data-target=#grCdModModal]').on('click', function (ev) {
	        ev.preventDefault();
	        if ($("#sel_grcode").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
	        	$("#grCdModModal").modal("hide");
	        }else {
	        	var selrow 	= $("#gridList").jqGrid('getGridParam','selrow');
	  		  	var grid1 	= $("#gridList").jqGrid('getRowData',selrow);
	        	var params = {
	        			grcode : $("#sel_grcode").val(),
	        			grcodenm_k : grid1.grcodenm_k,
	        			grcodenm_e : grid1.grcodenm_e,
	        			grcodenm_j : grid1.grcodenm_j	        			
	        	}
	        		        		        	
	            $(this).attr('href', '<%=ConstUrl.SYSTEM_SYSCOD_GRCODE_POP_MOD_URL%>');
	            var target = $(this).attr("href");

	            // load the url and show modal on success
	            $("#grCdModModal").load(target,params, function () {
	                $("#grCdModModal").modal({backdrop: 'static'});
	            });
	        }
	    });
	//삭제
		$("#grCdDel").bind("click", function(){
			if ($("#sel_grcode").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
			}else{
				if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
					delGrCd();
				}
			}
	     });
		
	/*****************************상세 코드*************************************/
	//코드 등록 모달 호출
	$('a[data-target=#grCdDtModal]').on('click', function (ev) {
        ev.preventDefault();
        if ($("#sel_grcode").val() == "") {
        	alert("<spring:message code="MSG.NONE.SELECT"/>");
        	$("#grCdDtModal").modal("hide");
        }else {
        	var param = {
        			grcode : $("#sel_grcode").val()
        	}
        	$(this).attr('href', '<%=ConstUrl.SYSTEM_SYSCOD_CODE_POP_URL%>');
            var target = $(this).attr("href");

            // load the url and show modal on success
            $("#grCdDtModal").load(target,param, function () {
                $("#grCdDtModal").modal({backdrop: 'static'});
            });
        }            
    });
	
	//코드 수정 모달 호출
	 $('a[data-target=#grCdDtModModal]').on('click', function (ev) {
	        ev.preventDefault();
	        if ($("#sel_code").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
	        	$("#grCdDtModModal").modal("hide");
	        }else {
	        	var selrow 	= $("#gridList2").jqGrid('getGridParam','selrow');
	  		  	var grid2 	= $("#gridList2").jqGrid('getRowData',selrow);
	        	var params = {
	        			grcode 		: $("#sel_grcode").val(),
	        			code			: $("#sel_code").val(),
	        			codenm_k 	: grid2.codenm_k,
	        			codenm_e 	: grid2.codenm_e,
	        			codenm_j 	: grid2.codenm_j,
	        			levelcod		: grid2.levelcod,
	        			use_yn		: grid2.use_yn
	        	}        	
	            $(this).attr('href', '<%=ConstUrl.SYSTEM_SYSCOD_CODE_POP_MOD_URL%>');
	            var target = $(this).attr("href");

	            // load the url and show modal on success
	            $("#grCdDtModModal").load(target,params, function () {
	                $("#grCdDtModModal").modal({backdrop: 'static'});
	            });
	        }
	    });
	//상세 코드 삭제
		$("#grCdDtDel").bind("click", function(){
			if ($("#sel_code").val() == "") {
	        	alert("<spring:message code="MSG.NONE.SELECT"/>");
			}else{
				if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
					delCd();
				}
			}
	     });		
		
	
}

//그룹코드 삭제 전 상세 코드 체크
function delGrCd(){	
	var param={actionID: "<%=SysCodConstKey.ACTION_SYSCOD_DELETE_CNT%>",
 			grcode : $("#sel_grcode").val()
 		};	
	 $.ajax({
	 		type : "POST",
	        dataType: "json",				 	        
	        data: param,
	        url: "<%=ConstUrl.SYSTEM_SYSCOD_URL%>",	
	        async: false,		//비동기식인 ajax에서 순차적으로 값을 찍어오기위해 false로 해준다.
	        success: function(data){
	        	if (data.resCode == "0") {
            		//alert(e.resMsg);
	        		GrCodeDelete();
	        		return;
            	}else{
            		alert("<spring:message code='MSG.SYSCODE.MGMT.ALERT.GRCODE_DEL_EXIST_CD'/>",'');
	        		return;
				}
	        	
			}, 
			error: function(){	
			}
	 	});
}
//그룹코드 삭제
function GrCodeDelete(){	
	var param = {actionID: "<%=SysCodConstKey.ACTION_SYSCOD_DELETE_OK%>",
			grcode : $("#sel_grcode").val()
		};
	 $.ajax({
	 		type : "POST",
	        dataType: "json",
	        data: param,
	        url: "<%=ConstUrl.SYSTEM_SYSCOD_URL%>",
	        page:1,
	        async: false,		//비동기식인 ajax에서 순차적으로 값을 찍어오기위해 false로 해준다.
	        success: function(e){
	        	if (e.resCode == "0") {
            		//alert(e.resMsg);
	        		GridReload();
		        	$("#gridList2").jqGrid('clearGridData');
		        	$("#sel_grcode").val("");
	        		 return;
            	}else{
            		alert(e.resMsg);
            		return;
				}
	        	
			}, 
			error: function(){	
			}
	 	});	
}

//상세 코드 삭제
function delCd(){
	var param = {actionID: "<%=SysCodConstKey.ACTION_SYSCODDTL_DELETE_OK%>",
			grcode : $("#sel_grcode").val(),
			code : $("#sel_code").val()
		};
	 $.ajax({
	 		type : "POST",
	        dataType: "json",
	        data: param,
	        url: "<%=ConstUrl.SYSTEM_SYSCOD_URL%>",
	        page:1,
	        async: false,		//비동기식인 ajax에서 순차적으로 값을 찍어오기위해 false로 해준다.
	        success: function(e){
	        	if (e.resCode == "0") {
            		//alert(e.resMsg);
	        		GridReload2($("#sel_grcode").val());			        	
		        	$("#sel_code").val("");
	        		 return;
            	}else{
            		alert(e.resMsg);
            		return;
				}
	        	
			}, 
			error: function(){	
			}
	 	});
}

</script>


<!-- 모달 창에 아이디 값들은 본화면과 겹지면 안됨 -->
<!-- 그룹코드 -->
	<div class="modal fade" id="grCdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>
	<div class="modal fade" id="grCdModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>
	
<!-- 그룹코드 상세-->	
	<div class="modal fade" id="grCdDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>
	<div class="modal fade" id="grCdDtModModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">			
	</div>




