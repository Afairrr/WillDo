package com.afair.auth.commons.utils;

import com.afair.auth.entity.User;
import com.afair.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.SecurityContext;

/**
 * @author WangBing
 * @date 2021/3/24 17:45
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrentUserUtils {
    private final UserService userService;

    public User getCurrentUser() {
        return userService.findUserByUserName(getUserName());
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
