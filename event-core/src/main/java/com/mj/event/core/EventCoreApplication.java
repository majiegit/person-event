package com.mj.event.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mj.event.core.core.mapper")
public class EventCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventCoreApplication.class, args);
    }

}
