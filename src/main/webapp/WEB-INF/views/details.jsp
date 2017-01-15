<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html class="no-js" lang="en">
  
<head>
     <%@ include file="title.jsp"%>  
    <title>CodersZone | blog</title>
   
    <!-- Stylesheets and Fonts-->
    <link href='https://fonts.googleapis.com/css?family=Merriweather:300,400,400italic,700|Oxygen:400,300,700' rel='stylesheet' type='text/css'>
    <link href="/service/resources/base/css/bootstrap.css" rel="stylesheet">
    <link href="/service/resources/summernote/summernote.css" rel="stylesheet">
    <link rel="stylesheet" href="/service/resources/base/css/foundation.min.css" />
    <link rel="stylesheet" href="/service/resources/base/css/main.min.css" />
    <link rel="stylesheet" href="/service/resources/base/css/animate.css">
    <link rel="stylesheet" href="/service/resources/base/css/animsition.min.css">
    <link rel="stylesheet" href="/service/resources/base/css/icomoon.css" />
    <link rel="stylesheet" href="/service/resources/base/css/pogo-slider.min.css">
    <link rel="stylesheet" href="/service/resources/base/css/responsive.css">
    <link rel="stylesheet" id="default" href="/service/resources/base/css/colors/colors.css" />
    <link rel="stylesheet" href="/service/resources/css/multiple-select.css">
    <link rel="stylesheet" href="/service/resources/css/style.css">
    
    <!-- include summernote css/js-->

   
    <!-- Scripts -->
   <script src="/service/resources/base/js/jquery.js"></script> 
  <script src="/service/resources/base/js/bootstrap.js"></script> 
  <script src="/service/resources/summernote/summernote.js"></script>
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
                 <a href="${context_root}/service/home"><img src="${context_root}/service/resources/img/logo.png" width="250px" height="31px"  alt="CodersZone.in"></a>
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
            <input type="text" class="searchtxt" id="searchbox"><input type="button" value="search" id="searchbtn"/>
        </div>
        </div>
      <div class="containerwrite">
      <div class="row">
        <div class="detail-blog-tags">
          <a href="#">${blog.tags}</a> &nbsp;
        </div>
         <div class="detail-blog-writer">
          <a href="#">${blog.createdBy }&nbsp;</a>|&nbsp; ${blog.modDate }
        </div> 
      </div>
      <div class="detail-blog-title">
        ${blog.title }
        <input type="hidden" name="id" id="blogid" value="${blog.id}">
      </div>
      
      <div id="summernote">
          ${blog.content}
      </div>
      <div class="item-ful">
      <c:if test="${blog.createdBy eq username }">
        <div class="right">
           <input type="button" value="Edit" class="greenBtn" id="postbtn" />
        </div>
        </c:if>
      </div>
      </div>
      <div class="comments-area">
       <div class="comments-sec">
        <ul>
         <li>
           <div class="comment-bx">
              <div class="comment-hd">
                vivek Kumar (vivekt211@bmc.com)|25 nov 2017
              </div>
              <div class="comment-bdy">
               <pre> Hi !
                 Thanks for this awesome portal.
               </pre>
              </div>
           </div>
         </li>
        </ul>
       </div>
       <div class="comment-write">
          <div class="write-hd">
             <div class="write-name"> Your Name : <input type="text" name="usrName"/> </div>
             <div class="write-email"> Your Email : <input type="text" name="usrEmail"/> </div>
          </div>
          <div class="write-cont">
            <textarea class="comment" name="comment" id="comment">
            </textarea>
          </div>
       </div>
       <div class="item-ful">
        <div class="right">
           <input type="button" value="Post Comment" class="greenBtn" id="postCommentbtn" />
        </div>
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
    <script src="/service/resources/base/js/style-switcher.js"></script>
    <script src="/service/resources/js/multiple-select.js"></script>
      
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
        $("#postbtn").click(function(){
          	window.location.replace("${context_root}/service/getupdate?id="+$("#blogid").val());
        }); 
     </script>
  </body>

</html>
