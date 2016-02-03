import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;
  private Scores scoreBoard;
  private String nickname;

  ClientReceiver(BufferedReader server,String nickname) {
    this.server = server;
    this.nickname=nickname;
  }

  public void run() {
    // Read from the server, also used to update the online user list and the score board:
	  
    try {
      while (true) {	
        String s = server.readLine();
        if(s.equals("ONLINE LIST")) 
        {
        	ListOfPlayers.setPlayers(server.readLine()); 
        }
        else if(s.equals("SCORE_BOARD"))
        {
        	LocalScoreboard.setPlayers(server.readLine());
        }
        else
        {
	        if(s!="Player not found" && s!=nickname)
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
