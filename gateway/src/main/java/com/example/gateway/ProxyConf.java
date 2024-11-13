package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class ProxyConf {
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auht-reg",
                        route -> route.path("/tes/**")
                                .and()
                                .method(HttpMethod.POST)
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://auth-reg"))
                .route("adv",
                        route -> route.path("/advertisement/**")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(filter -> filter.stripPrefix(1))
                                .uri("lb://advertisement"))


                .build();
    }
}
