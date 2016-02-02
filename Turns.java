// Each nickname has a different turns queue.

import java.awt.geom.Point2D;
import java.util.concurrent.*;

public class Turns {

  private ConcurrentMap<String,Point2D.Double> turns  = new ConcurrentHashMap<String,Point2D.Double>();

  // The following overrides any previously existing nickname, and
  // hence the last client to use this nickname will get the messages
  // for that nickname, and the previously exisiting clients with that
  // nickname won't be able to get messages. Obviously, this is not a
  // good design of a messaging system. So I don't get full marks:

  public void add(String nickname,int x, int y) {
    turns.put(nickname, new Point2D.Double(x,y));
  }

  // Returns null if the nickname is not in the table:
  public Point2D.Double getTurns(String nickname) {
    return turns.get(nickname);
  }

}
