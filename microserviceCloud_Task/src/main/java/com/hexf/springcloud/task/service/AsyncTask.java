package com.hexf.springcloud.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务
 */
@Service
public class AsyncTask {

    @Async
    public String sayHello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello, Async Method.");
        return "Hello, Async Method.";
    }

}
