package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomsServiceTest {

    final float[] payments = {23, 45, 155, 374, 22, 99.99f, 100, 101, 115, 209};

    @Autowired
    RoomsService roomsService;

    @Test
    void calculateRoomsRevenue_success_case_1() {
        final var r = roomsService.calculateRoomsRevenue(3, 3, payments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                3, 738,
                3, 167.99f));
    }

    @Test
    void calculateRoomsRevenue_success_case_2() {
        final var r = roomsService.calculateRoomsRevenue(3, 3, payments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                6, 1054,
                4, 189.99f));
    }

    @Test
    void calculateRoomsRevenue_success_case_3() {
        final var r = roomsService.calculateRoomsRevenue(3, 3, payments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                2, 583,
                4, 189.99f));
    }

    @Test
    void calculateRoomsRevenue_success_case_4() {
        final var r = roomsService.calculateRoomsRevenue(3, 3, payments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                7, 1153,
                1, 45.99f));
    }

    @Test
    void calculateRoomsRevenue_fail_invalid_data() {

        assertThat(roomsService.calculateRoomsRevenue(-1, 3, payments))
                .isNotPresent();
        assertThat(roomsService.calculateRoomsRevenue(3, -1, payments))
                .isNotPresent();
        assertThat(roomsService.calculateRoomsRevenue(3, 1, new float[]{}))
                .isNotPresent();
    }
}
