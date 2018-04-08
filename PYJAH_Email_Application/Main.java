package Main;
/*First:
 * put user.txt into your JRE library
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
	
	 //declaration
	static ArrayList<String> listofusername=new ArrayList<>();
	static ArrayList<String> listofpassword=new ArrayList<>();
	static String UN;
	static String PW;
	static HashMap <String,String> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		

		
		//read username and password, store them into different arraylist
		Scanner userFile = new Scanner (new File("user.txt"));
		
		while(userFile.hasNextLine()){
			String line=userFile.nextLine();
			String [] fields = line.split(",");
			listofusername.add(fields[0]);
			listofpassword.add(fields[1]);
		}
		
		//create a new user
		Scanner unCheck = new Scanner (System.in);
		String newuser;
		String newPW;
		
		//check if it is a existed user
		do{
			System.out.println("Enter username");
			newuser=unCheck.nextLine();
			
		}while(listofusername.contains(newuser.toUpperCase()));
		
		Scanner pws = new Scanner (System.in);
		System.out.println("Enter Password");
		newPW=pws.nextLine();
		
		 try{
		    	
		    	//Here true is to append the content to file
		    	FileWriter fw = new FileWriter("user.txt",true);
		    	//BufferedWriter writer give better performance
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	

		    	
		    	bw.write(("\n"+newuser.toUpperCase()+","+newPW));
		    	
		    
		    	//Closing BufferedWriter Stream
		    	bw.close();

		    	System.out.println("Data successfully appended at the end of file");

		      }catch(IOException ioe){
		         System.out.println("Exception occurred:");
		         ioe.printStackTrace();

		       }
		
		
	
		//check username and password
		

		
		for(int i=0;i<listofusername.size();i++){
			map.put(listofusername.get(i),listofpassword.get(i));
		}
		
		
	
		System.out.println("Enter username: ");
		
		Scanner sc = new Scanner(System.in);
		
		 UN=sc.nextLine().toUpperCase();
		
		System.out.println("Enter password: ");
		
		 PW=sc.nextLine();
		
	
		authentication();
		//for here if user login successful, it will print "true" else "false"
		//I will make some adjustment later
		System.out.println(authentication());

		
	}
	
	public static boolean authentication(){
		for(int i =0;i<=listofusername.size();i++){
			if(UN.equalsIgnoreCase(listofusername.get(i))){
				if(PW.equals(map.get(UN.toUpperCase()))){
					return true;
				}
			}

			
		}
		return false;
	}

}
