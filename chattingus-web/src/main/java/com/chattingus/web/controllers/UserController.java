package com.chattingus.web.controllers;

import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.User;
import com.chattingus.services.FriendService;
import com.chattingus.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author 
 * @since 2017-04-07
 */
@Controller
@RequestMapping(value="user")
public class UserController extends BaseController{
    private Logger logger = Logger.getLogger(UserController.class);

    @Resource
    UserService userService;

    @Resource
    FriendService friendService;

    /**
     * 消息
     */
    @RequestMapping(value="message")
    public ModelAndView message () {
        User user = sessionService.getUser();
        logger.info("用户名："+user.getUsername()+"查询了消息");
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("nav","message");
        modelAndView.addObject(user);
        return modelAndView;
    }


    /**
     * 好友
     */
    @RequestMapping(value="friend")
    public ModelAndView friend () throws Exception {
        User user = sessionService.getUser();
        logger.info("用户名："+user.getUsername()+"查询了好友");
        ModelAndView modelAndView = new ModelAndView("friend");
        modelAndView.addObject("nav","friend");
        modelAndView.addObject(user);
        return modelAndView;
    }



    /**
     * 保存
     */
    @RequestMapping(value="saveUser")
    @ResponseBody
    public void saveUser () throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

    /**
     * 查询User
     * @return
     */
    @RequestMapping(value="getUser")
    @ResponseBody
    public ServiceResponse getUser(@RequestParam Integer userId){
        ServiceResponse res = new ServiceResponse();
        User firendUser = userService.getUserByUserId(userId);
        if(firendUser == null){
            res.setSubMsg("用户不存在，请检查US号码是否正确");
        }else{
            res.put(firendUser);
        }
        return res;
    }


    /**
     * 查询UserList
     * @return
     */
    @RequestMapping(value="getUserList")
    @ResponseBody
    public List<User> getUserList (){
        //TODO 接下来尽情的实现你的业务逻辑。

        return null;
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value="delUser")
    @ResponseBody
    public void delUser () throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

}
