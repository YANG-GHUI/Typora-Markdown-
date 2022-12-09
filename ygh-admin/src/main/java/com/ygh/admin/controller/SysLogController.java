package com.ygh.admin.controller;


import com.ygh.admin.commons.R;
import com.ygh.admin.entity.SysLog;
import com.ygh.admin.entity.vo.LogQueryVo;
import com.ygh.admin.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author YangGh
 * @since 2022-11-26
 */
@Api(tags = "系统日志管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/syslog")
public class SysLogController {

    @Autowired
    private SysLogService logService;

    @ApiOperation("分页条件查询所有日志")
    @PostMapping("/{page}/{limit}")
    public R pageConditionalLog(@ApiParam(name = "page", value = "当前页")
                                @PathVariable Long page,
                                @ApiParam(name = "limit", value = "每页显示数")
                                @PathVariable Long limit,
                                @ApiParam(name = "logQuery", value = "日志查询对象")
                                @RequestBody LogQueryVo logQueryVo) {
        Map<String, Object> map = logService.pageLog(page, limit, logQueryVo);
        return R.ok().data(map);
    }

    @ApiOperation("根据id删除系统操作日志")
    @DeleteMapping("/{id}")
    public R delLogById(@ApiParam(name = "id", value = "日志id")
                        @PathVariable String id) {
        boolean flag = logService.removeById(id);
        return flag ? R.ok() : R.error().message("删除日志失败！");
    }

    @ApiOperation("根据id查询系统操作日志")
    @GetMapping("/{id}")
    public R getLogById(@ApiParam(name = "id", value = "日志id")
                        @PathVariable String id) {
        SysLog log = logService.getById(id);
        return R.ok().data("log", log);
    }

    @ApiOperation("批量删除系统操作日志")
    @DeleteMapping
    public R delSysLog(@RequestBody List<String> ids) {
        boolean flag = logService.removeByIds(ids);
        return flag ? R.ok() : R.error().message("删除日志失败！");
    }
}

