package com.roomsmanager.configurations;

import com.roomsmanager.services.FooBarService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthConfig {

    @Bean(name = "healthFooBarService")
    public FooBarService provideFooBarService() {
        return () -> "I am from health configuration";
    }

}
