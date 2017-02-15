package com.coderszone.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.coderszone.blog.controller.BlogController;
import com.coderszone.common.beans.FileMeta;
import com.coderszone.common.exception.CommonServiceException;
import com.coderszone.common.service.UploadService;

@Controller
public class FileuploadController {
	static Logger log = Logger.getLogger(FileuploadController.class.getName());

	@Value("${coderszone.context}")
	private String rootContext;
	
	public String getRootContext() {
		return rootContext;
	}

	public void setRootContext(String rootContext) {
		this.rootContext = rootContext;
	}

	@Autowired
	@Qualifier(value="uploadServiceImpl")
	private UploadService uploadService;

	@RequestMapping(value = "/upfile/upload", method = RequestMethod.POST)
	public Object upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		log.debug("Upload file request recieved /upfile/upload");
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		Iterator itr = request.getFileNames();
		MultipartFile mpf = null;
		FileMeta fileMeta = null;
		while (itr.hasNext()) {
			mpf = request.getFile((String) itr.next());
			InputStream stream = mpf.getInputStream();
			log.debug("/upfile/upload "+(new StringBuilder(String.valueOf(mpf.getOriginalFilename()))).append(" uploaded! ").append(files.size()).toString());
			fileMeta = new FileMeta();
			fileMeta.setFileName(FilenameUtils.getBaseName(mpf.getOriginalFilename()));
			fileMeta.setFileSize(mpf.getSize() / 1024L);
			fileMeta.setFileType(mpf.getContentType());
			fileMeta.setFileExt(FilenameUtils.getExtension(mpf.getOriginalFilename()));
			byte[] bytes = IOUtils.toByteArray(stream);
			String url;
			try {
				url = uploadService.uploadFileS3(fileMeta.getFileExt(), mpf.getContentType(), bytes);
				log.debug("/upfile/upload url after uploading to s3 : "+url);
				fileMeta.setFileName(url);
			} catch (CommonServiceException e) {
				log.error("/upfile/upload uploading to s3 failed "+e.getMessage());
			}
			
			files.add(fileMeta);
			for (; files.size() > 20; files.remove(0))
				;
		}
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		try {
			jsonConverter.write(files, jsonMimeType, new ServletServerHttpResponse(response));
		} catch (HttpMessageNotWritableException e) {
			log.error("/upfile/upload uploading to s3 failed HttpMessageNotWritableException |"+e.getMessage());
		} catch (IOException e) {
			log.error("/upfile/upload uploading to s3 failed IOException |"+e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/upfile/get/{base}/{ext}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String base, @PathVariable String ext) {
		log.debug("request recieve /upfile/get/"+base+"/"+ext+" ");
		try {
			InputStream inputStream=null;
			try {
				inputStream = uploadService.getStreamObject(base+"."+ext);
			} catch (CommonServiceException e) {
				log.error("request recieve /upfile/get/"+base+"/"+ext+" resulted in error | "+e.getMessage());
			}
			response.setHeader("cache-control", "private");
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bin = new BufferedInputStream(inputStream);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			for (int ch = 0; (ch = bin.read()) != -1;)
				bout.write(ch);
			inputStream.close();
			bin.close();
			bout.close();
			out.close();
			log.debug("request served for /upfile/get/"+base+"/"+ext);
		} catch (IOException e) {
			log.error("request recieve /upfile/get/"+base+"/"+ext+" resulted in error | "+e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/upfile/normupload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadNorm(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		log.debug("request recieved for /normupload ");
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		Iterator itr = request.getFileNames();
		MultipartFile mpf = null;
		FileMeta fileMeta = null;
		String curl=rootContext;
		if (itr.hasNext()) {
			mpf = request.getFile((String) itr.next());
			InputStream stream = mpf.getInputStream();
			log.debug("/normupload file recieved "+(new StringBuilder(String.valueOf(mpf.getOriginalFilename()))).append(" ").append(files.size()).toString());
			fileMeta = new FileMeta();
			fileMeta.setFileName(FilenameUtils.getBaseName(mpf.getOriginalFilename()));
			fileMeta.setFileSize(mpf.getSize() / 1024L);
			fileMeta.setFileType(mpf.getContentType());
			fileMeta.setFileExt(FilenameUtils.getExtension(mpf.getOriginalFilename()));
			byte[] bytes = IOUtils.toByteArray(stream);
			String url;
			try {
				url = uploadService.uploadFileS3(fileMeta.getFileExt(), mpf.getContentType(), bytes);
				log.debug(" /normupload file uploaded successfully to s3 . url |"+url);
				curl=curl+"service/upfile/get/"+url+"/"+fileMeta.getFileExt();
				fileMeta.setFileName(url);
			} catch (CommonServiceException e) {
				log.error("request /normupload resulted in error | "+e.getMessage());
			}
			
			files.add(fileMeta);
			
		}
		return curl;
	}
	
	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}
}
