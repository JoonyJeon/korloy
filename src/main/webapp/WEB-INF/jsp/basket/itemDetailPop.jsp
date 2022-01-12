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
<%@page contentType="text/html;charset=utf-8"%>
<%-- <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@page import = "java.util.List"%>
<%@page import = "java.lang.String"%>
<%@page import = "java.util.Map"%>
<%-- <%@page import = "java.util.Date"%> --%>
<%-- <%@page import = "java.text.SimpleDateFormat"%> --%>
<%-- <%@page import = "com.eaction.framework.common.util.PagingUtil" %> --%>
<%-- <%@page import = "com.eaction.framework.common.util.DateTimeUtil" %> --%>
<%-- <%@page import = "com.eaction.framework.common.util.StringUtil" %> --%>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstKey"%>
<%@page import = "com.eaction.framework.business.logic.basket.model.BasketInfo"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppSearchInfo"%>
<%-- <%@page import = "com.eaction.framework.common.model.CodeInfo"%> --%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
	String cart_no = (String)request.getAttribute("cartNo");
	String ig_cd = (String)request.getAttribute("ig_cd");
	String item_no = (String)request.getAttribute("search_matnr");
	String requestFromCd = (String)request.getAttribute("requestFrom");
	
%>
        <div id="container" class="sub">
			<div class="box">
                <div class="box_top">
                    <span id="icodian_it_pop"></span>
                </div>
                <div class="box_cont" id="area_item_sub_pop">
                    <div class="itemtit">
                        <p><img id="it_brand_logo_pop" style="display:none;" src="" onerror="onErrorImage(this)" alt="brand logo"><em id="it_tm_nm_pop"></em><em style="font-size: 20px; margin-left: 25px;" id="it_ig_nm_pop"></em></p>
                         <span id="it_ig_dscr_pop"></span>
                    </div>
                    <div class="picture_wrap">
                        <div class="picture">
                            <div class="big">
                            	<a href="javascript:void(0);" class="expand">
                            		<img id="it_image_pop" src="" alt="큰이미지" onerror="this.src='<%=PATH_IMG %>/noimg.png';">
                            	</a>
                            </div>
                            <div class="small">
                                <div class="swiper-container">
                                    <div class="swiper-wrapper" id="it_ig_sub_img_div_pop">
	                                        <div class="swiper-slide">
	                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="picture_detail">
                            <div class="picture_tab">
                                <ul>
                                	<!-- ISO 1399 -->
                                    <li><a id="btn_it_ig_iso_pop" class="on" data-lang="FR000036"><%=LangMng.LANG_D("FR000036", session_lang) %></a></li>
                                	<!-- Non-ISO -->
                                    <li><a id="btn_it_ig_non_iso_pop" data-lang="FR000037"><%=LangMng.LANG_D("FR000037", session_lang) %></a></li>
                                </ul>
                            </div>
                            <a onclick="" class="expand"><img width="auto" id="it_ig_dd_image_pop" alt="도면" src="" onerror="onErrorImage(this)" style="max-height: 290px;"></a>
                        </div>
                    </div>
                    
                    <div class="box">
                        <div class="box_cont pc">
                        	  <div class="view_cont">
				             	<div class="listTable fiexd">
				             		<table id="table_itemInfo1_pop" style="margin-left:443px; min-height:85px;">  
			                    	</table>
			                	</div>
			                	<div class="listTable fiexd" style="">   
			                    	<table id="table_itemInfo2_pop">
			                    	</table>
			                	</div>
				             </div>
                        </div>
                    </div>
                    
                    <div class="box_two">
                        <div class="box">
                            <div class="box_top">
                               	<!-- 3D Representation -->
                                <span data-lang="FR000049"><%=LangMng.LANG_D("FR000049", session_lang) %></span>
                                <div class="box_tab">
                                    <ul>
		                               	<!-- Basic -->
                                        <li><a  href="javascript:void(0);" onclick="go3dBasic();" id="btn_3d_basic_pop" class="on" data-lang="FR000051"><%=LangMng.LANG_D("FR000051", session_lang) %></a></li>
		                               	<!-- Detailed -->
                                        <li><a href="javascript:void(0);" onclick="go3dDetailed();" id="btn_3d_detail_pop" data-lang="FR000052"><%=LangMng.LANG_D("FR000052", session_lang) %></a></li>
                                    </ul>
                                </div>
                               	<!-- Download -->
                                <a id="it_stp_basic_file_url_pop"  class="download" style="cursor:pointer;" data-lang="FR000053"><%=LangMng.LANG_D("FR000053", session_lang) %></a>
                               	<!-- Download -->
                    			<a id="it_stp_detail_file_url_pop" class="download" style="cursor:pointer; display:none;" data-lang="FR000053"><%=LangMng.LANG_D("FR000053", session_lang) %></a>
                            </div>
                            <div class="box_cont" id="div_stp_basic_pop">
                                <div class="img" style="height: 570px; ">
                                	 <div class="img" id="img_3d_basic_pop" style="height: 570px;border:0;" >
				                        <a onclick="javascript:void(0);" class="expand"><img alt="" onerror="onErrorImage(this)" src="" style="max-width:95%;"></a>
				                    </div>
			                        <a href="javascript:void(0);" onclick="" class="expand" style="padding:0px; height:499px;">
			                        <iframe id="3d_rendering_basic_pop" src="" style="width:100%;height:100%;" onerror="onErrorImage(this)">				
									</iframe>
									
			                        </a>
                                </div>
                            </div> 
                             <!-- 3D Detail -->
			                <div class="box_cont" id="div_stp_detail_pop" style="display:none;height: 570px;">
			                    <div class="img" style="height: 570px;">
			                    	 <div class="img" id="img_3d_detail_pop"  style="height:570px;border:0;">
				                        <a onclick="" class="expand"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 95%"></a>
				                    </div>
<!-- 			                        	<img onerror="onErrorImage(this)" alt="" style="max-width: 64%">                         	 -->
			                        <iframe id="3d_rendering_detail_pop" src="" style="width:100%; height:567px;" >				
									</iframe>
									
			                    </div>
			                </div>
                        </div>
                        <div class="box">
                            <div class="box_top">
                               	<!-- 2D Representation -->
                                <span data-lang="FR000050"><%=LangMng.LANG_D("FR000050", session_lang) %></span>
                               	<!-- Download -->
                                <a id="it_dxf_file_url_pop"  href="javascript:void(0);" class="download" data-lang="FR000053"><%=LangMng.LANG_D("FR000053", session_lang) %></a>
                            </div>
                            <div class="box_cont">
                                <div class="img"  style="padding:0px; height:570px; ">
                                    <a href="javascript:void(0);" class="expand"><img id="img_it_dxf_pop" onerror="onErrorImage(this)" src="" alt="" style="width:98%;height:98%;"></a>
                                </div>
                            </div>  
                        </div>
                    </div>
                    
                    <div class="box">
                        <div class="box_top" id="insert_header_pop">
                             <!--Related Inserts-->
                            <span data-lang="FR000054"><%=LangMng.LANG_D("FR000054", session_lang) %></span>
                        </div>
                        <div class="box_cont" id="area_inserts_pop">
                            <div class="view_cont">
                                <div class="listTable fiexd" >
                                    <table id="table_relInserts_pop" style="margin-left:443px;">
                                    </table>
                                </div>
                            </div>
                        </div>  
                    </div>
                    <div class="box">
                        <div class="box_top" id="holder_header_pop">
                             <!--Related Holders-->
                            <span data-lang="FR000055"><%=LangMng.LANG_D("FR000055", session_lang) %></span>
                        </div>
                        <div class="box_cont" id="area_holders_pop">
                            <div class="view_cont">
                                <div class="listTable fiexd">
                                    <table style="margin-left:443px;" id="table_relHolders_pop">
                                    </table>
                                </div>
                            </div>
                        </div>  
                    </div>
                    
                    <div class="box">
                        <div class="box_top" id="cutting_header_pop">
                             <!--Recommended Cutting Speeds-->
                            <span data-lang="FR000056"><%=LangMng.LANG_D("FR000056", session_lang) %></span>
                        </div>
                        <div class="box_cont" id="area_cutting_pop">
                            <div class="listTable noMargin noScroll">
                                <table id="table_cuttingSpeeds_pop">
                                </table>
                            </div>
                        </div> 
                    </div> <!--  -->
                    
                       
                    <div class="box">
                        <div class="box_top" id="spare_header_pop">
                            <!-- Spare Parts -->
                            <span data-lang="FR000057"><%=LangMng.LANG_D("FR000057", session_lang) %></span>
                        </div>
                        <div class="box_cont" id="area_spare_parts_pop">
                            <div class="listTable noMargin noScroll">
                                <table id="table_spareParts_pop">
                                </table>
                            </div>
                        </div> 
                    </div>
                    
                </div>
            </div>
        </div>
<style>
#footer, #header, .fullmenu_list .allowCookie{
	display: none !important;
}
.listTable td {
	background-color:white;
}
.swiper-slide.swiper-slide-active{
	width:auto !important;
	margin-right:0 !important;
}
#it_ig_sub_img_div_pop.swiper-wrapper{
	justify-content: flex-start;
}
.open_pop .in .pop_cont {
	overflow-y:none !important;
	max-heigth: 1000px;
}
</style>
<script type="text/javascript">
var isoImgUrl;
var nonIsoImgUrl;

var cart_num = '<%=cart_no%>'
var ig_code = '<%=ig_cd%>'
var item_no = '<%=item_no%>'

var default_prop_symbol_arry = []; // default filter prop symbol list : Reset시 사용
/* Item */
var item_prop_symbol_arry = []; // item filter prop symbol list
var item_prop_type = "I"; // I : ISO, N:Non-ISO
var itemInfo;
//사이즈단위변경
var cur_size_unit = "<%=ConfigMng.getSizeUnit(request)%>";
var cur_currency = "<%=ConfigMng.getCurrency(request)%>";

/* ISO 계산 */
let isoCalMap;
/*로그 이벤트 코드 구분을 위한 페이지 구분 값*/
let reqCd ='<%=requestFromCd%>';

var inchYn = "";

jQuery(document.children).ready(function(){
<%-- 	alert('<%=requestFromCd%>') --%>
	//해당 아이템 정보 셋팅
	getItemInfo();
	$('#footer').remove();
	$('#header').remove();
	$('.fullmenu_list').remove();
	//클릭이벤트
	bindClickEvent();
	$(".allowCookie").remove();
	$(".gotoBack").remove();
});
function bindClickEvent(){
	//Item - ISO1399 버튼 클릭
	$('#btn_it_ig_iso_pop').on('click', function(ev){
		ev.preventDefault();
		if(isoImgUrl !== ""){
			$("#it_ig_dd_image_pop").attr("src", isoImgUrl);				
			$("#btn_it_ig_iso_pop").addClass("on");				
			$("#btn_it_ig_non_iso_pop").removeClass("on");
		}	
		item_prop_type = "I";

		setItemFilterIsoName();
	});
	
	//Item - Non-ISO 버튼 클릭
	$('#btn_it_ig_non_iso_pop').on('click', function(ev){
		ev.preventDefault();
		if(nonIsoImgUrl !== ""){
			$("#it_ig_dd_image_pop").attr("src", nonIsoImgUrl);
			$("#btn_it_ig_iso_pop").removeClass("on");
			$("#btn_it_ig_non_iso_pop").addClass("on");
		}
		item_prop_type = "N";
		
		setItemFilterNisoName();
	});
	
}
//아이템 테이블 그리기  (PC)
function createItemInfo1Table(){
	$("#table_itemInfo1_pop *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width="*"><col width="*"><col width="*">';		
	if(item_prop_type == 'I'){
		for(var i=0; i<item_prop_symbol_arry.length; i++){
			if(item_prop_symbol_arry[i].iso){
				if(item_prop_symbol_arry[i].chkYn == 'Y'){
					var colHtml;
					colHtml = '<col width="*">';		
					colGroupHtml += colHtml;
				}
			}
		}
	}else{
		for(var i=0; i<item_prop_symbol_arry.length; i++){
			if(item_prop_symbol_arry[i].niso){
				if(item_prop_symbol_arry[i].chkYn == 'Y'){
					var colHtml;
					colHtml = '<col width="*">';		
					colGroupHtml += colHtml;
				}
			}
		}
	}
	colGroupHtml += '</colgroup>';
	// <colgroup> 태그 END
	<%-- Order Number --%>
	<%-- 자재내역 --%>
	colGroupHtml += '<thead style="position:absolute; left:0; z-index:10;"><tr><th style="width:200px;">' + LANG_SET["FR000038"] + '</th><th style="width:200px;">' + LANG_SET["FR000039"] + '</th></tr>'
	
	colGroupHtml += '<tr style="height:42px;"><td>' + itemInfo.MATNR + '</td>';  // Order Number 
	// 자재내역
	colGroupHtml += '<td class="left"  style="width: 220px;">' + itemInfo.MAKTX + '</td></tr>';	
	colGroupHtml += '</thead><tbody>'
	
	for(var j=0; j<item_prop_symbol_arry.length; j++){
		var th_key = '';
		
		if(item_prop_type == 'I'){
			if(item_prop_symbol_arry[j].iso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					colGroupHtml += '<th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' +  item_prop_symbol_arry[j].iso + '</th>';	
				}
			}				
		}else{
			if(item_prop_symbol_arry[j].niso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					colGroupHtml += '<th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' +  item_prop_symbol_arry[j].niso + '</th>';
				}
			}
		}		
	}
	colGroupHtml += '</tr>'
				
	colGroupHtml += '<tr>';  // Default : Cart
	for(var j=0; j<item_prop_symbol_arry.length; j++){
		var th_key = '';
		var th_val = '';		
		
		if(item_prop_type == 'I'){
			if(item_prop_symbol_arry[j].iso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					th_key = item_prop_symbol_arry[j].iso;
					th_val = itemInfo[th_key];
					
					if(typeof th_val == "undefined" || th_val == null){
						th_val = '';
					}
				    th_val = String(th_val).replace(/}/, '');
                    th_val = String(th_val).replace(/{/, '');
					// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
					if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자
							var val = calVal.split('$$')[1]; //값
							
							if(unit = "/"){
								th_val = Number((Number(th_val) / val).toFixed(4));
							}else if(unit = "*"){
								th_val = Number((Number(th_val) * val).toFixed(4));	
							}else if(unit = "+"){
								th_val = Number((Number(th_val) + val).toFixed(4));	
							}else if(unit = "-"){
								th_val = Number((Number(th_val) - val).toFixed(4));	
							}
						}						
                        //th_val = Number((Number(th_val) / 25.4).toFixed(4));
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                    	
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
					colGroupHtml += '<td class="center" style="height:21px;">' + th_val + '</td>';	
				}
			}				
		}else{
			if(item_prop_symbol_arry[j].niso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					th_key = item_prop_symbol_arry[j].iso;
					th_val = itemInfo[th_key];						

					if(typeof th_val == "undefined" || th_val == null){
						th_val = '';
					}
				  	th_val = String(th_val).replace(/}/, '');
                    th_val = String(th_val).replace(/{/, '');
					// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
					if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자
							var val = calVal.split('$$')[1]; //값
							
							if(unit = "/"){
								th_val = Number((Number(th_val) / val).toFixed(4));
							}else if(unit = "*"){
								th_val = Number((Number(th_val) * val).toFixed(4));	
							}else if(unit = "+"){
								th_val = Number((Number(th_val) + val).toFixed(4));	
							}else if(unit = "-"){
								th_val = Number((Number(th_val) - val).toFixed(4));	
							}
						}						
                        //th_val = Number((Number(th_val) / 25.4).toFixed(4));
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
					colGroupHtml += '<td class="center" style="height:21px;">' + th_val + '</td>';	
				}
			}
		}
	}
	colGroupHtml += '</tr></tbody>';
	
	$('#table_itemInfo1_pop').append(colGroupHtml);
	
}
function createItemInfo2Table(cart_no, priceYN, stockYN){
	
	// blueLine Table
	$("#table_itemInfo2_pop *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width="20%"><col width="*"><col width="*"><col width="*"><col width="*"><col width="*">';	 

	colGroupHtml += '</colgroup><tbody><tr>';
	// <colgroup> 태그 END
	
	// <tr><th> 태그 START
	colGroupHtml += '<tbody>'
													/*GTIN(EAN)*/										/* File Download */
	colGroupHtml += '<tr><th data-lang="FR000175">' + LANG_SET["FR000175"] + '</th><th colspan="2">'+LANG_SET['FR000043']+'</th>' 
											/* Currency */									/* Price */										/* Stock */
// 							+ '<th colspan="2">'+LANG_SET['FR000045']+'</th><th colspan="2">'+LANG_SET['FR000046']+'</th><th colspan="2">'+LANG_SET['FR000047']+'</th></tr>';
		//Admin 설정값(Currency, Price)에 따라 필드 표시	
		if(priceYN == 'Y'){
			colGroupHtml += '<th colspan="2" data-lang="FR000045">' + LANG_SET["FR000045"] + '</th><th colspan="2" data-lang="FR000046">' + LANG_SET["FR000046"] + '</th>';
		}
		//Admin 설정값(Stock)에 따라 필드 표시
		if(stockYN == 'Y'){
			colGroupHtml += '<th colspan="2" data-lang="FR000047">' + LANG_SET["FR000047"] + '</th>'
		}						  
		var assemblyImgUrl = "<%=PATH_IMG%>/img_assembly.png";
		
		var userCurrency = cur_currency; //세션 통화로 변경	
		var kbetr;
		var selCurrency = "";
		if(userCurrency == 'CC0076'){ // KRW
			selCurrency = "KRW";
			kbetr = numberFormatting(itemInfo.K_KBETR, selCurrency)
			//kbetr = itemInfo.K_KBETR;
		}else if(userCurrency == 'CC0077'){ // USD
			selCurrency = "USD";
			kbetr = numberFormatting(itemInfo.U_KBETR, selCurrency)
			//kbetr = itemInfo.U_KBETR;
		}else if(userCurrency == 'CC0078'){ // EUR
			selCurrency = "EUR";
			kbetr = numberFormatting(itemInfo.E_KBETR, selCurrency)
			//kbetr = itemInfo.E_KBETR;
		}else{
			selCurrency = "USD";
			kbetr = numberFormatting(itemInfo.U_KBETR, selCurrency)
			//kbetr = itemInfo.U_KBETR;
		}
		
		//재고수량
		var labst;
		if(parseInt(itemInfo.ST_LABST) > 0){
			<%-- IN STOCK --%>
			labst = LANG_SET['FR000227'];
		}else{
			<%-- OUT OF STOCK --%>
			labst = LANG_SET['FR000226'];
		}
		//GTIN(EAN)
		var zaddean = "";
	 	if(typeof itemInfo.ZADDEAN == "undefined" || itemInfo.ZADDEAN == null){
	 		zaddean = ""
	 	}else{
	 		zaddean = itemInfo.ZADDEAN
	 	}	
		var evtCdGtc;
		var evtCdP21;
		if(reqCd =='IS'){
			//related insert에서 클릭한 레이어 팝업일 때
			evtCdGtc=""
			evtCdP21=""
		}else if(reqCd =='SP'){
			//spare parts에서 클릭한 레이어 팝업일 때
			evtCdGtc="EC0033"
			evtCdP21="EC0034"
		}else if(reqCd =='HD'){
			//related holders에서 클릭한 레이어 팝업일 때
			evtCdGtc="EC0029"
			evtCdP21="EC0030"
		}else{
			evtCdGtc=""
		}
		colGroupHtml += '<tr><td id="it_zaddean_pop">' + zaddean + '</td><td><a id="it_gtc_file_url_pop" onclick="downGtcFile('+"'"+evtCdGtc+"'"+','+"'"+itemInfo.MATNR+"'"+');" style="cursor:pointer;">' + LANG_SET["FR000079"] + '</a></td><td><a id="it_p21_file_url_pop" style="cursor:pointer;" onclick="downP21File('+"'"+evtCdP21+"'"+','+"'"+itemInfo.MATNR+"'"+')">P21</a></td>' 
// 								'<td colspan="2">' + selCurrency + '</td><td colspan="2" class="right">' + kbetr + '</td><td colspan="2" class="left">' + labst + '</td></tr></tbody>';	
		if(priceYN == 'Y'){
			colGroupHtml += '<td colspan="2">' + selCurrency + '</td><td colspan="2" class="right">' + kbetr + '</td>';
		}
		//Admin 설정값(Stock)에 따라 필드 표시
		if(stockYN == 'Y'){
			colGroupHtml += '<td colspan="2" class="left">' + labst + '</td>';
		}
	// <tr><th> 태그 END
	
 	colGroupHtml += '</tbody>'
	// itemList <tr><td> 태그 END

 	$('#table_itemInfo2_pop').append(colGroupHtml);
	
 	
}

var itGtcFileUrlPop; // GTC파일
var itP21FileUrlPop; // P21파일
var itDxfFileUrlPop; // 2D파일
var itStpBasicFileUrlPop; // 3D Basic파일
var itStpDetailFileUrlPop; // 3D Detail파일

//아이템 정보 취득
function getItemInfo(){
	var param = {
				actionID		: "<%=BasketConstKey.ACTION_SHOW_ITEM_DETAIL%>"
				,search_matnr	: item_no
				,cart_no		: cart_num
				,search_ig_cd	: ig_code
			}
	
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.ITEM_DETAIL_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
//         	console.log("[ITEM] = " + JSON.stringify(e.itemInfo))        	
//         	console.log("[ITEM GROUP NAME] = " + JSON.stringify(e.igName))        	
//         	console.log("[ITEM GROUP IMG] = " + JSON.stringify(e.igImgInfo));
//         	console.log("[ITEM GROUP] = " + JSON.stringify(e.igInfo));
//         	console.log("[SUB APP IMAGE] = " + JSON.stringify(e.subAppImgList));
//         	console.log("[FILTER PROP] = " + JSON.stringify(e.itemPropertyList));
//         	console.log("[INSERT LIST] = " + JSON.stringify(e.insertsList));
//         	console.log("[HOLDER LIST] = " + JSON.stringify(e.holdersList));
//         	console.log("[CUTTING LIST] = " + JSON.stringify(e.cuttingSpeedList));
//         	console.log("[SPARE LIST] = " + JSON.stringify(e.sparePartsList));
//         	console.log(e);
//         	console.log(e.inchYN);
			//인치사용여부
        	inchYn = e.inchYN
        	
        	itemInfo = e.itemInfo;  
        	var resultIgImgInfo = e.igImgInfo;        	
        	var resultIgInfo = e.igInfo;        	
        	
        	var resultSubAppImgList = e.subAppImgList;
        	var resultPropList = e.itemPropertyList;        	
        	var insertsList = e.insertsList;        	
        	var holdersList = e.holdersList;        	
        	var cuttingSpeedList = e.cuttingSpeedList;        	
        	var sparePartsList = e.sparePartsList; 
        	var insProp = e.ins_prop;
        	var holdProp = e.hold_prop;
        	
        	chgMapIsoCal(e.isoCalList);
        	var priceYN = e.priceYN;
        	var stockYN = e.stockYN;        	

//        		$("#it_ig_nm_pop").text(e.igName);
        	//아이템 그룹 정보
        	if(resultIgInfo !== null){
        		if(resultIgInfo.ig_nm == ''){
	    			$("#it_ig_nm_pop").text(resultIgInfo.ig_nm);
	    			$("#it_tm_nm_pop").text(resultIgInfo.tm_nm);
        		}
        		
        		$("#it_ig_nm_pop").text(resultIgInfo.ig_nm);
    			$("#it_tm_nm_pop").text(resultIgInfo.tm_nm);
    			$("#it_ig_dscr_pop").text(resultIgInfo.ig_dscr);
    			
				if(resultIgImgInfo != null){
	    			//브랜드로고 이미지
	    			if(resultIgImgInfo.brand_logo !== ""){
	    				var brandLogoUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.brand_logo;
	    				
	    				$("#it_brand_logo_pop").attr("src", brandLogoUrl);
	    				$("#it_brand_logo_pop").show();
	    			} 
				}
        	}else{
        		$("#it_ig_nm").text(resultItemGroupInfo.ig_nm);
        	}
        	//이미지
        	if(resultIgImgInfo !=null){
    			
    			//ISO 도면 이미지 URL
    			if(resultIgImgInfo.iso_image !== "" || resultIgImgInfo.iso_image !== null){
    				isoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.iso_image_thumb;
    			}
    			
    			//NON-ISO 도면 이미지 URL
    			if(resultIgImgInfo.non_image !== "" || resultIgImgInfo.non_image !== null){
    				nonIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.non_image_thumb;
    			}
    			
        	}
//         	//아이템그룹 대표 이미지
// 			if(resultIgImgInfo.ig_image !== "" ||resultIgImgInfo.ig_image != null){
<%-- 				itemImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.item_image_thumb; --%>
// 				$("#it_ig_image_pop").attr({"src": itemImgUrl});		
// 			}
			
			
// 			//ISO 도면 이미지 URL
// 			if(resultIgImgInfo.iso_image !== "" || resultIgImgInfo.iso_image !== null){
<%-- 				isoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.iso_image_thumb; --%>
// 			}
			
// 			//NON-ISO 도면 이미지 URL
// 			if(resultIgImgInfo.non_image !== "" || resultIgImgInfo.non_image !== null){
<%-- 				nonIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.non_image_thumb; --%>
// 			}
			
			//ISO 1399 도면 이미지 세팅
			if(isoImgUrl !== "" || isoImgUrl !==null){
				$("#ig_dd_image_pop").attr("src", isoImgUrl);				
				$("#it_ig_dd_image_pop").attr("src", isoImgUrl);
			}	

		    var itemImgUrl = "";
            //아이템 대표 이미지
            if(itemInfo.ITEM_IMG !== "" || itemInfo.ITEM_IMG != null){
                itemImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.ITEM_IMG;
            }else{
            	itemImgUrl = "";
            }
            $("#it_image_pop").attr({"src": itemImgUrl});
            
			// GTC 파일
// 			var itGtcFileUrl;
			if(itemInfo.GTC_FILE !== undefined){
				itGtcFileUrlPop = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.GTC_FILE;
			}else{
				itGtcFileUrlPop = "";
			}
			
			// P21 파일
			if(itemInfo.P21_FILE !== undefined){
				itP21FileUrlPop = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.P21_FILE;
			}else{
				itP21FileUrlPop = "";
			}
			
			// 2D Representation Image
			var itDxfImgUrl;		
			if(itemInfo.DXF_IMG_THUMB !== undefined){
				itDxfImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.DXF_IMG_THUMB;
			}else{
				itDxfImgUrl = "";
			}
			
			//2D이미지 표시
			//$("#img_it_dxf").attr("src", ""); //초기화
			if(itDxfImgUrl !== ""){ 
				$("#img_it_dxf_pop").attr("src", itDxfImgUrl);
			}
			
			// 2D Representation
			//itDxfFileUrl = "";
			if(itemInfo.DXF_FILE !== undefined){
				itDxfFileUrlPop = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.DXF_FILE;
			}else{
				itDxfFileUrlPop = "";
			}
			//로그저장 - 2d 다운로드
			var evtCd2d;
			if(reqCd =='IS'){
				//related insert에서 클릭한 레이어 팝업일 때
				evtCd2d="EC0026"
			}else if(reqCd =='SP'){
				//spare parts에서 클릭한 레이어 팝업일 때
				evtCd2d="EC0036"
			}else if(reqCd =='HD'){
				//related holders에서 클릭한 레이어 팝업일 때
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++임시 이벤트 코드 홀더에서 넘어온 레이어 팝업일때 추가해야함 S
				evtCd2d=""
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++임시 이벤트 코드 홀더에서 넘어온 레이어 팝업일때 추가해야함 E
			}else{
				evtCd2d=""
			}
			$('#it_dxf_file_url_pop').attr('onclick', 'doLogAtDownLoad("'+itemInfo.MATNR+'", "'+evtCd2d+'")');
			//2D 다운로드
			if(USERAUTH.isDxfDownAuth){
			 	if(itDxfFileUrlPop !== ""){		
					//dxf 파일 다운로드
					var file_nm = itDxfFileUrlPop.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_dxf_file_url_pop").attr('href', itDxfFileUrlPop);
					$("#it_dxf_file_url_pop").attr('download', file_nm);		
				}else{
					<%-- 저장된 파일이 없습니다. --%>
					$('#it_dxf_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				$("#it_dxf_file_url_pop").attr("onclick", 'openLogin('+itemInfo.MATNR+', '+evtCd2d+')');
			}
			
			
			// 3D Representation (Basic)
			//itStpBasicFileUrl = "";
			if(itemInfo.STP_BASIC_FILE !== undefined){
				itStpBasicFileUrlPop = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.STP_BASIC_FILE;
				go3dBasic();
			}else{
				itStpBasicFileUrlPop = "";
			}
			//로그저장 - 3d 다운로드 / EC0022
			var evtCd3d;
			if(reqCd =='IS'){
				//related insert에서 클릭한 레이어 팝업일 때
				evtCd3d="EC0025"
			}else if(reqCd =='SP'){
				//spare parts에서 클릭한 레이어 팝업일 때
				evtCd3d="EC0035"
			}else if(reqCd =='HD'){
				//related holders에서 클릭한 레이어 팝업일 때
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++임시 이벤트 코드 홀더에서 넘어온 레이어 팝업일때 추가해야함 S
				evtCd3d=""
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++임시 이벤트 코드 홀더에서 넘어온 레이어 팝업일때 추가해야함 E
			}else{
				evtCd3d=""
			}
			
			$("#it_stp_basic_file_url_pop").attr('onclick', 'doLogAtDownLoad("'+itemInfo.MATNR+'","'+evtCd3d+'")')
			//3D Basic 다운로드
			if(USERAUTH.isStpDownAuth){
				if(itStpBasicFileUrlPop !== ""){
					//stp basic 파일 다운로드
					var file_nm = itStpBasicFileUrlPop.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_stp_basic_file_url_pop").attr('href', itStpBasicFileUrlPop);
					$("#it_stp_basic_file_url_pop").attr('download', file_nm);
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#it_stp_basic_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				//로그인 한 경우와 아닌경우 분기 처리
				var userId = USERINFO.user_id;
				if(userId == ""){
// 					$(".open_login").click();
					$('#it_stp_basic_file_url_pop').attr('onclick', 'openLogin("'+itemInfo.MATNR+'", "'+evtCd3d+'")');
				}else{
					<%-- 권한이 없으면 승급 요청을 하세요 --%>
					$('#it_stp_basic_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000229"]+'");');
				}
// 				$("#it_stp_basic_file_url_pop").attr("onclick", 'openLogin('+itemInfo.MATNR+','+evtCd3d+')');
			}		
			
			// 3D Representation (Detail)
			//itStpDetailFileUrl = "";
			if(itemInfo.STP_DETAIL_FILE !== undefined){
				itStpDetailFileUrlPop = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.STP_DETAIL_FILE;
				// 3D basic이 없고 Detail만 있는경우 Detail 보여주기
				if(itStpBasicFileUrlPop == ""){
					go3dDetailed();
				}
			}else{
				itStpDetailFileUrlPop = "";
			}
			
			$("#it_stp_detail_file_url_pop").attr("onclick",'doLogAtDownLoad("'+itemInfo.MATNR+'","'+evtCd3d+'")');
			//3D detail 다운로드
			if(USERAUTH.isStpDownAuth){
				if(itStpDetailFileUrlPop !== ""){				
					//stp basic 파일 다운로드
					var file_nm = itStpDetailFileUrlPop.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_stp_detail_file_url_pop").attr('href', itStpDetailFileUrlPop);
					$("#it_stp_detail_file_url_pop").attr('download', file_nm);
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#it_stp_detail_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				//로그인 한 경우와 아닌경우 분기 처리
				var userId = USERINFO.user_id;
				if(userId == ""){
// 					$(".open_login").click();
					$('#it_stp_detail_file_url_pop').attr('onclick', 'openLogin("'+itemInfo.MATNR+'", "'+evtCd3d+'")');
				}else{
					<%-- 권한이 없으면 승급 요청을 하세요 --%>
					$('#it_stp_detail_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000229"]+'");');
				}
// 				$("#it_stp_detail_file_url_pop").attr("onclick", 'openLogin('+itemInfo.MATNR+','+evtCd3d+')');
			}	
			
			setItemFilterIsoName();
			
			//Sub App 이미지 
			for(var i=0; i<resultSubAppImgList.length; i++){
				var subImgHtml = "";
				var subImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultSubAppImgList[i].sub_img;
				subImgHtml = '<div class="swiper-slide" style="width: 56px; margin-right: 10px;"><a><img src="' + subImgUrl + '"  onerror="onErrorImage(this)"></a></div>';
				$("#it_ig_sub_img_div_pop").append(subImgHtml);
				
				//$('#list').html(data).trigger("create");
			}

			// 필터 초기화
			default_prop_symbol_arry = [];
			prop_symbol_arry = [];
			item_prop_symbol_arry = [];
			
			//Property
			for(var i=0; i<resultPropList.length; i++){
				//디바이스 타입이 PC인경우만
				default_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn });
				prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn });
				item_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn });
					
			}
			
			$("#icodian_it_pop").text(itemInfo.DESIGNATION);	
        	createItemInfo1Table();
        	createItemInfo2Table(cart_num, priceYN, stockYN);
        	
        	createSparePartsTable(sparePartsList);
        	createCuttingSpeedTable(cuttingSpeedList);
        	createRelatedHoldersTable(holdersList, holdProp);
        	createRelatedInsertsTable(insertsList, insProp);
        	
        }, complete:function(){
            $('#loadbar').fadeOut();
        }, error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}
//ISO 1399 클릭
function setItemFilterIsoName(){
	createItemInfo1Table();	
}

//Non-ISO 클릭
function setItemFilterNisoName(){
	createItemInfo1Table();	
}
//metric/inch 계산 단위 및 값 Map으로 변경
function chgMapIsoCal(isoCalList){
	//console.log("isoCalList == " + JSON.stringify(isoCalList));
	isoCalMap = new Map();
	for(var i=0; i<isoCalList.length; i++){
		if(isoCalList[i].cal_unit !== ""){
			isoCalMap.set(isoCalList[i].iso, isoCalList[i].cal_unit + "$$" + isoCalList[i].cal_val);
		}		
	}
	//console.log("isoCalMap == " + isoCalMap);
}
//Related Inserts 테이블 그리기
function createRelatedInsertsTable(insertsList, propList){

	 $("#table_relInserts_pop *").remove();
	if(insertsList.length !=0){
		// <colgroup> 태그 START
		var colGroupHtml = '<colgroup><col width="*"><col width="*"><col width="*">';		
		for(var i=0; i<propList.length; i++){
			if(propList[i].prop_iso){
                var colHtml;
                colHtml = '<col width="*">';        
                colGroupHtml += colHtml;
			}
		}
		colGroupHtml += '</colgroup>';
		// <colgroup> 태그 END
																											/*Order Number*/									/*Designation*/
		colGroupHtml += '<thead style="position:absolute; left:0; z-index:10;"><tr><th style="width:200px;" data-lang="FR000038">'+LANG_SET['FR000038']+'</th><th style="width:200px;" data-lang="FR000039">'+LANG_SET['FR000039']+'</th></tr>'
		//어드민에서 인치사용여부 on인 경우
		if(inchYn == 'Y'){
			for(var k=0; k<insertsList.length; k++){ 		
				var rowHtml = '<tr style="height:42px;">';
				rowHtml += '<td class="center">' + insertsList[k].MATNR + '</td>';  // Default :  Order Number 
				rowHtml += '<td class="left">' + insertsList[k].MAKTX + '</td></tr>';	
				colGroupHtml += rowHtml; 
			}
		}else{
			//어드민에서 인치사용여부 off인 경우
			for(var k=0; k<insertsList.length; k++){ 
				if(insertsList[k].UNIT_CD !='CC0002'){
					var rowHtml = '<tr style="height:42px;">';
					rowHtml += '<td class="center">' + insertsList[k].MATNR + '</td>';  // Default :  Order Number 
					rowHtml += '<td class="left">' + insertsList[k].MAKTX + '</td></tr>';	
					colGroupHtml += rowHtml; 
				}
			}
		}//end of else inchYn			
													/*GRADE*/
		colGroupHtml += '</thead><tbody><tr>'

		for(var j=0; j<propList.length; j++){
			var th_key = '';
			if(propList[j].prop_iso){
                colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
			}				
		}
		colGroupHtml += '</tr>'
//어드민에서 인치사용 on인 경우
if(inchYn =='Y'){
		for(var k=0; k<insertsList.length; k++){ 	
			
			colGroupHtml += '<tr style="height:42px;">';  // Default : Cart
			
			for(var j=0; j<propList.length; j++){
				var th_key = '';
				var th_val = '';		
				if(propList[j].prop_iso){
                    th_key = propList[j].prop_iso;
                    th_val = insertsList[k][th_key];
                    if(typeof th_val == "undefined" || th_val == null){
                        th_val = '';
                    }
                    th_val = String(th_val).replace(/}/, '');
                    th_val = String(th_val).replace(/{/, '');
                 // Inch 이고, 값이 숫자이고, unit mtric이 "mm"이면 / 25.4
					// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
					if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(propList[j].prop_iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자
							var val = calVal.split('$$')[1]; //값
							
							if(unit = "/"){
								th_val = Number((Number(th_val) / val).toFixed(4));
							}else if(unit = "*"){
								th_val = Number((Number(th_val) * val).toFixed(4));	
							}else if(unit = "+"){
								th_val = Number((Number(th_val) + val).toFixed(4));	
							}else if(unit = "-"){
								th_val = Number((Number(th_val) - val).toFixed(4));	
							}
						}
                        //th_val = Number((Number(th_val) / 25.4).toFixed(4));
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(propList[j].prop_iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    }
						}
                    }                  
                    colGroupHtml += '<td class="center">' + th_val + '</td>';
				}				
			}// end of inner for
			colGroupHtml += '</tr>';
		}// end of first for
}else{
//어드민에서 인치사용 off인 경우
		for(var k=0; k<insertsList.length; k++){ 	
			if(insertsList[k].UNIT_CD !='CC0002'){
				colGroupHtml += '<tr style="height:42px;">';  
				
				for(var j=0; j<propList.length; j++){
					var th_key = '';
					var th_val = '';		
					if(propList[j].prop_iso){
		                   th_key = propList[j].prop_iso;
		                   th_val = insertsList[k][th_key];
		                   if(typeof th_val == "undefined" || th_val == null){
		                       th_val = '';
		                   }
		                   th_val = String(th_val).replace(/}/, '');
		                   th_val = String(th_val).replace(/{/, '');
		                // Inch 이고, 값이 숫자이고, unit mtric이 "mm"이면 / 25.4
						// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
						if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
							var calVal = isoCalMap.get(propList[j].prop_iso);
							
							if(typeof calVal !== "undefined"){
								var unit = calVal.split('$$')[0]; //연산자
								var val = calVal.split('$$')[1]; //값
								
								if(unit = "/"){
									th_val = Number((Number(th_val) / val).toFixed(4));
								}else if(unit = "*"){
									th_val = Number((Number(th_val) * val).toFixed(4));	
								}else if(unit = "+"){
									th_val = Number((Number(th_val) + val).toFixed(4));	
								}else if(unit = "-"){
									th_val = Number((Number(th_val) - val).toFixed(4));	
								}
							}
		                       //th_val = Number((Number(th_val) / 25.4).toFixed(4));
		                   }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
							var calVal = isoCalMap.get(propList[j].prop_iso);
							
							if(typeof calVal !== "undefined"){
								var unit = calVal.split('$$')[0]; //연산자							
								if(unit != ""){
			                    }
							}
		                   }                  
		                   colGroupHtml += '<td class="center">' + th_val + '</td>';
					}				
				}// end of inner for
				colGroupHtml += '</tr>';
		}
	}// end of first for
}
		colGroupHtml += '</tbody>'

// 		console.log("colGroupHtml" + colGroupHtml);	
		$('#table_relInserts_pop').append(colGroupHtml);
		
	}else{
		$('#insert_header_pop').hide();
		$('#area_inserts_pop').hide();
	} 	 
				
}

//Related Holders 테이블 그리기
function createRelatedHoldersTable(holdersList, propList){
	 $("#table_relHolders_pop *").remove();
	 if(holdersList.length !=0){
		// <colgroup> 태그 START
			var colGroupHtml = '<colgroup><col width="*"><col width="*">';		
			for(var i=0; i<propList.length; i++){
				if(propList[i].prop_iso){
                    var colHtml;
                    colHtml = '<col width="*">';        
                    colGroupHtml += colHtml;
				}
			}
			colGroupHtml += '</colgroup>';
			// <colgroup> 태그 END
																													/*Order Number*/									/*Designation*/
			colGroupHtml += '<thead style="position:absolute; left:0; z-index:10;"><tr><th style="width:200px;" data-lang="FR000038">'+LANG_SET['FR000038']+'</th><th style="width:200px;" data-lang="FR000039">'+LANG_SET['FR000039']+'</th></tr>'
//어드민에서 인치사용 여부 on했을 때
if(inchYn =='Y'){
			for(var k=0; k<holdersList.length; k++){ 		
				var rowHtml = '<tr style="height:42px;">';
				rowHtml += '<td class="center">'+ holdersList[k].MATNR +'</td>';  
				rowHtml += '<td class="left">' + holdersList[k].MAKTX + '</td></tr>';	
				colGroupHtml +=  rowHtml; 
			}//end of for
}else{
//어드민에서 인치사용 여부 off했을 때
		for(var k=0; k<holdersList.length; k++){
			if(holdersList[k].UNIT_CD !='CC0002'){
// 				alert(holdersList[k].MATNR)
				var rowHtml = '<tr style="height:42px;">';
				rowHtml += '<td class="center">'+ holdersList[k].MATNR +'</td>';  // Default :  Order Number 
				rowHtml += '<td class="left">' + holdersList[k].MAKTX + '</td></tr>';	
				colGroupHtml +=  rowHtml; 
// 				console.log(colGroupHtml)
			}
		}//end of for

}
			colGroupHtml += '</thead><tbody><tr>'
			
			if(propList.length !=0){
				for(var j=0; j<propList.length; j++){
					var th_key = '';
					if(propList[j].prop_iso){
                        colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
					}				
				}
			}else{
				colGroupHtml += '<th style="min-height:41px;" data-lang="FR000042">'+LANG_SET['FR000042']+'</th>';
			}
			colGroupHtml += '</tr>'
//어드민에서 인치사용 여부 on했을 때
if(inchYn =='Y'){
	for(var k=0; k<holdersList.length; k++){ 				
		colGroupHtml += '<tr style="height:42px;">'; 
		if(propList.length !=0){
			for(var j=0; j<propList.length; j++){
				var th_key = '';
				var th_val = '';		
				if(propList[j].prop_iso){
                    th_key = propList[j].prop_iso;
                    th_val = holdersList[k][th_key];
                    if(typeof th_val == "undefined" || th_val == null){
                        th_val = '';
                    }
                    th_val = String(th_val).replace(/}/, '');
                    th_val = String(th_val).replace(/{/, '');
                 // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
					if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(propList[j].prop_iso);
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자
							var val = calVal.split('$$')[1]; //값
							
							if(unit = "/"){
								th_val = Number((Number(th_val) / val).toFixed(4));
							}else if(unit = "*"){
								th_val = Number((Number(th_val) * val).toFixed(4));	
							}else if(unit = "+"){
								th_val = Number((Number(th_val) + val).toFixed(4));	
							}else if(unit = "-"){
								th_val = Number((Number(th_val) - val).toFixed(4));	
							}
						}
						//th_val = Number((Number(th_val) / 25.4).toFixed(4));
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(propList[j].prop_iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
                    colGroupHtml += '<td class="center">' + th_val + '</td>';
				}				
			}//end of inner for
		}else{
			colGroupHtml += '<td class="">'+'N/A'+'</td>';	
		}
		colGroupHtml += '</tr>';
	}// end of for
}else{
//어드민에서 인치사용 여부 off했을 때
	for(var k=0; k<holdersList.length; k++){
		if(holdersList[k].UNIT_CD !='CC0002'){
			colGroupHtml += '<tr style="height:42px;">'; 
			if(propList.length !=0){
				for(var j=0; j<propList.length; j++){
					var th_key = '';
					var th_val = '';		
					if(propList[j].prop_iso){
	                          th_key = propList[j].prop_iso;
	                          th_val = holdersList[k][th_key];
	                          if(typeof th_val == "undefined" || th_val == null){
	                              th_val = '';
	                          }
	                          th_val = String(th_val).replace(/}/, '');
	                          th_val = String(th_val).replace(/{/, '');
	                       // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
	      					if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
	      						var calVal = isoCalMap.get(propList[j].prop_iso);
	      						if(typeof calVal !== "undefined"){
	      							var unit = calVal.split('$$')[0]; //연산자
	      							var val = calVal.split('$$')[1]; //값
	      							
	      							if(unit = "/"){
	      								th_val = Number((Number(th_val) / val).toFixed(4));
	      							}else if(unit = "*"){
	      								th_val = Number((Number(th_val) * val).toFixed(4));	
	      							}else if(unit = "+"){
	      								th_val = Number((Number(th_val) + val).toFixed(4));	
	      							}else if(unit = "-"){
	      								th_val = Number((Number(th_val) - val).toFixed(4));	
	      							}
	      						}
	      						//th_val = Number((Number(th_val) / 25.4).toFixed(4));
	                          }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
	      						var calVal = isoCalMap.get(propList[j].prop_iso);
	      						
	      						if(typeof calVal !== "undefined"){
	      							var unit = calVal.split('$$')[0]; //연산자							
	      							if(unit != ""){
	      		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
	      		                    }
	      						}
	                          }
	                          colGroupHtml += '<td class="center">' + th_val + '</td>';
					}				
				}//end of inner for
			}else{
				colGroupHtml += '<td class="">'+'N/A'+'</td>';	
			}
			colGroupHtml += '</tr>';
		}
	}// end of for
}// end of else inchYn 
			colGroupHtml += '</tbody>'
				
// 			console.log("colGroupHtml = " + colGroupHtml)
			$('#table_relHolders_pop').append(colGroupHtml);	
	 }else{
		 $('#holder_header_pop').hide()
		 $('#area_holders_pop').hide()
	 }
	
}

//Recommended Cutting Speeds 테이블 그리기
function createCuttingSpeedTable(cuttingSpeedList){
	 
	 $("#table_cuttingSpeeds_pop *").remove();
		
		if(cuttingSpeedList.length > 0){
			// <colgroup> 태그 START
			var colGroupHtml = '<colgroup><col width="10%"><col width="15%"><col width="15%"><col width="*"><col width="20%"></colgroup>';				
			colGroupHtml += '';
			// <colgroup> 태그 END			
				
			// <tr><th> 태그 START
																	/*ISO*/												/*Symbol*/												/*단위*/												/*사용범위*/											/*Grade*/
			colGroupHtml += '<tbody><tr><th data-lang="FR000122">'+LANG_SET['FR000122']+'</th><th data-lang="FR000123">'+LANG_SET['FR000123']+'</th><th data-lang="FR000058">'+LANG_SET['FR000058']+'</th><th data-lang="FR000232">'+LANG_SET['FR000232']+'</th><th data-lang="FR000041">'+LANG_SET['FR000041']+'</th>';
			// <tr><th> 태그 END			
			
			var pre_gubun = "";
			
			// cuttingSpeedList<tr><td> 태그 START
		 	for(var k=0; k<cuttingSpeedList.length; k++){ 		
				var rowHtml = '<tr>';
				var bgClass;
				if(cuttingSpeedList[k].gubun == 'P'){
					bgClass = 'isoP';
				}else if(cuttingSpeedList[k].gubun == 'M'){
					bgClass = 'isoM';
				}else if(cuttingSpeedList[k].gubun == 'K'){
					bgClass = 'isoK';
				}else if(cuttingSpeedList[k].gubun == 'N'){
					bgClass = 'isoN';
				}else if(cuttingSpeedList[k].gubun == 'S'){
					bgClass = 'isoS';
				}else if(cuttingSpeedList[k].gubun == 'H'){
					bgClass = 'isoH';
				}else if(cuttingSpeedList[k].gubun == 'Z'){
					bgClass = 'isoZ';
				}else if(cuttingSpeedList[k].gubun == 'V'){
					bgClass = 'isoV';
				}else if(cuttingSpeedList[k].gubun == 'I'){
					bgClass = 'isoI';
				}else if(cuttingSpeedList[k].gubun == 'E'){
					bgClass = 'isoE';
				}
				
				rowHtml += '<th class="' + bgClass +'"><span>' + cuttingSpeedList[k].gubun + '</span></th>';  // ISO
				rowHtml += '<td class="center">' + cuttingSpeedList[k].symbol + '</td>';  // Symbol

				if(cur_size_unit == "CC0002"){
					rowHtml += '<td class="center">' + cuttingSpeedList[k].unit2 + '</td>';  // mm
					rowHtml += '<td class="left">' + cuttingSpeedList[k].print_inch + '</td>';  // print INCH
				}else{
					rowHtml += '<td class="center">' + cuttingSpeedList[k].unit1 + '</td>';  // mm
					rowHtml += '<td class="left">' + cuttingSpeedList[k].print_mm + '</td>';  // print MM	
				}
				
				
				// 이전 row의 ISO(gubun)값이 현 row의 값과 같으면 표시 안함 처리
// 				var grade = "";
// 				if(pre_gubun !== cuttingSpeedList[k].gubun){
// 					grade = cuttingSpeedList[k].grade;
// 				}else{
// 					grade = "";
// 				}
				
				pre_gubun = cuttingSpeedList[k].gubun;	
				
				//Grade는 모두 동일한 값이므로 병합하여 1번만 표시
				var grade = cuttingSpeedList[0].grade;
				var rowSpan = cuttingSpeedList.length;
				if(k == 0){
					rowHtml += '<td class="center" rowspan="' + rowSpan + '">' + grade + '</td>';  // Grade	
				}
				
				colGroupHtml += rowHtml + '</tr>'; 
			}
			
		 	colGroupHtml += '</tbody>'
			// holdersList <tr><td> 태그 END
			$('#table_cuttingSpeeds_pop').append(colGroupHtml);	
		}else{
			$('#cutting_header_pop').hide()
			$('#area_cutting_pop').hide()
		}
	 
}
//Spare Parts 테이블 그리기
function createSparePartsTable(sparePartsList){
 	 
 $("#table_spareParts_pop *").remove();
	
	if(sparePartsList.length > 0){
		// <colgroup> 태그 START
		var colGroupHtml = '<colgroup><col width="120px"><col width="200px"><col width="80px"><col width="80px"><col width="80px"><col width="*"></colgroup>';				
		colGroupHtml += '';
		// <colgroup> 태그 END			
		
																												/*Order Number*/									/*Designation*/
// 			colGroupHtml += '<thead style="position:absolute; left:0; z-index:10;"><tr><th style="width:200px;">'+LANG_SET['FR000038']+'</th><th style="width:200px;">'+LANG_SET['FR000039']+'</th></tr>'
				
		// <tr><th> 태그 START
																/*Order Number*/									/*Designation*/											/*Unit*/											/*Quantity*/											/*Image*/						
		colGroupHtml += '<tbody><tr><th data-lang="FR000038">'+LANG_SET['FR000038']+'</th><th data-lang="FR000039">'+LANG_SET['FR000039']+'</th><th data-lang="FR000058">'+LANG_SET['FR000058']+'</th><th data-lang="FR000059">'+LANG_SET['FR000059']+'</th><th data-lang="FR000032">'+LANG_SET['FR000032']+'</th>';
		// <tr><th> 태그 END			
//어드민에서 인치사용 여부 on했을 때
if(inchYn =='Y'){
	// sparePartsList<tr><td> 태그 START
 	for(var k=0; k<sparePartsList.length; k++){ 
 		//bom_matnr이 공백일 경우는 자기자신이므로 그리지 않는다
		if(sparePartsList[k].bom_matnr !=''){
			var rowHtml = '<tr>';
			rowHtml += '<td>' + sparePartsList[k].bom_matnr + '</td>';  // Order Number 
			rowHtml += '<td class="left">' + sparePartsList[k].idnrk_hyung + '</td>';  // Designation
			rowHtml += '<td>' + sparePartsList[k].meins + '</td>';  // 단위
			rowHtml += '<td class="right">' + sparePartsList[k].menge + '</td>';  // 수량
			
			var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
			rowHtml += '<td><img src="' + imgUrl + '" alt="" style="height: 30px;"></td>';  // Image
			colGroupHtml += rowHtml + '</tr>'; 
 		}
	}
	
}else{
//어드민에서 인치사용 여부 off했을 때
	// sparePartsList<tr><td> 태그 START
 	for(var k=0; k<sparePartsList.length; k++){ 
 		if(sparePartsList[k].UNIT_CD !='CC0002'){
	 		//bom_matnr이 공백일 경우는 자기자신이므로 그리지 않는다
			if(sparePartsList[k].bom_matnr !=''){
				var rowHtml = '<tr>';
				rowHtml += '<td>' + sparePartsList[k].bom_matnr + '</td>';  // Order Number 
				rowHtml += '<td class="left">' + sparePartsList[k].idnrk_hyung + '</td>';  // Designation
				rowHtml += '<td>' + sparePartsList[k].meins + '</td>';  // 단위
				rowHtml += '<td class="right">' + sparePartsList[k].menge + '</td>';  // 수량
				
				var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
				rowHtml += '<td><img src="' + imgUrl + '" alt="" style="height: 30px;"></td>';  // Image
				colGroupHtml += rowHtml + '</tr>'; 
	 		}
 		}
	}//end of for
}// end of else inchYn		
	 	colGroupHtml += '</tbody>'
		// holdersList <tr><td> 태그 END
			
		$('#table_spareParts_pop').append(colGroupHtml);	
	}else{
		$('#spare_header_pop').hide()
		$('#area_spare_parts_pop').hide()
	}
	
	//스페어 파트가 없을때 해당부분 숨김
	if($('#table_spareParts_pop').length ==1){
		$('#spare_header_pop').hide()
		$('#area_spare_parts_pop').hide()
	}
	
}
// 3D Representation Basic 클릭
function go3dBasic(){
	$("#btn_3d_basic_pop").addClass("on");
	$("#btn_3d_detail_pop").removeClass("on");
	
	var matnr = itemInfo.MATNR;
	var designation = itemInfo.DESIGNATION;
	
	if(itStpBasicFileUrlPop !== ""){
		if(matnr !== "" && designation !== ""){
			get3dRendering(matnr, designation, 'B');	
		}
	}
	$('#div_stp_basic_pop').show();
	$('#div_stp_detail_pop').hide();
	
	// 다운로드 URL이 없으면 다운로드버튼 hide처리
	var basicHref = $('#it_stp_basic_file_url_pop').attr("href");
	if(basicHref !== undefined){
		$('#it_stp_basic_file_url_pop').show();
		$('#it_stp_detail_file_url_pop').hide();
	}
	
}

// 3D Representation Detailed 클릭 
function go3dDetailed(){
// 	alert("detail")
	$("#btn_3d_basic_pop").removeClass("on");
	$("#btn_3d_detail_pop").addClass("on");
	var matnr = itemInfo.MATNR;
	var designation = itemInfo.DESIGNATION;
	
	if(itStpDetailFileUrlPop !== ""){
		if(matnr !== "" && designation !== ""){
			get3dRendering(matnr, designation, 'D');	
		}	
	}
	$('#div_stp_basic_pop').hide();
	$('#div_stp_detail_pop').show();

	// 다운로드 URL이 없으면 다운로드버튼 hide처리
	var detailHref = $('#it_stp_detail_file_url_pop').attr("href");
	if(detailHref !== undefined){
		$('#it_stp_basic_file_url_pop').hide();
		$('#it_stp_detail_file_url_pop').show();
	}
}
//가격 format 표시
function numberFormatting(price, currency){
	var resultPrice;
// 	console.log(price);
	if(currency == 'KRW'){
		resultPrice = price;		
	}else{
		resultPrice = parseFloat(price).toFixed(2);
	}
// 	console.log(resultPrice);
	
	return resultPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
//3d rendering pop
function get3dRendering (viewer, title, gubun) {
	
	$('#img_3d_pop').css('display', 'none');
	
 	var url = "";
	if(gubun == "D") {
		url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
	} else if(gubun == "B") {
		url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
	}
	
	if(url != "") {
		if(gubun == "D"){
			$('#3d_rendering_detail_pop').attr('src', url);
// 			$('#3d_rendering_pop').attr('src', url);	
			$('#img_3d_basic_pop').css('display', 'block');
			$('#img_3d_detail_pop').css('display', 'none');
		}else if(gubun == "B"){
			$('#3d_rendering_basic_pop').attr('src', url);	
// 			$('#3d_rendering_pop').attr('src', url);
			$('#img_3d_basic_pop').css('display', 'none');
			$('#img_3d_detail_pop').css('display', 'block');
		}
	}
}
//P21파일 다운로드 클릭
function downP21File(eventCode, matnr){
	if(USERAUTH.isP21DownAuth){
		if(eventCode !=''){
			//로그 등록
			doLog(eventCode, matnr, '')
		}		
		if(itP21FileUrlPop !== ""){
			//dxf 파일 다운로드
			var file_nm = itP21FileUrlPop.split('/');
			file_nm = file_nm[file_nm.length-1];		
			$("#it_p21_file_url_pop").attr('href', itP21FileUrlPop);
			$("#it_p21_file_url_pop").attr('download', file_nm);
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#it_p21_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
	}	
}
//GTC파일 다운로드 클릭
function downGtcFile(eventCode, matnr){
	if(USERAUTH.isOneGtcDownAuth){
		if(eventCode !=''){
			//로그 등록
			doLog(eventCode, matnr, '')
		}	
		if(itGtcFileUrlPop !== ""){
			//dxf 파일 다운로드
			var file_nm = itGtcFileUrlPop.split('/');
			file_nm = file_nm[file_nm.length-1];							
			$("#it_gtc_file_url_pop").attr('href', itGtcFileUrlPop);
			$("#it_gtc_file_url_pop").attr('download', file_nm);
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#it_gtc_file_url_pop').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
	}	
}
function openLogin(matnr, eventCode){
	//로그 등록
// 	doLog(eventCode, matnr, '')
	var userId = USERINFO.user_id;
	if(userId == ""){
// 		$(".open_login").click();
		//로그인 후 다시 시도해주세요
		alert(LANG_SET["FR000159"]);
	}
}
//2d, 3d 다운로드 로그등록을 위해 추가
function doLogAtDownLoad(matnr, eventCode){
	if(eventCode !=''){
		doLog(eventCode, matnr, '')
	}
}
</script>
