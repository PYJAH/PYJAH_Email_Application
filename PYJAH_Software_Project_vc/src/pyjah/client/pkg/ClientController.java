package pyjah.client.pkg;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import pyjah.util.pkg.Email;
import pyjah.util.pkg.User;
//import unused.User;

public class ClientController implements Initializable {
	@FXML
	private TabPane tPane;
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
	private Tab loginTab;
	@FXML
	private Tab composeTab;
	@FXML
	private Tab inboxTab;
	@FXML
	private Tab sentTab;
	@FXML
	private Button logoutButton;

	@FXML
	private ListView<String> inboxListView;

	@FXML
	private ListView<String> sentboxListView;

	ToggleGroup group = new ToggleGroup();

	@FXML
	private Button inboxRefreshButton;

	@FXML
	private Button sentRefreshButton;

	private ArrayList<Email> inboxAL = new ArrayList<Email>();
	private ArrayList<Email> sentBoxAL = new ArrayList<Email>();

	// private User currentUser;
	private HashMap messageFields = new HashMap();
	private User user;
	// private Email email;

	Client pyjahClient = new Client();

	// Method to send the input from the GUI fields to a location on button click.
	// As of now it just prints the text fields inputted.


	
//<<<<<<< HEAD
	//Composes a new email object with appropriate fields.
	@FXML
	public void handleSendButtonClick(ActionEvent event) {
		Email email = new Email(user.getUsername(), toLine.getText(), subjectLine.getText(), messageBody.getText());// ,
		email.setTime();																											// email.getTime(),
		
		pyjahClient.addToSentBox(email);
		// pyjahClient.sendUser(pyjahClient.getCurrentUser());
		pyjahClient.sendEmail(email);
		toLine.clear();
		subjectLine.clear();
		messageBody.clear();

		updateInbox();
		updateSentbox();


	}


	@FXML
	void loginOnButtonClick(ActionEvent event) {
		pyjahClient.sendUser(pyjahClient.getCurrentUser());
		pyjahClient.setLoggedIn(true);
		composeTab.setDisable(false);
		inboxTab.setDisable(false);
		sentTab.setDisable(false);
		tPane.getSelectionModel().select(composeTab);
		this.user = pyjahClient.getCurrentUser();

		System.out.println("This is the user" + pyjahClient.getCurrentUser().getUsername());
		// Thread.sleep(500);

		/*System.out.println(thread1.getState());
		if (thread1.isAlive()==false) {
			thread1.start();
		}
		System.out.println(thread1.getState());*/

		updateInbox();
		updateSentbox();

		loginTab.setDisable(true);

	}

	@FXML
	void radioSetToUserA(ActionEvent event) {

		userARadioButton.setToggleGroup(group);
		userARadioButton.setSelected(true);

		this.user = new User();
		user.setUsername("User A");
		pyjahClient.setUser(this.user);
		this.userIdLabel.setText(this.user.getUsername());
	}

	@FXML
	void radioSetToUserB(ActionEvent event) {
		System.out.println("radio b");
		userBRadioButton.setToggleGroup(group);
		userBRadioButton.setSelected(true);

		this.user = new User();
		user.setUsername("User B");
		pyjahClient.setUser(this.user);
		this.userIdLabel.setText(this.user.getUsername());
	}

	// AKA log off button :P
	@FXML
	void backToLoginButtonClick(ActionEvent event) {
		pyjahClient.setLoggedIn(false);
		loginTab.setDisable(false);
		tPane.getSelectionModel().select(loginTab);
		composeTab.setDisable(true);
		inboxTab.setDisable(true);
		sentTab.setDisable(true);
	}

	public void updateInbox() {
		ArrayList<String> testList = new ArrayList<String>();
		for (int i = 0; i < pyjahClient.user.getInboxAL().size(); i++) {
			testList.add(pyjahClient.user.getInboxAL().get(i).getSubject());
		}

		inboxListView.setItems(FXCollections.observableList(testList));
	}

	public void updateSentbox() {
		ArrayList<String> testList = new ArrayList<String>();
		for (int i = 0; i < pyjahClient.user.getSentboxAL().size(); i++) {
			testList.add(pyjahClient.user.getSentboxAL().get(i).getSubject());
		}

		sentboxListView.setItems(FXCollections.observableList(testList));
	}

	@FXML
	void onInboxRefreshButtonClick(ActionEvent event) {
		System.out.println(pyjahClient.getCurrentUser().getUsername());
		updateInbox();
	}

	@FXML
	void onSentRefreshButtonClick(ActionEvent event) {
		System.out.println("Refresh");
		updateSentbox();
	}

	// This is the thread to update the inbox and sent box during startup
	Thread thread1 = new Thread() {
		public void run() {
			while (true) {     // pyjahClient.isLoggedIn() == 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updateSentbox();
				updateInbox();
			}

		}

	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1r) {
		// thread1.start();
	}

}
