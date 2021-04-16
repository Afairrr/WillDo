package com.afair.auth.service.impl;

import com.afair.auth.entity.Role;
import com.afair.auth.entity.User;
import com.afair.auth.entity.UserRole;
import com.afair.auth.entity.request.UserRegisterRequest;
import com.afair.auth.entity.request.UserUpdateRequest;
import com.afair.auth.enums.RoleTypeEnum;
import com.afair.auth.mapper.RoleMapper;
import com.afair.auth.mapper.UserMapper;
import com.afair.auth.mapper.UserRoleMapper;
import com.afair.auth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ImmutableMap;
import exceptions.UserNameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author WangBing
 * @date 2021/3/24 17:47
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USERNAME = "username:";

    @Override
    public User findUserByUserName(String userName) {
        Optional<User> user = Optional.ofNullable(userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName)));
        return user.orElseThrow(() -> new UsernameNotFoundException("userName is not exist"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.toUser();
        User findUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if(findUser!=null){
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME,findUser));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        //默认注册的角色为用户
        Role userRole = roleMapper.selectOne(new QueryWrapper<Role>().eq("name", RoleTypeEnum.USER.getName()));
        userRoleMapper.insert(new UserRole(user.getId(),userRole.getId().toString()));
    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        User user = findUserByUserName(userUpdateRequest.getUsername());
        if(ObjectUtils.isEmpty(userUpdateRequest.getFullName())){
            user.setFullName(userUpdateRequest.getFullName());
        }
        if(ObjectUtils.isEmpty(userUpdateRequest.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        if(ObjectUtils.isEmpty(userUpdateRequest.getEnabled())){
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        userMapper.updateById(user);
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
