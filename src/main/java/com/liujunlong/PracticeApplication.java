package com.liujunlong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/29 13:07
 */
@SpringBootApplication
@MapperScan(basePackages = "com.liujunlong.dao")
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

}
