package com.roomsmanager.controllers;

import com.roomsmanager.models.RoomsRevenue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @GetMapping("/occupied-revenue")
    public ResponseEntity<RoomsRevenue> validateParameters(
            @RequestParam @NotNull @Min(0) int freePremiumRooms,
            @RequestParam @NotNull @Min(0) int freeEconomyRooms) {

        final var resp = new RoomsRevenue(freePremiumRooms, 0, freeEconomyRooms, 0);

        return ResponseEntity.ok(resp);

    }
}
