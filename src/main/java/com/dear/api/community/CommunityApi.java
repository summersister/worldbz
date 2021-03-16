package com.dear.api.community;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dear.api.community.service.ICommunityService;
import com.dear.common.base.BaseApi;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.vo.CommunityDetailsVO;
import com.dear.domain.bbs.vo.CommunityVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"社区中心"})
@RestController
@RequestMapping("/community")
public class CommunityApi extends BaseApi {

    @Autowired
    private ICommunityService service;

    @ApiOperation(value = "获取基本社区列表",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getCommunityList", method = RequestMethod.POST)
    public ResultJson<PageInfo<List<CommunityVO>>> getCommunityList(@RequestBody JSONObject jo){

        Integer pageNo = jo.getInteger("pageNo");

        Integer pageSize = jo.getInteger("pageSize");

        if(pageNo == null || pageSize == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getCommunityList(pageNo, pageSize);
    }

    @ApiOperation(value = "获取社区信息",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* id " +
                    "\n* ")
    @RequestMapping(value = "/v1/getCommunityById", method = RequestMethod.POST)
    public ResultJson<CommunityDetailsVO> getCommunityById(@RequestBody String params){

        JSONObject jo = JSON.parseObject(params);

        Integer id = jo.getInteger("id");

        if(id == null) {

            return new ResultJson(ResultCode.ERROR.getCode(), null);
        }

        return this.service.getCommunityById(id);
    }




}
