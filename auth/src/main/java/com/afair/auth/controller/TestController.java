package com.afair.auth.controller;

/**
 * @author WangBing
 * @date 2021/2/26 17:18
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/test")
@RestController
@Api(tags = "测试模块")
public class TestController {
    @GetMapping("/task1")
    @ApiOperation("task1")
    public String task1(){
        return "task1";
    }

    @GetMapping("/task2")
    @ApiOperation("task2")
    public String task2(){
        return "task2";
    }

    @GetMapping("/task3")
    @ApiOperation("task3")
    public String task3(){
        return "task3";
    }
}
