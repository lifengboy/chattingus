package com.chattingus.web.controllers;

import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.Friend;
import com.chattingus.domain.Message;
import com.chattingus.services.FriendService;
import com.chattingus.services.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author
 * @since 2017-04-24
 */
@Controller
@RequestMapping(value = "message")
public class MessageController extends BaseController {

    @Resource
    MessageService messageService;

    @Resource
    FriendService friendService;

    /**
     * 保存
     */
    @RequestMapping(value = "sendAddFriendMessage")
    @ResponseBody
    public ServiceResponse sendAddFriendMessage(@RequestParam Integer userId) throws Exception {
        ServiceResponse res = new ServiceResponse();
        Integer toUserId = userId;
        Integer fromUserId = sessionService.getUser().getUserId();
        if(toUserId.intValue() == fromUserId.intValue()){
            res.setSubMsg("不能添加自己");
        }else{
            Friend friend = friendService.getFriendByUserIdFriendId(fromUserId, toUserId);
            if(friend != null){
                res.setSubMsg("对方已是你的好友了");
                return res;
            }
            Message message = new Message();
            message.setFromUserId(fromUserId);
            message.setToUserId(toUserId);
            message.setMessageType(0);
            message.setReadOrNot(0);
            message.setSendTime(new Date());
            messageService.addMessage(message);
            res.setSubMsg("请求发送成功，请等待对方回复");
        }
        return res;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "saveMessage")
    @ResponseBody
    public void saveMessage() throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping(value = "getMessageList")
    @ResponseBody
    public void getMessageList() throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "delMessage")
    @ResponseBody
    public void delMessage() throws Exception {
        //TODO 接下来尽情的实现你的业务逻辑。

    }

}
