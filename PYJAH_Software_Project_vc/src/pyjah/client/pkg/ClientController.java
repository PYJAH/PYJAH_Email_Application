package pyjah.client.pkg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import pyjah.util.pkg.Email;

public class ClientController implements Initializable {

	@FXML
	private TextArea messageBody = new TextArea();
	@FXML
	private TextField toLine = new TextField();
	@FXML
	private TextField subjectLine = new TextField();
	@FXML
	private Button sendButton = new Button();
	@FXML
	Label userIdLabel;
	@FXML
	private RadioButton userARadioButton;
	@FXML
	private RadioButton userBRadioButton;
	@FXML
	private Button loginButton;
	@FXML
	private Tab composeTab;
	@FXML
	private Tab inboxTab;
	@FXML
	private Tab sentTab;
	
	ToggleGroup group = new ToggleGroup();

	private HashMap messageFields = new HashMap();
	private User user;
	private Email email;

	Client pyjahClient = new Client();

	// Method to send the input from the GUI fields to a location on button click.
	// As of now it just prints the text fields inputted.
	public void handleSendButtonClick() {
		this.email = new Email(user.getUsername(), toLine.getText(), subjectLine.getText(), messageBody.getText());// ,
																													// email.getTime(),
																													// "Unread");

		pyjahClient.sendEmail(email);

		toLine.clear();
		subjectLine.clear();
		messageBody.clear();

		/*
		 * messageFields.put("Recipient", toLine.getText());
		 * messageFields.put("Subject", subjectLine.getText());
		 * messageFields.put("Message", messageBody.getText());
		 * System.out.println("Recipient:\n" + messageFields.get("Recipient") +"\n\n" +
		 * "Subject:\n" + messageFields.get("Subject") + "\n\n" + "Message Body:\n" +
		 * messageFields.get("Message"));
		 * 
		 */

		/*
		 * String sender; String recipient=toLine.getText(); String
		 * body=messageBody.getText(); String subject=subjectLine.getText();
		 * pyjahClient.sendMessage(recipient); pyjahClient.sendMessage(body);
		 * pyjahClient.sendMessage(subject);
		 */

	}

	public void getUser() {
		this.user = new User();
		user.setUsername("Dude");
		this.userIdLabel.setText(this.user.getUsername());

	}

	Thread thread1 = new Thread() {
		public void run() {
			pyjahClient.startClient();
		}
	};

	public void setUserLabel() {
		// this.user.setUsername("Dude");

	}

	@FXML
	void loginOnButtonClick(ActionEvent event) {
		composeTab.setDisable(false);
		inboxTab.setDisable(false);
		sentTab.setDisable(false);
	}

	@FXML
	void radioSetToUserA(ActionEvent event) {
		userARadioButton.setToggleGroup(group);
		userARadioButton.setSelected(true);
		this.user = new User();
		user.setUsername("User A");
		this.userIdLabel.setText(this.user.getUsername());
	}

	@FXML
	void radioSetToUserB(ActionEvent event) {
		userBRadioButton.setToggleGroup(group);
		userBRadioButton.setSelected(true);
		this.user = new User();
		user.setUsername("User B");
		this.userIdLabel.setText(this.user.getUsername());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1r) {
		// user.setUsername("Dude");
		// setUserLabel();

		getUser();

	}

}
