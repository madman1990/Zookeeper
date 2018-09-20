package com.imobpay.base.configuration;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Register {
    //三台服务器主机名+端口分别用","隔开;
    private static final String connectString = "192.168.1.16:2181";
    //超时设置
    private static final int sessionTimeout = 2000;
    //父节点
    private static final String parentNode = "/servers";

    private ZooKeeper zk = null;

    /**
     * 获取zk连接
     *
     * @throws IOException
     */
    public void getConnect() throws IOException {
        /**
         * Watcher 监听器
         * 每监听一次之后都需要重新注册
         */
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    //重新监听
                    zk.getChildren("/", true);
                    System.out.println("[server : " + event.getType() + ":" + event.getPath() + "]");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 向ZooKeeper集群注册服务器信息
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    @SuppressWarnings("unused")
    private void registerServer(String hostName) throws KeeperException, InterruptedException {
        /**
         *  param1 : 要创建的节点路径
         *  param2 : 节点的数据,必须是byte类型.
         *  param3 : 节点的权限(枚举类型)
         *  param4 : 创建的类别(枚举类型)
         *
         */
        String create = zk.create(parentNode + "/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(hostName + " register ZooKeeper , Create Node Info :" + create);
    }

    private void handleBussiness(String hostName) throws InterruptedException {
        System.out.println(hostName + " start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Register server = new Register();
        //获取ZK连接
        server.getConnect();
        //利用ZK连接注册服务器信息
        server.registerServer("ccc");
        //业务功能
        server.handleBussiness("aaaa");
    }
}
