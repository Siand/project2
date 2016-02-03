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

  public ServerReceiver(String n, BufferedReader c, PrintStream p, Scores s,ClientTable clientTable,Online o) {
    myClientsName = n;
    myClient = c;
    scores = s;
    print=p;
    table = clientTable;
    on =o;
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
        else
        {
        	boolean flag=false;
	
	        	for(int i=0;i<on.online.size();i++)
	        		if(command.equals(on.online.get(i)))
	        			flag=true;
	        	if(!flag)
	        		print.println("Player not found");
	        	else 
	        	{
	        		Message c = new Message(myClientsName,command);
	        		MessageQueue queue = table.getQueue(command);
	        		queue.offer(c);	
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
