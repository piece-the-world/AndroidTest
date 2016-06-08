package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public class Body implements ICarElement {
  public void accept(ICarElementVisitor visitor) {
    visitor.visit(this);
  }
}
