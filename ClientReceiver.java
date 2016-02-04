import java.io.*;
import java.net.*;
import java.util.ArrayList;

// Gets messages from other clients via the server (by the
// ServerSender thread).

public class ClientReceiver extends Thread {

  private BufferedReader server;
  private Scores scoreBoard;
  private String nickname;
  private PrintStream SO;

  ClientReceiver(BufferedReader server,String nickname,PrintStream s) {
    this.server = server;
    this.nickname=nickname;
    this.SO=s;
  }

  public void run() {
    // Read from the server, also used to update the online user list and the score board:
	  
    try 
    {
    	ClientGameThread game;
    	boolean ig=false;
    	String ingame="";
    	while (true) 
    	{	
		    if(!ig)
			{
		    	SO.println("IS_INAGAME/|"+nickname);
			    ingame = server.readLine();
			}
	    	if(ingame.equals("false"))
	    	{
	    		String s = server.readLine();
	    	
		        if(s.equals("ONLINE LIST")) 
		        {
		        	ListOfPlayers.setPlayers(server.readLine()); 
		        }
		        else if(s.equals("SCORE_BOARD"))
		        {
		        	LocalScoreboard.setPlayers(server.readLine());
		        } 
		        else if(s.contains("GAME/|"))
		        {
		        	int length=s.length();
		        	s=s.substring(6,length);
		        	length-=6;
		        	for(int i=0;i<length;i++)
		        	{
		        		if(s.charAt(i)==' ')
		        		{
		        			if(nickname.equals(s.substring(0,i)))
		        			{
		        				game = new ClientGameThread(s.substring(i+1,length), true, server, SO);
		        				ig = true;
		        				game.start();
		        			}
		        			else
		        			{
		        				game = new ClientGameThread(s.substring(0,i), false, server, SO);
		        				ig=true;
		        				game.start();
		        			}
		        			SO.println("INGAME/|"+nickname+" "+"t");
		        			game.join();
		        			SO.println("INGAME/|"+nickname+" "+"f");
		        			ig=false;
		        		}
		        	}
		        }
		        else
		        {
			        if(!s.equals("Player not found") && !s.equals(nickname) && !s.equals("Player is in a game"))
				    {
				        	Proposal p = new Proposal(s,nickname,SO);
				    }
		        }
	    	}
    	}
    }
    catch (IOException e) {
      System.out.println("Server seems to have died " + e.getMessage());
      System.exit(1); // Give up.
    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
