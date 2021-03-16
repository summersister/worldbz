package com.richman.domain.c2c.vo;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "OtcFinanceWalletVO(钱包and币种VO)")
public class OtcFinanceWalletVO implements Serializable{

    @ApiModelProperty(value = "钱包ID")
    private Long id;

    @ApiModelProperty(value = "币种ID")
    private Integer coinId;

    @ApiModelProperty(value = "可用余额")
    private String amount;

    @ApiModelProperty(value = "冻结余额")
    private String amountFrozen;

    @ApiModelProperty(value = "币种英文名称")
    private String defaultEnName;

    @ApiModelProperty(value = "可用加冻结")
    private BigDecimal sumAmount;

    @ApiModelProperty(value = "币种中文名称")
    private String defaultCnName;

    @ApiModelProperty(value = "LOGO")
    private String logo;

    @ApiModelProperty(value = "商户买单手续费")
    private String businessTradeBuyFee;

    @ApiModelProperty(value = "商户卖单手续费")
    private String businessTradeSellFee;

    @ApiModelProperty("折合 人民数量")
    private BigDecimal rmbAmount;
    private String rmbAmountStr;

    public String getRmbAmountStr() {
        return StrUtil.toString(this.rmbAmount);
    }

}