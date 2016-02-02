import javax.swing.JFrame;

public class NoughtsCrossesGUI 
{
	private NoughtsCrosses game;
	private NoughtsCrossesModel model;
	public NoughtsCrossesGUI(String nickname)
	{
		this.game = new NoughtsCrosses();
		NoughtsCrossesComponent comp = new NoughtsCrossesComponent(game);
		this.model= comp.getModel();
		JFrame frame = new JFrame("Noughts and Crosses");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(comp);
		frame.setVisible(true);
	}
	public NoughtsCrossesModel getModel()
	{
		return this.model;
	}
}
