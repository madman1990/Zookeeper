package com.imobpay.base;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.test.ClientBase;

public class ZookeeperWatcherTest extends Thread implements Watcher {
    private static ZooKeeper    zk;
    public static final String  PATH = "/testRootPath"; // 所要监控的结点
    private static List<String> nodeList;              // 所要监控的结点的子结点列表

    @Override
    public void process(WatchedEvent event) {
        System.out.println("已经触发了" + event.getType() + "事件！");
    }

    public ZookeeperWatcherTest() throws IOException {

        zk = new ZooKeeper("192.168.1.22:2181", ClientBase.CONNECTION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent event) {
            }
        });
    }

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        Watcher wc = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("已经触发了:" + event.getType() + "事件！");
                System.out.println("路劲:" + event.getPath());
            }
        };
        while (true) {
            try {
                zk.exists(PATH, wc);// 所要监控的主结点
                nodeList = zk.getChildren(PATH, wc);
                for (String nodeName : nodeList) {
                    zk.exists(PATH + "/" + nodeName, wc);
                }
                Thread.sleep(3000);// sleep一会，减少CUP占用率
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        try {
            ZookeeperWatcherTest zw = new ZookeeperWatcherTest();
            Thread t = new Thread(zw);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
