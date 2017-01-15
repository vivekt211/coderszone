package com.coderszone.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.coderszone.common.beans.FileMeta;
import com.coderszone.common.service.UploadService;

@Controller
@RequestMapping("/upfile")
public class FileuploadController {
	
	@Value("${upload.path}")
	private String imgpath;
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		System.out.println("Upload request recieved ");
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		Iterator itr = request.getFileNames();
		MultipartFile mpf = null;
		FileMeta fileMeta = null;
		while (itr.hasNext()) {
			mpf = request.getFile((String) itr.next());
			InputStream stream = mpf.getInputStream();
			System.out.println((new StringBuilder(String.valueOf(mpf.getOriginalFilename()))).append(" uploaded! ").append(files.size()).toString());
			fileMeta = new FileMeta();
			fileMeta.setFileName(FilenameUtils.getBaseName(mpf.getOriginalFilename()));
			fileMeta.setFileSize(mpf.getSize() / 1024L);
			fileMeta.setFileType(mpf.getContentType());
			fileMeta.setFileExt(FilenameUtils.getExtension(mpf.getOriginalFilename()));
			//OutputStream out = new FileOutputStream((new StringBuilder(String.valueOf(imgpath))).append(mpf.getOriginalFilename()).toString());
			//byte buf[] = new byte[1024];
			//int len;
			//while ((len = stream.read(buf)) > 0)
			//	out.write(buf, 0, len);
			byte[] bytes = IOUtils.toByteArray(stream);
			String url=uploadService.uploadFileS3(fileMeta.getFileExt(), mpf.getContentType(), bytes);
			System.out.println(url);
			fileMeta.setFileName(url);
			files.add(fileMeta);
			for (; files.size() > 20; files.remove(0))
				;
		}
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		try {
			jsonConverter.write(files, jsonMimeType, new ServletServerHttpResponse(response));
		} catch (HttpMessageNotWritableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/get/{base}/{ext}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, @PathVariable String base, @PathVariable String ext) {
		try {
			Path source = Paths.get((new StringBuilder(String.valueOf(imgpath))).append(base).append(".").append(ext).toString(), new String[0]);
			String contType=Files.probeContentType(source);
			System.out.println(contType);
			
			String url=uploadService.getSignedUrl(base+"."+ext);
			System.out.println("UR: "+url);
			URI path=new URI(url);
			System.out.println(path.getPath());
			InputStream inputStream = path.toURL().openStream();
		       
			//String contentType = URLConnection.guessContentTypeFromStream(inputStream);
			//connection.connect();
			//String contentType = inputStream.getContentType();
			response.setContentType(contType);
			response.setHeader("cache-control", "private");
			response.setHeader("content-type",contType);
			ServletOutputStream out = response.getOutputStream();
			//FileInputStream fin = new FileInputStream(url);
			BufferedInputStream bin = new BufferedInputStream(inputStream);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			for (int ch = 0; (ch = bin.read()) != -1;)
				bout.write(ch);
			inputStream.close();
			bin.close();
			bout.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getImgpath() {
		return imgpath;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}
}
