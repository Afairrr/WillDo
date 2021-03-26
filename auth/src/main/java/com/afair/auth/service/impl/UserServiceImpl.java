package com.afair.auth.service.impl;

import com.afair.auth.entity.User;
import com.afair.auth.mapper.UserMapper;
import com.afair.auth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WangBing
 * @date 2021/3/24 17:47
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public User findUserByUserName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_name",userName));
    }
}
