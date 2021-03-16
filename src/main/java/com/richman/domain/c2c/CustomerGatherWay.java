package com.richman.domain.c2c;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "收款方式")
public class CustomerGatherWay {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userid;

    @ApiModelProperty("收款账户")
    private String gatheraccount;

    @ApiModelProperty("收款账户姓名")
    private String realname;

    @ApiModelProperty("收款方式(1银行卡；2支付宝；3微信)")
    private Integer gatherway;

    @ApiModelProperty("银行名称(限银行卡支付)")
    private String bankname;

    @ApiModelProperty("银行所在地址(限银行卡支付)")
    private String bankaddress;

    @ApiModelProperty("开户行名称(限银行卡支付)")
    private String bankopen;

    @ApiModelProperty("创建时间")
    private Long createtime;

    @ApiModelProperty("修改时间")
    private Long updatetime;

    @ApiModelProperty("是否删除(0未删除；1删除)")
    private Boolean deltag;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("支付宝二维码地址")
    private String alipayqraddress;

    @ApiModelProperty("微信二维码地址")
    private String wechatqraddress;
}