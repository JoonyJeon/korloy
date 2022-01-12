<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/error/catch404.jsp
 - 설    명	:	시스템에러시 표시하는화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.08.01    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>

<eaction:html>
<body>
	<div id="main" role="main">

			
			<!-- MAIN CONTENT -->
			<div id="content">

				<!-- row -->
				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
						<div class="row">
							<div class="col-sm-12">
								<div class="text-center error-box">
									<h1 class="error-text-2 bounceInDown animated"> Error 404 <span class="particle particle--c"></span><span class="particle particle--a"></span><span class="particle particle--b"></span></h1>
									<h2 class="font-xl"><strong><i class="fa fa-fw fa-warning fa-lg text-warning"></i> Page <u>Not</u> Found</strong></h2>
									<br />
									<p class="lead">
										The page you requested could not be found, either contact your webmaster or try again. Use your browsers <b>Back</b> button to navigate to the page you have prevously come from
									</p>
									<p class="font-md">
										<b>... That didn't work on you? Dang. May we suggest a search, then?</b>
									</p>
									<br>
								</div>
				
							</div>
				
						</div>
				
					</div>

				<!-- end row -->

			</div>
			
			</div>
			<!-- END MAIN CONTENT -->

		</div>
</body>
</eaction:html>