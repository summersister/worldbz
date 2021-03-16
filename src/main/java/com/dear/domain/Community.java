package com.dear.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("community")
public class Community {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "head_url")
    private String headUrl;

    @TableField(value = "del")
    private boolean del;

    @TableField(value = "create_time")
    private Long createTime;

    @TableField(value = "update_time")
    private Long updateTime;
}