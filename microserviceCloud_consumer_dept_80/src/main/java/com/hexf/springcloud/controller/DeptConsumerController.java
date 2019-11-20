package com.hexf.springcloud.controller;


import com.hexf.springCloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://microserviceCloud-Dept";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @GetMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    @RequestMapping(value = "/consume/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }

}
