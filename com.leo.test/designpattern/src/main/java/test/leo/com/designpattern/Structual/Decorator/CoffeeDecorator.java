package test.leo.com.designpattern.Structual.Decorator;

/**
 * Created by leo on 16-2-24.
 */
public abstract class CoffeeDecorator implements Coffee {
  protected final Coffee decoratedCoffee;

  public CoffeeDecorator(Coffee decoratedCoffee) {
    this.decoratedCoffee = decoratedCoffee;
  }

  @Override
  public double getCost() {
    return decoratedCoffee.getCost();
  }

  @Override
  public String getIngredients() {
    return decoratedCoffee.getIngredients();
  }
}
