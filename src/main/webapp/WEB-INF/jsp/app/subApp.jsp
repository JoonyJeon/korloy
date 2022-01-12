<%----------------------------------------------------------------------------------------
 - 파일이름 : inti/init.jsp
 - 설    명       : 첫화면
 - 추가내용     :   
 - 버전관리 : 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2019.05.10    1.0       HSI      신규작성
 -                          KSH
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
<%@page import = "com.eaction.framework.business.logic.app.model.AppSearchInfo"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@page import = "java.util.stream.Collectors"%>
<%@page import="net.sf.json.JSONObject"%>

<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
JSONObject json_tail = (JSONObject)request.getAttribute("json_tail");
AppInfo searchInfo = (AppInfo)request.getAttribute(ConstKey.SEARCH_DATA);
List<AppInfo> sub_app_list = (List<AppInfo>)request.getAttribute("sub_app_list");
List<AppInfo> main_app_list = (List<AppInfo>)request.getAttribute("main_app_list");
int sub_app_list_cnt = (int)request.getAttribute("sub_app_list_cnt");
String deviceType = (String)request.getAttribute("deviceType");
AppInfo selectMain = new AppInfo();
for(AppInfo mainInfo : main_app_list) {
	 if(searchInfo.getMa_cd().equals(mainInfo.getMa_cd())){
         selectMain = mainInfo;
     }
}
%>
<div id="container" class="sub">
    <div class="path">
        <div class="inner">
            <ul>
                <li class="home" id="mainApptitle" data-lang="<%=selectMain.getMa_cd() %>"><%=selectMain.getMa_nm() %></li>
                <%-- Sub Application --%>
                <li data-lang="FR000118"><%=LangMng.LANG_D("FR000118", session_lang) %></li>
            </ul>
        </div>
    </div>
    <div id="contents">
        <div class="bg">
            <div class="inner">
                <div class="mainapp_list">
                    <%-- Main Application --%>
                    <h3 data-lang="FR000081"><%=LangMng.LANG_D("FR000081", session_lang) %></h3>
                    <div class="swiper-container">
                        <div class="swiper-wrapper" id="main_app_list">
                        <%
                        int selMainIndex = 0;
                        if(main_app_list != null && main_app_list.size() > 0) {
                            for (int nMain=0; nMain<main_app_list.size(); nMain++) {
                            	AppInfo mainInfo = (AppInfo)main_app_list.get(nMain);
                                 String classOn = "";
                                 if(searchInfo.getMa_cd().equals(mainInfo.getMa_cd())){
                                     classOn = "class=\"on\"";
                                     selectMain = mainInfo;
                                     selMainIndex = nMain;
                                 }
                        %>
                            <div class="swiper-slide">
                                <a onclick="javascript:goSubApplication('<%=mainInfo.getMa_cd()%>')" id="ma_<%=mainInfo.getMa_cd()%>" data-macd="<%=mainInfo.getMa_cd()%>" data-manm="<%=mainInfo.getMa_nm()%>" data-subcnt="<%=mainInfo.getSub_cnt()%>" <%=classOn%>>
                                    <div class="img">
                                        <img src="<%=UPLOAD_ROOT_PATH %><%=mainInfo.getMain_img() %>" alt="<%=mainInfo.getMa_nm()%>" onerror="onErrorImage(this)">
                                    </div> <span data-lang="<%=mainInfo.getMa_cd()%>"><%=mainInfo.getMa_nm()%></span>
                                </a>
                            </div>
                        <%
                                }
                           }
                        %>
                        </div>
                    </div>
                    <div class="btn_prev">이전</div>
                    <div class="btn_next">다음</div>
                </div>
            </div>
        </div>
        <div class="listview">
            <div class="inner">
                <h2 id="mainAppCnt">
                </h2>
                <div class="view">
                    <div class="view_top">
                        <%-- Sub Application --%>
                        <div class="tit" id="selectMaNm" data-lang="FR000118"><%=LangMng.LANG_D("FR000118", session_lang) %><span> (<%=StringUtil.toComma(selectMain.getSub_cnt()) %>)</span></div>
                        <div class="subFuncBtns" style="display: none;">
                        	<span class="sfBtn">
                        		<a id="excelTBtn" data-lang="FR000257" onclick="doTailoredItemDown(this);"><%=LangMng.LANG_D("FR000257", session_lang) %></a>
                       		</span>
                       	</div>
                        <div class="btn_area">
                            <ul>
                                <li class="recommend"><select name="sub_app_sort" id="sub_app_sort" onchange="javascript:getSubApplication(false);">
                                        <%-- Recommended --%>
                                        <option value="SA_HIER.SEQ"  data-lang="FR000029"><%=LangMng.LANG_D("FR000029", session_lang) %></option>
                                        <%-- Name --%>
                                        <option value="SUB_APP.SA_NM"  data-lang="FR000117"><%=LangMng.LANG_D("FR000117", session_lang) %></option>
                                </select></li>
                                <li><a onclick="javascript:doChangeFormat('sub_text')"><img src="<%=PATH_IMG %>/ico_liststyle01.png"
                                        alt="텍스트로 보기"></a></li>
                                <li><a onclick="javascript:doChangeFormat('sub_big')" class="on"><img src="<%=PATH_IMG %>/ico_liststyle02.png"
                                        alt="큰썸네일로 보기"></a></li>
                                <li><a onclick="javascript:doChangeFormat('sub_small')"><img src="<%=PATH_IMG %>/ico_liststyle03.png"
                                        alt="작은썸네일"></a></li>
                            </ul>
                        </div>
                    </div>
                    <%if("P".equals(deviceType)){ %>
                    <div class="cont_wrap big" id="sub_big">
                        <div class="item_list">
	                        <ul id="sub_app_list">
	                        <%
	                           if(sub_app_list != null && sub_app_list.size() > 0) {
	                                for(AppInfo subInfo : sub_app_list) {
	                        %>
	                            <li><a onclick="javascript:goItemGroupList(this);" data-sacd="<%=subInfo.getSa_cd()%>" data-sanm="<%=subInfo.getSa_nm()%>">
                                        <div class="top">
                                            <div class="num"><%=StringUtil.toComma(subInfo.getSub_cnt())%></div>
                                        </div>
	                                    <div class="img" >
	                                        <img src="<%=UPLOAD_ROOT_PATH %><%=subInfo.getMain_img()%>" alt="<%=subInfo.getSa_nm()%>" onerror="onErrorImage(this)">
	                                    </div>
	                                    <div class="txt" ><p style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;" data-lang="<%=subInfo.getSa_cd()%>"><%=subInfo.getSa_nm()%></p></div>
	                            </a></li>
	                        <%    } %>
	                        <%} %>
	                        </ul>
                        </div>
                    </div>
                    <div class="cont_wrap small" id="sub_small">
						<div class="item_list">
						<ul id="sub_app_list2">
							<%
							if (sub_app_list != null && sub_app_list.size() > 0) {
							    for (AppInfo subInfo : sub_app_list) {
							%>
							<li>
								<a onclick="javascript:goItemGroupList(this);" data-sacd="<%=subInfo.getSa_cd()%>" data-sanm="<%=subInfo.getSa_nm()%>">
									<div class="top">
									   <div class="num"><%=StringUtil.toComma(subInfo.getSub_cnt())%></div>
									</div>
									<div class="img" >
									   <img src="<%=UPLOAD_ROOT_PATH%><%=subInfo.getMain_img()%>" onerror="onErrorImage(this)" >
									</div>
									<div class="txt">
									   <p data-lang="<%=subInfo.getSa_cd()%>" datalangsize="20" style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;"><%=subInfo.getSa_nm()%></p>
									</div>
								</a>
							</li>
							  <%
							   }
							}
							%>
						</ul>
						</div>
                    </div>
                    <div class="cont_wrap text"  id="sub_text">
                        <div class="listTable03">
                            <table>
                                <colgroup>
                                    <col width="80%">
                                    <col width="*%">
                                    <col width="*%">
                                </colgroup>
                                <thead>
                                          <tr>
                                              <%-- Sub Application --%>
                                              <th data-lang="FR000118"><%=LangMng.LANG_D("FR000118", session_lang) %></th>
                                              <%-- Quantity --%>
                                              <th data-lang="FR000230"><%=LangMng.LANG_D("FR000230", session_lang) %></th>
                                              <%-- Image --%>
                                              <th data-lang="FR000032"><%=LangMng.LANG_D("FR000032", session_lang) %></th>
                                          </tr>
                                </thead>
                                <tbody id="sub_app_list3">
                                    <%
                                    if (sub_app_list != null && sub_app_list.size() > 0) {
                                        for (AppInfo subInfo : sub_app_list) {
                                    %>
                                    <tr onclick="javascript:goItemGroupList(this);" data-sacd="<%=subInfo.getSa_cd()%>" data-sanm="<%=subInfo.getSa_nm()%>" style="cursor:pointer">
                                        <td class="left" data-lang="<%=subInfo.getSa_cd()%>"><%=subInfo.getSa_nm()%> </td>
                                        <td class="center" ><%=StringUtil.toComma(subInfo.getSub_cnt())%></td>
                                        <td>
                                            <div class="img_view"><img src="<%=UPLOAD_ROOT_PATH%><%=subInfo.getMain_img()%>" onerror="onErrorImage(this);" style="width:50px"></div>
                                        </td>
                                    </tr>
                                    <%
                                       }
                                    }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <%}else{ %>
                    <div class="cont_wrap big" id="sub_mobile">
                        <div class="item_list">
                            <ul id="sub_app_list4">
                            <%
                               if(sub_app_list != null && sub_app_list.size() > 0) {
                                    for(AppInfo subInfo : sub_app_list) {
                            %>
                                <li ><a onclick="javascript:goItemGroupList(this);" data-sacd="<%=subInfo.getSa_cd()%>" data-sanm="<%=subInfo.getSa_nm()%>">
                                        <div class="top">
                                            <div class="num"><%=StringUtil.toComma(subInfo.getSub_cnt())%></div>
                                        </div>
                                        <div class="img" style="width:100px;margin:10px 10px 10px 30px;">
                                            <img src="<%=UPLOAD_ROOT_PATH %><%=subInfo.getMain_img()%>" alt="<%=subInfo.getSa_nm()%>" style="width:80%" onerror="onErrorImage(this);onErrorImageMSize(this);">
                                        </div>
                                        <div class="txt" data-lang="<%=subInfo.getSa_cd()%>" data-langsize="10" style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;"><%=subInfo.getSa_nm()%></div>
                                </a></li>
                            <%    } %>
                            <%} %>
                            </ul>
                        </div>
                    </div>
                    <%} %>
                </div>
                <div class="more">
                    <%-- More --%>
                    <a href="javascript:getSubApplication(true)" class="btn_more" id="moreCount" data-lang="FR000034"></a>
                    <a href="javascript:getMoreAll();" class="btn_more" id="moreAll" data-lang="FR000249"></a>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="myForm" name="myForm" method="POST" action="<%=ConstUrl.APP_ITEM_GRP_LIST_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="ma_cd" id="ma_cd" value="<%=searchInfo.getMa_cd() %>" />
    <input type="hidden" name="sa_cd" id="sa_cd" />
    <input type="hidden" name="unit_cd" id="unit_cd" />
    <input type="hidden" name="orderSort" id="orderSort" value="<%=searchInfo.getOrderSort() %>"/>
    <input type="hidden" name="pageCount" id="pageCount" value="<%=searchInfo.getPageCount() %>"/>
    <input type="hidden" name="remainCount" id="remainCount" value="<%=sub_app_list_cnt %>"/>
    <input type="hidden" name="startIndex" id="startIndex" value="<%=searchInfo.getStartIndex() %>" />
    <input type="hidden" name="isPageYn" id="isPageYn" value="Y" />
    
</form>
<script type="text/javascript">
var selectMain = "<%=searchInfo.getMa_cd()%>";
var tail_info = JSON.parse(JSON.stringify(<%=json_tail.toString()%>));
var curr_lang_cd = "<%= session_lang %>";

jQuery(document).ready(function(){
	mainSwiper.slideTo(<%=selMainIndex%>);
    setMoreCount("<%=sub_app_list_cnt%>");
    $('.loading').delay('1000').fadeOut();

	setTailoredItemBtn();
});

// 상단 언어 변경시  Tailored Item 파일 정보 변경
$(document).on("change","#btnLang", function() {
	curr_lang_cd = $(this).val()
	setTailoredItemBtn();
});

function onErrorImageSize(obj){
	$(obj).attr('style', 'width:50px');
}

function onErrorImageMSize(obj){
    $(obj).attr('style', 'width:100%');
}

function doChangeFormat(format){
    if(format == "sub_big"){
        $('#sub_big').show();
        $('#sub_small').hide();
        $('#sub_text').hide();
    }else if(format == "sub_small"){
        $('#sub_big').hide();
        $('#sub_small').show();
        $('#sub_text').hide();
    }else{
        $('#sub_big').hide();
        $('#sub_small').hide();
        $('#sub_text').show();
    }
}

function goItemGroupList(obj){
    var sa_cd = $(obj).data("sacd");
    var sa_nm = $(obj).data("sanm");
    doLog("EC0014", sa_nm, ""); 
    $("#myForm #sa_cd").val(sa_cd);
    $("#myForm #unit_cd").val($("#btnSizeUnit").val());
    goPageTwo (myForm, "");
}

function goSubApplication(ma_cd){
    myForm.action = "<%=ConstUrl.APP_SUB_URL%>";
    $("#myForm #ma_cd").val(ma_cd);
    $("#myForm #unit_cd").val($("#btnSizeUnit").val());
    $("#myForm").attr("action", "<%=ConstUrl.APP_SUB_URL%>").submit();
}

function getMainApplication(){
    var param = {
            actionID :"<%=AppConstKey.ACTION_MAIN_APP_LIST%>",
            ma_cd : $("#myForm #ma_cd").val(),
            startIndex : $("#myForm #startIndex").val(),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val()
        };
    
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.APP_SUB_URL%>?v=" + (new Date().getTime()),
        data: param, 
        dataType: "json",
        async: false,
        cache: false,
        success: function(d){
            var content =  "";
            var selectMainName = "";
            var selectMainCnt = "";
            $.each(d, function(index, data) {
                var ma_cd = data.ma_cd;
                var ma_nm = data.ma_nm;
                var sub_cnt = data.sub_cnt;
                var img_url = "<%=UPLOAD_ROOT_PATH %>"+data.main_img;
                var class_on = "";
                if(data.ma_cd == $("#myForm #ma_cd").val()){
                    class_on = 'class="on"';
                    selectMainName = ma_nm;
                    selectMainCnt = sub_cnt;
                }
                content = '<div class="swiper-slide" >'+
                '<a onclick="javascript:changeMainApp(this)" style="cursor:pointer;" '+class_on+' data-subcnt="'+sub_cnt+'" data-manm="'+ma_nm+'" data-macd="'+ma_cd+'" id="ma_'+ma_cd+'">' +
                '<div class="img" >'+
                '<img src="'+img_url+'" alt="'+ma_nm+'" onerror="onErrorImage(this)" />'+
                '</div> <span>'+ma_nm+'</span>'+
                '</a>'+
                '</div>';
                $("#main_app").append(content).trigger("create");
            });
            $("#mainApptitle").text(selectMainName);
            $("#mainAppCnt").text(LANGSET["FR000118"] + '('+selectMainCnt+')');
            $("#selectMaNm").text(selectMainName);
        }, error: function() {

        }
    });
}

function getList(param, isRemove){
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.APP_SUB_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function(d){
            var content = "";
            var content2 = "";
            var content3 = "";
            var content4 = "";
            $.each(d.list, function(index, data) {
                var sa_cd = data.sa_cd;
                var sa_nm = data.sa_nm;
                var sub_cnt = data.sub_cnt;
                var img_url = "<%=UPLOAD_ROOT_PATH %>"+data.main_img;
                content =  content + '<li><a href="#" onclick="javascript:goItemGroupList(this);" data-sacd="'+sa_cd+'" data-sanm="'+sa_nm+'">'+
                '<div class="top">' +
                '<div class="num">'+addCommas(sub_cnt)+'</div>'+
                '</div>'+
                '<div class="img" >'+
                '<img src="'+img_url+'" alt="'+toTruncate(sa_nm, 30, "...")+'" onerror="onErrorImage(this)" />'+
                '</div>'+
                '<div class="txt"><p style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;" data-lang="'+sa_cd+'">'+sa_nm+'</span></p></div>'+
                '</a></li>';

                content2 =  content2 + '<li>'+
                '<a onclick="javascript:goItemGroupList(this);" data-sacd="'+sa_cd+'" data-sanm="'+sa_nm+'">'+
                '<div class="top">'+
                '<div class="num">'+addCommas(sub_cnt)+'</div>'+
                '</div>'+
                '<div class="img" >' +
                '<img src="'+img_url+'" onerror="onErrorImage(this)">'+
                '</div>'+
                '<div class="txt" >'+
                '<p style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;" data-lang="'+sa_cd+'">'+sa_nm+'</p>'+
                '</div>'+
                '</a>'+
                '</i>';

                content3 =  content3 + '<tr onclick="javascript:goItemGroupList(this);" data-sacd="'+sa_cd+'" data-sanm="'+sa_nm+'" style="cursor:pointer">'+
                '<td class="left" data-lang="'+sa_cd+'">'+sa_nm+'</td>'+
                '<td class="center" >'+addCommas(sub_cnt)+'</td>'+
                '<td>'+
                '<div class="img_view"><img src="'+img_url+'" onerror="onErrorImage(this);" style="width:50px"></div>'+
                '</td>'+
                '</tr>';

                content4 =  content4 + '<li ><a onclick="javascript:goItemGroupList(this);" data-sacd="'+sa_cd+'" data-sanm="'+sa_nm+'" >'+
                '<div class="img" >'+
                '<img src="'+img_url+'" alt="'+sa_nm+'" style="width:80%" onerror="onErrorImage(this);">'+
                '</div>'+
                '<div class="txt" data-lang="'+sa_cd+'" data-langsize="10" style="text-overflow:ellipsis;overflow:hidden;white-space: nowrap;">'+sa_nm+'</div>'+
                '</a>'+
                '</i>';
            });
            if(isRemove){
                $("#sub_app_list *").remove();
                $("#sub_app_list2 *").remove();
                $("#sub_app_list3 *").remove();
                $("#sub_app_list4 *").remove();
            }
            $("#sub_app_list").append(content).trigger("create");
            $("#sub_app_list2").append(content2).trigger("create");
            $("#sub_app_list3").append(content3).trigger("create");
            $("#sub_app_list4").append(content4).trigger("create");
            
            setMoreCount(d.total_cnt);
        }, error: function() {

        },
        complete:function(){
            $('#loadbar').fadeOut();
        }
    });
}
function getMoreAll(){

    $("#myForm #startIndex").val("0");
    var sort = $("#sub_app_sort  option:selected").val();
    $("#myForm #orderSort").val(sort);
    $("#myForm #isPageYn").val("N");
    $("#myForm #remainCount").val("0");
    $("#myForm #pageCount").val("99");
    var param = {
            actionID :"<%=AppConstKey.ACTION_SUB_APP_LIST%>",
            ma_cd : $("#myForm #ma_cd").val(),
            startIndex : $("#myForm #startIndex").val(),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn : $("#myForm #isPageYn").val()
            };
    getList(param, true);
}
function getSubApplication(isMore){
    if(!isMore){
        $("#myForm #startIndex").val("0");
    }else{
        var startIndex = Number($("#myForm #startIndex").val()) +1;
        $("#myForm #startIndex").val(startIndex);
    }
    var sort = $("#sub_app_sort  option:selected").val();
    $("#myForm #orderSort").val(sort);
    
    var param = {
            actionID :"<%=AppConstKey.ACTION_SUB_APP_LIST%>",
            ma_cd : $("#myForm #ma_cd").val(),
            startIndex : $("#myForm #startIndex").val(),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn : $("#myForm #isPageYn").val()
            };
    
    if(!isMore){
        getList(param, true);
    }else{
    	getList(param, false);
    }
}


function setMoreCount(total_cnt){
	console.log("total_cnt = " + total_cnt);
	console.log("pageCount = " + $("#myForm #pageCount").val());
    var startIndex = Number($("#myForm #startIndex").val())+ 1;
    var pageCount = Number($("#myForm #pageCount").val()) ;
    var remainCount = Number(total_cnt) - (startIndex * pageCount);
    console.log("remainCount = " + remainCount);
    if(remainCount <= 0){
        $("#myForm #remainCount").val("0");
        $("#moreCount").html("");
        $("#moreAll").html("");
        jQuery("#moreAll").css("display", "none");
        jQuery("#moreCount").css("display", "none");
    }else{
        $("#myForm #remainCount").val(remainCount);
        $("#moreCount").html(LANG_SET["FR000034"]+'<span>('+ addCommas(remainCount) +')<span>');
        $("#moreAll").html(LANG_SET["FR000249"]+'<span>('+ addCommas(remainCount) +')<span>');
        jQuery("#moreAll").css("display", "block");
        jQuery("#moreCount").css("display", "block");
    }
}

function changeMainApp(obj){
    // class="on/off" 처리
    var sel_ma_cd = $(obj).data('macd');
    var sel_ma_nm = $(obj).data('manm');
    var sel_sub_cnt = $(obj).data('subcnt');
    var cur_ma_cd = $("#myForm #ma_cd").val();

    if(sel_ma_cd != cur_ma_cd){
        $("#ma_"+cur_ma_cd).removeAttr('class');
        $(obj).attr('class', 'on');
        $("#myForm #ma_cd").val(sel_ma_cd);

        $("#mainApptitle").text(sel_ma_nm);
        $("#mainAppCnt").text('Sub Application ('+sel_sub_cnt+')');
        $("#selectMaNm").text(sel_ma_nm);
        
        getSubApplication(false);
    }
}
function doTailoredItemDown(obj){
    if(USERINFO.user_id == ""){
        $(".open_login").click();
    }else{
        if(USERAUTH.isTailoredAuth){
        	if (Object.keys(tail_info).length != 0 && curr_lang_cd in tail_info) {
        		for (var key in tail_info) {
        			if (key == curr_lang_cd) {
			        	$(obj).attr("href","<%=UPLOAD_ROOT_PATH%>" + tail_info[key]);
			        	$(obj).data("down","<%=UPLOAD_ROOT_PATH%>" + tail_info[key]);
        			}
        		}
        	}
        }else{
            // 승급요청을 하세요
            alert(LANG_SET["FR000229"]);
        }
    }
}

function setTailoredItemBtn() {
	//Tailored Item 파일 정보가 없으면 숨김
	if (Object.keys(tail_info).length != 0 && curr_lang_cd in tail_info) {
		for (var key in tail_info) {
			if (key == curr_lang_cd) {
				$('.subFuncBtns').show();
			}
		}
	} else {
		$('.subFuncBtns').hide();
	}
}
</script>
