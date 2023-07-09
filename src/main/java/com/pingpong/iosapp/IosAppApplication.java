package com.pingpong.iosapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IosAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(IosAppApplication.class, args);
    }

}
