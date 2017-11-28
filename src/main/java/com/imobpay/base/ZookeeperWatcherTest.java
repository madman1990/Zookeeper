package com.imobpay.base;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.test.ClientBase;

import com.imobpay.base.util.EmptyChecker;

public class ZookeeperWatcherTest extends Thread implements Watcher {
    private static ZooKeeper    zk;
    public static final String  PATH = "/app"; // 所要监控的结点
    private static List<String> nodeList;     // 所要监控的结点的子结点列表

    @Override
    public void process(WatchedEvent event) {
        System.out.println("已经触发了" + event.getType() + "事件！");
    }

    public ZookeeperWatcherTest() throws IOException {

        zk = new ZooKeeper("192.168.1.16:2181", ClientBase.CONNECTION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent event) {
            }
        });
//        try {
//            zk.create(PATH, PATH.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        } catch (KeeperException | InterruptedException e) {
//            e.printStackTrace();
//        }
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
                System.out.println("监听");
                Thread.sleep(30000);// sleep一会，减少CUP占用率
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        try {
            // ZooKeeper zk = new ZooKeeper("192.168.1.16:2181", ClientBase.CONNECTION_TIMEOUT, new Watcher() {
            // // 监控所有被触发的事件
            // public void process(WatchedEvent event) {
            // System.out.println("启动的已经触发了" + event.getType() + "事件！");
            // }
            // });
            // System.out.println("准备设置初始化数据");
            //
            // List<String> children = zk.getChildren("/", new Watcher() {
            // @Override
            // public void process(WatchedEvent event) {
            // System.out.println("Watch事件发生");
            // }
            // });
            // System.out.println(zk.getChildren("/", null));
            //
            // for (String string : children) {
            // // rmZkNode(zk, string);
            // }
            // System.out.println(zk.getChildren("/", null));

            // 创建一个目录节点
            // zk.setData("/codeMessage/conf/codeMessage.properties", "f8801895-47f7-475d-9491-d1f9faef783n".getBytes(), -1);
            // zk.create("/madman", "madman".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            ZookeeperWatcherTest zw = new ZookeeperWatcherTest();
            Thread t = new Thread(zw);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void rmZkNode(ZooKeeper zk, String nodeString) {
        try {
            if (EmptyChecker.isNotEmpty(zk.getChildren("/" + nodeString, null))) {
                for (String string : zk.getChildren("/" + nodeString, null)) {
                    rmZkNode(zk, string);
                }
            } else {
                zk.delete("/" + nodeString, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
