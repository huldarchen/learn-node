package com.huldar.nio;

import java.util.Arrays;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/11/23 15:35
 */
public class Client {

  public static void main(String[] args) {
    byte[] buf = new byte[4];
    int size = 1212324243;
    System.out.println(Byte.TYPE);
    System.out.println(Integer.toBinaryString(size >>> 24));
    buf[0] = (byte)((size >>> 24) & 0xFF);
    System.out.println(Integer.toBinaryString(size >>> 16));
    buf[1] = (byte)((size >>> 16) & 0xFF);
    System.out.println(Integer.toBinaryString(size >>> 8));
    buf[2] = (byte)((size >>>  8) & 0xFF);
    System.out.println(Integer.toBinaryString(size));
    buf[3] = (byte)((size >>>  0) & 0xFF);
    System.out.println(Arrays.toString(buf));
  }
}
