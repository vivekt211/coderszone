<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html class="no-js" lang="en">
  
<head>
    <meta charset="utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Meta Tags -->
    <meta name="description" content="${blog.desc }" />
    <meta name="author" content="${blog.createdBy }" />
    <meta name="keywords" content="Coderszone ${blog.keywords }">
    <link rel="icon" href="${context_root}/service/resources/img/favicon/favicon.ico" type="image/x-icon" /> 
    <link rel="shortcut icon" href="${context_root}/service/resources/img/favicon/favicon-32x32.png">
    <title>CodersZone|${blog.title }</title>
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
        <input type="hidden" name="isadmin" id="isadmin" value="${isadmin}">
        <input type="hidden" name="isblogger" id="isblogger" value="${isblogger}">
      </div>
      
      <div id="summernote">
          ${blog.content}
      </div>
      <div class="item-ful">
      <c:if test="${blog.createdBy eq username || isadmin eq true}">
        <div class="left">
           <input type="button" value="Delete" class="greenBtn" id="delbtn" />
        </div>
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
       <c:if test="${isblogger eq true}">
         <div class="comment-write" id="commentWrite">
            <div class="write-hd">
              <input type="hidden" name="name" id="usrName" value="${user.name}"/> 
              <input type="hidden" name="email" id="usrEmail" value="${user.username}"/>
            </div>
            <div class="write-cont">
              <textarea class="comment" name="content" id="comment" placeholder="Hey ${user.name} ! What is in your mind ?"></textarea>
            </div>
         </div>
         <div class="item-ful">
        <div class="right">
           <input type="button" value="Post Comment" class="greenBtn" id="postCommentbtn" />
        </div>
      </div>
       </c:if>
       
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
   		var isadmin=$("#isadmin").val();
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
        $("#delbtn").click(function(){
          $.ajax({
            url: '/service/delpost?id=' + $("#blogid").val(),
            type: 'GET',
            success: function(result) {
              if (result.responseCode == 200) {
                notifyGoHome('Success',"This post has been deleted !");
              } else {
                notify('error',"There is some issue with deletion");
              }
            }
          });
        });
        $(".comments-sec").on("click",".del-btn",function(){
          var ths=$(this);
          var id=ths.next('input').val();
           $.ajax({
            url: '/service/delcomment?id=' + id,
            type: 'GET',
            success: function(result) {
              if (result.responseCode == 200) {
                notify('Success',"This post has been deleted !");
                ths.parent().parent().parent().remove();
              } else {
                notify('error',"There is some issue with deletion");
              }
            }
          }); 
        });
    function loadComments(){
    	
    	$.ajax({
            url: '/service/comments?id=' + $("#blogid").val(),
            type: 'GET',
            success: function(result) {
              if (result.responseCode == 200) {
            	  var ul=$(".comments-sec").find('ul');
            	for(var i=0;i<result.data.length;i++){
            	    var btn='';
            	    
            	    if(result.data[i].email=='${user.username}' || isadmin){
            	      btn='<a class="del-btn">del</a>';
            	    }
            		ul.append('<li>'+
            		           '<div class="comment-bx">'+
            		              '<div class="comment-hd">'+
            		              '<div class="left">'+
            		              result.data[i].name+'('+result.data[i].email+')|'+result.data[i].dateTime+
            		              '</div>'+
            		              '<div class="right">'+
            		              btn+
            		              '<input type="hidden" value="'+result.data[i].id+'"/>'+
            		              '</div>'+
            		              '</div>'+
            		              '<div class="comment-bdy">'+
            		               '<pre>'+
            		               '</pre>'+
            		              '</div>'+
            		           '</div>'+
            		         '</li>');
            		ul.find('li:last-child').find(".comment-bdy pre").text(result.data[i].content);
            		}
            	
              } else {
                notify('error',"There is some issue with comments addition");
                
              }
            }
          });
    }
    function validComments(){
    	var content=$("#comment").val();
    	
    	if(content.trim()==''  ){
    		notify("error","Write something ! We do not like Silent comments.");
    		return false;
    	}
    	return true;
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
               if (result.responseCode == 200) {
                 var btn='<a class="del-btn">del</a>';
            	   var ul=$(".comments-sec").find('ul');
            	       ul.append('<li>'+
        		           '<div class="comment-bx">'+
        		              '<div class="comment-hd">'+
            		              '<div class="left">'+
            		              result.data.name+'('+result.data.email+')|'+result.data.dateTime+
            		              '</div>'+
            		              '<div class="right">'+
            		              btn+
            		              '<input type="hidden" value="'+result.data.id+'"/>'+
            		              '</div>'+
        		              '</div>'+
        		              '<div class="comment-bdy">'+
        		               '<pre>'+
        		               '</pre>'+
        		              '</div>'+
        		           '</div>'+
        		         '</li>');
            	       ul.find('li:last-child').find(".comment-bdy pre").text(result.data.content);
            	       $("#comment").val("");
               	 } else {
               	  notify("failure","Opps something went wrong. Can you write this to admin@coderszone.in?");
                 }
             }
           });
    }
       
     </script>
  </body>

</html>
