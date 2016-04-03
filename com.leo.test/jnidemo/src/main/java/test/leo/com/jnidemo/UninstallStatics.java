package test.leo.com.jnidemo;

/**
 * Created by leo on 16-3-22.
 */
public class UninstallStatics {

  static {
    System.loadLibrary("leo-jni");
  }

  public static native void watchUninstallEvent();

}
