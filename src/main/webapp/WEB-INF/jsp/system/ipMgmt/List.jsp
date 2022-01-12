<%----------------------------------------------------------------------------------------
 - 파일이름	: system/ipmgmt/List.jsp
 - 설      명	: 관리자 ip관리  page
 - 추가내용     :
 - 버전관리     : 1.0
 ----------------------------------------------------------
 -   Date      Version   SysCoder   Description
 ------------  --------  -------  --------------------------
 - 2018.11.21    1.0     LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import = "java.util.List"%>

<%@page import="com.eaction.framework.common.util.PagingUtil" %>
<%@page import="com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@page import="com.eaction.framework.business.common.code.CodeTableMng" %>
<%@page import="com.eaction.framework.business.system.code.constant.SysCodConstKey"%>
<%@page import="com.eaction.framework.business.system.ipMgmt.constant.IpMgmtConstKey"%>
<%@page import="com.eaction.framework.business.system.code.model.SysCod"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>



<%
	SysCod searchInfo = (SysCod)request.getAttribute(ConstKey.SEARCH_DATA);	
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
			
			<div class="col-lg-4" style="float: right;height: 46px;padding-top: 5px;text-align: right;padding-right: 10px;">
				<!-- 조회 -->
				<button type="button" class="btn btn-danger btn-sm" id="btnSearch">
				 	<spring:message code="TEXT.COMM.BTN.SEARCH"/>
				</button>
				<a  href="#" data-target="#grCdDtModal" class="btn btn-primary btn-sm">
				 	<spring:message code="TEXT.COMM.BTN.REG"/>
				</a>
				<a href="#" data-target="#grCdDtModModal" class="btn btn-primary btn-sm">
				 	<spring:message code="TEXT.COMM.BTN.UPDATE"/>
				</a>
				<button type="button" class="btn btn-danger btn-sm" id="grCdDtDel">
					<spring:message code="TEXT.COMM.BTN.DELETE"/>
				</button>
			</div>
		</div>
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
	
</div>


<eaction:script common="<%=ConstKey.KEY_YES %>" jqGrid="<%=ConstKey.KEY_YES %>"/>

<script type="text/javascript">
jQuery(document).ready(function(){
	pageSetUp();	
	bindClickEvent();
	
	jQuery.ajaxSettings.traditional = true;
	jQuery("#gridList").jqGrid({
		url: "<%=ConstUrl.SYSTEM_IPMGMT_URL %>",
		postData: {
			actionID: "<%=IpMgmtConstKey.ACTION_SYSTEM_IPADMIN_LIST%>",
			grcode : "MGMT_USER_IP_LIST"
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
	   	          		
						"IP",
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
						{name:"code"				,index:"code"			, width:230},
						{name:"codenm_k"		,index:"codenm_k"	,  hidden:true},
						{name:"codenm_e"		,index:"codenm_e"	, hidden:true},
						{name:"codenm_j"		,index:"codenm_j"	, hidden:true},
						{name:"use_yn_nm"		,index:"use_yn_nm"	, hidden:true},
						{name:"use_yn"			,index:"use_yn"		, hidden:true},
						{name:"levelcod"			,index:"levelcod"	, hidden:true},							   		
						{name:"levelcodNm"		,index:"levelcodNm"	, hidden:true},
						{name:"upd_dt"			,index:"upd_dt"		, width:180, align:"center", search:false},
						{name:"upd_id"			,index:"upd_id"			, width:138, align:"center", search:false}
	   	],
	   	multiselect:true,
	   	sortname: "code",
	   	rowNum:<%=JQGRID_NORMAL_ROWNUM %>,
	   	rowList : [15, 30, 50],
	   	sortorder: "asc",
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
	})

	var myGrid = $("#gridList");
	$("#cb_"+myGrid[0].id).hide();
});

/* 상세 코드 목록 리로드 */
function goList(){
	$("#gridList").jqGrid('clearGridData');
	jQuery("#gridList").setGridParam({url: "<%=ConstUrl.SYSTEM_IPMGMT_URL%>?v=" + (new Date().getTime()),
		postData: {
			 actionID: "<%=IpMgmtConstKey.ACTION_SYSTEM_IPADMIN_LIST%>",
			 grcode : "MGMT_USER_IP_LIST"
	}}).trigger("reloadGrid");
}
function bindClickEvent(){

	//검색 버튼 클릭 이벤트 처리
	$("#btnSearch").bind("click", function() {
		goList();
	 });
	//코드 등록 모달 호출
	$('a[data-target=#grCdDtModal]').on('click', function (ev) {
        ev.preventDefault();
        var target = '<%=DOMAIN_NAME%><%=ConstUrl.SYSTEM_IPMGMT_POP_ADD_URL%>';
    	// URL 불러오고 모달창 띄우기 성공시
    	$("#myModal").load(target, function() {
    		$("#myModal").modal();
    	});            
    });
	
	//코드 수정 모달 호출
	 $('a[data-target=#grCdDtModModal]').on('click', function (ev) {
	        ev.preventDefault();
	        showEditModal();
	    });
	//상세 코드 삭제
	$("#grCdDtDel").bind("click", function(){
		var selrow = $('#gridList').jqGrid('getGridParam', 'selrow');
		if (selrow == null) {
        	alert("<spring:message code="MSG.NONE.SELECT"/>");
		}else{
			if (confirm("<spring:message code='MSG.CONFIRM.DELETE'/>")) {
				delCd();
			}
		}
     });		
		
	
}


//상세 코드 삭제
function delCd(){
	var selrow = $("#gridList").jqGrid('getGridParam', 'selrow');
	var param = {actionID: "<%=IpMgmtConstKey.ACTION_SYSTEM_IPADMIN_DELETE_OK%>",
			grcode : "MGMT_USER_IP_LIST",
			code : $('#gridList').getCell(selrow, 'code')
		};
	 $.ajax({
	 		type : "POST",
	        dataType: "json",
	        data: param,
	        url: "<%=ConstUrl.SYSTEM_IPMGMT_URL%>",
	        page:1,
	        async: false,		//비동기식인 ajax에서 순차적으로 값을 찍어오기위해 false로 해준다.
	        success: function(e){
	        	if (e.resCode == "0") {
            		alert(e.resMsg);
	        		goList();			        	
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


//편집모달창 띄우기
function showEditModal() {
	var target = '<%=DOMAIN_NAME%><%=ConstUrl.SYSTEM_IPMGMT_POP_MOD_URL%>'; 
	var selrow = $("#gridList").jqGrid('getGridParam', 'selrow');
	if(selrow == null) {
		alert("<spring:message code='MSG.NONE.SELECT'/>");
		$("#myModal").modal("hide");
	} else {
		var param = {
	    		grcode : $('#gridList').getCell(selrow, 'grcode'),
				code : $('#gridList').getCell(selrow, 'code')
		}
		//URL불러오고 모달창 띄우기 성공시
		$("#myModal").load(target, param, function() {
			$("#myModal").modal();
		});
	}
}

</script>


<!-- 그룹코드 상세-->	
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel" aria-hidden="true"></div>


