package com.leo.test.fragments;

import com.leo.test.R;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by taochao on 16/5/26.
 */
public class ClassLoaderFragment extends Fragment implements
    View.OnClickListener {
  private View view;
  private TextView tv;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.classloader_fragment, container, false);
    tv = (TextView) view.findViewById(R.id.tv1);
    view.findViewById(R.id.bt1).setOnClickListener(this);
    return view;
  }

  private String printClassLoaderStructure() {
    StringBuilder stringBuilder = new StringBuilder();
    ClassLoader classloader = this.getClass().getClassLoader();
    while (classloader != null) {
      stringBuilder.append(classloader.toString()).append("\n");
      classloader = classloader.getParent();
    }
    return stringBuilder.toString();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.bt1:
      tv.setText(printClassLoaderStructure());
      break;
    }
  }
}
