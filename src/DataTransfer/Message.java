package DataTransfer;

import java.io.Serializable;

/**
 * Allows for server and clients to talk with each other
 * Written by Michael Schuetze on 12/1/2017.
 */
public class Message implements Serializable {

    /** Client that sent the message */
    private String sender;

    /** The message being sent over the server */
    private String message = "";


    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Constructor for the Message class
     * @param sender- client sending the message
     * @param message- message being sent to the clients
     */
    public Message(String sender, String message){
        this.sender = sender;
        this.message = message;
    }

    @Override
    public String toString() { return getMessage(); }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    public String getSender() { return this.sender; }

    public String getMessage() { return this.message; }

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
