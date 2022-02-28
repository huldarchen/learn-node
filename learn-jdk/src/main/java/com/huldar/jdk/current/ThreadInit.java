package com.huldar.jdk.current;

import java.util.stream.IntStream;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2022/2/28 17:14
 */
public class ThreadInit {

  private static final String PREFIX = "alex-";

  public static void main(String[] args) {
    IntStream.range(0, 5).mapToObj(ThreadInit::createThread).forEach(Thread::start);
  }

  private static Thread createThread(int intName) {
    return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
  }
}
