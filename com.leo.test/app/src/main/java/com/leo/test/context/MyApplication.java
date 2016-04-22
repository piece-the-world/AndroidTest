package com.leo.test.context;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.leo.test.R;
import com.leo.test.widget.FloatingEntry;

/**
 * Created by leo on 16-4-13.
 */
public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    this.registerActivityLifecycleCallbacks(new MyActivityLifeCycle(this));
  }

  void onResume(final Activity activity) {
    showFloatingEntry(activity);
  }

  void onPause(final Activity activity) {
    hideFloatingEntry(activity);
  }

  FloatingEntry floatingEntry = null;

  private void showFloatingEntry(final Activity activity) {
    floatingEntry = new FloatingEntry(activity);
    floatingEntry.setImageResource(R.drawable.ball_red);
    WindowManager windowManager = (WindowManager) activity
        .getSystemService(Context.WINDOW_SERVICE);
    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
    params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
    // params.gravity = Gravity.LEFT | Gravity.TOP;
    params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
    params.x = 200;
    params.y = 200;
    params.format = PixelFormat.TRANSPARENT;
    params.width = WindowManager.LayoutParams.WRAP_CONTENT;
    params.height = WindowManager.LayoutParams.WRAP_CONTENT;
    windowManager.addView(floatingEntry, params);
  }

  private void hideFloatingEntry(final Activity activity) {
    if (floatingEntry != null && floatingEntry.getContext() == activity) {
      WindowManager windowManager = (WindowManager) activity
          .getSystemService(Context.WINDOW_SERVICE);
      windowManager.removeView(floatingEntry);
      floatingEntry = null;
    }
  }

}
