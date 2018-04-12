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
	private static User user;
	private static User userA;
	private static User userB;
	public static boolean loggedIn = false;

	public static String loggedInUser;

	private boolean flag = false;

	// private ArrayList<Email> inboxAL = new ArrayList<Email>();
	// private ArrayList<Email> sentBoxAL = new ArrayList<Email>();

	// private ArrayList<Email> inboxALUserB = new ArrayList<Email>();
	// private ArrayList<Email> sentBoxALUserB = new ArrayList<Email>();

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

		do {

			try {
				/*
				 * 
				 * The try method tries to get the user objects from the client such as user
				 * name and password and, implement to the filing processing system and display
				 * through the Server's GUI.
				 */

				byte[] data = (byte[]) input.readObject();
				Object obj = (Object) SerializationUtils.deserialize(data);

				if (obj.getClass() == Email.class) {
					Email email1 = (Email) obj;

					if (email1.getRecipient().equals("User B")) {
	//----->Yang		//addEmailToUserInbox(email1, "User B");
	//----->Yang		//addEmailToUserSentbox(email1, "User A");
						
						userB.addToInbox(email1);
						System.out.println("User B INbox - " + userB.getInboxAL().get(userB.getInboxAL().size() - 1));
					} else if (email1.getRecipient().equals("User A")) {
	//----->Yang		//addEmailToUserInbox(email1, "User A");
	//----->Yang		//addEmailToUserSentbox(email1, "User B");
						user.addToInbox(email1);
						System.out.println("User A INbox - " + user.getInboxAL().get(user.getInboxAL().size() - 1));
					} else {
						System.out.println("Invalid Recipient Adress");
					}
					// System.out.println("\n" + email1);
					// System.out.println("The status of the email is: " + email1.getStatus());
					// showEmail(email1);

				}

				else if (obj.getClass() == User.class) {
					this.user = (User) obj;
					loggedInUser = user.getUsername();
					System.out.println("User recieved");
					loggedIn = true;

				}

				System.out.println("Server - " + loggedIn);

				System.out.println(user.getUsername() + "*********" + loggedIn);

				System.out.println("flag = " + flag);

			} catch (ClassNotFoundException classNotFoundException) {
				// Display on the Server GUI
				System.out.println("\n I have no idea what the user sent!");
			}

		} while (!(message.equals("CLIENT - END") || message.equals("CLIENT - end")));
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

	// Method used to retrieve user byte data from text file and set equal to
	// current user
	//This method is called in Thread1 within the SERVER CONTROLLER CLASS
	public void populateUser(User user) {
		if (loggedInUser.equals("User A")) {

			// instead of testUserA() do the file processing method that retrieves User A
			// byte code
			// byte [] data = Yangs code to retrieve user A byte array
			// this.user = (User) SerializationUtils.deserialize(data);

			this.user = testUserA();
		} else if (loggedInUser.equals("User B")) {
			// instead of testUserB() do the file processing method that retrieves User A
			// byte code
			// byte [] data = Yangs code to retrieve user B byte array
			// this.user = (User) SerializationUtils.deserialize(data);

			this.user = testUserB();
		}

		loggedIn = true;

	}

	// This method when modified will create a new file and store it in the user's
	// inbox folder for 1st time users
	private void sendInitialEmail() {
		sendMessage(
				"Welcome new user to the most coolest emailing system." + "\nFeel free to check the tabs and enjoy.");
	}

	private void writeToInbox(Email email) {
		User recipient = new User();
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

	public void addToUserA(User user) {

		userA.addToInbox((Email) user.getSentBox().get(user.getSentBox().size() - 1));

	}

	public User testUserA() {
		ArrayList<Email> inboxAL = new ArrayList<Email>();
		ArrayList<Email> sentBoxAL = new ArrayList<Email>();

		userA = new User();
		Email aTest1 = new Email();
		aTest1.setSender("User A");
		aTest1.setRecipient("User B");
		aTest1.setSubject("Test email 1");
		aTest1.setTime();
		aTest1.setBody("Hello, this is a test email body message");
		aTest1.setStatus("unread");

		Email aTest2 = new Email();
		aTest2.setSender("User A");
		aTest2.setRecipient("User B");
		aTest2.setSubject("Test email 1");
		aTest2.setTime();
		aTest2.setBody("Hello, this is a test email body message");
		aTest2.setStatus("unread");

		Email bTest1 = new Email();
		bTest1.setSender("User B");
		bTest1.setRecipient("User A");
		bTest1.setSubject("BBBBBBB");
		bTest1.setTime();
		bTest1.setBody("BBBBBBBBBBBBBB");
		bTest1.setStatus("unread");

		Email bTest2 = new Email();
		bTest2.setSender("User B");
		bTest2.setRecipient("User A");
		bTest2.setSubject("BBBB2");
		bTest2.setTime();
		bTest2.setBody("Hello, this is a test email body message");
		bTest2.setStatus("unread");

		userA.setSentboxAL(sentBoxAL);
		userA.setInboxAL(inboxAL);
		userA.setUsername("User A test");
		userA.setPassword("123");

		userA.addToInbox(bTest1);
		userA.addToInbox(bTest2);
		userA.addToSentBox(aTest1);
		userA.addToSentBox(aTest2);

		return userA;

	}

	public User testUserB() {
		ArrayList<Email> inboxAL = new ArrayList<Email>();
		ArrayList<Email> sentBoxAL = new ArrayList<Email>();
		userB = new User();
		Email aTest1 = new Email();
		aTest1.setSender("User A");
		aTest1.setRecipient("User B");
		aTest1.setSubject("Test email 1");
		aTest1.setTime();
		aTest1.setBody("Hello, this is a test email body message");
		aTest1.setStatus("unread");

		Email aTest2 = new Email();
		aTest2.setSender("User A");
		aTest2.setRecipient("User B");
		aTest2.setSubject("Test email 1");
		aTest2.setTime();
		aTest2.setBody("Hello, this is a test email body message");
		aTest2.setStatus("unread");

		Email bTest1 = new Email();
		bTest1.setSender("User B");
		bTest1.setRecipient("User A");
		bTest1.setSubject("BBBBBBB");
		bTest1.setTime();
		bTest1.setBody("BBBBBBBBBBBBBB");
		bTest1.setStatus("unread");

		Email bTest2 = new Email();
		bTest2.setSender("User B");
		bTest2.setRecipient("User A");
		bTest2.setSubject("BBBB2");
		bTest2.setTime();
		bTest2.setBody("Hello, this is a test email body message");
		bTest2.setStatus("unread");

		userB.setSentboxAL(sentBoxAL);
		userB.setInboxAL(inboxAL);
		userB.setUsername("User B test");
		userB.setPassword("123");

		userB.addToInbox(aTest1);
		userB.addToInbox(aTest2);
		userB.addToSentBox(bTest1);
		userB.addToSentBox(bTest2);

		return userB;

	}
	
	public void setUserB() {
		this.userB = testUserB();
	}

	public User getUser() {
		return this.user;

	}

	public boolean getLoggedStatus() {
		return loggedIn;
	}

	public void setLoggedStatus(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void addEmailToUserInbox(Email emailToWrite, String username) {
		User userFromFile = new User();
		byte[] data = null; // ****TODO: scan the user file for the byte[] for the user and set it equal to
							// "data" variable
		// Yangs Code Here |
		// V

		userFromFile = (User) SerializationUtils.deserialize(data);
		userFromFile.addToInbox(emailToWrite);

		data = serializeUser(userFromFile);

		// now write this data var to the file again to save the objects state with new
		// emailS
	}
	
	public void addEmailToUserSentbox(Email emailToWrite, String username) {
		User userFromFile = new User();
		byte[] data = null; // ****TODO: scan the user file for the byte[] for the user and set it equal to
							// "data" variable
		// Yangs Code Here |
		// V

		userFromFile = (User) SerializationUtils.deserialize(data);
		userFromFile.addToInbox(emailToWrite);

		data = serializeUser(userFromFile);

		// now write this data var to the file again to save the objects state with new
		// emailS
	}
}
