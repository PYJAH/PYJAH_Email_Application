package pyjah.client.pkg;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserController {
	
	public void handleCreateUserButtonClick(ActionEvent event) throws IOException {
		Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
		Scene loginViewScene = new Scene(loginViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(loginViewScene);
		window.show();
		
	}
	
	public User newUserFieldsHandler(TextField usernameLine, TextField passwordLine) {
		String username = usernameLine.getText();
		String password = passwordLine.getText();
		User user = new User();
		return user;
		
	}
}
