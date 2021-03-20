package com.afair.auth.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangBing
 * @date 2021/3/20
 */
@Data
public class Role {
    private Long id;
    private String name;
    private String description;
    private List<UserRole> userRoles = new ArrayList<>();
}
