package com.afair.auth.service;

import com.afair.auth.entity.User;
import com.afair.auth.entity.request.UserRegisterRequest;

/**
 * @author WangBing
 * @date 2021/3/24 17:48
 */
public interface UserService {
    /**
     * 根据用户登录名查到用户信息
     *
     * @param userName 用户登录名
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 新增用户
     *
     * @param userRegisterRequest
     * @return
     */
    int insertUser(UserRegisterRequest userRegisterRequest);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 检查用户的密码是否正确
     *
     * @param currentPassword
     * @param password
     * @return
     */
    boolean check(String currentPassword, String password);
}
