package com.hexf.springcloud.task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/***
 * 程序启动自动执行任务
 */
@Component
public class AutoStartTask implements CommandLineRunner, ApplicationRunner {

    @Autowired
    private ScheduledService scheduledService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====" + args.length);
        scheduledService.sayHello();
    }

    /**
     * 主程序启动后自动执行该方法，参数为主程序的参数
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(Arrays.toString(args.getSourceArgs()));
    }
}
