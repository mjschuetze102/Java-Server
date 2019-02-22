import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Handles setting up a connection to the client
 * Written by Michael Schuetze on 2/21/2019.
 */
public class ConnectionHandler {

    /** Socket which all communication to the server will happen over **/
    private ServerSocket serverSocket;

    /** The endpoint which receives data from the inputManager **/
    private Server server;

    /**
     * Initializes the class that will create connections to the clients
     * @param server - the ConnectionEndpoint the input and output managers will communicate with
     * @param serverSocket - the socket communication will happen over
     */
    public ConnectionHandler(Server server, ServerSocket serverSocket) {
        this.server = server;
        this.serverSocket = serverSocket;
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Runs the server allowing for users to connect
     */
    public void run() {
        // Run the server establishing connections to all clients that attempt to connect
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                // Create the input and output streams for the connection
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Creates the Input and Output Managers for the client
                int clientID = CollectionManager.addOutput(outputStream);
                new InputManager(server, inputStream, clientID);
            } catch (EOFException EOFex) {
                System.out.println("[INFO] Server Connection Ended");
            } catch (IOException IOex) {
                System.out.println("[ERROR] Could Not Establish Connection");
            }
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
