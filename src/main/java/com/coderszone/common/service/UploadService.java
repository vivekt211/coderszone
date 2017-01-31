package com.coderszone.common.service;

import com.coderszone.common.exception.CommonServiceException;
import com.coderszone.common.exception.DataBaseAccessException;


public interface UploadService {

	String uploadFileS3(String ext, String contentType, byte[] file) throws CommonServiceException;

	String getSignedUrl(String key) throws CommonServiceException;
}
