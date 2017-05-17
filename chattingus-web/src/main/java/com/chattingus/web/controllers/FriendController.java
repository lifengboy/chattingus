package com.chattingus.web.controllers;

import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.Friend;
import com.chattingus.domain.Message;
import com.chattingus.domain.User;
import com.chattingus.services.FriendService;
import com.chattingus.services.MessageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Resource
    MessageService messageService;

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
    @RequestMapping(value="submitAddFriend", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse submitAddFriend (HttpServletRequest request){
        Integer mId = Integer.parseInt(request.getParameter("mId"));
        Integer fromUserId = Integer.parseInt(request.getParameter("fromUserId"));
        Integer toUserId = Integer.parseInt(request.getParameter("toUserId"));
        ServiceResponse res = new ServiceResponse();
        Friend f1 = new Friend();
        f1.setUserId(fromUserId);
        f1.setFriendId(toUserId);
        Friend f2 = new Friend();
        f2.setUserId(toUserId);
        f2.setFriendId(fromUserId);
        try {
            friendService.addFriend(f1);
            friendService.addFriend(f2);
        }catch (Exception e){
            logger.info("对方已是你的好友");
            res.setSubMsg("对方已是你的好友");
        }
        //改变状态为已读
        Message m = new Message();
        m.setMId(mId);
        m.setReadOrNot(1);
        messageService.updateMessageByKey(m);
        return res;
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
