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
    USER("1", "USER", "用户"),
    /**
     * 临时用户
     */
    TEMP_USER("2", "TEMP_USER", "临时用户"),
    /**
     * 管理者
     */
    MANAGER("3", "MANAGER", "管理者"),
    /**
     * 超级管理员
     */
    ADMIN("4", "ADMIN", "超级管理员");

    private final String roleId;
    private final String name;
    private final String desc;

    RoleTypeEnum(String roleId, String name, String desc) {
        this.roleId = roleId;
        this.name = name;
        this.desc = desc;
    }

    public static String getNameById(String roleId) {
        RoleTypeEnum[] roleTypeEnums = RoleTypeEnum.values();
        for (RoleTypeEnum roleTypeEnum : roleTypeEnums) {
            if (roleTypeEnum.roleId.equals(roleId)) {
                return roleTypeEnum.name;
            }
        }
        return null;
    }
}
