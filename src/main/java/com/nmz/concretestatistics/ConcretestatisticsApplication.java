package com.nmz.concretestatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nmz.concretestatistics.mapper")
@SpringBootApplication
public class ConcretestatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcretestatisticsApplication.class, args);
    }

}
