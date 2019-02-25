package ServerSide;

import Communication.ConnectionEndpoint;
import Communication.OutputManager;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
     * @param maxQueuedUsers - the maximum numbers of users who can use the server at once
     * @param port - the port number the server is listening on
     */
    public Server(int maxQueuedUsers, int port) {
        this.outputManager = new OutputManager();

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port, maxQueuedUsers);
            (new ConnectionHandler(this, serverSocket)).start();
        } catch (IOException IOex) {
            System.out.println("[ERROR] Could Not Establish Server");
        }
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Provide functionality for receiving messages over the client-server connection
     * @param messageContents - contents for the Message received over the client-server connection
     */
    public abstract void receiveMessage(HashMap<String, Object> messageContents, int clientID);

    /**
     * Provide functionality for sending messages over the client-server connection
     * @param messageContents - contents for the Message to be sent over the client-server connection
     * @param clients - list of unique IDs for the clients the message will be sent to
     */
    protected void sendMessage(HashMap<String, Object> messageContents, int[] clients) {
        // List of endpoints to send the information to
        ObjectOutputStream[] recipients = new ObjectOutputStream[clients.length] ;

        // Get the list of output streams to send message over
        for (int index = 0; index < clients.length; index++) {
            recipients[index] = ConnectionManager.getSpecificClientOutput(clients[index]);
        }

        // Send the message to the clients
        outputManager.sendMessage(messageContents, recipients);
    }

    /**
     * Provide functionality for sending messages to all the clients
     * @param messageContents - contents for the Message to be sent over the client-server connection
     */
    protected void sendMessageToAll(HashMap<String, Object> messageContents) {
        // Send the message to the clients
        outputManager.sendMessage(messageContents, ConnectionManager.getAllClientOutputs());
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
