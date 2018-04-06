package pyjah.client.pkg;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;


public class ClientController {
	private HashMap messageFields = new HashMap();
	private String user;
	@FXML private TextArea messageBody = new TextArea();
	@FXML private TextField toLine = new TextField();
	@FXML private TextField subjectLine = new TextField();
	@FXML private Button sendButton = new Button();
	@FXML private ListView<String> subjectList;
	
	
	//Method to send the input from the GUI fields to a location on button click.
	// As of now it just prints the text fields inputted. 
	public void handleSendButtonClick() {
		messageFields.put("Recipient", toLine.getText());
		messageFields.put("Subject", subjectLine.getText());
		messageFields.put("Message", messageBody.getText());
		populateMessages();
		System.out.println("Recipient:\n" + messageFields.get("Recipient") +"\n\n" + "Subject:\n" + messageFields.get("Subject")
							+ "\n\n" + "Message Body:\n" + messageFields.get("Message"));
	}
	
	
	public void populateMessages() {
		ObservableList<String> subjects = FXCollections.observableArrayList();
		subjects.add("Test subject");
		subjectList.setItems(subjects);
	}
	
	
	public String getUser() {
		return user;
	}

}
