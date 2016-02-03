import java.awt.BorderLayout;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProposalComponent extends JPanel{
	public ProposalComponent(String nickname,JFrame frame,String myName,PrintStream server){
		
	
		JLabel challenger = new JLabel("You have been challenged to a game by " +nickname);
		JButton accept = new JButton("Accept");
		accept.addActionListener(e->
		{
			server.println("STARTGAME/|"+myName+" "+nickname);
		});
		JButton reject = new JButton("Reject");
		reject.addActionListener(e->frame.dispose());
		add(challenger);
		add(accept);
		add(reject);
	}	
}
