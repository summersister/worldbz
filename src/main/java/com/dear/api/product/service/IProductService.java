package com.dear.api.product.service;

import com.dear.common.bean.ResultJson;

public interface IProductService {

    ResultJson getProductListByDel();

    ResultJson getProductList(Integer pageNo, Integer pageSize);
}
