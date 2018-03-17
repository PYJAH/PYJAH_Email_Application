package pyjah.client.pkg;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;


public class LoginController {
	
	@FXML private TextField usernameLine;
	@FXML private TextField passwordLine;
	
	
	//Method to send the output from the GUI fields to a location
	//Prints gui inputs and logs into the client.  Will need to send the input fields to the 
	//server and add username and password authentication in this method.  
	public void handleLoginButtonClick(ActionEvent event) throws IOException {
		
		System.out.println("UserName: " + usernameLine.getText() + "\nPassword: " + passwordLine.getText());
		Parent clientViewParent = FXMLLoader.load(getClass().getResource("ClientView.fxml"));
		Scene clientViewScene = new Scene(clientViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(clientViewScene);
		window.show();
		
	}
	
	
	//Method to switch to the create user view with clicking the hyperlink
	public void handleUserLinkClick(ActionEvent event) throws IOException {
		Parent createUserViewParent = FXMLLoader.load(getClass().getResource("CreateUserView.fxml"));
		Scene createUserViewScene = new Scene(createUserViewParent);
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(createUserViewScene);
		window.show();
	}
}
