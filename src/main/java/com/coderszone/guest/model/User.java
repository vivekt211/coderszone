package com.coderszone.guest.model;

public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private String lastModDate;
	private String lastModBy;
	private int verified;
	private String verificationCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getLastModBy() {
		return lastModBy;
	}
	public void setLastModBy(String lastModBy) {
		this.lastModBy = lastModBy;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
