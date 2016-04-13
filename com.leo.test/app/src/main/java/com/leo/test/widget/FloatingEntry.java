package com.leo.test.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.leo.test.log.Logger;

/**
 * Created by taochao on 16-4-12.
 */
public class FloatingEntry extends ImageView {

  private static final String TAG = "floatingimageview";
  private String uri;
  private WindowManager windowManager;
  private WindowManager.LayoutParams params;
  private float x;
  private float y;
  private float mTouchStartX;
  private float mTouchStartY;

  public FloatingEntry(Context context) {
    super(context);
    init();
  }

  public FloatingEntry(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FloatingEntry(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    windowManager = (WindowManager) getContext().getSystemService(
        Context.WINDOW_SERVICE);
    params = new WindowManager.LayoutParams();
    params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    params.gravity = Gravity.LEFT | Gravity.TOP;
    params.width = WindowManager.LayoutParams.WRAP_CONTENT;
    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
  }

  int statusBarHeight = 0;

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    Logger.debug(
        TAG,
        "action=" + event.getAction() + ";x=" + event.getX() + ";y="
            + event.getY() + ";rawX=" + event.getRawX() + ";rawY="
            + event.getRawY());
    if (statusBarHeight <= 0) {
      Rect rect = new Rect();
      getWindowVisibleDisplayFrame(rect);
      statusBarHeight = rect.top;
    }
    Logger.debug(TAG, "statusbarheitht=%d", statusBarHeight);
    x = event.getRawX();
    y = event.getRawY() - statusBarHeight;
    switch (event.getAction()) {
    case MotionEvent.ACTION_DOWN:
      mTouchStartX = event.getX();
      mTouchStartY = event.getY();
      break;
    case MotionEvent.ACTION_MOVE:
      updateViewPosition();
      break;
    case MotionEvent.ACTION_CANCEL:
    case MotionEvent.ACTION_UP:
      updateViewPosition();
      return false;
    }
    return true;
  }

  private void updateViewPosition() {
    params.x = (int) (x - mTouchStartX);
    params.y = (int) (y - mTouchStartY);
    Logger.debug(TAG, "params.x=" + params.x + ";params.y=" + params.y);
    windowManager.updateViewLayout(this, params);
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

}
