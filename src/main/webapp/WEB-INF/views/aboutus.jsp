<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
  
<head>
    <%@ include file="title.jsp"%>  
    <title>CodersZone | About Me</title>
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

    <!-- Scripts -->
    <script src="/service/resources/base/js/vendor/jquery.min.js"></script>
    <!--[if IE]>
      <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>
  
  <body class="boxed">

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
              <div id="mobileMenuTrigger" class="hide-for-large-up"><i class="icon-menu"></i></div>

              <nav id="primaryMenu">
                <%@ include file="menu.jsp"%>  
              </nav>
              <!-- ==========================================
                   Mega Menu Ends 
              =============================================== -->
            </div>
                
          </div>
        </div>
      </div>
    </header>

    <div class="heroWrapper relPosition">
      <!-- Hero Banner Starts -->
      <div class="heroSmall relPosition" style="background-image: url('/service/resources/img/me.jpg');background-position: 50% 50%;">
        <div class="row">
          <div class="heroIntro padding horizontalCenter">
            <div class="small-10 medium-8 large-8 columns">
              <h6>About Me</h6>
              <div class="divider"></div>
              <h2>Vivek Kumar Tiwari</h2> 
              <h4>founder of CodersZone.in</h4>
              <h4>Lead Engineer with Samsung India Electronics pvt ltd.</h4>
            </div>
          </div> 
        </div> 
      </div>
      <!-- Hero Banner Ends -->
    </div>

    <div class="mainWrapper">

      <div class="bgWhite">
        <div class="row padding">

          <div class="small-12 medium-12 large-7 columns"> 
            <div class="row">
              <div class="small-12 columns">
                <h4>About me</h4>
                <div class="divider"></div>
                <p>I am Java/Spring Web Application developer .Now I am working as a Lead Engineer with Samsung India. I have been involved in the Design and Development of various enterprise solutions like Cloud Authoring Solution, CMS, Entertainment App Server,Smart TV App Store etc.  </p>
                <p>The continuous pursuit of technological awareness and expertise in Java/Spring & AWS Cloud related technologies has driven me to the development of this portal. Any Suggestions and feedback is welcome.</p>

                <div class="row">
                  <div class="small-12 medium-4 large-4 columns noPadding">
                    <ul class="iconList">
                      <li><span class="icon-check coloredText"></span>Solution Architect Design</li>
                      <li><span class="icon-check coloredText"></span>Java</li>
                    </ul>
                  </div>

                  <div class="small-12 medium-4 large-4 columns noPadding">
                    <ul class="iconList">
                      <li><span class="icon-check coloredText"></span>Spring</li>
                      <li><span class="icon-check coloredText"></span>HTML5</li>
                    </ul>
                  </div>

                  <div class="small-12 medium-4 large-4 columns noPadding">
                    <ul class="iconList">
                      <li><span class="icon-check coloredText"></span>Mysql/Oracle</li>
                      <li><span class="icon-check coloredText"></span>AWS Cloud Platform</li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="small-12 medium-12 large-5 columns">
            <div class="row">
              <div class="small-12 small-centered columns content bar">
                <h4>Contacts</h4>
                <div class="divider"></div>
                <p class="smallText">PLease write to me for any suggestion or feedBack.</p>
                <p class="smallText"><b>linkvivek.vt@gmail.com</b></p>

              </div>   
            </div>
          </div>
          
        </div>
      </div>

      <div class="row">
        <div class="sectionSeparator"></div>
      </div>
    </div>
    <!-- Main Wrapper Ends -->

    <!-- Footer Small Starts -->
    <%@ include file="footer.jsp"%>
    <!-- Footer Small Ends -->

    </div>
    
    <a href="#0" class="backTop">Top</a>

    <!-- Scripts -->
    <script src="/service/resources/base/js/vendor/modernizr.js"></script>
    <script src="/service/resources/base/js/plugins.js"></script>
    <script src="/service/resources/base/js/foundation.min.js"></script>
    <script src="/service/resources/base/js/functions.js"></script>
    <script src="/service/resources/base/js/ajax-form.js"></script>
    <script src="/service/resources/base/js/vendor/jquery.cookie.js"></script>
  </body>

</html>
