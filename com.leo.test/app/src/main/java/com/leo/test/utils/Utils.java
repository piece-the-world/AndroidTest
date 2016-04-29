package com.leo.test.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by leo on 16-4-27.
 */
public class Utils {

  /**
   * 拿到指定包名app的签名
   */
  public static android.content.pm.Signature[] getAppSignature(Context context,
      String packageName) {
    PackageInfo packageInfo = null;
    try {
      packageInfo = context.getPackageManager().getPackageInfo(packageName,
          PackageManager.GET_SIGNATURES);
    } catch (PackageManager.NameNotFoundException e) {
      return null;
    }
    return packageInfo.signatures;
  }

  /**
   * 使用MessageDigest计算ｍｄ５
   */
  public static byte[] md5(byte[] data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(data);
      return digest.digest();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 16进制化
   */
  public static String byteToHex(byte[] bytes) {
    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
        'b', 'c', 'd', 'e', 'f' };
    int j = bytes.length;
    char str[] = new char[j * 2];
    int k = 0;
    for (int i = 0; i < j; i++) {
      byte byte0 = bytes[i];
      str[k++] = hexDigits[byte0 >>> 4 & 0xf];
      str[k++] = hexDigits[byte0 & 0xf];
    }
    return new String(str);
  }
}
