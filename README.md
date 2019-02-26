# Java-Server
An easy to use server to get your code up and running fast

## Four easy steps and you're on your way
1. Have a class that extends Server
2. Have a class that extends Client
3. Implement readMessage(HashMap<String, Object> messageContents, int clientID) for both the client and the server
   - This is a Map containing whatever you want to send
   - int clientID is a unique identifier given to each client that connects
4. Call sendMessage(HashMap<String, Object> messageContents, int[] clients)
   - This is a Map containing whatever you want to send
   - int[] clients is a list of the unique identifiers given to each client

## A look under the hood
![uml](https://user-images.githubusercontent.com/16418232/53384456-f86f8f00-3948-11e9-85ff-6fba3ffc457f.PNG "Design")
