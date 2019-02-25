package Communication;

import java.util.HashMap;

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
     * @param messageContents - contents for the Message received over the client-server connection
     */
    void receiveMessage(HashMap<String, Object> messageContents, int clientID);

    /**
     * Closes the connection to the other endpoint
     * @param clientID - ID to establish which connection to close
     */
    void closeConnection(int clientID);

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
