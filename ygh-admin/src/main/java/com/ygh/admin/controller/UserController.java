package com.ygh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ygh.admin.commons.R;
import com.ygh.admin.commons.annotation.Log;
import com.ygh.admin.commons.utils.MD5;
import com.ygh.admin.entity.User;
import com.ygh.admin.entity.vo.UserQueryVo;
import com.ygh.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YangGh
 * @since 2022-11-23
 */
@Api(tags = "用户管理接口")
@RestController
@CrossOrigin
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有用户列表")
    @GetMapping
    public R getAllUser() {
        List<User> list = userService.list(null);
        return R.ok().data("users", list);
    }

    @ApiOperation("根据id查询用户信息")
    @GetMapping("/{id}")
    public R getUserById(@ApiParam(name = "id", value = "用户id")
                         @PathVariable String id) {
        User user = userService.getById(id);
        user.setPassword("");
        return R.ok().data("user", user);
    }

    @ApiOperation("分页条件查询所有用户列表")
    @PostMapping("/pageUser/{page}/{limit}")
    public R pageUser(@ApiParam(name = "page", value = "当前页")
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页数")
                      @PathVariable Long limit,
                      @ApiParam(name = "queryVo", value = "用户查询对象")
                      @RequestBody UserQueryVo queryVo) {
        Map<String, Object> map = userService.pageUser(page, limit, queryVo);
        return R.ok().data(map);
    }

    @Log("添加用户")
    @ApiOperation("添加用户")
    @PostMapping
    public R addUser(@ApiParam(name = "user", value = "用户对象")
                     @RequestBody User user) {
        String password = user.getPassword();
        String encrypt = MD5.encrypt(password);
        user.setPassword(encrypt);
        boolean flag = userService.save(user);
        return flag ? R.ok() : R.error();
    }

    @Log("删除用户")
    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public R removeUser(@ApiParam(name = "id", value = "用户id")
                        @PathVariable String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        boolean flag = userService.remove(wrapper);
        return flag ? R.ok() : R.error();
    }

    @Log("修改用户")
    @ApiOperation("修改用户")
    @PutMapping
    public R updateUser(@ApiParam(name = "user", value = "用户对象")
                        @RequestBody User user) {
        String password = user.getPassword();
        String encrypt = MD5.encrypt(password);
        user.setPassword(encrypt);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", user.getId());
        boolean flag = userService.update(user, wrapper);
        return flag ? R.ok() : R.error();
    }
}

