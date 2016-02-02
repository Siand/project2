import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class OpponentChallenger
{
	JFrame frame;
	public OpponentChallenger(PrintStream server) 
	{
		Challenger opponent = new Challenger(server);
		
		frame = new JFrame("Challenge an opponent");
		frame.setSize(500, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(opponent);
		frame.setVisible(false);
	}
	public void setVisible(boolean b)
	{
		frame.setVisible(b);
	}
}