package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public interface ICarElementVisitor {
  void visit(Wheel wheel);

  void visit(Engine engine);

  void visit(Body body);

  void visit(Car car);
}
