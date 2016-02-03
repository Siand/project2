import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ClientGameThread extends Thread {

	public ClientGameThread(String nickname,boolean myTurn,BufferedReader fromServer,PrintStream toServer)
	{
		NoughtsCrossesGUI game= new NoughtsCrossesGUI(nickname);
		int x=0,y=0,lastTurn=44,turn;
		if(!myTurn)
		{
			myTurn=true;
			try {
				String z=(fromServer.readLine());
				x=getx(z);
				y=gety(z);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.getModel().turn(x, y);
		}
		else
		{
			while((turn=game.getModel().getLastTurn())!=lastTurn)
			{
				lastTurn=turn;
				toServer.println(nickname+"."+String.valueOf(lastTurn));
			}
			myTurn=false;
		}
	}
	private int getx(String move)
	{
		int x = (Integer.parseInt(move)/10)-1;
		return x;
	}
	private int gety(String move)
	{
		int y = (Integer.parseInt(move)/10)-1;
		return y;
	}
}
