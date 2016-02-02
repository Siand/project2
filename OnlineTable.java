import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OnlineTable
{
	JFrame frame;
	private String online="";
	private JLabel onlineTable;
	public OnlineTable()
	{
		 onlineTable = new JLabel(online);
		 
		frame = new JFrame("Players Online");
		frame.setSize(250, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(onlineTable);
		frame.setVisible(false);
	}
	public void setVisible(boolean b)
	{
		frame.setVisible(b);
	}
	public void setTable(String x)
	{
		onlineTable.setText(x);
	}
}
