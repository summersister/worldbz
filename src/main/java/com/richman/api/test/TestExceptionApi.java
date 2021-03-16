package com.richman.api.test;

import com.richman.api.test.service.ITestExceptionService;
import com.richman.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api(tags = {"测试中心-异常测试"})
@RestController
@RequestMapping("/testOnlineApi")
public class TestExceptionApi {

    @Autowired
    private ITestExceptionService testExceptionService;

    @ApiOperation(value = "testJackAddEnt")
    @RequestMapping(value = "/v1/testJackAddEnt", method = RequestMethod.GET)
    public @ResponseBody
    ResultJson testJackAddEnt() {

        return this.testExceptionService.testJackAddEnt();
    }


}
