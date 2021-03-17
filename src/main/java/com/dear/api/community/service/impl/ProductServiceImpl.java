package com.dear.api.community.service.impl;

import com.dear.api.community.service.IProductService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.Product;
import com.dear.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultJson getProductList(Integer pageNo, Integer pageSize) {

        //PageHelper.startPage(pageNo, pageSize);
        List<Product> list = this.productMapper.selectList(null);

        return new ResultJson(ResultCode.OK.getCode(), list);
    }
}
