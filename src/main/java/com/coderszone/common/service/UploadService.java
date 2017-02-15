package com.coderszone.common.service;

import java.io.InputStream;

import com.coderszone.common.exception.CommonServiceException;
import com.coderszone.common.exception.DataBaseAccessException;


public interface UploadService {

	String uploadFileS3(String ext, String contentType, byte[] file) throws CommonServiceException;

	String getSignedUrl(String key) throws CommonServiceException;

	InputStream getStreamObject(String string)throws CommonServiceException;
}
