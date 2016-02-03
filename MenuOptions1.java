import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.PrintStream;

public class MenuOptions1 extends JPanel
{
	public MenuOptions1(PrintStream server, String nickname,BufferedReader reader)
	{
		super();
		OpponentChallenger opponent= new OpponentChallenger(server);
		OnlineTable onlineTable= new OnlineTable();
		ScoresTable scoresTable = new ScoresTable();
		JLabel nick = new JLabel("Logged in as " + nickname);
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(e->
		{
			opponent.setVisible(true);
			onlineTable.setVisible(false);
			scoresTable.setVisible(false);
		});
		
		JButton online = new JButton("Online players");
		online.addActionListener(e->
		{
			server.println("online");
			try {
				Thread.sleep(1000);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			onlineTable.setTable(ListOfPlayers.getPlayers());
			opponent.setVisible(false);
			scoresTable.setVisible(false);
			onlineTable.setVisible(true);
			
		});
		JButton scores = new JButton("Scores");
		scores.addActionListener(e->
		{
			server.println("scores");
			try {
				Thread.sleep(1000);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			scoresTable.setTable(LocalScoreboard.getBoard());
			scoresTable.setVisible(true);
			onlineTable.setVisible(false);
			opponent.setVisible(false);
		});

		setLayout(new GridLayout(4,1));
		add(nick);
		add(newGame);
		add(online);
		add(scores);
	}
}
