package com.hexf.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashbord9001_App {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashbord9001_App.class, args);
    }
}
