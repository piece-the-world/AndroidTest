package test.leo.com.multiprocesslibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by leo on 16-2-26.
 */
public class MyService extends Service {
  public static final String TAG = "MultiProcess";

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.d(TAG, "MyService:onBind()");
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "MyService:onCreate()");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);
    Log.d(TAG, "MyService:onStartCommand()");

    return START_STICKY;
  }
}
