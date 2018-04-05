/*				Modified and implemented by Ammanuel.
 *This is the server class. It needs more updating from the file processing program, email class, and user class.
 */

package pyjah.server.pkg;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import pyjah.util.pkg.Email;

import javafx.application.Platform;
import javafx.scene.control.TextArea;


public class Server {
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static ServerSocket server;
    private static Socket connection;
    //The message String will be changed to the message/email object and use the getters and setters to access the data.
    private String message;
    private TextArea console;
    private Email email;
    
    public Server() {
    	this.email = null;
    	
    	
    }
    
    public Server(TextArea console) {
    	this.console = console;
    	this.message="";

    }


    public TextArea getConsole() {
    	return console;
    }

    //Set the console so it doesnt point null
    public void setConsole(TextArea console) {
    	this.console = console;
    }

//The server is set up here
    public void startServer(){
    	try {
    		server = new ServerSocket(6789, 100);
    		while(true){
    			try {
    				waitForConnection();
    				setupStreams();
    				keepStreamsOpen();
    			}catch (EOFException eofException){
    				//Need to display an error dialog box
    				System.out.println("Server ended the connection!");
    			}finally {
    				closeConnection();
    			}
    		}
    	}catch (IOException ioException){
    		ioException.printStackTrace();
    	}
    }

    //This method will wait for a connection and displays the connection information
    private void waitForConnection() throws IOException{
    	//Display on the Server GUI
    	System.out.println("Waiting for client to connect...\n");
    	connection = server.accept();
    	//Display on the Server GUI that it's connected to the host name by
    	System.out.println("Now connected to "+connection.getInetAddress().getHostName());
    }

    //This method will get the stream to send and receive data
    private void setupStreams() throws IOException{
    	output = new ObjectOutputStream(connection.getOutputStream());
    	output.flush();
    	input = new ObjectInputStream(connection.getInputStream());
    	//Display on the Server GUI
    	System.out.println("\n The streams are now set up.\n");
    }

    /* This method needs to modified for the message and user objects.
     * 
     * This method is going to ensure the streams are open and functional so that
     * the message object and the user object is sent back and forth.
     */   
    private void keepStreamsOpen() throws IOException{
    	message = "Server is now connected!";
    	//Display on the Server GUI
    	System.out.println(message);
    	

    	do{
    		
    		/*
    		try {
    			/* The try method tries to get the user objects from the client such as user name and password and,
    			 *  implement to the filing processing system and display through the Server's GUI.
    			 *		
    			message = (String) input.readObject();
    			
   
    			
    			//Display on the Server GUI

    			showMessage(message);

    			
    			System.out.println("\n" + message);
    			
    		} catch (ClassNotFoundException classNotFoundException){
    			//Display on the Server GUI
    			System.out.println("\n I have no idea what the user sent!");
    		}
    		
    		
    		//This where we show if there is any messages sent from the client
    		
    		
    		*/
    		
    		try {
    			/* The try method tries to get the user objects from the client such as user name and password and,
    			 *  implement to the filing processing system and display through the Server's GUI.
    			 */
    			//message = (String) input.readObject();

    			email = (Email) input.readObject();
    			//Display on the Server GUI
    			showEmail(email);
    //			System.out.println("\n" + email.toString());
    			//System.out.println("The status of the email is: " + email1.getStatus());
    		} catch (ClassNotFoundException classNotFoundException){
    			//Display on the Server GUI
    			System.out.println("\n I have no idea what the user sent!");
    		}
    		
    	} while (!(message.equals("CLIENT - END") || message.equals("CLIENT - end")));
    }
    
    
    

    //This method will close the streams and sockets when we close the program or when a client exits
    public void closeConnection(){
    	//Display on the Server GUI
    	System.out.println("\n Closing connections...\n");
    	//ableToType(false);
    	try {
    		output.close();
    		input.close();
    		connection.close();
    	}catch (IOException ioException){
    		ioException.printStackTrace();
    	}
    }

    /*This method will send the message to client
     * Will modify this to a method that sends the email to a user's inbox folder by writing to a file.
     * Method name will become writeToInbox()
     */

    private void sendMessage(String message){
    	try {
    		output.writeObject("SERVER - " + message);
    		output.flush();
    		//Display on the Server GUI
    		System.out.println("\nSERVER - "+ message);

    	}catch (IOException ioException){
    		ioException.printStackTrace();
    	}
    }
    
   
    
    /*//These methods will utilize & implement the folder writing and tracking program
    //I was also thinking of creating an Email object
     * 
    private void writeToInbox(Email email){

    }

    private void writeToSent(Email email){

    }

    private void readFromInbox(Email email){

    }

    private void readFromSend(Email email){

    }
*/

    //This method when modified will create a new file and store it in the user's inbox folder for 1st time users
    private void sendInitialEmail(){
    	sendMessage("Welcome new user to the most coolest emailing system." + "\nFeel free to check the tabs and enjoy.");
    }
    
    //Append message to GUI
     public void showMessage(final String message) {
		 Platform.runLater(new Runnable(){
				@Override
				public void run() {
				// Update your GUI here.
					console.appendText(message);
					
				}
				});
	 }
     
   //Append message to GUI
     public void showEmail(final Email email) {
		 Platform.runLater(new Runnable(){
				@Override
				public void run() {
				// Update your GUI here.
					console.appendText("Sent by: " + email.getSender() + "\n"
							+ "Subject: " + email.getSubject() + "\n" + 
							"Body: " + email.getBody()+"\n\n\n");
					
					
				}
				});
	 }

 
}


