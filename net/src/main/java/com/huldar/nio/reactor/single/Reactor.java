package com.huldar.nio.reactor.single;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/1 00:46
 * TODO 这里为什么要实现runnable接口呢??
 */
public class Reactor implements Runnable{
  Selector selector;
  ServerSocketChannel ssc;


  @Override
  public void run() {

  }
}
