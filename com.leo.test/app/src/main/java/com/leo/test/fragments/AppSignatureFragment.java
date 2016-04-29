package com.leo.test.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.test.R;
import com.leo.test.utils.Utils;

/**
 * Created by leo on 16-4-27.
 */
public class AppSignatureFragment extends Fragment {
  private View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.app_signature_fragment, container, false);
    ((TextView) (view.findViewById(R.id.tv1))).setText("com.netease.caipiao:"
        + getSignature("com.netease.caipiao"));
    ((TextView) (view.findViewById(R.id.tv2)))
        .setText("com.netease.caipiaolede:"
            + getSignature("com.netease.caipiaolede"));
    ((TextView) (view.findViewById(R.id.tv3)))
        .setText("com.netease.caipiaohyg:"
            + getSignature("com.netease.caipiaohyg"));
    return view;
  }



  private String getSignature(String packageName) {
    String rst;
    try {
      rst = Utils.byteToHex(Utils.md5(Utils.getAppSignature(getContext(),
          packageName)[0].toByteArray()));
    } catch (Exception e) {
      return null;
    }
    return rst;
  }

}
