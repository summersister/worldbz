package com.dear.api.product.service.impl;

import com.dear.api.product.service.IProductService;
import com.dear.common.bean.ResultCode;
import com.dear.common.bean.ResultJson;
import com.dear.domain.Product;
import com.dear.domain.ProductVO;
import com.dear.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultJson getProductListByDel() {

        List<ProductVO> list = this.productMapper.getProductListByDel();

        return new ResultJson(ResultCode.OK.getCode(), list);
    }

    @Override
    public ResultJson getProductList(Integer pageNo, Integer pageSize) {

        List<Product> list = this.productMapper.selectList(null);

        return new ResultJson(ResultCode.OK.getCode(), list);
    }
}
