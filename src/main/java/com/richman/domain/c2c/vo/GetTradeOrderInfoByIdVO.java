package com.richman.domain.c2c.vo;

import com.richman.domain.c2c.TradeCreditworthiness;
import com.richman.domain.c2c.TradeOrderInfo;
import com.richman.domain.c2c.dto.ChatContentDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "根据主键ID查询交易订单返回数据VO")
public class GetTradeOrderInfoByIdVO {

    @ApiModelProperty(value = "交易订单视图对象")
    private TradeOrderInfo order;

    @ApiModelProperty(value = "环信账户视图对象")
    private IMAccountVO im;

    @ApiModelProperty(value = "聊天记录")
    private List<ChatContentDTO> chatRecord;

    @ApiModelProperty(value = "信誉")
    private TradeCreditworthiness otherPart;

    @ApiModelProperty(value = "信誉视图对象")
    private CustomerUserVO userInfo;

    @ApiModelProperty("交易币种名")
    private String coinName;

    @ApiModelProperty(value = "交易类型")
    private Integer tradeType;

    @ApiModelProperty(value = "价格精度")
    private Integer priceLimit;

    @ApiModelProperty(value = "数量精度")
    private Integer amountLimit;

}
