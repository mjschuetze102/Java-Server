import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

/**
 * Represents the ConnectionEndpoint for the client end
 * Written by Michael Schuetze on 2/18/2019.
 */
public abstract class Client implements ConnectionEndpoint {

    /** Manager that handles sending output **/
    private OutputManager outputManager;

    /**
     * Initializes the client half of the connection
     * @param ipAddress - address the server is running at
     * @param port - the port number the server is listening on
     */
    public Client(String ipAddress, int port) {
        try {
            // Create the communication line between endpoints
            Socket socket = new Socket(InetAddress.getByName(ipAddress), port);

            // Create the input and output streams for the connection
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            // TODO: Figure out how outputStream is being stored client side

            // Creates the Input and Output Managers for the client
            new InputManager(this, inputStream, 0);
            this.outputManager = new OutputManager();
        } catch (IOException IOex) {
            System.out.println("[ERROR] Could Not Establish Connection");
        }
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Provide functionality for receiving messages over the client-server connection
     * @param messageContents - contents for the Message to be sent over the client-server connection
     */
    public abstract void receiveMessage(HashMap<String, Object> messageContents);

    /**
     * Provide functionality for sending messages over the client-server connection
     * @param messageContents - contents for the Message to be sent over the client-server connection
     */
    protected void sendMessage(HashMap<String, Object> messageContents) {
        // TODO Implement outputManager.sendMessage();
    }

    /**
     * Closes the connection to the other endpoint
     * @param clientID - unique identifier to figure out what connection to close
     */
    public void closeConnection(int clientID) {
        // TODO: Close the outputStream connection
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Abstract Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
