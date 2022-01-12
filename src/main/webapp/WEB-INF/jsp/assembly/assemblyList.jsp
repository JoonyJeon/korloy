<%----------------------------------------------------------------------------------------
 - 파일이름	: assembly/search.jsp
 - 설    명		: Assembly
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2021.09.01    1.0      왕윤아      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8"
	errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.List"%>
<%@page import = "java.lang.String"%>
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import="com.eaction.framework.common.util.PagingUtil"%>
<%@page import="com.eaction.framework.common.util.DateTimeUtil"%>
<%@page import="com.eaction.framework.common.util.StringUtil"%>
<%@page import="com.eaction.framework.business.logic.assembly.constant.AssemblyConstUrl"%>
<%@page import="com.eaction.framework.business.logic.assembly.constant.AssemblyConstKey"%>
<%@page import="com.eaction.framework.business.logic.assembly.model.AssemblyInfo"%>
<%@page import="com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc"%>

<%
	String devType = (String) request.getAttribute("deviceType");
	String rootPath = (String) ConfigMng.getValue(IPropertyKey.UPLOAD_ROOT_PATH);
	
	// Assembly그룹에서 +버튼 누른 후, 아이템 선택 후, 다시 Assembly페이지 이동시 기존 선택한 assembly_no
	String item_assem_no = (String)request.getAttribute("item_assem_no"); 
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

<div id="container" class="cart_wrap">
<!-- GNB Navi 아래 page depth 표시 -->
    <div class="path">
        <div class="inner">
            <ul>
            	<%-- Home --%>
                <li class="home" data-lang="FR000137"><%= LangMng.LANG_D("FR000137", session_lang)%></li>
                <%-- My Assembly --%>
                <li data-lang="FR000003"><%= LangMng.LANG_D("FR000003", session_lang)%></li>
            </ul>
        </div>
    </div>
    
    <div id="contents" class="assembly">
        <div class="inner">
        	<%-- My Assembly --%>
            <h2 data-lang="FR000003"><%= LangMng.LANG_D("FR000003", session_lang)%> <span id="assemblyCnt"></span></h2>
            <div class="btn_assembly">
                <div class="right">
                     <ul>
                     	 <%-- Edit --%>
                         <li><a href="javascript:void(0);" onclick="clickUpdateButton()" class="edit" data-lang="FR000063"><%= LangMng.LANG_D("FR000063", session_lang)%></a></li>
                         <%-- Save --%>
                         <li><a href="javascript:void(0);" onclick="saveAssemblyInfo()" class="save" data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
                         <%-- Delete --%>
                         <li><a href="javascript:void(0);" onclick="deleteAssembly()" class="delete" data-lang="FR000065"><%= LangMng.LANG_D("FR000065", session_lang)%></a></li>
                         <%-- ADD --%>
                         <li><a href="javascript:void(0);" onclick="addNewRow()" class="add" data-lang="FR000247"><%= LangMng.LANG_D("FR000247", session_lang)%></a></li>
                     </ul>
                </div>
            </div>
            <div class="cart_top">
                <div class="check"> 
                    <label class="chkN">
                    	<%-- Select --%>
                        <input type="checkbox" id="selectAll" onclick="checkBoxEvent('all', '')"><span data-lang="FR000067"><%= LangMng.LANG_D("FR000067", session_lang)%></span>
                    </label>
                </div>
                <div class="btn_wrap">
                    <%-- Update Date --%>
                    <a href="javascript:location.reload();" class="update" data-lang="FR000068"><%= LangMng.LANG_D("FR000068", session_lang)%></a>
                </div>
            </div>
            
            <div id="assem_group">
            	<div class="cart_list pc" id="assem_pc">
                        <ul>
                            <li>
                                <div class="listTable04">
                                    <table>
                                        <colgroup>
                                            <col width="5%">
                                            <col width="*">
                                            <col width="18%">
                                            <col width="12%">
                                            <col width="12%">
                                        </colgroup>
                                        <tbody>
	                                         <!-- 어셈블리 그룹이 없을 때 -->
			                                <tr id="no_result_assem_pc" style="display:none;">
			                                	<%-- 생성된 어셈블리 그룹이 없습니다. 새로 추가해주세요. --%>
	                                         	<td rowspan="2" colspan="5" data-lang="FR000160"><%= LangMng.LANG_D("FR000160", session_lang)%></td>
	                                     	</tr>
                                            <tr id="assem_clone_head_pc">
                                                <td rowspan="2" class="arCenter">
                                                    <label class="chkN">
                                                        <input type="checkbox" name="check_pc" id="check_pc"><span></span>
                                                    </label>
                                                    <div class="asemStats">
														<span class="stDoing" id="st_doing" style="display:none;cursor: pointer;" onclick='javascript:cancelCreate()'></span>
														<span class="stComp" id="st_comp" style="display:none;"></span>
														<span class="stError" id="st_error" style="display:none;"></span>
													</div>
                                                </td>
                                                <td rowspan="2" class="left">
                                                    <div class="write">
                                                        <ul>
                                                            <li>
                                                            	<%-- Assembly Name --%>
                                                                <span data-lang="FR000132"><%= LangMng.LANG_D("FR000132", session_lang)%></span>
                                                                <div class="textarea">
                                                                    <textarea name="" id="assem_nm_pc" readonly></textarea> 
                                                                </div>
                                                            </li>
                                                            <li>
                                                            	<%-- Assembly Description --%>
                                                                <span data-lang="FR000141"><%= LangMng.LANG_D("FR000141", session_lang)%></span>
                                                                <div class="textarea">
                                                                    <textarea name="" id="assem_desc_pc" readonly></textarea> 
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                                <%-- File Download --%>
                                                <td data-lang="FR000043"><%= LangMng.LANG_D("FR000043", session_lang)%></td>
                                                <%-- Create Date --%>
                                                <td data-lang="FR000223"><%= LangMng.LANG_D("FR000223", session_lang)%></td>
                                                <%-- Update Date --%>
                                                <td data-lang="FR000068"><%= LangMng.LANG_D("FR000068", session_lang)%></td>
                                            </tr>
                                            <tr id="assem_clone_ctnt_pc">
                                                <td>
                                                    <div class="file">                                                    
                                                    	<%--GTC --%>
                                                        <a href="javascript:void(0);" class="gtc" id="gtc_down_pc" data-lang="FR000079"><%= LangMng.LANG_D("FR000079", session_lang)%></a>
                                                        <%--EXCEL --%>
                                                        <a class="excel" id="excel_down_pc" data-lang="FR000080"><%= LangMng.LANG_D("FR000080", session_lang)%></a>
                                                    </div>
                                                </td>
                                                <td id="crt_dt_pc"></td>
                                                <td id="upd_dt_pc"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="cart_detail"  id="assem_detail_pc">
                                    <div class="adaptive" id="adaptive_pc">
                                    	
                                    <!-- Assembly 체결 그림 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
                                    
                                    
                                    
                                    <div class="adaptive_cont" id="adaptive_cont_pc_ins">
                                    </div>
                                    
                                    
                                    </div>
                                    <div class="listTable03">
                                       <!--  <div class="error">
                                            <strong class="red">ERROR!</strong>
                                            <span class="red">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.</span>
                                            <a href="" class="close_error"><img src="<%=PATH_IMG%>/ico_error.png" alt=""></a>
                                        </div> -->
                                        <input type="hidden" id="tl_csw_arr_pc" name="tl_csw_arr_pc">
                                        <table id="assem_item_list_pc">
                                            <colgroup>
                                                <col width="12%">
                                                <col width="12%">
                                                <col width="*">
                                                <col width="*">
                                                <col width="*">
                                                <col width="*">
                                                <col width="10%">
                                            </colgroup>
                                            <tbody id="assem_item_tbody_pc">
                                                <tr id="assem_item_head_pc">
                                                	<%--NO --%>
                                                    <th data-lang="FR000144"><%= LangMng.LANG_D("FR000144", session_lang)%></th>
                                                    <%--Image --%>
                                                    <th data-lang="FR000032"><%= LangMng.LANG_D("FR000032", session_lang)%></th>
                                                    <%--Order Code --%>
                                                    <th data-lang="FR000038"><%= LangMng.LANG_D("FR000038", session_lang)%></th>
                                                    <%--Item Designation --%>
                                                    <th data-lang="FR000039"><%= LangMng.LANG_D("FR000039", session_lang)%></th>
                                                    <%--Grade --%>
                                                    <th data-lang="FR000041"><%= LangMng.LANG_D("FR000041", session_lang)%></th>
                                                    <%--수량 --%>
                                                    <th data-lang="FR000059"><%= LangMng.LANG_D("FR000059", session_lang)%></th>
                                                    <%--Action --%>
                                                    <th data-lang="FR000145"><%= LangMng.LANG_D("FR000145", session_lang)%></th>
                                                </tr>
                                            	<!-- pc 아이템 목록 결과 없음 -->
			                                    <tr id="no_result_item_pc" style="display:none;">
			                                    	<%--Action --%>
	                                             	<td colspan="6" data-lang="FR000161"><%= LangMng.LANG_D("FR000161", session_lang)%></td>
	                                         	</tr>
                                                <tr id="assem_item_ctnt_pc">
                                                    <td id="no_pc"></td>
                                                    <td>
                                                        <div class="img">
                                                            <img src="" id="img_pc" alt=""  onerror="onErrorImage(this)" >
                                                        </div>
                                                    </td>
                                                    <td id="order_cd_pc" class="viewGrade"></td>
                                                    <td id="designation_pc" class="viewGrade"></td>
                                                    <td id="grade_pc"></td>
                                                    <td id="quantity_pc"></td>
                                                    <td>
                                                        <a href="javascript:void(0);" id="del_assem_item"><img src="<%=PATH_IMG%>/ico_delete.png" alt=""></a>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="btn_group" id="btn_assem_grp_in_pc">
                                            <div class="center">
                                            	<%--Build Assembly --%>
                                                <a class="btn01 build popInsert" id="build_pc" data-lang="FR000146"><%= LangMng.LANG_D("FR000146", session_lang)%></a>
                                                <%--Cart --%>
                                                <a class="btn01 cart" id="cart_pc" data-lang="FR000040"><%= LangMng.LANG_D("FR000040", session_lang)%></a>
                                                <%--Request of Quotation --%>
                                                <%-- <a class="btn02 request" id="cart_order_pc" data-lang="FR000217"><%= LangMng.LANG_D("FR000217", session_lang)%></a> --%>
                                            </div>
                                        </div>
                                        <div class="box_two"  id="assem_build_img_pc">
                                            <div class="box">
                                                <div class="box_top">
                                                	<%--3D Representation --%>
                                                    <span data-lang="FR000049"><%= LangMng.LANG_D("FR000049", session_lang)%></span>
                                                    <div class="box_tab">
								                        <ul>
								                        	<%--Basic --%>
								                            <li><a id="btn_3d_basic_pc" class="on" style="cursor:pointer;" data-lang="FR000051"><%= LangMng.LANG_D("FR000051", session_lang)%></a></li>
								                            <%--Detailed --%>
								                            <li><a id="btn_3d_detail_pc" style="cursor:pointer;" data-lang="FR000052"><%= LangMng.LANG_D("FR000052", session_lang)%></a></li>
								                        </ul>
                                                    </div>
                                                    <%--Download --%>
								                    <a id="download_basic_file_url_pc"  class="download" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
								                    <%--Download --%>
								                    <a id="download_detail_file_url_pc"  class="download" style="cursor:pointer; display:none;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
								                    <a href="javascript:void(0);" class="btn_down" ><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
                                                </div>
                                                
                                                <!-- 3D Basic -->
								                <div class="box_cont" id="div_stp_basic_pc">
								                    <div class="img" style="height: 501px; border: 0px;">
									                    <div class="img" id="img_3d_pc" >
									                        <a onclick="" class="expand"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
									                    </div>
								                        <a onclick="open3dPop()" id="btn_3dpop_b" class="expand_3d" style="padding:0px; height:499px;">
								                        	<!-- <img onerror="onErrorImage(this)" alt="" style="max-width: 64%"> -->         	
								                        <iframe id="img_3d_basic_pc" src="" style="width:100%; height:460px;" onerror="onErrorImage(this)">				
														</iframe>
														
								                        </a>
								                    </div>
								                </div>
								                <!-- 3D Detail -->
								                <div class="box_cont" id="div_stp_detail_pc" style="display:none;">
								                    <div class="img" style="height: 501px;">
								                        <a onclick="open3dPop()" id="btn_3dpop_d" class="expand_3d" style="padding:0px; height:499px;">
								                        	<!-- <img onerror="onErrorImage(this)" alt="" style="max-width: 64%"> -->                         	
								                        <iframe id="img_3d_detail_pc" src="" style="width:100%; height:460px;">				
														</iframe>
														
								                        </a>
								                    </div>
								                </div>
								                
                                            </div>
                                            <div class="box" >
                                                <div class="box_top">
                                                    <%--2D Representation --%>
                                                    <span data-lang="FR000050"><%= LangMng.LANG_D("FR000050", session_lang)%></span>
                                                    <%--Download --%>
                                                    <a href="javascript:void(0);" class="download" id="download_dxf_file_url_pc" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
                                                    <a href="javascript:void(0);" class="btn_down" ><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
                                                </div>
                                                <div class="box_cont">
                                                    <div class="img" style="padding:0px; height:502px;">
                                                        <a onclick="open2dPop()" id="btn_2dpop" class="expand_3d"  href="javascript:void(0);" ><img id="2d_dxf_pc" alt="" onerror="onErrorImage(this)" style="padding:0px; height:499px; max-width: 70%;"></a>
                                                    </div>
                                                </div>  
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="btn_cart" id="btn_assem_grp_out_pc">
                                	<%--Open --%>
                                    <a onclick="" id="open_pc" class="open_cart" data-lang="FR000077"><%= LangMng.LANG_D("FR000077", session_lang)%></a>
                                    <%--Request of Quotation --%>
                                    <a class="proceed"  id="cart_order_pc" data-lang="FR000217"><%= LangMng.LANG_D("FR000217", session_lang)%></a>
                                </div> 
                            </li>
                        </ul>
                    </div><!--  end of cart_list pc -->
            	<div class="cart_list mobile" id="assem_mobile">
	                <ul>
	                    <li>
	                        <div class="listTable04">
	                            <table>
	                                <colgroup>
	                                    <col width="32%">
	                                    <col width="*">
	                                </colgroup>
	                                <tbody id="assem_list_tbody_mobile">
	                                	<tr id="no_result_assem_mobile" style="display:none;">
	                                		<%--생성된 어셈블리가 없습니다. 새로 추가해주세요. --%>
	                                	 	<td colspan="2" data-lang="FR000160"><%= LangMng.LANG_D("FR000160", session_lang)%></td>
	                                	</tr>
	                                    <tr id="assem_nm_desc_section_mobile">
	                                        <td colspan="2">
	                                            <div class="write">
	                                                <ul>
	                                                    <li>
	                                                        <label class="chkN">
	                                                            <input type="checkbox" id="check_mobile" name="check_mobile"><span></span>
	                                                        </label>
	                                                        <%--Assembly Name --%>
	                                                        <span data-lang="FR000132"><%= LangMng.LANG_D("FR000132", session_lang)%></span>
	                                                        <div class="textarea">
	                                                            <textarea name="" id="assem_nm_mobile" readonly></textarea> 
	                                                        </div>
	                                                    </li>
	                                                    <li>
	                                                    	<%--Assembly Description --%>
	                                                        <span data-lang="FR000141"><%= LangMng.LANG_D("FR000141", session_lang)%></span>
	                                                        <div class="textarea">
	                                                            <textarea name="" id="assem_desc_mobile" readonly></textarea> 
	                                                        </div>
	                                                    </li>
	                                                </ul>
	                                            </div>
	                                        </td>
	                                    </tr>
	                                    <tr id="assem_down_section_mobile">
	                                    	<%--File Download --%>
	                                        <td data-lang="FR000043"><%= LangMng.LANG_D("FR000043", session_lang)%></td>
	                                        <td>
	                                            <div class="file">
	                                            	<%--GTC --%>
	                                                <a href="javascript:void(0);" class="gtc" id="gtc_down_mobile" data-lang="FR000079"><%= LangMng.LANG_D("FR000079", session_lang)%></a>
	                                                <%--EXCEL --%>
	                                                <a class="excel" id="excel_down_mobile" data-lang="FR000080"><%= LangMng.LANG_D("FR000080", session_lang)%></a>
	                                            </div>
	                                        </td>
	                                    </tr>
	                                    <tr id="crt_dt_section_mobile">
	                                    	<%-- Create Date --%>
	                                        <td data-lang="FR000223"><%= LangMng.LANG_D("FR000223", session_lang)%></td>
	                                        <td id="crt_dt_mobile"></td>
	                                    </tr>
	                                    <tr id="upd_dt_section_mobile">
	                                    	<%-- Update Date --%>
	                                        <td data-lang="FR000068"><%= LangMng.LANG_D("FR000068", session_lang)%></td>
	                                        <td id="upd_dt_mobile"></td>
	                                    </tr>
	                                </tbody>
	                            </table>
	                        </div>
	                        <div class="cart_detail" id="assem_detail_mobile">

	                            <!-- item_list_moile이 append할 부모 -->
	                            <div class="listTable03" >
<!-- 	                                <div class="error">
	                                    <strong class="red">ERROR!</strong>
	                                    <span class="red">The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.</span>
	                                    <a href="" class="close_error"><img src="<%=PATH_IMG%>/ico_error.png" alt=""></a>
	                                </div> -->
        <!-- ++++++++++++++하나의 테이블이 하나의 아이템 표시 ++++++++++++++++++-->
        <!-- item 여러 개일 경우 모바일 #item_table_moible을 복제하여 item_list_mobile에 append -->
        						<div id="assem_item_list_mobile">
	                                <table id="assem_item_table_mobile">
	                                    <colgroup>
	                                        <col width="30%">
	                                        <col width="*">
	                                    </colgroup>
	                                    <tbody id="assem_item_tbody_mobile">
	                                        <tr id="no_result_item_mobile" style="display:none;">
	                                        <%-- 추가된 아이템이 없습니다. --%>
	                                            <th colspan="2" data-lang="FR000161"><%= LangMng.LANG_D("FR000161", session_lang)%></th>
	                                        </tr>
	                                        <tr>
	                                        	<%--NO --%>
	                                            <th data-lang="FR000144"><%= LangMng.LANG_D("FR000144", session_lang)%></th>
	                                            <td id="no_mobile"></td>
	                                        </tr>
	                                        <%-- 
	                                        <tr>
	                                            <th>Image</th>
	                                            <td>
	                                                <div class="img">
	                                                    <img id="item_img_mobile" src="<%=PATH_IMG%>/img_list01.png" alt="">
	                                                </div>
	                                            </td>
	                                        </tr>
	                                         --%>
	                                        <tr id="">
	                                        	<%--Order Code --%>
	                                            <th data-lang="FR000038"><%= LangMng.LANG_D("FR000038", session_lang)%></th>
	                                            <td id="order_cd_mobile"></td>
	                                        </tr>
	                                        <tr >
	                                        	<%--Item Designation --%>
	                                            	<th data-lang="FR000039"><%= LangMng.LANG_D("FR000039", session_lang)%></th>
	                                            <td id="designation_mobile"></td>
	                                        </tr>
	                                        <tr>
	                                        	<%--Grade --%>
	                                            <th data-lang="FR000041"><%= LangMng.LANG_D("FR000041", session_lang)%></th>
	                                            <td id="grade_mobile"></td>
	                                        </tr>
	                                        <tr>
	                                        	<%--수량 --%>
	                                            <th data-lang="FR000059"><%= LangMng.LANG_D("FR000059", session_lang)%></th>
	                                            <td id="quantity_mobile"></td>
	                                        </tr>
	                                        <tr >
	                                        	<%--Action --%>
	                                            <th data-lang="FR000145"><%= LangMng.LANG_D("FR000145", session_lang)%></th>
	                                            <td>
	                                                <a href="javascript:void(0);" onclick="" id="delete_item_mobile"><img src="<%=PATH_IMG%>/ico_delete.png" alt=""></a>
	                                            </td>
	                                        </tr>
	                                    </tbody>
	                                </table>
        						</div>
         						
	                                <div class="btn_group" id="btn_assem_grp_in_mobile">
	                                    <div class="center">
	                                        <!--<a href="javascript:void(0);" class="btn01 build" id="build_mobile">Build Assembly</a>
	                                        <a href="javascript:void(0);" class="btn01 cart" id="cart_mobile">Cart</a>-->
	                                        <%--Request of Quotation --%>
	                                        <%-- <a class="btn02 request" id="cart_order_mobile" data-lang="FR000217"><%= LangMng.LANG_D("FR000217", session_lang)%></a> --%>	                                        
	                                    </div>
	                                </div>
 	                                
	                                <div class="box_two" id="assem_build_img_mobile">
	                                    <div class="box">
	                                        <div class="box_top">
	                                        	<%--3D Representation --%>
	                                            <span data-lang="FR000049"><%= LangMng.LANG_D("FR000049", session_lang)%></span>
	                                            <div class="box_tab">
	                                            
							                        <ul>
							                        	<%--Basic --%>
							                            <li><a id="btn_3d_basic_mobile" class="on" style="cursor:pointer;" data-lang="FR000051"><%= LangMng.LANG_D("FR000051", session_lang)%></a></li>
							                            <%--Detailed --%>
							                            <li><a id="btn_3d_detail_mobile" style="cursor:pointer;" data-lang="FR000052"><%= LangMng.LANG_D("FR000052", session_lang)%></a></li>
							                        </ul>
	                                            </div>
	                                            
                                                <%--Download --%>
							                    <a id="download_basic_file_url_mobile"  class="download" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
							                    <%--Download --%>
							                    <a id="download_detail_file_url_mobile"  class="download" style="cursor:pointer; display:none;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
							                    <a href="javascript:void(0);" class="btn_down" ><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
	                                        </div>
	                                        
	                                        <!-- 3D Basic -->
							                <div class="box_cont" id="div_stp_basic_mobile">
							                    <div class="img" style="height: 501px; border: 0px;">
								                    <div class="img" id="img_3d_mobile" >
								                        <a onclick="" class="expand"><img alt="" onerror="onErrorImage(this)" src="" style="max-width: 63%"></a>
								                    </div>
							                        <a onclick="open3dPop()" id="btn_3dpop_b" class="expand_3d" style="padding:0px; height:499px;">
							                        <iframe id="img_3d_basic_mobile" src="" style="width:100%; height:409px;" onerror="onErrorImage(this)">				
													</iframe>
													
							                        </a>
							                    </div>
							                </div>
							                
							                <!-- 3D Detail -->
							                <div class="box_cont" id="div_stp_detail_mobile" style="display:none;">
							                    <div class="img" style="height: 501px;">
							                        <a onclick="open3dPop()" id="btn_3dpop_d" class="expand_3d" style="padding:0px; height:499px;">
							                        <iframe id="img_3d_detail_mobile" src="" style="width:100%; height:409px;">				
													</iframe>
													
							                        </a>
							                    </div>
							                </div>	                                        
	                                    </div>
	                                    
	                                    <div class="box">
	                                        <div class="box_top">
                                                <%--2D Representation --%>
	                                            <span data-lang="FR000050"><%= LangMng.LANG_D("FR000050", session_lang)%></span>
	                                            
	                                            <%--Download --%>
                                                <a href="javascript:void(0);" class="download" id="download_dxf_file_url_mobile" style="cursor:pointer;" data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></a>
                                                <a href="javascript:void(0);" class="btn_down" ><img src="<%=PATH_IMG%>/btn_down.png" alt=""></a>
	                                        </div>
	                                        <div class="box_cont">
	                                            <div class="img">
	                                                <a href="javascript:void(0);" class="expand"><img id="2d_dxf_mobile" src="" onerror="onErrorImage(this)" alt=""></a>
	                                            </div>
	                                        </div>  
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="btn_cart" id="btn_assem_grp_out_mobile">
	                        	<%--Open --%>
                                <a onclick="" id="open_mobile" class="open_cart" data-lang="FR000077"><%= LangMng.LANG_D("FR000077", session_lang)%></a>                                
								<%--Request of Quotation --%>
								<a id="cart_order_mobile" class="proceed"  data-lang="FR000217"><%= LangMng.LANG_D("FR000217", session_lang)%></a>                                
	                        </div> 
	                    </li>
	                </ul>
	            </div><!-- end of cart_list mobile -->
            </div><!-- end of assem_group -->
         
            <div class="btn_group" >
                <div class="center">
                    <a href="javascript:void(0);" onclick="getAssemblyList('more')" class="btn01 same" id="more" data-lang="FR000034"><%= LangMng.LANG_D("FR000034", session_lang)%></a>
                     <a href="javascript:void(0);" onclick="goRequest('multi')" class="btn02 request" id="request" data-lang="FR000216"><%= LangMng.LANG_D("FR000216", session_lang)%></a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 장바구니팝업 -->
<div class="open_cart_pop cart_pop01">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
        	<%-- 장바구니 그룹 --%>
            <div class="left"><span data-lang="FR000125"><%= LangMng.LANG_D("FR000125", session_lang)%></span></div>
            <div class="right">
            	<input type="hidden" id="assem_no_bf_pop" name="assem_no_bf_pop">
                <div class="select">
                	<input type="hidden" name="select_item_matnr_arr" id="select_item_matnr_arr" /> <!-- 아이템목록 -->
                	<input type="hidden" name="add_type" id="add_type" /> <!-- Cart: Cart, Cart & Quotation: REQ -->
                	  
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
   
<div class="open_cart_pop cart_pop02">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont">
            <%-- 장바구니 그룹명 --%>
            <div class="left"><span data-lang="FR000126"><%= LangMng.LANG_D("FR000126", session_lang)%></span></div>
            <div class="right">
            	<input type="hidden" id="assem_no_new_pop" name="assem_no_new_pop">
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

<!-- Item Group 페이지 이동 -->
 <form id="goIgPage" name="goIgPage" method="POST" action="<%=ConstUrl.APP_ITEM_GRP_LIST_URL%>">
	<input type="hidden" name="<%=ConstKey.ACTION_ID %>"  />
	<input type="hidden" name="ig_cd"  />
	<input type="hidden" name="matnr_list"  />
	<input type="hidden" name="sa_cd"  />
	<input type="hidden" name="search_assem_no"  />
</form>

<!-- 주문서 페이지 이동 -->
 <form id="goOrderPage" name="goOrderPage" method="POST" action="<%=ConstUrl.ORDER_URL%>">
	<input type="hidden" name="assem_no"  />
	<input type="hidden" name="cart_no"  />
</form>

<!-- 엑셀 파일 다운로드 -->
<form id="assemItemExceldownForm" name="assemItemExceldownForm" method="POST" action="<%=ConstUrl.ASSEMBLY_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.ACTION_GET_FILE%>"/>
    <input type="hidden" name="excel_file_path" id="excel_file_path" />
    <input type="hidden" name="atch_file_name" id="atch_file_name" />
</form>


<!--  Assembly 그룹목록 더보기 처리를 위해 사용 -->
<input type="hidden" id="all_startIndex" name="startIndex" value=0 />
<input type="hidden" id="all_endIndex" name="endIndex" value=20 />
<input type="hidden" id="all_tot_cnt" name="tot_cnt" value=0 />
<input type="hidden" id="all_orderSort" name="orderSort" value=0 />
<input type="hidden" id="all_pageCount" name="pageCount" value=20 />


<style>
#footer{
	position:inherit;
}

.listTable03 td.viewGrade {
    color: #0094da;
    cursor:pointer;
    text-decoration: underline;
}s
</style>

<script type="text/javascript">
//더보기 처리를 위한 변수 선언
var startIndex = 0;
var endIndex = 20;
var perCnt = 20;
var totCnt = 0; //전체 리스트 갯수
//체크박스 제어를 위해
var checkedAssem = new Array();
var devType ='<%=devType%>';
// ↙ 엑셀다운
var $assemItemExceldownForm = $('#assemItemExceldownForm')
var excel_file_path = "";
var file_name = "";
//  엑셀다운 -------------------------
jQuery(document).ready(function(){
	<%-- console.log("선택한 assembly no ==> " + '<%=item_assem_no%>'); --%>
	getAssemblyList('first');
	
});

//more button누를 때와 초기조회 다르게 처리
/* 초기 조회(first) or more로 조회(more) or save*/
function getAssemblyList(show_type){
	//배열을 바로 넘기면 에러 발생하여 string으로 처리하여서 넘김
	//서비스에서 배열로 변경 후 처리
	var formData = new FormData();
	formData.append("actionID",'<%=AssemblyConstKey.ACTION_ASSEMBLY_LIST%>');

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
		url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
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
			totCnt = result.nTotal;
			//console.log("Assembly List === > " + JSON.stringify(result));
			appendResult(show_type, result);
			
		}, error: function (e){
 			console.log(e);
		},
		complete: function(){
			$('#loadbar').fadeOut();
		}
	});
	
}

//Assembly 그룹 테이블 그리는 부분
function appendResult(show_type, result){
//more 버튼 show and hide처리를 위해 조회 후 관련 변수 값 변경
	//startIndex 값
	var st = $("input[name='startIndex']").val() *1 + perCnt
	$("input[name='startIndex']").val(st)
	// endIndex 값
	var ed = $("input[name='endIndex']").val() *1 + perCnt
	$("input[name='endIndex']").val(ed)
	//기존 데이터수 * 1 + 조회 데이터 개수 * 1
	var tt = $("input[name='tot_cnt']").val() * 1 + result.list.length * 1
	$("input[name='tot_cnt']").val(tt)
	
	// 조건에 맞는 조회 리스트의 전채 개수와 현제 조회한 데이터 개수 비교 후 more 버튼 보이고 숨기기 
	tt < result.nTotal ? $("#more").show() : $("#more").hide()
	
	if(show_type == 'first'){
	    //조회된 총 row의 개수 표시
	    $('#assemblyCnt').html("You have "+result.nTotal+" Assembly List")
	    //pc와 mobile에서 복사할 부분 제외하고 모두 삭제
	    $('.cart_list.pc').not("#assem_pc").remove();
		$('.cart_list.mobile').not("#assem_mobile").remove();
		
		$('#assem_pc').css("display", "");
		$('#assem_mobile').css("display", "");
                                             		
	} else {
		$('#assem_pc').show();
	    $('#assem_mobile').show();
	}
	
	if(result.nTotal > 0){
		//pc&모바일 공통
		$('#request').show();
		//result 갯수만큼 for문돌면서 Assembly 테이블 그리기
		for(var i =0; i<result.list.length; i++){
			
			//console.log("###### = " + JSON.stringify(result.list[i]));
			
			//pc			
			$('#assem_pc').clone().appendTo('#assem_group');
				var plus_id = result.list[i].assem_no;
				
				//복사한 요소의 id를 변경
				$('#assem_group').children().last().attr('id', 'assem_pc'+'_'+plus_id);
				//복사한 요소 id 취득
				var cartPcId = '#assem_pc'+'_'+plus_id;
				
				//id 변경  
				//기존 id에 '_'와 assem_no 추가
				//어셈블리 그룹 상단
				$(cartPcId).find('#assem_clone_head_pc').attr('id','assem_clone_head_pc'+'_'+plus_id);
				$(cartPcId).find('input[name="check_pc"]').attr('id','check_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_nm_pc').attr('id','assem_nm_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_desc_pc').attr('id','assem_desc_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_clone_ctnt_pc').attr('id','assem_clone_ctnt_pc'+'_'+plus_id);
				$(cartPcId).find('#gtc_down_pc').attr('id','gtc_down_pc'+'_'+plus_id);
				$(cartPcId).find('#excel_down_pc').attr('id','excel_down_pc'+'_'+plus_id);
				$(cartPcId).find('#crt_dt_pc').attr('id','crt_dt_pc'+'_'+plus_id);
				$(cartPcId).find('#upd_dt_pc').attr('id','upd_dt_pc'+'_'+plus_id);
				$(cartPcId).find('#no_result_assem_pc').attr('id','no_result_assem_pc'+'_'+plus_id);
				
				//어셈블리 체결 이미지 영역
				$(cartPcId).find('#adaptive_cont_pc_ins').attr('id', 'adaptive_cont_pc_ins_'+plus_id);
				
				//어셈블리 그룹 아이템 리스트 상위 부분
				$(cartPcId).find('#assem_detail_pc').attr('id','assem_detail_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_item_list_pc').attr('id','assem_item_list_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_item_tbody_pc').attr('id','assem_item_tbody_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_item_head_pc').attr('id','assem_item_head_pc'+'_'+plus_id);
				$(cartPcId).find('#assem_item_ctnt_pc').attr('id','assem_item_ctnt_pc'+'_'+plus_id);
				$(cartPcId).find('#no_result_item_pc').attr('id','no_result_item_pc'+'_'+plus_id);
				$(cartPcId).find('#tl_csw_arr_pc').attr('id','tl_csw_arr_pc'+'_'+plus_id);
				
				//어셈블리 그룹 내부 아이템 리스트 하단 버튼
				$(cartPcId).find('#btn_assem_grp_in_pc').attr('id','btn_assem_grp_in_pc'+'_'+plus_id);
				$(cartPcId).find('#build_pc').attr('id','build_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_pc').attr('id','cart_pc'+'_'+plus_id);
				$(cartPcId).find('#cart_order_pc').attr('id','cart_order_pc'+'_'+plus_id);
				
				//어셈블리 3D/2D 이미지 영역
				$(cartPcId).find('#assem_build_img_pc').attr('id','assem_build_img_pc'+'_'+plus_id);
				$(cartPcId).find('#btn_3d_basic_pc').attr('id','btn_3d_basic_pc'+'_'+plus_id);
				$(cartPcId).find('#btn_3d_detail_pc').attr('id','btn_3d_detail_pc'+'_'+plus_id);
				$(cartPcId).find('#img_3d_basic_pc').attr('id','img_3d_basic_pc'+'_'+plus_id);
				$(cartPcId).find('#img_3d_detail_pc').attr('id','img_3d_detail_pc'+'_'+plus_id);
				$(cartPcId).find('#img_3d_pc').attr('id','img_3d_pc'+'_'+plus_id);
				$(cartPcId).find('#2d_dxf_pc').attr('id','2d_dxf_pc'+'_'+plus_id);
				$(cartPcId).find('#div_stp_basic_pc').attr('id','div_stp_basic_pc'+'_'+plus_id);
				$(cartPcId).find('#div_stp_detail_pc').attr('id','div_stp_detail_pc'+'_'+plus_id);
				$(cartPcId).find('#btn_3dpop_b').attr('id','btn_3dpop_b'+'_'+plus_id);
				$(cartPcId).find('#btn_3dpop_d').attr('id','btn_3dpop_d'+'_'+plus_id);
				$(cartPcId).find('#btn_2dpop').attr('id','btn_2dpop'+'_'+plus_id);
												
				$('#btn_3d_basic_pc'+'_'+plus_id).attr('onclick', "toggleBasic("+plus_id+")");
				$('#btn_3d_detail_pc'+'_'+plus_id).attr('onclick', "toggleDetail("+plus_id+")");	
				$('#btn_3dpop_b'+'_'+plus_id).attr('onclick', "open3dPop('PB',"+plus_id+")");
				$('#btn_3dpop_d'+'_'+plus_id).attr('onclick', "open3dPop('PD',"+plus_id+")");
				$('#btn_2dpop'+'_'+plus_id).attr('onclick', "open2dPop("+plus_id+")");

				$(cartPcId).find('#download_basic_file_url_pc').attr('id','download_basic_file_url_pc'+'_'+plus_id);			
				$(cartPcId).find('#download_detail_file_url_pc').attr('id','download_detail_file_url_pc'+'_'+plus_id);
				$(cartPcId).find('#download_dxf_file_url_pc').attr('id','download_dxf_file_url_pc'+'_'+plus_id);
								
				$(cartPcId).find('#st_doing').attr('id','st_doing'+'_'+plus_id);
				$(cartPcId).find('#st_comp').attr('id','st_comp'+'_'+plus_id);
				$(cartPcId).find('#st_error').attr('id','st_error'+'_'+plus_id);
				
				$('#st_doing'+'_'+plus_id).attr('onclick', "cancelCreate('"+result.list[i].buld_st+"', "+plus_id+")");
				
				// [Mobile]
				$('#assem_mobile').css("display", "");
				$('#assem_mobile').clone().appendTo('#assem_group');
				
				//복사한 요소의 id를 변경
				$('#assem_group').children().last().attr('id', 'assem_mobile'+'_'+plus_id);
				//복사한 요소 id 취득
				var cartMobId = '#assem_mobile'+'_'+plus_id;
				
				//id 변경  
				//기존 id에 _+ctnt_img_no 추가하여 id가 겹치지 않도록 한다.
				//어셈블리 그룹 
				$(cartMobId).find('#assem_list_tbody_mobile').attr('id','assem_list_tbody_mobile'+'_'+plus_id);
				$(cartMobId).find('#no_result_assem_mobile').attr('id','no_result_cart_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_nm_mobile').attr('id','assem_nm_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_desc_mobile').attr('id','assem_desc_mobile'+'_'+plus_id);
				$(cartMobId).find('#crt_dt_mobile').attr('id','crt_dt_mobile'+'_'+plus_id);
				$(cartMobId).find('#upd_dt_mobile').attr('id','upd_dt_mobile'+'_'+plus_id);
				$(cartMobId).find('input[name="check_mobile"]').attr('id','check_mobile'+'_'+plus_id);
				
				$(cartMobId).find('#assem_nm_desc_section_mobile').attr('id','assem_nm_desc_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#gtc_down_mobile').attr('id','gtc_down_mobile'+'_'+plus_id);
				$(cartMobId).find('#excel_down_mobile').attr('id','excel_down_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_down_section_mobile').attr('id','assem_down_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#crt_dt_section_mobile').attr('id','crt_dt_section_mobile'+'_'+plus_id);
				$(cartMobId).find('#upd_dt_section_mobile').attr('id','upd_dt_section_mobile'+'_'+plus_id);
				
				//어셈블리 내부 아이템 부분
				$(cartMobId).find('#assem_detail_mobile').attr('id','assem_detail_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_item_list_mobile').attr('id','assem_item_list_mobile'+'_'+plus_id);
				$(cartMobId).find('#no_result_item_mobile').attr('id','no_result_item_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_item_tbody_mobile').attr('id','assem_item_tbody_mobile'+'_'+plus_id);
				$(cartMobId).find('#assem_item_table_mobile').attr('id','assem_item_table_mobile'+'_'+plus_id);
				//$(cartMobId).find('#btn_assem_grp_in_mobile').attr('id','btn_assem_grp_in_mobile'+'_'+plus_id);
				//$(cartMobId).find('#build_mobile').attr('id','build_mobile'+'_'+plus_id);
				//$(cartMobId).find('#cart_mobile').attr('id','cart_mobile'+'_'+plus_id);
				$(cartMobId).find('#cart_order_mobile').attr('id','cart_order_mobile'+'_'+plus_id);
				
				//3D/2D - 현재 2D만
				$(cartMobId).find('#assem_build_img_mobile').attr('id','assem_build_img_mobile'+'_'+plus_id);
				$(cartMobId).find('#btn_3d_basic_mobile').attr('id','btn_3d_basic_mobile'+'_'+plus_id);
				$(cartMobId).find('#btn_3d_detail_mobile').attr('id','btn_3d_detail_mobile'+'_'+plus_id);
				$(cartMobId).find('#img_3d_basic_mobile').attr('id','img_3d_basic_mobile'+'_'+plus_id);
				$(cartMobId).find('#img_3d_detail_mobile').attr('id','img_3d_detail_mobile'+'_'+plus_id);
				$(cartMobId).find('#img_3d_mobile').attr('id','img_3d_mobile'+'_'+plus_id);
				$(cartMobId).find('#2d_dxf_mobile').attr('id','2d_dxf_mobile'+'_'+plus_id);
				$(cartMobId).find('#div_stp_basic_mobile').attr('id','div_stp_basic_mobile'+'_'+plus_id);
				$(cartMobId).find('#div_stp_detail_mobile').attr('id','div_stp_detail_mobile'+'_'+plus_id);				
				
				$('#btn_3d_basic_mobile'+'_'+plus_id).attr('onclick', "toggleBasic("+plus_id+")");
				$('#btn_3d_detail_mobile'+'_'+plus_id).attr('onclick', "toggleDetail("+plus_id+")");

				$(cartMobId).find('#download_basic_file_url_mobile').attr('id','download_basic_file_url_mobile'+'_'+plus_id);			
				$(cartMobId).find('#download_detail_file_url_mobile').attr('id','download_detail_file_url_mobile'+'_'+plus_id);
				$(cartMobId).find('#download_dxf_file_url_mobile').attr('id','download_dxf_file_url_mobile'+'_'+plus_id);
				
				//어셈블리 외부 버튼
				$(cartMobId).find('#btn_assem_grp_out_mobile').attr('id','btn_assem_grp_out_mobile'+'_'+plus_id);
				$(cartMobId).find('#open_mobile').attr('id','open_mobile'+'_'+plus_id);
				
				//mobile 어셈블리 보여야 하는 부분
				$('#no_result_cart_mobile'+'_'+plus_id).hide();
				$('#assem_nm_desc_section_mobile'+'_'+plus_id).css("display","");
				$('#assem_down_section_mobile'+'_'+plus_id).css("display","");
				$('#assem_detail_mobile'+'_'+plus_id).css("display","");
				$('#btn_assem_grp_out_mobile'+'_'+plus_id).css("display","");
				$('#crt_dt_section_mobile'+'_'+plus_id).css("display","");
				$('#upd_dt_section_mobile'+'_'+plus_id).css("display","");
				
				//데이터 삽입 및 이벤트 추가 
				//어셈블리 그룹 내용
				$('#check_mobile_'+plus_id).attr("data-no", plus_id);
				$('#check_mobile_'+plus_id).attr("data-type", "E");
				$('#check_mobile_'+plus_id).val(plus_id);
				$('#check_mobile_'+plus_id).attr("onclick", "checkBoxEvent('part',"+plus_id+")");
				$('#assem_nm_mobile'+'_'+plus_id).html(result.list[i].assem_nm);
				$('#assem_desc_mobile'+'_'+plus_id).html(result.list[i].assem_desc);
				$('#crt_dt_mobile'+'_'+plus_id).html(result.list[i].crt_dt);
				$('#upd_dt_mobile'+'_'+plus_id).html(result.list[i].upd_dt);
				
				
				// GTC 파일 다운로드
				var itGtcFileUrl;
				if(result.list[i].gtc_file !== undefined && result.list[i].gtc_file !== ""){
					itGtcFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.list[i].gtc_file;
				}else{
					itGtcFileUrl = "";
				}
				if(itGtcFileUrl !== ""){			
					var file_nm = itGtcFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$('#gtc_down_pc'+'_'+plus_id).attr('href', itGtcFileUrl);
					$('#gtc_down_pc'+'_'+plus_id).attr('download', file_nm);				
					$('#gtc_down_mobile'+'_'+plus_id).attr('href', itGtcFileUrl);
					$('#gtc_down_mobile'+'_'+plus_id).attr('download', file_nm);				
					
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#gtc_down_pc'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					$('#gtc_down_mobile'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					
					
				}
								
				// 3D Basic 파일 다운로드
				var itStpBasicFileUrl;
				if(result.list[i].stp_b_file !== undefined && result.list[i].stp_b_file !== ""){
					itStpBasicFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.list[i].stp_b_file;
				}else{
					itStpBasicFileUrl = "";
				}
				if(itStpBasicFileUrl !== ""){				
					//stp basic 파일 다운로드
					var file_nm = itStpBasicFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					$('#download_basic_file_url_pc'+'_'+plus_id).attr('href', itStpBasicFileUrl);
					$('#download_basic_file_url_pc'+'_'+plus_id).attr('download', file_nm);
					
					$('#download_basic_file_url_mobile'+'_'+plus_id).attr('href', itStpBasicFileUrl);
					$('#download_basic_file_url_mobile'+'_'+plus_id).attr('download', file_nm);
					
					//3D이미지 영역
					$('#img_3d_pc'+'_'+plus_id).css('display', 'none');
					$('#3d_basic_pc'+'_'+plus_id).attr('src', itStpBasicFileUrl);
					$('#img_3d_mobile'+'_'+plus_id).css('display', 'none');
					$('#3d_basic_mobile'+'_'+plus_id).attr('src', itStpBasicFileUrl);					
					
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#download_basic_file_url_pc'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					$('#download_basic_file_url_mobile'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
				}
				
				// 3D Detail 파일 다운로드
				var itStpDetailFileUrl;
				//console.log(JSON.stringify(result.list[i]))
				if(result.list[i].stp_d_file !== undefined && result.list[i].stp_d_file !== ""){
					itStpDetailFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.list[i].stp_d_file;
				}else{
					itStpDetailFileUrl = "";
				}

				if(itStpDetailFileUrl !== ""){				
					//stp basic 파일 다운로드
					var file_nm = itStpDetailFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];					
					$('#download_detail_file_url_pc'+'_'+plus_id).attr('href', itStpDetailFileUrl);
					$('#download_detail_file_url_pc'+'_'+plus_id).attr('download', file_nm);		
					
					$('#download_detail_file_url_mobile'+'_'+plus_id).attr('href', itStpDetailFileUrl);
					$('#download_detail_file_url_mobile'+'_'+plus_id).attr('download', file_nm);					
					
					//3D이미지 영역
					$('#3d_detail_pc'+'_'+plus_id).attr('src', itStpDetailFileUrl);
					$('#3d_detail_mobile'+'_'+plus_id).attr('src', itStpDetailFileUrl);					
					
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#download_detail_file_url_pc'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					$('#download_detail_file_url_mobile'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');					
				}
				
				// 2D DXF 파일 다운로드
				var itDxfFileUrl;
				if(result.list[i].dxf_file !== undefined && result.list[i].dxf_file !== ""){
					itDxfFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.list[i].dxf_file;
					itDxfFileUrl = itDxfFileUrl.replace("dxf", "jpg"); 
				}else{
					itDxfFileUrl = "";
				}
				
				if(itDxfFileUrl !== ""){
					$('#2d_dxf_pc'+'_'+plus_id).attr("src", itDxfFileUrl);
					$('#2d_dxf_mobile'+'_'+plus_id).attr("src", itDxfFileUrl);					
					
					//dxf 파일 다운로드
					var file_nm = itDxfFileUrl.split('/');
					file_nm = file_nm[file_nm.length-1];								
					itDxfFileUrl = itDxfFileUrl.replace("jpg", "dxf"); 
					file_nm = file_nm.replace("jpg", "dxf"); 
					$('#download_dxf_file_url_pc'+'_'+plus_id).attr('href', itDxfFileUrl);
					$('#download_dxf_file_url_pc'+'_'+plus_id).attr('download', file_nm);	
					$('#download_dxf_file_url_mobile'+'_'+plus_id).attr('href', itDxfFileUrl);
					$('#download_dxf_file_url_mobile'+'_'+plus_id).attr('download', file_nm);	
					
				}else{
					<%-- 저장된 파일이 없습니다.--%>
					$('#download_dxf_file_url_pc'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					$('#2d_dxf_pc'+'_'+plus_id).attr("src", "");
					
					$('#download_dxf_file_url_mobile'+'_'+plus_id).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
					$('#2d_dxf_mobile'+'_'+plus_id).attr("src", "");
				}
				
				//어셈블리 하단 버튼
				$(cartPcId).find('#btn_assem_grp_out_pc').attr('id','btn_assem_grp_out_pc'+'_'+plus_id);
				$(cartPcId).find('#open_pc').attr('id','open_pc'+'_'+plus_id);
				
				//어셈블리 보여야 하는 부분
				$('#assem_clone_head_pc'+'_'+plus_id).css("display", "");
				$('#assem_clone_ctnt_pc'+'_'+plus_id).css("display", "");
				$('#btn_assem_grp_out_pc'+'_'+plus_id).css("display", "");
				$('#assem_detail_pc'+'_'+plus_id).css("display", "");
				$('#no_result_assem_pc'+'_'+plus_id).hide();
				
				//데이터 삽입 및 이벤트 추가 
				//어셈블리 그룹 내용
				$('#check_pc_'+plus_id).attr("data-no", plus_id);
				$('#check_pc_'+plus_id).attr("data-type", "E");
				$('#check_pc_'+plus_id).val(plus_id);
				$('#check_pc_'+plus_id).attr("onclick", "checkBoxEvent('part',"+plus_id+")");
				$('#assem_nm_pc'+'_'+plus_id).html(result.list[i].assem_nm);
				$('#assem_desc_pc'+'_'+plus_id).html(result.list[i].assem_desc);
				$('#crt_dt_pc'+'_'+plus_id).html(result.list[i].crt_dt);
				$('#upd_dt_pc'+'_'+plus_id).html(result.list[i].upd_dt);
				
				//파일 다운로드 이벤트
				//$('#gtc_down_pc'+'_'+plus_id).attr('onclick', "gtcDownload("+plus_id+")");
				$('#excel_down_pc'+'_'+plus_id).attr('onclick', "excelDownload("+plus_id+")");				
				$('#excel_down_mobile'+'_'+plus_id).attr('onclick', "excelDownload("+plus_id+")");
				
				//어셈블리 그룹 하단 open 버튼 이벤트
				$('#open_pc'+'_'+plus_id).attr('onclick', "onAndOffClass("+plus_id+", '"+result.list[i].buld_sta+"')");
				$('#open_mobile'+'_'+plus_id).attr('onclick', "onAndOffClass("+plus_id+")");
				$('#cart_order_pc'+'_'+plus_id).attr('onclick',  "goRequest('single', '"+result.list[i].assem_no+ "')");
				$('#check_pc'+'_'+result.list[i].assem_no).attr("data-itemcnt", result.list[i].item_cnt); //어셈블리그룹 내 아이템 갯수
				$('#cart_order_mobile'+'_'+plus_id).attr('onclick',  "goRequest('single', '"+result.list[i].assem_no+ "')");
				$('#check_mobile'+'_'+result.list[i].assem_no).attr("data-itemcnt", result.list[i].item_cnt); //어셈블리그룹 내 아이템 갯수
				
				// 생성 상태
				if(result.list[i].buld_sta == 'ING') {
					$("#st_doing_"+plus_id).show();
				} else if(result.list[i].buld_sta == 'OK') {
					$("#st_comp_"+plus_id).show();
				} else if(result.list[i].buld_sta == 'FAIL') {
					$("#st_error_"+plus_id).show();
				} 
								
				//어셈블리 내부 버튼 이벤트
				//$('#build_pc'+'_'+plus_id).attr('onclick', "buildAssembly("+plus_id+")");			
				
				
/* 				//#########################################################
				//카트 클릭시 matnr 번호 넘기기
				var item_matnr_arr = "";
				var item_assy_type_arr = "";
				alert(result.list[i].assembly_detail_list.length)
				if(result.list[i].assembly_detail_list.length > 0){
					for(var k=0; k<result.list[i].assembly_detail_list.length; k++){
						item_matnr_arr += result.list[i].assembly_detail_list[k].matnr + ","; 
						item_assy_type_arr += result.list[i].assembly_detail_list[k].assy_type + ","; 
					}
				}
				//#########################################################
				
				$('#cart_pc'+'_'+plus_id).attr('onclick',  "insertToCart("+plus_id+ ",'" + item_matnr_arr +"')");					
				$('#build_pc'+'_'+plus_id).attr('onclick', "buildAssembly("+plus_id+ ",'" + item_matnr_arr +"','" + item_assy_type_arr +"')");					
				$('#cart_order_pc'+'_'+plus_id).attr('onclick',  "goRequest('single', '"+plus_id+ "')");	 */
// 				$('#cart_order_pc'+'_'+plus_id).attr('onclick',  "javascript:alert('작업중')");	
				
				//아이템 리스트 테이블 삽입 pc
				//openDetail
				//appendItemListTable(plus_id ,result.list[i], 'first');
				
		}
		  
	}else{
		//생성된 Assembly가 하나도 없을 때
		//pc
		$('#assem_clone_head_pc').hide();
		$('#assem_clone_ctnt_pc').hide();
		$('#btn_assem_grp_out_pc').hide();
		$('#assem_detail_pc').hide();
		$('#no_result_assem_pc').show();
		
		//mobile
		$('#no_result_assem_mobile').show();
		$('#assem_nm_desc_section_mobile').hide();
		$('#assem_down_section_mobile').hide();
		$('#crt_dt_section_mobile').hide();
		$('#upd_dt_section_mobile').hide();
		$('#assem_detail_mobile').hide();
		$('#btn_assem_grp_out_mobile').hide();
		
		//pc&mobile 공통
		$('#request').hide();
	}
	
	$('#assem_pc').hide();
    $('#assem_mobile').hide();
    
}

//3D Basic 버튼 클릭
function toggleBasic(assem_no){
	//PC
	$('#btn_3d_basic_pc_' + assem_no).addClass('on');
	$('#btn_3d_detail_pc_' + assem_no).removeClass();

	$('#div_stp_basic_pc_' + assem_no).show();
	$('#div_stp_detail_pc_' + assem_no).hide();
	
	$('#download_basic_file_url_pc_' + assem_no).show();
	$('#download_detail_file_url_pc_' + assem_no).hide();
	
	//var open = $('#img_3d_basic_pc_'+assem_no).attr('src');
	//if(open == "") {
	get3dRendering ($("#download_basic_file_url_pc_"+assem_no).attr("download"), "B", assem_no);
	//}
	
	//Mobile
	$('#btn_3d_basic_mobile_' + assem_no).addClass('on');
	$('#btn_3d_detail_mobile_' + assem_no).removeClass();

	$('#div_stp_basic_mobile_' + assem_no).show();
	$('#div_stp_detail_mobile_' + assem_no).hide();
	
	$('#download_basic_file_url_mobile_' + assem_no).show();
	$('#download_detail_file_url_mobile_' + assem_no).hide();
	
	//var open = $('#img_3d_basic_mobile_'+assem_no).attr('src');
	//if(open == "") {
	get3dRendering ($("#download_basic_file_url_mobile_"+assem_no).attr("download"), "B", assem_no);
	//}
	
}

//3D Detail 버튼 클릭
function toggleDetail(assem_no){
	//PC
	$('#btn_3d_basic_pc_' + assem_no).removeClass();
	$('#btn_3d_detail_pc_' + assem_no).addClass('on');

	$('#div_stp_basic_pc_' + assem_no).hide();
	$('#div_stp_detail_pc_' + assem_no).show();
	
	$('#download_basic_file_url_pc_' + assem_no).hide();
	$('#download_detail_file_url_pc_' + assem_no).show();
	
	var open = $('#img_3d_detail_pc_'+assem_no).attr('src');
	
	//if(open == "") {
		get3dRendering ($("#download_detail_file_url_pc_"+assem_no).attr("download"), "D", assem_no);	
	//}
	
	//Mobile
	$('#btn_3d_basic_mobile_' + assem_no).removeClass();
	$('#btn_3d_detail_mobile_' + assem_no).addClass('on');

	$('#div_stp_basic_mobile_' + assem_no).hide();
	$('#div_stp_detail_mobile_' + assem_no).show();
	
	$('#download_basic_file_url_mobile_' + assem_no).hide();
	$('#download_detail_file_url_mobile_' + assem_no).show();
	
	var open = $('#img_3d_detail_mobile_'+assem_no).attr('src');
	
	//if(open == "") {
		get3dRendering ($("#download_detail_file_url_mobile_"+assem_no).attr("download"), "D", assem_no);	
	//}
}

//3D Rendering
function get3dRendering (viewer, gubun, assem_no) {
	if(viewer != undefined) {
		viewer = viewer.replace("_basic.stp", "");	
		viewer = viewer.replace("_detail.stp", "");
		
		var url = "";
		if(gubun == "D") {
			url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
			$('#img_3d_detail_pc_'+assem_no).attr('src', url);
			$('#img_3d_detail_mobile_'+assem_no).attr('src', url);		
		} else if(gubun == "B") {
			url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
			$('#img_3d_basic_pc_'+assem_no).attr('src', url);
			$('#img_3d_basic_mobile_'+assem_no).attr('src', url);
		} else if(gubun == "PB") {
			url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
			$('#3d_rendering_pop').attr('src', url);
		} else if(gubun == "PD") {
			url = "/3d_viewer/viewer.jsp?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
			$('#3d_rendering_pop').attr('src', url);
		}
	}
}


//새로 어셈블리 그룹 추가시
function addNewRow(){
	//alert("새로운 어셈블리 그룹 화면상에서 추가")
	var cloneItem ;
	//새로운 어셈블리 추가시 임시로 현재시간을 id로 붙여준다
	var plus_id = (new Date().getTime()).toString();
	//체크박스 관리하는 배열에 추가
	checkedAssem.push(plus_id);
	
	//devType에  따라 나눠서 처리해주고 save 시 재조회
	if(devType =='M' ){
		$('#assem_mobile').show() ;
		cloneItem = $('#assem_mobile').clone(); 
		$('#assem_mobile').hide() 
	}else{
		$('#assem_pc').show();		
		cloneItem =  $('#assem_pc').clone();  
		$('#assem_pc').hide();		
	}
	
	$('#assem_group').prepend(cloneItem);
	//아이디 겹치지 않게 변경
	//복사한 요소의 id를 변경
	if(devType =='M'){
		
		$('#assem_group').children().first().attr('id', 'assem_mobile'+'_'+plus_id) 
		var cartMobId = '#assem_mobile_'+plus_id
		//id 변경  
		//기존 id에 '_'와 assem_no 추가
		//상단
		$(cartMobId).find('#assem_list_tbody_mobile').attr('id','assem_list_tbody_mobile'+'_'+plus_id);
		$(cartMobId).find('#assem_detail_mobile').attr('id','assem_detail_mobile'+'_'+plus_id);
		$(cartMobId).find('#no_result_assem_mobile').attr('id','no_result_assem_mobile'+'_'+plus_id);
		$(cartMobId).find('#assem_nm_desc_section_mobile').attr('id','assem_nm_desc_section_mobile'+'_'+plus_id);
		$(cartMobId).find('input[name="check_mobile"]').attr('id','check_mobile'+'_'+plus_id);
		$(cartMobId).find('#cart_detail_list_mobile').attr('id','cart_detail_list_mobile'+'_'+plus_id);
		$(cartMobId).find('#assem_down_section_mobile').attr('id','assem_down_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#crt_dt_section_mobile').attr('id','crt_dt_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#upd_dt_section_mobile').attr('id','upd_dt_section_mobile'+'_'+plus_id);
		$(cartMobId).find('#assem_item_list_mobile').attr('id','assem_item_list_mobile'+'_'+plus_id);
		$(cartMobId).find('#btn_assem_grp_out_pc').attr('id','btn_assem_grp_out_pc'+'_'+plus_id);
		$(cartMobId).find('#open_mobile').attr('id','open_mobile'+'_'+plus_id);
		
		$(cartMobId).find('#assem_nm_mobile').attr('id','assem_nm_mobile'+'_'+plus_id);
		$(cartMobId).find('#assem_desc_mobile').attr('id','assem_desc_mobile'+'_'+plus_id);
		
		$(cartMobId).find('#2d_dxf_mobile').attr('id','2d_dxf_mobile'+'_'+plus_id);
		
		
		$('#check_mobile_'+plus_id).prop("checked", true);
		$('#check_mobile_'+plus_id).val("new");
		$('#check_mobile_'+plus_id).attr("data-type",'A');
		$('#check_mobile_'+plus_id).attr("onclick","checkBoxEvent('part',"+plus_id+")");
		
		//보여줘야할 부분 숨길부분 구분하여 처리
		//mobile
		$('#no_result_assem_mobile'+'_'+plus_id).hide();
		$('#assem_nm_desc_section_mobile'+'_'+plus_id).show();
		$('#assem_down_section_mobile'+'_'+plus_id).hide();
		$('#crt_dt_section_mobile'+'_'+plus_id).hide();
		$('#upd_dt_section_mobile'+'_'+plus_id).hide();
		$('#assem_detail_mobile'+'_'+plus_id).hide();
		$('#btn_assem_grp_out_mobile'+'_'+plus_id).hide();
		
		$('#btn_cart_group_mobile_'+plus_id).hide();
		$('#assem_detail_mobile_'+plus_id).hide();
		$(cartMobId).find('.file').hide();
		
	}else{
		$('#assem_group').children().first().attr('id', 'assem_pc'+'_'+plus_id);
		var cartPcId = '#assem_pc_'+plus_id
		
		//id 변경  
		//기존 id에 '_'와 assem_no 추가
		$(cartPcId).find('#assem_clone_head_pc').attr('id','assem_clone_head_pc'+'_'+plus_id);
		$(cartPcId).find('#assem_clone_ctnt_pc').attr('id','assem_clone_ctnt_pc'+'_'+plus_id);
		$(cartPcId).find('#no_result_assem_pc').attr('id','no_result_assem_pc'+'_'+plus_id);
		$(cartPcId).find('input[name="check_pc"]').attr('id','check_pc'+'_'+plus_id);
		$(cartPcId).find('#assem_nm_pc').attr('id','assem_nm_pc'+'_'+plus_id);
		$(cartPcId).find('#assem_desc_pc').attr('id','assem_desc_pc'+'_'+plus_id);
		$(cartPcId).find('#btn_assem_grp_out_pc').attr('id','btn_assem_grp_out_pc'+'_'+plus_id);
		$(cartPcId).find('#assem_detail_pc').attr('id','assem_detail_pc'+'_'+plus_id);
		
		$('#assem_clone_head_pc_'+plus_id).show();
		$('#assem_clone_ctnt_pc_'+plus_id).show();
		$('#cart_list_detail_ctnt_pc_'+plus_id).show();
		$('#no_result_assem_pc_'+plus_id).hide();
		$('#btn_assem_grp_out_pc_'+plus_id).hide();
		$('#assem_detail_pc_'+plus_id).hide();
		$(cartPcId).find('.file').hide();
		//체크 true
		$('#check_pc_'+plus_id).prop("checked", true);
		$('#check_pc_'+plus_id).val("new");
		$('#check_pc_'+plus_id).attr("data-type",'A');
		$('#check_pc_'+plus_id).attr("onclick","checkBoxEvent('part','"+plus_id+"')");
	}
	//추가 영역 활성화
	for(var i =0; i<checkedAssem.length; i++){
		$('#assem_nm_pc_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_desc_pc_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_nm_mobile_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_desc_mobile_'+checkedAssem[i]).removeAttr("readonly");
	}
}
//어셈블리 정보 저장 - 추가와 수정이 한번에 일어날 수있음 
function saveAssemblyInfo(){
	//선택된 Assembly 없을 경우
	if(checkedAssem.length == 0){
		<%-- 정보를 저장할 Assembly를 선택해주세요 --%>
		alert(LANG_SET["FR000151"]);
		return;
	}
	
	<%-- 저장하시겠습니까? --%>
	if(confirm(LANG_SET["FR000073"])){
		var tmpDataAdd = new Array();
		var tmpDataEdit = new Array();
		if(devType=='M'){
			for(var i=0;i<checkedAssem.length;i++){
				if( $('#assem_nm_mobile_'+checkedAssem[i]).val() ==''){
					<%-- Assembly 이름을 입력해주세요 --%>
					alert(LANG_SET["FR000152"]);
					return;
				}					
				if($('#check_mobile_'+checkedAssem[i]).data('type') == 'A'){
					var objAdd = new Object();					
					objAdd.assem_nm = $('#assem_nm_mobile_'+checkedAssem[i]).val()
					objAdd.assem_desc = $('#assem_desc_mobile_'+checkedAssem[i]).val()
					
					tmpDataAdd.push(objAdd);
				}else{
					var objEdit = new Object();
					objEdit.assem_no = $('#check_mobile_'+checkedAssem[i]).val()
					objEdit.assem_nm = $('#assem_nm_mobile_'+checkedAssem[i]).val()
					objEdit.assem_desc = $('#assem_desc_mobile_'+checkedAssem[i]).val()
					
					tmpDataEdit.push(objEdit);
				}
			}
		}else{
			var obj; 
			for(var i=0;i<checkedAssem.length;i++){
				if( $('#assem_nm_pc_'+checkedAssem[i]).val() ==''){
					<%-- Assembly 이름을 입력해주세요 --%>
					alert(LANG_SET["FR000152"]);
					return;
				}	
				if($('#check_pc_'+checkedAssem[i]).data('type') == 'A'){
					var objAdd = new Object();					
					objAdd.assem_nm = $('#assem_nm_pc_'+checkedAssem[i]).val()
					objAdd.assem_desc = $('#assem_desc_pc_'+checkedAssem[i]).val()
					tmpDataAdd.push(objAdd);
				}else{
					var objEdit = new Object();
					objEdit.assem_no = $('#check_pc_'+checkedAssem[i]).val()
					objEdit.assem_nm = $('#assem_nm_pc_'+checkedAssem[i]).val()
					objEdit.assem_desc = $('#assem_desc_pc_'+checkedAssem[i]).val()
					tmpDataEdit.push(objEdit);
				}
			}
		}	
		if(tmpDataEdit.length !=0){ updateAssemInfo(tmpDataEdit)}
		if(tmpDataAdd.length !=0){ insertAssemInfo(tmpDataAdd)}
	}
}

//어셈블리 수정
function updateAssemInfo(updateData){

	var formData = new FormData();
	formData.append("actionID",'<%=AssemblyConstKey.ACTION_ASSEMBLY_EDIT_OK%>');
	formData.append("editParam",JSON.stringify(updateData));
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
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
				checkedAssem = [];
				getAssemblyList('first');
			}, error: function (e){
// 				console.log(e);
			}
			
		});
}

//어셈블리 추가
function insertAssemInfo(insertData){
	var formData = new FormData();
	formData.append("actionID", "<%=AssemblyConstKey.ACTION_ASSEMBLY_ADD_OK%>");
	formData.append("addParam", JSON.stringify(insertData));
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
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
			checkedAssem = [];
			getAssemblyList('first');
			
		}, error: function (e){
// 			console.log(e);
		}
	});
	
}


//어셈블리 그룹 삭제
function deleteAssembly(){
	if(checkedAssem.length == 0){
		<%-- 삭제할 Assembly를 선택해주세요 --%>
		alert(LANG_SET["FR000149"]);
		return;
	}
	//멘트 다국어처리**
	<%-- 삭제하시겠습니까? --%>
	if(confirm(LANG_SET["FR000076"])){
		var formData = new FormData();
		formData.append("actionID",'<%=AssemblyConstKey.ACTION_ASSEMBLY_DEL_OK%>');
		formData.append("deleteParam",checkedAssem);
		
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
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
				//alert(result.resMsg);
				if(result.resCode == 0){
					checkedAssem = [];
					getAssemblyList('first');
				}
			}, error: function (e){
// 				console.log(e);
			},
			complete: function(){
			  //$('#loadbar').fadeOut();
			}
		});
	}
}

// 디테일 열기
function openDetail(assem_no, buld_sta) {
	var param = {
            actionID :"<%=AssemblyConstKey.ACTION_ASSEMBLY_DETAIL%>",
            assem_no : assem_no,
    };
	
	$.ajax({
        type:"POST",
        dataType: "json",
        data: param,
        async: true,
        cache: false,
        url: "<%=ConstUrl.ASSEMBLY_URL%>",
        beforeSend:function(){
        	$('#loadbar').show();
        },
       	success: function (result) {   
       		
       		//해당 카트의 아이템 목록만 다시 그려주기 pc와 모바일 둘다
       		//console.log("result == " + JSON.stringify(result));
       		if(result.tot_list != undefined){
       			//모바일
    			if(devType == 'M'){
    				appendItemListTableMoblie(assem_no ,result, 'after');
         			$("#assem_detail_mobile_"+assem_no).attr("opened", true);
         		//PC	
    			} else {
    				appendItemListTable(assem_no ,result, 'after', buld_sta);
    				$("#assem_detail_pc_"+assem_no).attr("opened", true);
    			}
     			
       		}else{
       			//AssemblyServiceImpl > getAssembly 리턴값이 null인 경우 무한로딩 방지
       			<%-- 계층구조가 명확하지 않아 어셈블리 아이템을 확인할 수 없습니다. --%>	
       			alert(LANG_SET["FR000263"]);     
       			
       			onAndOffClass(assem_no);
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

//아이템 테이블 pc
function appendItemListTable(assem_no, list, appendType, buld_sta){
	var itemList = list.tot_list; 		// 종합리스트
	var tl_adp_list = list.tl_adp_list; // TOOL/ADP 리스트
	var ins_list = list.ins_list; 		// INS 리스트
	//console.log("[itemList] : : "+JSON.stringify(itemList))
	//console.log("[tl_adp_list] : : "+JSON.stringify(tl_adp_list))
	//console.log("[ins_list] : : "+JSON.stringify(ins_list))
	
	var TL_YN = "N"; // TOOL이 있는지 여부
	var INS_YN = "N"; // INSERT가 있는지 여부
	
	if(appendType == 'after'){
		$('#assem_item_tbody_pc_'+assem_no).children().not('#assem_item_head_pc_'+assem_no).not('#no_result_item_pc_'+assem_no).not('#assem_item_ctnt_pc_'+assem_no).remove();
	}
		
	//Assembly 체결 이미지 영역 내 비우고 다시 그리기
	$('#assem_detail_pc_' + assem_no).find('[id^="adaptive_cont_pc_adptl"]').remove();
	$('#adaptive_cont_pc_ins_' + assem_no).empty();
	
	var assemItemHtml = "";
	
	//Ins 체결 이미지 영역
	if(ins_list.length > 0){
		INS_YN = "Y";
		for(var i=0; i<ins_list.length;i++){
			
			if(ins_list[i].add_btn == true) {
				// Add 버튼인 경우
				var items = ""; //체결할 수 있는 아이템
				var itemGroup = ""; //체결할 수 있는 아이템그룹
				var saCds = ""; //서브어플리케이션코드
				
				if(ins_list[i].itemMS != null) {
					for(var k=0; k<ins_list[i].itemMS.length; k++){
						items += ins_list[i].itemMS[k].matnr + ",";	
					}
				}
				
				for(var k=0; k<ins_list[i].itGroupMS.length; k++){
					itemGroup += ins_list[i].itGroupMS[k].ig_cd + ",";
				}
				
				/* if(ins_list[i].saCdList != null) {
					for(var k=0; k<ins_list[i].saCdList.length; k++){
						saCds += ins_list[i].saCdList[k].sa_cd + ",";	
					}
				} */
				
				// Ins Add 버튼 추가
				assemItemHtml = getAssemItemAdd('INS', itemGroup, items, saCds, assem_no);
				$('#adaptive_cont_pc_ins_'+assem_no).append(assemItemHtml);	
				
			} else {
				// 이미지인 경우
				var grade = "Non-Data";
				if(ins_list[i].grade){
					grade = ins_list[i].grade;
				}
				var itImgUrl = "<%=UPLOAD_ROOT_PATH %>"+ins_list[i].it_img;
				
				assemItemHtml = getAssemItemHtml(itImgUrl, ins_list[i].matnr, ins_list[i].designation, grade);
				$('#adaptive_cont_pc_ins_'+assem_no).append(assemItemHtml);
			}
		}
	}
	
	// TOOL/ADP 체결 이미지 영역
	//for(var i=tl_adp_list.length-1; i>=0;i--){
	for(var i=0; i<tl_adp_list.length;i++){	
		if(tl_adp_list[i].add_btn == true) {
			// Add 버튼인 경우
			var items = ""; //체결할 수 있는 아이템
			var itemGroup = ""; //체결할 수 있는 아이템그룹
			var saCds = ""; //서브어플리케이션코드
			
			if(tl_adp_list[i].itemMS != null) {
				for(var k=0; k<tl_adp_list[i].itemMS.length; k++){
					items += tl_adp_list[i].itemMS[k].matnr + ",";	
				}
			}
			if(tl_adp_list[i].itGroupMS != null) {
				for(var k=0; k<tl_adp_list[i].itGroupMS.length; k++){
					itemGroup += tl_adp_list[i].itGroupMS[k].ig_cd + ",";
				}
			}
			
			if(tl_adp_list[i].itemWS != null) {
				for(var k=0; k<tl_adp_list[i].itemWS.length; k++){
					items += tl_adp_list[i].itemWS[k].matnr + ",";	
				}
			}
			if(tl_adp_list[i].itGroupWS != null) {
				for(var k=0; k<tl_adp_list[i].itGroupWS.length; k++){
					itemGroup += tl_adp_list[i].itGroupWS[k].ig_cd + ",";	
				}
			}
			
			/* if(tl_adp_list[i].saCdList != null) {
				for(var k=0; k<tl_adp_list[i].saCdList.length; k++){
					saCds += tl_adp_list[i].saCdList[k].sa_cd + ",";	
				}
			} */
			
			assemItemHtml = getAssemItemAdd('TOOLADP', itemGroup, items, saCds, assem_no);
			
		} else {
			// 이미지인 경우
			var grade = "Non-Data";
			if(tl_adp_list[i].grade){
				grade = tl_adp_list[i].grade;
			}
			var itImgUrl = "<%=UPLOAD_ROOT_PATH %>"+tl_adp_list[i].it_img;
			
			assemItemHtml = getAssemItemHtml(itImgUrl, tl_adp_list[i].matnr, tl_adp_list[i].designation, grade);
		}
		
		assemItemHtml = '<div class="adaptive_cont" id="adaptive_cont_pc_adptl' + i +'_'+assem_no +'">' + assemItemHtml + '</div>';
		$('#adaptive_cont_pc_ins_'+assem_no).before(assemItemHtml);
		
	}
	
	// INS이 없는 경우 INS <div>영역 삭제 (css의 before 연결선 표시 안하기위해)
	if(TL_YN == 'N' && INS_YN == 'N'){
		$('#adaptive_cont_pc_ins_'+assem_no).remove();
	}
	
	//카트 클릭시 matnr 번호 넘기기
	var item_matnr_arr = "";
	var item_assy_type_arr = "";
	var pop_ins_info_arr = "";

	// 리스트 표시
	if(itemList.length > 0){
		for(var i=0; i<itemList.length;i++){
			$('#assem_item_ctnt_pc_'+assem_no).css("display","");
			$('#btn_assem_grp_in_pc_'+assem_no).css("display","");
			$('#assem_item_ctnt_pc_'+assem_no).css("display","");
			//pc
			var cloneItem = $('#assem_item_ctnt_pc_'+assem_no).clone();
			$('#assem_item_tbody_pc_'+assem_no).append(cloneItem);
			
			//pc 모바일 공통 사용
			var plus_id = assem_no + '_'+i
			$('#assem_item_tbody_pc_'+assem_no).children().last().attr('id', 'assem_item_ctnt_pc_'+plus_id);
			
			var ctntId = '#'+'assem_item_ctnt_pc_'+plus_id;
			
			$(ctntId).find('#no_pc').attr('id', 'no_pc_'+plus_id);
			$(ctntId).find('#img_pc').attr('id', 'img_pc_'+plus_id);
			$(ctntId).find('#order_cd_pc').attr('id', 'order_cd_pc_'+plus_id);
			$(ctntId).find('#designation_pc').attr('id', 'designation_pc_'+plus_id);
			$(ctntId).find('#grade_pc').attr('id', 'grade_pc_'+plus_id);
			$(ctntId).find('#quantity_pc').attr('id', 'quantity_pc_'+plus_id);
			$(ctntId).find('#del_assem_item').attr('id', 'del_assem_item_'+plus_id);
			
			$('#no_pc_'+plus_id).html(i+1)
			var itImgUrl = "<%=UPLOAD_ROOT_PATH %>"+itemList[i].it_img;
			$('#img_pc_'+plus_id).attr('src', itImgUrl);
			$('#img_pc_'+plus_id).css('width', '78px'); // 이미지 사이즈 css
			$('#order_cd_pc_'+plus_id).html(itemList[i].matnr)
			$('#designation_pc_'+plus_id).html(itemList[i].designation)
			$('#designation_pc_'+plus_id).addClass("left"); // 좌측정렬
			$('#grade_pc_'+plus_id).html(itemList[i].grade);
			//수량이 0인경우는 표시하지 않는다. 생성완료 된 건에 한해서만 location갯수 표시
			if(itemList[i].quantity == 0){
				$('#quantity_pc_'+plus_id).html("");
			}else{
				$('#quantity_pc_'+plus_id).html(itemList[i].quantity);
			}
			$('#order_cd_pc_'+plus_id).attr('onclick','showItemDetailPop("'+itemList[i].matnr+'", "'+itemList[i].ig_cd+'",'+assem_no+')')
			$('#designation_pc_'+plus_id).attr('onclick','showItemDetailPop("'+itemList[i].matnr+'", "'+itemList[i].ig_cd+'",'+assem_no+')')
			//아이템 삭제버튼
			$('#del_assem_item_'+plus_id).attr('onclick','deleteItem("'+assem_no+'","'+itemList[i].matnr+'")');
			if(itemList.length > 1) {
				if(itemList[i].assy_type != 'INS'){
					if(i > 0 && itemList[itemList.length-1].matnr != itemList[i].matnr) {
						$('#del_assem_item_'+plus_id).remove();
					}	
				}
			}
						
			item_matnr_arr += itemList[i].matnr + ","; 
			item_assy_type_arr += itemList[i].assy_type + ",";
			
			if(itemList[i].assy_type == 'INS'){
				pop_ins_info_arr += itemList[i].assy_type + "$$" + itemList[i].it_img + "$$" + itemList[i].matnr + "$$" +  itemList[i].designation + "$$" + itemList[i].grade + ",";
			}
			
			if(itemList[i].assy_type == 'TL'){
				TL_YN = "Y"
			}
			
			// CSW 목록
			if(itemList[i].arr_csw !== null){
				if(itemList[i].arr_csw.length > 0){
					var item_tl_csw_arr = "";
					for(var k=0; k<itemList[i].arr_csw.length; k++){
						item_tl_csw_arr += itemList[i].arr_csw[k] + ",";	
					}
					$('#tl_csw_arr_pc_'+assem_no).val(item_tl_csw_arr);
				}
			}
			
		}//end of for
		
		$('#cart_pc'+'_'+assem_no).attr('onclick',  "insertToCart("+assem_no+ ",'" + item_matnr_arr +"')");					
		$('#build_pc'+'_'+assem_no).attr('onclick', "popCswLocation("+assem_no+ ",'" + item_matnr_arr +"','" + item_assy_type_arr +"','" +  pop_ins_info_arr +"','" + TL_YN +"','" + buld_sta +"')");
		
		//openDetail
		$('#no_result_item_pc_'+assem_no).hide();
		$('#assem_build_img_pc_'+assem_no).show();
		$('#btn_assem_grp_in_pc_'+assem_no).show();
		$('#excel_down_pc'+'_'+assem_no).attr('onclick', "excelDownload("+assem_no+")");
		
		
	}else{
		// Item이 없으면...............................
		$('#check_pc_'+assem_no).attr("data-itemcnt", 0);
		$('#no_result_item_pc_'+assem_no).show();
		$('#assem_build_img_pc_'+assem_no).hide(); // 3D/2D 영역 숨기기
		$('#btn_assem_grp_in_pc_'+assem_no).hide(); // 하단버튼(Build Assembly, Cart, Cart&Quotation)
		$('#excel_down_pc'+'_'+assem_no).attr('onclick', "");
		<%-- 저장된 파일이 없습니다. --%>
		//$('#excel_down_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
		if(list.item_cnt > 0) {
			$('#excel_down_pc'+'_'+assem_no).attr('onclick', "excelDownload("+assem_no+")");	
		} else {
			$('#excel_down_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
		}
		
	}

	$('#assem_item_ctnt_pc_'+assem_no).hide();
	
}

// 이미지 버튼	
function getAssemItemHtml(itImgUrl, matnr, designation, grade) {
	var assemItemHtml = '<ul><li><a href="#"><div class="left"><img src="' + itImgUrl + '" alt="" style="width: 67px;height: 57px" onerror="onErrorImage(this)" ></div>'
	+ '<div class="right"><p>' + matnr + '<br />' + designation + '<br />' + grade + '</p></div></a></li></ul>';
	
	return assemItemHtml;
}

// Add 버튼
function getAssemItemAdd(assyType, itemGroup, items, saCds, assem_no) {
	var assemItemHtml = '<ul><li><a><div class="addPart" onClick="goItemGroup(' + "'" + assyType + "'" + ', ' + "'" + itemGroup + "'" + ', ' + "'" + items + "'" + ', ' + "'" + saCds + "'" + ', ' + "'" + assem_no + "'" + ')"><span>'
	+ '<img src="<%=PATH_IMG%>/img_more.png" alt=""></span></div></a></li></ul>';
	
	return assemItemHtml;
}


//아이템 리스트 테이블 모바일
function appendItemListTableMoblie(assem_no,list,appendType){
	var itemList = list.tot_list; 		// 종합리스트

	//아이템 삭제 후 테이블 다시 그릴 때 이전 내용 삭제 
	if(appendType == 'after'){
		$('#assem_detail_list_mobile_'+assem_no).children().not('#assem_item_table_mobile_'+assem_no).remove();
	}
	
	if(itemList.length !=0){
		$('#assem_item_list_mobile_'+assem_no).show();
		
		//카트 클릭시 matnr 번호 넘기기
		var item_matnr_arr = "";
		var item_assy_type_arr = "";
		
		for(var i=0; i<itemList.length;i++){
			var plus_id = assem_no + '_'+i
			//mobile
			var cloneItem2 =  $('#assem_item_table_mobile_'+assem_no).clone();
			$('#assem_item_list_mobile_'+assem_no).append(cloneItem2);
			
			$('#assem_item_list_mobile_'+assem_no).children().last().attr('id', 'assem_item_table_mobile_'+plus_id);
			var ctntId2 = '#assem_item_table_mobile_'+plus_id;
			
			//cart_detail_list_mobile
			$(ctntId2).find('#no_result_item_mobile').attr('id', 'no_result_item_mobile_'+plus_id);
			$(ctntId2).find('#no_mobile').attr('id', 'no_mobile_'+plus_id);
			//$(ctntId2).find('#item_img_mobile').attr('id', 'item_img_mobile_'+plus_id);
			$(ctntId2).find('#order_cd_mobile').attr('id', 'order_cd_mobile_'+plus_id);
			$(ctntId2).find('#designation_mobile').attr('id', 'designation_mobile_'+plus_id);
			$(ctntId2).find('#grade_mobile').attr('id', 'grade_mobile_'+plus_id);
			$(ctntId2).find('#quantity_mobile').attr('id', 'quantity_mobile_'+plus_id);
			$(ctntId2).find('#item_gtc_down').attr('id', 'item_gtc_down_'+plus_id);
			$(ctntId2).find('#delete_item_mobile').attr('id', 'delete_item_mobile_'+plus_id);
						
			$('#no_result_item_mobile_'+plus_id).hide();
			$('#no_mobile_'+plus_id).html(i+1);
			$('#order_cd_mobile_'+plus_id).html(itemList[i].matnr)
			var itImgUrl = "<%=UPLOAD_ROOT_PATH %>"+itemList[i].it_img;
			//$('#item_img_mobile_'+plus_id).attr('src', itImgUrl);
			//$('#item_img_mobile_'+plus_id).css('width', '78px'); // 이미지 사이즈 css

			$('#designation_mobile_'+plus_id).html(itemList[i].designation)
			$('#grade_mobile_'+plus_id).html(itemList[i].grade)
			//수량이 0인경우는 표시하지 않는다. 생성완료 된 건에 한해서만 location갯수 표시
			if(itemList[i].quantity == 0){
				$('#quantity_mobile_'+plus_id).html('');
			}else{
				$('#quantity_mobile_'+plus_id).html(itemList[i].quantity);
			}
			
			// 아이템 삭제버튼
			$('#delete_item_mobile_'+plus_id).attr('onclick','deleteItem("'+assem_no+'","'+itemList[i].matnr+'")')
			if(itemList.length > 1) {
				if(itemList[i].assy_type != 'INS'){
					if(i > 0 && itemList[itemList.length-1].matnr != itemList[i].matnr) {
						$('#delete_item_mobile_'+plus_id).remove();
					}	
				}
			}
			
			item_matnr_arr += itemList[i].matnr + ","; 
			item_assy_type_arr += itemList[i].assy_type + ","; 
			
		}//end of for
		$('#assem_item_table_mobile_'+assem_no).hide();
		//$('#cart_order_mobile'+'_'+assem_no).show();
		//$('#cart_order_mobile'+'_'+assem_no).attr('onclick',  "goRequest('single', '"+assem_no+ "')");
			
	}else{
		$('#check_mobile_'+assem_no).attr("data-itemcnt", 0);
		//no item 표시
		var tbody_id = '#assem_item_tbody_mobile_'+assem_no
		$(tbody_id).children().not('#no_result_item_mobile_'+assem_no).remove();
		//show 아이템 없음 표시
		$('#no_result_item_mobile_'+assem_no).show();
		
		$('#excel_down_mobile'+'_'+assem_no).attr('onclick', "");
		<%-- 저장된 파일이 없습니다. --%>
		//$('#excel_down_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
		if(list.item_cnt > 0) {
			$('#excel_down_mobile'+'_'+assem_no).attr('onclick', "excelDownload("+assem_no+")");	
		} else {
			$('#excel_down_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
		}
		//$('#cart_order_mobile'+'_'+assem_no).hide();
	}
	
}
//어셈블리 그룹의 open버튼 onAndOffClass
function onAndOffClass(assem_no, buld_sta){
	//console.log(typeof assem_no)
	//디바이스 타입에 따라 제어해야하는 부분이 구분되어있음
	if(devType =='M' ){
		if($('#open_mobile_'+assem_no).hasClass('on') ){
			$('#open_mobile_'+assem_no).removeClass('on');
// 			<!-- close -->
			$('#open_mobile_'+assem_no).attr('data-lang','FR000077');
			$('#open_mobile_'+assem_no).html(LANG_SET["FR000077"]);
			$('#assem_detail_mobile_'+assem_no).hide();
			
			$('#img_3d_detail_mobile_'+assem_no).attr('src', '');
			$('#img_3d_basic_mobile_'+assem_no).attr('src', '');
			
		}else{
			// 모든 리스트 close
			$('[id^="assem_detail_mobile_"]').each(function(index, item){ 
				var ids = $(item).attr("id").split('_');
				var assemNo = ids[ids.length-1];
				$('#open_mobile_'+assemNo).removeClass('on');
				$('#open_mobile_'+assemNo).attr('data-lang','FR000077');
				$('#open_mobile_'+assemNo).html(LANG_SET["FR000077"]);
				$('#assem_detail_mobile_'+assemNo).hide();
				
				$('#img_3d_detail_mobile_'+assemNo).attr('src', '');
				$('#img_3d_basic_mobile_'+assemNo).attr('src', '');
			});	
					
			//if(!$("#assem_detail_pc_"+assem_no).attr("opened")) {
				openDetail(assem_no, buld_sta);
			//}
			$('#open_mobile_'+assem_no).addClass('on');
// 			<!-- open -->
			$('#open_mobile_'+assem_no).attr('data-lang','FR000090');
			$('#open_mobile_'+assem_no).html(LANG_SET["FR000090"]);
			$('#assem_detail_mobile_'+assem_no).show();
			
			var open_mobile = $('#img_3d_basic_mobile_'+assem_no).attr('src');
			if(open_mobile == "") {
				get3dRendering ($("#download_basic_file_url_mobile_"+assem_no).attr("download"), "B", assem_no);	
			}
		}
	} else {
		if($('#open_pc_'+assem_no).hasClass('on') ){
			$('#open_pc_'+assem_no).removeClass('on');
			
// 			<!-- close -->
			$('#open_pc_'+assem_no).attr('data-lang','FR000077');
			$('#open_pc_'+assem_no).html(LANG_SET["FR000077"]);
			$('#assem_detail_pc_'+assem_no).hide();
			
			$('#img_3d_detail_pc_'+assem_no).attr('src', '');
			$('#img_3d_basic_pc_'+assem_no).attr('src', '');
			
		}else{
			// 모든 리스트 close
			$('[id^="assem_detail_pc_"]').each(function(index, item){ 
				var ids = $(item).attr("id").split('_');
				var assemNo = ids[ids.length-1];
				$('#open_pc_'+assemNo).removeClass('on');
				$('#open_pc_'+assemNo).attr('data-lang','FR000077');
				$('#open_pc_'+assemNo).html(LANG_SET["FR000077"]);
				$('#assem_detail_pc_'+assemNo).hide();
				
				$('#img_3d_detail_pc_'+assemNo).attr('src', '');
				$('#img_3d_basic_pc_'+assemNo).attr('src', '');
			});
					
			//if(!$("#assem_detail_pc_"+assem_no).attr("opened")) {
				openDetail(assem_no, buld_sta);
			//}
			$('#open_pc_'+assem_no).addClass('on');
// 			<!-- open -->
			$('#open_pc_'+assem_no).attr('data-lang','FR000090');
			$('#open_pc_'+assem_no).html(LANG_SET["FR000090"]);
			$('#assem_detail_pc_'+assem_no).show();
			
			var open = $('#img_3d_basic_pc_'+assem_no).attr('src');			
			if(open == "") {
				get3dRendering ($("#download_basic_file_url_pc_"+assem_no).attr("download"), "B", assem_no);	
			}
			
		}
	}
}
//check box event
function checkBoxEvent(check_type, assem_no/*All 일경우 ''*/){
	if(check_type == 'all'){
		if($("#selectAll").is(":checked")) { 
			//모두 선택을 누르기전에 각각 선택했을 수도 있으므로 초기화
			checkedAssem = [];
			//모바일
			if(devType == 'M'){
				$('input[name="check_mobile"]').not('#check_mobile').prop("checked", true);
				$('input[name="check_mobile"]:checked').each(function(){
					checkedAssem.push($(this).val());
				})
			}else{
				//PC
				$('input[name="check_pc"]').not('#check_pc').prop("checked", true);
				$('input[name="check_pc"]:checked').each(function(){
					$(this).is(":checked") ?  checkedAssem.push($(this).val()) : ''
				})
			}
		}else{ 
			devType == 'M'? $('input[name="check_mobile"]').prop("checked", false) : $('input[name="check_pc"]').prop("checked", false)
			checkedAssem = [];
		}
	}else{
		//checkbox 이벤트 단일 선택일때
		var idx = checkedAssem.indexOf(assem_no.toString());
		//체크된 경우 
		if(idx == -1){
			checkedAssem.push(assem_no.toString())
		} else{
			//체크 해제인 경우
			if(devType =='P' && $('#check_pc_'+assem_no).data('type')=='E'){
				//수정영역 비활성화
				$('#assem_nm_pc_'+assem_no).attr("readonly","readonly");
				$('#assem_desc_pc_'+assem_no).attr("readonly" ,"readonly");
			}
			if(devType =='M' && $('#cart_mobile_'+assem_no).data('type')=='E'){
				//수정영역 비활성화
				$('#assem_nm_mobile_'+assem_no).attr("readonly" ,"readonly");
				$('#assem_desc_mobile_'+assem_no).attr("readonly" ,"readonly");
			}
			//추가후 체크박스 해제시 삭제
			if($('#check_pc_'+assem_no).data('type')=='A'){
				$('#assem_pc_'+assem_no).remove();
			}
			if($('#check_mobile_'+assem_no).data('type')=='A'){
				$('#assem_mobile_'+assem_no).remove();
			}
			checkedAssem.splice(idx,1);
			//전체선택이 체크되어있으면해제
			if($('#selectAll').is(':checked')){$('#selectAll').prop("checked",false)}
		}
	}
}
//업데이트 버튼 이벤트
function clickUpdateButton(){
	if(checkedAssem.length == 0){
		<%-- 수정할 어셈블리를 선택해주세요 --%>
		alert(LANG_SET["FR000150"]);
		return;
	}
	//수정영역 활성화
	for(var i =0; i<checkedAssem.length; i++){
		$('#assem_nm_pc_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_desc_pc_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_nm_mobile_'+checkedAssem[i]).removeAttr("readonly");
		$('#assem_desc_mobile_'+checkedAssem[i]).removeAttr("readonly");
	}
}

//Assembly 내 Item 삭제
function deleteItem(assem_no, matnr){
 
	<%-- 삭제하시겠습니까? --%>
	if(confirm(LANG_SET["FR000076"])){
		//console.log("assem_no = " + assem_no + " / " + "matnr = " + matnr);		
		
		var formData = new FormData();
		formData.append("actionID",'<%=AssemblyConstKey.ACTION_ASSEMBLY_ITEM_DEL_OK%>');
		formData.append("assem_no",assem_no);
		formData.append("matnr",matnr);
		
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
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
					//해당 카트의 아이템 목록만 다시 그려주기 pc와 모바일 둘다
	 				if(devType == 'M'){
	 					//모바일
	 					appendItemListTableMoblie(assem_no ,result, 'after');
	 				} else {
	 					// PC
	 					//마지막 아이템 삭제 후, 다시 그릴때는 tot_list가 없기때문에 buld_sta 에 빈값을 넣어준다.
	 					var buld_sta = "";
	 					if(result.tot_list.length > 0){
	 						buld_sta = result.tot_list[0].buld_sta;
	 					}
	 					appendItemListTable(assem_no ,result, 'after', buld_sta);
	 				}
	 				
				}else{
					alert(result.resMsg);
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

//Build Assembly 이벤트
function buildAssembly(assem_no, item_matnr_arr, item_assy_type_arr, ins_list){
	//console.log("item_matnr_arr == " + JSON.stringify(item_matnr_arr));
	//console.log("item_assy_type_arr == " + JSON.stringify(item_assy_type_arr));
	var params = new Object();
	
	var adpItems = [];
	var toolItems = [];
	var cuttingItems = [];
	
	//////////////////////////////////////////////
	var matnrs = item_matnr_arr.split(",");
	var assy_types = item_assy_type_arr.split(",");
	var ins = ins_list.split(",");
	
	for(var i=0; i<matnrs.length; i++) {
		if(matnrs[i] != '') {
			if(assy_types[i] == '') {
       			<%--  is type does not exist. --%>       			
				alert(matnrs[i] + LANG_SET["FR000262"])
				return;
			}
			
			var item = 	{
				matnr: matnrs[i],
				assy_type: assy_types[i]
			}
			
			if(assy_types[i] == 'ADP'){
				adpItems.push(item);
			}
			if(assy_types[i] == 'TL'){
				item.overhang = '0';
				toolItems.push(item);
			}
		}
		
	}
	
	for(var i=0; i<ins.length; i++) {
		var matnr_ins = ins[i].split("/");
		var item = 	{
			matnr: matnr_ins[0],
			assy_type: 'INS',
			location: matnr_ins[1]
		}
		cuttingItems.push(item);
	}
	
	//////////////////////////////////////////////
/* 		
	toolItems.push({
		matnr: '1-06-049077',
		assy_type: 'TL'
	});
	
	cuttingItems.push({
		matnr: '1-02-055739',
		assy_type: 'INS',
		location : 'CSW1'
	});
	cuttingItems.push({
		matnr: '1-02-055739',
		assy_type: 'INS',
		location : 'CSW2'
	});

	cuttingItems.push({
		matnr: '1-02-068107',
		assy_type: 'INS',
		location : 'CSW1_2'
	});
*/
	var param = {
            actionID :"<%=AssemblyConstKey.ACTION_ASSEMBLY_BUILD_OK%>",
            assem_no : assem_no,
            fileName : "assemble."+assem_no,
            //item_type : "INDEXABLE",
            itemADP_str : JSON.stringify(adpItems),
            itemTL_str : JSON.stringify(toolItems),
            itemINS_str : JSON.stringify(cuttingItems),
    };
	
	console.log(JSON.stringify(param));
	
	$.ajax({
        type:"POST",
        dataType: "json",
        data: param,
        async: true,
        cache: false,
        url: "<%=ConstUrl.ASSEMBLY_URL%>",
        beforeSend:function(){
        	$('#loadbar').show();
        },
       	success: function (e) {   
       		/* alert(e.resMsg);
       		if(e.resCode == "0") {
       			$(".open_assem_pop").fadeOut();
       			getAssemblyInfo(assem_no);	
       		} */
       		if(e.resCode == "0") {
       			alert(LANG_SET["FR000258"]);
       			$(".open_assem_pop").fadeOut();
       			$("#st_doing_"+assem_no).show();
       			$("#st_comp_"+assem_no).hide();
       			$("#st_error_"+assem_no).hide();
       		} else if(e.resCode == "2") {
       			<%-- 생성중인 데이터가 존재합니다. 생성완료 까지 대기하시거나 해당 데이터의 생성중 아이콘을 클릭하여 해제하여 주십시오. --%>	
       			alert(LANG_SET["FR000261"]);       			
       		} else {
       			alert(e.resMsg);
       		}
       		
       	},
       	complete:function(){
       		$('#loadbar').fadeOut();
       		//location.reload();
       	},
       	error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       	}
	});
}

function getAssemblyInfo(assem_no){
	
	var param = {
            actionID :"<%=AssemblyConstKey.ACTION_ASSEMBLY_INFO%>",
            assem_no : assem_no
    };
	
	$.ajax({
        type:"POST",
        dataType: "json",
        data: param,
        async: true,
        cache: false,
        url: "<%=ConstUrl.ASSEMBLY_URL%>",
        beforeSend:function(){
        	//$('#loadbar').show();
        },
       	success: function (result) {   
       		// GTC 파일 다운로드
			var itGtcFileUrl;
			if(result.gtc_file !== undefined && result.gtc_file !== ""){
				itGtcFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.gtc_file;
			}else{
				itGtcFileUrl = "";
			}
			if(itGtcFileUrl !== ""){			
				var file_nm = itGtcFileUrl.split('/');
				file_nm = file_nm[file_nm.length-1];								
				$('#gtc_down_pc'+'_'+assem_no).attr('href', itGtcFileUrl);
				$('#gtc_down_pc'+'_'+assem_no).attr('download', file_nm);
				$('#gtc_down_mobile'+'_'+assem_no).attr('href', itGtcFileUrl);
				$('#gtc_down_mobile'+'_'+assem_no).attr('download', file_nm);
			}else{
				<%-- 저장된 파일이 없습니다.--%>
				$('#gtc_down_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
				$('#gtc_down_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
			}
       		       	
       		// 3D Basic 파일 다운로드
			var itStpBasicFileUrl;
			if(result.stp_b_file !== undefined && result.stp_b_file !== ""){
				itStpBasicFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.stp_b_file;
			}else{
				itStpBasicFileUrl = "";
			}
			if(itStpBasicFileUrl !== ""){				
				//stp basic 파일 다운로드
				var file_nm = itStpBasicFileUrl.split('/');
				file_nm = file_nm[file_nm.length-1];								
				$('#download_basic_file_url_pc'+'_'+assem_no).attr('href', itStpBasicFileUrl);
				$('#download_basic_file_url_pc'+'_'+assem_no).attr('download', file_nm);
				$('#download_basic_file_url_mobile'+'_'+assem_no).attr('href', itStpBasicFileUrl);
				$('#download_basic_file_url_mobile'+'_'+assem_no).attr('download', file_nm);
				
				//3D이미지 영역
				$('#img_3d_pc'+'_'+assem_no).css('display', 'none');
				$('#3d_basic_pc'+'_'+assem_no).attr('src', itStpBasicFileUrl);
				$('#img_3d_mobile'+'_'+assem_no).css('display', 'none');
				$('#3d_basic_mobile'+'_'+assem_no).attr('src', itStpBasicFileUrl);
				
			}else{
				<%-- 저장된 파일이 없습니다.--%>
				$('#download_basic_file_url_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
				$('#download_basic_file_url_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
			}
			
			// 3D Detail 파일 다운로드
			var itStpDetailFileUrl;
			if(result.stp_d_file !== undefined && result.stp_d_file !== ""){
				itStpDetailFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.stp_d_file;
			}else{
				itStpDetailFileUrl = "";
			}

			if(itStpDetailFileUrl !== ""){				
				//stp basic 파일 다운로드
				var file_nm = itStpDetailFileUrl.split('/');
				file_nm = file_nm[file_nm.length-1];					
				$('#download_detail_file_url_pc'+'_'+assem_no).attr('href', itStpDetailFileUrl);
				$('#download_detail_file_url_pc'+'_'+assem_no).attr('download', file_nm);
				$('#download_detail_file_url_mobile'+'_'+assem_no).attr('href', itStpDetailFileUrl);
				$('#download_detail_file_url_mobile'+'_'+assem_no).attr('download', file_nm);
				
				//3D이미지 영역
				$('#3d_detail_pc'+'_'+assem_no).attr('src', itStpDetailFileUrl);
				$('#3d_detail_mobile'+'_'+assem_no).attr('src', itStpDetailFileUrl);
				
			}else{
				<%-- 저장된 파일이 없습니다.--%>
				$('#download_detail_file_url_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
				$('#download_detail_file_url_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
			}
			
			// 2D DXF 파일 다운로드
			var itDxfFileUrl;
			if(result.dxf_file !== undefined && result.dxf_file !== ""){
				itDxfFileUrl = "<%=UPLOAD_ROOT_PATH %>"  + result.dxf_file;
				itDxfFileUrl = itDxfFileUrl.replace("dxf", "jpg"); 
			}else{
				itDxfFileUrl = "";
			}
			
			if(itDxfFileUrl !== ""){
				$('#2d_dxf_pc'+'_'+assem_no).attr("src", itDxfFileUrl);
				$('#2d_dxf_mobile'+'_'+assem_no).attr("src", itDxfFileUrl);
				
				//dxf 파일 다운로드
				var file_nm = itDxfFileUrl.split('/');
				file_nm = file_nm[file_nm.length-1];								
				itDxfFileUrl = itDxfFileUrl.replace("jpg", "dxf"); 
				file_nm = file_nm.replace("jpg", "dxf"); 
				$('#download_dxf_file_url_pc'+'_'+assem_no).attr('href', itDxfFileUrl);
				$('#download_dxf_file_url_pc'+'_'+assem_no).attr('download', file_nm);
				$('#download_dxf_file_url_mobile'+'_'+assem_no).attr('href', itDxfFileUrl);
				$('#download_dxf_file_url_mobile'+'_'+assem_no).attr('download', file_nm);
			}else{
				<%-- 저장된 파일이 없습니다.--%>
				$('#download_dxf_file_url_pc'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
				$('#download_dxf_file_url_mobile'+'_'+assem_no).attr('href', 'javascript:alert("' + LANG_SET["FR000153"] + '");');
			}
       		
			toggleBasic(assem_no)
       	},
       	complete:function(){
       		$('#loadbar').fadeOut();
       	},
       	error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       	}
	});
	
}

//Cart 버튼 클릭시
function insertToCart(assem_no, item_matnr_arr){
	//console.log("item_list == " + JSON.stringify(item_matnr_arr));
		
	goItemCart(item_matnr_arr, "CART",assem_no);
}

//Cart & Request of Quotation 클릭 시
function insertCartAndGoRequest(assem_no, item_matnr_arr){
	//console.log("item_list == " + JSON.stringify(item_matnr_arr));		

	goItemCart(item_matnr_arr, "REQ", assem_no);
}

//gtc 다운로드
function gtcDownload(assem_no){
	var param = {
			actionID : '<%=AssemblyConstKey.ACTION_GET_GTC_ZIP_FILE%>'
				,assem_no : assem_no
	}
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
		data: param,
		dataType: 'json',
		async: true,
		cache: false,
        beforeSend: function(){
        	$('#loadbar').show();
        },
		success: function(result) {
			console.log(arguments);

		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});	
}

//엑셀 다운로드
function excelDownload(assem_no){	
	var param = {
			actionID : '<%=ConstKey.ACTION_GET_EXCEL%>'
			,assem_no : assem_no
	}
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.ASSEMBLY_URL%>?v=' + (new Date().getTime()),
		data: param,
		dataType: 'json',
		async: true,
		cache: false,
        beforeSend: function(){
        	$('#loadbar').show();
        },
		success: function(result) {
			console.log(arguments);
//					console.log("upload Path here ============")
//					console.log(result.resData)
//					console.log("upload Path here ============")
				excel_file_path = result.resData;	// 물리주소 
				file_name = excel_file_path.split("\\");
				file_name = file_name[file_name.length - 1];	// \단위로 분리한 물리주소 마지막 값은 파일명
//					console.log("excel_file_path ==> " + excel_file_path);
//					console.log("file_name ==> " + file_name);
				doExcelFileDown()

		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
}
function showItemDetailPop(matnr, ig_cd, assem_no){
	//alert("아이템 레이어 팝업");
	//alert(ig_cd);
 	$('#ifr_item_detail').attr('src','<%=ConstUrl.ITEM_DETAIL_URL%>'+'?search_ig_cd='+ig_cd+'&search_matnr='+matnr)
 	
	$(".open_pop.cart_item_detail").fadeIn("fast");
    $(".open_pop.cart_item_detail .in > div").hide();
    $(".pop_cont").fadeIn();
    return; 
}

//카트 팝업
function goItemCart(item_matnr_arr, type, assem_no){	
	var userId = USERINFO.user_id;
	//var item_matnr = $(obj).data("matnr");
		
	 // 로그인이 되어있는경우
	 if(userId){
		 getCartList(item_matnr_arr, type, assem_no);
	 // 로그인이 안되어있는경우	 
	 }else{
		 //로그인창 오픈
		  $(".open_login").click();
		 //alert("Please try again after login.");
	 }	 
}

//Cart 목록 조회
function getCartList(item_matnr_arr, type, assem_no){
	var param = {
			actionID:"<%=AssemblyConstKey.ACTION_CART_LIST%>"
	};
	
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
        async: true,
        cache: false,
		url: "<%=ConstUrl.ASSEMBLY_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
       success: function (e) {   
       	//console.log("[Cart List] = " + JSON.stringify(e.cartList));       	
       	var cartList = e.cartList;
       	
       	$("#select_cart_list option").remove(); //장바구니 selectboc option 삭제       	
       	
       	$("#select_item_matnr_arr").val(item_matnr_arr);
       	$("#add_type").val(type);
       	
    	for(var i = 0; i<cartList.length; i++){
           	var option = '<option value="' + cartList[i].cart_no +'">'+cartList[i].cart_nm+'</option>';
           	$("#select_cart_list").append(option);           		
        }
    	//assem_no hidden값 설정
    	$('#assem_no_bf_pop').val(assem_no);
    	$('#assem_no_new_pop').val(assem_no);
    	
    	// Cart 목록 팝업
	    $(".open_cart_pop.cart_pop01").fadeIn();
       },
       complete:function(){
           $('#loadbar').fadeOut();
       },
       error: function (request, error) {
       	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
}

//Cart 새로만들기 버튼 클릭
function newCartPop(){
    $(".open_cart_pop.cart_pop01").fadeOut();
    $(".open_cart_pop.cart_pop02").fadeIn();
}

// 장바구니 추가 Save버튼 클릭
function saveCartItem(){
	//console.log("Cart = " + cartNo + " / " + "MATNR = " + itemMatnr);
	var cartNo = $("#select_cart_list").val();
	var itemMatnrArr = $("#select_item_matnr_arr").val(); //아이템번호 arr
	itemMatnrArr = itemMatnrArr.slice(0, -1); //마지막 ,제거	
	var addType = $("#add_type").val(); //카트담기 인지? 카트담은 후 오더요청인지?	
	var assem_no = $('#assem_no_bf_pop').val();
	
	if(cartNo){
		insertCartItem(cartNo, "", itemMatnrArr, addType, assem_no);
	}else{
		<%-- 장바구니를 선택하세요 --%>
		alert(LANG_SET["FR000164"]);
	}
}

//신규 장바구니 추가  Save버튼 클릭
function saveNewCartItem(){
	var itemMatnrArr = $("#select_item_matnr_arr").val(); //아이템번호 arr	
	itemMatnrArr = itemMatnrArr.slice(0, -1); //마지막 ,제거
	var newCartNm = $("#new_cart_name").val(); //장바구니 그룹명	
	var addType = $("#add_type").val(); //카트담기 인지? 카트담은 후 오더요청인지?	
	var assem_no = $('#assem_no_new_pop').val();
	
	if(newCartNm){
		insertCartItem("", newCartNm, itemMatnrArr, addType, assem_no);
	}else{
	    <%-- 장바구니 그룹명을 입력하세요 --%>
		alert(LANG_SET["FR000127"]);
	}
	
}

//카트에 Item 추가
function insertCartItem(cartNo, cartNm, matnrArr, addType, assem_no){	
	//ajax호출
	var param = {
			actionID:"<%=AssemblyConstKey.ACTION_CART_ADD_OK%>"
			,cart_no: cartNo
			,matnr: matnrArr
			,cart_nm: cartNm
	};
    $.ajax({
		type:"POST",
		dataType: "json",
		data: param,
       	async: true,
       	cache: false,
		url: "<%=ConstUrl.ASSEMBLY_URL%>",
		beforeSend:function(){
           $('#loadbar').show();
       },
		success: function(result) {
			console.log(result.resMsg);			
			if(result.resCode == '0'){
				<%-- 장바구니에 담았습니다 --%>
				alert(LANG_SET["FR000129"]);
			    $(".open_cart_pop.cart_pop01").fadeOut();
			    $(".open_cart_pop.cart_pop02").fadeOut();			   
			    
			    //신규장바구니 input box 데이터 삭제
			    $("#new_cart_name").val("");
				//선택한 아이템목록 삭제
		       	$("#select_item_matnr_arr").val("");
				//선택한 addType 삭제
		       	$("#add_type").val("");
				//선택한 어셈블리 넘버 삭제
			    $('#assem_no_bf_pop').val("");
			    $('#assem_no_new_pop').val("");
				
                $("#cartCnt_top").text(result.resCartCnt);
                $("#cartCnt_top2").text(result.resCartCnt);
                $("#cartCnt_rgt_wrap").text(result.resCartCnt);
                
                if(cartNo == ""){
                	cartNo = result.resCartNo
                }
                
                if(addType === 'REQ'){
                	goRequest('single', assem_no, cartNo);
                	 $('#loadbar').fadeOut();
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

// 아이템그룹 페이지로 이동
function goItemGroup(assy_type, itemGroup, items, saCds, assem_no){
	//인서트의 경우 CSW목록 체크
	/* if(assy_type == 3) {
		var csw_arr = $('#tl_csw_arr_pc_'+assem_no).val();
		var csw = null; 
		if(csw_arr !== ""){
			csw_arr = csw_arr.slice(0, -1);
			csw = csw_arr.split(",");
		}
		
		if(csw == null || csw.length == 0) {
			alert("TOOL에 체결할 수 있는 INSERT 갯수가 0건입니다.");
			return;
		}
		
		var ins_cnt = $('#adaptive_cont_pc_ins_'+assem_no + " > ul").length - 1;
		if(ins_cnt == csw.length) {
			alert("TOOL에 체결할 수 있는 INSERT 갯수가 초과되었습니다.");
			return;
		}
		
	} */
	if(itemGroup !== ""){
		itemGroup = itemGroup.slice(0, -1);
	}
	if(items !== ""){
		items = items.slice(0, -1);
	}
	$('input[name="ig_cd"]').val(itemGroup);
	$('input[name="matnr_list"]').val(items);
	//$('input[name="sa_cd"]').val(saCds);
	$('input[name="search_assem_no"]').val(assem_no);
	
	$('#loadbar').show();
	goPageTwo(goIgPage);
}

//카트를 거치지 않고 바로 주문서 화면으로 이동
function goRequest(request_type, assem_no/*하단 버튼일 경우 값 없음*/){
	//여러개 선택해서 주문화면으로 넘길 때
	if(request_type == 'multi'){
		var assemNo;
		//모바일
		if(devType == 'M'){
			//for문으로 체크된 어셈블리에 아이템이 있는지 검증
			for(var i =0; i< checkedAssem.length; i++){
				console.log("in for loop")
				if($('#check_mobile_'+checkedAssem[i]).data('itemcnt') == "0"){
					var idx = checkedAssem.indexOf(checkedAssem[i].toString());
					checkedAssem.splice(idx,1);
				}
			}
			//검증 후 assemNo set
			checkedAssem.length !=0 ? assemNo = checkedAssem.join() : assemNo = ''
			if(assemNo != ''){
				//cartNo가 빈값이 아닐 경우만 order 화면으로 넘긴다
				$('input[name="assem_no"]').val(assemNo)
				goOrderPage.submit();
			}else{
				<%-- 어셈블리에 추가된 아이템이 없습니다. --%>
				alert(LANG_SET["FR000161"]);
				return;
			}
		}else{
			//PC 
			for(var i =0; i< checkedAssem.length; i++){
				if($('#check_pc_'+checkedAssem[i]).data('itemcnt') == "0"){
					var idx = checkedAssem.indexOf(checkedAssem[i].toString());
					checkedAssem.splice(idx,1);
				}
			}
			//검증 후 cartNo set
			checkedAssem.length !=0 ? assemNo = checkedAssem.join() : assemNo = ''
			if(assemNo != ''){
				//cartNo가 빈값이 아닐 경우만 order 화면으로 넘긴다
				$('input[name="assem_no"]').val(assemNo)
				goOrderPage.submit();
			}else{
				<%-- 어셈블리에 추가된 아이템이 없습니다. --%>
				alert(LANG_SET["FR000161"]);
				return;
			}
		}
	}else{
		//단건으로 주문을 요청할 때  (2021/09/23 사양 변경으로 카트 번호 없음)
		if(devType =='M'){
			//PC 
			if($('#check_mobile_'+assem_no).data('itemcnt') == "0"){
				<%-- 어셈블리에 추가된 아이템이 없습니다. --%>
				alert(LANG_SET["FR000161"]);
				return;
			}else{
				$('input[name="assem_no"]').val(assem_no)
				goOrderPage.submit();
// 				goPageTwo(goOrderPage, '');
			}
		}else{
			//PC 
			if($('#check_pc_'+assem_no).data('itemcnt') == "0"){
				<%-- 장바구니에 추가된 아이템이 없습니다. --%>
				alert(LANG_SET["FR000161"]);
				return;
			}else{
				$('input[name="assem_no"]').val(assem_no)
				goOrderPage.submit();
			}
		}
	}
}
//카트 엑셀 다운로드
function doExcelFileDown() {
	$("#excel_file_path", $assemItemExceldownForm).val(excel_file_path);
	$("#atch_file_name", $assemItemExceldownForm).val(file_name);
	goPageTwo(assemItemExceldownForm, "<%=ConstKey.ACTION_GET_FILE%>");
	
	
	$('#cartExceldownForm').actionID = "";
}	// doExcelFileDown()

//3D 팝업
function open3dPop(gubun, assem_no){
	$(".open_cart_pop.3d_pop").fadeIn("fast");
    $(".open_cart_pop.3d_pop .in > div").hide();
    $(".pop_cont").fadeIn();
    
    get3dRendering ($("#download_basic_file_url_pc_"+assem_no).attr("download"), gubun, assem_no);
    
    return; 
}

//2D 팝업
function open2dPop(assem_no){
	$(".open_cart_pop.2d_pop").fadeIn("fast");
    $(".open_cart_pop.2d_pop .in > div").hide();
    $(".pop_cont").fadeIn();
    
    var src = $('#2d_dxf_pc_'+assem_no).attr("src");
    if(src == undefined) {
    	src = "";
    }
    $('#2d_pop_img').attr("src", src);
    return; 
}


var assem_no1 = "";
var item_matnr_arr1 = ""; 
var item_assy_type_arr1 = "";

//INS CSW location 팝업
function popCswLocation(assem_no, item_matnr_arr, item_assy_type_arr, pop_ins_info_arr, tool_yn, buld_sta){	
	
	//TOOL이 없는경우 어셈블리생성 안됨(CSW가 없기때문에)
	if(tool_yn == 'N'){
		<%-- 1개 이상의 TOOL이 있어야 어셈블리 생성이 가능합니다. --%>
		alert(LANG_SET["FR000324"])
		return false;
	}
	
	//alert("pop_ins_info_arr.length = " + pop_ins_info_arr.length);
	var csw_arr = $('#tl_csw_arr_pc_'+assem_no).val();
	
	if(csw_arr !== ""){
		csw_arr = csw_arr.slice(0, -1);
		csw_arr = csw_arr.split(",");
	}
	
	if(pop_ins_info_arr !== ""){
		pop_ins_info_arr = pop_ins_info_arr.slice(0, -1);
		pop_ins_info_arr = pop_ins_info_arr.split(",");
	}
	
	//CSW 체크박스 그리기
	$('#div_pop_csw *').remove();
	for(var i=0; i<pop_ins_info_arr.length; i++){
		
		var ins_info = pop_ins_info_arr[i].split('$$');
		var assy_type = ins_info[0];
		var img_path = ins_info[1];
		var matnr = ins_info[2];
		var designation = ins_info[3];
		var grade = ins_info[4];		
		
		var insImgUrl = "<%=UPLOAD_ROOT_PATH %>"+img_path;
		//INS 정보
		var insertSetHtml = '<div class="insertSet">';
		insertSetHtml += '<div class="ipInsert"><div class="left"><img src="' + insImgUrl +'" alt=""  onerror="onErrorImage(this)" style="width: 76px;"></div>';
		insertSetHtml += '<div class="right"><p>' + matnr + '<br>' + designation + '<br>' + grade + '</p></div>';
		insertSetHtml += '</div>';
		
		//CSW체크박스
		var div_id = "div_csw_" + i;
		insertSetHtml += '<div class="inLocation" id="' + div_id + '">';
		for(var k=0; k<csw_arr.length; k++){
			var chk_id = "chk_csw_" + k + "_" + i;
			if(i == 0){
				insertSetHtml += '<label class="chkA"><input type="checkbox" name="chkCSW" id="' + chk_id + '" value="'+matnr+'/'+csw_arr[k]+'" onclick="checkCSW(' + "'" + chk_id + "'" + ' )" checked/><span>' + csw_arr[k] + '</span></label>';
				
			}else{
				insertSetHtml += '<label class="chkA"><input type="checkbox" name="chkCSW" id="' + chk_id + '" value="'+matnr+'/'+csw_arr[k]+'" onclick="checkCSW(' + "'" + chk_id + "'" + ' )"/><span>' + csw_arr[k] + '</span></label>';
			}
		}
		insertSetHtml += '</div></div>'
		
		$('#div_pop_csw').append(insertSetHtml);
	}
	
	assem_no1 = assem_no;
	item_matnr_arr1 = item_matnr_arr;
	item_assy_type_arr1 = item_assy_type_arr;
	
    $(".open_assem_pop").fadeIn();
    
    // Assembly Build Status가 OK(완료) 상태인 경우 기존 Location정보 가지고온다.
    if(buld_sta == "OK"){
        getBuildCompLocation(assem_no);
    }
}

//csw체크박스 클릭
function checkCSW(cswId){
	//alert(cswId);
	var pickCommId = cswId.split('_'); // 체크이벤트를 먹은 체크박스 아이디의 공통 아이디 (ex. chk_csw_0)
	pickCommId = pickCommId[0] + '_' + pickCommId[1] + '_' + pickCommId[2];
	//alert(pickCommId);
	
	var checkedYN = $('[id="' + cswId + '"]').is(':checked'); //클릭한 체크박스의 체크여부 확인
	//alert(checkedYN);
	
	//체크박스 전체 해제하고
	$('[id^="' + pickCommId + '"]').prop('checked',false);
	//클릭한 체크박스의 체크여부 세팅 
	if(checkedYN == false){
		$('[id="' + cswId + '"]').prop('checked',false);	
	}else{
		$('[id="' + cswId + '"]').prop('checked',true);
	}
	
}

// 팝업 confirm 버튼
function doCwsConfirm(){
 	var insCnt = $("#div_pop_csw").children().length; // INS의 갯수 	 	
 	var cswCnt = $("#div_csw_0").children().length; // CSW의 갯수 	
 	var checkedCnt =  $("input:checkbox[name='chkCSW']:checked").length; // 전체 체크박스 중 checked 상태인 갯수
 	
 	//선택한 csw가 사용가능한 csw보다 많으면
 	if(checkedCnt > cswCnt){
 		<%-- 선택하신 CSW가 사용가능한 CSW 갯수보다 많습니다. --%>
 		alert(LANG_SET["FR000325"]);
 		return false;
 	//선택한 csw가 사용가능한 csw보다 적으면	
 	}else if(checkedCnt < cswCnt){
 		<%-- 선택하지 않은 CSW가 있습니다. --%>
 		alert(LANG_SET["FR000326"]);
 		return false;
 	}else{
 		//INS갯수 만큼 돌면서 INS에 선택한 CSW가 하나도 없으면 false반환
 		for(var i=0; i<insCnt; i++){
 			//alert($("#div_csw_" + i).find("input:checkbox[id^='chk_csw']").is(":checked"));
 			var insCswYn = $("#div_csw_" + i).find("input:checkbox[id^='chk_csw']").is(":checked");
 			if(insCswYn == false){
 				<%-- CSW를 선택하지 않은 아이템이 존재합니다. 모든 Insert 아이템은 최소 1개이상의 CSW가 필요합니다. --%>
 				alert(LANG_SET["FR000327"])
 				return false;
 			} 			
 		 	//var rowChkedCnt =  $("input:checkbox[id^=' chk_csw']:checked").length;
 		}
 	}
 	
 	//build Assembly 실행
 	var ins_list = "";
 	for(var i=0; i<insCnt; i++){
		$("#div_csw_" + i).find("input:checkbox[id^='chk_csw']").each(function(index, item){ 
			if($(item).is(":checked")) {
				ins_list = ins_list + "," + $(item).val();	
			}			
		});
	}
 	ins_list = ins_list.substring(1); 	
 	
 	buildAssembly(assem_no1, item_matnr_arr1, item_assy_type_arr1, ins_list);
	
}

//팝업 reset 버튼
function  doCwsReset(){
	$("input:checkbox[name='chkCSW']:checked").prop('checked',false);
	$("#div_csw_0").find("input:checkbox[name='chkCSW']").prop('checked',true);
}

//생성취소
function cancelCreate(buld_st, assem_no) {
	if(buld_st == "") {
		return;	
	}
	var date1 = new Date(buld_st.substring(0, 4), buld_st.substring(5, 7), buld_st.substring(8, 10), buld_st.substring(11, 13), buld_st.substring(14, 16), buld_st.substring(17, 19)); 
	var today = new Date();
	var date2 = new Date(today.getFullYear(), ('0' + (today.getMonth() + 1)).slice(-2), ('0' + today.getDate()).slice(-2), ('0' + today.getHours()).slice(-2), ('0' + today.getMinutes()).slice(-2), seconds = ('0' + today.getSeconds()).slice(-2));
	
	var elapsedMSec = date2.getTime() - date1.getTime(); 
	var elapsedMin = (elapsedMSec / 1000 / 60); 
	elapsedMin = parseInt(elapsedMin); 
	
	if(elapsedMin < 10) {
		<%-- 생성 후 10분 후에 취소할 수 있습니다. --%>	
		alert(LANG_SET["FR000260"]);
		return;
	}
	

	<%-- 생성을 취소하시겠습니까? --%>	
	if(confirm(LANG_SET["FR000259"])){
		var param = {
	    	actionID :"<%=AssemblyConstKey.ACTION_ASSEMBLY_CREATE_CANCEL%>",
	    	assem_no : assem_no,
	        buld_sta : '',
	    };
		
		$.ajax({
	        type:"POST",
	        dataType: "json",
	        data: param,
	        async: true,
	        cache: false,
	        url: "<%=ConstUrl.ASSEMBLY_URL%>",
	        beforeSend:function(){
	        	$('#loadbar').show();
	        },
	       	success: function (result) {   
	       		if(result.resCode == 0){
	       			$("#st_doing_"+assem_no).hide();
	       			$("#st_comp_"+assem_no).hide();
	       			$("#st_error_"+assem_no).hide();
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
}

// Build Status가 OK(완료)인 경우 Location 정보 가져오기
function getBuildCompLocation(assem_no) {
	var param = {
            actionID :"<%=AssemblyConstKey.ACTION_ASSEMBLY_BUILD_COMP_LOCATION%>",
            assem_no : assem_no,
    };
	
	$.ajax({
        type:"POST",
        dataType: "json",
        data: param,
        async: true,
        cache: false,
        url: "<%=ConstUrl.ASSEMBLY_URL%>",
        beforeSend:function(){
        	$('#loadbar').show();
        },
       	success: function (result) {
       		//console.log("location == " + JSON.stringify(result));
       		locationChk(result);
       	},
       	complete:function(){
       		$('#loadbar').fadeOut();
       	},
       	error: function (request, error) {
        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       	}
	});
}

// 기존 Location 정보에 체크하기
function locationChk(result){
	//console.log("locationChk == " + JSON.stringify(result));
	
	// location 체크박스 전체해제
	$('input:checkbox[name="chkCSW"]').prop('checked',false);
	
	// location 체크박스 돌면서 value값과 location result값이 동일하면 체크
	$('input:checkbox[name="chkCSW"]').each(function() {
		//console.log("chkCSW ==== " + this.value);		
		for(var i=0; i<result.length; i++){
			var locValue = result[i].matnr + "/" + result[i].location;
			//console.log("result ==== " + locValue);
			if(this.value == locValue){
				 this.checked = true; //checked 처리
			}
	 	}
	 });
}

</script>
 <!-- +++++++++++++++++++++++++++++ 팝업 표시 ! ++++++++++++++++++++++++++ -->
 <div class="open_pop cart_item_detail" style="z-index:9999">
    <div class="in"  style="max-height:100%;">
        <a href="" class="close_pop"><img style="width:auto;" src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="overflow-y:hidden; max-height:800px; width:1300px;">
        	<iframe src="" style="width:1200px; height:720px;" id="ifr_item_detail">
        	</iframe>
        </div>
    </div>
</div>

<div class="open_cart_pop 3d_pop">
	<div class="in"  style="max-height:100%;width: auto;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="max-height: 825px;">
        	 <iframe id="3d_rendering_pop" src="" style="width:1000px; height:665px;" onerror="onErrorImage(this)">				
			 </iframe>
        </div>
     </div>
</div>

<div class="open_cart_pop 2d_pop">
	<div class="in"  style="max-height:100%;width: auto;">
		<a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt=""></a>
        <div class="pop_cont" style="max-height: 825px;">
        	<div class="pop_img"><img src="" alt="" id="2d_pop_img" onerror="onErrorImage(this)" style="width:1000px; height:665px;"></div>
        </div>
     </div>
</div>

<!-- CSW 팝업 -->
<div class="open_assem_pop">
	<div class="in">
		<a href="#" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt="닫기"></a>
		<div class="pop_cont">
			<div id="div_pop_csw">
			</div>
		</div>

		<div class="btn_group">
			<div class="center">
				<a class="btn01 same"  onclick="doCwsReset()">Reset</a>
				<a class="btn02 same"  onclick="doCwsConfirm()">Confirm</a>
			</div>
		</div>

	</div>
</div>
<!-- pop-insert //-->

