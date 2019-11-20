package com.hexf.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "dept/test")
    public String test() {
        return "Hello, eureka.";
    }

}
