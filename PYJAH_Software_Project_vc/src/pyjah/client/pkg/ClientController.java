package pyjah.client.pkg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
	public User user = new User();
	private HashMap messageFields = new HashMap();
	@FXML private TextArea messageBody = new TextArea();
	@FXML private TextField toLine = new TextField();
	@FXML private TextField subjectLine = new TextField();
	@FXML private Button sendButton = new Button();
	
	
	//Method to send the output from the GUI fields to a location
	public void handleSendButtonClick() {
		user.sendMessage(messageFields, messageBody, toLine, subjectLine);
		System.out.println("Recipient:\n" + messageFields.get("Recipient") +"\n\n" + "Subject:\n" + messageFields.get("Subject")
							+ "\n\n" + "Message Body:\n" + messageFields.get("Message"));
	}
	

}
