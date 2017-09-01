package com.hongshan.myflow.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class BaseController {

    Logger log = Logger.getLogger(BaseController.class.getName());
    protected void responseResult (HttpServletResponse response,String result) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
        } catch (IOException e) {
            log.info("response writer write exception:"+e);
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                log.info("response writer close exception:"+e);
            }
        }
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return attrs.getRequest();
    }


    public HttpServletResponse getResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return attrs.getResponse();
    }
}
