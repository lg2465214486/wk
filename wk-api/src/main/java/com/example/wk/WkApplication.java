package com.example.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author：luogan
 * DATE：2023-11-2023/11/20 21:59
 * Description：<描述>
 */
@SpringBootApplication
@MapperScan({"com.example.wk.mapper"})
public class WkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WkApplication.class, args);
    }

}
