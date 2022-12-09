package com.ygh.admin.service;

import com.ygh.admin.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ygh.admin.entity.vo.LogQueryVo;

import java.util.Map;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-26
 */
public interface SysLogService extends IService<SysLog> {

    Map<String ,Object> pageLog(Long page, Long limit, LogQueryVo logQueryVo);
}
