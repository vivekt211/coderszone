<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
 <%@ include file="title.jsp"%>  
 <title>CodersZone | Register</title>
   
<!-- Stylesheets and Fonts-->
<link href='https://fonts.googleapis.com/css?family=Merriweather:300,400,400italic,700|Oxygen:400,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/service/resources/base/css/foundation.min.css" />
<link rel="stylesheet" href="/service/resources/base/css/main.min.css" />
<link rel="stylesheet" href="/service/resources/base/css/animate.css">
<link rel="stylesheet" href="/service/resources/base/css/animsition.min.css">
<link rel="stylesheet" href="/service/resources/base/css/icomoon.css" />
<link rel="stylesheet" href="/service/resources/base/css/pogo-slider.min.css">
<link rel="stylesheet" href="/service/resources/base/css/responsive.css">
<link rel="stylesheet" id="default" href="/service/resources/base/css/colors/colors.css" />
    <link rel="stylesheet" type="text/css" href="/service/resources/css/buttons.css"/>
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
            <div id="logo">
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
            <!-- ==========================================
                   Mega Menu Ends 
              =============================================== -->
          </div>

        </div>
      </div>
    </div>
    </header>

    <div class="login-pg">
              <div class="reg-block" id="registerBlock">
                <form id="regblock" name="regblock">
                <div class="item-block" id="registerMsg"></div>
                <div class="item-block">
                  <input type="text" name="name" id="name" placeholder="Enter Your Name" />
                </div>
                <div class="item-block">
                  <input type="text" name="email" id="email" class="txt" placeholder="Email ID" />
                </div>
                <div class="item-block">
                  <input type="password" name="password" id="password" placeholder="Password" />
                </div>
                <div class="item-block">
                  <input type="password" name="repassword" id="repassword" placeholder="Retype Password" />
                </div>
                <div class="item-block">
                  <div class="rght">
                    <input type="button" value="Register" class="greenBtn" id="regbtn" />
                  </div>
                </div>
                </form>
              </div>
              <div class="reg-block" id="verifyBlock">
                <div class="item-block" id="verify-msg"></div>
                <div class="item-block">Please Enter the code sent to your email id to verify your Account.</div>
                <div class="item-block">
                  <input type="text" name="verifykey" id="verifyKey" placeholder="Verification Key" />
                  <input type="hidden" name="id" id="id" />
                </div>
                <div class="item-block">
                  <div class="rght">
                    <input type="button" value="Verify" class="greenBtn" id="verifybtn" />
                  </div>
                </div>
              </div>

              <div class="reg-block" id="messageBlock">
                <div class="item-block" id="successmsg">Thank You ! for joining our community.</div>
                <div class="item-block">
                  <div class="rght">
                    <a href="/service/login" class="greenBtn" id="loginbtn">Go to Login</a>
                  </div>
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
      $(document).on('ready', function() {
        
        $("#verifyBlock").hide();
        $("#messageBlock").hide();
        function validReg(){
          if($("#name").val()==''){
            notify('error',"Name cannot be empty");
            return false;
          }
          if($("#email").val()==''){
            notify('error',"Email ID cannot be empty");
            return false;
          }
          if($("#password").val()==''){
            notify('error',"Password cannot be empty");
            return false;
          }
          if($("#repassword").val()==''){
            notify('error',"Retype Password cannot be empty");
            return false;
          }
          var re = '/\S+@\S+\.\S+/';
          if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val())) {
            
          }else{
            notify('error',"Please Enter a valid Email");
            return false;
          }
          
          if($("#password").val()!=$("#repassword").val()){
            notify('error',"Password do not match");
            return false;
          }
          return true;
        }
        $("#regbtn").click(function() {
          //Alert ajax call to register
          if(validReg()){
            $("#registerMsg").html("registering ...");
            $.ajax({
              url: '/service/register',
              type: 'POST',
              data: $("#regblock").serialize(),
              success: function(result) {
                //var result1 = JSON.parse(result);
                if (result.responseCode == 200) {
                  $("#registerBlock").hide();
                  $("#messageBlock").hide();
                  $("#verifyBlock").show();
                  $("#id").val(result.data.id);
                } else {
                  $("#registerMsg").html(result.message);
                }
              }
            });
          }
        });
        

        $("#verifybtn").click(function() {
          var id = $("#id").val();
          var code = $("#verifyKey").val();
          $.ajax({
            url: '/service/verifyreg?id=' + id + '&code='+code,
            type: 'GET',
            success: function(result) {
              if (result.responseCode == 200) {
                $("#registerBlock").hide();
                $("#verifyBlock").hide();
                $("#messageBlock").show();
              } else {
                $("#verify-msg").html(result.message);
              }
            }
          });

        });

      });
    </script>

</body>

</html>
