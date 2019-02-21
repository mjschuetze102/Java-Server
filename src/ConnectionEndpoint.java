import DataTransfer.Message;

/**
 * Abstracts out common functionality between client and server
 * Written by Michael Schuetze on 2/18/2019.
 */
public interface ConnectionEndpoint {

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Provide functionality for receiving messages over the client-server connection
     * @param message- Message being sent over the client-server connection
     */
    void receiveMessage(Message message);

    /**
     * Provide functionality for sending messages over the client-server connection
     * @param messageComponents- Components for the Message to be sent over the client-server connection
     */
    void sendMessage(Message message);

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
