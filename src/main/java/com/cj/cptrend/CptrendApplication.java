package com.cj.cptrend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cj.cptrend.*.**.mapper")
public class CptrendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CptrendApplication.class, args);
    }

}
