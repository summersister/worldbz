package com.dear.api.community;

import com.alibaba.fastjson.JSONObject;
import com.dear.api.community.service.ICommunityService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"社区中心"})
@RestController
@RequestMapping("/community")
public class CommunityApi {

    @Autowired
    private ICommunityService service;

    @ApiOperation(value = "获取基本社区列表",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getCommunityList", method = RequestMethod.POST)
    public ResultJson getCommunityList(@RequestBody JSONObject jo){

        Integer pageNo = jo.getInteger("pageNo");

        Integer pageSize = jo.getInteger("pageSize");

        if(pageNo == null || pageSize == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getCommunityList(pageNo, pageSize);
    }


}
