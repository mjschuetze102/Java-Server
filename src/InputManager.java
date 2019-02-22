import DataTransfer.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Handles all operations for receiving data from a ConnectionEndpoint
 * Written by Michael Schuetze on 2/18/2019.
 */
public class InputManager extends Thread {

    /** Stream used to collect data for the ConnectionEndpoint **/
    private ObjectInputStream input;

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
        this.endpoint= endpoint;
        this.input = inputStream;
        this.clientID = clientID;
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Reads a message and sends it to the ConnectionEndpoint
     */
    @Override
    public void run(){
        try {
            do {
                // Receive Message
                Message message = (Message) input.readObject();

                // Send message to the ConnectionEndpoint
                endpoint.receiveMessage(message);

            } while (true);

        }catch (ClassNotFoundException CNFex){
            System.out.println("[ERROR] Could Not Find Message");
        }catch (EOFException EOFex){
            close();
        } catch (IOException IOex){
            System.out.println("[ERROR] Did Not Receive Message Successfully");
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
    private void close() {
        try {
            input.close();
            System.out.println("[INFO] Successfully Closed Connection");
        } catch (IOException IOex){
            System.out.println("[ERROR] Did Not Close Connection Properly");
        }
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
