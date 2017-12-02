package Server;

import DataTransfer.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Thread allowing for communication to/from clients
 * Written by Michael Schuetze on 12/1/2017.
 */
public class ClientThread extends Thread {

    /** Socket communication will take place on */
    private Socket socket;

    /** Client identifier (used to distinguish clients) */
    private String clientName;

    /** Handles communication from the client */
    private ObjectInputStream inputStream;

    /** Handles communication to the client */
    private ObjectOutputStream outputStream;

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Constructor for the ClientThread class
     * @param socket- the accepted ServerSocket the system will be running on
     */
    ClientThread(Socket socket){ this.socket = socket; }

    /**
     * Loops the communication with the client until connection has been broken
     */
    public void run(){
        // Catch IO exception due to improper socket connection
        try {
            // Gets the means to communicate with the client
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            // Catch CNF exception due to the Message class not being found
            try {
                startCommunication();

                while (true) {
                    // Read in the message being sent
                    Message message = (Message) inputStream.readObject();

                    // Display that the message has been read
                    System.out.println("Message: "+ message.toString()+ " has been read.");

                    // Send the message
                    OutputManager.sendMessage(message);

                    // Display that the message has been sent out
                    System.out.println("Message: "+ message.toString()+ " has been sent.");
                }

            } catch (ClassNotFoundException cnf) {
                System.err.println("Could not find Message class");
            } catch (IOException io) {
                // Close the communication line to the dropped client
                inputStream.close();
                OutputManager.removeOutput(clientName);
                socket.close();

                // Display that the socket and streams has been closed
                System.out.println("Streams and socket have been closed");
            }

        } catch (IOException io) {
            System.err.println("Server: " + io.getMessage());
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Starts the communication between the server and client
     * @throws IOException - error in connecting to socket
     * @throws ClassNotFoundException - error in location Message class
     */
    private void startCommunication() throws IOException, ClassNotFoundException {
        // Receive the name of the client
        // Message should be clientName, new ArrayList(), ""
        Message message = (Message) inputStream.readObject();
        if ( !message.getMessage().equals("") ){
            System.err.print("Faulty Identifier Message!! Possible false name.");
        }else {
            // Add the client to the ClientList
            clientName= message.getSender();
            OutputManager.addOutput(message.getSender(), outputStream);

            // Display that the Client has been added to the ClientList
            System.out.println("Client has been added to the server");
        }

        // Send a message to the client saying the connection has been established
        ArrayList<String> client= new ArrayList<>(); client.add(clientName);
        message= new Message("Server", client, "Connection has been established.");
        OutputManager.sendMessage(message);
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}