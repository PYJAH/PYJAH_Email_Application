package pyjah.client.pkg;

import javafx.scene.control.TextField;

/*				Modified and implemented by Ammanuel.
 *					This is the ClientTest class.
 *
 *
 * This test class runs but doesn't take input from the console, it need Scanners for specific input/output
 * from the client.
*/


public class ClientTest {
	static TextField userText;
   public static void main (String[] args){
        Client clientPYJAH = new Client();			//"127.0.0.1", userText
        clientPYJAH.startClient();
    }
}
