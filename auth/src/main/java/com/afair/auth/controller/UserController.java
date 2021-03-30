package com.afair.auth.controller;

import com.afair.auth.entity.User;
import com.afair.auth.entity.request.UserRegisterRequest;
import com.afair.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangBing
 * @date 2021/3/27 12:19
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "用户管理")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterRequest request){
        userService.insertUser(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
