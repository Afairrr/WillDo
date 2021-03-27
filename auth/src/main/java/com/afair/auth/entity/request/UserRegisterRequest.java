package com.afair.auth.entity.request;

import com.afair.auth.entity.User;
import lombok.Data;

/**
 * @author WangBing
 * @date 2021/3/26 22:34
 */
@Data
public class UserRegisterRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String fullName;

    public User toUser() {
        return User.builder().userName(this.username)
                .password(this.password)
                .fullName(this.fullName).build();
    }
}
