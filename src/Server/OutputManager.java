package Server;

import DataTransfer.Message;

import java.io.*;
import java.util.*;

/**
 * Handles all communication to the clients
 * Written by Michael Schuetze on 12/1/2017.
 */
public class OutputManager {

    /** Collection of all clients server is connected to */
    static private HashMap<String, ObjectOutputStream> outputs = new HashMap<>();

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Sends a message to the server's notification list
     * @param message- message being sent to the clients
     */
    static synchronized void sendMessage(Message message){
        for ( String recipient : outputs.keySet()){
            try {
                outputs.get(recipient).writeObject(message);
                outputs.get(recipient).flush();
            } catch (IOException IOex) {
                System.err.print("Sending Message err: " + IOex.getMessage());
            }
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    static synchronized ArrayList<String> getClientList(){
        Set<String> keys = outputs.keySet();

        ArrayList<String> clientList = new ArrayList<>();
        clientList.addAll(keys);

        return clientList;
    }

    /**
     * Adds a client to the server's notification list
     * @param client- client identifier (used to distinguish clients)
     * @param outputStream- means of communication to the client
     */
    static synchronized void addOutput(String client, ObjectOutputStream outputStream){ outputs.put(client, outputStream); }

    /**
     * Removes a client from the server's notification list
     * @param client- client identifier (used to distinguish clients)
     */
    static synchronized void removeOutput( String client ){
        try{
            outputs.get(client).close();
            outputs.remove(client);
        }catch (IOException IOex){
            System.err.print("Closing output: " + IOex.getMessage());
        }
    }

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
