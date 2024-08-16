package com.roomsmanager.configurations;

import com.roomsmanager.services.FooBarService;
import com.roomsmanager.services.RoomsService;
import com.roomsmanager.services.RoomsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomsConfig {

    @Bean(name = "roomFooBarService")
    public FooBarService provideFooBarService() {
        return () -> "I am from rooms configuration";
    }

    @Bean
    public RoomsService roomsService() {
        return new RoomsServiceImpl();
    }
}
