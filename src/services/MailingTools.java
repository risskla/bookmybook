package services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailingTools {
	
	public static void sendMail(String exp, String dest, String sujet, String msgContent) 
			throws AddressException, MessagingException{
		
	// create some properties and get the default Session
	Properties props = new Properties();
	//serveur smtp sortant
	String host = "smtp.gmail.com";
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");

	props.put("mail.debug", "true");
	props.put("mail.smtp.socketFactory.port", "465");

	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.socketFactory.fallback", "false");
	Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
	{ 
	protected PasswordAuthentication getPasswordAuthentication() 
	{
	return new PasswordAuthentication("clarisse.durand.henriot@gmail.com","Youpimoi174zert@");
	}
	});
	
	
	    // create a message
	    MimeMessage msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(exp));
	    InternetAddress[] address = {new InternetAddress(dest)};
	    msg.setRecipients(Message.RecipientType.TO, address);
	    InternetAddress[] addresscc = {new InternetAddress("clarisse.durand.henriot@gmail.com")};
	    msg.setRecipients(Message.RecipientType.CC, addresscc);
	    msg.setSubject(sujet);
	    msg.setSentDate(new Date());
	    // If the desired charset is known, you can use
	    // setText(text, charset)
	    msg.setText(msgContent);
	    
	    Transport.send(msg);		
	}
}