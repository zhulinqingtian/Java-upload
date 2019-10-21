package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ConsumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumApplication.class, args);
    }

}
