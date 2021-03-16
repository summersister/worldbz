package com.dear.common.task;

import com.dear.common.cache.redis.RedisUtil;
import com.dear.common.cache.redis.config.RedisKey;
import com.dear.mapper.bbs.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BbsTasks {

    @Autowired
    private RedisUtil cache;

    @Autowired
    private PostMapper postMapper;
 
    @Scheduled(fixedRate = 5000)
    public void replyNumberTask() {

        while (true) {

            String postId = this.cache.rpop(RedisKey.REPLY_NUMBER_QUEUE);

            if(StringUtils.isBlank(postId)) {

                return;

            } else {

                log.info("replyNumberTask --> postId = " + postId);

                int i = this.postMapper.addNumberCount(Integer.parseInt(postId));

                if(i < 1) {

                    log.error("replyNumberTask:修改 给帖子增加一个回复数 失败");
                }
            }
        }
    }
}