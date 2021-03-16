package com.dear.common.taskThread;

import com.dear.domain.bbs.UserIp;
import com.dear.mapper.bbs.UserIpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserThread {

    @Autowired
    private UserIpMapper userIpMapper;

    /**
     * 记录用户访问ip
     *
     * @param userId
     * @param ip
     * @param type
     */
    @Async
    public void saveUserIp(Integer userId, String ip, int type) {

        UserIp userIp = new UserIp();

        userIp.setType(type);
        userIp.setIp(ip);
        userIp.setUserId(userId);
        userIp.setCreateTime(new Date());

        int j = this.userIpMapper.insertSelective(userIp);

        if(j < 1){

            throw new RuntimeException("UserLoginServiceImpl:userRegister:记录注册用户ip失败");
        }
    }
}
