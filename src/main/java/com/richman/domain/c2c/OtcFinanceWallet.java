package com.richman.domain.c2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("OtcFinanceWallet(法币钱包)")
public class OtcFinanceWallet {

    @ApiModelProperty("钱包id")
    private Long id;

    @ApiModelProperty("用户id")
    private Integer userid;

    @ApiModelProperty("币种id")
    private Integer coinid;

    @ApiModelProperty("可用数量")
    private BigDecimal amount;

    @ApiModelProperty("冻结数量")
    private BigDecimal amountfrozen;

    @ApiModelProperty("创建时间")
    private Long createtime;

    @ApiModelProperty("冻结时间")
    private Long updatetime;

}