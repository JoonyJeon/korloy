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
<%@page import = "java.lang.String"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.basket.constant.BasketConstKey"%>
<%@page import = "com.eaction.framework.business.logic.basket.model.BasketInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
	String devType = (String) request.getAttribute("deviceType");
	String rootPath = (String) ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
%>

<style>
#contents .expand {
    display: block;
    background: #fff url(.<%=PATH_IMG%>/bg_expand.png) no-repeat right 15px bottom 15px;
    background-size: 20px;
    height: 400px;
}

#contents .expand_3d {
    display: block;
    background: #fff url(.<%=PATH_IMG%>/bg_expand.png) no-repeat right 15px bottom 15px;
    background-size: 20px;
    height: 500px;
}

.cart_wrap .btn_cart a.open_cart.on {
	background-image: url(../img/btn_cart_close_white.png);
	border: 1 px solid #0094da;
    background-color: #0094da;
    color: #fff;
}
</style>


<form id="cartItemExceldownForm" name="cartItemExceldownForm" method="POST" action="<%=ConstUrl.BASKET_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.ACTION_GET_FILE%>"/>
    <input type="hidden" name="excel_file_path" id="excel_file_path" />
    <input type="hidden" name="atch_file_name" id="atch_file_name" />
</form>
<div id="container" class="cart_wrap assembly">
     <div class="path">
         <div class="inner">
             <ul>
             	 <%-- Home --%>
                 <li class="home" data-lang="FR000137"><%=LangMng.LANG_D("FR000137", session_lang) %></li>
                 <%-- Cart --%>
                 <li data-lang="FR000040"><%=LangMng.LANG_D("FR000040", session_lang) %></li>
             </ul>
         </div>
     </div>
     <div id="contents">
         <div class="inner">
        	 <%-- Cart --%>
             <h2 data-lang="FR000040"><%=LangMng.LANG_D("FR000040", session_lang) %><span style="display:inline-flex; padding-left:15px;"><span data-lang="FR000061" style="padding-left:5px;"><%=LangMng.LANG_D("FR000061", session_lang) %></span><span id="cartCnt" style="padding-left:5px;"></span><span data-lang="FR000062" style="padding-left:5px;"><%=LangMng.LANG_D("FR000062", session_lang) %></span></span></h2>
               <div class="btn_assembly">
	                 <div class="right">
	                     <ul>
	                         <li>
	                         	<a href="javascript:void(0);" onclick="clickUpdateButton()" class="edit" data-lang="FR000063">
	                         		<!-- Edit -->
	                         		<%=LangMng.LANG_D("FR000063", session_lang) %>
	                         	</a>
	                         </li>
	                         <li>
	                         	<a href="javascript:void(0);" onclick="saveCartInfo()" class="save" data-lang="FR000064">
	                         		<!-- Save -->
	                         		<%=LangMng.LANG_D("FR000064", session_lang) %>
	                         	</a>
	                         </li>
	                         <li>
	                         	<a href="javascript:void(0);" onclick="deleteCart()" class="delete" data-lang="FR000065">
	                         		<!-- Delete -->
	                         		<%=LangMng.LANG_D("FR000065", session_lang) %>
	                         	</a>
	                         </li>
	                         <li>
	                         	<a href="javascript:void(0);" onclick="addNewRow()" class="add" data-lang="FR000066">
	                         		<!-- ADD -->
	                         		<%=LangMng.LANG_D("FR000066", session_lang) %>
	                         	</a>
	                         </li>
	                     </ul>
	                 </div>
             	</div>
             <div class="cart_top">
                 <div class="check"> 
                     <label class="chkN">
                         <input type="checkbox" id="selectAll" onclick="checkBoxEvent('all', '')">
                         	<!-- Select -->
                         	<span  data-lang="FR000067"><%=LangMng.LANG_D("FR000067", session_lang) %></span>
                     </label>
                 </div>
                 <div class="btn_wrap">
                     <a href="javascript:void(0);" onclick="getBasketList('first');" class="update"  data-lang="FR000068">
                     	<!-- Update Date -->
                     	<%=LangMng.LANG_D("FR000068", session_lang) %>
                     </a>
                 </div>
             </div>
        <!-- 장바구니 그룹 S -->
             <div id="cart_group">
             	<div class="cart_list pc" id="cart_pc">
		            <ul>
		                <li>
		                <!-- cart 그룹 pc S -->
		                    <div class="listTable04">
		                        <table id="cart_table_pc">
		                            <colgroup>
		                                <col width="5%">
		                                <col width="*">
		                                <col width="12%">
		                                <col width="22%">
		                                <%-- <col width="15%"> --%>
		                                <col width="15%">
		                            </colgroup>
		                            <tbody>
                           <!-- cart 그룹 pc table head-->
		                                <tr id="cart_clone_head_pc">
		                                    <td rowspan="2">
		                                        <label class="chkN">
		                                            <input type="checkbox" name="check_pc" id="check_pc" value="" data-type="E" ><span></span>
		                                        </label>
		                                    </td>
		                                    <td rowspan="2" class="left">
		                                        <div class="write">
		                                            <ul>
		                                                <li>
		                                                	<!-- Cart Name -->
		                                                    <span data-lang="FR000070"><%=LangMng.LANG_D("FR000070", session_lang) %></span>
		                                                    <div class="textarea">
		                                                        <textarea name="" id="cart_nm_pc" readonly></textarea> 
		                                                    </div>
		                                                </li>
		                                                <li>
		                                                	<!-- Cart Description -->
		                                                    <span data-lang="FR000071"><%=LangMng.LANG_D("FR000071", session_lang) %></span>
		                                                    <div class="textarea">
		                                                        <textarea name="" id="cart_desc_pc" readonly></textarea> 
		                                                    </div>
		                                                </li>
		                                            </ul>
		                                        </div>
		                                    </td>
		                                    <!-- Total Price -->
		                                    <td data-lang="FR000072" id="td_cart_total_pc"><%=LangMng.LANG_D("FR000072", session_lang) %></td>
		                                    <!-- File Download -->
		                                    <td data-lang="FR000043"><%=LangMng.LANG_D("FR000043", session_lang) %></td>
		                                    <!-- Update Date -->
		                                    <td data-lang="FR000068"><%=LangMng.LANG_D("FR000068", session_lang) %></td>
		                                </tr>
		                 <!-- cart 그룹 pc table head 2-->
		                                <tr id="cart_clone_ctnt_pc">
		                                    <td id="tot_curr_pc"><strong id="total_price_cart_pc"></strong></td>
		                                    <td>
		                                        <div class="file">
		                                            <!-- GTC -->
		                                            <a href="javascript:void(0);" onclick="" id="gtc_cart_pc" class="gtc" data-lang="FR000079"><%=LangMng.LANG_D("FR000079", session_lang) %></a>
		                                            <!-- excel -->
		                                            <a href="javascript:void(0);" onclick="" id="excel_cart_items_pc" class="excel" data-lang="FR000080"><%=LangMng.LANG_D("FR000080", session_lang) %></a>
		                                        </div>
		                                    </td>
		                                    <td id="upd_dt_cart_pc"></td>
		                                </tr>
		                                <!-- 장바구니가 없을 때 -->
		                                <tr id="no_result_cart_pc" style="display:none;">
		                                	<!-- 생성된 장바구니가 없습니다. 새로 추가해주세요.-->
                                         	<td rowspan="2" colspan="5" data-lang="FR000069"><%=LangMng.LANG_D("FR000069", session_lang) %></td>
                                     	</tr>
		                            </tbody>
		                        </table>
		                    </div>
		           <!-- 아이템 목록 pc S-->
		                    <div class="cart_detail" id="cart_detail_pc">
		                        <div class="listTable03">
		                            <table id="cart_detail_table_pc">
		                                <colgroup>
		                                    <col width="10%">
		                                    <col width="20%">
		                                    <col width="7%">
		                                    <col width="8%" id="col_item_cur_pc">
		                                    <col width="8%" id="col_item_price_pc">
		                                    <col width="12%">
		                                    <col width="10%" id="col_item_stock_pc">
		                                    <col width="8%" id="col_item_total_pc">
		                                    <col width="8%">
		                                    <col width="5%">
		                                </colgroup>
		                                <tbody id="item_list_tbody_pc">
		                                <!-- 아이템 목록 pc table head-->
		                                    <tr id="cart_list_detail_head_pc">
		                                    	<!-- Order No -->
		                                        <th data-lang="FR000038"><%=LangMng.LANG_D("FR000038", session_lang) %></th>
		                                    	<!-- Designation -->
		                                        <th data-lang="FR000039"><%=LangMng.LANG_D("FR000039", session_lang) %></th>
		                                    	<!-- Grade -->
		                                        <th data-lang="FR000041"><%=LangMng.LANG_D("FR000041", session_lang) %></th>
		                                    	<!-- Currency -->
		                                        <th data-lang="FR000045" id="th_item_cur_pc"><%=LangMng.LANG_D("FR000045", session_lang) %></th>
		                                    	<!-- Price -->
		                                        <th data-lang="FR000046" id="th_item_price_pc"><%=LangMng.LANG_D("FR000046", session_lang) %></th>
		                                    	<!-- Quantity -->
		                                        <th data-lang="FR000059"><%=LangMng.LANG_D("FR000059", session_lang) %></th>
		                                    	<!-- Stock -->
		                                        <th data-lang="FR000047" id="th_item_stock_pc"><%=LangMng.LANG_D("FR000047", session_lang) %></th>
		                                    	<!-- Total -->
		                                        <th data-lang="FR000173" id="th_item_total_pc"><%=LangMng.LANG_D("FR000173", session_lang) %></th>
		                                    	<!-- Download -->
		                                        <th data-lang="FR000053"><%=LangMng.LANG_D("FR000053", session_lang) %></th>
		                                    	<!-- Action -->
		                                        <th data-lang="FR000145"><%=LangMng.LANG_D("FR000145", session_lang) %></th>
		                                    </tr>
                                    	<!-- pc 아이템 목록 결과 없음 -->
		                                     <tr id="no_result_item_pc" style="display:none;">
		                                    	<!-- 장바구니에 추가된 아이템이 없습니다. -->
                                             	<td colspan="10" data-lang="FR000169"><%=LangMng.LANG_D("FR000169", session_lang) %></td>
                                         	</tr>
                                       	<!-- pc 아이템 목록 clone할 것 -->
		                                    <tr id="cart_list_detail_ctnt_pc">
		                                        <td id="order_no_pc" class="viewGrade" ></td>
		                                        <td id="designation_pc" style="text-align:left;"></td>
		                                        <td id="grade_pc" style="text-align:left;"></td>
		                                        <td id="currency_pc"></td>
		                                        <td id="price_pc" style="text-align:right;"></td>
		                                        <td>
		                                            <div class="input">
		                                                <input type="number" max=999 min=0 id="quantity_pc" value="" onchange="">
		                                            </div>
		                                        </td>
		                                        <td id="stock_pc" style="text-align:left;"></td>
		                                        <td id="total_pc" style="text-align:right;"></td>
		                                        <td>
		                                            <div class="download">
			                                            <!-- GTC -->
		                                                <a href="javascript:void(0);" onclick="" id="item_gtc_pc" class="gtc" data-lang="FR000079"><%=LangMng.LANG_D("FR000079", session_lang) %></a>
		                                            </div>
		                                        </td>
		                                        <td>
		                                            <a href="javascript:void(0);" onclick="" id="delete_item_pc" ><img src="./img/ico_delete.png" alt=""></a>
		                                        </td>
		                                    </tr>
		                                </tbody>
		                            </table>
		                        </div>                                    
		                    </div>
		                    <!-- PC cart 그룹 하단 버튼 -->
		                    <div class="btn_cart" id="btn_cart_group_pc">
		                        <a href="javascript:void(0);" class="open_cart" id="open_pc" data-lang="FR000077">
		                        	<!-- Open -->
		                        	<%=LangMng.LANG_D("FR000077", session_lang) %>
		                        </a>
		                        <a href="javascript:void(0);" class="proceed" id="proceed_pc" data-lang="FR000217">
		                        	<!-- Request to Order -->
		                        	<%=LangMng.LANG_D("FR000217", session_lang) %>
		                        </a>
		                    </div> 
		                </li>
		            </ul>
	        	</div> <!--  end of cart_list pc -->
	       <!-- 카트 그룹  mobile S-->
        		<div class="cart_list mobile" id="cart_mobile">
                       <ul>
                           <li>
                               <div class="listTable04" >
                                   <table>
                                       <colgroup>
                                           <col width="32%">
                                           <col width="*">
                                       </colgroup>
                                       <tbody id="cart_list_tbody_mobile">
                                       	   <tr id="no_result_cart_mobile" style="display:none;">
                                       	   		<!--생성된 장바구니가 없습니다. 새로 추가해주세요. -->
                                       	   		<td colspan="2" data-lang="FR000069"><%=LangMng.LANG_D("FR000069", session_lang) %></td>
                                       	   	</tr>
                                           <tr id="cart_nm_desc_section_mobile">
                                               <td colspan="2">
                                                   <div class="write">
                                                       <ul>
                                                           <li>
                                                               <label class="chkN">
                                                                   <input type="checkbox"  id="check_mobile" name="check_mobile" value="" data-type="E"><span></span>
                                                               </label>
                                                               <!-- Cart Name -->
                                                               <span data-lang="FR000070"><%=LangMng.LANG_D("FR000070", session_lang) %></span>
                                                               <div class="textarea">
                                                                   <textarea name="" id="cart_nm_mobile" readonly></textarea> 
                                                               </div>
                                                           </li>
                                                           <li>
                                                               <!-- Cart Description -->
                                                               <span data-lang="FR000071"><%=LangMng.LANG_D("FR000071", session_lang) %></span>
                                                               <div class="textarea">
                                                                   <textarea name="" id="cart_desc_mobile" readonly></textarea> 
                                                               </div>
                                                           </li>
                                                       </ul>
                                                   </div>
                                               </td>
                                           </tr>
                                           <tr id="total_price_section_mobile">
                                           	   <!-- Total Price -->	
                                               <td data-lang="FR000072"><%=LangMng.LANG_D("FR000072", session_lang) %></td>
                                               <td id="tot_curr_mobile"><strong id="total_price_cart_mobile"></strong></td>
                                           </tr>
                                           <tr id="download_cart_section_mobile">
                                           	 	<!-- File Download -->
                                               <td data-lang="FR000043"><%=LangMng.LANG_D("FR000043", session_lang) %></td>
                                               <td>
                                                   <div class="file">
                                                   	   <!-- GTC -->
                                                       <a href="javascript:void(0);" class="gtc" id="gtc_down_mobile" data-lang="FR000079"><%=LangMng.LANG_D("FR000079", session_lang) %></a>
                                                   	   <!-- excel -->
                                                       <a href="javascript:void(0);" class="excel" id="excel_down_mobile" data-lang="FR000080"><%=LangMng.LANG_D("FR000080", session_lang) %></a>
                                                   </div>
                                               </td>
                                           </tr>
                                           <tr id="upd_dt_section_mobile">
                                           		<!-- Update Date -->
                                               <td data-lang="FR000068"><%=LangMng.LANG_D("FR000068", session_lang) %></td>
                                               <td id="upd_dt_mobile"></td>
                                           </tr>
                                       </tbody>
                                   </table>
                               </div>
                               <div class="cart_detail" id="cart_detail_mobile">
                                   <div class="listTable03" id="cart_detail_list_mobile">
                                       <table>
                                           <colgroup>
                                               <col width="30%">
                                               <col width="*">
                                           </colgroup>
                                           <tbody id="cart_detail_tbody">
                                               <tr id="no_result_item_mobile">
                                               		<!-- 장바구니에 추가된 아이템이 없습니다. -->
                                                   <th colspan="2" data-lang="FR000169"><%=LangMng.LANG_D("FR000169", session_lang) %></th>
                                               </tr>
                                           	 <tr>
                                           	 		<!-- Order No -->
                                                   <th data-lang="FR000038"><%=LangMng.LANG_D("FR000038", session_lang) %></th>
                                                   <td id="order_no_mobile"></td>
                                               </tr>
                                               <tr>
                                               		<!-- Designation -->
                                                   <th data-lang="FR000039"><%=LangMng.LANG_D("FR000039", session_lang) %></th>
                                                   <td id="designation_mobile"></td>
                                               </tr>
                                               <tr>
                                               		<!-- Grade -->
                                                   <th data-lang="FR000041"><%=LangMng.LANG_D("FR000041", session_lang) %></th>
                                                   <td id="grade_mobile"></td>
                                               </tr>
                                               <tr id="item_cur_section_mobile">
                                               		<!-- Currency -->
                                                   <th data-lang="FR000045"><%=LangMng.LANG_D("FR000045", session_lang) %></th>
                                                   <td id="currency_mobile"></td>
                                               </tr>
                                               <tr id="item_price_section_mobile">
                                               		<!-- Last Price -->
                                                   <th data-lang="FR000046"><%=LangMng.LANG_D("FR000046", session_lang) %></th>
                                                   <td><strong id="price_mobile"></strong></td>
                                               </tr>
                                               <tr >
                                               		<!-- Quantity -->
                                                   <th data-lang="FR000059"><%=LangMng.LANG_D("FR000059", session_lang) %></th>
                                                   <td>
                                                       <div class="input">
                                                           <input type="number" max=999 min=0 id="quantity_mobile" value="">
                                                       </div>
                                                   </td>
                                               </tr>
                                               <tr id="item_stock_section_mobile">
                                               		<!-- Stock -->
                                                   <th data-lang="FR000047"><%=LangMng.LANG_D("FR000047", session_lang) %></th>
                                                   <td id="stock_mobile"></td>
                                               </tr>
                                               <tr id="item_total_section_mobile">
                                               		<!-- Total -->
                                                   <th data-lang="FR000173"><%=LangMng.LANG_D("FR000173", session_lang) %></th>
                                                   <td><strong id="total_mobile"></strong></td>
                                               </tr>
                                               <tr >
                                               		<!-- Download -->
                                                   <th data-lang="FR000053"><%=LangMng.LANG_D("FR000053", session_lang) %></th>
                                                   <td>
                                                       <div class="download">
                                                       		  <!-- GTC -->
                                                           <a href="javascript:void(0);" onclick="" id="item_gtc_down" class="gtc" data-lang="FR000079"><%=LangMng.LANG_D("FR000079", session_lang) %></a>
                                                       </div>
                                                   </td>
                                               </tr>
                                               <tr >
                                               		<!-- Action -->
                                                   <th data-lang="FR000145"><%=LangMng.LANG_D("FR000145", session_lang) %></th>
                                                   <td>
                                                       <a href="javascript:void(0);" id="delete_item_mobile" onclick=""><img src="./img/ico_delete.png" alt=""></a>
                                                   </td>
                                               </tr>
                                           </tbody>
                                       </table>
                                   </div>                                    
                               </div>
                               <div class="btn_cart" id="btn_cart_group_mobile" >
                                   <a href="javascript:void(0);" onclick="" class="open_cart" id="open_mobile" data-lang="FR000077">
	                                 	<!-- Open -->
			                        	<%=LangMng.LANG_D("FR000077", session_lang) %>
                                   </a>
                                   <a href="javascript:void(0);" onclick="" class="proceed" id="proceed_mobile" data-lang="FR000217">
                                   		<!-- Request to Order -->
		                        		<%=LangMng.LANG_D("FR000217", session_lang) %>
                                   </a>
                               </div> 
                           </li>
                       </ul>
                   </div> <!-- end of cart_list moblie -->
           	</div> <!-- 장바구니 그룹 E -->
             <div class="btn_group">
                 <div class="center">
                     <a href="javascript:void(0);" onclick="getBasketList('more')" class="btn01 same" id="more" style="display:none;" data-lang="FR000034">
                     	<!-- more -->
                     	<%=LangMng.LANG_D("FR000034", session_lang) %>
                     </a>
                     <a href="javascript:void(0);" onclick="goRequest('multi')" class="btn01 same" id="multiple_request" data-lang="FR000217">
                     	<!-- Request of Quotation -->
                     	<%=LangMng.LANG_D("FR000217", session_lang) %>
                     </a>
                 </div>
             </div>
         </div>
     </div>
 </div>
 <!-- 주문서 이동을 위해 -->
 <form id="goOrderPage" name="goOrderPage" method="POST" action="<%=ConstUrl.ORDER_URL%>">
	<input type="hidden" name="<%=ConstKey.ACTION_ID %>"  />
	<input type="hidden" name="cart_no"  />
</form>
<!-- 파일다운로드 form -->
<form name="downLoadGtcZipForm" id="downLoadGtcZipForm" method="POST" action="<%=ConstUrl.BASKET_URL%>">
	<!-- 공통 객체 정의 -->
	<input type="hidden" name="gtc_file_path" id="gtc_file_path" />
	<input type="hidden" name="gtc_file_name" id="gtc_file_name" />
	<input type="hidden" name="<%=ConstKey.ACTION_ID %>">
	<!-- 업무별 사용 객체 정의 -->
</form>
<!--  장바구니 그룹목록 더보기 처리를 위해 사용 -->
<input type="hidden" id="all_startIndex" name="startIndex" value=0 />
<input type="hidden" id="all_endIndex" name="endIndex" value=20 />
<input type="hidden" id="all_tot_cnt" name="tot_cnt" value=0 />
<input type="hidden" id="all_orderSort" name="orderSort" value=0 />
<input type="hidden" id="all_pageCount" name="pageCount" value=20 />
<style>
/*input text만 css가 적용되어 아래와 같이 모든 input에 적용되도록 수정*/
.cart_wrap .cart_detail .input input{width: 100%;height: 30px;line-height: 30px;border: 1px solid #dfdfdf;text-indent: 10px;}

.cart_wrap .cart_detail .input {
    max-width: 70px;
    position: relative;
    margin-left: 20%;
}
@media only screen and (max-width: 461px){
	.cart_wrap .cart_detail .input {
	    margin-left:0;
	}
	.cart_wrap .cart_detail .input input{
	    text-align:left !important;
	}
}

.cart_wrap .cart_detail .input input{
    text-align:right;
}
#footer{
	position : inherit;
}
.listTable03 td.viewGrade {
    color: #0094da;
    cursor:pointer;
    text-decoration: underline;
}
</style>
<script type="text/javascript">
//더보기 처리를 위한 변수 선언
var startIndex = 0;
var endIndex = 20;
var perCnt = 20;
//디바이스 타입에 따라서 table 형태가 다름
//따라서 장바구니 그룹과 아이템을 그릴때 디바이스타입에 따라서 clone 할 것이 다름
//devType == M || devType == P 일 때를 나누어서 모두 데이터 뿌려주기
var devType='<%=devType%>'
//체크박스 제어를 위해
var checkedCart=[];
//추가 수정 동시에 할 때 제어를 위해
var insertEditBoth = false;
var iframeSrc;
// ↙ 엑셀다운
var $cartItemExceldownForm = $('#cartItemExceldownForm')
var $cartGtcZipdownForm = $('#downLoadGtcZipForm')
var excel_file_path = "";
var file_name = "";
var gtc_file_path = "";
var gtc_file_name = "";

var priceYn;
var stockYn;

//  엑셀다운 -------------------------
jQuery(document).ready(function(){
    $('.loading').delay('1000').fadeOut();
	getBasketList('first');

	// 국가
    cur_nation = $("#btnNation").val();
    
    // 언어변경
    $("input[name='btnLang']").change(function(){            
               cur_lang = this.value;
               getBasketList('first');
// 		 location.reload();
    });

    // 길이단위변경
    $("#btnSizeUnit").change(function(){            
               cur_size_unit = this.value;
    });

    // 통화변경
    $("#btnCurr").change(function(){            
               cur_currency = this.value;
               getBasketList('first');
    });
 ///////////////////////모바일
    // 통화변경 
    $("#mbtnCurr").change(function(){            
               cur_currency = this.value;
               getBasketList('first');
    });

});

//새로 카트그룹 추가시
function addNewRow(){
	
	//add 감지 
	isAdd = true;
	var cloneItem ;
	var plus_id = (new Date().getTime()).toString();
	
	//체크박스 제어를 위해
	checkedCart.push(plus_id)
	
	//devType에  따라 나눠서 처리해주고 save 시 재조회
	if(devType =='M' ){
		$('#cart_mobile').show() 
		cloneItem = $('#cart_mobile').clone(); 
		$('#cart_mobile').hide() 
	}else{
		$('#cart_pc').show();		
		cloneItem =  $('#cart_pc').clone();  
		$('#cart_pc').hide();		
	}
	
	$('#cart_group').prepend(cloneItem);
	//아이디 겹치지 않게 변경
	//복사한 요소의 id를 변경
	if(devType =='M'){
		
		$('#cart_group').children().first().attr('id', 'cart_mobile'+'_'+plus_id) 
		var cartMobId = '#cart_mobile_'+plus_id
		//id 변경  
		//기존 id에 '_'와 cart_no 추가
		$(cartMobId).find('#cart_list_tbody_mobile').attr('id','cart_list_tbody_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_detail_mobile').attr('id','cart_detail_mobile'+'_'+plus_id);
		$(cartMobId).find('#no_result_cart_mobile').attr('id','no_result_cart_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_nm_desc_section_mobile').attr('id','cart_nm_desc_section_mobile'+'_'+plus_id);
		$(cartMobId).find('input[name="check_mobile"]').attr('id','check_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_detail_list_mobile').attr('id','cart_detail_list_mobile'+'_'+plus_id);
		$(cartMobId).find('#total_price_section_mobile').attr('id','total_price_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#download_cart_section_mobile').attr('id','download_cart_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#request_section_mobile').attr('id','request_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#upd_dt_section_mobile').attr('id','upd_dt_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_detail_mobile').attr('id','cart_detail_mobile'+'_'+plus_id);
		$(cartMobId).find('#btn_cart_group_mobile').attr('id','btn_cart_group_mobile'+'_'+plus_id);
		$(cartMobId).find('#open_mobile').attr('id','open_mobile'+'_'+plus_id);
		$(cartMobId).find('#request_mobile').attr('id','request_mobile'+'_'+plus_id);
		
		$(cartMobId).find('#cart_nm_mobile').attr('id','cart_nm_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_desc_mobile').attr('id','cart_desc_mobile'+'_'+plus_id);
		
		$('#check_mobile_'+plus_id).prop("checked", true);
		$('#check_mobile_'+plus_id).val("new");
		$('#check_mobile_'+plus_id).attr("data-type",'A');
		$('#check_mobile_'+plus_id).attr("onclick","checkBoxEvent('part','"+plus_id+"')");
		//새로 추가시 보여야 하는 부분
		$('#no_result_cart_mobile'+'_'+plus_id).hide();
		$('#cart_nm_desc_section_mobile'+'_'+plus_id).show();
		$('#total_price_section_mobile'+'_'+plus_id).hide();
		$('#download_cart_section_mobile'+'_'+plus_id).hide();
		$('#upd_dt_section_mobile'+'_'+plus_id).hide();
		
		$('#btn_cart_group_mobile_'+plus_id).hide();
		$('#cart_detail_mobile_'+plus_id).hide();
		
	}else{
		$('#cart_group').children().first().attr('id', 'cart_pc'+'_'+plus_id);
		var cartPcId = '#cart_pc_'+plus_id
		
		//id 변경  
		//기존 id에 '_'와 cart_no 추가
		$(cartPcId).find('#cart_clone_head_pc').attr('id','cart_clone_head_pc'+'_'+plus_id);
		$(cartPcId).find('#no_result_cart_pc').attr('id','no_result_cart_pc'+'_'+plus_id);
		$(cartPcId).find('input[name="check_pc"]').attr('id','check_pc'+'_'+plus_id);
		$(cartPcId).find('#cart_nm_pc').attr('id','cart_nm_pc'+'_'+plus_id);
		$(cartPcId).find('#cart_desc_pc').attr('id','cart_desc_pc'+'_'+plus_id);
		$(cartPcId).find('#cart_clone_ctnt_pc').attr('id','cart_clone_ctnt_pc'+'_'+plus_id);
		$(cartPcId).find('#total_price_cart_pc').attr('id','total_price_cart_pc'+'_'+plus_id);
		$(cartPcId).find('#tot_curr_pc').attr('id','tot_curr_pc'+'_'+plus_id);
		$(cartPcId).find('#gtc_cart_pc').attr('id','gtc_cart_pc'+'_'+plus_id);
		$(cartPcId).find('#excel_cart_items_pc').attr('id','excel_cart_items_pc'+'_'+plus_id);
		$(cartPcId).find('#upd_dt_cart_pc').attr('id','upd_dt_cart_pc'+'_'+plus_id);
		$(cartPcId).find('#btn_cart_group_pc').attr('id','btn_cart_group_pc'+'_'+plus_id);
		$(cartPcId).find('#cart_detail_pc').attr('id','cart_detail_pc'+'_'+plus_id);
		$(cartPcId).find('#cart_table_pc').attr('id','cart_table_pc'+'_'+plus_id);
		
		$('#cart_clone_head_pc_'+plus_id).show();
		var elementHead = $('#cart_clone_head_pc_'+plus_id).children();
		elementHead.get(2).remove()
		elementHead.get(3).remove()
		elementHead.get(4).remove()
		$('#cart_clone_ctnt_pc_'+plus_id).show();
		//필요없는 부분 삭제
		$('#cart_clone_ctnt_pc_'+plus_id).children().remove();
		var elementColGrp = $('#cart_table_pc'+'_'+plus_id+' colgroup').children()
		elementColGrp.get(2).remove()
		elementColGrp.get(3).remove()
		elementColGrp.get(4).remove()
		$('#cart_list_detail_ctnt_pc_'+plus_id).show();
		$('#no_result_cart_pc_'+plus_id).hide();
		$('#gtc_cart_pc_'+plus_id).hide();
		$('#excel_cart_items_pc_'+plus_id).hide();
		$('#btn_cart_group_pc_'+plus_id).hide();
		$('#cart_detail_pc_'+plus_id).hide();
		$('#check_pc_'+plus_id).prop("checked", true);
		$('#check_pc_'+plus_id).val("new");
		$('#check_pc_'+plus_id).attr("data-type",'A');
		$('#check_pc_'+plus_id).attr("onclick","checkBoxEvent('part','"+plus_id+"')");
	}
	//추가 영역 활성화
	for(var i =0; i<checkedCart.length; i++){
		$('#cart_nm_pc_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_desc_pc_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_nm_mobile_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_desc_mobile_'+checkedCart[i]).removeAttr("readonly");
	}
}

//more button누를 때와 초기조회 다르게 처리
function getBasketList(show_type/* 초기 조회(first) or more로 조회(more) or save*/){
	//배열을 바로 넘기면 에러 발생하여 string으로 처리하여서 넘김
	//서비스에서 배열로 변경 후 처리
	var formData = new FormData();
	formData.append("actionID",'<%=BasketConstKey.ACTION_BASKET_LIST%>');
	formData.append("user_id", USERINFO.user_id);
	if(devType=='P'){
		formData.append("cur",$('#btnCurr :selected').val());
		formData.append("comm_cur",$('#btnCurr :selected').val());
	}else{
		formData.append("cur",$('#mbtnCurr :selected').val());
		formData.append("comm_cur",$('#mbtnCurr :selected').val());
	}
	
	//more button눌렀을 때 
	 if(show_type=='more'){
		formData.append("startIndex" , $("input[name='startIndex']").val());
	 }else {
		//검색조건을 입력한 것이 없다면 return false;
		//검색의 경우 처읍부터 ~ 20개를 조회하는 것 startIndex = 0
		formData.append("startIndex" , 0);
		formData.append("pageCount" , $("input[name='pageCount']").val());
		//저장하거나
		//더보기 관련 변수들의 값을 수정했을 수도 있기 때문에 처음 값으로 초기화
		startIndex = 0;
		endIndex = 20;
		perCnt = 20;
		 $("input[name='startIndex']").val(0);
		 $("input[name='endIndex']").val(20);
		 $("input[name='tot_cnt']").val(0);
	}
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
		data: formData,
		dataType: 'json',
		async: true,
		cache: false,
		contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        beforeSend: function(){
        	$('#loadbar').show();
        },
		success: function(result) {
			var result = result;
			priceYn = result.priceYN
			stockYn = result.stockYN
			appendResult(show_type, result);
		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
	
}

//장바구니 그룹 목록 초기조회랑 더보기 구분
function appendResult(show_type/*초기조회인지 더보기인지*/, result/*ajax결과로 넘긴 list, nTotal*/){
	//startIndex 값
	var st = $("input[name='startIndex']").val() *1 + perCnt
	$("input[name='startIndex']").val(st)
	// endIndex 값
	var ed = $("input[name='endIndex']").val() *1 + perCnt
	$("input[name='endIndex']").val(ed)
	//기존 데이터수 * 1 + 조회 데이터 개수 * 1
	var tt = $("input[name='tot_cnt']").val() * 1 + result.list.length * 1
	$("input[name='tot_cnt']").val(tt)
	
	//상위 table 영역 접근 - 장바구니 목록
	var tableRef = document.getElementById("search_result");
	
	// 조건에 맞는 조회 리스트의 전채 개수와 현제 조회한 데이터 개수 비교 후 더보기 버튼 보이고 숨기기 
	tt < result.nTotal ? $("#more").show() : $("#more").hide()
	
	//초기조회
	if(show_type == 'first'){
	    //조회된 총 row의 개수 표시
	    					/* You have */													/* Cart List */
<%-- 	    $('#cartCnt').html("<%=LangMng.LANG_D("FR000061", session_lang) %>"+result.nTotal+" <%=LangMng.LANG_D("FR000062", session_lang) %>") --%>
	    $('#cartCnt').html(result.nTotal)
		//cart_pc부분이랑 mobile 부분에서 지우지 않아야할 부분을 제외하고 모두 삭제
		$('.cart_list.pc').not("#cart_pc").remove();
		$('.cart_list.mobile').not("#cart_mobile").remove();
	
		$('#cart_pc').css("display", "");
		$('#cart_mobile').css("display", "");
	}
	
	if(result.nTotal == 0){
		//생성된 장바구니가 하나도 없을 때
		//pc 장바구니 없다는 메시지 표시영역을 제외하고 hide
		$('#cart_clone_head_pc').hide();
		$('#cart_clone_ctnt_pc').hide();
		$('#cart_list_detail_ctnt_pc').hide();
		$('#btn_cart_group_pc').hide();
		$('#cart_detail_pc').hide();
		$('#no_result_cart_pc').show();
		
		//mobile 장바구니 없다는 메시지 표시영역을 제외하고 hide
		$('#no_result_cart_mobile').show();
		$('#cart_nm_desc_section_mobile').hide();
		$('#total_price_section_mobile').hide();
		$('#download_cart_section_mobile').hide();
		$('#upd_dt_section_mobile').hide();
		$('#cart_nm_desc_section_mobile').hide();
		$('#cart_detail_mobile').hide();
		$('#btn_cart_group_mobile').hide();
		
		//pc&mobile 공통 hide
		$('#multiple_request').hide();
	}else{
		//pc
		$('#cart_clone_head_pc').css("display","");
		$('#cart_clone_ctnt_pc').css("display","");
		$('#cart_list_detail_ctnt_pc').css("display","");
		$('#btn_cart_group_pc').css("display","");
		$('#cart_detail_pc').css("display","");
		$('#no_result_cart_pc').hide();
		
		//mobile
		$('#no_result_cart_mobile').hide();
		$('#cart_nm_desc_section_mobile').css("display","");
		$('#total_price_section_mobile').css("display","");
		$('#download_cart_section_mobile').css("display","");
		$('#upd_dt_section_mobile').css("display","");
		$('#cart_nm_desc_section_mobile').css("display","");
		$('#cart_detail_mobile').css("display","");
		$('#btn_cart_group_mobile').css("display","");
		
		//pc&모바일 공통
		$('#multiple_request').show();
		
		//장바구니 append
		for(var i =0; i<result.list.length; i++){
			//pc용 처리
			$('#cart_pc').css("display", "");
				$('#cart_pc').clone().appendTo('#cart_group');
				
				var plus_id = result.list[i].cart_no;
				
				//복사한 요소의 id를 변경
				$('#cart_group').children().last().attr('id', 'cart_pc'+'_'+plus_id);
				//복사한 요소 id 취득
				var cartPcId = '#cart_pc'+'_'+plus_id;
				
				//id 변경  
				//기존 id에 '_'와 cart_no 추가
				$(cartPcId).find('#cart_clone_head_pc').attr('id','cart_clone_head_pc'+'_'+plus_id);
				$(cartPcId).find('#no_result_cart_pc').attr('id','no_result_cart_pc'+'_'+plus_id);
				$(cartPcId).find('input[name="check_pc"]').attr('id','check_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_nm_pc').attr('id','cart_nm_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_desc_pc').attr('id','cart_desc_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_clone_ctnt_pc').attr('id','cart_clone_ctnt_pc'+'_'+plus_id);
				$(cartPcId).find('#total_price_cart_pc').attr('id','total_price_cart_pc'+'_'+plus_id);
				$(cartPcId).find('#tot_curr_pc').attr('id','tot_curr_pc'+'_'+plus_id);
				$(cartPcId).find('#gtc_cart_pc').attr('id','gtc_cart_pc'+'_'+plus_id);
				$(cartPcId).find('#excel_cart_items_pc').attr('id','excel_cart_items_pc'+'_'+plus_id);
				$(cartPcId).find('#upd_dt_cart_pc').attr('id','upd_dt_cart_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_detail_pc').attr('id','cart_detail_pc'+'_'+plus_id);
				$(cartPcId).find('#item_list_tbody_pc').attr('id','item_list_tbody_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_list_detail_ctnt_pc').attr('id','cart_list_detail_ctnt_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_detail_table_pc').attr('id','cart_detail_table_pc'+'_'+plus_id);
				$(cartPcId).find('#btn_cart_group_pc').attr('id','btn_cart_group_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_table_pc').attr('id','cart_table_pc'+'_'+plus_id);
				$(cartPcId).find('#td_cart_total_pc').attr('id','td_cart_total_pc'+'_'+plus_id);
				
				//장바구니 없을 때 나타나는 메시지 가리기
				$('#no_result_cart_pc_'+plus_id).hide();
				
				//하위 아이템 목록 상단 아이디 변경 후 아이템 목록 그리는 function으로 토스
				$(cartPcId).find('#cart_list_detail_head_pc').attr('id','cart_list_detail_head_pc'+'_'+plus_id);
				$(cartPcId).find('#no_result_item_pc').attr('id','no_result_item_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_list_detail_ctnt_pc').attr('id','cart_list_detail_ctnt_pc'+'_'+plus_id);
				
				//카트 하단 버튼
				$(cartPcId).find('#open_pc').attr('id','open_pc'+'_'+plus_id);
				$(cartPcId).find('#proceed_pc').attr('id','proceed_pc'+'_'+plus_id);
				//$(cartPcId).find('#request').attr('id','request'+'_'+plus_id);
				
				//데이터 삽입 및 이벤트 추가 
				$('#check_pc_'+plus_id).attr("data-no", plus_id);
				$('#check_pc_'+plus_id).attr("data-type", "E");
				$('#check_pc_'+plus_id).val(plus_id);
				$('#check_pc_'+plus_id).attr("onclick", "checkBoxEvent('part',"+plus_id+")");
				$('#cart_nm_pc'+'_'+plus_id).html(result.list[i].cart_nm);
				$('#cart_desc_pc'+'_'+plus_id).html(result.list[i].cart_desc);
				//금액표시
				if(result.priceYN == 'Y'){
					var ttlPrice = numberFormatting(result.list[i].total_price, result.list[i].cur);
					$('#total_price_cart_pc'+'_'+plus_id).html(ttlPrice +" ");
					$('#tot_curr_pc'+'_'+plus_id).append(result.list[i].cur);
				}else{
					$('#td_cart_total_pc'+'_'+plus_id).remove();
					$('#tot_curr_pc'+'_'+plus_id).remove();
					var element = $('#cart_table_pc'+'_'+plus_id+" colgroup").children()
// 					console.log(element)
					element.get(2).remove();
				}

				//장바구니 전체 gtc다운로드 권한에 따른처리
				if(USERAUTH.isAllGtcDownAuth){
					$('#gtc_cart_pc'+'_'+plus_id).attr('onclick', "fileDownload('gtc', 'cart', "+plus_id+")");
// 					$('#gtc_cart_pc'+'_'+plus_id).attr('onclick', "alert('작업중')");
				}else{
															/* gtc파일을 다운로드할 권한이 없습니다. */	
					var msg = LANG_SET['FR000229'];
// 					$('#gtc_cart_pc'+'_'+plus_id).attr('onclick', "alert('gtc파일을 다운로드할 권한이 없습니다.')")
					$('#gtc_cart_pc'+'_'+plus_id).attr('onclick', 'javascript:alert("'+msg+'");')
				}
				
				//장바구니 엑셀 다운로드 권한에 따른 처리
				if(USERAUTH.isExcelDownAuth){
					$('#excel_cart_items_pc'+'_'+plus_id).attr('onclick', "fileDownload('excel', 'cart',"+plus_id+")");
				}else{
															/*엑셀파일을 다운로드할 권한이 없습니다. */	
					var msg = LANG_SET['FR000229'];
					$('#excel_cart_items_pc'+'_'+plus_id).attr('onclick', 'javascript:alert("'+msg+'");');
				}
				$('#upd_dt_cart_pc'+'_'+plus_id).html(result.list[i].upd_dt);
				
				//카트 그룹 하단 버튼 이벤트
				$('#open_pc'+'_'+plus_id).attr('onclick', "onAndOffClass("+plus_id+")");					
				$('#proceed_pc'+'_'+plus_id).attr('onclick', "goRequest('single',"+plus_id+")");					
				$('#request'+'_'+plus_id).attr('onclick', "goRequest('single',"+plus_id+")");	
				
				//테이블 삽입 pc
				appendItemListTable(plus_id ,result.list[i].basket_detail_list, 'first' , result.priceYN, result.stockYN);
				
			//mobile용 처리 --------------------------------------------------------------------------------
				$('#cart_mobile').css("display", "");
				$('#cart_mobile').clone().appendTo('#cart_group');
				
				//복사한 요소의 id를 변경
				$('#cart_group').children().last().attr('id', 'cart_mobile'+'_'+plus_id);
				//복사한 요소 id 취득
				var cartMobId = '#cart_mobile'+'_'+plus_id;
				
				//id 변경  
				//기존 id에 _+ctnt_img_no 추가하여 id가 겹치지 않도록 한다.
				$(cartMobId).find('#no_result_cart_mobile').attr('id','no_result_cart_mobile'+'_'+plus_id);
				$(cartMobId).find('#cart_nm_mobile').attr('id','cart_nm_mobile'+'_'+plus_id);
				$(cartMobId).find('#cart_desc_mobile').attr('id','cart_desc_mobile'+'_'+plus_id);
				$(cartMobId).find('#upd_dt_mobile').attr('id','upd_dt_mobile'+'_'+plus_id);
				$(cartMobId).find('input[name="check_mobile"]').attr('id','check_mobile'+'_'+plus_id);
				$(cartMobId).find('#tot_curr_mobile').attr('id','tot_curr_mobile'+'_'+plus_id);
				$(cartMobId).find('#total_price_cart_mobile').attr('id','total_price_cart_mobile'+'_'+plus_id);
				
				$(cartMobId).find('#cart_nm_desc_section_mobile').attr('id','cart_nm_desc_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#total_price_section_mobile').attr('id','total_price_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#gtc_down_mobile').attr('id','gtc_down_mobile'+'_'+plus_id);
				$(cartMobId).find('#excel_down_mobile').attr('id','excel_down_mobile'+'_'+plus_id);
				$(cartMobId).find('#download_cart_section_mobile').attr('id','download_cart_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#request_section_mobile').attr('id','request_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#upd_dt_section_mobile').attr('id','upd_dt_section_mobile'+'_'+plus_id);
				
				$(cartMobId).find('#cart_detail_mobile').attr('id','cart_detail_mobile'+'_'+plus_id);
				$(cartMobId).find('#cart_detail_list_mobile').attr('id','cart_detail_list_mobile'+'_'+plus_id);
				$(cartMobId).find('#cart_detail_tbody').attr('id','cart_detail_tbody'+'_'+plus_id);
				$(cartMobId).find('#btn_cart_group_mobile').attr('id','btn_cart_group_mobile'+'_'+plus_id);
				$(cartMobId).find('#open_mobile').attr('id','open_mobile'+'_'+plus_id);
				$(cartMobId).find('#proceed_mobile').attr('id','proceed_mobile'+'_'+plus_id);
				
				//데이터 삽입 및 이벤트 추가 
				$('#check_mobile_'+plus_id).attr("data-no", plus_id);
				$('#check_mobile_'+plus_id).val(plus_id);
				$('#check_mobile_'+plus_id).attr("onclick", "checkBoxEvent('part',"+plus_id+")");
				$('#cart_nm_mobile'+'_'+plus_id).html(result.list[i].cart_nm);
				$('#cart_desc_mobile'+'_'+plus_id).html(result.list[i].cart_desc);
				$('#check_mobile_'+plus_id).attr("data-type", "E");
				
				
				$('#cart_nm_mobile'+'_'+plus_id).html(result.list[i].cart_nm);
				$('#cart_desc_mobile'+'_'+plus_id).html(result.list[i].cart_desc);
				$('#upd_dt_mobile'+'_'+plus_id).html(result.list[i].upd_dt);
				//어드민 설정에 따른  금액표시  
				if(result.priceYN == 'Y'){
					$('#tot_curr_mobile_'+plus_id).append(result.list[i].cur);
					$('#total_price_cart_mobile'+'_'+plus_id).html(ttlPrice +" ");
				}else{
					$('#total_price_section_mobile'+'_'+plus_id).hide();
				}
				//장바구니 전체 gtc다운로드 권한에 따른처리
				if(USERAUTH.isAllGtcDownAuth){
					$('#gtc_down_mobile'+'_'+plus_id).attr('onclick', "fileDownload('gtc', 'cart', "+plus_id+")");
// 					$('#gtc_down_mobile'+'_'+plus_id).attr('onclick', "alert('작업중')");
				}else{
															/* gtc파일을 다운로드할 권한이 없습니다. */	
					var msg = LANG_SET['FR000229'];
					$('#gtc_down_mobile'+'_'+plus_id).attr('onclick', 'javascript:alert("'+msg+'");');
				}

				//장바구니 엑셀 다운로드 권한에 따른 처리
				if(USERAUTH.isExcelDownAuth){
					$('#excel_down_mobile'+'_'+plus_id).attr('onclick', "fileDownload('excel', 'cart',"+plus_id+")");
				}else{
																/* 엑셀파일을 다운로드할 권한이 없습니다. */	
					var msg = LANG_SET['FR000229'];
					$('#excel_down_mobile'+'_'+plus_id).attr('onclick', 'javascript:alert("'+msg+'");');
				}
				
				$('#upd_dt_cart_mobile'+'_'+plus_id).html(result.list[i].upd_dt);
				
				//카트 그룹 하단 버튼 이벤트
				$('#open_mobile'+'_'+plus_id).attr('onclick', "onAndOffClass("+plus_id+")");					
				$('#proceed_mobile'+'_'+plus_id).attr('onclick', "goRequest('single',"+plus_id+")");					
				//$('#request_mobile'+'_'+plus_id).attr('onclick', "goRequest("+plus_id+")");		
				
				//모바일 아이템
				appendItemListTableMoblie(plus_id ,result.list[i].basket_detail_list, 'first', result.priceYN, result.stockYN);
			}//end of for
			
			//금액표시
// 			if(result.priceYN == 'Y'){
// 				alert(1)
// 				$('#cart_clone_head_pc'+'_'+plus_id+" td:eq(3)").remove();
// 				var element = $('#cart_table_pc'+'_'+plus_id+" colgroup").children()
// 				console.log(element)
// 				element.get(2).remove();
// 			}
// 		$('#cart_table_pc'+'_'+plus_id).css('table-layout', '');
		$('#cart_mobile').hide();
		$('#cart_pc').hide();
	}
}
//장바구니 그룹의 아이템 리스트 생성
function appendItemListTable(cart_no, itemList, appendType, priceYN, stockYN){
// 	console.log("[ITEM LIST] : : "+JSON.stringify(itemList))	
	var itemDwlPathCnt = 0;
	if(appendType == 'after'){
		$('#item_list_tbody_pc_'+cart_no).children().not('#cart_list_detail_head_pc_'+cart_no).not('#no_result_item_pc_'+cart_no).not('#cart_list_detail_ctnt_pc_'+cart_no).remove();
	}
	
	if(itemList.length > 0){
		$('#cart_detail_table_pc_'+cart_no).css('table-layout','auto');
		for(var i=0; i<itemList.length;i++){
			$('#cart_list_detail_ctnt_pc_'+cart_no).css("display","");
			//pc
			var cloneItem = $('#cart_list_detail_ctnt_pc_'+cart_no).clone();
			$('#item_list_tbody_pc_'+cart_no).append(cloneItem);
			
			//pc 모바일 공통 사용
			var plus_id = cart_no + '_'+i
			
			$('#item_list_tbody_pc_'+cart_no).children().last().attr('id', 'cart_list_detail_ctnt_pc_'+plus_id);
			
			var ctntId = '#'+'cart_list_detail_ctnt_pc_'+plus_id;
			//금액 재고 표시 여부에 따라 삭제할수도 있음
// 			$('#cart_list_detail_head_pc_'+cart_no).eq().attr('id', 'col_item_stock_pc'+'_'+cart_no);
			$('#cart_list_detail_head_pc_'+cart_no).find('#th_item_stock_pc').attr('id', 'th_item_stock_pc'+'_'+cart_no);
			
// 			$('#cart_list_detail_head_pc_'+cart_no+' colgroup').children().('#col_item_cur_pc').attr('id', 'col_item_cur_pc'+'_'+cart_no);
// 			$('#cart_list_detail_head_pc_'+cart_no+' colgroup').children().('#col_item_price_pc').attr('id', 'col_item_price_pc'+'_'+cart_no);
// 			$('#cart_list_detail_head_pc_'+cart_no+' colgroup').children().('#col_item_total_pc').attr('id', 'col_item_total_pc'+'_'+cart_no);
			$('#cart_list_detail_head_pc_'+cart_no).find('#th_item_price_pc').attr('id', 'th_item_price_pc'+'_'+cart_no);
			$('#cart_list_detail_head_pc_'+cart_no).find('#th_item_total_pc').attr('id', 'th_item_total_pc'+'_'+cart_no);
			$('#cart_list_detail_head_pc_'+cart_no).find('#th_item_cur_pc').attr('id', 'th_item_cur_pc'+'_'+cart_no);
			
			$(ctntId).find('#order_no_pc').attr('id', 'order_no_pc_'+plus_id);
			$(ctntId).find('#designation_pc').attr('id', 'designation_pc_'+plus_id);
			$(ctntId).find('#grade_pc').attr('id', 'grade_pc_'+plus_id);
			$(ctntId).find('#currency_pc').attr('id', 'currency_pc_'+plus_id);
			$(ctntId).find('#price_pc').attr('id', 'price_pc_'+plus_id);
			$(ctntId).find('#quantity_pc').attr('id', 'quantity_pc_'+plus_id);
			$(ctntId).find('#stock_pc').attr('id', 'stock_pc_'+plus_id);
			$(ctntId).find('#total_pc').attr('id', 'total_pc_'+plus_id);
			$(ctntId).find('#item_gtc_pc').attr('id', 'item_gtc_pc_'+plus_id);
			$(ctntId).find('#item_dxf_pc').attr('id', 'item_dxf_pc_'+plus_id);
			$(ctntId).find('#delete_item_pc').attr('id', 'delete_item_pc_'+plus_id);
			
			$('#order_no_pc_'+plus_id).html(itemList[i].matnr)
			$('#order_no_pc_'+plus_id).attr('onclick','showItemDetailPop("'+itemList[i].matnr+'", "'+itemList[i].ig_cd+'",'+cart_no+')')
			$('#designation_pc_'+plus_id).html(itemList[i].designation)
			$('#grade_pc_'+plus_id).html(itemList[i].grade)
			
			////어드민 설정에 따른 금액 or  재고표시
			if(priceYN =='Y'){
				var price = numberFormatting(itemList[i].price, itemList[i].cur)
				$('#price_pc_'+plus_id).html(price)
				$('#currency_pc_'+plus_id).html(itemList[i].cur)
				var total_price = numberFormatting(itemList[i].total_price, itemList[i].cur) 
				$('#total_pc_'+plus_id).html(total_price)
			}else{
				$('#currency_pc'+'_'+plus_id).remove();
				$('#price_pc'+'_'+plus_id).remove();
				$('#total_pc'+'_'+plus_id).remove();
			}
			
			$('#quantity_pc_'+plus_id).val(itemList[i].qty)
			$('#quantity_pc_'+plus_id).attr('onchange','editItemQuantity('+cart_no+',"'+itemList[i].matnr+'",'+i+',"'+itemList[i].cur+'")')
			
			//파일 다운로드 설정 
			if(itemList[i].file_dwl_path !=''){
				itemDwlPathCnt++;
				//장바구니 아이템 단일 gtc다운로드 권한에 따른처리
				if(USERAUTH.isOneGtcDownAuth){
					$('#item_gtc_pc_'+plus_id).attr('href', '<%=rootPath%>'+itemList[i].file_dwl_path)
				}else{
																	/* gtc파일을 다운로드할 권한이 없습니다. */	
					var msg = LANG_SET['FR000229'];
					$('#item_gtc_pc_'+plus_id).attr('href', 'javascript:alert("'+msg+'");')
				}
			}else{
																			/* 저장된 파일이 없습니다. */
<%-- 				$('#item_gtc_pc_'+plus_id).attr('href', 'javascript:alert("<%=LangMng.LANG_D("FR000153", session_lang) %>");') --%>
				$('#item_gtc_pc_'+plus_id).hide();
			}
			
			$('#delete_item_pc_'+plus_id).attr('onclick','deleteItem('+cart_no+',"'+itemList[i].matnr+'", "'+itemList[i].cur+'")')
			////어드민 설정에 따른 금액 or  재고표시
			if(stockYN =='Y'){
				if(itemList[i].stock_yn == 'Y'){
													/* In Stock  */
					$('#stock_pc_'+plus_id).html(LANG_SET['FR000227'])
					$('#stock_pc_'+plus_id).attr('data-lang', 'FR000227')
				}else{
												/* Out of Stock  */
					$('#stock_pc_'+plus_id).html(LANG_SET['FR000226'])
					$('#stock_pc_'+plus_id).attr('data-lang', 'FR000226')
				}
			}else{
				$('#stock_pc'+'_'+plus_id).remove();
			}
		}//end of for
		
		//어드민 설정에 따른 금액 및 재고 테이블 컬럼 표시
		if(priceYn == 'N'){
			var element = $('#cart_detail_table_pc_'+cart_no+' colgroup').children()
// 			console.log(element)
// 			console.log(element.get(3))
// 			console.log(element.get(4))
// 			console.log(element.get(7))
			if(appendType =='first'){
				element.get(3).remove()
				element.get(4).remove()
				element.get(7).remove()
			}
// 			$('#col_item_price_pc'+'_'+cart_no).remove();
// 			$('#col_item_total_pc'+'_'+cart_no).remove();
			$('#th_item_price_pc'+'_'+cart_no).remove();
			$('#th_item_total_pc'+'_'+cart_no).remove();
			$('#th_item_cur_pc'+'_'+cart_no).remove();
		}
		if(stockYn == 'N'){
// 			$('#th_item_stock_pc'+'_'+cart_no).hide();
			var element = $('#cart_detail_table_pc_'+cart_no+' colgroup').children()
			console.log(element)
			
			if(appendType == 'first'){
				element.get(6).remove()
			}
			$('#th_item_stock_pc'+'_'+cart_no).remove();
		}
	}else{
		$('#check_pc_'+cart_no).attr("data-itemCnt", 0);
		$('#no_result_item_pc_'+cart_no).show();
		$('#excel_cart_items_pc'+'_'+cart_no).attr('onclick', "");
																			/* 장바구니에 엑셀 다운로드할 아이템이 없습니다. */
<%-- 		$('#excel_cart_items_pc'+'_'+cart_no).attr('href', "javascript:alert('<%=LangMng.LANG_D("FR000170", session_lang) %>');"); --%>
		var msg = LANG_SET['FR000170'];
		$('#excel_cart_items_pc'+'_'+cart_no).attr('href', 'javascript:alert("'+msg+'");');
		$('#cart_list_detail_head_pc_'+cart_no).hide();
	}
	if(itemDwlPathCnt == 0){
		$('#gtc_cart_pc'+'_'+cart_no).attr('onclick', "");
															/* 저장된 파일이 없습니다.  */
<%-- 		$('#gtc_cart_pc'+'_'+cart_no).attr('href', "javascript:alert('<%=LangMng.LANG_D("FR000153", session_lang) %>');");		 --%>
		var msg = LANG_SET['FR000153'];
		$('#gtc_cart_pc'+'_'+cart_no).attr('href', 'javascript:alert("'+msg+'");');		
	}
	$('#cart_list_detail_ctnt_pc_'+cart_no).hide();
// 	$('#cart_detail_list_mobile_'+cart_no).hide();
}

//itemPC와 모바일 분리
function appendItemListTableMoblie(cart_no, itemList, appendType, priceYN, stockYN){
// 	console.log("[ITEM LIST] : : "+JSON.stringify(itemList))
	//다운로드 가능한 아이템 path가 있는지 체크하기 위해
	var itemDwlPathCnt = 0;
	//아이템 삭제 후 테이블 다시 그릴 때 이전 내용 삭제 
	if(appendType == 'after'){
		$('#cart_detail_mobile_'+cart_no).children().not('#cart_detail_list_mobile_'+cart_no).remove();
	}
	
	if(itemList.length !=0){
		for(var i=0; i<itemList.length;i++){
			var plus_id = cart_no + '_'+i
			//mobile
			$('#cart_detail_list_mobile_'+cart_no).show()
			var cloneItem2 =  $('#cart_detail_list_mobile_'+cart_no).clone();
			$('#cart_detail_mobile_'+cart_no).append(cloneItem2);
			
			$('#cart_detail_mobile_'+cart_no).children().last().attr('id', 'cart_detail_list_mobile_'+plus_id);
			var ctntId2 = '#cart_detail_list_mobile_'+plus_id;
			
			//cart_detail_list_mobile
			$(ctntId2).find('#cart_detail_list_mobile').attr('id', 'cart_detail_list_mobile_'+plus_id);
			$(ctntId2).find('#no_result_item_mobile').attr('id', 'no_result_item_mobile_'+plus_id);
			$(ctntId2).find('#order_no_mobile').attr('id', 'order_no_mobile_'+plus_id);
			$(ctntId2).find('#designation_mobile').attr('id', 'designation_mobile_'+plus_id);
			$(ctntId2).find('#grade_mobile').attr('id', 'grade_mobile_'+plus_id);
			$(ctntId2).find('#currency_mobile').attr('id', 'currency_mobile_'+plus_id);
			$(ctntId2).find('#price_mobile').attr('id', 'price_mobile_'+plus_id);
			$(ctntId2).find('#quantity_mobile').attr('id', 'quantity_mobile_'+plus_id);
			$(ctntId2).find('#stock_mobile').attr('id', 'stock_mobile_'+plus_id);
			$(ctntId2).find('#total_mobile').attr('id', 'total_mobile_'+plus_id);
			$(ctntId2).find('#item_quantity_mobile').attr('id', 'item_quantity_mobile_'+plus_id);
			$(ctntId2).find('#item_gtc_down').attr('id', 'item_gtc_down_'+plus_id);
			$(ctntId2).find('#delete_item_mobile').attr('id', 'delete_item_mobile_'+plus_id);
			$(ctntId2).find('#item_price_section_mobile').attr('id','item_price_section_mobile'+'_'+plus_id);
			$(ctntId2).find('#item_cur_section_mobile').attr('id','item_cur_section_mobile'+'_'+plus_id);
			$(ctntId2).find('#item_total_section_mobile').attr('id','item_total_section_mobile'+'_'+plus_id);
			$(ctntId2).find('#item_stock_section_mobile').attr('id','item_stock_section_mobile'+'_'+plus_id);
			
			$('#no_result_item_mobile_'+plus_id).hide();
			$('#order_no_mobile_'+plus_id).html(itemList[i].matnr)
// 			$('#order_no_mobile_'+plus_id).attr('onclick','showItemDetailPop("'+itemList[i].matnr+'")')
			$('#designation_mobile_'+plus_id).html(itemList[i].designation)
			$('#grade_mobile_'+plus_id).html(itemList[i].grade)
			
			//어드민 설정에 따른 priceYN
			if(priceYN =='Y'){
				var price = numberFormatting(itemList[i].price,itemList[i].cur)
				$('#price_mobile_'+plus_id).html(price)
				var total_price = numberFormatting(itemList[i].total_price, itemList[i].cur)
				$('#total_mobile_'+plus_id).html(total_price)
				$('#currency_mobile_'+plus_id).html(itemList[i].cur)
			}else{
				$('#item_total_section_mobile'+'_'+plus_id).hide();
				$('#item_cur_section_mobile'+'_'+plus_id).hide();
				$('#item_price_section_mobile'+'_'+plus_id).hide();
			}
			
			$('#quantity_mobile_'+plus_id).val(itemList[i].qty)
			
			$('#delete_item_mobile_'+plus_id).attr('onclick','deleteItem('+cart_no+',"'+itemList[i].matnr+'","'+itemList[i].cur+'")')
			
			$('#total_mobile_'+plus_id).val(itemList[i].total_price)
			$('#quantity_mobile_'+plus_id).attr('onchange','editItemQuantity('+cart_no+',"'+itemList[i].matnr+'",'+i+',"'+itemList[i].cur+'")')
			
			//파일 다운로드 설정 
			if(itemList[i].file_dwl_path !=''){
				itemDwlPathCnt++;
				//장바구니 아이템 단일 gtc다운로드 권한에 따른처리
				if(USERAUTH.isOneGtcDownAuth){
					$('#item_gtc_down_'+plus_id).attr('href', '<%=rootPath%>'+itemList[i].file_dwl_path)
				}else{
					var msg = LANG_SET['FR000229'];
					$('#item_gtc_down_'+plus_id).attr('href', 'javascript:alert("'+msg+'");')
				}
			}else{
				/* var msg = LANG_SET['FR000153']; */
// 				$('#item_gtc_down_'+plus_id).attr('href', 'javascript:alert("'+msg+'");')
				$('#item_gtc_down_'+plus_id).hide();
			}
			//어드민 설정여부에 따른 재고표시
			if(stockYN == 'Y'){
				//재고 표시
				if(itemList[i].stock_yn == 'Y'){
													/* In Stock  */
					$('#stock_mobile_'+plus_id).html(LANG_SET['FR000227'])
					$('#stock_mobile_'+plus_id).attr('data-lang', 'FR000227')
				}else{
													/* Out of Stock  */
					$('#stock_mobile_'+plus_id).html(LANG_SET['FR000226'])
					$('#stock_mobile_'+plus_id).attr('data-lang', 'FR000226')
				}
			}else{
				$('#item_stock_section_mobile'+'_'+plus_id).hide();
			}
			
		}//end of for
		$('#cart_detail_list_mobile_'+cart_no).hide();
	}else{
		$('#check_mobile_'+cart_no).attr("data-itemCnt", 0);
		//no item 표시
		var tbody_id = '#cart_detail_tbody_'+cart_no
		$(tbody_id).children().not('#no_result_item_mobile').remove();
		//show 아이템 없음 표시
		$('#no_result_item_mobile').show();
		
		$('#excel_down_mobile'+'_'+cart_no).attr('onclick', "");
															/* 장바구니에 엑셀 다운로드할 아이템이 없습니다. */
		$('#excel_down_mobile'+'_'+cart_no).attr('href', 'javascript:alert("'+LANG_SET['FR000170']+'");');
	}
	if(itemDwlPathCnt == 0){
		$('#gtc_down_mobile'+'_'+cart_no).attr('onclick', "");
															/* 저장된 파일이 없습니다. */
		var msg = LANG_SET['FR000153']
		$('#gtc_down_mobile'+'_'+cart_no).attr('href', 'javascript:alert("'+LANG_SET['FR000153']+'");');
	}
	
}
//장바구니 그룹의 open버튼 onAndOffClass
function onAndOffClass(cart_no){
	// 하나의 카트가 오픈되면 나머지 카트는 close 
	$('.cart_list.pc:not(#cart_pc_'+ cart_no +') .btn_cart .open_cart.on').click()	// PC
// 	$('.cart_list.pc:not(#cart_pc_'+ cart_no +') .btn_cart .open_cart.on').click()	// Mobile
	//디바이스 타입에 따라 제어해야하는 부분이 구분되어있음
		if($('#open_mobile_'+cart_no).hasClass('on') ){
			$('#open_mobile_'+cart_no).removeClass('on');
// 			<!-- Open -->
			$('#open_mobile_'+cart_no).attr('data-lang','FR000077');
<%-- 			$('#open_mobile_'+cart_no).html('<%=LangMng.LANG_D("FR000077", session_lang) %>') --%>
			$('#open_mobile_'+cart_no).html(LANG_SET['FR000077'])
			$('#cart_detail_mobile_'+cart_no).hide();
		}else{
			$('#open_mobile_'+cart_no).addClass('on');
// 			<!-- close -->
			$('#open_mobile_'+cart_no).attr('data-lang','FR000090');
<%-- 			$('#open_mobile_'+cart_no).html('<%=LangMng.LANG_D("FR000090", session_lang) %>') --%>
			$('#open_mobile_'+cart_no).html(LANG_SET['FR000090'])
			$('#cart_detail_mobile_'+cart_no).show();
		}
	
		if($('#open_pc_'+cart_no).hasClass('on') ){
			$('#open_pc_'+cart_no).removeClass('on');
// 			<!-- Open -->
			$('#open_pc_'+cart_no).attr('data-lang','FR000077');
			$('#open_pc_'+cart_no).html(LANG_SET['FR000077'])
<%-- 			$('#open_pc_'+cart_no).html('<%=LangMng.LANG_D("FR000077", session_lang) %>') --%>
			$('#cart_detail_pc_'+cart_no).hide();
		}else{
			$('#open_pc_'+cart_no).addClass('on');
// 			<!-- close -->
			$('#open_pc_'+cart_no).attr('data-lang','FR000090');
			$('#open_pc_'+cart_no).html(LANG_SET['FR000090'])
<%-- 			$('#open_pc_'+cart_no).html('<%=LangMng.LANG_D("FR000090", session_lang) %>') --%>
			$('#cart_detail_pc_'+cart_no).show();
		}
}
//check box event
function checkBoxEvent(check_type, cart_no/*All 일경우 ''*/){
	if(check_type == 'all'){
		if($("#selectAll").is(":checked")) { 
			//모두 선택을 누르기전에 각각 선택했을 수도 있으므로 초기화
			checkedCart = [];
			//모바일
			if(devType == 'M'){
				$('input[name="check_mobile"]').not('#check_mobile').prop("checked", true);
				$('input[name="check_mobile"]:checked').each(function(){
					checkedCart.push($(this).val());
				})
			}else{
				//PC
				$('input[name="check_pc"]').not('#check_pc').prop("checked", true);
				$('input[name="check_pc"]:checked').each(function(){
					$(this).is(":checked") ?  checkedCart.push($(this).val()) : ''
				})
			}
		}else{ 
			devType == 'M'? $('input[name="check_mobile"]').prop("checked", false) : $('input[name="check_pc"]').prop("checked", false)
			checkedCart = [];
		}
	}else{
// 		//checkbox 이벤트 단일 선택일때
		var idx = checkedCart.indexOf(cart_no.toString());
		if(idx == -1){
			checkedCart.push(cart_no.toString())
// 			devType == 'M'? $('#cart_nm_mobile_'+cart_no).focus()	: $('#cart_nm_pc_'+cart_no).focus()
		} else{
			
			if(devType =='P' && $('#check_pc_'+cart_no).data('type')=='E'){
				//수정영역 비활성화
				$('#cart_nm_pc_'+cart_no).attr("readonly","readonly");
				$('#cart_desc_pc_'+cart_no).attr("readonly" ,"readonly");
			}
			if(devType =='M' && $('#cart_mobile_'+cart_no).data('type')=='E'){
				//수정영역 비활성화
				$('#cart_nm_mobile_'+cart_no).attr("readonly" ,"readonly");
				$('#cart_desc_mobile_'+cart_no).attr("readonly" ,"readonly");
			}
			if($('#check_pc_'+cart_no).data('type')=='A'){
				$('#cart_pc_'+cart_no).remove();
			}
			if($('#check_mobile_'+cart_no).data('type')=='A'){
				$('#cart_mobile_'+cart_no).remove();
			}
			
			checkedCart.splice(idx,1);
			//전체선택이 체크되어있으면해제
			if($('#selectAll').is(':checked')){$('#selectAll').prop("checked",false)}
		}
	}
}
//카트 삭제
function deleteCart(){
	if(checkedCart.length == 0){
		/* 장바구니를 선택해주세요*/
<%-- 		alert('<%=LangMng.LANG_D("FR000164", session_lang) %>'); --%>
		alert(LANG_SET['FR000164']);
		return;
	}
		
	/* 삭제하시겠습니까 */
<%-- 	if(confirm('<%=LangMng.LANG_D("FR000076", session_lang) %>')){ --%>
	if(confirm(LANG_SET['FR000076'])){
		var formData = new FormData();
		formData.append("actionID",'<%=BasketConstKey.ACTION_BASKET_DEL_OK%>');
		formData.append("deleteParam", checkedCart);
		formData.append("user_id", USERINFO.user_id);
		
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
			data: formData,
			dataType: 'json',
			async: true,
			cache: false,
			contentType: false,
	        processData: false,
	        enctype: 'multipart/form-data',
	        beforeSend: function(){
	        	$('#loadbar').show();
	        },
			success: function(result) {
					checkedCart = [];
					if(result.resCode =='0'){
						$('#cartCnt_top').html(result.cartCnt);			
						$('#cartCnt_rgt_wrap').html(result.cartCnt);			
					}
					getBasketList('first');
			}, error: function (e){
// 				console.log(e);
			},
			complete: function(){
			  $('#loadbar').fadeOut();
			}
		});
	}
}

function clickUpdateButton(){
	/* 수정할 장바구니를 선택해주세요 */
	if(checkedCart.length == 0){
		alert(LANG_SET["FR000164"]);
<%-- 		alert('<%=LangMng.LANG_D("FR000164", session_lang) %>'); --%>
		return;
	}
	//수정영역 활성화
	for(var i =0; i<checkedCart.length; i++){
		$('#cart_nm_pc_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_desc_pc_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_nm_mobile_'+checkedCart[i]).removeAttr("readonly");
		$('#cart_desc_mobile_'+checkedCart[i]).removeAttr("readonly");
	}
}

//카트 수정
function updateCartInfo(updateData){
		var formData = new FormData();
		formData.append("actionID",'<%=BasketConstKey.ACTION_BASKET_EDIT_OK%>');
		formData.append("editParam",JSON.stringify(updateData));
		formData.append("user_id", USERINFO.user_id);
		
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
			data: formData,
			dataType: 'json',
			async: true,
			cache: false,
			contentType: false,
	        processData: false,
	        enctype: 'multipart/form-data',
			success: function(result) {
					checkedCart = [];
					getBasketList('first');
			}, error: function (e){
// 				console.log(e);
			}
			
		});
}
//카트 추가
function insertCartInfo(insertData){
	var formData = new FormData();
	formData.append("actionID", "<%=BasketConstKey.ACTION_BASKET_ADD_OK%>");
	formData.append("addParam", JSON.stringify(insertData));
	formData.append("user_id", USERINFO.user_id);
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
		data: formData,
		dataType: 'json',
		async: true,
		cache: false,
		contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
		success: function(result) {
				checkedCart = [];
				if(result.resCode =='0'){
						$('#cartCnt_top').html(result.cartCnt);			
						$('#cartCnt_rgt_wrap').html(result.cartCnt);			
				}
				getBasketList('first');
				
		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
}

//카트 정보 저장
function saveCartInfo(){
	//선택된 카트 없을 경우
	if(checkedCart.length == 0){
		/* 장바구니를 선택해주세요 */
		alert(LANG_SET["FR000164"]);
<%-- 		alert('<%=LangMng.LANG_D("FR000164", session_lang) %>') --%>
		return;
	}
	
		/* 저장하시겠습니까? */
<%-- 	if(confirm('<%=LangMng.LANG_D("FR000073", session_lang) %>')){ --%>
	if(confirm(LANG_SET['FR000073'])){
		var tmpDataAdd = new Array();
		var tmpDataEdit = new Array();
		if(devType=='M'){
			for(var i=0;i<checkedCart.length;i++){
				if( $('#cart_nm_mobile_'+checkedCart[i]).val() ==''){
					/* 카트이름을 입력해주세요. */
					alert(LANG_SET["FR000074"]);
<%-- 					alert('<%=LangMng.LANG_D("FR000074", session_lang) %>') --%>
					return;
				}					
				if($('#check_mobile_'+checkedCart[i]).data('type') == 'A'){
					var objAdd = new Object();					
					objAdd.cart_nm = $('#cart_nm_mobile_'+checkedCart[i]).val()
					objAdd.cart_desc = $('#cart_desc_mobile_'+checkedCart[i]).val()
					
					tmpDataAdd.push(objAdd);
				}else{
					var objEdit = new Object();
					objEdit.cart_no = $('#check_mobile_'+checkedCart[i]).val()
					objEdit.cart_nm = $('#cart_nm_mobile_'+checkedCart[i]).val()
					objEdit.cart_desc = $('#cart_desc_mobile_'+checkedCart[i]).val()
					
					tmpDataEdit.push(objEdit);
				}
			}
		}else{
			var obj; 
			for(var i=0;i<checkedCart.length;i++){
				if( $('#cart_nm_pc_'+checkedCart[i]).val() ==''){
					/* 카트이름을 입력해주세요. */
					alert(LANG_SET["FR000074"]);
<%-- 					alert('<%=LangMng.LANG_D("FR000074", session_lang) %>') --%>
					return;
				}	
				if($('#check_pc_'+checkedCart[i]).data('type') == 'A'){
					var objAdd = new Object();					
					objAdd.cart_nm = $('#cart_nm_pc_'+checkedCart[i]).val()
					objAdd.cart_desc = $('#cart_desc_pc_'+checkedCart[i]).val()
					tmpDataAdd.push(objAdd);
				}else{
					var objEdit = new Object();
					objEdit.cart_no = $('#check_pc_'+checkedCart[i]).val()
					objEdit.cart_nm = $('#cart_nm_pc_'+checkedCart[i]).val()
					objEdit.cart_desc = $('#cart_desc_pc_'+checkedCart[i]).val()
					tmpDataEdit.push(objEdit);
				}
			}
		}	
		if(tmpDataEdit.length !=0){ updateCartInfo(tmpDataEdit)}
		if(tmpDataAdd.length !=0){ insertCartInfo(tmpDataAdd)}
	}
}

//request 화면으로 이동
function goRequest(request_type, cart_no){
// 	alert("작업중");
	
	//여러개 선택해서 주문화면으로 넘길 때
	if(request_type == 'multi'){
		var cartNo;
		//처음부터 선택한 카트가 없을 때
		if(checkedCart.length ==0){
			/* 장바구니를 선택해주세요*/
			alert(LANG_SET["FR000164"]);
<%-- 			alert('<%=LangMng.LANG_D("FR000164", session_lang) %>'); --%>
			return;
		}else{
			//모바일
			if(devType == 'M'){
				//for문으로 체크된 카트의 아이템이 있는지 검증
				for(var i =0; i< checkedCart.length; i++){
// 					console.log("in for loop")
					if($('#check_mobile_'+checkedCart[i]).data('itemcnt') == "0"){
						var idx = checkedCart.indexOf(checkedCart[i].toString());
						checkedCart.splice(idx,1);
					}			
				}
				//검증 후 cartNo set
				checkedCart.length !=0 ? cartNo = checkedCart.join() : cartNo = ''
				if(cartNo != ''){
					//cartNo가 빈값이 아닐 경우만 order 화면으로 넘긴다
					$('input[name="cart_no"]').val(cartNo)
					goOrderPage.submit();
// 					goPageTwo(goOrderPage, '');
				}else{
					/* <!-- 장바구니에 추가된 아이템이 없습니다. --> */
					alert(LANG_SET["FR000169"]);
<%-- 					alert('<%=LangMng.LANG_D("FR000169", session_lang) %>') --%>
					return;
				}
				
			}else{
				//PC 
				for(var i =0; i< checkedCart.length; i++){
					if($('#check_pc_'+checkedCart[i]).data('itemcnt') == "0"){
						var idx = checkedCart.indexOf(checkedCart[i].toString());
						checkedCart.splice(idx,1);
					}
				}
				//검증 후 cartNo set
				checkedCart.length !=0 ? cartNo = checkedCart.join() : cartNo = ''
				if(cartNo != ''){
					//cartNo가 빈값이 아닐 경우만 order 화면으로 넘긴다
					$('input[name="cart_no"]').val(cartNo)
					goOrderPage.submit();
// 					goPageTwo(goOrderPage, '');
				}else{
					/* <!-- 장바구니에 추가된 아이템이 없습니다. --> */
					alert(LANG_SET.FR000169);
<%-- 					alert('<%=LangMng.LANG_D("FR000169", session_lang) %>') --%>
					return;
				}
			}
		}
	}else{
		//단건으로 주문을 요청할 때
		if(devType =='M'){
			//PC 
			if($('#check_mobile_'+cart_no).data('itemcnt') == "0"){
				/* <!-- 장바구니에 추가된 아이템이 없습니다. --> */
				alert(LANG_SET["FR000169"]);
<%-- 				alert('<%=LangMng.LANG_D("FR000169", session_lang) %>') --%>
				return;
			}else{
				$('input[name="cart_no"]').val(cart_no)
				goOrderPage.submit();
// 				goPageTwo(goOrderPage, '');
			}
		}else{
			//PC 
			if($('#check_pc_'+cart_no).data('itemcnt') == "0"){
				/* <!-- 장바구니에 추가된 아이템이 없습니다. --> */
				alert(LANG_SET["FR000169"]);
<%-- 				alert('<%=LangMng.LANG_D("FR000169", session_lang) %>') --%>
				return;
			}else{
				$('input[name="cart_no"]').val(cart_no)
				goOrderPage.submit();
// 				goPageTwo(goOrderPage, '');
			}
		}
	}

}

//카트 그룹 내의 아이템 수량 변경 
function editItemQuantity(cart_no, item_no/*matnr*/,index, currency){
	var formData = new FormData();
	formData.append("actionID",'<%=BasketConstKey.ACTION_BASKET_ITEM_EDIT_OK%>');
	formData.append("user_id", USERINFO.user_id);
	formData.append("cart_no",cart_no);
	formData.append("matnr",item_no);
	formData.append("cur",$('#btnCurr :selected').val());
	formData.append("comm_cur",$('#btnCurr :selected').val());

	if(devType =='M'){
		if($('#quantity_mobile_'+cart_no+'_'+index).val() > 999|| $('#quantity_mobile_'+cart_no+'_'+index).val() < 0){
			/*아이템 수량은 최소 0부터 999까지 선택가능합니다.*/
				alert(LANG_SET["FR000171"]);
<%-- 			alert('<%=LangMng.LANG_D("FR000171", session_lang) %>'); --%>
			return false;
		}
		formData.append("qty",$('#quantity_mobile_'+cart_no+'_'+index).val());
	}else{
		if($('#quantity_pc_'+cart_no+'_'+index).val() > 999 || $('#quantity_pc_'+cart_no+'_'+index).val() < 0){
			/*아이템 수량은 최소 0부터 999까지 선택가능합니다.*/
			alert(LANG_SET["FR000171"]);
<%-- 			alert('<%=LangMng.LANG_D("FR000171", session_lang) %>'); --%>
			return false;
		}
		formData.append("qty",$('#quantity_pc_'+cart_no+'_'+index).val());
		
	}
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
		data: formData,
		dataType: 'json',
		async: true,
		cache: false,
		contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        beforeSend: function(){
        	$('#loadbar').show();
        },
		success: function(result) {
// 			if
			var price = numberFormatting(result.total_price, currency);
			//카트 그룹의 총액
			$('#total_price_cart_pc_'+cart_no).html(price+" ");
			$('#total_price_cart_mobile_'+cart_no).html(price+" ");
			
			//수량이 변경된 아이템의 총액
			var item_total = numberFormatting(result.item_total, currency);
			$('#total_pc_'+cart_no+'_'+index).html(item_total);
			$('#total_mobile_'+cart_no+'_'+index).html(item_total);

		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
	
}

//파일 다운로드 
function fileDownload(file_type/*excel, gtc*/, down_type/*cart or item*/, no/*cart_no*/){
		var param;
		
		if(file_type == 'excel'){
			param = {
					actionID : '<%=ConstKey.ACTION_GET_EXCEL%>'
					,cart_no : no
					,user_id : USERINFO.user_id
			}
			
			
		}else{
			//카트그룹의 전체 gtc 파일을 넘길때
			param = {
					actionID : '<%=BasketConstKey.ACTION_GET_GTC_ZIP_FILE_PATH%>'
					,cart_no : no
					,user_id : USERINFO.user_id
			}
			<%-- $('#cart_no').val(no);
			$('#user_id').val('<%=userInfo.getUser_id()%>');
		goPageTwo(downLoadGtcZipForm, "<%=BasketConstKey.ACTION_GET_GTC_ZIP_FILE_PATH%>"); --%>
			
		}
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
			data: param,
			dataType: 'json',
			async: true,
			cache: false,
	        beforeSend: function(){
	        	$('#loadbar').show();
	        },
			success: function(result) {
// 				console.log(arguments);
				if(file_type == 'excel'){
// 					console.log("upload Path here ============")
// 					console.log(result.resData)
// 					console.log("upload Path here ============")
					excel_file_path = result.resData;	// 물리주소 
					file_name = excel_file_path.split("\\");
					file_name = file_name[file_name.length - 1];	// \단위로 분리한 물리주소 마지막 값은 파일명
// 					console.log("excel_file_path ==> " + excel_file_path);
// 					console.log("file_name ==> " + file_name);
					doExcelFileDown()
				}
				if(file_type == 'gtc'){
					gtc_file_path = result.resData;
					gtc_file_name = gtc_file_path.split("\\");
					gtc_file_name = gtc_file_name[gtc_file_name.length - 1];
					doGtcZipFileDown()
				}

			}, error: function (e){
//	 			console.log(e);
			},
			complete: function(){
				  $('#loadbar').fadeOut();
			}
		});
}
//아이템 삭제
function deleteItem(cart_no, item_no){
	//멘트 다국어처리**
<%-- 	if(confirm('<%=LangMng.LANG_D("FR000076", session_lang) %>')){ --%>
	if(confirm(LANG_SET['FR000076'])){
		var formData = new FormData();
		formData.append("actionID",'<%=BasketConstKey.ACTION_BASKET_ITEM_DEL_OK%>');
		formData.append("cart_no",cart_no);
		formData.append("matnr",item_no);
		formData.append("user_id", USERINFO.user_id);
		formData.append("cur",$('#btnCurr :selected').val());
		
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.BASKET_URL%>?v=' + (new Date().getTime()),
			data: formData,
			dataType: 'json',
			async: true,
			cache: false,
			contentType: false,
	        processData: false,
	        enctype: 'multipart/form-data',
	        beforeSend: function(){
	        	$('#loadbar').show();
	        },
			success: function(result) {
				if(result.resCode=='0'){
					var curr = $('#btnCurr :selected').val()
					var currency;
					if(curr == 'CC0076'){
						currency = 'KRW'
					}else if(curr == 'CC0077'){
						currency = 'USD'
					}else if(curr == 'CC0078'){
						currency = 'EUR'
					}else{
						currency = 'USD'
					}
					var price = numberFormatting(result.total_price,currency )
					//카트 그룹의 총액
					$('#total_price_cart_pc_'+cart_no).html(price+" ");
					$('#total_price_cart_mobile_'+cart_no).html(price+" ");
	// 				getBasketList('first');
					//해당 카트의 아이템 목록만 다시 그려주기 pc와 모바일 둘다
	 				appendItemListTable(cart_no ,result.basket_detail_list, 'after',result.priceYN,result.stockYN);
	 				appendItemListTableMoblie(cart_no ,result.basket_detail_list, 'after',result.priceYN,result.stockYN);
				}
			}, error: function (e){
// 				console.log(e);
			},
			complete: function(){
				  $('#loadbar').fadeOut();
			}
		});
	}
}

//아이템 디테일 팝업
function showItemDetailPop(item_no, ig_cd, cart_no){
	iframeSrc = '<%=ConstUrl.ITEM_DETAIL_URL%>'+'?cart_no='+cart_no+'&search_ig_cd='+ig_cd+'&search_matnr='+item_no
 	$('#ifr_item_detail').attr('src',iframeSrc)
 	
	$(".open_pop.cart_item_detail").fadeIn("fast");
    $(".open_pop.cart_item_detail .in > div").hide();
    $(".pop_cont").fadeIn();
    return; 
}


// 카트 엑셀 다운로드
function doExcelFileDown() {
	$("#excel_file_path", $cartItemExceldownForm).val(excel_file_path);
	$("#atch_file_name", $cartItemExceldownForm).val(file_name);
	goPageTwo(cartItemExceldownForm, "<%=ConstKey.ACTION_GET_FILE%>");
	
	
	$('#cartExceldownForm').actionID = "";
}	// doExcelFileDown()

// 장바구니 전체 GTC 다운로드
function doGtcZipFileDown() {
	$("#gtc_file_path", $cartGtcZipdownForm).val(gtc_file_path);
	$("#gtc_file_name", $cartGtcZipdownForm).val(gtc_file_name);
	goPageTwo(downLoadGtcZipForm, "<%=BasketConstKey.ACTION_GET_GTC_ZIP_FILE%>");
	$('#downLoadGtcZipForm').actionID = "";
}

function numberFormatting(price, currency){
	var resultPrice;
	if(currency == 'KRW'){
		resultPrice = price;		
	}else{
		resultPrice = parseFloat(price).toFixed(2);
	}
	//console.log(resultPrice);
	
	return resultPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
</script>

 <!-- +++++++++++++++++++++++++++++아이템 레이어 팝업 표시 ! ++++++++++++++++++++++++++ -->
 <div class="open_pop cart_item_detail" style="z-index:9999">
    <div class="in" style="max-height:100%;">
        <a href="" class="close_pop"><img style="width:auto;" src="./img/btn_close.png" alt="닫기"></a>
        <div class="pop_cont"  style="overflow-y:hidden; max-height:850px;">
        	<iframe src="" style="width:1200px; height:780px;" id="ifr_item_detail">
        	</iframe>
        </div>
    </div>
</div>
 