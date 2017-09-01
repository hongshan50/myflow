package com.hongshan.myflow.zk.spring.namespace;

import com.google.common.base.Strings;
import com.hongshan.myflow.zk.spring.ZkConfig;
import com.hongshan.myflow.zk.spring.ZookeeperClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Package com.jd.baina.item.zk.spring.namespace
 * @Description: spring zookeeper client
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 14:16
 * @Version V1.0
 */
public class SpringZookeeperClient extends ZookeeperClient implements BeanFactoryPostProcessor {

    private SpringZkConfig springZkConfig;

    public SpringZookeeperClient(final SpringZkConfig springZkConfig) {
        super(new ZkConfig());
        this.springZkConfig = springZkConfig;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        getZkConfig().setHosts(springZkConfig.getHosts())
                .setEnable(Strings.isNullOrEmpty(springZkConfig.getIsEnable())?true:springZkConfig.getIsEnable().equals("true"))
                .setNamespace(springZkConfig.getNamespace())
                .setDigest(springZkConfig.getDigest())
                .setNestedDataDir(springZkConfig.getNestedDataDir())
                .setNestedPort(Strings.isNullOrEmpty(springZkConfig.getNestedPort())?-1:Integer.valueOf(springZkConfig.getNestedPort()))
                .setBaseSleepTimeMs(Strings.isNullOrEmpty(springZkConfig.getBaseSleepTimeMs())?0:Integer.valueOf(springZkConfig.getBaseSleepTimeMs()))
                .setConnectionTimeoutMs(Strings.isNullOrEmpty(springZkConfig.getConnectionTimeoutMs())?0: Integer.valueOf(springZkConfig.getConnectionTimeoutMs()))
                .setMaxRetries(Strings.isNullOrEmpty(springZkConfig.getMaxRetries())?0:Integer.valueOf(springZkConfig.getMaxRetries()))
                .setMaxSleepTimeMs(Strings.isNullOrEmpty(springZkConfig.getMaxSleepTimeMs())?0:Integer.valueOf(springZkConfig.getMaxSleepTimeMs()))
                .setSessionTimeoutMs(Strings.isNullOrEmpty(springZkConfig.getSessionTimeoutMs())?0:Integer.valueOf(springZkConfig.getSessionTimeoutMs()));

        init();

    }
}
