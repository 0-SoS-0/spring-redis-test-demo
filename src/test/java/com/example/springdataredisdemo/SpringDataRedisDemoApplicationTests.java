package com.example.springdataredisdemo;

import com.example.springdataredisdemo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class SpringDataRedisDemoApplicationTests {

    Logger logger= LoggerFactory.getLogger(SpringDataRedisDemoApplicationTests.class);

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


    /**
     * 测试redis 序列化对象存取(存在问题需要修改)
     *  优点：     存对象的时候自动转JSON
     *            取数据的时候自动转对象
     *
     *  缺点：     会存入对象的类字节码对象信息（对象路径），占用内存大；但是不存入不行，反序列化的时候不知道是谁
     *
     */
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:test:01", new User("小明", "19"));

        Object o = redisTemplate.opsForValue().get("user:test:01");
        System.out.println(o);
        logger.info("user:test:01 :{}",o);
    }

}
