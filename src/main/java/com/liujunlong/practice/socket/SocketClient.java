package com.liujunlong.practice.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author liujunlong
 * @since 2020/10/28 14:49
 */
public class SocketClient extends Thread{

    Socket socket = null;

    public SocketClient(String serverHost, int port) {
        try {
            socket = new Socket(serverHost, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            InputStream inputStream = socket.getInputStream();
            //客户端一连接就可以写数据给服务器了
            new SendMessThread().start();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                System.out.println( "收到服务器消息："+new String(buf, 0, len,"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 往Socket里面写数据，需要新开一个线程
     */
    class SendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //写操作
            OutputStream os= null;
            try {
                os= socket.getOutputStream();
                do {
                    os.write(("hello world").getBytes());
                    os.flush();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        //需要服务器的正确的IP地址和端口号
        SocketClient client = new SocketClient("127.0.0.1", 6666);
        client.start();
    }
}
