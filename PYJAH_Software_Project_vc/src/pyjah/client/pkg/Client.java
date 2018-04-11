/*				Modified and implemented by Ammanuel.
 *This is the Client class. It needs more updating from the file processing program, email class, and user class.
 */

package pyjah.client.pkg;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.SerializationUtils;

import pyjah.util.pkg.Email;
import pyjah.util.pkg.User;

import javafx.scene.control.TextField;

public class Client {

	/*
	 * IMPORTANT! The message and message2 String will be changed to the
	 * message/email object and use the getters and setters to access the data.
	 */
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	private String message = "";
	private String message2 = "";
	private static final String serverIP = "127.0.0.1";
	private static Socket connection;
	private TextField userText;
	private Calendar date;
	private Email email; // = new Email("Howie", "Dude", "Test email", date, "Test Body boyyyyiiii",
							// "Unread");
	public static User user;
	private static boolean loggedIn = false;

	/*
	 * The constructor right now just takes the IP address needed for the client to
	 * connect to the server. Since the server runs locally, it will connect to the
	 * local IP: 127.0.0.1.
	 */

	// Original Constructor*****

	/*
	 * public Client(String host, TextField userText) { this.serverIP=host;
	 * this.userText = userText; }
	 */

	public Client() {
		// this.serverIP = serverIP;
		this.connection = connection;
	}

	// This method is going to be running the program
	public void startClient() {
		try {
			connectToServer();
			setupStreams();
			keepStreamsOpen();
			// setUser("A");
		} catch (EOFException eofException) {
			// Need to display an error dialog box
			System.out.println("\n Client terminated connection");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	// This methods allows the client to connect to the server
	private void connectToServer() throws IOException {
		// Display on the Client GUI
		System.out.println("Attempting connection \n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);

		// Display on the Client GUI that it's connected to the host name by
		System.out.println("Connected to IP address: " + connection.getInetAddress().getHostName());

	}

	// This method is setting up the streams for the clients
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("\nThe streams are now connected!\n");
	}

	/*
	 * This method needs to modified for the message and user objects.
	 * 
	 * This method is going to ensure the streams are open and functional so that
	 * the message object and the user object is sent back and forth.
	 */

	private void keepStreamsOpen() throws IOException {
		message = "Client is now connected!";
		message2 = "You are now connected to the server!";

		// Display on the Client GUI
		System.out.println(message2);

		
			
			
			do {

				try {
					/*
					 * The try method will send the user name and password to the server to get it
					 * verified. This method also will get(getBody()) the message from the Email
					 * object from the client and type cast it to String then display it through the
					 * Client's GUI.
					 */
					
					
					byte[] data = (byte[]) input.readObject();
					
					this.user = (User) SerializationUtils.deserialize(data);
					
					for (int i = 0; i < data.length - 1; i++) {
						System.out.print(data[i]);
					}
					System.out.println("\nThis is the user serialization number");

					
				
					System.out.println("Client recieved user" + user.toString());
					System.out.println(loggedIn + " = logged in status");
					
					if(loggedIn == false) {
						loggedIn = true;
					}
					
					System.out.println(loggedIn + " = logged in status");
					

				} catch (ClassNotFoundException classNotFoundException) {
					// Display on the Client's GUI
					System.out.println("\n I have no idea what the user sent!");

				}

			} while (!message.equals("Close"));
		

	}

	// Close connection
	void closeConnection() {
		System.out.println("\nClosing the connection!");
		// ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// send message to server
	void sendMessage(String message) {
		try {
			output.writeObject(message + "\n");
			output.flush();
		} catch (IOException ioException) {
			System.out.println("\nOops! Something went wrong!");
		}
	}

	public void sendEmail(Email email) {
		try {
			output.writeObject(serializeEmail(email));
			output.flush();
		} catch (IOException ioException) {
			System.out.println("\n Oops! Something went wrong!");
		}
	}

	public void sendUser(User user) {
		try {
			output.writeObject(serializeUser(user));
			output.flush();
		} catch (IOException ioException) {
			System.out.println("\n Oops! Something went wrong!");
		}
	}

	public void recieveUser() throws ClassNotFoundException, IOException {
		byte[] data = (byte[]) input.readObject();
		User populate = (User) SerializationUtils.deserialize(data);
		this.user.setUser(populate);
	}

	/*
	 * private void setUser(String username) { this.user.setUsername(username);
	 * this.email.setSender(username); }
	 */

	private byte[] serializeEmail(Email serEmail) {
		byte[] data = SerializationUtils.serialize(serEmail);
		return data;
	}

	private byte[] serializeUser(User serUser) {
		byte[] data = SerializationUtils.serialize((Serializable) serUser);
		return data;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public User getCurrentUser() {
		return this.user;
	}
	
	public void addToInbox(Email email) {
		this.user.addToInbox(email);
	}
	
	public void addToSentBox(Email email) {
		this.user.addToSentBox(email);
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}