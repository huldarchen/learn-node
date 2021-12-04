package com.huldar.nio.reactor.single;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/1 17:38
 */
public class Acceptor implements Runnable{
  private final Selector selector;
  private final SelectionKey selectionKey;

  public Acceptor(Selector selector, SelectionKey selectionKey) {
    this.selector = selector;
    this.selectionKey = selectionKey;
  }

  @Override
  public void run() {
    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
    try {
      SocketChannel socketChannel = ssc.accept();
      socketChannel.configureBlocking(false);
      SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
      key.attach(new Dispatcher(key));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
