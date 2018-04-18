package Testing;

/*
 * 								Object Encryption using DES Algorithm/
* This is standard encyrption and won't work over streams since you need to send over the secretKey
*/

import java.io.*;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import pyjah.util.pkg.Email;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class ObjectEncryption {
	private static Cipher encryptCipher;
	private static Cipher decryptCipher;
	private static SecretKey secretKey;

	public static void main(String[] args) {
		try {
			secretKey = KeyGenerator.getInstance("AES").generateKey();
			encryptCipher = Cipher.getInstance("AES");
			decryptCipher = Cipher.getInstance("AES");
			
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
			
			SealedObject sealed = new SealedObject(new Email("Amman5","The streams are open","Read"), encryptCipher);
			System.out.println("The encrypted email is: "+sealed);
			
			String algo = sealed.getAlgorithm();
			System.out.println("The algorithm used is: "+ algo);
			
			Email email = (Email) sealed.getObject(decryptCipher);
			System.out.println("The encrypted and decrpyted object is: " + email);
			
			
		} catch (Exception exception) {
			System.out.println("Found an error!");
		}

	}
}
