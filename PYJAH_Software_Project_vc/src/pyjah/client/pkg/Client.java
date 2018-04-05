/*				Modified and implemented by Ammanuel.
 *This is the Client class. It needs more updating from the file processing program, email class, and user class.
 */

package pyjah.client.pkg;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

//<<<<<<< HEAD
import pyjah.util.pkg.Email;
//=======
import javafx.scene.control.TextField;


public class Client {
	
	/*            IMPORTANT!  
	 *  The message and message2 String will be changed to the message/email 
	    object and use the getters and setters to access the data.
	 */
	private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private String message = "";
    private String message2 = "";
    private static final String serverIP = "127.0.0.1";
    private static Socket connection;
    private TextField userText;
    private Calendar date;
    private Email email; // = new Email("Howie", "Dude", "Test email", date, "Test Body boyyyyiiii", "Unread");
    private User user;
    
    
    
    /* The constructor right now just takes the IP address needed for the client to connect to the server.
     * Since the server runs locally, it will connect to the local IP: 127.0.0.1.
     */  
    
    
    //Original Constructor*****
    
   /* public Client(String host, TextField userText) {
    	this.serverIP=host;
    	this.userText = userText;
    }*/
    
    
    
    
    public Client() {
    	//this.serverIP = serverIP;
    	this.connection = connection;
    }
    
  //This method is going to be running the program
    public void startClient(){
        try {
            connectToServer();
            setupStreams();
            keepStreamsOpen();
            //setUser("A");
        } catch (EOFException eofException){
			//Need to display an error dialog box
            System.out.println("\n Client terminated connection");
        } catch (IOException ioException){
            ioException.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    //This methods allows the client to connect to the server
    private void connectToServer() throws IOException{
    	//Display on the Client GUI
        System.out.println("Attempting connection \n");
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        
      //Display on the Client GUI that it's connected to the host name by
        System.out.println("Connected to IP address: "+ connection.getInetAddress().getHostName());

    }

    //This method is setting up the streams for the clients
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("\nThe streams are now connected!\n");
    }

    /* This method needs to modified for the message and user objects.
     * 
     * This method is going to ensure the streams are open and functional so that
     * the message object and the user object is sent back and forth.
     */ 

    private void keepStreamsOpen() throws IOException{
        message = "Client is now connected!";
        message2 = "You are now connected to the server!";
       // Email email1 = new Email("Amman","The streams are open","Read");
        //sendMessage(message);
        sendEmail(email);
        
    	//Display on the Client GUI
        System.out.println(message2);
        
         do {
            /*
             * This loop will ensure the streams stay open even at least 
            */ 
        	 try {
            	 /* The try method will send the user name and password to the server to get it verified.
            	  * This method also will get(getBody()) the message from the Email object from the client and 
            	  *  type cast it to String then display it through the Client's GUI.
            	  */               
            	//message = (String) input.readObject();
        		 
        		 email = (Email) input.readObject();
        		 //sendEmail(email);
             	//Display on the Client's GUI
                 System.out.println("\n" +message);
             } catch (ClassNotFoundException classNotFoundException){
              	//Display on the Client's GUI
                 System.out.println("\n I have no idea what the user sent!");
             }

         }while (!message.equals("SERVER - END"));
    }

    //Close connection
    private void closeConnection(){
        System.out.println("\nClosing the connection!");
        //ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //send message to server
    void sendMessage(String message){
        try{
            output.writeObject(message + "\n");
            output.flush();
        }catch(IOException ioException){
            System.out.println("\nOops! Something went wrong!");
        }
    }
    
    public void sendEmail(Email email){
        try{
            output.writeObject(email);
            output.flush();
        }catch(IOException ioException){
            System.out.println("\n Oops! Something went wrong!");
        }
    }
    
    private void setUser(String username) {
    	this.user.setUsername(username);
    	this.email.setSender(username);
    }
    
}