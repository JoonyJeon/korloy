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
<%@page import = "java.util.Date"%>
<%@page import = "java.text.SimpleDateFormat"%>
<%@page import = "com.eaction.framework.common.util.PagingUtil" %>
<%@page import = "com.eaction.framework.common.util.DateTimeUtil" %>
<%@page import = "com.eaction.framework.common.util.StringUtil" %>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.app.constant.AppConstKey"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<%
	List<AppInfo> item_prop_list = (List<AppInfo>)request.getAttribute("item_prop_list");
%>

        <div id="container" class="sub">
            <div class="path">
                <div class="inner">
                    <ul>
                        <li class="home">Milling</li>
                        <li>Rampdown</li>
                        <li>AMXCM</li>
                        <li class="blue">AMXCM050R-22-5-AD17</li>
                    </ul>
                </div>
            </div>
            <div class="top_filter">
                <div class="inner">
                    <ul>
                        <li><span>FILTER</span></li>
                        <li><a href="#">Millig</a></li>
                        <li><a href="#">Facing</a></li>
                        <li><a href="#">Shouldering</a></li>
                        <li><a href="#">Slotting</a></li>
                        <li><a href="#">Rampdown</a></li>
                        <li><a href="#">Coolant</a></li>
                        <li><a href="#">AA 90</a></li>
                        <li><a href="#">AR 8</a></li>
                        <li><a href="#">RR -10 ~-3</a></li>
                    </ul>
                    <div class="btn_print">
                        <a href="#"><img src="<%=PATH_IMG %>/btn_print.png" alt="인쇄"></a>
                    </div>
                </div>
            </div>
            <div id="contents">
                
                <div class="listview">
                    <div class="inner">
                        <div class="itemtit">AMXCM <span>AlphaMill-X <a href="#" class="more">More Info.</a></span></div>
                        <div class="picture_wrap">
                            <div class="picture">
                                <div class="big"><a href="#" class="expand"><img src="<%=PATH_IMG %>/img_gallery01.png" alt="큰이미지"></a></div>
                                <div class="small">
                                    <ul>
                                        <li><a href="#"><img src="<%=PATH_IMG %>/img_gallerysmall01.png" alt="작은이미지1"></a></li>
                                        <li><a href="#"><img src="<%=PATH_IMG %>/img_gallerysmall02.png" alt="작은이미지2"></a></li>
                                        <li><a href="#"><img src="<%=PATH_IMG %>/img_gallerysmall03.png" alt="작은이미지3"></a></li>
                                        <li><a href="#"><img src="<%=PATH_IMG %>/img_gallerysmall04.png" alt="작은이미지4"></a></li>
                                        <li><a href="#"><img src="<%=PATH_IMG %>/img_gallerysmall05.png" alt="작은이미지5"></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="picture_detail">
                                <a href="#" class="expand"><img src="<%=PATH_IMG %>/img_detail01.png" alt="도면"></a>
                            </div>
                        </div>
                        <div class="box">
                            <div class="box_top">
                                <span>Item</span>
                                <a href="#" class="btn_down"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                            </div>
                            <div class="box_cont">
                                <div class="box_tab">
                                    <ul>
                                        <li><a href="#" class="on">ISO 13399</a></li>
                                        <li><a href="#">Non ISO</a></li>
                                    </ul>
                                </div>
                                <div class="listTable">
                                    <table>
                                        <colgroup>
                                        	<%if(item_prop_list != null && item_prop_list.size() > 0){ %>	
                                            	<col width="20%">                                            
                                            <% for(AppInfo result : item_prop_list){ %>
                                            	<col width="*">
                                            <% } %>
                                            <% } %>
                                            
                                            <%-- 
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                             --%>
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                        	<%if(item_prop_list != null && item_prop_list.size() > 0){ %>
                                            	<th>Designation</th>                
                                            <% for(AppInfo result : item_prop_list){ %>
                                            	<th><%= result.getSymbol() %></th>                                            	
                                            <% } %>
                                            <% } %>
                                            
                                            <!-- 
                                            <th>DC</th>
                                            <th>DCX</th>
                                            <th>OAL</th>
                                            <th>DCONMS</th>
                                            <th>APMX</th>
                                            <th>WT</th>
                                            <th>CXSC</th>
                                            <th>ZEFP</th>
                                            <th>CICT</th>
                                            <th>DHUB</th>
                                             -->
                                        </tr>
                                        <tr>
                                        	<%if(item_prop_list != null && item_prop_list.size() > 0){ %>
                                            	<td><%= item_prop_list.get(0).getDesignation() %></td>                                            	
                                            <% for(AppInfo result : item_prop_list){ %>
                                            	<td class="right"><%= result.getVal() %></td>
                                            <% } %>                                            
                                            <% } %>
                                            
                                            <!-- <td class="right">40</td>
                                            <td class="right">9</td>
                                            <td class="right">40</td>
                                            <td class="right">16</td>
                                            <td class="right">16.5</td>
                                            <td class="right">0.18</td> 
                                            <td>Y</td>
                                            <td class="right">10</td>
                                            <td class="right">10</td>
                                            <td class="right">35</td>
                                             -->
                                        </tr>
                                        </tbody>
                                        <tbody class="blueLine">
                                            <tr>
                                                <th>Cart NO.</th>
                                                <th colspan="2">File Download</th>
                                                <th colspan="2">Add to Assembly</th>
                                                <th colspan="2">Currency</th>
                                                <th colspan="2">Price</th>
                                                <th colspan="2">Stock Availability</th>
                                            </tr>
                                            <tr>
                                                <td>2021050506</td>
                                                <td>GTC</td>
                                                <td>P21</td>
                                                <td colspan="2"><img src="<%=PATH_IMG %>/img_assembly.png" alt="assembly"></td>
                                                <td colspan="2">USD</td>
                                                <td colspan="2" class="right">4,530.00</td>
                                                <td colspan="2" class="left">Stock</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div> 
                        </div>
                        <div class="box_two">
                            <div class="box">
                                <div class="box_top">
                                    <span>3D Representation</span>
                                    <div class="box_tab">
                                        <ul>
                                            <li><a href="#" class="on">Basic</a></li>
                                            <li><a href="#">Detailed</a></li>
                                        </ul>
                                    </div>
                                    <a href="#" class="download">Download</a>
                                    <a href="#" class="btn_down"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                                </div>
                                <div class="box_cont">
                                    <div class="img">
                                        <a href="#" class="expand"><img src="<%=PATH_IMG %>/img_representation01.png" alt=""></a>
                                    </div>
                                </div> 
                            </div>
                            <div class="box">
                                <div class="box_top">
                                    <span>2D Representation</span>
                                    <a href="#" class="btn_down"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                                </div>
                                <div class="box_cont">
                                    <div class="img">
                                        <%-- <a href="#" class="expand"><img src="<%=PATH_IMG %>/img_representation02.png" alt=""></a> --%>
                                        <a href="#" class="expand"><img <%=item_prop_list.get(0).getDxf_img() %> alt=""></a>
                                    </div>
                                </div>  
                            </div>
                        </div>
                        <div class="box">
                            <div class="box_top">
                                <span>Related Inserts</span>
                                <a href="#" class="btn_down" id="relatedInserts"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                            </div>
                            <div class="box_cont">
                                <div class="listTable hover">
                                    <table>
                                        <colgroup>
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th class="left" colspan="7">Related Inserts Group</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td class="on">
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_inserts01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="listTable maT20">
                                    <table>
                                        <colgroup>
                                            <col width="20%">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th>Order Number</th>
                                            <th>l</th>
                                            <th>d</th>
                                            <th>t</th>
                                            <th>r</th>
                                            <th>dl</th>
                                            <th colspan="6">Grade</th>
                                        </tr>
                                        <tr>
                                            <td>AMXCM050R-22-5-AD17</td>
                                            <td class="right">19.65</td>
                                            <td class="right">10.843</td>
                                            <td class="right">6.529</td>
                                            <td class="right">0.8</td>
                                            <td class="right">4.5</td>
                                            <td class="left"><a href="#" class="under">NCM535</a></td>
                                            <td class="left"><a href="#" class="under">PC3700</a></td>
                                            <td class="left"><a href="#" class="under">PC9530</a></td>
                                            <td class="left"><a href="#" class="under">PC9540</a></td>
                                            <td class="left"><a href="#" class="under">PC5300</a></td>
                                            <td class="left"><a href="#" class="under">PC5400</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div> 
                        </div>
                        <div class="box">
                            <div class="box_top">
                                <span>Related Holders</span>
                                <a href="#" class="btn_down" id="relatedHolders"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                            </div>
                            <div class="box_cont">
                                <div class="listTable">
                                    <table>
                                        <colgroup>
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th class="left" colspan="7">Related Holders Group</th>
                                        </tr>
                                        <tr>
                                            <td class="on">
                                                <span><img src="<%=PATH_IMG %>/img_holders01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_holders01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_holders01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_holders01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td>
                                                <span><img src="<%=PATH_IMG %>/img_holders01.png" alt=""></span>
                                                <em>ADKT-ML</em>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="listTable maT20">
                                    <table>
                                        <colgroup>
                                            <col width="20%">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th>Order Number</th>
                                            <th>l</th>
                                            <th>d</th>
                                            <th>t</th>
                                            <th>r</th>
                                            <th>dl</th>
                                            <th colspan="6">Grade</th>
                                        </tr>
                                        <tr>
                                            <td>AMXCM050R-22-5-AD17</td>
                                            <td class="right">19.65</td>
                                            <td class="right">10.843</td>
                                            <td class="right">6.529</td>
                                            <td class="right">0.8</td>
                                            <td class="right">4.5</td>
                                            <td class="left"><a href="#" class="under">NCM535</a></td>
                                            <td class="left"><a href="#" class="under">PC3700</a></td>
                                            <td class="left"><a href="#" class="under">PC9530</a></td>
                                            <td class="left"><a href="#" class="under">PC9540</a></td>
                                            <td class="left"><a href="#" class="under">PC5300</a></td>
                                            <td class="left"><a href="#" class="under">PC5400</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div> 
                        </div>
                        <div class="box_two">
                            <div class="box">
                                <div class="box_top">
                                    <span>Recommended Cutting Speeds</span>
                                    <a href="#" class="btn_down"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                                </div>
                                <div class="box_cont">
                                    <div class="listTable">
                                    <table>
                                        <colgroup>
                                            <col width="10%">
                                            <col width="15%">
                                            <col width="15%">
                                            <col width="*">
                                            <col width="15%">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th>ISO</th>
                                            <th>Symbol</th>
                                            <th>mm</th>
                                            <th>print MM</th>
                                            <th>Grade</th>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">ap</td>
                                            <td class="left">mm</td>
                                            <td class="left">8.00mm(1.00-16.00)</td>
                                            <td class="left">PC3700</td>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">vc</td>
                                            <td class="left">m/min</td>
                                            <td class="left">215m/min(160-270)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">fz</td>
                                            <td class="left">mm/t</td>
                                            <td class="left">0.20mm/t(0.10-0.30)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_orange"><span>S</span></td>
                                            <td class="left">ap</td>
                                            <td class="left">mm</td>
                                            <td class="left">2.00mm(1.00-3.50)</td>
                                            <td class="left">UNC840</td>
                                        </tr>
                                        <tr>
                                            <td class="bg_orange"><span>S</span></td>
                                            <td class="left">vc</td>
                                            <td class="left">m/min</td>
                                            <td class="left">0.30mm/t(0.10-0.50)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_orange"><span>S</span></td>
                                            <td class="left">fz</td>
                                            <td class="left">mm/t</td>
                                            <td class="left">50m/min(30-80)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">ap</td>
                                            <td class="left">mm</td>
                                            <td class="left">8.00mm(1.00-16.50)</td>
                                            <td class="left">PC5300</td>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">vc</td>
                                            <td class="left">m/min</td>
                                            <td class="left">195m/min(150-240)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_blue"><span>P</span></td>
                                            <td class="left">fz</td>
                                            <td class="left">mm/t</td>
                                            <td class="left">0.10mm/t(0.08-0.25)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_yellow"><span>M</span></td>
                                            <td class="left">ap</td>
                                            <td class="left">mm</td>
                                            <td class="left">8.00mm(1.00-16.50)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_yellow"><span>M</span></td>
                                            <td class="left">vc</td>
                                            <td class="left">m/min</td>
                                            <td class="left">120m/min(90-150)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_yellow"><span>M</span></td>
                                            <td class="left">fz</td>
                                            <td class="left">mm/t</td>
                                            <td class="left">0.10mm/t(0.08-0.25)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_red"><span>K</span></td>
                                            <td class="left">ap</td>
                                            <td class="left">mm</td>
                                            <td class="left">8.00mm(1.00-16.50)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_red"><span>K</span></td>
                                            <td class="left">vc</td>
                                            <td class="left">m/min</td>
                                            <td class="left">160m/min(120-200)</td>
                                            <td class="left"></td>
                                        </tr>
                                        <tr>
                                            <td class="bg_red"><span>K</span></td>
                                            <td class="left">fz</td>
                                            <td class="left">mm/t</td>
                                            <td class="left">0.10mm/t(0.08-0.25)</td>
                                            <td class="left"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                </div> 
                            </div>
                            <div class="box">
                                <div class="box_top">
                                    <span>Spare Parts</span>
                                    <a href="#" class="btn_down"><img src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
                                </div>
                                <div class="box_cont">
                                    <div class="listTable">
                                    <table>
                                        <colgroup>
                                            <col width="*">
                                            <col width="*">
                                            <col width="*">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <th>Specification</th>
                                            <th>Screw</th>
                                            <th>Wrench</th>
                                        </tr>
                                        <tr>
                                            <td class="left">040-080</td>
                                            <td class="left">FTKA0410</td>
                                            <td class="left">TW15S</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">

jQuery(document).ready(function(){
	
	//화면이벤트를 모두 정의함
	bindClickEvent();


});

function bindClickEvent() {		
	
	//Related Inserts
	$('#relatedInserts').on('click', function(ev){
		ev.preventDefault();
		goRelatedInserts();
	});
	
	//Related Holders
	$('#relatedHolders').on('click', function(ev){
		ev.preventDefault();
		goRelatedHolders();
	});
	
	
}

// Related Inserts 취득
function goRelatedInserts(){
	alert("goRelatedInserts");
}

//Related Holders 취득
function goRelatedHolders(){
	alert("goRelatedHolders");
}





</script>
