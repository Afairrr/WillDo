package com.afair.auth.service;

import com.afair.auth.entity.User;
import com.afair.auth.entity.request.UserRegisterRequest;
import com.afair.auth.entity.request.UserUpdateRequest;

/**
 * @author WangBing
 * @date 2021/3/24 17:48
 */
public interface UserService {
    /**
     * 根据用户登录名查到用户信息
     *
     * @param userName 用户登录名
     * @return 用户信息
     */
    User findUserByUserName(String userName);

    /**
     * 新增用户
     *
     * @param userRegisterRequest 注册用户请求参数
     */
    void insertUser(UserRegisterRequest userRegisterRequest);

    /**
     * 更新用户
     *
     * @param userUpdateRequest 更新用户请求参数
     */
    void updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 检查用户的密码是否正确
     *
     * @param currentPassword 当前的密码
     * @param password 存储的密码
     * @return 是否正确
     */
    boolean check(String currentPassword, String password);
}
