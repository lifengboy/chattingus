package com.chattingus.web.controllers;

import com.alibaba.fastjson.JSON;
import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.Friend;
import com.chattingus.domain.Message;
import com.chattingus.services.FriendService;
import com.chattingus.services.MessageService;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
/**
 * @author
 * @since 2017-04-24
 */
@Controller
@RequestMapping(value = "message")
public class MessageController extends BaseController {
    private Logger logger = Logger.getLogger(MessageController.class);

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
            MessageInbound mw = MessageWebSocket.webSocketMap.get(toUserId);
            if(mw != null){
                logger.info("给在线ID"+toUserId+"发送验证消息");
                Message message2 = new Message();
                message2.setFromUserId(fromUserId);
                message2.setToUserId(toUserId);
                List<Message> confirmMessage = new ArrayList();
                confirmMessage.add(message2);
                ServiceResponse res2 = new ServiceResponse();
                res2.put("serverMessageType", 1);
                res2.put("confirmMessage", confirmMessage);
                mw.getWsOutbound().writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res2)));
            }
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
