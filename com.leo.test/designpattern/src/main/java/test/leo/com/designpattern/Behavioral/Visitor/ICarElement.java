package test.leo.com.designpattern.Behavioral.Visitor;

/**
 * Created by taochao on 16/6/8.
 */
public interface ICarElement {
  void accept(ICarElementVisitor visitor);
}
