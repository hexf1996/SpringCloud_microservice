package com.hexf.springcloud.controller;


import com.hexf.springCloud.entities.Dept;
import com.hexf.springCloud.service.DeptClientFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptConsumerController {

    @Autowired
    private DeptClientFeignService service;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
        return service.add(dept);
    }

    @GetMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
       return service.get(id);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return service.list();
    }

    @RequestMapping(value = "/consume/dept/discovery")
    public Object discovery() {
        return service.discovery();
    }

}
