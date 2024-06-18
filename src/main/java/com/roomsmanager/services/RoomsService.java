package com.roomsmanager.services;

import com.roomsmanager.models.RoomsRevenue;

import java.util.List;
import java.util.Optional;

public interface RoomsService {

    Optional<RoomsRevenue> calculateRoomsRevenue(int freePremiumRooms, int freeEconomyRooms, List<Double> payments);
}
