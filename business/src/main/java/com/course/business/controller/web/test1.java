package com.course.business.controller.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test1 {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void test() {
        redisTemplate.opsForValue().set("hello","world");
        System.out.println(redisTemplate.opsForValue().get("hello"));
    }
}
