package com.roomsmanager.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ping_success() throws JSONException {
        final var freePremium = 3;
        final var freeEconom = 3;

        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freePremiumRooms=" + freePremium + "&freeEconomyRooms=" + freeEconom, HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "foo":"bar",
                        }
                        """,
                r.getBody(),
                true);
    }

    @Test
    public void ping_fail_invalid_parameters() throws JSONException {
        final var freePremium = -1;
        final var freeEconom = -1;

        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freePremiumRooms=" + freePremium + "&freeEconomyRooms=" + freeEconom, HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "foo":"bar",
                        }
                        """,
                r.getBody(),
                true);
    }

    @Test
    public void ping_fail_missing_parameters() throws JSONException {
        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue", HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "foo":"bar",
                        }
                        """,
                r.getBody(),
                true);
    }

}
