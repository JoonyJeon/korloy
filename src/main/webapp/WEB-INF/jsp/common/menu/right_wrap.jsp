<%----------------------------------------------------------------------------------------
 - 파일이름	:	common/right_wrap/top.jsp
 - 설    명	:	(KORLOY) 상단팝업화면
 - 추가내용     :
 - 버전관리
 ----------------------------------------------------------
 -   Date      Version   Auther   Description
 ------------  --------  -------  --------------------------
 - 2021.08.03    1.0       YJI      신규작성
 ----------------------------------------------------------
------------------------------------------------------------------------------------------%>
<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/include.inc" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@page import = "com.eaction.framework.business.logic.user.constant.UserConstUrl"%>
<%@page import = "com.eaction.framework.business.logic.user.constant.UserConstKey"%>
<%@page import = "com.eaction.framework.business.logic.login.constant.LoginConstKey"%>
<%@page import = "com.eaction.framework.common.model.CodeInfo"%>
<%@page import = "java.util.Comparator"%>
<%@page import = "java.util.List"%>
<%@page import = "java.util.ArrayList"%>

<%
List<CodeInfo> nationList = (List<CodeInfo>)request.getAttribute("nationList");
String comm_lang = ConfigMng.getLang(request);
List<CodeInfo> unitList = (List<CodeInfo>)request.getAttribute("unitList");
List<CodeInfo> currencyList = (List<CodeInfo>)request.getAttribute("currencyList");
List<CodeInfo> languageList = (List<CodeInfo>)request.getAttribute("languageList");
List<CodeInfo> empByNationCdList = (List<CodeInfo>)request.getAttribute("empByNationCdList");
int cartCnt = (int)request.getAttribute("cartCnt");
String deviceType = (String)request.getAttribute("deviceType");
String comm_nation = ConfigMng.getNation(request);
String comm_size_unit = ConfigMng.getSizeUnit(request);
String comm_curr = ConfigMng.getCurrency(request);
String inch_use_yn = CodeTableMng.getCodeName("CM0019", "CC0204");
%>
 
<!-- 상단팝업 -->   
<div class="login_wrap" style="position:fixed;" >
<!-- <div class="login_wrap"> -->
    <div class="in">
        <div class="pop_close"><a><img src="<%=PATH_IMG %>/img_pop_close.png" alt=""></a></div>
        <div class="img">
            <img src="<%=PATH_IMG %>/bg_login.png" alt="">
        </div>
        <div class=pop_cont>
        	<!-- 로그인 -->
            <div id="login">
                <div class="txt">
                    <div class="login_tab">
                        <ul>
                        	<%-- Login --%>
                            <li><a class="open_login on" data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></a></li>
                        	<%-- Register --%>
                            <li><a class="open_register" data-lang="FR000005"><%=LangMng.LANG_D("FR000005", session_lang) %></a></li>
                        </ul>
                    </div>
                    <div class="login_cont">
                        <div class="login">
                        	<form id="login-form" name="login-form" method="POST" action="<%=ConstUrl.LOGIN_URL%>">
					            <input type="hidden" name="<%=ConstKey.ACTION_ID %>" value="<%=ConstKey.LOGIN_OK %>"> 
	                            <div class="input">
		                        	<%-- Email address --%>
	                                <label for="login_user_id"  data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %><span class="red">*</span></label>
	                                <input type="text" id="login_user_id" name="login_user_id" placeholder="<%=LangMng.LANG_D("FR000009", session_lang) %>" data-lang="FR000009" data-langtype="placeholder"/>
	                            </div>
	                            <div class="input">
		                        	<%-- Password --%>
	                                <label for="login_user_pwd"  data-lang="FR000010"><%=LangMng.LANG_D("FR000010", session_lang) %><span class="red">*</span></label>
	                                <input type="password" id="login_user_pwd" name="login_user_pwd" placeholder="<%=LangMng.LANG_D("FR000010", session_lang) %>" data-lang="FR000010" data-langtype="placeholder"/>
	                                <p>
	                                    <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                    <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                </p>
	                            </div>
                        	</form>
                            <div class="btn_group">
	                        	<%-- Login --%>
                                <a id="doLogin" class="btn02" data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></a>
                            </div>
                            <div class="btn_group">
                                <div class="left">
                                    <label class="chkN">
			                        	<%-- Remember Me --%>
                                        <input type="checkbox" id="remember"><span  data-lang="FR000011"><%=LangMng.LANG_D("FR000011", session_lang) %></span>
                                    </label>
                                </div>
                                <div class="right">
		                        	<%-- Forgot password? --%>
                                    <a class="open_password" data-lang="FR000012"><%=LangMng.LANG_D("FR000012", session_lang) %></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 회원가입 -->
            <div id="register">
                <div class="txt">
                    <div class="login_tab">
                        <ul>
                        	<%-- Login --%>
                            <li><a class="open_login" data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></a></li>
                        	<%-- Register --%>
                            <li><a class="open_register on" data-lang="FR000005"><%=LangMng.LANG_D("FR000005", session_lang) %></a></li>
                        </ul>
                    </div>
                    <div class="login_cont">
                        <div class="register">
	                        <form id="register-form" name="register-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
	                            <p data-lang="FR000016">
		                        	<%-- You can enjoy more benefits by registering as a member Register for membership through email verification.
		                        	 --%>
		                        	 <%=LangMng.LANG_D("FR000016", session_lang) %>
<!-- 	                                You can enjoy more benefits by registering as a member Register for membership through email verification. -->
	                            </p>
	                            <div class="input">
		                        	<%-- Email address --%>
	                                <label for="join_user_id"><span data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %></span><span class="red">*</span>
	                                </label>
	                                <input type="text" id="join_user_id" name="join_user_id" placeholder="<%=LangMng.LANG_D("FR000009", session_lang) %>" data-lang="FR000009" data-langtype="placeholder"/>
	                            </div>
	                            <div class="input join_pwd">
		                        	<%-- Password --%>
	                                <label for="join_user_pwd"><span data-lang="FR000010"><%=LangMng.LANG_D("FR000010", session_lang) %></span><span class="red">*</span></label>
	                                <input type="password" id="join_user_pwd" name="join_user_pwd" placeholder="<%=LangMng.LANG_D("FR000010", session_lang) %>" data-lang="FR000010" data-langtype="placeholder"/>
                                    <p>
                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
                                    </p>
	                            </div>
	                            <div class="input join_pwd_re">
		                        	<%-- Password(again) --%>
	                                <label for="join_user_pwd"><span data-lang="FR000018"><%=LangMng.LANG_D("FR000018", session_lang) %></span><span class="red">*</span></label>
	                                <input type="password" id="conf_join_user_pwd" name="conf_join_user_pwd" placeholder="<%=LangMng.LANG_D("FR000018", session_lang) %>" data-lang="FR000018" data-langtype="placeholder"/>
                                    <p>
                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
                                    </p>
	                            </div>
								<div class="robot">	
			                        <!-- reCAPCHA  -->
			                        <div class="g-recaptcha" 
								   		 data-sitekey="<%=ConfigMng.getValue(IPropertyKey.SITE_KEY)%>"
								   		 data-callback="suc_func"
								   		 data-expired-callback="exp_func">
								   	</div>
	                            </div>
	                            <div class="btn_group">
		                        	<%-- Create Account --%>
	                                <a class="btn02" id="doRegister" data-lang="FR000020"><%=LangMng.LANG_D("FR000020", session_lang) %></a>
	                            </div>
	                            <div class="bot_txt agreement">
		                        	<%-- By creating an account, --%>
<%-- 	                                <span data-lang="FR000021"><%=LangMng.LANG_D("FR000021", session_lang) %></span> --%>
		                        	<%-- I accept the Term of use and Privacy Polish. --%>
	                                <a class="open_privacy">
<%-- 	                                	<span class="blue" data-lang="FR000022"><%=LangMng.LANG_D("FR000022", session_lang) %></span> --%>
			                        	<label style="display: inline;">
			                        		<input type="checkbox" id="join_terms_read" name="join_terms_read" style="position: unset" />
	                                		<%-- 3자 제공 동의 --%>
			                        		<span class="blue" style="display: inline" data-lang="FR000224"><%=LangMng.LANG_D("FR000224", session_lang) %></span>
		                                </label>
		                                <%-- Terms of use and Legal disclaimer, Privacy Statement --%>
        								<span class="left" data-lang="FR000007" style="cursor:pointer;text-align:left"><%=LangMng.LANG_D("FR000007", session_lang) %></span>
	                                </a>
	                            </div>
	                        </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 비밀번호 재발급 -->
            <div id="password">
                <div class="txt">
                    <div class="login_cont">
                        <div class="password">
                            <div class="send_wrap">
	                        	<%-- Forgot Password? --%>
                                <div class="send_tit tit03" data-lang="FR000012"><%=LangMng.LANG_D("FR000012", session_lang) %></div>
                                <p data-lang="FR000204">
                                    <%-- Provide your account’s email<br />
                                    for which you want to reset your password! --%>
		                        	<%=LangMng.LANG_D("FR000204", session_lang) %>
                                </p>
                                <form id="pwd-user_id-form" name="pwd-user_id-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
	                                <div class="input">
			                        	<%-- Email address --%>
	                                    <label for="rspwd_user_id"><span data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %></span><span class="red">*</span></label>
	                                    <input type="text" value="" id="rspwd_user_id" name="rspwd_user_id" placeholder="<%=LangMng.LANG_D("FR000009", session_lang) %>" data-lang="FR000009" data-langtype="placeholder"/>
	                                </div>
                                </form>
                            </div>
                            <div class="btn_group">
                            	<%-- BACK --%>
								<a class="open_login btn01 dirBack"><span class="btn_back" data-lang="FR000237"><%=LangMng.LANG_D("FR000237", session_lang) %></span></a>
                            	<%-- NEXT --%>
                                <a id="getUserbyUserIdBtn" class="btn02 dirNext"><span class="btn_login" data-lang="FR000205"><%=LangMng.LANG_D("FR000205", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 비밀번호 재설정 -->
            <div id="reset_password">
                <div class="txt">
                    <div class="login_cont">
                        <div class="password register">
                            <div class="send_wrap">
	                        	<%-- Reset Password --%>
                                <div class="send_tit tit03" data-lang="FR000206"><%=LangMng.LANG_D("FR000206", session_lang) %></div>
                                <p data-lang="FR000207">
		                        	<%-- Reset your Password. --%>
                                    <%=LangMng.LANG_D("FR000207", session_lang) %>
                                </p>
                                <form id="reset-pwd-form" name="reset-pwd-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
		                            <div class="input">
			                        	<%-- Password --%>
		                                <label for="reset_user_pwd"><span data-lang="FR000010"><%=LangMng.LANG_D("FR000010", session_lang) %></span><span class="red">*</span></label>
		                                <input type="password" id="reset_user_pwd" name="reset_user_pwd" placeholder="<%=LangMng.LANG_D("FR000010", session_lang) %>" data-lang="FR000010" data-langtype="placeholder"/>
	                                    <p>
	                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                    </p>
		                            </div>
		                            <div class="input">
			                        	<%-- Password(again) --%>
		                                <label for="conf_reset_user_pwd"><span data-lang="FR000018"><%=LangMng.LANG_D("FR000018", session_lang) %></span><span class="red">*</span></label>
		                                <input type="password" id="conf_reset_user_pwd" name="conf_reset_user_pwd" placeholder="<%=LangMng.LANG_D("FR000018", session_lang) %>" data-lang="FR000018" data-langtype="placeholder"/>
	                                    <p>
	                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                    </p>
		                            </div>
	                            </form>
                            </div>
                            <div class="btn_group">
	                        	<%-- NEXT --%>
                                <a id="doResetPwdBtn" class="btn02"><span class="btn_login" data-lang="FR000205"><%=LangMng.LANG_D("FR000205", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 회원 비밀번호 재설정(이메일X) -->
            <div id="user_reset_password">
                <div class="txt">
                    <div class="login_cont">
                        <div class="password register">
                            <div class="send_wrap">
	                        	<%-- Reset Password --%>
                                <div class="send_tit tit03" data-lang="FR000206"><%=LangMng.LANG_D("FR000206", session_lang) %></div>
                                <p data-lang="FR000207">
		                        	<%-- Reset your Password. --%>
                                    <%=LangMng.LANG_D("FR000207", session_lang) %>
                                </p>
                                <form id="user-pwd-form" name="user-pwd-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
		                            <div class="input pwd_input">
			                        	<%-- 기존비밀번호 --%>
		                                <label for="user_old_pwd"><span data-lang="FR000234"><%=LangMng.LANG_D("FR000234", session_lang) %></span><span class="red">*</span></label>
		                                <input type="password" id="user_old_pwd" name="user_old_pwd" placeholder="<%=LangMng.LANG_D("FR000234", session_lang) %>" data-lang="FR000234" data-langtype="placeholder"/>
	                                    <p>
	                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                    </p>
		                            </div>
		                            <div class="input pwd_input">
			                        	<%-- Password --%>
		                                <label for="user_new_pwd"><span data-lang="FR000010"><%=LangMng.LANG_D("FR000010", session_lang) %></span><span class="red">*</span></label>
		                                <input type="password" id="user_new_pwd" name="user_new_pwd" placeholder="<%=LangMng.LANG_D("FR000010", session_lang) %>" data-lang="FR000010" data-langtype="placeholder"/>
	                                    <p>
	                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                    </p>
		                            </div>
		                            <div class="input pwd_input">
			                        	<%-- Password(again) --%>
		                                <label for="conf_user_new_pwd"><span data-lang="FR000018"><%=LangMng.LANG_D("FR000018", session_lang) %></span><span class="red">*</span></label>
		                                <input type="password" id="conf_user_new_pwd" name="conf_user_new_pwd" placeholder="<%=LangMng.LANG_D("FR000018", session_lang) %>" data-lang="FR000018" data-langtype="placeholder"/>
	                                    <p>
	                                        <a class="pwdHide"><img src="<%=PATH_IMG %>/ico_passopen.png" alt=""></a>
	                                        <a class="pwdHide show"><img src="<%=PATH_IMG %>/ico_passno.png" alt=""></a>
	                                    </p>
		                            </div>
	                            </form>
                            </div>
                            <div class="btn_group">
	                        	<%-- BACK --%>
								<a class="open_user btn01 dirBack"><span class="btn_back" data-lang="FR000237"><%=LangMng.LANG_D("FR000237", session_lang) %></span></a>
	                        	<%-- NEXT --%>
                                <a id="doResetUserPwdBtn" class="btn02 dirNext"><span class="btn_login" data-lang="FR000205"><%=LangMng.LANG_D("FR000205", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 가입완료 -->
            <div id="confirm">
                <div class="txt">
                    <div class="login_cont">
                        <div class="confirm">
                            <div class="send_wrap">
	                        	<%-- Send authentication mail --%>
                                <div class="send_tit tit01" data-lang="FR000208"><%=LangMng.LANG_D("FR000208", session_lang) %></div>
                                <p data-lang="FR000209">
		                        	<%=LangMng.LANG_D("FR000209", session_lang) %>
								<%-- 
                                    An authentication email was sent to the email
                                    you signed up for. You must check your e-mail
                                    to complete the subscription. 
                                    <br /><br />
                                    <%=LangMng.LANG_D("FR000210", session_lang) %>
	                        	<%-- 
                                    If you need to resend the verification email,<br />
                                    please click the button below.
								--%>  
                                </p>
                            </div>
                            <div class="btn_group">
	                        	<%-- Resend authentication mail --%>
                                <a class="resendAuthMail btn01" data-lang="FR000210"><%=LangMng.LANG_D("FR000210", session_lang) %></a>
	                        	<%-- Confirm --%>
<%--                                 <a id="joinAuthConf" class="open_join btn02"><span class="btn_confirm" data-lang="FR000211"><%=LangMng.LANG_D("FR000211", session_lang) %></span></a> --%>
	                        	<%-- Confirm --%>
                                <a id="resetPwdAuth" class="open_rst_pwd btn02"><span class="btn_confirm" data-lang="FR000211"><%=LangMng.LANG_D("FR000211", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 비밀번호 찾기 =>이메일인증확인 -->
            <div id="confirm_for_find_pass">
            	<input type="hidden" id="user_id_for_find_pass" />
                <div class="txt">
                    <div class="login_cont">
                        <div class="confirm">
                            <div class="send_wrap">
                            	<%-- Send authentication mail --%>
                                <div class="send_tit tit01" data-lang="FR000208"><%=LangMng.LANG_D("FR000208", session_lang) %></div>
                                <p data-lang="FR000209">
                                	<%=LangMng.LANG_D("FR000209", session_lang) %>
                            	<%-- 
                                    An authentication email was sent to the email
                                    you signed up for. You must check your e-mail
                                    to complete the subscription.
                                    <br /><br />
                                    <%=LangMng.LANG_D("FR000210", session_lang) %>
                            	<%-- 
                                    If you need to resend the verification email,<br />
                                    please click the button below.
								 --%>
                                </p>
                            </div>
                            <div class="btn_group">
                            	<%-- Resend authentication mail --%>
                                <a class="resendAuthMail btn01" data-lang="FR000210"><%=LangMng.LANG_D("FR000210", session_lang) %></a>
                                <%-- Confirm --%>
                                <a id="emailAuthConf" class="open_join btn02"><span class="btn_confirm" data-lang="FR000211"><%=LangMng.LANG_D("FR000211", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 가입환영 -->
            <div id="join">
                <div class="txt">
                    <div class="login_cont">
                        <div class="join">
                            <div class="send_wrap">
                            	<%-- WELCOME --%>
                                <div class="send_tit tit02" data-lang="FR000212"><%=LangMng.LANG_D("FR000212", session_lang) %></div>
                                <p data-lang="FR000213">
                                	<%=LangMng.LANG_D("FR000213", session_lang) %>
                               	<%-- 
                                    KORLOY strives to become the most
                                    competitive company in the world
                                    to share happiness with people in the world
                               	 --%>
                                </p>
                            </div>
                            <div class="btn_group">
                            	<%-- LOGIN --%>
                                <a href="javascript:void(0);" class="open_login btn02"><span class="btn_login" data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- My page -->
            <div id="user">
                <div class="txt">
                    <div class="login_cont">
                        <div class="user">
                            <div class="send_wrap">
                            	<%-- My Page --%>
                                <div class="send_tit" data-lang="FR000214"><%=LangMng.LANG_D("FR000214", session_lang) %></div>
                                <div class="user_list">
                                	<form name="mypage-form" id="mypage-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
	                                    <ul>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- ID --%>
	                                                <label for="mPage_user_id"><span data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %></span><span class="red">*</span></label>
	                                                <input type="text"  id="mPage_user_id" name="mPage_user_id" readOnly>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Name --%>
	                                                <label for="mPage_user_name" data-lang="FR000017"><%=LangMng.LANG_D("FR000017", session_lang) %></label>
	                                                <input type="text"  id="mPage_user_name" name="mPage_user_name" />
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Company --%>
	                                                <label for="mPage_user_company" data-lang="FR000099"><%=LangMng.LANG_D("FR000099", session_lang) %></label>
	                                                <input type="text"  id="mPage_user_company" name="mPage_user_company">
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Company E-mail --%>
	                                                <label for="mPage_user_com_mail" data-lang="FR000100"><%=LangMng.LANG_D("FR000100", session_lang) %></label>
	                                                <input type="text"  id="mPage_user_com_mail" name="mPage_user_com_mail">
	                                            </div>
	                                        </li>
	                                       <li>
	                                            <div class="input">
	                                            	<%-- Company Phone --%>
	                                                <label for="mPage_user_com_tel" data-lang=FR000241><%=LangMng.LANG_D("FR000241", session_lang) %></label>
	                                                <!-- input type="text"  id="mPage_user_com_tel" name="mPage_user_com_tel"-->
	                                                <input type="text" id="mPage_user_com_tel" name="mPage_user_com_tel" placeholder="<%=LangMng.LANG_D("FR000265", session_lang) %>" data-lang="FR000266" data-langtype="placeholder" autocomplete='off'>
	                                                
	                                            </div>
	                                        </li>
	                                        
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Company Address --%>
	                                                <label for="mPage_user_com_addr" data-lang="FR000242"><%=LangMng.LANG_D("FR000242", session_lang) %></label>
	                                                <input type="text"  id="mPage_user_com_addr" name="mPage_user_com_addr">
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Phone --%>
	                                                <label for="mPage_user_mobile" data-lang="FR000194"><%=LangMng.LANG_D("FR000194", session_lang) %></label>
	                                                <!-- input type="text"  id="mPage_user_mobile" name="mPage_user_mobile"-->
													<input type="text" id="mPage_user_mobile" name="mPage_user_mobile" placeholder="<%=LangMng.LANG_D("FR000266", session_lang) %>" data-lang="FR000266" data-langtype="placeholder" autocomplete='off'>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Current Site --%>
	                                                <label for="mPage_user_nation" data-lang="FR000019"><%=LangMng.LANG_D("FR000019", session_lang) %></label>
<%-- 	                                                <eaction:select name="mPage_user_nation" id="mPage_user_nation"  --%>
<%-- 	                                                	list="<%= nationList %>" > --%>
<%-- 	                                                </eaction:select> --%>
								                <!-- Add 212026 S -->
								                <div class="nationPart">
								                    <div class="current" title=""></div>
								                    <input type="hidden" id="mPage_user_nation" />
								                    <div class="nationForm" id="nationResultSelect">
								                        <div class="schForm">
								                            <input type="text" value="" id="mPage_search_nation" />
								                            <button type="button" class="btnNation">나라검색</button>
								                        </div>
								                        <!-- 검색결과 없을 경우 S //-->
								                        <ul class="empty">
								                        	<%-- No search results --%>
								                            <li data-lang="FR000136"><%=LangMng.LANG_D("FR000136", session_lang) %></li>
								                        </ul>
								                        <!-- 검색결과 없을 경우 E //-->
								                        <ul class="nationResult">
<!-- 								                        	<li><a title="" data-schntn=""></a></li> -->
								                            <% for(CodeInfo info : nationList) {
								                            	info.setCodenm(info.getCodenm_e());
								                            	if((comm_lang).equals("KOR")) info.setCodenm(info.getCodenm_k());
								                            %>
								                            <li><a title="<%= info.getCodenm() %>" data-schntn="<%= info.getCode() %>"><%= info.getCodenm() %></a></li>
								                            <% }%>
								                        </ul>
								                    </div>
								                </div>
								                <!-- Add 212026 E -->
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Current language --%>
	                                                <label for="mPage_user_lang" data-lang="FR000195"><%=LangMng.LANG_D("FR000195", session_lang) %></label>
	                                                <eaction:select name="mPage_user_lang" id="mPage_user_lang" 
	                                                	list="<%=languageList %>" >
	                                                </eaction:select>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Unit of measurement --%>
	                                                <label for="mPage_unit_cd" data-lang="FR000058"><%=LangMng.LANG_D("FR000058", session_lang) %></label>
	                                                <select name="mPage_unit_cd" id="mPage_unit_cd" >
	                                                	<option value="CC0001">Metric</option>
	                                                	<option value="CC0002">Inch</option>
                                                	</select>
	                                            </div>
	                                        </li>
	                                        <li>
	                                            <div class="input">
	                                            	<%-- Currency --%>
	                                                <label for="mPage_curr_cd" data-lang="FR000045"><%=LangMng.LANG_D("FR000045", session_lang) %></label>
	                                                <eaction:select name="mPage_curr_cd" id="mPage_curr_cd" 
	                                                	list="<%= currencyList %>" >
	                                                </eaction:select>
	                                            </div>
	                                        </li>
	                                    </ul>
                                    </form>
                                </div>
                            </div>
                            <div class="btn_group" style="margin-bottom: 0">
								<%-- 승급요청 --%>
                                <a class="btn02 same open_vip active" data-lang="FR000102"><%=LangMng.LANG_D("FR000102", session_lang) %></a>
								<%-- 비밀번호 변경 --%>
                                <a id="resetPwdUserBtn" class="btn02 same" data-lang="FR000196"><%=LangMng.LANG_D("FR000196", session_lang) %></a>
								<%-- 회원정보 변경 --%>
                                <a id="updMypage" class="btn02 same" data-lang="FR000064"><%=LangMng.LANG_D("FR000064", session_lang) %></a>
								<%-- 회원탈퇴 --%>
                                <a class="btn01 same open_withdrawal" data-lang="FR000105"><%=LangMng.LANG_D("FR000105", session_lang) %></a>
                            </div>
                            <div class="btn_group" style="margin-top: 0">
								<%-- 로그아웃 --%>
                                <a id="doLogoutBtn" class="btn01 same" data-lang="FR000111" style="width: 98%; "><%=LangMng.LANG_D("FR000111", session_lang) %></a>
                            </div>
                            <div class="privacy">
								<%-- Privacy Statement --%>
<%--                                 <a data-lang="FR000115" onclick="location.href='/privacy.do'"><%=LangMng.LANG_D("FR000115", session_lang) %></a> --%>
                                <%-- Terms of use and Legal disclaimer, Privacy Statement --%>
                                <a class="left" data-lang="FR000007" style="cursor:pointer;float: left;" onclick="location.href='/privacy.do'"><%=LangMng.LANG_D("FR000007", session_lang) %></a>
                            </div>
							<%-- 회원탈퇴 --%>
<!-- 							<div class="btnwithDrawal"> -->
<%-- 								<a class="open_withdrawal" data-lang="FR000105"><%=LangMng.LANG_D("FR000105", session_lang) %></a> --%>
<!-- 							</div> -->
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 회원탈퇴 -->
            <div id="withdrawal">
                <div class="txt">
                    <div class="login_cont">
                        <div class="withdrawal">
                            <div class="send_wrap">
								<%-- Membership Withdrawal --%>
                                <div class="send_tit tit04" data-lang="FR000240"><%=LangMng.LANG_D("FR000240", session_lang) %></div>
                                <p data-lang="FR000106">
									<%-- Please check before leaving KORLOY --%>
                                    <%=LangMng.LANG_D("FR000106", session_lang) %>
                                </p>
                                <div class="checklist">
                                	<form id="withdrawal-form" name="withdrawal-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
	                                    <ul>
	                                        <li>
	                                            <label class="chkN">
													<%-- The items in your shopping cart will be deleted --%>
	                                                <input type="checkbox" id="withdrawal_1" name="withdrawal_1"><span data-lang="FR000107"><%=LangMng.LANG_D("FR000107", session_lang) %></span>
	                                            </label>
	                                        </li>
	                                        <li>
	                                            <label class="chkN">
													<%-- Assembly, quotation details are deleted --%>
	                                                <input type="checkbox" id="withdrawal_2" name="withdrawal_2"><span data-lang="FR000108"><%=LangMng.LANG_D("FR000108", session_lang) %></span>
	                                            </label>
	                                        </li>
	                                    </ul>
	                                    <label class="chkN">
											<%-- I have read all the instructions and agree to it. --%>
	                                        <input type="checkbox" id="withdrawal_3" name="withdrawal_3"><span data-lang="FR000109"><%=LangMng.LANG_D("FR000109", session_lang) %></span>
	                                    </label>
                                    </form>
                                </div>
                            </div>
                            <div class="btn_group">
								<%-- BACK --%>
								<a class="open_user btn01 dirBack"><span class="btn_back" data-lang="FR000237"><%=LangMng.LANG_D("FR000237", session_lang) %></span></a>
								<%-- GO --%>
                                <a id="doWithdrawalBtn" class="btn02 dirNext"><span class="btn_login" data-lang="FR000110"><%=LangMng.LANG_D("FR000110", session_lang) %></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- VIP 전환 -->
            <div id="vip">
	            <div class="txt">
	                <div class="login_cont">
	                    <div class="vip">
							<form id="upgrade-form" name="upgrade-form" method="POST" action="<%=UserConstUrl.USER_URL%>">
		                        <div class="send_wrap">
	                              	<%-- Become KORLOY's <br />VIP Customer --%>
		                            <div class="send_tit tit05" data-lang="FR000103"><%=LangMng.LANG_D("FR000103", session_lang) %></div>
									<p>
										<%-- < PRO user's benefits > --%>
			                            <span data-lang="FR000317"><%=LangMng.LANG_D("FR000317", session_lang) %></span><br />
										<%--  · Download various files --%>
			                            <span data-lang="FR000318"><%=LangMng.LANG_D("FR000318", session_lang) %></span><br />
										<%--  · Assembly tools --%>
			                            <span data-lang="FR000319"><%=LangMng.LANG_D("FR000319", session_lang) %></span><br />
										<%--  · Cart/Quotation/Tailored Item Request --%>
			                            <span data-lang="FR000320"><%=LangMng.LANG_D("FR000320", session_lang) %></span><br />
										<%--  · Assigned to a salesman --%>
			                            <span data-lang="FR000321"><%=LangMng.LANG_D("FR000321", session_lang) %></span><br />
		                            </p>
		                            <div class="login">
<%-- 			                            <eaction:select name="ug_user_nation" id="ug_user_nation" init="<%=ConstKey.KEY_YES %>" --%>
<%-- 		                                 	list="<%= empByNationCdList %>" style="display:none;" /> --%>
		                            	<div class="input">
											<%-- 메일주소(ID) --%>
		                                    <label for="ug_user_id"><span data-lang="FR000009"><%=LangMng.LANG_D("FR000009", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_id" name="ug_user_id" readOnly>
		                                </div>
			                                
		                                <div class="input">
											<%-- Name --%>
		                                    <label for="ug_user_name"><span data-lang="FR000017"><%=LangMng.LANG_D("FR000017", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_name" name="ug_user_name" placeholder="<%=LangMng.LANG_D("FR000017", session_lang) %>" data-lang="FR000017" data-langtype="placeholder"/>
		                                </div>
		                                
		                                <div class="input">
											<%-- Company Name --%>
		                                    <label for="ug_user_company"><span data-lang="FR000099"><%=LangMng.LANG_D("FR000099", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_company" name="ug_user_company" placeholder="<%=LangMng.LANG_D("FR000099", session_lang) %>" data-lang="FR000099" data-langtype="placeholder"/>
		                                </div>
		                                <div class="input">
											<%-- Company Email address --%>
		                                    <label for="ug_user_com_mail"><span data-lang="FR000100"><%=LangMng.LANG_D("FR000100", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_com_mail" name="ug_user_com_mail"  placeholder="<%=LangMng.LANG_D("FR000100", session_lang) %>" data-lang="FR000100" data-langtype="placeholder"/>
		                                </div>
		                                
		                                <div class="input">
											<%-- Company Phone --%>
		                                    <label for="ug_user_com_tel"><span data-lang="FR000241"><%=LangMng.LANG_D("FR000241", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_com_tel" name="ug_user_com_tel" placeholder="<%=LangMng.LANG_D("FR000241", session_lang) %>" data-lang="FR000241" data-langtype="placeholder"/>
		                                </div>
		                                <div class="input">
											<%-- Company Address --%>
		                                    <label for="ug_user_com_addr"><span data-lang="FR000242"><%=LangMng.LANG_D("FR000242", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_com_addr" name="ug_user_com_addr" placeholder="<%=LangMng.LANG_D("FR000242", session_lang) %>" data-lang="FR000242" data-langtype="placeholder"/>
		                                </div>
		                                <div class="input">
											<%-- Phone --%>
		                                    <label for="ug_user_mobile"><span data-lang="FR000194"><%=LangMng.LANG_D("FR000194", session_lang) %></span><span class="red">*</span></label>
		                                    <input type="text" id="ug_user_mobile" name="ug_user_mobile" placeholder="<%=LangMng.LANG_D("FR000194", session_lang) %>" data-lang="FR000194" data-langtype="placeholder"/>
		                                </div>
		                                <div class="input">
											<%-- Current Site --%>
		                                    <label for="ug_user_nation"><span data-lang="FR000019"><%=LangMng.LANG_D("FR000019", session_lang) %></span><span class="red">*</span></label>
<%-- 			                                    <input type="text" id="ug_user_nation" name="ug_user_nation" placeholder="<%=LangMng.LANG_D("FR000019", session_lang) %>" data-lang="FR000019" data-langtype="placeholder"/> --%>
                                               <eaction:select name="ug_user_nation" id="ug_user_nation" init="<%=ConstKey.KEY_YES %>"
                                               	list="<%= nationList %>">
                                               </eaction:select>
		                                </div>
		                                <div class="input">
											<%-- Current language --%>
		                                    <label for="ug_user_lang"><span data-lang="FR000195"><%=LangMng.LANG_D("FR000195", session_lang) %></span><span class="red">*</span></label>
<%-- 			                                    <input type="text" id="ug_user_lang" name="ug_user_lang" placeholder="<%=LangMng.LANG_D("FR000195", session_lang) %>" data-lang="FR000195" data-langtype="placeholder"/> --%>
                                               <eaction:select name="ug_user_lang" id="ug_user_lang" init="<%=ConstKey.KEY_YES %>"
                                               	list="<%=languageList %>" >
                                               </eaction:select>
		                                </div>
		                                <div class="input">
											<%-- Unit --%>
		                                    <label for="ug_unit_cd"><span data-lang="FR000058"><%=LangMng.LANG_D("FR000058", session_lang) %></span><span class="red">*</span></label>
<%-- 			                                    <input type="text" id="ug_unit_cd" name="ug_unit_cd" placeholder="<%=LangMng.LANG_D("FR000058", session_lang) %>" data-lang="FR000058" data-langtype="placeholder"/> --%>
                                               <select name="ug_unit_cd" id="ug_unit_cd" >
                                               	<option value="CC0001">Metric</option>
                                               	<option value="CC0002">Inch</option>
                                              	</select>
		                                </div>
		                                <div class="input">
											<%-- Currency --%>
		                                    <label for="ug_curr_cd"><span data-lang="FR000045"><%=LangMng.LANG_D("FR000045", session_lang) %></span><span class="red">*</span></label>
<%-- 			                                    <input type="text" id="ug_curr_cd" name="ug_curr_cd" placeholder="<%=LangMng.LANG_D("FR000045", session_lang) %>" data-lang="FR000045" data-langtype="placeholder"/> --%>
											<eaction:select name="ug_curr_cd" id="ug_curr_cd" init="<%=ConstKey.KEY_YES %>"
                                               	list="<%= currencyList %>" >
                                               </eaction:select>
		                                </div>
		                                <div class="input">
											<%-- Upgrade Manager --%>
			                                <label for="ug_ug_emp"><span data-lang="FR000101"><%=LangMng.LANG_D("FR000101", session_lang) %></span><span class="red">*</span></label>
                                               <input type="text" id="ug_ug_emp" name="ug_ug_emp" readOnly placeholder="<%=LangMng.LANG_D("FR000225", session_lang) %>" data-lang="FR000225" data-langtype="placeholder"/>
			                            </div>
		                            </div>
		                        </div>
		                        <div class="btn_group">
									<%-- BACK --%>
									<a class="open_user btn01 dirBack"><span class="btn_back" data-lang="FR000237"><%=LangMng.LANG_D("FR000237", session_lang) %></span></a>
									<%-- Join VIP --%>
		                            <a id="reqUpgradeBtn" class="btn02 dirNext"><span class="btn_login" data-lang="FR000102"><%=LangMng.LANG_D("FR000102", session_lang) %></span></a>
		                        </div>
	                            <div class="input agreement">
		                        	<label style="display: inline;">
	                            		<input type="checkbox" id="ug_terms_read" name="ug_terms_read"/>
	                                	<%-- 3자 제공 동의 --%>
	                                	<span class="blue" data-lang="FR000224"><%=LangMng.LANG_D("FR000224", session_lang) %></span>
	                                </label>
	                                <%-- Terms of use and Legal disclaimer, Privacy Statement --%>
	                                <div data-lang="FR000007" style="cursor:pointer" onclick="location.href='/privacy.do'"><%=LangMng.LANG_D("FR000007", session_lang) %></div>
	                            </div>
							</form>
	                    </div>
	                </div>
	            </div>
	        </div>
        </div>	<!-- END div.pop_cont -->
    </div>	<!-- END div.in -->
</div> <!-- END div.login_wrap -->
<!-- END 상단팝업 -->
<!-- 모바일 fullmenu -->
<%if(!"P".equals(deviceType)){ %>
<div class="fullmenu_list">
	<div class="in">
		<a class="btn_close"><img src="<%=PATH_IMG %>/btn_close02.png"
			alt=""></a>
		<h2>
			<img src="<%=PATH_IMG %>/img_m_logo.png" alt="">
		</h2>
		<div class="header_icon">
			<ul>
				<%-- Login --%>
				<li id="area_login"><a class="open_login" href="javascript:void(0);"><span><img
							src="<%=PATH_IMG %>/img_login.png" alt="Login"></span><span data-lang="FR000004"><%=LangMng.LANG_D("FR000004", session_lang) %></span></a></li>
				<%-- Register --%>
				<li id="area_register"><a class="open_register" href="javascript:void(0);"><span><img
							src="<%=PATH_IMG %>/img_register.png" alt="Register"></span><span data-lang="FR000005"><%=LangMng.LANG_D("FR000005", session_lang) %></span></a></li>
				<%-- Mypage --%>
				<li id="area_mypage"><a class="open_user" href="javascript:void(0);"><span><img 
							src="<%=PATH_IMG %>/img_mypage.png" alt="mypage"></span><span data-lang="FR000214"><%=LangMng.LANG_D("FR000214", session_lang) %></span></a></li>
				<li id="area_cart"><a href="javascript:void(0);" onclick="goCart();" class=""> <span><img
							src="<%=PATH_IMG %>/img_cart.png" alt="Cart"><em class="count" id="cartCnt_rgt_wrap"><%=cartCnt %></em></span>
						Cart
				</a></li>
			</ul>
		</div>
		<div class="m_search">
			<div class="input">
				<input type="text" id="mSearchInput" placeholder="Input Keywords">
				<%-- 검색 ( ==> Search) --%>
				<button class="btn_search" data-lang="FR000138" onclick="doMobileKeyWordTopSearch();"><%=LangMng.LANG_D("FR000138", session_lang) %></button>
			</div>
		</div>
	</div>
	<div class="link">
		<div class="in">
			<ul>
                 <%
                 String menu_search_on = "";
                 String menu_grade_on = "";
                 String menu_assem_on = "";
                 if(ConstUrl.SEARCH_URL.equals(menu_url)){
                     menu_search_on = "class=\"on\"";
                 }else if(ConstUrl.GRADES_GUIDE_URL.equals(menu_url)){
                     menu_grade_on = "class=\"on\"";
                 }else if(ConstUrl.ASSEMBLY_URL.equals(menu_url)){
                     menu_assem_on = "class=\"on\"";
                 }
                 %>
				<%-- - Search Center --%>
				<li <%=menu_search_on %>><a href="<%=ConstUrl.SEARCH_URL%>" data-lang="FR000001">- <%=LangMng.LANG_D("FR000001", session_lang) %></a></li>
				<%-- - Grades Guide --%>
				<li <%=menu_grade_on %>><a href="<%=ConstUrl.GRADES_GUIDE_URL%>" data-lang="FR000002">- <%=LangMng.LANG_D("FR000002", session_lang) %></a></li>
				<%-- - My Assembly --%>
				<li <%=menu_assem_on %>><a href="javascript:void(0);" onclick="goPageAssem();" data-lang="FR000003">- <%=LangMng.LANG_D("FR000003", session_lang) %></a></li>
			</ul>
		</div>
	</div>
	<div class="select_area">
		<div class="in">
			<%-- 
			<div class="select">
                <eaction:select name="btnNation" id="btnNation"  list="<%=nationList%>" selected="<%=comm_nation %>" 
                event="onchange=\"javascript:NationChange(this.value);\""></eaction:select>
			</div>
			--%>
			<div class="select">
				 <eaction:select name="btnLang" id="btnLang"
				     list="<%=LangMng.getLangCode()%>" selected="<%=session_lang %>"
				     event="onchange=\"javascript:LangChange(this.value);\""></eaction:select>
			</div>
			<div class="select">
				<select name="mbtnSizeUnit" id="btnSizeUnit" onchange="javascript:SizeUnitChange(this.value);">
					<option value="CC0001">Metric</option>
					<%if(ConstKey.KEY_Y.equals(inch_use_yn)){ %>
					<option value="CC0002">Inch</option>
					<%} %>
				</select>
			</div>
			<div class="select">
				<select  id="mbtnCurr" name="btnCurr" onchange="javascript:CurrencyChange(this.value);">
                     <option value="CC0077">USD</option>
                     <option value="CC0076">KRW</option>
                     <option value="CC0078">EUR</option>
				</select>
			</div>
		</div>
	</div>
</div>
<%} %>
<!-- 모바일 fullmenu -->
<style>
.loading {
    z-index: 9999;
}
input:read-only {
    background: #e6e6e6;
}
input:read-only:focus {
	border: 1px solid #a8b7be !important;
}
.pwd_input > p {
    padding-bottom: 0 !important;
    padding-top: 0 !important;
    border-top: none !important;
    border-bottom: none !important;
}
.input .input_span_txt {
	cursor: default;
}
.open_privacy {
	position: relative;
	display: inline-block;
}
.open_privacy input {
	position: absolute;
	<%if ("P".equals(deviceType)) {%>
	left: 40px;
	<%}%>
	top: 3px;
}
.open_privacy em#join_terms_read-error{
    left: 0;
    top: 35px;
}
.login_wrap #vip .login_cont .agreement em {
	top: 40px;
    left: 0;
}
#vip input[type="checkbox"] {
    margin-right: 5px;
    margin-bottom: 2px;
}
#user .send_tit {
	display: inline-block;
}
.btn_disable {
    background: #e6e6e6 !important;
    border-color: #cfcfcf !important;
    color: #ababab !important;
    cursor: unset !important;
}
/* .input .nationPart { 
     border: 1px solid #a8b7be; 
     text-indent: 10px; 
     height: 32px; 
     line-height: 32px; 
     font-size: 14px; 
     color: #000; 
     border-radius: 5px; 
 } 
 .input .nationPart > p.current { 
     position: unset; 
     padding: 0; 
     border: none; 
     line-height: normal; 
 } 
 #mPage_search_nation { 
 	width: 100%; 
 } */
</style>
<!-- <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" -->
<!--     async defer> -->
<!-- </script> -->
<script type="text/javascript">
doTopButtonShowHide();
// var login_uId = USERINFO.user_id;
var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
var nationList_open = $('#mypage-form .nationPart .current.on').siblings(".nationForm")	// Mypage 국가 selectbox 외 영역 클릭시 slide처리를 위함
var nt_email_pair = [];		// 국가 : 담당자이메일
$(".open_privacy > span").on('click', function(){
   	showPrivacyPop();
});

var reCAPCHA_flag = false;	// reCAPCHA 검증값 
function suc_func() {
	reCAPCHA_flag = true;
}

function exp_func() {
	reCAPCHA_flag = false;
}

function doRightWrapLangChange() {
	registerValidate();
}

function doMobileKeyWordTopSearch(){
    var auto_input_text = $("#mSearchInput").val();
    if(auto_input_text != ""){
        $("#autoSearchForm #auto_input_text").val(auto_input_text);
        $("#autoSearchForm #ma_cd").val("");
        $("#autoSearchForm #sa_cd").val("");
        $("#autoSearchForm #ig_cd").val("");
        $("#autoSearchForm #matnr").val("");
        $("#autoSearchForm #auto_mode").val("M");
        autoSearchForm.submit();
    }
}

// mypage > nation init
function nationSrch() {
	$('#mPage_search_nation').val("");
	$('#mypage-form .empty').css("display", "none");	// empty 초기 표시 (none)
	$('.nationResult li').css("display", "block");		// list 초기표시 (block)	
}

// [X] 클릭
$('.pop_close, .login_tab > ul > li > a').on('click', function() {
	// 입력값 초기화
	$('div#login .input input, div#register .input input, div#password .input input').val("");	// input 값 초기화
	$("#login em, #register em, #withdrawal-form em, #pwd-user_id-form em, #upgrade-form em, #user-pwd-form em").remove();	// input 유효성메세지 초기화
	$('#vip #upgrade-form .state-error:last-child').removeClass('state-error');	// vip 담당자 유효성메세지 초기화
	
})

// mypage 국가 select 검색
$('#mPage_search_nation').on('keyup', function() {
	var nation_list = $('.nationResult li');	// 국가 셀렉트박스 리스트 
	$(nation_list).css("display", "block");		// 국가 셀렉트박스 리스트 표시상태(초기 : 표시)
    $('#mypage-form .empty').css("display", "block");	// empty 표시상태(초기 : 표시)

	for(var i = 0; i < $(nation_list).length; i++) {
	    var ele = $(nation_list)[i]
	    if ($(ele).text().toUpperCase().includes($(this).val().toUpperCase())) {
	    	// 검색어 걸림
    	 	//console.log("TRUE ==> " + $(ele).text() + " : " + $(ele).text().includes($(this).val()))
		    $('#mypage-form .empty').css("display", "none");	// empty 표시상태(검색된 국가 있을때 표시x)
	    } else {
	    	// 검색 결과 없음
	    	//console.log("FALSE ==> " + $(ele).text() + " : " + $(ele).text().includes($(this).val()))
		    $(ele).css("display", "none");
	    }
	}
});

// mypage 국가 검색 select 선택
$(document).on("click","#mypage-form .nationResult > li > a",function() {

// $('#mypage-form .nationResult > li > a').on('click', function() {
	var n_cd = $(this).data("schntn");
//	console.log("n_cd ==> " + n_cd)
	// mypage>국가 div 텍스트
	$('.nationPart .current').text($('.nationResult li a[data-schntn="' + n_cd + '"]').text());
	// mPage_user_nation
	$('#mPage_user_nation').val(n_cd);
	nationSrch();	 // mypage > nation init
	$(".nationPart .current").click()	// 닫기
});

// VIP req > 국가 change시 담당자
$(document).on("change",'#ug_user_nation',function() {
	var ug_sel_nation = $('#ug_user_nation').val();
// 	console.log(nt_email_pair)
	if (ug_sel_nation == "") {
    	$('#ug_ug_emp').val("")
	} else {
		nt_email_pair.forEach(function(index, value, array) {
		    var nt = index;
			if (Object.keys(nt) == $('#ug_user_nation').val()) {
				$('#ug_ug_emp').val(nt[$('#ug_user_nation').val()])
			}
		})
	}
})

$(document).ready(function() {
	// 권한그룹에 따라 Mypage타이틀 옆에 PRO 뱃지(0003이하)
	if (USERINFO.user_grp == "<%= IPropertyKey.UG0001 %>"
		|| USERINFO.user_grp == "<%= IPropertyKey.UG0002 %>"
		|| USERINFO.user_grp == "<%= IPropertyKey.UG0003 %>"
		|| USERINFO.user_grp == "<%= IPropertyKey.UG0007 %>") {
		if (USERINFO.user_grp != "<%= IPropertyKey.UG0007 %>") {
			$('#user .send_tit').after('<img src="<%= PATH_IMG %>/bg_customer.png" />')
		}
		$('.open_vip').addClass("btn_disable");
		$('.open_vip').removeClass("active");
	}
	// mypage>국가 div 텍스트
	$('.nationPart .current').text($('.nationResult li a[data-schntn="' + USERINFO.user_nation + '"]').text());
	$('.nationPart .current').attr("title", $('.nationResult li a[data-schntn="' + USERINFO.user_nation + '"]').text());
	
	// mypage>국가  select 외부 클릭시 slidetoggle
	$(document).on("mouseup",nationList_open, function(e){
// 		console.log($(e.target)[0])
// 		console.log($(e.target).prop("tagName") == "DIV")
// 		console.log($(e.target)[0] == "div" && $(e.target).attr("class").includes("current on"))
		if ($(e.target).prop("tagName") == "DIV" && $(e.target).attr("class").includes("current on")) {
			return;			
		} else {
		    var autoLayer2 = $('#nationResultSelect');
		    if(autoLayer2.has(e.target).length == 0 && $(".nationPart .current").attr("class").includes("on")){
		    	$(".nationPart .current").toggleClass("on");
		        $(".nationForm").slideToggle();
		        nationSrch();	// 초기화 : 검색어+empty(hide)+list(show)
			}
		}
	});	
	// 국가코드 : ug_emp mail 배열
	<% for(CodeInfo info : nationList) {%>
		nt_email_pair.push({"<%=info.getCode()%>" : "<%=info.getEmail_to()%>"})
	<%}%>
})

	// password masking on/off
	$(document).on('click', '.pwdHide.show', function(e){
		var eventBtn = $(event.target).parent();
		var pwdIp = eventBtn.parent().parent('div').find('input')
		if(pwdIp.attr('type') == "text") {
			pwdIp.attr('type','password')
		} else {
			pwdIp.attr('type','text')
		}
		eventBtn.siblings().toggleClass('show');
	    eventBtn.toggleClass('show');
	});
	
	// 로그인 패스워드 입력시 로그인실패 msg 제거
	$('input[name="login_user_pwd"]').on('keydown', function() {
		$('.red-warn').remove();
	});

	// 회원가입 [Create Account] 클릭
	$("#doRegister").bind("click", function(){
		$("#register-form").submit();
	});
	
	<%-- 사용안함 --%>
	// 회원가입:이메일 인증 확인 완료
	$("#joinAuthConf").bind("click", function(){
		doCertEmailAuth("join");
	});

	// 로그인 [Login] 클릭
	$("#doLogin").bind("click", function(){
		$("#login-form").submit();
	});

	// 로그인 [Login] 클릭
	$("#getUserbyUserIdBtn").bind("click", function(){
// 		getUserByUserId();
		$("#pwd-user_id-form").submit();
		
	});

	// 로그아웃 [로그아웃] 클릭
	$("#doLogoutBtn").bind("click", function(){
		doLogout();
	});
	
	// [회원정보 변경] 클릭
	$('#updMypage').bind("click", function(){
		doUpdateMypage();
	});
	
	// [회원정보 변경] 클릭
	$('#doWithdrawalBtn').bind("click", function(){
		doUserWithdrwal();
	});
	
	// 회원[비밀번호 변경] 클릭 
	$('#resetPwdUserBtn').bind("click", function(){
        $(".login_wrap").fadeIn();
        $(".login_wrap .pop_cont > div").hide();
		$('#user_reset_password').show();
 	});
	
	// 로그인 유저 비밀번호 변경
	$('#doResetUserPwdBtn').bind("click", function() {
		$('#user-pwd-form').submit();
	})
	
	// 비밀번호 재설정모달 [NEXT] 클릭
	$('#doResetPwdBtn').bind("click", function(){
		$("#reset-pwd-form").submit();
	});
	
	// 비밀번호변경:이메일 인증 확인 완료
	$("#resetPwdAuth").bind("click", function(){
		doCertEmailAuth(USERINFO.user_id);
	});
	
	// 비밀번호찾기:이메일 인증 확인 완료
	$("#emailAuthConf").bind("click", function(){
		var req_uId = $('#user_id_for_find_pass').val()
		doCertEmailAuth(req_uId);
	});
	
	//	엔터키로 로그인버튼 클릭하기
	$("input[name=login_user_pwd]").keydown(function (key) {
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
        	$("#doLogin").click();
        } 
    });
	
	// 이메일 인증 메일 재발송
	$('#confirm .resendAuthMail').bind("click", function() {
		if (USERINFO.user_id == "") {
			doResendAuthMail("join");
		} else {
			doResendAuthMail("reset_pass");
		}
	});
	
	// 이메일 인증 메일 재발송
	$('#confirm_for_find_pass .resendAuthMail').bind("click", function() {
		var req_uId = $('#user_id_for_find_pass').val();
		doResendAuthMail(req_uId);
	});

	// [Join VIP] 승급요청 클릭
	$("#reqUpgradeBtn").bind("click", function(){
// 		doReqUpgrade();
		$("#upgrade-form").submit();
	});
	
	// [BACK]: 로그인>Forgot Password  
	$('#password .dirBack').bind("click", function() {
		$('#rspwd_user_id').val("");	// 입력값 지우기
		$('#rspwd_user_id').siblings('em').remove()	// validate msg 지우기
	})
	
	// [BACK]: My Page> VIP, Password, withdrawal  
	$('#vip .dirBack, #withdrawal .dirBack, #user_reset_password .dirBack').bind("click", function() {
		$('#vip #upgrade-form em').remove();	// vip 담당자 유효성메세지 초기화		
		$('#vip #upgrade-form input[type="checkbox"]').prop('checked',false)

		$('#user_old_pwd, #user_new_pwd, #conf_user_new_pwd').val("");
		$('#user_reset_password em').remove();
		
		$('#withdrawal input[type="checkbox"]').prop('checked',false)
		$('#withdrawal em').remove();
	})
	
	// 저장하지 않은 input 값이 남아있는 것 처리 (USERINFO의 값을 표시해야함)
	$(".open_user").click(function() {
		// mypage
		$('#mPage_user_id').val(USERINFO.user_id);
		$('#mPage_user_name').val(USERINFO.user_name);
		$('#mPage_user_company').val(USERINFO.user_company);
		$('#mPage_user_com_mail').val(USERINFO.user_com_mail);
		$('#mPage_user_mobile').val(USERINFO.user_mobile);
		$('#mPage_user_nation').val(USERINFO.user_nation);
		$('#mPage_user_lang').val(USERINFO.user_lang);
		$('#mPage_unit_cd').val(USERINFO.unit_cd);
		$('#mPage_curr_cd').val(USERINFO.curr_cd);
		
		// joinvip
		$('#ug_user_name').val(USERINFO.user_name);
		$('#ug_user_company').val(USERINFO.user_company);
		$('#ug_user_com_mail').val(USERINFO.user_com_mail);
		$('#ug_user_com_tel').val(USERINFO.user_com_tel);
		$('#ug_user_com_addr').val(USERINFO.user_com_addr);
		$('#ug_user_mobile').val(USERINFO.user_mobile);
		$('#ug_user_nation').val(USERINFO.user_nation);
		$('#ug_user_lang').val(USERINFO.user_lang);
		$('#ug_unit_cd').val(USERINFO.unit_cd);
		$('#ug_curr_cd').val(USERINFO.curr_cd);

		$('.nationPart .current').text($('.nationResult li a[data-schntn="' + USERINFO.user_nation + '"]').text());

		// mypage 국가 select close
	 	if ($('#mypage-form .nationPart .current').attr('class').includes("on")) $('#mypage-form .nationPart .current').click();
		nationSrch();	 // mypage > nation init
	})
	
	// [Join VIP]: Save and Move doUpdateMypage()
    $(".open_vip").click(function(){
    	/**
    	 * str.includes('text') : str 에 text가 포함 여부
    	 * .active false일 경우 PRO회원. open_vip 버튼을 비활성화 한다 
    	*/
//     	console.log($(this).attr('class').includes('active'))
    	if (!$('.open_vip').attr('class').includes('active')) {
			return;
    	}
    	// 회원정보 수정 후 이동하려할때 update 먼저수행
    	if (USERINFO.user_name     != $('#mPage_user_name').val() ||
		    USERINFO.user_company  != $('#mPage_user_company').val() ||
		    USERINFO.user_com_mail != $('#mPage_user_com_mail').val() ||
		    USERINFO.user_com_tel  != $('#mPage_user_com_tel').val() ||
		    USERINFO.user_com_addr != $('#mPage_user_com_addr').val() ||
		    USERINFO.user_mobile   != $('#mPage_user_mobile').val() ||
		    USERINFO.user_nation   != $('#mPage_user_nation').val() ||
		    USERINFO.user_lang     != $('#mPage_user_lang').val() ||
		    USERINFO.uit_cd        != $('#mPage_uit_cd').val() ||
		    USERINFO.curr_cd       != $('#mPage_curr_cd').val()) {
			<%-- 회원정보 변경 후 승급요청 화면으로 이동합니다. --%>
<%--     	    if(confirm('<%=LangMng.LANG_D("FR000313", session_lang) %>')) { --%>
    	    if(confirm(LANG_SET.FR000313)) {
    	    	var param = {
    	    			actionID		: "<%=UserConstKey.ACTION_EDIT_USER_INFO_OK%>",
    	    			user_id			: USERINFO.user_id,
    	    			user_name		: $("#mPage_user_name").val(),
    	    			user_company	: $("#mPage_user_company").val(),
    	    			user_com_mail	: $("#mPage_user_com_mail").val(),
    	    			user_com_tel	: $("#mPage_user_com_tel").val(),
    	    			user_com_addr	: $("#mPage_user_com_addr").val(),
    	    			user_mobile		: $("#mPage_user_mobile").val(),
    	    			user_nation		: $("#mPage_user_nation").val(),
    	    			user_lang		: $("#mPage_user_lang").val(),
    	    			unit_cd			: $("#mPage_unit_cd").val(),
    	    			curr_cd			: $("#mPage_curr_cd").val(),
    	    			use_yn			: USERINFO.use_yn
    			};
    	       	$.ajax({
    	       		type: "POST",
    	       		url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
    		       	data: param,
    		       	dataType: "json",
    		       	async: true,
    		       	cache: false,
    				// Ajax 통신 전 로딩표시
    	            beforeSend:function(){
    	            	$('#loadbar').show();
    	            }, success: function(e) {
    	//	       		console.log(e);
    		       		if(e.resCode == "0") {
    		       			USERINFO = e.resData;

	    	       			// 회원이름 셋팅 ([YJI] 21/09/24 )
    	       			    $("#area_user_name .user_name").text(USERINFO.user_name == "" ? USERINFO.user_id : USERINFO.user_name)
    	       		    	// mypage
    	       				$('#mPage_user_id').val(USERINFO.user_id);
    	       				$('#mPage_user_name').val(USERINFO.user_name);
    	       				$('#mPage_user_company').val(USERINFO.user_company);
    	       				$('#mPage_user_com_mail').val(USERINFO.user_com_mail);
    	       				$('#mPage_user_com_tel').val(USERINFO.user_com_tel);
    	       				$('#mPage_user_com_addr').val(USERINFO.user_com_addr);
    	       				$('#mPage_user_mobile').val(USERINFO.user_mobile);
    	       				$('#mPage_user_nation').val(USERINFO.user_nation);
    	       				$('#mPage_search_nation').text($('#mypage-form .nationResult a[data-schntn="'+ USERINFO.user_nation +'"]').text());
    	       				$('.current').attr("title", $('#mypage-form .nationResult a[data-schntn="'+ USERINFO.user_nation +'"]').text());
    	       				
    	       				$('#mPage_user_lang').val(USERINFO.user_lang);
    	       				$('#mPage_unit_cd').val(USERINFO.unit_cd);
    	       				$('#mPage_curr_cd').val(USERINFO.curr_cd);
    	       				
    	       				// joinvip
    	       				$('#ug_user_name').val(USERINFO.user_name);
    	       				$('#ug_user_company').val(USERINFO.user_company);
    	       				$('#ug_user_com_mail').val(USERINFO.user_com_mail);
    	       				$('#ug_user_com_tel').val(USERINFO.user_com_tel);
    	       				$('#ug_user_com_addr').val(USERINFO.user_com_addr);
    	       				$('#ug_user_mobile').val(USERINFO.user_mobile);
    	       				$('#ug_user_nation').val(USERINFO.user_nation);
    	       				$('#ug_user_lang').val(USERINFO.user_lang);
    	       				$('#ug_unit_cd').val(USERINFO.unit_cd);
    	       				$('#ug_curr_cd').val(USERINFO.curr_cd);
    	       				$('#ug_ug_emp').val(USERINFO.ug_emp);
    	       				
    	       				// 회원정보수정처리완료 --> 이동
    	       				$(".login_wrap").fadeIn();
    					    if(isMobile){
    					        $(".login_wrap").css('position', '');
    					    }
    					    $(".login_wrap .pop_cont > div").hide();
    					    $("#vip").show();
    	       			} else {
    	       				<%-- 요청하신 처리가 실패했습니다 --%>
    	<%-- 				alert('<%=LangMng.LANG_D("FR000174", session_lang) %>'); --%>
    	           			alert( LANG_SET.FR000174 );
    	       			}
    		       	}, error: function() {
    		       	},
    		        // Ajax 통신 완료 후 로딩숨김
    		        complete:function(){
    		           $('#loadbar').fadeOut();
    		        } 
                 });
    	     }
    	} else {
    		// 회원정보 수정하지 않음
			$(".login_wrap").fadeIn();
		    if(isMobile){
		        $(".login_wrap").css('position', '');
		    }
		    $(".login_wrap .pop_cont > div").hide();
		    $("#vip").show();
		}
    	
    });
	
	
	// 회원가입 유효성
	var errorClass = 'invalid red';
	var errorElement = 'em';
   
	// 비밀번호 양식체크
	$.validator.addMethod("regx", function(value, element, regexpr) {
		var pw = value;
	   	var tf = true;	// return 값
		//숫자있는지 확인하는 정규식
		var pattern1 = /[0-9]/;
		//영문 있는지 확인하는 정규식
	    var pattern2 = /[a-zA-Z]/;
		//특수문자 있는지 확인하는 정규식
	    var pattern3 = /[~!@\#$%<>^&*()?_+=]/;
		
	   	if (!pattern1.test(pw) || !pattern2.test(pw) || !pattern3.test(pw)) {
	   		tf = false
	   	}
	    return tf;
	});
	
	// 상단언어 변경시 validate message 다국어 재설정
    function registerValidate(){
    	// register-form
        $("#join_user_id").rules('add', {
            messages : { email : LANG_SET.FR000023, required: LANG_SET.FR000013,  }
        });
        $("#join_user_pwd").rules('add', {
            messages: { required: LANG_SET.FR000014, rangelength: LANG_SET.FR000189, regx: LANG_SET.FR000190, }
        });
        $("#conf_join_user_pwd").rules('add', {
            messages: { required: LANG_SET.FR000025, equalTo: LANG_SET.FR000221, }
        });
        $("#join_terms_read").rules('add', {
            messages: { required: LANG_SET.FR000199, }
        });
        
    	// login-form
        $("#login_user_id").rules('add', {
            messages: { email : LANG_SET.FR000023, required: LANG_SET.FR000013, }
        });
        $("#login_user_pwd").rules('add', {
            messages: { required: LANG_SET.FR000014, }
        });
        
    	// reset-pwd-form
        $("#reset_user_pwd").rules('add', {
            messages: { required: LANG_SET.FR000014, rangelength: LANG_SET.FR000189, regx: LANG_SET.FR000190, }
        });
        $("#conf_reset_user_pwd").rules('add', {
            messages: { required: LANG_SET.FR000025, equalTo: LANG_SET.FR000221, }
        });
        
    	// pwd-user_id-form
        $("#rspwd_user_id").rules('add', {
            messages: { email : LANG_SET.FR000023, required: LANG_SET.FR000013, }
        });
    	
    	// user-pwd-form
        $("#user_old_pwd").rules('add', {
            messages: { required: LANG_SET.FR000014, }
        });
        $("#user_new_pwd").rules('add', {
            messages: { required: LANG_SET.FR000014, rangelength: LANG_SET.FR000189, regx: LANG_SET.FR000190, }
        });
        $("#conf_user_new_pwd").rules('add', {
            messages: { required: LANG_SET.FR000025, equalTo: LANG_SET.FR000221, }
        });
    	
    	// upgrade-form
        $("#ug_user_name").rules('add', {
            messages: { required: LANG_SET.FR000024, }
        });
        $("#ug_user_company").rules('add', {
            messages: { required: LANG_SET.FR000201, }
        });
        $("#ug_user_com_mail").rules('add', {
            messages: { required: LANG_SET.FR000202, email : LANG_SET.FR000023, }
        });

        
        $("#ug_user_com_tel").rules('add', {
            messages: { required: LANG_SET.FR000251, }
        });
        $("#ug_user_com_addr").rules('add', {
            messages: { required: LANG_SET.FR000252, }
        });
        $("#ug_user_mobile").rules('add', {
            messages: { required: LANG_SET.FR000250, }
        });
        $("#ug_user_nation").rules('add', {
            messages: { required: LANG_SET.FR000026, }
        });
        $("#ug_user_lang").rules('add', {
            messages: { required: LANG_SET.FR000253, }
        });
        $("#ug_unit_cd").rules('add', {
            messages: { required: LANG_SET.FR000254, }
        });
        $("#ug_curr_cd").rules('add', {
            messages: { required: LANG_SET.FR000255, }
        });
        
        
        $("#ug_ug_emp").rules('add', {
            messages: { required: LANG_SET.FR000225, }
        });
        $("#ug_terms_read").rules('add', {
            messages: { required: LANG_SET.FR000224, }
        });
    	
		// 주문서 화면일 경우
		if ($("#req-order-form").length != 0) {
	        $("#order_agree").rules('add', {
	            messages: { required: LANG_SET.FR000179, }
	        });
		}
    }
	
	// 회원가입 Validation
	$("#register-form").validate({
		errorClass		: errorClass,
		errorElement	: errorElement,
		highlight: function(element) {
			$(element).parent().removeClass('state-success').addClass("state-error");
			$(element).removeClass('valid');
		},
		unhighlight: function(element) {
			$(element).parent().removeClass("state-error").addClass('state-success');
			$(element).addClass('valid');
		},
		// Rules for form validation
		rules : {
			join_user_id : {
				email : true,
				required: true,
			},
			join_user_pwd : {
				required: true,
				rangelength: [8, 16],
				regx: true
			},
			conf_join_user_pwd : {
				required : true,
				equalTo: '#join_user_pwd',
			},
			join_terms_read: {
				required: true,
			}
		},
		// Messages for form validation
		messages : {
			join_user_id : {
				<%-- email@a.com please --%>
				<%-- please write email --%>
<%-- 				email : '<%=LangMng.LANG_D("FR000023", session_lang) %>', --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000013", session_lang) %>', --%>
				email : LANG_SET.FR000023,
				required: LANG_SET.FR000013,
			},
			join_user_pwd : {
				<%-- 비밀번호을 입력해주세요. --%>
				<%-- 8~16자리를 입력해주세요. --%>
				<%-- 특수문자를 포함해주세요. --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000014", session_lang) %>', --%>
<%-- 				rangelength: '<%=LangMng.LANG_D("FR000189", session_lang) %>', --%>
<%-- 				regx: '<%=LangMng.LANG_D("FR000190", session_lang) %>' --%>
				required: LANG_SET.FR000014,
				rangelength: LANG_SET.FR000189,
				regx: LANG_SET.FR000190
			},
			conf_join_user_pwd: {
				<%-- 비밀번호를 재입력해주세요. --%>
				<%-- 비밀번호가 일치하지 않습니다. --%>
<%-- 				required : '<%=LangMng.LANG_D("FR000025", session_lang) %>', --%>
<%-- 				equalTo: '<%=LangMng.LANG_D("FR000221", session_lang) %>'  --%>
				required: LANG_SET.FR000025,
				equalTo: LANG_SET.FR000221 
			},
			join_terms_read: {
	       		<%-- 모두 체크해주세요. --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000199", session_lang) %>', --%>
				required: LANG_SET.FR000199,
			}
		},
		submitHandler: function() {
			if(reCAPCHA_flag == false) {
				console.log("verify reCAPCHA")
				// reCAPCHA로 포커싱
				$('.rc-anchor-checkbox-label').click()
				return;
			}
		
			var param = {
					actionID	:"<%=UserConstKey.ACTION_USER_JOIN_OK%>",
					user_id		: $('#join_user_id').val(),
					user_pwd	: $('#join_user_pwd').val(),
					user_nation	: $('#btnNation').val(),
					user_lang	: $('#btnLang').val(),
					confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
			};
			$.ajax({
				type: "POST",
				url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
				data: param,
                dataType: "json",
                async: true,
                cache: false,
                // Ajax 통신 전 로딩표시
                beforeSend:function(){
               	   $('#loadbar').show();
               	}, success: function (e) {
//                 	console.log(e);
                	if (e.resCode == "0") {
                		<%-- 이메일 인증 메일을 발송하였습니다. --%>
<%--                 		alert('<%=LangMng.LANG_D("FR000186", session_lang) %>'); --%>
						alert( LANG_SET.FR000186 );
                		$('#register').css('display','none');
                		$('#confirm').css('display','block');
                	} else if (e.resCode == "1") {
                		<%-- 이메일 발송 중 오류가 발생했습니다. --%>
<%--                 		alert('<%=LangMng.LANG_D("FR000188", session_lang) %>'); --%>
						alert( LANG_SET.FR000188 );
                	} else if (e.resCode == "2") {
                		<%-- 이미 존재하는 이메일입니다. --%>
<%--                 		alert('<%=LangMng.LANG_D("FR000215", session_lang) %>'); --%>
						alert( LANG_SET.FR000215 );
                	}else{
                		alert(e.resMsg);
					}
                }, error: function () {
                },
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
            });
		},
	});
	// 회원가입 END----------------------------
	
	// 이메일 인증 확인 완료 (join + reset password)
	function doCertEmailAuth(login_type) {	// login_type == "join" --> pwd 서비스 X
		var service = login_type;
//		console.log("service ==> " + service);
		var user_id = "";	// join : find_pass : reset_pass
		if (USERINFO.user_id == "") {
			if (login_type == "join") {
				user_id = $("#join_user_id").val()
			} else {
				service = "find_pass"
				user_id = login_type;
			} 
		} else {
			service = "reset_pass"
			user_id = USERINFO.user_id;
		}
//		console.log(user_id)
		var param = {
			actionID	:"<%=UserConstKey.ACTION_USER_MAIL_AUTH_OK%>",
		    user_id 	: user_id
		};
		$.ajax({
			type: "POST",
            url: "<%=UserConstUrl.USER_EMAIL_AUTH %>?v=" + (new Date().getTime()),
            data: param,
            dataType: "json",
            async: true,
            cache: false,
        	// Ajax 통신 전 로딩표시
            beforeSend:function(){
         	   $('#loadbar').show();
        	}, success: function (e) {
//             	console.log(e);
            	if (e.resCode == "0") {
            		<%-- 인증 완료되었습니다. --%>
<%--             		alert('<%=LangMng.LANG_D("FR000191", session_lang) %>'); --%>
            		alert( LANG_SET.FR000191 );
            		$(".login_wrap").fadeIn();
			        $(".login_wrap .pop_cont > div").hide();
					if (service != "join") {	// "user" --> login OK
				        $("#reset_password").show();
					} else { 
				        $("#join").show();
					}
            	}else if (e.resCode == "1") {
            		<%-- 이메일 인증 중 오류가 발생했습니다. --%>
<%--             		alert('<%=LangMng.LANG_D("FR000188", session_lang) %>'); --%>
            		alert( LANG_SET.FR000188 );
            	}else if (e.resCode == "2") {
            		<%-- 이메일 인증을 완료하여 주세요. --%>
<%--             		alert('<%=LangMng.LANG_D("FR000187", session_lang) %>'); --%>
            		alert( LANG_SET.FR000187 );
				} else {
            		alert(e.resMsg);
				}
            }, error: function () {
            },
            // Ajax 통신 완료 후 로딩숨김
            complete:function(){
              $('#loadbar').fadeOut();
            } 
        });
		
	};
	// 이메일 인증 확인 완료 END --------------------

	// 이메일 인증 메일 재발송
	function doResendAuthMail(service_type) {
		var service = service_type;
//		console.log(service)
		var user_id = "";
		var param = "";
		if (USERINFO.user_id == "") {
			if(service == "join") {
				user_id = $("#join_user_id").val()
				param = {
					actionID	:"<%=UserConstKey.ACTION_USER_RESEND_AUTH_MAIL_OK%>",
				    user_id 	: user_id,
					confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
				};
			} else {
				// 비밀번호 찾기
				service = "find_pass"
				user_id = service_type;
				param = {
					actionID	:"<%=UserConstKey.ACTION_USER_RESEND_AUTH_MAIL_OK%>",
				    user_id 	: user_id,
					confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
					use_yn		: 'Y'
				};
			}
		} else {
			user_id = USERINFO.user_id;
			param = {
				actionID	:"<%=UserConstKey.ACTION_USER_RESEND_AUTH_MAIL_OK%>",
			    user_id 	: user_id,
				confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
				use_yn		: 'Y'
			};
		}
		$.ajax({
			type: "POST",
	        url: "<%=UserConstUrl.USER_EMAIL_AUTH %>?v=" + (new Date().getTime()),
			data: param,
			dataType: "json",
			async: true,
			cache: false,
			// Ajax 통신 전 로딩표시
            beforeSend:function(){
         	   $('#loadbar').show();
        	}, success: function (e) {
//				console.log(e);
            	if (e.resCode == "0") {
            		<%-- 이메일 인증 메일을 발송하였습니다. --%>
<%--             		alert('<%=LangMng.LANG_D("FR000186", session_lang) %>'); --%>
            		alert( LANG_SET.FR000186 );
            	}else{
            		<%-- 이메일 발송 중 오류가 발생했습니다. --%>
<%--             		alert('<%=LangMng.LANG_D("FR000188", session_lang) %>'); --%>
            		alert( LANG_SET.FR000188 );
				}
            }, error: function () {
            },
            // Ajax 통신 완료 후 로딩숨김
            complete:function(){
              $('#loadbar').fadeOut();
            } 
        });
	}; 
	// 이메일 인증 메일 재발송 END ------------------
    
    // 로그인 Validation
    $("#login-form").validate({
        errorClass		: errorClass,
        errorElement	: errorElement,
        highlight: function(element) {
            $(element).parent().removeClass('state-success').addClass("state-error");
            $(element).removeClass('valid');
        },
        unhighlight: function(element) {
            $(element).parent().removeClass("state-error").addClass('state-success');
            $(element).addClass('valid');
        },
        // Rules for form validation
        rules : {
        	login_user_id : {
                email : true,
                required: true,
            },
            login_user_pwd : {
            	required: true,
            },
        },
        // Messages for form validation
        messages : {
        	login_user_id : {
				<%-- email@a.com please --%>
				<%-- please write email --%>
<%-- 				email : '<%=LangMng.LANG_D("FR000023", session_lang) %>', --%>
<%--                 required: '<%=LangMng.LANG_D("FR000013", session_lang) %>', --%>
				email : LANG_SET.FR000023,
                required: LANG_SET.FR000013,
            },
            <%-- pw please --%>
			<%-- required: '<%=LangMng.LANG_D("FR000014", session_lang) %>', --%>
            login_user_pwd : {
            	required: LANG_SET.FR000014,
            }
        },
        submitHandler: function() {
        	var param = {
       			actionID	: "<%=LoginConstKey.LOGIN_OK%>",
           		user_id		: $('#login_user_id').val(),
           		user_pwd	: $('#login_user_pwd').val()	
       		};
			$.ajax({
	       		type: "POST",
	       		url: "<%=ConstUrl.LOGIN_URL%>?v=" + (new Date().getTime()),
	       		data: param,
	       		dataType: "json",
	       		async: true,
	       		cache: false,
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
             	   $('#loadbar').show();
            	}, success: function(e) {
//  	       			console.log(e);
					// Log : Login시						
	       			if(e.resCode == "0") {
                        USERINFO = JSON.parse(JSON.stringify(e.data));
                        USERAUTH = JSON.parse(JSON.stringify(e.json_auth));
	       				// location.reload();
	       				// 권한그룹에 따라 Mypage타이틀 옆에 PRO 뱃지(0003이하)
       					if (USERINFO.user_grp == "<%= IPropertyKey.UG0001 %>"
								|| USERINFO.user_grp == "<%= IPropertyKey.UG0002 %>"
								|| USERINFO.user_grp == "<%= IPropertyKey.UG0003 %>"
								|| USERINFO.user_grp == "<%= IPropertyKey.UG0007 %>") {
							if (USERINFO.user_grp != "<%= IPropertyKey.UG0007 %>") {
								$('#user .send_tit').after('<img src="<%= PATH_IMG %>/bg_customer.png" />')
       						}
       						$('.open_vip').removeClass("active");
       						$('.open_vip').addClass("btn_disable");
       					}
	       				doAfterLogin(e, false);
	       				doTopButtonShowHide();
	       			} else if(e.resCode == "1") {
						$('#login_user_pwd').parent('.input').append('<em class="red red-warn"><%=LangMng.LANG_D("FR000014", session_lang) %></em>');
	       			} else if(e.resCode == "2") {
	       				<%-- 존재하지않는 ID입니다 --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000015", session_lang) %>'); --%>
						alert( LANG_SET.FR000015 );
	       			} else if(e.resCode == "3") {
	       				<%-- 이메일 인증을 진행하여 주세요. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000187", session_lang) %>'); --%>
	            		alert( LANG_SET.FR000187 );
	       			} else {
	       				alert(e.resMsg);
	       			}
	       		}, error: function() {
	       		},
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
			});
		},
	});
// 로그인 END----------------------------

// 로그아웃
	function doLogout(){
		
       	var param = {
       			actionID	: "<%=LoginConstKey.LOGOUT_OK %>",
           		user_id		: USERINFO.user_id
       		};
       	$.ajax({
       		type: "POST",
       		url: "<%=ConstUrl.LOGIN_URL%>?v=" + (new Date().getTime()),
       		data: param,
       		dataType: "json",
       		async: true,
       		cache: false,
			// Ajax 통신 전 로딩표시
               beforeSend:function(){
            	   $('#loadbar').show();
           	}, success: function(e) {
//        			console.log(e);
       			if(e.resCode == "0") {
   			       location.href = "<%=ConstUrl.APP_MAIN_URL%>";
       			} else {
       				alert(e.resMsg);
       			}
       		}, error: function() {
       		},
             // Ajax 통신 완료 후 로딩숨김
             complete:function(){
               $('#loadbar').fadeOut();
             } 
       	});
	}
	// 로그아웃 END --------------------------
	
	// 회원정보 변경
	function doUpdateMypage() {
		<%-- 회원정보를 변경하시겠습니까? --%>
		if(confirm(`<%=LangMng.LANG_D("FR000112", session_lang) %>`)){
	       	var param = {
	       			actionID		: "<%=UserConstKey.ACTION_EDIT_USER_INFO_OK%>",
	           		user_id			: USERINFO.user_id,
	           		user_name		: $("#mPage_user_name").val(),
	           		user_company	: $("#mPage_user_company").val(),
	           		user_com_mail	: $("#mPage_user_com_mail").val(),
	    			user_com_tel	: $("#mPage_user_com_tel").val(),
	    			user_com_addr	: $("#mPage_user_com_addr").val(),
	           		user_mobile		: $("#mPage_user_mobile").val(),
	           		user_nation		: $("#mPage_user_nation").val(),
	           		user_lang		: $("#mPage_user_lang option:selected").val(),
	           		unit_cd			: $("#mPage_unit_cd option:selected").val(),
	           		curr_cd			: $("#mPage_curr_cd option:selected").val(),
	           		use_yn			: USERINFO.use_yn
	       		};

	       	$.ajax({
	       		type: "POST",
	       		url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
	       		data: param,
	       		dataType: "json",
	       		async: true,
	       		cache: false,
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
             	   $('#loadbar').show();
            	}, success: function(e) {
//	       			console.log(e);
	       			if(e.resCode == "0") {
	       				<%--
	       					회원 정보를 수정했습니다. 114
	       					비밀번호를 변경하였습니다. 193 
	       				 --%>
	       				alert(e.resMsg);
	   			        location.reload();
	       			} else {
	       				<%-- 요청하신 처리가 실패했습니다 --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000174", session_lang) %>'); --%>
	            		alert( LANG_SET.FR000174 );
	       			}
	       		}, error: function() {
	       		},
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
	       	});
		}
	}
	// 회원정보 변경 END ----------------------
	
	// 회원탈퇴    
	function doUserWithdrwal() {
   		$("#withdrawal-form em").remove()
       	if($('input[name="withdrawal_1').is(":checked") && 
       			$('input[name="withdrawal_2').is(":checked") && 
       			$('input[name="withdrawal_3').is(":checked")) {
       		// 탈퇴 안내 숙지사항 모두 체크했을 때만
       		
			// Log : 회원탈퇴
			doLog("EC0007", "", "");
     			
        	var param = {
       			actionID	: "<%= UserConstKey.ACTION_DELETE_USER_INFO_OK%>",
           		user_id		: USERINFO.user_id
       		};
			$.ajax({
	       		type: "POST",
	       		url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
	       		data: param,
	       		dataType: "json",
	       		async: true,
	       		cache: false,
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
             	   $('#loadbar').show();
            	}, success: function(e) {
//	       			console.log(e);
	       			if(e.resCode == "0") {
	       				<%-- 탈퇴 완료했습니다. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000116", session_lang) %>'); --%>
	            		alert( LANG_SET.FR000116 );
						location.reload();
	       			} else {
	       				<%-- 요청하신 처리가 실패했습니다 --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000174", session_lang) %>'); --%>
	            		alert( LANG_SET.FR000174 );
	       			}
	       		}, error: function() {
	       		},
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
			});
       	} else {
       		<%-- 모두 체크해주세요. --%>
<%--        		$("#withdrawal-form").append('<em data-lang="FR000199" style="position: relative; color: red; "><%=LangMng.LANG_D("FR000199", session_lang) %></em>') --%>
       		$("#withdrawal-form").append('<em data-lang="FR000199" style="position: relative; color: red; ">' + LANG_SET.FR000199 + '</em>')
//        		$('#withdrawal-form input[type="checkbox"]').prop('checked', false);
       	}
	};
	// 회원탈퇴 END -----------------------------
	
	// [비밀번호 변경] (로그인유저 메일로 인증 메일 발송)
	function sendResetPwdAuthMail() {
		var param = {
			actionID	: "<%= UserConstKey.ACTION_USER_RESET_PWD_MAIL_SEND_OK%>",
			user_id		: USERINFO.user_id,
			confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
			// 사용자 Y --> reset pwd 요청
			use_yn 		: USERINFO.use_yn
		};
		$.ajax({
       		type: "POST",
       		url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
       		data: param,
       		dataType: "json",
       		async: true,
       		cache: false,
			// Ajax 통신 전 로딩표시
            beforeSend:function(){
         	   $('#loadbar').show();
        	}, success: function(e) {
       			if(e.resCode == "0") {
       				<%-- 이메일 인증 메일을 발송하였습니다. --%>
<%--        				alert('<%=LangMng.LANG_D("FR000186", session_lang) %>'); --%>
            		alert( LANG_SET.FR000186 );
					$('#user').css('display','none');
					$('#confirm').css('display','block');
       			} else {
       				<%-- 이메일 발송 중 오류가 발생했습니다. --%>
<%--        				alert('<%=LangMng.LANG_D("FR000188", session_lang) %>'); --%>
            		alert( LANG_SET.FR000188 );
       				alert(e.resMsg);
       			}
       		}, error: function() {
       		},
            // Ajax 통신 완료 후 로딩숨김
            complete:function(){
              $('#loadbar').fadeOut();
            } 
		});
	};
	// [비밀번호 변경]---------------------------
	
	// 비밀번호 재설정
	$("#reset-pwd-form").validate({
		errorClass		: errorClass,
		errorElement	: errorElement,
		highlight: function(element) {
			$(element).parent().removeClass('state-success').addClass("state-error");
			$(element).removeClass('valid');
		},
		unhighlight: function(element) {
			$(element).parent().removeClass("state-error").addClass('state-success');
			$(element).addClass('valid');
		},
		// Rules for form validation
		rules : {
			reset_user_pwd : {
				required: true,
				rangelength: [8, 16],
				regx: true
			},
			conf_reset_user_pwd : {
				required : true,
				equalTo: '#reset_user_pwd',
			}
		},
		// Messages for form validation
		messages : {
			reset_user_pwd : {
				<%-- 비밀번호을 입력해주세요. --%>
				<%-- 8~16자리를 입력해주세요. --%>
				<%-- 특수문자를 포함해주세요. --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000014", session_lang) %>', --%>
<%-- 				rangelength: '<%=LangMng.LANG_D("FR000189", session_lang) %>', --%>
<%-- 				regx: '<%=LangMng.LANG_D("FR000190", session_lang) %>', --%>
				required: LANG_SET.FR000014,
				rangelength: LANG_SET.FR000189,
				regx: LANG_SET.FR000190,
			},
			conf_reset_user_pwd: {
				<%-- 비밀번호를 재입력해주세요. --%>
				<%-- 비밀번호가 일치하지 않습니다. --%>
<%-- 				required : '<%=LangMng.LANG_D("FR000025", session_lang) %>', --%>
<%-- 				equalTo: '<%=LangMng.LANG_D("FR000221", session_lang) %>' --%>
				required: LANG_SET.FR000025,
				equalTo: LANG_SET.FR000221
			}
		},
		submitHandler: function() {
			var reset_pass_target = USERINFO.user_id
			var isLogin = USERINFO.use_yn;
			if (USERINFO.user_id == "") {
				// 로그인X --> find_pass
				reset_pass_target = $('#user_id_for_find_pass').val();
			} 
			var param = {
				actionID	:"<%=UserConstKey.ACTION_RESET_PASSWORD_OK%>",
				user_id		: reset_pass_target,
				user_pwd	: $('#reset_user_pwd').val()  
			};
			$.ajax({
				type: "POST",
				url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
				data: param,
				dataType: "json",
				async: true,
				cache: false,
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
             	   $('#loadbar').show();
            	}, success: function (e) {
// 					console.log(e);
					if (e.resCode == "0") { 
						alert(e.resMsg);
						location.reload();
					} else {
						alert(e.resMsg);
					}
				}, error: function () {
				},
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
			});
		}
	});
	// 비밀번호 재설정 ----------------------------
	
	// forgot password
// 	function getUserByUserId() {
	$('#pwd-user_id-form').validate({
		errorClass		: errorClass,
		errorElement	: errorElement,
		highlight: function(element) {
			$(element).parent().removeClass('state-success').addClass("state-error");
			$(element).removeClass('valid');
		},
		unhighlight: function(element) {
			$(element).parent().removeClass("state-error").addClass('state-success');
			$(element).addClass('valid');
		},
		// Rules for form validation
		rules : {
			rspwd_user_id : {
				email: true,
				required: true,
			},
		},
		// Messages for form validation
		messages : {
			rspwd_user_id : {
				<%-- email@a.com please --%>
				<%-- please write email --%>
<%-- 				email : '<%=LangMng.LANG_D("FR000023", session_lang) %>', --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000013", session_lang) %>', --%>
				email : LANG_SET.FR000023,
				required: LANG_SET.FR000013,
			},
		},
		submitHandler: function() {
			var fPassword_email = $('#rspwd_user_id').val();
			var param = {
				actionID	:"<%=UserConstKey.ACTION_SELECT_USER_BY_EMAIL%>",
				user_id		: fPassword_email,
				confirm_url : window.location.protocol + "//" + window.location.host + "<%=UserConstUrl.USER_EMAIL_AUTH%>?email_cert_key=",
			};
			$.ajax({
				type: "POST",
				url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
				data: param,
				dataType: "json",
				async: true,
				cache: false,
				// Ajax 통신 전 로딩표시
	            beforeSend:function(){
	         	   $('#loadbar').show();
	        	}, success: function (e) {
//					console.log(e);
					if (e.resCode == "0") {
						<%-- 이메일 인증 메일을 발송하였습니다. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000186", session_lang) %>') --%>
	            		alert( LANG_SET.FR000186 );
						$('#password').hide();
						$('#confirm_for_find_pass').show();
						$("#user_id_for_find_pass").val(fPassword_email)
					} else if (e.resCode == "1") {
						<%-- 존재하지 않는 이메일 입니다. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000015", session_lang) %>') --%>
	            		alert( LANG_SET.FR000015 );
					} else if (e.resCode == "2") {
						<%-- 이메일 발송 중 오류가 발생했습니다. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000188", session_lang) %>') --%>
	            		alert( LANG_SET.FR000188 );
					} else {
						alert(e.resMsg);
					}
				}, error: function () {
				},
	            // Ajax 통신 완료 후 로딩숨김
	            complete:function(){
	              $('#loadbar').fadeOut();
	            } 
			});
		}
	});
// 	};
	// forgot password -------------------

	// My page > password
	$('#user-pwd-form').validate({
		errorClass		: errorClass,
		errorElement	: errorElement,
		highlight: function(element) {
			$(element).parent().removeClass('state-success').addClass("state-error");
			$(element).removeClass('valid');
		},
		unhighlight: function(element) {
			$(element).parent().removeClass("state-error").addClass('state-success');
			$(element).addClass('valid');
		},
		// Rules for form validation
		rules : {
			user_old_pwd : {
				required: true,
			},
			user_new_pwd : {
				required: true,
				rangelength: [8, 16],
				regx: true
			},
			conf_user_new_pwd : {
				required : true,
				equalTo: '#user_new_pwd',
			},
		},
		// Messages for form validation
		messages : {
			user_old_pwd : {
				<%-- 비밀번호을 입력해주세요. --%>
				required: LANG_SET.FR000014,
			},
			user_new_pwd : {
				<%-- 비밀번호을 입력해주세요. --%>
				<%-- 8~16자리를 입력해주세요. --%>
				<%-- 특수문자를 포함해주세요. --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000014", session_lang) %>', --%>
<%-- 				rangelength: '<%=LangMng.LANG_D("FR000189", session_lang) %>', --%>
<%-- 				regx: '<%=LangMng.LANG_D("FR000190", session_lang) %>' --%>
				required: LANG_SET.FR000014,
				rangelength: LANG_SET.FR000189,
				regx: LANG_SET.FR000190
			},
			conf_user_new_pwd : {
				<%-- 비밀번호를 재입력해주세요. --%>
				<%-- 비밀번호가 일치하지 않습니다. --%>
<%-- 				required : '<%=LangMng.LANG_D("FR000025", session_lang) %>', --%>
<%-- 				equalTo: '<%=LangMng.LANG_D("FR000221", session_lang) %>'  --%>
				required: LANG_SET.FR000025,
				equalTo: LANG_SET.FR000221 
			},
		},
		submitHandler: function() {
			var param = {
				actionID	:"<%=UserConstKey.ACTION_EDIT_USER_PASSWORD_OK%>",
				user_id		: USERINFO.user_id,
				old_user_pwd: $('#user_old_pwd').val(),
				user_pwd	: $('#user_new_pwd').val() 
			};
			$.ajax({
				type: "POST",
				url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
				data: param,
				dataType: "json",
				async: true,
				cache: false,
				// Ajax 통신 전 로딩표시
	            beforeSend:function(){
	         	   $('#loadbar').show();
	        	}, success: function (e) {
//					console.log(e);
					if (e.resCode == "0") {
						<%-- 비밀번호를 변경하였습니다 --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000193", session_lang) %>') --%>
	            		alert( LANG_SET.FR000193 );
	            		location.reload();
					} else if (e.resCode == "1") {
						<%-- Fail --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000174", session_lang) %>') --%>
	            		alert( LANG_SET.FR000174 );
					} else if (e.resCode == "2") {
						<%-- 비밀번호가 일치하지 않습니다. --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000221", session_lang) %>') --%>
	            		alert( LANG_SET.FR000221 );
					} else {
						alert(e.resMsg);
					}
				}, error: function () {
				},
	            // Ajax 통신 완료 후 로딩숨김
	            complete:function(){
	              $('#loadbar').fadeOut();
	            } 
			});
		}
	})	// My page > password ---------------
	
	// [Join VIP] 승급요청 클릭
	$("#upgrade-form").validate({
		errorClass		: errorClass,
		errorElement	: errorElement,
		highlight: function(element) {
			$(element).parent().removeClass('state-success').addClass("state-error");
			$(element).removeClass('valid');
		},
		unhighlight: function(element) {
			$(element).parent().removeClass("state-error").addClass('state-success');
			$(element).addClass('valid');
		},
		// Rules for form validation
		rules : {
			ug_user_name : {
				required: true,
			},
			ug_user_company : {
				required: true,
			},
			ug_user_com_mail : {
				required : true,
				email : true,
			},
			ug_user_com_tel : {
				required: true,
			},
			ug_user_com_addr : {
				required: true,
			},
			ug_user_mobile : {
				required: true,
			},
			ug_user_nation : {
				required: true,
			},
			ug_user_lang : {
				required: true,
			},
			ug_unit_cd : {
				required: true,
			},
			ug_curr_cd : {
				required: true,
			},
			ug_ug_emp : {
				required : true,
			},
			ug_terms_read : {
				required: true,
			} 
		},
		// Messages for form validation
		messages : {
			ug_user_name : {
				<%-- Please enter Name --%>
				required: LANG_SET.FR000024,
			},
			ug_user_company : {
				<%-- 회사명을 입력해주세요. --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000201", session_lang) %>', --%>
				required: LANG_SET.FR000201,
			},
			ug_user_com_mail: {
				<%-- 회사 이메일을 입력해주세요. --%>
				<%-- 이메일 형식으로 입력해주세요. --%>
<%-- 				required : '<%=LangMng.LANG_D("FR000202", session_lang) %>', --%>
<%-- 				email : '<%=LangMng.LANG_D("FR000023", session_lang) %>', --%>
				required : LANG_SET.FR000202,
				email : LANG_SET.FR000023,
			},
			ug_user_com_tel : {
				<%-- 회사 전화번호를 입력해주세요. --%>
				required: LANG_SET.FR000251,
			},
			ug_user_com_addr : {
				<%-- 회사 주소를 입력해주세요. --%>
				required: LANG_SET.FR000252,
			},
			ug_user_mobile : {
				<%-- 전화번호를 입력해주세요. --%>
				required: LANG_SET.FR000250,
			},
			ug_user_nation : {
				<%-- Please select current location --%>
				required: LANG_SET.FR000026,
			},
			ug_user_lang : {
				<%-- 언어를 선택해주세요. --%>
				required: LANG_SET.FR000253,
			},
			ug_unit_cd : {
				<%-- 단위를 선택해주세요. --%>
				required: LANG_SET.FR000254,
			},
			ug_curr_cd : {
				<%-- 통화를 선택해주세요. --%>
				required: LANG_SET.FR000255,
			},
			ug_ug_emp: {
			<%-- Mypage에서 국가를 입력바랍니다. --%>
<%-- 				required : '<%=LangMng.LANG_D("FR000225", session_lang) %>', --%>
				required : LANG_SET.FR000225,
			},
			ug_terms_read : {
				<%-- 3자 제공 동의 --%>
<%-- 				required: '<%=LangMng.LANG_D("FR000224", session_lang) %>', --%>
				required: LANG_SET.FR000224,
			} 
		},
		submitHandler: function() {
			
			var param = {
				actionID		:"<%=UserConstKey.ACTION_USER_REQ_UPGRADE%>",
				user_id			: $('#ug_user_id').val(),
				user_name		: $('#ug_user_name').val(),
				user_company	: $('#ug_user_company').val(),
				user_com_mail	: $('#ug_user_com_mail').val(),
				
				user_com_tel	: $('#ug_user_com_tel').val(),
				user_com_addr	: $('#ug_user_com_addr').val(),
				user_mobile		: $('#ug_user_mobile').val(),
				user_nation		: $('#ug_user_nation').val(),
				user_lang		: $('#ug_user_lang').val(),
				unit_cd			: $('#ug_unit_cd').val(),
				curr_cd			: $('#ug_curr_cd').val(),
				
				ug_emp			: $('#ug_ug_emp').val(),
				
				nation_txt		: $('#ug_user_nation option:selected').text(),
				lang_txt		: $('#ug_user_lang option:selected').text(),
				unit_txt		: $('#ug_unit_cd option:selected').text(),
				curr_txt		: $('#ug_curr_cd option:selected').text(),
			};
			$.ajax({
				type: "POST",
				url: "<%=UserConstUrl.USER_URL%>?v=" + (new Date().getTime()),
				data: param,
				dataType: "json",
				async: true,
				cache: false,
				// Ajax 통신 전 로딩표시
                beforeSend:function(){
             	   $('#loadbar').show();
            	}, success: function (e) {
// 					console.log(e);
					if (e.resCode == "0") {
						<%-- 담당자에게 VIP 승급요청 메일을 발송했습니다.  --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000203", session_lang) %>') --%>
	            		alert( LANG_SET.FR000203 );
						location.reload();
					} else {
						<%-- 요청하신 처리가 실패했습니다 --%>
<%-- 	       				alert('<%=LangMng.LANG_D("FR000174", session_lang) %>') --%>
	            		alert( LANG_SET.FR000174 );
					}
				}, error: function () {
				},
	             // Ajax 통신 완료 후 로딩숨김
	             complete:function(){
	               $('#loadbar').fadeOut();
	             } 
			});
		}
	});
	// [Join VIP] 승급요청 클릭 END --------------
</script>
