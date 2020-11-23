package com.course.business.controller.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test1 {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void test() {
        redisTemplate.setEnableTransactionSupport(true);// // 开启事务支持，在同一个 Connection 中执行命令
        redisTemplate.multi();//开启事务

        try {
           /* int i = 1/0;*/
            Set<String> keys = redisTemplate.keys("*");
            System.out.println(keys);
            redisTemplate.delete(keys);
            redisTemplate.opsForValue().set("hello","world");
            redisTemplate.opsForValue().set("zhang","lujun");
            System.out.println(redisTemplate.opsForValue().get("zhang"));
            redisTemplate.exec();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
}
