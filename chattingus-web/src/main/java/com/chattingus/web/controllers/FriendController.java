package com.chattingus.web.controllers;

import com.chattingus.domain.Friend;
import com.chattingus.domain.User;
import com.chattingus.services.FriendService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;
/**
 * @author 
 * @since 2017-04-07
 */
@Controller
@RequestMapping(value="friend")
public class FriendController extends BaseController{
    private Logger logger = Logger.getLogger(FriendController.class);

    @Resource
    FriendService friendService;

    /**
     * 保存
     */
    @RequestMapping(value="addFriend")
    public ModelAndView addFriend (){
        User user = sessionService.getUser();
        logger.info("用户名："+user.getUsername()+"进入了添加好友界面");
        ModelAndView modelAndView = new ModelAndView("add-friend");
        modelAndView.addObject("nav","add-friend");
        modelAndView.addObject(user);
        return modelAndView;
    }


    /**
     * 保存
     */
    @RequestMapping(value="saveFriend")
    @ResponseBody
    public void saveFriend () throws Exception {
//        WebResponse webResponse = new WebResponse();

    }

    /**
     * 查询
     * @return
     */
    @RequestMapping(value="getFriendList")
    @ResponseBody
    public List<Friend> getFriendList () throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

        return null;
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value="delFriend")
    @ResponseBody
    public void delFriend () throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

}
