package com.example.discoveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryAppApplication.class, args);
    }

}
