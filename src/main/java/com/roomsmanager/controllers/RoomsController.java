package com.roomsmanager.controllers;

import com.roomsmanager.models.RoomsRevenue;
import com.roomsmanager.services.FooBarService;
import com.roomsmanager.services.RoomsService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.roomsmanager.services.RoomsServiceImpl.defaultUserPayments;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;
    @Qualifier("roomFooBarService")
    @Autowired
    private FooBarService fooBarService;


    @GetMapping("/occupied-revenue")
    public ResponseEntity<RoomsRevenue> calculateRevenue(
            @RequestParam @Min(0) int freePremiumRooms,
            @RequestParam @Min(0) int freeEconomyRooms) {

        final var resp = roomsService.calculateRoomsRevenue(freePremiumRooms, freeEconomyRooms, defaultUserPayments);

        return resp.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));

    }

    @GetMapping("/info")
    public String configInfo() {
        return fooBarService.getAnswer();
    }
}
