package pyjah.client.pkg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pyjah.util.pkg.Email;

public class ClientController implements Initializable {
	private HashMap messageFields = new HashMap();
	private User user;
	@FXML private TextArea messageBody = new TextArea();
	@FXML private TextField toLine = new TextField();
	@FXML private TextField subjectLine = new TextField();
	@FXML private Button sendButton = new Button();
	
	Client pyjahClient = new Client();
	
	//Method to send the input from the GUI fields to a location on button click.
	// As of now it just prints the text fields inputted. 
	public void handleSendButtonClick() {
		//Email email = new Email(toLine.getText(),subjectLine.getText(),messageBody.getText());
		
		//pyjahClient.sendEmail(email);
		
		
		/*
		messageFields.put("Recipient", toLine.getText());
		messageFields.put("Subject", subjectLine.getText());
		messageFields.put("Message", messageBody.getText());
		System.out.println("Recipient:\n" + messageFields.get("Recipient") +"\n\n" + "Subject:\n" + messageFields.get("Subject")
							+ "\n\n" + "Message Body:\n" + messageFields.get("Message"));
							
		*/	
		
		String sender;
		String recipient=toLine.getText();
		String body=messageBody.getText();
		String subject=subjectLine.getText();
		pyjahClient.sendMessage(recipient);
		pyjahClient.sendMessage(body);
		pyjahClient.sendMessage(subject);
		
	}
	
	public User getUser() {
		return user;
	}
	
	Thread thread1 = new Thread() {
		public void run() {
			pyjahClient.startClient();
		}
	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
