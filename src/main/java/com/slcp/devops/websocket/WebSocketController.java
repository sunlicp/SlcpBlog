package com.slcp.devops.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: Slcp
 * @date: 2021/6/16 14:19
 * @code: 一生的挚爱
 * @description: webSocket实现网站用户人数的统计，有一个弊端前端是根据窗口判断用户个数的（可根据ip判断）
 */
@ServerEndpoint(value = "/websocketTest/{userId}")
@Component
public class WebSocketController {
    private static int onlineCount=0;
    private static final CopyOnWriteArrayList<WebSocketController> WEB_SOCKET_SET =new CopyOnWriteArrayList<WebSocketController>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session=session;
        //加入set中
        WEB_SOCKET_SET.add(this);
        addOnlineCount();
        //发送信息通知前段，给前端显示人数
        sendMessage(String.valueOf(getOnlineCount()));
        System.out.println("有新连接加入！当前在线人数为"+getOnlineCount());
    }

    @OnClose
    public void onClose() throws IOException {
        WEB_SOCKET_SET.remove(this);
        subOnlineCount();
        sendMessage(String.valueOf(getOnlineCount()));
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 接收前端发送过来的消息
     * @param message 信息
     * @throws IOException io
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("来自客户端的消息："+message);
        //发送信息通知前段，给前端显示人数
        sendMessage(String.valueOf(getOnlineCount()));
//        群发消息
//        for (WebSocketController item:webSocketSet){
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    @OnError
    public void onError(Throwable throwable){
        System.out.println("发生错误！");
        throwable.printStackTrace();
    }
    /**
     * 下面是自定义的一些方法
     */
    public void sendMessage(String message) throws IOException {
        //回调消息给前端
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketController.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketController.onlineCount--;
    }


}