package com.richman.api;

import com.alibaba.fastjson.JSONObject;
import com.richman.common.cache.redis.config.RedisKey;
import com.richman.domain.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Controller来接收和发送消息
 *
 * 以/app开头的客户端发送的所有消息都将路由到这些使用@MessageMapping注释的消息处理方法
 */
@Controller
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 接收发送过来的消息
     *
     * 使用 @MessageMapping 或者 @SubscribeMapping 注解可以处理客户端发送过来的消息，并选择方法是否有返回值。
     *
     * @param chatMessage
     */
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {

        System.out.println("来消息了 chat.sendMessage");

        try {

            /**
             * redis充当 消息队列  把消息放入队列中
             *
             */
            redisTemplate.convertAndSend(RedisKey.msgToAll, JSONObject.toJSONString(chatMessage));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        LOGGER.info("User added in Chatroom:" + chatMessage.getSender());
        try {
            //headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            //redisTemplate.opsForSet().add(RedisKey.onlineUsers, chatMessage.getSender());
            redisTemplate.convertAndSend(RedisKey.userStatus, JSONObject.toJSONString(chatMessage));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
