import javax.swing.JPanel;
import java.awt.BorderLayout;

public class NoughtsCrossesComponent extends JPanel
{
	private NoughtsCrossesModel model;
	private BoardView board;
	public NoughtsCrossesComponent(NoughtsCrosses game)
	{
		super();
		NoughtsCrossesModel m = new NoughtsCrossesModel(game);
		this.model=m;
		board = new BoardView(model);
		
		model.addObserver(board);
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
	}
	public BoardView getBoard()
	{
		return this.board;
	}
	
}
