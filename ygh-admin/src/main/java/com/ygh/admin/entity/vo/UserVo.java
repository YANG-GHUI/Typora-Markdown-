package com.ygh.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YangGh
 * @version 1.0
 */
@ApiModel(value = "User登录对象", description = "用户登录对象封装")
@Data
public class UserVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;
}
