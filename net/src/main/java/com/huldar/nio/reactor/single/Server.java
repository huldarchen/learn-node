package com.huldar.nio.reactor.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/1 00:43
 */
public class Server implements Runnable{

  private final Selector selector;

  public Server() throws IOException {
    ServerSocketChannel ssc = ServerSocketChannel.open();
    ssc.configureBlocking(false);
    ssc.bind(new InetSocketAddress(8888), 1024);
    selector = Selector.open();
    // 关注连接事件
    SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
    selectionKey.attach(new Acceptor(selector, selectionKey));
  }

  @Override
  public void run() {
    // 监听连接事件,有连接了进行分发
    while (!Thread.interrupted()) {
      try {
        int acceptCount = selector.select();
        System.out.println(Thread.currentThread().getName() + "监听就绪事件个数" + acceptCount);

        if (acceptCount == 0) {
          continue;
        }

        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
          SelectionKey key = iterator.next();
          Runnable attachment = (Runnable) key.attachment();
          attachment.run();
          iterator.remove();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
