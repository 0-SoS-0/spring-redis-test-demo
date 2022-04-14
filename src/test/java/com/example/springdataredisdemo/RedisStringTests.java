package com.example.springdataredisdemo;

import com.alibaba.fastjson.JSONObject;
import com.example.springdataredisdemo.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class RedisStringTests {

    Logger logger= LoggerFactory.getLogger(RedisStringTests.class);

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    void contextLoads() {
    }


    /**
     * 测试redis 序列化对象存取(存在问题需要修改)
     *  优点：     存对象的时候自动转JSON
     *            取数据的时候自动转对象
     *
     *  缺点：     会存入对象的类字节码对象信息（对象路径），占用内存大；但是不存入不行，反序列化的时候不知道是谁
     *
     * redis 要求统一使用String 类型的 key和 value ，存储对象的时候需要手动序列化转为String，
     */
    @Test
    void testSaveUser(){
        User user = new User("小明", "19");
        String userJson=JSONObject.toJSONString(user);
        redisTemplate.opsForValue().set("user:test:01", userJson);

        String  jsonUser = redisTemplate.opsForValue().get("user:test:01");
        User user1 = JSONObject.parseObject(jsonUser, User.class);
        System.out.println(user1);
        logger.info("user:test:01 :{}",user1.toString());
    }

    @Test
    void testHash(){
        redisTemplate.opsForHash().put("hash:test:00", "name", "lihuaming");

        redisTemplate.opsForHash().get("hash:test:00", "name");

        Map<String,String> testMpa=new HashMap<>();
        testMpa.put("patName","lulu" );
        testMpa.put("age","29" );
        testMpa.put("sex","man" );
        redisTemplate.opsForHash().putAll("hash:test:00", testMpa);

        logger.info("key-value {}", redisTemplate.opsForHash().entries("hash:test:00"));
        logger.info("allKeys {}", redisTemplate.opsForHash().keys("hash:test:00"));
        logger.info("key-value {}", redisTemplate.opsForHash().values("hash:test:00"));


    }

}
