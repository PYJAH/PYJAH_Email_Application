package pyjah.server.pkg;

import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerController implements Initializable {

	@FXML TextArea console;
	@FXML private Button sendButton;
	@FXML private TextField textInput;
	Server pyjahServer = new Server(console);
	
	
	
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

		 
		 Thread thread1 = new Thread () {
				public void run () {
					pyjahServer.startServer();
				}
			};

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			pyjahServer.setConsole(console);
			thread1.start();
			
		}
		 
		
}
