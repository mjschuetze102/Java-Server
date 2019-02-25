package Sample;

import ClientSide.Client;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Sample client for a chat room
 * Written by Michael Schuetze on 2/24/2019.
 */
public class SampleClient extends Client {

    private SampleClient(String ipAddress, int port) {
        super(ipAddress, port);
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    /**
     * Starting code to initialize the client
     * Sends a message which will be interpreted by the server
     * @param name - name of the client
     */
    private void start(String name) {
        System.out.println("[INFO] Starting Client");

        HashMap<String, Object> messageContents = new HashMap<>();
        messageContents.put("message", "Client wants to connect");
        messageContents.put("name", name);
        sendMessage(messageContents);
        System.out.println("[INFO] Sent Initialization Message");

        conversation();
    }

    /**
     * Provide functionality for receiving messages over the client-server connection
     * @param messageContents - contents for the Message received over the client-server connection
     * @param clientID - unique identifier for who sent the message
     */
    @Override
    public void receiveMessage(HashMap<String, Object> messageContents, int clientID) {
        System.out.println(messageContents.get("message"));
    }

    /////////////////////////////////////////////////////
    //              Getters and Setters
    /////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    //               Helper Functions
    /////////////////////////////////////////////////////

    /**
     * Loop which allows the user to communicate with everyone else on the server
     */
    private void conversation() {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            HashMap<String, Object> messageContents = new HashMap<>();
            messageContents.put("message", scanner.nextLine());

            sendMessage(messageContents);
        }
    }

    /////////////////////////////////////////////////////
    //               Testing Purposes
    /////////////////////////////////////////////////////

    public static void main(String[] args) {
        SampleClient client = new SampleClient("localhost", 9001);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        client.start(name);
    }
}
