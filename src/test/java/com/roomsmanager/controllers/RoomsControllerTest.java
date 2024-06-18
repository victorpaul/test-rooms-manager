package com.roomsmanager.controllers;

import com.roomsmanager.models.RoomsRevenue;
import com.roomsmanager.services.RoomsService;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.roomsmanager.services.RoomsServiceImpl.defaultUserPayments;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomsControllerTest {

    @MockBean
    RoomsService roomsService;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void afterEach() {
        Mockito.verifyNoMoreInteractions(roomsService);
        Mockito.reset(roomsService);
    }

    @Test
    public void calculateRevenue_success() throws JSONException {
        Mockito.when(roomsService.calculateRoomsRevenue(anyInt(), anyInt(), anyList())).thenReturn(Optional.of(
                new RoomsRevenue(0, 0, 0, 0)
        ));
        final var freePremium = 1;
        final var freeEconom = 2;

        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freePremiumRooms=" + freePremium + "&freeEconomyRooms=" + freeEconom, HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(
                """
                        {
                            "premiumUsage":0,
                            "premiumRevenue":0.0,
                            "economyUsage":0,
                            "economyRevenue":0.0
                        }
                        """,
                r.getBody(),
                true);

        Mockito.verify(roomsService).calculateRoomsRevenue(1, 2, defaultUserPayments);
    }

    @Test
    public void calculateRevenue_fail_invalid_parameters() throws JSONException {
        final var freePremium = -1;
        final var freeEconom = -1;

        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freePremiumRooms=" + freePremium + "&freeEconomyRooms=" + freeEconom, HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "status":400,
                            "error":"Bad Request",
                            "message":"Validation failed for method='public org.springframework.http.ResponseEntity com.roomsmanager.controllers.RoomsController.calculateRevenue(int,int)'. Error count: 0",
                            "errors":[],
                            "path":"/api/v1/rooms/occupied-revenue"
                        }
                        """,
                r.getBody(),
                false);
    }

    @Test
    public void calculateRevenue_fail_missing_parameters_freePremiumRooms() throws JSONException {
        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freeEconomyRooms=1", HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "status":400,
                            "error":"Bad Request",
                            "message":"Required parameter 'freePremiumRooms' is not present.",
                            "path":"/api/v1/rooms/occupied-revenue"
                        }
                        }
                        """,
                r.getBody(),
                false);
    }

    @Test
    public void calculateRevenue_fail_missing_parameters_freeEconomyRooms() throws JSONException {
        final var r = restTemplate.exchange("/api/v1/rooms/occupied-revenue?freePremiumRooms=1", HttpMethod.GET, null, String.class);

        assertThat(r.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.out.println(r.getBody());
        JSONAssert.assertEquals(
                """
                        {
                            "status":400,
                            "error":"Bad Request",
                            "message":"Required parameter 'freeEconomyRooms' is not present.",
                            "path":"/api/v1/rooms/occupied-revenue"
                        }
                        }
                        """,
                r.getBody(),
                false);
    }

}
