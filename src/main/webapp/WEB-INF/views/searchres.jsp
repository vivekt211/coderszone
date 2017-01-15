<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
  
<head>
     <%@ include file="title.jsp"%>  
    <title>CodersZone | My Page</title>
   
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
    <link rel="stylesheet" href="/service/resources/css/style.css"/>
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
              <div id="mobileMenuTrigger" class="hide-for-large-up"><i class="icon-menu"></i></div>

              <nav id="primaryMenu">
                  <%@ include file="menu.jsp" %>    
              </nav>
              <!-- ==========================================
                   Mega Menu Ends 
              =============================================== -->
            </div>
                
          </div>
        </div>
      </div>
    </header>

    <div class="mainWrapper">

     
      <div class="bgpatti">
        <div class="searcharea">
            <input type="text" class="searchtxt" id="searchbox" value="${keyword }"><input type="button" value="search" id="searchbtn"/>
        </div>
      </div>
      
       <c:choose>
           <c:when test="${empty blogPage.pageItems}">
            <div class="blank-bdy">
              No blogs were find. Please try some different keywords !
              </div>
            </c:when>
          <c:otherwise>
          <ul class="searchres">
            <c:forEach var="blog" items="${blogPage.pageItems}" varStatus="i">
              <li class="search-li">
                  <div class="meta">
                    <p>
                    <a href="#"><strong>${blog.tags}</strong></a>
                    <span class="smallText"> / ${blog.modDate}</span></p>
                  </div>
                  <h2 class="blogTitle"><a href="/service/detail?id=${blog.id}">${blog.title}</a></h2>
                  <p class="smallText">${blog.desc} ....</p>
                  <%-- <a class="link" href="/service/detail?id=${blog.id}">Read Details<span class="icon-arrow-right"></span></a> --%>
              </li>
          </c:forEach>
          </ul>
        </c:otherwise>
      </c:choose>
      

      <div class="bgWhite">
        <div class="row stretched padding clearfix blogPagination">
          <ul class="pagination">
                 <c:forEach begin="1" end="${blogPage.pagesAvailable}" varStatus="loop"><!-- ${schoolPage.pagesAvailable} -->
                       <c:choose>
                           <c:when test="${loop.count == blogPage.pageNumber }">
                             <li class="active"><a  class="pagelink" href="#"><c:out value="${loop.count}"/></a></li>
                           </c:when>
                            <c:otherwise>
                             <li><a  class="pagelink" href="${context_root}/service/searchlist?keyword=${keyword }&pageno=<c:out value="${loop.count}"/>&pagesize=10"><c:out value="${loop.count}"/></a></li>
                           </c:otherwise>
                           </c:choose>
                      </c:forEach>        
                </ul>
          <!-- <div class="small-12 columns">
            <div class="left">
              <a class="button btnOutlineBlack btnSmall" href="#"><i class="icon-angle-left iconLeft"></i>Older Posts</a>
            </div>

            <div class="right">
              <a class="button btnOutlineBlack btnSmall" href="#">Newer Posts<i class="iconRight icon-angle-right"></i></a>
            </div>
          </div> -->
        </div>
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
    <script type="text/javascript">
      $("#searchbtn").click(function(){
        if($("#searchbox").val().trim()!='')
        window.location = '${context_root}/service/searchlist?keyword=' + $("#searchbox").val() + '&pageno='+1+ '&pagesize='+10;
      });
      $("#searchbox").keyup(function(event){
        if(event.keyCode == 13){
            $("#searchbtn").click();
        }
      });
    </script>
  </body>

</html>
