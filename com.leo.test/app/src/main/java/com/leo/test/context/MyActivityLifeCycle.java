package com.leo.test.context;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by leo on 16-4-13.
 */
public class MyActivityLifeCycle implements
    Application.ActivityLifecycleCallbacks {

  private final MyApplication mApp;

  public MyActivityLifeCycle(Context context) {
    mApp = (MyApplication) context;
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

  }

  @Override
  public void onActivityStarted(Activity activity) {

  }

  @Override
  public void onActivityResumed(Activity activity) {
    if (activity.getClass().getSimpleName().contains("Main")) {
      mApp.onResume(activity);
    }
  }

  @Override
  public void onActivityPaused(Activity activity) {
    if (activity.getClass().getSimpleName().contains("Main")) {
      mApp.onPause(activity);
    }
  }

  @Override
  public void onActivityStopped(Activity activity) {

  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

  }

  @Override
  public void onActivityDestroyed(Activity activity) {

  }
}
