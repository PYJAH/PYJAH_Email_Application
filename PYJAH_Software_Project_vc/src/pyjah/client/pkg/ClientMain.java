package pyjah.client.pkg;

import javafx.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class ClientMain extends Application {

	static Client pyjahClient = new Client();

	@Override
	public void start(Stage primaryStage) {
		try {
			// login scene

			primaryStage.setTitle("PYJAH Email Client");
			//Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("ClientView.fxml"));
			Scene login = new Scene(root);
			primaryStage.setScene(login);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Thread thread1 = new Thread() {
		public void run() {
			pyjahClient.startClient();
		}
	};

	public static void main(String[] args) {
		thread1.start();
		launch(args);
		pyjahClient.closeConnection();

	}

}
