package com.ygh.admin.service;

import com.ygh.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ygh.admin.entity.vo.UserQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YangGh
 * @since 2022-11-23
 */
public interface UserService extends IService<User> {

    Map<String, Object> pageUser(Long page, Long limit, UserQueryVo queryVo);
}
