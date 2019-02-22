import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

/**
 * Represents the ConnectionEndpoint for the server end
 * Written by Michael Schuetze on 2/21/2019.
 */
public abstract class Server implements ConnectionEndpoint {

    /** Manager that handles sending output **/
    private OutputManager outputManager;

    /**
     * Initializes the server half of the connection
     * @param maxUsers - the maximum numbers of users who can use the server at once
     * @param port - the port number the server is listening on
     */
    public Server(int maxUsers, int port) {
        this.outputManager = new OutputManager();

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port, maxUsers);
            new ConnectionHandler(this, serverSocket);
        } catch (IOException IOex) {
            System.out.println("[ERROR] Could Not Establish Server");
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
        ConnectionManager.removeOutput(clientID);
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
