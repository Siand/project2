// Each nickname has a different incomming-message queue.

import java.util.concurrent.*;

public class Scores {

  private ConcurrentMap<String,Integer> scores  = new ConcurrentHashMap<String,Integer>();

  // The following overrides any previously existing nickname, and
  // hence the last client to use this nickname will get the messages
  // for that nickname, and the previously exisiting clients with that
  // nickname won't be able to get messages. Obviously, this is not a
  // good design of a messaging system. So I don't get full marks:

  public void add(String nickname) {
    scores.put(nickname, 0);
  }

  // Returns null if the nickname is not in the table:
  public int getScore(String nickname) {
    return scores.get(nickname);
  }
  
  public void increaseScore(String nickname)
  {
	  if(scores.get(nickname)!=null)
		  scores.put(nickname,(scores.get(nickname)+1));
  }

}
