<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/leftmenu.jsp
 - 설    명	:	왼쪽메뉴화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.09    1.0      LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8"%>
<%--@page import="com.eaction.framework.business.logic.main.model.MainInfo" --%>
<%@page import="com.eaction.framework.business.logic.staff.constant.StaffConstUrl" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%@page import = "com.eaction.framework.business.logic.user.model.UserInfo"%>		
<%--
MainInfo mainInfo = (MainInfo)request.getAttribute(ConstKey.RESULT_DATA);

String company_cnt = mainInfo.getCompany_cnt();
String request_cnt = mainInfo.getRequest_cnt();


//String total_cnt = String.valueOf(Integer.parseInt(request_cnt));

--%>
  
<style>
/* 로그인 사용자 이름 부분 마우스 오버 무효화 */
.login-info a:hover{color:#c0bbb7}

div.dataTables_processing {
  z-index: 1;
}
</style>
<form name="leftForm" method="POST">  
<input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="">
<input type="hidden" name="root" value="<%=root%>">
<input type="hidden" name="menuUrl">
<input type="hidden" name="user_id" id="user_id" value="<%=userInfo.getUser_id() %>">
</form>
<aside id="left-panel">
	<!-- User info -->
	<div class="login-info">
		<span> <!-- User image size is adjusted inside CSS, it should stay as it -->
			<a href="javascript:showMyPageModal();" id="show-shortcut">
				<%--<img src="<%=PATH_IMG %>/avatars/male.png" alt="me" class="online" />  --%>
				<span id="leftmenu-usernm" style="max-width:200px">

				</span>						
			</a> 
			
		</span>
	</div>
	<!-- end user info -->
	
	<eaction:menu root="<%=root %>" menu_url="<%=menu_url %>"/>
	
	
	<span class="minifyme" data-action="minifyMenu"> 
		<i class="fa fa-arrow-circle-left hit"></i> 
	</span>

</aside>

<div class="modal fade" id="mypageModal" tabindex="-1" role="dialog" aria-labelledby="myModallabel" aria-hidden="true"></div>

<script>
	
	var sDom = "<<'col-xs-12 col-sm-6'>r>t<'dt-toolbar-footer'<'col-sm-2 col-xs-2 hidden-xs'l><'col-sm-4 col-xs-4 hidden-xs'i><'col-xs-12 col-sm-6'p>>";
	var lengthMenu = [ [ 5, 15, 30, 50], [ 5, 15, 30, 50] ];
	var language = {
        paginate: {
            first: "처음",
            last: "마지막",
            previous: "«",
            next: "»"
        },
        emptyTable: "정보가 없습니다.",
        lengthMenu: "목록 수  _MENU_",
        info: "총 _MAX_ 건",
        infoEmpty: "총 0 건",
        processing: "<img src='/images/search_spiner.gif'>",
      	zeroRecords : "검색 결과가 없습니다.",
      	sInfoFiltered : "(총 _MAX_ 개)",
    }
</script>