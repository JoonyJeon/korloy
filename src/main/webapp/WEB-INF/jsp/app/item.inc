  <%----------------------------------------------------------------------------------------
 - 파일이름	: item.inc
 - 설    명		: itemList > item 화면
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2021.08.05    1.0       WYA      신규작성
------------------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<style>
#contents .expand_3d {
    display: block;
    background: #fff url(../img/bg_expand.png) no-repeat right 15px bottom 15px;
    background-size: 20px;
    height: 400px;
}

.open_cart_pop .in {
	display:inline-block;
	vertical-align: middle; 
	background: #fff;
	position:relative;
	border-radius: 20px;
	padding: 40px 20px
;}


.open_pop .in .pop_cont {
    height: 620px;
    width: 800px;
    text-align: center;
    padding: 40px;
    overflow: hidden;
    height: auto;
    max-height: 600px;
    /* overflow-y: auto; */
}

.picture_wrap .picture .big a.expand_pop {
    display: block;
    border: 1px solid #aeaeae;
    padding: 40px 0;
    height: 230px !important;
    line-height: 230px;
}

#contents .expand_pop {
    display: block;
    background: #fff url(../img/bg_expand.png) no-repeat right 15px bottom 15px;
    background-size: 20px;
}

.box_top{
    cursor:pointer;
}
@media only screen and (max-width: 461px){
	.open_cart_pop .in {
	    width: 80% !important;
	    height: 60% !important;
	}
	.open_cart_pop .in .pop_cont{
	    height: inherit !important;
	}
	.open_cart_pop .in .pop_cont .pop_img{
	    line-height: 400px !important;
	    height:60% !important;
	}
	.open_cart_pop .in .pop_cont .pop_img img{
	    width:80% !important;
	    height:auto !important;
	}
	/* .open_cart_pop .in .pop_cont .pop_img iframe#3d_rendering_pop{
	    width:94%;
	    min-height:350px;
	    height:auto;
	} */
}
.listTable table tbody tr td {
	height: 30px; 
}

/*  .item_wrap .listTable table {
    min-width: 749px;
    width : 100%
}  */

.ellipsis{
	max-width:200px; /*max-width속성 추가*/
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}




</style>


<div class="box" id="area_itemList">
    <div class="box_top" id="ig_box_top" onclick="addRemoveOn('ig_box_top')">
        <%--Item Group --%>
        <span id="icodian_ig" data-lang="FR000031"><%= LangMng.LANG_D("FR000031", session_lang)%></span>
        <a id="toggle_itemList" class="btn_down" onclick="addRemoveOn('ig_box_top');"><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
    </div>
    <div class="box_cont" id="area_itemList_sub">
        <div class="itemtit">
            <p><img id="ig_brand_logo" alt="brand logo" style="display:none; max-width: 80px;"  src=""  onerror="onErrorImage(this)"><em id="ig_tm_nm" style="color: #0094da; font-size: 36px; font-weight: 500;"></em><em id="ig_nm" style="font-size: 20px; margin-left: 25px;"></em></p>
            <span id="ig_dscr"></span>
            <%-- More Info --%>
            <div class="info"><a id="btn_ig_more_info" class="btn_info" data-lang="FR000035"><%= LangMng.LANG_D("FR000035", session_lang)%></a></div>
        </div>
        <div class="picture_wrap">
            <div class="picture">
               <%if("P".equals(devType)){ %>
                <div class="big"><a onclick="openIgImgPop('IG')" class="expand_pop" id="btnIgBig_pc"><img id="ig_image"  alt=""  src="" onerror="onErrorImage(this)"></a></div>
               <%}else{ %>
                <div class="big"><a href="javascript:void(0);" class="expand_pop" id="btnIgBig_mob" style="background:none;pointer-events: none;"><img id="ig_image"  alt=""  src="" onerror="onErrorImage(this)"></a></div>
               <%}%>
                <div class="small">
                    <div class="swiper-container">
                        <div class="swiper-wrapper" id="ig_sub_img_div">
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="picture_detail">
                <div class="picture_tab">
                    <ul>
                            <%-- ISO 13399 --%>
							<li><a id="btn_ig_iso" class="on" style="cursor:pointer;" data-lang="FR000036"><%= LangMng.LANG_D("FR000036", session_lang)%></a></li>
							<%-- Non-ISO --%>
							<li><a id="btn_ig_non_iso" style="cursor:pointer;" data-lang="FR000037"><%= LangMng.LANG_D("FR000037", session_lang)%></a></li>
                    </ul>
                </div>
                 <%if("P".equals(devType)){ %>
					<a onclick="openIsoPop('IG')" class="expand_pop" style="height: 287px; max-height: 290px;" id="btnIgIsoBig_pc"><img id="ig_dd_image" alt="" src="" onerror="onErrorImage(this)" style="max-height: 290px;"></a>
                 <%}else{ %>
					<a href="javascript:void(0);" class="expand_pop" style="height: 287px; max-height: 290px; background:none;pointer-events: none;" id="btnIgIsoBig_mob"><img id="ig_dd_image" alt="" src="" onerror="onErrorImage(this)" style="max-height: 290px;"></a>
                 <%}%>
            </div>
        </div>
        
        <div class="box">
            <div class="box_top" id="itemList_box_top" >
            	<%-- Select Column --%>
                <span id="igSelectCol" data-lang="FR000120"><%= LangMng.LANG_D("FR000120", session_lang)%></span>
                <a  class="btn_down"><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display:none;">
                <div class="filter_detail">
                    <ul id="ig_filter_ul"> 
 
                    </ul>
                </div>
                
                <div class="btn_group">
                    <div class="right">
                        <%-- RESET --%>
                        <a id="btn_ig_filter_reset" class="reset" style="cursor:pointer;" data-lang="FR000060"><%= LangMng.LANG_D("FR000060", session_lang)%></a>
                    </div>
                </div>
                
            </div>
            
            <!-- Item Group List pc-->
            <div class="view_cont pc">
            	<div class="listTable">
                    <table id="table_itemList">                        
                        
                    </table>
                </div>
            </div>
            <!-- Item Group List mobile-->
            <div class="box_cont mobile" id="itemList_mob">
			</div>
        </div>
        
    </div>
</div> 

	
<!--###################################### [ITEM] ######################################-->

<div class="box" id="area_item">
    <div class="box_top" id="item_ig_box_top" onclick="addRemoveOn('item_ig_box_top')">
    	<%-- Item --%>
        <span id="icodian_it" data-lang="FR000121"><%= LangMng.LANG_D("FR000121", session_lang)%></span>
        <a id="toggle_item"  onclick="addRemoveOn('item_ig_box_top');" class="btn_down"><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
    </div>
    <div class="box_cont"  id="area_item_sub">
        <div class="itemtit">
            <p><img id="it_brand_logo" alt="brand logo" style="display:none; max-width: 80px;"  src=""  onerror="onErrorImage(this)"><em id="it_tm_nm" style="color: #0094da; font-size: 36px; font-weight: 500;"></em><em id="it_ig_nm"  style="font-size: 20px; margin-left: 25px;"></em></p>
            <span id="it_ig_dscr"></span>
            <%-- More Info --%>
            <div class="info"><a id="btn_it_more_info"  class="btn_info" data-lang="FR000035"><%= LangMng.LANG_D("FR000035", session_lang)%></a></div>
        </div>
        <div class="picture_wrap">
            <div class="picture">
              <%if("P".equals(devType)){ %>
                	<div class="big"><a onclick="openIgImgPop('IT')" class="expand_pop" id="btnItemBig_pc"><img id="it_image" alt=""  src=""  onerror="onErrorImage(this)"></a></div>
                <%}else{ %>
                	<div class="big"><a href="javascript:void(0);" class="expand_pop" style="background:none;pointer-events: none;" id="btnItemBig_mob"><img id="it_image" alt=""  src=""  onerror="onErrorImage(this)"></a></div>
                <%} %>
                <div class="small">
                    <div class="swiper-container">
                        <div class="swiper-wrapper" id="it_ig_sub_img_div">
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="picture_detail">
                <div class="picture_tab">
                    <ul>
                        <%-- ISO 13399 --%>
                    	<li><a id="btn_it_ig_iso" class="on" style="cursor:pointer;" data-lang="FR000036"><%= LangMng.LANG_D("FR000036", session_lang)%></a></li>
                    	<%-- Non-ISO --%>
                        <li><a id="btn_it_ig_non_iso" style="cursor:pointer;" data-lang="FR000037"><%= LangMng.LANG_D("FR000037", session_lang)%></a></li>
                    </ul>
                </div>
                 <%if("P".equals(devType)){ %> 
                	<a onclick="openIsoPop('IT')" class="expand_pop" id="btnItemIsoBig_pc" style="height: 287px; max-height: 290px;"><img id="it_ig_dd_image" alt=""  src=""  onerror="onErrorImage(this)" style="max-height: 290px;"></a>
                <%}else{ %>
                	<a href="javascript:void(0);" class="expand_pop" id="btnItemIsoBig_mob" style="height: 287px; max-height: 290px; background:none;pointer-events: none;"><img id="it_ig_dd_image" alt=""  src=""  onerror="onErrorImage(this)" style="max-height: 290px;"></a>
                <%} %>
            </div>
        </div>
        
        <div class="box">
            <div class="box_top" >
            	<%-- Select Column --%>
                <span id="itSelectCol" data-lang="FR000120"><%= LangMng.LANG_D("FR000120", session_lang)%></span>
                <a class="btn_down"><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display:none;">
            	<div class="filter_detail">
                    <ul id="it_filter_ul"> 
 
                    </ul>
                </div>
                <div class="btn_group">
                    <div class="right">
                        <%-- RESET --%>
                        <a id="btn_it_filter_reset" class="reset" style="cursor:pointer;" data-lang="FR000060"><%= LangMng.LANG_D("FR000060", session_lang)%> </a>
                    </div>
                </div>
            </div>
            
            <!-- (PC) 아이템정보 -->
            <%-- <%if("P".equals(devType)){ %> --%>
            <div class="box_cont pc">
	             <div class="view_cont">
	             	<div class="listTable">
	             		<table id="table_itemInfo1">  
                    	</table>
                	</div>
                	<div class="listTable" style="margin-left: 0px; overflow-x: hidden; border-left: 1px solid #ccc;">   
                    	<table id="table_itemInfo2">
                    	</table>
                	</div>
	                 
	             </div>
            </div>
            <%-- <%}else{ %> --%>
            <!-- (모바일) 아이템정보 -->
            <div class="box_cont mobile">
	            <div class="listTable noMargin noScroll blueLine">
	            	<p class="currentItem" ><a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="" class="bnt_cart" data-evtcd="EC0017" id="item_cart_mob"><img src="/img/ico_cart.png" alt=""></a><span id="item_desig_mob"></span></p>
				   <table class="mobileDetailItem" style="display:table !important;" id="item_mob_bar">
				      <colgroup>
				         <col width="100%">
				      </colgroup>
				      <tbody>
				         <tr>
				            <th class="mdTitle"><span data-lang="FR000038"><%= LangMng.LANG_D("FR000038", session_lang)%></span><span> : </span><span id="item_matnr_mob"></span></th>
				         </tr>
				         <tr>
				            <td>
				               <div class="mdTable">
				                  <table id="table_itemInfo1_mobile" >
				                  </table>
				                  <table class="mar10" id="table_itemInfo2_mobile">
				                  </table>
				               </div>
				            </td>
				         </tr>
				      </tbody>
				   </table>
	            </div>
	            <div class="listTable blueLine  noMargin noScroll">
	                <table>
	                    <colgroup>
	                        <col width="33.33%">
	                        <col width="*">
	                        <col width="*">
	                        <col width="33.33%">
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <%-- GTIN(EAN) --%>
	                            <th data-lang="FR000175"><%= LangMng.LANG_D("FR000175", session_lang)%></th>
	                            <%-- File Download --%>
	                            <th colspan="2" data-lang="FR000043"><%= LangMng.LANG_D("FR000043", session_lang)%></th>
	                            <%-- Add to Assembly --%>
	                            <th data-lang="FR000044"><%= LangMng.LANG_D("FR000044", session_lang)%></th>
	                        </tr>
	                        <tr>
	                            <td id="it_m_gtin"></td>
	                            <%-- GTC --%>
	                            <td><a id="m_it_gtc_file_url" style="cursor:pointer;" onclick="mDownGtcFile()" data-lang="FR000079"><%= LangMng.LANG_D("FR000079", session_lang)%></a></td>
	                            <%-- P21 --%>
	                            <td><a id="m_it_p21_file_url" style="cursor:pointer;" onclick="mDownP21File()">P21</a></td>
	                            <td><img onclick="javascript:goAssembly();" src="<%=PATH_IMG%>/img_assembly.png" alt="assembly" style="cursor:pointer;"></td>
	                        </tr>
	                    </tbody>
	                </table>
	                <table>
	                    <colgroup>
	                        <col width="*" id="col_currency_item_mob">
	                        <col width="*" id="col_price_item_mob">
	                        <col width="*" id="col_stock_item_mob">
	                    </colgroup>
	                    <tbody>
	                        <tr>
	                            <%-- Currency --%>
	                            <th id="th_m_currency" data-lang="FR000045"><%= LangMng.LANG_D("FR000045", session_lang)%></th>
	                            <%-- Price --%>
	                            <th id="th_m_price" data-lang="FR000046"><%= LangMng.LANG_D("FR000046", session_lang)%></th>
	                            <%-- Stock Availability --%>
	                            <th id="th_m_stock" data-lang="FR000047"><%= LangMng.LANG_D("FR000047", session_lang)%></th>
	                        </tr>
	                        <tr>
	                            <td id="it_m_currency"></td>
	                            <td id="it_m_price" class="right"></td>
	                            <td id="it_m_stock"></td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
	        </div>
	        <%-- <%} %> --%>
	    </div>
        
        <div class="box_two">
            <div class="box">
                <div class="box_top"  id="box_top_3d" >
                    <%-- 3D Representation --%>
                    <span data-lang="FR000049" onclick="$('#box_top_3d').click();"><%= LangMng.LANG_D("FR000049", session_lang)%></span>
                    <div class="box_tab">
                        <ul>
                            <%-- Basic --%>
                            <li><a id="btn_basic"  onClick="javascript:go3dBasic()" class="on" style="cursor:pointer;" data-lang="FR000051"><%= LangMng.LANG_D("FR000051", session_lang)%></a></li>
                            <%-- Detailed --%>
                            <li><a id="btn_detail"  onClick="javascript:go3dDetailed()" style="cursor:pointer;" data-lang="FR000052"><%= LangMng.LANG_D("FR000052", session_lang)%></a></li>
                        </ul>
                    </div>
                    <%-- Download --%>
                    <a id="it_stp_basic_file_url"  class="download" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
                    <%-- Download --%>
                    <a id="it_stp_detail_file_url"  class="download" style="cursor:pointer; display:none;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
                    <a onclick="" class="btn_down"><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
                </div>
                <!-- 3D Basic -->
                <div class="box_cont">
	                <div class="box_cont_tmp" id="div_stp_basic">
	                    <div class="img" style="height: 501px; border: 0px;">
		                    <div class="img" id="img_3d_basic" >
		                    <!-- 모바일일 경우 확대창 보이지 않도록 -->
			                    <%if("P".equals(devType)){ %>
			                        <a onclick="" class="expand"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
			                    <%}else{ %>
			                        <a href="javascript:void(0);" class="expand" style="background:none;"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
			                    <%} %>
		                    </div>
		                    <!-- a tag 시작 -->
	                        	<a 
				                     <%if("P".equals(devType)){ %>
		                        		onclick="open3dPop()" 
	                        			style="padding:0px; height:499px;"
				                    <%}else{ %>
				                    	href="javascript:void(0);"
				                    	style="background:none; padding:0px; height:499px;"
				                    <%} %>
		                        		class="expand_3d" id="btn3dBasicPop">
	                        	<!-- <img onerror="onErrorImage(this)" alt="" style="max-width: 64%"> -->         	
		                        <iframe id="3d_rendering_basic" src="" style="width:100%; height:464px;" onerror="onErrorImage(this)">				
								</iframe>
	                        	</a>
	                    </div>
	                </div>
	                <!-- 3D Detail -->
	                <div class="box_cont_tmp" id="div_stp_detail" style="display:none;">
	                    <div class="img" style="height: 501px;  border: 0px;">
		                    <div class="img" id="img_3d_detail" >
		                    <% if("P".equals(devType)){ %>
		                        <a onclick="" class="expand" id="btn3dDetailPop_pc"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
		                    <% }else{ %>
		                        <a href="javascript:void(0);"  style="background:none;" class="expand" id="btn3dDetailPop_mob"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
		                    <% } %>
		                    </div>
		                    
	                        <% if("P".equals(devType)){ %>
	                        	<a onclick="open3dPop()" style="padding:0px; height:499px;" class="expand_3d" id="btn3dDetailPop" >
	                         <% }else{ %>	
		                        <a href="javascript:void(0);" style="padding:0px; height:499px;background:none;" class="expand_3d" id="btn3dDetailPop">
                             <% } %>
	                        	<!-- <img onerror="onErrorImage(this)" alt="" style="max-width: 64%"> -->                         	
		                        <iframe id="3d_rendering_detail" src="" style="width:100%; height:464px;">				
								</iframe>
	                        </a>
	                    </div>
	                </div>
                </div>
                 
            </div>
            <div class="box">
                <div class="box_top" id="box_top_2d">
                    <%-- 2D Representation --%>
                    <span data-lang="FR000050" onclick="$('#box_top_2d').click();"><%= LangMng.LANG_D("FR000050", session_lang)%></span>
						<%-- Download --%>
                    	<a id="it_dxf_file_url"  class="download" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
                    	<a class="btn_down"><img src="<%=PATH_IMG%>/btn_down.png" alt="" ></a>
                </div>
                <div class="box_cont" id="div_2d_box">
                    <div class="img" style="padding:0px; height:502px;">
                        <a 
                        	<% if("P".equals(devType)){ %>
                        		onclick="open2dPop()" 
                       			style="height:499px;"
                       		<% }else{ %>
                       			onclick=""
                       			href="javascript:void(0);"
                       			style="height:499px;background:none;pointer-events: none;"
                       		<% } %>
                        	id="btn2dPop" 
                        	class="expand_3d"><img id="img_it_dxf" alt="" onerror="onErrorImage(this)" src="" style="padding:0px; height:100%; max-width: 70%;"></a>
                    </div>
                </div>  
            </div>
        </div>
        
        <div class="box" id="bar_rel_inserts">
            <div class="box_top"  id="bar_relatedInserts" onclick="addRemoveOn('bar_relatedInserts')">
                <%-- Related Inserts --%>
                <span data-lang="FR000054" class="insert"><%= LangMng.LANG_D("FR000054", session_lang)%></span>
                <a onclick="addRemoveOn('bar_relatedInserts')" class="btn_down"><img id="relatedInserts" src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display: none;" id="area_inserts">
            	<div class="view_cont pc">
                	<div class="listTable">
                    	<table id="table_relInserts">               
                        
                    	</table>
                	</div>
                </div>
                
            	<div class="box_cont mobile" id="area_inserts_mob">
                </div>
            </div>  
        </div>
        <div class="box" id="bar_rel_holders">
            <div class="box_top" id="bar_relatedHolders" onclick="addRemoveOn('bar_relatedHolders')">
                <%-- Related Holders --%>
                <span data-lang="FR000055" class="insert"><%= LangMng.LANG_D("FR000055", session_lang)%></span>
                <a onclick="addRemoveOn('bar_relatedHolders')" class="btn_down"><img id="relatedHolders" src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display: none;" id="area_holders">
            	<div class="view_cont pc">
	                <div class="listTable">
	                    <table id="table_relHolders">            
	                                            
	                    </table>
	                </div>
                </div>
                <div class="box_cont mobile" id="area_holders_mob">
                </div>
            </div>  
        </div>
        
        <div class="box" id="bar_cut_speed">
            <div class="box_top" id="bar_cuttingSpeeds" onclick="addRemoveOn('bar_cuttingSpeeds')">
                <%-- Recommended Cutting Speeds --%>
                <span data-lang="FR000056" class="insert"><%= LangMng.LANG_D("FR000056", session_lang)%></span>
                <a onclick="addRemoveOn('bar_cuttingSpeeds')" class="btn_down"><img id="cuttingSpeeds"  src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display: none;" id="area_cutting_speed">
                <div class="listTable w500" style="margin-left: 0px; overflow-x: hidden;">
                    <table id="table_cuttingSpeeds">
                        
                    </table>
                </div>
            </div> 
        </div>
        
        <div class="box" id="bar_spare_parts">
            <div class="box_top" id="bar_spareParts" onclick="addRemoveOn('bar_spareParts')">
                <%-- Spare Parts --%>
                <span data-lang="FR000057" class="insert"><%= LangMng.LANG_D("FR000057", session_lang)%></span>
                <a onclick="addRemoveOn('bar_spareParts')" class="btn_down"><img id="spareParts"  src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
            </div>
            <div class="box_cont" style="display: none;" id="area_spare_parts">
            	<div class="pc">
	                <div class="listTable" style="margin-left: 0px; overflow-x: hidden;">
	                    <table id="table_spareParts">
	                        
	                    </table>
	                </div>
            	</div>
            	<div class="box_cont mobile" id="area_spare_parts_mob">
                </div>
            </div> 
        </div>
    </div>
</div>	

<!-- 팝업 모달 -->
<div class="open_pop"  style="z-index:9999">
	<div class="in">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="height: 600px;">
            <div class="pop_img"><img src="<%=PATH_IMG%>/img_gallery01.png" alt=""  style="max-height: 525px;"></div>
        </div>
        <!-- More Info 팝업 -->
        <div class="pop_info" style="width: 1000px; height: 650px;">
            <div class="info_tit" id="pop_more_info_nm"></div>
            <div class="info_cont" style="width: 100%;">
                <p id="pop_more_info" style="vertical-align: top !important; text-align: left; word-break: break-word;"></p>
            </div>
        </div>
        
    </div>
</div>

<!-- 장바구니팝업 -->
<div class="open_cart_pop cart_pop01" style="z-index:9999">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
        	<%-- 장바구니 그룹 --%>
            <div class="left"><span data-lang="FR000125"><%= LangMng.LANG_D("FR000125", session_lang)%></span></div>
            <div class="right">
                <div class="select">
                
    			<input type="hidden" name="select_item_matnr" id="select_item_matnr" />
                <select name="select_cart_list" id="select_cart_list" init="<%=ConstKey.KEY_YES %>">
                </select>
                </div>
            </div>
        </div>
        <div class="btn_assembly">
            <ul>
                <%--ADD --%>
                <li><a onclick="newCartPop()" class="add" data-lang="FR000066"><%= LangMng.LANG_D("FR000066", session_lang)%></a></li>
                <%-- Save --%>
                <li><a onclick="saveCartItem()" class="save open_save" data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
            </ul>
        </div>
    </div>
</div>
   
<div class="open_cart_pop cart_pop02" style="z-index:9999">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
            <%-- 장바구니 그룹명 --%>
            <div class="left"><span data-lang="FR000126"><%= LangMng.LANG_D("FR000126", session_lang)%></span></div>
            <div class="right">
                <div class="input">
                    <input type="text" id="new_cart_name" name="new_cart_name">
                </div>
            </div>
        </div>
        <div class="btn_assembly">
            <ul>
                <%-- Save --%>
                <li><a onclick="saveNewCartItem()" class="save" data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
            </ul>
        </div>
    </div>
</div>
<!-- //장바구니팝업 -->


<!-- Asssembly 팝업 -->
<div class="open_cart_pop assembly_pop01" style="z-index:9999">
    <div class="in" >
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
            <%-- Asssembly 그룹 --%>
            <div class="left"><span data-lang="FR000131"><%= LangMng.LANG_D("FR000131", session_lang)%></span></div>
            <div class="right">
                <div class="select">
                
                <select name="select_assembly_list" id="select_assembly_list" init="<%=ConstKey.KEY_YES %>">
                </select>
                </div>
            </div>
        </div>
        <div class="btn_assembly">
            <ul>
                <%--ADD --%>
                <li><a onclick="newAssemblyPop()" class="add" data-lang="FR000066"><%= LangMng.LANG_D("FR000066", session_lang)%></a></li>
                <%-- Save --%>
                <li><a onclick="saveAssemblyItem()" class="save open_save" data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
            </ul>
        </div>
    </div>
</div>
   
<div class="open_cart_pop assembly_pop02" style="z-index:9999">
    <div class="in" >
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
            <%-- Asssembly 그룹명 --%>
            <div class="left"><span data-lang="FR000132"><%= LangMng.LANG_D("FR000132", session_lang)%></span></div>
            <div class="right">
                <div class="input">
                    <input type="text" id="new_assembly_name" name="new_assembly_name">
                </div>
            </div>
        </div>
        <div class="btn_assembly">
            <ul>
                <%-- Save --%>
                <li><a onclick="saveNewAssemblyItem()" class="save" data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
            </ul>
        </div>
    </div>
</div>
<!-- //Asssembly 팝업 -->


	

<script type="text/javascript">



var igImgUrl;
var popIgImgUrl;

var itemImgUrl;
var popItemImgUrl;


var isoImgUrl; //ISO 도면 썸네일 이미지 URL
var popIsoImgUrl; // ISO 도면 팝업 원본 이미지 URL

var nonIsoImgUrl;  //NON-ISO 도면 썸네일 이미지 URL
var popNonIsoImgUrl; // NON-ISO 도면 팝업 원본 이미지 URL


var moreInfoNmTxt; // More Info IG명
var moreInfoTxt; // More Info 내용
var cartImgUrl = "<%=PATH_IMG%>" + '/ico_cart.png';

var default_prop_symbol_arry = []; // default filter prop symbol list : Reset시 사용

/* Item List */
var prop_symbol_arry = []; // filter prop symbol list
var prop_type = "I"; // I : ISO, N:Non-ISO
var itemList;
//var insertItemCnt = 0; // Item List의 Insert Item의 갯수. 0이면 Item List 테이블에 'Grade'컬럼 표시하지 않는다.

/* Item */
var item_prop_symbol_arry = []; // item filter prop symbol list
var item_prop_type = "I"; // I : ISO, N:Non-ISO
var itemInfo;

/* ISO 계산 */
let isoCalMap;

/*  클릭한 아이템 부분(테이블이 보이는)아이디 - 아이템리스트, 인서트,홀더, 스페어파트 동일*/
var itemClickId;

// 데이터 초기화 (아이템 조회 후, 다른아이템 재조회시 이전아이템 데이터보이는것 방지)
function initItemData(){
	
	igImgUrl = "";
	popIgImgUrl = "";
	itemImgUrl = "";
	popItemImgUrl = "";
	isoImgUrl = "";
	popIsoImgUrl = "";
	nonIsoImgUrl = "";
	popNonIsoImgUrl = "";

	moreInfoNmTxt = "";
	moreInfoTxt = "";
	
	itGtcFileUrl = "";
	itP21FileUrl = "";
	itDxfFileUrl = "";
	itStpBasicFileUrl = "";
	itStpDetailFileUrl = "";
		
	$("#ig_brand_logo").attr("src", "");
	$('#ig_brand_logo').css('display', 'none');
	$("#it_brand_logo").attr("src", "");
	$('#it_brand_logo').css('display', 'none');
		
	$("#ig_image").attr("src", "");
	$("#it_image").attr("src", "");
	
	$("#ig_dd_image").attr("src", "");
	$("#it_ig_dd_image").attr("src", "");
	
	$("#img_it_dxf").attr("src", "");
	$("#img_pop_dxf").attr("src", "");	

	$('#img_3d_basic').css('display', 'block');
	$('#img_3d_detail').css('display', 'block');
	
}

function init_Item(){
	$("#area_itemList_sub").slideUp();
	$("#area_item_sub").slideUp();
	
	//화면이벤트를 모두 정의함
	bindClickEvent();	
}
//사이즈단위변경
var cur_size_unit = "<%=ConfigMng.getSizeUnit(request)%>";
var cur_currency = "<%=ConfigMng.getCurrency(request)%>";

function doItemReload(mode) {
    // 아이템리스트는 길이단위변경할때만 다시불러옴
    if("SIZE" == mode) {
	    // 아이템 리스트 영역이 열려있으면 다시 불러옴
		if ($('#area_itemList').css('display') == 'block') {
			console.log("Refreshing Item Info...");
			getItemGroupInfo();
		} else {
			console.log("There is no Item Info section on this page...");
		}
    }
	
    // 아이템 영역은 통화&길이단위 상관없이 다시불러옴
    // 아이템 영역이 열려있으면 다시 불러옴
    if($('#area_item').css('display') == 'block'){
    	getItemIfno();
    	getRelatedInsertsList();
    	getRelatedHolders();
		getCuttingSpeeds();
    }
}

function bindClickEvent() {
	
    // 회색바 클릭 시 박스내리기
    $(".box_top").click(function(e){
    	if($(e.target).is(".box_top")){
    		$(this).next(".box_cont").slideToggle();
        	return false;
  		}
    });

    // 아이템 띠의 텍스트 클릭했을때 상위 태그 클릭
    $("#icodian_it").click(function(e){
    	e.target.parentNode.click();
		return false;
    });

    // 아이템 그룹 띠의 텍스트 클릭했을때 상위 태그 클릭
    $("#icodian_ig").click(function(e){
    	e.target.parentNode.click();
        return false;
    });

    // 아이템 그룹 셀렉트컬럼 띠의 텍스트 클릭했을때 상위 태그 클릭
    $("#igSelectCol").click(function(e){
        e.target.parentNode.click();
        return false;
    });

    // 아이템 셀렉트컬럼 띠의 텍스트 클릭했을때 상위 태그 클릭    
    $("#itSelectCol").click(function(e){
        e.target.parentNode.click();
        return false;
    });
    
    // 회색바 클릭 시 박스내리기 -- span tag 누를때 이벤트가 적용되지 않아 추가
    // 2021-10-19 정세연 : 이렇게 하면 모든 span 태그가 다먹힘!!!!!
    // 어떤 문제가 있어 이렇게 했는지 확인 후 해당 태그만 이벤트 되도록 수정할것.
    //릴레이티드인서트와 홀더 스페어 파트 컷팅스피드의 회색바를 누를때 박스가 내려오지 않아 class insert를 추가하여 해당 부분만 이벤트가 먹히도록 수정
   $("span.insert").click(function(e){
   		e.target.parentNode.click();
   });
	
	//Item list toggle 버튼
	$("#toggle_itemList").on('click', function(ev){
		$(this).parents(".box_cont").parents(".box_top").click();
/* 	    // 아이템리스트 영역이 닫혀있으면
	    if($("#area_itemList_sub").is(":visible") == true){
		    console.log("area_itemList_sub false");
	        // 아이템리스트는 열고
	    	$("#area_itemList_sub").slideDown();
	    	// 아이템은 닫고
	    	$("#area_item_sub").slideUp();
	    // 아이템리스트 영역이 열려있으면
		}else{
            // 아이템리스트만 닫아
            $("#area_itemList_sub").slideUp();
		} */
	});
	
	//Item toggle 버튼
 	$("#toggle_item").on('click', function(ev){
 		$(this).parents(".box_cont").parents(".box_top").click();
/*         // 아이템영역이 닫혀있으면
        if($("#area_item_sub").is(":visible") != true){
            // 아이템은 열고
            $("#area_item_sub").slideDown();
            // 아이템리스트는 닫고
            $("#area_itemList_sub").slideUp();
        // 아이템 영역이 열려있으면
        }else{
            // 아이템만 닫아
            $("#area_item_sub").slideUp();
        } */
	});	
	
	//Item Group more info 버튼 클릭
	$("#btn_ig_more_info").on('click', function(ev){
		getMoreInfo();
	});
	
	//Item Group Filter Reset 버튼 클릭
	$("#btn_ig_filter_reset").on('click', function(ev){		
		// 필터정보 초기화
		prop_symbol_arry = [];
		prop_symbol_arry = JSON.parse(JSON.stringify(default_prop_symbol_arry));
		if($('.mobileDetailItem').is(':visible') != true){
			itemClickId= ''
		}
		if(prop_type == 'I'){
			setFilterIsoName();
		}else{
			setFilterNisoName();
		}
	});
	 
	//Item Group - ISO1399 버튼 클릭
	$('#btn_ig_iso').on('click', function(ev){
		if(isoImgUrl !== ""){
			$("#ig_dd_image").attr("src", isoImgUrl);				
			$("#btn_ig_iso").addClass("on");				
			$("#btn_ig_non_iso").removeClass("on");
		}
		prop_type = 'I';
		
		setFilterIsoName();
	});
	
	//Item Group - Non-ISO 버튼 클릭
	$('#btn_ig_non_iso').on('click', function(ev){
		if(nonIsoImgUrl !== ""){
			$("#ig_dd_image").attr("src", nonIsoImgUrl);
			$("#btn_ig_iso").removeClass("on");
			$("#btn_ig_non_iso").addClass("on");
		}
		prop_type = 'N';
		
		setFilterNisoName();
	});
	
	//Item Group more info 버튼 클릭
	$("#btn_it_more_info").on('click', function(ev){
		getMoreInfo();
	});
	
	//Item Filter Reset 버튼 클릭
	$("#btn_it_filter_reset").on('click', function(ev){
		
		// 필터정보 초기화
		item_prop_symbol_arry = [];
		item_prop_symbol_arry = JSON.parse(JSON.stringify(default_prop_symbol_arry));
		
		if(item_prop_type == 'I'){
			setItemFilterIsoName();	
		}else{
			setItemFilterNisoName();
		}
	});
	
	//Item - ISO1399 버튼 클릭
	$('#btn_it_ig_iso').on('click', function(ev){
		if(isoImgUrl !== ""){
			$("#it_ig_dd_image").attr("src", isoImgUrl);				
			$("#btn_it_ig_iso").addClass("on");				
			$("#btn_it_ig_non_iso").removeClass("on");
		}	
		item_prop_type = "I";

		setItemFilterIsoName();
	});
	
	//Item - Non-ISO 버튼 클릭
	$('#btn_it_ig_non_iso').on('click', function(ev){
		if(nonIsoImgUrl !== ""){
			$("#it_ig_dd_image").attr("src", nonIsoImgUrl);
			$("#btn_it_ig_iso").removeClass("on");
			$("#btn_it_ig_non_iso").addClass("on");
		}
		item_prop_type = "N";
		
		setItemFilterNisoName();
	});
	
	
	//Related Inserts
	$('#relatedInserts').on('click', function(ev){
		getRelatedInsertsList();
	});
	$('#bar_relatedInserts').on('click', function(ev){
		getRelatedInsertsList();
	});
	
	//Related Holders
	$('#relatedHolders').on('click', function(ev){
		getRelatedHolders();
	});
	$('#bar_relatedHolders').on('click', function(ev){
		getRelatedHolders();
	});
	
	//Recommended Cutting Speeds
	$('#cuttingSpeeds').on('click', function(ev){
		getCuttingSpeeds();
	});
	$('#bar_cuttingSpeeds').on('click', function(ev){
		getCuttingSpeeds();
	});
	
	//Spare Parts
	$('#spareParts').on('click', function(ev){
		getSpareParts();
	});
	$('#bar_spareParts').on('click', function(ev){
		getSpareParts();
	});
	
}

function itListShowHide(){
	// 아이템 그룹영역이 닫혀있으면
	if($('#area_itemList_sub').is(":visible") != true){
	    $("#area_itemList_sub").slideToggle();
		$('#ig_box_top').removeClass('on')
		$("#igListBar").removeClass("on");
	}else{
		$('#ig_box_top').addClass('on')
		$("#igListBar").removeClass("on");
	}
    $("#area_item_sub").slideToggle();
}

//////////////////////////////////////////////////////////////// [ItemGroup List] //////////////////////////////////////////////////////////////// 
var cur_ig_cd = "";
var cur_ig_nm = "";
var inchYn ="";
//리스트 선택
function goItemGroup(obj){
	// 플로팅버튼 관련 처리
/*
	$('#btn_mv_ig').data("target", $(obj).data("igcd")).text($(obj).text());
	$('#btn_mv_it').data("target", "").text("IT");	//ITEM 은 초기화
*/
	$("#area_itemList").show();
	cur_ig_cd = $(obj).data("igcd");
	$('#ig_cd').val(cur_ig_cd);
	cur_ig_nm = $(obj).data("ignm");
	//로그 등록
	doLog("EC0015", cur_ig_nm, "");
    // 아이템 리스트가 닫혀있으면
    if($('#area_itemList_sub').css('display') == 'none'){
        $('#area_itemList_sub').slideDown();
    }
    //아이템이 열려있으면
    if($('#area_item_sub').css('display') == 'block'){
        $('#area_item_sub').slideUp();
    }
    
    $("#area_itemList").trigger("create");
	getItemGroupInfo();
}

//아이템 선택
function goItem(matnr, designation){
	var offset_obj = new Object();
	offset_obj.type = "ITEM";
	offset_obj.cd = matnr + ":" + designation;
	click_offset.push(offset_obj);
	//로그저장 아이템리스트 화면( Item List) - K.P code 클릭시
	doLog('EC0016', matnr, '')
	// 플로팅 버튼 관련 처리
/*
	$('#btn_mv_it').data("target", matnr + ":" + designation).text(matnr + ":" + designation)
*/
	$("#area_item").show();
	$('#item_ig_box_top').addClass('on');
	$('#it_matnr').val(matnr);
	$('#it_designation').val(designation); // 3D Representation 시 사용

    if($('#area_itemList_sub').css('display') == 'block'){
        $('#area_itemList_sub').slideUp();
     }else{
    	 $("#ig_box_top").removeClass('on');
     }
         
//  if($('#area_item').css('display') == 'none'){
//       $('#area_item').slideToggle();
//   }
         
     if($('#area_item_sub').css('display') == 'none'){
         $('#area_item_sub').slideToggle();
    	 $("#ig_box_top").removeClass('on');
     }
     
     //Related Inserts 펼쳐져있으면 닫기
     if($('#area_inserts').css('display') !== 'none'){
    	 $("#bar_relatedInserts").removeClass('on');
         $('#area_inserts').slideToggle();
     }
     
     //Related Inserts 펼쳐져있으면 닫기
     if($('#area_holders').css('display') !== 'none'){
    	 $("#bar_relatedHolders").removeClass('on');
         $('#area_holders').slideToggle();
     }
     
     //Recommended Cutting Speeds 펼쳐져있으면 닫기
     if($('#area_cutting_speed').css('display') !== 'none'){
         $('#area_cutting_speed').slideToggle();
    	 $("#bar_cuttingSpeeds").removeClass('on');
     }
     
     //Spare Parts 펼쳐져있으면 닫기
     if($('#area_spare_parts').css('display') !== 'none'){
         $('#area_spare_parts').slideToggle();
    	 $("#bar_spareParts").removeClass('on');
     }
     
	getItemIfno();
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

// 아이템그룹 정보(아이템 리스트) 취득
function getItemGroupInfo(){
	var param = {
			actionID:"<%=AppConstKey.ACTION_ITEM_GROUP_INFO%>"
			,search_ig_cd: cur_ig_cd
			,search_assem_no: $("#search_assem_no").val()
			,matnr_list: $("#matnr_list").val()
			,search_unit_cd: $("#btnSizeUnit").val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
        	//console.log("[ITEM GROUP] = " + JSON.stringify(e.itemGroupInfo))        	
        	//console.log("[ITEM GROUP IMG] = " + JSON.stringify(e.igImgInfo));
        	//console.log("[SUB APP IMAGE] = " + JSON.stringify(e.subAppImgList));
        	//console.log("[FILTER PROP] = " + JSON.stringify(e.propList));
//         	console.log("[ITEM LIST] = " + JSON.stringify(e.itemList));
        	//console.log("[ISO CAL LIST] = " + JSON.stringify(e.isoCalList));
//         	console.log(e.inchYN)
        	
        	var resultItemGroupInfo = e.itemGroupInfo;
        	var resultIgImgInfo = e.igImgInfo;        	
        	var resultSubAppImgList = e.subAppImgList;
        	var resultPropList = e.propList;        	
        	itemList = e.itemList; 
        	inchYn = e.inchYN				//인치 사용여부
//         	console.log(inchYn)
        	//insertItemCnt = e.insertCnt;
        	
        	initItemData();
        	
        	//ISO metric/inch  계산 단위 및 값 취득(isoCalMap 없는 경우에만)
        	if(isoCalMap == undefined){
            	chgMapIsoCal(e.isoCalList);
        	}
        	//
        	$('#ig_box_top').addClass('on');
//         	$('#3d_box_top').addClass('on');
//         	$('#2d_box_top').addClass('on');
        	//아이템 그룹 정보
        	if(resultItemGroupInfo !== null){
        		$("#icodian_ig").text(resultItemGroupInfo.ig_nm);
        		$("#ig_nm").text(resultItemGroupInfo.ig_nm);

    			$("#ig_tm_nm").attr("data-lang", resultItemGroupInfo.tm_cd);
    			$("#ig_tm_nm").text(resultItemGroupInfo.tm_nm);

        		$("#ig_dscr").attr("data-lang", resultItemGroupInfo.ig_cd);
    			$("#ig_dscr").text(resultItemGroupInfo.ig_dscr);
    			
    			$("#it_ig_nm").text(resultItemGroupInfo.ig_nm);
    			
    			$("#it_tm_nm").attr("data-lang", resultItemGroupInfo.tm_cd);
    			$("#it_tm_nm").text(resultItemGroupInfo.tm_nm);
    			$("#it_ig_dscr").text(resultItemGroupInfo.ig_dscr);
    			
    			//브랜드로고 이미지
    			if(resultItemGroupInfo.brand_logo !== ""){
    				var brandLogoUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultItemGroupInfo.brand_logo;
    				$("#ig_brand_logo").attr("src", brandLogoUrl);
    				$("#ig_brand_logo").show();
    				
    				$("#it_brand_logo").attr("src", brandLogoUrl);
    				$("#it_brand_logo").show();
    			}    			
        	}else{
        		$("#icodian_ig").text(cur_ig_nm);
    			$("#ig_nm").text(cur_ig_nm);    			
    			$("#it_ig_nm").text(cur_ig_nm);
        	}
        	        	
        	if(resultIgImgInfo !== null){
        		//아이템그룹 대표 이미지
    			if(resultIgImgInfo.item_image_thumb !== ""){
    				igImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.item_image_thumb;
    				$("#ig_image").attr("src", igImgUrl);
    			}        		
        		
    			//아이템그룹 대표 이미지(팝업)
    			if(resultIgImgInfo.item_image !== ""){
    				popIgImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.item_image;
    				//$("#pop_ig_image").attr("src", popItemImgUrl);
    			}else{
    				// 원본이미지가 없으면 썸네일 이미지로 대체
    				if(resultIgImgInfo.item_image_thumb !== ""){
    					popIgImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.item_image_thumb;	
    				}
    			}
    			
    			//ISO 도면 이미지 URL
    			if(resultIgImgInfo.iso_image_thumb !== ""){
    				isoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.iso_image_thumb;
    			}
    			
    			//ISO 도면 이미지 URL(팝업)
    			if(resultIgImgInfo.iso_image !== ""){
    				popIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.iso_image;
    			}else{
    				// 원본이미지가 없으면 썸네일 이미지로 대체
    				if(resultIgImgInfo.iso_image_thumb !== ""){
    					popIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.iso_image_thumb;
        			}
    			}
    			
    			//NON-ISO 도면 이미지 URL
    			if(resultIgImgInfo.non_image_thumb !== ""){
    				nonIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.non_image_thumb;
    			}
    			
    			//NON-ISO 도면 이미지 URL(팝업)
    			if(resultIgImgInfo.non_image !== ""){
    				popNonIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.non_image;
    			}else{
    				// 원본이미지가 없으면 썸네일 이미지로 대체
    				if(resultIgImgInfo.non_image_thumb !== ""){
    					popNonIsoImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultIgImgInfo.non_image_thumb;
        			}	
    			}
    			
    			//ISO 1399 도면 이미지 세팅
    			if(isoImgUrl !== ""){
    				$("#ig_dd_image").attr("src", isoImgUrl);				
    				$("#it_ig_dd_image").attr("src", isoImgUrl);
    			}  			
    			
        	}
        	
        	if(resultSubAppImgList.length > 0){
        		$("#ig_sub_img_div *").remove();
				$("#it_ig_sub_img_div *").remove();
				
				//Sub App 이미지 
				for(var i=0; i<resultSubAppImgList.length; i++){
					var subImgHtml = "";
					var subImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + resultSubAppImgList[i].sub_img;
					subImgHtml = '<div class="swiper-slide" style="width: 56px; margin-right: 10px;"><a><img src="' + subImgUrl + '"  onerror="onErrorImage(this)"></a></div>';
					$("#ig_sub_img_div").append(subImgHtml);
					$("#it_ig_sub_img_div").append(subImgHtml);
					
					//$('#list').html(data).trigger("create");
				}
        	}
			
			// 필터 초기화
			default_prop_symbol_arry = [];
			prop_symbol_arry = [];
			item_prop_symbol_arry = [];
			
			//Property
			for(var i=0; i<resultPropList.length; i++){
				//디바이스 타입이 PC인경우
				if(devType == "P"){
					default_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch});
					prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch });
					item_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_pc_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch });
					
				//디바이스 타입이 모바일인 경우	
				}else{
					default_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_mb_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch });
					prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_mb_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch });
					item_prop_symbol_arry.push({"iso" : resultPropList[i].prop_iso, "niso" : resultPropList[i].prop_n_iso, "chkYn" : resultPropList[i].disp_mb_yn, "unitMetr" : resultPropList[i].unit_metr, "unitInch" : resultPropList[i].unit_inch });
				}		
			}
			setFilterIsoName();

        },
        complete:function(){
            $('#loadbar').fadeOut();
        },
        error: function (request, error) {
        	//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}


//아이템리스트 테이블 그리기 (PC)
function createItemListTable(){
	$("#table_itemList *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width=""><col width=""><col width=""><col width=""><col width=""><col width=""><col width="">';		
		colGroupHtml += '</colgroup>';
	
	<%-- Cart --%>	<%-- Order Number --%>	<%-- Designation --%> 	<%-- Grade --%>			
	colGroupHtml += '<thead><tr><th class="tdCart" data-lang="FR000040">' + LANG_SET["FR000040"] + '</th><th class="tdOrd" data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th class="tdDegi" data-lang="FR000039">' + LANG_SET["FR000039"] + '</th></tr>'
	//어드민에서 설정한 인치사용여부가 on일 때
	if(inchYn == 'Y'){
		for(var k=0; k<itemList.length; k++){
//+++++++++++++++++++++++++++++++++++++++++++++++++++++아이템 리스트 PC S
			var rowHtml = '<tr>';
			//cart
			rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + itemList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0017"><img src="' +  cartImgUrl + '" alt=""></a></td>'
			//order number
			rowHtml += '<td onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )"  style="cursor:pointer; text-decoration:underline;color: #0094da;">' + (itemList[k].NEW_YN === 'Y' ? '<div class="new_tag" data-lang="FR000030">' + LANG_SET["FR000030"] + '</div>' : '') + itemList[k].MATNR + '</td>';  // Default :  Order Number 
			// 자재내역 
			rowHtml += '<td class="ellipsis"onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )"  style="cursor:pointer; text-decoration:underline;color: #0094da;" title ="' + itemList[k].DESIGNATION + '">'  + itemList[k].DESIGNATION; +'</td></tr>'
			colGroupHtml += rowHtml; 
//+++++++++++++++++++++++++++++++++++++++++++++++++++++아이템 리스트 PC  E
		}// end of inch y for
	}else {
		//어드민에서 설정한 인치사용여부가 off일때
		for(var k=0; k<itemList.length; k++){
			//off인 경우는 인치 only 아이템은 제외하고 테이블을 그려준다.
			if(itemList[k].UNIT_CD !='CC0002'){
				var rowHtml = '<tr>';
				rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + itemList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0017"><img src="' +  cartImgUrl + '" alt=""></a></td>'
				rowHtml += '<td onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )"  style="cursor:pointer; text-decoration:underline;color: #0094da;">' + (itemList[k].NEW_YN === 'Y' ? '<div class="new_tag" data-lang="FR000030">' + LANG_SET["FR000030"] + '</div>' : '') + itemList[k].MATNR + '</td>';  // Default :  Order Number 
				rowHtml += '<td class="ellipsis"onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )"  style="cursor:pointer; text-decoration:underline;color: #0094da;" title ="' + itemList[k].DESIGNATION + '">'  + itemList[k].DESIGNATION; +'</td></tr>'
				colGroupHtml += rowHtml; 
			}
		}
	}
	colGroupHtml += '</thead><tbody><tr>' 
//++++++++++++++item List property 컬럼 셋팅 S
	// <tbody><th> START
	for(var j=0; j<prop_symbol_arry.length; j++){
		var th_key = '';
		
		if(prop_type == 'I'){
			if(prop_symbol_arry[j].iso){
				if(prop_symbol_arry[j].chkYn == 'Y'){
					colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].iso + '</th>';	
				}
			}				
		}else{
			if(prop_symbol_arry[j].niso){
				if(prop_symbol_arry[j].chkYn == 'Y'){
					colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].niso + '</th>';
				}
			}
		}		
	}	
//++++++++++++++item List property 컬럼 셋팅 E
	// Grade 컬럼 Y
	//if(insertItemCnt > 0){
		<%-- Grade --%>
	//	colGroupHtml += '<th data-lang="FR000041">' + LANG_SET["FR000041"] + '</th></tr>'; // Default :  Grade
	//}else{
	// Grade 컬럼 N
		colGroupHtml += '</tr>';
	//}	
	// <tbody><th> END
// 어드민에서 인치사용여부 on일 떄
	if(inchYn == 'Y'){
		for(var k=0; k<itemList.length; k++){ 	
			colGroupHtml += '<tr>';  // Default : Cart
			//colGroupHtml += '<tr><td style="cursor:pointer;"><a href="#" class="btn_cart"><img src="' +  cartImgUrl + '" alt=""></a></td>';  // Default : Cart
			for(var j=0; j<prop_symbol_arry.length; j++){
				var th_key = '';
				var th_val = '';		
				
				if(prop_type == 'I'){
					if(prop_symbol_arry[j].iso){
						if(prop_symbol_arry[j].chkYn == 'Y'){
							th_key = prop_symbol_arry[j].iso;
							th_val = itemList[k][th_key];
							
							if(typeof th_val == "undefined" || th_val == null){
								th_val = '';
							}
		                    th_val = String(th_val).replace(/}/, '');
		                    th_val = String(th_val).replace(/{/, '');
		                    
							// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
							if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
								var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
								
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
							// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
							}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
								var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
								
								if(typeof calVal !== "undefined"){
									var unit = calVal.split('$$')[0]; //연산자							
									if(unit != ""){
				                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
				                    }
								}
		                    }
							colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';	
						}
					}				
				}else{
					if(prop_symbol_arry[j].niso){
						if(prop_symbol_arry[j].chkYn == 'Y'){
							th_key = prop_symbol_arry[j].iso;
							th_val = itemList[k][th_key];						

							if(typeof th_val == "undefined" || th_val == null){
								th_val = '';
							}
		                    th_val = String(th_val).replace(/}/, '');
		                    th_val = String(th_val).replace(/{/, '');
							
		                    // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
							if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
								
								var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
								
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
	                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1
	                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
	    						var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
	    						
	    						if(typeof calVal !== "undefined"){
	    							var unit = calVal.split('$$')[0]; //연산자							
	    							if(unit != ""){
	    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
	    		                    }
	    						}
	                        }
							colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';	
						}
					}
				}
			}
				colGroupHtml += '</tr>';
		}//end of for -- property 컬럼별 값 셋팅
	}else{
// 어드민에서 인치사용여부 off일 떄
		for(var k=0; k<itemList.length; k++){ 	
			if(itemList[k].UNIT_CD != 'CC0002'){
				colGroupHtml += '<tr>'  
				for(var j=0; j<prop_symbol_arry.length; j++){
					var th_key = '';
					var th_val = '';		
					
					if(prop_type == 'I'){
						if(prop_symbol_arry[j].iso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								th_key = prop_symbol_arry[j].iso;
								th_val = itemList[k][th_key];
								
								if(typeof th_val == "undefined" || th_val == null){
									th_val = '';
								}
			                    th_val = String(th_val).replace(/}/, '');
			                    th_val = String(th_val).replace(/{/, '');
			                    
								// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
								if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
									var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
									
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
								}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
									var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
									
									if(typeof calVal !== "undefined"){
										var unit = calVal.split('$$')[0]; //연산자							
										if(unit != ""){
					                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
					                    }
									}
			                    }
								colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';	
							}
						}				
					}else{
						if(prop_symbol_arry[j].niso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								th_key = prop_symbol_arry[j].iso;
								th_val = itemList[k][th_key];						
		
								if(typeof th_val == "undefined" || th_val == null){
									th_val = '';
								}
			                    th_val = String(th_val).replace(/}/, '');
			                    th_val = String(th_val).replace(/{/, '');
								
			                    // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
								if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
									
									var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
									
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
		                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1
		                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
		    						var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
		    						
		    						if(typeof calVal !== "undefined"){
		    							var unit = calVal.split('$$')[0]; //연산자							
		    							if(unit != ""){
		    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		    		                    }
		    						}
		                        }
								colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';	
							}
						}
					}
				}// end of inner for
				colGroupHtml += '</tr>';
			}
			
		}//end of for -- property 컬럼별 값 셋팅
	}
	
	colGroupHtml += '</tbody>'
	//PC 아이템 리스트
	$('#table_itemList').append(colGroupHtml);
}

//아이템리스트 테이블 그리기 (모바일)
function createItemListTableMobile(){
	
	$("#itemList_mob").children().remove();
	var colGroupHtml;
	var drawCnt = 0;
	//어드민에서 설정한 인치사용여부가 on일 때
	if(inchYn == 'Y'){
		for(var k=0; k<itemList.length; k++){
			var toggleId = 'itList_'+k
			var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
			colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + itemList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0017"><img src="' +  cartImgUrl + '" alt=""></a> ' + itemList[k].MAKTX+'</p>'
			colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
			colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span><a onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )" >'+itemList[k].MATNR+'</a></th></tr>'
			colGroupHtml += '<tr>'
			colGroupHtml += '	<td>'
			colGroupHtml+= '		<div class="mdTable" id="">'
			colGroupHtml+= '			<table>'
			colGroupHtml+= '				<colgroup>'
			colGroupHtml+= '					<col width="32%">'
			colGroupHtml+= '					<col width="*">'
			colGroupHtml+= '					<col width="32%">'
			colGroupHtml+= '				</colgroup>'
			colGroupHtml+= '				<tbody>'
			colGroupHtml+= '					<tr>'
			//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
			//프로퍼티 3개까지는 따로 셋팅
				for(var j=0; j<prop_symbol_arry.length; j++){
					var th_key = '';
					if(prop_type == 'I'){
						if(prop_symbol_arry[j].iso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								if(drawCnt < 3){
									colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].iso + '</th>';	
									drawCnt++;
								}
							}
						}				
					}else{
						if(prop_symbol_arry[j].niso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								if(drawCnt < 3){
									colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].niso + '</th>';
									drawCnt++;
								}
							}
						}
					}		
				}
			colGroupHtml+= '			</tr>'
				drawCnt = 0;
	 			for(var j=0; j<prop_symbol_arry.length; j++){
					var th_key = '';
					var th_val = '';		
					
					if(prop_type == 'I'){
						if(prop_symbol_arry[j].iso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								if(drawCnt < 3){
									th_key = prop_symbol_arry[j].iso;
									th_val = itemList[k][th_key];
									
									if(typeof th_val == "undefined" || th_val == null){
										th_val = '';
									}
									
				                    th_val = String(th_val).replace(/}/, '');
				                    th_val = String(th_val).replace(/{/, '');
				                    
									// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
									if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
										var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
										
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
									// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
									}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
										var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
										
										if(typeof calVal !== "undefined"){
											var unit = calVal.split('$$')[0]; //연산자							
											if(unit != ""){
						                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
						                    }
										}
				                    }
									colGroupHtml += '<td class="center">' + th_val + '</td>';	
									drawCnt++;
								}								
							}
						}				
					}else{
						if(prop_symbol_arry[j].niso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								if(drawCnt < 3){
									th_key = prop_symbol_arry[j].iso;
									th_val = itemList[k][th_key];						
									if(typeof th_val == "undefined" || th_val == null){
										th_val = '';
									}
				                    th_val = String(th_val).replace(/}/, '');
				                    th_val = String(th_val).replace(/{/, '');
									
				                    // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
									if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
										
										var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
										
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
			                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1
			                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
			    						var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
			    						
			    						if(typeof calVal !== "undefined"){
			    							var unit = calVal.split('$$')[0]; //연산자							
			    							if(unit != ""){
			    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
			    		                    }
			    						}
			                        }
									colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';
									drawCnt++;
								}
							}
						}
					}
				} // end of for
			colGroupHtml+= '</tr></tbody></table>'
			colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
			drawCnt = 0;
			//3개 이후
				for(var j=0; j<prop_symbol_arry.length; j++){
					//console.log(rowHtml)
					if(prop_symbol_arry[j].niso){
						if(prop_symbol_arry[j].chkYn == 'Y'){
							drawCnt++;
							if(drawCnt > 3){
								colGroupHtml += '<tr>'
									colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].iso + '</th>';
								var th_key = '';
								var th_val = '';
								th_key = prop_symbol_arry[j].iso;
								th_val = itemList[k][th_key];
								
								if(typeof th_val == "undefined" || th_val == null){
									th_val = '';
								}
			                    th_val = String(th_val).replace(/}/, '');
			                    th_val = String(th_val).replace(/{/, '');
			                    
								// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
								if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
									var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
									
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
								}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
									var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
									
									if(typeof calVal !== "undefined"){
										var unit = calVal.split('$$')[0]; //연산자							
										if(unit != ""){
					                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
					                    }
									}
			                    }
								colGroupHtml += '<td class="center">' + th_val + '</td>';		
								colGroupHtml += '</tr>';	
// 								console.log(rowHtml)
// 								colGroupHtml += rowHtml
							}							
						}
					}
				}
				
			colGroupHtml+= '			</tbody>'
			colGroupHtml+= '		</table>'
			colGroupHtml+= '	</div>'
			//++++++++++++++item List property 컬럼 및 vaule E
			colGroupHtml	+= '</td></tr></tbody></table></div>'
			//모바일 아이템 리스트
			$('#itemList_mob').append(colGroupHtml);
		}// end of inch y for
	}else {
		//어드민에서 설정한 인치사용여부가 off일때
		for(var k=0; k<itemList.length; k++){
			//off인 경우는 인치 only 아이템은 제외하고 테이블을 그려준다.
			if(itemList[k].UNIT_CD !='CC0002'){
				var toggleId = 'itList_'+k
				var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
				colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + itemList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0017"><img src="' +  cartImgUrl + '" alt=""></a> ' + itemList[k].MAKTX +'</p>'
				colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
				colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span><a onClick="goItem(' + "'" +itemList[k].MATNR + "','" + itemList[k].DESIGNATION + "'" + ' )" >'+itemList[k].MATNR+'</a></th></tr>'
				colGroupHtml += '<tr>'
				colGroupHtml += '	<td>'
				colGroupHtml+= '		<div class="mdTable" id="">'
				colGroupHtml+= '			<table>'
				colGroupHtml+= '				<colgroup>'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '					<col width="*">'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '				</colgroup>'
				colGroupHtml+= '				<tbody>'
				colGroupHtml+= '					<tr>'
				//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
				drawCnt = 0;
				//프로퍼티 3개까지는 따로 셋팅
					for(var j=0; j<prop_symbol_arry.length; j++){
						var th_key = '';
						if(prop_type == 'I'){
							if(prop_symbol_arry[j].iso){
								if(prop_symbol_arry[j].chkYn == 'Y'){
									if(drawCnt < 3){
										colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].iso + '</th>';	
										drawCnt++;
									}
								}
							}				
						}else{
							if(prop_symbol_arry[j].niso){
								if(prop_symbol_arry[j].chkYn == 'Y'){
									if(drawCnt < 3){
										colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].niso + '</th>';
										drawCnt++;
									}
								}
							}
						}		
					}
				colGroupHtml+= '			</tr>'
					drawCnt = 0;
		 			for(var j=0; j<prop_symbol_arry.length; j++){
						var th_key = '';
						var th_val = '';		
						
						if(prop_type == 'I'){
							if(prop_symbol_arry[j].iso){
								if(prop_symbol_arry[j].chkYn == 'Y'){
									if(drawCnt < 3){
										th_key = prop_symbol_arry[j].iso;
										th_val = itemList[k][th_key];
										
										if(typeof th_val == "undefined" || th_val == null){
											th_val = '';
										}
										
					                    th_val = String(th_val).replace(/}/, '');
					                    th_val = String(th_val).replace(/{/, '');
					                    
										// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
										if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
											var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
											
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
										// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
										}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
											var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
											
											if(typeof calVal !== "undefined"){
												var unit = calVal.split('$$')[0]; //연산자							
												if(unit != ""){
							                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
							                    }
											}
					                    }
										colGroupHtml += '<td class="center">' + th_val + '</td>';	
										drawCnt++;
									}								
								}
							}				
						}else{
							if(prop_symbol_arry[j].niso){
								if(prop_symbol_arry[j].chkYn == 'Y'){
									if(drawCnt < 3){
										th_key = prop_symbol_arry[j].iso;
										th_val = itemList[k][th_key];						
										if(typeof th_val == "undefined" || th_val == null){
											th_val = '';
										}
					                    th_val = String(th_val).replace(/}/, '');
					                    th_val = String(th_val).replace(/{/, '');
										
					                    // Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
										if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
											
											var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
											
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
				                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1
				                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
				    						var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
				    						
				    						if(typeof calVal !== "undefined"){
				    							var unit = calVal.split('$$')[0]; //연산자							
				    							if(unit != ""){
				    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
				    		                    }
				    						}
				                        }
										colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';
										drawCnt++;
									}
								}
							}
						}
					} // end of for
				colGroupHtml+= '</tr></tbody></table>'
				colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
				drawCnt =0;
				//3개 이후
					for(var j=0; j<prop_symbol_arry.length; j++){
						if(prop_symbol_arry[j].niso){
							if(prop_symbol_arry[j].chkYn == 'Y'){
								drawCnt++;
								if(drawCnt > 3){
									colGroupHtml += '<tr>'
										colGroupHtml += '<th title="'+LANG_SET[prop_symbol_arry[j].iso]+'" data-lang="'+prop_symbol_arry[j].iso+'" data-langtype="title">' +  prop_symbol_arry[j].iso + '</th>';
									var th_key = '';
									var th_val = '';
									th_key = prop_symbol_arry[j].iso;
									th_val = itemList[k][th_key];
									
									if(typeof th_val == "undefined" || th_val == null){
										th_val = '';
									}
				                    th_val = String(th_val).replace(/}/, '');
				                    th_val = String(th_val).replace(/{/, '');
				                    
									// Inch 이고, 값이 숫자이면 cal_unit, cal_val 로 계산
									if(cur_size_unit == "CC0002" && $.isNumeric(th_val)){
										var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
										
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
									// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
									}else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                    	
										var calVal = isoCalMap.get(prop_symbol_arry[j].iso);
										
										if(typeof calVal !== "undefined"){
											var unit = calVal.split('$$')[0]; //연산자							
											if(unit != ""){
						                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
						                    }
										}
				                    }
									colGroupHtml += '<td class="center">' + th_val + '</td>';		
									colGroupHtml += '</tr>';	
								}							
							}
						}
					}
					
				colGroupHtml+= '			</tbody>'
				colGroupHtml+= '		</table>'
				colGroupHtml+= '	</div>'
				//++++++++++++++item List property 컬럼 및 vaule E
				colGroupHtml	+= '</td></tr></tbody></table></div>'
				//모바일 아이템 리스트
				$('#itemList_mob').append(colGroupHtml);
				
				if(itemClickId !== ''){
					var clickId ="#"+itemClickId
					$(clickId).slideToggle();
				}
			}
		}
	}
}

//ISO 1399 클릭
function setFilterIsoName(){
	$("#ig_filter_ul *").remove();
	
	var propHtml;
	for(var i=0; i<prop_symbol_arry.length; i++){
		if(prop_symbol_arry[i].iso){
			if(prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')" checked><span id="' + prop_symbol_arry[i].iso + '">' + prop_symbol_arry[i].iso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')"><span>' + prop_symbol_arry[i].iso + '</span></label></div></li>'
			}
			$("#ig_filter_ul").append(propHtml);
		}		
	}
	createItemListTable();
	createItemListTableMobile();
}

//Non-ISO 클릭
function setFilterNisoName(){
	$("#ig_filter_ul *").remove();
	
	var propHtml;
	for(var i=0; i<prop_symbol_arry.length; i++){		
		if(prop_symbol_arry[i].niso){
			if(prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')" checked ><span id="' + prop_symbol_arry[i].iso + '">' + prop_symbol_arry[i].niso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" title="'+LANG_SET[prop_symbol_arry[i].niso]+'" onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')"><span>' + prop_symbol_arry[i].iso + '</span></label></div></li>'
			}
			$("#ig_filter_ul").append(propHtml);
		//N-ISO가 없으면 ISO값 넣기	
		}else{
			if(prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')" checked ><span id="' + prop_symbol_arry[i].iso + '">' + prop_symbol_arry[i].iso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox"  onClick="chkProp(' + "'" + prop_symbol_arry[i].iso + "'" + ')"><span>' + prop_symbol_arry[i].iso + '</span></label></div></li>'
			}
			$("#ig_filter_ul").append(propHtml);			
		}
	}
	createItemListTable();
	createItemListTableMobile();
}

//Filter 체크박스 property 클릭
function chkProp(prop_iso_nm){
	if($('.mobileDetailItem').is(':visible') != true){
		itemClickId= ''
	}
	for(var i=0; i<prop_symbol_arry.length; i++){
		if(prop_symbol_arry[i].iso == prop_iso_nm){
			if(prop_symbol_arry[i].chkYn == 'Y'){
				prop_symbol_arry[i].chkYn = 'N';
			}else{
				prop_symbol_arry[i].chkYn = 'Y';
			}
			break;
		}
	}
	//console.log("prop_symbol_arry = " + JSON.stringify(prop_symbol_arry));
	createItemListTable();
	createItemListTableMobile();

}
//////////////////////////////////////////////////////////////// [Item] //////////////////////////////////////////////////////////////// 

var itGtcFileUrl; // GTC파일
var itP21FileUrl; // P21파일
var itDxfFileUrl; // 2D파일
var itStpBasicFileUrl; // 3D Basic파일
var itStpDetailFileUrl; // 3D Detail파일


// 아이템 정보 취득
function getItemIfno(){
	var param = {
			actionID:"<%=AppConstKey.ACTION_ITEM_INFO%>"
			,search_matnr: $('#it_matnr').val()
			,search_unit_cd: $("#btnSizeUnit").val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
//         	console.log("[ITEM] = " + JSON.stringify(e.itemInfo));
        	//console.log("[CART NO] = " + JSON.stringify(e.cartNo));
        	//console.log("[insertsCnt] = " + JSON.stringify(e.insertsCnt));
        	//console.log("[holdersCnt] = " + JSON.stringify(e.holdersCnt));
        	//console.log("[cuttingSpeedCnt] = " + JSON.stringify(e.cuttingSpeedCnt));
        	//console.log("[sparePartsCnt] = " + JSON.stringify(e.sparePartsCnt));
        	//console.log("[priceYN] = " + JSON.stringify(e.priceYN));
        	//console.log("[stockYN] = " + JSON.stringify(e.stockYN));        	
        	
        	itemInfo = e.itemInfo;
        	var cart_no = e.cartNo;
        	var insertsCnt = e.insertsCnt;
        	var holdersCnt = e.holdersCnt;
        	var cuttingSpeedCnt = e.cuttingSpeedCnt;
        	var sparePartsCnt = e.sparePartsCnt;
        	var priceYN = e.priceYN;
        	var stockYN = e.stockYN;        	

			$("#icodian_it").text(itemInfo.DESIGNATION);
			$('#ig_box_top').removeClass('on');
			$('#item_ig_box_top').addClass('on')

        	createItemInfo2Table(cart_no, priceYN, stockYN);
        	
			// 아이템이미지
			//console.log("ITEM_IMG_THUMB == " +  itemInfo.ITEM_IMAGE_THUMB);
			//console.log("ITEM_IMG == " +  itemInfo.ITEM_IMG);
			
			//itemImgUrl = "";
			$("#it_image").attr("src", ""); //초기화
			itemClickId =''
			if(itemInfo.ITEM_IMAGE_THUMB !== undefined){
				itemImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.ITEM_IMAGE_THUMB;
			}else{
				itemImgUrl = "";
			}
			$("#it_image").attr("src", itemImgUrl);
			
			// 아이템 팝업 이미지
			//popItemImgUrl = "";
			if(itemInfo.ITEM_IMG !== undefined){
				popItemImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.ITEM_IMG;
			}else{
				// 원본이미지가 없으면 썸네일 이미지로 대체
				if(itemInfo.ITEM_IMAGE_THUMB !== undefined){
					popItemImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.ITEM_IMAGE_THUMB;
				}
			}			
			
			// GTC 파일
			//itGtcFileUrl = "";
			if(itemInfo.GTC_FILE !== undefined){
				itGtcFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.GTC_FILE;
			}else{
				itGtcFileUrl = "";
			}
 
			// P21 파일
			//itP21FileUrl = "";
			if(itemInfo.P21_FILE !== undefined){
				itP21FileUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.P21_FILE;
			}else{
				itP21FileUrl = "";
			}			
			console.log("itemInfo:", itemInfo);
			// 2D Representation Image
			var itDxfImgUrl;		
			if(itemInfo.DXF_IMG_THUMB !== undefined){
				itDxfImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.DXF_IMG_THUMB;
			}else{
				itDxfImgUrl = "";
			}
			//2D 팝업 이미지
			var popItDxfImgUrl;
			if(itemInfo.DXF_IMG !== undefined){
				popItDxfImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.DXF_IMG;
			}else{
				// 원본이미지가 없으면 썸네일 이미지로 대체
				if(itemInfo.ITEM_IMAGE_THUMB !== undefined){
					popItDxfImgUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.ITEM_IMAGE_THUMB;
				}
			}			
			
			//2D이미지 표시
			//$("#img_it_dxf").attr("src", ""); //초기화
			if(itDxfImgUrl !== ""){ 
				$("#img_it_dxf").attr("src", itDxfImgUrl);
			}
			
			//2D 팝업 이미지 표시
			//$("#img_pop_dxf").attr("src", ""); //초기화
			if(popItDxfImgUrl !== ""){ 
				$("#img_pop_dxf").attr("src", popItDxfImgUrl);
			}
			
			// 2D Representation
			//itDxfFileUrl = "";
			if(itemInfo.DXF_FILE !== undefined){
				itDxfFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.DXF_FILE;
			}else{
				itDxfFileUrl = "";
			}
			//로그등록 이벤트 코드 - 2d download
			var evtCd2d = "'EC0022'"
			$('#it_dxf_file_url').attr('onclick', 'doLogAtDownLoad("'+itemInfo.MATNR+'", '+evtCd2d+')');
			if(USERAUTH.isDxfDownAuth){
			 	if(itDxfFileUrl !== ""){		
					//dxf 파일 다운로드
					var file_nm = itDxfFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_dxf_file_url").attr('href', itDxfFileUrl);
					$("#it_dxf_file_url").attr('download', file_nm);		
				}else{
					<%-- 저장된 파일이 없습니다. --%>
					$('#it_dxf_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				//로그인 한 경우와 아닌경우 분기 처리
				var userId = USERINFO.user_id;
				if(userId == ""){
					$('#it_dxf_file_url').attr('onclick', 'openLogin('+itemInfo.MATNR+', '+evtCd2d+')');
// 					$(".open_login").click();
				}else{
					<%-- 권한이 없으면 승급 요청을 하세요 --%>
					$('#it_dxf_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000229"]+'");');
				}
			}
			 
			// 3D Representation (Basic)
			//itStpBasicFileUrl = "";
			if(itemInfo.STP_BASIC_FILE !== undefined){
				itStpBasicFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.STP_BASIC_FILE;
				go3dBasic();
			}else{
				itStpBasicFileUrl = "";
			}
			//로그저장 - 3d 다운로드 / EC0021
			var evtCd3d ="'EC0021'"
			$("#it_stp_basic_file_url").attr('onclick', 'doLogAtDownLoad("'+itemInfo.MATNR+'", "'+evtCd3d+'")')
			if(USERAUTH.isStpDownAuth){
				if(itStpBasicFileUrl !== ""){
					//stp basic 파일 다운로드
					var file_nm = itStpBasicFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_stp_basic_file_url").attr('href', itStpBasicFileUrl);
					$("#it_stp_basic_file_url").attr('download', file_nm);					
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#it_stp_basic_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				//로그인 한 경우와 아닌경우 분기 처리
				var userId = USERINFO.user_id;
				if(userId == ""){
// 					$(".open_login").click();
					$('#it_stp_basic_file_url').attr('onclick', 'openLogin("'+itemInfo.MATNR+'", "'+evtCd3d+'")');
				}else{
					<%-- 권한이 없으면 승급 요청을 하세요 --%>
					$('#it_stp_basic_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000229"]+'");');
				}
			}						
			
 			// 3D Representation (Detail)
			//itStpDetailFileUrl = "";
			if(itemInfo.STP_DETAIL_FILE !== undefined){
				itStpDetailFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + itemInfo.STP_DETAIL_FILE;
				// 3D basic이 없고 Detail만 있는경우 Detail 보여주기
				if(itStpBasicFileUrl == ""){
					go3dDetailed();
				}
			}else{
				itStpDetailFileUrl = "";
			}
			//로그저장 - 3d detail
			$("#it_stp_detail_file_url").attr('onclick', 'doLogAtDownLoad("'+itemInfo.MATNR+'", "'+evtCd3d+'")')
			if(USERAUTH.isStpDownAuth){
				if(itStpDetailFileUrl !== ""){				
					//stp basic 파일 다운로드
					var file_nm = itStpDetailFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$("#it_stp_detail_file_url").attr('href', itStpDetailFileUrl);
					$("#it_stp_detail_file_url").attr('download', file_nm);
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#it_stp_detail_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
				}	
			}else{
				//로그인 한 경우와 아닌경우 분기 처리
				var userId = USERINFO.user_id;
				if(userId == ""){
					$("#it_stp_detail_file_url").attr("onclick", 'openLogin("'+itemInfo.MATNR+'", "'+evtCd3d+'")');
				}else{
					<%-- 권한이 없으면 승급 요청을 하세요 --%>
					$('#it_stp_detail_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000229"]+'");');
				}
			}	
		
			setItemFilterIsoName();
			
			
			// Related Inserts 리스트가 하나도 없으면 테이블 보이지 않게처리
 			if(parseInt(insertsCnt) < 1){
				$("#bar_rel_inserts").hide();
			}else{
				$("#bar_rel_inserts").show();
			}
 			// Related Holders 리스트가 하나도 없으면 테이블 보이지 않게처리
 			if(parseInt(holdersCnt) < 1){
				$("#bar_rel_holders").hide();
			}else{
				$("#bar_rel_holders").show();
			}
 			
			// Recommended Cutting Speeds 리스트가 하나도 없으면 테이블 보이지 않게처리
 			if(parseInt(cuttingSpeedCnt) < 1){
				$("#bar_cut_speed").hide();
			}else{
				$("#bar_cut_speed").show();
			}
 			// Spare Parts 리스트가 하나도 없으면 테이블 보이지 않게처리
 			if(parseInt(sparePartsCnt ) < 1){
				$("#bar_spare_parts").hide();
			}else{
				$("#bar_spare_parts").show();
			}
        	
        }, complete:function(){
            $('#loadbar').fadeOut();
        }, error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

//GTC파일 다운로드 클릭
function downGtcFile(){	
	if(USERAUTH.isOneGtcDownAuth){
		//로그 동록	
		doLog('EC0018', itemInfo.MATNR, '')	
		if(itGtcFileUrl !== ""){
			//dxf 파일 다운로드
			var file_nm = itGtcFileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];							
			$("#it_gtc_file_url").attr('href', itGtcFileUrl);
			$("#it_gtc_file_url").attr('download', file_nm);
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#it_gtc_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
		    //$(".open_vip").click();
		}	
	}		
}

//모바일 GTC파일 다운로드 클릭
function mDownGtcFile(){	
	if(USERAUTH.isOneGtcDownAuth){
		//로그 동록	
		doLog('EC0018', itemInfo.MATNR, '')		
		if(itGtcFileUrl !== ""){
			//dxf 파일 다운로드
			var file_nm = itGtcFileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];				
			$("#m_it_gtc_file_url").attr('href', itGtcFileUrl);
			$("#m_it_gtc_file_url").attr('download', file_nm);	
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#m_it_gtc_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
		    //$(".open_vip").click();
		}	
	}	
}

//P21파일 다운로드 클릭
function downP21File(){
	if(USERAUTH.isP21DownAuth){
		//로그 동록	
		doLog('EC0019', itemInfo.MATNR, '')	
		if(itP21FileUrl !== ""){
			//dxf 파일 다운로드
			var file_nm = itP21FileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];		
			$("#it_p21_file_url").attr('href', itP21FileUrl);
			$("#it_p21_file_url").attr('download', file_nm);
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#it_p21_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
		    //$(".open_vip").click();
		}	
	}	
}

//모바일 P21파일 다운로드 클릭
function mDownP21File(){
	if(USERAUTH.isP21DownAuth){
		//로그 동록	
		doLog('EC0019', itemInfo.MATNR, '')	
		if(itP21FileUrl !== ""){
			//dxf 파일 다운로드
			var file_nm = itP21FileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];		
			$("#m_it_p21_file_url").attr('href', itP21FileUrl);
			$("#m_it_p21_file_url").attr('download', file_nm);
		}else{
			<%-- 저장된 파일이 없습니다.--%>
			$('#m_it_p21_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{
			<%-- 권한이 없으면 승급 요청을 하세요 --%>
			alert(LANG_SET["FR000229"]);
		    //$(".open_vip").click();
		}	
	}	
}
<%-- 
//2D 파일 다운로드 클릭
function downDxfFile(){
	
	if(USERAUTH.isDxfDownAuth){
	 	if(itDxfFileUrl !== ""){		
			//dxf 파일 다운로드
			var file_nm = itDxfFileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];								
			$("#it_dxf_file_url").attr('href', itDxfFileUrl);
			$("#it_dxf_file_url").attr('download', file_nm);		
		}else{
			저장된 파일이 없습니다.
			$('#it_dxf_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}
	}	
}
 --%>
//3D 파일 Basic 파일 다운로드 클릭
<%-- 
function downBasicFile(){

	if(USERAUTH.isStpDownAuth){
		if(itStpBasicFileUrl !== ""){
			//stp basic 파일 다운로드
			var file_nm = itStpBasicFileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];								
			$("#it_stp_basic_file_url").attr('href', itStpBasicFileUrl);
			$("#it_stp_basic_file_url").attr('download', file_nm);
		}else{
			저장된 파일이 없습니다.
			$('#it_stp_basic_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}
	}
}
 --%>
<%--  
//3D 파일 Detail 파일 다운로드 클릭
function downDetailFile(){
	
	if(USERAUTH.isStpDownAuth){
		if(itStpDetailFileUrl !== ""){				
			//stp basic 파일 다운로드
			var file_nm = itStpDetailFileUrl.split('/');
			file_nm = file_nm[file_nm.length-1];								
			$("#it_stp_detail_file_url").attr('href', itStpDetailFileUrl);
			$("#it_stp_detail_file_url").attr('download', file_nm);
		}else{
			저장된 파일이 없습니다.
			$('#it_stp_detail_file_url').attr('href', 'javascript:alert("' + LANG_SET["FR000153"]+'");');
		}	
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}
	}	
}
 --%>
function openLogin(matnr, evtCd){
// 	//로그저장
// 	doLog(evtCd, matnr, '')
	var userId = USERINFO.user_id;
	if(userId == ""){
		$(".open_login").click();
	}
}


//ISO 1399 클릭
function setItemFilterIsoName(){
	$("#it_filter_ul *").remove();
	
	var propHtml;
	for(var i=0; i<item_prop_symbol_arry.length; i++){
		if(item_prop_symbol_arry[i].iso){
			if(item_prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'"  data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')" checked><span id="' + item_prop_symbol_arry[i].iso + '">' + item_prop_symbol_arry[i].iso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title" ><input type="checkbox" onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')"><span>' + item_prop_symbol_arry[i].iso + '</span></label></div></li>'
			}
			$("#it_filter_ul").append(propHtml);
		}
	}
	if(devType == "P"){
		createItemInfo1Table();	
	}else{
		createItemInfoTableMobile();		
	}
}

//Non-ISO 클릭
function setItemFilterNisoName(){
	$("#it_filter_ul *").remove();
	
	var propHtml;
	for(var i=0; i<item_prop_symbol_arry.length; i++){
		if(item_prop_symbol_arry[i].niso){
			if(item_prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check" ><label class="chkN" title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox"  onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')" checked ><span id="' + item_prop_symbol_arry[i].iso + '">' + item_prop_symbol_arry[i].niso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check" ><label class="chkN" title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox"  onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')"><span>' + item_prop_symbol_arry[i].niso + '</span></label></div></li>'
			}
			$("#it_filter_ul").append(propHtml);
		//N-ISO가 없으면 ISO값 넣기	
		}else{
			if(item_prop_symbol_arry[i].chkYn == 'Y'){
				propHtml = '<li><div class="check"><label class="chkN"  title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')" checked ><span id="' + item_prop_symbol_arry[i].iso + '">' + item_prop_symbol_arry[i].iso + '</span></label></div></li>'
			}else{
				propHtml = '<li><div class="check"><label class="chkN" title="'+LANG_SET[item_prop_symbol_arry[i].iso]+'" data-lang="'+prop_symbol_arry[i].iso+'" data-langtype="title"><input type="checkbox" onClick="chkItemProp(' + "'" + item_prop_symbol_arry[i].iso + "'" + ')"><span>' + item_prop_symbol_arry[i].iso + '</span></label></div></li>'
			}
			$("#it_filter_ul").append(propHtml);
		}
	}
	
	if(devType == "P"){
		createItemInfo1Table();	
	}else{
		createItemInfoTableMobile();		
	}
}

//Filter 체크박스 property 클릭
function chkItemProp(prop_iso_nm){		
	for(var i=0; i<item_prop_symbol_arry.length; i++){
		if(item_prop_symbol_arry[i].iso == prop_iso_nm){
			if(item_prop_symbol_arry[i].chkYn == 'Y'){
				item_prop_symbol_arry[i].chkYn = 'N';
			}else{
				item_prop_symbol_arry[i].chkYn = 'Y';
			}
			break;
		}
	}
	
	if(devType == "P"){
		createItemInfo1Table();	
	}else{
		createItemInfoTableMobile();		
	}
}

//아이템 테이블 그리기  (PC)
 function createItemInfo1Table(){
	$("#table_itemInfo1 *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width=""><col width=""><col width=""><col width=""><col width=""><col width=""><col width="">';		
// 	if(item_prop_type == 'I'){
// 		for(var i=0; i<item_prop_symbol_arry.length; i++){
// 			if(item_prop_symbol_arry[i].iso){
// 				if(item_prop_symbol_arry[i].chkYn == 'Y'){
// 					var colHtml;
// 					colHtml = '<col width="">';		
// 					colGroupHtml += colHtml;
// 				}
// 			}
// 		}
// 	}else{
// 		for(var i=0; i<item_prop_symbol_arry.length; i++){
// 			if(item_prop_symbol_arry[i].niso){
// 				if(item_prop_symbol_arry[i].chkYn == 'Y'){
// 					var colHtml;
// 					colHtml = '<col width="*">';		
// 					colGroupHtml += colHtml;
// 				}
// 			}
// 		}
// 	}
	colGroupHtml += '</colgroup>';
	// <colgroup> 태그 END
	<%-- Cart --%>
	<%-- Order Number --%>
	<%-- Designation --%>

	
	colGroupHtml += '<thead><tr><th class="tdCart" data-lang="FR000040">' + LANG_SET["FR000040"] + '</th><th class="tdOrd" data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th class="tdDegi" data-lang="FR000039">' + LANG_SET["FR000039"] + '</th></tr>'
	
	colGroupHtml += '<tr><td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + itemInfo.MATNR + '" class="bnt_cart" data-evtcd="EC0017"><img src="' +  cartImgUrl + '" alt=""></a></td><td style="" >' + itemInfo.MATNR + '</td>';  // Order Number 
	colGroupHtml += '<td class="ellipsis" title="' + itemInfo.MAKTX + '">' + itemInfo.MAKTX + '</td></tr>';
	colGroupHtml += '</thead><tbody><tr>'


	for(var j=0; j<item_prop_symbol_arry.length; j++){
		var th_key = '';
		
		if(item_prop_type == 'I'){
			if(item_prop_symbol_arry[j].iso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					colGroupHtml += '<th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title" >' +  item_prop_symbol_arry[j].iso + '</th>';	
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
				
	colGroupHtml += '<tr>';  
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
                    // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                    	
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
					colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';	
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
                    // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
					colGroupHtml += '<td class="center"  style="height:30px;">' + th_val + '</td>';	
				}
			}
		}
	}
	colGroupHtml += '</tr></tbody>';
	
	$('#table_itemInfo1').append(colGroupHtml);	
} 

function createItemInfo2Table(cart_no, priceYN, stockYN){
	
	// blueLine Table
	$("#table_itemInfo2 *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width="20%"><col width="*"><col width="*"><col width="*"><col width="*"><col width="*">';	 

	colGroupHtml += '</colgroup><tbody><tr>';
	// <colgroup> 태그 END
	
	// <tr><th> 태그 START
	colGroupHtml += '<tbody>'
	<%-- GTIN(EAN) --%>
	<%-- File Download --%>
	<%-- Add to Assembly --%>
	<%-- Currency --%>
	<%-- Price --%>
	<%-- Stock Availability --%>
// 	alert(inchYn)
/* 	
	colGroupHtml += '<tr><th>' + LANG_SET["FR000042"] + '</th><th colspan="2">' + LANG_SET["FR000043"] + '</th><th colspan="2">' + LANG_SET["FR000044"] + '</th>' +
		'<th colspan="2">' + LANG_SET["FR000045"] + '</th><th colspan="2">' + LANG_SET["FR000046"] + '</th><th colspan="2">' + LANG_SET["FR000047"] + '</th></tr>';
 */		
		
	colGroupHtml += '<tr><th data-lang="FR000175">' + LANG_SET["FR000175"] + '</th><th colspan="2" data-lang="FR000043">' + LANG_SET["FR000043"] + '</th><th colspan="2" data-lang="FR000044">' + LANG_SET["FR000044"] + '</th>';
	//Admin 설정값(Currency, Price)에 따라 필드 표시	
	if(priceYN == 'Y'){
		colGroupHtml += '<th colspan="2" data-lang="FR000045">' + LANG_SET["FR000045"] + '</th><th colspan="2" data-lang="FR000046">' + LANG_SET["FR000046"] + '</th>';
	}else{
// 		$('#col_price_item_mob').remove();
	}
	//Admin 설정값(Stock)에 따라 필드 표시
	if(stockYN == 'Y'){
		colGroupHtml += '<th colspan="2" data-lang="FR000047">' + LANG_SET["FR000047"] + '</th>'
	}else{
// 		$('#col_stock_item_mob').remove();
	}
	colGroupHtml += '</tr>';			
	
	var assemblyImgUrl = "<%=PATH_IMG%>/img_assembly.png";
	
	<%-- var userCurrency = "<%=ConfigMng.getCurrency(request) %>"; --%>
	var userCurrency = cur_currency; //세션 통화로 변경		
	
	var kbetr;
	var selCurrency = "";
	if(userCurrency == 'CC0076'){ // KRW
		kbetr = numberFormatting(itemInfo.K_KBETR, userCurrency)
		selCurrency = "KRW";
		//kbetr = itemInfo.K_KBETR;
	}else if(userCurrency == 'CC0077'){ // USD
		kbetr = numberFormatting(itemInfo.U_KBETR, userCurrency)
		selCurrency = "USD";
		//kbetr = itemInfo.U_KBETR;
	}else if(userCurrency == 'CC0078'){ // EUR
		kbetr = numberFormatting(itemInfo.E_KBETR, userCurrency)
		selCurrency = "EUR";
		//kbetr = itemInfo.E_KBETR;
	}else{
		kbetr = numberFormatting(itemInfo.U_KBETR, userCurrency)
		selCurrency = "USD";
		//kbetr = itemInfo.U_KBETR;
	}
 
	//재고수량
	var labst;
	if(parseInt(itemInfo.ST_LABST) > 0){
		<%-- IN STOCK --%>
		labst = LANG_SET["FR000227"];
	}else{
		<%-- OUT OF STOCK --%>
		labst = LANG_SET["FR000226"];
	}
		
	<%-- GTC --%>
	<%-- P21 --%>
/* 	
	colGroupHtml += '<tr><td id="it_cart_no">' + cart_no + '</td><td><a id="it_gtc_file_url" style="cursor:pointer;" onclick="downGtcFile()">' + LANG_SET["FR000079"] + '</a></td><td><a id="it_p21_file_url" style="cursor:pointer;" onclick="downP21File()">P21</a></td><td colspan="2"><img onclick="javascript:goAssembly();" src="' + assemblyImgUrl + '" alt="assembly" style="cursor:pointer;"></td>' +
							'<td colspan="2">' + selCurrency + '</td><td colspan="2" class="right">' + kbetr + '</td><td colspan="2" class="left">' + labst + '</td></tr></tbody>';	
 */
//   	console.log(itemInfo)
//   	alert(":"+itemInfo.ZADDEAN+":")
	var zaddean = "";
 	if(typeof itemInfo.ZADDEAN == "undefined" || itemInfo.ZADDEAN == null){
 		zaddean = ""
 	}else{
 		zaddean = itemInfo.ZADDEAN
 	}
	colGroupHtml += '<tr><td id="it_zaddean">' + zaddean + '</td><td><a id="it_gtc_file_url" style="cursor:pointer;" onclick="downGtcFile()" data-lang="FR000079">' + LANG_SET["FR000079"] + '</a></td><td><a id="it_p21_file_url" style="cursor:pointer;" onclick="downP21File()">P21</a></td><td colspan="2"><img onclick="javascript:goAssembly();" src="' + assemblyImgUrl + '" alt="assembly" style="cursor:pointer;"></td>';
	//Admin 설정값(Currency, Price)에 따라 필드 표시						
	if(priceYN == 'Y'){
		colGroupHtml += '<td colspan="2">' + selCurrency + '</td><td colspan="2" class="right">' + kbetr + '</td>';
	}else{
// 		$('#col_currency_item_mob').remove();
	}
	//Admin 설정값(Stock)에 따라 필드 표시
	if(stockYN == 'Y'){
		colGroupHtml += '<td colspan="2" class="left">' + labst + '</td>';
	}else{
		
	}
	colGroupHtml += '</tr></tbody>';							
	// <tr><th> 태그 END
	
 	colGroupHtml += '</tbody>'
	// itemList <tr><td> 태그 END

 	$('#table_itemInfo2').append(colGroupHtml);
	
	//모바일
 	$('#it_m_gtin').text(zaddean);
 	if(priceYN == 'Y'){
 		$("#th_m_currency").show();
 		$('#it_m_currency').text(selCurrency);
 		$("#th_m_price").show();
 	 	$('#it_m_price').text(kbetr);	
 	}else{
 		$("#th_m_currency").hide();
 		$('#it_m_currency').hide();
 		$("#th_m_price").hide();
 	 	$('#it_m_price').hide();
 	 	$('#col_price_item_mob').remove();
 	 	$('#col_currency_item_mob').remove();
 	 	
 	}
 	if(stockYN == 'Y'){
 		$('#th_m_stock').show();
 		$('#it_m_stock').text(labst);	
 	}else{
 	 	$('#col_stock_item_mob').remove();
 		$('#th_m_stock').hide();
 		$('#it_m_stock').hide();
 	}
}

// 아이템정보 테이블 (모바일)
function createItemInfoTableMobile(){
	$("#table_itemInfo1_mobile *").remove();
	$("#table_itemInfo2_mobile *").remove();
	
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width="32%"><col width="*"><col width="32%"></colgroup><tbody><tr>'; 
	// <colgroup> 태그 END	
	$('#item_cart_mob').attr('data-matnr', itemInfo.MATNR)
	$('#item_matnr_mob').html(itemInfo.MATNR)
	// Default : 자재내역
	$('#item_desig_mob').html(itemInfo.MAKTX)
	var drawCnt = 0;
	// <tr><th> 태그 START
// 	colGroupHtml += '<th style="padding-left: 0px; padding-right: 0px;" data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th style="padding-left: 0px; padding-right: 0px;" data-lang="FR000039">' + LANG_SET["FR000039"] + '</th><th style="padding-left: 0px; padding-right: 0px;" data-lang="FR000040">' + LANG_SET["FR000040"] + '</th>'; // Default :  Order Number,  Designation, Cart
	
	for(var j=0; j<item_prop_symbol_arry.length; j++){
		var th_title;
		if(item_prop_type == 'I'){
			if(item_prop_symbol_arry[j].iso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					if(drawCnt < 3){
						th_title = item_prop_symbol_arry[j].iso;
						colGroupHtml += '<th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' + th_title + '</th>';
						drawCnt++;
					}
				}
			}
		}else{
			if(item_prop_symbol_arry[j].niso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					if(drawCnt < 3){
						th_title = item_prop_symbol_arry[j].niso;
						colGroupHtml += '<th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' + th_title + '</th>';
						drawCnt++;
					}
				}
			}
		}
	}
	colGroupHtml += '</tr>';
	// <tr><th> 태그 END	
	
	drawCnt = 0;
	// itemInfo<tr><td> 태그 START 		
	var rowHtml = '<tr>';
	
	for(var j=0; j<item_prop_symbol_arry.length; j++){
		var th_key = '';
		var th_val = '';
		
		if(item_prop_type == 'I'){
			if(item_prop_symbol_arry[j].iso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					if(drawCnt < 3){
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
                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                        	
    						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
    						
    						if(typeof calVal !== "undefined"){
    							var unit = calVal.split('$$')[0]; //연산자							
    							if(unit != ""){
    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
    		                    }
    						}
                        }
						rowHtml += '<td>' + th_val + '</td>';
						drawCnt++;
					}
				}					
			}
		}else{
			if(item_prop_symbol_arry[j].niso){
				if(item_prop_symbol_arry[j].chkYn == 'Y'){
					if(drawCnt < 3){
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
                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                        	
    						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
    						
    						if(typeof calVal !== "undefined"){
    							var unit = calVal.split('$$')[0]; //연산자							
    							if(unit != ""){
    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
    		                    }
    						}
                        }
						rowHtml += '<td>' + th_val + '</td>';	
						drawCnt++;
					}
				}					
			}
		}
	}		
	rowHtml += '</tr>'; // Default :  Order Number 
	colGroupHtml += rowHtml;
	
 	colGroupHtml += '</tbody>'
	// itemInfo <tr><td> 태그 END
	
	drawCnt = 0;
	$('#table_itemInfo1_mobile').append(colGroupHtml);		
	
	//###############################################################
	// 필터에서 선택한 갯수가 3개 이상이면 테이블 추가로 그리기
		
		// <colgroup> 태그 START
		var colGroupHtml = '<colgroup><col width="*"><col width="*"></colgroup>';
		// <colgroup> 태그 END	
		
		colGroupHtml += '<tbody>';
		
		var propRrowHtml;
		for(var j=0; j<item_prop_symbol_arry.length; j++){
			
			var th_key = '';
			var th_val = '';
			
			if(item_prop_type == 'I'){
				if(item_prop_symbol_arry[j].iso){
					if(item_prop_symbol_arry[j].chkYn == 'Y'){
						drawCnt++;
						if(drawCnt > 3){
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
	                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
	                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                        	
	    						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
	    						
	    						if(typeof calVal !== "undefined"){
	    							var unit = calVal.split('$$')[0]; //연산자							
	    							if(unit != ""){
	    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
	    		                    }
	    						}
	                        }
							propRrowHtml += '<tr><th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' + th_key +'</th><td>' + th_val + '</td></tr>';
						}
					}					
				}
			}else{
				if(item_prop_symbol_arry[j].niso){
					if(item_prop_symbol_arry[j].chkYn == 'Y'){
						drawCnt++;
						if(drawCnt < 3){
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
	                        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
	                        }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){	                        	
	    						var calVal = isoCalMap.get(item_prop_symbol_arry[j].iso);
	    						
	    						if(typeof calVal !== "undefined"){
	    							var unit = calVal.split('$$')[0]; //연산자							
	    							if(unit != ""){
	    		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
	    		                    }
	    						}
	                        }
							propRrowHtml += '<tr><th title="'+LANG_SET[item_prop_symbol_arry[j].iso]+'" data-lang="'+item_prop_symbol_arry[j].iso+'" data-langtype="title">' + th_key +'</th><td>' + th_val + '</td></tr>';
						}
					}					
				}
			}
		}
		
		colGroupHtml += propRrowHtml;		
	 	colGroupHtml += '</tbody>'
		// itemInfo <tr><td> 태그 END
				
		$('#table_itemInfo2_mobile').append(colGroupHtml);
				
}

//Related Inserts  목록 조회
function getRelatedInsertsList(){
	var param = {
			actionID:"<%=AppConstKey.ACTION_RELATED_INSERTS_LIST%>"
			,search_matnr: $('#it_matnr').val()
			,search_unit_cd: $("#btnSizeUnit").val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
         success: function (e) {
         	//console.log("[Related Inserts] = " + JSON.stringify(e.insertsList));
         	//console.log("[Related Inserts Properties] = " + JSON.stringify(e.prop_list));
         	
         	var resultInsertsList = e.insertsList;
         	var propList = e.prop_list;
         	if(resultInsertsList.length > 0){
         		createRelatedInsertsTable(resultInsertsList, propList);	
         		createRelatedInsertsTableMobile(resultInsertsList, propList);	
         	}
         },complete:function(){
             $('#loadbar').fadeOut();
         },error: function (request, error) {
         	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
     });
 }
 
//Related Inserts 테이블 그리기
 function createRelatedInsertsTable(insertsList, propList){

	 $("#table_relInserts *").remove();
	 	 
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width=""><col width=""><col width=""><col width=""><col width=""><col width=""><col width="">';		
	colGroupHtml += '</colgroup>';
	// <colgroup> 태그 END
	
	<%-- Cart --%>	<%-- Order Number --%>	<%-- Designation --%> 	<%-- Grade --%>			
	colGroupHtml += '<thead><tr><th class="tdCart" data-lang="FR000040">' + LANG_SET["FR000040"] + '</th><th class="tdOrd" data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th class="tdDegi" data-lang="FR000039">' + LANG_SET["FR000039"] + '</th></tr>'
	
	//로그 저장를 위해 필요한 이벤트 코드값 - 인서트
	var evtCd = "'EC0023'"	
	//어드민에서 인치사용여부 on인 경우
	if(inchYn == 'Y'){
		for(var k=0; k<insertsList.length; k++){ 		
			var rowHtml = '<tr>';
			var matnr = "'"+insertsList[k].MATNR +"'";
			rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + insertsList[k].MATNR + '" class="bnt_cart"  data-evtcd="EC0024"><img src="' +  cartImgUrl + '" alt=""></a></td>';  // Default : Cart
			rowHtml += '<td style="cursor:pointer;" onclick="showItemDetailPop('+matnr+','+evtCd+' )"><a>' + insertsList[k].MATNR + '</a></td>';  // Default :  Order Number 
			rowHtml += '<td class="left" onclick="showItemDetailPop('+matnr+','+evtCd+' )"><a>' + insertsList[k].MAKTX + '</a></td></tr>';  
			colGroupHtml += rowHtml; 
		}// end of for insertLists
	}else{
		//어드민에서 인치사용여부 off인 경우
		for(var k=0; k<insertsList.length; k++){ 
			if(insertsList[k].UNIT_CD !='CC0002'){
				var rowHtml = '<tr>';
				var matnr = "'"+insertsList[k].MATNR +"'";
				rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + insertsList[k].MATNR + '" class="bnt_cart"  data-evtcd="EC0024"><img src="' +  cartImgUrl + '" alt=""></a></td>';  // Default : Cart
				rowHtml += '<td style="cursor:pointer;" onclick="showItemDetailPop('+matnr+','+evtCd+' )"><a>' + insertsList[k].MATNR + '</a></td>';  // Default :  Order Number 
				rowHtml += '<td class="left" onclick="showItemDetailPop('+matnr+','+evtCd+' )"><a>' + insertsList[k].MAKTX + '</a></td></tr>';  
				colGroupHtml += rowHtml; 
			}
		}// end of for insertLists
	}
	
	colGroupHtml += '</thead><tbody><tr>'

	for(var j=0; j<propList.length; j++){
		var th_key = '';
		if(propList[j].prop_iso){
               colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
		}				
	}
	colGroupHtml += '</tr>'
if(inchYn == 'Y'){
	for(var k=0; k<insertsList.length; k++){ 	
		
		colGroupHtml += '<tr>';  // Default : Cart
		
		for(var j=0; j<propList.length; j++){
			var th_key = '';
			var th_val = '';
			
			//if(item_prop_type == 'I'){
				if(propList[j].prop_iso){
                    th_key = propList[j].prop_iso;
                    th_val = insertsList[k][th_key];
                    
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
                    // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
						var calVal = isoCalMap.get(propList[j].prop_iso);
						
						if(typeof calVal !== "undefined"){
							var unit = calVal.split('$$')[0]; //연산자							
							if(unit != ""){
		                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
		                    }
						}
                    }
                    colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>'; 
				}				
		} //end of inner for
		colGroupHtml += '</tr>';
	}// end of first for
}else{
for(var k=0; k<insertsList.length; k++){ 	
		if(insertsList[k].UNIT_CD !='CC0002'){
			colGroupHtml += '<tr>';  // Default : Cart
			
			for(var j=0; j<propList.length; j++){
				var th_key = '';
				var th_val = '';
				
				//if(item_prop_type == 'I'){
					if(propList[j].prop_iso){
	                    th_key = propList[j].prop_iso;
	                    th_val = insertsList[k][th_key];
	                    
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
	                    // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
	                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){
							var calVal = isoCalMap.get(propList[j].prop_iso);
							
							if(typeof calVal !== "undefined"){
								var unit = calVal.split('$$')[0]; //연산자							
								if(unit != ""){
			                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
			                    }
							}
	                    }
	                    colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>'; 
					}				
			} //end of inner for
			colGroupHtml += '</tr>';
		}
	}// end of first for
}
	colGroupHtml += '</tbody>'

	//console.log("colGroupHtml" + colGroupHtml);	
	$('#table_relInserts').append(colGroupHtml)
 }
//Related Inserts 테이블 그리기 - 모바일
 function createRelatedInsertsTableMobile(insertsList, propList){
	
	 $("#area_inserts_mob").children().remove();
	 	 
	//로그 저장를 위해 필요한 이벤트 코드값 - 인서트
	var evtCd = "'EC0023'"	
	var drawCnt=0;
	//어드민에서 설정한 인치사용여부가 on일 때
	if(inchYn == 'Y'){
		for(var k=0; k<insertsList.length; k++){
			var toggleId = 'relInsert_'+k
			var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 			colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + insertsList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0024"><img src="' +  cartImgUrl + '" alt=""></a> ' + insertsList[k].MAKTX +'</p>'
			colGroupHtml +=  insertsList[k].MAKTX +'</p>'
			colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
			colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+insertsList[k].MATNR+'</th></tr>'
			colGroupHtml += '<tr>'
			colGroupHtml += '	<td>'
			colGroupHtml+= '		<div class="mdTable" id="">'
			colGroupHtml+= '			<table>'
			colGroupHtml+= '				<colgroup>'
			colGroupHtml+= '					<col width="32%">'
			colGroupHtml+= '					<col width="*">'
			colGroupHtml+= '					<col width="32%">'
			colGroupHtml+= '				</colgroup>'
			colGroupHtml+= '				<tbody>'
			colGroupHtml+= '					<tr>'
			//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
			//프로퍼티 3개까지는 따로 셋팅
			for(var j=0; j<propList.length; j++){
				if(propList[j].disp_mb_yn == 'Y'){
					if(drawCnt < 3){
						colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';	
						drawCnt++;
					}
				}
			}//end of for property head
			colGroupHtml+= '			</tr>'
			drawCnt = 0;
	 			for(var j=0; j<propList.length; j++){
					var th_key = '';
					var th_val = '';		
					
					if(propList[j].disp_mb_yn == 'Y'){
						if(drawCnt < 3){
							th_key = propList[j].prop_iso;
							th_val = insertsList[k][th_key];
							
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
							// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
					}
				} // end of for
			colGroupHtml+= '</tr></tbody></table>'
			colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
			drawCnt = 0;
			//3개 이후
				for(var j=0; j<propList.length; j++){
					if(propList[j].disp_mb_yn == 'Y'){
							drawCnt++;
							if(drawCnt > 3){
								colGroupHtml += '<tr>'
									colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
								var th_key = '';
								var th_val = '';
								th_key = propList[j].prop_iso;
								th_val = insertsList[k][th_key];
								
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
								colGroupHtml += '</tr>';	
							}							
					}
				}
				
			colGroupHtml+= '			</tbody>'
			colGroupHtml+= '		</table>'
			colGroupHtml+= '	</div>'
			//++++++++++++++item List property 컬럼 및 vaule E
			colGroupHtml	+= '</td></tr></tbody></table></div>'
			//모바일 아이템 리스트
			$('#area_inserts_mob').append(colGroupHtml);
		}// end of inch y for
	}else {
		//어드민에서 설정한 인치사용여부가 off일때
		for(var k=0; k<insertsList.length; k++){
			//off인 경우는 인치 only 아이템은 제외하고 테이블을 그려준다.
			if(insertsList[k].UNIT_CD !='CC0002'){
				var toggleId = 'relInsert__'+k
				var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 				colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + insertsList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0024"><img src="' +  cartImgUrl + '" alt=""></a> ' + insertsList[k].MAKTX +'</p>'
				colGroupHtml +=  insertsList[k].MAKTX +'</p>'
				colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
				colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+insertsList[k].MATNR+'</th></tr>'
				colGroupHtml += '<tr>'
				colGroupHtml += '	<td>'
				colGroupHtml+= '		<div class="mdTable" id="">'
				colGroupHtml+= '			<table>'
				colGroupHtml+= '				<colgroup>'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '					<col width="*">'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '				</colgroup>'
				colGroupHtml+= '				<tbody>'
				colGroupHtml+= '					<tr>'
				//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
				drawCnt = 0;
				//프로퍼티 3개까지는 따로 셋팅
				for(var j=0; j<propList.length; j++){
					if(propList[j].disp_mb_yn == 'Y'){
						if(drawCnt < 3){
							colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';	
							drawCnt++;
						}
					}
				}	
				colGroupHtml+= '			</tr>'
				drawCnt = 0;
				for(var j=0; j<propList.length; j++){
					var th_key = '';
					var th_val = '';		
					if(propList[j].disp_mb_yn == 'Y'){
						if(drawCnt < 3){
							th_key = propList[j].prop_iso;
							th_val = insertsList[k][th_key];
							
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
							// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
							drawCnt++;
						}								
					}				
				} // end of for
				colGroupHtml+= '</tr></tbody></table>'
				colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
				drawCnt =0;
				//3개 이후
				for(var j=0; j<propList.length; j++){
					if(propList[j].disp_mb_yn == 'Y'){
						drawCnt++;
						if(drawCnt > 3){
							colGroupHtml += '<tr>'
								colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
							var th_key = '';
							var th_val = '';
							th_key = propList[j].prop_iso;
							th_val = insertsList[k][th_key];
							
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
							// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
							colGroupHtml += '</tr>';	
						}							
					}
				}
				colGroupHtml += '			</tbody>'
				colGroupHtml += '		</table>'
				colGroupHtml += '	</div>'
				//++++++++++++++item List property 컬럼 및 vaule E
				colGroupHtml	+= '</td></tr></tbody></table></div>'
				//모바일 아이템 리스트
				$('#area_inserts_mob').append(colGroupHtml);
			}
		}
	}
 }
 
//Related Holders  목록 조회
 function getRelatedHolders(){
 	var param = {
 			actionID:"<%=AppConstKey.ACTION_RELATED_HOLDERS_LIST%>"
			,search_matnr: $('#it_matnr').val()
			,search_unit_cd: $("#btnSizeUnit").val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
        	//console.log("[Related Holders] = " + JSON.stringify(e.insertsList));
        	var resultHoldersList = e.holdersList;
        	var propList = e.prop_list;
        	if(resultHoldersList.length > 0){
        		createRelatedHoldersTable(resultHoldersList, propList);	
        		createRelatedHoldersTableMobile(resultHoldersList, propList);	
        	}
        },
        complete:function(){
            $('#loadbar').fadeOut();
        },
        error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
 }
 
//Related Holders 테이블 그리기
 function createRelatedHoldersTable(holdersList, propList){
	 $("#table_relHolders *").remove();
	 
	// <colgroup> 태그 START
	var colGroupHtml = '<colgroup><col width=""><col width=""><col width=""><col width=""><col width=""><col width=""><col width="">';		
	colGroupHtml += '</colgroup>';
	// <colgroup> 태그 END
    <%-- Cart --%>
    <%-- Order Number --%>
    <%-- Designation --%>
    <%-- Grade --%>
    colGroupHtml += '<thead><tr><th class="tdCart" data-lang="FR000040">' + LANG_SET["FR000040"] + '</th><th class="tdOrd" data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th class="tdDegi" data-lang="FR000039">' + LANG_SET["FR000039"] + '</th></tr>'
	
	//어드민에서 인치사용여부 on할 경우
	if(inchYn == 'Y'){
		for(var k=0; k<holdersList.length; k++){ 		
			var rowHtml = '<tr>';
			var matnr = "'"+holdersList[k].MATNR +"'";
			//로그 저장를 위해 필요한 이벤트 코드값 - 홀더
			var evtCd = "'EC0027'"
			rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + holdersList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0028"><img src="' +  cartImgUrl + '" alt=""></a></td><td style="cursor:pointer;" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + holdersList[k].MATNR + '</a></td>';  // Default :  Order Number 
			rowHtml += '<td class="left"style="cursor:pointer;" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + holdersList[k].MAKTX + '</a></td></tr>';  
			colGroupHtml += rowHtml; 
		}// end of holerList for -> matnr
	}else{
	//어드민에서 인치사용여부 off할 경우
		for(var k=0; k<holdersList.length; k++){ 
			if(holdersList[k].UNIT_CD !='CC0002'){
				var rowHtml = '<tr>';
				var matnr = "'"+holdersList[k].MATNR +"'";
				//로그 저장를 위해 필요한 이벤트 코드값 - 홀더
				var evtCd = "'EC0027'"
				rowHtml += '<td style="cursor:pointer;"><a onclick="javascript:goItemCart(this);" data-matnr="' + holdersList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0028"><img src="' +  cartImgUrl + '" alt=""></a></td><td style="cursor:pointer;" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + holdersList[k].MATNR + '</a></td>';  // Default :  Order Number 
				rowHtml += '<td class="left"style="cursor:pointer;" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + holdersList[k].MAKTX + '</a></td></tr>';  
				colGroupHtml += rowHtml; 
			}
		}// end of holerList for -> matnr
	}

	colGroupHtml += '</thead><tbody><tr>'

	for(var j=0; j<propList.length; j++){
		var th_key = '';
		if(propList[j].prop_iso){
               colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
		}				
	}
	colGroupHtml += '</tr>'
	//어드민에서 인치사용 on한 경유
	if(inchYn =='Y'){
		for(var k=0; k<holdersList.length; k++){ 				
			colGroupHtml += '<tr>';  
			for(var j=0; j<propList.length; j++){
				var th_key = '';
				var th_val = '';		
				
				//if(item_prop_type == 'I'){
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
				        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
	                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                    	
							var calVal = isoCalMap.get(propList[j].prop_iso);
							
							if(typeof calVal !== "undefined"){
								var unit = calVal.split('$$')[0]; //연산자							
								if(unit != ""){
			                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
			                    }
							}
	                    }
	                    colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';
					}				
			}//end of inner for
			colGroupHtml += '</tr>';
		}//end of first for
	}else{
	//어드민에서 인치사용 off한 경우
		for(var k=0; k<holdersList.length; k++){
			if(holdersList[k].UNIT_CD !='CC0002'){
				colGroupHtml += '<tr>';  
				for(var j=0; j<propList.length; j++){
					var th_key = '';
					var th_val = '';		
					
					//if(item_prop_type == 'I'){
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
					        // Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
		                    }else if(cur_size_unit == "CC0001" && $.isNumeric(th_val)){                    	
								var calVal = isoCalMap.get(propList[j].prop_iso);
								
								if(typeof calVal !== "undefined"){
									var unit = calVal.split('$$')[0]; //연산자							
									if(unit != ""){
				                    	th_val = Number((Number(th_val) * 1).toFixed(4));                    	
				                    }
								}
		                    }
		                    colGroupHtml += '<td class="center" style="height:30px;">' + th_val + '</td>';
						}				
				}//end of inner for
				colGroupHtml += '</tr>';
			}
		}//end of first for
	}
	colGroupHtml += '</tbody>'
		
	//console.log("colGroupHtml = " + colGroupHtml)
	$('#table_relHolders').append(colGroupHtml);	

 }
//Related Holders 테이블 그리기 - 모바일
function createRelatedHoldersTableMobile(holdersList, propList){
	 $("#area_holders_mob").children().remove();
	 var drawCnt=0;
		//어드민에서 설정한 인치사용여부가 on일 때
		if(inchYn == 'Y'){
			for(var k=0; k<holdersList.length; k++){
				var toggleId = 'relHolder_'+k
				var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 				colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + holdersList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0028"><img src="' +  cartImgUrl + '" alt=""></a> ' + holdersList[k].MAKTX +'</p>'
				colGroupHtml +=  holdersList[k].MAKTX +'</p>'
				colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
				colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+holdersList[k].MATNR+'</th></tr>'
				colGroupHtml += '<tr>'
				colGroupHtml += '	<td>'
				colGroupHtml+= '		<div class="mdTable" id="">'
				colGroupHtml+= '			<table>'
				colGroupHtml+= '				<colgroup>'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '					<col width="*">'
				colGroupHtml+= '					<col width="32%">'
				colGroupHtml+= '				</colgroup>'
				colGroupHtml+= '				<tbody>'
				colGroupHtml+= '					<tr>'
				//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
				//프로퍼티 3개까지는 따로 셋팅
				for(var j=0; j<propList.length; j++){
					if(propList[j].disp_mb_yn == 'Y'){
						if(drawCnt < 3){
							colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';	
							drawCnt++;
						}
					}
				}//end of for property head
				colGroupHtml+= '			</tr>'
				drawCnt = 0;
		 			for(var j=0; j<propList.length; j++){
						var th_key = '';
						var th_val = '';		
						
						if(propList[j].disp_mb_yn == 'Y'){
							if(drawCnt < 3){
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
						}
					} // end of for
				colGroupHtml+= '</tr></tbody></table>'
				colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
				drawCnt = 0;
				//3개 이후
					for(var j=0; j<propList.length; j++){
						if(propList[j].disp_mb_yn == 'Y'){
								drawCnt++;
								if(drawCnt > 3){
									colGroupHtml += '<tr>'
										colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
									var th_key = '';
									var th_val = '';
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
									// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
									colGroupHtml += '</tr>';	
								}							
						}
					}
					
				colGroupHtml+= '			</tbody>'
				colGroupHtml+= '		</table>'
				colGroupHtml+= '	</div>'
				//++++++++++++++item List property 컬럼 및 vaule E
				colGroupHtml	+= '</td></tr></tbody></table></div>'
				//모바일 아이템 리스트
				$('#area_holders_mob').append(colGroupHtml);
			}// end of inch y for
		}else {
			//어드민에서 설정한 인치사용여부가 off일때
			for(var k=0; k<holdersList.length; k++){
				//off인 경우는 인치 only 아이템은 제외하고 테이블을 그려준다.
				if(holdersList[k].UNIT_CD !='CC0002'){
					var toggleId = 'relHolder__'+k
					var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 					colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + holdersList[k].MATNR + '" class="bnt_cart" data-evtcd="EC0028"><img src="' +  cartImgUrl + '" alt=""></a> ' + holdersList[k].MAKTX +'</p>'
					colGroupHtml +=  holdersList[k].MAKTX +'</p>'
					colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
					colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+holdersList[k].MATNR+'</th></tr>'
					colGroupHtml += '<tr>'
					colGroupHtml += '	<td>'
					colGroupHtml+= '		<div class="mdTable" id="">'
					colGroupHtml+= '			<table>'
					colGroupHtml+= '				<colgroup>'
					colGroupHtml+= '					<col width="32%">'
					colGroupHtml+= '					<col width="*">'
					colGroupHtml+= '					<col width="32%">'
					colGroupHtml+= '				</colgroup>'
					colGroupHtml+= '				<tbody>'
					colGroupHtml+= '					<tr>'
					//++++++++++++++item List property 컬럼 및 vaule 셋팅 S
					drawCnt = 0;
					//프로퍼티 3개까지는 따로 셋팅
					for(var j=0; j<propList.length; j++){
						if(propList[j].disp_mb_yn == 'Y'){
							if(drawCnt < 3){
								colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';	
								drawCnt++;
							}
						}
					}	
					colGroupHtml+= '			</tr>'
					drawCnt = 0;
					for(var j=0; j<propList.length; j++){
						var th_key = '';
						var th_val = '';		
						if(propList[j].disp_mb_yn == 'Y'){
							if(drawCnt < 3){
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
								drawCnt++;
							}								
						}				
					} // end of for
					colGroupHtml+= '</tr></tbody></table>'
					colGroupHtml+= '<table class="mar10"><colgroup><col width="*"><col width="*"></colgroup><tbody>'
					drawCnt =0;
					//3개 이후
					for(var j=0; j<propList.length; j++){
						if(propList[j].disp_mb_yn == 'Y'){
							drawCnt++;
							if(drawCnt > 3){
								colGroupHtml += '<tr>'
									colGroupHtml += '<th title="'+LANG_SET[propList[j].prop_iso]+'" data-lang="'+propList[j].prop_iso+'" data-langtype="title">' +  propList[j].prop_iso + '</th>';
								var th_key = '';
								var th_val = '';
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
								// Metric 이고, 값이 숫자이고 cal_unit에 값이 있으면 *1 
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
								colGroupHtml += '</tr>';	
							}							
						}
					}
					colGroupHtml += '			</tbody>'
					colGroupHtml += '		</table>'
					colGroupHtml += '	</div>'
					//++++++++++++++item List property 컬럼 및 vaule E
					colGroupHtml	+= '</td></tr></tbody></table></div>'
					//모바일 아이템 리스트
					$('#area_holders_mob').append(colGroupHtml);
				}
			}
		}

 } 
//Recommended Cutting Speeds  목록 조회
 function getCuttingSpeeds(){
 	var param = {
 			actionID:"<%=AppConstKey.ACTION_RECOMMENDED_CUTTING_SPEEDS_LIST%>"
			,search_matnr: $('#it_matnr').val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       async: true,
       cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {
        	//console.log("[Cutting Speeds] = " + JSON.stringify(e.cuttingSpeedList));
        	
        	var resultCuttingSpeedList = e.cuttingSpeedList;
        	createCuttingSpeedTable(resultCuttingSpeedList);
        	
        },
        complete:function(){
            $('#loadbar').fadeOut();
        },
        error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
     });
 }
 
 //Recommended Cutting Speeds 테이블 그리기
 function createCuttingSpeedTable(cuttingSpeedList){
	 	 
	 $("#table_cuttingSpeeds *").remove();
		
		if(cuttingSpeedList.length > 0){
			// <colgroup> 태그 START
			var colGroupHtml = '<colgroup><col width="10%"><col width="15%"><col width="15%"><col width="*"><col width="20%"></colgroup>';				
			colGroupHtml += '';
			// <colgroup> 태그 END			
			
			// <tr><th> 태그 START
			<%-- ISO --%>
			<%-- Symbol --%>
			<%-- mm --%>
			<%-- Print MM --%>
			<%-- Grade --%>
			colGroupHtml += '<tbody><tr><th data-lang="FR000122">' + LANG_SET["FR000122"] + '</th><th data-lang="FR000123">' + LANG_SET["FR000123"] + '</th><th data-lang="FR000058">' + LANG_SET["FR000058"] + '</th><th data-lang="FR000232">' + LANG_SET["FR000232"] + '</th><th data-lang="FR000041">' + LANG_SET["FR000041"] + '</th>';
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
				
			$('#table_cuttingSpeeds').append(colGroupHtml);	
		}	
	 
 }
 
//Spare Parts  목록 조회
 function getSpareParts(){
 	var param = {
 			actionID:"<%=AppConstKey.ACTION_SPARE_PARTS_LIST%>"
			,search_matnr: $('#it_matnr').val()
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       async: true,
       cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
//         	console.log("[Spare Parts] = " + JSON.stringify(e.sparePartsList));
        	
        	var resultSparePartsList = e.sparePartsList;
        	createSparePartsTable(resultSparePartsList);        	
        	createSparePartsTableMobile(resultSparePartsList);        	
        },
        complete:function(){
            $('#loadbar').fadeOut();
        },
        error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

//Spare Parts 테이블 그리기
function createSparePartsTable(sparePartsList){
 	 
 $("#table_spareParts *").remove();
	if(sparePartsList.length > 0){
		// <colgroup> 태그 START
		var colGroupHtml = '<colgroup><col width="80px"><col width="120px"><col width="200px"><col width="80px"><col width="80px"><col width="*"></colgroup>';				
		colGroupHtml += '';
		// <colgroup> 태그 END			
		
		// <tr><th> 태그 START
		<%-- Cart --%>
		<%-- Order Number --%>
		<%-- Designation --%>
		<%-- Quantity --%>
		<%-- Image --%>
		colGroupHtml += '<tbody><tr><th data-lang="FR000040">' + LANG_SET["FR000040"] + '</th><th data-lang="FR000038">' + LANG_SET["FR000038"] + '</th><th data-lang="FR000033">' + LANG_SET["FR000033"] + '</th><th data-lang="FR000058">' + LANG_SET["FR000058"] + '</th><th data-lang="FR000059">' + LANG_SET["FR000059"] + '</th><th data-lang="FR000032">' + LANG_SET["FR000032"] + '</th>';
		// <tr><th> 태그 END	
		//로그 저장를 위해 필요한 이벤트 코드값 - 스페어파트
		var evtCd = "'EC0031'"	
		// sparePartsList<tr><td> 태그 START
		//어드민에서 인치사용 on일때
		if(inchYn =='Y'){
		 	for(var k=0; k<sparePartsList.length; k++){ 
		 		if(sparePartsList[k].bom_matnr !=''){
					var rowHtml = '<tr>';
					var matnr = "'" +sparePartsList[k].bom_matnr +"'";
					rowHtml += '<td><a onclick="javascript:goItemCart(this);" data-matnr="' + sparePartsList[k].bom_matnr + '" class="bnt_cart" style="cursor:pointer;" data-evtcd="EC0032"><img src="' +  cartImgUrl + '" alt=""></a></td>';  // Cart
					if(devType =='P'){
						rowHtml += '<td onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + sparePartsList[k].bom_matnr + '</a></td>';  // Order Number 
						rowHtml += '<td class="left" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + sparePartsList[k].idnrk_hyung + '</a></td>';  // Designation
					}else{
						rowHtml += '<td>' + sparePartsList[k].bom_matnr + '</td>';  // Order Number 
						rowHtml += '<td class="left">' + sparePartsList[k].idnrk_hyung + '</td>';  // Designation
					}
				
					
					rowHtml += '<td>' + sparePartsList[k].meins + '</td>';  // 단위
					rowHtml += '<td class="right">' + sparePartsList[k].menge + '</td>';  // 수량
					
					var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
					rowHtml += '<td><img src="' + imgUrl + '" alt="" style="height: 30px;" onerror="onErrorImage(this)"></td>';  // Image
					colGroupHtml += rowHtml + '</tr>'; 
		 		}
			}
		}else{
			//어드민에서 인치사용 off일떄
		 	for(var k=0; k<sparePartsList.length; k++){ 
		 		if(sparePartsList[k].unit_cd !='CC0002'){
		 			if(sparePartsList[k].bom_matnr !=''){
						var rowHtml = '<tr>';
						var matnr = "'" +sparePartsList[k].bom_matnr +"'";
						rowHtml += '<td><a onclick="javascript:goItemCart(this);" data-matnr="' + sparePartsList[k].bom_matnr + '" class="bnt_cart" style="cursor:pointer;" data-evtcd="EC0032"><img src="' +  cartImgUrl + '" alt=""></a></td>';  // Cart
						if(devType =='P'){
							rowHtml += '<td onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + sparePartsList[k].bom_matnr + '</a></td>';  // Order Number 
							rowHtml += '<td class="left" onclick="showItemDetailPop('+matnr+', '+evtCd+' )"><a>' + sparePartsList[k].idnrk_hyung + '</a></td>';  // Designation
						}else{
							rowHtml += '<td>' + sparePartsList[k].bom_matnr + '</td>';  // Order Number 
							rowHtml += '<td class="left">' + sparePartsList[k].idnrk_hyung + '</td>';  // Designation
						}
				

						rowHtml += '<td>' + sparePartsList[k].meins + '</td>';  // 단위
						rowHtml += '<td class="right">' + sparePartsList[k].menge + '</td>';  // 수량
						
						var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
						rowHtml += '<td><img src="' + imgUrl + '" alt="" style="height: 30px;" onerror="onErrorImage(this)"></td>';  // Image
						colGroupHtml += rowHtml + '</tr>'; 
		 			}
		 		}
			}
		}
	 	colGroupHtml += '</tbody>'
		// holdersList <tr><td> 태그 END
			
		$('#table_spareParts').append(colGroupHtml);
	 	
	}
}
//Spare Parts 테이블 그리기 - 모바일
function createSparePartsTableMobile(sparePartsList){
 	 
 	$("#area_spare_parts_mob").children().remove();
 	
	if(sparePartsList.length > 0){
		//어드민에서 설정한 인치사용여부가 on일 때
		if(inchYn == 'Y'){
			for(var k=0; k<sparePartsList.length; k++){
				if(sparePartsList[k].bom_matnr !=''){
					var toggleId = 'spareParts_'+k
					var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
					var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 					colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + sparePartsList[k].bom_matnr + '" class="bnt_cart" data-evtcd="EC0032"><img src="' +  cartImgUrl + '" alt=""></a> ' + sparePartsList[k].idnrk_hyung +'</p>'
					colGroupHtml +=  sparePartsList[k].idnrk_hyung +'</p>'
					colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
					colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+sparePartsList[k].bom_matnr+'</th></tr>'
					colGroupHtml += '<tr>'
					colGroupHtml += '	<td>'
					colGroupHtml+= '		<div class="mdTable">'
					colGroupHtml+= '			<table>'
					colGroupHtml+= '				<colgroup>'
					colGroupHtml+= '					<col width="32%">'
					colGroupHtml+= '					<col width="*">'
					colGroupHtml+= '					<col width="32%">'
					colGroupHtml+= '				</colgroup>'
					colGroupHtml+= '				<tbody>'
					colGroupHtml+= '					<tr>'
// 														<!-- 단위 -->
					colGroupHtml+= '						<th data-lang="FR000058">' + LANG_SET["FR000058"] + '</th>'
// 															<!-- 수량 -->
					colGroupHtml+= '						<th data-lang="FR000059">' + LANG_SET["FR000059"] + '</th>'
// 															<!-- 이미지 -->
					colGroupHtml+= '						<th data-lang="FR000032">' + LANG_SET["FR000032"] + '</th>'
					colGroupHtml+= '					</tr>'
					colGroupHtml+= '					<tr>'
					colGroupHtml+= '						<td>' + sparePartsList[k].meins + '</td>'
					colGroupHtml+= '						<td class="right"> ' + sparePartsList[k].menge + '</td>'
					colGroupHtml+= '						<td><img src="' + imgUrl + '" alt="" style="height: 30px;" onerror="onErrorImage(this)"></td>'
					colGroupHtml+= '					</tr>'
					colGroupHtml+= '				</tbody>'
					colGroupHtml+= '			</table>'
					colGroupHtml+= '		</div>'
					colGroupHtml+= '	</td></tr></tbody></table></div>'
					//모바일 스페어파트 부분
					$('#area_spare_parts_mob').append(colGroupHtml);	
				}
			}// end of inch y for
		}else {
			//어드민에서 설정한 인치사용여부가 off일때
			for(var k=0; k<sparePartsList.length; k++){
				//off인 경우는 인치 only 아이템은 제외하고 테이블을 그려준다.
				if(sparePartsList[k].UNIT_CD !='CC0002'){
					if(sparePartsList[k].bom_matnr !=''){
						var toggleId = 'spareParts_'+k
						var imgUrl = "<%=UPLOAD_ROOT_PATH %>"+sparePartsList[k].item_img;
						var colGroupHtml = '<div class="listTable blueLine noMargin noScroll"><p class="currentItem" onclick="toggleItemMobile('+"'"+toggleId+"'"+')">';
// 						colGroupHtml += '<a style="margin-right: 5px;" onclick="javascript:goItemCart(this);" data-matnr="' + sparePartsList[k].bom_matnr + '" class="bnt_cart" data-evtcd="EC0032"><img src="' +  cartImgUrl + '" alt=""></a> ' + sparePartsList[k].idnrk_hyung +'</p>'
						colGroupHtml +=  sparePartsList[k].idnrk_hyung +'</p>'
						colGroupHtml += '<table class="mobileDetailItem" id="'+toggleId+'"><colgroup><col width="100%"></colgroup><tbody>'
						colGroupHtml += '<tr><th class="mdTitle"><span data-lang="FR000038">'+LANG_SET['FR000038']+'</span><span> : </span>'+sparePartsList[k].bom_matnr+'</th></tr>'
						colGroupHtml += '<tr>'
						colGroupHtml += '	<td>'
						colGroupHtml+= '		<div class="mdTable">'
						colGroupHtml+= '			<table>'
						colGroupHtml+= '				<colgroup>'
						colGroupHtml+= '					<col width="32%">'
						colGroupHtml+= '					<col width="*">'
						colGroupHtml+= '					<col width="32%">'
						colGroupHtml+= '				</colgroup>'
						colGroupHtml+= '				<tbody>'
						colGroupHtml+= '					<tr>'
	// 														<!-- 단위 -->
						colGroupHtml+= '						<th data-lang="FR000058">' + LANG_SET["FR000058"] + '</th>'
	// 															<!-- 수량 -->
						colGroupHtml+= '						<th data-lang="FR000059">' + LANG_SET["FR000059"] + '</th>'
	// 															<!-- 이미지 -->
						colGroupHtml+= '						<th data-lang="FR000032">' + LANG_SET["FR000032"] + '</th>'
						colGroupHtml+= '					</tr>'
						colGroupHtml+= '					<tr>'
						colGroupHtml+= '						<td>' + sparePartsList[k].meins + '</td>'
						colGroupHtml+= '						<td class="right"> ' + sparePartsList[k].menge + '</td>'
						colGroupHtml+= '						<td><img src="' + imgUrl + '" alt="" style="height: 30px;"></td>'
						colGroupHtml+= '					</tr>'
						colGroupHtml+= '				</tbody>'
						colGroupHtml+= '			</table>'
						colGroupHtml+= '		</div>'
						colGroupHtml+= '	</td></tr></tbody></table></div>'
						//모바일 스페어파트 부분
						$('#area_spare_parts_mob').append(colGroupHtml);							
					}
						
				}
			}
		}
	 	
	}
}

// 3D Representation Basic 클릭
function go3dBasic(){
	$("#btn_basic").addClass("on");
	$("#btn_detail").removeClass("on");
	
	var matnr = $('#it_matnr').val();
	var designation = $('#it_designation').val();
	
	if(itStpBasicFileUrl !== ""){
		if(matnr !== "" && designation !== ""){
			get3dRendering(matnr, designation, 'B');	
		}
	}
	
	$('#div_stp_basic').show();
	$('#div_stp_detail').hide();
	
	$('#it_stp_basic_file_url').show();
	$('#it_stp_detail_file_url').hide();		 
}

//3D Representation Detailed 클릭 
function go3dDetailed(){
	$("#btn_basic").removeClass("on");
	$("#btn_detail").addClass("on");
	
	var matnr = $('#it_matnr').val();
	var designation = $('#it_designation').val();
	
	if(itStpDetailFileUrl !== ""){
		if(matnr !== "" && designation !== ""){
			get3dRendering(matnr, designation, 'D');	
		}		
	}

	$('#div_stp_basic').hide();
	$('#div_stp_detail').show();

	$('#it_stp_basic_file_url').hide();
	$('#it_stp_detail_file_url').show();
}

// 카트 팝업
function goItemCart(obj){
	var userId = USERINFO.user_id;
	var item_matnr = $(obj).data("matnr");
	var eventCode = $(obj).data("evtcd")
	 // 로그인이 되어있는경우
	 if(userId !=''){
			//로그저장 - 아이템리스트, 릴레이티드 인서트, 홀더 스페어파트 
			doLog(eventCode, item_matnr, '')
		 if(USERAUTH.isBasketOrderAuth) {
			 getCartList(item_matnr);
		 }else{
			 <%-- 권한이 없으면 승급 요청을 하세요. --%>
			 alert(LANG_SET["FR000229"]);
		 }
	 // 로그인이 안되어있는경우	 
	 }else{
		 //로그인창 오픈
		  $(".open_login").click();
		 //alert("Please try again after login.");
	 }	 
}

// Cart 목록 조회
function getCartList(item_matnr){
	var param = {
			actionID:"<%=AppConstKey.ACTION_CART_LIST%>"
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
       success: function (e) {   
       	//console.log("[Cart List] = " + JSON.stringify(e.cartList));       	
       	var cartList = e.cartList;
       	
       	$("#select_cart_list option").remove(); //장바구니 selectboc option 삭제
       	
       	$("#select_item_matnr").val(item_matnr);
       	//if(cartList.length > 0){
       		for(var i = 0; i<cartList.length; i++){
               	var option = '<option value="' + cartList[i].cart_no +'">'+cartList[i].cart_nm+'</option>';
               	$("#select_cart_list").append(option);           		
           	}
       		// Cart 목록 팝업
   	     	$(".open_cart_pop.cart_pop01").fadeIn();
       	//}       	       	
       },
       complete:function(){
           $('#loadbar').fadeOut();
       },
       error: function (request, error) {
       	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
}

// Cart 새로만들기 버튼 클릭
function newCartPop(){
    $(".open_cart_pop.cart_pop01").fadeOut();
    $(".open_cart_pop.cart_pop02").fadeIn();
}

// 장바구니 추가 Save버튼 클릭
function saveCartItem(){
	var cartNo = $("#select_cart_list").val();
	var itemMatnr = $("#select_item_matnr").val();	
	//console.log("Cart = " + cartNo + " / " + "MATNR = " + itemMatnr);
	
	if(cartNo){
		insertCartItem(cartNo, "", itemMatnr);
	}else{
		<%-- 장바구니를 선택하세요 --%>
		alert(LANG_SET["FR000128"]);
	}
}

// 신규 장바구니 추가  Save버튼 클릭
function saveNewCartItem(){
	var itemMatnr = $("#select_item_matnr").val(); //아이템번호	
	var newCartNm = $("#new_cart_name").val(); //장바구니 그룹명
	
	if(newCartNm){
		insertCartItem("", newCartNm, itemMatnr);
	}else{
	    <%-- 장바구니 그룹명을 입력하세요 --%>
		alert(LANG_SET["FR000127"]);
	}
	
}

//카트에 Item 추가
function insertCartItem(cartNo, cartNm, matnr){	
	//ajax호출
	var param = {
			actionID:"<%=AppConstKey.ACTION_CART_ADD_OK%>"
			,cart_no: cartNo
			,matnr: matnr
			,cart_nm: cartNm
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       	async: true,
       	cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
		success: function(result) {
			//console.log(result.resMsg);			
			if(result.resCode == '0'){
				<%-- 장바구니에 담았습니다 --%>
				alert(LANG_SET["FR000129"]);
			    $(".open_cart_pop.cart_pop01").fadeOut();
			    $(".open_cart_pop.cart_pop02").fadeOut();			   
			    
			    //신규장바구니 input box 데이터 삭제
			    $("#new_cart_name").val("");
			    
                $("#cartCnt_top").text(result.resCartCnt);
                $("#cartCnt_top2").text(result.resCartCnt);
                $("#cartCnt_rgt_wrap").text(result.resCartCnt);
			    // 장바구니에 담은 아이템이 상세조회중인 아이템이면 마지막 Cart No 재조회
			    if(matnr == $('#it_matnr').val()){
			    	selectItemLastCartNo();	
			    }
			}			
		}, error: function (e){
			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
}

//마지막 카트번호 조회
function selectItemLastCartNo(){	
	//ajax호출
	var param = {
			actionID:"<%=AppConstKey.ACTION_ITEM_LAST_CART_NO%>"
			,search_matnr: $('#it_matnr').val()
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       	async: true,
       	cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
		success: function(result) {
			//console.log("[LAST CART NO] == " + result);			
			if(result !== ""){				
				$("#it_cart_no").text(result);
				$("#it_m_cart_no").text(result);					
			}			
					
		}, error: function (e){
			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
}

// Assembly 팝업
function goAssembly(){
	
	var item_matnr = $("#it_matnr").val();
	//로그저장 
	doLog('EC0020', item_matnr, '')
	// TEST용!!!!!!!!!!!!!!!!!!!!!!
	// getAssemblyList(item_matnr);	
	
	if(USERAUTH.isAssemblyAuth){
	 	getAssemblyList(item_matnr);
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{			
			<%-- 권한이 없으면 승급 요청을 하세요. --%>
			alert(LANG_SET["FR000229"]);
		    //$(".open_vip").click();
		}
	}
	
}

//Assembly그룹 및 그룹내 아이템 목록 리스트 (Assembly 그룹 선택 시 체결할 수 있는 아이템인지 확인위해..)
var assemblyList;

// Assembly 목록 조회
function getAssemblyList(item_matnr){
	var param = {
			actionID:"<%=AppConstKey.ACTION_ASSEMBLY_LIST%>"
			,search_matnr: $('#it_matnr').val()
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
       success: function (e) {
    	   //console.log("[Assembly Group List] = " + JSON.stringify(e.assemblyList));
       	   //console.log("[Assembly Info] = " + JSON.stringify(e.itemAssemInfo));
       	
       	assemblyList = null;
       	assemblyList = e.assemblyList;
       	
       	$("#select_assembly_list option").remove(); //장바구니 selectboc option 삭제
       	
       	//$("#select_item_matnr").val(item_matnr);
       	//if(assemblyList.length > 0){
       		var search_assem_no = $("#search_assem_no").val();
       		for(var i = 0; i<assemblyList.length; i++){
       			var option = '<option value="' + assemblyList[i].assem_no +'">'+assemblyList[i].assem_nm+'</option>';
       			if(search_assem_no == "") {
               		$("#select_assembly_list").append(option);
       			} else {
       				// 어셈블리에서 넘어왔을시에는 해당 어셈블리만 표시한다.
       				if(assemblyList[i].assem_no == search_assem_no) {
       					$("#select_assembly_list").append(option);
       				}
       			}
           	}
           	// 어셈블리에서 넘어왔을시에는 추가 버튼을 숨긴다.
           	if(search_assem_no != "") {
           		$(".add").hide();
           	}
           	
       		// Cart 목록 팝업
   	     	$(".open_cart_pop.assembly_pop01").fadeIn();
   	     	
   	     	$("#select_item_matnr").val($("#search_assem_no").val());
   	     	
       	//}
       	      	       	
       },
       complete:function(){
           $('#loadbar').fadeOut();
       },
       error: function (request, error) {
       	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
}

// Assembly 새로만들기 버튼 클릭
function newAssemblyPop(){
    $(".open_cart_pop.assembly_pop01").fadeOut();
    $(".open_cart_pop.assembly_pop02").fadeIn();
}

// Assembly 추가 Save버튼 클릭
function saveAssemblyItem(){
	var assemNo = $("#select_assembly_list").val();
	var itemMatnr = $("#it_matnr").val();	
	//console.log("Assembly = " + assemNo + " / " + "MATNR = " + itemMatnr);
	
	if(assemNo){
		insertAssemblyItem(assemNo, "", itemMatnr);
	}else{
		<%-- Assembly 그룹을 선택하세요. --%>
		alert(LANG_SET["FR000133"]);
	}
}

// 신규 Assembly 추가  Save버튼 클릭
function saveNewAssemblyItem(){
	var itemMatnr = $("#it_matnr").val();	 //아이템 번호
	var newAssemNm = $("#new_assembly_name").val(); //Assembly 그룹명
	
	if(newAssemNm){
		insertAssemblyItem("", newAssemNm, itemMatnr);
	}else{
		<%-- Assembly 그룹명을 입력하세요. --%>
		alert(LANG_SET["FR000134"]);
	}
	
}

// Assembly 그룹에 Item 추가
function insertAssemblyItem(assemNo, assemNm, matnr){
	//ajax호출
	var param = {
			actionID:"<%=AppConstKey.ACTION_ASSEMBLY_ADD_OK%>"
			,assem_no: assemNo
			,matnr: matnr
			,assem_nm: assemNm
			,search_assem_no : $("#search_assem_no").val()
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       	async: true,
       	cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
		beforeSend:function(){
			$(".open_cart_pop.assembly_pop01").fadeOut();
           	$('#loadbar').show();
       },
		success: function(result) {
			//console.log(result.resMsg);			
			if(result.resCode == '1'){
			    $(".open_cart_pop.assembly_pop01").fadeOut();
			    $(".open_cart_pop.assembly_pop02").fadeOut();			   
			    
			    //신규장바구니 input box 데이터 삭제
			    $("#new_assembly_name").val("");
			    
			    // Assembly 페이지로 이동
			    goAssemPage();
			   
			}else if(result.resCode == '3'){
				<%-- 선택한 Assembly그룹에 해당 아이템을 담을 수 없습니다. --%>
				alert(LANG_SET["FR000222"]);
			}else{
				<%-- Add to Assembly 에 실패하였습니다. --%>
				alert(LANG_SET["FR000233"]);
			}
		}, error: function (e){
			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
}

// Assembly 페이지로 이동
function goAssemPage(){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', "<%=ConstUrl.ASSEMBLY_URL%>");
	
	//assem_no 값
	var hiddenField = document.createElement('input');
	var assem_no = $("#search_assem_no").val();
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'assem_no');
	hiddenField.setAttribute('value', assem_no);
	form.appendChild(hiddenField);
		
	document.body.appendChild(form);
	form.submit();
}
 
 
/******************************************************************************
 * 3D Rendering 팝업
 */
 function get3dRendering (viewer, title, gubun) {
   // 본화면 과 팝업의 Math.random() 값을 다르게 하기위해 각가 설정
    var url = "";
    var pop_url = "";
   if(gubun == "D") {
      url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
      pop_url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
   } else if(gubun == "B") {
      url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
      pop_url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
   }
         
   if(url != "") {
      if(gubun == "D"){
         $('#3d_rendering_detail').attr('src', url);
         $('#3d_rendering_pop').attr('src', pop_url);   
         $('#img_3d_basic').css('display', 'block');
         $('#img_3d_detail').css('display', 'none');
      }else if(gubun == "B"){
         $('#3d_rendering_basic').attr('src', url);   
         $('#3d_rendering_pop').attr('src', pop_url);   
         $('#img_3d_basic').css('display', 'none');
         $('#img_3d_detail').css('display', 'block');
      }
   }
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

// 재종 상세화면으로 이동
function goGradeInfo(grade){
	var form = document.createElement('form');
	form.setAttribute('method', 'post');
	form.setAttribute('action', "<%=ConstUrl.GRADES_GUIDE_URL%>");
	
	//grade 값
	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'grade');
	hiddenField.setAttribute('value', grade);
	form.appendChild(hiddenField);
	
	document.body.appendChild(form);
	form.submit();
}
//아이템 디테일 팝업
function showItemDetailPop(matnr, eventCode){
	//로그 저장
	doLog(eventCode, matnr, '')
//++++++++++++++++++++++++++어느 부분에서 matnr 클릭했는지 구분을 위해
	var requestFrom;
	//릴레이티드 인서트에서 클릭시 - related Inserts
	if(eventCode == 'EC0023'){
		requestFrom = 'IS'
	}else if(eventCode == 'EC0027'){
		//릴레이티드 홀더에서 클릭 시 - related holders
		requestFrom= 'HD'
	}else if(eventCode == 'EC0031'){
		//스페어 파트에서 클릭 시 - spare parts
		requestFrom= 'SP'
	}else{
		requestFrom= ''
	}
//+++++++++++++++++++++++++	
 	$('#ifr_item_detail').attr('src','<%=ConstUrl.ITEM_DETAIL_URL%>?search_ig_cd='+$("#ig_cd").val()+'&search_matnr='+matnr+'&requestFrom='+requestFrom)
 	
	$(".open_cart_pop.cart_pop04").fadeIn("fast");
    $(".open_cart_pop.cart_pop04 .in > div").hide();
    $(".pop_cont").fadeIn();
    return; 
}

//3D 팝업
function open3dPop(){
		$(".open_cart_pop.3d_pop").fadeIn("fast");
	    $(".open_cart_pop.3d_pop .in > div").hide();
	    $(".pop_cont").fadeIn();
	    return; 
}

//2D 팝업
function open2dPop(){
		$(".open_cart_pop.2d_pop").fadeIn("fast");
	    $(".open_cart_pop.2d_pop .in > div").hide();
	    $(".pop_cont").fadeIn();
	    return; 
}

//아이템그룹/아이템 이미지 팝업
function openIgImgPop(type){
	// 아이템그룹 확대
	if(type == "IG"){
		$("#pop_image").attr("src", popIgImgUrl);
	// 아이템 확대
	}else{
		$("#pop_image").attr("src", popItemImgUrl);
	}
	
	$(".open_cart_pop.ig_pop").fadeIn("fast");
    $(".open_cart_pop.ig_pop .in > div").hide();
    $(".pop_cont").fadeIn();
    return; 	
}

//도면이미지 팝업
function openIsoPop(type){
	if(type == "IG"){
		if(prop_type == "I"){ //ISO13399
			$("#pop_iso_image").attr("src", popIsoImgUrl);
		}else{ //Non-ISO
			$("#pop_iso_image").attr("src", popNonIsoImgUrl);
		}
	}else if(type == "IT"){
		if(item_prop_type == "I"){ //ISO13399
			$("#pop_iso_image").attr("src", popIsoImgUrl);
		}else{ //Non-ISO
			$("#pop_iso_image").attr("src", popNonIsoImgUrl);
		}
	}	
	
	$(".open_cart_pop.iso_pop").fadeIn("fast");
    $(".open_cart_pop.iso_pop .in > div").hide();
    $(".pop_cont").fadeIn();
    return; 	
}


//아이템그룹 정보(아이템 리스트) 취득
function getMoreInfo(){
	var param = {
			actionID:"<%=AppConstKey.ACTION_IG_MORE_INFO%>"
			,search_ig_cd: cur_ig_cd
	};
   $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.APP_ITEM_URL%>",
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function (e) {   
        	//console.log("[MORE INFO] = " + JSON.stringify(e.moreInfo))
        	
        	var resultMoreInfo = e.moreInfo; 
        	
        	if(resultMoreInfo){
        		moreInfoNmTxt = resultMoreInfo.ig_nm;
        		moreInfoTxt = resultMoreInfo.more_info;
        		$("#pop_more_info_nm").text(moreInfoNmTxt);
        		$("#pop_more_info").html(moreInfoTxt);
        		
        	}else{
    			moreInfoNmTxt = cur_ig_nm;
			    <%-- No Data --%>
				moreInfoTxt = LANG_SET["FR000119"];
			}
        },
        complete:function(){
            $('#loadbar').fadeOut();
        },
        error: function (request, error) {
        	//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}
//2d, 3d 다운로드 로그등록을 위해 추가
function doLogAtDownLoad(matnr, eventCode){
	if(eventCode !=''){
		doLog(eventCode, matnr, '')
	}
}
//fuction toggleInserts
function toggleRelatedInserts(){
	 //인서트 테이블의 정보가 없을 때 해당 부분 hide
	if($('#table_relInserts').length == 1){
		$('#bar_rel_inserts').hide()
	}else{
		$('#bar_rel_inserts').show()
		
	}
}
//fuction toggleInserts
function toggleRelatedInserts(){
	 //인서트 테이블의 정보가 없을 때 해당 부분 hide
	if($('#table_relInserts').length == 1){
		$('#bar_rel_inserts').hide()
	}else{
		$('#bar_rel_inserts').show()
		
	}
}
function toggleRelatedHolders(){
	//홀더 테이블의 정보가 없을 때 해당 부분 hide
	if($('#table_relHolders').length == 1){
		$('#bar_rel_holders').hide();
	}else{
		$('#bar_rel_holders').show();
		
	}
}
function toggleItemMobile(id/*toggle 해야할 아이템 테이블 부분*/){
		if(itemClickId == id){
			if($('#'+id).is(':visible')==true){
				itemClickId=""
				$('#'+id).slideUp();
			}else{
				$('#'+id).slideDown();
			}
		}else{
			itemClickId = id
	        $(".mobileDetailItem").not('#item_mob_bar').hide();
			$('#'+id).slideToggle();
		}	
//         return false;
}
//회색바 상단 여닫음에 따른 색상 구분 
function addRemoveOn(id){
	$("#igListBar").removeClass("on");
	var element = $('#'+id);
	if(element.hasClass('on')){
		//클래스 삭제
		element.removeClass('on');
	}else{
		//클래스 추가
		element.addClass('on');
		
	}
}
//아이템그룹의 btn 다운 클릭시 클릭한 영역이외 부분 열려있으면 닫기
$('#igListBar >.btn_area > ul > li > .btn_down').on('click', function() {
	if($('#igListBar').attr("class").includes("on")){
		if($('#area_item_sub').css('display') != 'none'){
			//아이템 상단 회색바 부분에 class on인 부분이 있을 경우 삭제    				
			$('#item_ig_box_top').removeClass('on');
			//아이템 닫기
			$('#area_item_sub').slideToggle();
		}
		if($('#area_itemList_sub').css('display') != 'none'){
			//아이템 리스트 상단 회색바 부분에 class on인 부분이 있을 경우 삭제    				
			$('#ig_box_top').removeClass('on');
			//아이템리스트 닫기
			$('#area_itemList_sub').slideToggle();
		}
	} else {
		return;
	}
})
//아이템리스트의 btn 다운 클릭시 클릭한 영역이외 부분 열려있으면 닫기
$('#area_itemList > .box_top > .btn_down').on('click', function() {
	if($('#area_itemList > .box_cont').css('display') != 'none'){
		return;
	} else {
		if($('#area_item_sub').css('display') != 'none'){
			//아이템 상단 회색바 부분에 class on인 부분이 있을 경우 삭제    				
			$('#item_ig_box_top').removeClass('on');			
			//아이템 부분을 닫는다   				
			$('#area_item_sub').slideToggle();
		}
		if($('#area_itemGroup').css('display') != 'none'){
			//아이템 그룹 부분을 닫는다   				
			$('#area_itemGroup').slideToggle();
		}
	}
})
//아이템의 btn 다운 클릭시 클릭한 영역이외 부분 열려있으면 닫기
$('#area_item > .box_top > .btn_down').on('click', function() {
	if($('#area_item > .box_cont').css('display') != 'none'){
		return;
	} else {
		//회색바 부분에 class on인 부분이 있을 경우 삭제    				
		if($('#area_itemList_sub').css('display') != 'none'){
			//아이템 리스트 상단 회색바 부분에 class on인 부분이 있을 경우 삭제    				
			$('#ig_box_top').removeClass('on');
			//아이템 리스트 부분을 닫는다   				
			$('#area_itemList_sub').slideToggle();
		}
		if($('#area_itemGroup').css('display') != 'none'){
			//아이템 그룹 부분을 닫는다   				
			$('#area_itemGroup').slideToggle();
		}
	}
})
//회색 bar 부분 클릭시 클릭한 영역 이외에 부분 열려있다면 닫기
$('#igListBar .tit, .box_top').on('click',function(e){
    var p_id = $(this).parents().attr("id");
    if(p_id == "igListBar" || p_id == "area_itemList" || p_id == "area_item") {
		//아이템 그룹 리스트일떄    	
    	if(p_id == "igListBar"){
			//아이템 부분이 열려있다면			
    		if($('#area_item_sub').css('display') != 'none'){
				//회색바 부분에 class on인 부분이 있을 경우 삭제    				
    			$('#item_ig_box_top').removeClass('on');
				//아이템 부분을 닫음
    			$('#area_item_sub').slideToggle();
    			
    		}
			//아이템 리스트 부분이 열려있다면			
    		if($('#area_itemList_sub').css('display') != 'none'){
				//회색바 부분에 class on인 부분이 있을 경우 삭제    				
    			$('#ig_box_top').removeClass('on');
				//아이템 리스트 부분을 닫음
    			$('#area_itemList_sub').slideToggle();
    		}
		//아이템 리스트일떄    	
    	}else if(p_id == "area_itemList"){
			//아이템 부분이 열려있다면			
    		if($('#area_item_sub').css('display') != 'none'){
				//회색바 부분에 class on인 부분이 있을 경우 삭제    				
    			$('#item_ig_box_top').removeClass('on');
				//아이템 부분을 닫음
    			$('#area_item_sub').slideToggle();
    		}
			//아이템 그룹리스트 부분이 열려있다면			
    		if($('#area_itemGroup').css('display') != 'none'){
				//아이템 그룹 리스트 부분을 닫음
    			$('#area_itemGroup').slideToggle();
    		}
		//아이템 일떄    	
    	}else{
			//아이템 리스트 부분이 열려있다면			
    		if($('#area_itemList_sub').css('display') != 'none'){
				//회색바 부분에 class on인 부분이 있을 경우 삭제    				
    			$('#ig_box_top').removeClass('on');
				//아이템 리스트 부분을 닫음
    			$('#area_itemList_sub').slideToggle();
    		}
			//아이템 그룹리스트 부분이 열려있다면			
    		if($('#area_itemGroup').css('display') != 'none'){
				//아이템 그룹 리스트 부분을 닫음
    			$('#area_itemGroup').slideToggle();
    		}
    	}
    } else {
    	return;
    }
})

</script>

 <!-- +++++++++++++++++++++++++++++ 아이템 레이어 팝업 표시 ! ++++++++++++++++++++++++++ -->
<div class="open_cart_pop cart_pop04" style="z-index:9999">
     <div class="in" style="max-height:100%;width: auto;">
        <a href="" class="close_pop"><img style="width:auto;" src="/img/btn_close.png" alt=""></a>
        <div class="pop_cont"  style="overflow-y:hidden; max-height:750px;width:1300px;padding:0 20px;">
        	<iframe src="" style="width:1200px; height:720px;" id="ifr_item_detail">
        	</iframe>
        </div>
    </div>
</div>

<div class="open_cart_pop 3d_pop" style="z-index:9999">
	<div class="in"  style="max-height:100%;width: auto;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="max-height: 825px;">
        	 <iframe id="3d_rendering_pop" src="" style="width:1000px; height:665px;" onerror="onErrorImage(this)">				
			 </iframe>
        </div>
     </div>
</div>

<div class="open_cart_pop 2d_pop" style="z-index:9999">
	<div class="in"  style="max-height:100%;width: auto;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="max-height: 825px;">
        	<div class="pop_img"><img id="img_pop_dxf"  src="" alt="" onerror="onErrorImage(this)" style="width:100%; height:665px;"></div>
        </div>
     </div>
</div>

<!-- 아이템그룹 이미지 팝업 -->
<div class="open_cart_pop ig_pop" style="z-index:9999">
	<div class="in" style="width: 1000px; height: 800px;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="height: 600px;">
            <div class="pop_img" style="line-height: 600px;"><img id="pop_image" onerror="onErrorImage(this)"  src="" alt="" style="max-height: 720px; vertical-align: middle;"></div>
        </div>
    </div>
</div>

<!-- 도면 이미지 팝업 -->
<div class="open_cart_pop iso_pop" style="z-index:9999">
	<div class="in" style="width: 1000px; height: 800px;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="height: 600px;">
            <div class="pop_img"><img id="pop_iso_image" onerror="onErrorImage(this)"  src="" alt="" style="max-width: 965px;max-height: 720px;"></div>
        </div>
    </div>
</div>






