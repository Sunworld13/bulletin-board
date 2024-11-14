package com.example.newauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NewAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewAuthApplication.class, args);
    }

}
