package com.example.springdataredisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class SpringDataRedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Test
    void contextLoads() {
    }


    @Test
    void testString() {
        redisTemplate.opsForValue().set("test:name", "王二狗");
        System.out.println(redisTemplate.opsForValue().get("test:name"));

        redisTemplate.opsForValue().set("name", "称老方");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
