package com.hongshan.myflow.zk.spring;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Package com.jd.baina.item
 * @Description: ZK客户端
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 14:16
 * @Version V1.0
 */
public class ZookeeperClient {

    private static Logger log = LoggerFactory.getLogger(ZookeeperClient.class);

    private static ExecutorService executor = Executors.newSingleThreadExecutor();


    private final AtomicReference<State> state = new AtomicReference<State>(State.LATENT);


    private CuratorFramework client;

    private ZkConfig zkConfig;

    public ZookeeperClient(ZkConfig zkConfig){
        this.zkConfig = zkConfig;
    }

    public ZkConfig getZkConfig() {
        return zkConfig;
    }

    public void init() {

        Preconditions.checkState(state.compareAndSet(State.LATENT, State.STARTED), "此方法只能执行一次！！");

            executor.submit(new Runnable() {
                @Override
                public void run() {
                    if (zkConfig.isUseNestedZookeeper()) {
                        NestedZookeeperServers.getInstance().startServerIfNotStarted(zkConfig.getNestedPort(), zkConfig.getNestedDataDir());
                    }
                    log.debug("Elastic job: zookeeper registry center init, server lists is: {}.", zkConfig.getHosts());
                    CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                            .connectString(zkConfig.getHosts())
                            .retryPolicy(new BoundedExponentialBackoffRetry(zkConfig.getBaseSleepTimeMs(), zkConfig.getMaxRetries(), zkConfig.getMaxSleepTimeMs()))
                            .namespace(zkConfig.getNamespace());
                    if (0!=zkConfig.getSessionTimeoutMs()) {
                        builder.sessionTimeoutMs(Integer.valueOf(zkConfig.getSessionTimeoutMs()));
                    }
                    if (0!=zkConfig.getConnectionTimeoutMs()) {
                        builder.connectionTimeoutMs(Integer.valueOf(zkConfig.getConnectionTimeoutMs()));
                    }
                    if (!Strings.isNullOrEmpty(zkConfig.getDigest())) {
                        builder.authorization("digest", zkConfig.getDigest().getBytes(Charset.forName("UTF-8")))
                                .aclProvider(new ACLProvider() {

                                    @Override
                                    public List<ACL> getDefaultAcl() {
                                        return ZooDefs.Ids.CREATOR_ALL_ACL;
                                    }

                                    @Override
                                    public List<ACL> getAclForPath(final String path) {
                                        return ZooDefs.Ids.CREATOR_ALL_ACL;
                                    }
                                });
                    }
                    try {
                        client = builder.build();
                        client.start();
                        log.info("zkClient成功获取连接！");
                    } catch (final Exception ex) {

                        log.info("zkClient连接失败！");
                        //ex.printStackTrace();
                        //CHECKSTYLE:ON

                        //throw new ZkException(ex);
                    }
                }
            });

    }


    public boolean isConnected(){
        return client.getZookeeperClient().isConnected();
    }
    public CuratorFramework getClient() {
        return client;
    }

    public void close() {
        CloseableUtils.closeQuietly(client);
        if (zkConfig.isUseNestedZookeeper()) {
            NestedZookeeperServers.getInstance().closeServer(zkConfig.getNestedPort());
        }
    }

    public boolean isExisted(final String key) {
        try {
            return null != client.checkExists().forPath(key);
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
            return false;
        }
    }

    public void persist(final String key, final String value) {
        try {
            if (!isExisted(key)) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(key, value.getBytes());
            } else {
                update(key, value);
            }
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
    }

    public void update(final String key, final String value) {
        try {
            client.inTransaction().check().forPath(key).and().setData().forPath(key, value.getBytes(Charset.forName("UTF-8"))).and().commit();
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
    }

    public List<String> getChildren(String path) throws Exception {
        if(client.checkExists().forPath(path) == null) {
            return null;
        } else {
            List<String> children = client.getChildren().forPath(path);
            return children;
        }
    }

    public void persistEphemeral(final String key, final String value) {
        try {
            if (isExisted(key)) {
                client.delete().deletingChildrenIfNeeded().forPath(key);
            }
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(key, value.getBytes(Charset.forName("UTF-8")));
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
    }

    public void persistEphemeralSequential(final String key) {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(key);
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
    }

    public void remove(final String key) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(key);
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
    }

    public long getRegistryCenterTime(final String key) {
        long result = 0L;
        try {
            String path = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(key);
            result = client.checkExists().forPath(path).getCtime();
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
        Preconditions.checkState(0L != result, "Cannot get registry center time.");
        return result;
    }

    public String get(final String key) {
        String result = null;
        try {
            result = new String(client.getData().forPath(key));
            //CHECKSTYLE:OFF
        } catch (final Exception ex) {
            //CHECKSTYLE:ON
        }
        return result;
    }

    public enum State
    {
        LATENT,
        STARTED,
        CLOSED
    }

}
