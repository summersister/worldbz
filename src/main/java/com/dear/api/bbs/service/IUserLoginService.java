package com.dear.api.bbs.service;

import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.User;
import com.dear.domain.bbs.vo.UserRegVO;

public interface IUserLoginService {

    /**
     * 校验用户名是否存在
     *
     * @return
     */
    ResultJson userNameExists(Integer userId, Integer type, String name);

    /**
     * 用户注册
     *
     * @param regVo
     * @return
     */
    ResultJson userRegister(UserRegVO regVo, String ip);

    /**
     * 用户登录
     *
     * @param userDto
     * @param ip
     * @return
     */
    ResultJson login(User userDto, String ip);
}
