package com.richman.domain.c2c.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "信誉视图对象")
public class CustomerUserVO {

    @ApiModelProperty(value = "用户ID")
    private Integer id;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "国家码")
    private String countryCode;
    @ApiModelProperty(value = "邮箱地址")
    private String email;

}
