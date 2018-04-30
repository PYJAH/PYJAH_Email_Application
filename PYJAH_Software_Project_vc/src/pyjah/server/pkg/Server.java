package pyjah.server.pkg;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

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
	public static User user;
	private static User userA;
	private static User userB;
	public static boolean loggedIn = false;

	public static String loggedInUser;

	private boolean flag = false;

	 private ArrayList<Email> inboxAL = new ArrayList<Email>();
	 private ArrayList<Email> sentBoxAL = new ArrayList<Email>();

	 private ArrayList<Email> inboxALUserB = new ArrayList<Email>();
	 private ArrayList<Email> sentBoxALUserB = new ArrayList<Email>();
	private SecretKey secretKey;

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

				//byte[] data = (byte[]) input.readObject();
				SealedObject sealedObject = (SealedObject) input.readObject();
    			System.out.println("\n(Server) The encrypted byte array as a sealed object looks like this: "+sealedObject);
    			byte[] decryptedByte = decryptObject(sealedObject);
				Object obj = (Object) SerializationUtils.deserialize(decryptedByte);

				if (obj.getClass() == Email.class) {
					Email email1 = (Email) obj;

					if (email1.getRecipient().equals("User B")) {
						addEmailToUserInbox(email1, "User B");//----->Yang	
						addEmailToUserSentbox(email1, "User A");//----->Yang
						
						//user.addToInbox(email1);
						//System.out.println("User B INbox - " + userB.getInboxAL().get(userB.getInboxAL().size() - 1));
					} else if (email1.getRecipient().equals("User A")) {
						addEmailToUserInbox(email1, "User A");//----->Yang
						addEmailToUserSentbox(email1, "User B");//----->Yang
						//user.addToInbox(email1);
						//System.out.println("User A INbox - " + user.getInboxAL().get(user.getInboxAL().size() - 1));
					} else {
						System.out.println("Invalid Recipient Adress");
					}
					 System.out.println("\n" + email1);
					 System.out.println("The status of the email is: " + email1.getStatus());
					 showEmail(email1);

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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	public void populateUser(User user) throws IOException {
		if (loggedInUser.equals("User A")) {

			// instead of testUserA() do the file processing method that retrieves User A
			// byte code
			// byte [] data; // = Yangs code to retrieve user A byte array
			
			
			
			
			//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			 File file = new File("User A.txt");

			// EncryptedObj data = new EncryptedObj(sealedObj, key);
			 
			 
	         byte[] data = new byte[(int) file.length()];
	         try {
	               FileInputStream fileInputStream = new FileInputStream(file);
	               fileInputStream.read(data);
	               //for (int i = 0; i < data.length; i++) {
	                 //          System.out.print((char) data[i]);
	               // }
	               
	               fileInputStream.close();
	          } catch (FileNotFoundException e) {
	                      System.out.println("File Not Found.");
	                      e.printStackTrace();
	          }
	          catch (IOException e1) {
	                   System.out.println("Error Reading The File.");
	                    e1.printStackTrace();
	          }

	         
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	         this.user = (User) SerializationUtils.deserialize(data);

			//this.user = testUserA();
		} else if (loggedInUser.equals("User B")) {
			// instead of testUserB() do the file processing method that retrieves User A
			// byte code
			// byte [] data = Yangs code to retrieve user B byte array
			
			
			
			File file = new File("User B.txt");

	         byte[] data = new byte[(int) file.length()];
	         try {
	               FileInputStream fileInputStream1 = new FileInputStream(file);
	               fileInputStream1.read(data);
	               //for (int i = 0; i < data.length; i++) {
	                 //          System.out.print((char) data[i]);
	               // }
	               fileInputStream1.close();
	          } catch (FileNotFoundException e) {
	                      System.out.println("File Not Found.");
	                      e.printStackTrace();
	          }
	          catch (IOException e1) {
	                   System.out.println("Error Reading The File.");
	                    e1.printStackTrace();
	          }
	         
			
			this.user = (User) SerializationUtils.deserialize(data);

			//this.user = testUserB();
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
//		try {
//			output.writeObject(serializeUser(user));
//			output.flush();
//		} catch (IOException ioException) {
//			System.out.println("\n Oops! Something went wrong!");
//		}
		sendEncryptedByte(serializeUser(user));
		
	}

	// to accept user objects being sent from the client
	public void recieveUser() throws Exception {
		//byte[] data = (byte[]) input.readObject();
		SealedObject sealedObject = (SealedObject) input.readObject();
		System.out.println("\n(Recieve User - Client) The encrypted byte array as a sealed object looks like this: "+sealedObject);
		byte[] decryptedByte = decryptObject(sealedObject);
		this.user = (User) SerializationUtils.deserialize(decryptedByte);

		for (int i = 0; i < decryptedByte.length - 1; i++) {
			System.out.print(decryptedByte[i]);
		}
		System.out.println("\n");

	}

	/*public void addToUserA(User user) {
		userA.addToInbox((Email) user.getSentBox().get(user.getSentBox().size() - 1));
	}*/

	public User getUser() {
		return this.user;

	}

	public boolean getLoggedStatus() {
		return loggedIn;
	}

	public void setLoggedStatus(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void addEmailToUserInbox(Email emailToWrite, String username) throws FileNotFoundException, IOException {
		User userFromFile = new User();
		//byte[] data = null; // ****TODO: scan the user file for the byte[] for the user and set it equal to
							// "data" variable
		// Yangs Code Here |
		//                 V
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		 File file = new File(username+".txt");

         byte[] data = new byte[(int) file.length()];
         try {
               FileInputStream fileInputStream = new FileInputStream(file);
               fileInputStream.read(data);
               //for (int i = 0; i < data.length; i++) {
                 //          System.out.print((char) data[i]);
               // }
               System.out.println(data.toString());
               fileInputStream.close();
          } catch (FileNotFoundException e) {
                      System.out.println("File Not Found.");
                      e.printStackTrace();
          }
          catch (IOException e1) {
                   System.out.println("Error Reading The File.");
                    e1.printStackTrace();
          }
         //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		userFromFile = (User) SerializationUtils.deserialize(data);
		userFromFile.addToInbox(emailToWrite);

		data = serializeUser(userFromFile);

		// now write this data var to the file again to save the objects state with new
		// emailS
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		//overwrite to file
        FileOutputStream fos = new FileOutputStream(username+".txt");
        
        fos.write(data);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	}
	
	public void addEmailToUserSentbox(Email emailToWrite, String username) throws FileNotFoundException, IOException {
		User userFromFile = new User();
		//byte[] data = null; // ****TODO: scan the user file for the byte[] for the user and set it equal to
							// "data" variable
		// Yangs Code Here |
		// V
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		 File file = new File(username+".txt");

        byte[] data = new byte[(int) file.length()];
        try {
              FileInputStream fileInputStream = new FileInputStream(file);
              fileInputStream.read(data);
              //for (int i = 0; i < data.length; i++) {
                //          System.out.print((char) data[i]);
              // }
              fileInputStream.close();
         } catch (FileNotFoundException e) {
                     System.out.println("File Not Found.");
                     e.printStackTrace();
         }
         catch (IOException e1) {
                  System.out.println("Error Reading The File.");
                   e1.printStackTrace();
         }
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		userFromFile = (User) SerializationUtils.deserialize(data);
		userFromFile.addToSentBox(emailToWrite);

		data = serializeUser(userFromFile);

		// now write this data var to the file again to save the objects state with new
		// emailS
		
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		//overwrite to file
        FileOutputStream fos = new FileOutputStream(username+".txt");
        
        fos.write(data);
        
		
		
		
		
		
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		
		
	}
	
	/*
	 * The purpose of this method is to encrypt the byte array that we are going to send back and forth
	 * using AES encryption and transferring it using a SealedObject
	 * */
	
	public void sendEncryptedByte(byte[] data) {
        try{
        	
        	secretKey = KeyGenerator.getInstance("AES").generateKey();
        	//email.setEmailSecretKey(secretKey);
        	
        	writeToFile("secretkeyByte.dat", secretKey);
        	Cipher encryptCipher = Cipher.getInstance("AES");
        	encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        	
        	SealedObject sealedObject = new SealedObject(data, encryptCipher);
        	
			//writeToFile("sealed.dat", sealedObject);
			
        	output.writeObject(sealedObject);
        	output.flush();
        	
        }catch (IOException ioException){
            System.out.println("\n Oops! Something went wrong!");
        } catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/*
	 * The purpose of this method is to decrypt the SealedOject that we are going to send back and forth
	 *  through the streams using AES encryption and casting it back to a byte array.
	 * */
	private byte[] decryptObject(SealedObject encryptedObj) throws Exception {
		secretKey = (SecretKey) readFromFile("secretkeyByte.dat");

		if(encryptedObj != null) {

			//String algorithName = encryptedObj.getAlgorithm();
			Cipher decryptCipher = Cipher.getInstance(encryptedObj.getAlgorithm());
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

			byte[] decryptedObject = (byte[]) encryptedObj.getObject(decryptCipher);
			return decryptedObject;
		}

		else
			return null;
	}
	
	/*
	 * The purpose of this method is to write the secretkey into a file
	 * */
	private static void writeToFile(String filename, Object obj) throws Exception {
	 	   try(FileOutputStream fOutput = new FileOutputStream(new File(filename));
	 			   ObjectOutputStream output = new ObjectOutputStream(fOutput)){
	 		   output.writeObject(obj);
	 	   }
	    }

	/*
	 * The purpose of this method is to read the secretkey from a file
	 * */
	private static Object readFromFile(String filename) throws Exception{
		try(FileInputStream fInput = new FileInputStream(new File(filename));
				ObjectInputStream input = new ObjectInputStream(fInput)){
			return input.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}