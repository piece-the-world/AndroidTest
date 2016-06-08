package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public class MyStringBuffer {
  private static MyStringBuffer ourInstance = new MyStringBuffer();
  private StringBuilder buff = new StringBuilder();

  public static MyStringBuffer getInstance() {
    return ourInstance;
  }

  private MyStringBuffer() {
  }

  public void buff(String s) {
    buff.append(s);
  }

  public String getBuff() {
    return buff.toString();
  }

  public void clear() {
    buff = new StringBuilder();
  }
}
