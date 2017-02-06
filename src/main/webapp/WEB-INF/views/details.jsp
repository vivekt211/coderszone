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
      <h3>Comments</h3>
       <div class="comments-sec">
        <ul>
         <!-- commens goes here -->
        </ul>
       </div>
       <div class="comment-write" id="commentWrite">
          <div class="write-hd">
             <div class="write-name"> Your Name : <input type="text" name="name" id="usrName" placeholder=" Enter Your Name"/> 
       </div>
             <div class="write-email"> Your Email : <input type="text" name="email" id="usrEmail" placeholder=" Enter Your Email"/> </div>
          </div>
          <div class="write-cont">
            <textarea class="comment" name="content" id="comment" placeholder=" What is in your mind ?">
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
    <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
    <script type="text/javascript" src="/service/resources/js/notice.js"></script>
  
      
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
        
    function loadComments(){
    	
    	$.ajax({
            url: '/service/comments?id=' + $("#blogid").val(),
            type: 'GET',
            success: function(result) {
              if (result.responseCode == 200) {
            	  var ul=$(".comments-sec").find('ul');
            	for(var i=0;i<result.data.length;i++){
            		ul.append('<li>'+
            		           '<div class="comment-bx">'+
            		              '<div class="comment-hd">'+
            		              result.data[i].name+'('+result.data[i].email+')|'+result.data[i].dateTime+
            		              '</div>'+
            		              '<div class="comment-bdy">'+
            		               '<pre>'+
            		               '</pre>'+
            		              '</div>'+
            		           '</div>'+
            		         '</li>');
            		ul.find('li:last-child').find(".comment-bdy pre").text(result.data[i].content);
            		}
            	notify('success',"Comments added successfully");
                
               // $("#registerBlock").hide();
                //$("#verifyBlock").hide();
               // $("#messageBlock").show();
              } else {
               // $("#verify-msg").html(result.message);
               alert("na bhail")
              }
            }
          });
    }
    function validComments(){
    	var name=$("#usrName").val();
    	var email=$("#usrEmail").val();
    	var content=$("#comment").val();
    	
    	if(name.trim()=='' || email.trim()=='' || content.trim==''  ){
    		notify("error","Fields cannot be empty, plese fill it");
    		return false;
    	}
    	var re = '/\S+@\S+\.\S+/';
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
          return true;
        }else{
          notify('error',"Please Enter a valid Email");
          return false;
        }
    }
    loadComments();
    $("#postCommentbtn").click(function(){
    	if(validComments()){
    		postComment();
    	}
    });
    function postComment(){
    	var name=$("#usrName").val();
    	var email=$("#usrEmail").val();
    	var content=$("#comment").val();
    	
    	var dt={"name":name,"email":email,"content":content,"blogId":$("#blogid").val()};
    	 $.ajax({
             url: '/service/postcomment',
             type: 'POST',
             data: dt,
             success: function(result) {
               //var result1 = JSON.parse(result);
               if (result.responseCode == 200) {
            	   var ul=$(".comments-sec").find('ul');
            	       ul.append('<li>'+
        		           '<div class="comment-bx">'+
        		              '<div class="comment-hd">'+
        		              result.data.name+'('+result.data.email+')|'+result.data.dateTime+
        		              '</div>'+
        		              '<div class="comment-bdy">'+
        		               '<pre>'+decodeURIComponent(result.data.content)+
        		               '</pre>'+
        		              '</div>'+
        		           '</div>'+
        		         '</li>');
            	       notify("success","Comments added successfully");
               	 } else {
                 alert("pata na ");
               }
             }
           });
    }
       
     </script>
  </body>

</html>
