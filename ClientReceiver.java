import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;
  private ListOfPlayers online;
  private String nickname;

  ClientReceiver(BufferedReader server,String nickname) {
    this.server = server;
    this.nickname=nickname;
  }

  public void run() {
    // Print to the user whatever we get from the server:
	  
    try {
      while (true) {	
        String s = server.readLine();
        if(s.equals("ONLINE LIST"))
      	 
	{
 		ListOfPlayers.setPlayers(server.readLine()); 
 		System.out.println(ListOfPlayers.players);
	}	
        else
        {
	        if(s!="Player not found")
	        {
	        	Proposal p = new Proposal(s,nickname);
	        }
        }
      }
    }
    catch (IOException e) {
      System.out.println("Server seems to have died " + e.getMessage());
      System.exit(1); // Give up.
    }
  }
}
