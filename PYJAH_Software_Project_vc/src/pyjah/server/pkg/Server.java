/*				Modified and implemented by Ammanuel.
 *This is the server class. It needs more updating from the file processing program, email class, and user class.
 */

package pyjah.server.pkg;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.lang.SerializationUtils;

import pyjah.util.pkg.Email;
import pyjah.util.pkg.User;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Server {
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private static ServerSocket server;
	private static Socket connection;
	// The message String will be changed to the message/email object and use the
	// getters and setters to access the data.
	private String message;
	private TextArea console;
	// private Email email;
	private User user;
	private boolean loggedIn = false;

	private ArrayList<Email> inboxAL = new ArrayList<Email>();
	private ArrayList<Email> sentBoxAL = new ArrayList<Email>();

	public Server() {
		// this.email = null;

	}

	public Server(TextArea console) {
		this.console = console;
		this.message = "";

	}

	// to connect the gui console to the server class
	public TextArea getConsole() {
		return console;
	}

	// Set the console so it doesnt point null
	public void setConsole(TextArea console) {
		this.console = console;
	}

	// The server is set up here
	public void startServer() {
		try {
			server = new ServerSocket(6789, 100);
			while (true) {
				try {
					waitForConnection();
					setupStreams();
					keepStreamsOpen();
				} catch (EOFException eofException) {
					// Need to display an error dialog box
					System.out.println("Server ended the connection!");
				} finally {
					closeConnection();
				}
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// This method will wait for a connection and displays the connection
	// information
	private void waitForConnection() throws IOException {
		// Display on the Server GUI
		System.out.println("Waiting for client to connect...\n");
		connection = server.accept();
		// Display on the Server GUI that it's connected to the host name by
		System.out.println("Now connected to " + connection.getInetAddress().getHostName());
	}

	// This method will get the stream to send and receive data
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		// Display on the Server GUI
		System.out.println("\n The streams are now set up.\n");
	}

	/*
	 * This method needs to modified for the message and user objects.
	 * 
	 * This method is going to ensure the streams are open and functional so that
	 * the message object and the user object is sent back and forth.
	 */
	private void keepStreamsOpen() throws IOException {
		message = "Server is now connected!";
		// Display on the Server GUI
		System.out.println(message);
		
		
		//sendUser(user);
		
		do {

			try {
				/*
				 * The try method tries to get the user objects from the client such as user
				 * name and password and, implement to the filing processing system and display
				 * through the Server's GUI.
				 */

				//

				/*
				 * if(loggedIn == false) { recieveUser(); retrieveUser(); loggedIn = true; }else
				 * { recieveUser();
				 * 
				 * }
				 */

				// ********************************************************************************************************

				
				
			/*if(loggedIn == false) {
				
			}*/
				
					
			
					byte[] data = (byte[]) input.readObject();
					this.user = (User) SerializationUtils.deserialize(data);
					//fSystem.out.println(""+user.equals(this.user));
			
				

					for (int i = 0; i < data.length - 1; i++) {
						System.out.print(data[i]);
					}
					System.out.println("\n");
					
					/*if(loggedIn == false) {
						if(!this.user.equals(null)) {
						 sendUser(user);
						 loggedIn = true;
						}
					}*/
					
					System.out.println(user.getInboxAL().toString() + "*********");
					
					if(loggedIn == false) {
						populateUser(user);
						sendUser(user);
						loggedIn=true;
					}
					
					System.out.println(user.getInboxAL().toString() + "*********" + loggedIn);
					
					// System.out.println(data.toString());
					
					//showEmail(this.user.getSentboxAL().get(this.user.getSentboxAL().size() - 1));
					
				
				

				// **************************************************************************************************************

				/*
				 * if(loggedIn == false) { retrieveUser(); loggedIn = true; }else if(loggedIn ==
				 * true) { recieveUser(); sendUser(user); }
				 */

				// showEmail(this.user.getSentboxAL().get(this.user.getSentboxAL().size()-1));

			} catch (ClassNotFoundException e) {
				// Display on the Server GUI
				System.out.println("\n I have no idea what the user sent!");
			}

		} while (true);
		

	}
	
	public void populateUser(User user) {
		this.user = testUserA();
		loggedIn = true;
		sendUser(user);
	}

	// This method will close the streams and sockets when we close the program or
	// when a client exits
	public void closeConnection() {
		// Display on the Server GUI
		System.out.println("\n Closing connections...\n");
		// ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/*
	 * This method will send the message to client Will modify this to a method that
	 * sends the email to a user's inbox folder by writing to a file. Method name
	 * will become writeToInbox()
	 */

	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			// Display on the Server GUI
			System.out.println("\nSERVER - " + message);

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private void writeToInbox(Email email) {
		User recipient = new User();
	}

	/*
	 * //These methods will utilize & implement the folder writing and tracking
	 * program //I was also thinking of creating an Email object
	 * 
	 * 
	 * 
	 * 
	 * 
	 * private void writeToSent(Email email){
	 * 
	 * }
	 * 
	 * private void readFromInbox(Email email){
	 * 
	 * }
	 * 
	 * private void readFromSend(Email email){
	 * 
	 * }
	 */

	// This method when modified will create a new file and store it in the user's
	// inbox folder for 1st time users
	private void sendInitialEmail() {
		sendMessage(
				"Welcome new user to the most coolest emailing system." + "\nFeel free to check the tabs and enjoy.");
	}

	// Append message to GUI
	public void showMessage(final String message) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update your GUI here.
				console.appendText(message);

			}
		});
	}

	// Append message to GUI
	public void showEmail(final Email email) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Update your GUI here.
				console.appendText("Sent by: " + email.getSender() + ", At " + email.getTime() + "\n" + "Subject: "
						+ email.getSubject() + "\n" + "Body: " + email.getBody() + "\n\n\n");

			}
		});
	}

	public void retrieveUser() {
		// user = (User) SerializationUtils.deserialize(data);

		/*
		 * read the file where object byte array is stored find file where title is = to
		 * string returned by user.getUsername() scan the first or last byte[] that is
		 * where our user is stored byte[] data = scanned byte array
		 * 
		 * user = (User) SerializationUtils.deserialize(data);
		 *
		 * return user;
		 */

		this.user = testUserA();
		sendUser(user);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	// deconstructs a User object into a byte array
	private byte[] serializeUser(User serUser) {
		byte[] data = SerializationUtils.serialize((Serializable) serUser);
		return data;
	}

	public void sendUser(User user) {
		try {
			output.writeObject(serializeUser(user));
			output.flush();
		} catch (IOException ioException) {
			System.out.println("\n Oops! Something went wrong!");
		}
	}

	// to accept user objects being sent from the client
	public void recieveUser() throws ClassNotFoundException, IOException {
		byte[] data = (byte[]) input.readObject();
		this.user = (User) SerializationUtils.deserialize(data);

		for (int i = 0; i < data.length - 1; i++) {
			System.out.print(data[i]);
		}
		System.out.println("\n");

	}

	public User testUserA() {
		User testUserA = new User();
		Email test = new Email();
		test.setSender("Howie");
		test.setRecipient("John");
		test.setSubject("Test email 1");
		test.setTime();
		test.setBody("Hello, this is a test email body message");
		test.setStatus("unread");

		Email test2 = new Email();
		test2.setSender("Howie");
		test2.setRecipient("John");
		test2.setSubject("Test email 1");
		test2.setTime();
		test2.setBody("Hello, this is a test email body message");
		test2.setStatus("unread");

		testUserA.setSentboxAL(sentBoxAL);
		testUserA.setInboxAL(inboxAL);
		testUserA.setUsername("User A test");
		testUserA.setPassword("123");

		testUserA.addToInbox(test);
		testUserA.addToInbox(test2);

		return testUserA;

	}
}
