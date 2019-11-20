package com.hexf.springcloud.configBeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule getRule() {
//        return new RandomRule();  // 切换负载均衡算法：由随机替换默认的轮循算法
        return new RoundRobinRule();  // 切换负载均衡算法：由随机替换默认的轮循算法
    }

}
