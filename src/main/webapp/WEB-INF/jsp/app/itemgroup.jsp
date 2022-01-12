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
<%@page import = "com.eaction.framework.business.logic.app.model.AppSearchInfo"%>
<%@page import = "com.eaction.framework.business.logic.app.model.AppInfo"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%
AppInfo searchInfo = (AppInfo)request.getAttribute(ConstKey.SEARCH_DATA);
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
	<div id="contents" class="item_wrap">
		<div class="bg">
			<div class="inner">
				<div class="mainapp_list">
					<h3>Main Application</h3>
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<a href="#" class="on">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont01.png" alt="Milling">
									</div> <span>Milling</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont02.png" alt="Hole Making">
									</div> <span>Hole Making</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont03.png" alt="ISO-Turning">
									</div> <span>ISO-Turning</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont04.png" alt="Threading">
									</div> <span>Threading</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont05.png"
											alt="Multifunction Turning">
									</div> <span>Multifunction Turning</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont06.png" alt="Solid End Milling">
									</div> <span>Solid End Milling</span>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_maincont06.png" alt="Solid End Milling">
									</div> <span>Solid End Milling</span>
								</a>
							</div>
						</div>
					</div>
					<div class="btn_prev">이전</div>
					<div class="btn_next">다음</div>
				</div>
				<div class="sub_applist">
					<h3>Sub Application</h3>
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<a href="#" class="on">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist02.png" alt="Ramping">
									</div>
									<div class="txt">Ramping</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
							<div class="swiper-slide">
								<a href="#">
									<div class="img">
										<img src="<%=PATH_IMG %>/img_subapplist01.png" alt="Facing">
									</div>
									<div class="txt">Facing</div>
								</a>
							</div>
						</div>
					</div>
					<div class="btn_prev">이전</div>
					<div class="btn_next">다음</div>
				</div>
			</div>
		</div>
		<div class="listview">
			<div class="inner">
				<h2>Item Group List (120)</h2>
				<div class="view">
					<div class="view_top">
						<div class="tit">
							<span>All (4,3456)</span><span>MILLNG (4,3456)</span><span>Facing
								(423)</span>
						</div>
						<div class="btn_area">
							<ul>
								<li class="recommend"><select name="" id="">
										<option value="">Recommended</option>
										<option value="">New</option>
										<option value="">Sell</option>
								</select></li>
								<li><a href="#" class="open_filter"><img
										src="<%=PATH_IMG %>/ico_filter.png" alt="텍스트로 보기"></a></li>
								<li><a href="#"><img src="<%=PATH_IMG %>/ico_liststyle01.png"
										alt="텍스트로 보기"></a></li>
								<li><a href="#" class="on"><img
										src="<%=PATH_IMG %>/ico_liststyle02.png" alt="큰썸네일로 보기"></a></li>
								<li><a href="#"><img src="<%=PATH_IMG %>/ico_liststyle03.png"
										alt="작은썸네일"></a></li>
							</ul>
						</div>
						<div class="filter">
							<div class="filter_list">
								<ul>
									<li><span>DC 공구 인선 각도</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>BVF Mounting type machine side</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>CO Mounting type machine side</span>
										<div id="slider-range-min"></div></li>
									<li><span>DC 공구 인선 각도</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>C11 최대 캠핑 각도</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>PPP Mounting type machine side</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>Q14 Mounting type machine side</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>C11 최대 캠핑 각도</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>AC 절삭 직경</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
									<li><span>CO Mounting type machine side</span>
										<div id="slider-range-min02"></div></li>
									<li><span>Q14 Mounting type machine side</span>
										<div id="slider-range"></div>
										<p>
											<label for="amount"></label> <input type="text" id="amount"
												readonly style="border: 0; color: #0094da; font-size: 12px;">
										</p></li>
									<li><span>AC 절삭 직경</span>
										<div class="select">
											<select name="" id="">
												<option value="">Option selected</option>
											</select>
										</div></li>
								</ul>
							</div>
							<div class="btn_group">
								<div class="center">
									<a href="#" class="btn01 same">Reset</a>
								</div>
							</div>
						</div>
					</div>

					<div class="box">
						<div class="box_top">
							<span>선택한 아이템 그룹 이름</span> <a href="#" class="btn_down"><img
								src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
						</div>
						<div class="box_cont">
							<div class="itemtit">
								<p>
									<img src="<%=PATH_IMG %>/img_alphamill.png" alt="">AlphaMill-X
								</p>
								<span>Tools carrying Inserts with 5cutting Edges</span>
								<div class="info">
									<a href="#">More Info.</a>
								</div>
							</div>
							<div class="picture_wrap">
								<div class="picture">
									<div class="big">
										<a href="#" class="expand"><img
											src="<%=PATH_IMG %>/img_gallery01.png" alt="큰이미지"></a>
									</div>
									<div class="small">
										<ul>
											<li><a href="#"><img
													src="<%=PATH_IMG %>/img_gallerysmall01.png" alt="작은이미지1"></a></li>
											<li><a href="#"><img
													src="<%=PATH_IMG %>/img_gallerysmall02.png" alt="작은이미지2"></a></li>
											<li><a href="#"><img
													src="<%=PATH_IMG %>/img_gallerysmall03.png" alt="작은이미지3"></a></li>
											<li><a href="#"><img
													src="<%=PATH_IMG %>/img_gallerysmall04.png" alt="작은이미지4"></a></li>
											<li><a href="#"><img
													src="<%=PATH_IMG %>/img_gallerysmall05.png" alt="작은이미지5"></a></li>
										</ul>
									</div>
								</div>
								<div class="picture_detail">
									<div class="picture_tab">
										<ul>
											<li><a href="#" class="on">ISO 1399</a></li>
											<li><a href="#">Non-ISO</a></li>
										</ul>
									</div>
									<a href="#" class="expand"><img
										src="<%=PATH_IMG %>/img_detail01.png" alt="도면"></a>
								</div>
							</div>

							<div class="box">
								<div class="box_top">
									<span>Filter</span> <a href="#" class="btn_down"><img
										src="<%=PATH_IMG %>/btn_down.png" alt="내리기"></a>
								</div>
								<div class="box_cont">
									<div class="filter_detail">
										<ul>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>TPDC
															Plus Drill</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>Alpha
															Mill-X</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>PC9540</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>NCM535
															· NCM545</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>TPDB-F</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>TPDB-H</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>NC6310·NC6315</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>Pro-V
															Mill</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>Triple
															Mill</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>Super
															Endmill For HRSA</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>KHP
															Coolant</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>K
															Notch</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>FP
															칩브레이커(포지티브)</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>SSD-N</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>TPDB
															Plus</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>A+
															Endmill</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>RM14</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>S+
															Endmill</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>HFMD</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>PC3700</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>FS·MS
															칩브레이커</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>Saw
															Man-X</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>UNC805·UNC840</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>PC6510</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>MM·RM
															칩브레이커</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>TAP</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>CC1500·CC2500</span>
													</label>
												</div>
											</li>
											<li>
												<div class="check">
													<label class="chkN"> <input type="checkbox"><span>MP
															칩브레이커(포지티브)</span>
													</label>
												</div>
											</li>
										</ul>
									</div>

									<div class="listTable">
										<table>
											<colgroup>
												<col width="10%">
												<col width="15%">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="*">
												<col width="20%">
											</colgroup>
											<tbody>
												<tr>
													<th>Order Number</th>
													<th>Designation</th>
													<th>Cart</th>
													<th>DC</th>
													<th>DCX</th>
													<th>OAL</th>
													<th>DCONMS</th>
													<th>APMX</th>
													<th>WT</th>
													<th>CXSC</th>
													<th>Grade</th>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
												</tr>
												<tr>
													<td><a href="#">1-21-0004568</a></td>
													<td>ADKT170608PESR-MM</td>
													<td><a href="#" class="bnt_cart"><img
															src="<%=PATH_IMG %>/ico_cart.png" alt=""></a></td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>40</td>
													<td>Y</td>
													<td>PC8105 · PC8110 · PC8115</td>
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
	</div>
</div>
<form id="myForm" name="myForm" method="POST" action="<%=ConstUrl.APP_ITEM_URL%>">
    <input type="hidden" name="<%=ConstKey.ACTION_ID %>" id="<%=ConstKey.ACTION_ID %>" />
    <input type="hidden" name="<%=ConstKey.VAL_MAIN_APP_CD %>" id="<%=ConstKey.VAL_MAIN_APP_CD %>" value="<%=searchInfo.getMa_cd() %>" />
    <input type="hidden" name="<%=ConstKey.VAL_SUB_APP_CD %>" id="<%=ConstKey.VAL_SUB_APP_CD %>" value="<%=searchInfo.getSa_cd() %>"/>
    <input type="hidden" name="<%=ConstKey.VAL_ITEM_GRP_CD %>" id="<%=ConstKey.VAL_ITEM_GRP_CD %>" value="<%=searchInfo.getIg_cd() %>"/>
    <input type="hidden" name="<%=ConstKey.VAL_ITEM_CD %>" id="<%=ConstKey.VAL_ITEM_CD %>" />
</form>
<script type="text/javascript">

jQuery(document).ready(function(){


});
function goItem(matnr){
    $("#myForm #matnr").val(matnr);
    goPageTwo (myForm, "");
}



</script>
