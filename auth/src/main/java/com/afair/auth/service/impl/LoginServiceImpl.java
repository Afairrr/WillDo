package com.afair.auth.service.impl;

import com.afair.auth.commons.constants.RedisConstants;
import com.afair.auth.commons.utils.CurrentUserUtils;
import com.afair.auth.commons.utils.JwtTokenUtils;
import com.afair.auth.entity.JwtUser;
import com.afair.auth.entity.User;
import com.afair.auth.entity.UserRole;
import com.afair.auth.entity.request.UserLoginRequest;
import com.afair.auth.service.LoginService;
import com.afair.auth.service.UserRoleService;
import com.afair.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

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
    private final CurrentUserUtils currentUserUtils;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    public String createToken(UserLoginRequest request) {
        User findUser = userService.findUserByUserName(request.getUsername());
        if (!userService.check(findUser.getPassword(), request.getPassword())) {
            throw new BadCredentialsException("The username or password is not correct");
        }
        User user = User.builder()
                .id(findUser.getId())
                .userName(request.getUsername())
                .password(request.getPassword())
                .roles(userRoleService.getRoleByUser(findUser.getId()))
                .build();
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }
        List<String> authorizations = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorizations, request.isRememberMe());
        redisTemplate.opsForValue().set(RedisConstants.USER_KEY + user.getId().toString(), token);
        return token;
    }

    @Override
    public void removeToken() {
        redisTemplate.delete(RedisConstants.USER_KEY + currentUserUtils.getCurrentUser().getId());
    }
}
