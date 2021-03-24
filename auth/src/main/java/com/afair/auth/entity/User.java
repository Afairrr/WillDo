package com.afair.auth.entity;

import com.afair.auth.entity.base.AbstractAuditBase;
import com.afair.auth.enums.RoleTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Joiner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author WangBing
 * @date 2021/3/23 14:05
 */
@ApiModel(value = "用户表")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName(value = "sys_user")
public class User extends AbstractAuditBase {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 用户登录名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户登录名")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "full_name")
    @ApiModelProperty(value = "用户昵称")
    private String fullName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 是否可用(0-可用,1-停用)
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "是否可用(0-可用,1-停用)")
    private Boolean enabled;

    @TableField(exist = false)
    @ApiModelProperty(value = "对应角色信息")
    private UserRole userRoles;

    public List<SimpleGrantedAuthority> getRoles() {
        String roleListStr = userRoles.getRoleList();
        List<String> roleList = Arrays.asList(roleListStr.split(","));
        roleList.forEach(role->roleList.set(roleList.indexOf(role),RoleTypeEnum.getNameById(role)));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        return authorities;
    }
}