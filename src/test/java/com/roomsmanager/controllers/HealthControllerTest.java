package com.roomsmanager.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPing() {
        final var r = restTemplate.exchange("/ping", HttpMethod.GET, null, String.class);

        assertThat(r.getBody()).isEqualTo("pong");
    }

}
