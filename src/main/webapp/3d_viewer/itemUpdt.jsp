<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="tit">
	<i class="fa fa-tag"></i> Item(수정)
</div>

<div class="conBlock paB0">
	<div class="resultGuideSec">
		<div class="arRight">
			<span class="btnS white">
				<a href="javascript:;" class="btn" id="btnList"><i class="fa fa-list"></i> 목록</a>
			</span>
			<span class="btnS lineG">
				<a href="javascript:;" class="btn" id="btnSave"><i class="fa fa-save"></i> 저장</a>
			</span>
		</div>
	</div>
</div>

<form id="frmItem">
	<input type="hidden" id="gubun" readonly>
	<div class="conBlock paT0">
		<div class="inputTable">
			<table>
				<colgroup>
					<col width="180">
					<col width="*">
					<col width="180">
					<col width="*">
					<col width="180">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 플랜트</th>
						<td>
							<input type="text" id="s_werks" name="s_werks" readonly value="<c:out value='${werks}'/>" />
						</td>
						<th><i class="fa fa-chevron-right"></i> 자재번호</th>
						<td>
							<input type="text" id="s_matnr" name="s_matnr" readonly value="<c:out value='${matnr}'/>" />
						</td>
						<th><i class="fa fa-chevron-right"></i> 정가(원화)</th>
						<td>
							<input type="text" id="k_kbetr" readonly />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 형번(Metric)</th>
						<td colspan="3">
							<input type="text" id="designation" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 정가(달러화)</th>
						<td>
							<input type="text" id="u_kbetr" readonly />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 형번(Inch)</th>
						<td colspan="3">
							<input type="text" id="designation_inch" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 정가(유로화)</th>
						<td>
							<input type="text" id="e_kbetr" readonly />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 규격코드(C/B)</th>
						<td>
							<input type="text" id="cb" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 규격코드(C/B)-Inch</th>
						<td>
							<input type="text" id="cb_inch" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 재고수량</th>
						<td>
							<input type="text" id="labst" readonly />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 자재내역</th>
						<td colspan="3">
							<input type="text" id="maktx" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> Item Group</th>
						<td colspan="1">
				        	<select class="select2 w100p" id="ig_cd" name="ig_cd">
				        		<option value="-">Item Group을 선택하세요.</option>
        						<option value="">값없음</option>
				        		<c:forEach items="${itemGroupCombo}" var="result" varStatus="status">
				                    <option value="<c:out value='${result.code}'/>"><c:out value='${result.name}'/></option>
				                </c:forEach>
				        	</select>
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 재종</th>
						<td>
							<input type="text" id="grade" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 해외재종</th>
						<td>
							<input type="text" id="grade_os" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> Unit of Measurement</th>
						<td>
							<div class="btn-group btn-group-radio w100p">
								<c:forEach items="${unitCdCombo}" var="result" varStatus="status">
									<label class="btn btn-lineG" role="button" for="unit_cd_<c:out value='${result.code}'/>">
										<input type="radio" id="unit_cd_<c:out value='${result.code}'/>" name="unit_cd" readonly value="<c:out value='${result.code}'/>"><c:out value='${result.name}'/>
									</label>
				                </c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 사용</th>
						<td>
							<div class="btn-group btn-group-radio w100p">
				        		<label class="btn btn-lineG" role="button" for="use_yn_y">
									<input type="radio" id="use_yn_y" name="use_yn" value="Y">Yes
								</label>
								<label class="btn btn-lineG" role="button" for="use_yn_n">
									<input type="radio" id="use_yn_n" name="use_yn" value="N">No
								</label>
							</div>
						</td>
						<th><i class="fa fa-chevron-right"></i> 신제품 설정 기간</th>
						<td colspan="1">
							<div class="periodSec">
								<input type="text" id="new_fr_dt" class="calendar" name="new_fr_dt">&nbsp;~&nbsp;
								<input type="text" id="new_to_dt" class="calendar ml3" name="new_to_dt">
							</div>
						</td>
						<th><i class="fa fa-chevron-right"></i> Item Type</th>
						<td>
				        	<div class="btn-group btn-group-radio w100p">
								<%-- <c:forEach items="${tipsCdCombo}" var="result" varStatus="status">
									<c:if test="${!status.last}">
										<label class="btn btn-lineG" role="button" for="tips_cd_<c:out value='${result.code}'/>">
											<input type="radio" id="tips_cd_<c:out value='${result.code}'/>" name="tips_cd" readonly value="<c:out value='${result.code}'/>"><c:out value='${result.name}'/>
										</label>
									</c:if>
				                </c:forEach> --%>
				                <input type="text" id="tips_cd_nm" readonly>
				                <input type="hidden" id="tips_cd" name="tips_cd">
							</div>
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> LCS 코드</th>
						<td>
							<input type="text" id="lcs" name="lcs" maxlength="3" />
						</td>
						<th><i class="fa fa-chevron-right"></i> SC 코드</th>
						<td>
							<input type="text" id="labor" readonly />
						</td>
						<th><i class="fa fa-chevron-right"></i> 최소주문수량코드</th>
						<td>
							<input type="text" id="moq" readonly />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> LCS 코드설명</th>
						<td colspan="1">
							<input type="text" id="lcs_txt" name="lcs_txt" />
						</td>
						<th><i class="fa fa-chevron-right"></i> GTC_vendor_id</th>
						<td colspan="1">
							<input type="text" id="vendor_id" name="vendor_id" class="wd248" placeholder="Vendor ID" readonly="readonly">
							<span class="btnS lineG">
								<a href="javascript:;" class="contReq btn" id="btnGtcSearch"><i class="fa fa-search"></i></a>
							</span>
						</td>
						<th><i class="fa fa-chevron-right"></i> 최소주문수량</th>
						<td>
							<input type="number" id="moq_qty" name="moq_qty" />
						</td>
					</tr>
					<tr>
						<th><i class="fa fa-chevron-right"></i> 담당부서</th>
						<td colspan="1">
							<select class="select w100p" id="dept_cd" readonly>
				        		<option value="">N/A</option>
				        		<c:forEach items="${deptCdCombo}" var="result" varStatus="status">
				                    <option value="<c:out value='${result.code}'/>"><c:out value='${result.name}'/></option>
				                </c:forEach>
				        	</select>
						</td>
						<th><i class="fa fa-chevron-right"></i> 전략그룹</th>
						<td colspan="1">
							<select class="select w100p" id="strgr" readonly>
				        		<option value="">N/A</option>
				        		<option value="40">Stock</option>
				        		<option value="50">Non Stock</option>
				        	</select>
						</td>
						<th></th>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
		
	<div class="conBlock">
		<div class="inputTable">
			<table>
				<colgroup>
					<col width="33.3%">
					<col width="33.3%">
					<col width="33.3%">
				</colgroup>
				<tbody>
					<tr>
						<th class="">
							<i class="fa fa-chevron-right"></i> 사진 업로드(최소 400*300)
							<span class="float-right" id="item_image_download"></span>
						</th>
						<th class="">
							<i class="fa fa-chevron-right"></i> ISO 도면 업로드(최소 X*400)
							<span class="float-right" id="iso_dd_image_download"></span>
						</th>
						<th class="">
							<i class="fa fa-chevron-right"></i> Non ISO 도면 업로드(최소 X*400)
							<span class="float-right" id="non_dd_image_download"></span>
						</th>
					</tr>
					<tr>
						<td>
							<input type="file" class="dropify" id="item_image" name="item_image" />
						</td>
						<td>
							<input type="file" class="dropify" id="iso_dd_image" name="iso_dd_image" />
						</td>
						<td>
							<input type="file" class="dropify" id="non_dd_image" name="non_dd_image" />
						</td>
					</tr>
					<tr>
						<th class="">
							<i class="fa fa-chevron-right"></i> 2D(DXF)파일 업로드
							<span class="float-right" id="dxf_download"></span>
						</th>
						<th class="">
							<i class="fa fa-chevron-right"></i> 3D(STP Basic)파일 업로드
							<span class="float-right" id="stp_basic_download"></span>
							<span class="float-right" id="stp_basic_3d"></span>
						</th>
						<th class="">
							<i class="fa fa-chevron-right"></i> 3D(STP Detail)파일 업로드
							<span class="float-right" id="stp_detail_download"></span>							
							<span class="float-right" id="stp_detail_3d"></span>
						</th>
					</tr>
					<tr>
						<td>
							<input type="file" class="dropify" id="dxf_file_image" name="dxf_file_image" />
						</td>
						<td>
							<input type="file" class="dropify" id="stp_file_image" name="stp_file_image" />
						</td>
						<td>
							<input type="file" class="dropify" id="stp_file_detail_image" name="stp_file_detail_image" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>		
	</div>
	
	<div class="conBlock">
		<div class="resultGuideSec">
			<div class="halfGuide">
				<div class="midTitle">속성 (Property)</div>
			</div>
			<div class="halfGuide arRight">
				<!-- <label class="chkN mt5">
					<input type="checkbox" id="chkSearchEmpty" value="true"><span></span> 비어있는 <em>Property</em> 값만 보기
				</label> -->
				<select class="select" id="selProperty">
	        		<option value="">전체 Property 보기</option>
	        		<option value="notempty">값이 있는 Property 보기</option>
	        		<option value="empty">값이 비어있는 Property 보기</option>
	        	</select>
			</div>
		</div>
		<div class="listTable">
	        <table>
	            <colgroup>
	            	<col width="70">
	            	<col width="200">
	            	<col width="640">
	            	<col width="100">
	            	<col width="*">
	            	<col width="18">
	            </colgroup>
	            <thead>
		            <tr>
		            	<th>No</th>
		                <th>ISO Property</th>
		                <th>ISO Property 설명</th>
		                <th>Source</th>
		                <th>ISO Property 값</th>
		                <td></td>
		            </tr>
	            </thead>
	        </table>
	        <div class="h400px over_y_auto">
		        <table id="dtPropertyList">
		            <colgroup>
		            	<col width="70">
		            	<col width="200">
		            	<col width="640">
		            	<col width="100">
		            	<col width="*">
		            </colgroup>
		            <tbody>
			            <tr>
			                <td colspan="5">조회된 내용이 없습니다.</td>
			            </tr>
		            </tbody>
		        </table>
	        </div>
	    </div>
	</div>
	
	<div class="conBlock">
		<div class="resultGuideSec">
			<div class="halfGuide">
				<div class="midTitle">Spare Parts</div>
			</div>
			<div class="halfGuide arRight">
			</div>
		</div>
		<div class="listTable">
	        <table id="dtSparePartsList">
	            <colgroup>
	            	<col width="70">
	            	<col width="*">
	            	<col width="*">
	            </colgroup>
	            <thead>
		            <tr>
		            	<th>No</th>
		                <th>자재번호</th>
		                <th>형번</th>
		            </tr>
	            </thead>
	            <tbody>
	            </tbody>
	        </table>
	    </div>
	</div>
</form>

<div class="mask">
	<!-- VENDOR ID 선택 팝업 -->
	<div class="popWrap sizeTy03 popGtcHierarchy">
		<span class="popClose" title="닫기"></span>
		<p class="popTitle">GTC Hierarchy</p>
		<div class="popCon">
			<p class="txt">Vendor ID 를 선택하세요.</p>
			<div class="select">
				<div id="gtcHierarchy" class="treebox"></div>
			</div>
		</div>
	</div>
	<!-- //VENDOR ID 선택 팝업 -->
	<!-- 3D Rendering 팝업 -->
	<div class="popWrap sizeTy03 pop3dRendering">
		<span class="popClose" title="닫기"></span>
		<p class="popTitle" id="3d_title">3D Rendering</p>
		<div class="popCon">
			<div class="select">
				<iframe id="3d_rendering" src="" style="width:100%; height:600px;">				
				</iframe>
			</div>
		</div>
	</div>
	<!-- //3D Rendering 팝업 -->
</div>

<!-- 사용자 JS -->
<script type="text/javascript">
//=============================================================================
// 화면내에서 사용할 함수를 구현합니다.
//=============================================================================

/******************************************************************************
 * 아이템 정보를 조회합니다.
 */
getItem = function() {
	
	var params = new Object();
	params.s_werks = $('#s_werks').val();
	params.s_matnr = $('#s_matnr').val();
	
	$.ajax({
        url     : fn_get_url('/contents/item/getItem.do'),
        data    : JSON.stringify(params),
        success : function(result) {
        	console.log(result);
        	
        	// 조회된 데이터가 있을 경우에만 처리
        	if (result.data) {
        		
        		var data = result.data;
        		
        		$('#gubun').val(data.gubun);
        		$('#unit_cd_' + data.unit_cd).trigger('click');
        		$('#tips_cd').val(data.tips_cd);
        		
        		if (data.tips_cd == 'CC0049') {
        			$('#tips_cd_nm').val('Tool Indexable');
        		} else if (data.tips_cd == 'CC0050') {
        			$('#tips_cd_nm').val('Tool Solid');
        		} else {
        			$('#tips_cd_nm').val('');
        		}
        		
        		if (data.vendor_id && data.vendor_id.startsWith('ADP')) {
        			$('#tips_cd_nm').val('Adaptive Item');
        		} else if (data.vendor_id && data.vendor_id.startsWith('INS')) {
        			$('#tips_cd_nm').val('Insert');
        		} else if (data.vendor_id && data.vendor_id.startsWith('ASY')) {
        			$('#tips_cd_nm').val('Assembly Item');
        		} else if (data.vendor_id && data.vendor_id.startsWith('ACC')) {
        			$('#tips_cd_nm').val('Accessory Item');
        		}
        		
        		$('#designation').val(data.designation);
        		$('#designation_inch').val(data.designation_inch);
        		$('#cb').val(data.cb);
        		$('#cb_inch').val(data.cb_inch);
        		$('#maktx').val(data.maktx);
        		$('#grade').val(data.grade);
        		$('#grade_os').val(data.grade_os);
        		$('#k_kbetr').val(data.k_kbetr);
        		$('#u_kbetr').val(data.u_kbetr);
        		$('#e_kbetr').val(data.e_kbetr);
        		$('#labst').val(data.labst);
        		$('#moq').val(data.moq);
        		$('#moq_qty').val(data.moq_qty);
        		$('#lcs').val(data.lcs);
        		$('#lcs_txt').val(data.lcs_txt);
        		$('#labor').val(data.labor);
        		$('#ig_cd').val(data.ig_cd).trigger('change');
        		$("#vendor_id").val(data.vendor_id);
        		$("#dept_cd").val(data.dept_cd);
        		$("#strgr").val(data.strgr);
        		$('#new_fr_dt').val(fn_format_string('DATE', data.new_fr_dt));
        		$('#new_to_dt').val(fn_format_string('DATE', data.new_to_dt));
        		$('#use_yn_' + data.use_yn.toLowerCase()).trigger('click');
        		
        		// Image KEY
       			var file_cd = $('#s_matnr').val();
       			
        		// 제품 대표 이미지
        		var tmp = data.item_image_nm.split('|');
        		if (tmp[0]) {
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#item_image_download').html(html);
        		}
        		
        		$('#item_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			minWidth: 400,
        			minHeight: 300,
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.item_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		// ISO 도면 이미지
        		var tmp = data.iso_dd_image_nm.split('|');
        		if (tmp[0]) {
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#iso_dd_image_download').html(html);
        		}
        		
        		$('#iso_dd_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			minHeight: 400,
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.iso_dd_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		// NON ISO 도면 이미지
        		var tmp = data.non_dd_image_nm.split('|');
        		if (tmp[0]) {
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#non_dd_image_download').html(html);
        		}
        		
        		$('#non_dd_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			minHeight: 400,
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.non_dd_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		// PLM에서 인터페이스된 파일의 경우 수정불가능하도록 처리
        		if (data.gubun != 'P') {
        			$('#dxf_file_image').prop('disabled', true);
        			$('#stp_file_image').prop('disabled', true);
        			$('#stp_file_detail_image').prop('disabled', true);
        		}
        		
        		// 2D DXF
       			var tmp = data.dxf_file_image_nm.split('|');
       			if (tmp[0]) {
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#dxf_download').html(html);
        		}
        			
        		$('#dxf_file_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			allowedFileExtensions: ['dxf'],
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.dxf_file_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		// 3D STP(BASIC)
       			var tmp = data.stp_file_image_nm.split('|');
       			if (tmp[0]) {
        		    var html = '<a href="javascript:get3dRendering(\''+file_cd+'\',\''+data.designation+'\',\'B\');">미리보기</a>&nbsp;&nbsp;';
	        		$('#stp_basic_3d').html(html);
	        		
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#stp_basic_download').html(html);
        		}
        		
        		$('#stp_file_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			allowedFileExtensions: ['stp'],
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.stp_file_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		// 3D STP(DETAIL)
        		var tmp = data.stp_file_detail_image_nm.split('|');
        		if (tmp[0]) {
        		    var html = '<a href="javascript:get3dRendering(\''+file_cd+'\',\''+data.designation+'\',\'D\');">미리보기</a>&nbsp;&nbsp;';
	        		$('#stp_detail_3d').html(html);
        			
        		    var html = '<a href="/ecatadmin/download.do?file_cd='+file_cd+'&file_seq='+tmp[0]+'" download="'+tmp[2]+'">다운로드</a>';
	        		$('#stp_detail_download').html(html);	        		
        		}
        		
        		$('#stp_file_detail_image').dropify($.extend({}, $.dropifyDefaults, {
        			defaultFile: tmp[0] ? tmp[1]+'/'+tmp[2] : '',
        			allowedFileExtensions: ['stp'],
        		})).on('dropify.beforeClear', function(event, element){
        			var file = $(this).val();
        			if (!file) {
        				var file_seq = data.stp_file_detail_image_nm.split('|')[0];
        				if (!fn_delete_files('frmItem', '1000', file_cd, file_seq)) {
        					return false;
        				}
        			}
        		});
        		
        		getItemPropList();
        		getSparePartsList();
        	}
        }
    });
}

/******************************************************************************
 * 아이템 속성 목록을 조회합니다.
 */
getItemPropList = function(val = '') { 
	
	var params = new Object();
	params.s_werks = $('#s_werks').val();
	params.s_matnr = $('#s_matnr').val();
	if (val) {
		params.s_val = val;
	}
	
	$('#dtPropertyList>tbody').html('');
	$.ajax({
        url: fn_get_url('/contents/item/getItemPropList.do'),
        data: JSON.stringify(params),
        success: function(result) {
        	
        	var gubun = $('#gubun').val();
        	
        	if (result.data && result.data.length > 0) {
        		var html = [];
        		$.each(result.data, function(index, prop){
        			//console.log('prop: ', prop);
        			
					html.push('<tr>');
					html.push('    <td>' + (index+1) + '</td>');
					html.push('    <td class="arLeft">' + prop.iso + '</td>');
					html.push('    <td class="arLeft">' + prop.eng + '</td>');
					if (prop.gubun == 'P') {
						html.push('    <td class="arCenter">PLM</td>');
					} else {
						html.push('    <td class="arCenter">CMS</td>');
					}
					 
					if (gubun == 'P') { // PLM 데이터는 수정하지 않음
						html.push('    <td class="arLeft">');
						html.push('        <input type="text" name="val" value="'+prop.val+'" readonly />');
						html.push('        <input type="hidden" name="iso" value="' + prop.iso + '" />');
						html.push('    </td>');
					} else {
						html.push('    <td class="arLeft">');
						if (result.plmAttrCombo.group[prop.iso]) {
							html.push('        <select name="propAttr" data-iso="'+prop.iso+'" class="w100p">');
							html.push('            <option value="">N/A</option>');
							$.each(result.plmAttrCombo.group[prop.iso], function(index, attr){
								if (prop.val == attr.code) {
									html.push('        <option value="'+attr.code+'" selected>' + attr.name+ '</option>');
								} else {
									html.push('        <option value="'+attr.code+'">' + attr.name+ '</option>');
								}
							});
							html.push('        </select>');
							html.push('        <input type="hidden" id="'+prop.iso+'_val" name="val" value="' + prop.val + '" />');
						} else {
							html.push('        <input type="text" name="val" value="' + prop.val + '" />');
						}
						html.push('        <input type="hidden" name="iso" value="' + prop.iso + '" />');
						html.push('    </td>');
					}
					
					html.push('</tr>');
				});
        		
        		$('#dtPropertyList>tbody').html(html.join(''));
        		
        		// 속성 목록 SELECT 박스 변경시
        		$('#dtPropertyList').on('change', 'select[name="propAttr"]', function(event){
        			var iso = $(this).data('iso');
        			$('#'+ iso +'_val').val($(this).val());
        		});
        	}
        }
    });
}

/******************************************************************************
 * Spare Parts 목록을 조회합니다.
 */
getSparePartsList = function() {
	
	var params = new Object();
	params.s_werks = $('#s_werks').val();
	params.s_matnr = $('#s_matnr').val();
	
	$.ajax({
        url: fn_get_url('/contents/item/getSparePartsList.do'),
        data: JSON.stringify(params),
        success: function(result) {
        	
       		var html = [];
        	if (result.data && result.data.length > 0) {
        		$.each(result.data, function(index, item){
					html.push('<tr>');
					html.push('    <td>' + (index+1) + '</td>');
					html.push('    <td>' + item.matnr + '</td>');
					html.push('    <td class="arLeft">' + item.designation + '</td>');
					html.push('</tr>');
				});
        	} else {
				html.push('<tr>');
				html.push('    <td colspan="3">조회된 내용이 없습니다.</td>');
				html.push('</tr>');
        	}
       		$('#dtSparePartsList>tbody').html(html.join(''));
        }
    });
}

/******************************************************************************
 * 아이템 정보를 수정합니다.
 */
updateItem = function(){
	
	// 필수 항목을 체크합니다.
	
	// 입력한 데이터를 수집합니다.
    var form = $('#frmItem')[0];
    var params = new FormData(form);
    
	// 아이템 정보를 업데이트 합니다.
    $.ajax({
    	url: fn_get_url('contents/item/updateItem.do'),
    	data: params,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        timeout: 300000, // 5분
        success: function(result){
        	location.reload();
        	alert(result.message);
        },
    });
}

/******************************************************************************
 * GTC Hierarchy 팝업
 */
getGtcHierarchy = function() {
	
	var params = {};
	var hierarchy;
	
	$.ajax({
        async : true,
		url: fn_get_url('contents/item/getGtcHierarchy.do'),       
        data: JSON.stringify(params),
        success: function(result) {
        	// GTC Hierarchy
        	$('#gtcHierarchy').treeview({
        		data: result.gtcHierarchy, 		
        		enableLinks: true,
        		selectable: false,
        		highlightSelected: false,
        		collapseIcon: 'fa fa-minus',
        		expandIcon: 'fa fa-plus',
        	});
        	
        	// 현재 선택된 vendor_id 셋팅
        	var vendor_id = $('#vendor_id').val();
        	if (vendor_id) {
        		$('#gtcHierarchy').treeview('val', vendor_id);
        	}
        	
        	// 화면을 팝업한다.
        	$('.mask').show();
    		$('.popWrap').hide();
    		$('.popWrap.popGtcHierarchy').show();
        }
   	});
	
	return hierarchy;
}

/******************************************************************************
 * 3D Rendering 팝업 (KHW)
 */
get3dRendering = function (viewer, title, gubun) {	
	var url = "";
	if(gubun == "D") {
		url = "/3d_viewer/Index.html?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_detail.vizw";
	} else if(gubun == "B") {
		url = "/3d_viewer/Index.html?ran="+Math.random()+"&path=/data/3d_viewer/"+viewer+"_basic.vizw";
	}
	
	if(url != "") {
	   	// 화면을 팝업한다.
	   	$('.mask').show();
		$('.popWrap').hide();
		$('.popWrap.pop3dRendering').show();	
		$('#3d_title').text("3D Rendering ("+title+")");
		$('#3d_rendering').attr('src', url);
	}
}

/******************************************************************************
 * GTC Hierarchy 팝업에서 선택된 노드 셋팅
 */ 
function setGtcHierarchy(vendorId, itemType) {
	$("#vendor_id").val(vendorId);
	// Item Type 설정
	if (itemType == 'CC0049') {
		$("#tips_cd").val(itemType);
		$("#tips_cd_nm").val('Tool Indexable');
	} else if (itemType == 'CC0050') {
		$("#tips_cd").val(itemType);
		$("#tips_cd_nm").val('Tool Solid');
	} else {
		$("#tips_cd").val('');
		$("#tips_cd_nm").val(itemType);
	}
	$('.mask').hide();
	$('.popWrap').hide();
}

/******************************************************************************
 * 화면의 구성을 초기화하고, 데이터를 조회한다.
 */ 
function formInit() {
	
	// 달력 컴포넌트 초기화
	$(".calendar").datepicker();
	
	// 아이템 정보를 조회한다.
	getItem();
}

//=============================================================================
// 화면 로딩 후 초기 호출 및 이벤트 바인딩을 처리합니다.
//=============================================================================
$(function() {
	
	// 화면을 초기화한다.
	formInit()
	
	// 목록 버튼 클릭시 
	// 아이템 목록 화면으로 이동한다.
	$('#btnList').on('click', function(event){
		//history.back();
		fn_page_move('contents/item/itemList.do');
	});
	
	// 저장 버튼 클릭시 
	// 아이템 정보를 저장한다.
	$('#btnSave').on('click', function(event){
		updateItem();
	});
	
	// 프로퍼티 조회 조건 선택시
	// 선택된 조건으로 프로퍼티 목록을 조회한다.
	$('#selProperty').on('change', function(event){
		var val = $(this).val();
		getItemPropList(val);
	});
	
	// Vendor 돋보기 아이콘 클릭시
	// GTC계층구조 화면을 팝업한다.
	$('#btnGtcSearch').on('click', function(event){
		getGtcHierarchy();
	});
});
</script>
