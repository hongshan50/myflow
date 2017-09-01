package com.hongshan.myflow.zk.spring.namespace;

import com.hongshan.myflow.zk.spring.ZkException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Package com.jd.baina.item.zk.spring.namespace
 * @Description: 配置属性解析
 * @Author guojianwei@jd.com
 * @Date 2016/10/26
 * @Time 11:16
 * @Version V1.0
 */
public class ZkBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(final Element element) {
        return SpringZookeeperClient.class;
    }

    @Override
    protected void doParse(final Element element, final BeanDefinitionBuilder builder) {
        builder.addConstructorArgValue(createZookeeperConfiguration(element));
    }

    private BeanDefinition createZookeeperConfiguration(final Element element) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(SpringZkConfig.class);
        this.addValue(builder, element, "hosts", false);
        this.addValue(builder, element, "namespace", false);
        this.addValue(builder, element, "baseSleepTimeMs", false);
        this.addValue(builder, element, "maxSleepTimeMs", false);
        this.addValue(builder, element, "maxRetries", false);
        this.addValue(builder, element, "sessionTimeoutMs", false);
        this.addValue(builder, element, "connectionTimeoutMs", false);
        this.addValue(builder, element, "digest", false);
        this.addValue(builder, element, "nestedPort", false);
        this.addValue(builder, element, "nestedDataDir", false);
        this.addValue(builder, element, "localPropertiesPath", false);
        this.addValue(builder, element, "overwrite", false);
        this.addValue(builder, element, "isEnable", false);
        return builder.getBeanDefinition();
    }

    /**
     * 属性赋值
     *
     * @param builder  对象构建器
     * @param element  元素
     * @param property 属性
     * @param required 是否必须
     */

    protected void addValue(BeanDefinitionBuilder builder, Element element, String property, boolean required) {
        String val = element.getAttribute(property);
        if (!StringUtils.hasLength(val) && required) {
            throw new ZkException(property + " of "  + " must be configured!");
        }
        if (StringUtils.hasLength(val)) {
            builder.addPropertyValue(property, val);
        }
    }
}
