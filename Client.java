// Usage:
//        java Client user-nickname hostname
//
// After initializing and opening appropriate sockets, we start two
// client threads, one to send messages, and another one to get
// messages.
//
// A limitation of our implementation is that there is no provision
// for a client to end after we start it. However, we implemented
// things so that pressing ctrl-c will cause the client to end
// gracefully without causing the server to fail.
//
// Another limitation is that there is no provision to terminate when
// the server dies.


import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Client {

  public static void main(String[] args) {

    // Check correct usage:
    if (args.length != 3) {
      System.err.println("Usage: java Client user-nickname port number hostname");
      System.exit(1); // Give up.
    }
    // Initialize information:
    int port=0;
    String nickname = args[0];
    try {
        port = Integer.parseInt(args[2]);
    }
    catch (NumberFormatException e) {
        System.out.println("Error: Port number is not an integer");
    }
    String hostname = args[1];

    // Open sockets:
    PrintStream toServer = null;
    BufferedReader fromServer = null;
    Socket server = null;

    try {
      server = new Socket(hostname, port);
      toServer = new PrintStream(server.getOutputStream());
      fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
      
    } 
    catch (UnknownHostException e) {
      System.err.println("Unknown host: " + hostname);
      System.exit(1); // Give up.
    } 
    catch (IOException e) {
      System.err.println("The server doesn't seem to be running " + e.getMessage());
      System.exit(1); // Give up.
    }
    // Tell the server what my nickname is and wait for confirmation:
    toServer.println(nickname);
    try {
		nickname=fromServer.readLine();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
    //Create a list for online users
    // Create two client threads:
    ClientSender sender = new ClientSender(nickname,toServer,fromServer);
    ClientReceiver receiver = new ClientReceiver(fromServer,nickname,toServer);


    // Run them in parallel:
    sender.start();
    receiver.start();
    
    // Wait for them to end and close sockets.
    try {
      sender.join();
      toServer.close();
      receiver.join();
      fromServer.close();
      server.close();
    }
    catch (IOException e) {
      System.err.println("Something wrong " + e.getMessage());
      System.exit(1); // Give up.
    }
    catch (InterruptedException e) {
      System.err.println("Unexpected interruption " + e.getMessage());
      System.exit(1); // Give up.
    }
  }
}
