package com.leo.test;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.test.adapter.BitmapListViewAdapter;
import com.leo.test.widget.LoadMoreListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements
    LoadMoreListView.LoadMoreListener {

  private View view;
  private LoadMoreListView listView;
  private ArrayList<String> uris;
  private BitmapListViewAdapter listAdapter;

  public MainActivityFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_main, container, false);
    listView = (LoadMoreListView) view.findViewById(R.id.lmlv_list);
    mockData();
    listAdapter = new BitmapListViewAdapter(view.getContext(), uris);
    listView.setAdapter(listAdapter);
    listView.setLoadMoreListener(this);
    return view;
  }

  private void mockData() {
    uris = new ArrayList<String>();
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
  }

  private void mockAddData() {
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
    uris.add("res://com.leo.test/" + R.drawable.w2);
    uris.add("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
  }

  @Override
  public void loadMore() {
    mockAddData();
    listAdapter.notifyDataSetChanged();
  }
}
