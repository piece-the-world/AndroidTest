package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
class Car implements ICarElement {
  ICarElement[] elements;

  public Car() {
    this.elements = new ICarElement[] { new Wheel("front left"),
        new Wheel("front right"), new Wheel("back left"),
        new Wheel("back right"), new Body(), new Engine() };
  }

  public void accept(ICarElementVisitor visitor) {
    for (ICarElement elem : elements) {
      elem.accept(visitor);
    }
    visitor.visit(this);
  }
}