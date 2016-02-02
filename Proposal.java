import java.io.PrintStream;

import javax.swing.JFrame;

public class Proposal
{
	public Proposal(String nickname,String myName)
	{
		ProposalComponent pc = new ProposalComponent(nickname);
		JFrame frame = new JFrame(myName+", you have been challenged!");
		frame.setSize(320, 120);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(pc);
		frame.setVisible(true);
	}
}
