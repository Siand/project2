import java.awt.GridLayout;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoresTable
{
	JFrame frame;
	private ScoresTableComponent comp;
	public ScoresTable(String x)
	{
		comp= new ScoresTableComponent(x);
		frame = new JFrame("Players Online");
		frame.setSize(250, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(comp);
		frame.setVisible(false);
	}
	public void setVisible(boolean b)
	{
		frame.setVisible(b);
	}
}
