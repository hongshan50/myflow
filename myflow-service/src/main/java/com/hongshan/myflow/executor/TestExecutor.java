package com.hongshan.myflow.executor;

import com.hongshan.myflow.service.MyFactoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试定时器
 *
 * @author renhong1
 * @create 2017-09-01 13:49
 */
public class TestExecutor {

    @Autowired
    private MyFactoryService myFactoryService;

    public void execute(){
        myFactoryService.init();
    }
}
