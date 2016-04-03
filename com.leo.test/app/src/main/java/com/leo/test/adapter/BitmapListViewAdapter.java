package com.leo.test.adapter;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leo.test.R;
import com.leo.test.log.Logger;
import com.leo.test.log.Tags;

/**
 * Created by leo on 16-4-2.
 */
public class BitmapListViewAdapter extends BaseAdapter {

  private final List<String> mUris;
  private final Context mContext;

  public BitmapListViewAdapter(Context context, List<String> uris) {
    mContext = context;
    mUris = uris;
  }

  class ViewHolder {
    SimpleDraweeView iv_bitmap;
  }

  @Override
  public int getCount() {
    return mUris.size();
  }

  @Override
  public Object getItem(int position) {
    return mUris.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      viewHolder = new ViewHolder();
      convertView = LayoutInflater.from(mContext).inflate(
          R.layout.bitmap_listview_item, parent, false);
      viewHolder.iv_bitmap = (SimpleDraweeView) convertView
          .findViewById(R.id.iv_bitmap);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    Logger.debug(Tags.FRESCO, "bitmap uri=%s", mUris.get(position));
    viewHolder.iv_bitmap.setImageURI(Uri.parse(mUris.get(position)));
    return convertView;
  }
}
