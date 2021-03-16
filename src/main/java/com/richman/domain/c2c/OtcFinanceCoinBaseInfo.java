package com.richman.domain.c2c;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "OtcFinanceCoinBaseInfo(法币币种)")
public class OtcFinanceCoinBaseInfo {

    @ApiModelProperty("币种id")
    private Integer id;

    @ApiModelProperty("英文名称")
    private String defaultenname;

    @ApiModelProperty("价格精度")
    private Integer priceLimit;

    @ApiModelProperty("数量精度")
    private Integer amountLimit;

    @ApiModelProperty("图片")
    private String logo;

    @ApiModelProperty("中文名称")
    private String defaultcnname;

    @ApiModelProperty("广告单(买) 最小数量")
    private BigDecimal buyorderlessquantity;
    private String buyorderlessquantityStr;

    @ApiModelProperty("广告单(买) 最大数量")
    private BigDecimal buyordermorequantity;
    private String buyordermorequantityStr;

    @ApiModelProperty("广告单(卖) 最小数量")
    private BigDecimal sellorderlessquantity;
    private String sellorderlessquantityStr;

    @ApiModelProperty("广告单(卖) 最大数量")
    private BigDecimal sellordermorequantity;
    private String sellordermorequantityStr;

    @ApiModelProperty("广告单(买) 最小剩余数量")
    private BigDecimal sellorderlesssurplusquantity;
    private String sellorderlesssurplusquantityStr;

    @ApiModelProperty("广告单(卖) 最小剩余数量")
    private BigDecimal buyorderlesssurplusquantity;
    private String buyorderlesssurplusquantityStr;

    @ApiModelProperty("商家交易买手续费")
    private BigDecimal businesstradebuyfee;
    private String businesstradebuyfeeStr;

    @ApiModelProperty("商家交易卖手续费")
    private BigDecimal businesstradesellfee;
    private String businesstradesellfeeStr;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态（0正常；1禁用；2删除）")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Long createtime;

    @ApiModelProperty("更新时间")
    private Long updatetime;

    @ApiModelProperty("用户交易买手续费")
    private BigDecimal usertradebuyfee;
    private String usertradebuyfeeStr;

    @ApiModelProperty("用户交易卖手续费")
    private BigDecimal usertradesellfee;
    private String usertradesellfeeStr;

    @ApiModelProperty("买广告单最少成交量")
    private BigDecimal buyclinchlessquantity;
    private String buyclinchlessquantityStr;

    @ApiModelProperty("卖广告单最少成交量")
    private BigDecimal sellclinchlessquantity;
    private String sellclinchlessquantityStr;

    @ApiModelProperty("可用数量")
    private BigDecimal amount;
    private String amountStr;

    @ApiModelProperty("冻结数量")
    private BigDecimal amountfrozen;
    private String amountfrozenStr;

    @ApiModelProperty("保证金")
    private String extraFee;

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

    public String getBuyorderlessquantityStr() {
        return this.buyorderlessquantity == null ? null : this.buyorderlessquantity.stripTrailingZeros().toPlainString();
    }

    public String getBuyordermorequantityStr() {
        return this.buyordermorequantity == null ? null : this.buyordermorequantity.stripTrailingZeros().toPlainString();
    }

    public String getSellorderlessquantityStr() {
        return this.sellorderlessquantity == null ? null : this.sellorderlessquantity.stripTrailingZeros().toPlainString();
    }

    public String getSellordermorequantityStr() {
        return this.sellordermorequantity == null ? null : this.sellordermorequantity.stripTrailingZeros().toPlainString();
    }

    public String getSellorderlesssurplusquantityStr() {
        return this.sellorderlesssurplusquantity == null ? null : this.sellorderlesssurplusquantity.stripTrailingZeros().toPlainString();
    }

    public String getBuyorderlesssurplusquantityStr() {
        return this.buyorderlesssurplusquantity == null ? null : this.buyorderlesssurplusquantity.stripTrailingZeros().toPlainString();
    }

    public String getBusinesstradebuyfeeStr() {
        return this.businesstradebuyfee == null ? null : this.businesstradebuyfee.stripTrailingZeros().toPlainString();
    }

    public String getBusinesstradesellfeeStr() {
        return this.businesstradesellfee == null ? null : this.businesstradesellfee.stripTrailingZeros().toPlainString();
    }

    public String getUsertradebuyfeeStr() {
        return this.usertradebuyfee == null ? null : this.usertradebuyfee.stripTrailingZeros().toPlainString();
    }

    public String getUsertradesellfeeStr() {
        return this.usertradesellfee == null ? null : this.usertradesellfee.stripTrailingZeros().toPlainString();
    }

    public String getBuyclinchlessquantityStr() {
        return this.buyclinchlessquantity == null ? null : this.buyclinchlessquantity.stripTrailingZeros().toPlainString();
    }

    public String getSellclinchlessquantityStr() {
        return this.sellclinchlessquantity == null ? null : this.sellclinchlessquantity.stripTrailingZeros().toPlainString();
    }

    public String getAmountStr() {
        return this.amount == null ? null : this.amount.stripTrailingZeros().toPlainString();
    }

    public String getAmountfrozenStr() {
        return this.amountfrozen == null ? null : this.amountfrozen.stripTrailingZeros().toPlainString();
    }
}