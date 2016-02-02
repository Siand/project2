import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProposalComponent extends JPanel{
	public ProposalComponent(String nickname){
		
	
		JLabel challenger = new JLabel("You have been challenged to a game by " +nickname);
		JButton accept = new JButton("Accept");
		JButton reject = new JButton("Reject");
		add(challenger);
		add(accept);
		add(reject);
	}	
}
