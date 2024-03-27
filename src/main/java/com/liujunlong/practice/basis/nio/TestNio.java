package com.liujunlong.practice.basis.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TestNio {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open(); // 获取一个选择器实例

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090)); // 打开 SocketChannel 并连接到本机 9090 端口
        socketChannel.configureBlocking(false); // 配置通道为非阻塞模式

        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 将套接字通过到注册到选择器，关注 read 和 write 事件

        while (selector.select() > 0){ // 轮询，且返回时有就绪事件
            Set<SelectionKey> keys = selector.selectedKeys(); // 获取就绪事件集合

        }



    }
}
