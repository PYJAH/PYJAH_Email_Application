package pyjah.server.pkg;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pyjah.client.pkg.Client;



public class ServerMain extends Application {
	
	static Server pyjahServer = new Server();
	
	
		@Override
		public void start(Stage primaryStage) {
			try {
				//login scene
				
				primaryStage.setTitle("PYJAH Email Server");
				Parent root = FXMLLoader.load(getClass().getResource("ServerView.fxml"));
				Scene login = new Scene(root);
				primaryStage.setScene(login);
				primaryStage.show();
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	
	
		public static void main(String[] args) {
			
			launch(args);
			pyjahServer.closeConnection();
		}
		
		
}
