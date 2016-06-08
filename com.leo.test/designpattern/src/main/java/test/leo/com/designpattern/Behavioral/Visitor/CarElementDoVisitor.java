package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public class CarElementDoVisitor implements ICarElementVisitor {
  public void visit(Wheel wheel) {
    MyStringBuffer.getInstance().buff(
        "Kicking my " + wheel.getName() + " wheel\n");
  }

  public void visit(Engine engine) {
    MyStringBuffer.getInstance().buff("Starting my engine\n");
  }

  public void visit(Body body) {
    MyStringBuffer.getInstance().buff("Moving my body\n");
  }

  public void visit(Car car) {
    MyStringBuffer.getInstance().buff("Starting my car\n");
  }
}
