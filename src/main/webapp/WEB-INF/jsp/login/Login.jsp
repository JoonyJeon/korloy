<%----------------------------------------------------------------------------------------
 - 파일이름	:	login/Login.jsp
 - 설    명	:	로그인 화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.12.27    1.0       KYM      
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" %>

<%@page import = "java.util.List" %>
<%@page import="com.eaction.framework.common.util.StringUtil" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<% 
	String error = StringUtil.nvl((String)request.getAttribute("ERROR"));
	String login_result = StringUtil.nvl((String)request.getAttribute(ConstKey.LOGIN_RESULT));
	List<String> mgmt_ip = (List<String>)request.getAttribute("mgmt_ip");
%>
<eaction:script common="<%=ConstKey.KEY_YES %>"  picker="<%=ConstKey.KEY_YES %>" sha256="<%=ConstKey.KEY_YES %>"/> 
<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
<script>
<%if(mgmt_ip != null && mgmt_ip.size() > 0){%>
var mgmt_ip = new Array(); 
<%	for(int n=0; n<mgmt_ip.size(); n++){%>
<%		String user_ip = (String)mgmt_ip.get(n);%>
	mgmt_ip[<%=n%>] = String('<%=user_ip%>');
<%	}%>
if (!String.prototype.startsWith) {
    Object.defineProperty(String.prototype, 'startsWith', {
        value: function(search, rawPos) {
            var pos = rawPos > 0 ? rawPos|0 : 0;
            return this.substring(pos, pos + search.length) === search;
        }
    });
}

var isMgmtIp = false;
var con_ip = String(ip());
console.log(con_ip);
con_ip = con_ip.toLowerCase();
for(var n=0; n<mgmt_ip.length; n++){
	if(con_ip.startsWith(mgmt_ip[n])){
		isMgmtIp = true;
		break;
	}
}
if(isMgmtIp == false){
	alert("등록된 관리자 아이피가 아닙니다.");
	window.location.href = '/WEB-INF/jsp/common/error/catch404.jsp';
}
<%}%>
</script>
<style>
 @import url("/styles/login.css");
</style>

<c:set var="TITLE"><spring:message code="TEXT.TOP_MAIN_TITLE" /></c:set>
<eaction:html title="${TITLE}" login="<%=ConstKey.KEY_NO %>">
<body>

	<!-- MAIN CONTENT -->				    
    <div class="wrapcont">
        <div class="contents">
           	<h1><img src="images/carpeach_logo.png" alt="logo"/></h1>
           	<div class="loginfo">
	            <form id="login-form" action="<%=ConstUrl.LOGIN_URL %>" method="POST">
		            <input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.LOGIN_OK %>">
					<input type="hidden" name="time_zone" id="time_zone">
					<input type="hidden" name="login_ip" id="login_ip">
					
					<section class="gc_user wrapper">
						<!-- <img src="images/gc_icon_user.png" width="14"/> -->
	                	<input type="text" name="user_id" id="user_id" placeholder="<spring:message code="TEXT.LOGIN.ID" />"/>
	                </section>
	                
	                <section class="gc_pw wrapper">
	                	<!-- <img src="images/gc_icon_pw.png" width="12"/> -->
	                	<input type="hidden" name="password" id="password"/>
		                <input type="password" name="password2" id="password2" placeholder="<spring:message code="TEXT.LOGIN.PASSWORD" />"/>
	                </section>
	                <div class="checkbox_container gc_chk">
	                    <input type="checkbox" name="remember" id="remember" />
	                    <label for="remember"></label>
	                    <span><spring:message code="TEXT.LOGIN.SAVE" /></span>
	                    <!-- <select></select> -->
	                </div>
	                <button type="button" id="login"><spring:message code="TEXT.LOGIN.BTN.LOGIN" /></button>
	            </form>
			</div>
         	<h2><img src="images/logotype.png" alt="logo"/></h2>
		</div>
		<p id="p1"></p>				
	</div>
	<script type="text/javascript">
		var timeZone;
		jQuery(document).ready(function(){			
			// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백
		    var userInputId = getCookie("userInputId");
		    $("input[name='user_id']").val(userInputId); 
		    
		    // 로그인 페이지 로딩시 입력칸에 ID가 있을경우, remember 체크상태 유지
		    if($("input[name='user_id']").val() != ""){ 
		        $("#remember").attr("checked", true); 
		    }
		    
		    $("#remember").change(function(){ 
		        if($("#remember").is(":checked")){ // ID 저장하기 체크시
		            var userInputId = $("input[name='user_id']").val();
		            setCookie("userInputId", userInputId, 365); // 365일 동안 쿠키 보관
		        }else{
		            deleteCookie("userInputId"); // ID 저장하기 체크 해제 시, 쿠키삭제
		        }
		    });
		    
		    $("input[name='user_id']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
		        if($("#remember").is(":checked")){ // ID 저장하기를 체크한 상태시
		            var userInputId = $("input[name='user_id']").val();
		            setCookie("userInputId", userInputId, 365); // 365일 동안 쿠키 보관
		        }
		    });			
			
			
			//타임존 취득
			timeZone = moment.tz.guess();
			
			$("input[name=user_id]").focus();
			
			$("input[name=user_id]").keydown(function (key) {
		        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
		        	$("#login").click();
		        } 
		    });
			
			$("input[name=password2]").keydown(function (key) {
		        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
		        	$("#login").click();
		        } 
		    });
						
			$("#login").bind("click", function(){
				$("#login-form #login_ip").val(con_ip);
				$("#login-form").submit();
			});
			
			calcOffset();
		});
	
		var errorClass = 'invalid';
		var errorElement = 'em';
		$(function() {
			// Validation
			$("#login-form").validate({
				errorClass		: errorClass,
				errorElement	: errorElement,
				highlight: function(element) {
			        $(element).parent().removeClass('state-success').addClass("state-error");
			        $(element).removeClass('valid');
			    },
			    unhighlight: function(element) {
			        $(element).parent().removeClass("state-error").addClass('state-success');
			        $(element).addClass('valid');
			    },
			    submitHandler: function() {
			    	$("#password").val(sha256_digest($("#password2").val()));    	
			    	$("#password2").val("");
			    	$("#time_zone").val(timeZone);
	                
	                return true;
	                
	            },
	            // Rules for form validation
				rules : {
					user_id : {
						required : true							
					},
					password2 : {
						required : true,							
						maxlength : 30
					}
				},
	
				// Messages for form validation
				messages : {
					user_id : {
						required : '<spring:message code='MSG.ERROR.USER_ID'/>'							
					},
					password2 : {
						required : '<spring:message code='MSG.ERROR.PASSWORD'/>'
					}
				},
				// Do not change code below
				errorPlacement : function(error, element) {						
					error.insertAfter(element);
				}
			});
		});

		// 쿠키저정하기
		function setCookie(cookieName, value, exdays){
		    var exdate = new Date();
		    exdate.setDate(exdate.getDate() + exdays);
		    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
		    document.cookie = cookieName + "=" + cookieValue;
		}
		// 쿠키삭제하기
		function deleteCookie(cookieName){
		    var expireDate = new Date();
		    expireDate.setDate(expireDate.getDate() - 1);
		    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
		}
		// 쿠키불러오기
		function getCookie(cookieName) {
		    cookieName = cookieName + '=';
		    var cookieData = document.cookie;
		    var start = cookieData.indexOf(cookieName);
		    var cookieValue = '';
		    if(start != -1){
		        start += cookieName.length;
		        var end = cookieData.indexOf(';', start);
		        if(end == -1)end = cookieData.length;
		        cookieValue = cookieData.substring(start, end);
		    }
		    return unescape(cookieValue);
		}	

	<% if (error.equals("error")){ %>
		//(DB상태조회(DB접속정보) 취득시 에러가 발생했습니다.				
		alert("<spring:message code='MSG.ERROR.PROCESS'/>"); 
	<%} %>	
	<%if (ConstKey.LOGIN_FAIL.equals(login_result)) { %>	
	    alert("<spring:message code='MSG.ERROR.LOGIN_FAIL'/>"); 
	<% } else if (ConstKey.LOGIN_NEED.equals(login_result)) { %>
	    alert("<spring:message code='MSG.ERROR.LOGIN_NEED'/>");
	<% } else if (ConstKey.LOGIN_STATUS_FAIL.equals(login_result)) { %>
		alert("<spring:message code='MSG.ERROR.LOGIN_FAIL_STATUS'/>");
	<% } else if (ConstKey.LOGIN_ADMIN_NEED.equals(login_result)) { %>
	    alert("<spring:message code='MSG.ERROR.LOGIN_ADMIN_NEED'/>"); 	
	<% } else if (ConstKey.IP_ADDR_NOT_ALLOWED.equals(login_result)) { %>
	    alert("인가 되지 않은 IP 주소 입니다."); 	
	<% } %>
	
	</script>
</body>
</eaction:html>
