package com.ygh.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangGh
 * @version 1.0
 */
@ApiModel(value = "Log查询对象", description = "日志查询对象封装")
@Data
public class LogQueryVo {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "操作内容")
    private String operation;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "操作ip")
    private String ip;

    @ApiModelProperty(value = "查询开始时间")
    private String start;

    @ApiModelProperty(value = "查询结束时间")
    private String end;
}
