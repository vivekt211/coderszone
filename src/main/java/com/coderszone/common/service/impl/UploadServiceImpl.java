package com.coderszone.common.service.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.common.service.UploadService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UploadServiceImpl implements UploadService {

	@Value("${amazon.s3.accesskey}")
	private String awsAccessKey;
	@Value("${amazon.s3.secretkey}")
	private String awsSecretKey;

	@Override
	public String uploadFileS3(String ext, String contentType, byte[] file){
		
		try {
			AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
			S3Service s3Service = new RestS3Service(awsCredentials);
			S3Bucket bucket = s3Service.getBucket("coderszone");
			// File fileData = new
			String name = Long.toString(new Date().getTime());
			S3Object fileObject = new S3Object(name +"."+ext, file);
			fileObject.setContentType(contentType);
			s3Service.putObject(bucket, fileObject);
			return name;
		} catch (S3ServiceException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getSignedUrl(String key) {
		String signedUrl = null;
		try {
			AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
			S3Service s3Service = new RestS3Service(awsCredentials);
			S3Bucket bucket = s3Service.getBucket("coderszone");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 5);
			Date expiryDate = cal.getTime();
			// Create a signed HTTP GET URL valid for 5 minutes. If you use the
			// generated URL in a web browser within 5 minutes, you will be able
			// to view the object's contents. After 5 minutes, the URL will no
			// longer work and you will only see an Access Denied message.
			signedUrl = s3Service.createSignedGetUrl(bucket.getName(), key, expiryDate, false);

			System.out.println("Signed URL: " + signedUrl);
		} catch (S3ServiceException ex) {
			ex.printStackTrace();
		}
		return signedUrl;
	}

	public static void main(String[] args) {
		try {
			String awsAccessKey = "AKIAJ3ZSHEHKR4NEAZBA";
			String awsSecretKey = "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";
			AWSCredentials awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
			S3Service s3Service = new RestS3Service(awsCredentials);
			// S3Bucket[] myBuckets = s3Service.listAllBuckets();
			S3Bucket bucket = s3Service.getBucket("coderszone");
			/*
			 * for(S3Bucket bucket:myBuckets){
			 * System.out.println("---------"+bucket.getName()); }
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

}
