import java.net.*;
import java.io.*;
// Continuously reads from message queue for a particular client,
// forwarding to the client.

public class ServerSender extends Thread {
  private Turns turns;
  private PrintStream client;
  private String nickname;
  private ClientTable table;
  private Online online;

  public ServerSender(Turns t, PrintStream c,String nickname,ClientTable clientTable) {
    turns = t;   
    this.nickname=nickname;
    client = c;
    table= clientTable;
  }

  public void run() {
    while (true) {
    	
    	MessageQueue challenge =  table.getQueue(nickname);
    	Message m= challenge.take();
    	client.println(m);
    	
    //	int x = (int)turns.getTurns(nickname).getX();
    //	int y = (int)turns.getTurns(nickname).getY();
    //	client.println(x);
    //	client.println(y);
    }
  }
}
