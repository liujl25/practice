package com.liujunlong.practice.basis.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient {

        public static void main(String[] args) throws Exception{
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 5555);

            if(!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("客户端正在连接中，请耐心等待");
                }
            }

            ByteBuffer byteBuffer = ByteBuffer.wrap("mikechen的互联网架构".getBytes(StandardCharsets.UTF_8));
            socketChannel.write(byteBuffer);
            socketChannel.write(ByteBuffer.wrap("第二次-mikechen的互联网架构".getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();
        }

}
