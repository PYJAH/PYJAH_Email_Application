package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;


public class SampleController implements Initializable {
	
	@FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    @FXML
    
    void login(ActionEvent event) {
    	String id= username.getText();
    	String pw= password.getText();
    	String info= (id+","+pw);
    	System.out.println(info);
    	//need to pass it to authenticator
    }
    @FXML
    void signup(ActionEvent event) {
    	String id=username.getText();
    	String pw=password.getText();
    	String info= (id+","+pw);
    	System.out.println(info);
    	// pass it to list of users 
   }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
