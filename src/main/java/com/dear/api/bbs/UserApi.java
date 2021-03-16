package com.dear.api.bbs;

import com.alibaba.fastjson.JSONObject;
import com.dear.api.bbs.service.IUserService;
import com.dear.common.annotaion.Login;
import com.dear.common.base.BaseApi;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.vo.UserDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"用户中心-用户信息"})
@RestController
@RequestMapping("/customer/user")
public class UserApi extends BaseApi {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取登录用户信息")
    @RequestMapping(value = "/v1/getUserById", method = RequestMethod.POST)
    @Login
    public @ResponseBody ResultJson getUserById(@RequestBody JSONObject params){

        Integer userId = params.getInteger("userId");

        return this.userService.getUserByUserId(userId);
    }

    @ApiOperation(value = "个人中心获取用户信息 获取登录token")
    @RequestMapping(value = "/v1/getUserDataByUserId", method = RequestMethod.POST)
    @Login
    public @ResponseBody ResultJson getUserDataByUserId(@RequestBody JSONObject params){

        Integer userId = params.getInteger("userId");

        return this.userService.getUserDataByUserId(userId);
    }


    @ApiOperation(value = "个人中心获取用户信息 无需登录")
    @RequestMapping(value = "/v1/getUserDataNoLoginByUserId", method = RequestMethod.POST)
    public @ResponseBody ResultJson getUserDataNoLoginByUserId(@RequestBody JSONObject params){

        Integer userId = params.getInteger("userId");

        return this.userService.getUserDataByUserId(userId);
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/v1/updateUserDataByUserId", method = RequestMethod.POST)
    @Login
    public @ResponseBody ResultJson updateUserDataByUserId(@RequestBody UserDataVO vo){

        if(vo == null || StringUtils.isBlank(vo.getUserName())
                || StringUtils.isBlank(vo.getNickName())
                || (vo.getSex() != 0 && vo.getSex() != 1 && vo.getSex() != 2)) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.userService.updateUserDataByUserId(vo);
    }

    @ApiOperation(value = "保存用户头像")
    @RequestMapping(value = "/v1/updateUserHeadUrl", method = RequestMethod.POST)
    @Login
    public @ResponseBody ResultJson updateUserHeadUrl(@RequestBody JSONObject jo){

        Integer userId = jo.getInteger("userId");

        String imageUrl = jo.getString("imageUrl");

        if(StringUtils.isBlank(imageUrl)) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.userService.updateUserHeadUrl(userId, imageUrl);
    }
}
