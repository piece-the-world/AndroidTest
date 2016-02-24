package test.leo.com.designpattern.Structual.Decorator;

/**
 * Created by leo on 16-2-24.
 */
public class SprinkleDecorator extends CoffeeDecorator {
  public SprinkleDecorator(Coffee decoratedCoffee) {
    super(decoratedCoffee);
  }

  @Override
  public double getCost() {
    return super.getCost() + 1;
  }

  @Override
  public String getIngredients() {
    return super.getIngredients() + ",sprinkle";
  }
}
