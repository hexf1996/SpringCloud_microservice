package com.hexf.springCloud.service;

import com.hexf.springCloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HystrixFeedbackFactory implements FallbackFactory<DeptClientFeignService>{
    @Override
    public DeptClientFeignService create(Throwable cause) {
        return new DeptClientFeignService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept(id, "Consumer客户端提供的降级信息,此刻服务Provider已经关闭",
                        "no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public Object discovery() {
                return null;
            }
        };
    }
}
