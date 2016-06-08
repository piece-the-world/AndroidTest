package com.leo.test.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
      ip += (inetAddresses.getHostAddress()+"\n");
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return ip;
  }
}
