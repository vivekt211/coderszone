package com.coderszone.common.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.common.controller.FileuploadController;
import com.coderszone.common.exception.CommonServiceException;
import com.coderszone.common.service.UploadService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UploadServiceImpl implements UploadService {
	
	static Logger log = Logger.getLogger(UploadServiceImpl.class.getName());

	@Value("${amazon.s3.accesskey}")
	private String awsAccessKey;
	@Value("${amazon.s3.secretkey}")
	private String awsSecretKey;
	@Value("${amazon.s3.bucket}")
	private String bucket;
	
	@Override
	public String uploadFileS3(String ext, String contentType, byte[] file){
		log.debug("uploadFileS3|upload file to s3 ");
		try {
			AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
			S3Service s3Service = new RestS3Service(awsCredentials);
			//S3Bucket s3Bucket = s3Service.getBucket(bucket);
			// File fileData = new
			String name = Long.toString(new Date().getTime());
			log.debug("uploadFileS3|file to be uploaded "+name);
			S3Object fileObject = new S3Object(name +"."+ext, file);
			fileObject.setContentType(contentType);
			s3Service.putObject(bucket, fileObject);
			log.debug("uploadFileS3|file "+name+" uploaded successfuly");
			return name;
		} catch (S3ServiceException e) {
			log.error("uploadFileS3|file  upload failed "+e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			log.error("uploadFileS3|file  upload failed "+e.getMessage());
		} catch (IOException e) {
			log.error("uploadFileS3|file  upload failed "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public String getSignedUrl(String key) {
		String signedUrl = null;
		AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
		S3Service s3Service = new RestS3Service(awsCredentials);
		//S3Bucket s3Bucket = s3Service.getBucket(bucket);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 5);
		Date expiryDate = cal.getTime();
		// Create a signed HTTP GET URL valid for 5 minutes. If you use the
		// generated URL in a web browser within 5 minutes, you will be able
		// to view the object's contents. After 5 minutes, the URL will no
		// longer work and you will only see an Access Denied message.
		signedUrl = s3Service.createSignedGetUrl(bucket, key, expiryDate, false);

		return signedUrl;
	}
	
	@Override
	public InputStream getStreamObject(String key) {
		log.debug("getStreamObject |key "+key);
		AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
		S3Service s3Service = new RestS3Service(awsCredentials);
		S3Object objectComplete = null;
		try {
			objectComplete = s3Service.getObject(bucket, key);
		} catch (S3ServiceException e1) {
			log.error(" getStreamObject s3Service.getObject |key "+key+"failed "+e1.getMessage());
		}
		log.debug("getStreamObject S3Object, got: " + objectComplete);
       InputStream stream=null;
		try {
			stream= objectComplete.getDataInputStream();
		} catch (ServiceException e) {
			log.error("getStreamObject | failed "+e.getMessage());
		}
		return stream;

	}

	public static void main(String[] args) {
		try {
			String awsAccessKey = "xxx";
			String awsSecretKey = "xxx+xxx";
			AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
			S3Service s3Service = new RestS3Service(awsCredentials);
			// S3Bucket[] myBuckets = s3Service.listAllBuckets();
			S3Bucket bucket = s3Service.getBucket("coderszonedev");
			
			/*  for(S3Bucket bucket:myBuckets){
			  System.out.println("---------"+bucket.getName()); }
			 */

		} catch (S3ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}
	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}


	

}
