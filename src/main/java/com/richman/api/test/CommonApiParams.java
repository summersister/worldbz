package com.richman.api.test;/*
package com.richman.api.test;


import com.richman.framework.bean.ResultJson;
import com.richman.framework.util.AesUtil;
import com.richman.framework.util.jwt.JwtHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

*/
/**
 * 测试基本的微服务接口开发
 *//*

@Slf4j
@RestController
@Api(tags = {"开发接口"})
@RequestMapping("/commonParams")
public class CommonApiParams {

    @ApiOperation(value = "将请求的JSON变成加密的params", notes = "{\"id\":1,\"bit\":0,\"createTime\":4,\"str\":\"例子例子\",\"xiaoshu\":4.22222}")
    @RequestMapping(value = "/v1/encryptParams", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson encryptParams(@RequestParam("json") String json) {
        ResultJson ret = new ResultJson();
        ret.setModel(AesUtil.aesEncrypt(json));

        return ret;
    }

    @ApiOperation(value = "将加密的params进行解密", notes = "返回解密结果")
    @RequestMapping(value = "/v1/decryptParams", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson decryptParams(@RequestParam("params") String params) {
        ResultJson ret = new ResultJson();
        ret.setModel(AesUtil.aesDecryptCbc(params));

        return ret;
    }

    @ApiOperation(value = "将userid生产token", notes = "返回token")
    @RequestMapping(value = "/v1/createToken", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson createToken(@RequestParam("userId") String userId) {
        ResultJson ret = new ResultJson();
        ret.setModel(JwtHelper.createJWT(userId));
        return ret;
    }

}
*/
