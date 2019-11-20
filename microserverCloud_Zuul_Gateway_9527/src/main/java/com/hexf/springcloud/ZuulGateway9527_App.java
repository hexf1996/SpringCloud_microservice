package com.hexf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulGateway9527_App {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGateway9527_App.class, args);
    }

}
