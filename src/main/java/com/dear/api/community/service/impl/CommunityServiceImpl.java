package com.dear.api.community.service.impl;

import com.dear.api.community.service.ICommunityService;
import com.dear.common.base.BaseService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.Community;
import com.dear.mapper.CommunityMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl extends BaseService implements ICommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public ResultJson getCommunityList(Integer pageNo, Integer pageSize) {

        //PageHelper.startPage(pageNo, pageSize);
        Integer integer = this.communityMapper.selectCount(null);

        return new ResultJson(ResultCode.OK.getCode(), integer);
    }
}
