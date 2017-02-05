package com.coderszone.common.mail;

import java.util.List;

import javax.mail.MessagingException;

import com.coderszone.common.beans.MailMessage;


public interface MailService {

/*	void sendMail(String toId, String mailBody) throws Exception;

	void sendMailToMultiple(List<String> toIdList, String mailBody) throws MessagingException;
	
	int getQueueLength();

	void enqueueMailMessage(MailMessage mailMsg);

	void processQueue();*/

	void sendVerificationCode(String toId, String code) throws Exception;

	void sendNewPassCode(String toId, String code) throws Exception;
}
