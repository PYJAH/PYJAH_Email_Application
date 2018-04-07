/*					
 * 				Modified and implemented by Ammanuel and Howie.
 *					This is the Email class.
 *
 *			This class is the message, renamed to Email.
 */
package pyjah.util.pkg;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Email implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2328129903740407780L;
	private String sender;
	private String recipient;
	private String subject;
	private String time;
	private String body;
	private String status = "unread";
	
	/*
	 *  Not sure if we still need this: HashMap email contents
	 */
	
	public Email() {
		
	}
	public Email(String recipient, String subject, String body) {
		this.recipient=recipient;
		this.subject=subject;
		this.body=body;
		
	}
	
	public Email(String sender, String recipient, String subject, String body) {
		this.sender = sender;
		this.recipient=recipient;
		this.subject=subject;
		this.body=body;
		
	}
	/*public Email(String recipient, String subject, String status) {
		/*		
		 * This print statement is to check if the stream is receiving the Email object
		 * System.out.println("The stream works.");
		 *	
		this.recipient = recipient;
		this.subject = subject;
		this.status = status;
		
	}*/
	

	public Email(String sender, String recipient, String subject, String body, String time,String status) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.time = time;
		this.body = body;
		this.status = status;
	}


	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.time = dateFormat.format(date); //2016/11/16 12:08:43

	}


	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}


	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*public String toString() {
		return "The recipient is: "+getRecipient()+"\n"+"The subject is: "+getSubject() + "\n" + "Body: " + getBody();
	}*/
	

}
