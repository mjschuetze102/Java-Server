import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Stores the output streams for all of the connected clients
 * Written by Michael Schuetze on 2/21/2019.
 */
public class CollectionManager {

    /** A collection of output streams for clients connected to the server **/
    static private HashMap<Integer, ObjectOutputStream> clients = new HashMap<>();

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Store the output stream for the client
     * @param clientID - the client whose output is being stored
     * @param outputStream - the output stream to send information to the client
     */
    static synchronized void addOutput(int clientID, ObjectOutputStream outputStream){
        clients.put(clientID, outputStream);
    }

    /**
     * Remove the output stream for the client
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
