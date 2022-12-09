package com.ygh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygh.admin.entity.SysLog;
import com.ygh.admin.entity.vo.LogQueryVo;
import com.ygh.admin.mapper.SysLogMapper;
import com.ygh.admin.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-26
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    //分页条件查询所有系统操作日志
    @Override
    public Map<String, Object> pageLog(Long page, Long limit, LogQueryVo logQueryVo) {
        QueryWrapper<SysLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(logQueryVo.getUsername())) {
            wrapper.like("username", logQueryVo.getUsername());
        }
        if (!StringUtils.isEmpty(logQueryVo.getMethod())) {
            wrapper.like("method", logQueryVo.getMethod());
        }
        if (!StringUtils.isEmpty(logQueryVo.getOperation())) {
            wrapper.like("operation", logQueryVo.getOperation());
        }
        if (!StringUtils.isEmpty(logQueryVo.getIp())) {
            wrapper.like("ip", logQueryVo.getIp());
        }
        if (!StringUtils.isEmpty(logQueryVo.getStart())) {
            wrapper.ge("gmt_create", logQueryVo.getStart());
        }
        if (!StringUtils.isEmpty(logQueryVo.getEnd())) {
            wrapper.le("gmt_modified", logQueryVo.getEnd());
        }
        wrapper.orderByDesc("gmt_create", "gmt_modified");
        Page<SysLog> pageSysLog = new Page<>(page, limit);
        baseMapper.selectPage(pageSysLog, wrapper);
        List<SysLog> records = pageSysLog.getRecords();
        long total = pageSysLog.getTotal();
        long size = pageSysLog.getSize();
        long pages = pageSysLog.getPages();
        long current = pageSysLog.getCurrent();
        boolean hasNext = pageSysLog.hasNext();//是否有下一页
        boolean hasPrevious = pageSysLog.hasPrevious();//是否有下一页
        HashMap<String, Object> map = new HashMap<>();
        map.put("size", size);
        map.put("pages", pages);
        map.put("current", current);
        map.put("total", total);
        map.put("rows", records);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}
