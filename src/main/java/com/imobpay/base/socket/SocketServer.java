package com.imobpay.base.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    ThreadPoolExecutor threadPools = null;

    class ThreadListen implements Runnable {
        public void run() {
            ServerSocket server = null;
            try {
                // 创建端口的监听（服务端）
                server = new ServerSocket(5209);
                // 创建线程池
                threadPools = new ThreadPoolExecutor(1, Runtime
                        .getRuntime().availableProcessors() * 1, 100,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue(100),
                        new ThreadPoolExecutor.DiscardOldestPolicy());
                threadPools.allowCoreThreadTimeOut(true);
                // 用一个死循环来接收请求(来自客户端的请求)
                while (true) {
                    // 服务端监听等待客户端的连接，如果没有客户端来连接，这里就一直处于挂起状态，知道有客户端连接
                    Socket socket = server.accept();
                    // 如果有客户端来连接，处理线程
                    String pk = System.currentTimeMillis() + "";
                    threadPools.execute(new ThreadAcceptWork(socket, pk));
                }
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                try {
                    if (server != null)
                        server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @ClassName: ThreadAcceptWork
     * @Package com.imobpay.gcs.cifservice.shorttcp
     * @Description: 内部类,接受线程处理
     * @author Lance.Wu
     * @date Jul 5, 2014 3:14:23 PM
     * @modifyDate Jul 5, 2014 3:14:23 PM
     *
     *             Copyright: Copyright (c) 2014 Company:www.imobpay.com.cn
     */
    class ThreadAcceptWork implements Runnable {
        Socket sock = null;
        String pk;

        public ThreadAcceptWork(Socket socket, String pk) {
            this.sock = socket;
            this.pk = pk;
        }

        public void run() {
            try {
                Thread.currentThread().setName(pk);
                sock.setSoTimeout(60000);
                sock.setSoLinger(false, 0);
                sock.setTcpNoDelay(true);
                // 接收客户端的数据
                procHttp(sock);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @ClassName: ThreadAccept
     * @Package com.imobpay.gcs.cifservice.shorttcp
     * @Description: 内部类,接受线程处理(没有设置socket参数，以及没有错异常的处理)
     * @author Lance.Wu
     * @date Jul 5, 2014 3:15:36 PM
     * @modifyDate Jul 5, 2014 3:15:36 PM
     *
     *             Copyright: Copyright (c) 2014 Company:www.imobpay.com.cn
     */
    class ThreadAccept implements Runnable {
        private Socket sock;

        public ThreadAccept(Socket socket) {
            this.sock = socket;
        }

        public void run() {
            procHttp(sock);
        }
    }

    /**
     * procHttp(处理socket的数据，转发服务以及回复消息)
     *
     * @Title: procHttp
     * @Description: 处理socket的数据，转发服务以及回复消息
     * @Date Jul 5, 2014 3:18:00 PM
     * @modifyDate Jul 5, 2014 3:18:00 PM
     * @param sock
     *            网络socket对象
     */
    public void procHttp(Socket sock) {
        try {
            InputStream inputStream = sock.getInputStream();
            OutputStream outputStream = sock.getOutputStream();

            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            System.out.println("接收到的数据：" + sb);
            outputStream.write("0000".getBytes());
            outputStream.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (sock != null)
                    sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SocketServer s = new SocketServer();
        s.init();
    }

    public void init() {
        System.out.println("代发出款线程启动参数初始化");
        new Thread(new ThreadListen(), "AgentPayControlThread").start();
    }

    public void socket() throws Exception {
        // 监听指定的端口
        int port = 5209;
        ServerSocket server = new ServerSocket(port);

        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len, "UTF-8"));
        }
        System.out.println("get message from client: " + sb);
        outputStream.write("我是服务端  我返回给你的数据  你不要得意".getBytes("UTF-8"));
        outputStream.close();
        inputStream.close();
        socket.close();
        server.close();
    }
}