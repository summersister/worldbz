package com.dear.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应信息类
 * @author Neo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("结果集")
public class ResultJson<T> {

    @ApiModelProperty("业务code")
    private Integer code;

    @ApiModelProperty("业务数据")
    private T model;

}
