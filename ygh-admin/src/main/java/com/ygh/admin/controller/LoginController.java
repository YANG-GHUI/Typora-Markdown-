package com.ygh.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ygh.admin.commons.R;
import com.ygh.admin.commons.annotation.Log;
import com.ygh.admin.commons.utils.IpUtil;
import com.ygh.admin.commons.utils.JwtUtils;
import com.ygh.admin.commons.utils.MD5;
import com.ygh.admin.entity.User;
import com.ygh.admin.entity.vo.UserVo;
import com.ygh.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author YangGh
 * @version 1.0
 */
@Api(tags = "登录接口")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Log("用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R login(@RequestBody UserVo userVo, HttpServletRequest request) {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        int count = userService.count(wrapper);
        if (count > 0) {
            String encrypt = MD5.encrypt(password);
            User user = userService.getOne(wrapper);
            if (user.getPassword().equals(encrypt)) {
                String userIp = IpUtil.getIpAddr(request);
                //将用户信息存入redis
                redisTemplate.opsForValue().set("userInfo", user, 24 * 60, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(user.getUsername() + ":userIp", userIp, 24 * 60, TimeUnit.MINUTES);
                String token = JwtUtils.getJwtToken(user.getId(), username);
                return R.ok().data("token", token);
            } else {
                return R.error().message("请检查用户名或密码是否正确");
            }
        }
        return R.error().message("请检查用户名或密码是否正确");
    }

    @ApiOperation("用户信息")
    @GetMapping("/info")
    public R info() {
        //将用户信息从redis中取出
        User userInfo = (User) redisTemplate.opsForValue().get("userInfo");
        if (userInfo != null) {
            return R.ok()
                    .data("roles", "admin")
                    .data("name", userInfo.getUsername())
                    .data("avatar", userInfo.getAvatar());
        }
        return R.error().message("获取用户信息出错，请联系系统管理员处理");
    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public R logout() {
        redisTemplate.opsForValue().set("userInfo", "", 1, TimeUnit.SECONDS);
        return R.ok().data("token", "");
    }
}
