package com.dear.api.bbs;

import com.alibaba.fastjson.JSON;
import com.dear.api.bbs.service.IWxUserService;
import com.dear.common.base.BaseApi;
import com.dear.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"微信接口-用户登录"})
@RestController
@RequestMapping("/vx")
public class WxUserApi extends BaseApi {

    @Autowired
    private IWxUserService wxUserService;

    @ApiOperation(value = "",
            notes = "\n* 参数说明：" +
                    "\n* userName:用户名(必填)" +
                    "\n* ")
    @RequestMapping(value = "/v1/onLogin", method = RequestMethod.POST)
    public @ResponseBody ResultJson onLogin(@RequestBody String params){

        String code = JSON.parseObject(params).getString("code");

        return this.wxUserService.onLogin(code);
    }
}
