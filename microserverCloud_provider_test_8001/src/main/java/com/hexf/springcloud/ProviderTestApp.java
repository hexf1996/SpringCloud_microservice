package com.hexf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderTestApp {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTestApp.class, args);
    }

}
