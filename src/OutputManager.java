import DataTransfer.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;

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
     * @param message - message being sent to the recipients
     * @param recipients - ObjectOutStream for the ConnectionEndpoint
     */
    static synchronized void sendMessage(Message message, ObjectOutputStream[] recipients) {
        for ( ObjectOutputStream recipient : recipients) {
            try {
                recipient.writeObject(message);
                recipient.flush();
            } catch (IOException IOex) {
                System.out.println("[ERROR] Could Not Send Message:\n\t" + message.toString());
            }
        }
    }
}
