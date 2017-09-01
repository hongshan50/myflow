package com.hongshan.myflow.zk.spring.namespace;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * 自定义工厂解析类
 *
 * @author renhong1
 * @create 2017-08-31 10:39
 */
public class MyFacBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(final Element element) {
        return MyFactory.class;
    }

    @Override
    protected void doParse(final Element element, final BeanDefinitionBuilder builder) {
        String host = element.getAttribute("host");
        String num = element.getAttribute("num");
        builder.addPropertyValue("host",host);
        builder.addPropertyValue("num",num);
    }
}
