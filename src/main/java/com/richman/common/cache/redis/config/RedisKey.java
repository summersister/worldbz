package com.richman.common.cache.redis.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class RedisKey {

    @ApiModelProperty("")
    public final static String msgToAll = "msgToAll";

    @ApiModelProperty("")
    public final static String userStatus = "userStatus";

    @ApiModelProperty("")
    public final static String onlineUsers = "onlineUsers";
}
