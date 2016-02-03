// Each nickname has a different turns queue.

import java.awt.geom.Point2D;
import java.util.concurrent.*;

public class Turns {

  private ConcurrentMap<String,Integer> turns  = new ConcurrentHashMap<String,Integer>();

  public void add(String nickname,int x) {
    turns.put(nickname,x);
  }

  // Returns null if the nickname is not in the table:
  public int getTurns(String nickname) {
    return turns.get(nickname);
  }

}
