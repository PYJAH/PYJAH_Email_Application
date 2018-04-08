package fileProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


// this is a small program that ask user input file directory and then open the file 
//display all the contents
//  test path:

// /Users/nathan/Desktop/test/test.txt
public class readFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner sc = new Scanner(System.in);
		
		String filePath=sc.nextLine();
		
		try(BufferedReader br = new BufferedReader( new FileReader(filePath))){
			String line= null;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		}
		
		
	}

}

