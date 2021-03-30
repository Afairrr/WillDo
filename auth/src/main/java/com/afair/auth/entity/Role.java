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
 * 角色表
 * @author WangBing
 * @date 2021/3/29 14:06
 */
@ApiModel(value = "角色表")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class Role extends AbstractAuditBase {
    /**
     * 角色主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色主键")
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "角色描述")
    private String description;
}