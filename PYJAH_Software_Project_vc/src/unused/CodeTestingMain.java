package unused;


import pyjah.*;
import pyjah.util.pkg.Email;
public class CodeTestingMain {

	public static void main(String [] args) {
		Email email = new Email();
		email.setSender("Howie");
		email.setRecipient("John");
		email.setSubject("This is a test");
		email.setTime();
		email.setBody("This is some test code"
				+ " that is not too important to be honest");
		
		System.out.println(email);
		
	}
	
	/*
	 * private void keepStreamsOpen() throws IOException{ message =
	 * "Server is now connected!"; //Display on the Server GUI
	 * System.out.println(message);
	 * 
	 * 
	 * do{
	 * 
	 * /* try { /* The try method tries to get the user objects from the client such
	 * as user name and password and, implement to the filing processing system and
	 * display through the Server's GUI.
	 * 
	 * message = (String) input.readObject();
	 * 
	 * 
	 * 
	 * //Display on the Server GUI
	 * 
	 * showMessage(message);
	 * 
	 * 
	 * System.out.println("\n" + message);
	 * 
	 * } catch (ClassNotFoundException classNotFoundException){ //Display on the
	 * Server GUI System.out.println("\n I have no idea what the user sent!"); }
	 * 
	 * 
	 * //This where we show if there is any messages sent from the client
	 *
	 * 
	 * 
	 * 
	 * try { /* The try method tries to get the user objects from the client such as
	 * user name and password and, implement to the filing processing system and
	 * display through the Server's GUI.
	 *
	 * 
	 * 
	 * email = (Email) input.readObject(); showEmail(email);
	 * 
	 * 
	 * /* if(input.readObject() instanceof String) { showMessage(message); }
	 * 
	 * /*email = (Email) input.readObject(); //Display on the Server GUI
	 * showEmail(email);
	 * 
	 * } catch (ClassNotFoundException classNotFoundException){ //Display on the
	 * Server GUI System.out.println("\n I have no idea what the user sent!"); }
	 * 
	 * 
	 * 
	 * 
	 * //***************************************************************************
	 * *
	 * 
	 * 
	 * } while (!(message.equals("CLIENT - END") ||
	 * message.equals("CLIENT - end"))); }
	 */
}
