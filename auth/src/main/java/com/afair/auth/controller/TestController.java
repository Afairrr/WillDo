package com.afair.auth.controller;

/**
 * @author WangBing
 * @date 2021/2/26 17:18
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/test")
@RestController
public class TestController {
    @GetMapping("/task1")
    public String task1(){
        return "task1";
    }

    @GetMapping("/task2")
    public String task2(){
        return "task2";
    }

    @GetMapping("/task3")
    public String task3(){
        return "task3";
    }
}
