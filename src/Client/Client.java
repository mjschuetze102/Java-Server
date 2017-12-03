package Client;

import java.io.*;
import java.net.*;

/**
 * Runs the client allowing for connection to the server
 * Written by Michael Schuetze on 12/2/2017.
 */
public class Client {

    /** Port number the system is running on */
    final private int PORT = 9001;

    /** Name of the host IP address */
    final private String HOST = "localhost";

    /** Get the socket clients will connect to */
    private Socket socket;

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Run all the functions requires to start the connection to server
     */
    public void run() {
        try {
            // Connect a new socket
            connectToServer();
            // Set up the input and output streams for the socket
            startCommunication();
        } catch (EOFException EOFex) {
            System.out.println(("Connection terminated."));
        } catch (IOException IOex) {
            IOex.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Contacts the server to try and make a connection
     * @throws IOException - error in connecting to socket
     */
    private void connectToServer() throws IOException {
        System.out.println("Searching for connection.");

        socket = new Socket(InetAddress.getByName(HOST), PORT);

        System.out.println("Connected to: " + socket.getInetAddress().getHostName()+ ":" +
                socket.getInetAddress().getHostAddress());
    }

    /**
     * Sets up the send/receive streams for communication with the server
     * @throws IOException - error in connecting to socket
     */
    private void startCommunication() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.flush();

        // Create the thread that will receive messages from the server
        InputManager inputManager= new InputManager(this, socket);
        inputManager.start();
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}