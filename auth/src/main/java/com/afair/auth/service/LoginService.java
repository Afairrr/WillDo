package com.afair.auth.service;

import com.afair.auth.entity.request.UserLoginRequest;

/**
 * @author WangBing
 * @date 2021/3/23 11:00
 */
public interface LoginService {
    /**
     * 创建token
     * @param request
     * @return
     */
    String createToken(UserLoginRequest request);

    /**
     * 移除token
     */
    void removeToken();
}
