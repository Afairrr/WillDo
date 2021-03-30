package com.afair.auth.service;

import com.afair.auth.entity.Role;
import com.afair.auth.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author WangBing
 * @date 2021/3/29 14:09
 */
public interface UserRoleService extends IService<UserRole>{
    /**
     * 通过用户id查询对应的角色
     * @param userId 用户id
     * @return 角色信息列表
     */
    List<Role> getRoleByUser(Long userId);
}
