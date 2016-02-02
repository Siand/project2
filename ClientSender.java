import java.io.*;


// Repeatedly reads recipient's nickname and text from the user in two
// separate lines, sending them to the server (read by ServerReceiver
// thread).

public class ClientSender extends Thread {

  private String nickname;
  private PrintStream server;
  private BufferedReader fromServer;
  private ListOfPlayers online;

  ClientSender(String nickname, PrintStream server,BufferedReader from) {
    this.nickname = nickname;
    this.server = server;
    this.fromServer=from;
  }

  public void run() {
    // So that we can use the method readLine:
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    try {

      Menu menu = new Menu(server,nickname,fromServer); 
      // Then loop forever sending messages to recipients via the server:
      while (true) {
        String command = user.readLine();
          server.println(command);
      }
    }
    catch (IOException e) {
      System.err.println("Communication broke in ClientSender" 
                        + e.getMessage());
      System.exit(1);
    }
  }
}

