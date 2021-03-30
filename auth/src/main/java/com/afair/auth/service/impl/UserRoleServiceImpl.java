package com.afair.auth.service.impl;

import com.afair.auth.entity.Role;
import com.afair.auth.mapper.RoleMapper;
import com.afair.auth.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.afair.auth.mapper.UserRoleMapper;
import com.afair.auth.entity.UserRole;
import com.afair.auth.service.UserRoleService;
/**
 * @author WangBing
 * @date 2021/3/29 14:09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{
    private final UserRoleMapper userRoleMapper;
    private final RoleService roleService;
    @Override
    public List<Role> getRoleByUser(Long userId) {
        UserRole userRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id", userId));
        return roleService.getRolesByIds(userRole.getRoleList());
    }
}
