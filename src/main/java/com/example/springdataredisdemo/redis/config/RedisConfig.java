package com.example.springdataredisdemo.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;


/**
 *
 */
@Configuration
public class RedisConfig {

    /**
     * 设置redisTemplate 的serializer
     * RedisTemplate可以接收任意Object作为值写入redis，在写入前会把Object序列化为字节，默认使用JDK的序列化（可读性差，占用内存大）
     * @param connectionFactory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = {"redisTemplate1"})
    public RedisTemplate<String, Object> redisTemplate1(RedisConnectionFactory connectionFactory) {
        //创建redisTempLate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //设置连接工厂
        template.setConnectionFactory(connectionFactory);
        //创建 json处理工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //设置key的序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        //设置value的序列化
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);

        return template;
    }
    public void test(){

    }
}
