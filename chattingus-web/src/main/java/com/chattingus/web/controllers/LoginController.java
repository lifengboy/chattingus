package com.chattingus.web.controllers;

import com.chattingus.commons.constants.ErrorCode;
import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.User;
import com.chattingus.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 002116110445 on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController{
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping(value = "/in", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse login(@RequestBody User user,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model,
                                 HttpSession session) {
        ServiceResponse res = new ServiceResponse();
        logger.info("用户名："+user.getUsername()+" 密码："+user.getPassword()+"登录ip："+request.getRemoteAddr());
        User queryUser = userService.getUserByUserNamePassWord(user);

        // 用户信息为空，则返回
        if (queryUser == null) {
            res.setSubMsg(ErrorCode.USERNAME_OR_PASSWORD_IS_WRONG_SSTRING);
            res.setSubCode(ErrorCode.USERNAME_OR_PASSWORD_IS_WRONG_CODE);
            return res;
        }
        //登录之后保存当前机器的ip，等信息
        queryUser = userService.saveUserMessage(request.getRemoteAddr(),queryUser);

        // 登录成功 设置上下文环境
        setHttpServlet(request, response, session, model);

        // 先清空Session
        sessionService.deleteUser();
        // 设定Session
        sessionService.setUser(queryUser);

        // 保存日志
//        actionLogService.saveUserActionLog(null, UserLogActionType.LOGIN.getDesc(), UserLogOperateType.NORMAL_ACTION.getDesc(), "");

        return res;
    }

    /* 跳转到首页 */
    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("login");
        sessionService.deleteUser();
        return modelAndView;
    }


}
