package com.richman.domain.c2c.vo;

import com.richman.common.util.tools.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "CurrentAdvertiseVO(c2c广告单视图对象)")
public class CurrentAdvertiseVO {

    @ApiModelProperty(value = "广告单号")
    private Long id;

    @ApiModelProperty(value = "交易币种ID")
    private Integer coinId;

    @ApiModelProperty(value = "交易币种名称")
    private String coinName;

    @ApiModelProperty(value = "交易类型：1买；2卖")
    private Integer tradeType;

    @ApiModelProperty(value = "交易状态")
    private Integer tradeStatus;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    private String priceStr;

    @ApiModelProperty(value = "挂单数量")
    private BigDecimal orderQuantity;
    private String orderQuantityStr;

    @ApiModelProperty(value = "已成交数量")
    private BigDecimal finishQuantity;
    private String finishQuantityStr;

    @ApiModelProperty(value = "处理中数量")
    private BigDecimal waitDisposeQuantity;
    private String waitDisposeQuantityStr;

    @ApiModelProperty(value = "是否显示")
    private Boolean isHide;

    @ApiModelProperty(value = "单笔最大购买限制")
    private BigDecimal singleMaxAmountLimit;
    private String singleMaxAmountLimitStr;

    /**
     * @Authoer : peter
     * @Decription : 新增属性,交易区间和挂单时间,因为objectUtils.isBlank会校验所有属性,有一个为null则返回null所以设置初始值
     * @Date : 2018/6/29
     */
    @ApiModelProperty(value = "最低交易数量")
    private BigDecimal minQuantity = new BigDecimal("0");
    private String minQuantityStr;

    @ApiModelProperty(value = "挂单时间")
    private Long createTime = 0L;

    @ApiModelProperty(value = "价格精度")
    private Integer priceLimit;

    @ApiModelProperty(value = "数量精度")
    private Integer amountLimit;

    public String getPriceStr() {
        return this.price == null ? null : this.price.stripTrailingZeros().toPlainString();
    }

    public String getOrderQuantityStr() {
        return this.orderQuantity == null ? null : this.orderQuantity.stripTrailingZeros().toPlainString();
    }

    public String getFinishQuantityStr() {
        return this.finishQuantity == null ? null : this.finishQuantity.stripTrailingZeros().toPlainString();
    }

    public String getWaitDisposeQuantityStr() {
        return this.waitDisposeQuantity == null ? null : this.waitDisposeQuantity.stripTrailingZeros().toPlainString();
    }

    public String getMinQuantityStr() {
        return this.minQuantity == null ? null : this.minQuantity.stripTrailingZeros().toPlainString();
    }

    public String getSingleMaxAmountLimitStr() {
        return StrUtil.toString(this.singleMaxAmountLimit);
    }
}
