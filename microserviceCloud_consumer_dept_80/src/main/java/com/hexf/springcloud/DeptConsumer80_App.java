package com.hexf.springcloud;

import com.hexf.rule.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
// 对名字为microserviceCloud-Dept的微服务使用自定义的Ribbon负载均衡算法，
// 自定义的负载均衡算法类MyIRule不能放在@ComponentScan扫描的包及其子包下
@RibbonClient(name = "microserviceCloud-Dept", configuration = MyIRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }

}
