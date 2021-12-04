package com.huldar.nio.reactor.single;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/1 17:47
 */
public class Dispatcher implements Runnable{

  private final SelectionKey selectionKey;
  private static final int READ = 0;
  private static final int SEND = 1;

  private int state;

  public Dispatcher(SelectionKey selectionKey) {
    this.selectionKey = selectionKey;
  }

  @Override
  public void run() {
    SocketChannel sc = (SocketChannel) selectionKey.channel();
    try {
      if (state == READ) {
        read(sc);
      } else if (state == SEND) {
        send(sc);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void send(SocketChannel socketChannel) {

  }

  private void read(SocketChannel socketChannel) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(50);
    socketChannel.read(buffer);
    // buffer 前一个状态是可写, flip转成可读
    buffer.flip();
    String msg = new String(buffer.array(), StandardCharsets.UTF_8);
    System.out.println("接收到客户端的消息: " + msg);
    // 业务处理逻辑
    process();
    state = SEND;

    // 注册写事件
    selectionKey.interestOps(SelectionKey.OP_READ);
  }

  private void process() {

  }
}
