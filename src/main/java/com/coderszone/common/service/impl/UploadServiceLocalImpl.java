package com.coderszone.common.service.impl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.common.exception.CommonServiceException;
import com.coderszone.common.service.UploadService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UploadServiceLocalImpl implements UploadService {

	@Value("${upload.path}")
	private String imgpath;

	@Override
	public String uploadFileS3(String ext, String contentType, byte[] file) throws CommonServiceException{
		
		try {
			String name = Long.toString(new Date().getTime());
			String fileName = (new StringBuilder(String.valueOf(imgpath))).append("\\").append(name).append(".").append(ext).toString();
			OutputStream out = null;
			try {
			    out = new BufferedOutputStream(new FileOutputStream(fileName));
			    out.write(file);
			} finally {
			    if (out != null) out.close();
			}
			return name;
		}catch(IOException ex){
			throw new CommonServiceException("Uploading file to local has some Issues ("+ex.getMessage()+")");
		}
	}
	
	@Override
	public String getSignedUrl(String key) throws CommonServiceException {
		try{
			String fileName = (new StringBuilder(String.valueOf(imgpath))).append("\\").append(key).toString();
			return fileName;
		}catch(Exception ex){
			throw new CommonServiceException("Sorry This call is not meant for local");
		}
	}

	@Override
	public InputStream getStreamObject(String key) throws CommonServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
