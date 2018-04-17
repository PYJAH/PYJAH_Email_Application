package Testing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.swing.plaf.synth.SynthSeparatorUI;

import pyjah.util.pkg.*;
import org.apache.commons.lang.SerializationUtils;

public class Test {

	public static void main (String [] args) throws FileNotFoundException, IOException {
		Email email = new Email();
		email.setSender("PYJAH EMAIL");
		email.setSubject("Welcome User A!");
		email.setBody("Welcome to the Pyjah Network!  Enjoy our encrypted emailing system!");
		email.setRecipient("User A");
		email.setTime();
		
		User userB = new User();
		userB.setUsername("User A");
		userB.addToInbox(email);
		
		byte[] myByteArray = serializeUser(userB);
		try (FileOutputStream fos = new FileOutputStream("User A.txt")) {
			   fos.write(myByteArray);
			   System.out.println("Stored byte");
			   //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
			}
		
		/*System.out.println(myByteArray);
		String byteString="";
		for(int i = 0; i < myByteArray.length-1; i++) {
			byteString += myByteArray[i] + ",";
			
		}
		
		try (PrintWriter output = new PrintWriter("User B.txt");) {
			output.println(byteString);
		}catch (FileNotFoundException e) {
			System.out.println(e);
		}*/
		
		
		/*
		try(BufferedReader br = new BufferedReader( new FileReader("test.txt"))){
			String line= null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		}*/
		
		
	}
	
	
	private static byte[] serializeUser(User serUser) {
		byte[] data = SerializationUtils.serialize((Serializable) serUser);
		return data;
	}
	private static byte[] serializeEmail(Email serEmail) {
		byte[] data = SerializationUtils.serialize(serEmail);
		return data;
	}
}
