package com.afair.auth.controller;

import com.afair.auth.commons.constants.SecurityConstants;
import com.afair.auth.entity.request.UserLoginRequest;
import com.afair.auth.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangBing
 * @date 2021/3/20
 */
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "登录模块")
public class LoginController {
    private final LoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        String token = loginService.createToken(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public ResponseEntity<Void> logout() {
        loginService.removeToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
