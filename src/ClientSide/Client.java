package ClientSide;

import Communication.ConnectionEndpoint;
import Communication.InputManager;
import Communication.OutputManager;

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

    /** Stream used to send information to the server **/
    private ObjectOutputStream outputStream;

    /** Stream used to send information to the server **/
    private Socket socket;

    /**
     * Initializes the client half of the connection
     * @param ipAddress - address the server is running at
     * @param port - the port number the server is listening on
     */
    public Client(String ipAddress, int port) {
        try {
            // Create the communication line between endpoints
            socket = new Socket(InetAddress.getByName(ipAddress), port);

            // Create the input and output streams for the connection
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            // Creates the Input and Output Managers for the client
            (new InputManager(this, inputStream, 0)).start();
            outputManager = new OutputManager();
        } catch (IOException IOex) {
            System.out.println("[ERROR] Could Not Establish Connection");
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
     */
    protected void sendMessage(HashMap<String, Object> messageContents) {
        outputManager.sendMessage(messageContents, new ObjectOutputStream[]{outputStream});
    }

    /**
     * Closes the connection to the other endpoint
     * @param clientID - unique identifier to figure out what connection to close
     */
    public void closeConnection(int clientID) {
        try {
            outputStream.close();
            socket.close();
            System.out.println("[INFO] Successfully Closed Connection");
        } catch (IOException IOex){
            System.out.println("[ERROR] Did Not Close Connection Properly");
        }
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
