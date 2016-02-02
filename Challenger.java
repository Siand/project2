import java.awt.BorderLayout;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class Challenger extends JPanel {
	public Challenger(PrintStream server){
	JTextField opponent= new JTextField();
	JButton challenge = new JButton("Challenge");
	challenge.addActionListener(e->
	{
		String x = opponent.getText();
		server.println(x);
	});
	setLayout(new BorderLayout());
	add(opponent,BorderLayout.CENTER);
	add(challenge,BorderLayout.SOUTH);
	}
}
