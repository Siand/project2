import javax.swing.JPanel;
import java.awt.BorderLayout;

public class NoughtsCrossesComponent extends JPanel
{
	private NoughtsCrossesModel model;
	public NoughtsCrossesComponent(NoughtsCrosses game)
	{
		super();
		NoughtsCrossesModel m = new NoughtsCrossesModel(game);
		this.model=m;
		BoardView board = new BoardView(model);
		ButtonPanel controls = new ButtonPanel(model);
		
		model.addObserver(board);
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
	}
	public NoughtsCrossesModel getModel()
	{
		return this.model;
	}
	
}
