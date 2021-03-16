package com.richman.domain.c2c.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by 2019/05/10 on jack
 */
@Data
@ApiModel("CoinInfoVO(查询币种ID和币种英文名及价格精度和数量精度)")
public class CoinInfoVO {

    @ApiModelProperty("币种id")
    private Integer id;  //币种id

    @ApiModelProperty("英文名称")
    private String defaultEnName;  //英文名称

    @ApiModelProperty("价格精度")
    private Integer priceLimit;     //价格精度

    @ApiModelProperty("数量精度")
    private Integer amountLimit;       //数量精度

}
