package com.coderszone.common.service;

import com.coderszone.common.exception.DataBaseAccessException;


public interface UploadService {

	String uploadFileS3(String ext, String contentType, byte[] file);

	String getSignedUrl(String key);
}
