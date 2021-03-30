package com.afair.auth.enums;

import lombok.Getter;

/**
 * @author WangBing
 * @date 2021/3/23 14:45
 */
@Getter
public enum RoleTypeEnum {
    /**
     * 用户
     */
    USER("USER", "用户"),
    /**
     * 临时用户
     */
    TEMP_USER("TEMP_USER", "临时用户"),
    /**
     * 管理者
     */
    MANAGER("MANAGER", "管理者"),
    /**
     * 超级管理员
     */
    ADMIN("ADMIN", "超级管理员");

    private final String name;
    private final String desc;

    RoleTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public static String getNameById(String name) {
        RoleTypeEnum[] roleTypeEnums = RoleTypeEnum.values();
        for (RoleTypeEnum roleTypeEnum : roleTypeEnums) {
            if (roleTypeEnum.name.equals(name)) {
                return roleTypeEnum.name;
            }
        }
        return null;
    }
}
