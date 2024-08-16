package com.roomsmanager.controllers;

import com.roomsmanager.services.FooBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HealthController {

    private final FooBarService fooBarService;

    @Autowired
    public HealthController(@Qualifier("healthFooBarService") FooBarService healthFooBarService) {
        this.fooBarService = healthFooBarService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/info")
    public String configInfo() {
        return fooBarService.getAnswer();
    }
}
