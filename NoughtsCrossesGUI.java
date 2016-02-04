import javax.swing.JFrame;

public class NoughtsCrossesGUI 
{
	private NoughtsCrossesModel model;
	private BoardView board;
	public NoughtsCrossesGUI()
	{
		NoughtsCrosses nc = new NoughtsCrosses();
		this.model = new NoughtsCrossesModel(nc);
		board = new BoardView(model);
		model.addObserver(board);
		JFrame frame = new JFrame("Noughts and Crosses");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(board);
		frame.setVisible(true);
	}
	public BoardView getBoard()
	{
		return this.board;
	}
	
}
