package com.dear.common.listener.websocket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebsocketHealthy {

    @Autowired
    private WebSocketServer ws;

    @Scheduled(cron = "0/15 * * * * ? ")
    public void initBuyOrderFollow(){

        log.info("正在连接列表" + JSON.toJSONString(this.ws.getMapKey()));
    }

//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void testSend(){
//
//        log.info("测试发送数据");
//
//        this.ws.sendMessage("testtesttest");
//    }
}
