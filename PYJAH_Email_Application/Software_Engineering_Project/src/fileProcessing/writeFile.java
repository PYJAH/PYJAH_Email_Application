package fileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class writeFile {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter file directory: ");
		String filePath=sc.nextLine();
		System.out.println("Please enter name for the text file: ");
		String fileName=sc.nextLine();
		fileName=fileName+".txt";
		File file = new File(filePath+fileName);
		
		
		try (PrintWriter output = new PrintWriter(file);){
			
			System.out.println("Enter content: ");
			output.println(sc.nextLine());
			
			
		} catch (FileNotFoundException e) {
			
			System.out.println("error");
		}
		
		
		
		
		
		
	}

}
