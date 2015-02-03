/** Authors -	William Newberry V
 * 				Trevor Overfelt
 * 				Jacob Collins
 * 
 * Date last modified: 4/27/14
 * 
 * Constructs the message object by creating a session based off of 
 * the users configuration settings. Authenticates the users email/
 * password and gives a pop up if the authentication fails. Then
 * construct the message with the text fields passed from the
 * email transmission dialog.
 */

package edu.clemson.cpsc215.assignment3;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.*;
import javax.swing.JOptionPane;
import java.util.*;

public class MailMan {
	private Session sesh;
	private Properties props;
	private Message msg;
	private String userName;
	private String passWord;
	private PasswordAuthentication passAuth;
	private Authenticator auth;
	
	/**
	 * 
	 * @param admin Passed configuration settings found in the data store
	 * Construct the properties and session of the outgoing message
	 */
	public MailMan(Configuration admin) {
		//Set the properties with the passed configuration
		this.props = new Properties();
		this.userName = admin.getEAddr();
		this.passWord = admin.getpWrd();
		this.passAuth = new PasswordAuthentication(userName, passWord);
	
		//Authenticators require special treatment because no one could figure
		//out a better and easier way of doing this.
		this.auth = new Authenticator() {
			
			private PasswordAuthentication pass = passAuth;
			
			protected PasswordAuthentication getPasswordAuthentication() { 
				return pass;
			}
		};
		
		/* Puts for setting protocol (smtp), identifying routing port, getting
		   the protocol (smtp) of the user's email*/
		this.props.put("mail.transport.protocol", "smtp");
		
		this.props.put("mail.smtp.socketFactory.class",  "javax.net.ssl.SSLSocketFactory");
		//IP Address?
		this.props.put("mail.smtp.host", DataStore.getInstance().getConfig().
				getIPAddr());
		
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.port", DataStore.getInstance().getConfig().
				getPort());
		//should set timeout to 10 seconds
		this.props.put("mail.smtp.connectiontimeout", 10000);
		this.props.put("mail.smtp.timeout", 10000);

		//thank you again for the opportunity to fix it!
		this.props.put("mail.smtp.auth", "true");
		
		//Create the session using the above properties and authenticating
		this.sesh = Session.getInstance(props, auth);
		
		//Create a message attached to the session
		this.msg = new MimeMessage(sesh);
	}
	
	/**
	 * 
	 * @param body Test passed from the dialog
	 * @param att Attached file passed form the dialog
	 * @param subj Message subject passed from the dialog
	 * @param rcpt Message recipient passed from the dialog
	 * @param cc Message cc recipient passed from the dialog
	 * @param bcc Message bcc recipient passed form the dialog
	 * 
	 * Construct the message depending on the items passed from the dialog
	 */
	public void makeNSend(String body, String att, String subj, String rcpt,
			String cc, String bcc) {
		//Now to try to send the message.
		try {	
			/* Set a multi part message using the provided image file
			 * if one is given
			 */
			
			if(!att.isEmpty()) {	
				BodyPart msgBodPart = new MimeBodyPart();
				msgBodPart.setText(body);
				
				Multipart mult = new MimeMultipart();
				mult.addBodyPart(msgBodPart);
				
				msgBodPart = new MimeBodyPart();
				DataSource src = new FileDataSource(att);
				msgBodPart.setDataHandler(new DataHandler(src));
				msgBodPart.setFileName(att);
				mult.addBodyPart(msgBodPart);
				this.msg.setContent(mult);
			} else {
				/*If a file is not provided, just construct the message 
				with the given body.*/
				this.msg.setText(body);
			}
			
			this.msg.setRecipient(RecipientType.TO, new InternetAddress(rcpt));
			if(!bcc.isEmpty())
				this.msg.setRecipient(RecipientType.BCC, new 
						InternetAddress(bcc));
			if(!cc.isEmpty())
				this.msg.setRecipient(RecipientType.CC, new 
						InternetAddress(cc));
			this.msg.setFrom(new InternetAddress(this.userName));
			this.msg.setSubject(subj);
					
			/*Provide messages if anything went wrong or the message was 
			sent successfully */
			
			Transport.send(this.msg);
			JOptionPane.showMessageDialog(null, "Message Sent!", 
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Message failed to send," 
					+ " please check your configurations", "Message failure",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public String getPass() {
		return passWord;
	}
	
	public String getUser() {
		return userName;
	}
}