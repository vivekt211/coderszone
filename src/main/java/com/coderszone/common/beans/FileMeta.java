package com.coderszone.common.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"bytes"})
public class FileMeta {

	private String fileName;
	private long fileSize;
	private String fileType;
	private String fileExt;
	
	private byte[] bytes;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long l) {
		this.fileSize = l;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getFileExt() {
		return fileExt;
	}
}
