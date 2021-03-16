package com.richman.domain;

/**
 * ChatMessage用来在客户端和服务端中交互
 */
public class ChatMessage {
    private MessageType type;//消息类型
    private String content;//消息内容
    private String sender;//发送者

    public enum MessageType {
        CHAT,//消息
        JOIN,//加入
        LEAVE//离开
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
