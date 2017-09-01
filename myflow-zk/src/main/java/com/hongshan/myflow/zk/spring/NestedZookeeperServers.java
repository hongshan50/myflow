package com.hongshan.myflow.zk.spring;

import org.apache.curator.test.TestingServer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Package com.jd.baina.item.zk
 * @Description: 嵌入式ZK
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 14:24
 * @Version V1.0
 */
public final class NestedZookeeperServers {

    private static NestedZookeeperServers instance = new NestedZookeeperServers();

    private static ConcurrentMap<Integer, TestingServer> nestedServers = new ConcurrentHashMap<Integer, TestingServer>();

    public static NestedZookeeperServers getInstance() {
        return instance;
    }

    /**
     * 启动内嵌的Zookeeper服务.
     *
     * @param port 端口号
     *
     * <p>
     * 如果该端口号的Zookeeper服务未启动, 则启动服务.
     * 如果该端口号的Zookeeper服务已启动, 则不做任何操作.
     * </p>
     */
    public synchronized void startServerIfNotStarted(final int port, final String dataDir) {
        if (!nestedServers.containsKey(port)) {
            TestingServer testingServer = null;
            try {
                testingServer = new TestingServer(port, new File(dataDir));
                // CHECKSTYLE:OFF
            } catch (final Exception ex) {
                // CHECKSTYLE:ON
            }
            nestedServers.putIfAbsent(port, testingServer);
        }
    }

    /**
     * 关闭内嵌的Zookeeper服务.
     *
     * @param port 端口号
     */
    public void closeServer(final int port) {
        TestingServer nestedServer = nestedServers.get(port);
        if (null == nestedServer) {
            return;
        }
        try {
            nestedServer.close();
            nestedServers.remove(port);
        } catch (final IOException ex) {


        }
    }
}
