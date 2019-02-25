package Sample;

import ServerSide.Server;

import java.util.HashMap;

/**
 * Written by Michael Schuetze on 2/24/2019.
 */
public class SampleServer extends Server {

    private SampleServer(int maxQueuedUsers, int port) {
        super(maxQueuedUsers, port);
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    @Override
    public void receiveMessage(HashMap<String, Object> messageContents, int clientID) {
        HashMap<String, Object> newMessage = new HashMap<>();

        newMessage.put("message", "[Client " + String.valueOf(clientID) + "] " + messageContents.get("message"));

        //sendMessage(newMessage, new int[]{clientID});
        sendMessageToAll(newMessage);
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////

    public static void main(String[] args) {
        SampleServer server = new SampleServer(10, 9001);
    }
}
