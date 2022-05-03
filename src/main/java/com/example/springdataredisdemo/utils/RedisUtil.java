package com.example.springdataredisdemo.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    private final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public <E, R> void printArray(E[] e, Class<R> type, String name, String age) {
        for (E e1 : e) {
            System.out.print("\t" + e1);
        }
        System.out.println("\n-----------------------------");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        R r = JSONObject.toJavaObject(jsonObject, type);

        logger.info("result of json to Object :{}", r);

    }
}
