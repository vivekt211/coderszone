package com.coderszone.common.mail.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.zoho.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("admin@coderszone.in","omshiv211");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@coderszone.in"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("linkvivek.vt@gmail.com"));
			message.setSubject("CodersZone Registration Code");
			message.setContent("<p style=\"text-align:center\"><u><font color=\"#e76363\" face=\"Times New Roman\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Coders</font><font face=\"Impact\" color=\"#6ba54a\" style=\"font-size: 2.5rem; font-weight: 700; line-height: 1;\">Zone</font><font color=\"#6ba54a\" style=\"font-family: Oxygen, sans-serif; font-size: 2.5rem; font-weight: 700; line-height: 1;\">.<font face=\"Comic Sans MS\">in</font></font></u></p>" +
					"<h3 style=\"text-align:center\">Welcome to the <b>CodersZone </b>community.</h3>" +
					"<p style=\"text-align: center;\">Please Enter the blow verification code</p>" +
					"<h1 style=\"text-align: center;\"><font style=\"background-color: rgb(255, 255, 0);\" color=\"#311873\">" +
					"qawsedrf" +
					"</font></h1>" +
					"<p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><font color=\"#311873\" style=\"background-color: rgb(255, 255, 255);\">" +
					"Please note that this code is valid for only 24 hours. After 24 hours you registration will be deleted." +
					"</font></p><hr><p style=\"text-align: center;\"><font color=\"#cec6ce\">" +
					"*This portal has been developed for opensource and knowledge sharing purpose . By entering the above code you accept that any legal or other issue regarding the blog post and its content written by you shall be the responsibility of yourself. The owner and developer of this portal would have no responsibility of the content of this portal." +
					"</font></p><p style=\"text-align:center\">" +
					"</p>"
					,"text/html");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}