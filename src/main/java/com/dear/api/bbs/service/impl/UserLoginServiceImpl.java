package com.dear.api.bbs.service.impl;

import com.dear.api.bbs.service.IUserLoginService;
import com.dear.common.annotaion.MultipleTransaction;
import com.dear.common.base.BaseService;
import com.dear.common.bean.OperationType;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.common.taskThread.UserThread;
import com.dear.common.util.jwt.JwtHelper;
import com.dear.common.util.tools.MD5Util;
import com.dear.common.util.tools.UUIDUtil;
import com.dear.domain.bbs.User;
import com.dear.domain.bbs.UserDetails;
import com.dear.domain.bbs.vo.UserDataVO;
import com.dear.domain.bbs.vo.UserRegVO;
import com.dear.mapper.bbs.UserDetailsMapper;
import com.dear.mapper.bbs.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserLoginServiceImpl extends BaseService implements IUserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private UserThread userThread;

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson userNameExists(Integer userId, Integer type, String name) {

        UserDataVO user = this.userMapper.getUserDataByUserId(userId);

        if(type == 0) {

            if(!user.getUserName().equals(name)
                    && this.userMapper.getUserCountByUserName(name) > 0) {

                return new ResultJson(ResultCode.USER_NAME_EXISTS.getCode(), null);
            }
        } else {

            if(!user.getNickName().equals(name)
                    && this.userMapper.getUserCountByNickName(name) > 0) {

                return new ResultJson(ResultCode.USER_NICK_EXISTS.getCode(), "昵称重复");
            }
        }

        return new ResultJson(ResultCode.OK.getCode(), null);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson userRegister(UserRegVO regVo, String ip) {

        //校验用户是否存在
        int count = this.userMapper.getUserCountByUserNameAndNickName(regVo.getUserName());

        if(count > 0){

            return new ResultJson(ResultCode.USER_NAME_EXISTS.getCode(), null);
        }

        /**
         * 注册
         */

        User user = new User();

        user.setUserName(regVo.getUserName());
        user.setPassWord(regVo.getPassWord());

        //密码加密
        user.setPassWord(MD5Util.md5(user.getPassWord()));

        //随机一个昵称
        user.setNickName(UUIDUtil.getNickName());

        user.setDel((byte) 0);
        user.setIsFrozen((byte) 0);
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());

        int i = this.userMapper.insertSelective(user);

        this.base.isException(i, "UserLoginServiceImpl:userRegister:注册用户失败");

        UserDetails udI = new UserDetails();

        udI.setUserId(user.getUserId());
        udI.setUpdateTime(user.getUpdateTime());
        udI.setSex((byte) 0);

        this.userDetailsMapper.insert(udI);

        this.base.isException(i, "UserLoginServiceImpl:userRegister:注册用户附表失败");

        //记录ip
        this.userThread.saveUserIp(user.getUserId(), ip, OperationType.REGISTER.getCode());

        return new ResultJson(ResultCode.OK.getCode(), null);
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson login(User userDto, String ip) {

        User user = this.userMapper.getUserByUserNameAndPassWord(
                userDto.getUserName(), MD5Util.md5(userDto.getPassWord()));

        if(user == null) {

            return new ResultJson(ResultCode.PASSWORD_ERROR.getCode() ,ResultCode.PASSWORD_ERROR.getMessage());
        }

        if(user.getIsFrozen() == 1) {

            return new ResultJson(ResultCode.USER_IS_FROZEN.getCode(), ResultCode.USER_IS_FROZEN.getMessage());
        }

        this.userThread.saveUserIp(user.getUserId(), ip, OperationType.LOGIN.getCode());

        return new ResultJson(ResultCode.OK.getCode(), JwtHelper.createJWT(String.valueOf(user.getUserId())));
    }
}
