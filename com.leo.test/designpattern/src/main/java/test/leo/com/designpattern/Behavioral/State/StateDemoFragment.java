package test.leo.com.designpattern.Behavioral.State;

import test.leo.com.designpattern.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by leo on 16-2-1.
 */
public class StateDemoFragment extends Fragment implements View.OnClickListener {

  private State mState;
  private ImageView imageView;
  private View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.state_demo_fragment, container, false);
    imageView = (ImageView) view.findViewById(R.id.iv1);
    view.findViewById(R.id.bt1).setOnClickListener(this);
    mState = new ConcreteState1();
    return view;
  }

  public void setmState(State mState) {
    this.mState = mState;
  }

  private void showTrafficLight() {
    if (mState == null || imageView == null) {
      return;
    }
    mState.showTrafficLight(StateDemoFragment.this, imageView);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.bt1:
      showTrafficLight();
      break;
    }
  }
}
