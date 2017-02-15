<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html class="no-js" lang="en">

<head>
  <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Meta Tags -->
    <meta name="description" content="Login to CodersZone.in and start writing your tech blogs. The blogs focused on learning and helping open source community" />
    <meta name="author" content="coderszone.in" />
    <meta name="keywords" content="Coderszone Login join signin">
    <link rel="icon" href="${context_root}/service/resources/img/favicon/favicon.ico" type="image/x-icon" /> 
    <link rel="shortcut icon" href="${context_root}/service/resources/img/favicon/favicon-32x32.png">
     <title>CodersZone | login</title>
   
<!-- Stylesheets and Fonts-->
<link href='https://fonts.googleapis.com/css?family=Merriweather:300,400,400italic,700|Oxygen:400,300,700' rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="/service/resources/base/css/foundation.min.css" />
<link rel="stylesheet" href="/service/resources/base/css/main.min.css" />
<link rel="stylesheet" href="/service/resources/base/css/animate.css">
<link rel="stylesheet" href="/service/resources/base/css/animsition.min.css">
<link rel="stylesheet" href="/service/resources/base/css/icomoon.css" />
<link rel="stylesheet" href="/service/resources/base/css/responsive.css">
<link rel="stylesheet" id="default" href="/service/resources/base/css/colors/colors.css" />
<link rel="stylesheet" href="/service/resources/css/style.css">

<!-- Scripts -->
<script src="/service/resources/base/js/vendor/jquery.min.js"></script>

    <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
    <script type="text/javascript" src="/service/resources/js/notice.js"></script>
<!--[if IE]>
      <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body class="fullWidth">

  <div id="loading" class="loadSpinner">
    <div id="loading-center">
      <div id="loading-center-absolute">
        <div class="object" id="object_four"></div>
        <div class="object" id="object_three"></div>
        <div class="object" id="object_two"></div>
        <div class="object" id="object_one"></div>
      </div>
    </div>
  </div>

  <div class="animsition">

    <header class="relPosition">
    <div id="autoHide" class="headerWrapper fixedHeader bgGrey">
      <div class="row stretched">
        <div class="navWrapper clearfix">

          <div class="small-12 medium-12 large-4 columns">
            <!-- Logo Area -->
            <div id="logo" class="logoc">
               <a href="/service/home"><img src="/service/resources/img/logo.png" width="250px" height="31px"  alt="CodersZone.in"></a>
            </div>
          </div>

          <div class="small-12 medium-12 large-8 columns noPaddingRight">
            <!-- ==========================================
                   Mega Menu Starts 
              =============================================== -->
            <div id="mobileMenuTrigger" class="hide-for-large-up">
              <i class="icon-menu"></i>
            </div>

            <nav id="primaryMenu"> <%@ include file="menu.jsp"%> </nav>
          </div>

        </div>
      </div>
    </div>
    </header>

    <div class="bgWhite">
      <!-- Hero Banner Starts -->
      
        <div class="login-pg">
              <div class="login-block">
                <form id="loginform" action="<c:url value='/j_spring_security_check' />" method="post">
                <c:if test='${param["error"]}'>
                  <div class="item-fl">
                     UserName or password is incorrect, Please use correct credentials.
                  </div>
                </c:if>
                  <div class="item-fl">
                    <input type="text" name="username" id="userid" class="txt" placeholder="Enter your Email Id" />
                  </div>
                  <div class="item-fl">
                    <input type="password" name="password" id="password" placeholder="Enter password" />
                  </div>
                  <div class="item-block">
                    <div class="rght">
                      <input name="remember" type="checkbox" value="Remember Me">
                      <span class="checktxt">Remember Me</span>
                      <a class="fpass" href="/service/forgetpass"> Forget password ?</a>
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>
                  </div>

                  <div class="item-block">
                    <div class="rght">
                      <input type="button" value="login" class="greenBtn" id="loginbtn" />
                    </div>
                     <div class="lft">
                      <input type="button" value="register" class="greenBtn" id="regbtn" />
                    </div>
                  </div>
                  </form>
              </div>
        </div>

        

      
    </div>

  </div>

  
  <!-- Scripts -->
  <script src="/service/resources/base/js/vendor/modernizr.js"></script>
  <script src="/service/resources/base/js/plugins.js"></script>
  <script src="/service/resources/base/js/foundation.min.js"></script>
  <script src="/service/resources/base/js/functions.js"></script>
  <script src="/service/resources/base/js/ajax-form.js"></script>
  <script src="/service/resources/base/js/vendor/jquery.cookie.js"></script>

  <script>
      $(document).on('ready', function() {

        $("#regbtn").click(function() {
          //Alert ajax call to register
          window.location.href = "/service/registration";
        });
        function valid(){
          var email=$("#userid").val();
          var pass=$("#password").val();
          if(email.trim()==''  ){
        		notify("error","UserId cannot be empty");
        		return false;
          }
          if(pass.trim()==''  ){
        		notify("error","Password cannot be empty");
        		return false;
          }
          if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
              return true;
          }else{
              notify('error',"Please Enter a valid User Id");
              return false;
          }
        }
        $("#loginbtn").click(function(){
          if(valid()){
            $("#loginform").submit();
          }
        });
        $("#password").keyup(function(event){
          if(event.keyCode == 13){
              $("#loginbtn").click();
          }
        });
      });
    </script>

</body>

</html>
