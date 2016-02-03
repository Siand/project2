import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoresTable
{
	JFrame frame;
	private String scores="";
	private JLabel scoresTable;
	public ScoresTable()
	{
		 scoresTable = new JLabel(scores);
		 
		frame = new JFrame("Players Online");
		frame.setSize(250, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scoresTable);
		frame.setVisible(false);
	}
	public void setVisible(boolean b)
	{
		frame.setVisible(b);
	}
	public void setTable(String x)
	{
		String newString="";
		int j=0;
		for(int i=0;i<x.length();i++)
			if(x.charAt(i)=='.')
			{
				newString=newString+x.substring(j,i);
				newString=newString+"\n";
				j=i+1;
			}
		   scoresTable.setText(newString);
	}
}
