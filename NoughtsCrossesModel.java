import java.util.Observable;

public class NoughtsCrossesModel extends Observable
{
	private NoughtsCrosses oxo;
	private int lastTurn=55;
	private boolean turn;
	
	public NoughtsCrossesModel(NoughtsCrosses oxo)
	{
		super();
		this.oxo = oxo;
	}
	
/**
Get symbol at given location
@param i the row
@param j the column
@return the symbol at that location
*/
	public int get(int i, int j)
	{
		return oxo.get(i, j);
	}


/**
Is it cross's turn?
@return true if it is cross's turn, false for nought's turn
*/	
	public boolean isCrossTurn()
	{
		return oxo.isCrossTurn();
	}

/**
Let the player whose turn it is play at a particular location
@param i the row
@param j the column
*/
	public void turn(int i, int j)
	{
		oxo.turn(i, j);
		lastTurn=(1+i)*10+j+1;
		setChanged();
		notifyObservers();
		
	}
	
	public void enemyTurn(int i, int j)
	{
		oxo.turn(i, j);
		setChanged();
		notifyObservers();
		
	}
		
/**
Determine who (if anyone) has won
@return CROSS if cross has won, NOUGHT if nought has won, oetherwise BLANK
*/
	public int whoWon()
	{
		return oxo.whoWon();
	}
	
	
	public int getLastTurn()
	{
		return lastTurn;
	}
/**
Start a new game
*/
	public void newGame()
	{
		oxo.newGame();
		setChanged();
		notifyObservers();
	}

/**
 * Is there a winner?
 */
	public boolean winner()
	{
		return (oxo.winner(NoughtsCrosses.CROSS)|| oxo.winner(NoughtsCrosses.NOUGHT));
	}

	
	public boolean getTurn() {
		return turn;
	}
	public void setTurn(boolean b)
	{
		this.turn=b;
		setChanged();
		notifyObservers();
	}
 
}