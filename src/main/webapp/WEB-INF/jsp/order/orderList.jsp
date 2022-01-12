<%----------------------------------------------------------------------------------------
 - 파일이름	: orderList.jsp
 - 설    명	: 첫화면
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2021.08.25    1.0      SJY
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8" errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstKey"%>
<%@page import = "com.eaction.framework.business.logic.basket.model.BasketInfo"%>
<%@page import = "com.eaction.framework.business.logic.order.model.OrderInfo"%>
<%@page import = "com.eaction.framework.business.logic.order.constant.OrderConstKey"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
String deviceType = (String)request.getAttribute("deviceType");
String orderType = (String)request.getAttribute(OrderConstKey.ORDER_TYPE);
// OrderInfo orderInfo = (OrderInfo)request.getAttribute(OrderConstKey.LAST_ORDER_DATA);
// if (orderInfo == null) {
// 	orderInfo = new OrderInfo();
// }

Map<String, Object> map = (Map<String, Object>)request.getAttribute(OrderConstKey.ORDER_SELECT_LIST_DATA);
List<BasketInfo> basketList = (List<BasketInfo>)map.get("list");
String priceYN = (String)request.getAttribute("priceYN");
// String priceYN = "Y";
String stockYN = (String)request.getAttribute("stockYN");
// String stockYN = "Y";
int nTotal = (int)map.get("nTotal");
String k_basketTotalPrice = (String)map.get("k_basketTotalPrice");	// 총 장바구니 목록 합산금액 
String u_basketTotalPrice = (String)map.get("u_basketTotalPrice");	// 총 장바구니 목록 합산금액 
String e_basketTotalPrice = (String)map.get("e_basketTotalPrice");	// 총 장바구니 목록 합산금액 
String comm_curr = (String)map.get("comm_curr");	// 결과 통화코드
%>
<form id="cartExceldownForm" name="cartExceldownForm" method="POST" action="<%=ConstUrl.ORDER_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.ACTION_GET_FILE%>"/>
<!--     <input type="hidden" name="atch_file_url" id="atch_file_url" /> -->
    <input type="hidden" name="excel_file_path" id="excel_file_path" />
    <input type="hidden" name="atch_file_name" id="atch_file_name" />
</form>
<div id="container" class="cart_wrap">
    <div class="path">
        <div class="inner">
            <ul>
            	<%-- Home --%>
                <li class="home" data-lang="FR000137"><%=LangMng.LANG_D("FR000137", session_lang) %></li>
            	<%-- Cart --%>
                <li data-lang="FR000040"><%=LangMng.LANG_D("FR000040", session_lang) %></li>
            	<%--  --%>
                <li class="blue" data-lang="FR000216"><%=LangMng.LANG_D("FR000216", session_lang) %></li>
            </ul>
        </div>
    </div>
    <div id="contents">
        <div class="inner">
           	<form id="req-order-form" name="req-order-form">
            	<%-- Request --%>
	            <h2 data-lang="FR000216"><%=LangMng.LANG_D("FR000216", session_lang) %></h2>
	            <div class="cart_top">
	                <div class="check"> 
		            	<%--  data-lang="" --%>
		            	<%-- <%=LangMng.LANG_D("FR000004", session_lang) %> --%>
	                    <span>Order Information</span>
	                </div>
	            </div>
	            <div class="information">
		            <div class="cart_order">
		                <ul>
		                    <li>
		                        <div class="input">
		                        <%--
		                        1) userInfo
		                         --%>
		                         	<%-- Name --%>
		                            <label for="order_name"><span  data-lang="FR000017"><%=LangMng.LANG_D("FR000017", session_lang) %></span><!-- <span class="red">*</span> --></label>
		                            <input type="text" id="order_name" name="order_name" value="<%=userInfo.getUser_name() %>" readOnly>
		                        </div>
		                    </li>
		                    <li>
		                        <div class="input">
		                        <%--
		                        1) user_id 
		                         --%>
		                         	<%-- Email Address --%>
		                            <label for="order_user_id"><span  data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %></span><span class="red">*</span></label>
		                            <input type="text" id="order_user_id" name="order_user_id" value="<%=userInfo.getUser_id() %>" readonly>
								</div>
		                    </li>
		                    <li>
		                        <div class="input">
		                        <%--
		                        1) 최근주문내역 
		                        2) userInfo 
		                        2) input 
		                         --%>
		                         	<%-- Company --%>
		                            <label for="order_company"><span data-lang="FR000099"><%=LangMng.LANG_D("FR000099", session_lang) %></span><!-- <span class="red">*</span> --></label>
<%-- 		                        <% if(!"".equals(orderInfo.getCompany())) {%>
		                            <input type="text" id="order_company" name="order_company" value="<%=orderInfo.getCompany() %>" readOnly>
		                            <% } else if(!"".equals(userInfo.getUser_company())) { --%>
		                            <% if(!"".equals(userInfo.getUser_company())) { %>
		                            <input type="text" id="order_company" name="order_company" value="<%=userInfo.getUser_company() %>" readOnly>
		                            <%} else {%>
		                            <input type="text" id="order_company" name="order_company" placeholder="<%=LangMng.LANG_D("FR000201", session_lang) %>" data-lang="FR000201" data-langtype="placeholder">
		                            <%}%>
		                        </div>
		                    </li>
		                    <li>
		                        <div class="input">
		                        <%--
		                        1) 최근주문내역 
		                        2) userInfo 
		                        2) input 
		                         --%>
		                         	<%-- Company Email Address --%>
		                            <label for="order_cemail"><span data-lang="FR000100"><%=LangMng.LANG_D("FR000100", session_lang) %></span><!-- <span class="red">*</span> --></label>
<%-- 		                        <% if(!"".equals(orderInfo.getCemail())) {%>
		                            <input type="text" id="order_cemail" name="order_cemail" value="<%=orderInfo.getCemail() %>" readOnly>
		                            <% } else if(!"".equals(userInfo.getUser_com_mail())) { %>  --%>
		                            <% if(!"".equals(userInfo.getUser_com_mail())) { %>
		                            <input type="text" id="order_cemail" name="order_cemail" value="<%=userInfo.getUser_com_mail() %>" readOnly>
		                            <%} else {%>
		                            <input type="text" id="order_cemail" name="order_cemail" placeholder="<%=LangMng.LANG_D("FR000202", session_lang) %>" data-lang="FR000202" data-langtype="placeholder">
		                            <%}%>
								</div>
		                    </li>
		                    <li>
		                        <div class="input">
		                        <%--
		                        1) userInfo 
		                         --%>
		                         	<%-- Country --%>
		                            <label for="order_user_nation"><span data-lang="FR000176"><%=LangMng.LANG_D("FR000176", session_lang) %></span><!-- <span class="red">*</span> --></label>
		                            <input type="hidden" id="order_user_nation" name="order_user_nation" value="" readOnly>
		                            <% if(!"".equals(userInfo.getUser_nation())) {%>
		                            <input type="text" id="order_user_nation_v" name="order_user_nation_v" placeholder="<%=LangMng.LANG_D("FR000182", session_lang) %>" readOnly data-lang="FR000182" data-langtype="placeholder"/>
		                            <%} else {%>
		                            <%-- Mypage에서 국가를 입력바랍니다. --%>
		                            <input type="text" id="order_user_nation_v" name="order_user_nation_v" placeholder="<%=LangMng.LANG_D("FR000182", session_lang) %>" readOnly data-lang="FR000182" data-langtype="placeholder"/>
		                            <%}%>
								</div>
		                    </li>
		                </ul>
		            </div>
	                <div class="area">
                       	<%-- Request for Quotation --%>
	                    <span data-lang="FR000217"><%=LangMng.LANG_D("FR000217", session_lang) %></span>
	                    <textarea name="order_bigo" id="order_bigo" cols="30" rows="10"></textarea>
	                </div>
	                <div class="area">
                       	<%-- Attach the Microsoft excel file --%>
	                    <span data-lang="FR000177"><%=LangMng.LANG_D("FR000177", session_lang) %></span>
                        <div class="dropzone" id="fileDropzone">
                        </div>
	                </div>
                
	            </div>
	            <div class="area">
	          		<span></span>
                    <%-- Create Excel --%>
	                <a id="excelBtn" class="excel"><i class="fa fa-file-excel-o"></i><span data-lang="FR000080"><%=LangMng.LANG_D("FR000080", session_lang) %></span></a>
	            </div>
	            <div class="order_list pc">
	                <div class="listTable03" style="display: block;">
	                    <table>
	                        <colgroup>
	                            <col width="5%">
	                            <col width="15%">
	                            <col width="*">
	                            <col width="10%">
	                            <% if (!"N".equals(priceYN) ) {%>
	                            <col width="15%">
	                            <% } %>
	                            <col width="5%">
	                        </colgroup>
	                        <tbody>
	                            <tr>
				                    <%-- NO --%>
	                                <th data-lang="FR000144"><%=LangMng.LANG_D("FR000144", session_lang) %></th>
	                                <% if (orderType.equals("CART")) {  %>
				                    <%-- Cart Name --%>
	                                <th data-lang="FR000070"><%=LangMng.LANG_D("FR000070", session_lang) %></th>
				                    <%-- Cart Description --%>
	                                <th data-lang="FR000071"><%=LangMng.LANG_D("FR000071", session_lang) %></th>
	                                <% } else {%>
				                    <%-- Assembly 그룹명 --%>
	                                <th data-lang="FR000132"><%=LangMng.LANG_D("FR000132", session_lang) %></th>
				                    <%-- Assembly Description--%>
	                                <th data-lang="FR000141"><%=LangMng.LANG_D("FR000141", session_lang) %></th>
	                                <% }%>
				                    <%-- Quantity --%>
	                                <th class="text-right" data-lang="FR000059"><%=LangMng.LANG_D("FR000059", session_lang) %></th>
		                            <% if (!"N".equals(priceYN) ) {%>
				                    <%-- Total --%>
	                                <th class="text-right" data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></th>
		                            <% } %>
				                    <%-- Action --%>
	                                <th data-lang="FR000145"><%=LangMng.LANG_D("FR000145", session_lang) %></th>
	                            </tr>
<%
	int index_p = 0;
	String totalPrice = "";
	for(BasketInfo info : basketList) {
		index_p++;
		if ("CC0076".equals(comm_curr) ) {	// "KRW"
			totalPrice = info.getK_kbetr();
		} else if ("CC0078".equals(comm_curr) ) {	// "EUR"
			totalPrice = info.getE_kbetr();
		}else {
			totalPrice = info.getU_kbetr();
		}
%>
	                            <tr class="c_basket basket_<%=info.getCart_no()%>">
	                                <td><%= index_p %></td>
	                                <td class="left"><strong class="blue"><%=info.getCart_nm() %></strong></td>
	                                <td class="left"><%=info.getCart_desc() %></td>
	                                <td class="right"><%=info.getQty() %></td>
		                            <% if (!"N".equals(priceYN) ) {%>
	                                <td class="right"><strong class="basket_price" data-pric="<%= totalPrice %>"></strong> <span><%-- session currency 표시할 영역 --%><%= comm_curr %></span></td>
		                            <% } %>
	                                <td>
	                                    <a class="deleteCart"><img src="<%=PATH_IMG %>/ico_delete.png" alt=""></a>
	                                </td>
	                            </tr>
<%	}%>
	                        </tbody>
	                    </table>
	                </div>
	                <div class="total_area">
<% if (!"N".equals(priceYN) ) {%>
	                    <%-- TOTAL --%>
	                    <div class="total"><span data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></span> : <span class="nTotal"><%= nTotal %></span></div>
	                    <table>
	                        <colgroup>
	                            <col width="*">
	                            <col width="15%">
	                            <col width="5%">
	                        </colgroup>
	                        <tbody>
	                            <tr>
				                    <%-- Total --%>
	                                <td data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></td>
	                                
	                                <td>
	                                	<strong class="totalPrice" data-pric=""></strong>
	                                	<span>
	                                		<%-- session currency 표시할 영역 --%>
	                                		<%= comm_curr %>
	                                	</span>
                                	</td>
	                                <td></td>
	                            </tr>
	                        </tbody>
	                    </table>
<% } else {%>
	                    <%-- TOTAL --%>
	                    <table>
	                        <colgroup>
	                            <col width="*">
	                        </colgroup>
	                        <tbody>
	                            <tr>
	                                <td>
				                    	<div class="total" style="top: 10px;"><span data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></span> : <span class="nTotal"><%= nTotal %></span></div>
				                    </td>
	                            </tr>
	                        </tbody>
	                    </table>
<% }%>
	                </div>
	            </div>
	            <div class="order_list mobile">
	                <div class="listTable03" style="display: block;">
<%
	int index_m = 0;
	totalPrice = "";
	for(BasketInfo info : basketList) {
		index_m++;
		if ("CC0076".equals(comm_curr) ) {	// "KRW"
			totalPrice = info.getK_kbetr();
		} else if ("CC0078".equals(comm_curr) ) {	// "EUR"
			totalPrice = info.getE_kbetr();
		}else {
			totalPrice = info.getU_kbetr();
		}
%>
	                    <table>
	                        <colgroup>
	                            <col width="30%">
	                            <col width="*">
	                        </colgroup>
	                        <tbody class="c_basket basket_<%=info.getCart_no()%>">
	                            <tr>
				                    <%-- NO --%>
	                                <th data-lang="FR000144"><%=LangMng.LANG_D("FR000144", session_lang) %></th>
	                                <td><%= index_m %></td>
	                            </tr>
	                            <tr>
				                    <%-- Cart NO --%>
	                                <th data-lang="FR000175"><%=LangMng.LANG_D("FR000175", session_lang) %></th>
	                                <td><strong class="blue"><%= info.getCart_no() %></strong></td>
	                            </tr>
	                            <tr>
				                    <%-- Cart Description --%>
	                                <th data-lang="FR000071"><%=LangMng.LANG_D("FR000071", session_lang) %></th>
	                                <td><%= info.getCart_desc() %> </td>
	                            </tr>
	                            <tr>
				                    <%-- Quantity --%>
	                                <th data-lang="FR000059"><%=LangMng.LANG_D("FR000059", session_lang) %></th>
	                                <td><%=info.getQty() %></td>
	                            </tr>
<% if (!"N".equals(priceYN) ) {%>
	                            <tr>
				                    <%-- Total --%>
	                                <th data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></th>
	                                <td>
	                                    <strong class="basket_price" data-pric="<%= totalPrice %>"></strong>
	                                    <span><%-- session currency 표시할 영역 --%><%= comm_curr %></span>
	                                    <a class="deleteCart"><img src="<%=PATH_IMG %>/ico_delete.png" alt=""></a>
	                                </td>
	                            </tr>
<% } else { %>
	                            <tr>
	                                <td colspan="2">
	                                    <a class="deleteCart" style="position: unset;text-align: center;display: block;"><img src="<%=PATH_IMG %>/ico_delete.png" alt=""></a>
	                                </td>
	                            </tr>
<% } %>
	                        </tbody>
	                    </table>
<% } %>
	                </div>
	                <div class="total_area">

<% if (!"N".equals(priceYN) ) { %>
	                    <%-- TOTAL --%>
	                    <div class="total"><span data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></span> : <span class="nTotal"><%= nTotal %></span></div>
	                    <table>
	                        <colgroup>
	                            <col width="*">
	                            <col width="40%">
	                        </colgroup>
	                        <tbody>
	                            <tr>
				                    <%-- Total --%>
	                                <td data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></td>
	                                <td>
	                                	<strong class="totalPrice" data-pric=""></strong>
	                                	<span>
	                                		<%-- session currency 표시할 영역 --%>
	                                		<%= comm_curr %>
	                                	</span>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
<% } else {%>
	                    <%-- TOTAL --%>
	                    <table>
	                        <colgroup>
	                            <col width="*">
	                        </colgroup>
	                        <tbody>
	                            <tr>
	                                <td>
	                                	<div class="total" style="top: 10px;"><span data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></span> : <span class="nTotal"><%= nTotal %></span></div>
	                                </td>
	                            </tr>
	                        </tbody>
	                    </table>
<% } %>
	                </div>
	            </div>
	            <div class="agreement">
                    <%-- Agreement Check Post --%>
<%-- 	                <strong data-lang="FR000179"><%=LangMng.LANG_D("FR000179", session_lang) %></strong> --%>
	                <div class="conditions">
	                    <label class="chkN">
		                    <%-- I accept the Term of use and Privacy Polish.	undefined	I accept the Term of use and Privacy Polish. --%>
	                        <input type="checkbox" id="showChk"><span data-lang="FR000022"><%=LangMng.LANG_D("FR000022", session_lang) %></span>
	                        <input type="checkbox" id="order_agree" name="order_agree" />
	                    </label>
	                </div>
	            </div>
	            <div class="btn_group">
	                <div class="center">
	                    <%-- Place Your Order --%>
	                    <a id="submit_req_order" class="btn02 order" data-lang="FR000217"><%=LangMng.LANG_D("FR000217", session_lang) %></a>
<!-- 	                    <a onclick="alert('작업중')" class="btn02 order">Place Your Order</a> -->
	                </div>
	            </div>
           	</form>
        </div>
    </div>
</div>
<style>
input:read-only {
    background: #e6e6e6;
}
input:read-only:focus {
	border: 1px solid #a8b7be !important;
}
.cart_wrap #contents {
	padding-bottom: 50px;
}
.invalid.red{
	position: absolute;
}
.agreement .chkN {
	position: relative;
}
em#order_agree-error {
    bottom: -23px;
    left: 0;
}
.order_list > .total_area > table > tbody a:hover {
	text-decoration: underline;
}
.order_list > .total_area > table > tbody td:last-child {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.cart_wrap .area textarea {	/*cli req 210927*/
	height: 300px;
}
/*dropzone*/
.dropzone {
	height: 210px; 
	min-height: 210px; 
    border: 1px solid #a8b7be;
    border-radius: 0;
}
div.dz-default.dz-message span {
    font-size: 25px;
    color: #999;
    bottom: 40px;
    position: absolute;
    transform: translateX(-50%);
}
.cart_wrap .agreement .conditions {
    border-left: none;
}
/* 반응형 */
@media only screen and (max-width: 961px) {
	.cart_wrap #contents {
		padding-bottom: 70px;
	}
}

@media only screen and (max-width: 761px) {
	.cart_wrap #contents {
		padding-bottom: 100px;
	}
	.cart_wrap .information .cart_order > ul > li {
		width: 96%;
	}
}

@media only screen and (max-width: 461px) {
}

</style>
<script type="text/javascript">
var errorClass = 'invalid red';
var errorElement = 'em';

var $cartExceldownForm = $('#cartExceldownForm')
var ExcelOK = false;
var excel_file_path = "";	// create excel 1회 수행했으면 !""
var file_name = "";
var orderType = "<%= orderType %>";	<%-- "CART" // "ASSEMBLY" --%>
var nTotal = <%= nTotal %>;	// 선택 장바구니 총개수 (초기)
var totalPrice = 0;	// 선택 장바구니 총합계액 (초기)
var cart_no_arr = [];	// 선택 장바구니 번호 (초기)
// var fin_cart_no_arr = cart_no_arr;	// 변경된 최종 장바구니번호 배열
var fin_cart_no_arr = [];	// 변경된 최종 장바구니번호 배열
var curr_info_arr = [];		// 통화 변경시 마다 금액&통화 다르게 표시하기 위해 담아두는 배열
<% for(BasketInfo info : basketList) { %>
<%-- 상단 통화변경시 장바구니 목록에 표시할 금액&총합계액을 바꾸기 위함 --%>
curr_info_arr.push({
	"cart_no" : "<%= info.getCart_no()%>", 
	"k_kebtr" : "<%= info.getK_kbetr()%>", 
	"u_kebtr" : "<%= info.getU_kbetr()%>", 
	"e_kebtr" : "<%= info.getE_kbetr()%>",
	"k_basketTotalPrice" : "<%= k_basketTotalPrice%>",
	"u_basketTotalPrice" : "<%= u_basketTotalPrice%>",
	"e_basketTotalPrice" : "<%= e_basketTotalPrice%>"
	});
cart_no_arr.push(<%=info.getCart_no()%>);
fin_cart_no_arr.push(<%=info.getCart_no()%>);
<%-- totalPrice += Number(<%= StringUtil.nvl(info.getTotal_price(), "0")%>); --%>
<% } %>
if ("CC0076" == "<%= comm_curr %>") {	// KRW
	totalPrice = <%= k_basketTotalPrice %>;
} else if ("CC0078" == "<%= comm_curr %>") {	// EUR
	totalPrice = <%= e_basketTotalPrice %>;
} else {	// USD
	totalPrice = <%= u_basketTotalPrice %>;
}
var cart_no_string = "";			// "N1,N2"로 전송할 카드번호 joinString

// console.log("(LOAD PAGE)cart_no_string ==> " + cart_no_string);
// 카트 금액 ","설정 (0,000,000)
var $basket_price = $('.basket_price');
for(var i = 0; i < $basket_price.length; i++) {
	$basket_price.eq(i).text(numberFormatting($basket_price.eq(i).data("pric"), "<%= comm_curr %>"));
}
//주문서 총액 초기
$('.totalPrice').data("pric", totalPrice);	<%-- 원본금액은 data-pric --%>
// 주문서 총금액 ","설정 (0,000,000)
var $totalPrice = $('.totalPrice');
for(var i = 0; i < $totalPrice.length; i++) {
	$totalPrice.eq(i).text(numberFormatting($totalPrice.eq(i).data("pric"), "<%= comm_curr %>"));
}
Dropzone.autoDiscover = false;			// [Error Fix] Uncaught Error: Dropzone already attached.

jQuery(document).ready(function(){
	// 로그인 정보 없을 경우 메인화면으로 이동
	if (USERINFO.user_id == "") {
<%-- 		alert('<%=LangMng.LANG_D("FR000159", session_lang) %>') --%>
		alert(LANG_SET.FR000159)
		window.location.href = '/app/main.do';
	}
/* 코드변경시(공통) */
	// 국가변경
//     $("#btnNation").change(function(){
//     	cur_nation = this.value;
//     	// Country 필드 국가명칭 표시 
//     	$('#order_user_nation_v').val($('#mypage-form .nationResult a[data-schntn="'+ USERINFO.user_nation +'"]').text());
//     });
    
    // 언어변경
    $("#btnLang").change(function(){
    	cur_lang = this.value;
    	$("#req-order-form em.invalid").remove();
    	
    });

    // 길이단위변경
    $("#btnSizeUnit").change(function(){
    	cur_size_unit = this.value;
    });

    // 통화변경
    $("#btnCurr").change(function(){
    	cur_currency = this.value;
    	if ($('.c_basket').length == 0) return;
    	// 통화 변경시 장바구니 금액&통화 refresh
    	selectOrderCartList(cur_currency);
    });
    
    // 모바일 통화변경
    $('#mbtnCurr').change(function() {
    	cur_currency = this.value;
    	if ($('.c_basket').length == 0) return;
    	// 통화 변경시 장바구니 금액&통화 refresh
    	selectOrderCartList(cur_currency);
	})
	
/* 코드변경시 ------------ */    
	// Country 필드 국가명칭 표시 
	$('#order_user_nation_v').val($('#mypage-form .nationResult a[data-schntn="'+ USERINFO.user_nation +'"]').text());
	
	// session currency 코드 > 통화명 설정 (PC)
	$('.order_list.pc .c_basket .basket_price, .totalPrice').siblings('span').text(" " + $('#btnCurr option[value="<%= ConfigMng.getCurrency(request) %>"]').text());
	// session currency 코드 > 통화명 설정 (Mobile)
	$('.order_list.mobile .c_basket .basket_price, .totalPrice').siblings('span').text(" " + $('#mbtnCurr option[value="<%= ConfigMng.getCurrency(request) %>"]').text());
	
	// 휴지통아이콘 클릭 (선택 장바구니 삭제처리)
	$(".deleteCart").bind("click", function(){
		var cart_no_trEle = $(this).parents('.c_basket');
		var cur_currency = $('#btnCurr').val();
		<%if (!"P".equals(deviceType)) {%>
		cur_currency = $('#mbtnCurr').val();
		<%} %> 
		<%-- 해당 장바구니를 주문에서 제외합니다. --%>
		if(confirm('해당 장바구니를 주문에서 제외합니다.')){
			doDeleteCart(cart_no_trEle, cur_currency);
		}
	});
	
	// [Place Your Order]클릭
	$("#submit_req_order").bind("click", function(){
		$("#req-order-form").submit();
	});
	
	// hidden check 처리
	$('#showChk').bind("click", function() {
		$('#order_agree').prop("checked", $(this).is(":checked"));
	});
	
	// [Create Excel] 클릭
	$("#excelBtn").bind("click", function() {
		// 엑셀 다운 권한 검증
		if (!USERAUTH.isExcelDownAuth) {
			alert(LANG_SET.FR000229);
			return;
		}
		doCreateExcel();
	});

	$('.loading').delay('1000').fadeOut();
});

// dropzone 파일 드래그앤드롭
var myDropzone = new Dropzone(".dropzone", {
    url: "<%=ConstUrl.ORDER_URL%>?v=" + (new Date().getTime()),
    method: "post",
    autoProcessQueue: false, //자동으로 보내기 방지
    paramName: 'newFile',
<%--     dictDefaultMessage: 'Drop files here to upload', --%>
<%--     dictDefaultMessage: '<%=LangMng.LANG_D("FR000228", session_lang) %>', --%>
    dictDefaultMessage: LANG_SET.FR000228,
    parallelUploads: 99,
    maxFilesize: 10, // MB
    addRemoveLinks: true, // 삭제버튼 표시 여부
    dictRemoveFile: 'X', // 삭제버튼 표시 텍스트
    uploadMultiple: true,
	init: function() {
		var myDropzone = this;
        myDropzone.processQueue();
	}
});

/*dropzone에 이미지*/
$('div.dz-default.dz-message > span').show(); // Show message span
$('div.dz-default.dz-message').css(
		{'height': '210px',
		 'margin-top': '0',
		 'margin': '0',
		 'opacity':1,
		 'background-image': 'url(<%=UPLOAD_ROOT_PATH %>/img/img_upload.png)',
		 'background-repeat': 'no-repeat',
	     'background-position': '50% 30px',
	     'background-size': '80px'
		 });
// 전송요청
$("#req-order-form").validate({
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
	// Rules for form validation
	rules : {
// 		order_name : {
// 			required: true,
// 		},
		order_user_id : {
			required: true,
		},
// 		order_company : {
// 			required: true,
// 		},
// 		order_cemail : {
// 			required: true,
// 		},
		order_user_nation : {
			required: true,
		},
		order_agree : {
			required: true,
		},
	},
	// Messages for form validation
	messages : {
		<%-- 이름을 입력해주세요. --%>
// 		order_name : {
<%-- 			required: '<%=LangMng.LANG_D("FR000024", session_lang) %>' --%>
// 			required: LANG_SET.FR000024
// 		},
		<%-- 이메일을 입력해주세요. --%>
<%-- 			required: '<%=LangMng.LANG_D("FR000013", session_lang) %>' --%>
		order_user_id : {
			required: LANG_SET.FR000013 
		},
		<%-- 회사명을 입력해주세요. --%>
// 		order_company : {
<%-- 			required: '<%=LangMng.LANG_D("FR000181", session_lang) %>' --%>
// 			required: LANG_SET.FR000181
// 		},
		<%-- 회사이메일을 입력해주세요. --%>
// 		order_cemail : {
<%-- 			required: '<%=LangMng.LANG_D("FR000013", session_lang) %>' --%>
// 			required: LANG_SET.FR000013
// 		},
		<%-- Mypage에서 국가를 입력바랍니다. --%>
<%-- 			required: '<%=LangMng.LANG_D("FR000225", session_lang) %>' --%>
		order_user_nation : {
			required: LANG_SET.FR000225
		},
		<%-- 동의 체크를 해주세요. --%>
<%-- 			required: '<%=LangMng.LANG_D("FR000179", session_lang) %>' --%>
		order_agree : {
			required: LANG_SET.FR000179 
		},
	},
	submitHandler: function() {
//	$.each($("#newFile3")[0].files, function(index, file){
//		console.log($("#newFile3")[0].files[index]);
//	})
//	console.log(myDropzone.files);
//	console.log($('#order_newFile').val())
		
		<%-- 주문요청서를 전송하시겠습니까? --%>
<%-- 		if(confirm('<%=LangMng.LANG_D("FR000183", session_lang) %>')){ --%>
		if(confirm(LANG_SET.FR000183)){
			cart_no_string = "";	// 이 전에 += 수행한 것 초기화
			for(var i = 0; i < fin_cart_no_arr.length; i++) {
				cart_no_string += fin_cart_no_arr[i];
				if((i+1)<fin_cart_no_arr.length) {
					cart_no_string += ",";
				}
			}
// 			console.log("(submit)cart_no_string ==> " + cart_no_string);

			var param = new FormData();
			param.append("actionID", "<%=OrderConstKey.ORDER_INSERT_OK%>");
			if (orderType == "CART") {
				param.append("cart_no", cart_no_string);
			} else {
				param.append("assem_no", cart_no_string);
			}
// 			param.append("sur_name", $('#order_sur_name').val());
			param.append("name", $('#order_name').val());
			param.append("email", $('#order_user_id').val());
			param.append("company", $('#order_company').val());
			param.append("cemail", $('#order_cemail').val());
// 			param.append("user_nation", $('#btnNation option:selected').text());
			param.append("user_nation", USERINFO.user_nation);
			param.append("bigo", $('#order_bigo').val());
       		
// 			param.append("first_excel", excel_file_path == "" ? "Y" : "N");
// 			param.append("excel_file_path", excel_file_path);

//			$.each($("#newFile3")[0].files, function(index, file){
			$.each(myDropzone.files, function(index, file){
				param.append("newFile["+index+"]", myDropzone.files[index]);
			});

	       	$.ajax({
	       		type: "POST",
	       		url: "<%=ConstUrl.ORDER_URL%>?v=" + (new Date().getTime()),
	       		data: param,
	       		dataType: "json",
	       		async: true,
	       		cache: false,        
	       		contentType: false,
	            processData: false,  
	            enctype: 'multipart/form-data',
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
					$('#loadbar').show();
				}, success: function(e) {
//		       			console.log(e);
	       			if(e.resCode == "0") {
	       				<%-- 주문요청서를 전송하였습니다. --%>
						alert('<%=LangMng.LANG_D("FR000184", session_lang) %>');
						window.history.back();
	       			} else if(e.resCode == "1") {
	       				<%-- 주문요청 이메일 발송 중 오류가 발생했습니다. --%>
<%-- 						alert('<%=LangMng.LANG_D("FR000220", session_lang) %>'); --%>
						alert(LANG_SET.FR000220);
	       			} else if(e.resCode == "2") {
	       				<%-- 주문요청 중 오류가 발생했습니다. --%>
<%-- 						alert('<%=LangMng.LANG_D("FR000219", session_lang) %>'); --%>
						alert(LANG_SET.FR000219);
	       			} else {
						alert(e.resMsg);
	       			}
	       		}, error: function() {
	       		},
				// Ajax 통신 완료 후 로딩숨김
				complete:function(){
					cart_no_string = "";
// 					console.log("(submit complete)cart_no_string ==> " + cart_no_string);
					$('#loadbar').fadeOut();
				} 
			});
		}
	},
});	// END 전송요청


// 통화변경으로 인한 장바구니 목록 새로고침
function selectOrderCartList(cur_currency) {
// 	var order_currency = $('#btnCurr').val();
	var order_currency = cur_currency;
	totalPrice = 0;	// 전체금액 처리는 반복문 밖에 처리
	if (order_currency == "CC0076") {	// KRW
// 		totalPrice = curr_info_arr[0].k_basketTotalPrice;	// 소수점이 실종되어 반복문 계산처리
		curr_info_arr.forEach(function(index, value, array) {
			totalPrice += parseFloat(index.k_kebtr)
		})
	} else if (order_currency == "CC0078") {	// EUR
		curr_info_arr.forEach(function(index, value, array) {
			totalPrice += parseFloat(index.e_kebtr)
		})
	} else {	// USD	
		curr_info_arr.forEach(function(index, value, array) {
			totalPrice += parseFloat(index.u_kebtr)
		})
	}
	curr_info_arr.forEach(function(index, value, array) {
		var basket = index;
// 		console.log(index);
		if (order_currency == "CC0076") {	// KRW
// 			console.log("KRW");
			// 장바구니 data-pric
			$('.basket_' + basket.cart_no + ' .basket_price').data("pric", basket.k_kebtr);
			// 장바구니 금액
			$('.basket_' + basket.cart_no + ' .basket_price').text(numberFormatting(basket.k_kebtr, order_currency));
			/* [deleteCart 한 장바구니인지 체크]
			   .indexOf() : 배열 안에서 찾으려는 값(searchElement)과 정확하게 일치(===)하는'첫번째' element의 index를 리턴
			                            찾으려는 값이 배열에 없으면 -1을 리턴
			 */
			if (fin_cart_no_arr.indexOf(parseInt(basket.cart_no)) == -1) {
				totalPrice = totalPrice - basket.k_kebtr
			}
			// 총 장바구니 data-pric
			$('.totalPrice').data("pric", totalPrice);
			// 총 장바구니 금액
			$('.totalPrice').text(numberFormatting(totalPrice, order_currency));
		} else if (order_currency == "CC0078") {	// EUR
//			console.log("EUR");
			// 위 KRW 주석참고 
			$('.basket_' + basket.cart_no + ' .basket_price').data("pric", basket.e_kebtr);
			$('.basket_' + basket.cart_no + ' .basket_price').text(numberFormatting(basket.e_kebtr, order_currency));
			if (fin_cart_no_arr.indexOf(parseInt(basket.cart_no)) == -1) {
				totalPrice = totalPrice - basket.e_kebtr
			}
			$('.totalPrice').data("pric", totalPrice);
			$('.totalPrice').text(numberFormatting(totalPrice, order_currency));
		} else {	// USD
//			console.log("USD");
			// 위 KRW 주석참고 
			$('.basket_' + basket.cart_no + ' .basket_price').data("pric", basket.u_kebtr);
			$('.basket_' + basket.cart_no + ' .basket_price').text(numberFormatting(basket.u_kebtr, order_currency));
			if (fin_cart_no_arr.indexOf(parseInt(basket.cart_no)) == -1) {
				totalPrice = totalPrice - basket.u_kebtr
			}
			$('.totalPrice').data("pric", totalPrice);
			$('.totalPrice').text(numberFormatting(totalPrice, order_currency));
		}
	})
	// 장바구니 통화단위 
	// 총 장바구니 통화단위
// 	$('.c_basket .basket_price, .totalPrice').siblings('span').text(" " + $('#btnCurr option[value="'+order_currency+'"]').text());
	
	

	//  총 장바구니 통화단위(PC)
	$('.c_basket .basket_price, .totalPrice').siblings('span').text(" " + $('#btnCurr option[value="'+order_currency+'"]').text());
	// 총 장바구니 통화단위(Mobile)
	$('.c_basket .basket_price, .totalPrice').siblings('span').text(" " + $('#mbtnCurr option[value="'+order_currency+'"]').text());
	
}	// END selectOrderCartList


// 휴지통아이콘 클릭 (선택 장바구니 삭제처리)
function doDeleteCart(cart_no_trEle, cur_currency){	// <tr> 행 매개변수
	var order_currency = $('#btnCurr').val();
	var order_currency = cur_currency;
	var cart_no = cart_no_trEle.attr('class').split("_")[2]
	var cart_price = cart_no_trEle.find('.basket_price').data("pric");
	// 엑셀 flag ==> N
	ExcelOK = false;
	excel_file_path = "";
	// 장바구니 총수량
	$('.total .nTotal').text(--nTotal);
	// 금액계산
	totalPrice -= cart_price;	// 제거 된 장바구니 금액을 총액에서 감액 
	$('.totalPrice').text(numberFormatting(totalPrice, order_currency));
	
	// 처리 후 해당 카트 행 숨김
 	cart_no_trEle.remove();
	$('.basket_' + cart_no).remove();
	
	// ajax setting param (fin_cart_no_arr) 배열 해당요소 삭제
	fin_cart_no_arr.forEach(function(value,index,array){
		if (parseInt(value)==parseInt(cart_no)) array.splice(index, 1);
	})
	
	// 장바구니를 모두 제거한 경우 없음표시
	if($('.deleteCart').length == 0) {
		<%-- 선택된 장바구니가 없습니다. --%>		
		$('.order_list.pc .listTable03 table > tbody ').append('<tr><td colspan="6" data-lang="FR000218"><%=LangMng.LANG_D("FR000218", session_lang) %></td></tr>')
		$('.order_list.mobile .listTable03').append('<table><tr><td colspan="2" style="text-align: center" data-lang="FR000218"><%=LangMng.LANG_D("FR000218", session_lang) %></td></tr></table>')
	}
	
	// 
	for(var i = 0; i < $('.order_list.pc .c_basket td:first-child').length; i++) {
		var pcEle = $('.order_list.pc .c_basket td:first-child')[i]
		var mobileEle = $('.order_list.mobile .c_basket tr:first-child td')[i]
		$(pcEle).text(i+1)
		$(mobileEle).text(i+1)
	}
};	// doDeleteCart END

// 주문서 엑셀
function doCreateExcel() {
	for(var i = 0; i < fin_cart_no_arr.length; i++) {
		cart_no_string += fin_cart_no_arr[i];
		if((i+1)<fin_cart_no_arr.length) {
			cart_no_string += ",";
		}

	};
// 	console.log("(doCreateExcel)cart_no_string ==> " + cart_no_string);
	var param;
	if (orderType == "CART") {
	  	param = {
	 		actionID		: "<%=OrderConstKey.ORDER_CART_LIST_EXCEL_OK%>",
	     	cart_no			: cart_no_string,
	     	first_excel		: excel_file_path == "" ? "Y" : "N",
	 		excel_file_path	: excel_file_path
	   	};
	} else {
	  	param = {
	 		actionID		: "<%=OrderConstKey.ORDER_CART_LIST_EXCEL_OK%>",
	     	assem_no		: cart_no_string,
	     	first_excel		: excel_file_path == "" ? "Y" : "N",
	 		excel_file_path	: excel_file_path
	   	};
	}
   	$.ajax({
   		type: "POST",
   		url: "<%=ConstUrl.ORDER_URL%>?v=" + (new Date().getTime()),
   		data: param,
   		dataType: "json",
   		async: true,
   		cache: false,
		// Ajax 통신 전 로딩표시
        beforeSend:function(){
			$('#loadbar').show();
		}, success: function(e) {
//   			console.log(e);
   			if(e.resCode == "0") {
// 				alert(e.resMsg);
// 			   	console.log("upload Path here ============")
// 				console.log(e.resData)
// 				console.log("upload Path here ============")
				excel_file_path = e.resData;	// 물리주소 
				file_name = excel_file_path.split("\\");
				file_name = file_name[file_name.length - 1];	// \단위로 분리한 물리주소 마지막 값은 파일명 
				ExcelOK = true;
// 				console.log("file_name ==>" + file_name);
// 				console.log("excel_file_path ==>" + excel_file_path);
   			} else {
				alert(e.resMsg);
   			}
   		}, error: function() {
   		},
		// Ajax 통신 완료 후 로딩숨김
		complete:function(e){
			cart_no_string = "";
			// 파일 다운로드
			$("#excel_file_path", $cartExceldownForm).val(excel_file_path);
			$("#atch_file_name", $cartExceldownForm).val(file_name);
			goPageTwo(cartExceldownForm, "<%=ConstKey.ACTION_GET_FILE%>");
			
			$('#cartExceldownForm').actionID = "";
			// 파일 다운로드---------------------------------

			$('#loadbar').fadeOut();
		} 
	});
};	// doCreateExcel END

// 생성된 주문서 파일 다운로드
function doExcelFileDown() {
	$("#excel_file_path", $cartExceldownForm).val(excel_file_path);
	$("#atch_file_name", $cartExceldownForm).val(file_name);
	goPageTwo(cartExceldownForm, "<%=ConstKey.ACTION_GET_FILE%>");
	
	
	$('#cartExceldownForm').actionID = "";
}	// doExcelFileDown()

//가격 format 표시
function numberFormatting(price, currency){
	var resultPrice;
// 	console.log(price);
	if(currency == 'CC0076'){
		resultPrice = price;		
	}else{
		resultPrice = parseFloat(price).toFixed(2);
	}
// 	console.log(resultPrice);
	
	return resultPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}	// END numberFormatting
</script>
