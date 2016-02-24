package test.leo.com.designpattern.Structual.Decorator;

import test.leo.com.designpattern.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by leo on 16-2-24.
 */
public class DecoratorDemoFragment extends Fragment implements
    View.OnClickListener {

  private View view;
  private Coffee coffee;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.decorator_demo_fragment, container, false);
    view.findViewById(R.id.bt1).setOnClickListener(this);
    view.findViewById(R.id.bt2).setOnClickListener(this);
    view.findViewById(R.id.bt3).setOnClickListener(this);
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.bt1:
      coffee = new ConcreteCoffee1();
      refreshCoffeeInfo();
      break;
    case R.id.bt2:
      if (coffee != null) {
        coffee = new MilkDecorator(coffee);
        refreshCoffeeInfo();
      }
      break;
    case R.id.bt3:
      if (coffee != null) {
        coffee = new SprinkleDecorator(coffee);
        refreshCoffeeInfo();
      }
      break;
    }
  }

  private void refreshCoffeeInfo() {
    if (coffee != null) {
      ((TextView) view.findViewById(R.id.tv1)).setText("Cost: $"
          + String.valueOf(coffee.getCost()));
      ((TextView) view.findViewById(R.id.tv2)).setText("Ingredients: "
          + String.valueOf(coffee.getIngredients()));
    }
  }
}
