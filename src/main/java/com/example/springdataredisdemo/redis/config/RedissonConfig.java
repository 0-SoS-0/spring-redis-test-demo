package com.example.springdataredisdemo.redis.config;


import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public org.redisson.api.RedissonClient redissonClient() {
        Config config = new Config();
        //配置redissonClient
        config.useSingleServer().setAddress("redis://192.168.204.129").setPassword("123456");
        //创建redissonClient 对象
        return Redisson.create();
    }
}
