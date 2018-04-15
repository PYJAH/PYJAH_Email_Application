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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServerController implements Initializable {

	@FXML TextArea console;
	@FXML private Button sendButton;
	@FXML private TextField textInput;
	@FXML private Label userIdLabel;
	Server pyjahServer = new Server(console);
	
	
	 public void displayText() {
		 Platform.runLater(new Runnable(){
				@Override
				public void run() {
				// Update your GUI here.
					console.appendText("Connection made");
				}
				});
	 }

		
		 public void handleServerButtonClick() throws IOException {
			 pyjahServer.populateUser(pyjahServer.getUser());
			 pyjahServer.sendUser(pyjahServer.getUser());
			
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
			
			Thread thread2 = new Thread () {
				public void run () {
					//boolean flag = false;
					System.out.println("server Thread 2");
					
					
					while(true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(pyjahServer.getLoggedStatus() == true) {
							try {
								pyjahServer.populateUser(pyjahServer.getUser());
								//pyjahServer.setUserB();
								 pyjahServer.sendUser(pyjahServer.getUser());
								 break;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
					interrupt();
				}
			};
		 

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			pyjahServer.setConsole(console);
			thread1.start();
			thread2.start();
			
			
		}
		 
		
}