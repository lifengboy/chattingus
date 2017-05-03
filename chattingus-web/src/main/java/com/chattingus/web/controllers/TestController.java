package com.chattingus.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 002116110445 on 2017/4/6.
 */

@Controller
@RequestMapping("/chattingus")
public class TestController {
    Logger log = Logger.getLogger(TestController.class);

    @RequestMapping(value = "/start")
    public ModelAndView tojsp(HttpServletRequest request){
        String ua = request.getHeader("user-agent");
        log.debug(ua);

        log.debug("转发到jsp页面");
        return new ModelAndView("webSocketTest");
    }

    @RequestMapping(value = "/login")
    public ModelAndView tologin(HttpServletRequest request){
        log.debug("转发到login页面");
        return new ModelAndView("login1");
    }

    @RequestMapping(value = "/dologin")
    public ModelAndView dologin(HttpServletRequest request,@RequestParam String username,@RequestParam String password){

        log.debug("正在登录");
        return new ModelAndView("login1");
    }

}
