package com.hexf.springcloud.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /***
     *  second minute hour day Month Week
     * "0 * * * * MON-FRI"------表示周一到周五每分钟启动一次任务
     */
//    @Scheduled(cron = "0 * * * * MON-FRI")
//    @Scheduled(cron = "0,1,2,3 * * * * MON-SAT")
    @Scheduled(cron = "0/5 * * * * MON-SAT")  // 步长，每隔5s执行一次
    public void sayHello() {
        System.out.println("Hello, Scheduled Task.");
    }

}
