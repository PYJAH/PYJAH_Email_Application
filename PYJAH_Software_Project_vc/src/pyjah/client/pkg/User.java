package pyjah.client.pkg;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class User {
	private String id;
	private HashMap inbox;
	private HashMap sentBox;

	public User() {
		this.id = id;
		this.inbox = inbox;
		this.sentBox = sentBox;
	}
	
	public User(String id) {
		this.id = id;
	}
	
	public User(String id, HashMap inbox, HashMap sentBox) {
		this.id = id;
		this.inbox = new HashMap(inbox);
		this.sentBox = new HashMap(sentBox);
	}
	
	public String getId() {
		return id;
	}
	
	public HashMap getInbox() {
		return inbox;
	}
	
	public HashMap getSentBox() {
		return sentBox;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setInbox(HashMap inbox) {
		this.inbox = inbox;
	}
	
	public void setSentBox(HashMap sentBox) {
		this.sentBox = sentBox;
	}

	public void sendMessage(HashMap messageFields, TextArea messageBody, TextField toLine, TextField subjectLine) {
		messageFields.put("Recipient", toLine.getText());
		messageFields.put("Subject", subjectLine.getText());
		messageFields.put("Message", messageBody.getText());

	}
}
