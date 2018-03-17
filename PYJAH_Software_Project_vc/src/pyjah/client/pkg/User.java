package pyjah.client.pkg;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class User {
	private String username;
	private String password;
	private HashMap inbox;
	private HashMap sentBox;

	public User() {
		this.username = username;
		this.inbox = inbox;
		this.sentBox = sentBox;
	}
	
	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String password) {
		this.username = username;
	}
	
	public User(String username, HashMap inbox, HashMap sentBox) {
		this.username = username;
		this.inbox = new HashMap(inbox);
		this.sentBox = new HashMap(sentBox);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public HashMap getInbox() {
		return inbox;
	}
	
	public HashMap getSentBox() {
		return sentBox;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
