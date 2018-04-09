package pyjah.server.pkg;

/*				Modified and implemented by Ammanuel.
 *					This is the Server class.
 *
 *		If you need to use specific date from the server, check the server class for methods.
 */

public class ServerTest {
    public static void main (String[] args) throws ClassNotFoundException{
        Server PYJAH = new Server();
        PYJAH.startServer();
    }
}