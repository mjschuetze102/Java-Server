package Communication;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Handles all operations for receiving data from a ConnectionEndpoint
 * Written by Michael Schuetze on 2/18/2019.
 */
public class InputManager extends Thread {

    /** Stream used to collect data for the ConnectionEndpoint **/
    private ObjectInputStream inputStream;

    /** The endpoint which receives data from the inputManager **/
    private ConnectionEndpoint endpoint;

    /** The unique identifier for a client **/
    private int clientID;

    /**
     * Initialize the thread that collects data for the ConnectionEndpoint
     * @param endpoint - the endpoint which will get the data the InputManager receives
     * @param inputStream - the input stream for receiving data
     * @param clientID - used for the server to know who sent the message
     */
    public InputManager(ConnectionEndpoint endpoint, ObjectInputStream inputStream, int clientID) {
        this.endpoint = endpoint;
        this.inputStream = inputStream;
        this.clientID = clientID;
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Reads a message and sends it to the ConnectionEndpoint
     */
    @Override
    public void run() {
        try {
            while (true) {
                // Receive Message
                Message message = (Message) inputStream.readObject();

                // Send message to the ConnectionEndpoint
                endpoint.receiveMessage(message.getContents(), clientID);
            }
        } catch (ClassNotFoundException CNFex) {
            System.out.println("[ERROR] Could Not Find Message");
        } catch (IOException IOex) {
            closeConnection();
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Closes the input stream
     */
    private void closeConnection() {
        endpoint.closeConnection(clientID);

        try {
            inputStream.close();
            System.out.println("[INFO] No Longer Receiving Input From Client " + clientID);
        } catch (IOException IOex) {
            System.out.println("[ERROR] Did Not Close Connection Properly");
        }
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
