package pyjah.util.pkg;

import java.util.*;

public class Message {
	String sender;
	String recipient;
	String subject;
	String body;
	String timeStamp;
	String status;
	// HashMap messageContents;

	public Message(String sender, String recipient, String subject, String body, String timeStamp, String status) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
		this.timeStamp = timeStamp;
		this.status = status;
	}
	
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	
	

}
