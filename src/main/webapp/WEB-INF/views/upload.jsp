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
              <div class="item-block center"><b id="url">upload Image</b></div>
              <div class="item-block">
                <div id="uresult">
                  <img id="ulogoImg" src="/service/resources/img/upload.png" height="150" />
                </div>
                <div id="uprogress" class="upload-progress">
                  <div class="upload-bar" style="width: 0%;"></div>
                </div>
                
              </div>
                <!--  File upload related data ****************************************************************************************************** -->
                <div class="no-show">
                  <input id="ufileupload" type="file" name="files[]"
                    data-url="/service/upfile/upload"> <input
                    type="hidden" name="imageMeta.fileName" id="ufileName" /> <input
                    type="hidden" name="imageMeta.fileSize" id="ufileSize" /> <input
                    type="hidden" name="imageMeta.fileType" id="ufileType" /> <input
                    type="hidden" name="imageMeta.fileExt" id="ufileExt" />
                </div>
                <div class="form-group input-group incenter">
                  <button type="button" class="greenBtn" id="upButton">Upload
                    Image</button>
                </div>
              </div>
            </div>
                

  <!-- Scripts -->
  <script src="/service/resources/base/js/vendor/modernizr.js"></script>
  <script src="/service/resources/base/js/plugins.js"></script>
  <script src="/service/resources/base/js/foundation.min.js"></script>
  <script src="/service/resources/base/js/functions.js"></script>
  <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
  <script type="text/javascript" src="/service/resources/js/notice.js"></script>
  <script src="/service/resources/upload/js/jquery.ui.widget.js"></script>
   <script src="/service/resources/upload/js/jquery.iframe-transport.js"></script>
  <script src="/service/resources/upload/js/jquery.fileupload.js"></script>
  <script src="/service/resources/upload/js/myuploadfunction.js"></script>
  <script>
      $(document).on('ready', function() {
        
        $("#upButton").click(function() {
      		$("#ufileupload").click();
      	});

      });
    </script>

</body>

</html>
