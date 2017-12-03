package Client;

import DataTransfer.Message;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

/**
 * Handles all communication from the server
 * Written by Michael Schuetze on 12/2/2017.
 */
public class InputManager extends Thread {

    /** Handles communication from the server */
    private ObjectInputStream inputStream;

    /** The client the thread is receiving data for */
    private Client client;

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Constructor for the InputManager class
     * @param client- the client the thread works for
     * @param socket- the socket that has been connected to in order
     */
    InputManager(Client client, Socket socket){
        this.client = client;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException IOex) {
            System.err.print("\nError opening connection: " + IOex.getMessage());
        }
    }

    /**
     * Continually waits for messages from the server
     */
    @Override
    public void run(){
        try {
            while (true) {
                // Receive Message from the server
                Message message = (Message) inputStream.readObject();

                // Allows for the serverThread to update while the thread is still receiving data
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        receiveMessage(message);
                    }
                });
            }
        } catch (ClassNotFoundException NFex) {
            System.err.println("Getting Message err: " + NFex.getMessage());
        } catch (IOException io) {
            // Close the communication line to the dropped client
            closeConnection();

            // Display that the socket and streams has been closed
            System.out.println("Streams and socket have been closed");
        }
    }

    /**
     * Closes the connection to the server
     */
    public void close() {
        try{
            inputStream.close();
        }catch (IOException IOex){
            System.err.print("\nOpening inconnection: " + IOex.getMessage());
            System.out.println("Connection has been closed."+ IOex.getMessage());
        }
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    private void receiveMessage(Message message) {}

    /**
     * Closes the connection between server and client
     */
    private void closeConnection() {
        System.out.println("Closing connection.");

        try{
            //outputStream.close();
            inputStream.close();
            //socket.close();
        }catch (IOException IOex){
            System.err.print("\nIOex Client-closeClient: " + IOex.getMessage());
        }
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////
}
