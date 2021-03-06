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
    <meta name="robots" content="noindex,nofollow">
    <link rel="icon" href="${context_root}/service/resources/img/favicon/favicon.ico" type="image/x-icon" /> 
    <link rel="shortcut icon" href="${context_root}/service/resources/img/favicon/favicon-32x32.png">
    <title>CodersZone | Write Blog</title>
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
  <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
    <!--[if IE]>
      <script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
    <script type="text/javascript" src="/service/resources/js/notice.js"></script>
    
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
        <div class="row stretched clearfix">
          <div class="small-12 medium-12 large-6 columns">
            <h2 style="color: #ccc;">Compose Blog</h2>
          </div>

          <div class="small-12 medium-12 large-6 columns">
            <!-- <ul class="breadcrumbs"  role="menubar" aria-label="breadcrumbs">
              <li role="menuitem"><a href="#">Home</a></li>
              <li role="menuitem"><a href="#">Blog</a></li>
              <li role="menuitem" class="current"><a href="#">Full Wide Grid</a></li>
            </ul> -->
          </div>
        </div>
      </div>
      <div class="containerwrite">
      <label class="lblwrt margin10">Please Enter Blog title [max 10 words]</label>
      <textarea class="row stretched clearfix margin10" id="heading" name="heading" placeholder="Please Enter Blog title [max 10 words]"></textarea>
      <label class="lblwrt margin10">Please Enter Blog Description [Max 40 words]</label>
      <textarea class="row stretched clearfix margin10" id="desc" name="desc" placeholder="Please Enter Blog Description [Max 40 words]"></textarea>
      <label class="lblwrt margin10">Please Enter comma seperated keywords (This will help in searching)</label>
       <textarea class="row stretched clearfix margin10" id="keywords" name="keywords" placeholder="Please Enter comma separated Tags (This will help in searching)"></textarea> 
      <label class="lblwrt margin10">Select tag(Category)</label>
      <select id="tags" class="row stretched" name="tags" >
         <c:forEach var="tag" items="${tagsList}" varStatus="i">
			<option value="${tag}">${tag }</option>
		</c:forEach>
      </select>
      <div id="summernote">

      </div>
      <div class="item-ful">
        <div class="right">
           <input type="button" value="POST" class="greenBtn" id="postbtn" />
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
    <script type="text/javascript" src="/service/resources/js/jquery.noty.packaged.js"></script>
    <script type="text/javascript" src="/service/resources/js/notice.js"></script>
      
    <script type="text/javascript">
     
	    function sendFile(file,editor,welEditable) {
	        data = new FormData();
	        data.append("file", file);
	        //console.log('image upload:', file, editor, welEditable);
	        //console.log(data);
	        $.ajax({
	            data: data,
	            type: "POST",
	            url: "/service/upfile/normupload",
	            cache: false,
	            contentType: false,
				processData: false,
	            success: function(url) {
	               $('#summernote').summernote('editor.insertImage', url);
	            },
	            error:  function(jqXHR, textStatus, errorThrown)
	            {
	              notify("error",textStatus+": Automatic Image upload failed");
	            }
	        });
	      }
         $('#summernote').summernote({
            height: 450 ,//set editable area's height
            callbacks : {
	            onImageUpload: function(files, editor, welEditable) {
	                sendFile(files[0],editor,welEditable);
	            }
            }
        });
         function valid(){
           var title = $("#heading").val();
           var desc =  $("#desc").val();
           var tags =  $("#tags").val();
           var keywords = $("#keywords").val();
           var htm =  $(".note-editable").html();
           
           if(title == '' ||desc == '' ||tags ==''||keywords==''||htm==''){
             notify("error","Please fill all the fields, Its important for your post visibility");
             return false;
           }
           return true;
         }
        $("#postbtn").click(function(){
          
           var title = $("#heading").val();
           var desc =  $("#desc").val();
           var tags =  $("#tags").val();
           var keywords = $("#keywords").val();
           var htm =  $(".note-editable").html();
           var data= '&keywords='+encodeURIComponent(keywords)+'&title='+encodeURIComponent(title)+"&desc="+encodeURIComponent(desc)+"&tags="+encodeURIComponent(tags)+"&content="+encodeURIComponent(htm);
           if(valid()){
             $.ajax({
               url: '/service/postblog',
               type: 'POST',
               data: data,
               success: function(result) {
                 notifyGoMyPage("success",result.message);
               },
               error:function(jqXHR, textStatus, errorThrown)
 	            {
 	              notify("error",textStatus+": Post failed");
 	            }
             });
           }
        });
     </script>
  </body>

</html>
