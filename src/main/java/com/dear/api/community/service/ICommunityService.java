package com.dear.api.community.service;

import com.dear.common.bean.ResultJson;
import com.dear.domain.bbs.vo.CommunityDetailsVO;
import com.dear.domain.bbs.vo.CommunityVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICommunityService {

    /**
     * 获取基本社区列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResultJson<PageInfo<List<CommunityVO>>> getCommunityList(Integer pageNo, Integer pageSize);

    /**
     * 获取社区信息
     *
     * @param id
     * @return
     */
    ResultJson<CommunityDetailsVO> getCommunityById(Integer id);
}
