package com.afair.auth.entity.request;

import lombok.Data;

/**
 * 登录的请求参数
 * @author WangBing
 * @date 2021/3/23 10:54
 */
@Data
public class UserLoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住我
     */
    private boolean rememberMe;
}
