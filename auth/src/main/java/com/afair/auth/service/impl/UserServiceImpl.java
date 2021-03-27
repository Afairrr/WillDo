package com.afair.auth.service.impl;

import com.afair.auth.entity.User;
import com.afair.auth.entity.request.UserRegisterRequest;
import com.afair.auth.mapper.UserMapper;
import com.afair.auth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author WangBing
 * @date 2021/3/24 17:47
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USERNAME = "username:";

    @Override
    public User findUserByUserName(String userName) {
        Optional<User> user = Optional.ofNullable(userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName)));
        return user.orElseThrow(() -> new UsernameNotFoundException("userName is not exist"));
    }

    @Override
    public int insertUser(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.toUser();
        findUserByUserName(user.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public boolean check(String currentPassword, String password) {
        return bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
