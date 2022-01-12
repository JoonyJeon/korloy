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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
AppInfo searchInfo = (AppInfo)request.getAttribute(ConstKey.SEARCH_DATA);
List<AppInfo> sub_app_list = (List<AppInfo>)request.getAttribute("sub_app_list");
List<AppInfo> main_app_list = (List<AppInfo>)request.getAttribute("main_app_list");
List<AppInfo> item_grp_list = (List<AppInfo>)request.getAttribute("item_grp_list");
int nItemTotalCnt = (int)request.getAttribute("itemTotalCnt");
int item_grp_list_cnt = (int)request.getAttribute("item_grp_list_cnt");
List<AppInfo> filterList = (List<AppInfo>)request.getAttribute("filterList");
String jsonFilter = (String)request.getAttribute("jsonFilter");
AppInfo selectSub = new AppInfo();
for(AppInfo info : sub_app_list){
    if(info.getSa_cd().equals(searchInfo.getSa_cd())){
    	selectSub = info;
    }
}
AppInfo selectMain = new AppInfo();
for(AppInfo mainInfo : main_app_list) {
     if(searchInfo.getMa_cd().equals(mainInfo.getMa_cd())){
         selectMain = mainInfo;
     }
}

String devType = (String) request.getAttribute("deviceType");
%>
<%if("P".equals(devType)){ %>
<style>
.sub_applist .swiper-slide a:hover{opacity:1.0;}
.sub_applist .swiper-slide a:hover:after{content: '';position:absolute; left:50%; top:-12px; transform:translate(-50%, 0); width:24px; height:24px; background:#0094da url(../img/ck_ck.png) no-repeat 50% 50%; border-radius:50%;}
</style>
<%} %>
<style>
#contents .expand {
    display: block;
    background: #fff url(../img/bg_expand.png) no-repeat right 15px bottom 15px;
    background-size: 20px;
    height: 400px;
}
.disabledbutton {
    pointer-events: none;
    opacity: 0.4;
}

</style>
<div id="container" class="sub">
	<div class="path">
		<div class="inner">
			<ul>
				<li class="home" data-lang="<%=selectMain.getMa_cd()%>" data-langdef="Main Application"><%=StringUtil.nvl(StringUtil.nvl(LangMng.LANG_D(selectMain.getMa_cd(), session_lang), selectMain.getMa_nm()), "Main Application") %></li>
				<li id="subAppTitle" data-lang="<%=selectSub.getSa_cd()%>" data-langdef="Sub Application"><%=StringUtil.nvl(StringUtil.nvl(LangMng.LANG_D(selectSub.getSa_cd(), session_lang), selectSub.getSa_nm()), "Sub Application") %></li>
				<%-- Item Group List --%>
				<li data-lang="FR000027"><%=LangMng.LANG_D("FR000027", session_lang) %></li>
			</ul>
		</div>
	</div>
	<div id="contents" class="item_wrap">
		<div class="bg">
			<div class="inner" id="main_sub_wrap">
				<div class="mainapp_list">
				    <%-- Main Application --%>
					<h3 data-lang="FR000081"><%=LangMng.LANG_D("FR000081", session_lang) %></h3>
					<div class="swiper-container">
                        <div class="swiper-wrapper" id="main_app">
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
                                <a onclick="javascript:goSubApplication(this)" id="ma_<%=mainInfo.getMa_cd()%>" data-macd="<%=mainInfo.getMa_cd()%>" data-manm="<%=StringUtil.nvl(LangMng.LANG_D(mainInfo.getMa_cd(), session_lang), mainInfo.getMa_nm())%>" data-subcnt="<%=mainInfo.getSub_cnt()%>" <%=classOn%>>
                                    <div class="img">
                                        <img src="<%=UPLOAD_ROOT_PATH %><%=mainInfo.getMain_img() %>" alt="<%=mainInfo.getMa_nm()%>" onerror="onErrorImage(this)">
                                    </div> <span data-lang="<%=mainInfo.getMa_cd()%>"><%=StringUtil.nvl(LangMng.LANG_D(mainInfo.getMa_cd(), session_lang), mainInfo.getMa_nm())%></span>
                                </a>
                            </div>
                        <%
                             }
                       }
                        %>
                        </div>
					</div>
					<div class="btn_prev">이전</div>
					<div class="btn_next" id="btn_main_next">다음</div>
				</div>

				<div class="sub_applist">
				    <%-- Sub Application --%>
					<h3 id="mainAppCnt" data-lang="FR000118"><%=LangMng.LANG_D("FR000118", session_lang) %><%if(!"".equals(selectMain.getSub_cnt())){%><span>(<%=StringUtil.toComma(selectMain.getSub_cnt()) %>)</span><%} %></h3>
					<div class="spWrap">
						<div class="swiper-container subAppRolling">
							<div class="swiper-wrapper">
								<%
								int selSubIndex = 0;
								if (sub_app_list != null && sub_app_list.size() > 0) {
								for (int nSub=0; nSub<sub_app_list.size(); nSub++) {
									AppInfo subInfo = (AppInfo)sub_app_list.get(nSub);
									String classOn = "";
									if (selectSub.getSa_cd().equals(subInfo.getSa_cd())) {
										classOn = "class=\"on\"";
										selSubIndex = nSub;
									}
							%>
								<div class="swiper-slide" >
									<a  id="sa_<%=subInfo.getSa_cd() %>" onclick="javascript:changeSubApp(this)" <%=classOn %> data-index="<%=nSub%>" data-lang="<%=subInfo.getSa_cd() %>" data-langtype="data-sanm" data-sacd = "<%=subInfo.getSa_cd() %>" data-sanm="<%=StringUtil.nvl(LangMng.LANG_D(subInfo.getSa_cd(), session_lang), subInfo.getSa_nm())%>" data-sacd="<%=subInfo.getSa_cd() %>" data-subcnt="<%=StringUtil.toComma(subInfo.getSub_cnt()) %>">
										<div class="img" >
											<img src="<%=UPLOAD_ROOT_PATH%><%=subInfo.getMain_img()%>"	alt="<%=subInfo.getSa_nm()%>"  onerror="onErrorImage(this)">
										</div>
										<div class="txt" data-lang="<%=subInfo.getSa_cd()%>" ><%=StringUtil.nvl(LangMng.LANG_D(subInfo.getSa_cd(), session_lang), subInfo.getSa_nm())%></div>
									</a>
								</div>
								<%
								}
							}
							%>
							</div>
						</div>
					</div>
					<div class="btn_prev" id="sub_btn_prev">이전</div>
					<div class="btn_next" id="sub_btn_next">다음</div>
				</div>
			</div>
		</div>
		<div class="listview">
			<div class="inner">
				<h2 id="totalItemListCnt" data-lang="FR000027">
				    <%-- Item Group List --%>
					<%=LangMng.LANG_D("FR000027", session_lang) %> <%if(!"".equals(selectMain.getSub_cnt())){%><span>(<%=StringUtil.toComma(selectSub.getSub_cnt())%>)</span><%} %>
				</h2>
				<div class="view">
					<div class="view_top on" id="igListBar" >
						<div class="tit" onclick="javascript:igListShowHide(this);">
							<%-- All --%>
							<span data-lang="FR000028"><%=LangMng.LANG_D("FR000028", session_lang) %><span>(<%=StringUtil.toComma(nItemTotalCnt) %>)</span></span >
							<%
							String strTmpMain = "";
							String strMainDataLang = "";
							String strSubDataLang = "";
							if("".equals(selectMain.getMa_nm())){
								// 어셈블리에서 넘어온 경우 MA_CD 없음 Main Application
								strTmpMain = LangMng.LANG_D("FR000081", session_lang);
								strMainDataLang = "data-lang=\"FR000081\"";
							}else{
								strTmpMain = StringUtil.nvl(LangMng.LANG_D(selectMain.getMa_cd(), session_lang), selectMain.getMa_nm()) + "<span>(" + StringUtil.toComma(selectMain.getSub_cnt()) + ")</span>";
								strMainDataLang = "data-lang=\""+selectMain.getMa_cd()+"\"";
								
							}
							
							String strTmpSub = "";
                            if("".equals(selectSub.getSa_nm())){
                            	// 어셈블리에서 넘어온 경우 SA_CD 없음 Sub Application
                            	strTmpSub = LangMng.LANG_D("FR000118", session_lang);
                            	strSubDataLang = "data-lang=\"FR000118\"";
                            }else{
                            	strTmpSub = StringUtil.nvl(LangMng.LANG_D(selectSub.getSa_cd(), session_lang), selectSub.getSa_nm()) + "<span>(" + StringUtil.toComma(selectSub.getSub_cnt()) + ")</span>";
                            	strSubDataLang = "data-lang=\""+selectSub.getSa_cd()+"\"";
                            }
							%>
							<span <%=strMainDataLang %>><%=strTmpMain %></span>
							<span id="subAppTitleCnt" <%=strSubDataLang %>><%=strTmpSub %></span>
						</div>
						<div class="btn_area" >
							<ul>
								<li class="recommend short" ><select name="ig_list_sort" id="ig_list_sort" onchange="javascript:getItemGroupList(false);">
										<%-- Recommended  --%>
										<option value="ISNULL(TB.SEQ, 999999), TB.IG_NM" selected data-lang="FR000029"><%=LangMng.LANG_D("FR000029", session_lang) %></option>
										<%-- New --%>
										<option value="ISNULL(TB.NEW_TO_DT, CAST('1900/12/31' AS DATETIME)) DESC, ISNULL(TB.SEQ, 999999)" data-lang="FR000030"><%=LangMng.LANG_D("FR000030", session_lang) %></option>
										<%-- Name --%>
										<option value="TB.IG_NM" data-lang="FR000117"><%=LangMng.LANG_D("FR000117", session_lang) %></option>
								</select></li>
								<li  class="filters"><a href="" class="open_filter" ><img src="<%=PATH_IMG %>/ico_filter.png" alt="텍스트로 보기"></a></li>
								<li><a class="btn_text"><img src="<%=PATH_IMG %>/ico_liststyle01.png" alt="텍스트로 보기"></a></li>
								<li><a class="on btn_big"><img src="<%=PATH_IMG %>/ico_liststyle02.png" alt="큰썸네일로 보기"></a></li>
								<li><a class="btn_small"><img src="<%=PATH_IMG %>/ico_liststyle03.png" alt="작은썸네일"></a></li>
								<li><a style="cursor:pointer" onclick="javascript:igListShowHide(this);" class="btn_down" id="igBtnDown"><img src="<%=PATH_IMG%>/btn_down.png" alt="내리기"></a></li>
							</ul>
						</div>
						<div class="filter" style="z-index:100">
                            <%if("P".equals(devType)){ %>
                            <div class="options pc">
                                <div class="listTable02">
                                    <table>
										<colgroup>
                                            <col width="45%">
                                            <col width="*">
										</colgroup>
										<tbody id="filterGroup">
                                        <%if(filterList != null && filterList.size() > 0){ %>
										   <%for(int nFilter=0; nFilter<filterList.size(); nFilter++){ %>
										   <% AppInfo filterInfo =  (AppInfo)filterList.get(nFilter);%>
										   <% String sch_type =  filterInfo.getSch_typ();%>
                                            <tr>
                                                <td data-lang="<%=filterInfo.getProp_iso()%>"><%=LangMng.LANG_D(filterInfo.getProp_iso(), session_lang)%><span>(<%=filterInfo.getProp_iso()%>)</span></td>
                                                <td>
                                                <% if("IN".equals(sch_type)){ %>
													<div class="degree">
													     <div class="input">
													         <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>">
													     </div>
													</div>
                                                <%}else if("CE".equals(sch_type)){%>
		                                            <div class="degree">
		                                                <a onclick="javascript:doFilterCeClick(this)" data-type="ce1" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" id="filter_<%=filterInfo.getProp_iso()%>_ce1">≤</a>
		                                                <a onclick="javascript:doFilterCeClick(this)" data-type="ce2" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" class="on" id="filter_<%=filterInfo.getProp_iso()%>_ce2">=</a>
		                                                <a onclick="javascript:doFilterCeClick(this)" data-type="ce3" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" id="filter_<%=filterInfo.getProp_iso()%>_ce3">≥</a>
		                                                <div class="input">
		                                                    <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>_1">
		                                                </div>
		                                                <div class="input last"  id="filter_<%=filterInfo.getProp_iso()%>_2_div">
		                                                    <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>_2">
		                                                </div>
		                                            </div>
                                                <%}else if("SB".equals(sch_type)){ %>
			                                       <div class="select">
			                                           <select name="" id="filter_<%=filterInfo.getProp_iso()%>">
			                                                <option value=""></option>
			                                           <%for(CodeInfo codeInfo : filterInfo.getCombo_list()){ %>
			                                                <option value="<%=codeInfo.getCode()%>"><%=codeInfo.getName()%></option>
			                                           <%} %>
			                                           </select>
			                                       </div>
                                                <%} %>
                                                </td>
										   <%} %>
                                        <%} %>
	                                    </tbody>
                                    </table>
                                </div>
							</div>
							<%}else{ %>
							<div class="options mobile">
								<div class="listTable02">
									<ul id="filterGroup">
                                        <%if(filterList != null && filterList.size() > 0){ %>
                                           <%for(int nFilter=0; nFilter<filterList.size(); nFilter++){ %>
                                           <% AppInfo filterInfo =  (AppInfo)filterList.get(nFilter);%>
                                           <% String sch_type =  filterInfo.getSch_typ();%>
                                        <li>
                                            <span data-lang="<%=filterInfo.getProp_iso()%>"><%=LangMng.LANG_D(filterInfo.getProp_iso(), session_lang)%><span>(<%=filterInfo.getProp_iso()%>)</span></span>
                                            <% if("IN".equals(sch_type)){ %>
                                                <div class="degree" style="width:90%">
                                                     <div class="input" style="width:90%">
                                                         <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>">
                                                     </div>
                                                </div>
                                            <%}else if("CE".equals(sch_type)){%>
                                                <div class="degree">
                                                    <a onclick="javascript:doFilterCeClick(this)" data-type="ce1" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" id="filter_<%=filterInfo.getProp_iso()%>_ce1">≤</a>
                                                    <a onclick="javascript:doFilterCeClick(this)" data-type="ce2" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" class="on" id="filter_<%=filterInfo.getProp_iso()%>_ce2">=</a>
                                                    <a onclick="javascript:doFilterCeClick(this)" data-type="ce3" data-prop="<%=filterInfo.getProp_iso()%>" style="cursor:pointer" id="filter_<%=filterInfo.getProp_iso()%>_ce3">≥</a>
                                                    <div class="input"   style="width:70px">
                                                        <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>_1">
                                                    </div>
                                                    <div class="input last" id="filter_<%=filterInfo.getProp_iso()%>_2_div"  style="width:70px">
                                                        <input type="text"  id="filter_<%=filterInfo.getProp_iso()%>_2" >
                                                    </div>
                                                </div>
                                            <%}else if("SB".equals(sch_type)){ %>
                                               <div class="select">
                                                   <select name="" id="filter_<%=filterInfo.getProp_iso()%>">
                                                        <option value=""></option>
                                                   <%for(CodeInfo codeInfo : filterInfo.getCombo_list()){ %>
                                                        <option value="<%=codeInfo.getCode()%>"><%=codeInfo.getName()%></option>
                                                   <%} %>
                                                   </select>
                                               </div>
                                            <%} %>
                                        </li>
                                           <%} %>
                                        <%} %>
									</ul>
								</div>
							</div>
							<%} %>
							<div class="btn_group">
								<div class="center">
								    <%-- Reset --%>
									<a onclick="doFilterReset();" class="btn01 same" data-lang="FR000060"><%=LangMng.LANG_D("FR000060", session_lang)%></a>
									<%-- Search --%>
									<a onclick="javascript:doFilterSearch();" style="cursor:pointer" class="btn01 same" data-lang="FR000138"><%=LangMng.LANG_D("FR000138", session_lang)%></a>
								</div>
							</div>
						</div>
					</div>
					<div id="area_itemGroup">
						<div class="cont_wrap big" >
							<div class="item_list">
								<ul id="item_group_list">
								<%
			                          if (item_grp_list != null && item_grp_list.size() > 0) {
			                              for (AppInfo itemGrpInfo : item_grp_list) {
			                          %>
									<li><a style="cursor:pointer" onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="<%=itemGrpInfo.getIg_cd() %>" data-ignm="<%=itemGrpInfo.getIg_nm() %>" id="igGrpListInfo">
											<div class="top">
												<%if(ConstKey.KEY_Y.equals(itemGrpInfo.getNew_yn())){ %><div class="new">NEW</div><%} %>
												<%if("".equals(searchInfo.getSearch_assem_no())) {%><div class="num"><%=StringUtil.toComma(itemGrpInfo.getSub_cnt()) %></div><%} %>
											</div>
											<div class="img">
												<img
													src="<%=UPLOAD_ROOT_PATH%><%=itemGrpInfo.getItem_image_thumb()%>"
													alt="" onerror="onErrorImage(this)" />
											</div>
											<div class="txt">
												<strong style="display:block;text-overflow:ellipsis;overflow:hidden;white-space: nowrap;" ><%=itemGrpInfo.getIg_nm() %></strong>
												<p data-lang="<%=itemGrpInfo.getIg_cd()%>"><%=StringUtil.nvl(LangMng.LANG_D(itemGrpInfo.getIg_cd(), session_lang), itemGrpInfo.getIg_dscr())%></p>
											</div>
									</a></li>
								<%
								   }
			                          }
			                          %>
								</ul>
								<div>
									<div class="more" >
									    <%-- More --%>
										<a href="javascript:getItemGroupList(true)" class="btn_more" id="moreCount1" data-lang="FR000034"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000034", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
                                        <a href="javascript:getMoreAll()" class="btn_more" id="moreAll1" data-lang="FR000249"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000249", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
									</div>
                                </div>
							</div>
						</div>
						<div class="cont_wrap small">
							<div class="item_list">
								<ul id="item_group_list2">
			                          <%
			                          if (item_grp_list != null && item_grp_list.size() > 0) {
			                              for (AppInfo itemGrpInfo : item_grp_list) {
			                          %>
									<li><a onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="<%=itemGrpInfo.getIg_cd() %>" data-ignm="<%=itemGrpInfo.getIg_nm() %>" id="igGrpListInfo">
											<div class="top">
												<%if(ConstKey.KEY_Y.equals(itemGrpInfo.getNew_yn())){ %><div class="new">NEW</div><%} %>
												<div class="num"><%=StringUtil.toComma(itemGrpInfo.getSub_cnt()) %></div>
											</div>
											<div class="img">
												<img src="<%=UPLOAD_ROOT_PATH%><%=itemGrpInfo.getItem_image_thumb()%>" onerror="onErrorImage(this)">
											</div>
											<div class="txt">
												<p><%=itemGrpInfo.getIg_nm() %></p>
											</div>
									</a></li>
			                          <%
			                             }
			                          }
			                          %>
								</ul>
								<div class="more">
								    <%-- More --%>
								    <a href="javascript:getItemGroupList(true)" class="btn_more" id="moreCount2" data-lang="FR000034"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000034", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
                                    <a href="javascript:getMoreAll()" class="btn_more" id="moreAll2" data-lang="FR000249"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000249", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
								</div>
							</div>
						</div>
						<div class="cont_wrap text">
							<div class="listTable03">
								<table>
									<colgroup>
										<col width="15%">
										<col width="8%">
										<col width="8%">
										<col width="10%">
										<col width="*">
									</colgroup>
									<thead>
			                                  <tr>
			                                      <%-- Item group--%>
			                                      <th data-lang="FR000031"><%=LangMng.LANG_D("FR000031", session_lang) %></th>
	                                              <%-- Quantity --%>
	                                              <th data-lang="FR000230"><%=LangMng.LANG_D("FR000230", session_lang) %></th>
			                                      <%-- Image--%>
			                                      <th data-lang="FR000032"><%=LangMng.LANG_D("FR000032", session_lang) %></th>
			                                      <%-- New--%>
			                                      <th data-lang="FR000030"><%=LangMng.LANG_D("FR000030", session_lang) %></th>
			                                      <%-- Description--%>
			                                      <th data-lang="FR000033"><%=LangMng.LANG_D("FR000033", session_lang) %></th>
			                                  </tr>
									</thead>
									<tbody id="item_group_list3">
			                            <%
			                            if (item_grp_list != null && item_grp_list.size() > 0) {
			                                for (AppInfo itemGrpInfo : item_grp_list) {
			                            %>
										<tr >
											<td onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="<%=itemGrpInfo.getIg_cd() %>" data-ignm="<%=itemGrpInfo.getIg_nm() %>" style="cursor:pointer" class="left"><%=itemGrpInfo.getIg_nm() %></td>
											<td><%=StringUtil.toComma(itemGrpInfo.getSub_cnt()) %></td>
											<td>
                                                <div class="img_view"><img src="<%=UPLOAD_ROOT_PATH%><%=itemGrpInfo.getItem_image_thumb()%>" onerror="onErrorImage(this)"></div>
											</td>
											<td><%if(ConstKey.KEY_Y.equals(itemGrpInfo.getNew_yn())){ %><img src="/img/ico_new.png" alt="new"><%} %></td>
											<td class="left" data-lang="<%=itemGrpInfo.getIg_cd()%>" data-langdef="<%=itemGrpInfo.getIg_dscr()%>"><%=StringUtil.nvl(LangMng.LANG_D(itemGrpInfo.getIg_cd(), session_lang), itemGrpInfo.getIg_dscr())%></td>
										</tr>
			                            <%
			                               }
			                            }
			                            %>
									</tbody>
								</table>
							</div>
							<div class="more">
							     <%-- More --%>
							    <a href="javascript:getItemGroupList(true)" class="btn_more" id="moreCount3" data-lang="FR000034"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000034", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
                                <a href="javascript:getMoreAll()" class="btn_more" id="moreAll3" data-lang="FR000249"><%if(nItemTotalCnt > 0){%><%=LangMng.LANG_D("FR000249", session_lang) %><span> (<%=StringUtil.toComma(nItemTotalCnt) %>)</span><%} %></a>
							</div>
						</div>
					</div>
				</div>
				<%@include file="/WEB-INF/jsp/app/item.inc" %>
			</div>
		</div>
	</div>
</div>
<form id="myForm" name="myForm" method="POST" action="<%=ConstUrl.APP_ITEM_GRP_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID%>" id="<%=ConstKey.ACTION_ID%>" />
    <input type="hidden" name="ma_cd" id="ma_cd" value="<%=searchInfo.getMa_cd()%>" />
    <input type="hidden" name="sa_cd" id="sa_cd" value="<%=searchInfo.getSa_cd()%>"/>
    <input type="hidden" name="matnr_list" id="matnr_list" value="<%=searchInfo.getMatnr_list()%>" />
    <input type="hidden" name="search_assem_no" id="search_assem_no" value="<%=searchInfo.getSearch_assem_no()%>"/>
    <input type="hidden" name="orderSort" id="orderSort" value="<%=searchInfo.getOrderSort()%>"/>
    <input type="hidden" name="pageCount" id="pageCount" value="<%=searchInfo.getPageCount() %>"/>
    <input type="hidden" name="remainCount" id="remainCount" value="<%=item_grp_list_cnt %>" />
    <input type="hidden" name="startIndex" id="startIndex" value="<%=searchInfo.getStartIndex() %>" />
    <input type="hidden" name="assem_ig_cd" id="assem_ig_cd" value="<%=searchInfo.getAssem_ig_cd() %>" />
    <input type="hidden" name="ig_cd" id="ig_cd" />
    <input type="hidden" name="it_matnr" id="it_matnr" />
    <input type="hidden" name="it_designation" id="it_designation" />
    <input type="hidden" name="isSubAppChange" id="isSubAppChange" />
    <input type="hidden" name="isPageYn" id="isPageYn" value="Y" />
    <input type="hidden" name="unit_cd" id="unit_cd" />
    
</form>

<!-- yji_21-10-20 플로팅 -->
<!-- <div class="f_btn"> -->
<!-- 	<a id="btn_mv_top" class="TOP" href="#" onclick="javascript:float_move(this);" data-target="">TOP</a> -->
<!-- 	<a id="btn_mv_ig" class="IG" href="#" onclick="javascript:float_move(this);" data-target="">IG</a> -->
<!-- 	<a id="btn_mv_it" class="ITEM" href="#" onclick="javascript:float_move(this);" data-target="">IT</a> -->
<!-- </div> -->

<!-- // yji_21-10-20 플로팅 -->
<script type="text/javascript">
var selectMain = "<%=searchInfo.getMa_cd()%>";
var filterParamList = new Array();
var igListShow = true;
var devType='<%=devType%>'
var jsonFilter = JSON.parse('<%=jsonFilter%>');
var click_offset  = new Array();
jQuery(document).ready(function(){

mainSwiper.slideTo(<%=selMainIndex%>);
appSwiper.slideTo(<%=selSubIndex%>);
	$("#area_itemList").hide();
    $("#area_item").hide();
	setMoreCount("<%=item_grp_list_cnt %>");
	init_Item();
	//통화변경
	$("#btnCurr").change(function(){	        
		cur_currency = this.value;
        doItemReload("CURRENCY");
	});

   $("#btnSizeUnit").change(function(){            
	   cur_size_unit = this.value;
        doItemReload("SIZE");
    });

   // 검색에서 넘어온 경우 아이템 화면을 바로 표시한다.
   <%if("Y".equals(searchInfo.getItem_view_yn())){%>
   $("#igListBar").removeClass("on");
   doItemView();
   <%}%>
	$('.loading').delay('1000').fadeOut();
	
	//어셈블리에서 넘어온 경우 메인/서브어플리케이션 영역을 비활성화한다.
	<%if(!"".equals(searchInfo.getMatnr_list())){%>
	$("#main_sub_wrap").addClass("disabledbutton");
	<%}%>
});

function doItemView(){
	$("#area_itemList").show();
	 cur_ig_cd = "<%=searchInfo.getIg_cd()%>";
	 $('#ig_cd').val(cur_ig_cd);
	 cur_ig_nm = "<%=searchInfo.getIg_nm()%>";
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

	 goItem("<%=searchInfo.getMatnr()%>", "<%=searchInfo.getDesignation()%>");

	 $("#area_itemGroup").slideUp();
	 $('#area_item_sub').slideDown();
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
                content = content + '<div class="swiper-slide" style="width:16.6%;">'+
                '<a onclick="javascript:goSubApplication(this)" style="cursor:pointer;" '+class_on+' data-subcnt="'+sub_cnt+'" data-manm="'+ma_nm+'" data-macd="'+ma_cd+'" id="ma_'+ma_cd+'">' +
                '<div class="img">'+
                '<img src="'+img_url+'" alt="'+ma_nm+'" onerror="onErrorImage(this)">'+
                '</div> <span>'+ma_nm+'</span>'+
                '</a>'+
                '</div>';
            });
            $("#main_app").append(content).trigger("create");
            $("#mainApptitle").text(selectMainName);
            $("#mainAppCnt").text('Sub Application ('+selectMainCnt+')');
            $("#allItemCnt").text(selectMainName + ' ('+selectMainCnt+')');
            
        }, error: function() {

        }
    });
}

function igListShowHide(obj){
	var filter_color = $('.open_filter').css("background-color");
    // 아이템그룹 리스트 닫혀있는 상태
	if($('#area_itemGroup').is(":visible") != true){
		$("#igListBar").addClass("on");
		if(filter_color != "rgb(0, 148, 218)"){
			  $(".open_filter").css("background-color", "#008746");
		}
		$("#area_itemList").children("div:eq(0)").removeClass("on");
		$("#item_ig_box_top").removeClass("on");
		$("#area_itemGroup").slideToggle();
	    // 아이템 리스트가 열려있으면
		if($('#area_itemList_sub').css('display') == 'block'){
			$('#area_itemList_sub').slideToggle();
			$('#area_itemList_sub').hide();
		}
        // 아이템 리스트가 열려있으면
        if($('#area_item_sub').css('display') == 'block'){
            $('#area_item_sub').slideToggle();
            $('#area_item_sub').hide();
        }

		//ItemAreaAllClose();
	// 아이템그룹 리스트 열려있는상태
	}else{
		$("#igListBar").removeClass("on");
	    if(filter_color != "rgb(0, 148, 218)"){
	        $(".open_filter").css("background-color", "#8597a0");
	    }
		if($('#area_itemList').is(":visible") == true){
//			  $("#area_itemList").children("div:eq(0)").addClass("on");
		}
        $("#area_itemGroup").slideToggle();
        // 아이템 리스트가 닫혀있으면
//         if($('#area_itemList_sub').css('display') == 'none'){
//             $('#area_itemList_sub').slideToggle();
//         }
	}
}

function ItemAreaAllClose(){
    // 아이템 리스트가 열려있으면 
    if($('#area_itemList').css('display') == 'block'){
        // 닫아
        $('#area_itemList').slideUp();
    }
    // 아이템이 열려있으면
    if($('#area_item').css('display') == 'block'){
        // 닫아
        $('#area_item').slideUp();                
    }

    if($('#area_item_sub').css('display') == 'block'){
        $('#area_item_sub').slideUp();
    }
    
    //Related Inserts 펼쳐져있으면 닫기
    if($('#area_inserts').css('display') == 'block'){
        $('#area_inserts').slideUp();
    }
    
    //Related Inserts 펼쳐져있으면 닫기
    if($('#area_holders').css('display') == 'block'){
        $('#area_holders').slideUp();
    }
    
    //Recommended Cutting Speeds 펼쳐져있으면 닫기
    if($('#area_cutting_speed').css('display') == 'block'){
        $('#area_cutting_speed').slideUp();
    }
    
    //Spare Parts 펼쳐져있으면 닫기
    if($('#area_spare_parts').css('display') == 'block'){
        $('#area_spare_parts').slideUp();
    }
}
function goSubApplication(obj){
    myForm.action = "<%=ConstUrl.APP_SUB_URL%>";
    $("#myForm #ma_cd").val($(obj).data('macd'));
    $("#myForm #unit_cd").val($("#btnSizeUnit").val());
    $("#myForm").attr("action", "<%=ConstUrl.APP_SUB_URL%>").submit();
}

function makeItemGroupList(param, isRemove){
	console.log(param);
	console.log(isRemove);
    $.ajax({
        type: "POST",
        url: "<%=ConstUrl.APP_ITEM_GRP_LIST_URL%>?v=" + (new Date().getTime()),
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
            $.each(d.list, function(index, data) {
                var img_url = "<%=UPLOAD_ROOT_PATH%>" + data.item_image_thumb;
                var ig_nm = data.ig_nm;
                var ig_cd = data.ig_cd;
                var sub_cnt = data.sub_cnt;
                var ig_dscr = data.ig_dscr;
                var new_div = "";
                var new_div2 = "";
                var new_div3 = "";
                if("Y" == data.new_yn){
                    new_div = '<div class="new" data-lang="FR000030">'+LANG_SET["FR000030"]+'</div>';
                    new_div2 = '<div class="new" data-lang="FR000030">'+LANG_SET["FR000030"]+'</div>';
                    new_div3 = '<img src="/img/ico_new.png" alt="new">';
                }
                content = content + '<li><a onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'"><div class="top">'+new_div+'<div class="num">'+ addCommas(sub_cnt) + '</div>' +
                '</div><div class="img"><img id="ig_img'+ig_cd+'" src="' + img_url + '" onerror="onErrorImage(this)" /></div><div class="txt"><strong style="display:block">'+ ig_nm +'</strong>' +
                '<p data-lang="'+ig_cd+'" data-langdef="'+ig_dscr+'">'+ig_dscr+'</p></div></a></li>';

                content2 = content2 + '<li><a onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" id="igGrpListInfo">'+
                '<div class="top">'+new_div2 +
                '<div class="num">'+addCommas(sub_cnt)+'</div>'+
                '</div>'+
                '<div class="img">'+
                '<img src="'+img_url+'" onerror="onErrorImage(this)">' +
                '</div>'+
                '<div class="txt" >'+
                '<p style="font-size:14px;height:40px">'+ig_nm+'</p>'+
                '</div>'+
                '</a></li>';
                
                content3 = content3 + '<tr>'+
                '<td class="left" onclick="javascript:igListShowHide(this);goItemGroup(this);" data-igcd="'+ig_cd+'" data-ignm="'+ig_nm+'" style="cursor:pointer">'+ig_nm+ '</td>'+
                '<td>'+ addCommas(sub_cnt) + '</td>'+
                '<td>'+
                '<div class="img_view"><img src="'+img_url+'" onerror="onErrorImage(this)"></div>'+
                '</td>'+
                '<td>'+new_div3+'</td>'+
                '<td class="left" data-lang="'+ig_cd+'" data-langdef="'+ig_dscr+'">'+ig_dscr+'</td>'+
                '</tr>';
                
            });
            if(isRemove){
                $("#item_group_list *").remove();
                $("#item_group_list2 *").remove();
                $("#item_group_list3 *").remove();
                $("#myForm #startIndex").val('0');
            }
            $("#item_group_list").append(content).trigger("create");
            $("#item_group_list2").append(content2).trigger("create");
            $("#item_group_list3").append(content3).trigger("create");
            var isSubAppChange = $("#myForm #isSubAppChange").val();
            // Sub App 변경으로 필터 새로만듬 
            if(isSubAppChange == 'Y'){
                jsonFilter = JSON.parse(JSON.stringify(d.filterList));
                <%if("P".equals(devType)){ %>
                $("#filterGroup").empty();
                $("#filterGroup").append(d.filterHtml).trigger("create");
                <%}else{%>
                $("#filterGroup *").remove();
                $("#filterGroup").append(d.filterHtml).trigger("create");
                <%}%>
                <%-- Item Group List --%>
                $('#totalItemListCnt').html(LANG_SET["FR000027"]+'<span>(' + addCommas(d.item_cnt) +')</span>')
            }
            setMoreCount(d.cnt);
        }, error: function() {

        },
        complete:function(){
          $('#loadbar').fadeOut();
        }

    });
	
}
function getMoreAll(){
	var isSubAppChange = $("#myForm #isSubAppChange").val();
    $("#myForm #startIndex").val("0");
    var sort = $("#ig_list_sort  option:selected").val();
    $("#myForm #orderSort").val(sort);
    $("#myForm #isPageYn").val("N");
    $("#myForm #remainCount").val("0");
    $("#myForm #pageCount").val("999");
    
    // 필터 파라미터 만듬
    makeFilterParam();
    var param = {
            actionID :"<%=AppConstKey.ACTION_ITEM_GROUP_LIST%>",
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            startIndex : $("#myForm #startIndex").val(),
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            assem_ig_cd : $("#myForm #assem_ig_cd").val(),
            filterParam : JSON.stringify(filterParamList),
            isSubAppChange : isSubAppChange,
            isPageYn : $("#myForm #isPageYn").val()
            };
    makeItemGroupList(param, true);
}

// Sub Application을 선택해 Item Group List 다시 불러옴
function getItemGroupList(is_more){
	var sort = $("#ig_list_sort  option:selected").val();
	$("#myForm #orderSort").val(sort);
    var startIndex = Number($("#myForm #startIndex").val());
    if(is_more){
        startIndex = startIndex + 1;
    }else{
        startIndex = 0;
    }
    $("#myForm #startIndex").val(startIndex);
    // 필터 파라미터 만듬
    makeFilterParam();
    var isSubAppChange = $("#myForm #isSubAppChange").val();
    var param = {
            actionID :"<%=AppConstKey.ACTION_ITEM_GROUP_LIST%>",
            ma_cd : $("#myForm #ma_cd").val(),
            sa_cd : $("#myForm #sa_cd").val(),
            unit_cd : $("#myForm #unit_cd").val(),
            startIndex : startIndex,
            orderSort : $("#myForm #orderSort").val(),
            pageCount : $("#myForm #pageCount").val(),
            assem_ig_cd : $("#myForm #assem_ig_cd").val(),
            filterParam : JSON.stringify(filterParamList),
            isSubAppChange : isSubAppChange,
            isPageYn : $("#myForm #isPageYn").val()
        };
    if(!is_more){
    	makeItemGroupList(param, true);
    }else{
    	makeItemGroupList(param, false);
    }
}



function setMoreCount(nTotalCnt){
    var startIndex = Number($("#myForm #startIndex").val()) + 1;
    var pageCount = Number($("#myForm #pageCount").val());
    var remainCount = Number(nTotalCnt) - (startIndex * pageCount);
    $("#myForm #isSubAppChange").val("N");
	if(remainCount <= 0){
		$("#myForm #remainCount").val("0");
		$("#moreCount1").html("");
		$("#moreCount2").html("");
		$("#moreCount3").html("");
		$("#moreAll1").html("");
        $("#moreAll2").html("");
        $("#moreAll3").html("");
        jQuery("#moreCount1").css("display", "none");
        jQuery("#moreCount2").css("display", "none");
        jQuery("#moreCount3").css("display", "none");
        jQuery("#moreAll1").css("display", "none");
        jQuery("#moreAll2").css("display", "none");
        jQuery("#moreAll3").css("display", "none");
	}else{
		$("#myForm #remainCount").val(remainCount);
		$("#moreCount1").html(LANG_SET["FR000034"]+'<span>('+ addCommas(remainCount) +")</span>");
		$("#moreCount2").html(LANG_SET["FR000034"]+'<span>('+ addCommas(remainCount) +")</span>");
		$("#moreCount3").html(LANG_SET["FR000034"]+'<span>('+ addCommas(remainCount) +")</span>"); 
		$("#moreAll1").html(LANG_SET["FR000249"]+'<span>('+ addCommas(remainCount) +")</span>");
        $("#moreAll2").html(LANG_SET["FR000249"]+'<span>('+ addCommas(remainCount) +")</span>");
        $("#moreAll3").html(LANG_SET["FR000249"]+'<span>('+ addCommas(remainCount) +")</span>");
        jQuery("#moreCount1").css("display", "block");
        jQuery("#moreCount2").css("display", "block");
        jQuery("#moreCount3").css("display", "block");
        jQuery("#moreAll1").css("display", "block");
        jQuery("#moreAll2").css("display", "block");
        jQuery("#moreAll3").css("display", "block");
	}
}
function doFilterSearch(){
    // 필터영역 접고
	$(".open_filter").trigger("click");
	$("#myForm #isSubAppChange").val("N");
	// 검색
	getItemGroupList(false);
}
function changeSubApp(obj){
	$("#myForm #isPageYn").val("Y");
	if($('#area_itemGroup').css('display') == 'none'){
		igListShowHide();
	}
    // Sub Application class="on/off" 처리
    var sel_class_on = $(obj).attr('class');
    var ar_sa_cd = $("#myForm #sa_cd").val();
    var sel_sa_cd = $(obj).data('sacd');
    // 선택한 Sub가 on
    if("on" == sel_class_on){
        $(obj).removeAttr('class');
        ar_sa_cd = ar_sa_cd.replace(sel_sa_cd, '');
    }else{
        $(obj).attr('class', 'on');
        if(ar_sa_cd != ''){
            ar_sa_cd = ar_sa_cd + ',' + sel_sa_cd;
        }else{
            ar_sa_cd = sel_sa_cd;
        }
    }
    $(obj).blur();

    ar_sa_cd = ar_sa_cd.replace(/,\s*$/, "");
    
    var selectSub = ar_sa_cd.split(",");

    var subAppTitleCnt = "";
    var totalItemCnt = 0;
    ar_sa_cd = "";
    for ( var i = 0; i < selectSub.length; i++ ) {
        if(selectSub[i] != "" && selectSub[i].trim().length > 0){
            if(i == 0){
                ar_sa_cd = selectSub[i];
            }else{
                ar_sa_cd = ar_sa_cd +','+selectSub[i];
            }
	        var subApp = $('#sa_' + selectSub[i]);
	        var sa_nm = $(subApp).data('sanm');
	        var sub_cnt = $(subApp).data('subcnt');
	        var nSub_cnt = String(sub_cnt).replace(',', '');
	        totalItemCnt = totalItemCnt + Number(nSub_cnt);
	        if(i > 0){
		        <%-- Sub Application --%>
	            $('#subAppTitle').text(LANG_SET["FR000118"]);
	            subAppTitleCnt = subAppTitleCnt +' + '+ sa_nm + '('+sub_cnt+')';
	        }else{
	            subAppTitleCnt = sa_nm + '('+sub_cnt+')';
	            $('#subAppTitle').text(sa_nm);
	        }
        }
    }
    if(subAppTitleCnt.substring(1, 2) == "+"){
    	subAppTitleCnt = subAppTitleCnt.substring(2, subAppTitleCnt.length);
    }
    $('#subAppTitleCnt').text(toTruncate(subAppTitleCnt, 50, "..."));
    $("#myForm #sa_cd").val(ar_sa_cd);
    $("#myForm #unit_cd").val($("#btnSizeUnit").val());
    $("#myForm #isSubAppChange").val("Y");
    $("#myForm #isSubAppChange").val("Y");
    getItemGroupList(false);
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
function makeFilterParam(){
	filterParamList = [];
	var filterParam = null;
	var isInputFilter = false;
	
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
		if(filterParam.value != ""){
			isInputFilter = true;
		}
		filterParamList.push(filterParam);  
	});
	
	if(!$(".open_filter").hasClass("active") ){
	    if(isInputFilter){
//	        $(".open_filter").children('img').attr("src","/img/ico_onfilter.png");
	        $(".open_filter").css("background", "#0094DA");
	    }else{
//	        $(".open_filter").children('img').attr("src","/img/ico_filter.png");
            var view_top_color = $('.view_top').css("background");
            $(".open_filter").css("background", view_top_color);
	    }
	}
}
/**
 * yji_21-10-20 
 * 플로팅
 */
/*
function float_move(type, target_cd) {
	console.log("========================")
	console.log(type)
	console.log(target_cd)
	console.log("========================")
	var button_Type   = "";	// TOP / IG / ITEM
	var $target	 	  = "";	// 이동하여 위치 할 엘리먼트
// 	var target_cd 	  = "";	// 이동하여 위치 할 엘리먼트 고유번호
	var target_height = ""; // target_IG의 문서 내 스크롤 위치
	var active_view	  = "big";	// 현재 보여지고있는 표시 형태 (초기셋팅 big 아래에서 check)
	var active_view_btn = $('.view_top > .btn_area > ul > li > a.on').attr('class');
 	if (active_view_btn.includes("btn_text")){	// 텍스트 표시 활성화
		active_view	  = "text";
	} else if(active_view_btn.includes("btn_small")) {
		active_view	  = "small";
	} else {
		active_view	  = "big";
	}
	// 아이템그룹리스트의 선택된 엘리먼트로 이동 
	if (type == "IG") {
// 		target_cd = $(obj).data("target");	// 이동하여 위치 할 엘리먼트
		target_cd = target_cd;	// 이동하여 위치 할 엘리먼트
		if (target_cd != "") {
			if ($('#area_itemGroup').css("display") == "none") {	// bar close 시 open 먼저
				$('#area_itemGroup').slideToggle()
			}
			// 포커스 잡을 IG ==> target (활성 view 별로 처리)
			if (active_view == "text") {
				$target = $('#item_group_list3 > tr > td[data-igcd="' + target_cd + '"]').parents('tr')
			} else if (active_view == "small") {
				$target = $('#item_group_list2 > li > a[data-igcd="' + target_cd + '"]')
			} else {
				$target = $('#item_group_list > li > a[data-igcd="' + target_cd + '"]')
			}
			// IG 찾을수 없을경우 return
			if ($target.length < 1) {
				return;
			} 
			target_height = $target.offset().top;
			target_height -= 60;	// header에 가려짐 방지

			$target.addClass('f_btn_highlight');
		} else {
			console.log("IG없음")
			return;
		}
	} else if (type == "ITEM") {	// 아이템그룹으로 이동
		target_cd = target_cd.split(":");	// 이동하여 위치 할 엘리먼트
		var matnr = target_cd[0];
		var designation = target_cd[1];
		$target = $('#table_itemList tr:contains("' + matnr + '"):contains("' + designation + '")')
		console.log($target)
// 		console.log(matnr + ":" + designation)
		if (target_cd != "") {
			console.log(1)
			if ($('#area_itemList > .box_cont').css("display") == "none") {	// bar close 시 open 먼저
				console.log(2)
// 				$('#area_itemList .btn_down').click()
				$('#area_itemList .box_cont').slideToggle()
			}
			// IT 찾을수 없을경우 return
			if ($target.length < 1) {
				console.log(3)
				return;
			} 
			console.log(4)
			target_height = $target.offset().top;
			target_height -= 60;	// header에 가려짐 방지
			console.log("target_height ==> " + target_height)
// 			$target.addClass('f_btn_highlight');
		} else {
			console.log("IT없음")
			return;
		}
	} else {	// TOP으로 이동
		target_height = 0;
	}
	// 스크롤 이동
	$('html, body').animate({ scrollTop: target_height }, 300);
	setTimeout(function(){
		$('a, tr').removeClass('f_btn_highlight');
	},400);
};*/


function float_move() {
	var target_height = ""; // 이동할 요소의 문서 내 스크롤 위치
	var $open_area = "" ; // 클릭하여 open 할 영역 (IG/Item List)

	if ($('#area_itemList > .box_cont').is(":visible")) {	//	open된 영역이 아이템리스트면 IG로 이동
// 		back_type = "IG";	// IG로 이동
		target_height = $('#igListBar').offset().top;
		$open_area = $('#igListBar > .tit');
		if ($('#area_itemList > .box_cont').is(":visible")) $('#area_itemList > .box_cont').slideToggle(); 
		if ($('#area_item > .box_cont').is(":visible")) $('#area_item > .box_cont').slideToggle(); 
	} else if($('#area_item > .box_cont').is(":visible")) {	//	open된 영역이 아이템이면 Item List로 이동
// 		back_type = "ITL";	// Item List로 이동
		target_height = $('#area_itemList').offset().top;
		$open_area = $('#area_itemList > .box_top');
		if ($('#area_itemGroup').is(":visible")) $('#area_itemGroup').slideToggle(); 
		if ($('#area_itemList > .box_cont').is(":visible")) $('#area_itemList > .box_cont').slideToggle(); 
	}else {
		history.go(-1)	// 뒤로가기
	}
	// 스크롤 이동
	$('html, body').animate({ scrollTop: target_height - 60 }, 300);
	if ($open_area != "") $open_area.click();
}
</script>
