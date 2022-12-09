package com.ygh.admin.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//自定义异常类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YangException extends RuntimeException {

    private Integer code;//状态码

    private String msg;//异常信息
}
