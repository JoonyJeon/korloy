function getJqgridSelectData(obj){
	var selectData = "";
	for(i=0;i<obj.length;i++){
		selectData += obj.eq(i).val() + ":" + obj.eq(i).text();
		
		if(i<(obj.length - 1))
			selectData += ";";
	}
	return selectData;
}

/**
 * 화면이동을 위해 서브밋으로 화면을 이동시킨다.
 * 폼객체,actionID를 모두 기본값이외의 값으로 새로 설정하는경우
 * @param moveForm submit으로 이동할 폼객체
 * @param actionId 작업구분(폼객체의actionID값)
 */
function goPageTwo (moveForm, actionId){
    moveForm.actionID.value = actionId;
    moveForm.submit();
}

/**
 * 화면이동을 위해 서브밋으로 화면을 이동시킨다.
 * 이동URL,이동할프레임,actionID를 모두 기본값이외의 값으로 새로 설정하는경우
 * @param action 이동할 대상 URL
 * @param target 이동할 대상 프레임
 * @param actionId 작업구분(폼객체의actionID값)
 */
function goPageThree (action, target, actionId){
    document.forms[0].action = action;
    document.forms[0].target = target;
    document.forms[0].actionID.value = actionId;
    document.forms[0].submit();    
}
function goPageFour (moveForm, action, target, actionId){
    moveForm.action = action;
    moveForm.target = target;
    moveForm.actionID.value = actionId;
    moveForm.submit();
}


/**
 * popup 화면을 실행한다.
 * @param url 이동할 대상 URL
 */
function goPagePop(menuUrl, width, height){
	var target = menuUrl.substring(menuUrl.lastIndexOf("/")+1, menuUrl.indexOf("."));
	openWindow(menuUrl, target, width, height) ;
}

/**
 * 윈도우를 팝업시킨다
 * @param url 화면 URL
 * @param target 오픈할폼이름
 * @param width 오픈윈도우가로길이
 * @param height 오픈윈도우세로길이
 */
function openWindow(url, target, width, height) {
		var win;

		var iLeft = (window.screen.width / 2) - (Number(width) / 2);
        var iTop = (window.screen.height / 2) - (Number(height) / 2);
		var features = "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes";
		features += ",left=";
	    features += iLeft;
	    features += ",top=";
	    features += iTop;
	    features += ",width=";
	    features += width;
	    features += ",height=";
	    features += height;
		win = window.open(url, target, features);
		win.focus();
}

/**
 * JQGrid 원래 포맷을 클리어 하고 새로운 포맷디자인을 적용한다
 */
function resetCustomJQgrid() {
	// remove classes
	$(".ui-jqgrid").removeClass("ui-widget ui-widget-content");
	$(".ui-jqgrid-view").children().removeClass("ui-widget-header ui-state-default");
	$(".ui-jqgrid-labels, .ui-search-toolbar").children().removeClass("ui-state-default ui-th-column ui-th-ltr");
	$(".ui-jqgrid-pager").removeClass("ui-state-default");
	$(".ui-jqgrid").removeClass("ui-widget-content");

	// add classes
	$(".ui-jqgrid-htable").addClass("table table-bordered table-hover");
	$(".ui-jqgrid-btable").addClass("table table-bordered table-striped");

	$(".ui-pg-div").removeClass().addClass("btn btn-sm btn-primary");
	$(".ui-icon.ui-icon-plus").removeClass().addClass("fa fa-plus");
	$(".ui-icon.ui-icon-pencil").removeClass().addClass("fa fa-pencil");
	$(".ui-icon.ui-icon-trash").removeClass().addClass("fa fa-trash-o");
	$(".ui-icon.ui-icon-search").removeClass().addClass("fa fa-search");
	$(".ui-icon.ui-icon-refresh").removeClass().addClass("fa fa-refresh");
	$(".ui-icon.ui-icon-disk").removeClass().addClass("fa fa-save").parent(".btn-primary").removeClass("btn-primary").addClass("btn-success");
	$(".ui-icon.ui-icon-cancel").removeClass().addClass("fa fa-times").parent(".btn-primary").removeClass("btn-primary").addClass("btn-danger");

	$(".ui-icon.ui-icon-seek-prev").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-prev").removeClass().addClass("fa fa-backward");

	$(".ui-icon.ui-icon-seek-first").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-first").removeClass().addClass("fa fa-fast-backward");

	$(".ui-icon.ui-icon-seek-next").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-next").removeClass().addClass("fa fa-forward");

	$(".ui-icon.ui-icon-seek-end").wrap("<div class='btn btn-sm btn-default'></div>");
	$(".ui-icon.ui-icon-seek-end").removeClass().addClass("fa fa-fast-forward");	
}

/*
 * TextArea등의 값을 HTML형식으로 바꾼다
 * originId : 변경대상 객체의 ID (display:none등의 스타일로 안보이게 표시함)
 * targetId : 변경적용 객체의 ID 
 */
function convertToHtml(originId, targetId) {
	document.getElementById(targetId).innerHTML = jQuery.trim(document.getElementById(originId).value).replace(/\n\r?/g,"<br/>");
}

/**
 * 날짜 포맷을 사용자 환경에 따라 취득하는 함수
 * lang : 사용자의 환경 언어 코드
 */
function getDateFormat(lang) {
	
	var dateFormat = "dd.mm.yy";
	if ("KOR" == lang) { 
		dateFormat = "yy-mm-dd";
	} else if ("JPN" == lang) { 
		dateFormat = "yy.mm.dd";
	}
	return dateFormat;
}

/**
 * 정해진 날짜에서 년,월,일,날,시간만큼이동한다
 * @param time 계산할 날짜(YYYYMMDD)
 * @param addYear 더할년도
 * @param addMonth 더할월
 * @param addDay더할 날짜
 */
function com_MoveDate(time, addYear, addMonth, addDay) {

   var year  = time.substr(0,4);
   var month = time.substr(4,2) - 1; // 1월=0,12월=11
   var day   = time.substr(6,2);
   var date = new Date(year,month,day);

   date.setFullYear(date.getFullYear() + addYear); //y년을 더함
   date.setMonth(date.getMonth() + addMonth);       //m월을 더함
   date.setDate(date.getDate() + addDay);         //d일을 더함

   // 월단위 이동시 날짜 계산
   if(addYear=='0' && (addMonth=='1' || addMonth=='-1') && addDay=='0') {
       var cYear = date.getFullYear();
       var cMonth = date.getMonth();
       var cDay = date.getDate(); // 계산된 일자 할당
       date = (day == cDay) ? date : new Date(cYear, cMonth, 0); // 넘어간 월의 첫쨋날 에서 하루를 뺀 날짜 객체를 생성한다.
   }
   return com_ToDateString(date);

}

/**
 * 날짜를 데이터형으로 취득
 * @param date 날짜형데이터
 * @return YYMMDD 날짜데이터
 */
function com_ToDateStringShort(date) { //formatTime(date)

   var year  = date.getFullYear();
   var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
   var day   = date.getDate();

   if (("" + month).length == 1) {
       month = "0" + month;
   }
   if (("" + day).length   == 1) {
       day   = "0" + day;
   }

   return ("" + year + "" + month + ""+ day)
}

/**
 * 날짜를 데이터형으로 취득
 * @param date 날짜형데이터
 * @return YYMMDD 날짜데이터
 */
function com_ToDateString2(date) { //formatTime(date)

   var year  = date.getFullYear();
   var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
   var day   = date.getDate();

   if (("" + month).length == 1) {
       month = "0" + month;
   }
   if (("" + day).length   == 1) {
       day   = "0" + day;
   }

   return ("" + year + "-" + month + "-"+ day)
}

/**
 * 날짜포맷(YYYY.MM.DD(W))을 YYYYMMDD로 변경
 * @param obj 객체
 */
function toYYYYMMDD(dateObj){
    var dateStr = dateObj.value;
    dateStr = dateStr.replace(/\W|\s/g, ""); //특수문자 제거
    return dateStr;
}


/**
 * 정해진 날짜에서 년,월,일,날,시간만큼이동한다
 * @param time 계산할 날짜(YYYYMMDD)
 * @param addYear 더할년도
 * @param addMonth 더할월
 * @param addDay더할 날짜
 */
function com_MoveDateTime(time, addYear, addMonth, addDay, addHour, addMin, addSec) {

   var year  = time.substr(0,4);
   var month = time.substr(4,2) - 1; // 1월=0,12월=11
   var day   = time.substr(6,2);
   var hour   = time.substr(8,2);
   var min   = time.substr(10,2);
   var sec   = time.substr(12,2);
   var date = new Date(year,month,day,hour,min,sec);
   date.setFullYear(date.getFullYear() + addYear); //y년을 더함
   date.setMonth(date.getMonth() + addMonth);       //m월을 더함
   date.setDate(date.getDate() + addDay);         //d일을 더함
   date.setHours(date.getHours() + addHour);
   date.setMinutes(date.getMinutes() + addMin);
   date.setSeconds(date.getSeconds() + addSec);
   // 월단위 이동시 날짜 계산
   if(addYear=='0' && (addMonth=='1' || addMonth=='-1') && addDay=='0') {
       var cYear = date.getFullYear();
       var cMonth = date.getMonth();
       var cDay = date.getDate(); // 계산된 일자 할당
       date = (day == cDay) ? date : new Date(cYear, cMonth, 0); // 넘어간 월의 첫쨋날 에서 하루를 뺀 날짜 객체를 생성한다.
   }
   return com_ToDateString(date);
}


/**
 * 날짜를 데이터형으로 취득
 * @param date 날짜형데이터
 * @return YYMMDD 날짜데이터
 */
function com_ToDateString(date) { //formatTime(date)

   var year  = date.getFullYear();
   var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
   var day   = date.getDate();
   var hour   = date.getHours();
   var min   = date.getMinutes();
   var sec   = date.getSeconds();

   if (("" + month).length == 1) {
       month = "0" + month;
   }
   if (("" + day).length   == 1) {
       day   = "0" + day;
   }
   if (("" + hour).length   == 1) {
       hour   = "0" + hour;
   }
   if (("" + min).length   == 1) {
       min   = "0" + min;
   }
   if (("" + sec).length   == 1) {
       sec   = "0" + sec;
   }
   return ("" + year + "" + month + ""+ day + ""+ hour + ""+ min + ""+ sec)
}

/**
 * 입력년,월,일을 일정형식으로 형식으로 반환
 * @param selYear 선택년도
 * @param selMonth 선택월
 * @param selWeek 선택일
 * @param format 포맷(디폴트:"-")
 * @return rtnDate(YYYY-MM-DD)
 */
 function getFormatDate(date, format)
 {
 	var rtnDate = "";
 	if(format == null || format=="") {
 		format = "-";
 	}
 	rtnDate = date.substring(0, 4)+format+date.substring(4, 6)+format+date.substring(6, 8);

 	return rtnDate;
 }


/**
 * 두 날짜를 비교한다.
 * @param Eobj 종료일
 * @param Sobj 시작일
 * @return true/false
 */
function compareDate(Sobj, Eobj){
	
	var startDate = Sobj.replace(/-/g, "");
	var endDate = Eobj.replace(/-/g, "");

	var start_yyyy = startDate.substring(0,4);
	var start_mm = startDate.substring(4,6);
	var start_dd = startDate.substring(6,startDate.length);
	var sDate = new Date(start_yyyy, start_mm-1, start_dd);

	var end_yyyy = endDate.substring(0,4);
	var end_mm = endDate.substring(4,6);
	var end_dd = endDate.substring(6,endDate.length);
	var eDate = new Date(end_yyyy, end_mm-1, end_dd);

	var diff = Math.ceil((eDate.getTime() - sDate.getTime())/(1000*60*60*24));
	return diff;

}

//왼쪽에서부터 채운다는 의미
function comm_lpad(s, c, n) {   
    if (! s || ! c || s.length >= n) {
        return s;
    }
 
    var max = (n - s.length)/c.length;
    for (var i = 0; i < max; i++) {
        s = c + s;
    }
 
    return s;
}

function smartAlert(title, msg){
	$.smallBox({
		title : title,
		content : "<i class='fa fa-clock-o'></i> <i>" + msg +"</i>",
		color : "#296191",
		iconSmall : "fa fa-warning bounce animated",
		timeout : 2000
	});						
}

//Time Colon format으로 입력
function inputTimeColon(time) {
	// 숫자만 입력 가능
	$(time).val($(time).val().replace(/[^0-9]/g,""));

	// 먼저 기존에 들어가 있을 수 있는 콜론(:)기호를 제거한다.
    var replaceTime = time.value.replace(/\:/g, "");

    // 글자수가 4 ~ 5개 사이일때만 동작하게 고정한다.
    if(replaceTime.length >= 4 && replaceTime.length < 5) {
        // 시간을 추출
        var hours = replaceTime.substring(0, 2);
        // 분을 추출
        var minute = replaceTime.substring(2, 4);

        // 시간은 24:00를 넘길 수 없게 세팅
        if(hours + minute > 2400) {
            alert("시간은 24시를 넘길 수 없습니다.");
            time.value = "24:00";
            return false;
        }

        // 분은 60분을 넘길 수 없게 세팅
        if(minute > 60) {
            alert("분은 60분을 넘길 수 없습니다.");
            time.value = hours + ":00";
            return false;
        }
        // 콜론을 넣어 시간을 완성하고 반환한다.
        time.value = hours + ":" + minute;
    }
}

//Time Colon format으로 입력
function inputTimeColonBlur(time) {
	
	// 먼저 기존에 들어가 있을 수 있는 콜론(:)기호를 제거한다.
    var replaceTime = time.value.replace(/\:/g, "");

    // 글자수가 4 ~ 5개 사이일때만 동작하게 고정한다.
    if(replaceTime.length >= 4 && replaceTime.length < 5) {
        // 시간을 추출
        var hours = replaceTime.substring(0, 2);
        // 분을 추출
        var minute = replaceTime.substring(2, 4);

        // 시간은 24:00를 넘길 수 없게 세팅
        if(hours + minute > 2400) {
            alert("시간은 24시를 넘길 수 없습니다.");
            time.value = "24:00";
            return false;
        }

        // 분은 60분을 넘길 수 없게 세팅
        if(minute > 60) {
            alert("분은 60분을 넘길 수 없습니다.");
            time.value = hours + ":00";
            return false;
        }
        // 콜론을 넣어 시간을 완성하고 반환한다.
        time.value = hours + ":" + minute;
    } else {
    	$(time).val("");
    }
}

// 시작/종료 날짜 설정
function setStartEndDate(dt) {
	var date = new Date();
	var today = getDateStr(date);
	
	if(dt == "today"){
		$("#search_start_dt").val(today);
		$("#search_end_dt").val(today);
		
	} else if(dt == "week"){
		var dayOfMonth = date.getDate()
		date.setDate(dayOfMonth + 7)
		$("#search_start_dt").val(today);
		$("#search_end_dt").val(getDateStr(date));
		
	} else if(dt == "2weeks"){
		var dayOfMonth = date.getDate()
		date.setDate(dayOfMonth + 14)
		$("#search_start_dt").val(today);
		$("#search_end_dt").val(getDateStr(date));
		
	} else if(dt == "month"){
		var monthOfYear = date.getMonth()
		date.setMonth(monthOfYear + 1)
		$("#search_start_dt").val(today);
		$("#search_end_dt").val(getDateStr(date));
		
	} else if(dt == "3months"){
		var monthOfYear = date.getMonth()
		date.setMonth(monthOfYear + 3)
		$("#search_start_dt").val(today);
		$("#search_end_dt").val(getDateStr(date));

		
	} else if(dt == "preWeek"){
		var dayOfMonth = date.getDate()
		date.setDate(dayOfMonth - 7)
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
		
	} else if(dt == "preTwoWeeks"){
		var dayOfMonth = date.getDate()
		date.setDate(dayOfMonth - 14)
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
		
	} else if(dt == "preMonth"){
		var monthOfYear = date.getMonth()
		date.setMonth(monthOfYear - 1)
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "preThreeMonths"){
		var monthOfYear = date.getMonth()
		date.setMonth(monthOfYear - 3)
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "past30"){
		date.setDate(date.getDate() - 30);
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "past90"){
		date.setDate(date.getDate() - 90);
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "past150"){
		date.setDate(date.getDate() - 150);
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "past180"){
		date.setDate(date.getDate() - 180);
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	} else if(dt == "past365"){
		date.setDate(date.getDate() - 365);
		$("#search_start_dt").val(getDateStr(date));
		$("#search_end_dt").val(today);
	}

}

function getDateStr(date){
	var year  = date.getFullYear();
	var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
	var day   = date.getDate();
	if (("" + month).length == 1) {
       month = "0" + month;
	}
	if (("" + day).length   == 1) {
       day   = "0" + day;
	}
	return year + '-' + month + '-' + day;
}

function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
    }    
}

function capaDatePicker(obj, minDate, maxDate, cdPlant, fgCapa, capaNum) {
	
	if(minDate != '' && minDate != null) {
		var y = minDate.substr(0,4);
		var m = minDate.substr(5,2) - 1;
		var d = minDate.substr(8,2);
		$('#'+obj).data("DateTimePicker").minDate(new Date(y,m,d));
	}
	
	if(maxDate != '' && maxDate != null) {
		var y = maxDate.substr(0,4);
		var m = maxDate.substr(5,2) - 1;
		var d = maxDate.substr(8,2);
		$('#'+obj).data("DateTimePicker").maxDate(new Date(y,m,d));
	}
	
	var svcUrl = '/basic/data.do?v=' + (new Date().getTime());
	var param = {
			actionID: "ACTION_BASIC_HOLIDAY_LIST",
			search_cd_plant: cdPlant,
			search_fg_capa : fgCapa,
			search_capa_num: capaNum,
			search_min_date: minDate,
			search_max_date: maxDate
	};
	$.ajaxSetup({ cache: false });
	$.ajax({
	        type: "POST",
	        url: svcUrl,
	        dataType: "json",
	        data: param,
	        async: false,
	        success: function (response) {
	        	//console.log(response)
	        	var disabled = [];
	        	$.each(response, function (key, data) {
	        		disabled.push(moment(data.holiday));
                });
	        	$('#'+obj).data("DateTimePicker").disabledDates(disabled);
	        		        	
	        },
	        error: function (response){
	        	alert("요청하신 처리가 실패했습니다.");
	        }
	});

}

//3자리 단위마다 콤마 생성
function addCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//숫자만 입력가능
function onlyNumberInput( e ){
	if(!((e.keyCode > 95 && e.keyCode < 106)
		      || (e.keyCode > 47 && e.keyCode < 58) 
		      || e.keyCode == 8)) {
		        return false;
		    }
}

//CAPA 여유분 리스트 팝업
function popCapaSpare(fg_capa) {
	var width = 820;
	var height = 810;
	openWindow("", "CAPA_POPUP", width, height);
	goPageFour(myForm, "/order/capaSparePopup.do?fg_capa="+fg_capa, "CAPA_POPUP", "");
	
}

function formatLC1 ( d , index ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">'+
	    '<tr>'+
	        '<td style="width:70px;text-align: right;padding-right: 0 !important;">'+	            	
	        	'<a href="javascript:void(0);" id="edit_LC1^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i>LC1</a>'+
	        '</td>'+
	        '<td style="width:130px;text-align:right">'+
	        	'<span style="float:left;">'+
	        		'<a href="javascript:void(0);" id="editSchedPartner'+index+'" onclick="showEditSchedPartnerModal('+ "'" + index + "'"+')" class="btn bg-color-green txt-color-white btn-xs"><i class="fa fa-edit"></i>병원</a>'+
	        	'</span>'+
	        	'병원정보 :'+
	        '</td>'+
	        '<td colspan="3">' + d.cd_partner + ' | ' + d.nm_partner + ' | 주치의 : ' + d.docr_nm +' | 병원담당자 : ' + d.nurs_nm+ 
	        	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-phone"></i>&nbsp;' + d.cust_phn_no +'&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-fax"></i>&nbsp;' + d.cust_fax_no +	            	
	        '</td>'+
	        '<td colspan="2"><i class="fa fa-user"></i>&nbsp;수금담당 :' + d.nm_emp_sale + ' / ' + '판매담당 : ' + d.refurb_sale_nm +
	        '</td>'+
		'</tr>'+
		
		'<tr>'+
			 '<td style="width:70px;float: right;padding-right: 0 !important;">'+	            	
	        	'<a href="javascript:void(0);" id="history^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-warning btn-xs">변경이력</a>'+
	        '</td>'+
            '<td style="text-align:right">주원료시험일 :</td>'+
			'<td>' + d.bld_exp_dt + '</td>'+
			'<td style="text-align:right">일정변경사유 :</td>'+
			'<td>' + d.dt_chng_resn_kor +'</td>'+
			'<td style="text-align:right">배양일수변경사유 :</td>'+
			'<td>' + d.cult_resn_kor +'&nbsp;&nbsp;' + d.cult_resn_rmk +'</td>'+
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[수거]</td>'+
            '<td style="text-align:right">배송일시/담당자 : </td>'+
			'<td style="width:250px">'+d.dttm_trans + ' / '+d.nm_emp_trans+'</td>'+
			'<td style="width:115px;text-align:right">인수일시/담당자 : </td>'+
			'<td style="width:250px">'+d.dttm_take + ' / '+d.nm_emp_take+'</td>'+
			'<td style="width:150px;text-align:right">외관상태/수거확인 : </td>'+
			'<td>'+d.nm_fg_surface+' / '+d.nm_fg_collect+'</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[접수]</td>'+
            '<td style="text-align:right">공장도착일시 :</td>'+
			'<td>'+d.dt_ar + ' ' + d.tm_ar + '</td>'+
			'<td style="text-align:right">입고번호 :</td>'+
			'<td>'+d.rcvg_no+'</td>'+
			'<td></td>'+
			'<td></td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[제조]</td>'+
            '<td style="text-align:right">제조시작/완료일 :</td>'+
			'<td>'+d.mfg_strt_dt+ ' ~ ' + d.mfg_complt_dt+ ' ('+d.cd_wcop+')'+
			'<td style="text-align:right">제조번호 :</td>'+
			'<td>'+d.mfg_no+'('+d.nm_item+')'+'</td>'+
			'<td style="text-align:right">제조통보 :</td>'+
			'<td>' + d.mfg_noti_dt + ' / ' + d.mfg_rmk + '</td>'+					
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[출하]</td>'+
            '<td style="text-align:right">출하일시/배송담당자 :</td>'+
			'<td>' + d.shmt_dt+ ' / ' + d.shmt_nm_kor +'</td>'+
			'<td style="text-align:right">병원수취일시 :</td>'+
			'<td>'+d.shmt_rect_dt +'</td>'+				
			'<td style="text-align:right">배송비고 :</td>'+
			'<td>' + d.shmt_info_rmk + '</td>'+
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[회수|반품]</td>'+
            '<td style="text-align:right">회수일/사유 :</td>'+
			'<td colspan="3">' +d.cllct_dt +' / ' + d.cllct_resn_kor +' / ' + d.cllct_rmk + '</td>'+
			'<td style="text-align:right">반품일/사유 :</td>'+
			'<td>' + d.rtn_dt + ' / ' +d.rtn_resn_kor + ' / ' +d.rtn_rmk + '</td>'+
		'</tr>'+
		
		'<tr>'+
	        '<td style="width:70px">[폐기]</td>'+
	        '<td style="text-align:right">주원료폐기요청 :</td>'+
			'<td>'+d.mm_del_dt + ' / ' +d.mm_del_resn_kor + ' / ' +d.mm_del_rmk +'</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td style="text-align:right">주원료폐기 :</td>'+
			'<td>'+d.rma_discd_dt+ ' / ' + d.rma_discd_resn + '</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px"></td>'+
			'<td style="text-align:right">제조중폐기요청 :</td>'+
			'<td>'+d.discd_req_dt + ' / ' +d.prd_discd_resn_kor + ' / ' +d.prd_discd_rmk +'</td>'+
			'<td style="text-align:right">폐기매출:</td>'+
			'<td>'+d.discd_sale_rate+"%"+' ('+d.nm_item_sale+')'+'</td>'+
			'<td style="text-align:right">작업중단/폐기일,사유:</td>'+
			'<td>' + d.job_st_dt + ' / ' + d.discd_dt + ' , ' + d.discd_resn + '</td>'+
		'</tr>'+
		
		'<tr>'+
        '<td style="width:70px">[문서]</td>'+
        '<td colspan="6">품질확인서: ' + d.qty_cfm_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;품질확인서(영) : ' + d.qty_cfme_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;Cell Data Sheet : ' + d.data_sht_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;시험성적서 : ' + d.test_rpt_yn + '</td>'+	
	    '</tr>'+
	
		'<tr>'+
            '<td style="width:70px">[비고]</td>'+
            '<td colspan="7" style="text-align:left">'+ chgRmk(d.rmk) +'</td>'+					            	
		'</tr>'+
	
    '</table>';
}

function formatLC2 ( d , index ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">'+
	    '<tr>'+
	        '<td style="width:70px;text-align: right;padding-right: 0 !important;">'+	            	
	        	'<a href="javascript:void(0);" id="edit_LC2^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-primary btn-xs" style="margin-left: 10px;"><i class="fa fa-edit"></i>LC2</a>'+
	        '</td>'+
	        '<td style="width:130px;text-align:right">'+
	        	'<span style="float:left;">'+
	        		'<a href="javascript:void(0);" id="editSchedPartner'+index+'" onclick="showEditSchedPartnerModal('+ "'" + index + "'"+')" class="btn bg-color-green txt-color-white btn-xs"><i class="fa fa-edit"></i>병원</a>'+
	        	'</span>'+
	        	'병원정보 :'+
	        '</td>'+
	        '<td colspan="3">' + d.cd_partner + ' | ' + d.nm_partner + ' | 주치의 : ' + d.docr_nm +' | 병원담당자 : ' + d.nurs_nm+ 
	        	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-phone"></i>&nbsp;' + d.cust_phn_no +'&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-fax"></i>&nbsp;' + d.cust_fax_no +	            	
	        '</td>'+
	        '<td colspan="2"><i class="fa fa-user"></i>&nbsp;수금담당 :' + d.nm_emp_sale + ' / ' + '판매담당 : ' + d.refurb_sale_nm +
	        '</td>'+
		'</tr>'+
		
		'<tr>'+
			'<td style="width:70px;float: right;padding-right: 0 !important;">'+	            	
        	'<a href="javascript:void(0);" id="history^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-warning btn-xs">변경이력</a>'+
	        '</td>'+
            '<td style="text-align:right">주원료시험일 :</td>'+
			'<td>' + d.bld_exp_dt + '</td>'+
			'<td style="text-align:right">일정변경사유 :</td>'+
			'<td colspan="3">' + d.dt_chng_resn +'</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px">[수거]</td>'+
            '<td style="text-align:right">배송일시/담당자 : </td>'+
			'<td style="width:250px;">'+d.dttm_trans + ' / '+d.nm_emp_trans+'</td>'+
			'<td style="width:110px;text-align:right">인수일시/담당자 : </td>'+
			'<td style="width:250px;">'+d.dttm_take + ' / '+d.nm_emp_take+'</td>'+
			'<td style="width:150px;text-align:right">외관상태/수거확인 : </td>'+
			'<td>'+d.nm_fg_surface+' / '+d.nm_fg_collect+'</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px">[접수]</td>'+
            '<td style="text-align:right">공장도착일시 :</td>'+
			'<td>'+d.dt_ar + ' ' + d.tm_ar + '</td>'+
			'<td style="text-align:right">입고번호 :</td>'+
			'<td>'+d.rcvg_no+'</td>'+
			'<td style="text-align:right">요청바이알수 :</td>'+
			'<td>'+d.vial_req_num+'</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px">[제조]</td>'+
            '<td style="text-align:right">제조시작/완료일 :</td>'+
			'<td>'+d.mfg_strt_dt+ ' ~ ' + d.mfg_complt_dt+ ' ('+d.cd_wcop+')' +
			'<td style="text-align:right">제조번호 :</td>'+
			'<td>'+d.mfg_no+'('+d.nm_item+')'+'</td>'+
			'<td style="text-align:right">제조통보 :</td>'+
			'<td>' + d.mfg_noti_dt + ' / ' + d.mfg_rmk + '</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px">[보관]</td>'+
            '<td style="text-align:right">보관확정일 :</td>'+
			'<td>'+d.keep_cnfrm_dt+'</td>'+
			'<td style="text-align:right">보관바이얼수 :</td>'+
			'<td>'+d.vial_keep_num + '</td>'+
			'<td style="text-align:right">사용바이얼수 :</td>'+
			'<td>'+d.vial_use_num + '</td>'+				
		'</tr>'+	
		
		'<tr>'+
	        '<td style="width:70px">[폐기]</td>'+
	        '<td style="text-align:right">바이얼폐기요청 :</td>'+
			'<td>'+d.vial_del_req + ' / ' +d.vial_del_resn_kor + ' / ' +d.vial_del_rmk +'</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td style="text-align:right">바이얼폐기 :</td>'+
			'<td>'+d.vial_desty_dt+ ' / ' + d.vial_desty_resn + '</td>'+				
		'</tr>'+
	
		'<tr>'+
	        '<td style="width:70px"></td>'+
	        '<td style="text-align:right">주원료폐기요청 :</td>'+
			'<td>'+d.mm_del_dt + ' / ' +d.mm_del_resn_kor + ' / ' +d.mm_del_rmk +'</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td style="text-align:right">주원료폐기 :</td>'+
			'<td>'+d.rma_discd_dt+ ' / ' + d.rma_discd_resn + '</td>'+				
		'</tr>'+
		
		'<tr>'+
            '<td style="width:50px"></td>'+
			'<td style="text-align:right">제조중폐기요청 :</td>'+
			'<td>'+d.discd_req_dt + ' / ' +d.prd_discd_resn_kor + ' / ' +d.prd_discd_rmk +'</td>'+
			'<td style="text-align:right">폐기매출:</td>'+
			'<td>'+d.discd_sale_rate+"%"+' ('+d.nm_item_sale+')'+'</td>'+
			'<td style="text-align:right">작업중단/폐기일,사유:</td>'+
			'<td>' + d.job_st_dt + ' / ' + d.discd_dt + ' , ' + d.discd_resn + '</td>'+
		'</tr>'+
				
		'<tr>'+
        '<td style="width:70px">[문서]</td>'+
        '<td colspan="6">품질확인서: ' + d.qty_cfm_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;품질확인서(영) : ' + d.qty_cfme_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;Cell Data Sheet : ' + d.data_sht_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;시험성적서 : ' + d.test_rpt_yn + '</td>'+	
	    '</tr>'+
	    
		'<tr>'+
            '<td style="width:50px">[비고]</td>'+
            '<td colspan="7" style="text-align:left">'+ chgRmk(d.rmk) +'</td>'+					            								
		'</tr>'+
	
    '</table>';
}

function formatTW ( d , index ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">'+
	    '<tr>'+
	        '<td style="width:70px;text-align: right;padding-right: 0 !important;">'+	            	
	        	'&nbsp;<a href="javascript:void(0);" id="edit_TW^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i>TW</a>'+
	        	'<span class="no_patient" style="display:none;">'+d.no_patient+'</span>'+
	        '</td>'+         
	        '<td style="width:130px;text-align:right">'+
	    		'<span style="float:left;">'+
	            	'&nbsp;<a href="javascript:void(0);" id="editSchedPartner'+index+'" onclick="showEditSchedPartnerModal('+ "'" + index + "'"+')" class="btn bg-color-green txt-color-white btn-xs"><i class="fa fa-edit"></i>병원</a>'+
	            '</span>'+
	            '병원정보 :'+
	        '</td>'+
	        '<td colspan="3">' + d.cd_partner + ' | ' + d.nm_partner + ' | 주치의 : ' + d.docr_nm +' | 병원담당자 : ' + d.nurs_nm+ 
	        	'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-phone"></i>&nbsp;' + d.cust_phn_no +'&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-fax"></i>&nbsp;' + d.cust_fax_no +	            	
	        '</td>'+
	        '<td colspan="2"><i class="fa fa-user"></i>&nbsp;수금담당 :' + d.nm_emp_sale + ' / ' + '판매담당 : ' + d.refurb_sale_nm +
	        '</td>'+
		'</tr>'+
		
		'<tr>'+
			 '<td style="width:70px;float: right;padding-right: 0 !important;">'+	            	
	        	'<a href="javascript:void(0);" id="history^'+d.ord_no+'^'+d.gds_tm_no+'" class="btn btn-warning btn-xs">변경이력</a>'+
	        '</td>'+
            '<td style="text-align:right">주원료시험일 :</td>'+
			'<td style="width:250px;">' + d.bld_exp_dt + '</td>'+
			'<td style="width:110px;text-align:right">일정변경사유 :</td>'+
			'<td style="width:250px;">' + d.dt_chng_resn_kor+'</td>'+
			'<td style="width:150px;text-align:right">배양일수변경사유 :</td>'+
			'<td>' + d.cult_resn_kor + '&nbsp;&nbsp;' +d.cult_resn_rmk + '</td>'+
		'</tr>'+			
		
		'<tr>'+
            '<td style="width:70px">[제조]</td>'+
            '<td style="text-align:right">제조시작/완료일 :</td>'+
			'<td>'+d.mfg_strt_dt+ ' ~ ' + d.mfg_complt_dt+ ' ('+d.cd_wcop+')' +
			'<td style="text-align:right">제조번호 :</td>'+
			'<td>'+d.mfg_no+'('+d.nm_item+')'+'</td>'+
			'<td style="text-align:right">제조통보 :</td>'+
			'<td>' + d.mfg_noti_dt + ' / ' + d.mfg_rmk + '</td>'+					
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[출하]</td>'+
            '<td style="text-align:right">출하일시/배송담당자 :</td>'+
			'<td>' + d.shmt_dt+ ' / ' + d.shmt_nm_kor +'</td>'+
			'<td style="text-align:right">병원수취일시 :</td>'+
			'<td>'+d.shmt_rect_dt +'</td>'+				
			'<td style="text-align:right">배송비고 :</td>'+
			'<td>' + d.shmt_info_rmk + '</td>'+
		'</tr>'+
		
		'<tr>'+
            '<td style="width:70px">[회수|반품]</td>'+
            '<td style="text-align:right">회수일/사유 :</td>'+
            '<td colspan="3">' +d.cllct_dt +' / ' + d.cllct_resn_kor +' / ' + d.cllct_rmk + '</td>'+
            '<td style="text-align:right">반품일/사유 :</td>'+
			'<td>' + d.rtn_dt + ' / ' +d.rtn_resn_kor + ' / ' +d.rtn_rmk + '</td>'+
		'</tr>'+			
		
		'<tr>'+
	        '<td style="width:70px">[폐기]</td>'+
	        '<td style="text-align:right">주원료폐기요청 :</td>'+
			'<td>'+d.mm_del_dt + ' / ' +d.mm_del_resn_kor + ' / ' +d.mm_del_rmk +'</td>'+
			'<td></td>'+
			'<td></td>'+
			'<td style="text-align:right">주원료폐기 :</td>'+
			'<td>'+d.rma_discd_dt+ ' / ' + d.rma_discd_resn + '</td>'+				
		'</tr>'+
		
		'<tr>'+
	        '<td style="width:50px"></td>'+
			'<td style="text-align:right">제조중폐기요청 :</td>'+
			'<td>'+d.discd_req_dt + ' / ' +d.prd_discd_resn_kor + ' / ' +d.prd_discd_rmk +'</td>'+
			'<td style="text-align:right">폐기매출:</td>'+
			'<td>'+d.discd_sale_rate+"%"+' ('+d.nm_item_sale+')'+'</td>'+
			'<td style="text-align:right">작업중단/폐기일,사유:</td>'+
			'<td>' + d.job_st_dt + ' / ' + d.discd_dt + ' , ' + d.discd_resn + '</td>'+
		'</tr>'+
				
		'<tr>'+
        '<td style="width:70px">[문서]</td>'+
        '<td colspan="6">품질확인서: ' + d.qty_cfm_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;품질확인서(영) : ' + d.qty_cfme_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;Cell Data Sheet : ' + d.data_sht_yn + ' &nbsp;&nbsp;&nbsp;&nbsp;시험성적서 : ' + d.test_rpt_yn + '</td>'+	
	    '</tr>'+
	    
		'<tr>'+
            '<td style="width:70px">[비고]</td>'+
            '<td colspan="7" style="text-align:left">'+ chgRmk(d.rmk) +'</td>'+					            								
		'</tr>'+
	
    '</table>';
}	

function getToday(){
	if (!String.prototype.padStart) {
	    String.prototype.padStart = function padStart(targetLength, padString) {
	        targetLength = targetLength >> 0; //truncate if number, or convert non-number to 0;
	        padString = String(typeof padString !== 'undefined' ? padString : ' ');
	        if (this.length >= targetLength) {
	            return String(this);
	        } else {
	            targetLength = targetLength - this.length;
	            if (targetLength > padString.length) {
	                padString += padString.repeat(targetLength / padString.length);
	            }
	            return padString.slice(0, targetLength) + String(this);
	        }
	    };
	}
	
	var today = new Date();
	
	var dd = String(today.getDate()).padStart(2, '0');	
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();
	
	return today = yyyy + '-' +  mm + '-' + dd;
}

//이름 가운데 글자만 마스킹 처리
function setMaskingforName(strName){
	console.log('1');
	// 2글자면 마지막 글자만
	if (strName.length > 2) {
		var originName = strName.split('');
		originName.forEach(function(name, i) {
			if (i === 0 || i === originName.length - 1) return;
			originName[i] = '*';
		});
		var joinName = originName.join(',');
		return joinName.toString().replace(/,/g, '');
	} else {
		var pattern = /.$/; // 정규식
		return strName.replace(pattern, '*');
	}
}

function setMaskingforEmail(strMail){
	let originStr = strMail;
	let emailStr = originStr.match(/([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\.[a-zA-Z0-9._-]+)/gi);
	let strLength;
	if(checkNull(originStr) == true || checkNull(emailStr) == true){ 
	    return originStr;
	}else{ 
	    strLength = emailStr.toString().split('@')[0].length - 3;
	    return originStr.toString().replace(new RegExp('.(?=.{0,' + strLength + '}@)', 'g'), '*').replace(/.{6}$/, "******");
	}
}

function setMaskingforPhoneNumber(strPhoneNumber){
	let originStr = strPhoneNumber;
	let phoneStr;
	let maskingStr;
	if(this.checkNull(originStr) == true){ 
	    return originStr;
	} 
	if (originStr.toString().split('-').length != 3) { 
	    // 1) -가 없는 경우 
	    phoneStr = originStr.length < 11 ? originStr.match(/\d{10}/gi) : originStr.match(/\d{11}/gi);
	    if(this.checkNull(phoneStr) == true){ 
	        return originStr;
	    } 
	    if(originStr.length < 11) { 
	        // 1.1) 0110000000 
	        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/(\d{3})(\d{3})(\d{4})/gi,'$1***$3'));
	    } else { 
	        // 1.2) 01000000000 
	        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/(\d{3})(\d{4})(\d{4})/gi,'$1****$3'));
	    } 
	}else { 
	    // 2) -가 있는 경우 
	    phoneStr = originStr.match(/\d{2,3}-\d{3,4}-\d{4}/gi);
	    if(this.checkNull(phoneStr) == true){ 
	        return originStr;
	    } 
	    if(/-[0-9]{3}-/.test(phoneStr)) { 
	        // 2.1) 00-000-0000 
	        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/-[0-9]{3}-/g, "-***-"));
	    } else if(/-[0-9]{4}-/.test(phoneStr)) { 
	        // 2.2) 00-0000-0000 
	        maskingStr = originStr.toString().replace(phoneStr, phoneStr.toString().replace(/-[0-9]{4}-/g, "-****-"));
	    } 
	} 
	return maskingStr;
}
function checkNull(str){
	if(typeof str == "undefined" || str == null || str == ""){ return true; } else{ return false; }
}

function onErrorImage(_img) {
    $(_img).attr('src', "/img/korloy_blanklogo.svg");
}

// 문자열에서 원하는 길이만큼 자르고 나머지는 end로 채움
function toTruncate(str, size, end){
	var result = "";
	if(str.length > size){
	   result = str.substring(0, size) + end;
	}else{
	   result = str;
	}
	return result;
}