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
<%@page import = "java.util.Map"%>
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.grade.constant.GradeConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.grade.constant.GradeConstKey"%>
<%@page import = "com.eaction.framework.business.logic.grade.model.GradeInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
	//검색 조건
	Map<String, Object> condition_lists = (Map<String, Object>) request.getAttribute("condition_lists");
	List<CodeInfo> main_app_list = (List<CodeInfo>)condition_lists.get("mainAppList");
	List<CodeInfo> iso_grade_list = (List<CodeInfo>) condition_lists.get("isoGradeList");
	List<CodeInfo> grade_class_list = (List<CodeInfo>) condition_lists.get("gradeClassList");
	List<CodeInfo> subst_list = (List<CodeInfo>) condition_lists.get("substList");
	
	//url Controller에서 초기 조회인지 item에서 이동한 것인지 구분하는 값을 가져온다
	String fromItemYn = (String)request.getAttribute("fromItmeYn");
	String grade = (String)request.getAttribute("grade");
%>

<div id="container" class="sub">
<!-- GNB Navi 아래 page depth 표시 -->
    <div class="path">
        <div class="inner">
            <ul>
            	<%-- Home --%>
                <li class="home" data-lang="FR000137"><%=LangMng.LANG_D("FR000137", session_lang) %></li>
                <%-- Grades Guide --%>
                <li data-lang="FR000002"><%=LangMng.LANG_D("FR000002", session_lang) %></li>
            </ul>
        </div>
    </div>
	<div id="contents" class="guide_wrap">
	    <div class="listview">
	        <div class="inner">
	        	 <%-- Grades Guide --%>
	            <h2 data-lang="FR000002"><%=LangMng.LANG_D("FR000002", session_lang) %></h2>
	            <div class="grade_guide">
           	<!-- Main Application -->
	                <div class="guide">
	                 	<%-- Main Application --%>
	                    <span data-lang="FR000081"><%=LangMng.LANG_D("FR000081", session_lang) %></span>
	                    <div class="guide_list">
	                        <ul id="main_app_conditions" style="display:flex;flex-wrap:wrap;">
		                        <% if(main_app_list !=null && main_app_list.size()!=0){ %>
		                       		<% for(CodeInfo result : main_app_list) {%>
			                            <li>
			                            	<%-- <input type="checkbox" name="main_app"  value="<%=result.getCode() %>" style="display:none;"/> --%>
			                            	<a href="javascript:void(0);" 
			                            		id="<%=result.getCode() %>" onclick="onAndOffClass('<%=result.getCode() %>', '<%=result.getName() %>', 'ma_cd_arr')" 
			                            		style="font-weight:400;"
			                            		data-lang="<%=result.getCode() %>"
			                            		class="" ><%=result.getName() %></a>
			                            </li>
		                        	<%} %>
		                        <%} %>
	                        </ul>
	                    </div>
	                </div> 
            	<!-- ISO Grade -->
	                <div class="guide" >
	                <%-- ISO Grade --%>
	                    <span data-lang="FR000082"><%=LangMng.LANG_D("FR000082", session_lang) %></span>
	                    <div class="guide_list iso" >
	                        <ul id="iso_grade_conditions">
	                             <% if(iso_grade_list !=null && iso_grade_list.size()!=0){ %>
		                       		<% for(CodeInfo result : iso_grade_list) {%>
			                            <li>
			                            	<a 
			                            		style="font-weight:400;"
			                            		id="<%=result.getCode() %>" 
			                            		onclick="onAndOffClass('<%=result.getCode() %>', '<%=result.getName() %>', 'iso_grade_arr')" 
		                            			<%if (result.getName().length() >11) {%>
			                            		<%} %>
			                            		<% if("P".equals(result.getName())) {%>
			                            			class="isoP"
			                            		<%} %>
			                            		<% if("M".equals(result.getName())) {%>
			                            			class="isoM"
			                            		<%} %>
			                            		<% if("K".equals(result.getName())) {%>
			                            			class="isoK"
			                            		<%} %>
			                            		<% if("N".equals(result.getName())) {%>
			                            			class="isoN"
			                            		<%} %>
			                            		<% if("S".equals(result.getName())) {%>
			                            			class="isoS"
			                            		<%} %>
			                            		<% if("H".equals(result.getName())) {%>
			                            			class="isoH"
			                            		<%} %>
			                            		<% if("Z".equals(result.getName())) {%>
			                            			class="isoZ"
			                            		<%} %>
			                            		<% if("V".equals(result.getName())) {%>
			                            			class="isoV"
			                            		<%} %>
			                            		<% if("I".equals(result.getName())) {%>
			                            			class="isoI"
			                            		<%} %>
			                            		<% if("E".equals(result.getName())) {%>
			                            			class="isoE"
			                            		<%} %>
			                            		><%=result.getName() %></a>
			                            </li>
		                        	<%} %>
		                        <%} %>
	                        </ul>
	                    </div>
	                </div>  
            	<!-- Grade Classfication -->
	                <div class="guide">
	               		<%-- Grade Classfication --%>
	                    <span data-lang="FR000083"><%=LangMng.LANG_D("FR000083", session_lang) %></span>
	                    <div class="guide_list">
	                        <ul class="nine" id="grade_class_conditions" style="display:flex;flex-wrap:wrap;">
	                             <% if(grade_class_list !=null && grade_class_list.size()!=0){ %>
		                       		<% for(CodeInfo result : grade_class_list) {%>
			                            <li>
			                            	<%-- <input type="checkbox" name="grade_clas"  value="<%=result.getName() %>" style="display:none;" /> --%>
			                            	<a href="javascript:void(0);" 
			                            		id="<%=result.getCode() %>" 
			                            		style="font-weight:400;"
			                            		onclick="onAndOffClass('<%=result.getCode() %>','<%=result.getName() %>', 'grade_clas_arr')" 
			                            		class=""><%=result.getName() %></a>
			                            </li>
		                        	<%} %>
		                        <%} %>
	                        </ul>
	                    </div>
	                </div>  
            	<!-- Substrate -->
	                <div class="guide">
	                	<%-- Substrate --%>
	                    <span data-lang="FR000084"><%=LangMng.LANG_D("FR000084", session_lang) %></span>
	                    <div class="guide_list">
	                        <ul class="nine" id="subst_conditions" style="display:flex;flex-wrap:wrap;">
	                             <% if(subst_list !=null && subst_list.size()!=0){ %>
		                       		<% for(CodeInfo result : subst_list) {%>
			                            <li>
			                            	<%-- <input type="checkbox" name="subst"  value="<%=result.getName() %>" style="display:none;" /> --%>
			                            	<a href="javascript:void(0);" 
			                            		style="font-weight:400;"
			                            		id="<%=result.getCode() %>" 
			                            		onclick="onAndOffClass('<%=result.getCode() %>','<%=result.getName() %>', 'subst_arr')" 
			                            		class=""><%=result.getName() %></a>
			                            </li>
		                        	<%} %>
		                        <%} %>
	                        </ul>
	                    </div>
	                </div>  
	                <div class="guide">
	                	<%-- Grades name --%>
	                    <span data-lang="FR000248"><%=LangMng.LANG_D("FR000248", session_lang) %></span>
	                    <div class="guide_list">
	                        <div class="input">
	                            <input type="text" id="grade_name">
	                        </div>
	                    </div>
	                </div>  
	                <div class="btn_group marTop">
	                    <div class="center">
	                        <a href="javascript:void(0);" id="reset" onclick="onAndOffClass('reset', 'reset', null)" class="reset" data-lang="FR000060">
	                        	<%-- Reset --%>
	                        	<%=LangMng.LANG_D("FR000060", session_lang) %>
	                        </a>
	                        <a href="javascript:void(0);" id="cancel" onclick="onCancelSelect('cancel')"class="cancel" data-lang="FR000264">
	                        	<%-- cancel --%>
	                        	<%=LangMng.LANG_D("FR000264", session_lang) %>
	                        </a>
	                        <a href="javascript:void(0);" onclick="getSearchList('search')" class="search" data-lang="FR000138">
	                        	<%-- Search --%>
	                        	<%=LangMng.LANG_D("FR000138", session_lang) %>
	                        </a>
	                    </div>
	                </div>
	            </div> <!-- end of grade_guide(검색조건 부분) -->
<!--+++++++++++++ Search Result Start+++++++++++++++-->
	            <div class="view" id="part_of_search_result">
	                <h2 id="total_rows"></h2>
	                <div class="view_top">
	                    <div class="tit" id="all_rows"></div>
	                </div>
	                <div class="view_cont">
					    <div class="gradeTable pc">
					        <table  class="fixTable">
					              <colgroup>
									<col width="160px">
									<col width="120px">
									<col width="80px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="*">
									<col width="130px">
									<col width="160px">
					            </colgroup>
					            <thead class="resFix">
					                <tr id="table_head_fixed">
					                    <th data-lang="FR000081">
					                    	<%-- Main Application --%>
					                    	<%=LangMng.LANG_D("FR000081", session_lang) %>
					                    </th>
					                    <th data-lang="FR000086">
					                    	<%-- Grade Type For --%>
					                    	<%=LangMng.LANG_D("FR000086", session_lang) %>
					                    </th>
					                    <th data-lang="FR000041">
						                	<%-- Grade --%>
					                		<%=LangMng.LANG_D("FR000041", session_lang) %>
						                </th>
						                <th class="isoP">P</th>
						                <th class="isoM">M</th>
						                <th class="isoK">K</th>
						                <th class="isoN">N</th>
						                <th class="isoS">S</th>
						                <th class="isoH">H</th>
						                <th data-lang="FR000083">
						                	<%-- Grade classification --%>
						                	<%=LangMng.LANG_D("FR000083", session_lang) %>
						                </th>
						                <th data-lang="FR000087">
						                	<%-- Coating LayerCoating Layer --%>
						                	<%=LangMng.LANG_D("FR000087", session_lang) %>
						                </th>
						                <th data-lang="FR000088">
						                	<%-- Coating --%>
						                	<%=LangMng.LANG_D("FR000088", session_lang) %>
						                </th>
					                </tr>
					            </thead>
					        </table>
					        <!--재종 데이터를 그리는 테이블 -->
					        <table id="search_result">
					            <colgroup>
									<col width="160px">
									<col width="120px">
									<col width="80px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="78px">
									<col width="*">
									<col width="130px">
									<col width="160px">
								</colgroup>
					            <tbody id="tbody_flex">
						            <tr id="table_ctnt_flex">
						                <td style="text-align:left;"></td>
						                <td style="text-align:left;"></td>
						                <td style="text-align:left;" class="viewGrade"></td>
						                <td class="isoP"></td>
						                <td class="isoM"></td>
						                <td class="isoK"></td>
						                <td class="isoN"></td>
						                <td class="isoS"></td>
						                <td class="isoH"></td>
						                <td style="text-align:left;"></td>
						                <td style="text-align:left;"></td>
						                <td style="text-align:left;"></td>
						            </tr>
						            <tr id="no_result">
						            	<td colspan="12"  data-lang="FR000136">
			            					<%-- 	검색 결과가 없습니다. --%>
					                		<%=LangMng.LANG_D("FR000136", session_lang) %>
						            	</td>
						            </tr>
					            </tbody>
					        </table>
					    </div>
					    <!-- 모바일용 테이블 -->
					    <div class="box_cont mobile" id="result_mob">
							<!-- LOOP -->
							<div class="listTable blueLine noMargin noScroll grade" style="display:none;" id="result_mob_clone">
								<p class="currentItem">
									<a href="" onclick="" class="gradeName" data-grade="" data-macd=""></a> / <span class="mainAppName" style="margin-bottom: 0px;"></span>
								</p>
								<table class="mobileDetailItem" >
									<colgroup>
										<col width="100%">
									</colgroup>
									<tbody>
									<tr>
										<th class="mdTitle" data-lang="FR000086"><%=LangMng.LANG_D("FR000086", session_lang) %> <span class="typeName" style="margin-bottom: 0px;"></span></th>
									</tr>
									<tr>
										<td>
											<span class="isoP" style="margin-bottom: 0px;"></span>
											<span class="isoM" style="margin-bottom: 0px;"></span>
											<span class="isoK" style="margin-bottom: 0px;"></span>
											<span class="isoN" style="margin-bottom: 0px;"></span>
											<span class="isoS" style="margin-bottom: 0px;"></span>
											<span class="isoH" style="margin-bottom: 0px;"></span>
										</td>
									</tr>
									<tr>
										<td>
											<div class="mdTable">
												<table>
													<colgroup>
														<col width="40%">
														<col width="*">
													</colgroup>
													<tbody>
													<tr>
														<!-- Grade Classification -->
														<th data-lang="FR000083"><%=LangMng.LANG_D("FR000083", session_lang) %></th>
														<td class="gradeClasVal"></td>
													</tr>
													<tr>
														<!-- Coating Layer -->
														<th data-lang="FR000087"><%=LangMng.LANG_D("FR000087", session_lang) %></th>
														<td class="coatLayerVal"></td>
													</tr>
													<tr>
													<!-- Coat -->
														<th data-lang="FR000088"><%=LangMng.LANG_D("FR000088", session_lang) %></th>
														<td class="coatVal"></td>
													</tr>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
									<tr class="gradeDescPartTr">
										<td class="gradeDescPart"></td>
									</tr>
									</tbody>
								</table>
							</div>
							<!-- LOOP -->
						</div>
					</div>
	            </div>
	            <div class="more" id="part_of_more" style="display:none;">
	                <a href="javascript:void(0);" onclick="getSearchList('more')" class="btn_more" data-lang="FR000034">
	                	<%-- More --%>
	                	<%=LangMng.LANG_D("FR000034", session_lang) %>
	                	<span id="more_cnt"></span>
	                </a>
	                 <a href="javascript:void(0);" onclick="getSearchList('all')" class="btn_more" data-lang="FR000249">
	                	<!-- List all -->
	                	<%=LangMng.LANG_D("FR000249", session_lang) %>
	                </a>
	            </div>
	               
	        </div>
	    </div>
	</div>
</div>
<!-- 더보기 처리를 위해 -->
<input type="hidden" id="all_startIndex" name="startIndex" value=0 />
<input type="hidden" id="all_endIndex" name="endIndex" value=20 />
<input type="hidden" id="all_tot_cnt" name="tot_cnt" value=0 />
<style>
#footer{
	position:inherit;
}
td.viewGrade {
    color: #0094da !important;
    cursor:pointer;
    text-decoration: underline;
}
/* .guide_wrap .listTable.grade span { */
/*    margin-bottom: 0px !important;  */
/* } */
</style>
<script type="text/javascript">
//더보기 처리를 위한 변수 선언
var startIndex = 0;
var endIndex = 20;
var perCnt = 20;
var more_cnt = 0;
var searchCount = 0;
var moreCount = 0;
var allCnt = 0;
//검색 시 넘길 조건처리를 위한 변수 선언
const arrays = {
		iso_grade_arr:[],
		ma_cd_arr:[],
		grade_clas_arr:[],
		subst_arr:[],
}

//객체 내부 변수 초기화
arrays.iso_grade_arr = [];
arrays.ma_cd_arr = [];
arrays.grade_clas_arr= [];
arrays.subst_arr = [];

//iso_grade_list 제어를 위해
var isoListLength = 0
var isoList = new Array();
var fromItemYn = '<%=fromItemYn%>';

jQuery(document).ready(function(){
    $('.loading').delay('1000').fadeOut();
	getSearchList('first')

    // 언어변경
    $("#btnLang").change(function(){            
           	cur_lang = this.value;
            //언어 변경시
			getSearchList('chgLang')
    });
    // 길이단위변경
    $("#btnSizeUnit").change(function(){            
               cur_size_unit = this.value;
    });
    // 통화변경
    $("#btnCurr").change(function(){            
               cur_currency = this.value;
    });

    var jbOffset = $( '.fixTable' ).offset();
    $( window ).scroll( function() {
      if ( $( document ).scrollTop() > jbOffset.top - 100 ) {
        $( '.fixTable' ).addClass( 'jbFixed' );
        $( '.gradeTable' ).addClass( 'fixed' );
      }
      else {
        $( '.fixTable' ).removeClass( 'jbFixed' );
        $( '.gradeTable' ).removeClass( 'fixed' );
      }
    });
});

//검색조건 클릭 이벤트 활성/비활성
function onAndOffClass(id/*code(reset버튼일 경우 reset)*/, value/*name (reset버튼일 경우 reset)*/, array_name/*검색조건을 넣을 배열 변수 이름(reset일 경우 null)*/){
	//a tag의 아이디로 class 제어 
	//main_app_list는 code 값을 배열에 넣어야 하고 이외에는 name값을 넣어야함
	
	//검색조건의 버튼 클릭 했을 때
	if(id != 'reset') {
		var elementClass = $('#'+id).attr('class');
		
		//1. class가 on 인 경우
		if($('#'+id).hasClass('on')){
			//클래스 삭제
			$('#'+id).removeClass('on');
			//검색조건 배열에서 삭제  ma_cd_arr(main application)인 경우와 아닌 경우 나눠서 처리 
			var index ;
			array_name == 'ma_cd_arr' ? index = arrays[array_name].indexOf(id)  : index = arrays[array_name].indexOf(value);
			arrays[array_name].splice(index, 1);
			
		//2. class가 없는 경우
		}else{
			//클래스 추가
			$('#'+id).addClass('on');
			//검색조건 배열에 추가 ma_cd_arr(main application)인 경우와 아닌 경우 나눠서 처리 
			array_name == 'ma_cd_arr' ? arrays[array_name].push(id) : arrays[array_name].push(value);
		}
	//3. reset 버튼 클릭했을 때 class on 되어있는 부분 삭제
	}else{
		$('#main_app_conditions > li a').removeClass('on');
		$('#iso_grade_conditions > li a').removeClass('on');
		$('#grade_class_conditions > li a').removeClass('on');
		$('#subst_conditions > li a').removeClass('on');
		$('#grade_name').val('')
		
		//검색조건 배열 모두 초기화
		arrays.iso_grade_arr=[];
		arrays.ma_cd_arr=[];
		arrays.grade_clas_arr=[];
		arrays.subst_arr=[];
		searchCount = 0;
		getSearchList('first');
	}
}

//select 조건 전체 cancel

function onCancelSelect(id)
{
	
	$('#main_app_conditions > li a').removeClass('on');
	$('#iso_grade_conditions > li a').removeClass('on');
	$('#grade_class_conditions > li a').removeClass('on');
	$('#subst_conditions > li a').removeClass('on');
	$('#grade_name').val('');
	
	arrays.iso_grade_arr=[];
	arrays.ma_cd_arr=[];
	arrays.grade_clas_arr=[];
	arrays.subst_arr=[];
	//searchCount = 0;

	
//	 $('#main_app_conditions > li a').removeAttr("class");
//	 $('#iso_grade_conditions > li a').removeAttr("class");
//	 $('#grade_class_conditions > li a').removeAttr("class");
//	 $('#subst_conditions > li a').removeAttr("class");
//	 $('#main_app_conditions > li a').removeAttr("class");
//	 $('#grade_name').val('');
}



//more button누를 때와 search버튼 누를 때 다르게 처리해야 함
function getSearchList(search_type/*search, more, first, chgLang*/){
	var formData = new FormData();
	//더보기일때
	if(search_type =='more'){
		formData.append("startIndex", $("input[name='startIndex']").val());
	//검색버튼 누르거나 초기조회일 때  		
	}else if(search_type == 'chgLang'){
 		//상단 gnb navigation에서 국가 선택을 변경했을 떄
 		//현재 조회한 개수 만큼 재 조회
 		formData.append("startIndex",  0);
 		formData.append("pageCount", $("input[name='endIndex']").val()-20);
 		
 	}else  if(search_type =='search' || search_type == 'first'){
	 	//초기조회인데 아이템페이지에서 넘어오는 경우
	 	if(fromItemYn == 'Y'){
	 		$('#grade_name').val('<%=grade%>')
<%-- 			formData.append("grade", '<%=grade%>'); --%>
		}else if(fromItemYn == 'N' && searchCount == 0){
			//아예 초기 조회인 경우
			$('#main_app_conditions > li a').click();
			$('#iso_grade_conditions > li a').click();
			$('#grade_class_conditions > li a').click();
			$('#subst_conditions > li a').click();
			searchCount++;
		}
		//검색의 경우 처읍부터 ~ 20개를 조회하는 것 startIndex = 0
		formData.append("startIndex", 0);
		
		//검색버튼을 누르기 전에 more 버튼을 눌러
		//더보기 관련 변수들의 값을 수정했을 수도 있기 때문에 처음 값으로 초기화
		startIndex = 0;
		endIndex = 20;
		perCnt = 20;
		more_cnt = 0;
		 $("input[name='startIndex']").val(0);
		 $("input[name='endIndex']").val(20);
		 $("input[name='tot_cnt']").val(0);
 	}else if(search_type == 'all'){
 		formData.append("pageCount", allCnt);
 		formData.append("startIndex", $("input[name='startIndex']").val());
 	}
	var isoRag = arrays['iso_grade_arr'].join();
	var maCds = arrays['ma_cd_arr'].join();
	var gradeClas = arrays['grade_clas_arr'].join();
	var subst = arrays['subst_arr'].join();
	
	//검색조건을 누르지 않고 검색하려고 할 때 
	//검색조건을 입력한 것이 없다면 return false;
 	var totalLength = arrays['iso_grade_arr'].length+arrays['ma_cd_arr'].length+arrays['grade_clas_arr'].length+arrays['subst_arr'].length;
 	if(totalLength == 0 && fromItemYn == 'N' && search_type =='search'){
 		<%-- 검색조건을 선택해주세요. --%>
<%-- 		alert('<%=LangMng.LANG_D("FR000135", session_lang) %>');  --%>
		alert(LANG_SET['FR000135']); 
		return;
	}

 	<%-- [YJI(211019)] --%>
	// Log : 재종가이드 Search 시
	var logText = "";
	var $main_app_on 	= $('#main_app_conditions > li > a.on');	// Main Application Ele
	var $iso_grade_on 	= $('#iso_grade_conditions > li > a.on');	// ISO_GRADE
	var $grade_class_on = $('#grade_class_conditions > li > a.on');	// Grade Classification
	var $subst_on 		= $('#subst_conditions > li > a.on');		// Substrate
	var log_target_arr = [];	// On options array
	log_target_arr.push({[LANG_SET.FR000081] : $main_app_on});		// Main Application
	log_target_arr.push({[LANG_SET.FR000082] : $iso_grade_on});		// ISO Grade
	log_target_arr.push({[LANG_SET.FR000083] : $grade_class_on});	// Grade Classification
	log_target_arr.push({[LANG_SET.FR000084] : $subst_on});			// Substrate
	
	/**
		EX ==> Main Application:N/A; ISO Grade:P,M,K,N,S,H; Grade Classification:PVD,WC,Coated Cermet,Cermet,Diamond Coating,cBN,PCD; Substrate:HT,HC,BH,BL,BN; 
	*/ 
	log_target_arr.forEach(function(value, index, array) {
	    if (index > 0) {	// 구분자 처리
	        logText += "; ";
	    }
	    logText += Object.keys(value) + ":";	// MA ; IG ; GRADE CLASS ; SUBST
	    var ele = Object.values(value)[0];		// On options Text
	    if (ele.length > 0) {
	        logText += ele[0].text;
	        for (var i = 1; i < ele.length; i++) {	// 첫번째 제외 구분자 처리 ,VAR 
	            logText += "," + $(ele[i]).text()	// P,M,K,N,S,H
	        }
	    } else {
	        logText += LANG_SET.FR000119;	// No Data
	    }
	})
	
	logText += "; " + LANG_SET.FR000248 + ":";						// Search Grade
	logText += $('#grade_name').val() == "" ? LANG_SET.FR000119 : $('#grade_name').val();
	
	// alert(logText)
	doLog("EC0009", "", $('#grade_name').val());
	// LOG End---------------------------
 	<%-- // [YJI(211019)] --%>
	
	formData.append("actionID",'<%=GradeConstKey.ACTION_GRADE_GUIDE_SEARCH_RESULT_LIST%>');
	formData.append("ma_cd",maCds);
	formData.append("grade_clas",gradeClas);
	formData.append("subst",subst);
	formData.append("iso_rag",isoRag);
	formData.append("grade",$('#grade_name').val());

    var nation = "";
    if(USERINFO.user_nation == ""){
    	formData.append("nation", "USA");
    }else{
    	formData.append("nation", USERINFO.user_nation);
    }
	
	//ajax호출
	$.ajax({
		type: "POST",
		url: '<%=ConstUrl.GRADES_GUIDE_URL%>?v=' + (new Date().getTime()),
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
			//조회된 총 row의 개수 표시
									<%-- Search Result --%>
<%-- 		    $('#total_rows').html('<%=LangMng.LANG_D("FR000085", session_lang) %>'+" ("+result.nTotal+")") --%>
		    $('#total_rows').html(LANG_SET['FR000085']+" ("+result.nTotal+")")
								<%-- All --%>
		    allCnt = result.allTotal
<%-- 		    $('#all_rows').html('<%=LangMng.LANG_D("FR000028", session_lang) %>'+" ("+result.allTotal+")") --%>
		    $('#all_rows').html(LANG_SET['FR000028']+" ("+result.allTotal+")")
				//search버튼을 클릭, more 버튼을 클릭했을 경우를
				//나눠서 분기처리 해줘야 하기 때문에 파라미터로 search(검색버튼 클릭했을 떄)/more(more버튼 클릭 시)/chgLang(상단 혹은 모바일에서 언어변경시) 넘겨준다.
				if(search_type =='search'||search_type =='first'){
					 appendSearchResult('search', result)
				}else if(search_type == 'chgLang'){
					 appendSearchResult('chgLang', result)
				}else{
					appendSearchResult('more', result);
				}
		}, error: function (e){
// 			console.log(e);
		},
		complete: function(){
			  $('#loadbar').fadeOut();
		}
	});
	
}

//검색결과 insertRow search랑 more 구분
function appendSearchResult(search_type/*search, more, first, chgLang, all*/, result/*ajax결과로 넘긴 list, nTotal*/){
	
	//국가를 변경하지 않을때만 더보기 관련 변수 및 화면 제어
	if(search_type !='chgLang'){
		//startIndex 값
		var st = $("input[name='startIndex']").val() *1 + perCnt
		$("input[name='startIndex']").val(st)
		// endIndex 값
		var ed = $("input[name='endIndex']").val() *1 + perCnt
		$("input[name='endIndex']").val(ed)
		//기존 데이터수 * 1 + 조회 데이터 개수 * 1
		var tt = $("input[name='tot_cnt']").val() * 1 + result.list.length * 1
		$("input[name='tot_cnt']").val(tt)
		// 조건에 맞는 조회 리스트의 전채 개수와 현제 조회한 데이터 개수 비교 후 더보기 버튼 보이고 숨기기 
		tt < result.nTotal ? $("div.more").show() : $("div.more").hide();
	}
	
	//검색버튼을 눌렀을 경우
	if(search_type !='more'){
		if(result.nTotal == 0){
			$('#table_ctnt_flex').hide();
			$('#no_result').show();
			$('#search_result').css('overflow','hidden');
		}else{
			//조회된 결과가 0이 아닐 경우 more버튼 옆 숫자 조정(초기 조회시) 결과 없을 경우 조회된 결과 없음 표시
			$('#no_result').hide();
			search_type == 'search' ?  more_cnt = result.nTotal-20 : '';
		}
		if(search_type == 'search'|| search_type == 'first'){
			//첫번째 row를 제외한 나머지 삭제
			$('#tbody_flex').children().not('#table_head_flex').not('#table_ctnt_flex').not('#no_result').remove();
		}
	}else{
		more_cnt -=20 
	}
	//more버튼 옆 숫자 표시
	$('#more_cnt').html("("+more_cnt+")");
	//상단 언어 변경시 혹은 모바일에서 언어변경시에는 그리지 않는다
	if(search_type != 'chgLang'){
		//list의 길이만큼 row 및 컬럼 생성
		//PC와 모바일에서 보여지는 부분이 다르기 때문에 따로 각각 그려준다.
		for(var i =0; i< result.list.length; i++){
	//++++++++++++++++++++++++++++++++++++++++++++++ PC S		
			$('#table_ctnt_flex').show();
			
			var cloneFlex = $('#table_ctnt_flex').clone();
			$('#tbody_flex').append(cloneFlex);
			$('#tbody_flex').children().last().attr('id', '');
			
			var elementFlex = $('#tbody_flex').children().last()
			elementFlex.find('td:eq(0)').html(result.list[i].ma_nm)
			elementFlex.find('td:eq(0)').attr('data-lang',result.list[i].ma_cd)
			elementFlex.find('td:eq(1)').html(result.list[i].tips_cd_nm)
			elementFlex.find('td:eq(1)').attr('data-lang',result.list[i].tips_cd)
			elementFlex.find('td:eq(2)').html(result.list[i].grade)
			//재종 상세 팝업
			if(result.list[i].grade_desc != ""){
				elementFlex.find('td:eq(2)').attr('data-grade', result.list[i].grade)
				elementFlex.find('td:eq(2)').attr('data-macd', result.list[i].ma_cd)
				elementFlex.find('td:eq(2)').attr('onclick', 'goGradeDetailPop(this)')
			}else{
				elementFlex.find('td:eq(2)').removeClass('viewGrade')
				elementFlex.find('td:eq(2)').attr('onclick','')
				
			}
			elementFlex.find('td.isoP').html(result.list[i].iso_p)
			elementFlex.find('td.isoM').html(result.list[i].iso_m)
			elementFlex.find('td.isoK').html(result.list[i].iso_k)
			elementFlex.find('td.isoN').html(result.list[i].iso_n)
			elementFlex.find('td.isoS').html(result.list[i].iso_s)
			elementFlex.find('td.isoH').html(result.list[i].iso_h)
			elementFlex.find('td:eq(9)').html(result.list[i].grade_clas)
			elementFlex.find('td:eq(10)').html(result.list[i].coat_lay)
			elementFlex.find('td:eq(11)').html(result.list[i].coat)
			
	//++++++++++++++++++++++++++++++++++++++++++++++ PC S		
	//++++++++++++++++++++++++++++++++++++++++++++++ Mobile S	
			var cloneMob = $('#result_mob_clone').clone();
			$('#result_mob').append(cloneMob);
			$('#result_mob').children().last().attr('id', '');
			
			var elementMob = $('#result_mob').children().last()
			elementMob.show();
			elementMob.find('a.gradeName').html(result.list[i].grade)
			
			var loopNum = i + moreCount*20
			var toggleId = 'grade_'+loopNum
			
			elementMob.find('.mobileDetailItem').attr('id', toggleId)
			elementMob.find('a.gradeName').attr('onclick',"$('#"+toggleId+"').click()")
			elementMob.find('a.gradeName').attr('href','javascript:void(0);')
			elementMob.find('.currentItem').attr('onclick',"toggleMobileGrade('"+toggleId+"')")
			
			if(result.list[i].grade_desc != ''){
				elementMob.find('.gradeDescPart').html(result.list[i].grade_desc)
				elementMob.find('.gradeDescPart').attr('data-lang', result.list[i].grade)
			}else{
				elementMob.find('.gradeDescPartTr').remove();
			}
			
			elementMob.find('.mainAppName').html(result.list[i].ma_nm)
			elementMob.find('.mainAppName').attr('data-lang',result.list[i].ma_cd)
			elementMob.find('.typeName').html(' : '+result.list[i].tips_cd_nm)
			elementMob.find('.typeName').attr('data-lang',result.list[i].tips_cd)
			
			result.list[i].iso_p != '' ? elementMob.find('span.isoP').html(result.list[i].iso_p) : elementMob.find('span.isoP').remove();
			result.list[i].iso_m != '' ? elementMob.find('span.isoM').html(result.list[i].iso_m) : elementMob.find('span.isoM').remove();
			result.list[i].iso_k != '' ? elementMob.find('span.isoK').html(result.list[i].iso_k) : elementMob.find('span.isoK').remove();
			result.list[i].iso_n != '' ? elementMob.find('span.isoN').html(result.list[i].iso_n) : elementMob.find('span.isoN').remove();
			result.list[i].iso_s != '' ? elementMob.find('span.isoS').html(result.list[i].iso_s) : elementMob.find('span.isoS').remove();
			result.list[i].iso_h != '' ? elementMob.find('span.isoH').html(result.list[i].iso_h) : elementMob.find('span.isoH').remove();
			
			elementMob.find('.gradeClasVal').html(result.list[i].grade_clas)
			elementMob.find('.coatLayerVal').html(result.list[i].coat_lay)
			elementMob.find('.coatVal').html(result.list[i].coat)
			
	//++++++++++++++++++++++++++++++++++++++++++++++ Mobile E
		} /* end of for */
		moreCount++;
		 $('#table_ctnt_flex').hide();
		 $('#result_mob_clone').hide();
	}
}
//PC에서 재종 클릭 시 상세 팝업 띄우기
function goGradeDetailPop(obj){
		var grade = $(obj).data("grade");
		var ma_cd = $(obj).data("macd")
		
		// Log : Grades Guide Search Result 의 재종 클릭시
		doLog("EC0010", grade, "");

	    var nation = "";
	    if(USERINFO.user_nation == ""){
	    	nation = "USA";
		}else{
			nation = USERINFO.user_nation;
		}
		var param ={
					actionID	: '<%=GradeConstKey.ACTION_GRADE_DETAIL%>'
					,grade		: grade
					,ma_cd		: ma_cd
					,nation	:  nation
		}	
		//ajax호출
		$.ajax({
			type: "POST",
			url: '<%=ConstUrl.GRADES_GUIDE_URL%>?v=' + (new Date().getTime()),
			data: param,
			dataType: 'json',
			async: true,
			cache: false,
	        beforeSend: function(){
// 	        	$('#loadbar').show();
	        },
			success: function(result) {
				console.log(result)
				//이전 정보 초기화
				$('#grade_pop').html('')
				$('#iso_range_pop').html('')
				$('#iso_grade_pop').html('')
				$('#coat_pop').html('')
				$('#coatLayer_pop').html('')
				$('#desc_section > .grDesc').remove();
				var gradeInfo = result.gradeInfo
				//팝업 정보 표시
				//재종
				$('#grade_pop').html(gradeInfo.grade)
				//ISO 범위
				$('#iso_range_pop').html(gradeInfo.iso_range)
				
				//ISO 재종
				var isoGrades = new Array();
				if(gradeInfo.iso_p != "" && gradeInfo.iso_p != null){
					isoGrades.push(gradeInfo.iso_p)
				}
				if(gradeInfo.iso_h != "" && gradeInfo.iso_h != null){
					isoGrades.push(gradeInfo.iso_h)
				}
				if(gradeInfo.iso_k != "" && gradeInfo.iso_k != null){
					isoGrades.push(gradeInfo.iso_k)
				}
				if(gradeInfo.iso_m != "" && gradeInfo.iso_m != null){
					isoGrades.push(gradeInfo.iso_m)
				}
				if(gradeInfo.iso_n != "" && gradeInfo.iso_n != null){
					isoGrades.push(gradeInfo.iso_n)
				}
				if(gradeInfo.iso_o != "" && gradeInfo.iso_o != null){
					isoGrades.push(gradeInfo.iso_o)
				}
				if(gradeInfo.iso_s != "" && gradeInfo.iso_s != null){
					isoGrades.push(gradeInfo.iso_s)
				}
				$('#iso_grade_pop').html(isoGrades.join(", "))
				$('#iso_range_pop').html(" - "+gradeInfo.iso_rag)
				
				if(gradeInfo.grade_desc != "" && gradeInfo.grade_desc !=null){
					//설명 추가
					$('#desc_section').html(gradeInfo.grade_desc);
				}
				
				//코팅
				$('#coat_pop').html(gradeInfo.coat)
				//코팅레이어
				$('#coatLayer_pop').html(gradeInfo.coat_lay)
				
			}, error: function (e){
	// 			console.log(e);
			},
			complete: function(){
				  $('#loadbar').fadeOut();
			}
		});
		
        $(".open_grade_pop").fadeIn();
        return false;
}
//Mobile에서 재종 toggle
function toggleMobileGrade(id){
    if($('#'+id).css('display') == 'table'){
	    $(".mobileDetailItem").hide();
		$('#'+id).slideUp();
    }else{
	    $(".mobileDetailItem").hide();
		$('#'+id).slideDown();
    }
}
</script>
<!-- Grade 팝업 // -->
<div class="open_grade_pop" style="z-index:9999;">
    <div class="in">
		<a href="#" class="close_pop"><img src="./img/btn_close.png" alt="닫기"></a>
		<div class="pop_cont">
										<!-- Grade -->
				<p class="grTitle" data-lang="FR000041"><%= LangMng.LANG_D("FR000041", session_lang)%><span> : </span><span id="grade_pop"></span></p>
				<p class="grDesc" id="desc_section"></p>
			<div class="grTable" id="iso_div_pop">
				<table id="iso_info_table_pop">
					<colgroup>
					<col width="30%" /><col width="70%" />
					</colgroup>
					<tr>
							<!--ISO Range-->
						<th data-lang="FR000082"><%= LangMng.LANG_D("FR000082", session_lang)%><span id="iso_range_pop"></span></th>
						<td id="iso_grade_pop"></td>
					</tr>
				</table>
			</div>
			<div class="grTable" id="coating_div_pop">
				<table id="coating_info_table_pop">
					<colgroup>
					<col width="30%" /><col width="70%" />
					</colgroup>
					<tr id="coating_section">
						<!-- Coating-->
						<th data-lang="FR000088"><%= LangMng.LANG_D("FR000088", session_lang)%></th>
						<td id="coat_pop"></td>
					</tr>
					<tr id="coating_layer_section">
						<!-- Coating Layer -->
						<th data-lang="FR000087"><%= LangMng.LANG_D("FR000087", session_lang)%></th>
						<td id="coatLayer_pop"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Grade 팝업 // -->
