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
   if(command.contains("2INGAME/|"))
        {
        	int length = command.length();

        	for(int i=0;i<length;i++)
        	{
        		if(command.charAt(i)=='|')
        		{
                	String playerName=command.substring(i+1,length);
                //	System.out.println(playerName);
                //	System.out.println(ingame.getInGame(playerName));
                	if(!ingame.getInGame(playerName))
                	{
                		boolean flag=false;
        	        	for(int j=0;j<on.online.size();j++)
        	        		if(playerName.equals(on.online.get(j)))
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
  else if(command.contains("STARTGAME/|"))
        {
        	int length=command.length();
        	String playerNames="",p1N="",p2N="";
        	for(int i=0;i<length;i++)
        		if(command.charAt(i)=='|')
        		{
        			playerNames=command.substring(i+1,length);
        		}
        	length=playerNames.length();
        	for(int i=0;i<length;i++)
        	{
        		if(playerNames.charAt(i)==' ')
        		{
        			p1N=playerNames.substring(0, i);
        			p2N=playerNames.substring(i+1, length);


        		}
        		
        	}
        	Message m1 = new Message("GAME/|"+p1N+" "+p2N,p1N);
    		MessageQueue queue1 = table.getQueue(p1N);
    		queue1.offer(m1);	
    		ingame.setInGame(p1N, true);
    		
        	Message m2 = new Message("GAME/|"+p1N+" "+p2N,p2N);
    		MessageQueue queue2 = table.getQueue(p2N);
    		queue2.offer(m2);	
    		ingame.setInGame(p2N, true);
        }
  else if(command.contains("IS_INAGAME/|"))
        {
        	int length = command.length();

        	for(int i=0;i<length;i++)
        	{
        		if(command.charAt(i)=='|')
        		{
                	String playerName=command.substring(i+1,length);
                	print.println(ingame.getInGame(playerName));
        		}
        	}
        }
  else if(command.contains("INGAME/|"))
        {
        	int length = command.length();
        	for(int i=0;i<length;i++)
        	{
        		if(command.charAt(i)=='|')
        		{
        			for(int j=i;j<length;j++)
        			{
        				if(command.charAt(j)==' ')
        				{
        					String name=command.substring(i+1,j);
        					boolean ing=false;
        					if(command.charAt(length-1)=='t')
        					{
        						ing =true;
        					}
        					ingame.setInGame(name,ing);
        				}
        			}
        		}
        	}
        }
  else if(command.contains("/|"))
        {
        	
        	int length = command.length();
        	for(int i=0;i<length;i++)
        	{
        		String nickname="";
        		if(command.charAt(i)=='|')
        		{
        			nickname=command.substring(0,i-1);
    				String move=command.substring(i+1, length);
    				Message m2 = new Message(move,nickname);
    				MessageQueue queue = table.getQueue(nickname);
    				queue.offer(m2);	
        		}
        	}
        }
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
