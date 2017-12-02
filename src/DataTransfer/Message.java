package DataTransfer;

import java.io.Serializable;
import java.util.*;

/**
 * Allows for server and clients to talk with each other
 * Written by Michael Schuetze on 12/1/2017.
 */
public class Message implements Serializable {

    /** Client that sent the message */
    private String sender;

    /** Clients intended to receive the message */
    private ArrayList<String> receivers = new ArrayList<>();

    /** The message being sent over the server */
    private String message = "";


    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Constructor for the Message class
     * @param sender- client sending the message
     * @param receivers- clients intended to receive the message
     * @param message- message being sent to the clients
     */
    public Message(String sender, ArrayList<String> receivers, String message){
        this.sender = sender;
        this.receivers = receivers;
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    public String getSender(){return this.sender;}

    public ArrayList<String> getReceivers(){return this.receivers;}

    public String getMessage(){return this.message;}

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
