package com.hexf.springcloud;


import com.hexf.springcloud.task.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TaskApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskApp.class, args);
    }

}
