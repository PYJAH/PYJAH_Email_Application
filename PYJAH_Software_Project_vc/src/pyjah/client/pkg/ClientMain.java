package pyjah.client.pkg;

import javafx.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class ClientMain extends Application {

	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//login scene
			
			primaryStage.setTitle("PYJAH Email Client");
			Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
			Scene login = new Scene(root);
			primaryStage.setScene(login);
			primaryStage.show();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
