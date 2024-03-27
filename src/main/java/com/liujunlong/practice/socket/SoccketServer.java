package com.liujunlong.practice.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujunlong
 * @since 2020/10/28 14:49
 */
public class SoccketServer extends  Thread{

    ServerSocket server = null;
    Socket socket =null;


    AtomicInteger count = new AtomicInteger(0);

    public SoccketServer(int port) {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("ServerSocket已启动，等待客户端连接...");
            socket = server.accept();
            //todo  启动发消息线程
            //接受客户端消息
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                System.out.println("收到客户端信息："
                        + new String(buf, 0, len, "UTF-8"));
                out.write("{ msg: success}".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class SendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            Scanner scanner = null;
            OutputStream out = null;
            try {
                if (socket != null) {
                    scanner = new Scanner(System.in);
                    out = socket.getOutputStream();
                    String in = "";
                    do {
                        in = scanner.next();
                        out.write(("" + in).getBytes("UTF-8"));
                        out.flush();// 清空缓存区的内容
                    } while (!in.equals("q"));
                    scanner.close();
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        SoccketServer server = new SoccketServer(6666);
        server.start();

    }
}
