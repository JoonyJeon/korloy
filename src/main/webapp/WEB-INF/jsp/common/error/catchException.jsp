<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/error/catch403.jsp
 - 설    명	:	시스템에러시 표시하는화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2019.08.01    1.0       LYS      신규작성
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@ page import="java.io.StringWriter"%> 
<%@ page import="java.io.PrintWriter"%> 
<%@ page import="org.slf4j.Logger"%> 
<%@ page import="org.slf4j.LoggerFactory"%> 
<%@taglib uri="/WEB-INF/tld/eaction.tld" prefix="eaction" %>

<%! 
private static final Logger logger = LoggerFactory.getLogger("com.waisolutions");
%> 

<%@include file="/WEB-INF/jsp/common/include.inc" %>

<eaction:html>

<body>
	<div id="main" role="main">

			<!-- RIBBON -->
			
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

				<!-- row -->
				<div class="row">
				
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
						<div class="row">
							<div class="col-sm-12">
								<div class="text-center error-box">
									<h1 class="error-text tada animated"><i class="fa fa-times-circle text-danger error-icon-shadow"></i> Error</h1>
									<h2 class="font-xl"><strong>죄송합니다. 시스템 처리 시에 에러가 발생했습니다!</strong></h2>
									<br />
									<p class="lead semi-bold">
										<strong>다시 실행해 보시기 바랍니다.</strong><br><br>
										<small>
											문제가 계속 될 경우 관리자에게 문의해 주십시요
										</small>
									</p>
									
								</div>
				
							</div>
				
						</div>
				
					</div>
					
				</div>
				<!-- end row -->

			</div>
			<!-- END MAIN CONTENT -->

		</div>   

	</body>

</eaction:html>
