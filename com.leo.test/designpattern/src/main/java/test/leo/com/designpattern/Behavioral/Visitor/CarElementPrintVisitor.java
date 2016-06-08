package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public class CarElementPrintVisitor implements ICarElementVisitor {
  public void visit(Wheel wheel) {
    MyStringBuffer.getInstance().buff(
        "Visiting " + wheel.getName() + " wheel\n");
  }

  public void visit(Engine engine) {
    MyStringBuffer.getInstance().buff("Visiting engine\n");
  }

  public void visit(Body body) {
    MyStringBuffer.getInstance().buff("Visiting body\n");
  }

  public void visit(Car car) {
    MyStringBuffer.getInstance().buff("Visiting car\n");
  }
}
