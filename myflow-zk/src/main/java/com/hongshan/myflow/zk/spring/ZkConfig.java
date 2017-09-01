package com.hongshan.myflow.zk.spring;

import com.google.common.base.Strings;

/**
 * @Package com.jd.baina.item.zk
 * @Description: ZK配置
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 14:49
 * @Version V1.0
 */
public class ZkConfig {

    private Boolean isEnable;

    /**
     * 连接Zookeeper服务器的列表.
     * 包括IP地址和端口号.
     * 多个地址用逗号分隔.
     * 如: host1:2181,host2:2181
     */
    private String hosts;

    /**
     * 命名空间.
     */
    private String namespace;

    /**
     * 等待重试的间隔时间的初始值.
     * 单位毫秒.
     */
    private int baseSleepTimeMs = 1000;

    /**
     * 等待重试的间隔时间的最大值.
     * 单位毫秒.
     */
    private int maxSleepTimeMs = 3000;

    /**
     * 最大重试次数.
     */
    private int maxRetries = 3;

    /**
     * 会话超时时间.
     * 单位毫秒.
     */
    private int sessionTimeoutMs;

    /**
     * 连接超时时间.
     * 单位毫秒.
     */
    private int connectionTimeoutMs;

    /**
     * 连接Zookeeper的权限令牌.
     * 缺省为不需要权限验证.
     */
    private String digest;

    /**
     * 内嵌Zookeeper的端口号.
     * -1表示不开启内嵌Zookeeper.
     */
    private int nestedPort = -1;

    /**
     * 内嵌Zookeeper的数据存储路径.
     * 为空表示不开启内嵌Zookeeper.
     */
    private String nestedDataDir;

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public ZkConfig setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
        return this;
    }

    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public ZkConfig setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
        return this;
    }

    public String getDigest() {
        return digest;
    }

    public ZkConfig setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    public String getHosts() {
        return hosts;
    }

    public ZkConfig setHosts(String hosts) {
        this.hosts = hosts;
        return this;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public ZkConfig setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public int getMaxSleepTimeMs() {
        return maxSleepTimeMs;
    }

    public ZkConfig setMaxSleepTimeMs(int maxSleepTimeMs) {
        this.maxSleepTimeMs = maxSleepTimeMs;
        return this;
    }

    public String getNamespace() {
        return namespace;
    }

    public ZkConfig setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getNestedDataDir() {
        return nestedDataDir;
    }

    public ZkConfig setNestedDataDir(String nestedDataDir) {
        this.nestedDataDir = nestedDataDir;
        return this;
    }

    public int getNestedPort() {
        return nestedPort;
    }

    public ZkConfig setNestedPort(int nestedPort) {
        this.nestedPort = nestedPort;
        return this;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public ZkConfig setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
        return this;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public ZkConfig setEnable(Boolean enable) {
        isEnable = enable;
        return this;
    }

    /**
     * 判断是否需要开启内嵌Zookeeper.
     *
     * @return 是否需要开启内嵌Zookeeper
     */
    public boolean isUseNestedZookeeper() {
        return -1 != nestedPort && !Strings.isNullOrEmpty(nestedDataDir);
    }
}
