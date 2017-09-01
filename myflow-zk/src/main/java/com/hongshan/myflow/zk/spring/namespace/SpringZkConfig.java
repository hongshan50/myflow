package com.hongshan.myflow.zk.spring.namespace;

/**
 * @Package com.jd.baina.item.zk.spring.namespace
 * @Description: 配置文件
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 11:18
 * @Version V1.0
 */
public class SpringZkConfig {

    private String hosts;

    private String namespace;

    private String baseSleepTimeMs;

    private String maxSleepTimeMs;

    private String maxRetries;

    private String sessionTimeoutMs;

    private String connectionTimeoutMs;

    private String digest;

    private String nestedPort = "-1";

    private String nestedDataDir;

    private String localPropertiesPath;

    private String overwrite;

    private String isEnable;


    public String getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(String baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public String getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(String connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getLocalPropertiesPath() {
        return localPropertiesPath;
    }

    public void setLocalPropertiesPath(String localPropertiesPath) {
        this.localPropertiesPath = localPropertiesPath;
    }

    public String getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(String maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getMaxSleepTimeMs() {
        return maxSleepTimeMs;
    }

    public void setMaxSleepTimeMs(String maxSleepTimeMs) {
        this.maxSleepTimeMs = maxSleepTimeMs;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNestedDataDir() {
        return nestedDataDir;
    }

    public void setNestedDataDir(String nestedDataDir) {
        this.nestedDataDir = nestedDataDir;
    }

    public String getNestedPort() {
        return nestedPort;
    }

    public void setNestedPort(String nestedPort) {
        this.nestedPort = nestedPort;
    }

    public String getOverwrite() {
        return overwrite;
    }

    public void setOverwrite(String overwrite) {
        this.overwrite = overwrite;
    }

    public String getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(String sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}
