package com.pingpong.quoteBakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QuoteBakeryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteBakeryApplication.class, args);
    }

}
