package fileProcessing;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.*;
import java.util.HashMap;
import java.util.Scanner;
public class scanFiles {

	public static void main(String[] args) {

		
		/*dir = directory
		 * Read all the files in inside the folder, and then map all the file names.
		 * Therefore, all the filesname becomes keywords.
		 * Value is all the information stored inside the file.
		 */
		
		HashMap<String, String> map= new HashMap<>();
		System.out.println("Enter Directory: ");
		
		Scanner scanner = new Scanner(System.in);
		String directory=scanner.nextLine();
		
		File dir = new File(directory);
		File[] listofFiles=dir.listFiles();
		
		
		
		for(int i=0;i<listofFiles.length;i++) {
			if(listofFiles[i].isFile()) {
				String fileName=null;
				String emailBody=null;
				fileName=listofFiles[i].getName();
				String newfileName=fileName.substring(0, fileName.indexOf("."));
				System.out.println(newfileName);
				
			}
			else if(listofFiles[i].isDirectory()) {
				System.out.println("error");
			}
		}
	
	}


	

}
