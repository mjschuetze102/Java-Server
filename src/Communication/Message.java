package Communication;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Allows for server and clients to talk with each other
 * Written by Michael Schuetze on 2/24/2019.
 */
class Message implements Serializable {

    /** The information being sent over the socket **/
    private HashMap<String, Object> contents;

    /**
     * Creates a new message with the information to send
     * @param messageContents - contents of the message being sent to the recipients
     */
    Message(HashMap<String, Object> messageContents){
        this.contents = messageContents;
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    HashMap<String, Object> getContents() { return this.contents; }

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
