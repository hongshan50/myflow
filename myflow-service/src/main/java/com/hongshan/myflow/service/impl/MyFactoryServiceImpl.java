package com.hongshan.myflow.service.impl;

import com.hongshan.myflow.service.MyFactoryService;
import com.hongshan.myflow.zk.spring.namespace.MyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 自定义工厂实现类
 *
 * @author renhong1
 * @create 2017-08-31 11:21
 */
@Service
public class MyFactoryServiceImpl implements MyFactoryService {

    @Autowired
    private MyFactory myfacClient;

    public void init() {
        System.out.println("****验证初始化****");
    }
}
