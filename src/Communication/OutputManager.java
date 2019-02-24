package Communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Handles all operations for sending data to a ConnectionEndpoint
 * Written by Michael Schuetze on 2/18/2019.
 */
public class OutputManager {

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Sends a message to a list of recipients
     * @param messageContents - contents of the message being sent to the recipients
     * @param recipients - ObjectOutStream for the ConnectionEndpoint
     */
    public synchronized void sendMessage(HashMap<String, Object> messageContents, ObjectOutputStream[] recipients) {
        Message message = new Message(messageContents);

        for ( ObjectOutputStream recipient : recipients) {
            try {
                recipient.writeObject(message);
                recipient.flush();
            } catch (IOException IOex) {
                System.out.println("[ERROR] Could Not Send Message:\n\t" + message.toString());
            }
        }
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
}
