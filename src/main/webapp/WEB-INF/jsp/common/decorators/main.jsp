<%----------------------------------------------------------------------------------------
* @program		:	main.jsp
* @description	:	sitemesh를 사용한 메인 Page
* @author		:	LYS
* @create		:	2018/11/09
* @update		:	
* @version		:	1.0
* @descripiton	:	
------------------------------------------------------------------------------------------%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib uri = "http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri = "http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file = "/WEB-INF/jsp/common/include.inc" %>
<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<eaction:html>

	<body onbeforeunload="HandleBackFunctionality()">
<eaction:script  />
<script >
var LANG_SET =  JSON.parse(JSON.stringify(<%=lang_code%>));
var USERINFO= JSON.parse(JSON.stringify(<%=json_user%>));
var main_combo = JSON.parse(JSON.stringify(<%=main_combo%>));
var USERAUTH = JSON.parse(JSON.stringify(<%=json_auth%>));
var curr_lang = "<%= session_lang %>";
</script>
    <!-- 210928 cookie 추가 S //-->
    <script>
    function HandleBackFunctionality()  
    {  
    	$('.loading').delay('1000').fadeOut();
    } 
    $(document).ready(function () {
        //동의 이벤트
        $(".btnAgree").click(function(e) {
            e.preventDefault();
             $(".allowCookie").hide();

             setCookiePopup("allowCookie","N", 30);
        })

        $( document ).ready(function() {
            if (getCookiePopup("allowCookie")=="N")         {
                $(".allowCookie").hide();
            } else {
                $(".allowCookie").show();

            }
        })


        $(window).on("scroll", function(){
            if (getCookiePopup("allowCookie")=="N") {
				
            } else {
                if($(window).scrollTop() >= 106 ) {
                    $(".allowCookie").addClass("allowCookie-scrolled");
                } else if ($(window).scrollTop() < 106 ) {
                    $(".allowCookie").removeClass("allowCookie-scrolled");
                }
            }
        }); 
    });

       function getCookiePopup( name ) {
           var nameOfCookie = name + "=";
           var x = 0;
           while ( x <= document.cookie.length ) {
               var y = (x+nameOfCookie.length);

               if ( document.cookie.substring( x, y ) == nameOfCookie ) {
                   if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                       endOfCookie = document.cookie.length;

                   return unescape( document.cookie.substring( y, endOfCookie ) );
               }
               x = document.cookie.indexOf( " ", x ) + 1;
               if ( x == 0 )
                   break;
           }
           return "";
       }
       function setCookiePopup( name, value, expiredays ){
           var todayDate = new Date();
           todayDate.setDate( todayDate.getDate() + expiredays );
           document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
       }

    </script>
    <!-- 210928 cookie 추가 E //-->
	<style>
	a { cursor: pointer; }

	html,body {
		margin: 0;
		height: 100%;
	}
	
	#wrap {
		min-height: 100%;
		position: relative;
	}
	
	#footer {
		position: absolute;
		bottom: 0;
		left: 0;
		right: 0;
		color: white;
		background-color: #333333;
	}
	
	#footer .inner {
		max-width: 1280px;
		margin: 0 auto;
	}
	
	#footer .left {
		float: left;
		font-size: 13px;
		color: #fff;
		width: auto;
	}
	
	#footer .right {
		float: right;
		text-align: right;
		font-size: 13px;
		color: #fff;
		width: auto;
	}

	@media only screen and (max-width: 761px) {
		#footer .left {
			float: left;
			margin-left: 4px;
			margin-bottom: 0px;
			width: auto;
		}
	
		#footer .right {
			float: right;
			margin-right: 4px;
			margin-bottom: 0px;
			width: auto;
		}
	}
	
	@media only screen and (max-width: 461px) {
		#footer .left {
			float: left;
			margin-left: 4px;
			margin-bottom: 0px;
			width: auto;
		}
	
		#footer .right {
			float: right;
			margin-right: 4px;
			margin-bottom: 0px;
			width: auto;
		}
	}
    </style>
        <div class="allowCookie">
            <div class="inner" data-lang="FR000314">
                <%=LangMng.LANG_D("FR000314", session_lang) %>
                <span class="btnAgree" data-lang="FR000315"><%=LangMng.LANG_D("FR000315", session_lang) %></span>
                <span class="detailCookie"><a href="/privacy.do" data-lang="FR000316"><%=LangMng.LANG_D("FR000316", session_lang) %></a></span>
            </div>
        </div>
        <div class="loading" id="loadbar"><div class="loader"></div><span>Loading</span></div>
        
		<div id="wrap">
		<page:applyDecorator name="panel" page="<%=ConstUrl.TOP_URL %>"/>
		<decorator:body />
 		<page:applyDecorator name="panel" page="<%=ConstUrl.FOOTER_URL %>"/>
		<page:applyDecorator name="panel" page="<%=ConstUrl.RIGHT_WRAP_URL %>"/>
        </div>

		<div class="open_privacy_pop" style="z-index:9999">
			<div class="in">
                <a class="close_pop"><img onclick="javascript:hidePrivacyPop();" style="width:auto;" src="/img/btn_close.png" alt="닫기"></a>
                <div class="pop_cont">
                    <div id="privacy_area" class="privacy" align="left" style="padding: 50px 0;line-height: 150%;">
                    <%=StringEscapeUtils.unescapeHtml3(PRIVACY_INFO) %>
                        <%--
                        <h2 style="font-size: 16px;color: #333;padding-bottom: 20px;font-weight: 400;">개인정보처리방침</h2>
                        한국야금(주)('www.korloy.com'이하 'KORLOY')은(는) 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
                        <br /><br />
                        한국야금(주)('KORLOY') 은(는) 회사는 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다.
                        <br /><br />
                        ○ 본 방침은부터 2018년 6월 5일부터 시행됩니다.
                        <br /><br />
                        1. 개인정보의 처리 목적 한국야금(주)('www.korloy.com'이하 'KORLOY')은(는) 개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전동의를 구할 예정입니다. 
                        <br /><br />
                        가. 마케팅 및 광고에의 활용
                        <br /><br />
                        신규 서비스(제품) 개발 및 맞춤 서비스 제공 등을 목적으로 개인정보를 처리합니다.
                        <br /><br />
                        나. 기타
                        <br /><br />
                        사내 교육 신청 등을 목적으로 개인정보를 처리합니다.
                        <br /><br />
                        2. 개인정보 파일 현황
                        <br /><br />
                        1. 개인정보 파일명 : 기술 강좌 신청서<br />
                         - 개인정보 항목 : 이메일, 휴대전화번호, 이름, 회사전화번호, 직책, 부서, 회사명, 쿠키<br />
                         - 수집방법 : 서면양식<br />
                         - 보유근거 : 피 교육자 정보 확인<br />
                         - 보유기간 : 1년<br />
                         - 관련법령 : 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년<br /><br />

                        3. 개인정보의 처리 및 보유 기간<br /><br />

                        ① 한국야금(주)('KORLOY')은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의 받은 개인정보 보유,이용기간 내에서 개인정보를 처리,보유합니다.<br /><br />

                        ② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.<br /><br />

                        4. 개인정보의 제3자 제공에 관한 사항<br /><br />

                        ① 한국야금(주)('www.korloy.com'이하 'KORLOY')은(는) 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.<br /><br />

                        ② 한국야금(주)('www.korloy.com')은(는) 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.<br /><br />

                        (현재 코오로이는 개인정보를 제 3자에게 제공하고 있지 않습니다.)<br /><br />

                        - 개인정보를 제공받는 자 : 이씨플라자(주)<br />
                        - 제공받는 자의 개인정보 이용목적 : 코오로이 홈페이지 정보전산 처리 및 유지관리<br />
                        - 제공받는 자의 보유.이용기간 : 위탁 계약 종료 시까지<br /><br />

                        5. 개인정보처리 위탁<br /><br />

                        ① 한국야금(주)('KORLOY')은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.<br /><br />

                        - 위탁받는 자 (수탁자) : 이씨플라자(주)<br />
                        - 위탁하는 업무의 내용 : 코오로이 홈페이지 정보전산 처리 및 유지관리<br />
                        - 위탁기간 : 위탁 계약 종료 시까지<br /><br />

                        ② 한국야금(주)('www.korloy.com'이하 'KORLOY')은(는) 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.<br /><br />

                        ③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.<br /><br />

                        6. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다.<br /><br />

                        ① 정보주체는 한국야금(주)에 대해 언제든지 개인정보 열람,정정,삭제,처리정지 요구 등의 권리를 행사할 수 있습니다.<br />
                        ② 제1항에 따른 권리 행사는한국야금(주)에 대해 개인정보 보호법 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 한국야금(주)은(는) 이에 대해 지체 없이 조치하겠습니다.<br />
                        ③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.<br />
                        ④ 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.<br />
                        ⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.<br />
                        ⑥ 한국야금(주)은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.<br /><br />

                        7. 처리하는 개인정보의 항목 작성<br /><br />

                        ① 한국야금(주)('www.korloy.com'이하 'KORLOY')은(는) 다음의 개인정보 항목을 처리하고 있습니다.<br /><br />

                        8. 개인정보의 파기 한국야금(주)('KORLOY')은(는) 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.<br /><br />

                        -파기절차<br />
                        이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.<br /><br />

                        -파기기한<br />
                        이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.<br /><br />

                        -파기방법<br />
                        전자적 파일 형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다.<br /><br />
                        종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.<br /><br />

                        9. 개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항<br /><br />

                        ① 한국야금(주) 은 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠기(cookie)’를 사용합니다. ② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다. 가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다. 나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다. 다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.<br /><br />

                        10. 개인정보 보호책임자 작성<br /><br />

                        ① 한국야금(주)(‘www.korloy.com’이하 ‘KORLOY) 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.<br /><br />

                        ▶ 개인정보 보호책임자<br />
                        성명 : 홍정원<br />
                        직책 : 팀원<br />
                        직급 : 대리<br />
                        연락처 : 02-3475-9962, jwhong@korloy.com, 02-525-0254<br />
                        ※ 개인정보 보호 담당부서로 연결됩니다.<br /><br />

                        ▶ 개인정보 보호 담당부서<br /><br />
                        부서명 : 홍보실<br />
                        담당자 : 홍정원<br />
                        연락처 : 02-3475-9962, 02-525-0254<br /><br />

                        ② 정보주체께서는 한국야금(주)(‘www.korloy.com’이하 ‘KORLOY) 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 한국야금(주)(‘www.korloy.com’이하 ‘KORLOY) 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.<br /><br />

                        11. 개인정보 처리방침 변경<br /><br />

                        ①이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.<br /><br />

                        12. 개인정보의 안전성 확보 조치 한국야금(주)('KORLOY')은(는) 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.<br /><br />

                        1. 정기적인 자체 감사 실시<br />
                        개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.<br /><br />

                        2. 내부관리계획의 수립 및 시행<br />
                        개인정보의 안전한 처리를 위하여 내부관리계획을 수립하고 시행하고 있습니다.<br /><br />

                        3. 해킹 등에 대비한 기술적 대책<br />
                        한국야금(주)('KORLOY')은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.<br /><br />

                        4. 접속기록의 보관 및 위변조 방지<br />
                        개인정보처리시스템에 접속한 기록을 최소 6개월 이상 보관, 관리하고 있으며, 접속 기록이 위변조 및 도난, 분실되지 않도록 보안기능 사용하고 있습니다.
                        --%>
                    </div>
                </div>
			</div>
		</div>
<script>
var LANG_COMBO;
var NATION_COMBO;
var PAGE_LANG_CODE = new Array();
var LANG_OBJ = new Array();
jQuery(document).ready(function(){	
    var obj = document.querySelectorAll('[data-lang]');
    for( var i = 0; i < obj.length; i++ ){
        var item = obj.item(i);        
        var lang_cd = $(item).data('lang');
        LANG_OBJ.push(item);
    }
    doAfterLogin(main_combo, true);
    $("#privacy_area").html();
    if(curr_lang == 'ZHO'){
      	$("body,h1,h2,h3,h4,input,button,input,select,button,textarea").css("font-family","'Tahoma', 'sans-serif','Noto Sans SC', serif ");
      }
});

function doAfterLogin(data, isInit){
/*     $("#area_mypage").hide();
    $("#area_cart").hide();
    $("#area_login").hide();
    $("#area_register").hide(); */
    $("#area_login_short").hide();
    $("#area_mypage_short").hide();
    $("#area_cart_short").hide();
    $("#joinAuthConf").hide();
    $("#resetPwdAuth").hide();

    if(data.SESSION_LANG != ""){
        $('#btnLang option[value="'+data.SESSION_LANG+'"]').attr('selected','selected');
        if(!isInit){
            $('#btnLang').change();
        }
    }
    $(".select_area select").niceSelect('update');
    if(data.SESSION_SIZE_UNIT != ""){
        $('#btnSizeUnit option[value="'+data.SESSION_SIZE_UNIT+'"]').attr('selected','selected');
        if(!isInit){
            $('#btnSizeUnit').change();
        }
    }
    $(".select_area select").niceSelect('update');
    if(data.SESSION_CURRENCY != ""){
        $('#btnCurr option[value="'+data.SESSION_CURRENCY+'"]').attr('selected','selected');
        if(!isInit){
            $('#btnCurr').change();
        }
    }
    
	if(USERINFO.user_id == ""){
	    $("#area_login_short").show();
	    $("#joinAuthConf").show();
	}else{
		$(".myAssem").show();
	    $("#area_mypage_short").show();
	    $("#area_cart_short").show();
	    $("#resetPwdAuth").show();
	    // 카트 카운트 셋팅
	    if(typeof data.cartCnt !="undefined"){
	        $("#cartCnt_top").text(data.cartCnt);
	        $("#cartCnt_top2").text(data.cartCnt);
	        // ↙ Mobile
	        $("#cartCnt_rgt_wrap").text(data.cartCnt);
		}
		if(typeof data.assemCnt !="undefined"){
		    $("#assemCnt").text(data.assemCnt);
		}
	    
	    //Mypage 셋팅
	    $("#mPage_user_id").val(USERINFO.user_id);
	    $("#mPage_user_name").val(USERINFO.user_name);
	    $("#mPage_user_company").val(USERINFO.user_company);
	    $("#mPage_user_com_mail").val(USERINFO.user_com_mail);
	    $("#mPage_user_com_tel").val(USERINFO.user_com_tel);
	    $("#mPage_user_com_addr").val(USERINFO.user_com_addr);
	    $("#mPage_user_mobile").val(USERINFO.user_mobile);

	    $('#mPage_user_nation option[value="'+USERINFO.user_nation+'"]').attr('selected','selected');
	    $('#mPage_user_lang option[value="'+USERINFO.user_lang+'"]').attr('selected','selected');
	    $('#mPage_unit_cd option[value="'+USERINFO.unit_cd+'"]').attr('selected','selected');
	    $('#mPage_curr_cd option[value="'+USERINFO.curr_cd+'"]').attr('selected','selected');

	    $(".select_area select").niceSelect('update');

	    $(".login_wrap .in .pop_close").click();

		//승급페이지 셋팅
		$("#ug_user_nation").val(USERINFO.user_nation);
		$("#ug_user_id").val(USERINFO.user_id);
		$("#ug_user_company").val(USERINFO.user_company);
		$("#ug_user_com_mail").val(USERINFO.user_com_mail);
		if(USERINFO.ug_emp == ""){
	        $("#ug_ug_emp").attr("placeholder", LANG_SET.FR000225);
	        $("#ug_ug_emp").attr("data-lang", LANG_SET.FR000225);
	        $("#ug_ug_emp").attr("data-langtype", "placeholder");
		}else{
	        $("#ug_ug_emp").val(USERINFO.ug_emp);
		}
	}
}

function hidePrivacyPop(){
    $(".open_privacy_pop").hide();
    return; 
}

function showPrivacyPop(){
	doLog("EC0038", "", "");
    $(".open_privacy_pop").fadeIn("fast");
//    $(".open_privacy_pop .in > div").hide();
    $(".privacy_pop_cont").fadeIn();
    return; 
}

function showPrivacyPop(){
    
    $(".open_privacy_pop").fadeIn("fast");
//    $(".open_privacy_pop .in > div").hide();
    $(".privacy_pop_cont").fadeIn();
    return; 
}

function doSelectTextChange(){
	var select_html = "";
	var select_val = $('#btnLang option:selected').val();
	select_html = "";
	for(var key in LANG_COMBO) {
        var selected = "";
        if(key == select_val){
            select_html = select_html + '<option value="'+key+'" selected>'+LANG_COMBO[key]+'</option>';
        }else{
            select_html = select_html + '<option value="'+key+'" >'+LANG_COMBO[key]+'</option>';
        }
	}
/*     $('#btnLang option').each(function() {
        //console.log("text==" + LANG_COMBO[this.value]);
        //console.log("value==" + this.value);
        var selected = "";
        if(this.value == select_val){
            select_html = select_html + '<option value="'+this.value+'" selected>'+LANG_COMBO[this.value]+'</option>';
        }else{
            select_html = select_html + '<option value="'+this.value+'" >'+LANG_COMBO[this.value]+'</option>';
        }
    });
 */
    $("#btnLang").empty();
    $("#btnLang").html(select_html);
    
    $(".select_area select").niceSelect('update');

    $(".fullmenu_list .select_area select").niceSelect('update');

    // MyPage 국가, 언어 셋팅
    // 현재 선택된 국가
    var selMypageNation = $('#mPage_user_nation option:selected').val();
    select_html = "";
    for(var key in NATION_COMBO) {
        var selected = "";
        if(key == selMypageNation){
            select_html = select_html + '<option value="'+key+'" selected>'+NATION_COMBO[key]+'</option>';
        }else{
            select_html = select_html + '<option value="'+key+'" >'+NATION_COMBO[key]+'</option>';
        }
    }
/*     $('#mPage_user_nation option').each(function() {
        var selected = "";
        if(this.value == ""){
        	select_html = select_html + '<option value=""></option>';
        }else if(this.value == selMypageNation){
            select_html = select_html + '<option value="'+this.value+'" selected>'+NATION_COMBO[this.value]+'</option>';
        }else{
            select_html = select_html + '<option value="'+this.value+'" >'+NATION_COMBO[this.value]+'</option>';
        }
    }); */
    $("#ug_user_nation").empty();
    $("#ug_user_nation").html(select_html);
    
    // 현재 선택된 언어
    var selMypageLang = $('#mPage_user_lang option:selected').val();
    select_html = "";
    for(var key in LANG_COMBO) {
        var selected = "";
        if(key == selMypageLang){
            select_html = select_html + '<option value="'+key+'" selected>'+LANG_COMBO[key]+'</option>';
        }else{
            select_html = select_html + '<option value="'+key+'" >'+LANG_COMBO[key]+'</option>';
        }
    }
    
/*     $('#mPage_user_lang option').each(function() {
        var selected = "";
        if(this.value == ""){
            select_html = select_html + '<option value=""></option>';
        }else if(this.value == selMypageLang){
            select_html = select_html + '<option value="'+this.value+'" selected>'+LANG_COMBO[this.value]+'</option>';
        }else{
            select_html = select_html + '<option value="'+this.value+'" >'+LANG_COMBO[this.value]+'</option>';
        }
    }); */
    $("#mPage_user_lang").empty();
    $("#mPage_user_lang").html(select_html);
    $("#ug_user_lang").empty();
    $("#ug_user_lang").html(select_html);

    // [211027_yji 국가 select 검색] 
    var selMypageNation_search = $('#mPage_user_nation').val();
    select_html = "";
    select_html = "";
    for(var key in NATION_COMBO) {
        var selected = "";
        if(key == selMypageNation_search){
            select_html = select_html +  '<li><a title="'+NATION_COMBO[key]+'" data-schntn="'+key+'">'+NATION_COMBO[key]+'</a></li>'
        }else{
            select_html = select_html +  '<li><a title="'+NATION_COMBO[key]+'" data-schntn="'+key+'">'+NATION_COMBO[key]+'</a></li>'
        }
    }
    $("#mypage-form .nationForm .nationResult").empty();
    $("#mypage-form .nationForm .nationResult").html(select_html);
    if ($('#order_user_nation_v').length != 0) $('#order_user_nation_v').val(NATION_COMBO[USERINFO.user_nation]);
}

function doSelectTextSort(){

  	for(var i=0; i<$("#btnLang").length; i++){
	    
	    //여러개의 셀렉트 박스가 존재하기 때문에 $('.select').eq(i) 로 하나씩 가져온다.
	    var oOptionList = $("#btnLang").eq(i).find('option');
	     
	    oOptionList.sort(function(a, b){
	        if (a.text > b.text) return 1;
	        else if (a.text < b.text) return -1;
	    });
	 
	    $("#btnLang").eq(i).html(oOptionList);
	}
 
	for(var i=0; i<$("#mPage_user_nation").length; i++){
        
        //여러개의 셀렉트 박스가 존재하기 때문에 $('.select').eq(i) 로 하나씩 가져온다.
        var oOptionList = $("#mPage_user_nation").eq(i).find('option');
         
        oOptionList.sort(function(a, b){
            if (a.text > b.text) return 1;
            else if (a.text < b.text) return -1;
        });
     
        $("#mPage_user_nation").eq(i).html(oOptionList);
    }
    
    for(var i=0; i<$("#mPage_user_lang").length; i++){
        
        //여러개의 셀렉트 박스가 존재하기 때문에 $('.select').eq(i) 로 하나씩 가져온다.
        var oOptionList = $("#mPage_user_lang").eq(i).find('option');
         
        oOptionList.sort(function(a, b){
            if (a.text > b.text) return 1;
            else if (a.text < b.text) return -1;
        });
     
        $("#mPage_user_lang").eq(i).html(oOptionList);
    } 
}

// 길이단위 변경
function SizeUnitChange(changeCd) {
    
    var param = {
            "<%=ConstKey.SESSION_SIZE_UNIT%>" : changeCd
    };
        
    $.ajax({
            type:"POST",
            dataType: "json",
            data: param,
            async: true,
            global : false,
            url : "<%=ConstUrl.MAIN_SIZE_UNIT_CHANGE_URL%>",
            success: function(e){
                if(e.resCode == "0"){

                }
            }, 
            error: function(){
            },
    });
}

//통화변경
function CurrencyChange(changeCd) {
        
    var param = {
            "<%=ConstKey.SESSION_CURRENCY%>" : changeCd
    };
        
    $.ajax({
            type:"POST",
            dataType: "json",
            data: param,
            async: true,
            global : false,
            url : "<%=ConstUrl.MAIN_CURRENCY_CHANGE_URL%>",
            success: function(e){
                if(e.resCode == "0"){
                }
            }, 
            error: function(){
                
            },
    });
}

// 국가변경
function NationChange(changeCd) {
		
   	var param = {
			"<%=ConstKey.SESSION_NATION%>" : changeCd
    };
	   	
   	$.ajax({
	   		type:"POST",
	   	    dataType: "json",
   	        data: param,
   			async: true,
   			global : false,
   			url : "<%=ConstUrl.MAIN_NATION_CHANGE_URL%>",
   			beforeSend:function(){
                $('#loadbar').show();
             },
   	        success: function(e){
   	   	   		if(e.resCode == "0"){

   	   	   	   	}
 	   		}, 
 	   		error: function(){
 	   		},
 	   	   complete:function(){
 	   		  $('#loadbar').fadeOut();
           }
  	});
}
function doChangeLang(){
	for( var i = 0; i < LANG_OBJ.length; i++ ){
		var lang_cd = $(LANG_OBJ[i]).data('lang');
		var langSize = $(LANG_OBJ[i]).data('langsize');
		var langType = $(LANG_OBJ[i]).data('langtype');
		var langDefault = $(LANG_OBJ[i]).data('langdef');

		var text = LANG_SET[lang_cd];
		
		if(typeof langSize !="undefined"){
			if(typeof text !="undefined"){
				text = toTruncate(text, Number(langSize), "...");
			}
		}
		
	    if(typeof langType =="undefined"){
		    var $cloneEle = $(LANG_OBJ[i]).children();
		    if(typeof text !="undefined"){
		    	$(LANG_OBJ[i]).html(text);
		    }else{
		    	if(typeof langDefault !="undefined"){
		    		$(LANG_OBJ[i]).html(langDefault);
				}
			}
	        $(LANG_OBJ[i]).append($cloneEle);
		}else{
			$(LANG_OBJ[i]).attr(langType, text);
		}

	}
}

//언어변경
function LangChange(changeLang) {
	  if(changeLang == 'ZHO'){
      	$("body,h1,h2,h3,h4,input,button,input,select,button,textarea").css("font-family","'Tahoma', 'sans-serif','Noto Sans SC', serif ");
      } else {
    	$("body,h1,h2,h3,h4,input,button,input,select,button,textarea").css("font-family","'Tahoma', 'sans-serif','Noto Sans KR', serif ");  
      }
	var obj = document.querySelectorAll('[data-lang]');
	LANG_OBJ = new Array();
    for( var i = 0; i < obj.length; i++ ){
        var item = obj.item(i);        
        var lang_cd = $(item).data('lang');
        LANG_OBJ.push(item);
    }
    
    var param = {
            "<%=ConstKey.SESSION_LANG%>" : changeLang,
    };
        
    $.ajax({
            type:"POST",
            dataType: "json",
            data: param,
            async: true,
            global : false,
            url : "<%=ConstUrl.MAIN_LANG_CHANGE_URL%>",
            beforeSend:function(){
                $('#loadbar').show();
            },
            success: function(e){
                if(e.resCode == "0"){
                	LANG_SET = null; 
                    LANG_SET = JSON.parse(JSON.stringify(e.lang_list));
                    doRightWrapLangChange();
//                     console.log("LANG_SET.FR000013= " + LANG_SET.FR000013);
                    LANG_COMBO = JSON.parse(JSON.stringify(e.json_lang));
                    NATION_COMBO = JSON.parse(JSON.stringify(e.json_nation));
                    //location.reload();
                    doChangeLang();
                    doSelectTextChange();
                    $("#privacy_area").html(e.privacy_info);
                }
            }, 
            error: function(){
            },
            complete: function(){
                $('#loadbar').fadeOut();
            }
    });
}

//언어변경
function doLog(event, item, content) {
    var param = {
    	    event : event,
    	    item : item,
    	    content : content
    };  
    $.ajax({
            type:"POST",
            dataType: "json",
            data: param,
            async: false,
            global : false,
            url : "<%=ConstUrl.ECAT_FR_LOG_URL%>",
            success: function(e){

            }, 
            error: function(){
            },
            complete: function(){
            }
    });
}
</script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script  src="https://www.googletagmanager.com/gtag/js?id=G-YLRGWBFMDG"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-YLRGWBFMDG');
</script>
</body>	
</eaction:html>