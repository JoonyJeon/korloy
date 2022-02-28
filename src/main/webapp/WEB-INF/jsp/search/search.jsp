 <%----------------------------------------------------------------------------------------
 - 파일이름	: search/search.jsp
 - 설    명		: 검색
 - 추가내용   	:   
 - 버전관리	: 1.0
 ----------------------------------------------------------
 -   Date      Version   Author   Description
 ------------  -----------  ---------  --------------------
 - 2019.05.10    1.0       정세연      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html;charset=utf-8"
	errorPage="/WEB-INF/jsp/common/error/catchException.jsp"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.eaction.framework.common.util.PagingUtil"%>
<%@page import="com.eaction.framework.common.util.DateTimeUtil"%>
<%@page import="com.eaction.framework.common.util.StringUtil"%>
<%@page import="com.eaction.framework.business.logic.search.constant.SearchConstUrl"%>
<%@page import="com.eaction.framework.business.logic.search.constant.SearchConstKey"%>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstKey"%>
<%@page import="com.eaction.framework.business.logic.search.model.SearchInfo"%>
<%@page import="com.eaction.framework.common.model.CodeInfo"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@include file="/WEB-INF/jsp/common/include.inc"%>
<%
List<SearchInfo> cond_main_app_list = (List<SearchInfo>)request.getAttribute("cond_main_app_list");
SearchInfo searchInfo = (SearchInfo)request.getAttribute(ConstKey.SEARCH_DATA);
String pc_yn = (String)request.getAttribute("pc_yn");
if(searchInfo == null){
	searchInfo = new SearchInfo();
}
JSONObject gtc_list = (JSONObject)request.getAttribute("gtc_list");
HashMap gtc_item_cnt = (HashMap)request.getAttribute("gtc_item_cnt");
JSONObject json_gtc_item_cnt =  JSONObject.fromObject(gtc_item_cnt);
%>
<style>
.search_bar {
    max-width: 850px;
    margin: 40px auto;
    border: 2px solid #0094da;
    position: relative;
    border-radius: 10px;
}

.search_bar .input {
    float: left;
    width: 100%;
    position: relative;
}
</style>

<div id="container" class="sub">
	<div class="path">
		<div class="inner">
			<ul>
			    <li class="home" data-lang="FR000137"><%=LangMng.LANG_D("FR000137", session_lang) %></li>
			    <%-- Search Center --%>
				<li data-lang="FR000001"><%= LangMng.LANG_D("FR000001", session_lang)%></li>
			</ul>
		</div>
	</div>
	<div id="contents" class="search_wrap">
		<div class="pop_bg"></div>
		<div class="bg">
			<div class="search_tab">
				<ul>
				    <%-- Global Search --%>
					<li><a onclick="doGlobalClick();" class="on" data-lang="FR000091" id="btnGlobal"><%= LangMng.LANG_D("FR000091", session_lang)%></a></li>
					<%-- Parametric Search --%>
					<li><a href="#" class="open_parametric" data-lang="FR000092" id="btnPara"><%= LangMng.LANG_D("FR000092", session_lang)%></a></li>
				</ul>
			</div>
			<div class="tab_cont" >
				<ul>
					<li>
						<div class="search_bar">
					        <div class="select" style="display:none">
					        </div>
							<div class="input" style="width:100%;border-radius: 10px 10px 10px 10px;">
							    <%-- Enter the model number or classification --%>
								<input type="text" style="margin-left:5px" id="search_auto_input_text" data-lang="FR000006" data-langtype="placeholder" placeholder="<%= LangMng.LANG_D("FR000006", session_lang)%>" autocomplete='off'>
								<button class="btn_clear" onclick="$(this).prev().val('');">지우기</button>
								<button class="btn_search" onclick="doKeyWordSearch()">검색</button>
							</div>
							<div class="automatic"  id="search_automatic" style="display:none;height:300px;OVERFLOW-Y:auto;z-index:9999">
								<ul id="search_automatic_ul">
								</ul>
							</div>
						</div>
						<div class="btn_advanced">
						    <%-- Advanced Search --%>
							<a href="#" data-lang="FR000089" id="btnAdvanced" ><%= LangMng.LANG_D("FR000089", session_lang)%></a>
						</div>
						<div class="categories_wrap">
							<div class="btn_close"  id="search_close">
							    <%-- Close --%>
								<a href="#" data-lang="FR000090"><%= LangMng.LANG_D("FR000090", session_lang)%></a>
							</div>
							<div class="btn_group">
								<div class="center">
									<%-- Reset --%>
									<%-- Search --%>
									<a href="#" class="reset" data-lang="FR000060"><%= LangMng.LANG_D("FR000060", session_lang)%></a> <a href="#" class="search" data-lang="FR000138"><%= LangMng.LANG_D("FR000138", session_lang)%>(0)</a>
								</div>
							</div>
						</div>
						<div class="detail_wrap">
							<div class="inner">
								<div class="detail">
									<ul>
										<li>
											<%-- Main Application --%>
											<h4><%= LangMng.LANG_D("FR000081", session_lang)%></h4>
											<div class="advanced_list">
												<ul id="global_main">
												    <%
												    if(cond_main_app_list != null && cond_main_app_list.size() > 0){
													    for(int nMain=0; nMain<cond_main_app_list.size(); nMain++){
													    	SearchInfo contMain = (SearchInfo)cond_main_app_list.get(nMain);
												    %>
													<li><a href="javascript:doSelectMain('search_<%=contMain.getMa_cd()%>')" id="search_<%=contMain.getMa_cd() %>" data-lang="<%=contMain.getMa_cd()%>"><%=StringUtil.nvl(LangMng.LANG_D(contMain.getMa_cd(), session_lang), contMain.getMa_nm()) %></a></li>
													<%
													    }
												    }
													%>
												</ul>
											</div>
										</li>
										<li>
										    <%-- Sub Application --%>
											<h4 data-lang="FR000118"><%= LangMng.LANG_D("FR000118", session_lang)%></h4>
											<div class="advanced_list">
												<div class="select">
													<select name="search_sub_app" id="search_sub_app" onchange="javascript:doSelectSub();">
													    <%-- Sub Application --%>
														<option value="" ><%= LangMng.LANG_D("FR000118", session_lang)%></option>
													</select>
												</div>
											</div>
										</li>
										<li>
										    <%-- Item Group --%>
											<h4 data-lang="FR000031"><%= LangMng.LANG_D("FR000031", session_lang)%></h4>
											<div class="advanced_list">
												<div class="select">
													<select name="search_item_group" id="search_item_group" onchange="javascript:doSelectItemGroup(this.value);">
														<%-- Item Group --%>
														<option value=""><%= LangMng.LANG_D("FR000031", session_lang)%></option>
													</select>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="btn_close">
							    <%-- Close --%>
								<a href="#" data-lang="FR000090"><%= LangMng.LANG_D("FR000090", session_lang)%></a>
							</div>
							<div class="btn_group">
								<div class="center">
								    <%-- Reset --%>
								    <%-- Search --%>
									<a onclick="javascript:doGlobalReset()" class="reset" data-lang="FR000060"><%= LangMng.LANG_D("FR000060", session_lang)%></a> <a href="javascript:doGlobalSearch();" class="search" id="btnGlobalSearch" data-lang="FR000138"><%= LangMng.LANG_D("FR000138", session_lang)%><span>(0)</span></a>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="parametric_wrap" <%if(ConstKey.KEY_N.equals(pc_yn)){ %>style="overflow:scroll; height:300px;"<%} %>>
							<div class="inner">
								<div class="parametric">
									<div class="categories">
										<ul>
                                            <li>
                                            <%if(gtc_list != null && gtc_list.size() > 0){ %>
                                            <%  JSONObject gtc0 = gtc_list.getJSONObject("CTL"); %>
                                                <li>
                                                    <a onclick="javascript:onClickParaRoot('<%=StringUtil.toComma((String)gtc_item_cnt.get(gtc0.getString("id")))%>');">
                                                        <%if(!"".equals(gtc0.getString("icon"))){ %>
                                                        <span class="ico">
                                                            <img src="<%=gtc0.getString("icon") %>" alt="">
                                                         </span>
                                                         <%} %>
                                                         <%=gtc0.getString("name")%>(<%=StringUtil.toComma((String)gtc_item_cnt.get(gtc0.getString("id")))%>)
                                                    </a>
                                                    <div class="sub_category">
                                                        <ul>
                                                            <% JSONArray childArr = gtc0.getJSONArray("list");%>
                                                            <%if(childArr != null && childArr.size() > 0){ %>
                                                            <%  for(int n=0; n < childArr.size(); n++){ %>
                                                            <%      JSONObject  subInfo = (JSONObject)childArr.get(n);%>
                                                            <%      int sub_cnt = Integer.parseInt(subInfo.getString("sub_cnt")); %>
                                                            <li>
                                                                <%if(sub_cnt > 0){ %>
                                                                <a onclick="javascript:onClickGTC(this);" data-id="<%=subInfo.getString("id")%>" >
                                                                    <%if(!"".equals(subInfo.get("icon"))){ %>
                                                                   <span class="ico"><img src="<%=subInfo.getString("icon") %>" alt=""></span>
                                                                   <%} %>
                                                                   <%=subInfo.getString("name") %>(<%=StringUtil.toComma((String)gtc_item_cnt.get(subInfo.getString("id")))%>)
                                                                </a>
                                                                <div class="sub_category">
                                                                    <ul id="sub_<%=subInfo.getString("id")%>">
                                                                    </ul>
                                                                </div>
                                                                <%}else{ %>
																<div class="check">
                                                                   <%if(!"".equals(subInfo.getString("icon"))){ %>
                                                                   <span class="ico"><img src="<%=subInfo.getString("icon") %>" alt=""></span>
                                                                   <%} %>
																    <label class="chkN">
																        <input type="checkbox" onclick="onClickGTCCheck(this)" data-id="<%=subInfo.getString("id")%>"><span><%=subInfo.getString("name") %>(<%=StringUtil.toComma((String)gtc_item_cnt.get(subInfo.getString("id")))%>)</span>
																    </label>
																 </div>
                                                                <%} %>
                                                            </li>
                                                            <%  } %>
                                                            <%} %>
                                                        </ul>
                                                    </div>
                                                </li>
                                            <%} %>
                                            </li>
                                        </ul>
									</div>
								</div>
								<%if(ConstKey.KEY_Y.equals(pc_yn)){ %>
								<div class="options pc">
									<div class="listTable02">
										<table>
											<colgroup>
												<col width="10%">
												<col width="40%">
												<col width="*">
											</colgroup>
											<tbody id="filterGroup1">
											</tbody>
										</table>
									</div>
									<div class="extended">
									    <%-- Extended Options --%>
										<a href="#" class="extend" data-lang="FR000094"><%= LangMng.LANG_D("FR000094", session_lang)%></a>
										<div class="extended_area">
											<div class="listTable02">
												<table>
													<colgroup>
														<col width="10%">
														<col width="40%">
														<col width="*">
													</colgroup>
													<tbody  id="filterGroup2">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<%}else{ %>
								<div class="options mobile">
									<div class="listTable02">
										<ul id="filterGroup1">
										</ul>
									</div>
									<div class="extended">
									   <%-- Extended Options --%>
										<a href="#" class="extend" data-lang="FR000094"><%= LangMng.LANG_D("FR000094", session_lang)%></a>
										<div class="extended_area">
											<div class="listTable02">
												<ul id="filterGroup2">
												</ul>
											</div>
										</div>
									</div>
								</div>
								<%} %>
							</div>
							<div class="btn_close">
							    <%-- Close --%>
								<a href="#" id="paraSrchClose" data-lang="FR000090"><%= LangMng.LANG_D("FR000090", session_lang)%></a>
							</div>
							<div class="btn_group">
								<div class="center">
								    <%-- Reset --%>
								    <%-- Search --%>
									<a onclick="javascript:doFilterReset();" class="reset" data-lang="FR000060"><%= LangMng.LANG_D("FR000060", session_lang)%></a> 
									<a onclick="javascript:onClickParaSearch();" id="btnParaSearch" class="search" data-lang="FR000138"><%= LangMng.LANG_D("FR000138", session_lang)%><span>(<%=StringUtil.toComma((String)gtc_item_cnt.get("CTL"))%>)</span></a>
								</div>
							</div>
						</div>

					</li>
				</ul>
			</div>
		</div>
		<div class="listview">
			<div class="inner">
			    <%-- Search Result --%>
				<h2 id="totalSearchCntTxt" data-lang="FR000085"><%= LangMng.LANG_D("FR000085", session_lang)%><span style="padding-left:0px;display: inline-block;">(0)</span></h2>
				<div class="view">
					<div class="view_top">
					    <%-- All --%>
						<div class="tit" id="mainAppItemCntTxt" data-lang="FR000028"><%= LangMng.LANG_D("FR000028", session_lang)%><span>(0)</span></div>
						<div class="btn_area">
							<ul>
								<li class="recommend"><select name="it_sort" id="it_sort" onchange="javascript:doSort(this.value);">
										<%-- New --%>
										<option value="NEW_FR_DT DESC" data-lang="FR000030"><%= LangMng.LANG_D("FR000030", session_lang)%></option>
										<%-- Name --%>
										<option value="DESIGNATION" data-lang="FR000017"><%= LangMng.LANG_D("FR000017", session_lang)%></option>
								</select></li>
								<%if(ConstKey.KEY_Y.equals(pc_yn)) {%>
								<li><a href="#" class="on" id="view01"><img
										src="./img/ico_liststyle01.png" alt="텍스트로 보기"></a></li>
								<li><a href="#" id="view02"><img
										src="./img/ico_liststyle02.png" alt="큰썸네일로 보기"></a></li>
								<li><a href="#" id="view03"><img
										src="./img/ico_liststyle03.png" alt="작은썸네일"></a></li>
								<%} %>
							</ul>
						</div>
					</div>
					<%if(ConstKey.KEY_Y.equals(pc_yn)) {%>
					<!-- 큰썸네일 -->
					<div class="view_cont view02">
						<div class="count_wrap">
							<div class="count">
								<%-- Show --%>
								<label data-lang="FR000139"><%= LangMng.LANG_D("FR000139", session_lang)%></label> <select name="pageCount_2" id="pageCount_2" aria-controls="myTable" class="" onchange="javascript:doPageCntChange(this.value);">
									<option value="25">25</option>
									<option value="50">50</option>
									<option value="100">100</option>
									<%-- All --%>
									<option value="" data-lang="FR000028"><%= LangMng.LANG_D("FR000028", session_lang)%></option>
								</select>
							</div>
							<div class="excell">
							    <%-- Create Excel --%>
								<a onclick="doExcelDown();"><i class="fa fa-file-excel-o"></i> <span data-lang="FR000080"><%= LangMng.LANG_D("FR000080", session_lang)%></span></a>
							</div>
						</div>
						<div class="search_list">
							<ul id="view_02_list">
							</ul>
						</div>
					</div>
					<!-- 리스트 -->
					<div class="view_cont view01">
						<div class="count_wrap">
                            <div class="count">
                                <%-- Show  --%>
                                <label data-lang="FR000139"><%= LangMng.LANG_D("FR000139", session_lang)%></label> <select name="pageCount_1" id="pageCount_1" aria-controls="myTable" class="" onchange="javascript:doPageCntChange(this.value);">
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <%-- All --%>
                                    <option value="" data-lang="FR000028"><%= LangMng.LANG_D("FR000028", session_lang)%></option>
                                </select>
                            </div>
							<div class="excell">
								<%-- Create Excel --%>
								<a onclick="doExcelDown();"><i class="fa fa-file-excel-o"></i><span data-lang="FR000080"> <%= LangMng.LANG_D("FR000080", session_lang)%></span></a>
							</div>
						</div>
						<div class="listTable03">
							<table id="view_01_list">
								<thead>
									<colgroup>
										<col width="12%">
										<col width="10%">
										<col width="10%">
										<col width="*">
							<!--		<col width="10%">	-->
										<col width="10%">
										<col width="20%">
									</colgroup>
								    <tr>
                                        <%-- Order No --%>
                                        <th data-lang="FR000038"><%= LangMng.LANG_D("FR000038", session_lang)%></th>
                                        <%-- Cart --%>
                                        <th data-lang="FR000040"><%= LangMng.LANG_D("FR000040", session_lang)%></th>
                                        <%-- New --%>
                                        <th data-lang="FR000030"><%= LangMng.LANG_D("FR000030", session_lang)%></th>
                                        <%-- Designation --%>
                                        <th data-lang="FR000039"><%= LangMng.LANG_D("FR000039", session_lang)%></th>
                             <!--           <%-- Grade --%>  -->
                             <!--           <th data-lang="FR000041"><%= LangMng.LANG_D("FR000041", session_lang)%></th>   -->
                                        <%-- Item Group --%>
                                        <th data-lang="FR000031"><%= LangMng.LANG_D("FR000031", session_lang)%></th>
                                        <%-- Download --%>
                                        <th data-lang="FR000053"><%= LangMng.LANG_D("FR000053", session_lang)%></th>
                                    </tr>
								</thead>
								<tbody  id="view_01_list_tbody">
									<tr>
									    <%--검색 결과가 없습니다.  --%>
                                        <td colspan="6" data-lang="FR000136"><%= LangMng.LANG_D("FR000136", session_lang)%></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- 작은썸네일 -->
					<div class="view_cont view03">
						<div class="count_wrap">
							<div class="count">
                                <%-- Show --%>
                                <label data-lang="FR000139"><%= LangMng.LANG_D("FR000139", session_lang)%></label> <select name="pageCount_3" id="pageCount_3" aria-controls="myTable" class="" onchange="javascript:doPageCntChange(this.value);">
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <%--All  --%>
                                    <option value="" data-lang="FR000028"><%= LangMng.LANG_D("FR000028", session_lang)%></option>
                                </select>
							</div>
							<div class="excell">
							    <%-- Create Excel --%>
								<a onclick="doExcelDown();"><i class="fa fa-file-excel-o"></i><span data-lang="FR000080"> <%= LangMng.LANG_D("FR000080", session_lang)%></span></a>
							</div>
						</div>
						<div class="listTable03">
							<table  id="view_03_list">
                                <thead>
									<colgroup>
										<col width="12%">
										<col width="5%">
										<col width="5%">
										<col width="5%">
										<col width="20%">
										<col width="10%">
										<col width="10%">
									</colgroup>
                                    <tr>
                                        <%-- Order No --%>
                                        <th><%= LangMng.LANG_D("FR000038", session_lang)%></th>
                                        <%-- Image --%>
                                        <th><%= LangMng.LANG_D("FR000032", session_lang)%></th>
                                        <%-- Cart --%>
                                        <th><%= LangMng.LANG_D("FR000040", session_lang)%></th>
                                        <%-- New --%>
                                        <th><%= LangMng.LANG_D("FR000030", session_lang)%></th>
                                        <%-- Designation --%>
                                        <th><%= LangMng.LANG_D("FR000039", session_lang)%></th>
<!--                                        <%-- Grade --%> 	-->

                                        <%-- Item Group --%>
                                        <th><%= LangMng.LANG_D("FR000031", session_lang)%></th>
                                        <%-- Download --%>
                                        <th><%= LangMng.LANG_D("FR000053", session_lang)%></th>
                                    </tr>
								</thead>
								<tbody  id="view_03_list_tbody">
									<tr>
									    <%--검색 결과가 없습니다.  --%>
										<td colspan="8" data-lang="FR000136"><%= LangMng.LANG_D("FR000136", session_lang)%></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<%}else{ %>
					<!-- 모바일 -->
					<div class="view_cont view04" style="display:block">
						<div class="count_wrap">
							<div class="count">
                                <%-- Show --%>
                                <label data-lang="FR000139"><%= LangMng.LANG_D("FR000139", session_lang)%></label> <select name="pageCount_4" id="pageCount_4" aria-controls="myTable" class="" onchange="javascript:doPageCntChange(this.value);">
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <%--All  --%>
                                    <option value="" data-lang="FR000028"><%= LangMng.LANG_D("FR000028", session_lang)%></option>
                                </select>
							</div>
							<div class="excell">
							     <%--Create Excel  --%>
								<a onclick="doExcelDown();"><i class="fa fa-file-excel-o"></i><span data-lang="FR000080"><%= LangMng.LANG_D("FR000080", session_lang)%></span></a>
							</div>
						</div>
	                    <div class="box_cont mobile" id="view_04_list">
	                    </div>
					</div>
					<%} %>
				</div>
				<div class="more">
				    <%-- More --%>
					<a href="javascript:getMoreList();" class="btn_more"  id="moreCount" data-lang="FR000034"></a>
					<a href="javascript:getMoreAll();" class="btn_more"  id="moreAll" data-lang="FR000249"></a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 장바구니팝업 -->
<div class="open_cart_pop cart_pop01">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt="닫기"></a>
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
                <%-- ADD --%>
                <li><a onclick="newCartPop()" class="add" data-lang="FR000066"><%= LangMng.LANG_D("FR000066", session_lang)%></a></li>
                <%-- Save --%>
                <li><a onclick="saveCartItem()" class="save open_save"  data-lang="FR000064"><%= LangMng.LANG_D("FR000064", session_lang)%></a></li>
            </ul>
        </div>
    </div>
</div>
   
<div class="open_cart_pop cart_pop02">
    <div class="in">
        <a onclick="" class="close_pop"><img src="<%=PATH_IMG%>/btn_close.png" alt="닫기"></a>
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
<!-- 더보기 처리를 위해 -->
<form id="myForm" name="myForm" method="POST" action="<%=ConstUrl.SEARCH_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="ma_cd" id="ma_cd" value="<%=searchInfo.getMa_cd() %>" />
    <input type="hidden" name="sa_cd" id="sa_cd" value="<%=searchInfo.getSa_cd() %>" />
    <input type="hidden" name="ig_cd" id="ig_cd" value="<%=searchInfo.getIg_cd() %>" />
    <input type="hidden" name="matnr" id="matnr" value="<%=searchInfo.getMatnr() %>" />
    <input type="hidden" name="auto_mode" id="auto_mode" value="<%=searchInfo.getAuto_mode() %>" />
    <input type="hidden" name="orderSort" id="orderSort" />
    <input type="hidden" name="pageCount" id="pageCount" />
    <input type="hidden" name="remainCount" id="remainCount" />
    <input type="hidden" name="isPageYn" id="isPageYn" value="Y"/>
    <input type="hidden" name="startIndex" id="startIndex"  />
    <input type="hidden" name="auto_input_text" id="auto_input_text" />    
</form>

<form id="paraForm" name="paraForm" method="POST" action="<%=ConstUrl.SEARCH_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="filterParam" id="filterParam" />
    <input type="hidden" name="gtc_vd_id" id="gtc_vd_id"  />
    <input type="hidden" name="orderSort" id="orderSort" />
    <input type="hidden" name="pageCount" id="pageCount" />
    <input type="hidden" name="remainCount" id="remainCount" />
    <input type="hidden" name="isPageYn" id="isPageYn" value="Y"/>
    <input type="hidden" name="startIndex" id="startIndex"  />
    <input type="hidden" name="auto_input_text" id="auto_input_text"  />    
</form>
<form id="itemGoForm" name="itemGoForm" method="POST" action="<%=ConstUrl.APP_ITEM_GRP_LIST_URL%>">
    <input type="hidden" name="ma_cd" id="ma_cd"  />
    <input type="hidden" name="sa_cd" id="sa_cd"  />
    <input type="hidden" name="ig_cd" id="ig_cd"  />
    <input type="hidden" name="ig_nm" id="ig_nm"  />
    <input type="hidden" name="matnr" id="matnr" />
    <input type="hidden" name="designation" id="designation"  />
    <input type="hidden" name="item_view_yn" id="item_view_yn" value="Y" />
</form>
<form id="downForm" name="downForm" method="POST" action="<%=ConstUrl.SEARCH_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.ACTION_GET_FILE%>"/>
    <input type="hidden" name="atch_file_url" id="atch_file_url" />
    <input type="hidden" name="atch_file_name" id="atch_file_name" />
</form>
<script type="text/javascript">
var pc_yn = "<%=pc_yn%>";
var gtc_list = JSON.parse(JSON.stringify(<%=gtc_list.toString()%>));
var gtc_item_cnt = JSON.parse(JSON.stringify(<%=json_gtc_item_cnt.toString()%>));
var jsonFilter;
var filterParamList = new Array();
var sel_GTC = "";
var searchMode = "";
jQuery(document).ready(function(){
    // 정렬순서
    $("#myForm #orderSort").val($("#it_sort option:selected").val());
    $("#paraForm #orderSort").val($("#it_sort option:selected").val());
	// 페이지 카운트 초기값
	if(pc_yn == "Y"){
		$("#myForm #pageCount").val($("#pageCount_1 option:selected").val());
		$("#paraForm #pageCount").val($("#pageCount_1 option:selected").val());
	}else{
		$("#myForm #pageCount").val($("#pageCount_4 option:selected").val());
		$("#paraForm #pageCount").val($("#pageCount_4 option:selected").val());
	}
    // 페이지 번호 초기값
	$("#myForm #startIndex").val("0");
	setMoreCount("0");
	<%if(!"".equals(searchInfo.getMa_cd()) || !"".equals(searchInfo.getAuto_input_text())){%>
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    var param = {
            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
            startIndex : Number($("#myForm #startIndex").val()),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn :  $("#myForm #isPageYn").val(),
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            ig_cd : $("#myForm #ig_cd").val(),
            matnr : $("#myForm #matnr").val(),
            unit_cd : $("#btnSizeUnit").val(),
            auto_input_text : "<%=searchInfo.getAuto_input_text()%>"
    };
    doSearch(param);
    if($("#myForm #auto_mode").val() == "A"){
        $("#search_auto_input_text").val("<%=searchInfo.getAuto_complete_text()%>");
    }else{
        $("#search_auto_input_text").val("<%=searchInfo.getAuto_input_text()%>");
        $("#myForm #auto_input_text").val("<%=searchInfo.getAuto_input_text()%>");
    }
	<%}else{%>
    $('.loading').delay('1000').fadeOut();
	<%}%>
	
	$(document).mouseup(function(e){
	    var autoLayer = $('#search_automatic');
	    if(autoLayer.has(e.target).length == 0){
	        $("#search_automatic_ul *").remove();
	        $("#search_automatic").hide();
		}
	});

	$("#paraSrchClose").click(function(e){
		$("#btnGlobal").trigger("click");
	});

	$("#btnAdvanced").click(function(e){
	     $("#btnGlobal").trigger("click");
	 });
});

function doItemShow(obj){
    $(".mobileDetailItem").hide();
    $(obj).next(".mobileDetailItem").slideToggle();
    return false;
}
function doAdvanceInit(){
	
}

function showNotAuthAlert(){
	alert(LANG_SET.FR000229);
}

function onClickParaRoot(cnt){
	$("#mainAppItemCntTxt").html(LANG_SET.FR000028 + '<span>('+cnt+')</span>');
}
//Cart 목록 조회
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
        if(cartList.length > 0){
            for(var i = 0; i<cartList.length; i++){
                var option = '<option value="' + cartList[i].cart_no +'">'+cartList[i].cart_nm+'</option>';
                $("#select_cart_list").append(option);                  
            }
        }
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

//장바구니 추가 Save버튼 클릭
function saveCartItem(){
    var cartNo = $("#select_cart_list").val();
    var itemMatnr = $("#select_item_matnr").val();  
    //console.log("Cart = " + cartNo + " / " + "MATNR = " + itemMatnr);
    
    if(cartNo){
        insertCartItem(cartNo, "", itemMatnr);
    }else{
        <%-- 장바구니를 선택하세요 --%>
        alert( LANG_SET["FR000128"] );
    }
}


//신규 장바구니 추가  Save버튼 클릭
function saveNewCartItem(){
    var itemMatnr = $("#select_item_matnr").val(); //아이템번호  
    var newCartNm = $("#new_cart_name").val(); //장바구니 그룹명
    
    if(newCartNm){
        insertCartItem("", newCartNm, itemMatnr);
    }else{
        <%-- 장바구니 그룹명을 입력하세요 --%>
        alert( LANG_SET["FR000127"] );
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
            if(result.resCode == '0'){
                <%-- 장바구니에 담았습니다 --%>
                alert( LANG_SET["FR000129"] );
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

function doFilterReset(){
    $.each(jsonFilter, function(index, data) {
         if("IN" == data.sch_typ){
             $("#filter_"+data.prop_iso).val('');
         }else if("CE" == data.sch_typ){
             var ce_on = "";
             $("#filter_"+data.prop_iso+"_ce1").removeAttr("class");
             $("#filter_"+data.prop_iso+"_ce3").removeAttr("class");
             $("#filter_"+data.prop_iso+"_ce2").attr("class", "on");
             $("#filter_"+data.prop_iso+"_2"+'_div').show();
             $("#filter_"+data.prop_iso+"_1").val('');
             $("#filter_"+data.prop_iso+"_2").val('');
         }else{
             $("#filter_"+data.prop_iso+" option:eq(0)").prop("selected",true);
         }
     });
}

function doGlobalClick(){
	if($('#btnPara').is(":visible") == true){
		$(".parametric_wrap").slideUp();
        $("#contents .pop_bg").fadeOut("fast");
	}
}
function onClickGTC(obj){
    // 선택한 GTC아이디
	var gtc_id = $(obj).data("id");
	var sel_gtc = gtc_list[gtc_id];
	var gtc_html = "";
	$.each(sel_gtc.list, function(index, data) {
		var id = data.id;
	    var name = data.name;
	    var icon = data.icon;
	    var sub_cnt = Number(data.sub_cnt);
	    var icon_html = "";
	    if(icon != ""){
	    	icon_html = '<span class="ico"><img src="'+icon+'" alt=""></span>';
		}
		gtc_html = gtc_html + '<li>';
        if(sub_cnt > 0){
        	gtc_html = gtc_html + '<a onclick="javascript:onClickGTC(this)" data-id="'+id+'">'+ icon_html + name +'(' + addCommas(gtc_item_cnt[id]) + ')'+
            '</a>'+
            '<div class="sub_category">'+
            '<ul id="sub_'+id+'">'+
            '</ul>'+
            '</div>';
        }else{
        	gtc_html = gtc_html + '<div class="check">'+ icon_html +
            '<label class="chkN">'+
            '<input type="checkbox" onclick="onClickGTCCheck(this)" data-id="'+id+'"><span>'+name+'('+addCommas(gtc_item_cnt[id])+')</span>'+
            '</label>'+
            '</div>';
        }
        gtc_html = gtc_html + '</li>';
	});
	$(obj).next().children('ul').append(gtc_html).trigger("create");
    if($(obj).next().css("display") == "none"){
        $(obj).next().show();
        $(obj).css({"background":"url(/img/bg_select03_2.png)  no-repeat left"});
    }else{
        $(obj).next().hide();
        $(obj).next().children('ul').empty();
        $(obj).css({"background":"url(/img/bg_select03.png)  no-repeat left"});
    }
    var sel_vd_item_cnt = addCommas(gtc_item_cnt[gtc_id]);
    $("#btnParaSearch").html(LANG_SET.FR000138 + '<span>('+sel_vd_item_cnt+')</span>');
    $("#mainAppItemCntTxt").html(LANG_SET.FR000028 + '<span>('+sel_vd_item_cnt+')</span>');
    
    getParamFilter(gtc_id);
    // 이전 체크값 해제
    if($(preChkObj).is(":checked") == true){
          $(preChkObj).prop("checked", false);
    }
}
var preChkObj;
function onClickGTCCheck(obj){
	if($(obj).is(":checked") == true){
		getParamFilter($(obj).data("id"));
	    // 이전 체크값 해제
	    if(obj != preChkObj && $(preChkObj).is(":checked") == true){
	          $(preChkObj).prop("checked", false);
	    }
		preChkObj = obj
	}else{
        $("#filterGroup1").empty();
        $("#filterGroup2").empty();
	}
}

function getParamFilter(vd_id){

	sel_GTC = vd_id;
    var param = {
            actionID :"<%=SearchConstKey.ACTION_GET_SEARCH_FILTER%>",
            gtc_vd_id : vd_id,
    };
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function(d){
            if("Y" == pc_yn){
	            $("#filterGroup1").empty();
	            $("#filterGroup2").empty();
	            $("#filterGroup1").append(d.filterHtml.html1).trigger("create");
               if(d.filterHtml.html2 != null && d.filterHtml.html2 != ""){
                    $("#filterGroup2").append(d.filterHtml.html2).trigger("create");
                }
             }else{
	            $("#filterGroup1 *").remove();
                $("#filterGroup2 *").remove();
                
	            $("#filterGroup1").append(d.filterHtml.html1).trigger("create");
	            if(d.filterHtml.html2 != null && d.filterHtml.html2 != ""){
	                $("#filterGroup2").append(d.filterHtml.html2).trigger("create");
		        }
            }
            // 필터 파라미터 재설정
            if(d.filterList != null && d.filterList != ""){
                jsonFilter = JSON.parse(JSON.stringify(d.filterList));
            }
        }, error: function() {
            $('#loadbar').fadeOut();
        },
        complete:function(){
            $('#loadbar').fadeOut();
        }
    });
}

function makeFilterParam(){
    filterParamList = [];
    var filterParam = null;
    $.each(jsonFilter, function(index, data) {
        filterParam = new Object();
        filterParam.key = data.prop_iso;
        filterParam.type = data.sch_typ;
        if("IN" == data.sch_typ){
          filterParam.value = $("#filter_"+data.prop_iso).val();
        }else if("CE" == data.sch_typ){
            var ce_on = "";
            if($("#filter_"+data.prop_iso+"_ce1").attr("class") == "on"){
                ce_on = "CE1";
            }else if($("#filter_"+data.prop_iso+"_ce2").attr("class") == "on"){
                ce_on = "CE2";
            }else if($("#filter_"+data.prop_iso+"_ce3").attr("class") == "on"){
                ce_on = "CE3";
            }
            filterParam.type = ce_on;
            if($("#filter_"+data.prop_iso+"_1").val() != "" && $("#filter_"+data.prop_iso+"_2").val() != ""){
                filterParam.value = $("#filter_"+data.prop_iso+"_1").val() + "|" + $("#filter_"+data.prop_iso+"_2").val();
            }else if($("#filter_"+data.prop_iso+"_1").val() != "" && $("#filter_"+data.prop_iso+"_2").val() == ""){
                filterParam.value = $("#filter_"+data.prop_iso+"_1").val();
            }else{
                filterParam.value = "";
            }
        }else{
            filterParam.value = $("#filter_"+data.prop_iso+" option:selected").val();
        }
        filterParamList.push(filterParam);  
    });
}

function doFilterCeClick(obj){
    $(obj).attr('class', 'on');
    var filter_type = $(obj).data('type');
    var filter_prop = $(obj).data('prop');
    var ce1 = '#filter_'+filter_prop+'_ce1';
    var ce2 = '#filter_'+filter_prop+'_ce2';
    var ce3 = '#filter_'+filter_prop+'_ce3';
    var ce_input_2 = '#filter_'+filter_prop+'_2';
    if('ce1' == filter_type){
        $(ce2).removeAttr('class');
        $(ce3).removeAttr('class');
        $(ce_input_2).val('');
        $(ce_input_2+'_div').hide();
    }else if ('ce2' == filter_type){
        $(ce1).removeAttr('class');
        $(ce3).removeAttr('class');
        $(ce_input_2+'_div').show();
    }else{
        $(ce1).removeAttr('class');
        $(ce2).removeAttr('class');
        $(ce_input_2).val('');
        $(ce_input_2+'_div').hide();
    }
}
function doSort(sort){
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    
	$("#myForm #startIndex").val("0");
	$("#myForm #isPageYn").val("Y");
	$("#myForm #orderSort").val(sort);
    $("#paraForm #startIndex").val("0");
    $("#paraForm #isPageYn").val("Y");
    $("#paraForm #orderSort").val(sort);

    if("G" == searchMode){
	    var param = {
	            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
	            startIndex : Number($("#myForm #startIndex").val()),
	            orderSort : $("#myForm #orderSort").val(),
	            pageCount : $("#myForm #pageCount").val(),
	            isPageYn :  $("#myForm #isPageYn").val(),
	            ma_cd : $("#myForm #ma_cd").val(),
	            sa_cd : $("#myForm #sa_cd").val(),
	            ig_cd : $("#myForm #ig_cd").val(),
	            matnr : $("#myForm #matnr").val(),
	            unit_cd : $("#btnSizeUnit").val(),
	            auto_input_text : $("#myForm #auto_input_text").val()
	    };
	    doSearch(param);
    }else if("P" == searchMode){
    	doParametricSearch();
    }
}

function getMoreList(){
	if("G" == searchMode){
		if(Number($("#myForm #remainCount").val()) > 0){
		    var startIndex = Number($("#myForm #startIndex").val()) + 1;
		    $("#myForm #startIndex").val(startIndex);
		    $("#myForm #isPageYn").val("Y");
		    var param = {
		            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
		            startIndex : Number($("#myForm #startIndex").val()),
		            orderSort : $("#myForm #orderSort").val(),
		            pageCount : $("#myForm #pageCount").val(),
		            isPageYn :  $("#myForm #isPageYn").val(),
		            ma_cd : $("#myForm #ma_cd").val(),
		            sa_cd : $("#myForm #sa_cd").val(),
		            ig_cd : $("#myForm #ig_cd").val(),
		            matnr : $("#myForm #matnr").val(),
		            unit_cd : $("#btnSizeUnit").val(),
		            auto_input_text : $("#myForm #auto_input_text").val(),
		    };
		    doSearch(param);
		}
    }else if("P" == searchMode){
    	if(Number($("#paraForm #remainCount").val()) > 0){
            var startIndex = Number($("#paraForm #startIndex").val()) + 1;
            $("#paraForm #startIndex").val(startIndex);
            $("#paraForm #isPageYn").val("Y");
            doParametricSearch();
    	}
    }
}

function getMoreAll(){
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    
    if("G" == searchMode){
        $("#myForm #isPageYn").val("N");
    	if(Number($("#myForm #remainCount").val()) > 0){
            var startIndex = Number($("#myForm #startIndex").val()) + 1;
            $("#myForm #startIndex").val(startIndex);
            var param = {
                    actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
                    startIndex : Number($("#myForm #startIndex").val()),
                    orderSort : $("#myForm #orderSort").val(),
                    pageCount : $("#myForm #pageCount").val(),
                    isPageYn :  $("#myForm #isPageYn").val(),
                    ma_cd : $("#myForm #ma_cd").val(),
                    sa_cd : $("#myForm #sa_cd").val(),
                    ig_cd : $("#myForm #ig_cd").val(),
                    matnr : $("#myForm #matnr").val(),
                    unit_cd : $("#btnSizeUnit").val(),
                    auto_input_text : $("#myForm #auto_input_text").val(),
            };
            doSearch(param);
        }
    }else if("P" == searchMode){
        $("#paraForm #isPageYn").val("N");
        if(Number($("#paraForm #remainCount").val()) > 0){
            doParametricSearch();
            var startIndex = Number($("#paraForm #startIndex").val()) + 1;
            $("#paraForm #startIndex").val(startIndex);
        }
    }	
}

function doGlobalSearch(){
	if($("#search_sub_app  option:selected").val() != ""){
		   $("#myForm #sa_cd").val($("#search_sub_app  option:selected").val());
		   $("#myForm #matnr").val("");
	}
	if($("#search_item_group  option:selected").val() != ""){
		   $("#myForm #ig_cd").val($("#search_item_group  option:selected").val());
		   $("#myForm #matnr").val("");
	}
	$("#myForm #startIndex").val("0");
	$("#myForm #isPageYn").val("Y");
	$("#view_01_list_tbody").empty();
	$("#view_02_list *").remove();
	$("#view_03_list_tbody").empty();
	$("#view_04_list *").remove();
	
	if($("#myForm #auto_mode").val() == "M"){
		var search_input_text = $("#search_auto_input_text").val();
		if(search_input_text != ""){
			$("#myForm #auto_input_text").val(search_input_text);
		}
	}
    var param = {
            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
            startIndex : Number($("#myForm #startIndex").val()),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn :  $("#myForm #isPageYn").val(),
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            ig_cd : $("#myForm #ig_cd").val(),
            matnr : $("#myForm #matnr").val(),
            unit_cd : $("#btnSizeUnit").val(),
            auto_input_text : $("#myForm #auto_input_text").val(),
    };
	doSearch(param);
    $(".search_wrap .btn_close a").click();
}
function onClickParaSearch(){
    $("#paraForm #startIndex").val("0");
    $("#paraForm #isPageYn").val("Y");
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    $("#btnGlobal").trigger("click");
    doParametricSearch();
}

function doParametricSearch(){
    if(sel_GTC == ""){
        return false;
    }
    searchMode = "P";
	$("#paraForm #gtc_vd_id").val(sel_GTC);
	makeFilterParam();
	$("#paraForm #filterParam").val(JSON.stringify(filterParamList));
	doLog("EC0004", "", sel_GTC);
    var param = {
            actionID :"<%=SearchConstKey.ACTION_PARA_SEARCH_LIST%>",
            startIndex : Number($("#paraForm #startIndex").val()),
            orderSort : $("#paraForm #orderSort").val(),
            pageCount : $("#paraForm #pageCount").val(),
            isPageYn :  $("#paraForm #isPageYn").val(),
            unit_cd : $("#btnSizeUnit").val(),
            filterParam : JSON.stringify(filterParamList),
            gtc_vd_id : sel_GTC
    };
    
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function(d){
            doMakeList(d);
            $("#btnParaSearch").text("<%= LangMng.LANG_D("FR000138", session_lang)%>("+d.total_cnt+")");
        }, error: function() {
            $('#loadbar').fadeOut();
        },
        complete:function(){
            $('#loadbar').fadeOut();
            $('.btn_close').click();
        }
    });
}
// 글로벌 검색
function doSearch(param){
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        beforeSend:function(){
        	$('#loadbar').show();
        },
        success: function(d){
        	searchMode = "G";
        	doMakeList(d);
        	$("#btnGlobalSearch").html(LANG_SET.FR000138 + '<span>('+d.total_cnt+')</span>');
        }, error: function() {
        	$('#loadbar').fadeOut();
        },
        complete:function(){
        	$('#loadbar').fadeOut();
        	$('.btn_close').click();
        }
    });
}
function doMakeList(dataRow){
    var view_01_new = "";
    var view_02_new = "";
    var view_03_new = "";
    var view_04_new = "";
    var view_01_list = "";
    var view_02_list = "";
    var view_03_list = "";
    var view_04_list = "";
    $.each(dataRow.list, function(index, data) {
    	console.log('data')
    	console.log(data)
        var img_url = "<%=UPLOAD_ROOT_PATH%>" + data.item_image_thumb;
        var matnr = data.matnr;
        var ig_cd = data.ig_cd;
        var new_yn = data.new_yn;
        var designation = data.designation;
        var grade = data.grade;
        var view_02_grade = '';
        var ig_nm = data.ig_nm;
        var stp_file = data.stp_file;
        var stp_file_name = data.stp_file_name;
        var dxf_file = data.dxf_file;
        var dxf_file_name = data.dxf_file_name;
        var stp_div_01 = "";
        var dxf_div_01 = "";
        var stp_div_02 = "";
        var dxf_div_02 = "";
        var stp_div_03 = "";
        var dxf_div_03 = "";
        var stp_div_04 = "";
        var dxf_div_04 = "";
        var view_01_cart = "";
        var view_02_cart = "";
        var view_03_cart = "";
        var view_04_cart = "";
        view_01_cart = '<a onclick="doItemCart(this)" class="btn_cart" data-id="'+matnr+'"><img src="/img/ico_cartplus.png" alt="cart"></a>';
        view_02_cart = '<a onclick="doItemCart(this)" class="btn_cart" data-id="'+matnr+'"><img src="./img/ico_cart02.png" alt="cart"></a>';
        view_03_cart = '<a onclick="doItemCart(this)" class="btn_cart" data-id="'+matnr+'"><img src="/img/ico_cartplus.png" alt="cart"></a>';
        view_04_cart = '<a onclick="doItemCart(this)" class="btn_cart" data-id="'+matnr+'"><img src="/img/ico_cart.png" alt="cart"></a>';
        
        if("Y" == new_yn){
            view_01_new = '<img src="/img/ico_new.png" alt="new">';
            view_02_new = '<div class="number"><span class="new">NEW</span></div>';
            view_03_new = '<img src="/img/ico_new.png" alt="new">';
            view_04_new = '<span class="new">NEW</span>';
        }
        // 3D 파일 권한 체크
        if(USERAUTH.isStpDownAuth){
	        if(stp_file != ""){
	            stp_div_01 = '<a href="'+stp_file+'" data-url="'+stp_file+'" data-name="'+stp_file_name+'" class="gtc" download >STP</a>';
	            stp_div_02 = '<a href="'+stp_file+'" data-url="'+stp_file+'" data-name="'+stp_file_name+'" class="step"download >STP</a>';
	            stp_div_03 = '<a href="'+stp_file+'" data-url="'+stp_file+'" data-name="'+stp_file_name+'" class="gtc" download >STP</a>';
	            stp_div_04 = '<a href="'+stp_file+'" data-url="'+stp_file+'" data-name="'+stp_file_name+'" class="step" download >STP</a>';
	        }else{
	            stp_div_02 = '<a style="padding: 13.5px 30px 13.5px 40px;display: inline-block;"></a>';
	        }
        }else{
	        var userId = USERINFO.user_id;
	        if(userId == ""){
	            if(stp_file != ""){
	                stp_div_01 = '<a onclick="doLoginShow();" class="gtc">STP</a>';
	                stp_div_02 = '<a onclick="doLoginShow();" class="step">STP</a>';
	                stp_div_03 = '<a onclick="doLoginShow();" class="gtc" >STP</a>';
	                stp_div_04 = '<a onclick="doLoginShow();" class="step">STP</a>';
	            }else{
	                stp_div_02 = '<a style="padding: 13.5px 30px 13.5px 40px;display: inline-block;"></a>';
	            }
	        }else{
	            if(stp_file != ""){
	                stp_div_01 = '<a onclick="showNotAuthAlert();" class="gtc">STP</a>';
	                stp_div_02 = '<a onclick="showNotAuthAlert();" class="step">STP</a>';
	                stp_div_03 = '<a onclick="showNotAuthAlert();" class="gtc">STP</a>';
	                stp_div_04 = '<a onclick="showNotAuthAlert();" class="step">STP</a>';
	            }else{
	                stp_div_02 = '<a style="padding: 13.5px 30px 13.5px 40px;display: inline-block;"></a>';
	            }
	        }
        }
        if(USERAUTH.isDxfDownAuth){
	        if(dxf_file != ""){
	            dxf_div_01 = '<a href="'+dxf_file+'" data-url="'+dxf_file+'" data-name="'+dxf_file_name+'" class="dxf" download >DXF</a>';
	            dxf_div_02 = '<a href="'+dxf_file+'" data-url="'+dxf_file+'" data-name="'+dxf_file_name+'" class="dxf"download >DXF</a>';
	            dxf_div_03 = '<a href="'+dxf_file+'" data-url="'+dxf_file+'" data-name="'+dxf_file_name+'" class="dxf"download >DXF</a>';
	            dxf_div_04 = '<a href="'+dxf_file+'" data-url="'+dxf_file+'" data-name="'+dxf_file_name+'" class="dxf"download >DXF</a>';
	        }
        }else{
	        var userId = USERINFO.user_id;
	        if(userId == ""){
	            if(dxf_file != ""){
	                dxf_div_01 = '<a onclick="doLoginShow();" class="dxf">DXF</a>';
	                dxf_div_02 = '<a onclick="doLoginShow();" class="dxf">DXF</a>';
	                dxf_div_03 = '<a onclick="doLoginShow();" class="dxf">DXF</a>';
	                dxf_div_04 = '<a onclick="doLoginShow();" class="dxf">DXF</a>';
	            }
	        }else{
	            if(dxf_file != ""){
	                dxf_div_01 = '<a onclick="showNotAuthAlert();" class="dxf">DXF</a>';
	                dxf_div_02 = '<a onclick="showNotAuthAlert();" class="dxf">DXF</a>';
	                dxf_div_03 = '<a onclick="showNotAuthAlert();" class="dxf">DXF</a>';
	                dxf_div_04 = '<a onclick="showNotAuthAlert();" class="dxf">DXF</a>';
	            }
	        }
        }
        if("" != grade){
            view_02_grade = '<li><em>Grade</em>'+grade+'</li>';
        }else{
            view_02_grade = '<li><em>Grade</em>No Grade</li>';
        }
        if(pc_yn == "Y"){
            view_01_list = view_01_list + '<tr><td style="cursor:pointer; text-decoration:underline;color: #0094da;" onclick="showItemDetail(this)" data-matnr="'+matnr+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">'+matnr+'<td>'+view_01_cart+'</td><td>'+view_01_new+ '</td>' +
            '<td style="cursor:pointer; text-decoration:underline;color: #0094da;" onclick="showItemDetail(this)" data-matnr="'+matnr+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">'+designation+'</td><td style="text-align:left">'+ig_nm+'</td><td><div class="download">'+stp_div_01 + dxf_div_01+
            '</div></td></tr>';
            var item_group_name = toTruncate(ig_nm, 10, '...');
            view_02_list = view_02_list + '<li>'+view_02_new + '<div class="img" style="height:120px"><img src="'+img_url+'" alert="'+designation+'" onerror="onErrorImage(this);"></div>'+
            '<div class="list"><ul>'+view_02_grade+'<li><em>Item Group</em>'+item_group_name+'</li></ul></div>'+
            '<div class="name" style="cursor:pointer" onclick="showItemDetail(this)" data-matnr="'+matnr+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">' +designation +'</div><div class="down">'+stp_div_02 + dxf_div_02+
            view_02_cart +'</div></li>';                

            view_03_list = view_03_list + '<tr><td style="cursor:pointer; text-decoration:underline;color: #0094da;" onclick="showItemDetail(this)" data-matnr="'+matnr+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">'+matnr+'</td><td><a href="#" class="open_view"><div class="img_view"><img src="'+img_url+'" alt="" onerror="onErrorImage(this)"></div><div class="view_pop"><img src="'+img_url+'" alt="" onerror="onErrorImage(this)"></div>'+
            '</a></td><td>'+view_03_cart+'</td><td>' + view_03_new + '</td><td style= "cursor:pointer; text-decoration:underline;color: #0094da;" onclick="showItemDetail(this)" data-matnr="'+matnr+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">' + designation +' </td><td style="">' + ig_nm +' </td><td>' +
            '<div class="download">'+stp_div_03 + dxf_div_03+'</div></td></tr>';
        }else{
            view_04_list = view_04_list + '<div class="listTable blueLine noMargin noScroll" ><p class="currentItem" onclick="doItemShow(this);">' + view_04_cart + view_04_new + designation + '</p>' +
            '<table class="mobileDetailItem"><colgroup><col width="100%"></colgroup><tbody><tr><th class="mdTitle">KP-CODE : <a onclick="showItemDetail(this)" data-matnr="'+data.maktx+'" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" data-designation="'+designation+'">'+matnr+'</a></th>'+
            '</tr><tr><td><div class="mdTable"><table class="mar10"><colgroup><col width="35%"><col width="*"></colgroup><tbody>'+
            '<tr><th>Item Group</th><td>'+ig_nm+'</td>' + '</tr>'+
            '<tr><th>Download</th><td>'+stp_div_04 + dxf_div_04+'</td>' + '</tr></tbody></table></div></td></tr></tbody></table></div>';
        }
    });
    if(pc_yn == "Y"){
        $("#view_01_list > tbody:last").append(view_01_list).trigger("create");
        $("#view_02_list").append(view_02_list).trigger("create");
        $("#view_03_list > tbody:last").append(view_03_list).trigger("create");
    }else{
        $("#view_04_list").append(view_04_list).trigger("create");
    }
    
    setMoreCount(dataRow.total_cnt);
    <%-- Search Result --%>
    $("#totalSearchCntTxt").html(LANG_SET.FR000085 + '<span style="padding-left:0px;display: inline-block;">('+addCommas(dataRow.total_cnt)+')</span>');
}


function doItemCart(obj){
    var userId = USERINFO.user_id;
    if(userId){
        if(USERAUTH.isBasketOrderAuth){
            var matnr = $(obj).data("id");
            getCartList(matnr);
        }else{
            <%-- 권한이 없으면 승급 요청을 하세요. --%>
            alert(LANG_SET.FR000229);
        }
    }else{
        //로그인창 오픈
        $(".open_login").click();
    }
}

function doLoginShow(){
	$(".open_login").click();
}
function onErrImgSize(obj){
	$(obj).attr('style', 'width:154px;height:120px');
}

function doExcelDown(){
	if(USERAUTH.isExcelDownAuth){
		if("G" == searchMode){
			$('#myForm #actionID').val("<%=ConstKey.ACTION_GET_EXCEL%>");
			myForm.submit();
		}else{
	        $('#paraForm #actionID').val("<%=ConstKey.ACTION_GET_EXCEL%>");
	        paraForm.submit();
		}
	}else{
		var userId = USERINFO.user_id;
		if(userId == ""){
			$(".open_login").click();
		}else{
			// 승급요청을 하세요
			alert(LANG_SET["FR000229"]);
		}
	}
}

function doGlobalReset(){
    <%
    if(cond_main_app_list != null && cond_main_app_list.size() > 0){
        for(int nMain=0; nMain<cond_main_app_list.size(); nMain++){
            SearchInfo contMain = (SearchInfo)cond_main_app_list.get(nMain);
    %>
    $('#search_<%=contMain.getMa_cd() %>').removeAttr("class");
    <%
        }
    }
    %>
    $("#search_sub_app *").empty();
    $("#myForm #sa_cd").val("");

    $("#search_item_group *").empty();
    $("#myForm #ig_cd").val("");
    <%-- Search --%>
    $("#btnGlobalSearch").html(LANG_SET.FR000138 + '<span>(0)</span>');
    $("#mainAppItemCntTxt").html(LANG_SET.FR000028 + '<span>(0)</span>');
    
}
function doSelectMain(ma_cd){
	if($("#myForm #auto_mode").val() == "A"){
		$("#myForm #ma_cd").val("");
		$("#myForm #sa_cd").val("");
		$("#myForm #ig_cd").val("");
		$("#myForm #matnr").val("");
		$("#myForm #auto_input_text").val("");
		$("#search_auto_input_text").val("");
		$("#myForm #auto_mode").val("M");
	}
    var cur_ma_cd = $("#myForm #ma_cd").val();
    var sel_ma_cd = ma_cd.replace("search_", "");
    $("#search_sub_app").empty();
    $("#myForm #sa_cd").val("");
    $("#search_item_group").empty();
    $("#myForm #ig_cd").val("");
	if(cur_ma_cd == sel_ma_cd){
		$("#"+ma_cd).removeAttr("class");
		$("#myForm #ma_cd").val("");
	}else{
		$("#search_"+cur_ma_cd).removeAttr("class");
		$("#"+ma_cd).attr("class", "on");
		$("#myForm #ma_cd").val(sel_ma_cd);
		var search_input_text = $("#search_auto_input_text").val();
		if(search_input_text != ""){
		    $("#myForm #auto_input_text").val(search_input_text);
		}
		var param = {
		          actionID :"<%=SearchConstKey.ACTION_GET_COND_SUB_APP%>",
		          ma_cd : $("#myForm #ma_cd").val(),
		          unit_cd : $("#btnSizeUnit").val(),
		          auto_input_text : $("#myForm #auto_input_text").val()
		};
	        
        $.ajax({
            type: "POST",
            url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
            data: param,
            dataType: "json",
            async: false,
            cache: false,
            beforeSend:function(){
                $('#loadbar').show();
            },
            success: function(d){
                var total_cnt = d.total_cnt;
                <%-- Search --%>
                $("#btnGlobalSearch").html(LANG_SET.FR000138 + '<span>('+total_cnt+')</span>');
                $("#mainAppItemCntTxt").html(LANG_SET.FR000028 + '<span>('+total_cnt+')</span>');
                $("#search_sub_app").append('<option value=""  data-lang="FR000118">'+LANG_SET.FR000118+'</option>');
                $.each(d.sub_app_list, function(index, data) {
                    var sa_cd = data.sa_cd;
                    var sa_nm = data.sa_nm;
                    var content =  '<option value="'+sa_cd+'">'+toTruncate(sa_nm, 35, "...")+'</option>'
                    $("#search_sub_app").append(content);
                });
            }, error: function() {

            },
            complete:function(){
                $('#loadbar').fadeOut();
            }
        });
	}
}

function doSelectSub(){
    var sa_cd = $("#search_sub_app  option:selected").val();
    $("#myForm #sa_cd").val(sa_cd);
    if(sa_cd == ""){
        $("#search_item_group *").remove();
        $("#myForm #ia_cd").val("");
        return false;
    }
    var search_input_text = $("#search_auto_input_text").val();
    if(search_input_text != ""){
        $("#myForm #auto_input_text").val(search_input_text);
    }
    var param = {
              actionID :"<%=SearchConstKey.ACTION_GET_COND_ITEM_GROUP%>",
              ma_cd : $("#myForm #ma_cd").val(),
              sa_cd : $("#myForm #sa_cd").val(),
              unit_cd : $("#btnSizeUnit").val(),
              auto_input_text : $("#myForm #auto_input_text").val()
          };
      
      $.ajax({
          type: "POST",
          url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
          data: param,
          dataType: "json",
          async: false,
          cache: false,
          beforeSend:function(){
              $('#loadbar').show();
          },
          success: function(d){
        	  var total_cnt = d.total_cnt;
        	  <%-- Search --%>
              $("#btnGlobalSearch").html(LANG_SET.FR000138 + '<span>('+total_cnt+')</span>');
              $("#search_item_group *").remove();
              $("#search_item_group").append('<option value="" data-lang="FR000031">'+LANG_SET.FR000031+'</option>');
              $.each(d.item_group_list, function(index, data) {
                  var ig_cd = data.ig_cd;
                  var ig_nm = data.ig_nm;
                  var content =  '<option value="'+ig_cd+'">'+ig_nm+'</option>'
                  $("#search_item_group").append(content);
              });
          }, error: function() {

          },
          complete:function(){
              $('#loadbar').fadeOut();
          }
      });
}


function doSelectItemGroup(ig_cd){
    $("#myForm #ig_cd").val(ig_cd);
    var search_input_text = $("#search_auto_input_text").val();
    if(search_input_text != ""){
        $("#myForm #auto_input_text").val(search_input_text);
    }
    var param = {
            actionID :"<%=SearchConstKey.ACTION_GET_ITEM_CNT%>",
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            ig_cd : $("#myForm #ig_cd").val(),
            unit_cd : $("#btnSizeUnit").val(),
            auto_input_text : $("#myForm #auto_input_text").val(),
        };
    
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
        data: param,
        dataType: "json",
        async: true,
        cache: false,
        beforeSend:function(){
            $('#loadbar').show();
        },
        success: function(d){
            var total_cnt = d.total_cnt;
            $("#btnGlobalSearch").html(LANG_SET.FR000138 + '<span>('+total_cnt+')</span>');
        }, error: function() {

        },
        complete:function(){
            $('#loadbar').fadeOut();
        }
    });
}


function doPageCntChange(page_cnt){
    $("#pageCount_1").val(page_cnt).prop("selected", true);
    $("#pageCount_2").val(page_cnt).prop("selected", true);
    $("#pageCount_3").val(page_cnt).prop("selected", true);
    $("#pageCount_4").val(page_cnt).prop("selected", true);

    if("G" == searchMode){
	    if(page_cnt == ""){
	    	$("#myForm #isPageYn").val('N');
	    	$("#myForm #pageCount").val("0");
	    }else{
	    	$("#myForm #isPageYn").val('Y');
	        $("#myForm #pageCount").val(page_cnt);
	    }
	    $("#myForm #startIndex").val("0");
	    $("#view_01_list_tbody").empty();
	    $("#view_02_list *").remove();
	    $("#view_03_list_tbody").empty();
	    $("#view_04_list *").remove();
	    var param = {
	            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
	            startIndex : Number($("#myForm #startIndex").val()),
	            orderSort : $("#myForm #orderSort").val(),
	            pageCount : $("#myForm #pageCount").val(),
	            isPageYn :  $("#myForm #isPageYn").val(),
	            ma_cd : $("#myForm #ma_cd").val(),
	            sa_cd : $("#myForm #sa_cd").val(),
	            ig_cd : $("#myForm #ig_cd").val(),
	            matnr : $("#myForm #matnr").val(),
	            unit_cd : $("#btnSizeUnit").val(),
	            auto_input_text : $("#myForm #auto_input_text").val(),
	    };
	    doSearch(param);
    }else{
        if(page_cnt == ""){
            $("#paraForm #isPageYn").val('N');
            $("#paraForm #pageCount").val("0");
        }else{
            $("#paraForm #isPageYn").val('Y');
            $("#paraForm #pageCount").val(page_cnt);
        }
        $("#paraForm #startIndex").val("0");
        $("#view_01_list_tbody").empty();
        $("#view_02_list *").remove();
        $("#view_03_list_tbody").empty();
        $("#view_04_list *").remove();

        doParametricSearch();
    }
}

function setMoreCount(nTotalCnt){
    if("G" == searchMode){
	    var startIndex = Number($("#myForm #startIndex").val()) + 1;
	    var pageCount = Number($("#myForm #pageCount").val());
	    var total_cnt = Number(nTotalCnt) - (startIndex * pageCount);
	
	    if(total_cnt < 0 || $("#myForm #isPageYn").val() == 'N'){
	        $("#myForm #remainCount").val("0");
	        <%-- More --%>
	        $("#moreCount").html("");
	        jQuery("#moreCount").css("display", "none");

	        $("#moreAll").html("");
            jQuery("#moreAll").css("display", "none");	        
	    }else{
	        $("#myForm #remainCount").val(total_cnt);
	    	jQuery("#moreCount").css("display", "block");
	        <%-- More --%>
	        $("#moreCount").text('<%= LangMng.LANG_D("FR000034", session_lang)%> ('+ addCommas(total_cnt) +')');

	        jQuery("#moreAll").css("display", "block");
            <%-- More --%>
            $("#moreAll").text('<%= LangMng.LANG_D("FR000249", session_lang)%> ('+ addCommas(total_cnt) +')'); 
	    }
    }else{
        var startIndex = Number($("#paraForm #startIndex").val()) + 1;
        var pageCount = Number($("#paraForm #pageCount").val());
        var total_cnt = Number(nTotalCnt) - (startIndex * pageCount);
    
        if(total_cnt < 0 || $("#paraForm #isPageYn").val() == 'N'){
            $("#paraForm #remainCount").val("0");
            <%-- More --%>
            $("#moreCount").html("");
            jQuery("#moreCount").css("display", "none");

            $("#moreAll").html("");
            jQuery("#moreAll").css("display", "none");
            
        }else{
            $("#paraForm #remainCount").val(total_cnt);
        	jQuery("#moreCount").css("display", "block");
            <%-- More --%>
            $("#moreCount").text("<%= LangMng.LANG_D("FR000034", session_lang)%> ("+ addCommas(total_cnt) +")");

            jQuery("#moreAll").css("display", "block");
            <%-- More --%>
            $("#moreAll").text("<%= LangMng.LANG_D("FR000249", session_lang)%> ("+ addCommas(total_cnt) +")"); 
        }
    }
}

var search_typingTimer;                //timer identifier
var search_doneTypingInterval = 350;  //time in ms, 5 second for example
var search_keyEvent;
$("#search_auto_input_text").keydown(function(event) {
   clearTimeout(search_typingTimer);
});
    
$("#search_auto_input_text").keyup(function(event) {
	search_keyEvent = event;
    clearTimeout(search_typingTimer);
    search_typingTimer = setTimeout(search_doneTyping, search_doneTypingInterval);
    event.preventDefault();
});

//user is "finished typing," do something
function search_doneTyping () {
    doAutoComplete();
}

var auto_sel_index = -1;
function doAutoComplete(){
    if(search_keyEvent.which == 38 || search_keyEvent.which == 40 || search_keyEvent.which == 13){
        if ( $('#search_automatic').css('display') == 'block' ){
            // up
            if( search_keyEvent.which == 38){
/*                 auto_sel_index = auto_sel_index -1;
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
            }else if( search_keyEvent.which == 40){
/*                 auto_sel_index = auto_sel_index +1;
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
            }else if(search_keyEvent.which == 13 && auto_sel_index >= 0){
                $(".auto_li").eq(auto_sel_index).children('a').trigger('click');
            }else if(search_keyEvent.which == 13 && auto_sel_index < 0){
            	doKeyWordSearch();
            }
        }else{
            // Enter
            if(search_keyEvent.which == 13){
            	doKeyWordSearch();
            }
        }
    }else{
        $("#myForm #auto_mode").val("M");
        auto_sel_index = -1;
	    if($("#search_auto_input_text").val() != "" && $("#search_auto_input_text").val().length > 2){
	        var param = {
	                actionID :"<%=SearchConstKey.ACTION_SEARCH_AUTO%>",
	                auto_input_text : $("#search_auto_input_text").val(),
	            };
	        
	        $.ajax({
	            type: "POST",
	            url: "<%=ConstUrl.SEARCH_URL%>?v=" + (new Date().getTime()),
	            data: param,
	            dataType: "json",
	            async: true,
	            cache: false,
	            success: function(d){               
	                $("#search_automatic_ul *").remove();
	                if(Number(d.total_cnt) > 0){
	                    $("#search_automatic_ul").append(d.html);
	                    $("#search_automatic").show();
	                }else{
	                    $("#search_automatic").hide();
	                }
	            }, error: function() {
	    
	            }
	        });
	    }else{
	        $("#search_automatic_ul *").remove();
	        $("#search_automatic").hide();
	    }
    }
}

function doAutoSearch(obj){
	doGlobalReset();
    var ma_cd = $(obj).data("macd");
    var sa_cd = $(obj).data("sacd");
    var ig_cd = $(obj).data("igcd");
    var matnr = $(obj).data("matnr");
    $("#myForm #ma_cd").val(ma_cd);
    $("#myForm #sa_cd").val(sa_cd);
    $("#myForm #ig_cd").val(ig_cd);
    $("#myForm #matnr").val(matnr);
    $("#myForm #auto_mode").val("A");
    $("#myForm #startIndex").val("0");
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    var param = {
            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
            startIndex : Number($("#myForm #startIndex").val()),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn :  $("#myForm #isPageYn").val(),
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            ig_cd : $("#myForm #ig_cd").val(),
            matnr : $("#myForm #matnr").val(),
            unit_cd : $("#btnSizeUnit").val()
    };
    doSearch(param);
    doLog("EC0003", "", $(obj).text());
    $("#search_auto_input_text").val($(obj).text())
    $("#search_automatic_ul *").remove();
    $("#search_automatic").hide();
}

function doKeyWordSearch(){
	$("#search_auto_input_text").blur();
    if($("#myForm #auto_mode").val() == "M"){
        $("#myForm #auto_input_text").val($("#search_auto_input_text").val());
        $("#myForm #ma_cd").val("");
        $("#myForm #sa_cd").val("");
        $("#myForm #ig_cd").val("");
        $("#myForm #matnr").val("");
    }
    $("#myForm #startIndex").val("0");
    $("#view_01_list_tbody").empty();
    $("#view_02_list *").remove();
    $("#view_03_list_tbody").empty();
    $("#view_04_list *").remove();
    var param = {
            actionID :"<%=SearchConstKey.ACTION_SEARCH_LIST%>",
            startIndex : Number($("#myForm #startIndex").val()),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            isPageYn :  $("#myForm #isPageYn").val(),
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            ig_cd : $("#myForm #ig_cd").val(),
            matnr : $("#myForm #matnr").val(),
            unit_cd : $("#btnSizeUnit").val(),
            auto_input_text : $("#myForm #auto_input_text").val(),
    };
    doSearch(param);
    doLog("EC0003", "", $("#myForm #auto_input_text").val());
    $("#search_automatic_ul *").remove();
    $("#search_automatic").hide();
}

//아이템 디테일 
function showItemDetail(obj){
	if("G" == searchMode){
	    $("#itemGoForm #ma_cd").val($("#myForm #ma_cd").val());
	    $("#itemGoForm #sa_cd").val($("#myForm #sa_cd").val());
	}
    var matnr = $(obj).data("matnr");
    $("#itemGoForm #matnr").val(matnr);
    var designation = $(obj).data("designation");
    $("#itemGoForm #designation").val(designation);

    var ig_cd = $(obj).data("igcd");
    $("#itemGoForm #ig_cd").val(ig_cd);
    var ig_nm = $(obj).data("ignm");
    $("#itemGoForm #ig_nm").val(ig_nm);
    itemGoForm.submit();
}
</script>
 <div class="open_pop" >
    <div class="in" style="overflow-y:hidden;">
        <a href="" class="close_pop"><img style="width:auto;" src="./img/btn_close.png" alt="닫기"></a>
        <div class="pop_cont">
            <iframe src="" style="width:1000px; height:720px;" id="ifr_item_detail">
            </iframe>
        </div>
    </div>
</div>