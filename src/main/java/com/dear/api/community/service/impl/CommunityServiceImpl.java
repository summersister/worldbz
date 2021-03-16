package com.dear.api.community.service.impl;

import com.dear.api.community.service.ICommunityService;
import com.dear.common.annotaion.MultipleTransaction;
import com.dear.common.base.BaseService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.vo.CommunityDetailsVO;
import com.dear.domain.bbs.vo.CommunityVO;
import com.dear.mapper.bbs.CommunityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl extends BaseService implements ICommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson<PageInfo<List<CommunityVO>>> getCommunityList(Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);

        List<CommunityVO> list = this.communityMapper.getCommunityList();

        return new ResultJson(ResultCode.OK.getCode(), new PageInfo(list));
    }

    @Override
    @MultipleTransaction({"bbsTM"})
    public ResultJson<CommunityDetailsVO> getCommunityById(Integer id) {

        CommunityDetailsVO vo = this.communityMapper.getCommunityById(id);

        return new ResultJson(ResultCode.OK.getCode(), vo);
    }
}
