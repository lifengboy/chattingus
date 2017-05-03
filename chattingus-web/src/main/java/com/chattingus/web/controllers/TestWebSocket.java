package com.chattingus.web.controllers;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
* Created by 002116110445 on 2017/4/6.
*/

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置
@ServerEndpoint("/chattingus/test")
public class TestWebSocket {
    Logger log = Logger.getLogger(TestWebSocket.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<TestWebSocket> webSocketSet = new CopyOnWriteArraySet<TestWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @OnMessage
    public void onMessage(String message,Session session) throws IOException, InterruptedException {
        log.debug("来自客户端的消息:" + message);

        //群发消息
        for(TestWebSocket item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.debug("有新连接加入！当前在线人数为"+getOnlineCount());

    }
    @OnClose
    public void onClose() {
        log.debug("Connection closed");
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.debug("有一连接关闭！当前在线人数为"+getOnlineCount());
    }


    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.debug("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws java.io.IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        TestWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        TestWebSocket.onlineCount--;
    }
}
