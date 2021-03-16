package com.richman.domain.c2c.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by 2019/04/28 jack
 */
@Data
@ApiModel(value = "货币信息和用户钱包余额视图")
public class OtcCoinAndWalletVO {

    @ApiModelProperty(value = "币种ID")
    private Integer id;

    @ApiModelProperty(value = "币种英文名")
    private String defaultEnName;

    @ApiModelProperty(value = "可用余额")
    private String amount;

    @ApiModelProperty(value = "冻结余额")
    private String amounFrozen;

    public OtcCoinAndWalletVO(Integer id, String defaultEnName, String amount, String amounFrozen) {
        this.id = id;
        this.defaultEnName = defaultEnName;
        this.amount = amount == null ? "0.00" : amount;
        this.amounFrozen = amounFrozen == null ?"0.00" : amounFrozen;
    }

}
