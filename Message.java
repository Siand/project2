public class Message {

  private final String sender;
  private final String challenged;

  Message(String sender, String challenged) {
    this.sender = sender;
    this.challenged = challenged;
  }

  public String getSender() {
    return sender;
  }

  public String getChallenged() {
    return challenged;
  }

  public String toString() {
    return sender;
  }
}
