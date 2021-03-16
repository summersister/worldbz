package com.richman.domain.c2c.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "买/卖下单参数")
public class SaveBuyOrSellTradeOrderDTO {

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Integer userId;
    @ApiModelProperty(value = "买/卖数量", required = true)
    private BigDecimal quantity;
    @ApiModelProperty(value = "广告单ID", required = true)
    private Long advertiseId;
    @ApiModelProperty(value = "当前用户是买家还是卖家 1.买家;2.卖家", required = true)
    private Integer type;

}
