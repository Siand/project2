// Usage:
//        java Server
//
// There is no provision for ending the server gracefully.  It will
// end if (and only if) something exceptional happens.


import java.net.*;
import java.io.*;

public class Server {

  public static void main(String [] args) {

    // This will be shared by the server threads:
    Scores scores = new Scores();
    Turns turns =new Turns();
    ClientTable clientTable = new ClientTable();
    
    if (args.length != 1) {
        System.err.println("Usage: java Server Port Number");
        System.exit(1); // Give up.
      }

    // Open a server socket:
    ServerSocket serverSocket = null;
    int port=0;
    try {
    	port = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException e) {
        System.out.println("Error: Port number is not an integer");
    }

    // We must try because it may fail with a checked exception:
    try {
      serverSocket = new ServerSocket(port);
    } 
    catch (IOException e) {
      System.err.println("Couldn't listen on port " + port);
      System.exit(1); // Give up.
    }

    // Good. We succeeded. But we must try again for the same reason:
    try { 
    	Online o= new Online();
      // We loop for ever, as servers usually do:

      while (true) {
        // Listen to the socket, accepting connections from new clients:
        Socket socket = serverSocket.accept();

        // This is so that we can use readLine():
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        
        PrintStream toClient = new PrintStream(socket.getOutputStream());

        // We ask the client what its name is:
        String x = fromClient.readLine();
        String clientName;
        int howMany=0;
        for(int i=0;i<o.online.size();i++)
        	if(o.online.get(i).contains(x))
        		howMany++;
        String hM = ""+howMany;
        if(howMany>0)
        	clientName = x.concat(hM) ;
        else 
        	clientName=x;
        
        //Send new nickname to client
        toClient.println(clientName);
        // For debugging:
        System.out.println(clientName + " connected");

        // We add the client to the tables:
        o.online.add(clientName);
        scores.add(clientName);
        clientTable.add(clientName);

        // We create and start a new thread to read/write from/to the client:
        
        (new ServerReceiver(clientName, fromClient, toClient, scores,clientTable,o)).start();

        // We create and start a new thread to write to the client:
        (new ServerSender(turns, toClient, clientName,clientTable)).start();
      }
    } 
    catch (IOException e) {
      // Lazy approach:
      System.err.println("IO error " + e.getMessage());
      // A more sophisticated approach could try to establish a new
      // connection. But this is beyond this simple exercise.
    }
  }
}
