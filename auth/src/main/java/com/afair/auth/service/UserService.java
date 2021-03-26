package com.afair.auth.service;

import com.afair.auth.entity.User;

/**
 * @author WangBing
 * @date 2021/3/24 17:48
 */
public interface UserService {
    /**
     * 根据用户登录名查到用户信息
     * @param userName 用户登录名
     * @return
     */
    User findUserByUserName(String userName);
}
