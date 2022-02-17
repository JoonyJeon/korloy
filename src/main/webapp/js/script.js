var isMobile = false;
var appSwiper;
var mainSwiper;
$(document).ready(function() {
    isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
    $(".select_area select").niceSelect();
    
    //open_login
    $(".open_login").click(function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .pop_cont > div").hide();
        $(".login_wrap .in").addClass("active");
        $("#login").show();
        /* YJI (210825)*/
    	// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백
        var userInputId = getCookie("userInputId");
        $("input[name='login_user_id']").val(userInputId); 
        
        // 로그인 페이지 로딩시 입력칸에 ID가 있을경우, remember 체크상태 유지
        if($("input[name='login_user_id']").val() != ""){ 
            $("#remember").attr("checked", true); 
        }
        
        $("#remember").change(function(){
            if($("#remember").is(":checked")){ // ID 저장하기 체크시
                var userInputId = $("#login_user_id").val();
                setCookie("userInputId", userInputId, 365); // 365일 동안 쿠키 보관
            }else{
                deleteCookie("userInputId"); // ID 저장하기 체크 해제 시, 쿠키삭제
            }
        });
        
        $("#login_user_id").change('change',function(){ // ID 입력 칸에 ID를 입력할 때,
            if($("#remember").is(":checked")){ // ID 저장하기를 체크한 상태시
                var userInputId = $("#login_user_id").val();
                setCookie("userInputId", userInputId, 365); // 365일 동안 쿠키 보관
            }
        });
        
//        $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
        /* YJI (210825) -------------------*/        
    });
    
    $(".open_register").click(function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .pop_cont > div").hide();
        /* 1105 회원가입 누락 처리*/
        $(".login_wrap .in").addClass("active");
        $("#register").show();
    });
    
    $(".open_password").click(function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .pop_cont > div").hide();
        $("#password").show();
    });
    
    $(".open_mail").click(function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .pop_cont > div").hide();
        $("#confirm").show();
    });

    /* YJI (210825) 사용하지 않음*/        
//    $(".open_join").click(function(){
//        $(".login_wrap").fadeIn();
//        $(".login_wrap .pop_cont > div").hide();
//        $("#join").show();
//    });
    /* YJI (210825) -------------------*/   
    
    $(".open_user").click(function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .in").addClass("active");
        if(isMobile){
            $(".login_wrap").css('position', '');
        }
        $(".login_wrap .pop_cont > div").hide();
        $("#user").show();
    });
    
    $(".open_withdrawal").click(function(){
        $(".login_wrap").fadeIn();
        if(isMobile){
            $(".login_wrap").css('position', '');
        }
        $(".login_wrap .pop_cont > div").hide();
        $("#withdrawal").show();
    });
    
    /* YJI (210929)
     * right_wrap.jsp 에서 처리 
     * */ 
//    $(".open_vip").click(function(){
//        $(".login_wrap").fadeIn();
//        if(isMobile){
//            $(".login_wrap").css('position', '');
//        }
//        $(".login_wrap .pop_cont > div").hide();
//        $("#vip").show();
//    });

// 화면 이동이라서 주석처리 2021.08.25 SJY
//    //quickcart
//    $(".open_quickcart").click(function(){
//        $(".login_wrap").fadeIn();
//        $(".login_wrap .pop_cont > div").hide();
//        $("#quickcart").show();
//    });
    
    //quickcart
//    $(".open_quickcart").click(function(){
//        $(".login_wrap").fadeIn();
//        $(".login_wrap .pop_cont > div").hide();
//        $("#quickcart").show();
//    });
    
    
    $(".pop_close").click(function(){
        $(".login_wrap").fadeOut();
        $(".login_wrap .in").removeClass("active");
        $(".login_wrap").css('position', 'fixed');
        $(".fullmenu_list .in .btn_close").click();
    });

    /* YJI (210912)
     * 회원가입 팝업오픈 비활성화 : 팝업 안에 내용 없고, 클릭하여 checkbox 체크처리가 필요하여 비활성화하였음 
     * */       
    // privacy
//    $(".open_privacy").click(function(){
//        $(".open_pop").fadeIn("fast");
//        $(".open_pop .in > div").hide();
//        $(".pop_info").fadeIn();
//        return false;
//    });
    
    $(".close_privacy").click(function(){
    	$(".open_pop").fadeOut();
    });
    /* YJI (210912) -------------------*/
    
	//mainapp_list
    mainSwiper = new Swiper('.mainapp_list .swiper-container', {
        direction: 'horizontal',
        spaceBetween : 0,
        slidesPerView: 7,
        slidesPerGroup: 1,
        navigation: {
            prevEl: '.mainapp_list .btn_prev',
            nextEl: '.mainapp_list .btn_next',
        },
        breakpoints: { 
            // 화면의 넓이가 320px 이상일 때
            320: { 
                slidesPerView: 2,
                spaceBetween: 0 
            },
            // 화면의 넓이가 461px 이상일 때
            462: { 
                slidesPerView: 3,
                spaceBetween: 0 
            },
            // 화면의 넓이가 741px 이상일 때
            762: { 
                slidesPerView: 7,
                spaceBetween: 0 
            },
            // 화면의 넓이가 962px 이상일 때
            962: { 
                slidesPerView: 7,
                spaceBetween: 0 
            } 
        }
    });
    
	//sub_applist
	appSwiper = new Swiper('.sub_applist .swiper-container', {
        direction: 'horizontal',
        spaceBetween : 0,
        slidesPerView : 'auto',
        slidesPerGroup: 1,
        navigation: {
            prevEl: '.sub_applist .btn_prev',
            nextEl: '.sub_applist .btn_next',
        },
        breakpoints: { 
            // 화면의 넓이가 320px 이상일 때
            320: { 
                slidesPerView: 3,
                spaceBetween: 0 
            },
            // 화면의 넓이가 461px 이상일 때
            462: { 
                slidesPerView: 3,
                spaceBetween: 0 
            },
            // 화면의 넓이가 741px 이상일 때
            762: { 
                slidesPerView: 6,
                spaceBetween: 0 
            },
            // 화면의 넓이가 962px 이상일 때
            962: { 
                slidesPerView: 10,
                spaceBetween: 0 
            } 
        }
    });
    
    //
    $(".btn_big").click(function(){
        $(".item_wrap .cont_wrap").hide();
		$(".item_wrap .cont_wrap.big").show();
		return false;
	});
    $(".btn_small").click(function(){
        $(".item_wrap .cont_wrap").hide();
		$(".item_wrap .cont_wrap.small").show();
		return false;
	});
    $(".btn_text").click(function(){
        $(".item_wrap .cont_wrap").hide();
		$(".item_wrap .cont_wrap.text").show();
		return false;
	});
    
    //listview .view_top tab
    $(".search_wrap .view_top #view01").click(function(){
        $(".search_wrap .view_cont").hide();
		$(".search_wrap .view_cont.view01").show();
		return false;
	});
    $(".search_wrap .view_top #view02").click(function(){
        $(".search_wrap .view_cont").hide();
		$(".search_wrap .view_cont.view02").show();
		return false;
	});
    $(".search_wrap .view_top #view03").click(function(){
        $(".search_wrap .view_cont").hide();
		$(".search_wrap .view_cont.view03").show();
		return false;
	});
    
    $(".listview .view_top .btn_area ul li a").click(function(){
        if( $(this).hasClass("on" )){
            $(this).removeClass("on");
        }else{
            $(".listview .view_top .btn_area ul li a").removeClass("on");
            $(this).addClass("on");
        }
        return false;
    });
    
    //cart
    $(".open_cart").click(function(){
        if( $(this).hasClass("on" )){
            $(this).removeClass("on");
            $(this).parent().prev(".cart_detail").slideUp();
            $(this).text("Open");
        }else{
            $(this).addClass("on");
            $(this).parent().prev(".cart_detail").slideDown();
            $(this).text("Close");
        }
        return false;
    });
    
    //Item Group List filter
    $(".open_filter").click(function(){
        if( $(this).hasClass("active") ){
            $(this).removeClass("active");
            $(this).children('img').attr("src","/img/ico_filter.png");
            $(".filter").slideUp();
        }else{
            $(this).addClass("active");
            $(this).children('img').attr("src","/img/ico_onfilter.png");
            $(".filter").slideDown();
            $(".open_filter").css("background", "#fff");
        }
        makeFilterParam();
        return false;
    });
    
    //btn_cart
    $(".btn_cart").click(function(){
        $(".open_cart_pop.cart_pop01").fadeIn();
        return false;
    });
    $(".open_save").click(function(){
        //$(".open_cart_pop.cart_pop01").fadeOut();
        //$(".open_cart_pop.cart_pop02").fadeIn();
        return false;
    });
    $(".close_pop").click(function(){
        $(".open_cart_pop").fadeOut();
        return false;
    });
    
    //메가메뉴
    $(".open_mega").click(function(){
        if( $(this).hasClass("on" )){
            $(this).removeClass("on");
            $(".mega_wrap").slideUp();
            $(".open_bg").fadeOut();
        }else{
            $(this).addClass("on");
            $(".mega_wrap").slideDown();
            $(".open_bg").fadeIn();
        }
        return false;
    });
    $(".mega_wrap .mega_cont .item:first-child").show();
    $(".mega_wrap .mega_menu ul li a").click(function(){
		var megaIndex = $(this).parent().index();
		$(".mega_wrap .mega_menu ul li").removeClass("on");
		$(this).parent().addClass("on");
		$(".mega_cont .item").hide();
		$(".mega_cont .item").eq(megaIndex).show();
        
		return false;
	});
    
    //레이어 확대 팝업
    $(".expand").click(function(){
        var srcImg = $(this).children('img').attr("src");
        //console.log(srcImg);
        $(".open_pop").fadeIn("fast");
        $(".open_pop .in > div").hide();
        $(".open_pop .pop_cont").show();
        $(".open_pop .in .pop_cont .pop_img img").attr("src",srcImg);   
        //console.log(srcImg);
        return false;
    });
    $(".close_pop").click(function(){
        $(".open_pop").fadeOut("fast");
        return false;
    });

	//grade 팝업
    $(".viewGrade").click(function(){
        $(".open_grade_pop").fadeIn();
        return false;
    });
    $(".close_pop").click(function(){
        $(".open_grade_pop").fadeOut();
        return false;
    });

	//Assembly 팝업
    $(".popInsert").click(function(){
        $(".open_assem_pop").fadeIn();
        return false;
    });
    $(".close_pop").click(function(){
        $(".open_assem_pop").fadeOut();
        return false;
    });
    
    //box_cont box_tab
    $(".box .box_cont .box_tab ul li a").click(function(){
        var index = $(this).parent().index();
        $(".box .box_cont .box_tab ul li a").removeClass("on");
        $(this).addClass("on");
        $(this).parent().parent().parent().next(".listTable").hide();
        $(this).parent().parent().parent().next(".listTable").eq(index).show();
        return false;
    });
    
    //box_top box_tab
    $(".box .box_top .box_tab ul li a").click(function(){
        var index = $(this).parent().index();
        $(".box .box_top .box_tab ul li a").removeClass("on");
        $(this).addClass("on");
        $(this).parent().parent().parent().parent().next(".box_cont").children('.img').hide();
        $(this).parent().parent().parent().parent().next(".box_cont").children('.img').eq(index).show();
        return false;
    });
    
    //picture
    $(".picture_wrap .picture .small a").click(function(){
        var bigImg = $(this).find("img").attr("src");
        $(".picture_wrap .picture .big a img").attr("src",bigImg);
    
        return false;
    });
    
    //박스내리기
    $(".btn_down").click(function(){
        $(this).parent().next(".box_cont").slideToggle();
        return false;
    });
    
    //range slider
    $( "#slider-range-min" ).slider({
      range: "min",
      value: 1,
      min: 1,
      max: 700
    });
    
    $( "#slider-range-min02" ).slider({
      range: "min",
      value: 1,
      min: 1,
      max: 700
    });
    
    //양쪽range slider
    $( "#slider-range" ).slider({
      range: true,
      min: 1,
      max: 1000,
      values: [ 300, 500 ],
      slide: function( event, ui ) {
        $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
      }
    });
    $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
      " - $" + $( "#slider-range" ).slider( "values", 1 ) );
    
    //Parametric Search
    var handle = $( "#custom-handle" );
    $( "#slider01" ).slider({
        range: "min",
        value: 34,
        min: 0,
        max: 100,
        create: function() {
            handle.text( $( this ).slider( "value" ) );
        },
        slide: function( event, ui ) {
            handle.text( ui.value );
        }
    });
    
    var handle2 = $( "#custom-handle2" );
    $( "#slider02" ).slider({
        range: "min",
        value: 34,
        min: 0,
        max: 100,
        create: function() {
            handle2.text( $( this ).slider( "value" ) );
        },
        slide: function( event, ui ) {
            handle2.text( ui.value );
        }
    });
    
    var handle3 = $( "#custom-handle3" );
    $( "#slider03" ).slider({
        range: "min",
        value: 34,
        min: 0,
        max: 100,
        create: function() {
            handle3.text( $( this ).slider( "value" ) );
        },
        slide: function( event, ui ) {
            handle3.text( ui.value );
        }
    });
    
    //list 형식
    $(".search_wrap .txt_list").click(function(){
        $(".search_list").css("display","none");
        $(".search_list02").css("display","block");
        return false;
    });
    $(".search_wrap .thumbnail_list").click(function(){
        $(".search_list").css("display","block");
        $(".search_list02").css("display","none");
        return false;
    });
    
    //search_tab click
    $(".search_tab ul li a").click(function(){
        var searchIndex = $(this).parent().index();
        $(".search_tab ul li a").removeClass("on");
        $(this).addClass("on");
        return false;
    });
    
    //open_parametric
    $(".open_parametric").click(function(){
        $(".parametric_wrap").slideDown();
        $("#contents .pop_bg").fadeIn("fast");
        return false;
    });
    
    //open_categories
    $(".open_categories").click(function(){
        $(this).parent().parent().siblings(".categories_wrap").slideDown();
        $("#contents .pop_bg").fadeIn("fast");
        return false;
    });
    
    //search_wrap .btn_close
    $(".search_wrap .btn_close a").click(function(){
        $("#contents .pop_bg").fadeOut("fast");
        $(this).parent().parent().slideUp();
        
        return false;
    });
    
    //Search
    $(".btn_advanced a").click(function(){
        $(this).addClass("on");
        $(this).parent().siblings(".detail_wrap").slideDown();
        $("#contents .pop_bg").fadeIn("fast");
        return false;
    });
    
    //Parametric Search
    $(".search_wrap .btn_search").click(function(){
        $(".search_category").slideToggle("fast");
    });
    
    //.search_wrap .img_view
    $(".search_wrap .open_view").click(function(){
        var viewImg = $(this).children('.img_view').children("img").attr("src");
        //console.log(viewImg);
        $(".view_pop").fadeOut();
        $(this).children(".view_pop").fadeIn();
        $(this).children(".view_pop").children("img").attr("src",viewImg);
        
        return false;
    });

    /* 모바일아이템그룹 테이블 더보기 */
    $(".currentItem").click(function(){
        $(".mobileDetailItem").hide();
        $(this).next(".mobileDetailItem").slideToggle();
        return false;
    });
    
    //assembly tab
    $(".assembly .box .box_cont ul li:first-child").show();
    $(".assembly .box .box_tab ul li a").click(function(){
        var assemblyIndex = $(this).parent().index();
        if( $(this).hasClass("on") ){
            $(this).removeClass("on");
            $(".box_cont ul li").hide();
        }else{
            $(".assembly .box .box_tab ul li a").removeClass("on");
            $(".box_cont ul li").hide();
            $(this).addClass("on");
            $(".box_cont ul").children().eq(assemblyIndex).show();
        }
        return false;
    });
    
	$('input, textarea').placeholder();
    
    
	
	//모든 datepicker에 대한 공통 옵션 설정
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd' //Input Display Format 변경
		,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
		,changeYear: true //콤보박스에서 년 선택 가능
		,changeMonth: true //콤보박스에서 월 선택 가능                
		,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
//			,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
		,buttonImageOnly: false //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
//			,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
		,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
		,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
		,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
		,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
		,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                    
	});

	//input을 datepicker로 선언
	$("#datepicker").datepicker();                    
	$("#datepicker2").datepicker();
	
	//From의 초기값을 오늘 날짜로 설정
	$('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
	//To의 초기값을 내일로 설정
	$('#datepicker2').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
    
	
	//업로드 컴포넌트
	$('.dropify').dropify();
	var drEvent = $('#input-file-events').dropify();
	drEvent.on('dropify.beforeClear', function(event, element){
		return confirm("Do you really want to delete \"" + element.file.name + "\" ?");
	});
	drEvent.on('dropify.afterClear', function(event, element){
		alert('File deleted');
	});
	drEvent.on('dropify.errors', function(event, element){
		console.log('Has Errors');
	});
	var drDestroy = $('#input-file-to-destroy').dropify();
	drDestroy = drDestroy.data('dropify')
	$('#toggleDropify').on('click', function(e){
		e.preventDefault();
		if (drDestroy.isDropified()) {
			drDestroy.destroy();
		} else {
			drDestroy.init();
		}
	});
	
	
	// 체크여부 확인
	$(".listTable table th input:checkbox").click(function(){
		if($(".listTable table th input:checkbox").is(":checked") == true) {
			$(this).parent().parent().parent().parent().parent().parent().find("input:checkbox").attr("checked", "checked");
		} else{
			$(this).parent().parent().parent().parent().parent().parent().find("input:checkbox").attr("checked", false);
		}
	});	
    
    /* 210803 */
    var smallSwiper = new Swiper('.small .swiper-container', {
        direction: 'horizontal',
        spaceBetween : 10,
        slidesPerView: 5,
        slidesPerGroup: 1,
    });
    
    var itemSwiper = new Swiper('.m_mega_wrap .mega_menu > ul > li .item .swiper-container', {
        direction: 'horizontal',
        spaceBetween : 0,
        autoHeight:true,
        slidesPerView: 1,
        slidesPerGroup: 1,
        pagination: {
          el: ".swiper-pagination",
        },
		observer: true,
		observeParents: true
    });
    
    //스크롤시 short 검색
    $(".btn_short_search").click(function(){
        if( $(this).parent().hasClass("on") ){
            $(this).parent().removeClass("on");
            $(this).prev(".short_search").hide();
            
        }else{
            $(this).parent().addClass("on");
            $(this).prev(".short_search").show();
        }
        return false;
    });
    
    //Global Search
    $(".search_wrap .categories > ul li a").click(function(){
        if( $(this).hasClass("on") ){
            $(this).removeClass("on");
            $(this).next().hide();
        }else{
            $(this).addClass("on");
            $(this).next().show();
        }
        return false;
    });
    
    //모바일 메가메뉴
    $(".open_m_mega").click(function(){
        if( $(this).hasClass("on") ){
            $(this).removeClass("on");
            $(".m_mega_wrap").slideUp();
            $(".open_bg").fadeOut();
            
        }else{
            $(this).addClass("on");
            $(".open_bg").fadeIn();
            $(".m_mega_wrap").slideDown();
        }
        return false;
        
    });
    
    $("#m_header .m_mega_wrap .mega_menu > ul > li > a").click(function(){
        if( $(this).hasClass("on") ){
            $(this).removeClass("on");
            $(this).next().slideUp();
        }else{
            $("#m_header .m_mega_wrap .mega_menu > ul > li .item").slideUp();
			$("#m_header .m_mega_wrap .mega_menu > ul > li > a").removeClass("on");
            $(this).addClass("on");
            $(".open_bg").fadeIn();
            $(this).next().slideDown();
        }
        return false;
        
    });
    
    //	
    $(".btn_fullmenu").click(function(){
        $(".fullmenu_list").addClass("on");
        $(".open_m_bg").fadeIn();
        return false;
    });
    
    $(".btn_close").click(function(){
        $(".fullmenu_list").removeClass("on");
        $(".open_m_bg").fadeOut();
        return false;
    });
	
    /* btn_info */
    $(".btn_info").click(function(){
        $(".open_pop").fadeIn("fast");
        $(".open_pop .in > div").hide();
        $(".pop_info").fadeIn();
        return false;
    });
    
    $(".btn_info02").click(function(){
        $(".open_pop .in > div").hide();
        $(".pop_info02").fadeIn();
        return false;
    });
    
    $(".open_setting").click(function(){
        $(".setting_cont").slideDown();
        return false;
    });
    
    $(".close_setting").click(function(){
        $(".setting_cont").slideUp();
        return false;
    });
    
    //extend
    $(".extend").click(function(){
        $(this).next(".extended_area").slideToggle();
        $(this).parent().toggleClass("pos");
        return false;
    });
    
    //close_error
    $(".close_error").click(function(){
        $(this).parent(".error").fadeOut();
        return false;
    });
    
});

$(window).scroll(function(){
	var wScroll = $(this).scrollTop();
	var wHeight = $(this).height();
	//console.log(wScroll);
	//console.log($(this).height());
	if(wScroll < 50) {
$(".header_bot").removeClass("active");
	} else {
	if((wScroll+wHeight) > 900) {
		$(".header_bot").addClass("active");
	} else if((wScroll+wHeight) < 900) {
		$(".header_bot").removeClass("active");
	}
	}
	return false;
});


/* YJI (210825)*/
//쿠키저정하기
function setCookie(cookieName, value, exdays){
 var exdate = new Date();
 exdate.setDate(exdate.getDate() + exdays);
 var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
 document.cookie = cookieName + "=" + cookieValue;
}
//쿠키삭제하기
function deleteCookie(cookieName){
 var expireDate = new Date();
 expireDate.setDate(expireDate.getDate() - 1);
 document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
//쿠키불러오기
function getCookie(cookieName) {
 cookieName = cookieName + '=';
 var cookieData = document.cookie;
 var start = cookieData.indexOf(cookieName);
 var cookieValue = '';
 if(start != -1){
     start += cookieName.length;
     var end = cookieData.indexOf(';', start);
     if(end == -1)end = cookieData.length;
     cookieValue = cookieData.substring(start, end);
 }
 return unescape(cookieValue);
}
/* YJI (210825) -------------------*/

/* topBtn */
$(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 300) {
            $('.floatBtns').fadeIn();
        } else {
            $('.floatBtns').fadeOut();
        }
    });
    
    $(".gotoTop a").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
});

  