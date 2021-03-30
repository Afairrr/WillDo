package com.afair.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.afair.auth.entity.Role;
import com.afair.auth.mapper.RoleMapper;
import com.afair.service.RoleService;
/**
 * @author WangBing
 * @date 2021/3/29 14:00
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}
