import java.io.*;
import java.net.*;
import java.util.*;
import java.math.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

public class AesServer {

	public static void main(String[] str) throws Exception {
		ServerSocket ss = new ServerSocket(5656);
		Socket s = null;
		InputStream is = null;
		ObjectInputStream ois = null;
		Frame f = null;

		while (true) {
			s = ss.accept();
			is = s.getInputStream();
			ois = new ObjectInputStream(is);
	
			f = (Frame) ois.readObject();
			System.out.println(f.data);
			System.out.println(decryptString(f.data, f.k, new IvParameterSpec(f.iv)));
			

			break;
		}

		ois.close();
		is.close();
		s.close();
		ss.close();

	}

	static String decryptString(String data, Key key, IvParameterSpec ivspec) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		// kg.init(128);
		Cipher c = Cipher.getInstance("AES/CFB/PKCS5Padding");
		Key k = key;

		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec("1234512345123451".getBytes(), "AES"), ivspec);

		byte[] plaintext = c.doFinal(data.getBytes());

		return new String(plaintext);
	}

}
