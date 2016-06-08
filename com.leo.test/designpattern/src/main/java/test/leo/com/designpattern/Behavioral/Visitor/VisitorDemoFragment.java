package test.leo.com.designpattern.Behavioral.Visitor;

import test.leo.com.designpattern.R;
import test.leo.com.designpattern.ViewImageActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by taochao on 16/6/8.
 */
public class VisitorDemoFragment extends Fragment implements
    View.OnClickListener {
  private ImageView imageView;
  private View view;
  private TextView textView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.visitor_demo_fragment, container, false);
    imageView = (ImageView) view.findViewById(R.id.iv1);
    textView = (TextView) view.findViewById(R.id.tv1);
    imageView.setOnClickListener(this);
    view.findViewById(R.id.bt1).setOnClickListener(this);
    view.findViewById(R.id.bt2).setOnClickListener(this);
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.iv1:
      Intent intent = new Intent();
      intent.setClass(getActivity(), ViewImageActivity.class);
      intent.putExtra(ViewImageActivity.IMAGE_NAME, "visitor");
      startActivity(intent);
      break;
    case R.id.bt1:
      MyStringBuffer.getInstance().clear();
      ICarElement car = new Car();
      car.accept(new CarElementDoVisitor());
      textView.setText(MyStringBuffer.getInstance().getBuff());
      break;
    case R.id.bt2:
      MyStringBuffer.getInstance().clear();
      ICarElement car1 = new Car();
      car1.accept(new CarElementPrintVisitor());
      textView.setText(MyStringBuffer.getInstance().getBuff());
      break;
    }
  }
}
