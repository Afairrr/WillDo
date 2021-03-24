package com.afair.auth.service.impl;

import com.afair.auth.commons.constants.RedisConstants;
import com.afair.auth.commons.utils.JwtTokenUtils;
import com.afair.auth.entity.JwtUser;
import com.afair.auth.entity.User;
import com.afair.auth.entity.UserRole;
import com.afair.auth.entity.request.UserLoginRequest;
import com.afair.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangBing
 * @date 2021/3/23 11:00
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {
    private final StringRedisTemplate redisTemplate;
    @Override
    public String createToken(UserLoginRequest request) {
        User user = User.builder()
                .id(1L)
                .userName(request.getUsername())
                .password(request.getPassword())
                .userRoles(new UserRole(null,null,"1"))
                .build();
        JwtUser jwtUser = new JwtUser(user);
        List<String> authorizations = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorizations, request.isRememberMe());
        redisTemplate.opsForValue().set(RedisConstants.USER_KEY +user.getId().toString(),token);
        return token;
    }
}
