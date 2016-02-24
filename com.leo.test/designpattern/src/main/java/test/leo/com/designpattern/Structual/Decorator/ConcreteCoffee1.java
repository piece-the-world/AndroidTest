package test.leo.com.designpattern.Structual.Decorator;

/**
 * Created by leo on 16-2-24.
 */
public class ConcreteCoffee1 implements Coffee {
  @Override
  public double getCost() {
    return 1;
  }

  @Override
  public String getIngredients() {
    return "coffee";
  }
}
