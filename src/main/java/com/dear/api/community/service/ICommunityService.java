package com.dear.api.community.service;

import com.dear.common.bean.ResultJson;

public interface ICommunityService {

    /**
     * 获取基本社区列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResultJson getCommunityList(Integer pageNo, Integer pageSize);
}
