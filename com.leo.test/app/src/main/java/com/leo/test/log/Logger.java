package com.leo.test.log;

import android.util.Log;

/**
 * Created by leo on 16-4-3.
 */
public class Logger {

  private static boolean debugSwitchOn = true;
  private static String message;

  public static void debug(String tag, String msg, Object... args) {
    if (debugSwitchOn) {
      message = String.format(msg, args);
      message += callMethodAndLine();
      Log.d(tag, message);
    }
  }

  public static void debug(String tag, String msg, Throwable throwable,
      Object... args) {
    if (debugSwitchOn) {
      message = String.format(msg, args);
      message += callMethodAndLine();
      Log.d(tag, message, throwable);
    }
  }

  private static String callMethodAndLine() {
    String rst = "at ";
    StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
    rst += thisMethodStack.getClassName() + ",";
    rst += thisMethodStack.getMethodName();
    rst += "(" + thisMethodStack.getFileName();
    rst += ":" + thisMethodStack.getLineNumber() + ") ";
    return rst;
  }

  public static void setDebugSwitchOn(boolean debuggable) {
    debugSwitchOn = debuggable;
  }
}
