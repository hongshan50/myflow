package com.hongshan.myflow.zk.spring;

/**
 * @Package com.jd.baina.item.zk.spring
 * @Description: Zk自定义异常
 * @Author guojianwei@jd.com
 * @Date 2016/10/27
 * @Time 9:23
 * @Version V1.0
 */
public class ZkException extends RuntimeException{

    /**  */
    private static final long serialVersionUID = 4283390130473640414L;

    /**
     *
     */
    public ZkException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public ZkException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ZkException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ZkException(Throwable cause) {
        super(cause);
    }
}
