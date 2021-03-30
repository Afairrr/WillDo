package com.afair.auth.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author WangBing
 * @date 2021/3/29 15:49
 */
@Data
public class UserUpdateRequest {
    /**
     * 用户名
     */
    @NotBlank
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String fullName;

    /**
     * 是否可用
     */
    private Boolean enabled;
}
