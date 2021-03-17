package com.dear.api.community.service;

import com.dear.common.bean.ResultJson;

public interface IProductService {

    ResultJson getProductList(Integer pageNo, Integer pageSize);
}
