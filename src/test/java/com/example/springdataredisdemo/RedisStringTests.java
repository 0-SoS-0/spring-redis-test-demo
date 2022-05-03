package com.example.springdataredisdemo;

import com.alibaba.fastjson.JSONObject;
import com.example.springdataredisdemo.domain.entity.User;
import com.example.springdataredisdemo.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class RedisStringTests {

    Logger logger = LoggerFactory.getLogger(RedisStringTests.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedissonClient redissonClient;


    @Test
    void contextLoads() {
    }


    /**
     * 测试redis 序列化对象存取(存在问题需要修改)
     * 优点：     存对象的时候自动转JSON
     * 取数据的时候自动转对象
     * <p>
     * 缺点：     会存入对象的类字节码对象信息（对象路径），占用内存大；但是不存入不行，反序列化的时候不知道是谁
     * <p>
     * redis 要求统一使用String 类型的 key和 value ，存储对象的时候需要手动序列化转为String，
     */
    @Test
    void testSaveUser() {
        User user = new User("小明", "19");
        String userJson = JSONObject.toJSONString(user);
        redisTemplate.opsForValue().set("user:test:01", userJson);

        String jsonUser = redisTemplate.opsForValue().get("user:test:01");
        User user1 = JSONObject.parseObject(jsonUser, User.class);
        System.out.println(user1);
        logger.info("user:test:01 :{}", user1.toString());
    }

    @Test
    void testHash() {
        redisTemplate.opsForHash().put("hash:test:00", "name", "lihuaming");

        redisTemplate.opsForHash().get("hash:test:00", "name");

        Map<String, String> testMpa = new HashMap<>();
        testMpa.put("patName", "lulu");
        testMpa.put("age", "29");
        testMpa.put("sex", "man");
        redisTemplate.opsForHash().putAll("hash:test:00", testMpa);

        logger.info("key-value {}", redisTemplate.opsForHash().entries("hash:test:00"));
        logger.info("allKeys {}", redisTemplate.opsForHash().keys("hash:test:00"));
        logger.info("key-value {}", redisTemplate.opsForHash().values("hash:test:00"));


    }

    @Test
    void testGenerics() {
        String[] ss = {"sss", "橙武", "房企"};
        Integer[] num = {2, 5, 6, 6, 87};
        Double[] doubles = {2.53, 5.09, 6.0, 6.978, 87.0988};
        Character[] characters = {'t', 'e', 's', 't'};
        redisUtil.printArray(ss, User.class, "tom", "10");
        redisUtil.printArray(num, User.class, "Jhon", "60");
        redisUtil.printArray(doubles, User.class, "王五", "20");
        redisUtil.printArray(characters, User.class, "为之", "50");
    }


    /**
     * 测试Redisson 分布式锁的使用;
     * 使用redis 的setnx（只能存入不存在的key值） 命令也可以作为分布式锁的一种，但是那个需要自己改动的地方较多还存在缺陷
     */
    @Test
    public void testRedisson() {
        //创建对象
        RLock redisLuck = redissonClient.getLock("test:user:10");
        //尝试获取锁，非阻塞式，可重试
        try {
            /**
             * 尝试获取锁
             * 参数含义：
             *  waitTime：重试时间，在指定时间内重试获取锁,
             * leaseTime: 指定时间之后释放锁
             * TimeUnit :释放锁的单位
             */
            boolean tryLock = redisLuck.tryLock(1, 20, TimeUnit.SECONDS);
            //判断获取锁是否成功
            if (tryLock) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
