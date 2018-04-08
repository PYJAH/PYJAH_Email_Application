package pyjah.util.pkg;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.Serializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/*
 * Class for creating a new user.  As of right now there are some fields which I think may
 * be unneccesary or misplaced or unused in this class (inbox, sentbox) but maybe not.  Lets
 * share ideas on next team meeting.  
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = -169741550773849168L;

	private String username;
	private String password;
	private HashMap inbox;
	private HashMap sentBox;
	private ArrayList<Email> inboxAL;
	private ArrayList<Email> sentboxAL;
	private Email email;
	private boolean objectPopulated = false;
	
	public User() {
		/*this.username = "";
		this.password = "";
		this.inboxAL = new ArrayList<Email>();
		this.sentboxAL = new ArrayList<Email>();*/
	}

	public User(String username, String password, HashMap inbox, HashMap sentBox) {
		this.username = username;
		this.password = password;
		this.inbox = inbox;
		this.sentBox = sentBox;
	}
	
	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, HashMap inbox, HashMap sentBox) {
		this.username = username;
		this.inbox = new HashMap(inbox);
		this.sentBox = new HashMap(sentBox);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public HashMap getInbox() {
		return inbox;
	}
	
	public HashMap getSentBox() {
		return sentBox;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setInbox(HashMap inbox) {
		this.inbox = inbox;
	}
	
	public void setSentBox(HashMap sentBox) {
		this.sentBox = sentBox;
	}
	
	public String toString() {
		return "User Name: " + username + "\nPassword: " + password;
	}

	public HashMap inbox(HashMap map) {
		HashMap hmap = new HashMap();
		return hmap;
	}
	
	
	 public void sendEmail(Email email, ObjectOutputStream output){
	        try{
	            output.writeObject(email);
	            output.flush();
	        }catch(IOException ioException){
	            System.out.println("\n Oops! Something went wrong!");
	        }
	    }

	
	public ArrayList<Email> getInboxAL() {
		return inboxAL;
	}

	public void setInboxAL(ArrayList<Email> inboxAL) {
		this.inboxAL = inboxAL;
	}
	
	public ArrayList<Email> getSentboxAL() {
		return sentboxAL;
	}

	public void setSentboxAL(ArrayList<Email> sentboxAl) {
		this.sentboxAL = sentboxAl;
	}

	
	public void addToSentBox(Email email) {
		this.sentboxAL.add(email);
	}
	
	public void addToInbox(Email email) {
		this.inboxAL.add(email);
	}

	public boolean isObjectPopulated() {
		return objectPopulated;
	}

	public void setObjectPopulated(boolean objectPopulated) {
		this.objectPopulated = objectPopulated;
	}


}
