package Sample;

import ServerSide.Server;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Sample server for a chat room
 * Written by Michael Schuetze on 2/24/2019.
 */
public class SampleServer extends Server {

    /** Used to map a name to an ID and an ID to a name **/
    private HashMap<String, Integer> nameToId = new HashMap<>();
    private HashMap<Integer, String> idToName = new HashMap<>();

    private SampleServer(int maxQueuedUsers, int port) {
        super(maxQueuedUsers, port);
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Provide functionality for receiving messages over the client-server connection
     * @param messageContents - contents for the Message received over the client-server connection
     * @param clientID - unique identifier for who sent the message
     */
    @Override
    public synchronized void receiveMessage(HashMap<String, Object> messageContents, int clientID) {
        // Initialize the message to send
        HashMap<String, Object> newMessage = new HashMap<>();

        // Get the message that was sent
        String message = (String) messageContents.get("message");

        // See what actions to take based on message
        if (message.equals("Client wants to connect")) {
            // Map the newly connected client name to the unique identifier
            mapClientName(clientID, (String) messageContents.get("name"));
            newMessage.put("message", idToName.get(clientID) + " has connected");

            sendMessageToAll(newMessage);
        } else if (message.indexOf("/pm") == 0) {
            // Private message a specific person on the server
            String[] messagePieces = message.split(" ");
            int sendTo = nameToId.get(messagePieces[1]);
            newMessage.put("message", "{Private} [" + idToName.get(clientID) + "] " +
                    String.join(" ", Arrays.copyOfRange(messagePieces, 2, messagePieces.length)));

            sendMessage(newMessage, new int[]{clientID, sendTo});
        } else {
            // Send a normal message to the whole server
            newMessage.put("message", "[" + idToName.get(clientID) + "] " + messageContents.get("message"));

            sendMessageToAll(newMessage);
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Maps the clientID to the client name so the server can keep track of people
     * @param clientID - unique identifier for the client
     * @param name - name for the client
     */
    private synchronized void mapClientName(int clientID, String name) {
        nameToId.put(name, clientID);
        idToName.put(clientID, name);
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////

    public static void main(String[] args) {
        new SampleServer(10, 9001);
    }
}
