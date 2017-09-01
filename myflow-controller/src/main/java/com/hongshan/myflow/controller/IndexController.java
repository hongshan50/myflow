package com.hongshan.myflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * 主逻辑
 *
 * @author renhong1
 * @create 2017-09-01 16:32
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    Logger logger = Logger.getLogger(IndexController.class.getName());

    @RequestMapping(value = "/itemDetail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView itemDetail(){
        logger.info("进入详情页");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("userid","RH");
        return mav;
    }
}
