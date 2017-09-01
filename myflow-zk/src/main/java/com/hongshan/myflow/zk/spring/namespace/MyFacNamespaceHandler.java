package com.hongshan.myflow.zk.spring.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义工厂标签
 *
 * @author renhong1
 * @create 2017-08-31 10:27
 */
public class MyFacNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("client", new MyFacBeanDefinitionParser());
    }
}
