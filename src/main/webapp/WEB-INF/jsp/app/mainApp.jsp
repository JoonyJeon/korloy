<%----------------------------------------------------------------------------------------
 - 파일이름	: inti/init.jsp
 - 설    명		: 첫화면
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2019.05.10    1.0       HSI      신규작성
 -							KSH
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstKey"%>
<%@page import = "com.eaction.framework.business.logic.search.constant.SearchConstKey"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
List<AppInfo> main_app_list = (List<AppInfo>)request.getAttribute("main_app_list");
%>
<style>
/*.automatic > ul > li a {
    padding: 5px 30px;
}
.search_bar .input {
    float: left;
    width: 100%;
    position: relative;
}*/

/*#footer {position:fixed; left:0; bottom:0; width:100%;}*/
</style>
<div id="container" class="main">
    <div class="mainDeco"><img src="/img/deco_home.svg"></div>
	<div class="search_bar">
		<div class="select" style="display:none">
			<select name="" id="">
				<option value="">Tools Classification</option>
				<option value=""></option>
				<option value=""></option>
				<option value=""></option>
				<option value=""></option>
			</select>
		</div>
		<div class="input" style="width:100%">
		    <%-- Enter the model number or classification --%>
			<input type="text" style="margin-left:5px" id="home_auto_input_text" placeholder="<%=LangMng.LANG_D("FR000006", session_lang) %>" data-lang="FR000006" data-langtype="placeholder" autocomplete='off'>
			<button class="btn_clear" onclick="$(this).prev().val('');">지우기</button>
			<button class="btn_search" onclick="doKeyWordSearch()">검색</button>
		</div>
        <div class="automatic" id="home_automatic" style="display:none" >
            <ul  id="home_automatic_ul">
            </ul>
        </div>
	</div>
	<div id="contents">
		<div class="main_cont">
			<div class="inner">
				<div class="mainapp_list">
                    <div class="swiper-container">
                        <div class="swiper-wrapper" id="main_app_list">
                        	<% if (main_app_list != null && main_app_list.size() > 0) { %>
								<% for (AppInfo result : main_app_list) { %>
									<div class="swiper-slide">
										<a href="#" onclick="javascript:goSubApplication('<%=result.getMa_cd() %>', '<%=result.getMa_nm() %>');">
		                                    <div class="img">
		                                        <img src="<%=UPLOAD_ROOT_PATH %><%=result.getMain_img() %>" alt="<%=result.getMa_nm() %>"  onerror="onErrorImage(this)" />
		                                    </div> <span data-lang="<%=result.getMa_cd() %>"><%=result.getMa_nm() %></span>
		                                </a>
		                            </div>
								<% } %>
							<% } %>
                        </div>
                    </div>
                    <div class="btn_prev">prev</div>
                   	<div class="btn_next">next</div>
                </div>
			</div>
		</div>
	</div>
	
</div>
<form id="myForm" name="myForm" method="POST" action="<%=ConstUrl.APP_SUB_URL%>">
	<input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
	<input type="hidden" name="ma_cd" id="ma_cd" />
	<input type="hidden" name="unit_cd" id="unit_cd" />
</form>

<form id="autoSearchForm" name="autoSearchForm" method="POST" action="<%=ConstUrl.SEARCH_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="ma_cd" id="ma_cd" />
    <input type="hidden" name="sa_cd" id="sa_cd" />
    <input type="hidden" name="ig_cd" id="ig_cd" />
    <input type="hidden" name="matnr" id="matnr" />
    <input type="hidden" name="auto_input_text" id="auto_input_text" />
    <input type="hidden" name="auto_complete_text" id="auto_complete_text" />
    <input type="hidden" name="auto_mode" id="auto_mode" />
</form>
<script type="text/javascript">

jQuery(document).ready(function(){
   
	$("#home_automatic").hide();

	$(document).mouseup(function(e){
	    var autoLayer = $('#home_automatic');
	    if(autoLayer.has(e.target).length == 0){
	        $("#home_automatic_ul *").remove();
	        $("#home_automatic").hide();
	    }
	});

	$('.loading').delay('1000').fadeOut();
});

var typingTimer;                //timer identifier
var doneTypingInterval = 350;  //time in ms, 5 second for example
var keyEvent;
$("#home_auto_input_text").keydown(function(event) {
   clearTimeout(typingTimer);
});
    
$("#home_auto_input_text").keyup(function(event) {
	keyEvent = event;
    clearTimeout(typingTimer);
    typingTimer = setTimeout(doneTyping, doneTypingInterval);
    event.preventDefault();
});

//user is "finished typing," do something
function doneTyping () {
    doAutoComplete();
}

function goSubApplication(ma_cd, ma_nm){
	doLog("EC0002", ma_nm, "");
    $("#myForm #ma_cd").val(ma_cd);
    $("#myForm #unit_cd").val($("#btnSizeUnit").val());
	goPageTwo (myForm, "");
}

var auto_sel_index = -1;
function doAutoComplete(){

    if(keyEvent.which == 38 || keyEvent.which == 40 || keyEvent.which == 13){
        if ( $('#home_automatic').css('display') == 'block' ){
            // up
            if( keyEvent.which == 38){
/*             	auto_sel_index = auto_sel_index -1;
                if(auto_sel_index >= 0){
	                $(".auto_li").eq(auto_sel_index).css("backgroundColor", "#eeeeee");
	                $(".auto_li").eq(auto_sel_index+1).css("backgroundColor", "");
                }else{
                	auto_sel_index = $(".auto_li").length-1
                	$(".auto_li").eq(auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(0).css("backgroundColor", "");
                }
                event.preventDefalt(); */
            // down
            }else if( keyEvent.which == 40){
/*             	auto_sel_index = auto_sel_index +1;
                if(auto_sel_index < $(".auto_li").length){
	                $(".auto_li").eq(auto_sel_index).css("backgroundColor", "#eeeeee");
	                $(".auto_li").eq(auto_sel_index-1).css("backgroundColor", "");
                }else{
                	auto_sel_index = 0
                    $(".auto_li").eq(auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(auto_sel_index-1).css("backgroundColor", "");
                }
                event.preventDefalt(); */
            // Enter
            }else if(keyEvent.which == 13 && auto_sel_index >= 0){
            	$(".auto_li").eq(auto_sel_index).children('a').trigger('click');
            }else if(keyEvent.which == 13 && auto_sel_index < 0){
            	doKeyWordSearch();
            }
        }else{
        	// Enter
	        if(keyEvent.which == 13){
	        	doKeyWordSearch();
		    }
        }
    }else{
    	auto_sel_index = -1;
	    if($("#home_auto_input_text").val() != "" && $("#home_auto_input_text").val().length > 2){
		    var param = {
		            actionID :"<%=SearchConstKey.ACTION_SEARCH_AUTO%>",
		            auto_input_text : $("#home_auto_input_text").val(),
		        };
		    $.ajax({
		        type: "POST",
		        url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
		        data: param,
		        dataType: "json",
		        async: false,
		        cache: false,
		        success: function(d){	        	
		        	$("#home_automatic_ul *").remove();
		        	if(Number(d.total_cnt) > 0){
		        		$("#home_automatic_ul").append(d.html);
		                $("#home_automatic").show();
			        }else{
			        	$("#home_automatic").hide();
				    }
		        }, error: function() {
		
		        },complete:function(){
		        	
			    }
		    });
	    }else{
	    	$("#home_automatic_ul *").remove();
	    	$("#home_automatic").hide();
	    }
    }
}

function doAutoSearch(obj){
	var ma_cd = $(obj).data("macd");
	var sa_cd = $(obj).data("sacd");
	var ig_cd = $(obj).data("igcd");
	var matnr = $(obj).data("matnr");
	var auto_complete_text = $(obj).text();
	$("#autoSearchForm #ma_cd").val(ma_cd);
	$("#autoSearchForm #sa_cd").val(sa_cd);
	$("#autoSearchForm #ig_cd").val(ig_cd);
	$("#autoSearchForm #matnr").val(matnr);
	$("#autoSearchForm #auto_mode").val("A");
	$("#autoSearchForm #auto_complete_text").val(auto_complete_text);
	doLog("EC0003", "", auto_complete_text);
	autoSearchForm.submit();
}


function doKeyWordSearch(){
    var auto_input_text = $("#home_auto_input_text").val();
    if(auto_input_text != ""){
    	$("#autoSearchForm #auto_input_text").val(auto_input_text);
    	$("#autoSearchForm #auto_mode").val("M");
    	$("#autoSearchForm #ma_cd").val("");
        $("#autoSearchForm #sa_cd").val("");
        $("#autoSearchForm #ig_cd").val("");
        $("#autoSearchForm #matnr").val("");
        doLog("EC0003", "", auto_input_text);
        autoSearchForm.submit();
    }
}
</script>
