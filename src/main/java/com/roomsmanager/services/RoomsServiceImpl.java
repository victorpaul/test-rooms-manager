package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;

import java.util.Optional;

public class RoomsServiceImpl implements RoomsService {
    @Override
    public Optional<RoomsRevenue> calculateRoomsRevenue(int freePremiumRooms, int freeEconomyRooms, float[] payments) {

        return Optional.empty();
    }
}
