package com.afair.auth.entity;

import lombok.Data;

/**
 * @author WangBing
 * @date 2021/3/20
 */
@Data
public class UserRole {
    Long id;
    private User user;
    private Role role;
}
