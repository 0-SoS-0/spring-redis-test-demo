package com.example.springdataredisdemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //无参构造（使用注解方式的构造函数）
@AllArgsConstructor     //有参构造
public class User {
    private String name;
    private String age;

}
