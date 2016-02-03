import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class inGame {
	//create a mapfor the servers of the players' game status
	private ConcurrentMap<String, Boolean> in= new ConcurrentHashMap<String,Boolean>();
	public inGame()
	{
	}
	public boolean getInGame(String nickname)
	{
		return in.get(nickname);
	}
	public void add(String nickname)
	{
		in.put(nickname,false);
	}
}
