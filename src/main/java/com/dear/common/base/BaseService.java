package com.dear.common.base;

import com.dear.common.cache.redis.RedisUtil;
import com.dear.common.util.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    public RedisUtil cache;

    @Autowired
    public BaseUtils base;

}
