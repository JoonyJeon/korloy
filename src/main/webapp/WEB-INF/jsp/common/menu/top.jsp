<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/menu/top.jsp
 - 설    명	:	탑메뉴화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2018.11.09    1.0       LYS      신규작성
 ----------------------------------------------------------
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%@page import = "java.util.List"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "com.eaction.framework.business.logic.search.constant.SearchConstKey"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "java.util.Comparator"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
List<AppInfo> mega_main_list = (List<AppInfo>)request.getAttribute("mega_main_list");
List<AppInfo> mega_sub_list = (List<AppInfo>)request.getAttribute("mega_sub_list");
List<AppInfo> nation_list = (List<AppInfo>)request.getAttribute("nation_list");
String deviceType = (String)request.getAttribute("deviceType");
int cartCnt = (int)request.getAttribute("cartCnt");
String comm_nation = ConfigMng.getNation(request);
String comm_size_unit = ConfigMng.getSizeUnit(request);
String comm_curr = ConfigMng.getCurrency(request);
String comm_lang = ConfigMng.getLang(request);
String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
List<CodeInfo> lang_list = (List<CodeInfo>)request.getAttribute("lang_list");
int assemCnt = (int)request.getAttribute("assemCnt");

%>
<form name="topForm" id="topForm" method="POST">
  <input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="">
</form>
<div class="open_bg"></div>
<div class="open_m_bg"></div>
<div class="floatBtns">
    <span class="gotoBack"><a href="javascript:goBack();">BACK</a></span>
    <span class="gotoTop"><a href="javascript:void(0);">TOP</a></span>
</div>
<%if("P".equals(deviceType)){ %>
<div id="header">
    <div class="header_top">
        <div class="inner">
            <h1><a href="<%=ConstUrl.APP_MAIN_URL%>"><img src="<%=PATH_IMG %>/img_logo.png" alt="KORLOY"></a></h1>
            <div class="goto">
				<div class="header_icon">
                    <ul>
                    	<li id="area_user_name"><a href="javascript:void(0);" class="user_name"></a></li>
                       	<%-- Mypage --%>
                       	<li id="area_mypage" style="display:none;"><a href="javascript:void(0);" class="open_user" style="cursor:pointer"><span ><img src="<%=PATH_IMG %>/img_mypage.png" title="mypage"></span ><div data-lang="FR000197"><%=LangMng.LANG_D("FR000197", session_lang) %></div></a></li>
                        <li id="area_cart" style="display:none;">
                            <a href="javascript:void(0);" onclick="goCart();" >
                                <em class="count" id="cartCnt_top"><%=cartCnt %></em>
                                <%-- Cart --%>
                                <span><img src="<%=PATH_IMG %>/img_cart.png" title="Cart"></span><div data-lang="FR000040"><%=LangMng.LANG_D("FR000040", session_lang) %></div>
                            </a>
                        </li>
                        <%-- Login --%>
                        <li id="area_login" style="display:none;"><a href="javascript:void(0);"  class="open_login" style="cursor:pointer"><span><img src="<%=PATH_IMG %>/img_login.png" title="Login"></span><div data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></div></a></li>
                        <%-- Register --%>
                        <li  id="area_register" style="display:none;"><a href="javascript:void(0);" class="open_register" style="cursor:pointer"><span><img src="<%=PATH_IMG %>/img_register.png" title="Register"></span><div data-lang="FR000005"><%=LangMng.LANG_D("FR000005", session_lang) %></div></a></li>
                    </ul>
                </div>
            </div>
        </div> 
    </div>
    <div class="header_bot">
		<div class="gnb">
			<div class="btn_menu">
                <a href="<%=ConstUrl.APP_MAIN_URL%>" class="logo_sort"></a>
				<a href="#" class="open_mega"></a>
				<div class="pop_bg">
					<div class="mega_wrap" >
						<div class="in">
							<div class="mega_menu">
								<ul>
									<%
									  if(mega_main_list != null && mega_main_list.size() > 0){
										  for(int nMain=0; nMain<mega_main_list.size(); nMain++){
											    AppInfo mega_main = (AppInfo)mega_main_list.get(nMain);
											    String on = "";
											    if(nMain == 0){
											    	on = "class=\"on\"";
											    }
                                     %> 
									<li <%=on %>><a href="#" style="background:url(<%=UPLOAD_ROOT_PATH %><%=mega_main.getMain_img()%>) no-repeat 5px 50%; background-size:50px 50px;" data-lang="<%=mega_main.getMa_cd()%>"><%=mega_main.getMa_nm() %></a></li>
									<%    } %>
									<%} %>
								</ul>
							</div>
							<div class="mega_cont" >
								<%
                                 if(mega_main_list != null && mega_main_list.size() > 0){
                                     for(int nMain=0; nMain<mega_main_list.size(); nMain++){
                                     	AppInfo mega_main = (AppInfo)mega_main_list.get(nMain);
                                 %>
								<div class="item">
								    <ul>
									<% 
                                      if(mega_main.getSubAppList() != null && mega_main.getSubAppList().size() > 0){
                                      	for(int nSub=1; nSub<=mega_main.getSubAppList().size(); nSub++){
                                      		AppInfo mega_sub = (AppInfo)mega_main.getSubAppList().get(nSub-1);
                                                // 첫번째
                                                if(nSub ==1){
                                                %>
                                                <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')" data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="20" title="<%=mega_sub.getSa_nm() %>"><%=mega_sub.getSa_nm() %></a></li>
                                                <%
                                                // 마지막
                                                }else if(nSub == mega_main.getSubAppList().size()){
                                                %>
                                                <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')"  data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="20" title="<%=mega_sub.getSa_nm() %>"><%=mega_sub.getSa_nm() %></a></li>
                                                <%                                                      
                                                // 중간
                                                }else{
                                                    // 마지막 8번째
                                                    if(nSub % 7 == 0){
                                                   %>
                                                 <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')" data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="20" title="<%=mega_sub.getSa_nm() %>"><%=mega_sub.getSa_nm() %></a></li>
                                                   <%
                                                    // 중간
                                                    }else{
                                                   %>
                                                   <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')" data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="20" title="<%=mega_sub.getSa_nm() %>"><%=mega_sub.getSa_nm() %></a></li>
                                                   <%                                                           
                                                    }
                                                }
                                      		}
                                      }
                                     %>
                                     </ul>
								</div>
								<%    } %>
								<%} %>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="menu">
				<ul>
				    <%
				    String menu_search_on = "";
				    String menu_grade_on = "";
				    String menu_assem_on = "";
				    if(ConstUrl.SEARCH_URL.equals(menu_url)){
				    	menu_search_on = "class=\"on\"";
				    }else if(ConstUrl.GRADES_GUIDE_URL.equals(menu_url)){
				    	menu_grade_on = "class=\"on\"";
				    }else if(ConstUrl.ASSEMBLY_URL.equals(menu_url)){
				    	menu_assem_on = "class=\"on\"";
				    }
				    %>
				    <%-- Search Center --%>
					<li <%=menu_search_on %>><a href="<%=ConstUrl.SEARCH_URL%>" data-lang="FR000001"><%=LangMng.LANG_D("FR000001", session_lang) %></a></li>
					<%-- Grades Guide --%>
					<li <%=menu_grade_on %>><a href="<%=ConstUrl.GRADES_GUIDE_URL%>" data-lang="FR000002"><%=LangMng.LANG_D("FR000002", session_lang) %></a></li>
					<%-- My Assembly --%>
					<li <%=menu_assem_on %>><a id="menu_assembly"  onclick="goPageAssem()" data-lang="FR000003"><%=LangMng.LANG_D("FR000003", session_lang) %></a></li>
				</ul>
			</div>
            <div class="shortMenu">
                <ul>
                    <li class="grades"><a href="<%=ConstUrl.GRADES_GUIDE_URL%>" data-lang="FR000002" data-langtype="title" title="<%=LangMng.LANG_D("FR000002", session_lang) %>"></a></li>
                    <li class="myAssem"><a id="menu_assembly"  onclick="goPageAssem()" data-lang="FR000003" data-langtype="title" title="<%=LangMng.LANG_D("FR000003", session_lang) %>"><span class="count" id="assemCnt"><%=assemCnt %></span></a></li>
                </ul>
            </div>
         
			<div class="select_area" onclick="onSelectClick();">
                <div class="btn_short">
                    <ul>
                        <li>
                            <div class="short_search" >
                                <div class="input">
                                 	
                                    <%--Enter the model number or classification --%>
                                    <input type="text" value="" id="top_auto_input_text" placeholder="<%=LangMng.LANG_D("FR000006", session_lang) %>" data-lang="FR000006" data-langtype="placeholder" autoFocus/>
                                    <button class="btn_clear" onclick="$(this).prev().val('');">지우기</button>
                                    <button class="btn_search" onclick="doKeyWordTopSearch();" style="cursor:pointer" autocursor>검색</button> 
                                </div>
                                <div class="automatic"  id="top_automatic" style="display:none;height:300px;OVERFLOW-Y:auto;">
                                    <ul id="top_automatic_ul">
                                    </ul>
                                </div>
                            </div>
                            <a href="#" class="btn_short_search"><img src="/img/short_search.png" alt=""></a>
                        </li>
                        <li id="area_login_short" style="display:none;"><a href="javascript:void(0);" class="open_login" ><img src="<%=PATH_IMG %>/short_login.png" title="Login"></a></li>
                        <li id="area_mypage_short"  style="display:none;"><a href="javascript:void(0);" class="open_user" ><img src="<%=PATH_IMG %>/short_login.png" title="mypage"></li>
                        <li id="area_cart_short"  style="display:none;">
                            <a onclick="goCart();"  class="open_quickcart">
                                <img src="/img/short_cart.png" title="cart">
                                <em id="cartCnt_top2"><%=cartCnt %></em>
                            </a>
                        </li>
                    </ul>
                </div>
                <input type="hidden" name="btnNation" id="btnNation" value="<%=comm_nation %>" />
				<%-- 
				<div class="select">
                    <eaction:select name="btnNation" id="btnNation"  list="<%=nation_list%>" selected="<%=comm_nation %>" 
                    event="onchange=\"javascript:NationChange(this.value);\""></eaction:select>
				</div>
				--%>
				<div class="select">
					<eaction:select name="btnLang" id="btnLang"
						list="<%=lang_list%>"
						event="onchange=\"javascript:LangChange(this.value);\""></eaction:select>
				</div>
				<div class="select">
					<select name="btnSizeUnit" id="btnSizeUnit" onchange="javascript:SizeUnitChange(this.value);" >
					   <option value="CC0001">Metric</option>
					   <%if(ConstKey.KEY_Y.equals(inch_use_yn)){ %>
					   <option value="CC0002">Inch</option>
					   <%} %>
					</select>
				</div>
                <div class="select" >
                    <select  id="btnCurr" name="btnCurr" onchange="javascript:CurrencyChange(this.value);" ">
                        <option value="CC0077">USD</option>
                        <option value="CC0076">KRW</option>
                        <option value="CC0078">EUR</option>
                    </select>
                </div>
			</div>
		</div>
	</div>
</div>
<%} %>
<div id="m_header">
	<div class="inner">
		<div class="header_top">
			<div class="m_logo">
				<a href="<%=ConstUrl.APP_MAIN_URL%>"><img src="<%=PATH_IMG %>/m_logo.png" alt="KORLOY"></a>
			</div>
			<div class="m_menu">
				<a href="#" class="btn_fullmenu"><img
					src="<%=PATH_IMG %>/img_fullmenu.png" alt="모바일메뉴"></a>
			</div>
		</div>
		<div class="m_mega">
			<a href="#" class="open_m_mega" data-lang="FR000256"><%=LangMng.LANG_D("FR000256", session_lang) %></a>
			<div class="m_mega_wrap">
				<div class="in">
					<div class="mega_menu">
						<ul>
                        <%
                        if(mega_main_list != null && mega_main_list.size() > 0){
                            for(int nMain=0; nMain<mega_main_list.size(); nMain++){
                                AppInfo mega_main = (AppInfo)mega_main_list.get(nMain);
                         %>
                            <li style="background-image:url(<%=UPLOAD_ROOT_PATH %><%=mega_main.getMain_img()%>);background-size:30px">
                            <a href="#" data-lang="<%=mega_main.getMa_cd()%>"><%=mega_main.getMa_nm() %></a>
                                <div class="item">
                                    <div class="swiper-container">
                                        <div class="swiper-wrapper">
                                        <%
                                        if(mega_main.getSubAppList() != null && mega_main.getSubAppList().size() > 0){
                                        	for(int nSub=1; nSub<=mega_main.getSubAppList().size(); nSub++){
                                        		AppInfo mega_sub = (AppInfo)mega_main.getSubAppList().get(nSub-1);
                                               	// 첫번째
                                               	if(nSub ==1){
		                                        %>
                                                   <div class="swiper-slide"><ul><li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')" data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="30"><%=mega_sub.getSa_nm() %></a></li>
		                                        <%
                                               	// 마지막
                                               	}else if(nSub == mega_main.getSubAppList().size()){
		                                        %>
                                                   <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')"  data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="30"><%=mega_sub.getSa_nm() %></a></li></ul></div>
		                                        <%                                                		
                                               	// 중간
                                               	}else{
                                               		// 마지막 6번째
                                               		if(nSub % 6 == 0){
                                                   %>
                                                   <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')"  data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="30"><%=mega_sub.getSa_nm() %></a></li></ul></div><div class="swiper-slide"><ul>
                                                   <%
                                               		// 중간
                                               		}else{
                                                   %>
                                                   <li><a href="javascript:goMega('<%=mega_main.getMa_cd()%>', '<%=mega_sub.getSa_cd()%>')"  data-lang="<%=mega_sub.getSa_cd()%>" data-langsize="30"><%=mega_sub.getSa_nm()%></a></li>
                                                   <%                                                			
                                               		}
                                               	}
                                        	}
                                        }
                                        %>
                                        </div>
                                        <div class="swiper-pagination"></div>
                                    </div>
                                 </div>
                            </li>     
                         <%} %>
                         <%} %>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<form name="megaForm" id="megaForm" method="POST" action="<%=ConstUrl.APP_ITEM_GRP_LIST_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="ma_cd" id="ma_cd">
    <input type="hidden" name="sa_cd" id="sa_cd">
</form>
<%if(!ConstUrl.APP_MAIN_URL.equals(menu_url)){ %>
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
<%} %>
<script type="text/javascript">
jQuery(document).ready(function(){
/* 	$("#area_mypage").hide();
	$("#area_cart").hide();
	$("#area_login").hide();
	$("#area_register").hide();
	$("#area_login_short").hide();
	$("#area_mypage_short").hide();
	$("#area_cart_short").hide(); */
	$("#joinAuthConf").hide();
	$("#resetPwdAuth").hide();

	$(document).mouseup(function(e){
	     var megaLayer = $('.btn_menu');
	     if(megaLayer.has(e.target).length == 0){
	    	 $(".open_mega").removeClass("on");
	    	 $(".mega_wrap").slideUp();
	    	 $(".open_bg").fadeOut();
	     }
	 });
});
function goBack(){
	<%--
	history.go(-1);
	--%>
	<%if(!ConstUrl.APP_ITEM_GRP_LIST_URL.equals(menu_url)){%>
	history.go(-1);
	<%}else{%>
//     if(click_offset.length == 0){
//     	history.go(-1);
//     }else{
		float_move();
    	
// 		$.each(click_offset, function(index, data){
// 			float_move(data.type, data.cd);
// 			click_offset.splice(click_offset.length, 1);
// 		});
//     } 
	<%}%>
}
doTopButtonShowHide();
function doTopButtonShowHide(){
	if(USERINFO.user_id == ""){
	    $("#area_login").show();
	    $("#area_register").show();
		$("#area_mypage").hide();
		$("#area_cart").hide();
		$(".myAssem").hide();
		
	}else{
	    $("#area_login").hide();
        $("#area_register").hide();
	    $("#area_mypage").show();
	    $("#area_cart").show();
	    // 회원이름 셋팅 ([YJI] 21/09/24 )
        $("#area_user_name .user_name").text(USERINFO.user_name == "" ? USERINFO.user_id : USERINFO.user_name)
	}
}

var top_typingTimer;                //timer identifier
var top_doneTypingInterval = 350;  //time in ms, 5 second for example
var top_keyEvent;
$("#top_auto_input_text").keydown(function(event) {
	clearTimeout(top_typingTimer);
});
    
$("#top_auto_input_text").keyup(function(event) {
	top_keyEvent = event;
	clearTimeout(top_typingTimer);
	top_typingTimer = setTimeout(top_doneTyping, top_doneTypingInterval);
	event.preventDefault();
});



//user is "finished typing," do something
function top_doneTyping () {
	doTopAutoComplete();
}

<%if(ConstKey.KEY_Y.equals(inch_use_yn)){%>
$('#btnSizeUnit').val("<%=comm_size_unit%>").attr("selected", "selected")
<%}%>
$('#btnCurr').val("<%=comm_curr%>").attr("selected", "selected")
if($('#btnCurr').css('display') == 'none'){
    $('#btnCurr').show();
}
// 국가콤보 클릭
function onSelectClick(){
//	console.log('nation_cd click');
	if ( $('.open_mega').hasClass("on" )) {
        $('.open_mega').removeClass("on");
        $(".mega_wrap").slideUp();
        $(".open_bg").fadeOut();
	}

}








// 언어콤보 클릭
$('.nice-select').click(function(){
	if ( $('.open_mega').hasClass("on" )) {
        $(".mega_wrap").slideUp();
    }
});

$(document).mouseup(function(e){
    var autoLayer = $('#top_automatic');
    if(autoLayer.has(e.target).length == 0){
        $("#top_automatic_ul *").remove();
        $("#top_automatic").hide();
    }
});

function goMega(ma_cd, sa_cd){
	$("#megaForm #ma_cd").val(ma_cd);
    $("#megaForm #sa_cd").val(sa_cd);
    goPageTwo (megaForm, "");
}

function doTopAutoComplete(inputText){
	$("#top_automatic").show();
}
var top_auto_sel_index = -1;
function doTopAutoComplete(){
    if(top_keyEvent.which == 38 || top_keyEvent.which == 40 || top_keyEvent.which == 13){
        if ( $('#top_automatic').css('display') == 'block' ){
            // up
            if( top_keyEvent.which == 38){
/*                 top_auto_sel_index = top_auto_sel_index -1;
                if(top_auto_sel_index >= 0){
                    $(".auto_li").eq(top_auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(top_auto_sel_index+1).css("backgroundColor", "");
                }else{
                    top_auto_sel_index = $(".auto_li").length-1
                    $(".auto_li").eq(top_auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(0).css("backgroundColor", "");
                }
                event.preventDefalt(); */
            // down
            }else if( top_keyEvent.which == 40){
/*                 top_auto_sel_index = top_auto_sel_index +1;
                if(top_auto_sel_index < $(".auto_li").length){
                    $(".auto_li").eq(top_auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(top_auto_sel_index-1).css("backgroundColor", "");
                }else{
                    top_auto_sel_index = 0
                    $(".auto_li").eq(top_auto_sel_index).css("backgroundColor", "#eeeeee");
                    $(".auto_li").eq(top_auto_sel_index-1).css("backgroundColor", "");
                }
                event.preventDefalt(); */
            // Enter
            }else if(top_keyEvent.which == 13 && top_auto_sel_index >= 0){
                $(".auto_li").eq(top_auto_sel_index).children('a').trigger('click');
            }else if(top_keyEvent.which == 13 && top_auto_sel_index < 0){
            	doKeyWordTopSearch();
            }
        }else{
            // Enter
            if(top_keyEvent.which == 13){
            	doKeyWordTopSearch();
            }
        }
    }else{
        top_auto_sel_index = -1;

	    if($("#top_auto_input_text").val() != "" && $("#top_auto_input_text").val().length > 2){
	        var param = {
	                actionID :"<%=SearchConstKey.ACTION_SEARCH_AUTO%>",
	                auto_input_text : $("#top_auto_input_text").val(),
	            };
	        
	        $.ajax({
	            type: "POST",
	            url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
	            data: param,
	            dataType: "json",
	            async: true,
	            cache: false,
	            success: function(d){               
	                $("#top_automatic_ul *").remove();
	                if(Number(d.total_cnt) > 0){
	                    $("#top_automatic_ul").append(d.html);
	                    $("#top_automatic").show();
	                    top_auto_sel_index = -1;
	                }else{
	                    $("#top_automatic").hide();
	                }
	            }, error: function() {
	    
	            },complete : function(){

		        }
	        });
	    }else{
	        $("#top_automatic_ul *").remove();
	        $("#top_automatic").hide();
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
    $("#autoSearchForm #auto_complete_text").val(auto_complete_text);
    $("#autoSearchForm #auto_mode").val("A");
    doLog("EC0003", "", auto_complete_text);
    autoSearchForm.submit();
}
// cart화면으로 이동처리
function goCart(){
    var userId = USERINFO.user_id;
    if(userId == ""){
        //로그인창 오픈
        $(".open_login").click();
    }else{
        if(USERAUTH.isBasketOrderAuth){
            location.href = "<%=ConstUrl.BASKET_URL%>";
        }else{
            <%-- 권한이 없으면 승급 요청을 하세요. --%>
            alert(LANG_SET.FR000229);
        }
    }
}

function doKeyWordTopSearch(){
    var auto_input_text = $("#top_auto_input_text").val();
    if(auto_input_text != ""){
        $("#autoSearchForm #auto_input_text").val(auto_input_text);
        $("#autoSearchForm #ma_cd").val("");
        $("#autoSearchForm #sa_cd").val("");
        $("#autoSearchForm #ig_cd").val("");
        $("#autoSearchForm #matnr").val("");
        $("#autoSearchForm #auto_mode").val("M");
        doLog("EC0003", "", auto_input_text);
        autoSearchForm.submit();
    }
}

// Assembly 권한체크하여 이동. 비로그인시 로그인창 오픈
function goPageAssem(){

	if(USERAUTH.isAssemblyAuth){
		location.href = "<%=ConstUrl.ASSEMBLY_URL%>";
	}else{ 

		var userId = USERINFO.user_id;
		console.log("userId =" + userId);
		if(userId == ""){
			//로그인창 오픈
		  	$(".open_login").click();
		}else{
			<%-- 권한이 없으면 승급 요청을 하세요. --%>
			alert(LANG_SET.FR000229);
		}
	}
	

	
	
	// TEST용으로 권한체크 안함
<%-- 	if("<%=userInfo.getUser_id() %>"){
		$("#menu_assembly").attr("href", "<%=ConstUrl.ASSEMBLY_URL%>");	
	}else{
		$(".open_login").click();
	} --%>
	
}

</script>

<!-- add Script 211026 -->
<script>
$(function() {
	$(".nationPart .current").click(function() {
		$(this).toggleClass("on");
        $(".nationForm").slideToggle();
	});
});



$(function() {
	$(".btn_short_search").click(function() {
		 document.getElementById("top_auto_input_text").focus();
	});
});
</script>