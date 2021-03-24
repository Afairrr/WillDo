package com.afair.auth.entity;

import com.afair.auth.entity.base.AbstractAuditBase;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WangBing
 * @date 2021/3/23 14:07
 */
@ApiModel(value = "用户和角色的关联表")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_role")
public class UserRole extends AbstractAuditBase {
    /**
     * 角色和用户关联表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色和用户关联表id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 角色id集
     */
    @TableField(value = "role_list")
    @ApiModelProperty(value = "角色id集")
    private String roleList;

    public UserRole(Long id, Long userId, String roleList) {
        this.id = id;
        this.userId = userId;
        this.roleList = roleList;
    }
}