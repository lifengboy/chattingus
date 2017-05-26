package com.chattingus.web.controllers;

import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.User;
import com.chattingus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 002116110445 on 2017/5/25.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    /* 跳转到注册页面 */
    @RequestMapping(value = "/main")
    @ResponseBody
    public ModelAndView registerMain() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @RequestMapping(value="checkUsername")
    @ResponseBody
    public ServiceResponse submitAddFriend (HttpServletRequest request){
        ServiceResponse res = new ServiceResponse();
        String username = request.getParameter("username");
        User user = userService.getUserByUsername(username);
        if(user != null){
            res.setSubMsg("用户名已存在");
        }
        return res;
    }


    /* 跳转到注册页面 */
    @RequestMapping(value = "/doregister")
    @ResponseBody
    public ModelAndView doregister(String username, String password, String nick, String gender) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNick(nick);
        user.setRealName(nick);
        user.setGender(gender);
        userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


}
