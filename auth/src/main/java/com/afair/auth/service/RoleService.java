package com.afair.auth.service;

import com.afair.auth.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author WangBing
 * @date 2021/3/29 14:06
 */
public interface RoleService extends IService<Role>{

    /**
     * 根据ids获取
     * @param ids 角色id集合
     * @return 角色信息列表
     */
    List<Role> getRolesByIds(String ids);

}
