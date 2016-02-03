// Each nickname has a different incomming-message queue.

import java.util.concurrent.*;

public class Scores {

  private static ConcurrentMap<String,Integer> scores  = new ConcurrentHashMap<String,Integer>();

  public void add(String nickname) {
    scores.put(nickname, 0);
  }

  // Returns null if the nickname is not in the table:
  public static int getScore(String nickname) {
    return scores.get(nickname);
  }
  
  public void increaseScore(String nickname)
  {
	  if(scores.get(nickname)!=null)
		  scores.put(nickname,(scores.get(nickname)+1));
  }

}
