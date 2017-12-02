package Server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Runs the server allowing for clients to connect
 * Written by Michael Schuetze on 12/1/2017.
 */
public class Server {

    /** Port number the system is running on */
    final private int PORT = 9001;

    /** Number of users */
    final private int MAXUSERS = 10;

    /** Create the server socket */
    private ServerSocket serverSocket;

    /** Get the socket clients will connect to */
    private Socket socket;

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Runs the server allowing for users to connect
     */
    public void run() {
        // Catch IO exceptions due to improper socket connection
        try {
            // Allocate the port for use by the server socket
            serverSocket = new ServerSocket(PORT, MAXUSERS);

            while (true) {
                try {
                    waitForConnection();
                    setupCommunicationThread();
                } catch (EOFException eof) {
                    System.err.print("Server Connection Ended");
                }
            }
        } catch(IOException io){
            io.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Waits for a client to try and make a connection
     * @throws IOException - error in connecting to socket
     */
    private void waitForConnection() throws IOException {
        socket = serverSocket.accept();

        // Display that the socket has been accepted
        System.out.println("Socket has been connected");
    }

    /**
     * Sets up the send/receive streams for communication to the client
     * @throws IOException - error in connecting to socket
     */
    private void setupCommunicationThread() throws IOException {
        // Sets up the output stream to send data and flushes out data
        ClientThread thread = new ClientThread(socket);
        thread.start();

        // Display that thread has been created
        System.out.println("Thread has been created");
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////

    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
}
