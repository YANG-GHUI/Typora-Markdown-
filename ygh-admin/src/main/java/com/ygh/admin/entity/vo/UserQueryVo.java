package com.ygh.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangGh
 * @version 1.0
 */
@ApiModel(value = "User查询对象", description = "用户查询对象封装")
@Data
public class UserQueryVo {
    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "性别（0：女，1：男）")
    private Integer gender;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    private Boolean isDisabled;

    @ApiModelProperty(value = "查询开始时间")
    private String start;

    @ApiModelProperty(value = "查询结束时间")
    private String end;
}
