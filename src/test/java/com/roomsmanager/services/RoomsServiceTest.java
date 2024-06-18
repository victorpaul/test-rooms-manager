package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.roomsmanager.services.RoomsServiceImpl.defaultUserPayments;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoomsServiceTest {

    @Qualifier("roomsServiceImpl")
    @Autowired
    RoomsService roomsService;

    @Test
    void calculateRoomsRevenue_success_case_1() {
        final var r = roomsService.calculateRoomsRevenue(3, 3, defaultUserPayments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                3, 738,
                3, 167.99));
    }

    @Test
    void calculateRoomsRevenue_success_case_2() {
        final var r = roomsService.calculateRoomsRevenue(7, 5, defaultUserPayments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                6, 1054,
                4, 189.99));
    }

    @Test
    void calculateRoomsRevenue_success_case_3() {
        final var r = roomsService.calculateRoomsRevenue(2, 7, defaultUserPayments);

        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                2, 583,
                4, 189.99));
    }

    @Test
    void calculateRoomsRevenue_success_case_4() {
        final var r = roomsService.calculateRoomsRevenue(7, 1, defaultUserPayments);

        // NOTES, I think in the test task was a mistake, desired result was
        // (output) Usage Premium: 7 (EUR 1153)
        // (output) Usage Economy: 1 (EUR 45.99)
        // the correct result is
        // (output) Usage Premium: 7 (EUR 1153.99)
        // (output) Usage Economy: 1 (EUR 45)
        assertThat(r.get()).isEqualTo(new RoomsRevenue(
                7, 1153.99,
                1, 45));
    }

    @Test
    void calculateRoomsRevenue_fail_invalid_data() {

        assertThat(roomsService.calculateRoomsRevenue(-1, 3, defaultUserPayments))
                .isNotPresent();
        assertThat(roomsService.calculateRoomsRevenue(3, -1, defaultUserPayments))
                .isNotPresent();
        assertThat(roomsService.calculateRoomsRevenue(3, 1, List.of()))
                .isNotPresent();
    }
}
