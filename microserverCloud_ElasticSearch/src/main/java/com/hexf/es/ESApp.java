package com.hexf.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 *
 *  两种方式：
 *     a. org.springframework.boot.autoconfigure.elasticsearch.jest
 *        默认不支持
 *        配置io.searchbox.client.JestClient
 *     b.org.springframework.boot.autoconfigure.data.elasticsearch
 *        设置Client ，设置clusterNodes，以及clientName
 *        ElasticsearchTemplate
 *        org.springframework.data.elasticsearch.repository.ElasticsearchRepository接口定义增删改查等基本操作
 *
 */
@SpringBootApplication
public class ESApp {

    public static void main(String[] args) {
        SpringApplication.run(ESApp.class, args);
    }
}
