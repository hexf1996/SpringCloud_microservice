package com.hexf.springcloud.task.controller;


import com.hexf.springcloud.task.service.AsyncTask;
import com.hexf.springcloud.task.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private ScheduledService scheduledService;

    @GetMapping(value = "/hello")
    public String hello() {
       asyncTask.sayHello();
       return "success";
    }

    @GetMapping(value = "/hello2")
    public String hello2() {
        scheduledService.sayHello();
        return "success";
    }

}
