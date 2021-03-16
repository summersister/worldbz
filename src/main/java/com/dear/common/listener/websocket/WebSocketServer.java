package com.dear.common.listener.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务类
 *
 * @ServerEndpoint 注解标注的类为多实例
 *
 * @author Neo
 */
@Slf4j
@ServerEndpoint(value = "/test/{id}")
@Component
public class WebSocketServer {

    //存储每一个客户端身份id和会话信息的线程安全的集合
    private static Map<String, Session> map = new ConcurrentHashMap<>();

    public List<String> getMapKey(){

        List<String> arrayList = new ArrayList();

        map.keySet().forEach(p-> arrayList.add(p));

        return arrayList;
    }

    /**
     * 连接成功时调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {

        String userKey = "TEST-" + id;

        /**
         * 这是一个重复调用的判断 关闭之前的连接
         *
         * 暂时不关闭之前的页面连接
         */

        if (WebSocketServer.map.containsKey(userKey)){

            this.closeConnection(WebSocketServer.map.get(userKey));
        }

        WebSocketServer.map.put(userKey, session);

        log.info("客户端" + session.getId() + "加入连接，连接数: " + WebSocketServer.map.size());
    }

    /**
     * 连接关闭时调用的方法
     * @param session
     */
    @OnClose
    public void onClose(Session session) {

        WebSocketServer.map.values().remove(session);

        log.info("客户端" + session.getId() + "断开连接，连接数: " + WebSocketServer.map.size());
    }

    /**
     * 出现异常时调用的方法
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        //打印日志
        log.error("发生错误：sessionID（"+session.getId()+"），错误信息（"+error.getMessage()+"）");

        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {

        log.info("收到客户端" + session.getId() + "的消息: " + message);
    }

    /**
     * 关闭客户端连接
     * @param session
     */
    private void closeConnection(Session session) {

        try {session.close();}catch (Exception e){

            WebSocketServer.map.values().remove(session);

            log.error("关闭会话出错：sessionID（"+session.getId()+"），错误信息（"+e.getMessage()+"）");

            e.printStackTrace();
        }
    }

    /**
     * 给客户端发送消息
     * @param session
     * @param message
     */
    public void sendMessage (Session session, String message){

        try {

            session.getBasicRemote().sendText(message);
        } catch (Exception e) {

            this.closeConnection(session);

            //打印日志
            log.error("发送消息出错：sessionID（"+session.getId()+"），错误信息（"+e.getMessage()+"）");

            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message
     */
    public void sendMessage(String message){

        //遍历会话信息
        for (Session session : WebSocketServer.map.values()) {

            //判断连接是否开着
            if(session.isOpen()){

                this.sendMessage(session, message);
            }
        }
    }
}
