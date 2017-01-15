
	
	$('#ufileupload').fileupload({
        dataType: 'json',
        
        done: function (e, data) {
            $.each(data.result, function (index, file) {
            	
            	$("#ufileName").val(file.fileName);
            	$("#ufileSize").val(file.fileSize);
            	$("#ufileType").val(file.fileType);
            	$("#ufileExt").val(file.fileExt);
            	$("#ulogoImg").attr("src","/service/upfile/get/"+file.fileName+"/"+file.fileExt);
            	$("#url").html("/service/upfile/get/"+file.fileName+"/"+file.fileExt);
            }); 
        },
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('#uprogress .upload-bar').css(
	            'width',
	            progress + '%'
	        );
   		},
   		
		dropZone: $('#dropzone')
    });
	