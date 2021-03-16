package com.richman.domain.c2c.vo;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("app获取法币币种手续费VO(TradeFeeVO)")
@Data
public class TradeFeeVO {

    @ApiModelProperty("交易手续费")
    private String tradeFee;

    @ApiModelProperty("保证金")
    private String extraFee;

    @ApiModelProperty("价格精度")
    private Integer priceLimit;

    @ApiModelProperty("数量精度")
    private Integer amountLimit;

    @ApiModelProperty("挂单最小价格限制")
    private BigDecimal priceLessQuantity;
    private String priceLessQuantityStr;

    @ApiModelProperty("挂单最大价格限制")
    private BigDecimal priceMoreQuantity;
    private String priceMoreQuantityStr;

    public String getPriceLessQuantityStr() {
        return StrUtil.toString(this.priceLessQuantity);
    }

    public String getPriceMoreQuantityStr() {
        return StrUtil.toString(this.priceMoreQuantity);
    }
}
