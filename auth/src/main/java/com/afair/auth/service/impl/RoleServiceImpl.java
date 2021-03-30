package com.afair.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.afair.auth.entity.Role;
import com.afair.auth.mapper.RoleMapper;
import com.afair.auth.service.RoleService;
/**
 * @author WangBing
 * @date 2021/3/29 14:06
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
    private final RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return roleMapper.selectBatchIds(idList);
    }
}
