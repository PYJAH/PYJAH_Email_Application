package pyjah.server.pkg;

import javafx.scene.control.TextArea;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerController {

	@FXML private TextArea console;
	@FXML private Button sendButton;
	@FXML private TextField textInput;
	

	
	
	/*public void displayServerInfo() {
		console.appendText("Welcome to Pyjah Server!");
	}*/
	
	 public void displayText() {
		 Platform.runLater(new Runnable(){
				@Override
				public void run() {
				// Update your GUI here.
					console.appendText("Connection made");
				}
				});
	 }

		
		 public void handleServerButtonClick() {
			 Platform.runLater(new Runnable(){
					@Override
					public void run() {
					// Update your GUI here.
						console.appendText("Hello!  You are now connected to PYJAH Email Server");
					}
					});
				
		 }
		 
		
}
