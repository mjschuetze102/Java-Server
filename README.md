# Java-Server
An easy to use server to get your code up and running fast. Made to be easy to pick up and place in any project.

Have a suggestion? Feel free to open a new issue.

## Five easy steps and you're on your way
1. Have a class that extends Server
2. Have a class that extends Client
3. Implement readMessage(HashMap<String, Object> messageContents, int clientID) for both the client and the server
   - This is a Map containing whatever you want to send
   - int clientID is a unique identifier given to each client that connects
4. Call sendMessage(HashMap<String, Object> messageContents, int[] clients) for server
   - This is a Map containing whatever you want to send
   - int[] clients is a list of the unique identifiers given to each client
5. Call sendMessage(HashMap<String, Object> messageContents) for client
   - This is a Map containing whatever you want to send
   
##### Sample code can be found at [src/Sample](https://github.com/mjschuetze102/Java-Server/tree/master/src/Sample)

## A look under the hood
![uml](https://user-images.githubusercontent.com/16418232/53384456-f86f8f00-3948-11e9-85ff-6fba3ffc457f.PNG "Design")
