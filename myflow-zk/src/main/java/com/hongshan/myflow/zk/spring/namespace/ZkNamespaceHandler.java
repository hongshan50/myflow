package com.hongshan.myflow.zk.spring.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Package com.jd.baina.item.zk.spring.namespace
 * @Description: zk命名空间处理
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 11:09
 * @Version V1.0
 */
public class ZkNamespaceHandler  extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("client", new ZkBeanDefinitionParser());
    }
}
