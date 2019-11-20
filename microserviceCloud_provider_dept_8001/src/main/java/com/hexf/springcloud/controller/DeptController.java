package com.hexf.springcloud.controller;

import com.hexf.springCloud.entities.Dept;
import com.hexf.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    private final String serverName = "microserviceCloud-dept";

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "exceptionHystrix")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
        return dept;
    }

    @GetMapping(value = "dept/list")
    public List<Dept> list() {
        return deptService.list();
    }

    @GetMapping(value = "dept/test")
    public String test() {
        return "Hello, eureka.";
    }


    @GetMapping(value = "dept/discovery")
    public Object disvovery() {
        List<String> list = discoveryClient.getServices();
        System.out.println("===" + list.toString());

        List<ServiceInstance> tempList = discoveryClient.getInstances(serverName);
        for (ServiceInstance svr : tempList) {
            System.out.println("ip:" + svr.getHost() + "; port:" + svr.getPort() + "; uri:" + svr.getUri());
        }

        return discoveryClient;
    }

    public Dept exceptionHystrix(@PathVariable("id") Long id) {
        return new Dept(id, "No this data", "NO this data in database.");
    }

}
