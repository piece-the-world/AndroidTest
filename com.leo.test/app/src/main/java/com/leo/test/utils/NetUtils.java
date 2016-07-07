package com.leo.test.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.util.Log;

/**
 * Created by taochao on 16/5/26.
 */
public class NetUtils {
  public static String getIpsByHost(String host) {
    String ips = host + ":\n";
    try {
      InetAddress[] inetAddresses = InetAddress.getAllByName(host);
      for (InetAddress i : inetAddresses) {
        ips += (i.getHostAddress() + "\n");
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ips;
  }

  public static String getIpByHost(String host) {
    String ip = host + ":\n";
    try {
      InetAddress inetAddresses = InetAddress.getByName(host);
      ip += (inetAddresses.getHostAddress() + "\n");
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ip;
  }

  public static void modifyDnsCache(String domainName, String ip) {
    try {
      Class inetAddressClass = InetAddress.class;
      Field field = inetAddressClass.getDeclaredField("addressCache");
      field.setAccessible(true);
      Object object = field.get(inetAddressClass);
      Class cacheClass = object.getClass();
      Method clearMethod = cacheClass.getDeclaredMethod("clear", null);
      clearMethod.setAccessible(true);
      clearMethod.invoke(object, null);

      // Field ttl = cacheClass.getDeclaredField("TTL_NANOS");
      // ttl.setAccessible(true);
      // // Field modifiersField = Field.class.getDeclaredField("modifiers");
      // // modifiersField.setAccessible(true);
      // // modifiersField.setInt(ttl, ttl.getModifiers() & ~Modifier.FINAL);
      // ttl.set(cacheClass, 3000 * 1000000000L);
      // Object temp = ttl.get(cacheClass);

      Method putMethod = cacheClass.getDeclaredMethod("put", new Class[] {
          String.class, int.class, InetAddress[].class });
      putMethod.setAccessible(true);
      String[] ipStr = ip.split("\\.");
      byte[] ipBuf = new byte[4];
      for (int i = 0; i < 4; i++) {
        ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xff);
      }
      InetAddress[] addresses = new InetAddress[] { InetAddress
          .getByAddress(ipBuf) };
      putMethod.invoke(object, domainName, 0, addresses);
      Log.d("test", "domainName=" + domainName);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
