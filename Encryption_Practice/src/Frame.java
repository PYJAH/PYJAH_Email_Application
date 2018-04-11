import java.io.*;
import java.net.*;
import java.util.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

public class Frame implements Serializable {

	String data;
	Key k;
	byte[] iv;

	Frame(String mydata, Key key, byte[] ivector) {
		data = mydata;
		k = key;
		if (ivector == null) {
			iv = null;
		} else {
			iv = new byte[ivector.length];
			for (int i = 0; i < ivector.length; i++) {
				iv[i] = ivector[i];
			}
		}
	}

}
