package test.leo.com.designpattern.Behavioral.State;

import test.leo.com.designpattern.R;

import android.widget.ImageView;

/**
 * Created by leo on 16-2-1.
 */
public class ConcreteState3 implements State {
  @Override
  public void showTrafficLight(StateDemoFragment stateDemoFragment,
      ImageView imageView) {
    if (stateDemoFragment == null || imageView == null) {
      return;
    }
    imageView.setBackgroundColor(stateDemoFragment.getResources().getColor(
        R.color.green));
    stateDemoFragment.setmState(new ConcreteState1());
  }
}
