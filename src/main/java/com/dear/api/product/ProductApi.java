package com.dear.api.product;

import com.dear.api.product.service.IProductService;
import com.dear.common.bean.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"产品中心"})
@RestController
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    private IProductService service;

    @ApiOperation(value = "获取基本产品列表",
            notes = "\n* 参数示例：" +
                    "\n* " +
                    "\n* 参数说明 " +
                    "\n* pageNo PageSize " +
                    "\n* ")
    @RequestMapping(value = "/v1/getProductList", method = RequestMethod.POST)
    public ResultJson getProductList(){

        return this.service.getProductListByDel();
    }


}
