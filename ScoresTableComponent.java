import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoresTableComponent extends JPanel{

	private ArrayList<JLabel> scoresTable;
	public ScoresTableComponent(String x)
	{
		 scoresTable = new ArrayList<JLabel>();
		 setTable(x);
		 setLayout(new GridLayout(scoresTable.size(),1));
		 for(int i=0;i<scoresTable.size();i++)
		 {
			 add(scoresTable.get(i));
		 }
	}
	public void setTable(String x)
	{
		String newString="";
		int j=0;
		for(int i=0;i<x.length();i++)
			if(x.charAt(i)=='.')
			{
				newString=x.substring(j,i);
				scoresTable.add(new JLabel(newString));
				j=i+1;
			}
	}

}
