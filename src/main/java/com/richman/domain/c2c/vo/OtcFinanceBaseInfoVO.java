package com.richman.domain.c2c.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "FinanceBaseInfoVO", description = "法币资产集合")
public class OtcFinanceBaseInfoVO {

    @ApiModelProperty("币种logo")
    private String logo;//币种logo
    @ApiModelProperty("默认英文名")
    private String defaultEnName;//默认英文名
    @ApiModelProperty("默认中文名")
    private String defaultCnName;//默认中文名
    @ApiModelProperty("币种id")
    private Integer coinId;//币种id
    @ApiModelProperty("可用数量")
    private BigDecimal amount;//可用数量
    @ApiModelProperty("冻结数量")
    private BigDecimal amountFrozen;//冻结数量
    @ApiModelProperty("总数量")
    private BigDecimal sumAmount;//总数量
    @ApiModelProperty("最新成交价")
    private BigDecimal toHkdPrice;//最新成交价
    @ApiModelProperty("排序字段")
    private Integer sort; //排序字段

}
