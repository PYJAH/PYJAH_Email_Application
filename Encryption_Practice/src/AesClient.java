import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;


public class AesClient {
	static KeyGenerator kg = null;
	static Cipher c = null;
	static Key k = null;
	static byte[] ivector = null;

	public static void main(String[] str) {

		String datatosend = null;
		try {
			datatosend = encryptString(getDataFromFile("Data.txt"));
			Frame f = new Frame(datatosend, k, ivector);

			sendFrame("localhost", 5656, f);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static String getDataFromFile(String filename) throws Exception {
		String str = "";
		Scanner sc = new Scanner(new File(filename));
		while (sc.hasNextLine()) {
			str = str + sc.nextLine();
		}
		sc.close();
		return str;

	}

	static String encryptString(String data) throws Exception {
		kg = KeyGenerator.getInstance("AES");
		// kg.init(128);
		c = Cipher.getInstance("AES/CFB/PKCS5Padding");
		k = kg.generateKey();

		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("1234512345123451".getBytes(), "AES"));
		byte[] plaintext = data.getBytes();
		byte[] ciphertext = c.doFinal(plaintext);

		ivector = c.getIV();

		return new String(ciphertext);

	}

	static void sendFrame(String remoteaddress, int port, Frame f) throws Exception {
		Socket s = new Socket(remoteaddress, port);
		OutputStream os = s.getOutputStream();
		
		ObjectOutputStream ous = new ObjectOutputStream(os);

		
		
		ous.writeObject(f);

		ous.flush();
		os.flush();
		ous.close();
		os.close();
		s.close();

	}

}
