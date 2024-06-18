package com.roomsmanager.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomsServiceTest {

    @Test
    void calculateRoomsRevenue_success_case_1() {
        assertThat("foo").isEqualTo("bar");
    }

    @Test
    void calculateRoomsRevenue_success_case_2() {
        assertThat("foo").isEqualTo("bar");
    }

    @Test
    void calculateRoomsRevenue_success_case_3() {
        assertThat("foo").isEqualTo("bar");
    }

    @Test
    void calculateRoomsRevenue_success_case_4() {
        assertThat("foo").isEqualTo("bar");
    }

    @Test
    void calculateRoomsRevenue_fail_invalid_data() {
        assertThat("foo").isEqualTo("bar");
    }
}
