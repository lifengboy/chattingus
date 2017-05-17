package com.chattingus.web.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.Friend;
import com.chattingus.domain.Message;
import com.chattingus.domain.User;
import com.chattingus.services.FriendService;
import com.chattingus.services.MessageService;
import com.chattingus.services.UserService;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 002116110445 on 2017/4/24.
 */
public class MessageWebSocket extends WebSocketServlet{

    Logger log = Logger.getLogger(MessageWebSocket.class);

    //常量
    public static final String SESSION_USER = "chattingusUser";

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    private static CopyOnWriteArraySet<MessageWebSocket> webSocketSet = new CopyOnWriteArraySet<MessageWebSocket>();

    public static ConcurrentMap<Integer, MyMessageInbound> webSocketMap = new ConcurrentHashMap();

    @Override
    protected StreamInbound createWebSocketInbound(String s, HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
        return new MyMessageInbound(wac);
    }

    private class MyMessageInbound extends MessageInbound{
        WsOutbound outbound;
        WebApplicationContext wac;
        User currentUser;

        private MyMessageInbound(WebApplicationContext wac) {
            this.wac = wac;
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            this.outbound = outbound;
        }

        @Override
        protected void onClose(int status) {
            log.info("Connection closed");
            webSocketMap.remove(currentUser.getUserId());  //从map中删除
            //改变数据库在线状态
            UserService userService = (UserService) wac.getBean("userService");
            userService.changeOutline(currentUser,"0");

            //将在线的好友的好友列表的状态改为离线
            FriendService friendService = (FriendService) wac.getBean("friendService");
            List<Friend> friends = friendService.getFriendListByUserId(currentUser);
            for(int i = 0;i < friends.size();i++){
                Friend friend = friends.get(i);
                if(webSocketMap.get(friend.getUser().getUserId()) != null && "1".equals(friend.getUser().getStatus())){
                    try {
                        ServiceResponse res2 = new ServiceResponse();
                        List<Friend> friendFriends = friendService.getFriendListByUserId(friend.getUser());
                        res2.put("serverMessageType", 0);
                        res2.put("friends", friendFriends);
                        webSocketMap.get(friend.getUser().getUserId()).getWsOutbound().writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res2)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.info("有一连接关闭！当前在线人数为" + webSocketMap.size());
        }

        @Override
        protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {

        }

        @Override
        protected void onTextMessage(CharBuffer charBuffer) throws IOException {
            log.info("收到用户的消息"+charBuffer.toString());
            String jsonStr = charBuffer.toString();
            JSONObject jo = JSONObject.parseObject(jsonStr);
            String messageType = jo.getString("messageType");
            //如果收到这类消息，就把该userId put进去
            //0----------传递uerId的消息
            //1----------一对一聊天的消息
            if("0".equals(messageType)){
                Integer currentUserId = Integer.parseInt(jo.getString("currentUserId"));
                UserService userService = (UserService) wac.getBean("userService");
                currentUser = userService.getUserByUserId(currentUserId);
                log.info("这是告诉系统当前用户的系统消息，currentUserId为"+currentUserId);
                if(webSocketMap.get(currentUserId) != null){
                    webSocketMap.get(currentUserId).onClose(0);
                    log.info("关闭了一个已存在的websocket线程");
                }
                webSocketMap.put(currentUserId, this);
                //查看在线人数
                log.info("有新连接加入！当前在线人数为" + webSocketMap.size());
                //改变数据库在线状态
                userService.changeOutline(currentUser,"1");
                MessageService messageService = (MessageService) wac.getBean("messageService");
                FriendService friendService = (FriendService) wac.getBean("friendService");
                //将这三个数组从服务器拿过来
                List<Message> confirmMessage =  messageService.getMessageByType(currentUserId, 0);
                List<Message> friendMessage =  messageService.getMessageByType(currentUserId, 1);
                List<Friend> friends = friendService.getFriendListByUserId(currentUser);
                ServiceResponse res = new ServiceResponse();
                res.put("serverMessageType", 0);
                res.put("confirmMessage",confirmMessage);
                res.put("friendMessage", friendMessage);
                res.put("friends", friends);
                log.info("给客户端发送系统消息："+ JSON.toJSONString(res));
                try {
                    getWsOutbound().writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将在线的好友的好友列表的状态改为在线
                for(int i = 0;i < friends.size();i++){
                    Friend friend = friends.get(i);
                    if(webSocketMap.get(friend.getUser().getUserId()) != null && "1".equals(friend.getUser().getStatus())){
                        try {
                            ServiceResponse res2 = new ServiceResponse();
                            List<Friend> friendFriends = friendService.getFriendListByUserId(friend.getUser());
                            res2.put("serverMessageType", 0);
                            res2.put("friends", friendFriends);
                            webSocketMap.get(friend.getUser().getUserId()).getWsOutbound().writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res2)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else if("1".equals(messageType)){
                Integer currentUserId = Integer.parseInt(jo.getString("currentUserId"));
                Integer friendId = Integer.parseInt(jo.getString("friendId"));
                String messageContent = jo.getString("messageContent");
                String myNick = jo.getString("myNick");
                ServiceResponse res = new ServiceResponse();
                res.put("serverMessageType", 2);
                res.put("fromUserId", currentUserId);
                res.put("fromUserNick", myNick);
                res.put("toUserId", friendId);
                res.put("messageContent", messageContent);
                if(webSocketMap.get(friendId) != null){
                    webSocketMap.get(friendId).outbound.writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res)));
                }
            }else if("2".equals(messageType)){
                Integer currentUserId = Integer.parseInt(jo.getString("userId"));
                String messageContent = jo.getString("messageContent");
                String myNick = jo.getString("myNick");
                ServiceResponse res = new ServiceResponse();
                res.put("serverMessageType", 3);
                res.put("fromUserNick", myNick);
                res.put("messageContent", messageContent);
                Set<Map.Entry<Integer, MyMessageInbound>> set = webSocketMap.entrySet();
                Iterator<Map.Entry<Integer, MyMessageInbound>> it = set.iterator();
                while(it.hasNext()){
                    Map.Entry<Integer, MyMessageInbound> entry = it.next();
                    if(entry.getKey() != currentUserId){
                        MyMessageInbound myMessageInbound = entry.getValue();
                        if(myMessageInbound != null){
                            myMessageInbound.outbound.writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res)));
                        }
                    }
                }

            }
        }

        @Override
        public int getReadTimeout() {
            return 0;
        }

    }
}
