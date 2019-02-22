import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Stores the output streams for all of the connected clients
 * Written by Michael Schuetze on 2/21/2019.
 */
public class ConnectionManager {

    /** A collection of output streams for clients connected to the server **/
    static private HashMap<Integer, ObjectOutputStream> clients = new HashMap<>();

    /** Counter to keep track of which unique ID to give to the next client to connect **/
    static private int nextID = 1; // Starts at 1 so that 0 can represent server on the client side

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Store the output stream for the client
     * @param outputStream - the output stream to send information to the client
     */
    static synchronized int addOutput(ObjectOutputStream outputStream) {
        clients.put(nextID, outputStream);
        return nextID++;
    }

    /**
     * Remove the output stream for the client
     * Also closes the connection
     * @param clientID - the client whose output is being removed
     */
    static synchronized void removeOutput(int clientID){
        try{
            clients.get(clientID).close();
        }catch (IOException IOex){
            System.out.println("[ERROR] Did Not Close Connection Properly");
        } finally {
            clients.remove(clientID);
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /**
     * Get the output streams for a specific client connected to the server
     * @param clientID - the client who a message will be sent to
     * @return OutputStream for the specific client
     */
    static synchronized ObjectOutputStream getSpecificClientOutput(int clientID) {
        return clients.get(clientID);
    }

    /**
     * Get the output streams for all clients connected to the server
     * @return array of OutputStreams for all clients
     */
    static synchronized ObjectOutputStream[] getAllClientOutputs(){
        return (ObjectOutputStream[]) clients.values().toArray();
    }

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
