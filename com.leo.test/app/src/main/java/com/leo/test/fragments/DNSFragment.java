package com.leo.test.fragments;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.leo.test.R;
import com.leo.test.utils.NetUtils;
import com.netease.hearttouch.hthttpdns.HTHttpDNS;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by taochao on 16/5/26.
 */
public class DNSFragment extends Fragment implements View.OnClickListener {
  private View view;
  private EditText et;
  private TextView tv;
  private Handler handler;
  private EditText et2;

  class MyHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
      if (msg.obj != null) {
        tv.setText(tv.getText() + (String) msg.obj);
      }
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.app_dns_fragment, container, false);
    view.findViewById(R.id.bt1).setOnClickListener(this);
    view.findViewById(R.id.bt2).setOnClickListener(this);
    view.findViewById(R.id.bt3).setOnClickListener(this);
    view.findViewById(R.id.bt4).setOnClickListener(this);
    view.findViewById(R.id.bt5).setOnClickListener(this);
    view.findViewById(R.id.bt6).setOnClickListener(this);
    view.findViewById(R.id.bt7).setOnClickListener(this);
    et = (EditText) view.findViewById(R.id.et1);
    et2 = (EditText) view.findViewById(R.id.et2);
    tv = (TextView) view.findViewById(R.id.tv1);
    handler = new MyHandler();
    return view;
  }

  @Override
  public void onDestroyView() {
    handler.removeCallbacksAndMessages(null);
    super.onDestroyView();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.bt1:
      new Thread(new Runnable() {
        @Override
        public void run() {
          String rst = NetUtils.getIpByHost(et.getText().toString());
          Message msg = new Message();
          msg.obj = rst;
          handler.sendMessage(msg);
        }
      }).start();
      break;
    case R.id.bt2:
      new Thread(new Runnable() {
        @Override
        public void run() {
          String rst = NetUtils.getIpsByHost(et.getText().toString());
          Message msg = new Message();
          msg.obj = rst;
          handler.sendMessage(msg);
        }
      }).start();
      break;

    case R.id.bt3:
      new Thread(new Runnable() {
        @Override
        public void run() {
          List<String> rst = HTHttpDNS.getInstance()
              .getInetAddressListByHostName(et.getText().toString());
          if (rst != null) {
            String s = et.getText().toString() + ":\n";
            for (String i : rst) {
              s += (i + "\n");
            }
            Message msg = new Message();
            msg.obj = s;
            handler.sendMessage(msg);
          }
        }
      }).start();
      break;
    case R.id.bt4:
      ConnectivityManager conManager = (ConnectivityManager) getActivity()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo wifiInfo = conManager
          .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      tv.setText(wifiInfo.toString());
      break;
    case R.id.bt5:
      ConnectivityManager conManager1 = (ConnectivityManager) getActivity()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo mobileInfo = conManager1
          .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
      tv.setText(mobileInfo.toString());
      break;
    case R.id.bt6:
      tv.setText("");
      break;
    case R.id.bt7:
      ScheduledExecutorService scheduledExecutorService = Executors
          .newScheduledThreadPool(1);
      scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
          NetUtils.modifyDnsCache(et.getText().toString(), et2.getText()
              .toString());
        }
      }, 0L, 300, TimeUnit.MILLISECONDS);
      break;
    }
  }
}
