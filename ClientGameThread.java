import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ClientGameThread extends Thread {

	private NoughtsCrossesGUI game;
	private BufferedReader fromServer;
	private PrintStream toServer;
	private String nickname;
	private boolean myturn;
	public ClientGameThread(String nickname,boolean myTurn,BufferedReader fromServer,PrintStream toServer)
	{
		game= new NoughtsCrossesGUI();
		this.myturn=myTurn;
		this.fromServer=fromServer;
		this.toServer=toServer;
		this.nickname=nickname;
	}
	public void run()
	{
		int x=0,y=0,lastTurn=55,turn;
		boolean mT=myturn;
		while(!game.getBoard().getModel().winner())
		{
			game.getBoard().setTurn(mT);
			if(!mT)
			{
				mT=true;
				try {
					String z=(fromServer.readLine());
					x=getx(z);
					y=gety(z);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				game.getBoard().getModel().enemyTurn(x, y);
			}
			else
			{
				while((turn=game.getBoard().getModel().getLastTurn())==lastTurn)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				lastTurn=turn;
				toServer.println(nickname+"/|"+String.valueOf(lastTurn));
				mT=false;
			}
		}
	}
	private int getx(String move)
	{
		int x = (Integer.parseInt(move)/10)-1;
		System.out.println(x);
		return x;
	}
	private int gety(String move)
	{
		int y = (Integer.parseInt(move)%10)-1;
		System.out.println(y);
		return y;
	}
}
