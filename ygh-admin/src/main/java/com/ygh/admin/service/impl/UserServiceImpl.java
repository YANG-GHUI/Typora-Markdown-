package com.ygh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ygh.admin.entity.User;
import com.ygh.admin.entity.vo.UserQueryVo;
import com.ygh.admin.mapper.UserMapper;
import com.ygh.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //分页条件查询用户列表
    @Override
    public Map<String, Object> pageUser(Long page, Long limit, UserQueryVo queryVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(queryVo.getName())) {
            wrapper.like("name", queryVo.getName());
        }
        if (!StringUtils.isEmpty(queryVo.getPhone())) {
            wrapper.like("phone", queryVo.getPhone());
        }
        if (!StringUtils.isEmpty(queryVo.getGender())) {
            wrapper.eq("gender", queryVo.getGender());
        }
        if (!StringUtils.isEmpty(queryVo.getAge())) {
            wrapper.eq("age", queryVo.getAge());
        }
        if (!StringUtils.isEmpty(queryVo.getStart())) {
            wrapper.ge("gmt_create", queryVo.getStart());
        }
        if (!StringUtils.isEmpty(queryVo.getEnd())) {
            wrapper.le("gmt_modified", queryVo.getEnd());
        }
        wrapper.orderByDesc("gmt_create", "gmt_modified");
        Page<User> pageUser = new Page<>(page, limit);
        baseMapper.selectPage(pageUser, wrapper);
        List<User> records = pageUser.getRecords();
        long total = pageUser.getTotal();
        long size = pageUser.getSize();
        long pages = pageUser.getPages();
        long current = pageUser.getCurrent();
        boolean hasNext = pageUser.hasNext();//是否有下一页
        boolean hasPrevious = pageUser.hasPrevious();//是否有下一页
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
