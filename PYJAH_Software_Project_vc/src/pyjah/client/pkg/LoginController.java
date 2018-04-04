package pyjah.client.pkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField usernameLine;
	@FXML
	private TextField passwordLine;

	Client pyjahClient = new Client("127.0.0.1", usernameLine);

	// Method to send the output from the GUI fields to a location
	// Prints gui inputs and logs into the client. Will need to send the input
	// fields to the
	// server and add username and password authentication in this method.
	public void handleLoginButtonClick(ActionEvent event) throws IOException {

		System.out.println("UserName: " + usernameLine.getText() + "\nPassword: " + passwordLine.getText());
		pyjahClient.sendMessage(usernameLine.getText());
		pyjahClient.sendMessage(passwordLine.getText());

		usernameLine.clear();
		passwordLine.clear();

		accessClient(event);

	}

	// Method to switch to the create user view with clicking the hyperlink
	public void handleUserLinkClick(ActionEvent event) throws IOException {
		/*
		 * Parent createUserViewParent =
		 * FXMLLoader.load(getClass().getResource("CreateUserView.fxml")); Scene
		 * createUserViewScene = new Scene(createUserViewParent); Stage window =
		 * (Stage)((Node)event.getSource()).getScene().getWindow();
		 * window.setScene(createUserViewScene); window.show();
		 */
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateUserView.fxml"));
			Parent root = (Parent) loader.load();

			//CreateUserController cuController = loader.getController();
			//cuController.getConnection(pyjahClient);
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Thread thread1 = new Thread() {
		public void run() {
			pyjahClient.startClient();
		}
	};

	private void accessClient(ActionEvent event) throws IOException {
		Parent clientViewParent = FXMLLoader.load(getClass().getResource("ClientView.fxml"));
		Scene clientViewScene = new Scene(clientViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

		window.setScene(clientViewScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		thread1.start();

	}
}
