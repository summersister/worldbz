package com.dear.api.bbs.service.impl;

import com.alibaba.fastjson.JSON;
import com.dear.api.bbs.service.IUserService;
import com.dear.common.annotaion.MultipleTransaction;
import com.dear.common.base.BaseService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.common.cache.redis.config.RedisKey;
import com.dear.domain.bbs.User;
import com.dear.domain.bbs.vo.UserDataVO;
import com.dear.mapper.bbs.UserDetailsMapper;
import com.dear.mapper.bbs.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson getUserByUserId(Integer userId) {

        String userJSON = this.cache.get(RedisKey.USER_KEY_ + userId);

        if(!StringUtils.isBlank(userJSON)){

            return new ResultJson(ResultCode.OK.getCode(), JSON.parseObject(userJSON, User.class));
        }

        User user = this.userMapper.selectByPrimaryKey(userId);

        if(user != null && user.getUserId() != null){

            //this.cache.setex(RedisKey.USER_KEY_ + user.getUserId(), JSON.toJSONString(user), 3600);
        }

        return new ResultJson(ResultCode.OK.getCode(), user);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson getUserDataByUserId(Integer userId) {

        UserDataVO vo = this.userMapper.getUserDataByUserId(userId);

        return new ResultJson(ResultCode.OK.getCode(), vo);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson updateUserDataByUserId(UserDataVO vo) {

        UserDataVO user = this.userMapper.getUserDataByUserId(vo.getUserId());

        if(!user.getUserName().equals(vo.getUserName())
                && this.userMapper.getUserCountByUserName(vo.getUserName()) > 0) {

            return new ResultJson(ResultCode.USER_NAME_EXISTS.getCode(), null);
        }

        if(!user.getNickName().equals(vo.getNickName())
                && this.userMapper.getUserCountByNickName(vo.getNickName()) > 0) {

            return new ResultJson(ResultCode.USER_NICK_EXISTS.getCode(), null);
        }

        int i = this.userMapper.updateNickNameAndUserName(vo.getUserId(), vo.getUserName(), vo.getNickName());

        this.base.isException(i, "UserServiceImpl:updateUserDataByUserId:更新用户名失败");

        int i2 = this.userDetailsMapper.updateDetailsByUserId(vo);

        this.base.isException(i2, "UserServiceImpl:updateDetailsByUserId:更新用户名资料失败");

        return new ResultJson(ResultCode.OK.getCode(), null);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson updateUserHeadUrl(Integer userId, String imageUrl) {

        int i = this.userMapper.updateUserHeadUrl(userId, imageUrl);

        this.base.isException(i, "UserServiceImpl:updateUserHeadUrl:更新用户头像失败");

        return new ResultJson(ResultCode.OK.getCode(), null);
    }
}
