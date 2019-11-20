package com.hexf.springcloud.controller;

import com.hexf.springCloud.entities.Dept;
import com.hexf.springcloud.service.DeptService;
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
    public Dept get(@PathVariable("id") Long id) {
        return deptService.get(id);
    }

    @GetMapping(value = "dept/list")
    public List<Dept> list() {
        return deptService.list();
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

}
