package com.coderszone.common.mail.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderszone.common.beans.MailMessage;
import com.coderszone.common.controller.FileuploadController;
import com.coderszone.common.exception.MailServiceException;
import com.coderszone.common.mail.MailService;

@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService{
	static Logger log = Logger.getLogger(MailServiceImpl.class.getName());

	@Value("${mail.smtpserver}")
	private String SMTP_HOST_NAME;
	@Value("${mail.userid}")
    private String SMTP_AUTH_USER;
	@Value("${mail.password}")
    private String SMTP_AUTH_PWD;
	@Value("${mail.userid}")
	private String FROM;
	@Value("${mail.smtp.port}")
	private String PORT;
	@Value("${mail.smtp.socketFactory.class}")
	private String SMTP_SOCKETFACTORY_CLASS;
	
	
	private Queue<MailMessage> mailMessageQueue;
	
	@Override
    public void sendVerificationCode(String toId,String code) throws MailServiceException{
		log.debug("sendVerificationCode to="+toId+", code="+code);
        Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class",SMTP_SOCKETFACTORY_CLASS);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", PORT);
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
					}
				});
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toId));
			message.setSubject("Welcome ! Your CodersZone Registration Code");
			message.setContent("<p style=\"text-align:center\"><u><font color=\"#e76363\" face=\"Times New Roman\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Coders</font><font face=\"Impact\" color=\"#6ba54a\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Zone</font><font color=\"#6ba54a\" style=\"font-family: Oxygen, sans-serif; font-size: 2.5rem; font-weight: 700; line-height: 1;\">.<font face=\"Comic Sans MS\">in</font></font></u></p>" +
					"<h3 style=\"text-align:center\">Welcome to the <b>CodersZone </b>community.</h3>" +
					"<p style=\"text-align: center;\">Please Enter the blow verification code</p>" +
					"<h1 style=\"text-align: center;\"><font style=\"background-color: rgb(255, 255, 0);\" color=\"#311873\">" +
					code +
					"</font></h1>" +
					"<p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><font color=\"#311873\" style=\"background-color: rgb(255, 255, 255);\">" +
					"Please note that this code is valid for only 24 hours. After 24 hours you registration will be deleted." +
					"</font></p><hr><p style=\"text-align: center;\"><font color=\"#cec6ce\">" +
					"*This portal has been developed for opensource and knowledge sharing purpose . By entering the above code you accept that any legal or other issue regarding the blog post and its content written by you shall be the responsibility of yourself. The owner and developer of this portal would have no responsibility of the content of this portal." +
					"</font></p><p style=\"text-align:center\">" +
					"</p>"
					,"text/html");

			Transport.send(message);
			log.debug("sendVerificationCode to="+toId+", code="+code+" COMPLETED ");

		} catch (MessagingException e) {
			log.error("sendVerificationCode to="+toId+", code="+code+" FAILED | "+e.getMessage());
			throw new MailServiceException(e);
		}
    }

	@Override
    public void sendNewPassCode(String toId,String code) throws MailServiceException{
		log.debug("sendNewPassCode to="+toId+", code="+code);
        Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class",SMTP_SOCKETFACTORY_CLASS);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", PORT);
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
					}
				});
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toId));
			message.setSubject("CodersZone New Password");
			message.setContent("<p style=\"text-align:center\"><u><font color=\"#e76363\" face=\"Times New Roman\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Coders</font><font face=\"Impact\" color=\"#6ba54a\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Zone</font><font color=\"#6ba54a\" style=\"font-family: Oxygen, sans-serif; font-size: 2.5rem; font-weight: 700; line-height: 1;\">.<font face=\"Comic Sans MS\">in</font></font></u></p>" +
					"<h3 style=\"text-align:center\">Welcome to the <b>CodersZone </b>community.</h3>" +
					"<p style=\"text-align: center;\">Your new password is as below</p>" +
					"<h1 style=\"text-align: center;\"><font style=\"background-color: rgb(255, 255, 0);\" color=\"#311873\">" +
					code +
					"</font></h1>" +
					"<p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><font color=\"#311873\" style=\"background-color: rgb(255, 255, 255);\">" +
					"Please change the password after login." +
					"</font></p><hr><p style=\"text-align: center;\"><font color=\"#cec6ce\">" +
					"</font></p><p style=\"text-align:center\">" +
					"</p>"
					,"text/html");

			Transport.send(message);
			log.debug("sendNewPassCode to="+toId+", code="+code+" COMPLETED ");
		} catch (MessagingException e) {
			log.error("sendNewPassCode to="+toId+", code="+code+" FAILED | "+e.getMessage());
			throw new MailServiceException(e);
		}
    }
	
	@Override
    public void sendPasswordChangeAlert(String toId) throws MailServiceException{
		log.debug("sendPasswordChangeAlert to="+toId);
        Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class",SMTP_SOCKETFACTORY_CLASS);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", PORT);
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
					}
				});
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toId));
			message.setSubject("CodersZone Password has been changed");
			message.setContent("<p style=\"text-align:center\"><u><font color=\"#e76363\" face=\"Times New Roman\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Coders</font><font face=\"Impact\" color=\"#6ba54a\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Zone</font><font color=\"#6ba54a\" style=\"font-family: Oxygen, sans-serif; font-size: 2.5rem; font-weight: 700; line-height: 1;\">.<font face=\"Comic Sans MS\">in</font></font></u></p>" +
					"<h3 style=\"text-align:center\">Welcome to the <b>CodersZone </b>community.</h3>" +
					"<p style=\"text-align: center;\">Your password has been change</p>" +
					"<p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><font color=\"#311873\" style=\"background-color: rgb(255, 255, 255);\">" +
					"If its not you then please goto forget password link and reset your password" +
					"</font></p><hr><p style=\"text-align: center;\"><font color=\"#cec6ce\">" +
					"</font></p><p style=\"text-align:center\">" +
					"</p>"
					,"text/html");

			Transport.send(message);
			log.debug("sendPasswordChangeAlert to="+toId+" COMPLETED ");
		} catch (MessagingException e) {
			log.error("sendPasswordChangeAlert to="+toId+" FAILED | "+e.getMessage());
			throw new MailServiceException(e);
		}
    }
	public String getSMTP_HOST_NAME() {
		return SMTP_HOST_NAME;
	}

	public void setSMTP_HOST_NAME(String sMTP_HOST_NAME) {
		SMTP_HOST_NAME = sMTP_HOST_NAME;
	}

	public String getSMTP_AUTH_USER() {
		return SMTP_AUTH_USER;
	}

	public void setSMTP_AUTH_USER(String sMTP_AUTH_USER) {
		SMTP_AUTH_USER = sMTP_AUTH_USER;
	}

	public String getSMTP_AUTH_PWD() {
		return SMTP_AUTH_PWD;
	}

	public void setSMTP_AUTH_PWD(String sMTP_AUTH_PWD) {
		SMTP_AUTH_PWD = sMTP_AUTH_PWD;
	}

	public String getFROM() {
		return FROM;
	}

	public void setFROM(String fROM) {
		FROM = fROM;
	}

	/*@Override
	public void sendMailToMultiple(List<String> toIdList, String mailBody) throws MessagingException {
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setContent(mailBody, "text/plain");
        message.setFrom(new InternetAddress(FROM));
        for(String toId:toIdList){
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(toId));
        }
        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
	}*/

	public void enqueueMailMessage(MailMessage mailMsg) {
		if(mailMessageQueue == null){
			mailMessageQueue = new LinkedList<MailMessage>();
			mailMessageQueue.offer(mailMsg);
		}else{
			mailMessageQueue.offer(mailMsg);
		}
	}

	public int getQueueLength() {
		if(mailMessageQueue == null){
			mailMessageQueue = new LinkedList<MailMessage>();
			return mailMessageQueue.size();
		}else{
			return mailMessageQueue.size();
		}
	}
	
	public void processQueue() {
		if(mailMessageQueue != null){
			while(!mailMessageQueue.isEmpty()){
				MailMessage msg=mailMessageQueue.poll();
				if(msg!=null){
					try {
						sendVerificationCode(msg.getTo(),msg.getBody());
						//alertReportDao.updateMailSentStatus(msg.getReportId(),"SUCCESS",new Date());
					} catch (Exception e) {
						e.printStackTrace();
						//alertReportDao.updateMailSentStatus(msg.getReportId(),"FAIL",new Date());
					}
				}
			}
		}
	}

	/*public AlertReportDao getAlertReportDao() {
		return alertReportDao;
	}

	public void setAlertReportDao(AlertReportDao alertReportDao) {
		this.alertReportDao = alertReportDao;
	}*/
	public static void main(String[] args){
		
		/*String to = "linkvivek.vt@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "admin@coderszone.in";

	      // Assuming you are sending email from localhost
	      String host = "smtp.zoho.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }*/
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.zoho.com");
        props.put("mail.smtp.port","465");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.ssl.enable","true");

        Authenticator auth = new  javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                String username = "admin@coderszone.in";
                String password = "omshiv211";
                return new PasswordAuthentication(username, password);
             }
         };
        	
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        try{
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setContent("Hi testing ", "text/plain");
        message.setFrom(new InternetAddress("admin@coderszone.in"));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress("linkvivek.vt@gmail.com"));
        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String pORT) {
		PORT = pORT;
	}

	public String getSMTP_SOCKETFACTORY_CLASS() {
		return SMTP_SOCKETFACTORY_CLASS;
	}

	public void setSMTP_SOCKETFACTORY_CLASS(String sMTP_SOCKETFACTORY_CLASS) {
		SMTP_SOCKETFACTORY_CLASS = sMTP_SOCKETFACTORY_CLASS;
	}
}
