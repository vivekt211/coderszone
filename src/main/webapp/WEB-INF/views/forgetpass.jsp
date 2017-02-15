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
   <meta name="robots" content="noindex,nofollow">
   <link rel="icon" href="${context_root}/service/resources/img/favicon/favicon.ico" type="image/x-icon" /> 
   <link rel="shortcut icon" href="${context_root}/service/resources/img/favicon/favicon-32x32.png">
    <title>CodersZone | Forget Password</title>
   
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
                <form id="forgetform" method="post">
                 <div class="item-fl" id="msg">
                  </div>
                  <div class="item-fl">
                    <input type="text" name="username" id="userid" class="txt" placeholder="Enter your Email Id" />
                  </div>
                  <div class="item-block">
                    <div class="rght">
                      <input type="button" value="Send New Password" class="greenBtn" id="passbtn" />
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
    <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
    <script type="text/javascript" src="/service/resources/js/notice.js"></script>

  <script>
      function valid(){
        var email=$("#userid").val();
        if(email.trim()==''  ){
      		notify("error","Fields cannot be empty, plese fill it");
      		return false;
      	}
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            return true;
        }else{
            notify('error',"Please Enter a valid Email");
            return false;
        }
      }
      
      $(document).on('ready', function() {
        /* var video = document.getElementById("heroVideo");
        video.addEventListener("canplay", function() {
          video.play();
        }); */
        $("#passbtn").click(function() {
          if(valid()){
            $.ajax({
              url: '/service/newpass?id=' + $("#userid").val(),
              type: 'GET',
              success: function(result) {
                if (result.responseCode == 200) {
                  	notifyGoLogin('success',result.message);
                  } else {
                 	notify('error',result.message);
                }
              }
            });
          }
          
        });
      });
    </script>

</body>

</html>
