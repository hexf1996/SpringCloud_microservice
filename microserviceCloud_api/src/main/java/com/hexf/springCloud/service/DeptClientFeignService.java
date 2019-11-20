package com.hexf.springCloud.service;

import com.hexf.springCloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "microserviceCloud-Dept")
@FeignClient(value = "microserviceCloud-Dept", fallbackFactory = HystrixFeedbackFactory.class)
public interface DeptClientFeignService {

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept);

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);

    @GetMapping(value = "dept/list")
    public List<Dept> list();

    @RequestMapping(value = "/dept/discovery")
    public Object discovery();

}
