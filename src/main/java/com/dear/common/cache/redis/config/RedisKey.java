package com.dear.common.cache.redis.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RedisKey {

    public final static String TEST_KEY = "TEST_KEY";

    @ApiModelProperty("用户缓存")
    public final static String USER_KEY_ = "USER_KEY_";

    public final static String EXCEPTION_LOG_ = "EXCEPTION_LOG_";

    @ApiModelProperty("帖子回复数增加队列")
    public final static String REPLY_NUMBER_QUEUE = "REPLY_NUMBER_QUEUE";

    @ApiModelProperty("帖子缓存哈希每一个")
    public final static String POST_H = "POST_H";

    @ApiModelProperty("帖子缓存哈希分页集合")
    public final static String POST_PAGE_H = "POST_PAGE_H";

    @ApiModelProperty("帖子回复缓存哈希分页集合")
    public final static String POST_REPLY_PAGE_H = "POST_REPLY_PAGE_H";

    @ApiModelProperty("帖子回复楼中楼缓存哈希分页集合")
    public final static String POST_REPLY_FLOOR_PAGE_H = "POST_REPLY_FLOOR_PAGE_H";

    @ApiModelProperty("用户缓存哈希分页集合")
    public final static String USER_H = "USER_H";

}
