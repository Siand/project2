import java.io.BufferedReader;
import java.io.PrintStream;

import javax.swing.JFrame;

public class Menu
{
	public Menu(PrintStream stream,String nickname,BufferedReader reader)
	{
		MenuOptions1 m1= new MenuOptions1(stream,nickname,reader);
		JFrame frame = new JFrame("Noughts and Crosses ONLINE");
		frame.setSize(250, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(m1);
		frame.setVisible(true);
	}
}
