package com.dear.api.bbs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dear.api.bbs.service.IUserLoginService;
import com.dear.common.annotaion.Login;
import com.dear.common.base.BaseApi;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.User;
import com.dear.domain.bbs.vo.UserRegVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = {"用户中心-用户登录"})
@RestController
@RequestMapping("/customer/userLogin")
public class UserLoginApi extends BaseApi {

    @Autowired
    private IUserLoginService service;

    @ApiOperation(value = "校验用户名是否存在",
            notes = "\n* 参数说明：" +
                    "\n* userName:用户名(必填)" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* 存在true 不存在false")
    @RequestMapping(value = "/v1/userNameExists", method = RequestMethod.POST)
    @Login
    public @ResponseBody ResultJson userNameExists(@RequestBody JSONObject jo){

        Integer userId = jo.getInteger("userId");

        Integer type = jo.getInteger("type");

        String name = jo.getString("name");

        if(StringUtils.isBlank(name) || (type != null && type != 0 && type != 1)){

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.userNameExists(userId, type, name);
    }

    @ApiOperation(value = "用户注册",
            notes = "\n* 参数说明：" +
                    "\n* userName:用户名(必填)" +
                    "\n* passWord:密码(必填)" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* ")
    @RequestMapping(value = "/v1/userRegister", method = RequestMethod.POST)
    public @ResponseBody ResultJson userRegister(
            @RequestBody UserRegVO regVo, HttpServletRequest request){

        if(regVo == null
                || StringUtils.isBlank(regVo.getUserName())
                || StringUtils.isBlank(regVo.getPassWord())){

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.userRegister(regVo, super.base.getIp(request));
    }

    @ApiOperation(value = "用户登录",
            notes = "\n* 参数说明：" +
                    "\n* userName:用户名(必填)" +
                    "\n* passWord:密码(必填)" +
                    "\n* " +
                    "\n* 接口返回：" +
                    "\n* ")
    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public @ResponseBody ResultJson login(
            @RequestBody String params, HttpServletRequest request){

        User userDto = JSON.parseObject(params, User.class);

        if(userDto == null
                || StringUtils.isBlank(userDto.getUserName())
                || StringUtils.isBlank(userDto.getPassWord())){

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.login(userDto, super.base.getIp(request));
    }
}
