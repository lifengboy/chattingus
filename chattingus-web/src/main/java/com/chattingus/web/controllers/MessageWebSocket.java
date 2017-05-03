package com.chattingus.web.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chattingus.commons.response.ServiceResponse;
import com.chattingus.domain.Message;
import com.chattingus.domain.User;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.*;

/**
 * Created by 002116110445 on 2017/4/24.
 */
public class MessageWebSocket extends WebSocketServlet{

    Logger log = Logger.getLogger(MessageWebSocket.class);

    //常量
    public static final String SESSION_USER = "chattingusUser";

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    private static CopyOnWriteArraySet<MessageWebSocket> webSocketSet = new CopyOnWriteArraySet<MessageWebSocket>();

    private static ConcurrentMap<Integer, MyMessageInbound> webSocketMap = new ConcurrentHashMap();

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MessageWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MessageWebSocket.onlineCount--;
    }


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
            addOnlineCount();           //在线数加1
            log.debug("有新连接加入！当前在线人数为" + getOnlineCount());
            this.outbound = outbound;
        }

        @Override
        protected void onClose(int status) {
            log.debug("Connection closed");
//            webSocketMap.remove(((User)session.getAttribute(SESSION_USER)).getUserId());  //从map中删除
            subOnlineCount();           //在线数减1
            log.debug("有一连接关闭！当前在线人数为" + getOnlineCount());
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
            if("0".equals(messageType)){
                Integer currentUserId = Integer.parseInt(jo.getString("currentUserId"));
                currentUser = ((UserService) wac.getBean("userService")).getUserByUserId(currentUserId);
                log.info("这是告诉系统当前用户的系统消息，currentUserId为"+currentUserId);
                webSocketMap.put(currentUserId, this);
                MessageService messageService = (MessageService) wac.getBean("messageService");
                List<Message> confirmMessage =  messageService.getMessageByType(currentUserId, 0);
                List<Message> friendMessage =  messageService.getMessageByType(currentUserId, 1);
                ServiceResponse res = new ServiceResponse();
                res.put("confirmMessage",confirmMessage);
                res.put("friendMessage", friendMessage);
                log.info("给客户端发送系统消息："+ JSON.toJSONString(res));
                try {
                    getWsOutbound().writeTextMessage(CharBuffer.wrap(JSON.toJSONString(res)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getReadTimeout() {
            return 0;
        }
    }
}
