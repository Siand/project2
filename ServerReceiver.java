import java.net.*;
import java.util.ArrayList;
import java.io.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
  private String myClientsName;
  private BufferedReader myClient;
  private Scores scores;
  private PrintStream print;
  private ClientTable table;
  private Online on;
  private inGame ingame;

  public ServerReceiver(String n, BufferedReader c, PrintStream p, Scores s,ClientTable clientTable,Online o,inGame in) {
    myClientsName = n;
    myClient = c;
    scores = s;
    print=p;
    table = clientTable;
    on =o;
    ingame=in;
  }

  public void run() {
    try {
      while (true) {
        String command = myClient.readLine();
        if(command.equals("online"))
        {
        	print.println("ONLINE LIST");
		    String oT="";
		    for(int i=0;i<on.online.size();i++)
		    {
		    	oT+=on.online.get(i);
		    	oT+=" ";
		    }
		    	print.println(oT);
        }
        if(command.equals("scores"))
        {
        	String scoreBoard="";
        	print.println("SCORE_BOARD");
        	for(int i=0;i<on.online.size();i++)
        	{
        		scoreBoard+=on.online.get(i);
        		scoreBoard+=" ";
        		scoreBoard+=Scores.getScore(on.online.get(i));
        		scoreBoard+=".";
        	}
        	for(int i=0;i<on.offline.size();i++)
        	{
        		scoreBoard+=on.offline.get(i);
        		scoreBoard+=" ";
        		scoreBoard+=Scores.getScore(on.offline.get(i));
        		scoreBoard+=".";
        	}
        	print.println(scoreBoard);
        }
        if(command.contains("INGAME/|"))
        {
        	int length = command.length();

        	for(int i=0;i<length;i++)
        	{
        		if(command.charAt(i)=='|')
        		{
                	String playerName=command.substring(i+1,length);
                	if(!ingame.getInGame(playerName))
                	{
                		boolean flag=false;
        	        	for(int j=0;j<on.online.size();j++)
        	        		if(command.equals(on.online.get(j)))
        	        			flag=true;
        	        	if(!flag)
        	        		print.println("Player not found");
        	        	else 
        	        	{
        	        		Message c = new Message(myClientsName,playerName);
        	        		MessageQueue queue = table.getQueue(playerName);
        	        		queue.offer(c);	
        	        	}
                	}
                	else print.println("Player is in a game");
        		}
        	}
        }
        	
       // TODO 	else 
        		//connection
        		// game init
        		// updates?????
      }
    }
    catch (IOException e) {
    	on.online.remove(myClientsName);
    	on.offline.add(myClientsName);
      System.err.println("Something went wrong with the client " 
                       + myClientsName + " " + e.getMessage()); 
      // No point in trying to close sockets. Just give up.
      // We end this thread (we don't do System.exit(1)).
    }
  }
}
