package Sample;

import ClientSide.Client;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Written by Michael Schuetze on 2/24/2019.
 */
public class SampleClient extends Client {

    private SampleClient(String ipAddress, int port) {
        super(ipAddress, port);
    }

    /////////////////////////////////////////////////////
    //              Class Functionality
    /////////////////////////////////////////////////////

    private void start() {
        System.out.println("[INFO] Starting Client");

        HashMap<String, Object> messageContents = new HashMap<>();
        messageContents.put("message", "Client wants to connect");
        sendMessage(messageContents);
        System.out.println("[INFO] Sent Initialization Message");

        conversation();
    }

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
        client.start();
    }
}
